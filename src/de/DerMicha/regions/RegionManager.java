package de.DerMicha.regions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.DerMicha.Survival;

/**
 * Der RegionManager lädet, speichert, erstellt und verwaltet alle Regions in Echtzeit.
 * @author _xThaDargen
 *
 */
public class RegionManager {

	//public ArrayList<SaveableRegion> regions = new ArrayList<SaveableRegion>();

	public HashMap<UUID, SaveableRegion> regions = new HashMap<UUID, SaveableRegion>();

	private YamlConfiguration cfg = null;

	public RegionManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Lädt alle Regionen aus der Regions-Datei in das Regions-Array.
	 * !! SOLLTE IN onEnable() AUSGEFÜHRT WEREN !!
	 */
	public void load(Survival main) {
		this.regions = new HashMap<UUID, SaveableRegion>();
		File localFile = new File(main.getDataFolder(), "regions.yml");
		if (!localFile.exists()) {
			main.saveResource("regions.yml", false);
		}
		this.cfg = new YamlConfiguration();
		try {
			BufferedReader localBufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(localFile), "UTF-8"));
			this.cfg.load(localBufferedReader);
			System.out.println("Regions erfolgreich geladen");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for(String i : cfg.getConfigurationSection("regions").getKeys(false)){
			UUID id;
			try{ id = UUID.fromString(i); }
			catch(Exception ex){
				System.out.println("Fehlerhafte Region ID: \""+i+"\"");
				continue;
			}
			SaveableRegion rg;
			World wld = Bukkit.getWorld(cfg.getString("regions."+i+".world"));
			Location pos1 = new Location(wld, cfg.getInt("regions."+i+".pos1.x"),cfg.getInt("regions."+i+".pos1.y"), cfg.getInt("regions."+i+".pos1.z"));
			Location pos2 = new Location(wld, cfg.getInt("regions."+i+".pos2.x"),cfg.getInt("regions."+i+".pos2.y"), cfg.getInt("regions."+i+".pos2.z"));
			rg = new SaveableRegion(pos1, pos2, id);
			regions.put(id, rg);
		}
	}

	/**
	 * Speichert alle Regions aus dem Regions-Array in die Regions-Datei.
	 * !! SOLLTE IN onDisable() AUSGEFÜHRT WERDEN !!
	 */
	public void saveAll(){
		try {
			File file = new File(Survival.instace.getDataFolder(), "regions.yml");
			for (Map.Entry<UUID, SaveableRegion> entry : regions.entrySet()) {
			    UUID id = entry.getKey();
			    SaveableRegion rg = entry.getValue();
			    cfg.set("regions."+id.toString()+".world", rg.pos1.getWorld().getName());;
				cfg.set("regions."+id.toString()+".pos1.x", rg.pos1.getBlockX());;
				cfg.set("regions."+id.toString()+".pos1.y", rg.pos1.getBlockY());;
				cfg.set("regions."+id.toString()+".pos1.z", rg.pos1.getBlockZ());;
				cfg.set("regions."+id.toString()+".pos2.x", rg.pos2.getBlockX());;
				cfg.set("regions."+id.toString()+".pos2.y", rg.pos2.getBlockY());;
				cfg.set("regions."+id.toString()+".pos2.z", rg.pos2.getBlockZ());
			}
			this.cfg.save(file);
		} catch (Exception e) {
			System.out.println("FEHLER BEIM SPEICHERN DER REGIONS");
			e.printStackTrace();
		}
	}

	/**
	 * Erstellt eine neue Region und Schreibt das Region-Array in die Region-Datei.
	 * ( Aufrufen über: Survival.instace.regions.createNew() )
	 * @param pos1 Punkt 1 der Region
	 * @param pos2 Punkt 2 der Region
	 */
	public UUID createNewSaved(Region r){
		UUID id = UUID.randomUUID();
		this.regions.put(id, new SaveableRegion(r.pos1, r.pos2, id));
		this.saveAll();
		return id;
	}

	public void claimForPlayer(Region r, Player p){
		UUID g;
		g = createNewSaved(r);
		setRegionOwner(g, p);
	}

	public void setRegionOwner(UUID g, Player p){
		File file = new File(Survival.instace.getDataFolder()+"/accounts", p.getUniqueId().toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		List<String> ls = new ArrayList<String>();
		for(String i : cfg.getStringList(p.getName()+".ownedLands")){
			try{ ls.add(i); }
			catch(Exception ex){
				continue;
			}
		}
		ls.add(g.toString());
		cfg.set(p.getName()+".ownedLands", ls);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void playClaimSound(ClaimKitType t, Location l){
		switch (t){
		default:
			l.getWorld().playSound(l, Sound.ORB_PICKUP, 1.0f, 1.5f);
			l.getWorld().playSound(l, Sound.ANVIL_LAND, 1.0f, 1.2f);
			l.getWorld().playSound(l, Sound.ANVIL_BREAK, 1.0f, 1.2f);
			l.getWorld().playSound(l, Sound.ZOMBIE_WOODBREAK, 1.0f, 2.0f);
			break;
		}
	}

	public static void playClaimFailedSound(Location l){
//		l.getWorld().playSound(l, Sound., 1.0f, 2.0f);
//		l.getWorld().playSound(l, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 2.0f);
//		l.getWorld().playSound(l, Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
	}

	public static ItemStack getRegionChest(ClaimKitType t, Player p){
		switch (t){
		case SMALL:
			ItemStack is = new ItemStack(Material.CHEST);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("§e§l"+Survival.instace.langs.getMessage(p, "claim_item_name")+" §7§o("+Survival.instace.langs.getMessage(p, "claim_item_size_small")+")§r");
			List<String> l = new ArrayList<String>();
			l.add("§7 "+Survival.instace.langs.getMessage(p, "claim_item_size_small")+" - 9x9");
			im.setLore(l);
			is.setItemMeta(im);
			return is;
		case MEDIUM:
			break;
		case LARGE:
			break;
		case HUGE:
			ItemStack ish = new ItemStack(Material.CHEST);
			ItemMeta imh = ish.getItemMeta();
			imh.setDisplayName("§c§l"+Survival.instace.langs.getMessage(p, "claim_item_name")+" §7§o("+Survival.instace.langs.getMessage(p, "claim_item_size_huge")+")§r");
			List<String> lh = new ArrayList<String>();
			lh.add("§7 "+Survival.instace.langs.getMessage(p, "claim_item_size_huge")+" - 21x21");
			imh.setLore(lh);
			ish.setItemMeta(imh);
			return ish;
		}

		return null;
	}

	public List<UUID> getOwnedRegions(Player p){
		File file = new File(Survival.instace.getDataFolder()+"/accounts", p.getUniqueId().toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		List<UUID> ret = new ArrayList<UUID>();
		for(String i : cfg.getStringList(p.getName()+".ownedLands")){
			try{
				ret.add(UUID.fromString(i));
			}catch(Exception e){

			}
		}


		return ret;
	}

	/**
	 * Gibt ALLE Regions aus auf denen der Spieler (p) bauen kann.
	 * Darunter zählen auch seine geownten Regions.
	 * @param p Der Spieler
	 * @return Eine Liste alle IDs der Regions auf denen der Spieler (p) bauen darf.
	 */
	public List<SaveableRegion> getAllPermitted(Player p){
		List<UUID> ls = new ArrayList<UUID>();
		for (UUID i : this.getOwnedRegions(p))ls.add(i);

		File file = new File(Survival.instace.getDataFolder()+"/accounts", p.getUniqueId().toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		for (String i : cfg.getStringList(p.getName()+".permittedLands")){
			try{
				ls.add(UUID.fromString(i));
			}catch(Exception ex){
				continue;
			}
		}

		List<SaveableRegion> rgs = new ArrayList<SaveableRegion>();
		for(UUID id : ls){
			SaveableRegion r = null;
			r = getById(id);
			if(r==null)continue;
			rgs.add(r);
		}
		return rgs;
	}

	/**
	 * Sucht und returnt eine Region nach ihrer ID.
	 *
	 * @param id Die ID nach der gesucht wird.
	 * @return Die gesuchte Region. null wenn nicht gefunden.
	 */
	public SaveableRegion getById(UUID id){
		return this.regions.get(id);
	}
}
