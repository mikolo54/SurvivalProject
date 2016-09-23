package de.DerMicha.money;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.DerMicha.Survival;

public class MoneyCommand implements CommandExecutor{

	public Survival main;
	public MoneyCommand(Survival main){
		this.main = main;
	}
	
	public String moneyPrefix = " §6» ";
	public File file;
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = (Player)cs;
		
		if(args.length == 0){
			p.sendMessage(moneyPrefix+"§bDein Kontostand: §3"+getMoney(p.getUniqueId(), p.getName())+"€");
		} else if(args.length == 3){
			if(args[0].equalsIgnoreCase("pay")){
				//money pay SadLea 100
				Player target = Bukkit.getServer().getPlayer(args[1]);
				Double amount = Double.valueOf(args[2]);
				if(hasEnoughMoney(p.getUniqueId(), p.getName(), amount)){
					if(target == null){
						p.sendMessage(moneyPrefix+"§cDieser Spieler ist nicht online");
						return false;
					} else if(target == p){
						p.sendMessage(moneyPrefix+"§cDu kannst dir selber kein Geld überweisen");
						return false;
					} else {
						addMoney(target.getUniqueId(), target.getName(), amount);
						removeMoney(p.getUniqueId(), p.getName(), amount);
						p.sendMessage(moneyPrefix+"§bDu hast den Spieler §e"+target.getName()+" §3"+amount+"€ §bgegeben");
						target.sendMessage(moneyPrefix+"§bDu hast von den Spieler §e"+p.getName()+" §3"+amount+"€ §bbekommen");
					}
				} else {
					p.sendMessage(moneyPrefix+"§cDu hast nicht genügend Geld");
				}
			}
			if(p.hasPermission("survival.money")){
				if(args[0].equalsIgnoreCase("add")){
					Player target = Bukkit.getPlayer(args[1]);
					Double amount = Double.valueOf(args[2]);
					addMoney(target.getUniqueId(), target.getName(), amount);
					p.sendMessage(moneyPrefix+"§bSpieler §e"+target.getName()+" §bbekam §3"+amount+"€");
				} else if(args[0].equalsIgnoreCase("remove")){
					Player target = Bukkit.getPlayer(args[1]);
					Double amount = Double.valueOf(args[2]);
					removeMoney(target.getUniqueId(), target.getName(), amount);
					p.sendMessage(moneyPrefix+"§bSpieler §e"+target.getName()+" §bverlor §3"+amount+"€");
				} else if(args[0].equalsIgnoreCase("set")){
					Player target = Bukkit.getPlayer(args[1]);
					Double amount = Double.valueOf(args[2]);
					setMoney(target.getUniqueId(), target.getName(), amount);
					p.sendMessage(moneyPrefix+"§bSpieler §e"+target.getName()+" §bwurden §3"+amount+"€ §bgesetzt");
				}
			} else {
				p.sendMessage(moneyPrefix+"§cKeine Rechte");
			}
		}
		
		return false;
	}
	
	public double getMoney(UUID uuid, String name){
		file = new File(Survival.instace.getDataFolder()+"/accounts", uuid.toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		double money = cfg.getDouble(name + ".geld");
		return money;
	}
	
	public void addMoney(UUID uuid, String name, double amount){
		file = new File(Survival.instace.getDataFolder()+"/accounts", uuid.toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		double money = cfg.getDouble(name + ".geld");
		money = money+amount;
		cfg.set(name + ".geld", money);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeMoney(UUID uuid, String name, double amount){
		
		file = new File(Survival.instace.getDataFolder()+"/accounts", uuid.toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		double money = cfg.getDouble(name + ".geld");
		money = money-amount;
		cfg.set(name + ".geld", money);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMoney(UUID uuid, String name, double amount){
		file = new File(Survival.instace.getDataFolder()+"/accounts", uuid.toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		cfg.set(name + ".geld", amount);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean hasEnoughMoney(UUID uuid, String name, double amount){
		file = new File(Survival.instace.getDataFolder()+"/accounts", uuid.toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		double money = cfg.getDouble(name + ".geld");
		if(money >= amount){
			return true;
		} else return false;
		
	}
	
}
