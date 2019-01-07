package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;

public class WhileBalise extends MCBalise {

	public static final String NAME = "while";
	
	private String condition;
	
	public WhileBalise() {
		super(NAME);
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {
		Node condition = namedNodeMap.getNamedItem("condition");
		if (condition != null) {
			this.condition = condition.getNodeValue();
		} else {
			System.out.println("Error when you use a <while> balise you should to use the 'condition' atribute...");
		}
	}

	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		boolean continueLoop = xmlFile.getScriptManager().compile(condition, 0).equalsIgnoreCase("true");
		
		if (continueLoop) {
			for (MCBalise balise : getChildrens()) {
				balise.execute(player, xmlFile);
			}
			this.execute(player, xmlFile);
		}
	}

	@Override
	public MCBalise getInstance() {
		return new WhileBalise();
	}

}
