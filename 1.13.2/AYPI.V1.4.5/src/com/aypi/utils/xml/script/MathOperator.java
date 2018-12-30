package com.aypi.utils.xml.script;

public enum MathOperator {
	
	ADDITION, SUBSTRACTION, MULTIPLICATION, DIVISION, 
	EQUALS, SUPERIOR, INFERIOR, SUPERIOR_OR_EQUALS, INFERIOR_OR_EQUALS;
	
	static MathOperator getOperator(String op) {
		if (op.equalsIgnoreCase("+"))
			return ADDITION;
		else if (op.equalsIgnoreCase("-"))
			return SUBSTRACTION;
		else if (op.equalsIgnoreCase("*"))
			return MULTIPLICATION;
		else if (op.equalsIgnoreCase("/"))
			return DIVISION;
		if (op.equalsIgnoreCase("eq"))
			return EQUALS;
		else if (op.equalsIgnoreCase("gt"))
			return SUPERIOR;
		else if (op.equalsIgnoreCase("lt"))
			return INFERIOR;
		else if (op.equalsIgnoreCase("goe"))
			return SUPERIOR_OR_EQUALS;
		else if (op.equalsIgnoreCase("loe"))
			return INFERIOR_OR_EQUALS;
		else
			return null;
	}
	
	static boolean isComparator(MathOperator operator) {
		if (operator == ADDITION || operator == MathOperator.SUBSTRACTION || operator == MULTIPLICATION || operator == MathOperator.DIVISION)
			return false;
		else
			return true;
	}
	
	static boolean isComparator(String operator) {
		return isComparator(getOperator(operator));
	}

}
