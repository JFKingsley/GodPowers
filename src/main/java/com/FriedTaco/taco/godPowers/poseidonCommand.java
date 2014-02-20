package com.FriedTaco.taco.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class poseidonCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public poseidonCommand(godPowers instance) 
    {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	String[] split = args;
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if(player.hasPermission("godpowers.poseidon"))
    		{
	    		if(split.length == 0)
	    		{	    	
	    			if(plugin.isPoseidon.contains(player.getName()))
	    			{
	    				plugin.isPoseidon.remove(player.getName());
	    				player.sendMessage(ChatColor.BLUE + "You feel your Poseidon like powers flowing away like water.");
	    				return true;
	    			}
	    			else
	    			{
	    				player.sendMessage(ChatColor.BLUE + "You suddenly feel a watery rush overcome you.");
	    				plugin.isPoseidon.add(player.getName());
	    				return true;
	        		}
	    		}
	    		else
	    		{
						player.sendMessage(ChatColor.RED + "Please use '/poseidon' to activate your powers.");				
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
