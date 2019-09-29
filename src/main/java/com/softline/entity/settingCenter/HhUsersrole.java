package com.softline.entity.settingCenter;

/**
 * HhUsersrole entity. @author MyEclipse Persistence Tools
 */

public class HhUsersrole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer sysId;
	private String vcEmployeeId;
	private Integer roleId;
	private Integer roleNum;
	
	private String userName;
	private String roleName;
	private String vcFullName;
	private String vcAccount;

	// Constructors

	/** default constructor */
	public HhUsersrole() {
	}

	/** full constructor */
	public HhUsersrole(Integer sysId, String vcEmployeeId, Integer roleId,
			Integer roleNum, String userName, String roleName, String vcFullName,String vcAccount) {
		this.sysId = sysId;
		this.vcEmployeeId = vcEmployeeId;
		this.roleId = roleId;
		this.roleNum = roleNum;
		this.userName = userName;
		this.roleName = roleName;
		this.vcFullName = vcFullName;
		this.vcAccount = vcAccount;
	}

	// Property accessors

	public String getVcFullName() {
		return vcFullName;
	}

	public void setVcFullName(String vcFullName) {
		this.vcFullName = vcFullName;
	}

	public String getUserName() {
		return userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public String getVcEmployeeId() {
		return this.vcEmployeeId;
	}

	public void setVcEmployeeId(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleNum() {
		return this.roleNum;
	}

	public void setRoleNum(Integer roleNum) {
		this.roleNum = roleNum;
	}
	
	public String getVcAccount() {
		return vcAccount;
	}

	public void setVcAccount(String vcAccount) {
		this.vcAccount = vcAccount;
	}
}