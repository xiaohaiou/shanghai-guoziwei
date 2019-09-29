package com.softline.entity;

/**
 * ReportFormsPattend entity. @author MyEclipse Persistence Tools
 */

public class ReportFormsPattend implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer isdel;
	private Integer formsId;
	private Integer startrow;
	private Integer startcol;
	private Integer endrow;
	private Integer endcol;
	private String cellcss;
	private Integer indexIdcol;
	private Integer indexIdrow;
	private Boolean isdata;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer unit;
	private Boolean isunit;
	private String value;
	private Integer colspan;
	private Integer rowspan;
	private String formula;
	private Boolean isformula;
	// Constructors

	/** default constructor */
	public ReportFormsPattend() {
	}

	/** minimal constructor */
	public ReportFormsPattend(Integer isdel, Integer formsId, Integer startrow,
			Integer startcol, Integer endrow, Integer endcol,
			Integer indexIdcol, Boolean isdata) {
		this.isdel = isdel;
		this.formsId = formsId;
		this.startrow = startrow;
		this.startcol = startcol;
		this.endrow = endrow;
		this.endcol = endcol;
		this.indexIdcol = indexIdcol;
		this.isdata = isdata;
	}

	/** full constructor */
	public ReportFormsPattend(Integer isdel, Integer formsId, Integer startrow,
			Integer startcol, Integer endrow, Integer endcol, String cellcss,
			Integer indexIdcol, Integer indexIdrow, Boolean isdata,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, Integer unit, Boolean isunit, String value,Integer colspan,Integer rowspan,String formalu) {
		this.isdel = isdel;
		this.formsId = formsId;
		this.startrow = startrow;
		this.startcol = startcol;
		this.endrow = endrow;
		this.endcol = endcol;
		this.cellcss = cellcss;
		this.indexIdcol = indexIdcol;
		this.indexIdrow = indexIdrow;
		this.isdata = isdata;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.unit = unit;
		this.isunit = isunit;
		this.value = value;
		this.colspan=colspan;
		this.rowspan=rowspan;
		this.formula=formalu;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getColspan() {
		return colspan;
	}

	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}

	public Integer getRowspan() {
		return rowspan;
	}

	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getFormsId() {
		return this.formsId;
	}

	public void setFormsId(Integer formsId) {
		this.formsId = formsId;
	}

	public Integer getStartrow() {
		return this.startrow;
	}

	public void setStartrow(Integer startrow) {
		this.startrow = startrow;
	}

	public Integer getStartcol() {
		return this.startcol;
	}

	public void setStartcol(Integer startcol) {
		this.startcol = startcol;
	}

	public Integer getEndrow() {
		return this.endrow;
	}

	public void setEndrow(Integer endrow) {
		this.endrow = endrow;
	}

	public Integer getEndcol() {
		return this.endcol;
	}

	public void setEndcol(Integer endcol) {
		this.endcol = endcol;
	}

	public String getCellcss() {
		return this.cellcss;
	}

	public void setCellcss(String cellcss) {
		this.cellcss = cellcss;
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

	public Boolean getIsdata() {
		return this.isdata;
	}

	public void setIsdata(Boolean isdata) {
		this.isdata = isdata;
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

	public Integer getUnit() {
		return this.unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Boolean getIsunit() {
		return this.isunit;
	}

	public void setIsunit(Boolean isunit) {
		this.isunit = isunit;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Boolean getIsformula() {
		return isformula;
	}

	public void setIsformula(Boolean isformula) {
		this.isformula = isformula;
	}


}