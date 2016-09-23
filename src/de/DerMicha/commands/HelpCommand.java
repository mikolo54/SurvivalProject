package de.DerMicha.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class HelpCommand implements CommandExecutor {

	// Â <-- HURENSHOHN
	@Override
	public boolean onCommand(CommandSender s, Command c, String str, String[] args) {
		if(s instanceof Player){
			Player p = (Player) s;
			p.sendMessage("§r §8[ §b§lHilfe §8]");
			p.sendMessage("§r §3>> §bTutorials erneut ansehen");
			p.sendMessage("§r §3>> §bSpieler melden");
			p.sendMessage("§r §3>> §bBug melden");
			p.sendMessage("§r §3>> §bTutorials ansehen");
			
		}else{
			s.sendMessage(ChatColor.RED+"FEHLER: Dieser Befehl ist nicht in der Konsole verfuegbar.");
		}
		return true;
	}

	

}
