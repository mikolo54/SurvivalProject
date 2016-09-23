package de.DerMicha.tpa;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpa implements CommandExecutor{

	public static HashMap<Player, Player> tpa = new HashMap<Player, Player>();
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
	
		
		Player p = (Player) cs;
		if (args.length == 1) {
			Player a = Bukkit.getPlayer(args[0]);
			
			if(a == null){
				p.sendMessage("§cDer Spieler ist offline!");
				return true;
			}
			
			if(tpa.containsKey(a) && tpa.get(a) == p){
				p.sendMessage("§cDu hast den Spieler bereits eine Anfrage gesendet!");
				return true;
			}
			
			p.sendMessage("§7Du hast den Spieler §e"+a.getName()+" §7eine Teleports-Anfrage gesendet!");
			a.sendMessage("§7Du hast eine Teleports-Anfrage von §e"+p.getName()+" §7erhalten!");
			a.sendMessage("§a/tpaccept §e- §7Bestätigt die Anfrage");
			a.sendMessage("§c/tpadeny §e- §7Lehnt die Anfrage ab");
			
			if(tpa.containsKey(a)){
				tpa.remove(a);
			}
			
			tpa.put(a, p);
			
		} else {
			p.sendMessage("§c/tpa <Spieler>");
		}
		
		return false;
	}
	
}
