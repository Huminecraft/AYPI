package com.aypi.utils.xml.balises;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;

public class MinecraftCommandBalise extends MCBalise {
	
	public static final String NAME = "minecraft-command";

	public MinecraftCommandBalise() {
		super(NAME);
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap) {}

	@Override
	public void customExecute(Player player, XMLFile xmlFile) {
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getString(xmlFile.getScriptManager().compile(getContent(), 0), xmlFile));
	}

	@Override
	public MCBalise getInstance() {
		return new MinecraftCommandBalise();
	}

}
