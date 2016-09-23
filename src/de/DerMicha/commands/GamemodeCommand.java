package de.DerMicha.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		Player p = (Player)cs;
		if(p.hasPermission("survival.gm")){
			if(args.length == 0){
				
			}
			
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("0")){
					p.setGameMode(GameMode.SURVIVAL);
					p.sendMessage("§aDein Gamemode wurde auf §e"+p.getGameMode()+" §agewechselt");
				}
				if(args[0].equalsIgnoreCase("1")){
					p.setGameMode(GameMode.CREATIVE);
					p.sendMessage("§aDein Gamemode wurde auf §e"+p.getGameMode()+" §agewechselt");
				}
				if(args[0].equalsIgnoreCase("2")){
					p.setGameMode(GameMode.ADVENTURE);
					p.sendMessage("§aDein Gamemode wurde auf §e"+p.getGameMode()+" §agewechselt");
				}
				if(args[0].equalsIgnoreCase("3")){
					p.setGameMode(GameMode.SPECTATOR);
					p.sendMessage("§aDein Gamemode wurde auf §e"+p.getGameMode()+" §agewechselt");
				}
			}
			
			if(args.length == 2){
				Player target = Bukkit.getPlayer(args[1]);
				if(args[0].equalsIgnoreCase("0")){
					if(target != null){
						target.setGameMode(GameMode.SURVIVAL);
						target.sendMessage("§aDein Gamemode wurde auf §e"+p.getGameMode()+" §agewechselt");
						p.sendMessage("§aDu hast den Spieler §e"+target.getName()+" §aauf den Gamemode §e"+target.getGameMode() + " §agesetzt!");
					} else {
						p.sendMessage("§cDer Spieler existiert nicht");
					}
				}
				if(args[0].equalsIgnoreCase("1")){
					if(target != null){
						target.setGameMode(GameMode.CREATIVE);
						target.sendMessage("§aDein Gamemode wurde auf §e"+p.getGameMode()+" §agewechselt");
						p.sendMessage("§aDu hast den Spieler §e"+target.getName()+" §aauf den Gamemode §e"+target.getGameMode() + " §agesetzt!");
					} else {
						p.sendMessage("§cDer Spieler existiert nicht");
					}
				}
				if(args[0].equalsIgnoreCase("2")){
					if(target != null){
						target.setGameMode(GameMode.ADVENTURE);
						target.sendMessage("§aDein Gamemode wurde auf §e"+p.getGameMode()+" §agewechselt");
						p.sendMessage("§aDu hast den Spieler §e"+target.getName()+" §aauf den Gamemode §e"+target.getGameMode() + " §agesetzt!");
					} else {
						p.sendMessage("§cDer Spieler existiert nicht");
					}
				}
				if(args[0].equalsIgnoreCase("3")){
					if(target != null){
						target.setGameMode(GameMode.SPECTATOR);
						target.sendMessage("§aDein Gamemode wurde auf §e"+p.getGameMode()+" §agewechselt");
						p.sendMessage("§aDu hast den Spieler §e"+target.getName()+" §aauf den Gamemode §e"+target.getGameMode() + " §agesetzt!");
					} else {
						p.sendMessage("§cDer Spieler existiert nicht");
					}
				}
			}
		}
		
		return false;
	}

	
	
}
