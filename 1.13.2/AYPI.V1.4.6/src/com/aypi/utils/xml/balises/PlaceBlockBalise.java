package com.aypi.utils.xml.balises;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;

public class PlaceBlockBalise extends LocationBaliseAdaptor {

	
	public static final String NAME = "place-block";
	
	private Location location;
	private boolean falling;
	
	public PlaceBlockBalise() {
		super(NAME);
		falling = false;
	}

	@Override
	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {

		Node falling = namedNodeMap.getNamedItem("falling");
		
		if (falling != null) {
			this.falling = falling.getNodeValue().equalsIgnoreCase("true");
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		ItemStack item = new ItemStack(Material.getMaterial(getString(getContent(), xmlFile)));
			
		if (falling) {
			location.getWorld().spawnFallingBlock(location, item.getType(), (byte) 0);
		} else {
			location.getWorld().getBlockAt(location).setType(item.getType());
		}
	}

	@Override
	public MCBalise getInstance() {
		return new PlaceBlockBalise();
	}
	
	

}
