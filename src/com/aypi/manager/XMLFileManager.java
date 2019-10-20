package com.aypi.manager;

import java.util.ArrayList;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;
import com.aypi.utils.xml.balises.ClearFileBalise;
import com.aypi.utils.xml.balises.DeletFileBalise;
import com.aypi.utils.xml.balises.DeletLineBalise;
import com.aypi.utils.xml.balises.FalseBalise;
import com.aypi.utils.xml.balises.FileManagerBalise;
import com.aypi.utils.xml.balises.IDBalise;
import com.aypi.utils.xml.balises.IfBalise;
import com.aypi.utils.xml.balises.ItemBalise;
import com.aypi.utils.xml.balises.ListScriptAddBalise;
import com.aypi.utils.xml.balises.ListScriptBalise;
import com.aypi.utils.xml.balises.ListScriptRemoveBalise;
import com.aypi.utils.xml.balises.LocationBalise;
import com.aypi.utils.xml.balises.LogBalise;
import com.aypi.utils.xml.balises.MessageBalise;
import com.aypi.utils.xml.balises.MinecraftCommandBalise;
import com.aypi.utils.xml.balises.PlaceBlockBalise;
import com.aypi.utils.xml.balises.PlayerJoinBalise;
import com.aypi.utils.xml.balises.PlayerLeftBalise;
import com.aypi.utils.xml.balises.PlayerMoveBalise;
import com.aypi.utils.xml.balises.PrintLineBalise;
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
		mcbs.add(new PlayerMoveBalise());
			
		mcbs.add(new IfBalise());
		mcbs.add(new TrueBalise());
		mcbs.add(new FalseBalise());
		mcbs.add(new VariableBalise());
		mcbs.add(new WhileBalise());
		mcbs.add(new LogBalise());
		mcbs.add(new ListScriptBalise());
		mcbs.add(new ListScriptAddBalise());
		mcbs.add(new ListScriptRemoveBalise());
		
		mcbs.add(new MessageBalise());
		mcbs.add(new SoundBalise());
		mcbs.add(new LocationBalise());
		mcbs.add(new MinecraftCommandBalise());
		mcbs.add(new PlaceBlockBalise());
		mcbs.add(new FileManagerBalise());
		mcbs.add(new PrintLineBalise());
		mcbs.add(new DeletLineBalise());
		mcbs.add(new ClearFileBalise());
		mcbs.add(new DeletFileBalise());
		mcbs.add(new ItemBalise());
		mcbs.add(new IDBalise());
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
