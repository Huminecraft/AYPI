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
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {
	}

	@Override
	public void customExecute(Player player) {
		String line = getContent();
		if (player != null) {
			line = line.replaceAll("%PLAYER%", player.getName());
		}
		
		System.out.println("[MCXML]: "+line);
	}
	
	@Override
	public MCBalise getInstance() {
		return new LogBalise();
	}

}
