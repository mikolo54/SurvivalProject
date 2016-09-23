package de.Dargen.npc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.WorldSettings.EnumGamemode;

public class NPC extends Reflections {

	private int entityID;
	public Location location;
	private GameProfile gameprofile;
	public int id;
	
	/**
	 * 
	 * @param interactId Dieser Wert kann für Abfragen in Interact-Listenern nützlich sein.
	 * @param name
	 * @param skin Der Skin der für den NPC genutzt wird.
	 * @param location Der Ort an dem der NPC spawnen soll.
	 */
	public NPC(int interactId, String name, NPCSkin skin, Location location){
		entityID = (int)Math.ceil(Math.random() * 1000) + 2000;
		gameprofile = new GameProfile(UUID.randomUUID(), name);
		gameprofile.getProperties().put("textures", new Property("textures", skin.value, skin.signature));
		this.location = location.clone();
		this.id = interactId;

	}
	
	/**
	 * Sendet den NPC an einen Spieler.
	 * @param p Der Spieler an den der NPC gesendet werden soll.
	 */
	public void spawn(Player p){
		/*
		PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn();
		setValue(packet, "a", entityID);
        setValue(packet, "b", gameprofile.getId());
        setValue(packet, "c", location.getX());
        setValue(packet, "d", location.getY());
        setValue(packet, "e", location.getZ());
        setValue(packet, "f", (byte)(int)(location.getYaw() * 256.0F / 360.0F));
        setValue(packet, "g", (byte)(int)(location.getPitch() * 256.0F / 360.0F));
        
        DataWatcher watcher = new DataWatcher(null);
        watcher.register(new DataWatcherObject<>(6, DataWatcherRegistry.c), 20F);
        watcher.register(new DataWatcherObject<>(10, DataWatcherRegistry.a), (byte)127);
        setValue(packet, "h", watcher);
		addToTablist();
		sendPacket(packet, p);
		headRotation(location.getYaw(), location.getPitch());
		*/
		try {
			Class<?> packetPlayOutNamedEntitySpawnClass = getNMSClass("PacketPlayOutNamedEntitySpawn");
			Constructor<?> packetPlayOutNamedEntitySpawnConstr = packetPlayOutNamedEntitySpawnClass.getConstructor();
			Object packetPlayOutNamedEntitySpawnObj = packetPlayOutNamedEntitySpawnConstr.newInstance();
			
			setValue(packetPlayOutNamedEntitySpawnObj, "a", entityID);
			setValue(packetPlayOutNamedEntitySpawnObj, "b", gameprofile.getId());
			setValue(packetPlayOutNamedEntitySpawnObj, "c", location.getX());
			setValue(packetPlayOutNamedEntitySpawnObj, "d", location.getY());
			setValue(packetPlayOutNamedEntitySpawnObj, "e", location.getZ());
			setValue(packetPlayOutNamedEntitySpawnObj, "f", (byte)(int)(location.getYaw() * 256.0F / 360.0F));
			setValue(packetPlayOutNamedEntitySpawnObj, "g", (byte)(int)(location.getYaw() * 256.0F / 360.0F));
			
			Class<?> dataWatcherClass = getNMSClass("DataWatcher");
			Class<?> entityClass = getNMSClass("Entity");
			Constructor<?> dataWatcherConstr = dataWatcherClass.getConstructor(entityClass);
			Object dataWatcherObj = dataWatcherConstr.newInstance(new Object[]{null});
			
			Class<?> dataWatcherObjectClass = getNMSClass("DataWatcherObject");
			Method registerMethod = dataWatcherClass.getMethod("register", dataWatcherObjectClass, Object.class);
			Class<?> dataWatcherSerializerClass = getNMSClass("DataWatcherSerializer");
			Constructor<?> dataWatcherObjectConstr = dataWatcherObjectClass.getConstructor(int.class, dataWatcherSerializerClass);
			Class<?> dataWatcherRegistry = getNMSClass("DataWatcherRegistry");
			Object c = dataWatcherRegistry.getField("c").get(null);
			Object dataWatcherObjectObj = dataWatcherObjectConstr.newInstance(6, c);
			registerMethod.invoke(dataWatcherObj, dataWatcherObjectObj, 20F);
			Object a = dataWatcherRegistry.getField("a").get(null);
			dataWatcherObjectObj = dataWatcherObjectConstr.newInstance(10, a);
			registerMethod.invoke(dataWatcherObj, dataWatcherObjectObj, (byte)127);

			setValue(packetPlayOutNamedEntitySpawnObj, "h", dataWatcherObj);
			
			sendPacket(packetPlayOutNamedEntitySpawnObj, p);
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
//	public void changeSkin(){
//		String value = "eyJ0aW1lc3RhbXAiOjE0NDI4MzY1MTU1NzksInByb2ZpbGVJZCI6IjkwZWQ3YWY0NmU4YzRkNTQ4MjRkZTc0YzI1MTljNjU1IiwicHJvZmlsZU5hbWUiOiJDb25DcmFmdGVyIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xMWNlZDMzMjNmYjczMmFjMTc3MTc5Yjg5NWQ5YzJmNjFjNzczZWYxNTVlYmQ1Y2M4YzM5NTZiZjlhMDlkMTIifX19";
//		String signature = "tFGNBQNpxNGvD27SN7fqh3LqNinjJJFidcdF8LTRHOdoMNXcE5ezN172BnDlRsExspE9X4z7FPglqh/b9jrLFDfQrdqX3dGm1cKjYbvOXL9BO2WIOEJLTDCgUQJC4/n/3PZHEG2mVADc4v125MFYMfjzkznkA6zbs7w6z8f7pny9eCWNXPOQklstcdc1h/LvflnR+E4TUuxCf0jVsdT5AZsUYIsJa6fvr0+vItUXUdQ3pps0zthObPEnBdLYMtNY3G6ZLGVKcSGa/KRK2D/k69fmu/uTKbjAWtniFB/sdO0VNhLuvyr/PcZVXB78l1SfBR88ZMiW6XSaVqNnSP+MEfRkxgkJWUG+aiRRLE8G5083EQ8vhIle5GxzK68ZR48IrEX/JwFjALslCLXAGR05KrtuTD3xyq2Nut12GCaooBEhb46sipWLq4AXI9IpJORLOW8+GvY+FcDwMqXYN94juDQtbJGCQo8PX670YjbmVx7+IeFjLJJTZotemXu1wiQmDmtAAmug4U5jgMYIJryXMitD7r5pEop/cw42JbCO2u0b5NB7sI/mr4OhBKEesyC5usiARzuk6e/4aJUvwQ9nsiXfeYxZz8L/mh6e8YPJMyhVkFtblbt/4jPe0bs3xSUXO9XrDyhy9INC0jlLT22QjNzrDkD8aiGAopVvfnTTAug=";
//		gameprofile.getProperties().put("textures", new Property("textures", value, signature));
//	}
	
	/*
	public void animation(int animation){
		PacketPlayOutAnimation packet = new PacketPlayOutAnimation();
		setValue(packet, "a", entityID);
		setValue(packet, "b", (byte)animation);
		sendPacket(packet);
	}
	
	public void status(int status){
		PacketPlayOutEntityStatus packet = new PacketPlayOutEntityStatus();
		setValue(packet, "a", entityID);
		setValue(packet, "b", (byte)status);
		sendPacket(packet);
	}
	
	public void equip(int slot,ItemStack itemstack){
		PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment();
		setValue(packet, "a", entityID);
		setValue(packet, "b", slot);
		setValue(packet, "c", itemstack);
		sendPacket(packet);
	}
	
	@SuppressWarnings("deprecation")
	public void sleep(boolean state){
		if(state){
			Location bedLocation = new Location(location.getWorld(), 1, 1, 1);
			PacketPlayOutBed packet = new PacketPlayOutBed();
			setValue(packet, "a", entityID);
			setValue(packet, "b", new BlockPosition(bedLocation.getX(),bedLocation.getY(),bedLocation.getZ()));
			
			for(Player pl : Bukkit.getOnlinePlayers()){
				pl.sendBlockChange(bedLocation, Material.BED_BLOCK, (byte)0);
			}

			sendPacket(packet);
			teleport(location.clone().add(0,0.3,0));
		}else{
			animation(2);
			teleport(location.clone().subtract(0,0.3,0));
		}
	}
	
	public void teleport(Location location){
		PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport();
		setValue(packet, "a", entityID);
		setValue(packet, "b", getFixLocation(location.getX()));
		setValue(packet, "c", getFixLocation(location.getY()));
		setValue(packet, "d", getFixLocation(location.getZ()));
		setValue(packet, "e", getFixRotation(location.getYaw()));
		setValue(packet, "f", getFixRotation(location.getPitch()));

		sendPacket(packet);
		headRotation(location.getYaw(), location.getPitch());
		this.location = location.clone();
	}
	
	public void headRotation(float yaw,float pitch){
		PacketPlayOutEntityLook packet = new PacketPlayOutEntityLook(entityID, getFixRotation(yaw),getFixRotation(pitch) , true);
		PacketPlayOutEntityHeadRotation packetHead = new PacketPlayOutEntityHeadRotation();
		setValue(packetHead, "a", entityID);
		setValue(packetHead, "b", getFixRotation(yaw));
		

		sendPacket(packet);
		sendPacket(packetHead);
	}
	*/
	public void destroy(){
		PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] {entityID});
		rmvFromTablist();
		sendPacket(packet);
	}
	
	public void addToTablist(){
		PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
		PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameprofile, 1, EnumGamemode.NOT_SET, CraftChatMessage.fromString(gameprofile.getName())[0]);
		@SuppressWarnings("unchecked")
		List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");
		players.add(data);
		
		setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
		setValue(packet, "b", players);
		
		sendPacket(packet);
	}
	
	public void rmvFromTablist(){
		PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
		PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameprofile, 1, EnumGamemode.NOT_SET, CraftChatMessage.fromString(gameprofile.getName())[0]);
		@SuppressWarnings("unchecked")
		List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");
		players.add(data);
		
		setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
		setValue(packet, "b", players);
		
		sendPacket(packet);
	}
	
	public int getFixLocation(double pos){
		return (int)MathHelper.floor(pos * 32.0D);
	}
	
	public int getEntityID() {
		return entityID;
	}
	
	public byte getFixRotation(float yawpitch){
		return (byte) ((int) (yawpitch * 256.0F / 360.0F));
	}
	
	private void sendPacket(Object packet, Player player) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException, ClassNotFoundException{
		Method getHandleMethod = player.getClass().getMethod("getHandle");
		Object nmsPlayerObj = getHandleMethod.invoke(player);
		Field conField = nmsPlayerObj.getClass().getField("playerConnection");
		Object con = conField.get(nmsPlayerObj);
		
		Method sendPacket = getNMSClass("PlayerConnection").getMethod("sendPacket", getNMSClass("Packet"));
		sendPacket.invoke(con, packet);
	}
	
	private Class<?> getNMSClass(String nmsClassString) throws ClassNotFoundException {
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
		String name = "net.minecraft.server." + version + nmsClassString;
		Class<?> nmsClass = Class.forName(name);
		return nmsClass;
	}
	
	public void setValue(Object instance, String fieldName, Object value){
		try{
		Field field = instance.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(instance, value);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}