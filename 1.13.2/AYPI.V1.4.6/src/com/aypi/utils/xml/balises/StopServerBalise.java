package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;

public class StopServerBalise extends MCBalise {

	public static final String NAME = "on-server-stop";
	
	public StopServerBalise() {
		super(NAME);
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {
		
	}

	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		for (MCBalise mcBalise : getChildrens()) {
			mcBalise.execute(player, xmlFile);
		}
	}
	
	@Override
	public MCBalise getInstance() {
		return new StopServerBalise();
	}
	
	

}
