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
<<<<<<< HEAD
		String line = getString(xmlFile.getScriptManager().compile(getContent(), 0), xmlFile);
=======
		String line = getContent();
>>>>>>> 7b190cf4b734a683ddc37f1c72d8123ac7af3e0d
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
