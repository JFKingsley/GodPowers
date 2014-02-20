package com.FriedTaco.taco.godPowers;

//import org.bukkit.World;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DieCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public DieCommand(godPowers instance) 
    {
        plugin = instance;
    }

	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if(player.hasPermission("godpowers.die"))
    		{
    			if(plugin.godmodeEnabled.contains(player.getName()))
    			{
    				player.sendMessage(ChatColor.BLUE + "Your godly powers prevent you from death.");
    				return true;
    			}
    			else
    			{			
    				player.setHealth(0);
    				plugin.dropDeadItems(player);
    				player.sendMessage("You have died.");
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
