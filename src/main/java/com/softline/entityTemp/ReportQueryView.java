package com.softline.entityTemp;

import java.util.ArrayList;
import java.util.List;

import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsGroup;

public class ReportQueryView {
	
	
	private String financeTime;
	private String starttime;
	private String endtime;
	private Integer examinestatus;
	//报表组ID
	private Integer groupID;
	//企业ID
	private String organalID;
	//报表标题
	private String title;
	//单位
	private String organalname;
	//上报人
	private String reportpersonName;
	//创建人
	private String createpersonName;	
	//审核人
	private String auditorpersonName;
	//报表时间
	private String time;
	
	//上报时间
	private String reporttime;
	//审核状态
	private String examinestatusName;
	//报表组种类
	private Integer groupType;
	//报表组属性
	private Integer type;
	
	//操作方式，查询或者是审核
	private Integer getOperateType;
	
	private String parentorg;
	private String status;
	

	

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFinanceTime() {
		return financeTime;
	}
	public void setFinanceTime(String financeTime) {
		this.financeTime = financeTime;
	}
	public String getParentorg() {
		return parentorg;
	}
	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getGetOperateType() {
		return getOperateType;
	}
	public void setGetOperateType(Integer getOperateType) {
		this.getOperateType = getOperateType;
	}
	public Integer getGroupType() {
		return groupType;
	}
	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}
	public Integer getGroupID() {
		return groupID;
	}
	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}
	public String getOrganalID() {
		return organalID;
	}
	public void setOrganalID(String organalID) {
		this.organalID = organalID;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Integer getExaminestatus() {
		return examinestatus;
	}
	public void setExaminestatus(Integer examinestatus) {
		this.examinestatus = examinestatus;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrganalname() {
		return organalname;
	}
	public void setOrganalname(String organalname) {
		this.organalname = organalname;
	}
	public String getReportpersonName() {
		return reportpersonName;
	}
	public void setReportpersonName(String reportpersonName) {
		this.reportpersonName = reportpersonName;
	}
	public String getCreatepersonName() {
		return createpersonName;
	}
	public void setCreatepersonName(String createpersonName) {
		this.createpersonName = createpersonName;
	}
	public String getAuditorpersonName() {
		return auditorpersonName;
	}
	public void setAuditorpersonName(String auditorpersonName) {
		this.auditorpersonName = auditorpersonName;
	}
	public String getReporttime() {
		return reporttime;
	}
	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}
	public String getExaminestatusName() {
		return examinestatusName;
	}
	public void setExaminestatusName(String examinestatusName) {
		this.examinestatusName = examinestatusName;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public ReportQueryView(String financeTime,String starttime, String endtime,
			Integer examinestatus, Integer groupID, String organalID,
			String title, String organalname, String reportpersonName,
			String createpersonName, String auditorpersonName, String time,
			String reporttime, String examinestatusName,String status) {
		super();
		this.financeTime=financeTime;
		this.starttime = starttime;
		this.endtime = endtime;
		this.examinestatus = examinestatus;
		this.groupID = groupID;
		this.organalID = organalID;
		this.title = title;
		this.organalname = organalname;
		this.reportpersonName = reportpersonName;
		this.createpersonName = createpersonName;
		this.auditorpersonName = auditorpersonName;
		this.time = time;
		this.reporttime = reporttime;
		this.examinestatusName = examinestatusName;
		this.status=status;
	}
	public ReportQueryView() {
		super();
	}
	
	
	
}
