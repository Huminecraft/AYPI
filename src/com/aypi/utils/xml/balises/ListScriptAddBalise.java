package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.aypi.Aypi;
import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;
import com.aypi.utils.xml.script.ListScript;
import com.aypi.utils.xml.script.Variable;

public class ListScriptAddBalise extends MCBalise {

	public static final String NAME = "list-add";
	
	private String name;
	
	public ListScriptAddBalise() {
		super(NAME);
		name = "";
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {
		Node name = namedNodeMap.getNamedItem("name");
		if (name != null) {
			this.name = name.getNodeValue();
		}
	}

	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		ListScript ls = xmlFile.getScriptManager().getListScript(name);
		if (ls != null) {
			ls.addValues(getContent());
			xmlFile.getScriptManager().addVariable(new Variable("%"+name.toUpperCase()+"_LENGTH%", ""+ls.getValues().size()));
		
		} else {
			Aypi.getErrorManager().getError(11).display(0, name);
		}
	}

	@Override
	public MCBalise getInstance() {
		return new ListScriptAddBalise();
	}

}
