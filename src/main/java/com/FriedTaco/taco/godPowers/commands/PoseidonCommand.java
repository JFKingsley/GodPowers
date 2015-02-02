package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PoseidonCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public PoseidonCommand(godPowers instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.poseidon")) {
                if (args.length == 0) {
                    if (plugin.isPoseidon.contains(player.getUniqueId())) {
                        plugin.isPoseidon.remove(player.getUniqueId());
                        player.sendMessage(ChatColor.BLUE + StringHandler.POSEIDON_REMOVE);
                    } else {
                        player.sendMessage(ChatColor.BLUE + StringHandler.POSEIDON_ADD);
                        plugin.isPoseidon.add(player.getUniqueId());
                    }
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.POSEIDON_YOURSELF);
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return true;
    }
}