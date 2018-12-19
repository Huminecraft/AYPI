package com.aypi.utils.xml.script;

public class Variable {

	private String name;
	private String value;
	private VariableType type;
	
	public Variable(String name, String value, VariableType type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public VariableType getType() {
		return this.type;
	}
	
}
