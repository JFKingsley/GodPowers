package com.FriedTaco.taco.godPowers;

//import org.bukkit.World;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.FriedTaco.taco.godPowers.Jesus.Raft;

public class JesusCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public JesusCommand(godPowers instance) 
    {
        plugin = instance;
    }

	@SuppressWarnings("unchecked")
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
        	Raft r = (Raft)Jesus.rafts.get(player.getName());
        	Jesus j = new Jesus();
        	if(player.hasPermission("godpowers.jesus"))
    		{
				if (r == null)
				{
					player.sendMessage(ChatColor.BLUE + "You feel as if you can walk on water, just like Jesus!");
					plugin.isJesus.add(player.getName());
					Jesus.rafts.put(player.getName(), j.new Raft());
					return true;
				}
				else
				{
					player.sendMessage(ChatColor.BLUE + "You feel your Jesus-like powers draining from you.");
					Jesus.rafts.remove(player.getName());
					plugin.isJesus.remove(player.getName());
					r.destroyJesusRaft(player);
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
