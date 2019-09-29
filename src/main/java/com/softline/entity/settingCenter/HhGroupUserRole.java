package com.softline.entity.settingCenter;

/**
 * HhUsersrole entity. @author MyEclipse Persistence Tools
 */

public class HhGroupUserRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer roleId;
	private Integer groupId;
	
	
	public HhGroupUserRole() {
		super();
	}
	public HhGroupUserRole(Integer id, Integer roleId, Integer groupId) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.groupId = groupId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// Constructors


}