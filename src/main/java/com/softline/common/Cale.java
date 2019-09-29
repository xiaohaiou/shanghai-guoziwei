package com.softline.common;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;

import com.softline.entity.ReportFormsCell;


public class Cale {
	
	/**
	 * 计算公式
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public String CaleStr(String str) throws Exception
	{
		JEP jep = new JEP();
		jep.addStandardFunctions();
		jep.addStandardConstants();
		jep.addComplex();
		String result="";
		 try {
			 //这个替换是因为jep不能识别简写的空字符串
			   str=str.replace(",,", ",\"\",");
		       Node a= jep.parse(str); 
		       Object obj=jep.evaluate(a);
		       result=String.valueOf(obj);
		       
		 } catch (Exception e) {
			 e.printStackTrace();
		      throw new Exception(str+"公式无法解析");
		 }
		 return result;
	}
	
	private String replaceStr(String formula,HashMap<String, String> map)
	{
		
		Pattern p=Pattern.compile("(?<=\\[)[^\\[\\]]*"); 
		Matcher m=p.matcher(formula); 
		while(m.find()) { 
		    String indexstr="["+m.group()+"]";
		    formula=formula.replace(indexstr, map.get(indexstr));
		} 
		return formula;
	}
	
	/**
	 * 获取值
	 * @param formula
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	public String getValue(String formula,HashMap<String, String> map) throws Exception
	{
		String a=formula;
		try{
			a=replaceStr(formula,map);
			return CaleStr(a);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			throw new Exception(a+"公式无法解析");
		}
	}
	
}
