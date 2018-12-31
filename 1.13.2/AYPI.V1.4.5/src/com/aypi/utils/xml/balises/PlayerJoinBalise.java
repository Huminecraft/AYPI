package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;
import com.aypi.utils.xml.script.Variable;

public class PlayerJoinBalise extends MCBalise {

	public final static String NAME = "on-player-join";
	
	public PlayerJoinBalise() {
		super(NAME);
	}

	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		xmlFile.getScriptManager().addVariable(new Variable("%PLAYER%", player.getName()));
		for (MCBalise mcb : getChildrens()) {
			mcb.execute(player, xmlFile);
		}
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {
		
	}

	@Override
	public MCBalise getInstance() {
		return new PlayerJoinBalise();
	}
	
}
