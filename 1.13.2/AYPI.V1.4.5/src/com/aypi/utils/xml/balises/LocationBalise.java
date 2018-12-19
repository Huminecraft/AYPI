package com.aypi.utils.xml.balises;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.aypi.utils.xml.MCBalise;

public class LocationBalise extends MCBalise {

	public static final String NAME = "location";
	
	private Location location;
	
	public LocationBalise() {
		super(NAME);
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {
		
		Node nWorld = namedNodeMap.getNamedItem("world");
		Node nX = namedNodeMap.getNamedItem("x");
		Node nY = namedNodeMap.getNamedItem("y");
		Node nZ = namedNodeMap.getNamedItem("z");
		
		if (nWorld != null && nX != null && nY != null && nZ != null) {
			
			location = new Location(
				Bukkit.getWorld(nWorld.getNodeValue()),
				Double.parseDouble(nX.getNodeValue()),
				Double.parseDouble(nY.getNodeValue()),
				Double.parseDouble(nZ.getNodeValue())
			);
			
		} else {
			System.out.println("[AYPI] : ERROR argument for location balise...");
		}
		
	}

	@Override
	public void customExecute(Player player) {
		for (MCBalise mcBalise : getChildrens()) {
			if (mcBalise instanceof LocationBaliseAdaptor) {
				((LocationBaliseAdaptor) mcBalise).setLocation(location);
				mcBalise.execute(player);
			}
		}
	}

	@Override
	public MCBalise getInstance() {
		return new LocationBalise();
	}

}