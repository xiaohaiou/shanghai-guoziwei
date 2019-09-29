package com.softline.entity.project;

/**
 * PjWeekreportdetail entity. @author MyEclipse Persistence Tools
 */

public class PjWeekreportdetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer weekreportId;
	private String typeName;
	private String thisWeek;
	private String nextWeek;

	// Constructors

	/** default constructor */
	public PjWeekreportdetail() {
	}

	/** full constructor */
	public PjWeekreportdetail(Integer weekreportId, String typeName,
			String thisWeek, String nextWeek) {
		this.weekreportId = weekreportId;
		this.typeName = typeName;
		this.thisWeek = thisWeek;
		this.nextWeek = nextWeek;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWeekreportId() {
		return this.weekreportId;
	}

	public void setWeekreportId(Integer weekreportId) {
		this.weekreportId = weekreportId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getThisWeek() {
		return this.thisWeek;
	}

	public void setThisWeek(String thisWeek) {
		this.thisWeek = thisWeek;
	}

	public String getNextWeek() {
		return this.nextWeek;
	}

	public void setNextWeek(String nextWeek) {
		this.nextWeek = nextWeek;
	}

}