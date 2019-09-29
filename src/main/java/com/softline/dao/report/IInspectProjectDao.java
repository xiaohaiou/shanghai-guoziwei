package com.softline.dao.report;

import java.util.List;

import com.softline.entity.ReportInspectProject;
import com.softline.entity.ReportInspectProjectOrder;
import com.softline.entity.ReportInspectProjectPlan;
import com.softline.entity.ReportInspectProjectProblem;
import com.softline.entity.bimr.BimrCriminalcaseWeek;
/**
 * 
 * @author tch
 *
 */
public interface IInspectProjectDao {
	
	
	public int getInspectProjectListCount(ReportInspectProject entity);
	
	public List<ReportInspectProject> getInspectProjectList(
			ReportInspectProject entity, Integer offsize, Integer pagesize);
	
	public ReportInspectProject getInspectProjectById(Integer id);
	
	public List<ReportInspectProjectProblem> getInspectProjectProblem(Integer inspectProjectId);
	
	public ReportInspectProjectProblem getReportInspectProjectProblemById(Integer id);
	
	public List<ReportInspectProjectOrder> getInspectProjectOrder(Integer inspect_project_id);
	
	public ReportInspectProjectOrder getReportInspectProjectOrderById(Integer id);
	
	public int getInspectProjectProblemListCount(ReportInspectProjectProblem entity, String parentOrg, Boolean isChangeTimeout);
	
	public List<ReportInspectProjectProblem> getInspectProjectProblemList(
			ReportInspectProjectProblem entity, String parentOrg, Boolean isChangeTimeout, Integer offsize, Integer pagesize);
	
	public int getInspectProjectChangeCount(ReportInspectProject entity);
	
	public List<ReportInspectProject> getInspectProjectChangeList(ReportInspectProject entity, Integer offsize, Integer pagesize);
	
	public Integer getInspectProjectProblemStatisticListCount(Integer year, Integer inspectType, String compId, String parentOrg);
	
	public List<Object[]> getInspectProjectProblemStatisticList(Integer year, Integer inspectType, String compId, String parentOrg, Integer offset, Integer pageSize);
	
	public ReportInspectProjectPlan getReportInspectProjectPlan(ReportInspectProjectPlan entity);
	
	public ReportInspectProject getInspectProjectByStatistic(Integer year, Integer inspectType, String compId, Integer itemType);
	
	public List<Object[]> getInspectProjectProblemCount(List<Integer> inspectProjectIds);
	
	public Integer getInspectProjectLeaderCheckCount(Integer year, Integer inspectType, String compId, String parentOrg);
	
	public List<Object[]> getInspectProjectLeaderCheckList(Integer year, Integer inspectType, String compId, String parentOrg, Integer offset, Integer pageSize);

	public ReportInspectProject inspectProjectSameCheck(ReportInspectProject entity);
	public List<ReportInspectProject> getshixiangQry1(ReportInspectProject criminal1);
	public List<ReportInspectProjectProblem> getshixiangQrypro(ReportInspectProjectProblem reportInspectProjectProblem);
}
