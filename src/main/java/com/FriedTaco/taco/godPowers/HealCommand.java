package com.FriedTaco.taco.godPowers;

//import org.bukkit.World;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public HealCommand(godPowers instance) 
    {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	String[] split = args;
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if(player.hasPermission("godpowers.heal"))
    		{
	    		if(split.length==0)
				{
	        		player.sendMessage(ChatColor.BLUE + "Thou hath been healed! Huzzah!");
	        		player.setHealth(20);
	        		return true;
				}
	        	else
	        	{
	        		Player targetPlayer = plugin.getServer().getPlayer(split[0]);
	        		if(targetPlayer==null) 
					{
						player.sendMessage(ChatColor.RED + "The user "+split[0]+" does not exist or is not currently logged in.");
					}
					if(targetPlayer == player)
					{
						player.sendMessage(ChatColor.RED + "To heal yourself, just type '/heal'");
	
					}
					else
					{
						player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " has been healed.");
						targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " has healed you! Huzzah!");	
						targetPlayer.setHealth(20);
					}
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
