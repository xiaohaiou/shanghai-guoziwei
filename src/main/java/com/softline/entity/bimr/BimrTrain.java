package com.softline.entity.bimr;

/**
 * 合规培训实体类
 * @author pengguolin
 */
public class BimrTrain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/** 单位id */ private Integer companyId;
	/** 单位名称 */ private String companyName;
	/** 被培训人姓名 */ private String beTrainPerson;
	/** 培训名称 */ private String trainName;
	/** 培训日期 */ private String trainDate;
	/** 授课人 */ private String lecturer;
	/** 是否内部授课人（0否1是） */ private Integer isInside;
	/** 授课人所在单位 */ private String lecturerCompany;
	/** 授课人职位 */ private String lecturerJob;
	/** 培训对象 */ private String trainObject;
	/** 授课方式（1:现场2现场+视频3视频） */ private Integer lecturerMode;
	/** 培训课时 */ private String lecturerHour;
	/** 培训反馈情况（1:满意2不满意） */ private Integer feedback;
	/** 培训地点（1:境内2:境外） */ private Integer address;
	/** 备注 */ private String remarks;
	/** 培训资料附件 */ private String lecturerEnclosure;
	/** 培训人员名单导入 */ private String listOf;
	/** 状态 (50112:未上报  50113:待审核 50114:已退回 50115:已审核)*/ private Integer status;
	/** 删除标记（0否1是） */ private Integer isDel;
	/** 创建人 */ private String createPersonName;
	/** 创建人ID */ private String createPersonId;
	/** 创建时间 */ private String createDate;
	/** 最后修改人ID */ private String lastModifyPersonId;
	/** 最后修改人名 */ private String lastModifyPersonName;
	/** 最后修改时间 */ private String lastModifyDate;
	/** 上报人姓名 */ private String reportPersonName;
	/** 上报人id */ private String reportPersonId;
	/** 上报时间 */ private String reportDate;
	/** 审核人名 */ private String auditorPersonName;
	/** 审核人id */ private String auditorPersonId;
	/** 审核时间 */ private String auditorDate;
	
	/** 查询条件培训日期区间：开始日期 */ private String startDate;
	/** 查询条件培训日期区间：结束日期 */ private String endDate;
	/** 状态名称 */ private String statusName;
	/** 审核人 */ private String examineName;

	public BimrTrain() {
	}

	public BimrTrain(Integer id, Integer companyId, String companyName,
			String beTrainPerson, String trainName, String trainDate,
			String lecturer, Integer isInside, String lecturerCompany,
			String lecturerJob, String trainObject, Integer lecturerMode,
			String lecturerHour, Integer feedback, Integer address,
			String remarks, String lecturerEnclosure, String listOf,
			Integer status, Integer isDel, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String startDate,
			String endDate, String statusName, String examineName) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.companyName = companyName;
		this.beTrainPerson = beTrainPerson;
		this.trainName = trainName;
		this.trainDate = trainDate;
		this.lecturer = lecturer;
		this.isInside = isInside;
		this.lecturerCompany = lecturerCompany;
		this.lecturerJob = lecturerJob;
		this.trainObject = trainObject;
		this.lecturerMode = lecturerMode;
		this.lecturerHour = lecturerHour;
		this.feedback = feedback;
		this.address = address;
		this.remarks = remarks;
		this.lecturerEnclosure = lecturerEnclosure;
		this.listOf = listOf;
		this.status = status;
		this.isDel = isDel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.reportPersonName = reportPersonName;
		this.reportPersonId = reportPersonId;
		this.reportDate = reportDate;
		this.auditorPersonName = auditorPersonName;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.statusName = statusName;
		this.examineName = examineName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBeTrainPerson() {
		return this.beTrainPerson;
	}

	public void setBeTrainPerson(String beTrainPerson) {
		this.beTrainPerson = beTrainPerson;
	}

	public String getTrainName() {
		return this.trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTrainDate() {
		return this.trainDate;
	}

	public void setTrainDate(String trainDate) {
		this.trainDate = trainDate;
	}

	public String getLecturer() {
		return this.lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public Integer getIsInside() {
		return this.isInside;
	}

	public void setIsInside(Integer isInside) {
		this.isInside = isInside;
	}

	public String getLecturerCompany() {
		return this.lecturerCompany;
	}

	public void setLecturerCompany(String lecturerCompany) {
		this.lecturerCompany = lecturerCompany;
	}

	public String getLecturerJob() {
		return this.lecturerJob;
	}

	public void setLecturerJob(String lecturerJob) {
		this.lecturerJob = lecturerJob;
	}

	public String getTrainObject() {
		return this.trainObject;
	}

	public void setTrainObject(String trainObject) {
		this.trainObject = trainObject;
	}

	public Integer getLecturerMode() {
		return this.lecturerMode;
	}

	public void setLecturerMode(Integer lecturerMode) {
		this.lecturerMode = lecturerMode;
	}

	public String getLecturerHour() {
		return this.lecturerHour;
	}

	public void setLecturerHour(String lecturerHour) {
		this.lecturerHour = lecturerHour;
	}

	public Integer getFeedback() {
		return this.feedback;
	}

	public void setFeedback(Integer feedback) {
		this.feedback = feedback;
	}

	public Integer getAddress() {
		return this.address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLecturerEnclosure() {
		return this.lecturerEnclosure;
	}

	public void setLecturerEnclosure(String lecturerEnclosure) {
		this.lecturerEnclosure = lecturerEnclosure;
	}

	public String getListOf() {
		return this.listOf;
	}

	public void setListOf(String listOf) {
		this.listOf = listOf;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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

	public String getReportPersonName() {
		return reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getReportPersonId() {
		return reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getAuditorPersonName() {
		return auditorPersonName;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public String getAuditorPersonId() {
		return auditorPersonId;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public String getAuditorDate() {
		return auditorDate;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getExamineName() {
		return examineName;
	}

	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}