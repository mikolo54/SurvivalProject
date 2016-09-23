package de.DerMicha.jobs;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobsCommand implements CommandExecutor{

	public HashMap<Player, JobsEnum> jbs = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		// Job - browse
		// Job - help
		// Job - info
		
		if(args.length == 0){
			
		}
		
		if
		
		return false;
	}
	
	public void setJob(Player name, JobsEnum jobs){
		jbs.put(name, jobs);
	}
	
	public void getJob(Player name){
		jbs.get(name);
	}

	
	
}
