package com.softline.dao.bimr;

import com.softline.entity.AuditProject;
import com.softline.entity.AuditProjectFindQuestion;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditWeekly;

import java.util.List;

public interface IBimrInsideAuditProjectDao {
    
	public Integer getBimrInsideAuditProjectListCount(BimrInsideAuditProject entity, String dataAuthority, String type, String vcEmployeeId);

	public List<BimrInsideAuditProject> getBimrInsideAuditProjectList(BimrInsideAuditProject entity, Integer offset, Integer pageSize, String dataAuthority, String type, String vcEmployeeId);

	public Integer saveBimrInsideAuditProject(BimrInsideAuditProject entity);

	public void updateBimrInsideAuditProject(BimrInsideAuditProject entity);
	
	public void deleteBimrInsideAuditProjectAppendix(String projectId);

	public void deleteBimrInsideAuditProject(Integer id);

	public BimrInsideAuditProject getBimrInsideAuditProjectById(Integer id);

	public BimrInsideAuditProject getBimrInsideAuditProject(BimrInsideAuditProject entity);

	public List<BimrInsideAuditProject> getBimrInsideAuditProjectHasNoChild(Integer status);

	public List<BimrInsideAuditProject> getBimrInsideAuditProjectsForList(Integer status);

	public List<BimrInsideAuditProject> getBimrInsideAuditProjectForChildren(Integer id);

	public List<BimrInsideAuditProject> getBimrInsideAuditProjectsIsOrNotChild(Integer isChildProject);

	public List<BimrInsideAuditProject> getBimrInsideAuditProjectsByParent(Integer parent_status);
	
	public List<BimrInsideAuditProject> getBimrProjectByCode(String code,String type);
	
	public Integer getInsideAuditProjectWeekReportListCount(BimrInsideAuditProject entity, String dataAuthority, String type, String vcEmployeeId);

	public List<BimrInsideAuditProject> getInsideAuditProjectWeekReportList(BimrInsideAuditProject entity, Integer offset, Integer pageSize, String dataAuthority, String type, String vcEmployeeId);
	
	public Integer getInsideAuditProjectQuestionListCount(BimrInsideAuditProject entity, String dataAuthority, String type, String vcEmployeeId);

	public List<BimrInsideAuditProject> getInsideAuditProjectQuestionList(BimrInsideAuditProject entity, Integer offset, Integer pageSize, String dataAuthority, String type, String vcEmployeeId);

//	public List<BimrInsideAuditProject> getInsideExport(
//			BimrInsideAuditProject entity, String dataAuthority);

	public List<BimrInsideAuditProject> getInsideExport(
			BimrInsideAuditProject entity, String dataAuthority,String vcEmployeeId);
	public List<Object[]> getInsideExport1(
			BimrInsideAuditProject entity, BimrInsideAuditQuestion entity1,String dataAuthority,String vcEmployeeId);
//	public List<BimrInsideAuditProject> getInsideExportXM(
//			BimrInsideAuditProject entity, String dataAuthority,String vcEmployeeId);

	public List<Object[]> getInsideExportXMDC(BimrInsideAuditProject entity,
			BimrInsideAuditWeekly entity1, String dataAuthority,
			String vcEmployeeId);

	public List<Object[]> getResultsDC(BimrInsideAuditProject entity,
			BimrInsideAuditQuestion entity1,AuditProjectFindQuestion entity2,AuditProject entity3, String dataAuthority,
			String vcEmployeeId);
	
//	List<BimrInsideAuditProject> getInsideExport(BimrInsideAuditProject entity,
//			String dataAuthority, String vcEmployeeId);
	//导出 风险问题类型数量
	public List<Object[]> getProblemTypeCountByID(Integer id);
	//导出 风险动因类型数量
	public List<Object[]> getRiskDriverTypesCountByID(Integer id);
	//导出 建议数量
	public Integer getInsideSuggestCount(Integer id);
	
	//导出 建议数量
	public List<Object> getfeedbackDesc(Integer id);
	
	//导出 
	public Double getInsideRectifySuccessCount(Integer id);
}
