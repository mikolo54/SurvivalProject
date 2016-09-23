package de.Dargen.npc;

import java.util.Map;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.DerMicha.Survival;
import net.md_5.bungee.api.ChatColor;

public class NPCCommand implements CommandExecutor {

	// TODO
	@Override
	public boolean onCommand(CommandSender s, Command c, String str, String[] args) {
		if (s instanceof Player) {

			Player p = (Player) s;
			if (args.length == 0) {
				p.sendMessage("/npc create <Skin> <Typ> <Nametag-Inhalt>");
				p.sendMessage("/npc delete <NPC-UUID>"); //man soll auch npcs mit einem speziellen NPC-Remove-Item löschen können
				p.sendMessage("/npc list");
				return true;
			}
			if ((args[0].contains("create"))) {
				if(args.length >= 4){
//					String skin = args[2];
					String nametag = "";
					int type;
					
					for(int i = 3; i <= args.length-1; i++){ nametag = nametag + args[i]; }
						
					try{
						type = Integer.parseInt(args[2]); 
					}catch (Exception e){
						p.sendMessage("Fehler: \""+args[2]+"\" ist keine Zahl");
						return true;
					}
					
					
					
					Survival.instace.npcs.createNew(new NPC(type, nametag, new NPCSkin("eyJ0aW1lc3RhbXAiOjE0NjEyNjc5MjU3NDMsInByb2ZpbGVJZCI6IjEzMzNlMTlhYzE0MDQzOTc4NzlkZGQyMjM1OWRmNWIzIiwicHJvZmlsZU5hbWUiOiJBZG9sZiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMGE4ZmIxMmQwNjM2ZGJlMzE0Y2I2NmMwMWEyOGY4NjRkMDgzNDcyZDk0YjAzYWUzNDFkMzZkNWExNWI3YSJ9fX0=", "wVtyhNcZzCQmgcsZz1kzIYaS5IsnfACEb0jQsEfPqpQiJC/GS6aWvthnjbBJidr9QwaLwlDl5rCtMCcVS+fp+bmKwd833HA2TQjovIpQMpYk9je2GOuAKSy9CXYokce2CqO4Eej5QtlIgjM6GUwUnRuNDCnafgjKld+Yk1mjsZ1PtqHN5BBi3aCVuGHSD30Iic7rRn75+L4JC4nsNW9egVC3Xw3Dv80lIBFFM5vTWWWQJugiMGssCPS1qKErV+OLr8kv+5Kb8y1sdE+PrCQxa3kitdS/mN53rIGcENHkDjA+M3gKg5xVM2ugAiGpOoNLZd46sJ1ho0aJ85MwooPseBrgJjturFtGIoisZ3KIIy9ubcO36h4zxX3ttgDUDapwM+Qij+QJBq73tbAMUZiYmhAGS2Nos7j3SVIQV846IO4w/efd1pL4+oF28D3XrXrMD8NuaEtyTt4z7IKhH3ntK2TLoESF7H8OgBcr7ramC8nIPJqa4FlhcqP3XivJsQVS6PbqWG7bUVC5tefc6JEWScBlodgQckHcuxG2306g3wCobl6MTK3t0m2LuZOgRWBvZi0UjkMAyPaQxw5ilHceB31ol79gR5q/vOWqks+rKgUMFhYERStJmA5qeCjURKr5gR9vzXvsfPJNoyKW5m9hYwv/1MD3bU3rEuhBq/Hon0I="), p.getLocation()));
					
				}else{
					p.sendMessage(ChatColor.RED+"Syntax: /npc create <Name> <Skin> <Typ>");
				}
				return true;
			}else if ((args[0].contains("delete"))) {
				p.sendMessage("#WorkInProgress");
				return true;
			}else if ((args[0].contains("list"))) {
				p.sendMessage("==== ALLE NPCs IM ARRAY ====");
				for(Map.Entry<UUID, NPC> map : Survival.instace.npcs.npcs.entrySet()){
					NPC n = map.getValue();
					UUID id = map.getKey();
					p.sendMessage("--- "+id.toString());
					p.sendMessage(" ENTITY_ID: "+n.getEntityID());
					p.sendMessage(" INTERACT_ID: "+n.id);
					p.sendMessage(" GET_LOC: "+n.location);
					p.sendMessage("§r §r");
				}
				return true;
			}else{
				return true;
			}
		}else{
			s.sendMessage(ChatColor.RED+"FEHLER: Dieser Befehl ist in der Konsole nicht verfügbar.");
		}
		
		return true;
	}

}