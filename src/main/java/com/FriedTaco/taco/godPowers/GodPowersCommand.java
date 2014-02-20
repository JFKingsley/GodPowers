package com.FriedTaco.taco.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodPowersCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public GodPowersCommand(godPowers instance) 
    {
        plugin = instance;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;	
    			
				if(args.length == 0)
				{
				player.sendMessage(ChatColor.DARK_AQUA+"About the "+ChatColor.DARK_GREEN+"godPowers"+ChatColor.DARK_AQUA+" Project:");		
				player.sendMessage(ChatColor.GOLD+"godPowers is an "+ChatColor.RED+"Open Source"+ChatColor.GOLD+" plugin created in December 2011");	
				player.sendMessage(ChatColor.GOLD+"by "+ChatColor.BLUE+"FriedTaco"+ChatColor.GOLD+". It is currently developed by "+ChatColor.BLUE+"Swift"+ChatColor.GOLD+".");
				player.sendMessage(ChatColor.GOLD+"The goal is to provide a set of commands to give users powers based on greek and roman mythology.");	
				player.sendMessage(ChatColor.DARK_AQUA+"Tips:");	
				player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"Use "+ChatColor.RED+"/godpowers commands"+ChatColor.GREEN+" to see all commands");
				player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"Use "+ChatColor.RED+"/godpowers update"+ChatColor.GREEN+" to manually install updates");
				player.sendMessage(ChatColor.DARK_AQUA+"Developers:");	
				player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"SwiftDev"+ChatColor.BLUE+" (Developer)");	
				player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"FriedTaco"+ChatColor.BLUE+" (Former Developer)");	
				player.sendMessage(ChatColor.DARK_AQUA+"Useful Links:");	
				player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"#godpowers @ irc.esper.net"+ChatColor.GOLD+" IRC Chat");	
				player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"bit.ly/GodPowers"+ChatColor.GOLD+" Bukkit Plugin Page");
				player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"jfkingsley.co.uk/godpowers"+ChatColor.GOLD+" Server list");
				player.sendMessage(ChatColor.DARK_AQUA+"Donation Info:");	
				player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"goldgamermc@gmail.com"+ChatColor.GOLD+" Paypal");	
				}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("commands")){
		    		if(player.hasPermission("godpowers.commands"))
		    		{
					player.sendMessage(ChatColor.BLUE + "You can use the following commands: (< > = Optional [ ] = Required)");
					for(String s : plugin.list.keySet())
						if(player.hasPermission("godpowers."+plugin.list.get(s)))
								player.sendMessage(ChatColor.AQUA + "/" + s + " " + plugin.list.get(s));
		    		}else
		    		{
		    			player.sendMessage("The gods prevent you from using this command.");
		    			return true;
		    		}
				}
				if(args[0].equalsIgnoreCase("update")){
					Updater updater = new Updater(plugin, 33866, plugin.file, Updater.UpdateType.NO_DOWNLOAD, false);
					if(updater.getResult().equals(Updater.UpdateResult.NO_UPDATE)){
						player.sendMessage(ChatColor.GREEN+"godPowers: "+ChatColor.GOLD+"There is currently no updates available.");		
					}else if(updater.getResult().equals(Updater.UpdateResult.UPDATE_AVAILABLE)){
						player.sendMessage(ChatColor.GREEN+"godPowers: "+ChatColor.GOLD+"There is currently an update available, downloading now...");	
						new Updater(plugin, 33866, plugin.file, Updater.UpdateType.NO_VERSION_CHECK, true);
					}
				}
				if(args[0].equalsIgnoreCase("credits")){
					player.sendMessage(ChatColor.DARK_AQUA+"Credits:");	
					player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"UnceCrafter"+ChatColor.BLUE+" (/poseidon)");	
				}
				}
				else
				{
					player.sendMessage("Incorrect syntax. Use '/godpowers [commands/update]'");
				}
				return true;    		
    	}        
        return false;
    }
}
