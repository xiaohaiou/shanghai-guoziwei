package com.softline.entity;

public class KnowledgeStoreHouse implements java.io.Serializable{
	
	private Integer id;
	private String documentNo;
	private String documentName;
	private Integer organizationId;
	private Integer moduleId;
	private String year;
	private Integer category;
	private Integer category2;
	private Integer category3;
	//必须字段
	private Integer isDel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	//关联字段
	private String organizationName;
	private String moduleName;
	private HhFile hhFile;
	
	public KnowledgeStoreHouse() {
		super();
	}
	public KnowledgeStoreHouse(Integer id, String documentNo, String documentName, Integer organizationId,
			Integer moduleId, String year, Integer category, Integer category2, Integer category3,
			Integer isDel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		super();
		this.id = id;
		this.documentNo = documentNo;
		this.documentName = documentName;
		this.organizationId = organizationId;
		this.moduleId = moduleId;
		this.year = year;
		this.category = category;
		this.category2 = category2;
		this.category3 = category3;
		this.isDel = isDel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getCategory2() {
		return category2;
	}
	public void setCategory2(Integer category2) {
		this.category2 = category2;
	}
	public Integer getCategory3() {
		return category3;
	}
	public void setCategory3(Integer category3) {
		this.category3 = category3;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getCreatePersonName() {
		return createPersonName;
	}
	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}
	public String getCreatePersonId() {
		return createPersonId;
	}
	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastModifyPersonId() {
		return lastModifyPersonId;
	}
	public void setLastModifyPersonId(String lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}
	public String getLastModifyPersonName() {
		return lastModifyPersonName;
	}
	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public HhFile getHhFile() {
		return hhFile;
	}
	public void setHhFile(HhFile hhFile) {
		this.hhFile = hhFile;
	}
	
}
