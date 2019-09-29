package com.softline.entity;

/**
 * HhProcedureOpRecord entity. @author MyEclipse Persistence Tools
 */

public class HhProcedureOpRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String procedureName;
	private String procedureParam;
	private Integer opResult;
	private String opMessage;
	private String opVcEmployeeId;
	private String opVcName;
	private String opTime;

	// Constructors

	/** default constructor */
	public HhProcedureOpRecord() {
	}

	/** full constructor */
	public HhProcedureOpRecord(String procedureName, String procedureParam,
			Integer opResult, String opMessage, String opVcEmployeeId,
			String opVcName, String opTime) {
		this.procedureName = procedureName;
		this.procedureParam = procedureParam;
		this.opResult = opResult;
		this.opMessage = opMessage;
		this.opVcEmployeeId = opVcEmployeeId;
		this.opVcName = opVcName;
		this.opTime = opTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProcedureName() {
		return this.procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getProcedureParam() {
		return this.procedureParam;
	}

	public void setProcedureParam(String procedureParam) {
		this.procedureParam = procedureParam;
	}

	public Integer getOpResult() {
		return this.opResult;
	}

	public void setOpResult(Integer opResult) {
		this.opResult = opResult;
	}

	public String getOpMessage() {
		return this.opMessage;
	}

	public void setOpMessage(String opMessage) {
		this.opMessage = opMessage;
	}

	public String getOpVcEmployeeId() {
		return this.opVcEmployeeId;
	}

	public void setOpVcEmployeeId(String opVcEmployeeId) {
		this.opVcEmployeeId = opVcEmployeeId;
	}

	public String getOpVcName() {
		return this.opVcName;
	}

	public void setOpVcName(String opVcName) {
		this.opVcName = opVcName;
	}

	public String getOpTime() {
		return this.opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

}