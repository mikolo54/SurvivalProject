package de.DerMicha.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {

		Player p = (Player)cs;
		if(p.hasPermission("survival.fly")){
			if(args.length == 0){
				if(p.getAllowFlight()){
					p.setAllowFlight(false);
					p.setFlying(false);
					p.sendMessage("§aDu bist aus den Flug-Modus gegangen");
				} else {
					p.setAllowFlight(true);
					p.setFlying(true);
					p.sendMessage("§aDu bist in den Flug-Modus gewechselt");
				}
			}
		}

		return false;
	}

}
