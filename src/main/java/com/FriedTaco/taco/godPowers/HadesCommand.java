package com.FriedTaco.taco.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HadesCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public HadesCommand(godPowers instance) 
    {
        plugin = instance;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if(player.hasPermission("godpowers.hades"))
    		{	
				if(args.length == 0)
				{
					if(plugin.hades.contains(player.getName()))
					{
						player.sendMessage(ChatColor.DARK_RED + "You no longer corrupt the ground you walk on.");
						plugin.hades.remove(player.getName());
					}
					else
					{
						player.sendMessage(ChatColor.DARK_RED + "The fires of the nether begin to corse through your veins.");
						plugin.hades.add(player.getName());
					}
					return true;
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Incorrect syntax, use '/hades'");
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
