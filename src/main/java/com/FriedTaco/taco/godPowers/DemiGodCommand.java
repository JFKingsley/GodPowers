package com.FriedTaco.taco.godPowers;

//import org.bukkit.World;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DemiGodCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public DemiGodCommand(godPowers instance) 
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
    		if(player.hasPermission("godpowers.demigod"))
    		{
	    		if(split.length == 0)
	    		{
	    			if(plugin.DemiGod.contains(player.getName()))
	    			{
	    				plugin.DemiGod.remove(player.getName());
	    				player.sendMessage(ChatColor.BLUE + "You have returned to being mortal.");
	    				return true;
	    			}
	    			else
	    			{
	    				player.sendMessage(ChatColor.BLUE + "The gods have shared their might with you.");
	    				player.sendMessage(ChatColor.BLUE + "You now feel as if fatal wounds are like mere scratches to you!");
	    				plugin.DemiGod.add(player.getName());
	    				player.setHealth(20);
	    				return true;
	        		}
	    		}
	    		else
	    		{
	    			Player targetPlayer = plugin.getServer().getPlayer(split[0]);
	    			if(targetPlayer==null) 
					{
						player.sendMessage(ChatColor.RED + "The user "+split[0]+" does not exist or is not currently logged in.");
					}
	    			else if(targetPlayer == player)
					{
						player.sendMessage(ChatColor.RED + "Please use '/demigod' to make yourself a demigod.");
	
					}
					else
					{
						if(plugin.DemiGod.contains(targetPlayer.getName()))
	        			{
	        				plugin.DemiGod.remove(targetPlayer.getName());
	        				targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " has returned you to being mortal.");
	        				player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " has been returned to being mortal.");
	        			}
	        			else
	            		{
	        				targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " has blessed you with god-like strength!");
	        				targetPlayer.sendMessage(ChatColor.BLUE + "You now feel as if fatal wounds are like mere scratches to you!");
	            			plugin.DemiGod.add(targetPlayer.getName());
	            			targetPlayer.setHealth(20);
	            			player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " is now a demigod.");
	            		}
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
