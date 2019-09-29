package com.softline.entity.bimr;

/**
 * BimrInsideAuditProjectAppendix entity. @author MyEclipse Persistence Tools
 */
public class BimrInsideAuditProjectAppendix implements java.io.Serializable {

	private Integer id;
	private String projectId;
	private String auditImplDeptId;

	public BimrInsideAuditProjectAppendix() {
	}

	public BimrInsideAuditProjectAppendix(String projectId,
			String auditImplDeptId) {
		this.projectId = projectId;
		this.auditImplDeptId = auditImplDeptId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getAuditImplDeptId() {
		return auditImplDeptId;
	}

	public void setAuditImplDeptId(String auditImplDeptId) {
		this.auditImplDeptId = auditImplDeptId;
	}
}
