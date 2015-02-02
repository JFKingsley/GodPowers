package com.FriedTaco.taco.godPowers.commands;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BlessCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public BlessCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.bless")) {
                if (args.length == 1) {
                    Player player2 = plugin.getServer().getPlayer(args[0]);
                    if (player2 != null) {
                        player.sendMessage(ChatColor.BLUE + StringHandler.BLESS_PLAYER2 + " " + player2.getDisplayName());
                        player2.sendMessage(ChatColor.BLUE + StringHandler.BLESS_BLESSED);
                        bless(player2);
                    } else {
                        player.sendMessage(ChatColor.RED + StringHandler.BLESS_CANNOTBLESS);
                    }
                } else if (args.length == 0) {
                    player.sendMessage(ChatColor.BLUE + StringHandler.BLESS_BLESSED);
                    bless(player);
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.BLESS_SYNTAXERROR);
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return true;
    }

    private void bless(Player p) {
        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null) {
                switch (i.getType()) {
                    case IRON_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case IRON_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case IRON_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case BOW:
                        i.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
                        i.addEnchantment(Enchantment.ARROW_FIRE, 1);
                        i.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                        break;
                    case IRON_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    case WOOD_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    case WOOD_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case WOOD_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case WOOD_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case STONE_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    case STONE_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case STONE_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case STONE_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case DIAMOND_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    case DIAMOND_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case DIAMOND_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case DIAMOND_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case GOLD_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    case GOLD_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case GOLD_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case GOLD_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    case LEATHER_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    default:
                        break;
                }
            }
        }
        for (ItemStack i : p.getInventory().getArmorContents()) {
            if (i != null) {
                switch (i.getType()) {
                    case LEATHER_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case LEATHER_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case CHAINMAIL_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case IRON_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case DIAMOND_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    case GOLD_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}