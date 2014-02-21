package com.FriedTaco.taco.godPowers.commands;

//import org.bukkit.World;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MaimCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public MaimCommand(godPowers instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.maim")) {
                if (split.length == 1) {
                    Player targetPlayer = plugin.getServer().getPlayer(split[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.RED + StringHandler.MAIM_ERROR);
                    } else if (plugin.godmodeEnabled.contains(targetPlayer.getName())) {
                        player.sendMessage(ChatColor.RED + StringHandler.MAIM_GOD);
                    } else {
                        targetPlayer.setHealth(2);
                        player.sendMessage(ChatColor.BLUE + StringHandler.MAIM_ATTACK + " " + targetPlayer.getName() + " " + StringHandler.MAIM_DAMAGE);
                        targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " " + StringHandler.MAIM_TARGET);
                    }
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.MAIM_SYNTAX);
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