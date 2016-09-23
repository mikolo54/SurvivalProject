package de.Dargen.npc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.DerMicha.Survival;

public class NPCManager {
	
	HashMap<UUID, NPC> npcs = new HashMap<UUID, NPC>();
	
	public NPCManager(){
		//Hier kann mann NPCs Hardcoden
		
		//z.B.
		//npcs.put(0, new NPC(0, "penis", new NPCSkin("eyJ0aW1lc3RhbXAiOjE0NjA5ODMwMTQyMDMsInByb2ZpbGVJZCI6IjEzNDRkNjBhY2U5NzRjNDU4NGQ1YjI5OTdkY2I3OThhIiwicHJvZmlsZU5hbWUiOiJEZXJNaWNoYSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGZlYWU2ZTZlYmE1MmIyNzY5MjQ0YmMzODBjNTQ3YzU5YTljZDVjZDMwZGZiNTI5MTk0N2M4OGViMzEyZTUiLCJtZXRhZGF0YSI6eyJtb2RlbCI6InNsaW0ifX19fQ==", "")));
	}
	
	public void load(Survival main){
		
	}
	
	public void saveAll(){
		
	}
	
	public void sendAll(Player p){
		for(Map.Entry<UUID, NPC> e : npcs.entrySet()){
			NPC npc = e.getValue();
			npc.spawn(p);
		}
	}
	
	public void refreshAll(){
		for(Player p : Bukkit.getOnlinePlayers()){
			sendAll(p);
			p.sendMessage("[Debug]: REFRESHED NPCS");
		}
	}
	
	public UUID createNew(NPC n){
		UUID id;
		id = UUID.randomUUID();
		npcs.put(id, n);
		refreshAll();
		return id;
	}
	
}
