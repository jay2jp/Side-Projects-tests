package apps;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import structures.Stack;

public class Expression {

	/**
	 * Expression to be evaluated
	 */
	String expr;                
    
	/**
	 * Scalar symbols in the expression 
	 */
	ArrayList<ScalarSymbol> scalars;   
	
	/**
	 * Array symbols in the expression
	 */
	ArrayList<ArraySymbol> arrays;
	Stack<Double> a = new Stack<Double>();
	Stack<Character> b = new Stack<Character>();
    
    /**
     * String containing all delimiters (characters other than variables and constants), 
     * to be used with StringTokenizer
     */
    public static final String delims = " \t*+-/()!]";
    
    /**
     * Initializes this Expression object with an input expression. Sets all other
     * fields to null.
     * 
     * @param expr Expression
     */
    public Expression(String expr) {
        this.expr = expr;
        scalars = null;
        arrays = null;
    }

    /**
     * Populates the scalars and arrays lists with symbols for scalar and array
     * variables in the expression. For every variable, a SINGLE symbol is created and stored,
     * even if it appears more than once in the expression.
     * At this time, values for all variables are set to
     * zero - they will be loaded from a file in the loadSymbolValues method.
     */
    public void buildSymbols() {
    		/** COMPLETE THIS METHOD **/
    	scalars = new ArrayList<ScalarSymbol>();
    	arrays = new ArrayList<ArraySymbol>();
    	String k = expr.replaceAll("\\s+", "");
    	k = k.replace("[","[!");
    	StringTokenizer st = new StringTokenizer(k,delims);  
    	while(st.hasMoreTokens()){
    		String sttemp = st.nextToken();
    		if(sttemp.charAt(sttemp.length()-1)=='['){
    			arrays.add(new ArraySymbol(sttemp.substring(0, sttemp.length()-1)));
    		}
    		if(Character.isLetter(sttemp.charAt(sttemp.length()-1))){
    			scalars.add(new ScalarSymbol(sttemp));
    		}
    	}
  
    }
    
    /**
     * Loads values for symbols in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     */
    public void loadSymbolValues(Scanner sc) 
    throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String sym = st.nextToken();
            ScalarSymbol ssymbol = new ScalarSymbol(sym);
            ArraySymbol asymbol = new ArraySymbol(sym);
            int ssi = scalars.indexOf(ssymbol);
            int asi = arrays.indexOf(asymbol);
            if (ssi == -1 && asi == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                scalars.get(ssi).value = num;
            } else { // array symbol
            	asymbol = arrays.get(asi);
            	asymbol.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    String tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    asymbol.values[index] = val;              
                }
            }
        }
    }
    
    
    /**
     * Evaluates the expression, using RECURSION to evaluate subexpressions and to evaluate array 
     * subscript expressions.
     * 
     * @return Result of evaluation
     */
    public float evaluate() {
    	String scalarstring = scalars.toString();
    	String arraystring = arrays.toString();
    	String nospace = expr.replaceAll("\\s+","");
    	if(!scalarstring.isEmpty()){
    	StringTokenizer stk = new StringTokenizer(scalarstring,",+-*/[]()=");  
		boolean f = false;
		boolean t = true;
		int q = 0;
		String one = "";
		String two = "";
		while (stk.hasMoreTokens()) {  
	    	if(f){
	    		two = stk.nextToken();
	    		for(int n = 0; n<nospace.length(); n++){
	    			t = true;
	    			if(nospace.charAt(n)==one.charAt(0)){
	    				if(nospace.length()-n>=one.length()){
	    					for(q = 0; q<one.length(); q++){
	    						if(!(nospace.charAt(n+q)==one.charAt(q))){
	    							t = false;
	    							break;
	    							}
	    						}
	    						if(n+q==nospace.length()){
	    								
	    						}
	    						else if(Character.isLetter(nospace.charAt(n+q))|| nospace.charAt(n+q) == '['){
	    							t = false;
	    						}
	    				if(t){			
	        			nospace = replacestring(nospace,n,n+q,two);
	    				}
	    				}
	    			}
	    			}
	    	f = false;
	    	}
	    	else{
	    		one = stk.nextToken();
	    		one = one.replaceAll("\\s+","");
	    		f = true;
	    	}
	    	}
    	}
    	nospace = nospace.replaceAll("\\s+", "");
    	int leftbound = 0;
    	int rightbound = nospace.length();
    	String min = "";
    	boolean o = true;
    	while(o){
    		int wake = 0;
    		for(wake = 0; wake<nospace.length();wake++){
    			if(nospace.charAt(wake) =='(' || nospace.charAt(wake) == '['){
    				break;
    			}
    		}
    		if(wake == nospace.length()){
    			o = false;
    			continue;
    		}
    		for(int e = 0; e<nospace.length();e++){
    			if(nospace.charAt(e) =='(' || nospace.charAt(e) == '['){
    				leftbound = e;
    			}
    			if(nospace.charAt(e)==')' || nospace.charAt(e)==']'){
    				rightbound = e;
    				break;
    			}
    		}
    		min = nospace.substring(leftbound+1, rightbound);
    		double me = stackinator(min);
    		if(nospace.charAt(leftbound) == '['){
    			String u = nospace.substring(leftbound+1, rightbound);
    			System.out.println(u);
    			leftbound = leftbound -1;
    			char veg = nospace.charAt(leftbound);
    			String mex = "";
    			while(Character.isLetter(veg)){
    				mex = veg+mex;
    				leftbound = leftbound -1;
    				if(leftbound < 0){
    					break;
    				}
    				veg = nospace.charAt(leftbound);
    			}
    			System.out.println(mex);
    			leftbound  = leftbound +1 ;
    			StringTokenizer stl = new StringTokenizer(arraystring,",+*/[]()=");
    			int counter = 0;
    			boolean  t = false;
    			int z = 0;
    			String nurr = "";
    			while(stl.hasMoreTokens()){
    				nurr = stl.nextToken();
    				nurr = nurr.replaceAll("\\s+","");
    				//System.out.println(r);
    				/////////////System.out.println(nurr + "    " + t);
    				if(t && Character.isDigit(nurr.charAt(nurr.length()-1)) && counter == (int)me){
    					z = Integer.parseInt(nurr);
    					break;
    					}
    				if(nurr.equals(mex)){
    					t = true;
    					continue;
    				}
    				if(t){
    					counter++;
    				}
    			}
    			if(z >= 0){
    				nospace = nospace.substring(0,leftbound) + z + nospace.substring(rightbound+1);
    			}
    			else{
    				nospace = nospace.substring(0,leftbound) + "!" + Math.abs(z) + nospace.substring(rightbound+1);
    			}
    		}
    		else{
    		if(leftbound == 0){
    			nospace = replacestring(nospace.substring(1),leftbound,rightbound,String.valueOf(me));
    		}else{
    			nospace = replacestring(nospace,leftbound,rightbound+1,String.valueOf(me));
    		}
    		}
    		
    	}
    	return (float) stackinator(nospace);
    
    		// following line just a placeholder for compilation
    }
    private double stackinator(String exp){
    	String origin = "";
    	if(b.isEmpty() && exp == ""){
    		return a.pop();
    	}
    	if(exp == ""){
    		char operator = b.pop();
    		double num1 = a.pop();
    		double num2= a.pop();
    		if(operator == '+'){
    			if(!b.isEmpty()&& b.peek()=='-'){
    					a.push(num1-num2);
    					b.pop();
    					b.push('+');	
    			}
    			else{
    				a.push(num1+num2);
    			}
    		}
    		else if(operator == '*'){
    			a.push(num1*num2);
    			}
    		else if(operator == '/'){
    			a.push(num2/num1);
    		}
    		else{
    			if(!b.isEmpty()){
    				if(b.peek() == '-' ){
    				a.push(-1*(Math.abs(num1)+Math.abs(num2)));
    				}
    				else{
    					if(num1>=0){
    						a.push(num2-num1);
    					}
    					else{
    						a.push(num2+num1);
    					}
    				}
    				}
    				else{
    					if(num1>=0){
    						a.push(num2-num1);
    					}
    					else{
    						a.push(num2+num1);
    					}
    				}
    			
    		}
    		return stackinator("");
    	}
    	if(Character.isDigit(exp.charAt(0)) || exp.charAt(0) == '!'){
    		if (exp.charAt(0) == '!'){
    			origin = "-";
    			exp = exp.substring(1);
    		}
    		while(Character.isDigit(exp.charAt(0)) || exp.charAt(0) == '.'){
    			origin = origin+exp.charAt(0);
    			if(0 == exp.length()-1){
    				exp = "";
    				break;
    			}
    			exp = exp.substring(1);
    		}
    		a.push(Double.parseDouble(origin));
    		if(!b.isEmpty()){
    		if(b.peek() == '*' && exp == ""){
    			double me = a.pop();
    			double yu = a.pop();
    			a.push(me*yu);
    			b.pop();
    			return stackinator("");
    		}
    		if(b.peek() == '/' && exp == ""){
    			double me = a.pop();
    			double yu = a.pop();
    			a.push(yu/me);
    			b.pop();
    			return stackinator("");
    		}
    		if(b.peek() == '*'){
    			double me = a.pop();
    			double yu = a.pop();
    			a.push(me*yu);
    			b.pop();
    			}
    		else if(b.peek() == '/'){
    			double me = a.pop();
    			double yu = a.pop();
    			a.push(yu/me);
    			b.pop();
    		}
    		
    		}
    		return stackinator(exp);
    	
    	}
    	b.push(exp.charAt(0));
    	if(exp.length()==1 && !Character.isDigit(exp.charAt(0))){
    		return stackinator("");
    	}
    	exp = exp.substring(1);
    	return stackinator(exp);
    }
    private static String replacestring(String in,int begin,int end,String insert){
		return in.substring(0,begin) + insert + in.substring(end,in.length());
	}

    /**
     * Utility method, prints the symbols in the scalars list
     */
	public static boolean isNumeric(String str){
	  return str.matches("-?\\d+(\\.\\d+)?"); 
	}
    public void printScalars() {
        for (ScalarSymbol ss: scalars) {
            System.out.println(ss);
        }
    }
    
    /**
     * Utility method, prints the symbols in the arrays list
     */
    public void printArrays() {
    		for (ArraySymbol as: arrays) {
    			System.out.println(as);
    		}
    }
    public static void main(String[] args){
    	Expression s = new Expression("(varx + vary*arrayA[(vara+varb[(a+b)*33])])/55");
    	 
    	s.buildSymbols();
    	System.out.println(s.arrays.toString());
//    	s.printArrays();
//    	s.printScalars();
    }

}
