package de.DerMicha.lang;

public class LanguageEnglish extends Language {

	/*
	 * ERKLÄRUNG DER PLATZHALTER:
	 * {0} = target
	 * {1} = Server name
	 */
	
	@Override
	public void load(){
		this.messages.put("hello", "Hello");
		this.messages.put("kick_restart", "§4§lThe server is restarting...\n Please rejoin in ~20 seconds.");
		this.messages.put("feed_other", "You have feeded the player {0}.");
		this.messages.put("feed_target", "You have been feeded by {0}");
		this.messages.put("feed", "You have been feeded");
		
		this.messages.put("nopermission_build", "You can't build here.");
		
		/*
		 * 						Menü
		 */
		this.messages.put("menu_title_main", "§lminePhone");
		this.messages.put("menu_title_claiming", "§lYour lands");
		this.messages.put("menu_title_teleporter", "§lNavigation");
		this.messages.put("menu_title_skills", "§lSkills");
		this.messages.put("menu_title_settings", "§lSettings");
		this.messages.put("menu_title_settings_language", "§lChoose your language");
		
		this.messages.put("menu_item_homebutton", "§7<- §fBack to homescreen");
		this.messages.put("menu_item_teleporter_lore", "You can switch the dimension with this app.");
		this.messages.put("menu_item_help", "§5§lHelp, Tutorials and FAQ");
		this.messages.put("menu_item_help_lore", "§8You can get started with our gameplay here.");
		this.messages.put("menu_item_settings", "§lSettings");
		this.messages.put("menu_item_settings_lore", "You can configure your Gameplay experience here.");
		
		
		/*
		 * 						Scoreboard
		 */
		this.messages.put("scoreboard_title_money", "Your money:");
		this.messages.put("scoreboard_title_bank",    "On Bank: ");
		this.messages.put("scoreboard_title_bargeld", "Cash:    ");
		this.messages.put("scoreboard_title_dimension", "Your current dimension:");
		

		
		/*
		 * 						Claim-Boxes
		 */ 
		this.messages.put("claim_item_size_small", "small");
		this.messages.put("claim_item_size_medium", "medium");
		this.messages.put("claim_item_size_large", "large");
		this.messages.put("claim_item_size_huge", "huge");
		this.messages.put("claim_item_name", "Claiming box");
		this.messages.put("claim_actionbar_invalid_place", "§c§lYou can't claim that area!");
		this.messages.put("claim_actionbar_tutorial_place", "§a§l§nRightclick§a§l to claim your area.");
	}

}
