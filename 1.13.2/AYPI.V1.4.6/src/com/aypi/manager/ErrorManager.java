package com.aypi.manager;

import java.util.ArrayList;

import com.aypi.utils.xml.script.error.MCError;

public class ErrorManager {
	
	private ArrayList<MCError> errors;
	
	public ErrorManager() {
		this.errors = new ArrayList<MCError>();
		
		errors.add(new MCError("Math operator ", 0, "when you use a math operator you need to have two arguments of type 'NUMBER' or 'STRING' if the operator is '+'. One before and one after..."));
		errors.add(new MCError("so many ')'", 1, "you have so many brackets epurate your code !"));
		errors.add(new MCError("close your 'STRING' type", 2, "you forget a ' symbole in your code."));
		errors.add(new MCError("'STRING' type fuse", 3, "Your programme make the fusion between two aknow type please change your code."));
		errors.add(new MCError("char '\\' ", 4, "the char '\\' take one arg after him. please change your code..."));
		errors.add(new MCError("Math Operator", 5, "a math operator can compare only two arg of 'NUMBER' type."));
		errors.add(new MCError("Boolean operator", 6, "a boolean operator need two args of 'BOOLEAN' type..."));
		errors.add(new MCError("Operator not exist", 7, "hum... what is this ?"));
		errors.add(new MCError("don't divide by 0", 8, "you can't divide by 0"));
		errors.add(new MCError("unknown arguments", 9, "what is this ?"));
		errors.add(new MCError("'Material' not found", 10, "the 'Material' should be a block"));
		
		errors.add(new MCError("List not dound", 11, "your list not exists."));
		errors.add(new MCError("out of band", 12, "this index is out of correct size."));
	}
	
	public ArrayList<MCError> getErrors() {
		return errors;
	}
	
	public MCError getError(int num) {
		for (MCError e : errors) {
			if (e.getNum() == num) {
				return e;
			}
		}
		return null;
	}
	
	public MCError getError(String name) {
		for (MCError e : errors) {
			if (e.getName().equalsIgnoreCase(name)) {
				return e;
			}
		}
		return null;
	}
	
	public void addError(MCError error) {
		errors.add(error);
	}
	
	public void removeError(MCError error) {
		errors.remove(error);
	}

}
