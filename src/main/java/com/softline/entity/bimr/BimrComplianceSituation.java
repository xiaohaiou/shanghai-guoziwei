package com.softline.entity.bimr;

/**
 * BimrComplianceSituation entity. @author MyEclipse Persistence Tools
 */

public class BimrComplianceSituation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer complianceId;
	private String problem;
	private String truth;
	private Integer isTruth;
	private Integer isAccountability;
	private Integer problemType;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer isDel;
	private Integer dataType;

	
	private String isTruthName;
	private String problemTypeName;
	// Constructors

	/** default constructor */
	public BimrComplianceSituation() {
	}

	/** minimal constructor */
	public BimrComplianceSituation(Integer isDel) {
		this.isDel = isDel;
	}

	/** full constructor */
	public BimrComplianceSituation(String problem, String truth,
			Integer isTruth, Integer isAccountability, Integer problemType,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, Integer isDel, Integer dataType) {
		this.problem = problem;
		this.truth = truth;
		this.isTruth = isTruth;
		this.isAccountability = isAccountability;
		this.problemType = problemType;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.isDel = isDel;
		this.dataType = dataType;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProblem() {
		return this.problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getTruth() {
		return this.truth;
	}

	public void setTruth(String truth) {
		this.truth = truth;
	}

	public Integer getIsTruth() {
		return this.isTruth;
	}

	public void setIsTruth(Integer isTruth) {
		this.isTruth = isTruth;
	}

	public Integer getIsAccountability() {
		return this.isAccountability;
	}

	public void setIsAccountability(Integer isAccountability) {
		this.isAccountability = isAccountability;
	}

	public Integer getProblemType() {
		return this.problemType;
	}

	public void setProblemType(Integer problemType) {
		this.problemType = problemType;
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

	public String getLastModifyPersonId() {
		return this.lastModifyPersonId;
	}

	public void setLastModifyPersonId(String lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}

	public String getLastModifyPersonName() {
		return this.lastModifyPersonName;
	}

	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
	}

	public String getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getDataType() {
		return this.dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Integer getComplianceId() {
		return complianceId;
	}

	public void setComplianceId(Integer complianceId) {
		this.complianceId = complianceId;
	}

	public String getIsTruthName() {
		return isTruthName;
	}

	public void setIsTruthName(String isTruthName) {
		this.isTruthName = isTruthName;
	}

	public String getProblemTypeName() {
		return problemTypeName;
	}

	public void setProblemTypeName(String problemTypeName) {
		this.problemTypeName = problemTypeName;
	}

	
}