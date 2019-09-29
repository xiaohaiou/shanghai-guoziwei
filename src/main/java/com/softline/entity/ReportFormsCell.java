package com.softline.entity;

/**
 * ReportFormsCell entity. @author MyEclipse Persistence Tools
 */

public class ReportFormsCell implements java.io.Serializable {

	// Fields

	private Integer id;
	private Boolean isformula;
	private String formulaStr;
	private String cellvalue;
	private Integer isdel;
	private Integer releatiionId;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer indexIdcol;
	private Integer indexIdrow;

	// Constructors

	/** default constructor */
	public ReportFormsCell() {
	}

	/** minimal constructor */
	public ReportFormsCell(Boolean isformula, Integer isdel,
			Integer releatiionId, Integer indexIdcol) {
		this.isformula = isformula;
		this.isdel = isdel;
		this.releatiionId = releatiionId;
		this.indexIdcol = indexIdcol;
	}

	/** full constructor */
	public ReportFormsCell(Boolean isformula, String formulaStr,
			String cellvalue, Integer isdel, Integer releatiionId,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, Integer indexIdcol, Integer indexIdrow) {
		this.isformula = isformula;
		this.formulaStr = formulaStr;
		this.cellvalue = cellvalue;
		this.isdel = isdel;
		this.releatiionId = releatiionId;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.indexIdcol = indexIdcol;
		this.indexIdrow = indexIdrow;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsformula() {
		return this.isformula;
	}

	public void setIsformula(Boolean isformula) {
		this.isformula = isformula;
	}

	public String getFormulaStr() {
		return this.formulaStr;
	}

	public void setFormulaStr(String formulaStr) {
		this.formulaStr = formulaStr;
	}

	public String getCellvalue() {
		return this.cellvalue;
	}

	public void setCellvalue(String cellvalue) {
		this.cellvalue = cellvalue;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getReleatiionId() {
		return this.releatiionId;
	}

	public void setReleatiionId(Integer releatiionId) {
		this.releatiionId = releatiionId;
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

	public Integer getIndexIdcol() {
		return this.indexIdcol;
	}

	public void setIndexIdcol(Integer indexIdcol) {
		this.indexIdcol = indexIdcol;
	}

	public Integer getIndexIdrow() {
		return this.indexIdrow;
	}

	public void setIndexIdrow(Integer indexIdrow) {
		this.indexIdrow = indexIdrow;
	}

}