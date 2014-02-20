package com.FriedTaco.taco.godPowers;

import java.util.ListIterator;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MedusaCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public MedusaCommand(godPowers instance) 
    {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	String[] split = args;
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if(player.hasPermission("godpowers.medusa"))
    		{
    			if(split.length > 0)
    			{
    				player.sendMessage(ChatColor.RED + "Incorrect syntax. Correct usage: '/medusa'");
    				return true;
    			}
    			else
    			{
    				if(plugin.isMedusa.contains(player.getName()) == false){
        				player.sendMessage(ChatColor.GREEN+"The gods have cursed you with the power to turn people to stone.");
        				plugin.isMedusa.add(player.getName());
    				}else{
    					player.sendMessage(ChatColor.GREEN+"The gods have lifted the curse upon you.");
        				plugin.isMedusa.remove(player.getName());
        				ListIterator<MedusaPlayer> it = plugin.isUnderMedusaInfluence.listIterator();
        				if(it.hasNext()){
        				MedusaPlayer mplayer = it.next();
        				if(mplayer.getMedusa().equals(player)){
        				Player player = mplayer.getPlayer();
        				player.sendMessage(ChatColor.AQUA+"Medusa has suddenly disappeared, you can move again.");
        				plugin.isUnderMedusaInfluence.remove(mplayer);
        				}       			
        				}
    				}
    				return true;
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