package com.softline.entity;

/**
 * HhBase entity. @author MyEclipse Persistence Tools
 */

public class HhBase implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer isDel;
	private Integer num;
	private String description;
	private Integer type;
	private Integer status;
    private Integer parentID;
	public HhBase(Integer id, Integer isDel, Integer num, String description,
		Integer type, Integer status, Integer parentID) {
	super();
	this.id = id;
	this.isDel = isDel;
	this.num = num;
	this.description = description;
	this.type = type;
	this.status = status;
	this.parentID = parentID;
}


	// Constructors

	public Integer getParentID() {
	return parentID;
}


public void setParentID(Integer parentID) {
	this.parentID = parentID;
}


	/** default constructor */
	public HhBase() {
	}


	/** full constructor */
	public HhBase(Integer isDel, Integer num, String description, Integer type,
			Integer status) {
		this.isDel = isDel;
		this.num = num;
		this.description = description;
		this.type = type;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}