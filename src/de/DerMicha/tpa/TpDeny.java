package de.DerMicha.tpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpDeny implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
	
		
		Player a = (Player) cs;
		if(Tpa.tpa.containsKey(a)){
			Player p = Tpa.tpa.get(a);
			if(p == null){
				a.sendMessage("§cDer Spieler ist offline!");
				Tpa.tpa.remove(a);
				return true;
			}
			Tpa.tpa.remove(a);
			a.sendMessage("§7Du hast die Teleports-Anfrage von §e"+p.getName()+"§c abgelehnt!");
			p.sendMessage("§e"+p.getName()+"§7 hat deine Teleports-Anfrage§c abgelehnt!");
		} else {
			a.sendMessage("§cDu hast keine Teleports-Anfrage erhalten!");
		}
		
		return false;
	}
	
}
