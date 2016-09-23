package de.DerMicha.lang;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class LanguageManager {
	
	private Language german;
	private Language english;

	public String ingameCurrenyDisplay = "€";
	
	public LanguageManager(){
		german = new LanguageGerman();
		english = new LanguageEnglish();
		german.load();
		english.load();
	}
	
	private String getLanguage(Player p){
		CraftPlayer cp = (CraftPlayer) p;
		return cp.getHandle().locale;
	}

	/**
	 * Diese Funktion konvertiert eine Message-ID in die Sprache des angegebenen Spielers.
	 * @param p Der Spieler von dem die Sprache verwendet wird.
	 * @param msg_id Die Message-ID der zu übersetzenden Message
	 * @return Die an die Sprache des Spielers angepasste Nachricht (falls verfügbar)
	 */
	public String getMessage(Player p, String msg_id){
		String lang = getLanguage(p);
			
		if(lang.contains("de_DE")){
			return german.get(msg_id);
		}else{
			return english.get(msg_id);
		}
		
	}


}
