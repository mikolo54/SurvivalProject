package de.DerMicha.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.DerMicha.Survival;
import de.DerMicha.Util;
import de.DerMicha.regions.ClaimKitType;
import de.DerMicha.regions.RegionManager;
import de.DerMicha.regions.SaveableRegion;
import net.md_5.bungee.api.ChatColor;

/**
 * Dieser Command wird benutzt, um mit Hilfe des Debuges bestimmte Funktionen auszuführen
 * @author _xThaDargen | SadMicha
 *
 */
public class DebugtestCommand implements CommandExecutor {

	// Â <-- HURENSHOHN
	@Override
	public boolean onCommand(CommandSender s, Command c, String str, String[] args) {
		if (s instanceof Player) {

			Player p = (Player) s;
			if (args.length == 0) {
				p.sendMessage("Dies ist ein Debug-Befehl.. Siehe Sourcecode für mehr");
			}
			if (args.length == 1) {
				if (args[0].contains("cbox1")) {
					p.getInventory().addItem(RegionManager.getRegionChest(ClaimKitType.SMALL, p));
				}else if (args[0].contains("cbox4")) {
					p.getInventory().addItem(RegionManager.getRegionChest(ClaimKitType.HUGE, p));
				}else if (args[0].contains("phelix1")){
					List<Player> pl = new ArrayList<Player>();
					for(Player pla : Bukkit.getOnlinePlayers()){
						pl.add(pla);
					}
					Util.runHelix(p.getLocation(), pl);
				}else if (args[0].contains("dumpgs")){
					p.sendMessage("==== ALLE GELADENEN GS-EINTRÄGE ====");
					for (Map.Entry<UUID, SaveableRegion> entry : Survival.instace.regions.regions.entrySet()) {
					    UUID id = entry.getKey();
					    SaveableRegion rg = entry.getValue();
					    p.sendMessage("§r §r");
						p.sendMessage("-- "+id+" --");
						p.sendMessage(" pos1:"+rg.pos1);
						p.sendMessage(" pos2:"+rg.pos2);
					}
				}
			}
		}else{
			s.sendMessage(ChatColor.RED+"FEHLER: Dieser Befehl ist in der Konsole nicht verfügbar.");
		}
		
		return true;
	}

}