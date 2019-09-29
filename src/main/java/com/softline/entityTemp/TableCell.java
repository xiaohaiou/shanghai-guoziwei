package com.softline.entityTemp;

import java.util.List;

import com.softline.entity.ReportForms;

public class TableCell {
	
	private String value;
	//起始行
	private Integer startrow;
	//起始列
	private Integer startcol;
	//终止行
	private Integer endrow;
	//终止列
	private Integer endcol;
	//样式
	private String cellcss;
	//列指标ID
	private Integer indexIdcol;
	//行指标ID
	private Integer indexIdrow;
	//是否是数据
	private Boolean isdata;
	//单位
	private Integer unit;
	//是否受单位影响
	private Boolean isunit;
	//跨列数
	private Integer colspan;
	//跨行数
	private Integer rowspan;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getStartrow() {
		return startrow;
	}
	public void setStartrow(Integer startrow) {
		this.startrow = startrow;
	}
	public Integer getStartcol() {
		return startcol;
	}
	public void setStartcol(Integer startcol) {
		this.startcol = startcol;
	}
	public Integer getEndrow() {
		return endrow;
	}
	public void setEndrow(Integer endrow) {
		this.endrow = endrow;
	}
	public Integer getEndcol() {
		return endcol;
	}
	public void setEndcol(Integer endcol) {
		this.endcol = endcol;
	}
	public String getCellcss() {
		return cellcss;
	}
	public void setCellcss(String cellcss) {
		this.cellcss = cellcss;
	}
	public Integer getIndexIdcol() {
		return indexIdcol;
	}
	public void setIndexIdcol(Integer indexIdcol) {
		this.indexIdcol = indexIdcol;
	}
	public Integer getIndexIdrow() {
		return indexIdrow;
	}
	public void setIndexIdrow(Integer indexIdrow) {
		this.indexIdrow = indexIdrow;
	}
	public Boolean getIsdata() {
		return isdata;
	}
	public void setIsdata(Boolean isdata) {
		this.isdata = isdata;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public Boolean getIsunit() {
		return isunit;
	}
	public void setIsunit(Boolean isunit) {
		this.isunit = isunit;
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
	public TableCell(String value, Integer startrow, Integer startcol,
			Integer endrow, Integer endcol, String cellcss, Integer indexIdcol,
			Integer indexIdrow, Boolean isdata, Integer unit, Boolean isunit,
			Integer colspan, Integer rowspan) {
		super();
		this.value = value;
		this.startrow = startrow;
		this.startcol = startcol;
		this.endrow = endrow;
		this.endcol = endcol;
		this.cellcss = cellcss;
		this.indexIdcol = indexIdcol;
		this.indexIdrow = indexIdrow;
		this.isdata = isdata;
		this.unit = unit;
		this.isunit = isunit;
		this.colspan = colspan;
		this.rowspan = rowspan;
	}
	public TableCell() {
		super();
	}
	
	
}
