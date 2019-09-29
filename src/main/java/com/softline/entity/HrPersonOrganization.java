package com.softline.entity;

public class HrPersonOrganization implements java.io.Serializable{
	
	private Integer id;
	private Integer isdel;
	private Integer groupId;
	private Integer businesscompany;
	private String companyname;
	private String post;
	private Integer postkind;
	private String otherWorkRemark;
	private Integer postStatus;
	private Integer manageLevel;
	private Integer numberPeopleA;
	private Integer actualNumberPeopleB;
	private String nameActualPeople;
	private Integer actualLevel;
	private Integer superLack;
	private String remark;
	
	private String postkindName;
	private String postStatusName;
	private String manageLevelName;
	private String actualLevelName;
	private String businesscompanyName;
	
	
	public HrPersonOrganization() {
	}


	public HrPersonOrganization(Integer id, Integer isdel, Integer groupId,
			Integer businesscompany, String companyname, String post,
			Integer postkind, String otherWorkRemark, Integer postStatus,
			Integer manageLevel, Integer numberPeopleA,
			Integer actualNumberPeopleB, String nameActualPeople,
			Integer actualLevel, Integer superLack, String remark,
			String postkindName, String postStatusName, String manageLevelName,
			String actualLevelName, String businesscompanyName) {
		super();
		this.id = id;
		this.isdel = isdel;
		this.groupId = groupId;
		this.businesscompany = businesscompany;
		this.companyname = companyname;
		this.post = post;
		this.postkind = postkind;
		this.otherWorkRemark = otherWorkRemark;
		this.postStatus = postStatus;
		this.manageLevel = manageLevel;
		this.numberPeopleA = numberPeopleA;
		this.actualNumberPeopleB = actualNumberPeopleB;
		this.nameActualPeople = nameActualPeople;
		this.actualLevel = actualLevel;
		this.superLack = superLack;
		this.remark = remark;
		this.postkindName = postkindName;
		this.postStatusName = postStatusName;
		this.manageLevelName = manageLevelName;
		this.actualLevelName = actualLevelName;
		this.businesscompanyName = businesscompanyName;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getIsdel() {
		return isdel;
	}


	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}


	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public Integer getBusinesscompany() {
		return businesscompany;
	}


	public void setBusinesscompany(Integer businesscompany) {
		this.businesscompany = businesscompany;
	}


	public String getCompanyname() {
		return companyname;
	}


	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}


	public String getPost() {
		return post;
	}


	public void setPost(String post) {
		this.post = post;
	}


	public Integer getPostkind() {
		return postkind;
	}


	public void setPostkind(Integer postkind) {
		this.postkind = postkind;
	}


	public String getOtherWorkRemark() {
		return otherWorkRemark;
	}


	public void setOtherWorkRemark(String otherWorkRemark) {
		this.otherWorkRemark = otherWorkRemark;
	}


	public Integer getPostStatus() {
		return postStatus;
	}


	public void setPostStatus(Integer postStatus) {
		this.postStatus = postStatus;
	}


	public Integer getManageLevel() {
		return manageLevel;
	}


	public void setManageLevel(Integer manageLevel) {
		this.manageLevel = manageLevel;
	}


	public Integer getNumberPeopleA() {
		return numberPeopleA;
	}


	public void setNumberPeopleA(Integer numberPeopleA) {
		this.numberPeopleA = numberPeopleA;
	}


	public Integer getActualNumberPeopleB() {
		return actualNumberPeopleB;
	}


	public void setActualNumberPeopleB(Integer actualNumberPeopleB) {
		this.actualNumberPeopleB = actualNumberPeopleB;
	}


	public String getNameActualPeople() {
		return nameActualPeople;
	}


	public void setNameActualPeople(String nameActualPeople) {
		this.nameActualPeople = nameActualPeople;
	}


	public Integer getActualLevel() {
		return actualLevel;
	}


	public void setActualLevel(Integer actualLevel) {
		this.actualLevel = actualLevel;
	}


	public Integer getSuperLack() {
		return superLack;
	}


	public void setSuperLack(Integer superLack) {
		this.superLack = superLack;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getPostkindName() {
		return postkindName;
	}


	public void setPostkindName(String postkindName) {
		this.postkindName = postkindName;
	}


	public String getPostStatusName() {
		return postStatusName;
	}


	public void setPostStatusName(String postStatusName) {
		this.postStatusName = postStatusName;
	}


	public String getManageLevelName() {
		return manageLevelName;
	}


	public void setManageLevelName(String manageLevelName) {
		this.manageLevelName = manageLevelName;
	}


	public String getActualLevelName() {
		return actualLevelName;
	}


	public void setActualLevelName(String actualLevelName) {
		this.actualLevelName = actualLevelName;
	}


	public String getBusinesscompanyName() {
		return businesscompanyName;
	}


	public void setBusinesscompanyName(String businesscompanyName) {
		this.businesscompanyName = businesscompanyName;
	}
	
	
}
