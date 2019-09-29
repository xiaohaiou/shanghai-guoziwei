package com.softline.entity.bimr;

/**
 * 员工问责实体类
 * @author pengguolin
 */
public class BimrDuty implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/** 问责人员 */ private String person;
	/** 问责人员 */ private String personId;
	/** 发生时间 */ private String happenDate;
	/** 问责来源(1:风险事项报告2:案件事项3:合同事项 4：审计5合规6:其它事项) */ private Integer source;
	/** 问责原因 */ private String reason;
	/** 建议问责落实情况 */ private String proposal;
	/** 处分问责呈报公文编号 */ private String bumfNum;
	/** 处分问责办文编号 */ private String articleNum;
	/** 附件 */ private String enclosure;
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
	
	/** 查询条件发生时间区间：开始日期 */ private String startDate;
	/** 查询条件发生时间区间：结束日期 */ private String endDate;
	/** 状态名称 */ private String statusName;
	//问责人员职务
	private String personPost;
	private String personCompany;
	
	//关联项目id
	private Integer projectId;
	//关联问题id
	private Integer questionId;
	//关联问责人员表Id
	private Integer accountableId;
	
	//问责结果
	private String accountableResult;
	public BimrDuty() {
	}
	
	public BimrDuty(Integer id, String person, String happenDate,
			Integer source, String reason, String proposal, String bumfNum,
			String articleNum, String enclosure, Integer status, Integer isDel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate,String personId,String personPost,String personCompany,
			Integer projectId,Integer questionId,Integer accountableId,String accountableResult) {
		super();
		this.id = id;
		this.person = person;
		this.personId=personId;
		this.happenDate = happenDate;
		this.source = source;
		this.reason = reason;
		this.proposal = proposal;
		this.bumfNum = bumfNum;
		this.articleNum = articleNum;
		this.enclosure = enclosure;
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
		this.projectId=projectId;
		this.questionId = questionId;
		this.accountableId=accountableId;
		this.accountableResult=accountableResult;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getHappenDate() {
		return happenDate;
	}
	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getProposal() {
		return proposal;
	}
	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	public String getBumfNum() {
		return bumfNum;
	}
	public void setBumfNum(String bumfNum) {
		this.bumfNum = bumfNum;
	}
	public String getArticleNum() {
		return articleNum;
	}
	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}
	public String getEnclosure() {
		return enclosure;
	}
	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getCreatePersonName() {
		return createPersonName;
	}
	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}
	public String getCreatePersonId() {
		return createPersonId;
	}
	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastModifyPersonId() {
		return lastModifyPersonId;
	}
	public void setLastModifyPersonId(String lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}
	public String getLastModifyPersonName() {
		return lastModifyPersonName;
	}
	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonPost() {
		return personPost;
	}

	public void setPersonPost(String personPost) {
		this.personPost = personPost;
	}

	public String getPersonCompany() {
		return personCompany;
	}

	public void setPersonCompany(String personCompany) {
		this.personCompany = personCompany;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getAccountableId() {
		return accountableId;
	}

	public void setAccountableId(Integer accountableId) {
		this.accountableId = accountableId;
	}

	public String getAccountableResult() {
		return accountableResult;
	}

	public void setAccountableResult(String accountableResult) {
		this.accountableResult = accountableResult;
	}
	
}