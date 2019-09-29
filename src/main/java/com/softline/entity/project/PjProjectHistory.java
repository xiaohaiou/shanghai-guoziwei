package com.softline.entity.project;

/**
 * PjProjectHistory entity. @author MyEclipse Persistence Tools
 */

public class PjProjectHistory implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer projectId;
	private String pjName;
	private String pjDept;
	private String pjDate;
	private String pjDiscription;
	private Double pjProgress;
	private Double totalContractProgress;
	private String imgPath;
	private Integer pjSort;
	private String pjVideoId;
	private String pjVideoStatus;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String reportTime;
	private String reportPersonId;
	private String reportPersonName;
	private Integer version;
	private Integer reportStatus;
	private Integer approveId;
	private Integer pjDeptId;
	private String operatorId;
	private String operatorName;
	private String approverId;
	private String approverName;

	private PjApprove approve;
	// Constructors

	/** default constructor */
	public PjProjectHistory() {
	}

	/** minimal constructor */
	public PjProjectHistory(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public PjProjectHistory(Integer projectId, String pjName, String pjDept,
			String pjDate, String pjDiscription, Double pjProgress,
			Double totalContractProgress, String imgPath, Integer pjSort,
			String pjVideoId, String pjVideoStatus, Integer isdel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportTime, String reportPersonId,
			String reportPersonName, Integer version, Integer reportStatus,
			Integer approveId,Integer pjDeptId) {
		this.projectId = projectId;
		this.pjName = pjName;
		this.pjDept = pjDept;
		this.pjDate = pjDate;
		this.pjDiscription = pjDiscription;
		this.pjProgress = pjProgress;
		this.totalContractProgress = totalContractProgress;
		this.imgPath = imgPath;
		this.pjSort = pjSort;
		this.pjVideoId = pjVideoId;
		this.pjVideoStatus = pjVideoStatus;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.reportTime = reportTime;
		this.reportPersonId = reportPersonId;
		this.reportPersonName = reportPersonName;
		this.version = version;
		this.reportStatus = reportStatus;
		this.approveId = approveId;
		this.pjDeptId = pjDeptId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getPjName() {
		return this.pjName;
	}

	public void setPjName(String pjName) {
		this.pjName = pjName;
	}

	public String getPjDept() {
		return this.pjDept;
	}

	public void setPjDept(String pjDept) {
		this.pjDept = pjDept;
	}

	public String getPjDate() {
		return this.pjDate;
	}

	public void setPjDate(String pjDate) {
		this.pjDate = pjDate;
	}

	public String getPjDiscription() {
		return this.pjDiscription;
	}

	public void setPjDiscription(String pjDiscription) {
		this.pjDiscription = pjDiscription;
	}

	public Double getPjProgress() {
		return this.pjProgress;
	}

	public void setPjProgress(Double pjProgress) {
		this.pjProgress = pjProgress;
	}

	public Double getTotalContractProgress() {
		return this.totalContractProgress;
	}

	public void setTotalContractProgress(Double totalContractProgress) {
		this.totalContractProgress = totalContractProgress;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getPjSort() {
		return this.pjSort;
	}

	public void setPjSort(Integer pjSort) {
		this.pjSort = pjSort;
	}

	public String getPjVideoId() {
		return this.pjVideoId;
	}

	public void setPjVideoId(String pjVideoId) {
		this.pjVideoId = pjVideoId;
	}

	public String getPjVideoStatus() {
		return this.pjVideoStatus;
	}

	public void setPjVideoStatus(String pjVideoStatus) {
		this.pjVideoStatus = pjVideoStatus;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
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

	public String getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportPersonId() {
		return this.reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportPersonName() {
		return this.reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getReportStatus() {
		return this.reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Integer getApproveId() {
		return this.approveId;
	}

	public void setApproveId(Integer approveId) {
		this.approveId = approveId;
	}

	public PjApprove getApprove() {
		return approve;
	}

	public void setApprove(PjApprove approve) {
		this.approve = approve;
	}

	public Integer getPjDeptId() {
		return pjDeptId;
	}

	public void setPjDeptId(Integer pjDeptId) {
		this.pjDeptId = pjDeptId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

}