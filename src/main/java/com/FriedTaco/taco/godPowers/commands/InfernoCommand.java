package com.FriedTaco.taco.godPowers.commands;

//import org.bukkit.World;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfernoCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public InfernoCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.inferno")) {
                if (plugin.isInferno.contains(player.getUniqueId())) {
                    plugin.isInferno.remove(player.getUniqueId());
                    player.sendMessage(ChatColor.BLUE + StringHandler.INFERNO_REMOVE);
                } else {
                    plugin.isInferno.add(player.getUniqueId());
                    player.sendMessage(ChatColor.DARK_RED + StringHandler.INFERNO_ADD);
                }
            } else {
                player.sendMessage(ChatColor.RED + StringHandler.GODPOWERS_NOPERMISSION);
                return true;
            }
            return true;
        }
        return false;
    }
}
