package com.FriedTaco.taco.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PlutusCommand implements CommandExecutor {
    private Player player;
    @SuppressWarnings("unused")
    private final godPowers plugin;

    public PlutusCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.plutus")) {
                if (split.length > 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect syntax. Correct usage: '/plutus'");
                    return true;
                } else {
                    Material poss[] = {Material.IRON_SPADE, Material.IRON_PICKAXE, Material.IRON_AXE, Material.WOOD_SPADE, Material.WOOD_PICKAXE, Material.WOOD_AXE, Material.STONE_SPADE, Material.STONE_PICKAXE, Material.STONE_AXE, Material.DIAMOND_SPADE, Material.DIAMOND_PICKAXE, Material.DIAMOND_AXE, Material.GOLD_SPADE, Material.GOLD_PICKAXE, Material.GOLD_AXE};
                    ArrayList<Material> possible = new ArrayList<Material>();
                    for (int i = 0; i < poss.length; i++) possible.add(poss[i]);
                    ItemStack i = player.getItemInHand();
                    if (i != null && possible.contains(i.getType())) {
                        player.sendMessage(ChatColor.GOLD + "You suddenly feel as if earthly riches will come easily to you.");
                        i.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 25);
                    } else {
                        player.sendMessage(ChatColor.RED + "You aren't holding the correct type of item.");
                    }
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
            }
        }
        return false;
    }
}