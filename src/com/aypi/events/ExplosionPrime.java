package com.aypi.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import com.aypi.Aypi;
import com.aypi.utils.Zone;
import com.aypi.utils.ZonePriorityBuffer;

public class ExplosionPrime implements Listener {
	
	@EventHandler
	public void onExplosionPrime(ExplosionPrimeEvent e)
	{		
		Entity entity = e.getEntity();

		ZonePriorityBuffer zpb = new ZonePriorityBuffer();
		
		for (Zone zone : Aypi.getZoneManager().getZones())
		{
			if (zone.containLocation(entity.getLocation()))
			{
				zpb.addZone(zone);
			}
		}
		
		for (Zone zone : zpb.getPriorityZones())
		{
			if (zone != null && zone.getZoneListener() != null)
			{
				zone.getZoneListener().onExplosionPrime(e);
			}
		}
	}
}
