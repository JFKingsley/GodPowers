package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;

import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public class RepairCommand implements CommandExecutor {
    private final godPowers plugin;

    public RepairCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("godpowers.repair")) {
                if (args.length == 0) {
                    try {
                        Material poss[] = {Material.WOOD_AXE, Material.WOOD_SPADE, Material.WOOD_SWORD, Material.WOOD_PICKAXE, Material.STONE_AXE, Material.STONE_SPADE, Material.STONE_SWORD, Material.STONE_PICKAXE, Material.DIAMOND_AXE, Material.DIAMOND_SPADE, Material.DIAMOND_SWORD, Material.DIAMOND_PICKAXE, Material.GOLD_AXE, Material.GOLD_SPADE, Material.GOLD_SWORD, Material.GOLD_PICKAXE, Material.BOW, Material.SHEARS, Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Material.IRON_BOOTS, Material.IRON_CHESTPLATE, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS, Material.FISHING_ROD};
                        ArrayList<Material> possible = new ArrayList<Material>();
                        Collections.addAll(possible, poss);
                        ItemStack i = player.getItemInHand();
                        if (i != null && possible.contains(i.getType())) {
                            if (player.getItemInHand().getDurability() == 0) {
                                player.sendMessage(ChatColor.BLUE + StringHandler.REPAIR_NOTBROKEN);
                            } else {
                                player.getItemInHand().setDurability((short) 0);
                                player.sendMessage(ChatColor.BLUE + StringHandler.REPAIR_REPAIRED);
                            }
                        } else if (player.getItemInHand().getType() == Material.AIR) {
                            player.sendMessage(ChatColor.BLUE + StringHandler.REPAIR_NOTHING);
                        } else {
                            player.sendMessage(ChatColor.RED + StringHandler.REPAIR_NOTALLOWED);
                        }
                    } catch (Exception i) {
                        player.sendMessage(ChatColor.RED + StringHandler.REPAIR_ERROR);
                    }
                } else {
                    player.sendMessage(StringHandler.REPAIR_SYNTAX);
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
                return true;
            }
        }
        return true;
    }
}