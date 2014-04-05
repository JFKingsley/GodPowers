package com.FriedTaco.taco.godPowers.commands;

//import org.bukkit.World;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.Jesus;
import com.FriedTaco.taco.godPowers.util.Jesus.Raft;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JesusCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public JesusCommand(godPowers instance) {
        plugin = instance;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            Raft r = (Raft) Jesus.rafts.get(player.getUniqueId());
            Jesus j = new Jesus();
            if (player.hasPermission("godpowers.jesus")) {
                if (r == null) {
                    player.sendMessage(ChatColor.BLUE + StringHandler.JESUS_ADD);
                    plugin.isJesus.add(player.getUniqueId());
                    Jesus.rafts.put(player.getUniqueId(), j.new Raft());
                    return true;
                } else {
                    player.sendMessage(ChatColor.BLUE + StringHandler.JESUS_REMOVE);
                    Jesus.rafts.remove(player.getUniqueId());
                    plugin.isJesus.remove(player.getUniqueId());
                    r.destroyJesusRaft(player);
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