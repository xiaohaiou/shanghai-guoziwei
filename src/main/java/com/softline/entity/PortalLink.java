package com.softline.entity;

/**
 * PortalLink entity. @author MyEclipse Persistence Tools
 */

public class PortalLink implements java.io.Serializable {

	// Fields

	private Integer id;
	private String pic;
	private String url;
	private String description;
	private Integer isIssue;

	// Constructors

	/** default constructor */
	public PortalLink() {
	}

	/** minimal constructor */
	public PortalLink(Integer isIssue) {
		this.isIssue = isIssue;
	}

	/** full constructor */
	public PortalLink(String pic, String url, String description,
			Integer isIssue) {
		this.pic = pic;
		this.url = url;
		this.description = description;
		this.isIssue = isIssue;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsIssue() {
		return this.isIssue;
	}

	public void setIsIssue(Integer isIssue) {
		this.isIssue = isIssue;
	}

}