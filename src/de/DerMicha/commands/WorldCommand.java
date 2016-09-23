package de.DerMicha.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldCommand implements CommandExecutor {

	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {

		Player p = (Player) cs;
		if (p instanceof Player) {
			if(p.hasPermission("survival.world")){

				if (args.length == 0) {
					p.sendMessage("§eHallo");
					return false;
				}

				if (args[0].equalsIgnoreCase("create")) {
					if (args.length >= 2) {
						WorldCreator world = new WorldCreator(args[1]);
						p.sendMessage("§eDie Welt§7(" + args[1] + "§7) §ewird erstellt...");
						world.createWorld();
						p.sendMessage("§eDu hast die Welt§7(" + args[1] + "§7) §eerstellt");
					} else {
						p.sendMessage("§c/world create <Name>");
					}
				}

				if (args[0].equalsIgnoreCase("tp")) {
					if (args.length >= 2) {
						if (Bukkit.getServer().getWorld(args[1]).getWorldFolder().exists()) {
							Location loc = Bukkit.getServer().getWorld(args[1]).getSpawnLocation();
							p.teleport(loc);
							p.sendMessage("§eDu hast dich zur Welt§7(" + args[1] + "§7) §eteleportiert");
						} else {
							p.sendMessage("§cDie Welt§7(" + args[1] + "§7) §cexistiert nicht");
						}
					}
				}

				if (args[0].equalsIgnoreCase("delete")) {
					if (args.length >= 2) {
						if (Bukkit.getServer().getWorld(args[1]).getWorldFolder().exists()) {
							Bukkit.getServer().getWorld(args[1]).getWorldFolder().delete();
							p.sendMessage("§eDu hast die Welt§7(" + args[1] + "§7) §egelöscht!");
							p.teleport(Bukkit.getWorld("spawn").getSpawnLocation());
						} else {
							p.sendMessage("§cDie Welt§7(" + args[1] + "§7) §cexistiert nicht");
						}
					}
				}

				if (args[0].equalsIgnoreCase("setspawn")) {
					if (args.length >= 2) {
						if(p.getWorld().getName() == Bukkit.getServer().getWorld(args[1]).getName()){
							if (Bukkit.getServer().getWorld(args[1]).getWorldFolder().exists()) {
								p.sendMessage("§eDu hast für die Welt§7(" + args[1] + "§7) §eeinen Spawn gesetzt!");
								Bukkit.getServer().getWorld(args[1]).setSpawnLocation(p.getLocation().getBlockX(),
										p.getLocation().getBlockY(), p.getLocation().getBlockZ());
							} else {
								p.sendMessage("§cDie Welt§7(" + args[1] + "§7) §cexistiert nicht");
							}
						} else {
							p.sendMessage("§cDu bist nicht in der Welt§7("+args[1]+"§7)");
							return false;
						}
					}
				}
			}
		}
		return false;

	}
}
