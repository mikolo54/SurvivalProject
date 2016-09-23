package de.DerMicha.recipe;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class BanRecipe implements Listener{

	//Sticks
	//Fackeln
	//Suppen
	//Flint and Steel
	//Schere

	@EventHandler
	public void onCrafting(CraftItemEvent e){
		if (e.getCurrentItem().getType() == Material.STICK || e.getCurrentItem().getType() == Material.FLINT_AND_STEEL
				|| e.getCurrentItem().getType() == Material.TORCH
				|| e.getCurrentItem().getType() == Material.MUSHROOM_SOUP
				|| e.getCurrentItem().getType() == Material.SHEARS
				|| e.getCurrentItem().getType() == Material.COAL_BLOCK
				|| e.getCurrentItem().getType() == Material.DIAMOND_BLOCK
				|| e.getCurrentItem().getType() == Material.GOLD_BLOCK
				|| e.getCurrentItem().getType() == Material.IRON_BLOCK
				|| e.getCurrentItem().getType() == Material.LAPIS_BLOCK) {

			e.setCancelled(true);
		}
	}

}
