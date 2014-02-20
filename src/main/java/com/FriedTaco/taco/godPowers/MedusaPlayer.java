package com.FriedTaco.taco.godPowers;

import org.bukkit.entity.Player;

public class MedusaPlayer {
private Player player;
private int timeLeft;
private Player medusa;

public MedusaPlayer(Player player, int timeLeft, Player medusa){
this.player = player;
this.timeLeft = timeLeft;
this.medusa = medusa;
}
public void setTimeLeft(int timeLeft){
this.timeLeft = timeLeft;
}
public Player getPlayer(){
return this.player;	
}
public int getTimeLeft(){
return this.timeLeft;	
}
public Player getMedusa(){
return this.medusa;	
}
}
