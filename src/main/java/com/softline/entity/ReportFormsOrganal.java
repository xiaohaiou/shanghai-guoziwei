package com.softline.entity;

/**
 * ReportFormsOrganal entity. @author MyEclipse Persistence Tools
 */

public class ReportFormsOrganal implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer isdel;
	private Integer formsId;
	private String organalId;
	private Integer isexamine;
	private String reportTime;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer iscollect;
	private String reportPersonName;
	private String reportPersonId;
	private String reportDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	private String parentorg;
	private Integer groupID;
	private String organName;
	private Integer formsKind;
	// Constructors

	/** default constructor */
	public ReportFormsOrganal() {
	}

	

	/** minimal constructor */
	public ReportFormsOrganal(Integer isdel, Integer formsId,
			String organalId, Integer isexamine, String reportTime) {
		this.isdel = isdel;
		this.formsId = formsId;
		this.organalId = organalId;
		this.isexamine = isexamine;
		this.reportTime = reportTime;
	}



	// Property accessors

	public ReportFormsOrganal(Integer id, Integer isdel, Integer formsId,
			String organalId, Integer isexamine, String reportTime,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, Integer groupID) {
		super();
		this.id = id;
		this.isdel = isdel;
		this.formsId = formsId;
		this.organalId = organalId;
		this.isexamine = isexamine;
		this.reportTime = reportTime;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.reportPersonName = reportPersonName;
		this.reportPersonId = reportPersonId;
		this.reportDate = reportDate;
		this.auditorPersonName = auditorPersonName;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
		this.groupID = groupID;
	}

	public String getOrganName() {
		return organName;
	}



	public void setOrganName(String organName) {
		this.organName = organName;
	}



	public Integer getIscollect() {
		return iscollect;
	}



	public String getParentorg() {
		return parentorg;
	}



	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}



	public void setIscollect(Integer iscollect) {
		this.iscollect = iscollect;
	}



	public Integer getGroupID() {
		return groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	public String getReportPersonName() {
		return reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getReportPersonId() {
		return reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getAuditorPersonName() {
		return auditorPersonName;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public String getAuditorPersonId() {
		return auditorPersonId;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public String getAuditorDate() {
		return auditorDate;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getFormsId() {
		return this.formsId;
	}

	public void setFormsId(Integer formsId) {
		this.formsId = formsId;
	}

	public String getOrganalId() {
		return this.organalId;
	}

	public void setOrganalId(String organalId) {
		this.organalId = organalId;
	}

	public Integer getIsexamine() {
		return this.isexamine;
	}

	public void setIsexamine(Integer isexamine) {
		this.isexamine = isexamine;
	}

	public String getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getCreatePersonName() {
		return this.createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public Integer getFormsKind() {
		return formsKind;
	}



	public void setFormsKind(Integer formsKind) {
		this.formsKind = formsKind;
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

}