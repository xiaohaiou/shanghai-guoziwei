package com.softline.entity;

/**
 * HhRolepage entity. @author MyEclipse Persistence Tools
 */

public class HhRolepage implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer portalId;
	private Integer roleId;
	private String roleNum;
	private Integer pageId;
	private String pageNum;

	// Constructors

	/** default constructor */
	public HhRolepage() {
	}

	/** full constructor */
	public HhRolepage(Integer portalId, Integer roleId, String roleNum,
			Integer pageId, String pageNum) {
		this.portalId = portalId;
		this.roleId = roleId;
		this.roleNum = roleNum;
		this.pageId = pageId;
		this.pageNum = pageNum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPortalId() {
		return this.portalId;
	}

	public void setPortalId(Integer portalId) {
		this.portalId = portalId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleNum() {
		return this.roleNum;
	}

	public void setRoleNum(String roleNum) {
		this.roleNum = roleNum;
	}

	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getPageNum() {
		return this.pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

}