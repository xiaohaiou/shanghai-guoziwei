package com.softline.entity.bimr;

/**
 * BimrCaseReport entity. @author MyEclipse Persistence Tools
 */

public class BimrCaseReport implements java.io.Serializable {

	private Integer id;
	private String year;
	private String week;
	private String civilcaseIds;
	private String criminalcaseIds;

	/** default constructor */
	public BimrCaseReport() {
	}

	/** full constructor */
	public BimrCaseReport(String year, String week, String civilcaseIds,
			String criminalcaseIds) {
		this.year = year;
		this.week = week;
		this.civilcaseIds = civilcaseIds;
		this.criminalcaseIds = criminalcaseIds;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getWeek() {
		return this.week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getCivilcaseIds() {
		return this.civilcaseIds;
	}

	public void setCivilcaseIds(String civilcaseIds) {
		this.civilcaseIds = civilcaseIds;
	}

	public String getCriminalcaseIds() {
		return this.criminalcaseIds;
	}

	public void setCriminalcaseIds(String criminalcaseIds) {
		this.criminalcaseIds = criminalcaseIds;
	}
}