package com.aypi.utils.xml.script;

public enum LogicOperator {

	AND, OR, NOT, XOR;
	
	static LogicOperator getOperator(String operateur) {
		if (operateur.equalsIgnoreCase(AND.toString()))
			return AND;
		else if (operateur.equalsIgnoreCase(OR.toString()))
			return OR;
		else if (operateur.equalsIgnoreCase(NOT.toString()))
			return NOT;
		else if (operateur.equalsIgnoreCase(XOR.toString()))
			return XOR;
		else
			return null;
	}
}
