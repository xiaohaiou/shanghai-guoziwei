package com.softline.entity.bimr;

/**
 * BimrContractMonthCheck entity. @author MyEclipse Persistence Tools
 */

public class BimrContractMonthCheck implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String compId;
	private String checkPersonName;
	private String checkPersonId;
	private String checkDate;
	private String checkContext;
	private Integer isdel;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;

	// Constructors

	/** default constructor */
	public BimrContractMonthCheck() {
	}

	/** full constructor */
	public BimrContractMonthCheck(Integer year, Integer month, String compId,
			String checkPersonName, String checkPersonId, String checkDate,
			String checkContext, Integer isdel, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate) {
		this.year = year;
		this.month = month;
		this.compId = compId;
		this.checkPersonName = checkPersonName;
		this.checkPersonId = checkPersonId;
		this.checkDate = checkDate;
		this.checkContext = checkContext;
		this.isdel = isdel;
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

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getCheckPersonName() {
		return this.checkPersonName;
	}

	public void setCheckPersonName(String checkPersonName) {
		this.checkPersonName = checkPersonName;
	}

	public String getCheckPersonId() {
		return this.checkPersonId;
	}

	public void setCheckPersonId(String checkPersonId) {
		this.checkPersonId = checkPersonId;
	}

	public String getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckContext() {
		return this.checkContext;
	}

	public void setCheckContext(String checkContext) {
		this.checkContext = checkContext;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
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

}