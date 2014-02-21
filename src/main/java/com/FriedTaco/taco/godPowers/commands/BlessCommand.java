package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlessCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public BlessCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.bless")) {
                if (split.length == 1) {
                    Player player2 = plugin.getServer().getPlayer(split[0]);
                    if (player2 != null) {
                        player.sendMessage(ChatColor.BLUE + StringHandler.BLESS_PLAYER2 + " " + player2.getDisplayName());
                        player2.sendMessage(ChatColor.BLUE + StringHandler.BLESS_BLESSED);
                        plugin.bless(player2);
                    } else {
                        player.sendMessage(ChatColor.BLUE + StringHandler.BLESS_CANNOTBLESS);
                    }
                    return true;
                } else if (split.length == 0) {
                    player.sendMessage(ChatColor.BLUE + StringHandler.BLESS_BLESSED);
                    plugin.bless(player);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.BLESS_SYNTAXERROR);
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return false;
    }
}