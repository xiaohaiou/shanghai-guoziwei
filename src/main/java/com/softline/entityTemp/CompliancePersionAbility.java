package com.softline.entityTemp;

public class CompliancePersionAbility {

	private String projectName;
	private Integer datatype;
	private String problem;
	private String accountabilityPerson;
	private String accountabilityPersonId;
	private Integer projectId;
	private Integer situationId;
	private Integer situationPersonId;
	private Integer status;
	public CompliancePersionAbility() {
		super();
	}
	public CompliancePersionAbility(String projectName, Integer datatype,
			String problem, String accountabilityPerson,Integer projectId,Integer situationId,Integer situationPersonId,String accountabilityPersonId,Integer status) {
		super();
		this.projectName = projectName;
		this.datatype = datatype;
		this.problem = problem;
		this.accountabilityPerson = accountabilityPerson;
		this.projectId=projectId;
		this.situationId=situationId;
		this.situationPersonId=situationPersonId;
		this.accountabilityPersonId =accountabilityPersonId;
		this.status=status;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getDatatype() {
		return datatype;
	}
	public void setDatatype(Integer datatype) {
		this.datatype = datatype;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getAccountabilityPerson() {
		return accountabilityPerson;
	}
	public void setAccountabilityPerson(String accountabilityPerson) {
		this.accountabilityPerson = accountabilityPerson;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getSituationId() {
		return situationId;
	}
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}
	public Integer getSituationPersonId() {
		return situationPersonId;
	}
	public void setSituationPersonId(Integer situationPersonId) {
		this.situationPersonId = situationPersonId;
	}
	public String getAccountabilityPersonId() {
		return accountabilityPersonId;
	}
	public void setAccountabilityPersonId(String accountabilityPersonId) {
		this.accountabilityPersonId = accountabilityPersonId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
