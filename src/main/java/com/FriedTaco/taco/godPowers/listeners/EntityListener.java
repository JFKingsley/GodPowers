package com.FriedTaco.taco.godPowers.listeners;

import com.FriedTaco.taco.godPowers.godPowers;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

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
            }
        }
    }

    public boolean isCancelled() {
        return false;
    }

    public void setCancelled(boolean arg0) {
    }
}