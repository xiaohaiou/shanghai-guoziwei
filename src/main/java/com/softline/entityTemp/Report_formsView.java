package com.softline.entityTemp;

import java.util.ArrayList;
import java.util.List;

import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsGroup;

public class Report_formsView {
	
	private Integer id;
	private Integer formkind;
	private String formkindName;
	private Integer fre;
	private String freName;
	private Integer fileId;
	private String groupkindName;
	private Integer groupkindId;
	private String groupTime;
	private Integer type;
	private String typeName;
	private String month;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFreName() {
		return freName;
	}
	public void setFreName(String freName) {
		this.freName = freName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFormkind() {
		return formkind;
	}
	public void setFormkind(Integer formkind) {
		this.formkind = formkind;
	}
	
	public Integer getFre() {
		return fre;
	}
	public void setFre(Integer fre) {
		this.fre = fre;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
	public String getFormkindName() {
		return formkindName;
	}
	public void setFormkindName(String formkindName) {
		this.formkindName = formkindName;
	}
	public String getGroupTime() {
		return groupTime;
	}
	public void setGroupTime(String groupTime) {
		this.groupTime = groupTime;
	}
	public Report_formsView() {
		super();
	}
	public String getGroupkindName() {
		return groupkindName;
	}
	public void setGroupkindName(String groupkindName) {
		this.groupkindName = groupkindName;
	}
	public Integer getGroupkindId() {
		return groupkindId;
	}
	public void setGroupkindId(Integer groupkindId) {
		this.groupkindId = groupkindId;
	}
	public Report_formsView(Integer id, Integer formkind, String formkindName,
			Integer fre, String freName, Integer fileId, String groupkindName,
			Integer groupkindId, String groupTime) {
		super();
		this.id = id;
		this.formkind = formkind;
		this.formkindName = formkindName;
		this.fre = fre;
		this.freName = freName;
		this.fileId = fileId;
		this.groupkindName = groupkindName;
		this.groupkindId = groupkindId;
		this.groupTime = groupTime;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	
}
