package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;

import com.aypi.utils.xml.MCBalise;

public class PlayerLeftBalise extends MCBalise {

	public static final String NAME = "on-player-left";
	
	public PlayerLeftBalise() {
		super(NAME);
		
	}

	@Override
	public void setUpAttributes(NamedNodeMap namedNodeMap) {
		
	}

	@Override
	public void execute(Player player) {
		
		for (MCBalise mcBalise : getChildrens()) {
			mcBalise.execute(player);
		}
		
	}
	
	@Override
	public MCBalise getInstance() {
		return new PlayerLeftBalise();
	}

}
