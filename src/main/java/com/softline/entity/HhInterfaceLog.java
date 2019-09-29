package com.softline.entity;

/**
 * HhInterfaceLog entity. @author MyEclipse Persistence Tools
 */

public class HhInterfaceLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String intefaceName;
	private String intefaceAlias;
	private String backResult;
	private String backResultInfo;
	private String remark;
	private String callTime;
	private String callPersonName;
	private String callVcEmployeeId;

	// Constructors

	/** default constructor */
	public HhInterfaceLog() {
	}

	/** full constructor */
	public HhInterfaceLog(String intefaceName, String intefaceAlias,
			String backResult, String backResultInfo, String remark,
			String callTime, String callPersonName, String callVcEmployeeId) {
		this.intefaceName = intefaceName;
		this.intefaceAlias = intefaceAlias;
		this.backResult = backResult;
		this.backResultInfo = backResultInfo;
		this.remark = remark;
		this.callTime = callTime;
		this.callPersonName = callPersonName;
		this.callVcEmployeeId = callVcEmployeeId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIntefaceName() {
		return this.intefaceName;
	}

	public void setIntefaceName(String intefaceName) {
		this.intefaceName = intefaceName;
	}

	public String getIntefaceAlias() {
		return this.intefaceAlias;
	}

	public void setIntefaceAlias(String intefaceAlias) {
		this.intefaceAlias = intefaceAlias;
	}

	public String getBackResult() {
		return this.backResult;
	}

	public void setBackResult(String backResult) {
		this.backResult = backResult;
	}

	public String getBackResultInfo() {
		return this.backResultInfo;
	}

	public void setBackResultInfo(String backResultInfo) {
		this.backResultInfo = backResultInfo;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCallTime() {
		return this.callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public String getCallPersonName() {
		return this.callPersonName;
	}

	public void setCallPersonName(String callPersonName) {
		this.callPersonName = callPersonName;
	}

	public String getCallVcEmployeeId() {
		return this.callVcEmployeeId;
	}

	public void setCallVcEmployeeId(String callVcEmployeeId) {
		this.callVcEmployeeId = callVcEmployeeId;
	}

}