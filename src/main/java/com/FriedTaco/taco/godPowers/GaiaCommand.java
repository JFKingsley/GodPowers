package com.FriedTaco.taco.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GaiaCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public GaiaCommand(godPowers instance) 
    {
        plugin = instance;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if(player.hasPermission("godpowers.gaia"))
    		{	
				if(args.length == 0)
				{
					if(plugin.gaia.contains(player.getName()))
					{
						player.sendMessage(ChatColor.DARK_GREEN + "The earth no longer rejuvenates with your every step.");
						plugin.gaia.remove(player.getName());
					}
					else
					{
						player.sendMessage(ChatColor.DARK_GREEN + "The essence of life spreads from you, rejuvenating the world around you.");
						plugin.gaia.add(player.getName());
					}
					return true;
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Incorrect syntax, use '/gaia'");
					return true;
				}
    		}
    		else
    		{
    			player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
    			return true;
    		}
    	}        
        return false;
    }
}
