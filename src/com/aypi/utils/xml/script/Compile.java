package com.aypi.utils.xml.script;

import java.util.ArrayList;

import com.aypi.Aypi;

public class Compile {
	
	private ScriptManager sm;
	private String code;
	private String codeOrigine;
	private int line;
	
	private String[] args;
	
	public Compile(ScriptManager sm, String code, int line) {
		
		this.sm = sm;
		this.code = code;
		this.line = line;
		this.codeOrigine = code;
		
		decomposeCalcule();
		removeSpace();
		
		updateCode();
		removeSpace();
		
	}
	
	public String compile() {
		updateCode();
		
		for (int i = 0 ; i < args.length ; i++) {
			
			String word = args[i];
			String last = null;
			String next = null;
			
			if (i >= 1) {
				last = args[i-1];
			}
			
			if (i+1 < args.length) {
				next = args[i+1];
			}
			
			//COMPILE
			if (sm.isMathCalculator(word)) {
				
				if (last != null && next != null && sm.isNumber(last) && sm.isNumber(next)) {
					replaceCode(last+" "+word+" "+next, sm.doCalcule(last, next, word, line, codeOrigine));
					i = 0;
				} else if (last != null && next != null && sm.getMathOperator(word) == MathOperator.ADDITION) {
					replaceCode(last+" "+word+" "+next, sm.fuseString(last, next, line, codeOrigine));
					i = 0;
				} else {
					Aypi.getErrorManager().getError(0).display(line, codeOrigine);
				}
				
			} 
			
			else if (sm.isMathComparator(word)) {
				
				if (last != null && next != null && sm.isNumber(last) && sm.isNumber(next)) {
					replaceCode(last+" "+word+" "+next, sm.doCompare(last, next, word, line, codeOrigine));
					i = 0;
				} else if (last != null && next != null && sm.getMathOperator(word) == MathOperator.EQUALS) {
					replaceCode(last+" "+word+" "+next, ""+last.equalsIgnoreCase(next));
					i = 0;
				} else {
					Aypi.getErrorManager().getError(5).display(line, codeOrigine);
				}
				
			}
			
			else if (sm.isBooleanOperator(word)) {
				
				if (last != null && next != null && sm.isBoolean(last) && sm.isBoolean(next)) {
					replaceCode(last+" "+word+" "+next, sm.doCompareBoolean(last, next, word, line, codeOrigine));
					i = 0;
				} else if (next != null && sm.isBoolean(next)) {
					replaceCode(word+" "+next, ""+!sm.getBoolean(next));
					i = 0;
				} else {
					Aypi.getErrorManager().getError(6).display(line, codeOrigine);
				}
				
			} else if (!sm.isNumber(word) && !sm.isBoolean(word) && !sm.isVariable(word) && !sm.isString(word)){
				Aypi.getErrorManager().getError(9).display(i, codeOrigine);
				return code;
			}
			
		}
		removeSpace();
		return code;
	}
	
	private void replaceCode(String oldString, String newString) {
		
		//TODO PROBLEM WITH STRING TO CORRECT		
		
		code = code.replace(oldString, newString);
		updateCode();
		
	}
	
	private void updateCode() {
		
		code = code.replace("(", "");
		code = code.replace(")", "");
		
		this.args = decomposeArg();
		
		replaceAllVariable(args);
	
		code="";
		for (String str : this.args) {
			code+=str+" ";
		}
		
	}
	
	private String[] decomposeArg() {
		
		char[] cTab = code.toCharArray();
		ArrayList<String> temp = new ArrayList<String>();
		
		boolean string = false;
		boolean cancel;
		
		String word = "";
		
		for (int i = 0 ; i < cTab.length ; i++) {
			
			cancel = false;
			char c = cTab[i];
			
			if (c == '\\') {
				cancel = true;
				i++;
				if (i < cTab.length) {
					c = cTab[i];
				} else {
					Aypi.getErrorManager().getError(4).display(line, code);
				}
			}
			
			if ((c == '\'') && !cancel) {
				string = !string;
			}
			
			if (i == cTab.length - 1 && string) {
				Aypi.getErrorManager().getError(2).display(line, code);
			}
			
			if (c == ' ' || i == (cTab.length - 1)) {
				
				if (!string) {
					if (c != ' ') {
						word+=c;
					}
					temp.add(word);
					word = "";
				} else {
					word+=c;
				}
				
			} else {
				word+=c;
			}
			
		}
		
		String[] args = new String[temp.size()];
		for (int i = 0 ; i < temp.size() ; i++) {
			args[i] = temp.get(i);
		}
		
		return args;
		
	}
	
	private void decomposeCalcule() {
		char[] tab = code.toCharArray();
		
		boolean find = false;
		boolean string = false;
		int eq = 0;
		
		int start = 0;
		
		
		for (int i = 0 ; i < tab.length ; i++) {
			
			if (tab[i] == '\'') {
				if (i >= 1) {
					if (tab[i-1] != '\\') {
						string = !string;
					}
				} else {
					string = !string;
				}
			}
			
			if (tab[i] == '(' && !string) {
				eq++;
				if (!find) {
					find = true;
					start = i+1;
				}
				
			}
			
			if (tab[i] == ')' && !string) {
				
				eq--;
				if (find) {
					
					if (eq == 0) {
						String seq = sm.charTabToString(tab, start, i);
						Compile compile = new Compile(sm, seq, line);
						String compileCode = compile.compile();
						replaceCode(seq, compileCode);
						find = false;
					}
					
				} else {
					Aypi.getErrorManager().getError(1).display(line, code);
					return;
				}
				
			}
		}
		
		
	}
	
	private void replaceAllVariable(String[] args) {
		
		for (int i = 0 ; i < args.length ; i++) {
			if (sm.isVariable(args[i])) {
				args[i] = sm.getVariable(args[i]).getValue();
				
			}
			
			if (sm.isListScript(args[i])) {
				ListScript mls = sm.getListScript(args[i]); 
				int index = sm.getListScriptValue(args[i]);
				if (index >= 0 && index < mls.getValues().size()) {
					args[i] = mls.getValues().get(index);
				} else {
					Aypi.getErrorManager().getError(12).display(i, args[i]);
				}
			}
		}
	}
	
	private void removeSpace() {

		code = code.replaceAll("\t", " ");
		code = code.replaceAll("\n", " ");
		
		char[] tab = code.toCharArray();
		
		String str = "";
		
		boolean string = false;
		boolean cancel = false;
		boolean space = false;
		
		for (int i = 0 ; i < tab.length ; i++) {
			char c = tab[i];
			cancel = false;
			
			if (c == '\\') {
				cancel = true;
			}
			
			if (c == '\'' && !cancel) {
				string = !string;
			}
			
			if ((c == ' ' || c == '\t' || c == '\n') && !string) {
				if (!space) str+=c;
				space = true;
			} else {
				str+=c;
				space = false;
			}
		}
		
		code = str;
		tab = code.toCharArray();
		while (tab[tab.length - 1] == ' ') {
			code = sm.charTabToString(tab, 0, tab.length - 1);
			tab = code.toCharArray();
		}
		
	}

}
