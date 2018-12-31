package com.aypi.utils.xml;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.aypi.utils.Timer;
import com.aypi.utils.inter.TimerFinishListener;

public abstract class MCBalise {
	
	private String name;
	private String content;
	private XMLFile xmlFile;
	private String id;
	private int line;
	
	private double delay;
	
	private ArrayList<MCBalise> mcbs;
	
	public MCBalise(String name) {
		this.name = name;
		this.delay = 1;
		this.mcbs = new ArrayList<MCBalise>();
	}
	
	public void setUpAttributes(NamedNodeMap namedNodeMap) {
		
		Node node = namedNodeMap.getNamedItem("delay");
		Node id = namedNodeMap.getNamedItem("id");
		
		if (node != null) {
			String value = node.getNodeValue();
			this.delay = Double.parseDouble(value);
		}
		
		if (id != null) {
			String value = id.getNodeValue();
			this.id = value;
		}
		
		setUpCustomAttributes(namedNodeMap);
	}
	
	public abstract void setUpCustomAttributes(NamedNodeMap namedNodeMap);
	
	public void execute(Player player, XMLFile xmlFile) {
		
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Aypi");
		
		if (plugin.isEnabled()) {
			new Timer(plugin, (int) this.delay, new TimerFinishListener() {
				
				@Override
				public void execute() {
					
					customExecute(player, xmlFile);
					
				}
				
<<<<<<< HEAD
			}, false).start();
=======
			}).start();
>>>>>>> 7b190cf4b734a683ddc37f1c72d8123ac7af3e0d
		} else {
			customExecute(player, xmlFile);
		}
		
	}
	
	public abstract void customExecute(Player player, XMLFile xmlFile);
	
	public abstract MCBalise getInstance();
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setContent(String value, XMLFile xmlFile, int line) {		
		this.content = value;
		this.xmlFile = xmlFile;
		this.line = line;
	}
	
	public String getContent() {
		return xmlFile.getScriptManager().compile(this.content, line);
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
	
	protected String getString(String string, XMLFile xmlFile) {
		
		if (xmlFile.getScriptManager().isString(string)) {
			
			char[] tab = string.toCharArray();
			
			string = new String(tab, 1, string.length() - 2);
			
			return string;
			
		}
		
		return null;
	}
	
}
