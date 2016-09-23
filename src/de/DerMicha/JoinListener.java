package de.DerMicha;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import de.DerMicha.bank.BankHandler;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class JoinListener implements Listener{

	public boolean onDamage;
	public boolean onFreeze;
	public int timer;

	public Survival main;

	public JoinListener(Survival main){
		this.main = main;
	}

	@EventHandler
	public void onTeleport(PlayerTeleportEvent e){
		List<Player> ls = new ArrayList<Player>();
		for(Player pl : Bukkit.getOnlinePlayers())ls.add(pl);
		Util.runHelix(e.getTo(), ls);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		PermissionUser user = PermissionsEx.getUser(e.getPlayer());
		String s = "";
		if(e.getPlayer().hasPermission("survival.admin")){
			s = s+"§4";
		}else if(e.getPlayer().hasPermission("survival.tl")){
			s = s+"§4";
		}else if(e.getPlayer().hasPermission("survival.dev")){
			s = s+"§3";
		}else if(e.getPlayer().hasPermission("survival.mod")){
			s = s+"§1";
		}else if(e.getPlayer().hasPermission("survival.support")){
			s = s+"§2";
		}else if(e.getPlayer().hasPermission("survival.architekt")){
			s = s+"§6";
		}else if(e.getPlayer().hasPermission("survival.premium")){
			s = s+"§d";
		} else if(e.getPlayer().hasPermission("survival.spieler")){
			s = s+"§8";
		}

		String message = e.getMessage();
		message.replaceAll("&", "§");
		
		e.setFormat(user.getPrefix().replaceAll("&", "§")+" §8| "+s+e.getPlayer().getName()+" §8»§r "+message);

		if(e.getPlayer().hasPermission("survival.chatcolor")){
			if(e.getMessage().startsWith("&")){
				e.getMessage().replaceAll("&", "§");
			}
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			if(onDamage){
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		Player p = e.getEntity();
		e.setDeathMessage(null);
		if(p != p.getKiller()){
			if(main.moneycmd.getMoney(p.getUniqueId(), p.getName()) > 0){
				main.moneycmd.removeMoney(p.getUniqueId(), p.getName(), main.moneycmd.getMoney(p.getUniqueId(), p.getName()) / 10);
				p.sendMessage("§cBeim Sterben hast du 10% deines Geldes verloren.");
			}
		}
	}

	//					Error

//	@EventHandler
//	public void onGetMoney(EntityDeathEvent e){
//		if(e.getEntity().getType() == EntityType.ZOMBIE){
//			LivingEntity zombie = e.getEntity();
//			Player p = zombie.getKiller();
//			MoneyCommand money = new MoneyCommand(main);
//			if(money.getMoney(p.getName()) == 0){
//				Double moneyV = (double) 1;
//				moneyV = moneyV * 0.14;
//				StringBuilder getMoney = new StringBuilder();
//				getMoney.append(moneyV);
//				getMoney.setLength(getMoney.length() - 14);
//				p.sendMessage("§aDu hast das Tier §e"+e.getEntity().getType()+" §agetötet und dafür §e"+getMoney+"€ §abekommen");
//				money.addMoney(p.getName(), money.getMoney(p.getName()) + moneyV);
//			} else {
//				Double moneyV = money.getMoney(p.getName()) * 0.14;
//				StringBuilder getMoney = new StringBuilder();
//				getMoney.append(moneyV);
//				getMoney.setLength(getMoney.length() - 15);
//				p.sendMessage("§aDu hast das Tier §e"+e.getEntity().getType()+" §agetötet und dafür §e"+getMoney+"€ §abekommen");
//				money.addMoney(p.getName(), money.getMoney(p.getName()) + moneyV);
//			}
//		}
//	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		final Player p = e.getPlayer();
		e.setJoinMessage("");
		//main.prefix(p);
		//p.setPlayerListName("§7"+p.getName());
		if(p.hasPermission("survival.admin")){
			p.setPlayerListName("§c§lA §8| §4"+p.getName());
		}

		if(p.hasPermission("survival.dev")){
			p.setPlayerListName("§b§lD §8| §3"+p.getName());
		}

		if(p.hasPermission("survival.tl")){
			p.setPlayerListName("§c§lTL §8| §4"+p.getName());
		}

		if(p.hasPermission("survival.mod")){
			p.setPlayerListName("§9§lM §8| §1"+p.getName());
		}

		if(p.hasPermission("survival.support")){
			p.setPlayerListName("§a§lS §8| §2"+p.getName());
		}

		if(p.hasPermission("survival.architekt")){
			p.setPlayerListName("§e§lA §8| §6"+p.getName());
		}

		if(p.hasPermission("survival.premium")){
			p.setPlayerListName("§d"+p.getName());
		}

		if(p.hasPermission("survival.spieler")){
			p.setPlayerListName("§7"+p.getName());
		}

		if(!Survival.instace.vanished.isEmpty()){
			for(Player all : Survival.instace.vanished){
				p.hidePlayer(all);
			}
		}

		p.sendMessage("§eWillkommen auf §6SurvivalProject.de");
		p.sendMessage("");
		p.sendMessage(" §6» §eRechtsklick auf dein 'minePhone' um ins Menü zukommen");
		p.sendMessage(" §6» §e/help - §7Sehe die Hilfe unseres Projektes");
		p.sendMessage("");
		p.sendMessage(" §6» §eFarmen, Bauen, Spaß haben - unser Motto des Tages");
		
		//p.getInventory().setItem(8, getItem(" §6» §eminePhone", Material., 1, 0, new String[]{"§8Öffnet das Handy"}));
		

		
		
		if(p.getWorld().getName() == "Farmwelt"){
			if(Bukkit.getServer().getWorld("Farmwelt").getWorldFolder().exists()){
				timer = 16;
				new BukkitRunnable() {

					@Override
					public void run() {
						timer--;
						if(timer == 15 || timer == 10 || timer <= 5 || timer != 0){
							p.sendMessage("§7[§2Survival§7] §eDie Schutzzeit ist in §3"+timer+" §eSekunden vorbei!");
							onFreeze = true;
							onDamage = true;
						}
						if(timer == 0){
							p.sendMessage("§7[§2Survival§7] §eDie Schutzzeit ist vorbei!");
							onFreeze = false;
							onDamage = false;
						}
					}

				}.runTaskTimer(main, 0L, 20L);
			} else {
				p.teleport(Bukkit.getServer().getWorld("spawn").getSpawnLocation());
			}
		} 

		LocalDate today = LocalDate.now();
		LocalDate zinsen = LocalDate.of(today.getYear(), today.getMonth(), 1);
		if(today == zinsen){
			BankHandler handler = new BankHandler(main);
			if(handler.getBankKonto(p) > 0){
				handler.addToBankKonto(p, handler.getBankKonto(p) * 1.45);
			}
		}

	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(p.getWorld().getName().contains("farmwelt")){
			if(onFreeze){
				e.getPlayer().teleport(e.getPlayer().getLocation());
			}
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().contains("minePhone")){
			e.setCancelled(true);
		}
	}
	
	public ItemStack getItem(String name, Material material, int count, int subid, String[] lore) {
		ItemStack stack = new ItemStack(material, count, (byte) subid);
		ItemMeta met = stack.getItemMeta();
		List<String> lorel = new ArrayList<String>();
		for (String s : lore) {
			lorel.add(s);
		}
		met.setLore(lorel);
		met.setDisplayName(name);
		stack.setItemMeta(met);
		return stack;
	}
	
}
