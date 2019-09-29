package com.softline.entity.settingCenter;

/**
 * HhPageregister entity. @author MyEclipse Persistence Tools
 */

public class HhRoleModel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer roleId;
	private Integer modelId;
	private Integer sysId;
	
	//formula
	private String modelName;
	
	// Constructors

	/** default constructor */
	public HhRoleModel() {
	}
	
	/** full constructor */
	public HhRoleModel(Integer id, Integer roleId, Integer modelId, Integer sysId) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.modelId = modelId;
		this.sysId = sysId;
	}

	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
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

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}