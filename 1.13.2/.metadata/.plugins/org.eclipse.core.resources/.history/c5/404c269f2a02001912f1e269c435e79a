package com.aypi.utils.xml;

import org.bukkit.entity.Player;

import com.aypi.Aypi;

public abstract class MCBalise {
	
	private String name;
	private String[] args;
	
	public MCBalise(String name, String[] args) {
		this.name = name;
		this.args = args;
		Aypi.getXMLFileManager().addMCBalise(this);
	}
	
	public abstract void load();
	public abstract void load(Player player);
	
	public String getName() {
		return name;
	}
	
	public String[] getArguments() {
		return this.args;
	}
	
}
