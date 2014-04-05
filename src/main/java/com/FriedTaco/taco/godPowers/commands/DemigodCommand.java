package com.FriedTaco.taco.godPowers.commands;

//import org.bukkit.World;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DemigodCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public DemigodCommand(godPowers instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.demigod")) {
                if (split.length == 0) {
                    if (plugin.DemiGod.contains(player.getUniqueId())) {
                        plugin.DemiGod.remove(player.getUniqueId());
                        player.sendMessage(ChatColor.BLUE + StringHandler.DEMIGOD_REMOVE);
                        return true;
                    } else {
                        player.sendMessage(ChatColor.BLUE + StringHandler.DEMIGOD_SHARED);
                        player.sendMessage(ChatColor.BLUE + StringHandler.DEMIGOD_ADD);
                        plugin.DemiGod.add(player.getUniqueId());
                        player.setHealth(player.getMaxHealth());
                        return true;
                    }
                } else {
                    Player targetPlayer = plugin.getServer().getPlayer(split[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.RED + StringHandler.DEMIGOD_ERROR);
                    } else if (targetPlayer == player) {
                        player.sendMessage(ChatColor.RED + StringHandler.DEMIGOD_YOURSELF);

                    } else {
                        if (plugin.DemiGod.contains(targetPlayer.getUniqueId())) {
                            plugin.DemiGod.remove(targetPlayer.getUniqueId());
                            targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " " + StringHandler.DEMIGOD_RETURNYOU);
                            player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " + StringHandler.DEMIGOD_REMOVEOTHER);
                        } else {
                            targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " " + StringHandler.DEMIGOD_ADDOTHER);
                            targetPlayer.sendMessage(ChatColor.BLUE + StringHandler.DEMIGOD_ADD);
                            plugin.DemiGod.add(targetPlayer.getUniqueId());
                            targetPlayer.setHealth(player.getMaxHealth());
                            player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " + StringHandler.DEMIGOD_ADDED);
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