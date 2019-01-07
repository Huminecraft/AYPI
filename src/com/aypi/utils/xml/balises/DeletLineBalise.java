package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;
import com.aypi.utils.xml.script.ListScript;
import com.aypi.utils.xml.script.Variable;

public class DeletLineBalise extends FileManagerBaliseAdaptor {

	public static final String NAME = "delet-line";
	
	private String allS;
	private boolean all;
	
	public DeletLineBalise() {
		super(NAME);
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {
		Node all = namedNodeMap.getNamedItem("all");
		if (all != null) {
			allS = all.getNodeValue();
		} else {
			allS = "false";
		}
	}

	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		all = xmlFile.getScriptManager().getBoolean(xmlFile.getScriptManager().compile(allS, 0));
		if (all) {
			getFileManager().removeAllLine(getString(xmlFile.getScriptManager().compile(getContent(), 0), xmlFile));
		} else {
			getFileManager().removeLine(getString(xmlFile.getScriptManager().compile(getContent(), 0), xmlFile));
		}
		ListScript ls = new ListScript("%LINES_LIST%");
		for (String str : getFileManager().getTextFile()) {
			ls.addValues("'"+str+"'");
		}
		xmlFile.getScriptManager().addListScript(ls);
		xmlFile.getScriptManager().addVariable(new Variable("%LINES_LIST_LENGTH%", ""+ls.getValues().size()));
	}


	@Override
	public MCBalise getInstance() {
		return new DeletLineBalise();
	}

}
