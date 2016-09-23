package de.DerMicha.broadcast;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class BroadcastCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String arg2, String[] args) {
		Player p = (Player)cs;
		if(args.length == 0){
			p.sendMessage("§6§m(§r§m-------§6§m)§r Broadcast-Hilfe §6§m(§r§m------§6§m)");
			p.sendMessage(" §6» §e/broadcast <Nachricht> §8│ §7Sendet eine Massennachricht");
		}
		if(args.length >= 1){
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				if (!sb.toString().equals("")) {
					sb.append(" " + args[i]);
				} else {
					sb.append(args[i]);
				}
			}

			String reason = sb.toString();
			
			reason.trim();
			reason.replaceAll("&", "§");
			
			Bukkit.getServer().broadcastMessage(ChatColor.AQUA + p.getDisplayName() + " §8│ §r" + reason);
		}
		return false;
	}
}
