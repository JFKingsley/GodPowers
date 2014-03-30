package com.FriedTaco.taco.godPowers.commands;

//import org.bukkit.World;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodmodeCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public GodmodeCommand(godPowers instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.godmode")) {
                if (split.length == 0) {
                    if (plugin.godmodeEnabled.contains(player.getName())) {
                        if (command.getName().equalsIgnoreCase("godmodeon")) {
                            player.sendMessage(ChatColor.BLUE + StringHandler.GODMODE_ALREADY);
                            return true;
                        }
                        plugin.godmodeEnabled.remove(player.getName());
                        player.sendMessage(ChatColor.BLUE + StringHandler.GODMODE_REMOVE);
                        player.setDisplayName(player.getName());
                        return true;
                    } else {
                        if (command.getName().equalsIgnoreCase("godmodeoff")) {
                            player.sendMessage(ChatColor.BLUE + StringHandler.GODMODE_ALREADYREMOVE);
                            return true;
                        }
                        player.sendMessage(ChatColor.BLUE + StringHandler.GODMODE_ADD);
                        player.setDisplayName(plugin.title + player.getDisplayName());
                        plugin.godmodeEnabled.add(player.getName());
                        player.setHealth(player.getMaxHealth());
                        return true;
                    }
                } else {
                    Player targetPlayer = plugin.getServer().getPlayer(split[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.RED + StringHandler.GODMODE_ERROR);
                    } else if (targetPlayer == player) {
                        player.sendMessage(ChatColor.RED + StringHandler.GODMODE_YOURSELF);

                    } else {
                        if (plugin.godmodeEnabled.contains(targetPlayer.getName())) {
                            if (command.getName().equalsIgnoreCase("godmodeon")) {
                                player.sendMessage(ChatColor.RED + StringHandler.GODMODE_ALREADY);
                                return true;
                            }
                            plugin.godmodeEnabled.remove(targetPlayer.getName());
                            targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " " + StringHandler.GODMODE_REMOVEOTHER);
                            targetPlayer.setDisplayName(targetPlayer.getName());
                            player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " + StringHandler.GODMODE_REMOVED);
                        } else {
                            if (command.getName().equalsIgnoreCase("godmodeoff")) {
                                player.sendMessage(StringHandler.GODMODE_ALREADYREMOVEOTHER);
                                return true;
                            }
                            targetPlayer.sendMessage(ChatColor.BLUE + StringHandler.GODMODE_POWEROF + " " + player.getName() + " " + StringHandler.GODMODE_POWEROFADDED);
                            targetPlayer.setDisplayName(plugin.title + targetPlayer.getName());
                            plugin.godmodeEnabled.add(targetPlayer.getName());
                            targetPlayer.setHealth(targetPlayer.getMaxHealth());
                            player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " + StringHandler.GODMODE_ADDED);
                        }
                    }
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
                return true;
            }
        }
        return false;
    }
}