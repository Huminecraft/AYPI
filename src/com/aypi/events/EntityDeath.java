package com.aypi.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.aypi.Aypi;
import com.aypi.utils.Zone;
import com.aypi.utils.ZonePriorityBuffer;

public class EntityDeath implements Listener  {

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e)
	{		
		Entity entity = e.getEntity();

		Location loc = entity.getLocation();
		ZonePriorityBuffer zpb = new ZonePriorityBuffer();
		
		if (!(entity instanceof Wither))//For now, used just for the wither so limit the check of each zones if it's not a wither to optimize for now
		{
			return;
		}
		
		for (Zone zone : Aypi.getZoneManager().getZones())
		{
			if (zone.containLocation(new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()))) {
				zpb.addZone(zone);
			}
		}
		
		for (Zone zone : zpb.getPriorityZones())
		{
			zone.getZoneListener().onEntityDeath(entity, e);
		}
	}
}
