package com.softline.entity;

public class KnowledgeBase implements java.io.Serializable{
	
	private Integer id;
	private String title;
	private String no;
	private Integer typeId;
	private String typeName;
	private Integer areaId;
	private String areaName;
	private String doDate;
	private Integer top;
	//必须字段
	private Integer isDel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	
	public KnowledgeBase() {
		super();
	}
	public KnowledgeBase(Integer id, String title, String no, Integer typeId,
			String typeName, Integer areaId, String areaName, String doDate,
			Integer top, Integer isDel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		super();
		this.id = id;
		this.title = title;
		this.no = no;
		this.typeId = typeId;
		this.typeName = typeName;
		this.areaId = areaId;
		this.areaName = areaName;
		this.doDate = doDate;
		this.top = top;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getDoDate() {
		return doDate;
	}
	public void setDoDate(String doDate) {
		this.doDate = doDate;
	}
	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
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

}
