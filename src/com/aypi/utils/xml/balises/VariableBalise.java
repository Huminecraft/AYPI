package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;
import com.aypi.utils.xml.script.Variable;

public class VariableBalise extends MCBalise {

	public static final String NAME = "variable";
	
	private String name;
	
	public VariableBalise() {
		super(NAME);
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {
		
		Node nName = namedNodeMap.getNamedItem("name");
		
		if (nName == null) {
			System.out.println("Error <variable> need a 'name'.");
			return;
		}
		
		name = nName.getNodeValue();
		
		if (name.equalsIgnoreCase("")) {
			System.out.println("Error <variable> need a 'name'.");
			return;
		}
	}

	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		
		if (xmlFile.getScriptManager().isVariable(name)) {
			xmlFile.getScriptManager().getVariable(name).setValue(getContent());
		} else {
			Variable variable = new Variable(name, getContent());
			xmlFile.getScriptManager().addVariable(variable);
		}
		
	}

	@Override
	public MCBalise getInstance() {
		return new VariableBalise();
	}

}
