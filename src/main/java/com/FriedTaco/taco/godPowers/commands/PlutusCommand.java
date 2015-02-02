package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public class PlutusCommand implements CommandExecutor {
    private Player player;
    @SuppressWarnings("unused")
    private final godPowers plugin;

    public PlutusCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.plutus")) {
                if (args.length > 0) {
                    player.sendMessage(ChatColor.RED + StringHandler.PLUTUS_SYNTAX);
                } else {
                    Material poss[] = {Material.IRON_SPADE, Material.IRON_PICKAXE, Material.IRON_AXE, Material.WOOD_SPADE, Material.WOOD_PICKAXE, Material.WOOD_AXE, Material.STONE_SPADE, Material.STONE_PICKAXE, Material.STONE_AXE, Material.DIAMOND_SPADE, Material.DIAMOND_PICKAXE, Material.DIAMOND_AXE, Material.GOLD_SPADE, Material.GOLD_PICKAXE, Material.GOLD_AXE};
                    ArrayList<Material> possible = new ArrayList<Material>();
                    Collections.addAll(possible, poss);
                    ItemStack i = player.getItemInHand();
                    if (i != null && possible.contains(i.getType())) {
                        player.sendMessage(ChatColor.GOLD + StringHandler.PLUTUS_ADD);
                        i.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 25);
                    } else {
                        player.sendMessage(ChatColor.RED + StringHandler.PLUTUS_ERROR);
                    }
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return true;
    }
}