package com.softline.entityTemp;

public class SDK_error {
	
	private Boolean result;
	private String EmpDirectoryError;
	private String OrganInfoError;
	private String EmpPostRecordError;
	private String EmpMessageInfoError;
	
	
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getEmpDirectoryError() {
		return EmpDirectoryError;
	}
	public void setEmpDirectoryError(String empDirectoryError) {
		EmpDirectoryError = empDirectoryError;
	}
	public String getOrganInfoError() {
		return OrganInfoError;
	}
	public void setOrganInfoError(String organInfoError) {
		OrganInfoError = organInfoError;
	}
	public String getEmpPostRecordError() {
		return EmpPostRecordError;
	}
	public void setEmpPostRecordError(String empPostRecordError) {
		EmpPostRecordError = empPostRecordError;
	}
	public String getEmpMessageInfoError() {
		return EmpMessageInfoError;
	}
	public void setEmpMessageInfoError(String empMessageInfoError) {
		EmpMessageInfoError = empMessageInfoError;
	}
}
