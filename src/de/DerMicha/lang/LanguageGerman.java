package de.DerMicha.lang;

public class LanguageGerman extends Language{
	
	/*
	 * ERKLÄRUNG DER PLATZHALTER:
	 * {0} = target
	 * {1} = server name
	 */
	
	@Override
	public void load(){
		this.messages.put("hello", "Hallo");
		this.messages.put("kick_restart", "§cDer Server wird neugestartet...\n§cBitte betrette den Server wieder in ~20 Sekunden.");
		this.messages.put("feed_other", "Du hast den Spieler {0} gefüttert.");
		this.messages.put("feed_target", "Du hast den Spieler {0} gefüttert");
		this.messages.put("feed", "Du hast dich gefüttert");
		
		this.messages.put("nopermission_build", "Du kannst hier nicht Bauen.");
		
		/*
		 * 						Menü
		 */
		
		this.messages.put("menu_title_main", "§lminePhone");
		this.messages.put("menu_title_claiming", "§lDeine Grundstücke");
		this.messages.put("menu_title_teleporter", "§lNavigation");
		this.messages.put("menu_title_skills", "§lFähigkeiten");
		this.messages.put("menu_title_settings", "§lEinstellungen");
		this.messages.put("menu_title_settings_language", "§lWähle deine Sprache.");
		 
		this.messages.put("menu_item_homebutton", "§7<- §fZurück zum Startbildschirm.");
		this.messages.put("menu_item_teleporter_lore", "Damit kannst du dich in eine andere Dimension teleportieren.");
		this.messages.put("menu_item_help", "§5§lHilfe, Tutorials und FAQ");
		this.messages.put("menu_item_help_lore", "§8Hier wird dir erklärt wie unser GamePlay funktioniert.");
		this.messages.put("menu_item_settings", "§6§lEinstellungen");
		this.messages.put("menu_item_settings_lore", "Hier kannst du Einstellungen betätigen.");
		
		/*
		 * 						Scoreboard
		 */
		
		this.messages.put("scoreboard_title_money", "Dein Geld:");
		this.messages.put("scoreboard_title_bank",    "Bank: ");
		this.messages.put("scoreboard_title_bargeld", "Bargeld: ");
		this.messages.put("scoreboard_title_dimension", "Aktuelle Dimension:");
		
		
		
		/*
		 * 						Claim-Boxen
		 */
		this.messages.put("claim_item_size_small", "Klein");
		this.messages.put("claim_item_size_medium", "Mittel");
		this.messages.put("claim_item_size_large", "Groß");
		this.messages.put("claim_item_size_huge", "Riesig");
		this.messages.put("claim_item_name", "Grundst\u00fcckskiste");
		this.messages.put("claim_actionbar_invalid_place", "§c§lDu kannst diesen Bereich nicht claimen!");
		this.messages.put("claim_actionbar_tutorial_place", "§a§l§nRechtsklick§a§l um dein Grundstück zu makieren.");
	}
	

}
