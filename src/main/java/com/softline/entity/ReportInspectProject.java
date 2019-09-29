package com.softline.entity;

/**
 * ReportInspectProject entity. @author MyEclipse Persistence Tools
 */

public class ReportInspectProject implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer inspectType;
	private String compId;
	private String compName;
	private String belongCompId;
	private String belongCompName;
	private Integer itemType;
	private String itemName;
	private String itemPerson;
	private String itemPersonId;
	private String startTime;
	private String endTime;
	private String remark;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer status;
	private Integer isDel;
	private String parentorg;
	
	private String itemTypeName;
	private Integer isOrder;
	private String statusName;
	private String inspectTypeName;
	
	private HhFile fileOne;
	
	//formula
	private String authOrg;

	// Constructors

	/** default constructor */
	public ReportInspectProject() {
	}

	/** full constructor */
	public ReportInspectProject(Integer year, String compId,
			String belongCompId, Integer itemType, String itemName,
			String itemPerson, String startTime, String endTime, String remark,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		this.year = year;
		this.compId = compId;
		this.belongCompId = belongCompId;
		this.itemType = itemType;
		this.itemName = itemName;
		this.itemPerson = itemPerson;
		this.startTime = startTime;
		this.endTime = endTime;
		this.remark = remark;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
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

	public Integer getYear() {
		return this.year;
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

	public String getCompId() {
		return this.compId;
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

	public String getBelongCompId() {
		return this.belongCompId;
	}

	public void setBelongCompId(String belongCompId) {
		this.belongCompId = belongCompId;
	}

	public Integer getItemType() {
		return this.itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPerson() {
		return this.itemPerson;
	}

	public void setItemPerson(String itemPerson) {
		this.itemPerson = itemPerson;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public Integer getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(Integer isOrder) {
		this.isOrder = isOrder;
	}

	public String getBelongCompName() {
		return belongCompName;
	}

	public void setBelongCompName(String belongCompName) {
		this.belongCompName = belongCompName;
	}

	public String getItemPersonId() {
		return itemPersonId;
	}

	public void setItemPersonId(String itemPersonId) {
		this.itemPersonId = itemPersonId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public HhFile getFileOne() {
		return fileOne;
	}

	public void setFileOne(HhFile fileOne) {
		this.fileOne = fileOne;
	}

	public String getInspectTypeName() {
		return inspectTypeName;
	}

	public void setInspectTypeName(String inspectTypeName) {
		this.inspectTypeName = inspectTypeName;
	}

	public String getParentorg() {
		return parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

	
	
}