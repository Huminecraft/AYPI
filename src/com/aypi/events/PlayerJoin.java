package com.aypi.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.aypi.Aypi;
import com.aypi.utils.Zone;
import com.aypi.utils.ZonePriorityBuffer;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerJoinEvent e)
	{

		Player player = e.getPlayer();
		Location loc = player.getLocation();

		for (Zone zone : Aypi.getZoneManager().getZones())
		{
			if (zone.containLocation(loc))
			{
				zone.addEntity(player);				
			}
		}
	}
}
