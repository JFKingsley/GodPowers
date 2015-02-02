package com.FriedTaco.taco.godPowers.commands;

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
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.godmode")) {
                if (args.length == 0) {
                    if (plugin.godmodeEnabled.contains(player.getUniqueId())) {
                        if (command.getName().equalsIgnoreCase("godmodeon")) {
                            player.sendMessage(ChatColor.BLUE + StringHandler.GODMODE_ALREADY);
                        }
                        plugin.godmodeEnabled.remove(player.getUniqueId());
                        player.sendMessage(ChatColor.BLUE + StringHandler.GODMODE_REMOVE);
                        player.setDisplayName(player.getName());
                    } else {
                        if (command.getName().equalsIgnoreCase("godmodeoff")) {
                            player.sendMessage(ChatColor.BLUE + StringHandler.GODMODE_ALREADYREMOVE);
                        }
                        player.sendMessage(ChatColor.BLUE + StringHandler.GODMODE_ADD);
                        player.setDisplayName(plugin.title + player.getDisplayName());
                        plugin.godmodeEnabled.add(player.getUniqueId());
                        player.setHealth(player.getMaxHealth());
                    }
                } else if (args.length == 1) {
                    Player targetPlayer = plugin.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.RED + StringHandler.GODMODE_ERROR);
                    } else if (targetPlayer == player) {
                        player.sendMessage(ChatColor.RED + StringHandler.GODMODE_YOURSELF);

                    } else {
                        if (plugin.godmodeEnabled.contains(targetPlayer.getUniqueId())) {
                            if (command.getName().equalsIgnoreCase("godmodeon")) {
                                player.sendMessage(ChatColor.RED + StringHandler.GODMODE_ALREADY);
                            }
                            plugin.godmodeEnabled.remove(targetPlayer.getUniqueId());
                            targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " " + StringHandler.GODMODE_REMOVEOTHER);
                            targetPlayer.setDisplayName(targetPlayer.getName());
                            player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " + StringHandler.GODMODE_REMOVED);
                        } else {
                            if (command.getName().equalsIgnoreCase("godmodeoff")) {
                                player.sendMessage(StringHandler.GODMODE_ALREADYREMOVEOTHER);
                            }
                            targetPlayer.sendMessage(ChatColor.BLUE + StringHandler.GODMODE_POWEROF + " " + player.getName() + " " + StringHandler.GODMODE_POWEROFADDED);
                            targetPlayer.setDisplayName(plugin.title + targetPlayer.getName());
                            plugin.godmodeEnabled.add(targetPlayer.getUniqueId());
                            targetPlayer.setHealth(targetPlayer.getMaxHealth());
                            player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " + StringHandler.GODMODE_ADDED);
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.GODMODE_SYNTAX);
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return true;
    }
}