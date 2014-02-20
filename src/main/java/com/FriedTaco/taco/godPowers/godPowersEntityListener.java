package com.FriedTaco.taco.godPowers;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


public class godPowersEntityListener implements Listener
{
	private final godPowers plugin;
	
    public godPowersEntityListener(godPowers instance) {
        plugin = instance;
    }
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event)
    {
    	Entity e = event.getEntity();
    	if(e instanceof Player && plugin.godmodeEnabled.contains(((Player) e).getName()))
    		event.setCancelled(true);
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event)
    {
    	if(event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();
			if(plugin.godmodeEnabled.contains(player.getName()))
			{
				player.setHealth(20);
				event.setCancelled(true);
			}
			else if(plugin.superJumper.contains(player.getName()) && event.getCause() == DamageCause.FALL)
			{
				event.setCancelled(true);
			}
			else if(plugin.DemiGod.contains(player.getName()))
			{
				event.setDamage((int) (event.getDamage() * plugin.DemiModifier));
			}
		}
    	if(event instanceof EntityDamageByEntityEvent)
		{
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
			if(e.getDamager() instanceof Player)
			{
				Player p = (Player) e.getDamager();
				ItemStack i = p.getItemInHand();
				if(i.containsEnchantment(Enchantment.KNOCKBACK) && i.getEnchantmentLevel(Enchantment.KNOCKBACK) == 10)
				{
					if(!(e.getEntity() instanceof Player))
					{
						Vector v = p.getEyeLocation().getDirection();
						v.setX(v.getX()*25);
						v.setZ(v.getZ()*25);
						v.setY(v.getY()*2);
						e.getEntity().setVelocity(v);
					}
				}
			}
		}
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event)
    {
    	if(event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();
			String name = player.getName();
			if(plugin.burn.contains(name))
			{
				plugin.burn.remove(player.getName());
				player.setFireTicks(0);
			}
			else if(plugin.arrowKill.contains(name))
			{
				plugin.arrowKill.remove(player.getName());
			}
			else if(plugin.curse.containsKey(name))
			{
				plugin.getServer().getScheduler().cancelTask(plugin.curse.get(name).intValue());
			}
		}
    }

	
	public boolean isCancelled() {
		return false;
	}
	public void setCancelled(boolean arg0) {
	}
	
}