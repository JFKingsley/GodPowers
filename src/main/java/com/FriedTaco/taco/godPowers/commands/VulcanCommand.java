package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class VulcanCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public VulcanCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.vulcan")) {
                if (args.length == 0) {
                    /*
                    Location loc = player.getTargetBlock(null, 100).getLocation();
                    loc.setY(player.getTargetBlock(null, 100).getLocation().getY()+1);
                    Location playerLoc = player.getLocation().add(loc);
                    */
                    if (plugin.isVulcan.contains(player.getUniqueId())) {
                        player.sendMessage(ChatColor.BLUE + StringHandler.VULCAN_REMOVE);
                        plugin.isVulcan.remove(player.getUniqueId());
                    } else {
                        player.sendMessage(ChatColor.BLUE + StringHandler.VULCAN_ADD);
                        plugin.isVulcan.add(player.getUniqueId());
                    }
                    //world.spawn(player.getTargetBlock(null, 100).getLocation(), Fireball.class);
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.VULCAN_SYNTAX);
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return true;
    }
}