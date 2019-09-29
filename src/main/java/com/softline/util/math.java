package com.softline.util;

import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;

import parsii.eval.Expression;
import parsii.eval.Parser;
import parsii.eval.Scope;
import parsii.eval.Variable;
import parsii.tokenizer.ParseException;



public class math {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		JEP jep = new JEP();
		jep.addStandardFunctions();
		jep.addStandardConstants();
		jep.addComplex();
		 try {
		      jep.addVariable("x", 10);
		      String str="if(1==0,,(-1-1)/abs(1)+1)";
		      str=str.replace(",,", ",\"\",");
		       Node a= jep.parse(str);
		       
		       Object result=jep.evaluate(a);
		        System.out.println("x + 1 = " + result);
		 } catch (Exception e) {
		        System.out.println("An error occurred: " + e.getMessage());
		 }
		 
	}

}
