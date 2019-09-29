/**
*Dec 20, 2012 11:50:12 AM
*谷利军     QQ:23796788
*Email:gulijun2001@163.com
*/
package com.softline.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	
	/**
	 * 把Json格式的字符串转化为LinkedHashMap,只能有一层,不能有多层
	 * @param jsonStr Json格式的字符串
	 * 需要的外部jar包 json-lib-2.4-jdk15.jar,ezmorph-1.0.6.jar,commons-beanutils.jar,
	 * eventgateway.jar,commons-lang-2.4.jar,commons-collections-3.2.jar共有六个外部包
	 * @return
	 */
	public static LinkedHashMap<String, String> parseJSON2Map(String jsonStr){
		LinkedHashMap<String, String> map = new LinkedHashMap<String,String>();
		try{
			JSONObject json = JSONObject.fromObject(jsonStr);
	        for(Object k : json.keySet()){
	            Object v = json.get(k); 
	            map.put(k.toString(), v.toString());
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 将jeson格式的字符串,转化成List<LinkedHashMap<String,Object>>
	 * @param jsonStr
	 * @return
	 */
	public static List<LinkedHashMap<String,Object>> parseJSON2List(String jsonStr){
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<LinkedHashMap<String,Object>> list = new ArrayList<LinkedHashMap<String,Object>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
            JSONObject json2 = it.next();
            list.add(json2Map(json2.toString()));
        }
        return list;
    }
	
	/**
	 * 将jeson格式的字符串,转化成List<LinkedHashMap<String,Object>>
	 * @param jsonStr
	 * @return
	 */
	public static List<LinkedHashMap<String,String>> Json2List1(String jsonStr){
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<LinkedHashMap<String,String>> list = new ArrayList<LinkedHashMap<String,String>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
            JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    }	
	
	
	/**
	 * 将JSON格式的字符串转换了List集合
	 * @param jsonStr
	 * @return
	 */
	public static List<String> Json2List(String jsonStr){
		List<String> list = new ArrayList<String>();
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		list = (List)JSONArray.toCollection(jsonArr);
		return list;
	}
	
    /**
     * 将jeson格式的字符串转化成对象,可能有多个子串的情况
     * @param jsonStr
     * @return
     */
	public static LinkedHashMap<String, Object> json2Map(String jsonStr){
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k); 
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String,Object>>();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = it.next();
                    list.add(json2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v.toString());
            }
        }
        return map;
    }
	/**
	 *  
	 * @param obj Map对象
	 * @return
	 */
	public static String Map2JSON(Object obj){
		return JSONObject.fromObject(obj).toString();
	}	
    
	/**
	 *将一个对象转化成JSON格式的字符串,
	 * @param obj 对象可以是,数组,集合等
	 * @return
	 */
	public static String object2JSON(Object obj){
		return JSONArray.fromObject(obj).toString();
	}
	
	/**
	 * map数据转换为json数据
	 * @param dataMap:其键可能又是一下对象
	 * @return JSONObject json格式
	 */
	public static JSONObject mapToJson(Map dataMap){
		return JSONObject .fromObject(dataMap);
	}
	
	/**
	 * map数据转换为json字符串
	 * @param dataMap:其键可能又是一下对象
	 * @return JSONObject json格式
	 */
	public static String mapToString(Map dataMap){
		return JSONObject.fromObject(dataMap).toString();
	}
	
	/**
	 * 将List对象转化为json格式的String
	 * @param li其中存放的是Map 或 LinkedHashMap
	 * @return
	 */
	public static String listToString(List li){
		return JSONArray.fromObject(li).toString();
	}
	
	
	/**
	 * json字符串转json对象
	 * @param jsonString
	 * @param pojoCalss
	 * @return
	 */
	public static <T> T jsonToObject(String jsonString, Class<T> pojoCalss) {    
        try{    
            Object pojo;    
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonString);    
            pojo = net.sf.json.JSONObject.toBean(jsonObject, pojoCalss);    
            return (T)pojo;    
        }catch(Exception ex){    
            ex.printStackTrace();    
                
            return null;    
        }    
    }  
	
	
	
	
	public static void main(String[] args) {
		String str = "[{'aimcol':'code','coltype':'varchar(50)','caseid':'0','casename':'编号'},{'aimcol':'name','coltype':'varchar(50)','caseid':'1','casename':'姓名'},{'aimcol':'age','coltype':'varchar(50)','caseid':'3','casename':'年龄'},{'aimcol':'zf','coltype':'float(53)','caseid':'9','casename':'总分'}]";
		List<LinkedHashMap<String,Object>> li = JsonUtil.parseJSON2List(str);
		System.out.println(li);
		//for(String r:li){
		//	System.out.println(r);
		//}
	}
}
