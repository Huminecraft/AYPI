package com.aypi.manager;

import java.util.ArrayList;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;
import com.aypi.utils.xml.balises.FalseBalise;
import com.aypi.utils.xml.balises.IfBalise;
import com.aypi.utils.xml.balises.LocationBalise;
import com.aypi.utils.xml.balises.LogBalise;
import com.aypi.utils.xml.balises.MessageBalise;
import com.aypi.utils.xml.balises.PlayerJoinBalise;
import com.aypi.utils.xml.balises.PlayerLeftBalise;
import com.aypi.utils.xml.balises.SoundBalise;
import com.aypi.utils.xml.balises.StartServerBalise;
import com.aypi.utils.xml.balises.StopServerBalise;
import com.aypi.utils.xml.balises.TrueBalise;
import com.aypi.utils.xml.balises.VariableBalise;
import com.aypi.utils.xml.balises.WhileBalise;

public class XMLFileManager {
	
	private ArrayList<XMLFile> xmls;
	private ArrayList<MCBalise> mcbs;
	
	public XMLFileManager() {
		xmls = new ArrayList<XMLFile>();
		mcbs = new ArrayList<MCBalise>();
		
		////////////////////////////
		mcbs.add(new StartServerBalise());
		mcbs.add(new StopServerBalise());
		mcbs.add(new PlayerJoinBalise());
		mcbs.add(new PlayerLeftBalise());
		
		mcbs.add(new MessageBalise());
		mcbs.add(new SoundBalise());
		mcbs.add(new LocationBalise());
		mcbs.add(new LogBalise());
		mcbs.add(new IfBalise());
		mcbs.add(new TrueBalise());
		mcbs.add(new FalseBalise());
		mcbs.add(new VariableBalise());
		mcbs.add(new WhileBalise());
	}
	
	public void addXMLFile(XMLFile xmlFile) {
		xmls.add(xmlFile);
	}
	
	public void removeXMLFile(XMLFile xmlFile) {
		xmls.remove(xmlFile);
	}
	
	public ArrayList<XMLFile> getXMLFile() {
		return xmls;
	}
	
	public void addMCBalise(MCBalise mcBalise) {
		mcbs.add(mcBalise);
	}
	
	public void removeMCBalise(MCBalise mcBalise) {
		mcbs.remove(mcBalise);
	}
	
	public ArrayList<MCBalise> getMCBalises() {
		return mcbs;
	}
	
	public boolean baliseExist(String name) {
		for (MCBalise mb : mcbs) {
			if (mb.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	public MCBalise getMCBalise(String name) {
		for (MCBalise mb : mcbs) {
			if (mb.getName().equalsIgnoreCase(name)) {
				return mb;
			}
		}
		return null;
	}

}
