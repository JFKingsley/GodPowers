package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HermesCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public HermesCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.hermes")) {
                if (args.length > 0) {
                    player.sendMessage(ChatColor.RED + StringHandler.HERMES_SYNTAX);
                } else {
                    if (plugin.isHermes.contains(player.getUniqueId())) {
                        plugin.isHermes.remove(player.getUniqueId());
                        player.sendMessage(ChatColor.AQUA + StringHandler.HERMES_REMOVE);
                    } else {
                        plugin.isHermes.add(player.getUniqueId());
                        player.sendMessage(ChatColor.AQUA + StringHandler.HERMES_ADD);
                    }
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return true;
    }
}