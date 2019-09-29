package com.softline.common;

import java.util.List;

public class JsonResult<T> {
	private String msg;
	private boolean isSuccessful ;
	private Object data;
	
	public static   JsonResult<Object> CreateSuccessful(String msg)
	{
		 return	new JsonResult<Object>(msg,true);
	}
	
	
	public static <U>  JsonResult<U> CreateSuccessful(List<U> data)
	{
	  return	new JsonResult<U>("",true,data);
	}
	
	public static <U>  JsonResult<U> CreateSuccessful(U data)
	{
	  return	new JsonResult<U>("",true,data);
	}
	
	public static <U>  JsonResult<U> CreateSuccessful(PagingOject<U> data)
	{
	  return	new JsonResult<U>("",true,data);
	}
	
	public static   JsonResult<Object> CreateFail(String msg )
	{
	  return	new JsonResult<Object>(msg,false);
	}
	
	public JsonResult(String m, boolean is)
	{
		msg=m;
		isSuccessful=is;
	}

	public JsonResult(String m, boolean is,T d)
	{
		msg=m;
		isSuccessful=is;
		data=d;
		
	}
	public JsonResult(String m, boolean is,PagingOject<T> d)
	{
		msg=m;
		isSuccessful=is;
		data=d;
		
	}
	
	public JsonResult(String m, boolean is,List<T> d)
	{
		msg=m;
		isSuccessful=is;
		data=d;
		
	}
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean getIsSuccessful() {
		return isSuccessful;
	}
	public void setIsSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
