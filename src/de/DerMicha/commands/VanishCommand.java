package de.DerMicha.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.DerMicha.Survival;

public class VanishCommand implements CommandExecutor{
	
	public Survival main;
	public VanishCommand(Survival main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = (Player)cs;
		if(p.hasPermission("survival.vanish")){
			if(args.length >= 0){
				if(!main.vanished.contains(p)){
					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 255, true));
					main.vanished.add(p);
					p.sendMessage("§3Du bist nun im Vanish Mode");
					for(Player all : Bukkit.getOnlinePlayers()){
						all.hidePlayer(p);
					}
				} else {
					main.vanished.remove(p);
					p.removePotionEffect(PotionEffectType.INVISIBILITY);
					p.sendMessage("§3Du bist nun nicht mer im Vanish Mode");
					for(Player all : Bukkit.getOnlinePlayers()){
						all.showPlayer(p);
					}
				}
			}
		}
		
		return false;
	}

	
	
}
