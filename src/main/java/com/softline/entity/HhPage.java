package com.softline.entity;

/**
 * HhPage entity. @author MyEclipse Persistence Tools
 */

public class HhPage implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer portalId;
	private String pageNum;
	private String pageName;
	private String pageDescription;
	private Integer isDel;

	// Constructors

	/** default constructor */
	public HhPage() {
	}

	/** minimal constructor */
	public HhPage(Integer portalId) {
		this.portalId = portalId;
	}

	/** full constructor */
	public HhPage(Integer portalId, String pageNum, String pageName,
			String pageDescription, Integer isDel) {
		this.portalId = portalId;
		this.pageNum = pageNum;
		this.pageName = pageName;
		this.pageDescription = pageDescription;
		this.isDel = isDel;
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

	public String getPageNum() {
		return this.pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageDescription() {
		return this.pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}