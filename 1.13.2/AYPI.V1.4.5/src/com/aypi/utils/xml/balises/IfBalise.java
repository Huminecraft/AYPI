package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;

public class IfBalise extends MCBalise {
	
	public static final String NAME = "if";
	
	private String condition;
	
	public IfBalise() {
		super(NAME);
		this.condition = "";
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {
		Node node = namedNodeMap.getNamedItem("condition");
		if (node != null) {
			condition = node.getNodeValue();
		}
	}

	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		boolean isValide = xmlFile.getScriptManager().compile(condition, 0).equalsIgnoreCase("true");
		for (MCBalise children : getChildrens()) {
			if (children instanceof TrueBalise) {
				if (isValide) {
					children.execute(player, xmlFile);
				}
					
			}
			
			if (children instanceof FalseBalise) {
				if (!isValide)
					children.execute(player, xmlFile);
			}
		}
	}

	@Override
	public MCBalise getInstance() {
		return new IfBalise();
	}

}
