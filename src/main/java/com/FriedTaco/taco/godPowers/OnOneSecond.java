package com.FriedTaco.taco.godPowers;

import java.util.ListIterator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class OnOneSecond extends BukkitRunnable {
	//Extends BukkitRunnable to create a run method
	 
	private final godPowers plugin;
	//Declares your plugin variable
	 
	public OnOneSecond(godPowers plugin) {
	this.plugin = plugin;
	}
	//This is called from your main class and sets your plugin variable
	 
	public void run() {
	ListIterator<MedusaPlayer> it = plugin.isUnderMedusaInfluence.listIterator();
	if(it.hasNext()){
	MedusaPlayer mplayer = it.next();
	if(mplayer.getTimeLeft() == 1){
	Player player = mplayer.getPlayer();
	player.sendMessage(ChatColor.GREEN+"You feel your skin beginning to crack and suddenly you can move again.");
	plugin.isUnderMedusaInfluence.remove(mplayer);	
	}else{
	mplayer.setTimeLeft(mplayer.getTimeLeft()-1);	
	}
	}
	}	
	}
