package com.aypi.manager;

import java.util.ArrayList;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;

public class XMLFileManager {
	
	private ArrayList<XMLFile> xmls;
	private ArrayList<MCBalise> mcbs;
	
	public XMLFileManager() {
		xmls = new ArrayList<XMLFile>();
		mcbs = new ArrayList<MCBalise>();
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
