package de.DerMicha.regions;

import java.util.UUID;

import org.bukkit.Location;

/**
 * Klasse, die für Speicherbare Regions genutzt wird.
 * @param id Die ID die für das Speichern genutzt wird.
 * @author _xThaDargen
 */
public class SaveableRegion extends Region {

	public UUID id;

	public SaveableRegion(Location pos1, Location pos2, UUID id) {
		super(pos1, pos2);
		this.id = id;
	}


}
