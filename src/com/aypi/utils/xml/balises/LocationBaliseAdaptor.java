package com.aypi.utils.xml.balises;

import org.bukkit.Location;

import com.aypi.utils.xml.MCBalise;

public abstract class LocationBaliseAdaptor extends MCBalise {

	public LocationBaliseAdaptor(String name) {
		super(name);
	}
	
	public abstract void setLocation(Location location);
	public abstract Location getLocation();
	
}
