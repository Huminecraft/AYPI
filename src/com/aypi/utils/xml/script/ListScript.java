package com.aypi.utils.xml.script;

import java.util.ArrayList;

public class ListScript {
	
	private String name;
	private ArrayList<String> values;
	
	public ListScript(String name) {
		this.name = name;
		values = new ArrayList<String>();
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public ArrayList<String> getValues() {
		return values;
	}
	
	public void addValues(String value) {
		values.add(value);
	}
	
	public void removeValues(String value) {
		values.remove(value);
	}
	
	public void setValues(ArrayList<String> as) {
		values = as;
	}

}
