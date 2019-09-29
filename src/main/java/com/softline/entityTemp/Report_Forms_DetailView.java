package com.softline.entityTemp;

import java.util.List;

import com.softline.entity.ReportForms;

public class Report_Forms_DetailView {
	//企业ID
	private String organID;
	//报表实例
	private String id;
	//企业名
	private String organName;
	//报表
	private ReportForms forms;
	
	private String createPersonName;
	
	private String createDate;
	
	private String reportPersonName;
	
	private String reportDate;
	
	
	private List<TableCell> cell;


	
	
	
	public String getCreatePersonName() {
		return createPersonName;
	}


	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public String getReportPersonName() {
		return reportPersonName;
	}


	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}


	public String getReportDate() {
		return reportDate;
	}


	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}


	public ReportForms getForms() {
		return forms;
	}


	public void setForms(ReportForms forms) {
		this.forms = forms;
	}


	public List<TableCell> getCell() {
		return cell;
	}


	public void setCell(List<TableCell> cell) {
		this.cell = cell;
	}


	public String getOrganID() {
		return organID;
	}


	public void setOrganID(String organID) {
		this.organID = organID;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getOrganName() {
		return organName;
	}


	public void setOrganName(String organName) {
		this.organName = organName;
	}



}
