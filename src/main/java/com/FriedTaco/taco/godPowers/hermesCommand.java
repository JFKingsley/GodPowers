package com.FriedTaco.taco.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class hermesCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public hermesCommand(godPowers instance) 
    {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	String[] split = args;
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if(player.hasPermission("godpowers.hermes"))
    		{
    			if(split.length > 0)
    			{
    				player.sendMessage(ChatColor.RED + "Incorrect syntax. Correct usage: '/hermes'");
    				return true;
    			}
    			else
    			{
    				if(plugin.isHermes.contains(player.getName())){
    				plugin.isHermes.remove(player.getName());	
    				player.getInventory().setBoots(null);
    	            player.sendMessage(ChatColor.AQUA+"You feel your hermes like powers slowly draining");
    	            return true;
    				}else{
    				plugin.isHermes.add(player.getName());
    		ItemStack feather = new ItemStack(Material.FEATHER,1);
            ItemMeta meta = feather.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA+"Hermes boots");
            feather.setItemMeta(meta);
            feather.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 10);
            player.getInventory().setBoots(feather);
            player.sendMessage(ChatColor.AQUA+"You feel like you have speed like hermes");
            return true;
                 }
    			}
    		}
    		else
    		{
    			player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
    		}
    	}
		return false;
    }
}