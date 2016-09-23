package de.DerMicha.api;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatAPI {

	public static BaseComponent courserText(String text, String courser, String ausführen){
		BaseComponent component = new TextComponent(text);
		component.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new TextComponent[] { new TextComponent(courser) }));
		component.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, ausführen));
		component.toLegacyText();
		return component;
	}
	
	public static BaseComponent VorschreibText(String text, String courser, String vorschreiben){
		BaseComponent component = new TextComponent(text);
		return component;
	}
	
	public static BaseComponent getNrmlText(String text){
		BaseComponent component = new TextComponent(text);
		return component;
	}
	
	public static void sendMessage(Player p, BaseComponent component){
		p.spigot().sendMessage(component);
	}
	
}

