package com.softline.entity;

import java.util.List;

/**
 * HrPersonitem entity. @author MyEclipse Persistence Tools
 */

public class HrPersonitem implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer isdel;
	private String post;
	private Integer postkind;
	private String otherWorkRemark;
	private Integer postStatus;
	private Integer manageLevel;
	private String name;
	private Integer sex;
	private String birthday;
	private Integer age;
	private Integer workage;
	private String setupTime;
	private Integer education;
	private String school;
	private Integer schoollevel;
	private Integer schoolqualified;
	private String major;
	private Integer isfinance;
	private Integer financialtitle;
	private Integer titlequalified;
	private String titlename;
	private Integer englishlevel;
	private Integer englishqualified;
	private String englishlevelremark;
	private String workplace;
	private Integer overseasexperience;
	private Integer businesscompany;
	private Integer groupId;
	private String companyname;
	
	
	private String postkindName;
	private String postStatusName;
	private String manageLevelName;
	
	private String sexName;
	private String educationName;
	
	private Integer learnfun;//学习形式 20180321
	private String learnfunName;
	
	private String schoollevelName;
	private String schoolqualifiedName;
	
	private String isfinanceName;
	private String financialtitleName;
	
	private String titlequalifiedName;
	private String englishlevelName;
	
	private String englishqualifiedName;
	private String overseasexperienceName;
	
	private String businesscompanyName;
	
	
	
	public HrPersonitem() {
		super();
	}



	public HrPersonitem(Integer id, Integer isdel, String post,
			Integer postkind, String otherWorkRemark, Integer postStatus,
			Integer manageLevel, String name, Integer sex, String birthday,
			Integer age, Integer workage, String setupTime, Integer education,
			String school, Integer schoollevel, Integer schoolqualified,
			String major, Integer isfinance, Integer financialtitle,
			Integer titlequalified, String titlename, Integer englishlevel,
			Integer englishqualified, String englishlevelremark,
			String workplace, Integer overseasexperience,
			Integer businesscompany) {
		super();
		this.id = id;
		this.isdel = isdel;
		this.post = post;
		this.postkind = postkind;
		this.otherWorkRemark = otherWorkRemark;
		this.postStatus = postStatus;
		this.manageLevel = manageLevel;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.age = age;
		this.workage = workage;
		this.setupTime = setupTime;
		this.education = education;
		this.school = school;
		this.schoollevel = schoollevel;
		this.schoolqualified = schoolqualified;
		this.major = major;
		this.isfinance = isfinance;
		this.financialtitle = financialtitle;
		this.titlequalified = titlequalified;
		this.titlename = titlename;
		this.englishlevel = englishlevel;
		this.englishqualified = englishqualified;
		this.englishlevelremark = englishlevelremark;
		this.workplace = workplace;
		this.overseasexperience = overseasexperience;
		this.businesscompany = businesscompany;
	}

	

	



	public String getCompanyname() {
		return companyname;
	}



	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}



	public Integer getGroupId() {
		return groupId;
	}



	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}



	public Integer getId() {
		return id;
	}



	public Integer getIsdel() {
		return isdel;
	}



	public String getPost() {
		return post;
	}



	public Integer getPostkind() {
		return postkind;
	}



	public String getOtherWorkRemark() {
		return otherWorkRemark;
	}



	public Integer getPostStatus() {
		return postStatus;
	}



	public Integer getManageLevel() {
		return manageLevel;
	}



	public String getName() {
		return name;
	}



	public Integer getSex() {
		return sex;
	}



	public String getBirthday() {
		return birthday;
	}



	public Integer getAge() {
		return age;
	}



	public Integer getWorkage() {
		return workage;
	}



	public String getSetupTime() {
		return setupTime;
	}



	public Integer getEducation() {
		return education;
	}



	public String getSchool() {
		return school;
	}



	public Integer getSchoollevel() {
		return schoollevel;
	}



	public Integer getSchoolqualified() {
		return schoolqualified;
	}



	public String getMajor() {
		return major;
	}



	public Integer getIsfinance() {
		return isfinance;
	}



	public Integer getFinancialtitle() {
		return financialtitle;
	}



	public Integer getTitlequalified() {
		return titlequalified;
	}



	public String getTitlename() {
		return titlename;
	}



	public Integer getEnglishlevel() {
		return englishlevel;
	}



	public Integer getEnglishqualified() {
		return englishqualified;
	}



	public String getEnglishlevelremark() {
		return englishlevelremark;
	}



	public String getWorkplace() {
		return workplace;
	}



	public Integer getOverseasexperience() {
		return overseasexperience;
	}



	public Integer getBusinesscompany() {
		return businesscompany;
	}



	public String getPostkindName() {
		return postkindName;
	}



	public String getPostStatusName() {
		return postStatusName;
	}



	public String getManageLevelName() {
		return manageLevelName;
	}



	public String getSexName() {
		return sexName;
	}



	public String getEducationName() {
		return educationName;
	}



	public String getSchoollevelName() {
		return schoollevelName;
	}



	public String getSchoolqualifiedName() {
		return schoolqualifiedName;
	}



	public String getIsfinanceName() {
		return isfinanceName;
	}



	public String getFinancialtitleName() {
		return financialtitleName;
	}



	public String getTitlequalifiedName() {
		return titlequalifiedName;
	}



	public String getEnglishlevelName() {
		return englishlevelName;
	}



	public String getEnglishqualifiedName() {
		return englishqualifiedName;
	}



	public String getOverseasexperienceName() {
		return overseasexperienceName;
	}



	public String getBusinesscompanyName() {
		return businesscompanyName;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}



	public void setPost(String post) {
		this.post = post;
	}



	public void setPostkind(Integer postkind) {
		this.postkind = postkind;
	}



	public void setOtherWorkRemark(String otherWorkRemark) {
		this.otherWorkRemark = otherWorkRemark;
	}



	public void setPostStatus(Integer postStatus) {
		this.postStatus = postStatus;
	}



	public void setManageLevel(Integer manageLevel) {
		this.manageLevel = manageLevel;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setSex(Integer sex) {
		this.sex = sex;
	}



	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public void setWorkage(Integer workage) {
		this.workage = workage;
	}



	public void setSetupTime(String setupTime) {
		this.setupTime = setupTime;
	}



	public void setEducation(Integer education) {
		this.education = education;
	}



	public void setSchool(String school) {
		this.school = school;
	}



	public void setSchoollevel(Integer schoollevel) {
		this.schoollevel = schoollevel;
	}



	public void setSchoolqualified(Integer schoolqualified) {
		this.schoolqualified = schoolqualified;
	}



	public void setMajor(String major) {
		this.major = major;
	}



	public void setIsfinance(Integer isfinance) {
		this.isfinance = isfinance;
	}



	public void setFinancialtitle(Integer financialtitle) {
		this.financialtitle = financialtitle;
	}



	public void setTitlequalified(Integer titlequalified) {
		this.titlequalified = titlequalified;
	}



	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}



	public void setEnglishlevel(Integer englishlevel) {
		this.englishlevel = englishlevel;
	}



	public void setEnglishqualified(Integer englishqualified) {
		this.englishqualified = englishqualified;
	}



	public void setEnglishlevelremark(String englishlevelremark) {
		this.englishlevelremark = englishlevelremark;
	}



	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}



	public void setOverseasexperience(Integer overseasexperience) {
		this.overseasexperience = overseasexperience;
	}



	public void setBusinesscompany(Integer businesscompany) {
		this.businesscompany = businesscompany;
	}



	public void setPostkindName(String postkindName) {
		this.postkindName = postkindName;
	}



	public void setPostStatusName(String postStatusName) {
		this.postStatusName = postStatusName;
	}



	public void setManageLevelName(String manageLevelName) {
		this.manageLevelName = manageLevelName;
	}



	public void setSexName(String sexName) {
		this.sexName = sexName;
	}



	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}



	public void setSchoollevelName(String schoollevelName) {
		this.schoollevelName = schoollevelName;
	}



	public void setSchoolqualifiedName(String schoolqualifiedName) {
		this.schoolqualifiedName = schoolqualifiedName;
	}



	public void setIsfinanceName(String isfinanceName) {
		this.isfinanceName = isfinanceName;
	}



	public void setFinancialtitleName(String financialtitleName) {
		this.financialtitleName = financialtitleName;
	}



	public void setTitlequalifiedName(String titlequalifiedName) {
		this.titlequalifiedName = titlequalifiedName;
	}



	public void setEnglishlevelName(String englishlevelName) {
		this.englishlevelName = englishlevelName;
	}



	public void setEnglishqualifiedName(String englishqualifiedName) {
		this.englishqualifiedName = englishqualifiedName;
	}



	public void setOverseasexperienceName(String overseasexperienceName) {
		this.overseasexperienceName = overseasexperienceName;
	}



	public void setBusinesscompanyName(String businesscompanyName) {
		this.businesscompanyName = businesscompanyName;
	}



	public Integer getLearnfun() {
		return learnfun;
	}



	public void setLearnfun(Integer learnfun) {
		this.learnfun = learnfun;
	}



	public String getLearnfunName() {
		return learnfunName;
	}



	public void setLearnfunName(String learnfunName) {
		this.learnfunName = learnfunName;
	}


	
	
	
}