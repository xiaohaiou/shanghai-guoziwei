package com.softline.service.bimr;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.AuditProject;
import com.softline.entity.AuditProjectFindQuestion;
import com.softline.entity.ReportPersonalloanNearweek;
import com.softline.entity.ReportPersonlloaninfoNew;
import com.softline.entity.bimr.BimrDuty;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditWeekly;
import com.softline.util.MsgPage;

public interface IBimrInsideAuditProjectService {
    
	public MsgPage getBimrInsideAuditProjects(BimrInsideAuditProject entity, Integer curPageNum, Integer pageSize, String dataAuthority, String type, String vcEmployeeId);
    
	public List<BimrInsideAuditProject> getBimrInsideAuditProjectList(BimrInsideAuditProject entity, Integer offset, Integer pageSize, String dataAuthority, String type);

	public Integer saveBimrInsideAuditProject(BimrInsideAuditProject entity);

	public void updateBimrInsideAuditProject(BimrInsideAuditProject entity);
    
	public void updateBimrProjectCode(BimrInsideAuditProject entity);

	public void deleteBimrInsideAuditProject(Integer id);

	public BimrInsideAuditProject getBimrInsideAuditProject(Integer id);

	public BimrInsideAuditProject getBimrInsideAuditProject(BimrInsideAuditProject entity);

	public List<BimrInsideAuditProject> getBimrInsideAuditProjectHasNoChild(Integer status);

	public List<BimrInsideAuditProject> getBimrInsideAuditProjectsForList(Integer status);

	public List<BimrInsideAuditProject> getBimrInsideAuditProjectForChildren(Integer id);

	public List<BimrInsideAuditProject> getBimrInsideAuditProjectsIsOrNotChild(Integer isChildProject);

	public List<BimrInsideAuditProject> getBimrInsideAuditProjectsByParent(Integer parent_status);

	public MsgPage getInsideAuditProjectWeeekReport(BimrInsideAuditProject entity, Integer curPageNum, Integer pageSize, String dataAuthority, String type, String vcEmployeeId);

	public MsgPage getInsideAuditProjectQuestion(BimrInsideAuditProject entity, Integer curPageNum, Integer pageSize, String dataAuthority, String type, String vcEmployeeId);
	
	public Integer saveCloseApply(BimrInsideAuditProject entity, MultipartFile[] sjFile, MultipartFile mailFile);

//	public XSSFWorkbook getInsideExportWorkbook(
//			List<BimrInsideAuditProject> list1);
//	导出666
	XSSFWorkbook getInsideExportWorkbook(List<BimrInsideAuditProject> list1);
//
	
	XSSFWorkbook getInsideExportWorkbook1(List<Object[]> list1);
	XSSFWorkbook getResultsExportWorkbook1(List<Object[]> list1);
	
	XSSFWorkbook getResultsAppRefineExportWorkbook(BimrInsideAuditProject entity,String dataAuthority,String vcEmployeeId);
	
	XSSFWorkbook getInsideExportWorkbook2(List<Object[]> list1);
	XSSFWorkbook getInsideExportWorkbook3(List<BimrInsideAuditWeekly> list1);
	public List<BimrInsideAuditProject> getInsideExport(BimrInsideAuditProject entity, String dataAuthority,String vcEmployeeId);
	public List<Object[]> getInsideExportXM(BimrInsideAuditProject entity, BimrInsideAuditWeekly entity1,String dataAuthority,String vcEmployeeId);
//	查询
	public List<Object[]> getInsideExportXM1(BimrInsideAuditProject entity,BimrInsideAuditQuestion entity1, String dataAuthority,String vcEmployeeId);

	public List<Object[]> getResultsDC(BimrInsideAuditProject entity,
			BimrInsideAuditQuestion entity1,AuditProjectFindQuestion entity2,AuditProject entity3, String dataAuthority,
			String vcEmployeeId);
	
////	111导出
//	public List<BimrInsideAuditProject> getBimrInsideAuditProjectList(BimrInsideAuditProject entity);
}
