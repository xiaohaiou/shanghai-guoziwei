package com.softline.entity;

/**
 * SysExamineReport entity. @author MyEclipse Persistence Tools
 */

public class SysExamineReport implements java.io.Serializable {

	// Fields

	private Integer id;
	private String examinestr;
	private Integer examresult;
	private String reportTime;
	private Integer groupId;
	private Integer examKind;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String organalId;
	private String examresultName;
	
	
	// Constructors

	public String getExamresultName() {
		return examresultName;
	}

	public void setExamresultName(String examresultName) {
		this.examresultName = examresultName;
	}

	/** default constructor */
	public SysExamineReport() {
	}

	/** minimal constructor */
	public SysExamineReport(Integer examresult, Integer examKind, Integer isdel) {
		this.examresult = examresult;
		this.examKind = examKind;
		this.isdel = isdel;
	}

	/** full constructor */
	public SysExamineReport(String examinestr, Integer examresult,
			String reportTime, Integer groupId, Integer examKind,
			Integer isdel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate, String organalId) {
		this.examinestr = examinestr;
		this.examresult = examresult;
		this.reportTime = reportTime;
		this.groupId = groupId;
		this.examKind = examKind;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.organalId = organalId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExaminestr() {
		return this.examinestr;
	}

	public void setExaminestr(String examinestr) {
		this.examinestr = examinestr;
	}

	public Integer getExamresult() {
		return this.examresult;
	}

	public void setExamresult(Integer examresult) {
		this.examresult = examresult;
	}

	public String getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getExamKind() {
		return this.examKind;
	}

	public void setExamKind(Integer examKind) {
		this.examKind = examKind;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
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

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyPersonId() {
		return this.lastModifyPersonId;
	}

	public void setLastModifyPersonId(String lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}

	public String getLastModifyPersonName() {
		return this.lastModifyPersonName;
	}

	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
	}

	public String getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getOrganalId() {
		return this.organalId;
	}

	public void setOrganalId(String organalId) {
		this.organalId = organalId;
	}

}