package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;

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
                        Material poss[] = {Material.WOOD_AXE, Material.WOOD_SPADE, Material.WOOD_SWORD, Material.WOOD_PICKAXE, Material.STONE_AXE, Material.STONE_SPADE, Material.STONE_SWORD, Material.STONE_PICKAXE, Material.DIAMOND_AXE, Material.DIAMOND_SPADE, Material.DIAMOND_SWORD, Material.DIAMOND_PICKAXE, Material.GOLD_AXE, Material.GOLD_SPADE, Material.GOLD_SWORD, Material.GOLD_PICKAXE, Material.BOW, Material.SHEARS, Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Material.IRON_BOOTS, Material.IRON_CHESTPLATE, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS,};
                        ArrayList<Material> possible = new ArrayList<Material>();
                        Collections.addAll(possible, poss);
                        ItemStack i = player.getItemInHand();
                        if (i != null && possible.contains(i.getType())) {
                            if (player.getItemInHand().getDurability() == 0) {
                                player.sendMessage(ChatColor.BLUE + "The gods cannot repair that which is not broken!");
                            } else {
                                player.getItemInHand().setDurability((short) 0);
                                player.sendMessage(ChatColor.BLUE + "The gods have repaired the item in your hand!");
                            }
                        } else if (player.getItemInHand().getType() == Material.AIR) {
                            player.sendMessage(ChatColor.BLUE + "The gods cannot repair nothing!");
                        } else {
                            player.sendMessage(ChatColor.RED + "Not even the gods are allowed to repair this item!");
                        }
                    } catch (Exception i) {
                        player.sendMessage(ChatColor.RED + "Not even the gods can repair this item!");
                    }
                } else {
                    player.sendMessage("Incorrect syntax. Correct usage: '/repair'");
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
                return true;
            }
        }
        return true;
    }
}