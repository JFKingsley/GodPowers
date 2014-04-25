package com.FriedTaco.taco.godPowers.listeners;

import com.FriedTaco.taco.godPowers.godPowers;
import com.FriedTaco.taco.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class EntityListener implements Listener {
    private final godPowers plugin;

    public EntityListener(godPowers instance) {
        plugin = instance;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        Entity e = event.getEntity();
        if (e instanceof Player && plugin.godmodeEnabled.contains(((Player) e).getUniqueId()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (plugin.godmodeEnabled.contains(player.getUniqueId())) {
                player.setHealth(player.getMaxHealth());
                event.setCancelled(true);
            } else if (plugin.isInferno.contains(player.getUniqueId()) && event.getCause() == DamageCause.FIRE) {
                event.setCancelled(true);
            } else if (plugin.superJumper.contains(player.getUniqueId()) && event.getCause() == DamageCause.FALL) {
                event.setCancelled(true);
            } else if (plugin.DemiGod.contains(player.getUniqueId())) {
                event.setDamage((int) (event.getDamage() * plugin.demiModifier));
            }
        }
        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getDamager();
                ItemStack i = p.getItemInHand();
                if (i.containsEnchantment(Enchantment.KNOCKBACK) && i.getEnchantmentLevel(Enchantment.KNOCKBACK) == 10) {
                    if (!(e.getEntity() instanceof Player)) {
                        Vector v = p.getEyeLocation().getDirection();
                        v.setX(v.getX() * 25);
                        v.setZ(v.getZ() * 25);
                        v.setY(v.getY() * 2);
                        e.getEntity().setVelocity(v);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            UUID UUID = player.getUniqueId();
            if (plugin.burn.contains(UUID)) {
                plugin.burn.remove(player.getUniqueId());
                player.setFireTicks(0);
            } else if (plugin.arrowKill.contains(UUID)) {
                plugin.arrowKill.remove(player.getUniqueId());
            } else if (plugin.curse.containsKey(UUID)) {
                plugin.getServer().getScheduler().cancelTask(plugin.curse.get(UUID).intValue());
            } else if (plugin.die.contains(UUID)) {
                PlayerDeathEvent e = (PlayerDeathEvent) event;
                e.setDeathMessage(player.getName() + " " + StringHandler.DIE_DEATHMSG);
                plugin.die.remove(UUID);
            } else if (plugin.medusaDropHead) {
                PlayerDeathEvent e = (PlayerDeathEvent) event;
                Material items[] = {Material.WOOD_SWORD, Material.STONE_SWORD, Material.DIAMOND_SWORD, Material.GOLD_SWORD, Material.IRON_SWORD};
                ArrayList<Material> swords = new ArrayList<Material>();
                Collections.addAll(swords, items);
                if (plugin.isMedusa.contains(UUID)) { // If dead player was a medusa player
                    if (player.getKiller() != null && swords.contains(player.getKiller().getItemInHand().getType())) { // If player was killed by a player holding a sword
                        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                        skullMeta.setDisplayName("Medusa Head");
                        skullMeta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + StringHandler.MEDUSA_LORE));
                        skullMeta.setOwner(e.getEntity().getName());
                        skull.setItemMeta(skullMeta);
                        e.getDrops().add(skull);
                        plugin.isMedusa.remove(player.getUniqueId());
                    }
                }
            }
        }
    }

    public boolean isCancelled() {
        return false;
    }

    public void setCancelled(boolean arg0) {
    }
}