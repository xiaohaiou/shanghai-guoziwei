package com.softline.entityTemp;

import java.util.List;

public class ResponseInfo {
	private String Result;
	private String ErrorCode;
	private String ErrorInfo;
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	public String getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorInfo() {
		return ErrorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		ErrorInfo = errorInfo;
	}
}
