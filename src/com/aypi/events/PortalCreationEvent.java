package com.aypi.events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

import com.aypi.Aypi;
import com.aypi.utils.Zone;
import com.aypi.utils.ZonePriorityBuffer;

public class PortalCreationEvent implements Listener  {

	@EventHandler
	public void onPortalCreation(PortalCreateEvent e)
	{
		ZonePriorityBuffer zpb = new ZonePriorityBuffer();
		boolean foundZone = false;
		for (BlockState bs : e.getBlocks())
		{
			Location blockLocation = bs.getBlock().getLocation();
			for (Zone zone : Aypi.getZoneManager().getZones())
			{
				if (zone.containLocation(blockLocation))
				{
					zpb.addZone(zone);
					foundZone = true;
				}
			}
			if (foundZone)
			{
				//WARNING : We will have to think about the case where two zone are next to each other and have not the same behavior
				break; //no need to continue to check other block : optimisation
			}
		}
		
		for (Zone zone : zpb.getPriorityZones())
		{
			zone.getZoneListener().onPortalCreate(e);
		}	
	}
}
