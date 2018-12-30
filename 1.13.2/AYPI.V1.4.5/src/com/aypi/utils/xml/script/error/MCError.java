package com.aypi.utils.xml.script.error;

public class MCError {
	
	private String name;
	private int num;
	private String description;
	
	public MCError(String name, int num, String description) {
		this.name = name;
		this.num = num;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNum() {
		return num;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void display(int line, String code) {
		System.out.println("-----------------#ERROR#-----------------");
		System.out.println("Error "+name+" | num: "+num+" | line: "+line);
		System.out.println("code: "+code);
		System.out.println(description);
		System.out.println("-----------------------------------------");
	}

}
