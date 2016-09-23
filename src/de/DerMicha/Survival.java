package de.DerMicha;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import de.Dargen.npc.NPCCommand;
import de.Dargen.npc.NPCManager;
import de.DerMicha.bank.BankHandler;
import de.DerMicha.broadcast.BroadcastCommand;
import de.DerMicha.commands.DebugtestCommand;
import de.DerMicha.commands.FeedCommand;
import de.DerMicha.commands.FlyCommand;
import de.DerMicha.commands.GamemodeCommand;
import de.DerMicha.commands.GodCommand;
import de.DerMicha.commands.GodHandler;
import de.DerMicha.commands.HealCommand;
import de.DerMicha.commands.HelpCommand;
import de.DerMicha.commands.VanishCommand;
import de.DerMicha.commands.WorldCommand;
import de.DerMicha.lang.LanguageManager;
import de.DerMicha.money.MoneyCommand;
import de.DerMicha.recipe.BanRecipe;
import de.DerMicha.recipe.Recipes;
import de.DerMicha.regions.BuildListener;
import de.DerMicha.regions.ClaimKitListener;
import de.DerMicha.regions.RegionManager;
import de.DerMicha.tpa.TpAccept;
import de.DerMicha.tpa.TpDeny;
import de.DerMicha.tpa.Tpa;

public class Survival extends JavaPlugin {

	public MoneyCommand moneycmd = new MoneyCommand(this);
	public ArrayList<Player> vanished = new ArrayList<Player>();
	public ArrayList<Player> godded = new ArrayList<Player>();
	public LanguageManager langs;
	public RegionManager regions;
	public NPCManager npcs;
	
	public static Survival instace;
	
	public void onEnable() {
		instace = this;
		Bukkit.getPluginManager().registerEvents(new JoinListener(this), this);
		Bukkit.getPluginManager().registerEvents(new BanRecipe(), this);
		Bukkit.getPluginManager().registerEvents(new Recipes(), this);
		Bukkit.getPluginManager().registerEvents(new GodHandler(this), this);
		//Bukkit.getPluginManager().registerEvents(new SuperMenuListener(), this);
		Bukkit.getPluginManager().registerEvents(new ClaimKitListener(), this);
		Bukkit.getPluginManager().registerEvents(new BuildListener(), this);
		getCommand("world").setExecutor(new WorldCommand());
		getCommand("money").setExecutor(new MoneyCommand(this));
		getCommand("vanish").setExecutor(new VanishCommand(this));
		getCommand("bank").setExecutor(new BankHandler(this));
		getCommand("gm").setExecutor(new GamemodeCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("feed").setExecutor(new FeedCommand());
		getCommand("god").setExecutor(new GodCommand(this));
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("help").setExecutor(new HelpCommand());
		getCommand("dbg").setExecutor(new DebugtestCommand());
		getCommand("npc").setExecutor(new NPCCommand());
		getCommand("tpa").setExecutor(new Tpa());
		getCommand("tpdeny").setExecutor(new TpDeny());
		getCommand("tpaccept").setExecutor(new TpAccept());
		getCommand("broadcast").setExecutor(new BroadcastCommand());
		
		ItemStack result = new ItemStack(Material.WORKBENCH);
		ShapedRecipe recipe = new ShapedRecipe(result);
		recipe.shape("BBA", 
				     "BBA", 
				     "AAA");
		recipe.setIngredient('B', Material.WOOD);
		Bukkit.addRecipe(recipe);
		
		langs = new LanguageManager();
		regions = new RegionManager();
		regions.load(this);
		npcs = new NPCManager();
		npcs.load(this);
	}
	
	@Override
	public void onDisable() {
		for(Player all : Bukkit.getOnlinePlayers()){
			all.kickPlayer(langs.getMessage(all, "kick_restart"));
		}
		regions.saveAll();
	}
}
