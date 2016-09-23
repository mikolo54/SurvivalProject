package de.DerMicha.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.DerMicha.Survival;

public class FeedCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {

		Player p = (Player)cs;
		if(p.hasPermission("survival.eat")){
			if(args.length == 0){
				p.setFoodLevel(20);
				p.sendMessage("§a"+Survival.instace.langs.getMessage(p, "feed"));
			}
			if(args.length == 1){
				Player target = Bukkit.getPlayer(args[0]);
				target.setFoodLevel(20);
				String msg = Survival.instace.langs.getMessage(p, "feed_other");
				msg.replaceAll("{0}", target.getName());
				p.sendMessage("§a"+msg);
				String targetmsg = Survival.instace.langs.getMessage(p, "feed_target");
				targetmsg.replaceAll("{0}", target.getName());
				target.sendMessage("§a"+targetmsg);
			}
		}

		return false;
	}

}
