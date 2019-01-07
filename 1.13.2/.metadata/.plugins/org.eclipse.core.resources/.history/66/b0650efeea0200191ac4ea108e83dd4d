package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;

import com.aypi.utils.xml.MCBalise;

public class LogBalise extends MCBalise {

	public static final String NAME = "console-log";
	
	public LogBalise() {
		super(NAME);
	}

	@Override
	public void setUpAttributes(NamedNodeMap namedNodeMap) {
	}

	@Override
	public void execute(Player player) {
		if (player != null) {
			setContent(getContent().replaceAll("%PLAYER%", player.getName()));
		}
		
		System.out.println("[MCXML]: "+getContent());
	}
	
	@Override
	public MCBalise getInstance() {
		return new LogBalise();
	}

}
