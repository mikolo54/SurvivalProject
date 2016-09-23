package de.DerMicha.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		if(cs instanceof Player){
			Player p = (Player)cs;
			if(p.hasPermission("survival.heal")){
				if(args.length == 0){
					p.setHealth(20.0D);
					p.setFoodLevel(20);
					p.sendMessage("§aDu hast dich geheilt!");
				} else 
				if(args.length == 1){
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null && target != p){
						target.setHealth(20.0D);
						target.setFoodLevel(20);
						p.sendMessage("§aDu hast den Spieler §e"+target.getName()+" §ageheilt!");
						target.sendMessage("§aDu wurdest von §e"+p.getName()+" §ageheilt");
					} else {
						p.sendMessage("§cDer Spieler ist nicht online oder du selber!");
					}
				} else {
					p.sendMessage("§e- §7/heal [Spieler] [Herzen] §8| §7Heilt einen Spieler");
				}
			}
		}
		
		
		return false;
	}

	
	
}
