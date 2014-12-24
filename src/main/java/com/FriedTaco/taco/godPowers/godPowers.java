package com.FriedTaco.taco.godPowers;


import com.FriedTaco.taco.godPowers.commands.*;
import com.FriedTaco.taco.godPowers.listeners.EntityListener;
import com.FriedTaco.taco.godPowers.listeners.PlayerListener;
import com.FriedTaco.taco.godPowers.util.MedusaPlayer;
import com.FriedTaco.taco.godPowers.util.OnOneSecond;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import net.gravitydevelopment.updater.Updater;
import net.gravitydevelopment.updater.Updater.UpdateResult;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.mcstats.Metrics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class godPowers extends JavaPlugin {
    public String title = "";
    public HashMap<UUID, Integer> curse = new HashMap<UUID, Integer>();
    public ArrayList<UUID> godmodeEnabled = new ArrayList<UUID>();
    public ArrayList<UUID> isJesus = new ArrayList<UUID>();
    public ArrayList<MedusaPlayer> isUnderMedusaInfluence = new ArrayList<MedusaPlayer>();
    public ArrayList<UUID> isInferno = new ArrayList<UUID>();
    public ArrayList<UUID> isHermes = new ArrayList<UUID>();
    public ArrayList<UUID> isPoseidon = new ArrayList<UUID>();
    public ArrayList<UUID> isMedusa = new ArrayList<UUID>();
    public ArrayList<UUID> hasMedusaHead = new ArrayList <UUID>();
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
    public double demiModifier = 0.2;
    public boolean godModeOnLogin = true;
    public boolean godTools = true;
    public boolean autoUpdate = false;
    public boolean checkUpdate = true;
    public boolean uploadToList = true;
    public int medusaFreezeTime = 10;
    public boolean developerJoinEffect = true;
    public boolean medusaDropHead = false;
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
            if (!this.getConfig().contains("MedusaDropHead"))
                this.getConfig().set("MedusaDropHead", false);
            checkUpdate = this.getConfig().getBoolean("CheckUpdateEnabled", false);
            autoUpdate = this.getConfig().getBoolean("AutoUpdateEnabled", false);
            uploadToList = this.getConfig().getBoolean("UploadToServerList", true);
            title = this.getConfig().getString("GodModeTitle", "");
            godModeOnLogin = this.getConfig().getBoolean("GodModeOnLogin", true);
            demiModifier = this.getConfig().getDouble("DemiGodDamageModifier", 0.2);
            godTools = this.getConfig().getBoolean("GodToolsEnabled", true);
            medusaFreezeTime = this.getConfig().getInt("MedusaFreezeTime", 10);
            developerJoinEffect = this.getConfig().getBoolean("DeveloperJoinEffect", true);
            medusaDropHead = this.getConfig().getBoolean("MedusaDropHead", false);
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
        // Because I'm not going to listen to people freak out about gaia flowers breaking things
        // and this is likely the direction ongoing development will go as more features are updated
        if (!this.getServer().getBukkitVersion().contains("1.8")) {
            this.getLogger().severe("============================================================");
            this.getLogger().severe("    This version of GodPowers only supports 1.8 servers!    ");
            this.getLogger().severe("Please update your server or use one of our legacy releases!");
            this.getLogger().severe("============================================================");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        file = this.getFile();
        loadConfig();
        StringHandler.init(this);
        @SuppressWarnings("unused")
        BukkitTask TaskName = new OnOneSecond(this).runTaskTimer(this, 25, 25);
        try {
            Metrics metrics = new Metrics(this);
            // Plot the total amount of protections
            Metrics.Graph graphTotalGM = metrics.createGraph("Total Players using Godmode");
            graphTotalGM.addPlotter(new Metrics.Plotter("Total Players using Godmode") {

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
        if (checkUpdate) {
            Updater updater = new Updater(this, 33866, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false); // Start Updater but just do a version check
            if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
                this.getServer().broadcastMessage(ChatColor.RED + "An update is available: " + updater.getLatestName() + ", a " + updater.getLatestType() + " for " + updater.getLatestGameVersion() + " available at " + updater.getLatestFileLink());
                if (autoUpdate) {
                    new Updater(this, 33866, this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
                    this.getServer().broadcastMessage(ChatColor.RED + "An update is available: " + updater.getLatestName() + ", downloading and updating automatically..");
                }
            }
        }

        // Register commands
        getCommand("bless").setExecutor(new BlessCommand(this));
        getCommand("demigod").setExecutor(new DemigodCommand(this));
        getCommand("die").setExecutor(new DieCommand(this));
        getCommand("dupe").setExecutor(new DupeCommand(this));
        getCommand("fusrodah").setExecutor(new FusrodahCommand(this));
        getCommand("gaia").setExecutor(new GaiaCommand(this));
        getCommand("godmode").setExecutor(new GodmodeCommand(this));
        getCommand("godmodeoff").setExecutor(new GodmodeCommand(this));
        getCommand("godmodeon").setExecutor(new GodmodeCommand(this));
        getCommand("godpowers").setExecutor(new GodPowersCommand(this));
        getCommand("hades").setExecutor(new HadesCommand(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("hermes").setExecutor(new HermesCommand(this));
        getCommand("inferno").setExecutor(new InfernoCommand(this));
        getCommand("itemrepair").setExecutor(new RepairCommand(this));
        getCommand("jesus").setExecutor(new JesusCommand(this));
        getCommand("maim").setExecutor(new MaimCommand(this));
        getCommand("medusa").setExecutor(new MedusaCommand(this));
        getCommand("midas").setExecutor(new MidasCommand(this));
        getCommand("myballsareonfire").setExecutor(new VulcanCommand(this));
        getCommand("plutus").setExecutor(new PlutusCommand(this));
        getCommand("poseidon").setExecutor(new PoseidonCommand(this));
        getCommand("repair").setExecutor(new RepairCommand(this));
        getCommand("slay").setExecutor(new SlayCommand(this));
        getCommand("smite").setExecutor(new SlayCommand(this));
        getCommand("superjump").setExecutor(new SuperjumpCommand(this));
        getCommand("vulcan").setExecutor(new VulcanCommand(this));
        getCommand("zeus").setExecutor(new ZeusCommand(this));

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EntityListener(this), this);
        pm.registerEvents(new PlayerListener(this), this);
        populateLists();
    }

    public void populateLists() {
        axeDrops.add(Material.LOG);
        axeDrops.add(Material.LOG_2);
        axeDrops.add(Material.WOOD);
        axeDrops.add(Material.WOOD_DOUBLE_STEP);
        axeDrops.add(Material.WOOD_STAIRS);
        axeDrops.add(Material.WOOD_STEP);
        pickDrops.add(Material.BRICK);
        pickDrops.add(Material.CLAY_BRICK);
        pickDrops.add(Material.COAL_BLOCK);
        pickDrops.add(Material.COAL_ORE);
        pickDrops.add(Material.COBBLESTONE);
        pickDrops.add(Material.DIAMOND_BLOCK);
        pickDrops.add(Material.DIAMOND_ORE);
        pickDrops.add(Material.DOUBLE_STEP);
        pickDrops.add(Material.EMERALD_BLOCK);
        pickDrops.add(Material.EMERALD_ORE);
        pickDrops.add(Material.ENDER_STONE);
        pickDrops.add(Material.GOLD_BLOCK);
        pickDrops.add(Material.GOLD_ORE);
        pickDrops.add(Material.IRON_BLOCK);
        pickDrops.add(Material.IRON_ORE);
        pickDrops.add(Material.LAPIS_BLOCK);
        pickDrops.add(Material.LAPIS_ORE);
        pickDrops.add(Material.MOSSY_COBBLESTONE);
        pickDrops.add(Material.NETHERRACK);
        pickDrops.add(Material.NETHER_BRICK);
        pickDrops.add(Material.OBSIDIAN);
        pickDrops.add(Material.QUARTZ_BLOCK);
        pickDrops.add(Material.QUARTZ_ORE);
        pickDrops.add(Material.REDSTONE_BLOCK);
        pickDrops.add(Material.REDSTONE_ORE);
        pickDrops.add(Material.SANDSTONE);
        pickDrops.add(Material.SMOOTH_BRICK);
        pickDrops.add(Material.STAINED_CLAY);
        pickDrops.add(Material.STEP);
        pickDrops.add(Material.STONE);
        shovelDrops.add(Material.CLAY);
        shovelDrops.add(Material.DIRT);
        shovelDrops.add(Material.GRASS);
        shovelDrops.add(Material.GRAVEL);
        shovelDrops.add(Material.SAND);
        shovelDrops.add(Material.SNOW_BLOCK);
        shovelDrops.add(Material.SOUL_SAND);


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
}




