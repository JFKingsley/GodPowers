package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.MedusaPlayer;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ListIterator;

public class MedusaCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public MedusaCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.medusa")) {
                if (split.length > 0) {
                    player.sendMessage(ChatColor.RED + StringHandler.MEDUSA_SYNTAX);
                    return true;
                } else {
                    if (!plugin.isMedusa.contains(player.getName())) {
                        player.sendMessage(ChatColor.GREEN + StringHandler.MEDUSA_ADD);
                        plugin.isMedusa.add(player.getName());
                    } else {
                        player.sendMessage(ChatColor.GREEN + StringHandler.MEDUSA_REMOVE);
                        plugin.isMedusa.remove(player.getName());
                        ListIterator<MedusaPlayer> it = plugin.isUnderMedusaInfluence.listIterator();
                        if (it.hasNext()) {
                            MedusaPlayer mplayer = it.next();
                            if (mplayer.getMedusa().equals(player)) {
                                Player player = mplayer.getPlayer();
                                player.sendMessage(ChatColor.AQUA + StringHandler.MEDUSA_GONE);
                                plugin.isUnderMedusaInfluence.remove(mplayer);
                            }
                        }
                    }
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return false;
    }
}