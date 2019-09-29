package com.softline.entityTemp;

public class CommitResult {
	//错误信息
	private String exceptionStr;
	//提交事物结果
	private boolean result;
	
	private Object entity;
	
	public String getExceptionStr() {
		return exceptionStr;
	}
	public void setExceptionStr(String exceptionStr) {
		this.exceptionStr = exceptionStr;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	public CommitResult(String exceptionStr, boolean result) {
		super();
		this.exceptionStr = exceptionStr;
		this.result = result;
	}
	
	public CommitResult() {
		super();
	}
	
	public static CommitResult createErrorResult(String ExceptionStr)
	{
		CommitResult result=new CommitResult();
		result.setExceptionStr(ExceptionStr);
		result.setResult(false);
		return result;
	}

}
