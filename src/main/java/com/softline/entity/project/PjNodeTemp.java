package com.softline.entity.project;

/**
 * PjNodeTemp entity. @author MyEclipse Persistence Tools
 */

public class PjNodeTemp implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pjId;
	private String pnName;
	private String pnCompletionTime;
	private String pnRespDept;
	private String pnCoordDept;
	private String pnStandard;
	private String pnRemark;
	private Integer pnStatus;
	private Double pnProgress;
	private Integer pnOrder;
	private Integer pnIsrequired;
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
	private String pnFinishTime;

	// Constructors

	/** default constructor */
	public PjNodeTemp() {
	}

	/** minimal constructor */
	public PjNodeTemp(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public PjNodeTemp(Integer pjId, String pnName, String pnCompletionTime,
			String pnRespDept, String pnCoordDept, String pnStandard,
			String pnRemark, Integer pnStatus, Double pnProgress,
			Integer pnOrder, Integer pnIsrequired, Integer isdel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportTime, String reportPersonId,
			String reportPersonName, Integer version, Integer reportStatus,Integer approveId,String pnFinishTime) {
		this.pjId = pjId;
		this.pnName = pnName;
		this.pnCompletionTime = pnCompletionTime;
		this.pnRespDept = pnRespDept;
		this.pnCoordDept = pnCoordDept;
		this.pnStandard = pnStandard;
		this.pnRemark = pnRemark;
		this.pnStatus = pnStatus;
		this.pnProgress = pnProgress;
		this.pnOrder = pnOrder;
		this.pnIsrequired = pnIsrequired;
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
		this.pnFinishTime = pnFinishTime;
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

	public String getPnName() {
		return this.pnName;
	}

	public void setPnName(String pnName) {
		this.pnName = pnName;
	}

	public String getPnCompletionTime() {
		return this.pnCompletionTime;
	}

	public void setPnCompletionTime(String pnCompletionTime) {
		this.pnCompletionTime = pnCompletionTime;
	}

	public String getPnRespDept() {
		return this.pnRespDept;
	}

	public void setPnRespDept(String pnRespDept) {
		this.pnRespDept = pnRespDept;
	}

	public String getPnCoordDept() {
		return this.pnCoordDept;
	}

	public void setPnCoordDept(String pnCoordDept) {
		this.pnCoordDept = pnCoordDept;
	}

	public String getPnStandard() {
		return this.pnStandard;
	}

	public void setPnStandard(String pnStandard) {
		this.pnStandard = pnStandard;
	}

	public String getPnRemark() {
		return this.pnRemark;
	}

	public void setPnRemark(String pnRemark) {
		this.pnRemark = pnRemark;
	}

	public Integer getPnStatus() {
		return this.pnStatus;
	}

	public void setPnStatus(Integer pnStatus) {
		this.pnStatus = pnStatus;
	}

	public Double getPnProgress() {
		return this.pnProgress;
	}

	public void setPnProgress(Double pnProgress) {
		this.pnProgress = pnProgress;
	}

	public Integer getPnOrder() {
		return this.pnOrder;
	}

	public void setPnOrder(Integer pnOrder) {
		this.pnOrder = pnOrder;
	}

	public Integer getPnIsrequired() {
		return this.pnIsrequired;
	}

	public void setPnIsrequired(Integer pnIsrequired) {
		this.pnIsrequired = pnIsrequired;
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
		return approveId;
	}

	public void setApproveId(Integer approveId) {
		this.approveId = approveId;
	}

	public String getPnFinishTime() {
		return pnFinishTime;
	}

	public void setPnFinishTime(String pnFinishTime) {
		this.pnFinishTime = pnFinishTime;
	}

}