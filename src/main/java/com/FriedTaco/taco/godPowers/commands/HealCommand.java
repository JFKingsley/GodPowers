package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public HealCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.heal")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.BLUE + StringHandler.HEAL_HEALED);
                    player.setHealth(player.getMaxHealth());
                    player.setFoodLevel(20);
                } else if (args.length == 1) {
                    Player targetPlayer = plugin.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.RED + StringHandler.HEAL_ERROR);
                    } else if (targetPlayer == player) {
                        player.sendMessage(ChatColor.RED + StringHandler.HEAL_YOURSELF);
                    } else {
                        player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " + StringHandler.HEAL_HEALEDOTHER);
                        targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " " + StringHandler.HEAL_HEALEDYOU);
                        targetPlayer.setHealth(targetPlayer.getMaxHealth());
                    }
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.HEAL_SYNTAX);
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return true;
    }
}