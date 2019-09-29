package com.softline.entity.settingCenter;

import java.util.List;

import com.softline.entity.HhUsers;

/**
 * HhRole entity. @author MyEclipse Persistence Tools
 */

public class HhRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer sysId;
	private String roleNum;
	private String roleName;
	private String roleDescription;
	private Integer isUse;
	private Integer isDel;
	private String creator;
	private String createTime;
	private String modifier;
	private String modifyTime;
	
	private String sysName;
	private Integer roleStatus;
	
	//temp 
	private List<HhUsers> users; // 该角色有的用户
	private String companyNames; //管理关系权限
	private String financeCompanyNames;//财务数据权限
	private String authTreeData;//角色权限树，包括模块、页面、功能

	// Constructors

	/** default constructor */
	public HhRole() {
	}

	/** full constructor */
	public HhRole(Integer sysId, String roleNum, String roleName,
			String roleDescription, Integer isUse, Integer isDel,
			String creator, String createTime, String modifier,
			String modifyTime, Integer roleStatus) {
		this.sysId = sysId;
		this.roleNum = roleNum;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.isUse = isUse;
		this.isDel = isDel;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.roleStatus = roleStatus;
	}

	// Property accessors

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysId() {
		return this.sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public String getRoleNum() {
		return this.roleNum;
	}

	public void setRoleNum(String roleNum) {
		this.roleNum = roleNum;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Integer getIsUse() {
		return this.isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public List<HhUsers> getUsers() {
		return users;
	}

	public void setUsers(List<HhUsers> users) {
		this.users = users;
	}

	public String getCompanyNames() {
		return companyNames;
	}

	public void setCompanyNames(String companyNames) {
		this.companyNames = companyNames;
	}

	public String getFinanceCompanyNames() {
		return financeCompanyNames;
	}

	public void setFinanceCompanyNames(String financeCompanyNames) {
		this.financeCompanyNames = financeCompanyNames;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAuthTreeData() {
		return authTreeData;
	}

	public void setAuthTreeData(String authTreeData) {
		this.authTreeData = authTreeData;
	}

}