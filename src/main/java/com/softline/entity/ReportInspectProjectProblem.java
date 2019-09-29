package com.softline.entity;

/**
 * ReportInspectProjectProblem entity. @author MyEclipse Persistence Tools
 */
public class ReportInspectProjectProblem implements java.io.Serializable {

	// Constructors

	/** default constructor */
	private Integer id;
	private Integer inspectProjectId;
	private String problem;
	private Integer isAccountability;
	private String accountabilityPerson;
	private String accountabilityPersonId;
	private Integer isChange;
	private Integer isFinish;
	private String changeMeasure;
	private String changeProgress;
	private String changeContent;
	private String changeLastTime;
	private String changeFollowPerson;
	private String changeFollowPersonId;
	private String changeAbutmentPerson;
	private String changeAbutmentPersonId;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer isDel;

	//附加字段页面显示使用
	private Integer year;
	private Integer inspectType;
	private String inspectTypeName;
	private String itemName;
	private String compId;
	private String compName;
	private Integer status;
	private Integer isChangeTimeout;
	private HhFile fileOne;
	
	// Constructors

	/** default constructor */
	public ReportInspectProjectProblem() {
	}

	public ReportInspectProjectProblem(Integer id, Integer inspectProjectId,
			String problem, Integer isAccountability,
			String accountabilityPerson, String accountabilityPersonId,
			Integer isChange, Integer isFinish, String changeMeasure,
			String changeProgress, String changeContent, String changeLastTime,
			String changeFollowPerson, String changeFollowPersonId,
			String changeAbutmentPerson, String changeAbutmentPersonId,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, Integer isDel, Integer year,
			Integer inspectType, String inspectTypeName, String itemName,
			String compId, String compName, Integer status,
			Integer isChangeTimeout, HhFile fileOne) {
		super();
		this.id = id;
		this.inspectProjectId = inspectProjectId;
		this.problem = problem;
		this.isAccountability = isAccountability;
		this.accountabilityPerson = accountabilityPerson;
		this.accountabilityPersonId = accountabilityPersonId;
		this.isChange = isChange;
		this.isFinish = isFinish;
		this.changeMeasure = changeMeasure;
		this.changeProgress = changeProgress;
		this.changeContent = changeContent;
		this.changeLastTime = changeLastTime;
		this.changeFollowPerson = changeFollowPerson;
		this.changeFollowPersonId = changeFollowPersonId;
		this.changeAbutmentPerson = changeAbutmentPerson;
		this.changeAbutmentPersonId = changeAbutmentPersonId;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.isDel = isDel;
		this.year = year;
		this.inspectType = inspectType;
		this.inspectTypeName = inspectTypeName;
		this.itemName = itemName;
		this.compId = compId;
		this.compName = compName;
		this.status = status;
		this.isChangeTimeout = isChangeTimeout;
		this.fileOne = fileOne;
	}
	
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInspectProjectId() {
		return this.inspectProjectId;
	}

	public void setInspectProjectId(Integer inspectProjectId) {
		this.inspectProjectId = inspectProjectId;
	}

	public String getProblem() {
		return this.problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public Integer getIsAccountability() {
		return this.isAccountability;
	}

	public void setIsAccountability(Integer isAccountability) {
		this.isAccountability = isAccountability;
	}

	public String getAccountabilityPerson() {
		return this.accountabilityPerson;
	}

	public void setAccountabilityPerson(String accountabilityPerson) {
		this.accountabilityPerson = accountabilityPerson;
	}

	public Integer getIsChange() {
		return this.isChange;
	}

	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}

	public Integer getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	public String getChangeMeasure() {
		return changeMeasure;
	}

	public void setChangeMeasure(String changeMeasure) {
		this.changeMeasure = changeMeasure;
	}

	public String getChangeProgress() {
		return changeProgress;
	}

	public void setChangeProgress(String changeProgress) {
		this.changeProgress = changeProgress;
	}

	public String getChangeContent() {
		return this.changeContent;
	}

	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}

	public String getChangeLastTime() {
		return this.changeLastTime;
	}

	public void setChangeLastTime(String changeLastTime) {
		this.changeLastTime = changeLastTime;
	}

	public String getChangeFollowPerson() {
		return this.changeFollowPerson;
	}

	public void setChangeFollowPerson(String changeFollowPerson) {
		this.changeFollowPerson = changeFollowPerson;
	}

	public String getChangeAbutmentPerson() {
		return this.changeAbutmentPerson;
	}

	public void setChangeAbutmentPerson(String changeAbutmentPerson) {
		this.changeAbutmentPerson = changeAbutmentPerson;
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

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	} 

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Integer getInspectType() {
		return inspectType;
	}

	public void setInspectType(Integer inspectType) {
		this.inspectType = inspectType;
	}

	public String getInspectTypeName() {
		return inspectTypeName;
	}

	public void setInspectTypeName(String inspectTypeName) {
		this.inspectTypeName = inspectTypeName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsChangeTimeout() {
		return isChangeTimeout;
	}

	public void setIsChangeTimeout(Integer isChangeTimeout) {
		this.isChangeTimeout = isChangeTimeout;
	}

	public String getAccountabilityPersonId() {
		return accountabilityPersonId;
	}

	public void setAccountabilityPersonId(String accountabilityPersonId) {
		this.accountabilityPersonId = accountabilityPersonId;
	}

	public String getChangeFollowPersonId() {
		return changeFollowPersonId;
	}

	public void setChangeFollowPersonId(String changeFollowPersonId) {
		this.changeFollowPersonId = changeFollowPersonId;
	}

	public String getChangeAbutmentPersonId() {
		return changeAbutmentPersonId;
	}

	public void setChangeAbutmentPersonId(String changeAbutmentPersonId) {
		this.changeAbutmentPersonId = changeAbutmentPersonId;
	}

	public HhFile getFileOne() {
		return fileOne;
	}

	public void setFileOne(HhFile fileOne) {
		this.fileOne = fileOne;
	}
}
