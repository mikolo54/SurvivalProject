package de.DerMicha.lang;

import java.util.HashMap;

public class Language {

	public HashMap<String, String> messages = new HashMap<String,String>();
	
	public void load (){
		//this.messages.put("penis", "Penis");
	}
	
	
	public String get(String text)
	{
	    if (messages.isEmpty()) this.load();
	    if (!messages.containsKey(text)) return "MISSING TRANSLATION: " + text;
	    return (String)messages.get(text);
	}
}
