package com.softline.entity.bylaw;

/**
 * BylawInfo entity. @author MyEclipse Persistence Tools
 */

public class BylawInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String org;
	private String orgNm;
	private Integer deptId;
	private String fileId;
	private String fileDocType;
	private String fileName;
	private String fileNumber;
	private String fileEffectiveTime;
	private String fileIsAbolish;
	private String fileDocDueDate;
	private String fileAbolishReason;
	private String fileStatus;
	private String fileTitle;
	private String filePath;
	private Integer fileLevel;
	private Integer parentId;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer isLinked; //是否关联 0没有关联 1关联
	private String coreOrg;
	private String coreOrgNm;
	private Integer haveLevel;
	private String bylawTitle;//层级类别标题
	private Integer isCoreOrg;//是否是核心企业
	
	//formular
	private String deptName;

	//temp
	private String linkDiscription;//建立的关联描述
	// Constructors

	/** default constructor */
	public BylawInfo() {
	}

	/** full constructor */
	public BylawInfo(String org, String orgNm, Integer deptId, String fileId,
			String fileDocType, String fileName, String fileNumber,
			String fileEffectiveTime, String fileIsAbolish,
			String fileDocDueDate, String fileAbolishReason, String fileStatus,
			String fileTitle, String filePath, Integer fileLevel,
			Integer parentId, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate) {
		this.org = org;
		this.orgNm = orgNm;
		this.deptId = deptId;
		this.fileId = fileId;
		this.fileDocType = fileDocType;
		this.fileName = fileName;
		this.fileNumber = fileNumber;
		this.fileEffectiveTime = fileEffectiveTime;
		this.fileIsAbolish = fileIsAbolish;
		this.fileDocDueDate = fileDocDueDate;
		this.fileAbolishReason = fileAbolishReason;
		this.fileStatus = fileStatus;
		this.fileTitle = fileTitle;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
		this.parentId = parentId;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrg() {
		return this.org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrgNm() {
		return this.orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileDocType() {
		return this.fileDocType;
	}

	public void setFileDocType(String fileDocType) {
		this.fileDocType = fileDocType;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNumber() {
		return this.fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getFileEffectiveTime() {
		return this.fileEffectiveTime;
	}

	public void setFileEffectiveTime(String fileEffectiveTime) {
		this.fileEffectiveTime = fileEffectiveTime;
	}

	public String getFileIsAbolish() {
		return this.fileIsAbolish;
	}

	public void setFileIsAbolish(String fileIsAbolish) {
		this.fileIsAbolish = fileIsAbolish;
	}

	public String getFileDocDueDate() {
		return this.fileDocDueDate;
	}

	public void setFileDocDueDate(String fileDocDueDate) {
		this.fileDocDueDate = fileDocDueDate;
	}

	public String getFileAbolishReason() {
		return this.fileAbolishReason;
	}

	public void setFileAbolishReason(String fileAbolishReason) {
		this.fileAbolishReason = fileAbolishReason;
	}

	public String getFileStatus() {
		return this.fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getFileTitle() {
		return this.fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getFileLevel() {
		return this.fileLevel;
	}

	public void setFileLevel(Integer fileLevel) {
		this.fileLevel = fileLevel;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public Integer getIsLinked() {
		return isLinked;
	}

	public void setIsLinked(Integer isLinked) {
		this.isLinked = isLinked;
	}

	public String getCoreOrg() {
		return coreOrg;
	}

	public void setCoreOrg(String coreOrg) {
		this.coreOrg = coreOrg;
	}

	public String getCoreOrgNm() {
		return coreOrgNm;
	}

	public void setCoreOrgNm(String coreOrgNm) {
		this.coreOrgNm = coreOrgNm;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getHaveLevel() {
		return haveLevel;
	}

	public void setHaveLevel(Integer haveLevel) {
		this.haveLevel = haveLevel;
	}

	public String getBylawTitle() {
		return bylawTitle;
	}

	public void setBylawTitle(String bylawTitle) {
		this.bylawTitle = bylawTitle;
	}

	public String getLinkDiscription() {
		return linkDiscription;
	}

	public void setLinkDiscription(String linkDiscription) {
		this.linkDiscription = linkDiscription;
	}

	public Integer getIsCoreOrg() {
		return isCoreOrg;
	}

	public void setIsCoreOrg(Integer isCoreOrg) {
		this.isCoreOrg = isCoreOrg;
	}

	
	
	
}