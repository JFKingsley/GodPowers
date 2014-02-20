package com.FriedTaco.taco.godPowers;


import com.FriedTaco.taco.godPowers.Metrics.Plotter;
import com.FriedTaco.taco.godPowers.Updater.UpdateResult;
import com.FriedTaco.taco.godPowers.commands.*;
import com.FriedTaco.taco.godPowers.listeners.EntityListener;
import com.FriedTaco.taco.godPowers.listeners.PlayerListener;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R1.CraftServer;
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
import java.util.logging.Logger;


public class godPowers extends JavaPlugin {
    @SuppressWarnings("unused")
    private Logger log;
    public String title = "";
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    public HashMap<String, Integer> curse = new HashMap<String, Integer>();
    public ArrayList<String> godmodeEnabled = new ArrayList<String>();
    public ArrayList<String> isJesus = new ArrayList<String>();
    public ArrayList<MedusaPlayer> isUnderMedusaInfluence = new ArrayList<MedusaPlayer>();
    public ArrayList<String> isInferno = new ArrayList<String>();
    public ArrayList<String> isHermes = new ArrayList<String>();
    public ArrayList<String> isPoseidon = new ArrayList<String>();
    public ArrayList<String> isMedusa = new ArrayList<String>();
    public ArrayList<String> superJumper = new ArrayList<String>();
    public ArrayList<String> arrowKill = new ArrayList<String>();
    public ArrayList<String> burn = new ArrayList<String>();
    public ArrayList<String> gaia = new ArrayList<String>();
    public ArrayList<String> isZeus = new ArrayList<String>();
    public ArrayList<String> isVulcan = new ArrayList<String>();
    public ArrayList<String> DemiGod = new ArrayList<String>();
    public ArrayList<String> hades = new ArrayList<String>();
    public ArrayList<Material> shovelDrops = new ArrayList<Material>();
    public ArrayList<Material> pickDrops = new ArrayList<Material>();
    public ArrayList<Material> axeDrops = new ArrayList<Material>();
    public HashMap<String, String> list = new HashMap<String, String>();
    public double DemiModifier = 0;
    public boolean godModeOnLogin = true;
    public boolean godTools = true;
    public boolean autoUpdate = false;
    public boolean checkUpdate = true;
    public boolean uploadToList = true;
    public int medusaFreezeTime = 10;
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
            checkUpdate = this.getConfig().getBoolean("CheckUpdateEnabled", false);
            autoUpdate = this.getConfig().getBoolean("AutoUpdateEnabled", false);
            uploadToList = this.getConfig().getBoolean("UploadToServerList", true);
            title = this.getConfig().getString("GodModeTitle", "");
            godModeOnLogin = this.getConfig().getBoolean("GodModeOnLogin", true);
            DemiModifier = this.getConfig().getDouble("DemiGodDamageModifier", 0.2);
            godTools = this.getConfig().getBoolean("GodToolsEnabled", true);
            medusaFreezeTime = this.getConfig().getInt("MedusaFreezeTime", 10);
            this.saveConfig();
        } catch (Exception e) {
            System.out.println("[GodPowers] Error loading config file.");
            e.printStackTrace();
        }
    }

    public void onDisable() {
    }

    @Override
    public void onEnable() {
        file = this.getFile();
        loadConfig();
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
            System.out.println("[GodPowers] Successfully sent stats to MCStats/Metrics ");
        } catch (IOException e) {
            System.out.println("[GodPowers] Failed to send stats to MCStats/Metrics :-(");
            // Failed to submit the stats :-(
        }
        if (checkUpdate == true) {
            Updater updater = new Updater(this, 33866, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false); // Start Updater but just do a version check
            if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
                this.getServer().broadcastMessage(ChatColor.RED + "[GodPowers] An update is available: " + updater.getLatestName() + ", a " + updater.getLatestType() + " for " + updater.getLatestGameVersion() + " available at " + updater.getLatestFileLink());
                if (autoUpdate == true) {
                    new Updater(this, 33866, this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
                    this.getServer().broadcastMessage(ChatColor.RED + "[GodPowers] An update is available: " + updater.getLatestName() + ", downloading and updating automatically..");
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
                e.printStackTrace();
            }
            try {
                String data = ((CraftServer) this.getServer()).toString();
                data = data.substring(12, data.length() - 1);
                data = data.split(",")[2].substring(17);
                String url = "http://jfkingsley.co.uk/godPowers.php?IP=" + URLEncoder.encode(ip, "UTF-8") + "&MOTD=" + URLEncoder.encode(getServer().getMotd(), "UTF-8") + "&VERSION=" + URLEncoder.encode(data, "UTF-8");
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                int responseCode = con.getResponseCode();
                System.out.println("Response Code from server list: " + responseCode);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println("Response: " + response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String error = "[GodPowers] ERROR another plugin has already taken the command ";
        try {
            getCommand("zeus").setExecutor(new ZeusCommand(this));
            list.put("zeus", "- Strike lightning with a swing of your arm!");
        } catch (Exception e) {
            System.out.println(error + "zeus.");
        }
        try {
            getCommand("godmode").setExecutor(new GodmodeCommand(this));
            list.put("godmode", "<Player> - Toggles godmode on and off.");
        } catch (Exception e) {
            System.out.println(error + "godmode.");
        }
        getCommand("godmodeon").setExecutor(new GodmodeCommand(this));
        getCommand("godmodeoff").setExecutor(new GodmodeCommand(this));
        try {
            getCommand("jesus").setExecutor(new JesusCommand(this));
            list.put("jesus", "<Player> - Allows you to walk on water and lava");
        } catch (Exception e) {
            System.out.println(error + "jesus.");
        }
        try {
            getCommand("die").setExecutor(new DieCommand(this));
            list.put("die", "- Causes you to die.");
        } catch (Exception e) {
            System.out.println(error + "die.");
        }
        try {
            getCommand("slay").setExecutor(new SlayCommand(this));
            list.put("slay", "[Player] <arrows/fire/drop> - Kills a player with/without the optional method.");
        } catch (Exception e) {
            System.out.println(error + "slay.");
            try {
                getCommand("smite").setExecutor(new SlayCommand(this));
                System.out.println(error + "slay, registering smite in place of slay.");
                list.put("smite", "[Player] <arrows/fire/drop> - Kills a player with/without the optional method.");
            } catch (Exception e1) {
                System.out.println(error + "smite in place of slay.");
            }
        }
        try {
            getCommand("maim").setExecutor(new MaimCommand(this));
            list.put("maim", "[Player] - Beat a player within an inch of their life!");
        } catch (Exception e) {
            System.out.println(error + "maim.");
        }
        try {
            getCommand("inferno").setExecutor(new InfernoCommand(this));
            list.put("inferno", "- Creates a trail of fire behind you.");
        } catch (Exception e) {
            System.out.println(error + "inferno.");
        }
        try {
            getCommand("superjump").setExecutor(new SuperjumpCommand(this));
            list.put("superjump", "- Be able to leap tall building in a single bound!");
        } catch (Exception e) {
            System.out.println(error + "superjump.");
        }
        try {
            getCommand("gaia").setExecutor(new GaiaCommand(this));
            list.put("gaia", "- Sprouts grass and flowers wherever you step.");
        } catch (Exception e) {
            System.out.println(error + "gaia.");
        }
        try {
            getCommand("heal").setExecutor(new HealCommand(this));
            list.put("heal", "<Player> - Heals either you or the specified player.");
        } catch (Exception e) {
            System.out.println(error + "heal.");
        }
        try {
            getCommand("godpowers").setExecutor(new GodPowersCommand(this));
            list.put("godpowers", "- Displays this message.");
        } catch (Exception e) {
            System.out.println(error + "godpowers. How dare they!");
        }
        try {
            getCommand("vulcan").setExecutor(new VulcanCommand(this));
            list.put("vulcan", "- Fling fireballs at those pesky mortals!");
        } catch (Exception e) {
            System.out.println(error + "vulcan.");
        }
        try {
            getCommand("demigod").setExecutor(new DemigodCommand(this));
            list.put("demigod", "- Allows you to take a small fraction of the damage you'd normally take.");
        } catch (Exception e) {
            System.out.println(error + "demigod.");
        }
        try {
            getCommand("hades").setExecutor(new HadesCommand(this));
            list.put("hades", "- Corrupt the world as you walk through it.");
        } catch (Exception e) {
            System.out.println(error + "hades.");
        }
        try {
            getCommand("bless").setExecutor(new BlessCommand(this));
            list.put("bless [player]", "- Enchant your equipment with the power of gods!");
        } catch (Exception e) {
            System.out.println(error + "bless.");
        }
        try {
            getCommand("fusrodah").setExecutor(new FusrodahCommand(this));
            list.put("FusrodahCommand", "- Enchants item in hand with Knockback level 10!");
        } catch (Exception e) {
            System.out.println(error + "FusrodahCommand.");
        }
        try {
            getCommand("plutus").setExecutor(new PlutusCommand(this));
            list.put("plutus", "- Enchants item in hand with Wealth level 10!");
        } catch (Exception e) {
            System.out.println(error + "plutus");
        }
        try {
            getCommand("dupe").setExecutor(new DupeCommand(this));
            list.put("dupe <amount>", "- Use your godly powers to create an exact replica of the item you hold!");
        } catch (Exception e) {
            System.out.println(error + "dupe.");
        }
        try {
            getCommand("medusa").setExecutor(new MedusaCommand(this));
            list.put("medusa", "- Become cursed by the gods, and turn anyone you look at to stone.");
        } catch (Exception e) {
            System.out.println(error + "medusa.");
        }
        try {
            getCommand("hermes").setExecutor(new HermesCommand(this));
            list.put("hermes", "- Gives you speed and the ability to send letters.");
        } catch (Exception e) {
            System.out.println(error + "hermes.");
        }
        try {
            getCommand("poseidon").setExecutor(new PoseidonCommand(this));
            list.put("poseidon", "- Gives you Poseidon like powers while in water.");
        } catch (Exception e) {
            System.out.println(error + "poseidon.");
        }

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EntityListener(this), this);
        pm.registerEvents(new PlayerListener(this), this);
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
        populateLists();
    }

    public void populateLists() {
        shovelDrops.add(Material.GRASS);
        shovelDrops.add(Material.DIRT);
        shovelDrops.add(Material.SAND);
        shovelDrops.add(Material.GRAVEL);
        shovelDrops.add(Material.CLAY);
        pickDrops.add(Material.STONE);
        pickDrops.add(Material.COBBLESTONE);
        pickDrops.add(Material.GOLD_ORE);
        pickDrops.add(Material.IRON_ORE);
        pickDrops.add(Material.COAL_ORE);
        pickDrops.add(Material.LAPIS_ORE);
        pickDrops.add(Material.LAPIS_BLOCK);
        pickDrops.add(Material.SANDSTONE);
        pickDrops.add(Material.GOLD_BLOCK);
        pickDrops.add(Material.IRON_BLOCK);
        pickDrops.add(Material.DOUBLE_STEP);
        pickDrops.add(Material.STEP);
        pickDrops.add(Material.BRICK);
        pickDrops.add(Material.MOSSY_COBBLESTONE);
        pickDrops.add(Material.OBSIDIAN);
        pickDrops.add(Material.DIAMOND_ORE);
        pickDrops.add(Material.DIAMOND_BLOCK);
        axeDrops.add(Material.WOOD);
        axeDrops.add(Material.LOG);

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
            for (int x = 0; x < item.length; x++) {
                if (item[x] != null && item[x].getType() != Material.AIR) {
                    player.getWorld().dropItemNaturally(position, item[x]);
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




