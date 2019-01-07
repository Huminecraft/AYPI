package com.aypi.utils.xml.balises;

import com.aypi.manager.FileManager;
import com.aypi.utils.xml.MCBalise;

public abstract class FileManagerBaliseAdaptor extends MCBalise {
	
	private FileManager fileManager;
	
	public FileManagerBaliseAdaptor(String name) {
		super(name);
		fileManager = null;
	}
	
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}

}
