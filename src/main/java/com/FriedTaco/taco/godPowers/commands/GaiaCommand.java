package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GaiaCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public GaiaCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.gaia")) {
                if (args.length == 0) {
                    if (plugin.gaia.contains(player.getUniqueId())) {
                        player.sendMessage(ChatColor.DARK_GREEN + StringHandler.GAIA_REMOVE);
                        plugin.gaia.remove(player.getUniqueId());
                    } else {
                        player.sendMessage(ChatColor.DARK_GREEN + StringHandler.GAIA_ADD);
                        plugin.gaia.add(player.getUniqueId());
                    }
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.GAIA_SYNTAX);
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