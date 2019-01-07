package com.aypi.utils.xml.script;

public enum LogicOperator {

	AND, OR, NOT, NULL;
	
	static LogicOperator getOperator(String operateur) {
		if(operateur.equalsIgnoreCase(AND.toString()))
			return AND;
		else if(operateur.equalsIgnoreCase(OR.toString()))
			return OR;
		else if(operateur.equalsIgnoreCase(NULL.toString()))
			return OR;
		else
			return NULL;
	}
}
