package de.DerMicha.regions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Block;

import de.DerMicha.Survival;
import de.DerMicha.api.ZoneVector;

/**
 * Die Region-Klasse wird als Datentyp für Regions benutzt.
 * @author _xThaDargen
 *
 */
public class Region {

	/** Punkt 1 der Region. */
	public Location pos1;
	
	/** Punkt 2 der Region. */
	public Location pos2;
	
	/**
	 * @param pos1 Position 1 der Region.
	 * @param pos2 Position 2 der Region.
	 * @param isTopToBottom Bestimmt ob die Region "vom Bedrock bis zum Himmel" oder nur in den ausgewählten Bereichen ist.
	 */
	public Region(Location pos1, Location pos2) {
		this.pos1 = pos1;
		this.pos2 = pos2;
	}

	/**
	 * Prüft ob der gegebene Punkt l sich in der Region befindet,
	 * @param l Die Location auf die geprüft wird.
	 * @return Ob die Location l 
	 */
	public boolean isInRegion(Location loc){
	    ZoneVector curr = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	    ZoneVector min = new ZoneVector(Math.min(this.pos1.getBlockX(), this.pos2.getBlockX()), Math.min(this.pos1.getBlockY(), this.pos2.getBlockY()), Math.min(this.pos1.getBlockZ(), this.pos2.getBlockZ()));
	    ZoneVector max = new ZoneVector(Math.max(this.pos1.getBlockX(), this.pos2.getBlockX()), Math.max(this.pos1.getBlockY(), this.pos2.getBlockY()), Math.max(this.pos1.getBlockZ(), this.pos2.getBlockZ()));
	    return curr.isInAABB(min, max);
	}

	/**
	 * Gibt ALLE Blöcke in dieser Region als Array aus.
	 * @return Ein Array aller Blöcke in dieser Region.
	 */
	public List<Block> getAllBlocks() {
        List<Block> blocks = new ArrayList<Block>();
        
        int minx = Math.min(this.pos1.getBlockX(), this.pos2.getBlockX()),
        miny = Math.min(this.pos1.getBlockY(), this.pos2.getBlockY()),
        minz = Math.min(this.pos1.getBlockZ(), this.pos2.getBlockZ()),
        maxx = Math.max(this.pos1.getBlockX(), this.pos2.getBlockX()),
        maxy = Math.max(this.pos1.getBlockY(), this.pos2.getBlockY()),
        maxz = Math.max(this.pos1.getBlockZ(), this.pos2.getBlockZ());
        for (int x = minx; x<=maxx;x++) {
            for (int y = miny; y<=maxy;y++) {
                for (int z = minz; z<=maxz;z++) {
                    blocks.add(this.pos1.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

	
	public List<Location> getAllBlockLocations() {
        List<Location> blocks = new ArrayList<Location>();
        
        int lowerx = Math.min(this.pos1.getBlockX(), this.pos2.getBlockX()),
        lowery = Math.min(this.pos1.getBlockY(), this.pos2.getBlockY()),
        lowerz = Math.min(this.pos1.getBlockZ(), this.pos2.getBlockZ()),
        greaterx = Math.max(this.pos1.getBlockX(), this.pos2.getBlockX()),
        greatery = Math.max(this.pos1.getBlockY(), this.pos2.getBlockY()),
        greaterz = Math.max(this.pos1.getBlockZ(), this.pos2.getBlockZ());
        for (int x = lowerx; x<=greaterx;x++) {
            for (int y = lowery; y<=greatery;y++) {
                for (int z = lowerz; z<=greaterz;z++) {
                    blocks.add(new Location(this.pos1.getWorld(), x,y,z));
                }
            }
        }
        return blocks;
    }
	
	/**
	 * Prüft ob diese Region mit einer anderen gespeicherten Region kollidiert.
	 * @return true wenn die Region mit einer anderen gespeicherten region kollidiert.
	 */
	public boolean overlaps(Region r){
		int c1MaxX = Math.max(this.pos1.getBlockX(), this.pos2.getBlockX());
		int c1MinX = Math.min(this.pos1.getBlockX(), this.pos2.getBlockX());
		int c1MaxZ = Math.max(this.pos1.getBlockZ(), this.pos2.getBlockZ());
		int c1MinZ = Math.min(this.pos1.getBlockZ(), this.pos2.getBlockZ());
		
		int c2MaxX = Math.max(r.pos1.getBlockX(), r.pos2.getBlockX());
		int c2MinX = Math.min(r.pos1.getBlockX(), r.pos2.getBlockX());
		int c2MaxZ = Math.max(r.pos1.getBlockZ(), r.pos2.getBlockZ());
		int c2MinZ = Math.min(r.pos1.getBlockZ(), r.pos2.getBlockZ());
		return !(c1MinX > c2MaxX || c1MaxX < c2MinX || c1MinZ > c2MaxZ || c1MaxZ < c2MinZ);
	}
	
	public boolean overlapsAnySaved(){
		for (Map.Entry<UUID, SaveableRegion> entry : Survival.instace.regions.regions.entrySet()) {
		    SaveableRegion rg = entry.getValue();
		    if((rg.pos1 == this.pos1) && (rg.pos2==this.pos2))continue;
		    if(this.overlaps(rg))return true;
		}
		return false;
	}
}
