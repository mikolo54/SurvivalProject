package de.DerMicha.bank;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import de.DerMicha.Survival;

public class BankHandler implements CommandExecutor, Listener{

	public Survival main;
	public BankHandler(Survival survival){
		this.main = survival;
	}
	
	public BankHandler handler;
	
	public Date date;
	
	public String bankPrefix = " §6»§r ";
	
	public void removeFromBankKonto(Player p, double amount){
		File file = new File("plugins/Survival/accounts", p.getUniqueId().toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		double money = cfg.getDouble(p.getName() + ".Bank");
		money = money-amount;
		cfg.set(p.getName() + ".Bank", money);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addToBankKonto(Player p, double d){
		File file = new File("plugins/Survival/accounts", p.getUniqueId().toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		double money = cfg.getDouble(p.getName() + ".Bank");
		money = money+d;
		cfg.set(p.getName() + ".Bank", money);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public double getBankKonto(Player p){
		File file = new File("plugins/Survival/accounts", p.getUniqueId().toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		double money = cfg.getDouble(p.getName() + ".Bank");
		return money;
	}

	public boolean hasEnoughMoney(Player p, int amount){
		File file = new File("plugins/Survival/accounts", p.getUniqueId().toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		double money = cfg.getDouble(p.getName() + ".Bank");
		if(money >= amount){
			return true;
		} else return false;
		
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = (Player)cs;
		if(args.length == 0){
			p.sendMessage(bankPrefix + "§7Aktueller Kontostand: §e"+getBankKonto(p)+Survival.instace.langs.ingameCurrenyDisplay);
			p.sendMessage("");
			p.sendMessage(bankPrefix + "§7Um Geld einzuzahlen, mache /bank deposit <Betrag>");
			p.sendMessage(bankPrefix + "§7Um Geld abzuheben, mache /bank withdraw <Betrag>");
		}
		
		//bank deposit 123
		
		if(args.length == 2){
			if(args[0].equalsIgnoreCase("deposit")){
				Integer amount = Integer.valueOf(args[1]);
				if(main.moneycmd.hasEnoughMoney(p.getUniqueId(), p.getName(), amount)){
					main.moneycmd.removeMoney(p.getUniqueId(), p.getName(), amount);
					addToBankKonto(p, amount);
					p.sendMessage(bankPrefix + "§3Du hast §e"+amount+Survival.instace.langs.ingameCurrenyDisplay+" §3auf dein Bankkonto eingezahlt");
				} else {
					p.sendMessage(bankPrefix + "§cDu hast nicht genügend Geld auf deiner Hand!");
				}
			}
			if(args[0].equalsIgnoreCase("withdraw")){
				Integer amount = Integer.valueOf(args[1]);
				if(hasEnoughMoney(p, amount)){
					main.moneycmd.addMoney(p.getUniqueId(), p.getName(), amount);
					removeFromBankKonto(p, amount);
					p.sendMessage(bankPrefix + "§3Du hast §e"+amount+Survival.instace.langs.ingameCurrenyDisplay+" §3von dein Bankkonto abgehoben");
				} else {
					p.sendMessage(bankPrefix + "§cDu hast nicht genügend Geld auf der Bank!");
				}
			}
		}
		
		return false;
	}
	
}
