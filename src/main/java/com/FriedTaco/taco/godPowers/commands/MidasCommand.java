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

public class MidasCommand implements CommandExecutor {
    private final godPowers plugin;

    public MidasCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("godpowers.midas")) {
                if (args.length == 0) {
                    goldenReplace(player);
                    goldenBless(player);
                    player.sendMessage(ChatColor.BLUE + StringHandler.MIDAS_MIDAS);
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
                return true;
            }
        }
        return true;
    }

    private void repair(ItemStack i) {
        if (i.getDurability() != 0) {
            i.setDurability((short) 0);
        }
    }

    private void goldenReplace(Player p) {
        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null) {
                switch (i.getType()) {
                    // Leather leather leather
                    case LEATHER_HELMET:
                        i.setType(Material.GOLD_HELMET);
                        break;
                    case LEATHER_CHESTPLATE:
                        i.setType(Material.GOLD_CHESTPLATE);
                        break;
                    case LEATHER_LEGGINGS:
                        i.setType(Material.GOLD_LEGGINGS);
                        break;
                    case LEATHER_BOOTS:
                        i.setType(Material.GOLD_BOOTS);
                        break;
                    // Wood wood wood
                    case WOOD_PICKAXE:
                        i.setType(Material.GOLD_PICKAXE);
                        break;
                    case WOOD_AXE:
                        i.setType(Material.GOLD_AXE);
                        break;
                    case WOOD_SPADE:
                        i.setType(Material.GOLD_SPADE);
                        break;
                    case WOOD_SWORD:
                        i.setType(Material.GOLD_SWORD);
                        break;
                    case WOOD_HOE:
                        i.setType(Material.GOLD_HOE);
                        break;
                    // Stone stone stone
                    case STONE_PICKAXE:
                        i.setType(Material.GOLD_PICKAXE);
                        break;
                    case STONE_AXE:
                        i.setType(Material.GOLD_AXE);
                        break;
                    case STONE_SPADE:
                        i.setType(Material.GOLD_SPADE);
                        break;
                    case STONE_SWORD:
                        i.setType(Material.GOLD_SWORD);
                        break;
                    case STONE_HOE:
                        i.setType(Material.GOLD_HOE);
                        break;
                    // Iron iron iron
                    case IRON_PICKAXE:
                        i.setType(Material.GOLD_PICKAXE);
                        break;
                    case IRON_AXE:
                        i.setType(Material.GOLD_AXE);
                        break;
                    case IRON_SPADE:
                        i.setType(Material.GOLD_SPADE);
                        break;
                    case IRON_SWORD:
                        i.setType(Material.GOLD_SWORD);
                        break;
                    case IRON_HOE:
                        i.setType(Material.GOLD_HOE);
                        break;
                    case IRON_HELMET:
                        i.setType(Material.GOLD_HELMET);
                        break;
                    case IRON_CHESTPLATE:
                        i.setType(Material.GOLD_CHESTPLATE);
                        break;
                    case IRON_LEGGINGS:
                        i.setType(Material.GOLD_LEGGINGS);
                        break;
                    case IRON_BOOTS:
                        i.setType(Material.GOLD_BOOTS);
                        break;
                    // Gold gold gold
                    case GOLD_PICKAXE:
                        i.setType(Material.GOLD_PICKAXE);
                        break;
                    case GOLD_AXE:
                        i.setType(Material.GOLD_AXE);
                        break;
                    case GOLD_SPADE:
                        i.setType(Material.GOLD_SPADE);
                        break;
                    case GOLD_SWORD:
                        i.setType(Material.GOLD_SWORD);
                        break;
                    case GOLD_HOE:
                        i.setType(Material.GOLD_HOE);
                        break;
                    case GOLD_HELMET:
                        i.setType(Material.GOLD_HELMET);
                        break;
                    case GOLD_CHESTPLATE:
                        i.setType(Material.GOLD_CHESTPLATE);
                        break;
                    case GOLD_LEGGINGS:
                        i.setType(Material.GOLD_LEGGINGS);
                        break;
                    case GOLD_BOOTS:
                        i.setType(Material.GOLD_BOOTS);
                        break;
                    // Diamond diamond diamond
                    case DIAMOND_PICKAXE:
                        i.setType(Material.GOLD_PICKAXE);
                        break;
                    case DIAMOND_AXE:
                        i.setType(Material.GOLD_AXE);
                        break;
                    case DIAMOND_SPADE:
                        i.setType(Material.GOLD_SPADE);
                        break;
                    case DIAMOND_SWORD:
                        i.setType(Material.GOLD_SWORD);
                        break;
                    case DIAMOND_HOE:
                        i.setType(Material.GOLD_HOE);
                        break;
                    case DIAMOND_HELMET:
                        i.setType(Material.GOLD_HELMET);
                        break;
                    case DIAMOND_CHESTPLATE:
                        i.setType(Material.GOLD_CHESTPLATE);
                        break;
                    case DIAMOND_LEGGINGS:
                        i.setType(Material.GOLD_LEGGINGS);
                        break;
                    case DIAMOND_BOOTS:
                        i.setType(Material.GOLD_BOOTS);
                        break;
                    // Chain chain chain
                    case CHAINMAIL_HELMET:
                        i.setType(Material.GOLD_HELMET);
                        break;
                    case CHAINMAIL_CHESTPLATE:
                        i.setType(Material.GOLD_CHESTPLATE);
                        break;
                    case CHAINMAIL_LEGGINGS:
                        i.setType(Material.GOLD_LEGGINGS);
                        break;
                    case CHAINMAIL_BOOTS:
                        i.setType(Material.GOLD_BOOTS);
                        break;
                    default:
                        break;
                }
            }
        } for (ItemStack i : p.getInventory().getArmorContents()) {
            if (i != null) {
                switch (i.getType()) {
                    // Leather leather leather
                    case LEATHER_HELMET:
                        i.setType(Material.GOLD_HELMET);
                        break;
                    case LEATHER_CHESTPLATE:
                        i.setType(Material.GOLD_CHESTPLATE);
                        break;
                    case LEATHER_LEGGINGS:
                        i.setType(Material.GOLD_LEGGINGS);
                        break;
                    case LEATHER_BOOTS:
                        i.setType(Material.GOLD_BOOTS);
                        break;
                    // Iron iron iron
                    case IRON_HELMET:
                        i.setType(Material.GOLD_HELMET);
                        break;
                    case IRON_CHESTPLATE:
                        i.setType(Material.GOLD_CHESTPLATE);
                        break;
                    case IRON_LEGGINGS:
                        i.setType(Material.GOLD_LEGGINGS);
                        break;
                    case IRON_BOOTS:
                        i.setType(Material.GOLD_BOOTS);
                        break;
                    // Diamond diamond diamond
                    case DIAMOND_HELMET:
                        i.setType(Material.GOLD_HELMET);
                        break;
                    case DIAMOND_CHESTPLATE:
                        i.setType(Material.GOLD_CHESTPLATE);
                        break;
                    case DIAMOND_LEGGINGS:
                        i.setType(Material.GOLD_LEGGINGS);
                        break;
                    case DIAMOND_BOOTS:
                        i.setType(Material.GOLD_BOOTS);
                        break;
                    // Chain chain chain
                    case CHAINMAIL_HELMET:
                        i.setType(Material.GOLD_HELMET);
                        break;
                    case CHAINMAIL_CHESTPLATE:
                        i.setType(Material.GOLD_CHESTPLATE);
                        break;
                    case CHAINMAIL_LEGGINGS:
                        i.setType(Material.GOLD_LEGGINGS);
                        break;
                    case CHAINMAIL_BOOTS:
                        i.setType(Material.GOLD_BOOTS);
                        break;
                    default:
                        break;
                }
            }
        }

    }

    private void goldenBless(Player p) {
        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null) {
                switch (i.getType()) {
                    case GOLD_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        repair(i);
                        break;
                    case GOLD_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        repair(i);
                    case GOLD_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        repair(i);
                        break;
                    case GOLD_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        repair(i);
                        break;
                    case GOLD_HOE:
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        repair(i);
                        break;
                    case GOLD_HELMET:
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                        repair(i);
                        break;
                    case GOLD_CHESTPLATE:
                        i.addEnchantment(Enchantment.THORNS, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                        repair(i);
                        break;
                    case GOLD_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                        repair(i);
                        break;
                    case GOLD_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                        repair(i);
                        break;
                    default:
                        break;
                }
            }
        } for (ItemStack i : p.getInventory().getArmorContents()) {
            if (i != null) {
                switch (i.getType()) {
                    case GOLD_HELMET:
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                        repair(i);
                        break;
                    case GOLD_CHESTPLATE:
                        i.addEnchantment(Enchantment.THORNS, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                        repair(i);
                        break;
                    case GOLD_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                        repair(i);
                        break;
                    case GOLD_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 3);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                        repair(i);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}