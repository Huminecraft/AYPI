package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;

import com.aypi.utils.xml.MCBalise;

public class StartServerBalise extends MCBalise {

	public static final String NAME = "on-server-start";
	
	public StartServerBalise() {
		super(NAME);
	}

	@Override
	public void setUpAttributes(NamedNodeMap namedNodeMap) {
		
	}

	@Override
	public void execute(Player player) {
		for (MCBalise mcBalise : getChildrens()) {
			mcBalise.execute(player);
			System.out.println(mcBalise.getName()+" "+mcBalise.getContent());
		}
	}
	
	@Override
	public MCBalise getInstance() {
		return new StartServerBalise();
	}
	

}
