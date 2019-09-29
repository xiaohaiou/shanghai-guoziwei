package com.softline.service.report;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


import com.softline.entity.HhUsers;
import com.softline.entity.ReportInspectProject;
import com.softline.entity.ReportInspectProjectOrder;
import com.softline.entity.ReportInspectProjectPlan;
import com.softline.entity.ReportInspectProjectProblem;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IInspectProjectService {

	public MsgPage getInspectProjectListView(ReportInspectProject entity,
			Integer curPageNum, Integer pageSize);
	
	public ReportInspectProject getInspectProjectById(Integer id);
	
	public CommitResult saveInspectProject(ReportInspectProject entity,HhUsers use,MultipartFile file1);
	
	public CommitResult deleteInspectProject(ReportInspectProject entity, HhUsers use);
	
	public List<ReportInspectProjectProblem> getInspectProjectProblem(Integer inspectProjectId);
	
	public ReportInspectProjectProblem getReportInspectProjectProblemById(Integer id);
	
	public CommitResult saveInspectProjectProblem(ReportInspectProjectProblem entity,HhUsers use);
	
	public CommitResult deleteInspectProjectProblem(ReportInspectProjectProblem entity, HhUsers use);
	
	public List<ReportInspectProjectOrder> getInspectProjectOrder(Integer inspect_project_id);
	
	public CommitResult saveInspectProjectOrder(ReportInspectProjectOrder entity,HhUsers use);
	
	public ReportInspectProjectOrder getReportInspectProjectOrderById(Integer id);
	
	public CommitResult updateChangeManageProblem(
			ReportInspectProjectProblem entity, HhUsers use, MultipartFile file);

	public MsgPage getInspectProjectProblemListView(ReportInspectProjectProblem entity, String parentOrg,
			Boolean isChangeTimeout, Integer curPageNum, Integer pageSize);
	
	public MsgPage getInspectProjectProblemStatisticListView(Integer year, 
			Integer inspectType, String compId, String parentOrg, Integer curPageNum, Integer pageSize);
	
	/**
	 * 年度稽核公司发现问题统计
	 * @param year
	 * @param inspectType
	 * @param compId
	 * @param parentOrg
	 * @return
	 */
	public List<Object[]>  getInspectProjectProblemStatisticListView(Integer year, 
			Integer inspectType, String compId, String parentOrg);
	
	public MsgPage getInspectProjectChangeListView(ReportInspectProject entity, Integer curPageNum, Integer pageSize);
	
	public CommitResult saveInspectProjectPlan(ReportInspectProjectPlan entity,HhUsers use);
	
	public ReportInspectProjectPlan getReportInspectProjectPlan(ReportInspectProjectPlan entity);
	
	public CommitResult updateStatus(ReportInspectProject entity, HhUsers use);
	
	public ReportInspectProject getInspectProjectByStatistic(Integer year, Integer inspectType, String compId, Integer itemType);
	
	public MsgPage getInspectProjectApplyCheckListView(ReportInspectProject entity, Integer curPageNum, Integer pageSize);
	
	public CommitResult saveInspectProjectCheck(Integer inspectProjectId, Integer isPass, String content, HhUsers use);
	
	public MsgPage getInspectProjectLeaderCheckListView(
			Integer year, Integer inspectType, String compId, String parentOrg, Integer curPageNum, Integer pageSize) ;
	
	public CommitResult saveInspectProjectLeaderCheck(List<Integer> ids, List<Integer> isPasses, String content, HhUsers user);
	
	/**
	 * 稽核财务事项信息维护
	 * @param reportInSpectProject1
	 * @return
	 */
	public List<ReportInspectProject> getshixiangQry(ReportInspectProject  reportInSpectProject1);
	
	/**
	 * 年度稽核整改问题清单
	 * @param reportInSpectProjectpro
	 * @param parentOrg
	 * @param isChangeTimeout
	 * @return
	 */
	public List<ReportInspectProjectProblem> getshixiangQrypro(ReportInspectProjectProblem  reportInSpectProjectpro,String parentOrg,  Boolean isChangeTimeout);
}
