package com.softline.entity;

/**
 * HhOrganInfoTreeRelation entity. @author MyEclipse Persistence Tools
 */

public class HhOrganInfoTreeRelationLog implements java.io.Serializable {

	// Fields
	private int id;
	private String year;
	private String month;
	private String day;
	private String nNodeId;
	private String vcFullName;
	private String vcOrganID;
	private String vcParentID;
	private int isdel;
	private String operate_time;
	private String operate_type;
	private String operate_desc;
	
	public HhOrganInfoTreeRelationLog(){super();};
	
	public HhOrganInfoTreeRelationLog(int id, String year, String month,
			String day, String nNodeId, String vcFullName, String vcOrganID,
			String vcParentID, int isdel, String operate_time,
			String operate_type, String operate_desc) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.day = day;
		this.nNodeId = nNodeId;
		this.vcFullName = vcFullName;
		this.vcOrganID = vcOrganID;
		this.vcParentID = vcParentID;
		this.isdel = isdel;
		this.operate_time = operate_time;
		this.operate_type = operate_type;
		this.operate_desc = operate_desc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getnNodeId() {
		return nNodeId;
	}
	public void setnNodeId(String nNodeId) {
		this.nNodeId = nNodeId;
	}
	public String getVcFullName() {
		return vcFullName;
	}
	public void setVcFullName(String vcFullName) {
		this.vcFullName = vcFullName;
	}
	public String getVcOrganID() {
		return vcOrganID;
	}
	public void setVcOrganID(String vcOrganID) {
		this.vcOrganID = vcOrganID;
	}
	public String getVcParentID() {
		return vcParentID;
	}
	public void setVcParentID(String vcParentID) {
		this.vcParentID = vcParentID;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public String getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	public String getOperate_desc() {
		return operate_desc;
	}
	public void setOperate_desc(String operate_desc) {
		this.operate_desc = operate_desc;
	}
	
}