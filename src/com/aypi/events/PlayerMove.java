package com.aypi.events;

import org.bukkit.Chunk;
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
		if (e.getTo().distance(e.getFrom()) == 0)
		{
			return;
		}
		//To optimize, just update the zones when we find a new chunk 
		if (e.getFrom().getChunk() == e.getTo().getChunk())
		{
			return;
		}
		
		Chunk chunk = e.getTo().getChunk();
		Player player = e.getPlayer();
		//WARNING WHEN THE CHUNK WILL DEPENDS ON THE Y TOO
		//Location loc = new Location(c.getWorld(), c.getX()*16, 64, c.getZ()*16);
		Location oldLoc = e.getFrom();
		Location newLoc = e.getTo();
		ZonePriorityBuffer zpb = new ZonePriorityBuffer();
		
		boolean isInZone = false;		
		
		for (Zone zone : Aypi.getZoneManager().getZones()) {
			if (zone.containLocation(newLoc))
			{
				zpb.addZone(zone);
				isInZone = true;
			}
		}
		
		if (!isInZone)
		{
			for (Zone zone : Aypi.getZoneManager().getZones())
			{
				if (zone.entityListContainPlayer(player))
				{
					zone.getZoneListener().onPlayerExitZone(player);
					zone.removeEntity(player);	
				}
			}		
		}
		
		for (Zone zone : zpb.getPriorityZones())
		{
			zone.getZoneListener().onPlayerMoveInZone(player, e);
			if (!zone.entityListContainPlayer(player))
			{
				zone.addEntity(player);
				zone.getZoneListener().onPlayerEnterZone(player);
			}
		}
		
	}

}
