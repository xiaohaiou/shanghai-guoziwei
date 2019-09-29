package com.softline.service.riskcontrol;

import com.softline.entity.AuditProject;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

public interface IAuditProjectService {

	MsgPage getAuditProjects(AuditProject entity, Integer curPageNum, Integer pageSize, String dataAuthority);
	
	MsgPage getExamineAuditProjects(AuditProject entity, Integer curPageNum, Integer pageSize, String dataAuthority);

	Integer saveAuditProject(AuditProject entity);
	
	void updateAuditProject(AuditProject entity);

	void deleteAuditProject(Integer id);

	AuditProject getAuditProject(Integer id);
	
	AuditProject getAuditProject(AuditProject entity);
	
	void saveAuditProjectExamine(Integer entityId, String examStr, Integer examResult, HhUsers user);
}
