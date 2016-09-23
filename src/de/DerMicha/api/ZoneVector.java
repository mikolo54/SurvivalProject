package de.DerMicha.api;

public class ZoneVector{
    private int x;
    private int y;
    private int z;
 
	public ZoneVector(int x, int y, int z){
	    this.x = x;
	    this.z = z;
	    this.y = y;
	}
	 
	public boolean isInAABB(ZoneVector min, ZoneVector max){ //Idk why i use this name for the method, i just do.
	return ((this.x <= max.x) && (this.x >=min.x) && (this.z <= max.z) && (this.z >= min.z) /* Optional code*/ && (this.y <= max.y) && (this.y >= min.y));
	 
	//Getter and setters for x y z
	}
}