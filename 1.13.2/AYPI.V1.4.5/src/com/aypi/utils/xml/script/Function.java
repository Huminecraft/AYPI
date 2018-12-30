package com.aypi.utils.xml.script;

import java.util.ArrayList;

import com.aypi.utils.xml.MCBalise;

public class Function {
	
	private String name;
	private ArrayList<MCBalise> mcBalises;
	private ArrayList<Variable> vars;
	
	public Function(String name, ArrayList<MCBalise> mcBalises, ArrayList<String> varsName) {
		this.name = name;
		this.mcBalises = mcBalises;
		
		
		
	}
	
	public void execute(ArrayList<String> varsName) {
		
	}

}
