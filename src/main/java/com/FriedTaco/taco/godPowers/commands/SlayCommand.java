package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SlayCommand implements CommandExecutor {
    private Player player, targetPlayer;
    private final godPowers plugin;

    public SlayCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        World world = sender instanceof Player ? ((Player) sender).getWorld() : plugin.getServer().getWorlds().get(0);
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.slay")) {
                if (split.length == 1) {
                    targetPlayer = plugin.getServer().getPlayer(split[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.RED + "The user " + split[0] + " does not exist or is not currently logged in.");
                    } else if (plugin.godmodeEnabled.contains(targetPlayer.getName())) {
                        player.sendMessage(ChatColor.RED + "Fool! You cannot kill a god!");
                    } else {
                        targetPlayer.setHealth(0);
                        plugin.dropDeadItems(targetPlayer);
                        player.sendMessage(ChatColor.BLUE + "You have slain " + targetPlayer.getName() + ".");
                        targetPlayer.sendMessage(ChatColor.BLUE + "By the will of " + player.getName() + ", you have died.");

                    }

                    return true;
                } else if (split.length == 2) {
                    targetPlayer = plugin.getServer().getPlayer(split[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.RED + "The user " + split[0] + " does not exist or is not currently logged in.");
                        return true;
                    } else if (plugin.godmodeEnabled.contains(targetPlayer.getName())) {
                        player.sendMessage(ChatColor.DARK_RED + "Fool! You cannot kill a god!");
                        return true;
                    }
                    if (split[1].equalsIgnoreCase("a") || split[1].equalsIgnoreCase("arrows")) {
                        int x = -4;
                        Location arrows = new Location(world, targetPlayer.getLocation().getX() + x, targetPlayer.getLocation().getY() + 1, targetPlayer.getLocation().getZ() + x);
                        while (arrows.getBlock().getType() != Material.AIR) {
                            arrows = new Location(world, targetPlayer.getLocation().getX() + x, targetPlayer.getLocation().getY() + 1, targetPlayer.getLocation().getZ() + x);
                            if (arrows.getBlock().getType() == Material.AIR)
                                break;
                            else if (x > 4)
                                break;
                        }
                        player.sendMessage(ChatColor.BLUE + "You slay " + targetPlayer.getName() + " with a volley of arrows!");
                        plugin.arrowKill.add(targetPlayer.getName());
                    } else if (split[1].equalsIgnoreCase("f") || split[1].equalsIgnoreCase("fire")) {
                        player.sendMessage(ChatColor.BLUE + "You ignite " + targetPlayer.getName() + " for your amusement.");
                        targetPlayer.setFireTicks(500);
                        targetPlayer.sendMessage(ChatColor.BLUE + "The gods have lit you on fire for their amusement.");
                        plugin.burn.add(targetPlayer.getName());
                    } else if (split[1].equalsIgnoreCase("d") || split[1].equalsIgnoreCase("drop")) {
                        player.sendMessage(ChatColor.BLUE + "You drop " + targetPlayer.getName() + " from the heavens and watch them plummet to their doom.");
                        targetPlayer.teleport(new Location(world, targetPlayer.getLocation().getX(), 1000, targetPlayer.getLocation().getZ()));
                        targetPlayer.sendMessage(ChatColor.BLUE + "The gods drop you from the heavens.");
                    } else if (split[1].equalsIgnoreCase("l") || split[1].equalsIgnoreCase("lightning")) {
                        player.sendMessage(ChatColor.BLUE + "You strike " + targetPlayer.getName() + " with a bolt of lightning!");
                        player.getWorld().strikeLightning(targetPlayer.getLocation());
                    } else if (split[1].equalsIgnoreCase("c") || split[1].equalsIgnoreCase("curse")) {
                        player.sendMessage(ChatColor.BLUE + "You cast a curse upon " + targetPlayer.getName() + "'s head!");
                        targetPlayer.sendMessage(ChatColor.DARK_PURPLE + "The gods have cast a deadly curse upon you!");
                        int id = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                            public void run() {
                                targetPlayer.damage(4);
                                targetPlayer.sendMessage(ChatColor.DARK_PURPLE + "You feel your life ebbing away as the curse takes hold.");
                            }
                        }, 30L, 30L);
                        plugin.curse.put(targetPlayer.getName(), Integer.valueOf(id));
                    } else if (split[1].equalsIgnoreCase("v") || split[1].equalsIgnoreCase("void")) {
                        player.sendMessage(ChatColor.BLUE + "You toss " + targetPlayer.getName() + " into the void!");
                        targetPlayer.sendMessage(ChatColor.DARK_GRAY + "The gods have cast you into the void.");
                        Location voidLoc = targetPlayer.getLocation();
                        voidLoc.setY(0);
                        targetPlayer.teleport(voidLoc);
                    }
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Incorrect syntax, use '/slay [playerName]'");
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
                return true;
            }
        }
        return false;
    }
}
