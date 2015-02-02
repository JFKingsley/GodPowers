package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HadesCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public HadesCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.hades")) {
                if (args.length == 0) {
                    if (plugin.hades.contains(player.getUniqueId())) {
                        player.sendMessage(ChatColor.DARK_RED + StringHandler.HADES_REMOVE);
                        plugin.hades.remove(player.getUniqueId());
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + StringHandler.HADES_ADD);
                        plugin.hades.add(player.getUniqueId());
                    }
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.HADES_SYNTAX);
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return true;
    }
}
