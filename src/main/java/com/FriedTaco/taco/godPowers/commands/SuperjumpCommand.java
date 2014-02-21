package com.FriedTaco.taco.godPowers.commands;

//import org.bukkit.World;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuperjumpCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public SuperjumpCommand(godPowers instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.superjump")) {
                if (plugin.superJumper.contains(player.getName())) {
                    plugin.superJumper.remove(player.getName());
                    player.sendMessage(ChatColor.BLUE + StringHandler.SUPERJUMP_REMOVE);
                } else {
                    player.sendMessage(ChatColor.BLUE + StringHandler.SUPERJUMP_ADD);
                    plugin.superJumper.add(player.getName());
                }
                return true;
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
                return true;
            }
        }
        return false;
    }
}