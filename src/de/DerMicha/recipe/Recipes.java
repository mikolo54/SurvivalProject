package de.DerMicha.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipes implements Listener {

	// Holz, Stein, Gold, Eisen, Diamant

	public String notEnoughItems1 = "§7Du hast nicht genügend Rohstoffe";
	public String notEnoughItems2 = "§7um dieses Werkstück herzustellen";

	public ItemStack getItem(String name, Material material, int count, int subid, String[] lore,
			boolean hasEnchants, HashMap<Enchantment, Integer> enchantments) {
		ItemStack stack = new ItemStack(material, count, (byte) subid);
		ItemMeta met = stack.getItemMeta();
		if (hasEnchants) {
			for (Enchantment ench : enchantments.keySet()) {
				stack.addUnsafeEnchantment(ench, enchantments.get(ench));
			}
		}
		List<String> lorel = new ArrayList<String>();
		for (String s : lore) {
			lorel.add(s);
		}
		met.setLore(lorel);
		met.setDisplayName(name);
		stack.setItemMeta(met);
		return stack;
	}

	public boolean isSlotFree(Inventory i) {
		if (i.firstEmpty() == -1) {
			return false;
		} else {
			return true;
		}

	}
	
	public Inventory getCombat(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 5, "§cKampf");
		if (p.getInventory().contains(Material.STICK, 3) && p.getInventory().contains(Material.STRING, 3)) {
			inv.setItem(3, getItem("§aBogen", Material.BOW, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					3,
					getItem("§cBogen", Material.BOW, 1, 0, new String[] { notEnoughItems1, notEnoughItems2 },
							false, null));
		}

		if (p.getInventory().contains(Material.FEATHER, 1) && p.getInventory().contains(Material.FLINT, 1)
				&& p.getInventory().contains(Material.STICK, 1)) {
			inv.setItem(6, getItem("§aPfeil", Material.ARROW, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					6,
					getItem("§cPfeil", Material.ARROW, 1, 0, new String[] { notEnoughItems1, notEnoughItems2 },
							false, null));
		}

		if (p.getInventory().contains(Material.LEATHER, 5)) {
			inv.setItem(10, getItem("§aLederkappe", Material.LEATHER_HELMET, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					10,
					getItem("§cLederkappe", Material.LEATHER_HELMET, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		if (p.getInventory().contains(Material.LEATHER, 8)) {
			inv.setItem(11,
					getItem("§aLederbrust", Material.LEATHER_CHESTPLATE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					11,
					getItem("§cLederbrust", Material.LEATHER_CHESTPLATE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		if (p.getInventory().contains(Material.LEATHER, 7)) {
			inv.setItem(12, getItem("§aLederhose", Material.LEATHER_LEGGINGS, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					12,
					getItem("§cLederhose", Material.LEATHER_LEGGINGS, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		if (p.getInventory().contains(Material.LEATHER, 4)) {
			inv.setItem(13, getItem("§aLederschuhe", Material.LEATHER_BOOTS, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					13,
					getItem("§cLederschuhe", Material.LEATHER_BOOTS, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		if (p.getInventory().contains(Material.GOLD_INGOT, 5)) {
			inv.setItem(14, getItem("§aGoldhelm", Material.GOLD_HELMET, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					14,
					getItem("§cGoldhelm", Material.GOLD_HELMET, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Holzhoe
		if (p.getInventory().contains(Material.GOLD_INGOT, 8)) {
			inv.setItem(15, getItem("§aGoldbrust", Material.GOLD_CHESTPLATE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					15,
					getItem("§cGoldbust", Material.GOLD_CHESTPLATE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Steinspitzhacke
		if (p.getInventory().contains(Material.GOLD_INGOT, 7)) {
			inv.setItem(16, getItem("§aGoldhose", Material.GOLD_LEGGINGS, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					16,
					getItem("§cGoldhose", Material.GOLD_LEGGINGS, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Steinschaufel
		if (p.getInventory().contains(Material.GOLD_INGOT, 4)) {
			inv.setItem(17, getItem("§aGoldschuhe", Material.GOLD_BOOTS, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					17,
					getItem("§cGoldschuhe", Material.GOLD_BOOTS, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Steinharke
		if (p.getInventory().contains(Material.IRON_INGOT, 5)) {
			inv.setItem(19, getItem("§aEisenhelm", Material.IRON_HELMET, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					19,
					getItem("§cEisenhelm", Material.IRON_HELMET, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Goldspitzhacke
		if (p.getInventory().contains(Material.IRON_INGOT, 8)) {
			inv.setItem(20, getItem("§aEisenbrust", Material.IRON_CHESTPLATE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					20,
					getItem("§cEisenbrust", Material.IRON_CHESTPLATE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Goldschaufel
		if (p.getInventory().contains(Material.IRON_INGOT, 7)) {
			inv.setItem(21, getItem("§aEisenhose", Material.IRON_LEGGINGS, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					21,
					getItem("§cEisenhose", Material.IRON_LEGGINGS, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Goldaxt
		if (p.getInventory().contains(Material.IRON_INGOT, 4)) {
			inv.setItem(22, getItem("§aEisenschuhe", Material.IRON_BOOTS, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					22,
					getItem("§cEisenschuhe", Material.IRON_BOOTS, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Goldharke
		if (p.getInventory().contains(Material.DIAMOND, 5)) {
			inv.setItem(23, getItem("§aDiamanthelm", Material.DIAMOND_HELMET, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					23,
					getItem("§cDiamanthelm", Material.DIAMOND_HELMET, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Eisenspitzhacke
		if (p.getInventory().contains(Material.DIAMOND, 8)) {
			inv.setItem(24,
					getItem("§aDiamantbrust", Material.DIAMOND_CHESTPLATE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					24,
					getItem("§cDiamantbrust", Material.DIAMOND_CHESTPLATE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Eisenschaufel
		if (p.getInventory().contains(Material.DIAMOND, 7)) {
			inv.setItem(25,
					getItem("§aDiamanthose", Material.DIAMOND_LEGGINGS, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					25,
					getItem("§cDiamanthose", Material.DIAMOND_LEGGINGS, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Eisenaxt
		if (p.getInventory().contains(Material.DIAMOND, 4)) {
			inv.setItem(26, getItem("§aDiamantschuhe", Material.DIAMOND_BOOTS, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					26,
					getItem("§cDiamantschuhe", Material.DIAMOND_BOOTS, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Eisenharke
		if (p.getInventory().contains(Material.WOOD, 2) && p.getInventory().contains(Material.STICK, 1)) {
			inv.setItem(30, getItem("§aHolzschwert", Material.WOOD_SWORD, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					30,
					getItem("§cHolzschwert", Material.WOOD_SWORD, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Diamantspitzhacke
		if (p.getInventory().contains(Material.COBBLESTONE, 2) && p.getInventory().contains(Material.STICK, 1)) {
			inv.setItem(31, getItem("§aSteinschwert", Material.STONE_SWORD, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					31,
					getItem("§cSteinschwert", Material.STONE_SWORD, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Diamantschaufel
//		if (p.getInventory().contains(Material.SHIELD, 1) && p.getInventory().contains(Material.SHIELD, 2)) {
//			inv.setItem(32, getItem("§aSchild", Material.SHIELD, 1, 0, new String[] {}, false, null));
//		} else {
//			inv.setItem(
//					32,
//					getItem("§cSchild", Material.SHIELD, 1, 0, new String[] { notEnoughItems1, notEnoughItems2 },
//							false, null));
//		}

		// Kompass
		if (p.getInventory().contains(Material.GOLD_INGOT, 2) && p.getInventory().contains(Material.STICK, 1)) {
			inv.setItem(33, getItem("§aGoldschwert", Material.GOLD_SWORD, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					33,
					getItem("§cGoldschwert", Material.GOLD_SWORD, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Diamantharke
		if (p.getInventory().contains(Material.IRON_INGOT, 2) && p.getInventory().contains(Material.STICK, 1)) {
			inv.setItem(39, getItem("§aEisenschwert", Material.IRON_SWORD, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					39,
					getItem("§cEisenschwert", Material.IRON_SWORD, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Diamantaxt
		if (p.getInventory().contains(Material.DIAMOND, 2) && p.getInventory().contains(Material.STICK, 1)) {
			inv.setItem(42,
					getItem("§aDiamantschwert", Material.DIAMOND_SWORD, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					42,
					getItem("§cDiamantschwert", Material.DIAMOND_SWORD, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		inv.setItem(0, getItem("§cZurück", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(9, getItem("§cZurück", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(18, getItem("§cZurück", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(27, getItem("§cZurück", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(36, getItem("§cZurück", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		return inv;
	}

	public Inventory getKategorie() {
		Inventory inv = Bukkit.createInventory(null, 9 * 5, "§bKategorie");
		inv.setItem(0, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(1, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(2, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(3, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(4, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(5, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(6, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(7, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(8, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(9, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(17, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(18, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(26, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(27, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(35, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(36, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(37, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(38, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(39, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(40, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(41, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(42, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(43, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(44, getItem("§c", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(20, getItem("§eBaustoffe", Material.WOOD, 1, 0, new String[] {}, false, null));
		inv.setItem(22, getItem("§aWerkzeuge", Material.WOOD_PICKAXE, 1, 0, new String[] {}, false, null));
		inv.setItem(24, getItem("§cKampf", Material.DIAMOND_SWORD, 1, 0, new String[] {}, false, null));
		return inv;
	}

	public Inventory getWerkzeuge(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 5, "§aWerkzeuge");
		if (p.getInventory().contains(Material.WOOD, 2)) {
			inv.setItem(3, getItem("§aStock", Material.STICK, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					3,
					getItem("§cStock", Material.STICK, 1, 0, new String[] { notEnoughItems1, notEnoughItems2 },
							false, null));
		}

		// Fackel
		if (p.getInventory().contains(Material.WOOD, 1) && p.getInventory().contains(Material.COAL, 1)) {
			inv.setItem(6, getItem("§aFackel", Material.TORCH, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					6,
					getItem("§cFackel", Material.TORCH, 1, 0, new String[] { notEnoughItems1, notEnoughItems2 },
							false, null));
		}

		// Holzspitzhacke
		if (p.getInventory().contains(Material.WOOD, 3) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(10, getItem("§aHolzspitzhacke", Material.WOOD_PICKAXE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					10,
					getItem("§cHolzspitzhacke", Material.WOOD_PICKAXE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Holzschaufel
		if (p.getInventory().contains(Material.WOOD, 1) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(11, getItem("§aHolzschaufel", Material.WOOD_SPADE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					11,
					getItem("§cHolzschaufel", Material.WOOD_SPADE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Holzaxt
		if (p.getInventory().contains(Material.WOOD, 3) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(12, getItem("§aHolzaxt", Material.WOOD_AXE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					12,
					getItem("§cHolzaxt", Material.WOOD_AXE, 1, 0,
							new String[] { notEnoughItems1, notEnoughItems2 }, false, null));
		}

		// Holzhoe
		if (p.getInventory().contains(Material.WOOD, 2) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(13, getItem("§aHolzharke", Material.WOOD_HOE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					13,
					getItem("§cHolzharke", Material.WOOD_HOE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Steinspitzhacke
		if (p.getInventory().contains(Material.COBBLESTONE, 3) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(14,
					getItem("§aSteinspitzhacke", Material.STONE_PICKAXE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					14,
					getItem("§cSteinspitzhacke", Material.STONE_PICKAXE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Steinschaufel
		if (p.getInventory().contains(Material.COBBLESTONE, 1) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(15, getItem("§aSteinschaufel", Material.STONE_SPADE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					15,
					getItem("§cSteinschaufel", Material.STONE_SPADE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Steinaxt
		if (p.getInventory().contains(Material.COBBLESTONE, 3) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(16, getItem("§aSteinaxt", Material.STONE_AXE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					16,
					getItem("§cSteinaxt", Material.STONE_AXE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Steinharke
		if (p.getInventory().contains(Material.COBBLESTONE, 2) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(17, getItem("§aSteinharke", Material.STONE_HOE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					17,
					getItem("§cSteinharke", Material.STONE_HOE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Goldspitzhacke
		if (p.getInventory().contains(Material.GOLD_INGOT, 3) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(19, getItem("§aGoldspitzhacke", Material.GOLD_PICKAXE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					19,
					getItem("§cGoldspitzhacke", Material.GOLD_PICKAXE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Goldschaufel
		if (p.getInventory().contains(Material.GOLD_INGOT, 1) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(20, getItem("§aGoldschaufel", Material.GOLD_SPADE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					20,
					getItem("§cGoldschaufel", Material.GOLD_SPADE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Goldaxt
		if (p.getInventory().contains(Material.GOLD_INGOT, 3) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(21, getItem("§aGoldaxt", Material.GOLD_AXE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					21,
					getItem("§cGoldaxt", Material.GOLD_AXE, 1, 0,
							new String[] { notEnoughItems1, notEnoughItems2 }, false, null));
		}

		// Goldharke
		if (p.getInventory().contains(Material.GOLD_INGOT, 2) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(22, getItem("§aGoldharke", Material.GOLD_HOE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					22,
					getItem("§cGoldharke", Material.GOLD_HOE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Eisenspitzhacke
		if (p.getInventory().contains(Material.IRON_INGOT, 3) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(23,
					getItem("§aEisenspitzhacke", Material.IRON_PICKAXE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					23,
					getItem("§cEisenspitzhacke", Material.IRON_PICKAXE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Eisenschaufel
		if (p.getInventory().contains(Material.IRON_INGOT, 1) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(24, getItem("§aEisenschaufel", Material.IRON_SPADE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					24,
					getItem("§cEisenschaufel", Material.IRON_SPADE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Eisenaxt
		if (p.getInventory().contains(Material.IRON_INGOT, 3) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(25, getItem("§aEisenaxt", Material.IRON_AXE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					25,
					getItem("§cEisenaxt", Material.IRON_AXE, 1, 0,
							new String[] { notEnoughItems1, notEnoughItems2 }, false, null));
		}

		// Eisenharke
		if (p.getInventory().contains(Material.IRON_INGOT, 2) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(26, getItem("§aEisenharke", Material.IRON_HOE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					26,
					getItem("§cEisenharke", Material.IRON_HOE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Diamantspitzhacke
		if (p.getInventory().contains(Material.DIAMOND, 3) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(28,
					getItem("§aDiamantspitzhacke", Material.DIAMOND_PICKAXE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					28,
					getItem("§cDiamantspitzhacke", Material.DIAMOND_PICKAXE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Diamantschaufel
		if (p.getInventory().contains(Material.DIAMOND, 1) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(29,
					getItem("§aDiamantschaufel", Material.DIAMOND_SPADE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					29,
					getItem("§cDiamantschaufel", Material.DIAMOND_SPADE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Diamantaxt
		if (p.getInventory().contains(Material.DIAMOND, 3) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(30, getItem("§aDiamantaxt", Material.DIAMOND_AXE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					30,
					getItem("§cDiamantaxt", Material.DIAMOND_AXE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Diamantharke
		if (p.getInventory().contains(Material.DIAMOND, 2) && p.getInventory().contains(Material.STICK, 2)) {
			inv.setItem(31, getItem("§aDiamantharke", Material.DIAMOND_HOE, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					31,
					getItem("§cDiamantharke", Material.DIAMOND_HOE, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}

		// Kompass
		if (p.getInventory().contains(Material.IRON_INGOT, 4) && p.getInventory().contains(Material.REDSTONE, 1)) {
			inv.setItem(32, getItem("§aKompass", Material.COMPASS, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					32,
					getItem("§cKompass", Material.COMPASS, 1, 0,
							new String[] { notEnoughItems1, notEnoughItems2 }, false, null));
		}

		// Uhr
		if (p.getInventory().contains(Material.GOLD_INGOT, 4) && p.getInventory().contains(Material.REDSTONE, 1)) {
			inv.setItem(33, getItem("§aUhr", Material.WATCH, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					33,
					getItem("§cUhr", Material.WATCH, 1, 0, new String[] { notEnoughItems1, notEnoughItems2 },
							false, null));
		}

		// Angel
		if (p.getInventory().contains(Material.STICK, 3) && p.getInventory().contains(Material.STRING, 2)) {
			inv.setItem(34, getItem("§aAngel", Material.FISHING_ROD, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					34,
					getItem("§cAngel", Material.FISHING_ROD, 1, 0,
							new String[] { notEnoughItems1, notEnoughItems2 }, false, null));
		}

		// Schere
		if (p.getInventory().contains(Material.IRON_INGOT, 2)) {
			inv.setItem(35, getItem("§aSchere", Material.SHEARS, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					35,
					getItem("§cSchere", Material.SHEARS, 1, 0, new String[] { notEnoughItems1, notEnoughItems2 },
							false, null));
		}

		// Leine
		if (p.getInventory().contains(Material.STRING, 4) && p.getInventory().contains(Material.SLIME_BALL, 1)) {
			inv.setItem(40, getItem("§aLeine", Material.LEASH, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					40,
					getItem("§cLeine", Material.LEASH, 1, 0, new String[] { notEnoughItems1, notEnoughItems2 },
							false, null));
		}

		// Feuerzeug
		if (p.getInventory().contains(Material.IRON_INGOT, 1) && p.getInventory().contains(Material.FLINT, 1)) {
			inv.setItem(41, getItem("§aFeuerzeug", Material.FLINT_AND_STEEL, 1, 0, new String[] {}, false, null));
		} else {
			inv.setItem(
					41,
					getItem("§cFeuerzeug", Material.FLINT_AND_STEEL, 1, 0, new String[] { notEnoughItems1,
							notEnoughItems2 }, false, null));
		}
		inv.setItem(0, getItem("§cZurück", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(9, getItem("§cZurück", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(18, getItem("§cZurück", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(27, getItem("§cZurück", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		inv.setItem(36, getItem("§cZurück", Material.STAINED_GLASS_PANE, 1, 14, new String[] {}, false, null));
		return inv;
	}

	@EventHandler
	public void onClickInv(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getClickedInventory() != null && e.getClickedInventory().getName().contains("Kampf")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Zurück")) {
				p.openInventory(getKategorie());
			}
		}
		if (e.getClickedInventory() != null && e.getClickedInventory().getName().contains("Werkzeuge")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Zurück")) {
				p.openInventory(getKategorie());
			}
			if(isSlotFree(p.getInventory())){
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aStock")) {
					if (p.getInventory().contains(Material.WOOD, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.WOOD, 2));
						p.getInventory().addItem(new ItemStack(Material.STICK, 4));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFackel")) {
					if (p.getInventory().contains(Material.WOOD, 1) && p.getInventory().contains(Material.COAL, 1)) {
						p.getInventory().removeItem(new ItemStack(Material.WOOD, 1));
						p.getInventory().removeItem(new ItemStack(Material.COAL, 1));
						p.getInventory().addItem(new ItemStack(Material.TORCH, 4));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aHolzspitzhacke")) {
					if (p.getInventory().contains(Material.WOOD, 3) && p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.WOOD, 3));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.WOOD_PICKAXE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aHolzschaufel")) {
					if (p.getInventory().contains(Material.WOOD, 1) && p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.WOOD, 1));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.WOOD_SPADE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aHolzaxt")) {
					if (p.getInventory().contains(Material.WOOD, 3) && p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.WOOD, 3));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.WOOD_AXE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aHolzharke")) {
					if (p.getInventory().contains(Material.WOOD, 2) && p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.WOOD, 2));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.WOOD_HOE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSteinspitzhacke")) {
					if (p.getInventory().contains(Material.COBBLESTONE, 3)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 3));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSteinschaufel")) {
					if (p.getInventory().contains(Material.COBBLESTONE, 1)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 1));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.STONE_SPADE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSteinaxt")) {
					if (p.getInventory().contains(Material.COBBLESTONE, 3)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 3));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSteinharke")) {
					if (p.getInventory().contains(Material.COBBLESTONE, 2)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 2));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.STONE_HOE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aGoldspitzhacke")) {
					if (p.getInventory().contains(Material.GOLD_INGOT, 3)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 3));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.GOLD_PICKAXE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aGoldschaufel")) {
					if (p.getInventory().contains(Material.GOLD_INGOT, 1)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 1));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.GOLD_SPADE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aGoldaxt")) {
					if (p.getInventory().contains(Material.GOLD_INGOT, 3)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 3));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.GOLD_AXE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aGoldharke")) {
					if (p.getInventory().contains(Material.GOLD_INGOT, 2)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 2));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.GOLD_HOE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aEisenspitzhacke")) {
					if (p.getInventory().contains(Material.IRON_INGOT, 3)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 3));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aEisenschaufel")) {
					if (p.getInventory().contains(Material.IRON_INGOT, 1)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 1));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.IRON_SPADE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aEisenaxt")) {
					if (p.getInventory().contains(Material.IRON_INGOT, 3)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 3));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.IRON_AXE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aEisenharke")) {
					if (p.getInventory().contains(Material.IRON_INGOT, 2)
							&& p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 2));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.IRON_HOE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aDiamantspitzhacke")) {
					if (p.getInventory().contains(Material.DIAMOND, 3) && p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 3));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aDiamantschaufel")) {
					if (p.getInventory().contains(Material.DIAMOND, 1) && p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 1));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND_SPADE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aDiamantaxt")) {
					if (p.getInventory().contains(Material.DIAMOND, 3) && p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 3));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aDiamantharke")) {
					if (p.getInventory().contains(Material.DIAMOND, 2) && p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 2));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND_HOE));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aKompass")) {
					if (p.getInventory().contains(Material.IRON_INGOT, 4)
							&& p.getInventory().contains(Material.REDSTONE, 1)) {
						p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 4));
						p.getInventory().removeItem(new ItemStack(Material.REDSTONE, 1));
						p.getInventory().addItem(new ItemStack(Material.COMPASS));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aUhr")) {
					if (p.getInventory().contains(Material.GOLD_INGOT, 4)
							&& p.getInventory().contains(Material.REDSTONE, 1)) {
						p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
						p.getInventory().removeItem(new ItemStack(Material.REDSTONE, 1));
						p.getInventory().addItem(new ItemStack(Material.WATCH));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAngel")) {
					if (p.getInventory().contains(Material.STRING, 3) && p.getInventory().contains(Material.STICK, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.STRING, 3));
						p.getInventory().removeItem(new ItemStack(Material.STICK, 2));
						p.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSchere")) {
					if (p.getInventory().contains(Material.IRON_INGOT, 2)) {
						p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 2));
						p.getInventory().addItem(new ItemStack(Material.SHEARS));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLeine")) {
					if (p.getInventory().contains(Material.STRING, 4)
							&& p.getInventory().contains(Material.SLIME_BALL, 1)) {
						p.getInventory().removeItem(new ItemStack(Material.STRING, 4));
						p.getInventory().removeItem(new ItemStack(Material.SLIME_BALL, 1));
						p.getInventory().addItem(new ItemStack(Material.LEASH));
						p.openInventory(getWerkzeuge(p));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFeuerzeug")) {
					if (p.getInventory().contains(Material.IRON_INGOT, 1)
							&& p.getInventory().contains(Material.FLINT, 1)) {
						p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 1));
						p.getInventory().removeItem(new ItemStack(Material.FLINT, 1));
						p.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL));
						p.openInventory(getWerkzeuge(p));
					}
				}
			} else {
				p.sendMessage("§7[§eCrafting-System§7] §cDein Inventar ist voll");
			}
		}

		if (e.getClickedInventory() != null && e.getClickedInventory().getName().contains("Kategorie")) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null) {
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Werkzeuge")) {
					p.openInventory(getWerkzeuge(p));
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Kampf")) {
					p.openInventory(getCombat(p));
				}
			}
		}
	}

	@EventHandler
	public void onCraftingBenchOpen(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.WORKBENCH) {
				e.setCancelled(true);
				Player p = e.getPlayer();
				p.openInventory(getKategorie());
			}
		}
	}

}
