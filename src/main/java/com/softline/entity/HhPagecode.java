package com.softline.entity;

/**
 * HhPagecode entity. @author MyEclipse Persistence Tools
 */

public class HhPagecode implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer portalId;
	private Integer pageId;
	private String pageNum;
	private String code;
	private String codeName;
	private Integer seqNo;
	private Integer isDel;

	// Constructors

	/** default constructor */
	public HhPagecode() {
	}

	/** minimal constructor */
	public HhPagecode(Integer portalId) {
		this.portalId = portalId;
	}

	/** full constructor */
	public HhPagecode(Integer portalId, Integer pageId, String pageNum,
			String code, String codeName, Integer seqNo, Integer isDel) {
		this.portalId = portalId;
		this.pageId = pageId;
		this.pageNum = pageNum;
		this.code = code;
		this.codeName = codeName;
		this.seqNo = seqNo;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Integer getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}