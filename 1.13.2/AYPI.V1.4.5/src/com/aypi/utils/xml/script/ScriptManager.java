package com.aypi.utils.xml.script;

import java.util.ArrayList;

public class ScriptManager {
	
	private ArrayList<Variable> vs;
	
	public ScriptManager() {
		this.vs = new ArrayList<Variable>();
		
		//test
	}
	
	//NUMBER
	public double mathOperator(Variable v1, Variable v2, MathOperator operator) {
		double product = 0;
		if (v1.getType() == VariableType.NUMBER && v2.getType() == VariableType.NUMBER) {
			
			mathComparator(Double.parseDouble(v1.getValue()), Double.parseDouble(v2.getValue()), operator);
		
		} else {
			System.out.println("Error operator "+v1.getType()+" "+v2.getType()+" not compatible");
		}
		return product;
	}
	
	public double mathOperator(double v1, double v2, MathOperator operator) {
		double product = 0;
		if (operator == MathOperator.ADDITION)
			product = v1 + v2;
		else if (operator == MathOperator.SUBSTRACTION)
			product = v1 - v2;
		else if (operator == MathOperator.MULTIPLICATION)
			product = v1 * v2;
		else if (operator == MathOperator.DIVISION)
			product = v1 / v2;
		else
			System.out.println("Error operator "+operator+" not compatible");
		return product;
	}
	
	
	
	public boolean mathComparator(Variable v1, Variable v2, MathOperator operator) {
		
		if (v1.getType() == VariableType.NUMBER && v2.getType() == VariableType.NUMBER) {
			
			return mathComparator(Double.parseDouble(v1.getValue()), Double.parseDouble(v2.getValue()), operator);
		
		} else {
			System.out.println("Error operator "+v1.getType()+" "+v2.getType()+" not compatible");
			return false;
		}
	}
	
	public boolean mathComparator(double v1, double v2, MathOperator operator) {
		
		if (operator == MathOperator.EQUALS)
			return v1 == v2;
		if (operator == MathOperator.INFERIOR)
			return v1 < v2;
		if (operator == MathOperator.SUPERIOR)
				return v1 > v2;
		if (operator == MathOperator.INFERIOR_OR_EQUALS)
			return v1 <= v2;
		if (operator == MathOperator.SUPERIOR_OR_EQUALS)
			return v1 >= v2;
		else
			return false;
		
		
	}
	
	//BOOLEAN
	public boolean logicOperator(Variable v1, Variable v2, LogicOperator logicOperator) {
		boolean value = false;
		
		if (v1.getType() == VariableType.BOOLEAN && v2.getType() == VariableType.BOOLEAN) {
			
			value = logicOperator(Boolean.getBoolean(v1.getValue()), Boolean.getBoolean(v2.getValue()), logicOperator);
			
		} else {
			System.out.println("Error type:"+v1.getType()+" "+v2.getType()+"not aplicable for "+v1.getName()+" "+v2.getName());
		}
		
		return value;
	}
	
	public boolean logicOperator(boolean b1, boolean b2, LogicOperator logicOperator) {
		boolean value = false;
		
		if (logicOperator == LogicOperator.AND) {
			value = b1 && b2; 
		} else if (logicOperator == LogicOperator.OR) {
			value = b1 || b2;
		} else {
			System.out.println("Error logic operator "+logicOperator+" not exist");
		}
		
		return value;
	}
	
	//STRING
	
	
	
	public ArrayList<Variable> getVariables() {
		return this.vs;
	}
	
	private String createString(char[] tab, int start, int end)  {
		String str = "";
		for (int i = start ; i < end ; i++) {
			str+=tab[i];
		}
		return str;
	}
	
	public boolean compileCodeBooleanValue(String code) {
		
		//DECOMPOSE
		
		char[] cCode = code.toCharArray();
		
		boolean searchBalise = false;
		int start = 0;
		int finish = 0;
		
		for (int i = 0 ; i < cCode.length ; i++) {
			if (cCode[i] == '(') {
				start = i+1;
				searchBalise = true;
				break;
			}
		}
		
		if (searchBalise) {
			boolean find = false;
			for (int i = (cCode.length - 1) ; i >= 0 ; i--) {
				if (cCode[i] == ')') {
					finish = i-1;
					find = true;
					break;
				}
			}
			
			
			
			if (find) {
				String old = createString(cCode, start, finish);
				
				String translateValue;
				if (containComparationOperator(createString(cCode, start, finish))) {
					translateValue = ""+compileCodeBooleanValue(createString(cCode, start, finish));
				} else {
					translateValue = ""+doCalcule(createString(cCode, start, finish));
				}
				code = code.replace(old, translateValue);
			} else {
				System.out.println("Error you need to close ')' !");
			}
		}
		
		//CALCULATE
		
		System.out.println(code+" <--");
		code = code.replace("(", "").replace(")", "");
		
		System.out.println("--------"+code);
		code = doCalcule(code);
		System.out.println("--------"+code);
		
		String[] args = code.split(" ");
		
		boolean finalBool = true;
		LogicOperator lo = LogicOperator.AND;
		
		for (int i = 0 ; i < args.length ; i++) {
			
			
			//BOOL
			if (args[i].equalsIgnoreCase("true") || args[i].equalsIgnoreCase("false")) {
				finalBool = logicOperator(finalBool, stringToBool(args[i]), lo);
				lo = LogicOperator.NULL;
			} else if (isVariable(args[i])) {
				
				finalBool = logicOperator(finalBool, stringToBool(getVariable(args[i]).getValue()), lo);
				
			} else if (isLogicOperator(args[i])) {
				
				LogicOperator l = LogicOperator.valueOf(args[i]);
				if (l == LogicOperator.NOT) {
					if (i+1 < args.length && (isVariable(args[i+1]) || (args[i+1].equalsIgnoreCase("true") || args[i+1].equalsIgnoreCase("false")))) {
						if (isVariable(args[i+1])) {
							args[i+1] = ""+!stringToBool(getVariable(args[i+1]).getValue());
						} else {
							args[i+1] = ""+!stringToBool(args[i+1]);
						}
					} else {
						System.out.println("Error: the argument 'NOT' need a coorect value...");
					}
				} else {
					lo = LogicOperator.valueOf(args[i]);
				}
				
			}
			
			//MATH
			
			else if (MathOperator.getOperator(args[i]) != MathOperator.NULL) {
				
				if (MathOperator.isComparator(args[i])) {
					if (i >= 1 && i+1 <= args.length) {
						if (isNumber(args[i-1]) && isNumber(args[i+1])) {
							finalBool = logicOperator(finalBool, mathComparator(Double.parseDouble(args[i-1]), Double.parseDouble(args[i+1]), MathOperator.getOperator(args[i])), lo);
						} else if (isVariable(args[i - 1]) && isVariable(args[i + 1])){
							finalBool = logicOperator(finalBool, mathComparator(getVariable(args[i-1]), getVariable(args[i+1]), MathOperator.getOperator(args[i])), lo);
						} else {
							System.out.println("Error: Operator "+args[i-1]+" "+args[i]+" "+args[i+1]+" impossible to calculate ...");
						}
					} else {
						System.out.println("Operator "+args[i]+" error bad use ...");
					}
				}
			}
					
		}
		
		return finalBool;
		
	} 
	
	private boolean containComparationOperator(String code) {
		
		return false;
	}
	
	private String doCalcule(String code) {
		String[] args = code.split(" ");
		
		for (int i = 0 ; i < args.length ; i++) {
			
			if (MathOperator.getOperator(args[i]) != MathOperator.NULL) {
				
					if (!MathOperator.isComparator(args[i])) {
						
						if (i >= 1 && i+1 <= args.length) {
							
							if (isNumber(args[i-1]) && isNumber(args[i+1])) {
								code = code.replace(args[i-1]+" "+args[i]+" "+args[i+1], ""+mathOperator(Double.parseDouble(args[i-1]), Double.parseDouble(args[i+1]), MathOperator.getOperator(args[i])));
								code = doCalcule(code);
								break;
							} else if (isVariable(args[i - 1]) && isVariable(args[i + 1])){
								code = code.replace(args[i-1]+" "+args[i]+" "+args[i+1], ""+mathOperator(getVariable(args[i - 1]), getVariable(args[i + 1]), MathOperator.getOperator(args[i])));
								code = doCalcule(code);
								break;
							} else {
								System.out.println("Error: Operator "+args[i-1]+" "+args[i]+" "+args[i+1]+" impossible to calculate ...");
							}
							
						} else {
							System.out.println("Operator "+args[i]+" error bad use ...");
						}
							
						
					}
				
					
			}
			
		}
		
		return code;
	}
	
	boolean stringToBool(String bool) {
		if (bool.equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}
	
	boolean isLogicOperator(String name) {
		LogicOperator lo = LogicOperator.getOperator(name);
		if (lo == LogicOperator.NULL) {
			return false;
		}
		return true;
	}
	
	public boolean isNumber(String name) {
		char[] cTab = name.toCharArray();
		for (char c : cTab) {
			if (!isNumberChar(c)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isNumberChar(char c) {
		return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.';  
	}
	
	public boolean isVariable(String name) {
		return getVariable(name) != null;
	}
	
	public Variable getVariable(String name) {
		
		for (Variable variable : vs) {
			if (variable.getName().equalsIgnoreCase(name)) {
				return variable;
			}
		}
		
		return null;
	}
	
	public void addVariable(Variable variable) {
		vs.add(variable);
	}
	
	public void removeVariable(Variable variable) {
		for (int i = 0 ; i < vs.size() ; i++) {
			if (vs.get(i).getName().equalsIgnoreCase(variable.getName())) {
				vs.remove(i);
				i--;
			}
		}
	}
	
}
