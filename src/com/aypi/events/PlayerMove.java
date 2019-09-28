package com.aypi.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.aypi.Aypi;
import com.aypi.utils.Zone;
import com.aypi.utils.ZonePriorityBuffer;

public class PlayerMove implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e)
	{
		//To optimize, just update the zones when we find a new chunk 
		if (e.getFrom().getChunk() == e.getTo().getChunk())
		{
			return;
		}
		
		Player player = e.getPlayer();
		Location loc = player.getLocation();
		ZonePriorityBuffer zpb = new ZonePriorityBuffer();
		
		boolean isInZone = false;		
		
		for (Zone zone : Aypi.getZoneManager().getZones()) {
			if (zone.containLocation(loc)) {
				zpb.addZone(zone);
				isInZone = true;
			}
		}
		
		if (!isInZone) {
			for (Zone zone : Aypi.getZoneManager().getZones()) {
				if (zone.entityListContainPlayer(player)) {
					zone.getZoneListener().onPlayerExitZone(player);
					zone.removeEntity(player);
				}
			}
		}
		
		for (Zone zone : zpb.getPriorityZones()) {
			zone.getZoneListener().onPlayerMoveInZone(player, e);
			if (!zone.entityListContainPlayer(player)) {
				zone.addEntity(player);
				zone.getZoneListener().onPlayerEnterZone(player);
			}
		}
		
	}

}
