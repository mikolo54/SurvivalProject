package de.Dargen.npc;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.DerMicha.Survival;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.Packet;

public class PacketReader {

	Player player;
	Channel channel;
	Survival main;
	
	
	public PacketReader(Player player, Survival main) {
		this.player = player;
		this.main = main;
	}
	
	public void inject(){
		CraftPlayer cPlayer = (CraftPlayer)this.player;
		channel = cPlayer.getHandle().playerConnection.networkManager.channel;
		channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<Packet<?>>() {@Override protected void decode(ChannelHandlerContext arg0, Packet<?> packet,List<Object> arg2) throws Exception {arg2.add(packet);readPacket(packet);}});
	}
	
	public void uninject(){
		if(channel.pipeline().get("PacketInjector") != null){
			channel.pipeline().remove("PacketInjector");
		}
	}
	

	public void readPacket(Packet<?> packet){
		if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")){
			int id = (Integer)getValue(packet, "a");
			
			for(Map.Entry<UUID, NPC> ls : Survival.instace.npcs.npcs.entrySet()){
				NPC npc = ls.getValue();
				if(npc.getEntityID() == id){
					if(getValue(packet, "action").toString().equalsIgnoreCase("ATTACK")){
						if(player.hasPermission("npcdestroy") || player.isOp()){
							npc.destroy();
	                    }
					}
					if(getValue(packet, "action").toString().equalsIgnoreCase("INTERACT")){
						Bukkit.broadcastMessage("ss");
//						player.sendMessage("�eM�chtest du die Lobby verlassen?");
//						ChatAPI.sendMessage(player, ChatAPI.getAusfhrText(" �a�lJA", "�aDu m�chtest die Lobby verlassen", "/ja"));
//						ChatAPI.sendMessage(player, ChatAPI.getAusf�hrText(" �a�lNEIN", "�cDu m�chtest die Lobby nicht verlassen", "/nein"));
					} 
				}
			}
			
			
			
		}
	}
	

	public void setValue(Object obj,String name,Object value){
		try{
		Field field = obj.getClass().getDeclaredField(name);
		field.setAccessible(true);
		field.set(obj, value);
		}catch(Exception e){}
	}
	
	public Object getValue(Object obj,String name){
		try{
		Field field = obj.getClass().getDeclaredField(name);
		field.setAccessible(true);
		return field.get(obj);
		}catch(Exception e){}
		return null;
	}
	
}