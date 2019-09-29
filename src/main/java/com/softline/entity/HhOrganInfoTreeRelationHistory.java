package com.softline.entity;

/**
 * HhOrganInfoTreeRelationHistory entity. @author MyEclipse Persistence Tools
 */

public class HhOrganInfoTreeRelationHistory implements java.io.Serializable {

	// Fields
	private HhOrganInfoTreeRelationHistoryId id;
	private String vcOrganId;
	private String vcParentId;
	private Integer ishowOrder;
	private String vcFullName;
	private String vcShortName;
	private String vcshoworder;
	private Integer bimaId;
	private String hrId;
	private Integer status;
	private String responsiblePersonName;
	private String vcEmployeeID;
	private String responsiblePersonEmail;
	// Constructors

	/** default constructor */
	public HhOrganInfoTreeRelationHistory() {
	}

	/** minimal constructor */
	public HhOrganInfoTreeRelationHistory(HhOrganInfoTreeRelationHistoryId id) {
		this.id = id;
	}

	/** full constructor */
	public HhOrganInfoTreeRelationHistory(HhOrganInfoTreeRelationHistoryId id,
			String vcOrganId, String vcParentId, Integer ishowOrder,
			String vcFullName, String vcShortName, String vcshoworder,
			Integer bimaId, String hrId, Integer status,
			String responsiblePersonName,String vcEmployeeID,
			String responsiblePersonEmail) {
		this.id = id;
		this.vcOrganId = vcOrganId;
		this.vcParentId = vcParentId;
		this.ishowOrder = ishowOrder;
		this.vcFullName = vcFullName;
		this.vcShortName = vcShortName;
		this.vcshoworder = vcshoworder;
		this.bimaId = bimaId;
		this.hrId = hrId;
		this.status = status;
		this.responsiblePersonName = responsiblePersonName;
		this.vcEmployeeID = vcEmployeeID;
		this.responsiblePersonEmail = responsiblePersonEmail;
	}

	// Property accessors

	public HhOrganInfoTreeRelationHistoryId getId() {
		return this.id;
	}

	public void setId(HhOrganInfoTreeRelationHistoryId id) {
		this.id = id;
	}

	public String getVcOrganId() {
		return this.vcOrganId;
	}

	public void setVcOrganId(String vcOrganId) {
		this.vcOrganId = vcOrganId;
	}

	public String getVcParentId() {
		return this.vcParentId;
	}

	public void setVcParentId(String vcParentId) {
		this.vcParentId = vcParentId;
	}

	public Integer getIshowOrder() {
		return this.ishowOrder;
	}

	public void setIshowOrder(Integer ishowOrder) {
		this.ishowOrder = ishowOrder;
	}

	public String getVcFullName() {
		return this.vcFullName;
	}

	public void setVcFullName(String vcFullName) {
		this.vcFullName = vcFullName;
	}

	public String getVcShortName() {
		return this.vcShortName;
	}

	public void setVcShortName(String vcShortName) {
		this.vcShortName = vcShortName;
	}

	public String getVcshoworder() {
		return this.vcshoworder;
	}

	public void setVcshoworder(String vcshoworder) {
		this.vcshoworder = vcshoworder;
	}

	public Integer getBimaId() {
		return this.bimaId;
	}

	public void setBimaId(Integer bimaId) {
		this.bimaId = bimaId;
	}

	public String getHrId() {
		return this.hrId;
	}

	public void setHrId(String hrId) {
		this.hrId = hrId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	public String getVcEmployeeID() {
		return vcEmployeeID;
	}

	public void setVcEmployeeID(String vcEmployeeID) {
		this.vcEmployeeID = vcEmployeeID;
	}

	public String getResponsiblePersonEmail() {
		return responsiblePersonEmail;
	}

	public void setResponsiblePersonEmail(String responsiblePersonEmail) {
		this.responsiblePersonEmail = responsiblePersonEmail;
	}

}