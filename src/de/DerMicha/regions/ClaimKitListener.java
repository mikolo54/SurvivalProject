package de.DerMicha.regions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import de.DerMicha.Survival;
import de.DerMicha.Util;
import de.DerMicha.api.ParticleEffect;

public class ClaimKitListener implements Listener {
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if ((int) e.getFrom().getX() != (int) e.getTo().getX() || (int) e.getFrom().getY() != (int) e.getTo().getY()
				|| (int) e.getFrom().getZ() != (int) e.getTo().getZ()) {
			onMoveBlock(e.getPlayer());
		}
	}

	private void updateParticleSelection(Player p){
		if (p.getInventory().getItemInHand().equals(RegionManager.getRegionChest(ClaimKitType.SMALL, p))) {
			World w = p.getWorld();
			int x = p.getLocation().getBlockX();
			int y = p.getLocation().getBlockY();
			int z = p.getLocation().getBlockZ();
			Region reg = new Region(new Location(w, x - 4, 255, z + 4), new Location(w, x + 4, 0, z - 4));
			
			ArrayList<Location> ls = new ArrayList<Location>();

			ls.add(new Location(w, x + 4, y, z));
			ls.add(new Location(w, x + 4, y, z + 1));
			ls.add(new Location(w, x + 4, y, z + 2));
			ls.add(new Location(w, x + 4, y, z + 3));
			ls.add(new Location(w, x + 4, y, z + 4));
			ls.add(new Location(w, x + 4, y, z - 1));
			ls.add(new Location(w, x + 4, y, z - 2));
			ls.add(new Location(w, x + 4, y, z - 3));
			ls.add(new Location(w, x + 4, y, z - 4));

			ls.add(new Location(w, x - 4, y, z));
			ls.add(new Location(w, x - 4, y, z + 1));
			ls.add(new Location(w, x - 4, y, z + 2));
			ls.add(new Location(w, x - 4, y, z + 3));
			ls.add(new Location(w, x - 4, y, z + 4));
			ls.add(new Location(w, x - 4, y, z - 1));
			ls.add(new Location(w, x - 4, y, z - 2));
			ls.add(new Location(w, x - 4, y, z - 3));
			ls.add(new Location(w, x - 4, y, z - 4));

			ls.add(new Location(w, x + 1, y, z + 4));
			ls.add(new Location(w, x + 2, y, z + 4));
			ls.add(new Location(w, x + 3, y, z + 4));
			ls.add(new Location(w, x - 1, y, z + 4));
			ls.add(new Location(w, x - 2, y, z + 4));
			ls.add(new Location(w, x - 3, y, z + 4));
			ls.add(new Location(w, x, y, z + 4));

			ls.add(new Location(w, x + 1, y, z - 4));
			ls.add(new Location(w, x + 2, y, z - 4));
			ls.add(new Location(w, x + 3, y, z - 4));
			ls.add(new Location(w, x - 1, y, z - 4));
			ls.add(new Location(w, x - 2, y, z - 4));
			ls.add(new Location(w, x - 3, y, z - 4));
			ls.add(new Location(w, x, y, z - 4));

			ls.add(new Location(w, x, y, z));
			for (Location l : ls) {
				Location l2; // Location zum Senden
				if (l.getBlock().getType() != Material.AIR) {
					l2 = new Location(w, l.getBlockX(), w.getHighestBlockYAt(l.getBlockX(), l.getBlockZ()),
							l.getBlockZ());
				} else l2 = l;
				List<Player> pl = new ArrayList<Player>();
				pl.add(p);
				int r = 0; int g = 255; int b = 128;
				if(reg.overlapsAnySaved()==true){
					r = 255; g = 0; b = 0;
				}
				ParticleEffect.REDSTONE.display(r / 255, g / 255, b / 255, 1, 0, l2, pl);
				// p.playEffect(l2, Effect.MAGIC_CRIT, 4);
			}

		}else if(p.getInventory().getItemInHand().equals(RegionManager.getRegionChest(ClaimKitType.HUGE, p))){
			World w = p.getWorld();
			int x = p.getLocation().getBlockX();
			int y = p.getLocation().getBlockY();
			int z = p.getLocation().getBlockZ();
			Region reg = new Region(new Location(w, x - 4, 255, z + 4), new Location(w, x + 4, 0, z - 4));
			
			ArrayList<Location> ls = new ArrayList<Location>();

			ls.add(new Location(w, x+10, y, z-10));
			ls.add(new Location(w, x+9, y, z-10));
			ls.add(new Location(w, x+8, y, z-10));
			ls.add(new Location(w, x+7, y, z-10));
			ls.add(new Location(w, x+6, y, z-10));
			ls.add(new Location(w, x+5, y, z-10));
			ls.add(new Location(w, x+4, y, z-10));
			ls.add(new Location(w, x+3, y, z-10));
			ls.add(new Location(w, x+2, y, z-10));
			ls.add(new Location(w, x+1, y, z-10));
			ls.add(new Location(w, x-10, y, z-10));
			ls.add(new Location(w, x-9, y, z-10));
			ls.add(new Location(w, x-8, y, z-10));
			ls.add(new Location(w, x-7, y, z-10));
			ls.add(new Location(w, x-6, y, z-10));
			ls.add(new Location(w, x-5, y, z-10));
			ls.add(new Location(w, x-4, y, z-10));
			ls.add(new Location(w, x-3, y, z-10));
			ls.add(new Location(w, x-2, y, z-10));
			ls.add(new Location(w, x-1, y, z-10));
			ls.add(new Location(w, x, y, z-10));
			
			ls.add(new Location(w, x+10, y, z+10));
			ls.add(new Location(w, x+9, y, z+10));
			ls.add(new Location(w, x+8, y, z+10));
			ls.add(new Location(w, x+7, y, z+10));
			ls.add(new Location(w, x+6, y, z+10));
			ls.add(new Location(w, x+5, y, z+10));
			ls.add(new Location(w, x+4, y, z+10));
			ls.add(new Location(w, x+3, y, z+10));
			ls.add(new Location(w, x+2, y, z+10));
			ls.add(new Location(w, x+1, y, z+10));
			ls.add(new Location(w, x-10, y, z+10));
			ls.add(new Location(w, x-9, y, z+10));
			ls.add(new Location(w, x-8, y, z+10));
			ls.add(new Location(w, x-7, y, z+10));
			ls.add(new Location(w, x-6, y, z+10));
			ls.add(new Location(w, x-5, y, z+10));
			ls.add(new Location(w, x-4, y, z+10));
			ls.add(new Location(w, x-3, y, z+10));
			ls.add(new Location(w, x-2, y, z+10));
			ls.add(new Location(w, x-1, y, z+10));
			ls.add(new Location(w, x, y, z+10));
			
			ls.add(new Location(w, x-10, y, z+10));
			ls.add(new Location(w, x-10, y, z+9));
			ls.add(new Location(w, x-10, y, z+8));
			ls.add(new Location(w, x-10, y, z+7));
			ls.add(new Location(w, x-10, y, z+6));
			ls.add(new Location(w, x-10, y, z+5));
			ls.add(new Location(w, x-10, y, z+4));
			ls.add(new Location(w, x-10, y, z+3));
			ls.add(new Location(w, x-10, y, z+2));
			ls.add(new Location(w, x-10, y, z+1));
			ls.add(new Location(w, x-10, y, z-10));
			ls.add(new Location(w, x-10, y, z-9));
			ls.add(new Location(w, x-10, y, z-8));
			ls.add(new Location(w, x-10, y, z-7));
			ls.add(new Location(w, x-10, y, z-6));
			ls.add(new Location(w, x-10, y, z-5));
			ls.add(new Location(w, x-10, y, z-4));
			ls.add(new Location(w, x-10, y, z-3));
			ls.add(new Location(w, x-10, y, z-2));
			ls.add(new Location(w, x-10, y, z-1));
			ls.add(new Location(w, x-10, y, z));
			
			ls.add(new Location(w, x+10, y, z+10));
			ls.add(new Location(w, x+10, y, z+9));
			ls.add(new Location(w, x+10, y, z+8));
			ls.add(new Location(w, x+10, y, z+7));
			ls.add(new Location(w, x+10, y, z+6));
			ls.add(new Location(w, x+10, y, z+5));
			ls.add(new Location(w, x+10, y, z+4));
			ls.add(new Location(w, x+10, y, z+3));
			ls.add(new Location(w, x+10, y, z+2));
			ls.add(new Location(w, x+10, y, z+1));
			ls.add(new Location(w, x+10, y, z-10));
			ls.add(new Location(w, x+10, y, z-9));
			ls.add(new Location(w, x+10, y, z-8));
			ls.add(new Location(w, x+10, y, z-7));
			ls.add(new Location(w, x+10, y, z-6));
			ls.add(new Location(w, x+10, y, z-5));
			ls.add(new Location(w, x+10, y, z-4));
			ls.add(new Location(w, x+10, y, z-3));
			ls.add(new Location(w, x+10, y, z-2));
			ls.add(new Location(w, x+10, y, z-1));
			ls.add(new Location(w, x+10, y, z));
			
			ls.add(new Location(w, x, y, z));
			for (Location l : ls) {
				Location l2; // Location zum Senden
				if (l.getBlock().getType() != Material.AIR) {
					l2 = new Location(w, l.getBlockX(), w.getHighestBlockYAt(l.getBlockX(), l.getBlockZ()),
							l.getBlockZ());
				} else
					l2 = l;
				List<Player> pl = new ArrayList<Player>();
				pl.add(p);
				int r = 0; int g = 255; int b = 128;
				if(reg.overlapsAnySaved()==true){
					r = 255; g = 0; b = 0;
				}
				ParticleEffect.REDSTONE.display(r / 255, g / 255, b / 255, 1, 0, l2, pl);
				// p.playEffect(l2, Effect.MAGIC_CRIT, 4);
			}

		}
	}
	
	@EventHandler
	private void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (e.getItem() != null && e.getItem().equals(RegionManager.getRegionChest(ClaimKitType.SMALL, p))) {
				e.setCancelled(true);
				World w = p.getWorld();
				int x = p.getLocation().getBlockX();
				int y = p.getLocation().getBlockY();
				int z = p.getLocation().getBlockZ();
				Region r = new Region(new Location(w, x - 5, 0, z + 5), new Location(w, x + 5, 255, z - 5));
				if(r.overlapsAnySaved()==true){
					RegionManager.playClaimFailedSound(p.getLocation());
					Util.sendActionBar(p, Survival.instace.langs.getMessage(p, "claim_actionbar_invalid_place"));
					return;
				}
				Survival.instace.regions.claimForPlayer(r, p);
				RegionManager.playClaimSound(ClaimKitType.SMALL, p.getLocation());
				p.getInventory().remove(RegionManager.getRegionChest(ClaimKitType.SMALL, p));
				
				ArrayList<Location> ls = new ArrayList<Location>();

				ls.add(new Location(w, x + 4.5f, y, z + 0.5f));
				ls.add(new Location(w, x + 4.5f, y, z + 1.5f));
				ls.add(new Location(w, x + 4.5f, y, z + 2.5f));
				ls.add(new Location(w, x + 4.5f, y, z + 3.5f));
				ls.add(new Location(w, x + 4.5f, y, z + 4.5f));
				ls.add(new Location(w, x + 4.5f, y, z - 1.5f));
				ls.add(new Location(w, x + 4.5f, y, z - 2.5f));
				ls.add(new Location(w, x + 4.5f, y, z - 3.5f));
				ls.add(new Location(w, x + 4.5f, y, z - 4.5f));

				ls.add(new Location(w, x - 4.5f, y, z + 0.5f));
				ls.add(new Location(w, x - 4.5f, y, z + 1.5f));
				ls.add(new Location(w, x - 4.5f, y, z + 2.5f));
				ls.add(new Location(w, x - 4.5f, y, z + 3.5f));
				ls.add(new Location(w, x - 4.5f, y, z + 4.5f));
				ls.add(new Location(w, x - 4.5f, y, z - 1.5f));
				ls.add(new Location(w, x - 4.5f, y, z - 2.5f));
				ls.add(new Location(w, x - 4.5f, y, z - 3.5f));
				ls.add(new Location(w, x - 4.5f, y, z - 4.5f));

				ls.add(new Location(w, x + 1.5f, y, z + 4.5f));
				ls.add(new Location(w, x + 2.5f, y, z + 4.5f));
				ls.add(new Location(w, x + 3.5f, y, z + 4.5f));
				ls.add(new Location(w, x - 1.5f, y, z + 4.5f));
				ls.add(new Location(w, x - 2.5f, y, z + 4.5f));
				ls.add(new Location(w, x - 3.5f, y, z + 4.5f));
				ls.add(new Location(w, x + 0.5f, y, z + 4.5f));

				ls.add(new Location(w, x + 1.5f, y, z - 4.5f));
				ls.add(new Location(w, x + 2.5f, y, z - 4.5f));
				ls.add(new Location(w, x + 3.5f, y, z - 4.5f));
				ls.add(new Location(w, x - 1.5f, y, z - 4.5f));
				ls.add(new Location(w, x - 2.5f, y, z - 4.5f));
				ls.add(new Location(w, x - 3.5f, y, z - 4.5f));
				
				for(Location l : ls){
					Location l2; // Location zum Senden
					if ((l.getBlock().getType() != Material.AIR) || (new Location(w, l.getBlockX(), l.getBlockY()-1, l.getBlockZ()).getBlock().getType()== Material.AIR)) {
						l2 = new Location(w, l.getBlockX(), w.getHighestBlockYAt(l.getBlockX(), l.getBlockZ()),
								l.getBlockZ());
					} else
						l2 = l;
					w.playEffect(l2, Effect.EXPLOSION_HUGE, 5);
					l2.getBlock().setType(Material.FENCE);
				}
				
			}else if (e.getItem() != null && e.getItem().equals(RegionManager.getRegionChest(ClaimKitType.HUGE, p))) {
				e.setCancelled(true);
				World w = p.getWorld();
				int x = p.getLocation().getBlockX();
				int y = p.getLocation().getBlockY();
				int z = p.getLocation().getBlockZ();
				Region r = new Region(new Location(w, x - 10, 0, z + 10), new Location(w, x + 10, 255, z - 10));
				if(r.overlapsAnySaved()==true){
					RegionManager.playClaimFailedSound(p.getLocation());
					Util.sendActionBar(p, Survival.instace.langs.getMessage(p, "claim_actionbar_invalid_place"));
					return;
				}
				Survival.instace.regions.claimForPlayer(r, p);
				RegionManager.playClaimSound(ClaimKitType.SMALL, p.getLocation());
				p.getInventory().remove(RegionManager.getRegionChest(ClaimKitType.SMALL, p));
				
				ArrayList<Location> ls = new ArrayList<Location>();

				ls.add(new Location(w, x+10, y, z-10));
				ls.add(new Location(w, x+9, y, z-10));
				ls.add(new Location(w, x+8, y, z-10));
				ls.add(new Location(w, x+7, y, z-10));
				ls.add(new Location(w, x+6, y, z-10));
				ls.add(new Location(w, x+5, y, z-10));
				ls.add(new Location(w, x+4, y, z-10));
				ls.add(new Location(w, x+3, y, z-10));
				ls.add(new Location(w, x+2, y, z-10));
				ls.add(new Location(w, x+1, y, z-10));
				ls.add(new Location(w, x-10, y, z-10));
				ls.add(new Location(w, x-9, y, z-10));
				ls.add(new Location(w, x-8, y, z-10));
				ls.add(new Location(w, x-7, y, z-10));
				ls.add(new Location(w, x-6, y, z-10));
				ls.add(new Location(w, x-5, y, z-10));
				ls.add(new Location(w, x-4, y, z-10));
				ls.add(new Location(w, x-3, y, z-10));
				ls.add(new Location(w, x-2, y, z-10));
				ls.add(new Location(w, x-1, y, z-10));
				ls.add(new Location(w, x, y, z-10));
				
				ls.add(new Location(w, x+10, y, z+10));
				ls.add(new Location(w, x+9, y, z+10));
				ls.add(new Location(w, x+8, y, z+10));
				ls.add(new Location(w, x+7, y, z+10));
				ls.add(new Location(w, x+6, y, z+10));
				ls.add(new Location(w, x+5, y, z+10));
				ls.add(new Location(w, x+4, y, z+10));
				ls.add(new Location(w, x+3, y, z+10));
				ls.add(new Location(w, x+2, y, z+10));
				ls.add(new Location(w, x+1, y, z+10));
				ls.add(new Location(w, x-10, y, z+10));
				ls.add(new Location(w, x-9, y, z+10));
				ls.add(new Location(w, x-8, y, z+10));
				ls.add(new Location(w, x-7, y, z+10));
				ls.add(new Location(w, x-6, y, z+10));
				ls.add(new Location(w, x-5, y, z+10));
				ls.add(new Location(w, x-4, y, z+10));
				ls.add(new Location(w, x-3, y, z+10));
				ls.add(new Location(w, x-2, y, z+10));
				ls.add(new Location(w, x-1, y, z+10));
				ls.add(new Location(w, x, y, z+10));
				
				ls.add(new Location(w, x-10, y, z+10));
				ls.add(new Location(w, x-10, y, z+9));
				ls.add(new Location(w, x-10, y, z+8));
				ls.add(new Location(w, x-10, y, z+7));
				ls.add(new Location(w, x-10, y, z+6));
				ls.add(new Location(w, x-10, y, z+5));
				ls.add(new Location(w, x-10, y, z+4));
				ls.add(new Location(w, x-10, y, z+3));
				ls.add(new Location(w, x-10, y, z+2));
				ls.add(new Location(w, x-10, y, z+1));
				ls.add(new Location(w, x-10, y, z-10));
				ls.add(new Location(w, x-10, y, z-9));
				ls.add(new Location(w, x-10, y, z-8));
				ls.add(new Location(w, x-10, y, z-7));
				ls.add(new Location(w, x-10, y, z-6));
				ls.add(new Location(w, x-10, y, z-5));
				ls.add(new Location(w, x-10, y, z-4));
				ls.add(new Location(w, x-10, y, z-3));
				ls.add(new Location(w, x-10, y, z-2));
				ls.add(new Location(w, x-10, y, z-1));
				ls.add(new Location(w, x-10, y, z));
				
				ls.add(new Location(w, x+10, y, z+10));
				ls.add(new Location(w, x+10, y, z+9));
				ls.add(new Location(w, x+10, y, z+8));
				ls.add(new Location(w, x+10, y, z+7));
				ls.add(new Location(w, x+10, y, z+6));
				ls.add(new Location(w, x+10, y, z+5));
				ls.add(new Location(w, x+10, y, z+4));
				ls.add(new Location(w, x+10, y, z+3));
				ls.add(new Location(w, x+10, y, z+2));
				ls.add(new Location(w, x+10, y, z+1));
				ls.add(new Location(w, x+10, y, z-10));
				ls.add(new Location(w, x+10, y, z-9));
				ls.add(new Location(w, x+10, y, z-8));
				ls.add(new Location(w, x+10, y, z-7));
				ls.add(new Location(w, x+10, y, z-6));
				ls.add(new Location(w, x+10, y, z-5));
				ls.add(new Location(w, x+10, y, z-4));
				ls.add(new Location(w, x+10, y, z-3));
				ls.add(new Location(w, x+10, y, z-2));
				ls.add(new Location(w, x+10, y, z-1));
				ls.add(new Location(w, x+10, y, z));
				
				for(Location l : ls){
					Location l2; // Location zum Senden
					if ((l.getBlock().getType() != Material.AIR) || (new Location(w, l.getBlockX(), l.getBlockY()-1, l.getBlockZ()).getBlock().getType()== Material.AIR)) {
						l2 = new Location(w, l.getBlockX(), w.getHighestBlockYAt(l.getBlockX(), l.getBlockZ()),
								l.getBlockZ());
					} else
						l2 = l;
					w.playEffect(l2, Effect.EXPLOSION_HUGE, 5);
					l2.getBlock().setType(Material.FENCE);
				}
				
			}
		}
	}

	
	private void onMoveBlock(Player p) {
		updateParticleSelection(p);
	}

	@EventHandler
	public void onPlayerChangeSlot(PlayerItemHeldEvent e) {
		Player p = e.getPlayer();
		if (p.getInventory().getItem(e.getNewSlot()) == null) {
			Util.sendActionBar(p, "§r §r");
			return;
		}
		if (p.getInventory().getItem(e.getNewSlot()).equals(RegionManager.getRegionChest(ClaimKitType.SMALL, p))) {
			Util.sendActionBar(p, Survival.instace.langs.getMessage(p, "claim_actionbar_tutorial_place"), 60);
			updateParticleSelection(p);
		} else {
			Util.sendActionBar(p, "§r §r");
		}
	}

}
