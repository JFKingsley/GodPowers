package com.FriedTaco.taco.godPowers.listeners;

import com.FriedTaco.taco.godPowers.*;
import com.FriedTaco.taco.godPowers.Jesus.Raft;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


public class PlayerListener implements Listener {


    int health = 0;
    int[] raftX = new int[25];
    int[] raftY = new int[25];
    int[] raftZ = new int[25];
    Raft jesus;
    private Vector dir;
    final godPowers plugin;

    void dropDeadItems(Player player) {
        if (player.getInventory() != null) {
            ItemStack[] item = this.plugin.getServer().getPlayer(player.getName()).getInventory().getContents();
            Location position = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
            for (int x = 0; x < 36; x++) {
                if (item[x].getType() != Material.AIR) {
                    player.getWorld().dropItemNaturally(position, item[x]);
                }
            }
        }
    }

    public PlayerListener(godPowers instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (plugin.godModeOnLogin && player.hasPermission("godpowers.godmodeonlogin")) {
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    player.sendMessage(StringHandler.GODMODE_LOGIN);
                    player.setDisplayName(plugin.title + player.getDisplayName());
                    plugin.godmodeEnabled.add(player.getName());
                    player.setHealth(player.getMaxHealth());
                    player.setFoodLevel(20);
                    player.setDisplayName(plugin.title + player.getName());
                }
            }, 10L);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (plugin.godmodeEnabled.contains(event.getPlayer().getName())) {
            plugin.godmodeEnabled.remove(event.getPlayer().getName());
        }
        if (plugin.isJesus.contains(event.getPlayer().getName())) {
            plugin.isJesus.remove(event.getPlayer().getName());
            jesus = (Raft) Jesus.rafts.get(event.getPlayer().getName());
            jesus.destroyJesusRaft(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        /*
        if(event.getFrom().getBlock() != event.getTo().getBlock())
    	{
    		event.getFrom().getBlock().getRelative(0,-1,0).setTypeId(plugin.lastID);
    		event.getFrom().getBlock().getRelative(0,-1,0).setData((byte) plugin.lastData);
    		plugin.lastID = event.getTo().getBlock().getRelative(0,-1,0).getTypeId();
    		plugin.lastData = event.getTo().getBlock().getRelative(0,-1,0).getData();
    		event.getTo().getBlock().getRelative(0,-1,0).setTypeId(89);
    	}
    	*/
        if (plugin.isHermes.contains(event.getPlayer().getName())) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 6));
        }
        if (plugin.isPoseidon.contains(event.getPlayer().getName())) {
            Material m = event.getPlayer().getLocation().getBlock().getType();
            if (m == Material.STATIONARY_WATER || m == Material.WATER) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120, 2));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 2));
                event.getPlayer().setRemainingAir(300);
            }
        }
        if (plugin.isMedusa.contains(event.getPlayer().getName())) {
            if (getTarget(event.getPlayer()) != null) {
                Player target = getTarget(event.getPlayer());
                Player player = event.getPlayer();
                boolean isAlreadyUnder = false;
                for (MedusaPlayer mplayer : plugin.isUnderMedusaInfluence) {
                    if (mplayer.getMedusa().equals(player) && mplayer.getPlayer().equals(target)) {
                        isAlreadyUnder = true;
                    }
                }
                if (!isAlreadyUnder) {
                    plugin.isUnderMedusaInfluence.add(new MedusaPlayer(target, plugin.medusaFreezeTime, player));
                    target.sendMessage(ChatColor.GREEN + StringHandler.MEDUSA_CURSED + " " + plugin.medusaFreezeTime + " seconds.");
                }
            }
        }
        ListIterator<MedusaPlayer> it = plugin.isUnderMedusaInfluence.listIterator();
        if (it.hasNext()) {
            MedusaPlayer mplayer = it.next();
            if (mplayer.getPlayer().equals(event.getPlayer())) {
                Player p = event.getPlayer();
                Location from = event.getFrom();
                double xfrom = event.getFrom().getX();
                double yfrom = event.getFrom().getY();
                double zfrom = event.getFrom().getZ();
                double xto = event.getTo().getX();
                double yto = event.getTo().getY();
                double zto = event.getTo().getZ();
                if (!(xfrom == xto && yfrom == yto && zfrom == zto)) {
                    p.teleport(from);
                    event.getPlayer().sendMessage(ChatColor.RED + StringHandler.MEDUSA_MOVEATTEMPT);
                }
            }
        }
        if (plugin.godmodeEnabled.contains(event.getPlayer().getName()) && event.getPlayer().getFireTicks() > 1) {
            event.getPlayer().setFireTicks(0);
        }
        if (plugin.isJesus.contains(event.getPlayer().getName())) {
            Raft jesus = (Raft) Jesus.rafts.get(event.getPlayer().getName());
            jesus.destroyJesusRaft(event.getPlayer());
            jesus.makeJesusRaft(event.getPlayer());
        }
        if (plugin.isInferno.contains(event.getPlayer().getName())) {
            double diffX = event.getFrom().getX() - event.getTo().getX();
            double diffZ = event.getFrom().getZ() - event.getTo().getZ();
            if (diffX > 0) {
                diffX = 1;
            } else if (diffX < 0) {
                diffX = -1;
            }
            if (diffZ > 0) {
                diffZ = 1;
            } else if (diffZ < 0) {
                diffZ = -1;
            }
            Block block = event.getPlayer().getWorld().getBlockAt((int) (event.getFrom().getX() + diffX), (int) event.getFrom().getY(), (int) (event.getFrom().getZ() + diffZ));
            if (block.getType() == Material.AIR) {
                event.getPlayer().setFireTicks(0);
                block.setType(Material.FIRE);
            }
        }
        if ((plugin.superJumper.contains(event.getPlayer().getName()))) {
            Block block, control;
            dir = event.getPlayer().getVelocity().setY(2);
            if (event.getTo().getY() > event.getFrom().getY()) {
                block = event.getPlayer().getWorld().getBlockAt(new Location(event.getPlayer().getWorld(), event.getTo().getX(), event.getTo().getY() + 2, event.getTo().getZ()));
                control = event.getPlayer().getWorld().getBlockAt(new Location(event.getPlayer().getWorld(), event.getTo().getX(), event.getTo().getY() - 2, event.getTo().getZ()));
                if (!(block.getType() != Material.AIR || control.getType() == Material.AIR)) {
                    //event.getPlayer().teleportTo(new Location(event.getPlayer().getWorld(), event.getTo().getX()+diffX, event.getTo().getY()+2, event.getTo().getZ()+diffZ));
                    event.getPlayer().setVelocity(dir);
                }
            }
        }
        if (plugin.burn.contains(event.getPlayer().getName()) && event.getPlayer().getFireTicks() < 10) {
            event.getPlayer().setFireTicks(9001);
        }
        if (plugin.arrowKill.contains(event.getPlayer().getName())) {
            plugin.arrowSlay(event.getTo(), event.getPlayer().getWorld(), event.getPlayer());
        }
        if (plugin.gaia.contains(event.getPlayer().getName())) {
            for (int x = -2; x <= 2; x++) {
                for (int z = -2; z <= 2; z++) {
                    Block block = event.getPlayer().getWorld().getBlockAt(event.getTo().getBlockX() + x, event.getTo().getBlockY() - 1, event.getTo().getBlockZ() + z);
                    if (block.getType() == Material.DIRT) {
                        block.setType(Material.GRASS);
                        block = event.getPlayer().getWorld().getBlockAt(event.getTo().getBlockX() + x, event.getTo().getBlockY(), event.getTo().getBlockZ() + z);
                        plantStuff(block);
                    } else if (block.getType() == Material.GRASS) {
                        block = event.getPlayer().getWorld().getBlockAt(event.getTo().getBlockX() + x, event.getTo().getBlockY(), event.getTo().getBlockZ() + z);
                        plantStuff(block);
                    }
                }
            }
        }
        if (plugin.hades.contains(event.getPlayer().getName()) && event.getFrom().getBlock().getLocation().distance(event.getTo().getBlock().getLocation()) > 0) {
            for (int x = -2; x <= 2; x++) {
                for (int z = -2; z <= 2; z++) {
                    Block block = event.getPlayer().getWorld().getBlockAt(event.getTo().getBlockX() + x, event.getTo().getBlockY() - 1, event.getTo().getBlockZ() + z);
                    if (block.getType() != Material.AIR && block.getType() != Material.WATER && block.getType() != Material.STATIONARY_WATER) {
                        double rand = Math.random();
                        if (x == 0 && z == 0) {
                            if (rand < .5)
                                block.setType(Material.NETHERRACK);
                            else
                                block.setType(Material.SOUL_SAND);
                        } else {
                            if (rand < .2)
                                block.setType(Material.NETHERRACK);
                            else if (rand > .2 && rand <= .4)
                                block.setType(Material.SOUL_SAND);
                        }
                    }
                }
            }
        }
    }

    private void plantStuff(Block block) {
        if (block.getType() == Material.AIR) {
            double chance = Math.random();
            if (chance < .02)
                block.setType(Material.YELLOW_FLOWER);
            else if (chance >= .02 && chance <= .04)
                block.setType(Material.RED_ROSE);
            else if (chance > .04 && chance <= .1) {
                block.setType(Material.LONG_GRASS);
                block.setData((byte) 1);
            }

        }
    }

    @EventHandler
    public void onPlayerAnimation(PlayerAnimationEvent event) {
        if (event.getAnimationType().equals(PlayerAnimationType.ARM_SWING)) {
            Player p = event.getPlayer();
            World w = p.getWorld();
            if (plugin.isZeus.contains(p.getName())) {
                w.strikeLightning((p.getTargetBlock(null, 100).getLocation())); // p.getTargetBlock is a Magic Value!
            }
            if (plugin.isVulcan.contains(p.getName())) {
                Fireball f = event.getPlayer().getWorld().spawn(event.getPlayer().getLocation().add(event.getPlayer().getLocation().getDirection().normalize().multiply(3).toLocation(event.getPlayer().getWorld(), event.getPlayer().getLocation().getYaw(), event.getPlayer().getLocation().getPitch())).add(0, 1D, 0), Fireball.class);
                f.setShooter(p);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (plugin.godTools && event.getPlayer().hasPermission("godpowers.godtools")) {
            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                ItemStack i = event.getItem();
                Block b = event.getClickedBlock();
                Player p = event.getPlayer();
                if (i != null) {
                    if (i.getType() == Material.GOLD_SPADE && plugin.shovelDrops.contains(b.getType())) {
                        mine(p, b, i);
                    } else if (i.getType() == Material.GOLD_PICKAXE && plugin.pickDrops.contains(b.getType())) {
                        mine(p, b, i);
                    } else if (i.getType() == Material.GOLD_AXE && plugin.axeDrops.contains(b.getType())) {
                        mine(p, b, i);
                    }
                }
            }
        }
    }

    private void mine(Player p, Block b, ItemStack i) {
        BlockBreakEvent e = new BlockBreakEvent(b, p);
        Bukkit.getPluginManager().callEvent(e);
        if (!e.isCancelled()) {
            i.setDurability((short) 0);
            b.breakNaturally();
            for (int x = 0; x <= 8; x++)
                p.getWorld().playEffect(b.getLocation(), Effect.SMOKE, x);
        }
    }

    public Player getTarget(Player player) {
        List<Entity> nearbyE = player.getNearbyEntities(10, 10, 10);
        ArrayList<Player> nearPlayers = new ArrayList<Player>();
        for (Entity e : nearbyE) {
            if (e instanceof Player) {
                nearPlayers.add((Player) e);
            }
        }
        Player target = null;
        BlockIterator bItr = new BlockIterator(player, 10);
        Block block;
        Location loc;
        int bx, by, bz;
        double ex, ey, ez;
        while (bItr.hasNext()) {

            block = bItr.next();
            bx = block.getX();
            by = block.getY();
            bz = block.getZ();
            for (Player e : nearPlayers) {
                loc = e.getLocation();
                ex = loc.getX();
                ey = loc.getY();
                ez = loc.getZ();
                if ((bx - .75 <= ex && ex <= bx + 1.75) && (bz - .75 <= ez && ez <= bz + 1.75) && (by - 1 <= ey && ey <= by + 2.5)) {
                    target = e;
                    break;

                }
            }

        }
        return target;

    }

    private Color getColor(int i) {
        Color c = null;
        if (i == 1) {
            c = Color.AQUA;
        }
        if (i == 2) {
            c = Color.BLACK;
        }
        if (i == 3) {
            c = Color.BLUE;
        }
        if (i == 4) {
            c = Color.FUCHSIA;
        }
        if (i == 5) {
            c = Color.GRAY;
        }
        if (i == 6) {
            c = Color.GREEN;
        }
        if (i == 7) {
            c = Color.LIME;
        }
        if (i == 8) {
            c = Color.MAROON;
        }
        if (i == 9) {
            c = Color.NAVY;
        }
        if (i == 10) {
            c = Color.OLIVE;
        }
        if (i == 11) {
            c = Color.ORANGE;
        }
        if (i == 12) {
            c = Color.PURPLE;
        }
        if (i == 13) {
            c = Color.RED;
        }
        if (i == 14) {
            c = Color.SILVER;
        }
        if (i == 15) {
            c = Color.TEAL;
        }
        if (i == 16) {
            c = Color.WHITE;
        }
        if (i == 17) {
            c = Color.YELLOW;
        }

        return c;
    }
}