package com.softline.entity.bimr;

import java.io.Serializable;

public class BimrInsideAuditProject implements Serializable{
    /**
     * 版本序列化.
     */
    private static final long serialVersionUID = -3058326062961334703L;

    /**
     * 表主键.
     */
    private Integer id;

    /**
     * 被审计单位或人员id.
     */
    private Integer auditDepted;
    /**
     * 被审计单位或人员.
     */
    private String auditDeptedName;
    /**
     * 审计项目名称.
     */
    private String auditProjectName;
    /**
     * 审计项目编号.
     */
    private String auditProjectCode;
    /**
     * 审计实施单位id.
     */
    private String auditImplDeptId;
    
    /**
     * 整改跟踪人name
     */
    private String rectifyTrackName;
    
    /**
     * 整改跟踪人ID
     */
    private String rectifyTrackId;
    /**
     * 审计实施单位名称.
     */
    
    private String auditImplDeptName;
    /**
     * 审计项目性质.
     */
    private Integer auditProjectProp;
    /**
     * 审计起始时间.
     */
    private String auditStartDate;
    /**
     * 审计截止时间.
     */
    private String auditEndDate;
    /**
     * 项目负责人.
     */
    private String projectPrincipal;
    
    /**
     * 项目负责人Id.
     */
    private String projectPrincipalId;
    
    /**
     * 项目状态.
     */
    private Integer status;
    /**
     * 项目审批状态.
     */
    private Integer estatus;
    /**
     * 审核意见.
     */
    private String opinion;
    /**
     * 是否重点项目.
     */
    private Integer isImportant;
    /**
     * 是否子项目.
     */
    private Integer isChildren;
    /**
     * 关联大审计项目.
     */
    private Integer auditParentId;
    /**
     * 审计项目目标.
     */
    private String auditProjectGoal;
    /**
     * 是否删除.
     */
    private Integer isDel;
    /**
     * 审计项目内容.
     */
    private String auditProjectContent;
    /**
     * 备注.
     */
    private String remark;
    /**
     * 创建人姓名.
     */
    private String createPersonName;
    /**
     * 创建人ID.
     */
    private String createPersonId;
    /**
     * 创建时间.
     */
    private String createDate;
    /**
     * 最后修改人ID.
     */
    private String lastModifyPersonId;
    /**
     * 最后修改人名.
     */
    private String lastModifyPersonName;
    /**
     * 最后修改时间.
     */
    private String lastModifyDate;
    /**
     * 审核人ID.
     */
    private String examinePersonId;
    /**
     *  审核人姓名.
     */
    private String examinePersonName;
    
    /**
     * 审核时间.
     */
    private String examineDate;
    
    
    /**
     * 审核时间.
     */
    private String applycloseremarks;
    
    
    
    /**
     * 多个项目状态.
     */
    private String morestatus;
    
    /**
     * 审计项目性质.
     */
    private String auditProjectPropName;
    
    /**
     * 项目状态.
     */
    private String statusName;
    
    private String vcOrganID;
    
    private String auditParentName;
    
    private String auditParentCode;
    
    private String sjFileId;

    private String mailFileId;

    private String docNum;
    
    
    private Integer accountabilityStatus;
    
    private String auditDeptedPersonId;
    
    private String auditDeptedPerson;
    
    private String auditDeptedPersonPost;

    public BimrInsideAuditProject() {
    }

    public BimrInsideAuditProject(Integer id, Integer auditDepted,String auditDeptedName, String auditProjectName,String auditProjectCode, String auditImplDeptId,
			String auditImplDeptName, Integer auditProjectProp,String auditStartDate, String auditEndDate,String projectPrincipal, String projectPrincipalId, 
			Integer status, Integer estatus, String opinion, Integer isImportant,Integer isChildren, Integer auditParentId, String auditProjectGoal,
			Integer isDel, String auditProjectContent, String remark,String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName, String lastModifyDate, String examinePersonId, String examinePersonName, String examineDate,
			String applycloseremarks, String morestatus, String auditProjectPropName, String statusName, String vcOrganID,
			String auditParentName,String rectifyTrackName, String rectifyTrackId, String auditParentCode, String sjFileId, String mailFileId, String docNum,Integer accountabilityStatus,
			String auditDeptedPersonId,String auditDeptedPerson,String auditDeptedPersonPost) {
		this.id = id;
		this.auditDepted = auditDepted;
		this.auditDeptedName = auditDeptedName;
		this.auditProjectName = auditProjectName;
		this.auditProjectCode = auditProjectCode;
		this.auditImplDeptId = auditImplDeptId;
		this.auditImplDeptName = auditImplDeptName;
		this.auditProjectProp = auditProjectProp;
		this.auditStartDate = auditStartDate;
		this.auditEndDate = auditEndDate;
		this.rectifyTrackName = rectifyTrackName;
		this.rectifyTrackId = rectifyTrackId;
		this.projectPrincipal = projectPrincipal;
		this.projectPrincipalId = projectPrincipalId;
		this.status = status;
		this.estatus = estatus;
		this.opinion = opinion;
		this.isImportant = isImportant;
		this.isChildren = isChildren;
		this.auditParentId = auditParentId;
		this.auditProjectGoal = auditProjectGoal;
		this.isDel = isDel;
		this.auditProjectContent = auditProjectContent;
		this.remark = remark;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.examinePersonId = examinePersonId;
		this.examinePersonName = examinePersonName;
		this.examineDate = examineDate;
		this.applycloseremarks = applycloseremarks;
		this.morestatus = morestatus;
		this.auditProjectPropName = auditProjectPropName;
		this.statusName = statusName;
		this.vcOrganID = vcOrganID;
		this.auditParentName = auditParentName;
		this.auditParentCode = auditParentCode;
		this.sjFileId = sjFileId;
		this.mailFileId = mailFileId;
		this.docNum = docNum;
		this.accountabilityStatus=accountabilityStatus;
		this.auditDeptedPersonId=auditDeptedPersonId;
		this.auditDeptedPerson=auditDeptedPerson;
		this.auditDeptedPersonPost=auditDeptedPersonPost;
	}

	public String getSjFileId() {
		return sjFileId;
	}

	public void setSjFileId(String sjFileId) {
		this.sjFileId = sjFileId;
	}

	public String getMailFileId() {
		return mailFileId;
	}

	public void setMailFileId(String mailFileId) {
		this.mailFileId = mailFileId;
	}

	public String getDocNum() {
		return docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	public String getAuditParentCode() {
		return auditParentCode;
	}

	public void setAuditParentCode(String auditParentCode) {
		this.auditParentCode = auditParentCode;
	}

	public String getAuditParentName() {
		return auditParentName;
	}

	public void setAuditParentName(String auditParentName) {
		this.auditParentName = auditParentName;
	}

	public String getVcOrganID() {
		return vcOrganID;
	}

	public String getRectifyTrackName() {
		return rectifyTrackName;
	}

	public void setRectifyTrackName(String rectifyTrackName) {
		this.rectifyTrackName = rectifyTrackName;
	}

	public void setVcOrganID(String vcOrganID) {
		this.vcOrganID = vcOrganID;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuditDepted() {
        return auditDepted;
    }

    public void setAuditDepted(Integer auditDepted) {
        this.auditDepted = auditDepted;
    }

    public String getAuditDeptedName() {
        return auditDeptedName;
    }

    public void setAuditDeptedName(String auditDeptedName) {
        this.auditDeptedName = auditDeptedName;
    }

    public String getAuditProjectName() {
        return auditProjectName;
    }

    public void setAuditProjectName(String auditProjectName) {
        this.auditProjectName = auditProjectName;
    }

    public String getAuditProjectCode() {
        return auditProjectCode;
    }

    public void setAuditProjectCode(String auditProjectCode) {
        this.auditProjectCode = auditProjectCode;
    }

    public String getAuditImplDeptId() {
        return auditImplDeptId;
    }

    public void setAuditImplDeptId(String auditImplDeptId) {
        this.auditImplDeptId = auditImplDeptId;
    }

    public String getAuditImplDeptName() {
        return auditImplDeptName;
    }

    public void setAuditImplDeptName(String auditImplDeptName) {
        this.auditImplDeptName = auditImplDeptName;
    }

    public Integer getAuditProjectProp() {
        return auditProjectProp;
    }

    public void setAuditProjectProp(Integer auditProjectProp) {
        this.auditProjectProp = auditProjectProp;
    }

    public String getAuditStartDate() {
        return auditStartDate;
    }

    public void setAuditStartDate(String auditStartDate) {
        this.auditStartDate = auditStartDate;
    }

    public String getAuditEndDate() {
        return auditEndDate;
    }

    public void setAuditEndDate(String auditEndDate) {
        this.auditEndDate = auditEndDate;
    }

    public String getProjectPrincipal() {
        return projectPrincipal;
    }

    public void setProjectPrincipal(String projectPrincipal) {
        this.projectPrincipal = projectPrincipal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(Integer isImportant) {
        this.isImportant = isImportant;
    }

    public Integer getIsChildren() {
        return isChildren;
    }

    public void setIsChildren(Integer isChildren) {
        this.isChildren = isChildren;
    }

    public Integer getAuditParentId() {
        return auditParentId;
    }

    public void setAuditParentId(Integer auditParentId) {
        this.auditParentId = auditParentId;
    }

    public String getAuditProjectGoal() {
        return auditProjectGoal;
    }

    public void setAuditProjectGoal(String auditProjectGoal) {
        this.auditProjectGoal = auditProjectGoal;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getAuditProjectContent() {
        return auditProjectContent;
    }

    public void setAuditProjectContent(String auditProjectContent) {
        this.auditProjectContent = auditProjectContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getExaminePersonId() {
        return examinePersonId;
    }

    public void setExaminePersonId(String examinePersonId) {
        this.examinePersonId = examinePersonId;
    }

    public String getExaminePersonName() {
        return examinePersonName;
    }

    public void setExaminePersonName(String examinePersonName) {
        this.examinePersonName = examinePersonName;
    }

    public String getExamineDate() {
        return examineDate;
    }

    public void setExamineDate(String examineDate) {
        this.examineDate = examineDate;
    }
    
    

    public String getApplycloseremarks() {
		return applycloseremarks;
	}

	public void setApplycloseremarks(String applycloseremarks) {
		this.applycloseremarks = applycloseremarks;
	}

	public String getMorestatus() {
		return morestatus;
	}

	public void setMorestatus(String morestatus) {
		this.morestatus = morestatus;
	}

	public String getProjectPrincipalId() {
		return projectPrincipalId;
	}

	public void setProjectPrincipalId(String projectPrincipalId) {
		this.projectPrincipalId = projectPrincipalId;
	}

	
	public String getRectifyTrackId() {
		return rectifyTrackId;
	}

	public void setRectifyTrackId(String rectifyTrackId) {
		this.rectifyTrackId = rectifyTrackId;
	}

	public String getAuditProjectPropName() {
		return auditProjectPropName;
	}

	public void setAuditProjectPropName(String auditProjectPropName) {
		this.auditProjectPropName = auditProjectPropName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getAccountabilityStatus() {
		return accountabilityStatus;
	}

	public void setAccountabilityStatus(Integer accountabilityStatus) {
		this.accountabilityStatus = accountabilityStatus;
	}

	
	public String getAuditDeptedPersonId() {
		return auditDeptedPersonId;
	}

	public void setAuditDeptedPersonId(String auditDeptedPersonId) {
		this.auditDeptedPersonId = auditDeptedPersonId;
	}

	public String getAuditDeptedPerson() {
		return auditDeptedPerson;
	}

	public void setAuditDeptedPerson(String auditDeptedPerson) {
		this.auditDeptedPerson = auditDeptedPerson;
	}

	public String getAuditDeptedPersonPost() {
		return auditDeptedPersonPost;
	}

	public void setAuditDeptedPersonPost(String auditDeptedPersonPost) {
		this.auditDeptedPersonPost = auditDeptedPersonPost;
	}

	@Override
    public String toString() {
        return "BimrInsideAuditProject{" +
                "id=" + id +
                ", auditDepted=" + auditDepted +
                ", auditDeptedName='" + auditDeptedName +
                ", auditProjectName='" + auditProjectName +
                ", auditProjectCode='" + auditProjectCode +
                ", auditImplDeptId='" + auditImplDeptId +
                "', auditImplDeptName='" + auditImplDeptName +
                ", auditProjectProp=" + auditProjectProp +
                ", auditStartDate='" + auditStartDate +
                ", auditEndDate='" + auditEndDate +
                ", projectPrincipal='" + projectPrincipal +
                ", status=" + status +
                ", estatus=" + estatus +
                ", opinion='" + opinion +
                ", isImportant=" + isImportant +
                ", isChildren=" + isChildren +
                ", auditParentId=" + auditParentId +
                ", auditProjectGoal='" + auditProjectGoal +
                ", isDel=" + isDel +
                ", auditProjectContent=" + auditProjectContent +
                ", remark='" + remark +
                ", createPersonName='" + createPersonName +
                ", createPersonId='" + createPersonId +
                ", createDate='" + createDate +
                ", lastModifyPersonId='" + lastModifyPersonId +
                ", lastModifyPersonName='" + lastModifyPersonName +
                ", lastModifyDate='" + lastModifyDate +
                ", examinePersonId='" + examinePersonId +
                ", examinePersonName='" + examinePersonName +
                ", examineDate='" + examineDate +
                ", applycloseremarks='" + applycloseremarks +
                ", projectPrincipalId='" + projectPrincipalId +
                '}';
    }
}
