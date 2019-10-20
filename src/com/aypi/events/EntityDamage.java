package com.aypi.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.aypi.Aypi;
import com.aypi.utils.Zone;
import com.aypi.utils.ZonePriorityBuffer;

public class EntityDamage implements Listener {
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		
		Entity entity = e.getEntity();

		Location loc = entity.getLocation();
		ZonePriorityBuffer zpb = new ZonePriorityBuffer();
		
		for (Zone zone : Aypi.getZoneManager().getZones()) {
			if (zone.containLocation(new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()))) {
				zpb.addZone(zone);
			}
		}
		
		for (Zone zone : zpb.getPriorityZones()) {
			zone.getZoneListener().onDamage(entity, e);
		}
		
	}
	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e)
	{
		Entity entity = e.getEntity();
		Entity damager = e.getDamager();
		if (damager instanceof Projectile && ((Projectile)damager).getShooter() instanceof Entity)
		{
			System.out.println("LE DAMAGER EST UN PROJECTILE, la source est " + ((Projectile)damager).getShooter() );
			damager = (Entity) ((Projectile)damager).getShooter();
		}
		
		System.out.println("LE DAMAGER EST " + damager);
		Location loc = entity.getLocation();
		ZonePriorityBuffer zpbForEntityGetDamage = new ZonePriorityBuffer();
		ZonePriorityBuffer zpbForEntityMakeDamage = new ZonePriorityBuffer();
		
		for (Zone zone : Aypi.getZoneManager().getZones())
		{
			if (zone.containLocation(loc))
			{
				zpbForEntityGetDamage.addZone(zone);
			}
			if (zone.containLocation(damager.getLocation()))
			{
				zpbForEntityMakeDamage.addZone(zone);
			}
		}
		
		for (Zone zone : zpbForEntityGetDamage.getPriorityZones())
		{
			zone.getZoneListener().onEntitySuffersDamages(entity, e);
		}
		
		for (Zone zone : zpbForEntityGetDamage.getPriorityZones())
		{
			zone.getZoneListener().onEntityMakesDamages(entity, e);
		}
	}

}
