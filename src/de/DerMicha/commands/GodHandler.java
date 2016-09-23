package de.DerMicha.commands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import de.DerMicha.Survival;

public class GodHandler implements Listener{
	
	public Survival main;
	public GodHandler(Survival main){
		this.main = main;
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(main.godded.contains(p)){
				e.setCancelled(true);
			} else {
				e.setCancelled(false);
			}
		}
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player)e.getEntity();
			if(main.godded.contains(p)){
				e.setCancelled(true);
			} else {
				e.setCancelled(false);
			}	
		}
	}
	
}
