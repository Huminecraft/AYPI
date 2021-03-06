package com.aypi.utils.xml.balises;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;
import com.aypi.utils.xml.script.Variable;

public class LocationBalise extends MCBalise {

	public static final String NAME = "location";
	
	private Location location;
	
	private String sx, sy, sz, sworld;
	
	private double x, y, z;
	private String world;
	
	private boolean playerloc;
	
	public LocationBalise(){
		super(NAME);
		playerloc = false;
	}
	
	public LocationBalise(String overrideName){
		super(overrideName);
		playerloc = false;
	}
	
	public Location GetLocation()
	{
		return location;
	}
	
	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {
		
		Node nWorld = namedNodeMap.getNamedItem("world");
		Node nX = namedNodeMap.getNamedItem("x");
		Node nY = namedNodeMap.getNamedItem("y");
		Node nZ = namedNodeMap.getNamedItem("z");
		
		Node playerLoc = namedNodeMap.getNamedItem("player");
		
		if (nWorld != null && nX != null && nY != null && nZ != null) {
			
			sworld = nWorld.getNodeValue();
			sx = nX.getNodeValue();
			sy = nY.getNodeValue();
			sz = nZ.getNodeValue();
			
		} else if (playerLoc != null){
			playerloc = playerLoc.getNodeValue().equalsIgnoreCase("true"); 
		} else {
			System.out.println("[AYPI] : ERROR argument for location balise...");
		}
		
	}

	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		
		if (playerloc) {
			
			location = player.getLocation();
			
			world = location.getWorld().getName();
			x = location.getX();
			y = location.getY();
			z = location.getZ();
			
		} else {
			
			world = getString(xmlFile.getScriptManager().compile(sworld, 0), xmlFile);
			x = Double.parseDouble(xmlFile.getScriptManager().compile(sx, 0));
			y = Double.parseDouble(xmlFile.getScriptManager().compile(sy, 0));
			z = Double.parseDouble(xmlFile.getScriptManager().compile(sz, 0));	
			
			location = new Location(Bukkit.getWorld(world), x, y, z);
		}
		
		xmlFile.getScriptManager().addVariable(new Variable("%WORLD%", "'"+world+"'"));
		
		xmlFile.getScriptManager().addVariable(new Variable("%X%", ""+x));
		xmlFile.getScriptManager().addVariable(new Variable("%Y%", ""+y));
		xmlFile.getScriptManager().addVariable(new Variable("%Z%", ""+z));
		
		for (MCBalise mcBalise : getChildrens()) {
			if (mcBalise instanceof LocationBaliseAdaptor) {
				((LocationBaliseAdaptor) mcBalise).setLocation(location);
			}
			mcBalise.directExecute(player, xmlFile);
		}
	}

	@Override
	public MCBalise getInstance() {
		return new LocationBalise();
	}

}
