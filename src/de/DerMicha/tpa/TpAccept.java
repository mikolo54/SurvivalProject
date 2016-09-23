package de.DerMicha.tpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAccept implements CommandExecutor{

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
			p.teleport(a);
			Tpa.tpa.remove(a);
			a.sendMessage("§7Du hast die Teleports-Anfrage von §e"+p.getName()+"§a angenommen!");
			p.sendMessage("§e"+a.getName()+"§7 hat deine Teleports-Anfrage§a angenommen!");
		} else {
			a.sendMessage("§cDu hast keine Teleports-Anfrage erhalten!");
		}
		
		return false;
	}
	
}
