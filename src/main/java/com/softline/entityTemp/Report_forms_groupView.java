package com.softline.entityTemp;

import java.util.ArrayList;
import java.util.List;

import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsGroup;

public class Report_forms_groupView {
	 //报表组
     private ReportFormsGroup group;
     //该报表组内的报表
     private List<ReportForms> group_item;
     private String group_itemstr;
     
	public Report_forms_groupView() {
		super();
		this.group=new ReportFormsGroup();
		this.group_item=new ArrayList<ReportForms>();
	}
	public Report_forms_groupView(ReportFormsGroup group,
			List<ReportForms> group_item) {
		super();
		this.group = group;
		this.group_item = group_item;
	}
	public ReportFormsGroup getGroup() {
		return group;
	}
	public void setGroup(ReportFormsGroup group) {
		this.group = group;
	}
	public List<ReportForms> getGroup_item() {
		return group_item;
	}
	public void setGroup_item(List<ReportForms> group_item) {
		this.group_item = group_item;
	}
	public String getGroup_itemstr() {
		return group_itemstr;
	}
	public void setGroup_itemstr(String group_itemstr) {
		this.group_itemstr = group_itemstr;
	}
     
     
}
