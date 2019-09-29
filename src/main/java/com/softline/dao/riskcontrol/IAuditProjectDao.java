package com.softline.dao.riskcontrol;

import java.util.List;

import com.softline.entity.AuditProject;

public interface IAuditProjectDao {
	
	Integer getAuditProjectListCount(AuditProject entity, String dataAuthority);
	
	Integer getExamineAuditProjectListCount(AuditProject entity, String dataAuthority);
	
	List<AuditProject> getAuditProjectList(AuditProject entity, Integer offset,	Integer pageSize, String dataAuthority);
	
	List<AuditProject> getExamineAuditProjectList(AuditProject entity, Integer offset,	Integer pageSize, String dataAuthority);

	Integer saveAuditProject(AuditProject entity);
	
    void updateAuditProject(AuditProject entity);
	
    void deleteAuditProject(Integer id);

	AuditProject getAuditProjectById(Integer id);
	
	AuditProject getAuditProject(AuditProject entity);
}
