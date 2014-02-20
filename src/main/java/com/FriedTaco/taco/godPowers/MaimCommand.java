package com.FriedTaco.taco.godPowers;

//import org.bukkit.World;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MaimCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public MaimCommand(godPowers instance) 
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
    		if(player.hasPermission("godpowers.maim"))
    		{
	    		if(split.length == 1)
				{
					Player targetPlayer = plugin.getServer().getPlayer(split[0]);
					if(targetPlayer==null) 
					{
						player.sendMessage(ChatColor.RED + "The user "+split[0]+" does not exist or is not currently logged in.");
					}
					else if(plugin.godmodeEnabled.contains(targetPlayer.getName()))
					{
						player.sendMessage(ChatColor.RED + "Fool! You cannot maim a god!");
					}
					else
					{
						targetPlayer.setHealth(2);
						player.sendMessage(ChatColor.BLUE + "You viciously beat " + targetPlayer.getName() + " within an inch of thier life.");
						targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " has beaten you within an inch of your life!");
					}
					return true;
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Incorrect syntax, use '/maim [playerName]'");
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
