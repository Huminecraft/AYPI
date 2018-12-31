package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;

public class FalseBalise extends MCBalise {

	public static final String NAME = "false";
	
	public FalseBalise() {
		super(NAME);
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {}

	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		for (MCBalise children : getChildrens()) {
			children.execute(player, xmlFile);
		}
	}

	@Override
	public MCBalise getInstance() {
		return new FalseBalise();
	}

}
