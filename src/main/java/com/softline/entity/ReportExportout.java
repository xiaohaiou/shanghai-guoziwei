package com.softline.entity;

/**
 * ReportExportout entity. @author MyEclipse Persistence Tools
 */

public class ReportExportout implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer grouptype;
	private String path;
	private String groupstarttime;
	private String groupendtime;
	private String remark;
	// Constructors

	/** default constructor */
	public ReportExportout() {
	}

	/** full constructor */
	public ReportExportout(Integer grouptype, String path,
			String groupstarttime, String groupendtime) {
		this.grouptype = grouptype;
		this.path = path;
		this.groupstarttime = groupstarttime;
		this.groupendtime = groupendtime;
	}

	// Property accessors

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGrouptype() {
		return this.grouptype;
	}

	public void setGrouptype(Integer grouptype) {
		this.grouptype = grouptype;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getGroupstarttime() {
		return this.groupstarttime;
	}

	public void setGroupstarttime(String groupstarttime) {
		this.groupstarttime = groupstarttime;
	}

	public String getGroupendtime() {
		return this.groupendtime;
	}

	public void setGroupendtime(String groupendtime) {
		this.groupendtime = groupendtime;
	}

}