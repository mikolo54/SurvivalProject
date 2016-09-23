package de.DerMicha;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.DerMicha.api.ParticleEffect;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Util {

	/**
	 * Wenn du diese Fuktion nicht verstehst bist du dumm.
	 * @param player Wer braucht dazu schon eine Erklärung.
	 * @param message Und was könnte das hier sein?
	 */
	public static void sendActionBar(Player player, String message){
    	CraftPlayer p = (CraftPlayer) player;
        IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc,(byte) 2);
        p.getHandle().playerConnection.sendPacket(ppoc);
    }

	/**
	 * Sendet eine Actionbar-Message an einen Spieler.
	 * @param player Das Ziel
	 * @param message Die Message
	 * @param durationTicks Die Anzeigedauer der Message in Ticks (20 = 1 Sekunde)
	 */
	public static void sendActionBar(final Player player, final String message, int durationTicks) {
		sendActionBar(player, message);
		if (durationTicks >= 0) {
			new BukkitRunnable() {
				@Override
				public void run() {
					sendActionBar(player, "");
				}
			}.runTaskLater(Survival.instace, durationTicks + 1);
		}
		while (durationTicks > 60) {
			durationTicks -= 60;
			int sched = durationTicks % 60;
			new BukkitRunnable() {
				@Override
				public void run() {
					sendActionBar(player, message);
				}
			}.runTaskLater(Survival.instace, sched);
		}
	}

//	@SuppressWarnings("deprecation")
//	public static void changeChestState(Location loc, boolean open) {
//	    for (Player p : loc.getWorld().getPlayers()) {
//	        p.playNote(loc, (byte) 1, (byte) (open ? 1 : 0));
//	    }
//	}

	public static void runHelix(Location loc, List<Player> players) {

        double radius = 5;

        for (double y = 5; y >= 0; y -= 0.007) {
            radius = y / 3;
            double x = radius * Math.cos(3 * y);
            double z = radius * Math.sin(3 * y);

            double y2 = 5 - y;

            Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);
            //ParticleEffect.REDSTONE.display(loc2, 0, 0, 0, 0, 1);
            ParticleEffect.REDSTONE.display(0.0f, 0.0f, 0.0f, 1.0f, 1, loc2, players);
        }

        for (double y = 5; y >= 0; y -= 0.007) {
            radius = y / 3;
            double x = -(radius * Math.cos(3 * y));
            double z = -(radius * Math.sin(3 * y));

            double y2 = 5 - y;

            Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);
            ParticleEffect.REDSTONE.display(0.0f, 0.0f, 0.0f, 1.0f, 1, loc2, players);
        }

    }


}
