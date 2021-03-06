package com.aypi.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.aypi.Aypi;
import com.aypi.utils.Zone;
import com.aypi.utils.ZonePriorityBuffer;

public class PlayerInteract implements Listener {
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		Player player = e.getPlayer();

		Location loc;
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			loc = e.getClickedBlock().getLocation();
		} else {
			loc = player.getLocation();
		}
		ZonePriorityBuffer zpb = new ZonePriorityBuffer();
		
		for (Zone zone : Aypi.getZoneManager().getZones()) {
			if (zone.containLocation(new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()))) {
				zpb.addZone(zone);
			}
		}
		
		for (Zone zone : zpb.getPriorityZones()) {
			zone.getZoneListener().onPlayerTryToInteractEvent(player, e);
		}
	}

}
