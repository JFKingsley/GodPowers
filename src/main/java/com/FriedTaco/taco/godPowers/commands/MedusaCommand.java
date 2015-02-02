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
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.medusa")) {
                if (args.length > 0) {
                    player.sendMessage(ChatColor.RED + StringHandler.MEDUSA_SYNTAX);
                } else {
                    if (!plugin.isMedusa.contains(player.getUniqueId())) {
                        player.sendMessage(ChatColor.GREEN + StringHandler.MEDUSA_ADD);
                        plugin.isMedusa.add(player.getUniqueId());
                    } else {
                        player.sendMessage(ChatColor.GREEN + StringHandler.MEDUSA_REMOVE);
                        plugin.isMedusa.remove(player.getUniqueId());
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
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return true;
    }
}