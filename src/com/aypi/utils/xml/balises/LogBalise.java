package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;

public class LogBalise extends MCBalise {

	public static final String NAME = "console-log";
	
	public LogBalise() {
		super(NAME);
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {
	}

	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		String line = getString(xmlFile.getScriptManager().compile(getContent(), 0), xmlFile);
		if (player != null) {
			line = line.replaceAll("%PLAYER%", player.getName());
		}
		
		System.out.println(line);
	}
	
	@Override
	public MCBalise getInstance() {
		return new LogBalise();
	}

}
