package de.DerMicha.jobs;

public enum JobsEnum {

	HOLZFÄLLER("§6Holzfäller"),
	ERKUNDER("§aErkunder"),
	MINENARBEITER("§7Minenarbeiter"),
	FARMER("§2Farmer"),
	BRAUER("§5Brauer");
	
	public String name;
	
	JobsEnum(String name){
		this.name = name;
	}
	
	
}
