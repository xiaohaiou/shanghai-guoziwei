package com.softline.entity.project;

/**
 * PjWeekreportHistory entity. @author MyEclipse Persistence Tools
 */

public class PjWeekreportHistory implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer wkReportId;
	private Integer pjId;
	private String wrTitle;
	private String wrContent;
	private String wrYear;
	private Integer wrWeek;
	private String wrDatetime;
	private String wrLine;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String createDate;
	private String wrStartTime;
	private String wrEndTime;
	private String wrJdContent;
	private String wrZlContent;
	private String wrAqContent;
	private String wrCgContent;
	private String wrSxContent;
	private String reportTime;
	private String reportPersonId;
	private String reportPersonName;
	private Integer version;
	private Integer reportStatus;
	private Integer approveId;
	
	private PjApprove approve;

	// Constructors

	/** default constructor */
	public PjWeekreportHistory() {
	}

	/** minimal constructor */
	public PjWeekreportHistory(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public PjWeekreportHistory(Integer wkReportId, Integer pjId,
			String wrTitle, String wrContent, String wrYear, Integer wrWeek,
			String wrDatetime, String wrLine, Integer isdel,
			String createPersonName, String createPersonId,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String createDate, String wrStartTime,
			String wrEndTime, String wrJdContent, String wrZlContent,
			String wrAqContent, String wrCgContent, String wrSxContent,
			String reportTime, String reportPersonId, String reportPersonName,
			Integer version, Integer reportStatus, Integer approveId) {
		this.wkReportId = wkReportId;
		this.pjId = pjId;
		this.wrTitle = wrTitle;
		this.wrContent = wrContent;
		this.wrYear = wrYear;
		this.wrWeek = wrWeek;
		this.wrDatetime = wrDatetime;
		this.wrLine = wrLine;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.createDate = createDate;
		this.wrStartTime = wrStartTime;
		this.wrEndTime = wrEndTime;
		this.wrJdContent = wrJdContent;
		this.wrZlContent = wrZlContent;
		this.wrAqContent = wrAqContent;
		this.wrCgContent = wrCgContent;
		this.wrSxContent = wrSxContent;
		this.reportTime = reportTime;
		this.reportPersonId = reportPersonId;
		this.reportPersonName = reportPersonName;
		this.version = version;
		this.reportStatus = reportStatus;
		this.approveId = approveId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWkReportId() {
		return this.wkReportId;
	}

	public void setWkReportId(Integer wkReportId) {
		this.wkReportId = wkReportId;
	}

	public Integer getPjId() {
		return this.pjId;
	}

	public void setPjId(Integer pjId) {
		this.pjId = pjId;
	}

	public String getWrTitle() {
		return this.wrTitle;
	}

	public void setWrTitle(String wrTitle) {
		this.wrTitle = wrTitle;
	}

	public String getWrContent() {
		return this.wrContent;
	}

	public void setWrContent(String wrContent) {
		this.wrContent = wrContent;
	}

	public String getWrYear() {
		return this.wrYear;
	}

	public void setWrYear(String wrYear) {
		this.wrYear = wrYear;
	}

	public Integer getWrWeek() {
		return this.wrWeek;
	}

	public void setWrWeek(Integer wrWeek) {
		this.wrWeek = wrWeek;
	}

	public String getWrDatetime() {
		return this.wrDatetime;
	}

	public void setWrDatetime(String wrDatetime) {
		this.wrDatetime = wrDatetime;
	}

	public String getWrLine() {
		return this.wrLine;
	}

	public void setWrLine(String wrLine) {
		this.wrLine = wrLine;
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

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getWrStartTime() {
		return this.wrStartTime;
	}

	public void setWrStartTime(String wrStartTime) {
		this.wrStartTime = wrStartTime;
	}

	public String getWrEndTime() {
		return this.wrEndTime;
	}

	public void setWrEndTime(String wrEndTime) {
		this.wrEndTime = wrEndTime;
	}

	public String getWrJdContent() {
		return this.wrJdContent;
	}

	public void setWrJdContent(String wrJdContent) {
		this.wrJdContent = wrJdContent;
	}

	public String getWrZlContent() {
		return this.wrZlContent;
	}

	public void setWrZlContent(String wrZlContent) {
		this.wrZlContent = wrZlContent;
	}

	public String getWrAqContent() {
		return this.wrAqContent;
	}

	public void setWrAqContent(String wrAqContent) {
		this.wrAqContent = wrAqContent;
	}

	public String getWrCgContent() {
		return this.wrCgContent;
	}

	public void setWrCgContent(String wrCgContent) {
		this.wrCgContent = wrCgContent;
	}

	public String getWrSxContent() {
		return this.wrSxContent;
	}

	public void setWrSxContent(String wrSxContent) {
		this.wrSxContent = wrSxContent;
	}

	public String getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportPersonId() {
		return this.reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportPersonName() {
		return this.reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getReportStatus() {
		return this.reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Integer getApproveId() {
		return this.approveId;
	}

	public void setApproveId(Integer approveId) {
		this.approveId = approveId;
	}

	public PjApprove getApprove() {
		return approve;
	}

	public void setApprove(PjApprove approve) {
		this.approve = approve;
	}

}