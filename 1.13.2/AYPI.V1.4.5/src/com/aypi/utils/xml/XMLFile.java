package com.aypi.utils.xml;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.entity.HumanEntity;

import com.aypi.Aypi;

public class XMLFile {
	
	private File file;
	private ArrayList<MCBalise> mcBalises;
	
	public XMLFile(File file) {
		this.file = file;
		this.mcBalises = new ArrayList<MCBalise>();
		Aypi.getXMLFileManager().addXMLFile(this);
	}
	
	public void load() {
		
		
		
	}
	
	public void destroy() {
		while (mcBalises.size() != 0) {
			mcBalises.remove(0);
		}
		Aypi.getXMLFileManager().removeXMLFile(this);
	}
	
	public ArrayList<MCBalise> getMCBalises() {
		return mcBalises;
	}
	
	public File getFile() {
		return file;
	}

}
