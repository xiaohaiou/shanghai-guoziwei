package com.softline.entityTemp;

public class Report_Forms_Organ_View {
	
	//报表实例ID
	private Integer id;
	//机构ID
	private String organalID;
	//机构名
	private String organalName;
	//报表状态（上报，未上报）
	private Integer formStatus;
	private String formStatusName;

	//报表频度
	private Integer fre;
	private String freName;

	//报表种类
	private Integer form;
	private String formName;
	
	//报表组名
	private Integer groupID;
	private String groupName;

	//报表时间
	private String reportTime;
	
	
	////查询用字段
	private String startTime;
	private String endTime;
	private String checkedCompany;
	private String checkedCompanyName;
	
	public String getCheckedCompanyName() {
		return checkedCompanyName;
	}


	public void setCheckedCompanyName(String checkedCompanyName) {
		this.checkedCompanyName = checkedCompanyName;
	}


	public String getCheckedCompany() {
		return checkedCompany;
	}


	public void setCheckedCompany(String checkedCompany) {
		this.checkedCompany = checkedCompany;
	}


	public Report_Forms_Organ_View() {
		super();
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getOrganalID() {
		return organalID;
	}


	public void setOrganalID(String organalID) {
		this.organalID = organalID;
	}


	public String getOrganalName() {
		return organalName;
	}


	public void setOrganalName(String organalName) {
		this.organalName = organalName;
	}


	public Integer getFormStatus() {
		return formStatus;
	}


	public void setFormStatus(Integer formStatus) {
		this.formStatus = formStatus;
	}


	public String getFormStatusName() {
		return formStatusName;
	}


	public void setFormStatusName(String formStatusName) {
		this.formStatusName = formStatusName;
	}


	public Integer getFre() {
		return fre;
	}


	public void setFre(Integer fre) {
		this.fre = fre;
	}


	public String getFreName() {
		return freName;
	}


	public void setFreName(String freName) {
		this.freName = freName;
	}


	public Integer getForm() {
		return form;
	}


	public void setForm(Integer form) {
		this.form = form;
	}


	public String getFormName() {
		return formName;
	}


	public void setFormName(String formName) {
		this.formName = formName;
	}


	public Integer getGroupID() {
		return groupID;
	}


	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getReportTime() {
		return reportTime;
	}


	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}


	public Report_Forms_Organ_View(Integer id, String organalID,
			String organalName, Integer formStatus, String formStatusName,
			Integer fre, String freName, Integer form, String formName,
			Integer groupID, String groupName, String reportTime) {
		super();
		this.id = id;
		this.organalID = organalID;
		this.organalName = organalName;
		this.formStatus = formStatus;
		this.formStatusName = formStatusName;
		this.fre = fre;
		this.freName = freName;
		this.form = form;
		this.formName = formName;
		this.groupID = groupID;
		this.groupName = groupName;
		this.reportTime = reportTime;
	}
	
	
}
