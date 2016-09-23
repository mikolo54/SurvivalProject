package de.DerMicha.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.DerMicha.Survival;

public class GodCommand implements CommandExecutor{
	
	public Survival main;
	public GodCommand(Survival main){
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = (Player)cs;
		if(p.hasPermission("survival.god")){
			if(args.length == 0){
				if(main.godded.contains(p)){
					main.godded.remove(p);
					p.sendMessage("§aGod-Modus deaktiviert");
				} else if(!main.godded.contains(p)){
					main.godded.add(p);
					p.sendMessage("§aGod-Modus aktiviert");
					p.setHealth(20.0D);
					p.setFoodLevel(20);
				}
			}
			if(args.length == 1){
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null){
					p.sendMessage("§cDer Spieler ist offline!");
					return true;
				}
				
				if(main.godded.contains(target)){
					main.godded.remove(target);
					p.sendMessage("§aDu hast den Spieler §e"+target.getName()+" §aaus den God-Modus rausgeschmissen");
				} else {
					main.godded.add(target);
					p.sendMessage("§aDu hast den Spieler §e"+target.getName()+" §ain den God-Modus versetzt");
					target.setHealth(20.0D);
					target.setFoodLevel(20);
				}
			}
		}
		
		return false;
	}

}
