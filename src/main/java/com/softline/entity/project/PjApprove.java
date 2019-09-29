package com.softline.entity.project;

/**
 * PjApprove entity. @author MyEclipse Persistence Tools
 */

public class PjApprove implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pjId;
	private Integer type;
	private String remark;
	private Integer approveResult;
	private String approveIds;
	private String createPersonName;
	private String createPersonId;
	private String createDate;

	// Constructors

	/** default constructor */
	public PjApprove() {
	}

	/** full constructor */
	public PjApprove(Integer pjId, Integer type, String remark,
			Integer approveResult, String approveIds, String createPersonName,
			String createPersonId, String createDate) {
		this.pjId = pjId;
		this.type = type;
		this.remark = remark;
		this.approveResult = approveResult;
		this.approveIds = approveIds;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPjId() {
		return this.pjId;
	}

	public void setPjId(Integer pjId) {
		this.pjId = pjId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getApproveResult() {
		return this.approveResult;
	}

	public void setApproveResult(Integer approveResult) {
		this.approveResult = approveResult;
	}

	public String getApproveIds() {
		return this.approveIds;
	}

	public void setApproveIds(String approveIds) {
		this.approveIds = approveIds;
	}

	public String getCreatePersonName() {
		return this.createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public String getCreatePersonId() {
		return this.createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}