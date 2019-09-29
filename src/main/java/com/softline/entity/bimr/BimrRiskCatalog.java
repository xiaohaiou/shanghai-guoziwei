package com.softline.entity.bimr;

/**
 * 风险目录
 * 
 * @author liu
 */
@SuppressWarnings("serial")
public class BimrRiskCatalog implements java.io.Serializable {
	private Integer id;
	private String name;
	private String define;
	private Integer level;
	private Integer parentId;
	private Integer status;
	private String createDate;
	private String createPersonName;
	private String createPersonId;
	private String lastModifyDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String submitAuditDate;
	private String submitAuditPersonName;
	private String submitAuditPersonId;
	private String auditDate;
	private String auditPersonName;
	private String auditPersonId;
	private Integer isDel;
	
	private String riskId;
	private String riskParentId;
	
	public BimrRiskCatalog(){
		//none instance
	} 

	public BimrRiskCatalog(Integer id, String name, String define,
			Integer level, Integer parentId, Integer status, String createDate,
			String createPersonName, String createPersonId,
			String lastModifyDate, String lastModifyPersonId,
			String lastModifyPersonName, String submitAuditDate,
			String submitAuditPersonName, String submitAuditPersonId,
			String auditDate, String auditPersonName, String auditPersonId,
			Integer isDel) {
		super();
		this.id = id;
		this.name = name;
		this.define = define;
		this.level = level;
		this.parentId = parentId;
		this.status = status;
		this.createDate = createDate;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.lastModifyDate = lastModifyDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.submitAuditDate = submitAuditDate;
		this.submitAuditPersonName = submitAuditPersonName;
		this.submitAuditPersonId = submitAuditPersonId;
		this.auditDate = auditDate;
		this.auditPersonName = auditPersonName;
		this.auditPersonId = auditPersonId;
		this.isDel = isDel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefine() {
		return define;
	}

	public void setDefine(String define) {
		this.define = define;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
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

	public String getSubmitAuditDate() {
		return submitAuditDate;
	}

	public void setSubmitAuditDate(String submitAuditDate) {
		this.submitAuditDate = submitAuditDate;
	}

	public String getSubmitAuditPersonName() {
		return submitAuditPersonName;
	}

	public void setSubmitAuditPersonName(String submitAuditPersonName) {
		this.submitAuditPersonName = submitAuditPersonName;
	}

	public String getSubmitAuditPersonId() {
		return submitAuditPersonId;
	}

	public void setSubmitAuditPersonId(String submitAuditPersonId) {
		this.submitAuditPersonId = submitAuditPersonId;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditPersonName() {
		return auditPersonName;
	}

	public void setAuditPersonName(String auditPersonName) {
		this.auditPersonName = auditPersonName;
	}

	public String getAuditPersonId() {
		return auditPersonId;
	}

	public void setAuditPersonId(String auditPersonId) {
		this.auditPersonId = auditPersonId;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getRiskParentId() {
		return riskParentId;
	}

	public void setRiskParentId(String riskParentId) {
		this.riskParentId = riskParentId;
	}
	
	
}
