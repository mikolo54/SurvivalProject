package de.DerMicha.regions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import de.DerMicha.Survival;
import de.DerMicha.Util;
import de.DerMicha.api.ParticleEffect;

public class BuildListener implements Listener{
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		boolean permitted = false;
		boolean isUnclaimedProtected = true;
		if(!p.hasPermission("regions.root")){
			if(isUnclaimedProtected == true){
				for(SaveableRegion rg : Survival.instace.regions.getAllPermitted(p)){
					if(rg.isInRegion(e.getBlockPlaced().getLocation())==true){
						permitted = true;
					}
					
				}
			}else{
				for (Map.Entry<UUID, SaveableRegion> entry : Survival.instace.regions.regions.entrySet()) {
				    UUID id = entry.getKey();
				    SaveableRegion rg = entry.getValue();
				    if(rg.isInRegion(e.getBlockPlaced().getLocation())==true){
				    	if(Survival.instace.regions.getOwnedRegions(p).contains(id))
						permitted = true;
					}
				}
			}
			
			if(permitted==true){
	    		return;
			}else{
				e.setCancelled(true);
				List<Player> ls = new ArrayList<Player>();
				ls.add(p);
				ParticleEffect.BARRIER.display(0.5f, 0.5f, 0.5f, 1, 1, e.getBlock().getLocation(), ls);
				//Util.sendActionBar(p, "§c§l"+Survival.instace.langs.getMessage(p, "nopermission_build"), 60);
				Util.sendActionBar(p, "§c§l"+Survival.instace.langs.getMessage(p, "nopermission_build"));
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		boolean permitted = false;
		boolean isUnclaimedProtected = true;
		if(!p.hasPermission("regions.root")){
			if(isUnclaimedProtected == true){
				for(SaveableRegion rg : Survival.instace.regions.getAllPermitted(p)){
					if(rg.isInRegion(e.getBlock().getLocation())==true){
						permitted = true;
					}
					
				}
			}else{
				for (Map.Entry<UUID, SaveableRegion> entry : Survival.instace.regions.regions.entrySet()) {
				    UUID id = entry.getKey();
				    SaveableRegion rg = entry.getValue();
				    if(rg.isInRegion(e.getBlock().getLocation())==true){
				    	if(Survival.instace.regions.getOwnedRegions(p).contains(id))
						permitted = true;
					}
				}
			}
			if(permitted==true){
	    		if (e.getBlock().getType() == Material.IRON_ORE) {
	    			if (e.getPlayer().getInventory().getItemInHand().getType() == Material.STONE_PICKAXE
	    					|| e.getPlayer().getInventory().getItemInHand().getType() == Material.IRON_PICKAXE
	    					|| e.getPlayer().getInventory().getItemInHand().getType() == Material.DIAMOND_PICKAXE) {

	    				e.setCancelled(true);
	    				e.getBlock().breakNaturally(new ItemStack(Material.AIR));
	    				e.getPlayer().getWorld()
	    						.dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
	    				e.getPlayer().setLevel(e.getPlayer().getLevel() + 1);
	    			} else {
	    				e.getBlock().breakNaturally(new ItemStack(Material.AIR));
	    			}
	    		}
	    		if (e.getBlock().getType() == Material.GOLD_ORE) {
	    			if (e.getPlayer().getInventory().getItemInHand().getType() == Material.IRON_PICKAXE
	    					|| e.getPlayer().getInventory().getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
	    				
	    				e.setCancelled(true);
	    				e.getBlock().breakNaturally(new ItemStack(Material.AIR));
	    				e.getPlayer().getWorld()
	    						.dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
	    				e.getPlayer().setLevel(e.getPlayer().getLevel() + 1);
	    			} else {
	    				e.getBlock().breakNaturally(new ItemStack(Material.AIR));
	    			}
	    		}
				return;
			}else{
				e.setCancelled(true);
				List<Player> ls = new ArrayList<Player>();
				ls.add(p);
				ParticleEffect.BARRIER.display(0.5f, 0.5f, 0.5f, 1, 1, e.getBlock().getLocation(), ls);
				Util.sendActionBar(p, "§c§l"+Survival.instace.langs.getMessage(p, "nopermission_build"), 60);
			}
		}
	}
}
