package com.FriedTaco.taco.godPowers;


import com.FriedTaco.taco.godPowers.util.*;
import com.FriedTaco.taco.godPowers.util.Metrics.Plotter;
import com.FriedTaco.taco.godPowers.util.Updater.UpdateResult;
import com.FriedTaco.taco.godPowers.commands.*;
import com.FriedTaco.taco.godPowers.listeners.EntityListener;
import com.FriedTaco.taco.godPowers.listeners.PlayerListener;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class godPowers extends JavaPlugin {
    @SuppressWarnings("unused")
    public String title = "";
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    public HashMap<UUID, Integer> curse = new HashMap<UUID, Integer>();
    public ArrayList<UUID> godmodeEnabled = new ArrayList<UUID>();
    public ArrayList<UUID> isJesus = new ArrayList<UUID>();
    public ArrayList<MedusaPlayer> isUnderMedusaInfluence = new ArrayList<MedusaPlayer>();
    public ArrayList<UUID> isInferno = new ArrayList<UUID>();
    public ArrayList<UUID> isHermes = new ArrayList<UUID>();
    public ArrayList<UUID> isPoseidon = new ArrayList<UUID>();
    public ArrayList<UUID> isMedusa = new ArrayList<UUID>();
    public ArrayList<UUID> superJumper = new ArrayList<UUID>();
    public ArrayList<UUID> arrowKill = new ArrayList<UUID>();
    public ArrayList<UUID> burn = new ArrayList<UUID>();
    public ArrayList<UUID> gaia = new ArrayList<UUID>();
    public ArrayList<UUID> isZeus = new ArrayList<UUID>();
    public ArrayList<UUID> isVulcan = new ArrayList<UUID>();
    public ArrayList<UUID> DemiGod = new ArrayList<UUID>();
    public ArrayList<UUID> hades = new ArrayList<UUID>();
    public ArrayList<UUID> die = new ArrayList<UUID>();
    public ArrayList<Material> shovelDrops = new ArrayList<Material>();
    public ArrayList<Material> pickDrops = new ArrayList<Material>();
    public ArrayList<Material> axeDrops = new ArrayList<Material>();
    public HashMap<String, String> list = new HashMap<String, String>();
    public double demiModifier = 0.2;
    public boolean godModeOnLogin = true;
    public boolean godTools = true;
    public boolean autoUpdate = false;
    public boolean checkUpdate = true;
    public boolean uploadToList = true;
    public int medusaFreezeTime = 10;
    public boolean developerJoinEffect = true;
    public File file;

    public void loadConfig() {
        try {
            this.saveDefaultConfig();
            if (!this.getConfig().contains("GodModeTitle"))
                this.getConfig().set("GodModeTitle", "[God] ");
            if (!this.getConfig().contains("GodModeOnLogin"))
                this.getConfig().set("GodModeOnLogin", true);
            if (!this.getConfig().contains("DemiGodDamageModifier"))
                this.getConfig().set("DemiGodDamageModifier", 0.2);
            if (!this.getConfig().contains("GodToolsEnabled"))
                this.getConfig().set("GodToolsEnabled", true);
            if (!this.getConfig().contains("AutoUpdateEnabled"))
                this.getConfig().set("AutoUpdateEnabled", false);
            if (!this.getConfig().contains("CheckUpdateEnabled"))
                this.getConfig().set("CheckUpdateEnabled", true);
            if (!this.getConfig().contains("UploadToServerList"))
                this.getConfig().set("UploadToServerList", true);
            if (!this.getConfig().contains("MedusaFreezeTime"))
                this.getConfig().set("MedusaFreezeTime", 10);
            if (!this.getConfig().contains("DeveloperJoinEffect"))
                this.getConfig().set("DeveloperJoinEffect", true);
            checkUpdate = this.getConfig().getBoolean("CheckUpdateEnabled", false);
            autoUpdate = this.getConfig().getBoolean("AutoUpdateEnabled", false);
            uploadToList = this.getConfig().getBoolean("UploadToServerList", true);
            title = this.getConfig().getString("GodModeTitle", "");
            godModeOnLogin = this.getConfig().getBoolean("GodModeOnLogin", true);
            demiModifier = this.getConfig().getDouble("DemiGodDamageModifier", 0.2);
            godTools = this.getConfig().getBoolean("GodToolsEnabled", true);
            medusaFreezeTime = this.getConfig().getInt("MedusaFreezeTime", 10);
            developerJoinEffect = this.getConfig().getBoolean("DeveloperJoinEffect", true);
            this.saveConfig();
        } catch (Exception e) {
            getLogger().warning("Error loading config file.");
            e.printStackTrace();
        }
    }

    public void onDisable() {
    }

    @Override
    public void onEnable() {
        file = this.getFile();
        loadConfig();
        StringHandler.init(this);
        @SuppressWarnings("unused")
        BukkitTask TaskName = new OnOneSecond(this).runTaskTimer(this, 25, 25);
        try {
            Metrics metrics = new Metrics(this);
            // Plot the total amount of protections
            metrics.addCustomData(new Plotter("Total Players using Godmode") {

                @Override
                public int getValue() {
                    return godmodeEnabled.size();
                }

            });
            metrics.start();
            getLogger().info("Successfully sent stats to MCStats/Metrics");
        } catch (IOException e) {
            getLogger().info("Failed to send stats to MCStats/Metrics :-(");
            // Failed to submit the stats :-(
        }
        if (checkUpdate == true) {
            Updater updater = new Updater(this, 33866, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false); // Start Updater but just do a version check
            if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
                this.getServer().broadcastMessage(ChatColor.RED + "An update is available: " + updater.getLatestName() + ", a " + updater.getLatestType() + " for " + updater.getLatestGameVersion() + " available at " + updater.getLatestFileLink());
                if (autoUpdate == true) {
                    new Updater(this, 33866, this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
                    this.getServer().broadcastMessage(ChatColor.RED + "An update is available: " + updater.getLatestName() + ", downloading and updating automatically..");
                }
            }
        }
        if (uploadToList) {
            String ip = null;
            try {
                URL whatismyip = new URL("http://checkip.amazonaws.com");
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        whatismyip.openStream()));
                ip = in.readLine(); //you get the IP as a String
            } catch (Exception e) {
                getLogger().info("Unable to add server to GodPowers' server list");
                e.printStackTrace();
            }
            try {
                String data = this.getServer().toString();
                data = data.substring(12, data.length() - 1);
                data = data.split(",")[2].substring(17);
                String url = "http://jfkingsley.co.uk/godPowers.php?IP=" + URLEncoder.encode(ip, "UTF-8") + "&MOTD=" + URLEncoder.encode(getServer().getMotd(), "UTF-8") + "&VERSION=" + URLEncoder.encode(data, "UTF-8");
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                int responseCode = con.getResponseCode();
                getLogger().info("Response Code from server list: " + responseCode);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // We really don't want to see this nasty output :/
                //getLogger().info("Response: " + response);
            } catch (Exception e) {
                getLogger().info("Unable to add server to GodPowers' server list");
                e.printStackTrace();
            }
        }
        String error = "ERROR another plugin has already taken the command ";
        try {
            getCommand("zeus").setExecutor(new ZeusCommand(this));
            list.put("zeus", "- " + StringHandler.LIST_ZEUS_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "zeus.");
        }
        try {
            getCommand("godmode").setExecutor(new GodmodeCommand(this));
            list.put("godmode", "[Player] - " + StringHandler.LIST_GODMODE_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "godmode.");
        }
        try {
            getCommand("godmodeon").setExecutor(new GodmodeCommand(this));
        } catch (Exception e) {
            getLogger().warning(error + "godmodeon.");
        }
        try {
            getCommand("godmodeoff").setExecutor(new GodmodeCommand(this));
        } catch (Exception e) {
            getLogger().warning(error + "godmodeoff.");
        }
        try {
            getCommand("jesus").setExecutor(new JesusCommand(this));
            list.put("jesus", "- " + StringHandler.LIST_JESUS_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "jesus.");
        }
        try {
            getCommand("die").setExecutor(new DieCommand(this));
            list.put("die", "- " + StringHandler.LIST_DIE_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "die.");
        }
        try {
            getCommand("slay").setExecutor(new SlayCommand(this));
            list.put("slay", "[Player] <arrows/fire/drop> - " + StringHandler.LIST_SLAY_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "slay.");
        }
        try {
            getCommand("smite").setExecutor(new SlayCommand(this));
            list.put("smite", "[Player] <arrows/fire/drop> - " + StringHandler.LIST_SLAY_DESCRIPTION);
        } catch (Exception e1) {
            getLogger().warning(error + "smite.");
        }
        try {
            getCommand("maim").setExecutor(new MaimCommand(this));
            list.put("maim", "[Player] - " + StringHandler.LIST_MAIM_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "maim.");
        }
        try {
            getCommand("inferno").setExecutor(new InfernoCommand(this));
            list.put("inferno", "- " + StringHandler.LIST_INFERNO_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "inferno.");
        }
        try {
            getCommand("superjump").setExecutor(new SuperjumpCommand(this));
            list.put("superjump", "- " + StringHandler.LIST_SUPERJUMP_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "superjump.");
        }
        try {
            getCommand("gaia").setExecutor(new GaiaCommand(this));
            list.put("gaia", "- " + StringHandler.LIST_GAIA_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "gaia.");
        }
        try {
            getCommand("heal").setExecutor(new HealCommand(this));
            list.put("heal", "<Player> - " + StringHandler.LIST_HEAL_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "heal.");
        }
        try {
            getCommand("godpowers").setExecutor(new GodPowersCommand(this));
            list.put("godpowers", "- Displays this message.");
        } catch (Exception e) {
            getLogger().warning(error + "godpowers. How dare they!");
        }
        try {
            getCommand("vulcan").setExecutor(new VulcanCommand(this));
            list.put("vulcan", "- " + StringHandler.LIST_VULCAN_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "vulcan.");
        }
        try {
            getCommand("myballsareonfire").setExecutor(new VulcanCommand(this));
        } catch (Exception e) {
            getLogger().warning(error + "myballsareonfire.");
        }
        try {
            getCommand("demigod").setExecutor(new DemigodCommand(this));
            list.put("demigod", "- " + StringHandler.LIST_DEMIGOD_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "demigod.");
        }
        try {
            getCommand("hades").setExecutor(new HadesCommand(this));
            list.put("hades", "- " + StringHandler.LIST_HADES_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "hades.");
        }
        try {
            getCommand("bless").setExecutor(new BlessCommand(this));
            list.put("bless", "[Player] - " + StringHandler.LIST_BLESS_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "bless.");
        }
        try {
            getCommand("fusrodah").setExecutor(new FusrodahCommand(this));
            list.put("FusRoDAH", "- " + StringHandler.LIST_FUSRODAH_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "FusRoDAHCommand.");
        }
        try {
            getCommand("plutus").setExecutor(new PlutusCommand(this));
            list.put("plutus", "- " + StringHandler.LIST_PLUTUS_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "plutus");
        }
        try {
            getCommand("dupe").setExecutor(new DupeCommand(this));
            list.put("dupe", "<amount> - " + StringHandler.LIST_DUPE_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "dupe.");
        }
        try {
            getCommand("medusa").setExecutor(new MedusaCommand(this));
            list.put("medusa", "- " + StringHandler.LIST_MEDUSA_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "medusa.");
        }
        try {
            getCommand("hermes").setExecutor(new HermesCommand(this));
            list.put("hermes", "- " + StringHandler.LIST_HERMES_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "hermes. ");
        }
        try {
            getCommand("poseidon").setExecutor(new PoseidonCommand(this));
            list.put("poseidon", "- " + StringHandler.LIST_POSEIDON_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "poseidon.");
        }
        try {
            getCommand("repair").setExecutor(new RepairCommand(this));
            list.put("repair", "- " + StringHandler.LIST_REPAIR_DESCRIPTION);
        } catch (Exception e) {
            getLogger().warning(error + "repair.");
        }
        try {
            getCommand("itemrepair").setExecutor(new RepairCommand(this));
        } catch (Exception e) {
            getLogger().warning(error + "itemrepair.");
        }

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EntityListener(this), this);
        pm.registerEvents(new PlayerListener(this), this);
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info("version " + pdfFile.getVersion() + " is enabled!");
        populateLists();
    }

    public void populateLists() {
        shovelDrops.add(Material.GRASS);
        shovelDrops.add(Material.DIRT);
        shovelDrops.add(Material.SAND);
        shovelDrops.add(Material.GRAVEL);
        shovelDrops.add(Material.CLAY);
        shovelDrops.add(Material.SNOW_BLOCK);
        shovelDrops.add(Material.SOUL_SAND);
        pickDrops.add(Material.STONE);
        pickDrops.add(Material.SMOOTH_BRICK);
        pickDrops.add(Material.COBBLESTONE);
        pickDrops.add(Material.GOLD_ORE);
        pickDrops.add(Material.IRON_ORE);
        pickDrops.add(Material.COAL_ORE);
        pickDrops.add(Material.COAL_BLOCK);
        pickDrops.add(Material.LAPIS_ORE);
        pickDrops.add(Material.LAPIS_BLOCK);
        pickDrops.add(Material.EMERALD_ORE);
        pickDrops.add(Material.EMERALD_BLOCK);
        pickDrops.add(Material.SANDSTONE);
        pickDrops.add(Material.GOLD_BLOCK);
        pickDrops.add(Material.IRON_BLOCK);
        pickDrops.add(Material.DOUBLE_STEP);
        pickDrops.add(Material.REDSTONE_ORE);
        pickDrops.add(Material.REDSTONE_BLOCK);
        pickDrops.add(Material.STEP);
        pickDrops.add(Material.BRICK);
        pickDrops.add(Material.MOSSY_COBBLESTONE);
        pickDrops.add(Material.OBSIDIAN);
        pickDrops.add(Material.DIAMOND_ORE);
        pickDrops.add(Material.DIAMOND_BLOCK);
        pickDrops.add(Material.NETHERRACK);
        pickDrops.add(Material.NETHER_BRICK);
        pickDrops.add(Material.QUARTZ_ORE);
        pickDrops.add(Material.QUARTZ_BLOCK);
        pickDrops.add(Material.CLAY_BRICK);
        pickDrops.add(Material.STAINED_CLAY);
        pickDrops.add(Material.ENDER_STONE);
        axeDrops.add(Material.WOOD);
        axeDrops.add(Material.WOOD_STEP);
        axeDrops.add(Material.WOOD_DOUBLE_STEP);
        axeDrops.add(Material.WOOD_STAIRS);
        axeDrops.add(Material.LOG);
        axeDrops.add(Material.LOG_2);

    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }

    public void dropDeadItems(Player player) {
        if (player.getInventory() != null) {
            ItemStack[] item = player.getInventory().getContents();
            Location position = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
            for (ItemStack anItem : item) {
                if (anItem != null && anItem.getType() != Material.AIR) {
                    player.getWorld().dropItemNaturally(position, anItem);
                }
            }
        }
    }

    public void arrowSlay(Location arrows, World world, Player player) {
        arrows = new Location(world, player.getLocation().getX() + 2, player.getLocation().getY() + 1, player.getLocation().getZ() + 2);
        Arrow arrow = world.spawnArrow(arrows, new Vector(5, 1, 5), 2.0f, 4.0f);
        arrow.setFireTicks(100);
        arrow.teleport((Entity) player);
    }

    public void bless(Player p) {
        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null) {
                switch (i.getType()) {
                    case IRON_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case IRON_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case IRON_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case BOW:
                        i.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
                        i.addEnchantment(Enchantment.ARROW_FIRE, 1);
                        i.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                        break;
                    case IRON_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    case WOOD_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    case WOOD_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case WOOD_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case WOOD_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case STONE_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    case STONE_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case STONE_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case STONE_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case DIAMOND_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    case DIAMOND_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case DIAMOND_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case DIAMOND_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case GOLD_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    case GOLD_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case GOLD_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case GOLD_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case LEATHER_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    default:
                        break;
                }
            }
        }
        for (ItemStack i : p.getInventory().getArmorContents()) {
            if (i != null) {
                switch (i.getType()) {
                    case LEATHER_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}




