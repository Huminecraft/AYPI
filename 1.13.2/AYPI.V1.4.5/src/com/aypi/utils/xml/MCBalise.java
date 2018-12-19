package com.aypi.utils.xml;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.aypi.utils.Timer;
import com.aypi.utils.inter.TimerFinishListener;

public abstract class MCBalise {
	
	private String name;
	private String content;
	
	private double delay;
	
	private ArrayList<MCBalise> mcbs;
	
	public MCBalise(String name) {
		this.name = name;
		this.delay = 0;
		this.mcbs = new ArrayList<MCBalise>();
	}
	
	public void setUpAttributes(NamedNodeMap namedNodeMap) {
		
		Node node = namedNodeMap.getNamedItem("delay");
		
		if (node != null) {
			String value = node.getNodeValue();
			this.delay = Double.parseDouble(value);
		}
		
		setUpCustomAttributes(namedNodeMap);
	}
	
	public abstract void setUpCustomAttributes(NamedNodeMap namedNodeMap);
	
	public void execute(Player player) {
		new Timer(Bukkit.getPluginManager().getPlugin("Aypi"), (int) this.delay, new TimerFinishListener() {
			
			@Override
			public void execute() {
				
				customExecute(player);
				
			}
		}).start();
	}
	
	public abstract void customExecute(Player player);
	
	public abstract MCBalise getInstance();
	
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
