package com.aypi.utils.xml;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;

import java.util.ArrayList;

public abstract class MCBalise {
	
	private String name;
	private String content;
	
	private ArrayList<MCBalise> mcbs;
	
	public MCBalise(String name) {
		this.name = name;
		this.mcbs = new ArrayList<MCBalise>();
	}
	
	public abstract void setUpAttributes(NamedNodeMap namedNodeMap);
	
	public abstract void execute(Player player);
	
	public String getName() {
		return name;
	}
	
	public void setContent(String value) {		
		this.content = value;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void addChildren(MCBalise mcb) {
		this.mcbs.add(mcb);
	}
	
	public ArrayList<MCBalise> getChildrens() {
		return this.mcbs;
	}
	
	public boolean haveChildren() {
		return mcbs.size() > 0;
	}
	
}
