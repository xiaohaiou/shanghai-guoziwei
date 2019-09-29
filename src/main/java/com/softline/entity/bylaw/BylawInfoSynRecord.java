package com.softline.entity.bylaw;

/**
 * BylawInfoSynRecord entity. @author MyEclipse Persistence Tools
 */

public class BylawInfoSynRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String optTime;
	private String synResult;
	private String synErrorCode;
	private String synErrorInfo;
	private String synStartTime;
	private String synEndTime;
	private Integer synRecordCount;
	private String createPersonName;
	private String createPersonId;
	
	//query condition
	private String opStartTime;
	private String opEndTime;
	

	// Constructors

	/** default constructor */
	public BylawInfoSynRecord() {
	}

	/** full constructor */
	public BylawInfoSynRecord(String optTime, String synResult,
			String synErrorCode, String synErrorInfo, String synStartTime,
			String synEndTime, Integer synRecordCount, String createPersonName,
			String createPersonId) {
		this.optTime = optTime;
		this.synResult = synResult;
		this.synErrorCode = synErrorCode;
		this.synErrorInfo = synErrorInfo;
		this.synStartTime = synStartTime;
		this.synEndTime = synEndTime;
		this.synRecordCount = synRecordCount;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOptTime() {
		return this.optTime;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	public String getSynResult() {
		return this.synResult;
	}

	public void setSynResult(String synResult) {
		this.synResult = synResult;
	}

	public String getSynErrorCode() {
		return this.synErrorCode;
	}

	public void setSynErrorCode(String synErrorCode) {
		this.synErrorCode = synErrorCode;
	}

	public String getSynErrorInfo() {
		return this.synErrorInfo;
	}

	public void setSynErrorInfo(String synErrorInfo) {
		this.synErrorInfo = synErrorInfo;
	}

	public String getSynStartTime() {
		return this.synStartTime;
	}

	public void setSynStartTime(String synStartTime) {
		this.synStartTime = synStartTime;
	}

	public String getSynEndTime() {
		return this.synEndTime;
	}

	public void setSynEndTime(String synEndTime) {
		this.synEndTime = synEndTime;
	}

	public Integer getSynRecordCount() {
		return this.synRecordCount;
	}

	public void setSynRecordCount(Integer synRecordCount) {
		this.synRecordCount = synRecordCount;
	}

	public String getCreatePersonName() {
		return this.createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public String getCreatePersonId() {
		return this.createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getOpStartTime() {
		return opStartTime;
	}

	public void setOpStartTime(String opStartTime) {
		this.opStartTime = opStartTime;
	}

	public String getOpEndTime() {
		return opEndTime;
	}

	public void setOpEndTime(String opEndTime) {
		this.opEndTime = opEndTime;
	}
	
	

}