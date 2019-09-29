package com.softline.entity.settingCenter;

/**
 * HhPageregister entity. @author MyEclipse Persistence Tools
 */

public class HhRoleSys implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer roleId;
	private Integer sysId;
	
	// Constructors

	/** default constructor */
	public HhRoleSys() {
	}
	
	/** full constructor */
	
	
	public HhRoleSys(Integer id, Integer roleId, Integer sysId) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.sysId = sysId;
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

	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}