package com.softline.entity.settingCenter;

/**
 * Page entity. @author MyEclipse Persistence Tools
 */

public class ShowPage implements java.io.Serializable {

	// Fields

	private Integer id;
	private String pageNum;
	private String pageName;
	private String pageDescription;
	private Integer isDel;
	private Integer portalId;

	// Constructors

	/** default constructor */
	public ShowPage() {
	}
	
	public Integer getPortalId() {
		return portalId;
	}

	public void setPortalId(Integer portalId) {
		this.portalId = portalId;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}