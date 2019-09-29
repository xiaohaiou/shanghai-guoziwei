package com.softline.service.report.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.dao.report.IInspectProjectDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhFile;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportInspectProject;
import com.softline.entity.ReportInspectProjectOrder;
import com.softline.entity.ReportInspectProjectPlan;
import com.softline.entity.ReportInspectProjectProblem;
import com.softline.entity.bimr.BimrCriminalcaseWeek;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IInspectProjectService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("inspectProjectService")
/**
 * 
 * @author tch
 *
 */
public class InspectProjectService implements IInspectProjectService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("commonService")
	private ICommonService commonService;
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Autowired
	@Qualifier("inspectProjectDao")
	private IInspectProjectDao inspectProjectDao;
	
	
	public MsgPage getInspectProjectListView(ReportInspectProject entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = inspectProjectDao.getInspectProjectListCount(entity);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<ReportInspectProject> list = inspectProjectDao
				.getInspectProjectList(entity, offset, pageSize);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}
	
	public ReportInspectProject getInspectProjectById(Integer id){
		ReportInspectProject entity=inspectProjectDao.getInspectProjectById(id);
		if(entity!=null)
			entity.setFileOne(commonService.getFileByEnIdAndType(entity.getId(), Base.inspect_project));
		
		return entity;
	}
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveInspectProject(ReportInspectProject entity,HhUsers use, MultipartFile file1)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveInspectProjectCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity.getId()==null)
		{
			HhOrganInfoTreeRelation a= systemService.getTreeOrganInfoNode(Base.financetype, entity.getCompId());
			entity.setParentorg(a.getVcOrganId());//权限
			
			entity.setIsDel(0);
			entity.setStatus(80040);//默认稽核中
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			
		}
		else
		{
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		commonDao.saveOrUpdate(entity);
		//保存附件
		if (file1!= null && !file1.isEmpty()) {
			if (entity.getFileOne() != null) {
				String uuid = entity.getFileOne().getFileUuid();
				Common.deleteFile(DES.report_path, uuid);
				commonDao.delete(entity.getFileOne());
			}
			HhFile f = commonService.saveFile(file1, entity.getId(),
					Base.inspect_project,DES.report_path);
		}
		
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveInspectProjectCheck(ReportInspectProject entity)
	{
		CommitResult result=new CommitResult();
		ReportInspectProject isexist=inspectProjectDao.inspectProjectSameCheck(entity);
		if(isexist!=null)
		{
			result=CommitResult.createErrorResult(isexist.getYear()+"年"+isexist.getCompName()+"的"+isexist.getItemTypeName()+"财务事项类型已存在,不能重复添加");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	@Override
	public CommitResult deleteInspectProject(ReportInspectProject entity, HhUsers use) {
		// TODO Auto-generated method stub
		CommitResult result=new CommitResult();
		/*result=theTimeDocumentCheck(entity);
		if(!result.isResult())
		{
			return result;
		}*/
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsDel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	@Override
	public MsgPage getInspectProjectProblemListView(ReportInspectProjectProblem entity, String parentOrg,  Boolean isChangeTimeout,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = inspectProjectDao.getInspectProjectProblemListCount(entity, parentOrg, isChangeTimeout);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<ReportInspectProjectProblem> list = 
				inspectProjectDao.getInspectProjectProblemList(entity, parentOrg, isChangeTimeout, offset, pageSize);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}
	
	public List<ReportInspectProjectProblem> getInspectProjectProblem(Integer inspectProjectId){
		return inspectProjectDao.getInspectProjectProblem(inspectProjectId);
	}
	
	public ReportInspectProjectProblem getReportInspectProjectProblemById(Integer id){
		ReportInspectProjectProblem t = inspectProjectDao.getReportInspectProjectProblemById(id);
		HhFile f = commonService.getFileByEnIdAndType(id, Base.inspect_project_problem);
		if(f != null){
			t.setFileOne(f);
		}
		return t;
	}
	
	public CommitResult saveInspectProjectProblem(ReportInspectProjectProblem entity,HhUsers use){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveInspectProjectProblemCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity.getId()==null)
		{
			entity.setIsDel(0);
			entity.setIsFinish(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			
		}
		else
		{
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		commonDao.saveOrUpdate(entity);
			
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	public CommitResult saveInspectProjectOrder(ReportInspectProjectOrder entity,HhUsers use){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveInspectProjectOrderCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity.getId()==null){
			entity.setOrderDate(df.format(new Date()));
			entity.setOrderSenderId(use.getVcEmployeeId() );
			entity.setOrderSenderName(use.getVcName());
			
		}else{
			entity.setOrderReportDate(df.format(new Date()));
			entity.setOrderReportId(use.getVcEmployeeId() );
			entity.setOrderReportName(use.getVcName());
		}
		commonDao.saveOrUpdate(entity);
			
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveInspectProjectProblemCheck(ReportInspectProjectProblem entity){
		CommitResult result=new CommitResult();
		/*if(!purchaseDao.savePurchaseRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该季度已经有数据，不能再添加");
			return result;
		}
		if(!purchaseDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}*/
		result.setResult(true);
		return result;
	}
	
	@Override
	public CommitResult deleteInspectProjectProblem(ReportInspectProjectProblem entity, HhUsers use) {
		// TODO Auto-generated method stub
		CommitResult result=new CommitResult();
		/*result=theTimeDocumentCheck(entity);
		if(!result.isResult())
		{
			return result;
		}*/
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsDel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	private CommitResult saveInspectProjectOrderCheck(ReportInspectProjectOrder entity){
		CommitResult result=new CommitResult();
		/*if(!purchaseDao.savePurchaseRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该季度已经有数据，不能再添加");
			return result;
		}
		if(!purchaseDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}*/
		result.setResult(true);
		return result;
	}
	
	public List<ReportInspectProjectOrder> getInspectProjectOrder(Integer inspect_project_id){
		return inspectProjectDao.getInspectProjectOrder(inspect_project_id);
	}
	
	public ReportInspectProjectOrder getReportInspectProjectOrderById(Integer id){
		return inspectProjectDao.getReportInspectProjectOrderById(id);
	}

	@Override
	public MsgPage getInspectProjectProblemStatisticListView(Integer year,
			Integer inspectType, String compId, String parentOrg, Integer curPageNum,
			Integer pageSize) {
		
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = inspectProjectDao.
				getInspectProjectProblemStatisticListCount(year, inspectType, compId, parentOrg);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<Object[]> list = inspectProjectDao.
				getInspectProjectProblemStatisticList(year, inspectType, compId, parentOrg, offset, pageSize);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}
	
	@Override
	public List<Object[]> getInspectProjectProblemStatisticListView(Integer year,
			Integer inspectType, String compId, String parentOrg) {
		List<Object[]> list = inspectProjectDao.
				getInspectProjectProblemStatisticList(year, inspectType, compId, parentOrg, null, null);
		return list;
	}
	
	@Override
	public CommitResult updateChangeManageProblem(
			ReportInspectProjectProblem entity, HhUsers use, MultipartFile file) {
		
		ReportInspectProjectProblem t = inspectProjectDao.getReportInspectProjectProblemById(entity.getId());
		t.setChangeLastTime(entity.getChangeLastTime());
		t.setIsFinish(entity.getIsFinish());
		t.setChangeMeasure(entity.getChangeMeasure());
		t.setChangeProgress(entity.getChangeProgress());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		t.setLastModifyDate(df.format(new  Date()));
		entity.setLastModifyPersonId(use.getVcEmployeeId());
		entity.setLastModifyPersonName(use.getVcName());
		
		//保存附件
		if (file!= null && !file.isEmpty()) {
			HhFile f = commonService.getFileByEnIdAndType(entity.getId(), Base.inspect_project_problem);
			if (f != null) {
				Common.deleteFile(DES.report_path, f.getFileUuid());
				commonDao.delete(f);
			}
			t.setFileOne(commonService.saveFile(file, entity.getId(), Base.inspect_project_problem, DES.report_path));
		}
		
		commonService.saveOrUpdate(t);
		CommitResult result = new CommitResult();
		result.setResult(true);
		result.setEntity(t);
		return result;
	}


	@Override
	public MsgPage getInspectProjectChangeListView(ReportInspectProject entity, Integer curPageNum, Integer pageSize) {
	
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = inspectProjectDao.getInspectProjectChangeCount(entity);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<ReportInspectProject> list = 
				inspectProjectDao.getInspectProjectChangeList(entity, offset, pageSize);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}



	
	public CommitResult saveInspectProjectPlan(ReportInspectProjectPlan entity,HhUsers use){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveInspectProjectPlanCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		
		ReportInspectProjectPlan oldEntity=inspectProjectDao.getReportInspectProjectPlan(entity);
		
		if(oldEntity==null)
		{
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		else
		{
			oldEntity.setYear(entity.getYear());
			oldEntity.setInspectType(entity.getInspectType());
			oldEntity.setInspectType(entity.getInspectType());
			oldEntity.setCompIdStr(entity.getCompIdStr());
			
			oldEntity.setLastModifyDate(df.format(new Date()));
			oldEntity.setLastModifyPersonId(use.getVcEmployeeId());
			oldEntity.setLastModifyPersonName(use.getVcName());
			
			commonDao.saveOrUpdate(oldEntity);
		}
		
			
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	@Override
	public ReportInspectProject getInspectProjectByStatistic(Integer year,
			Integer inspectType, String compId, Integer itemType) {

		return inspectProjectDao.getInspectProjectByStatistic(year, inspectType, compId, itemType);
	}

	@Override
	public MsgPage getInspectProjectApplyCheckListView(
			ReportInspectProject entity, Integer curPageNum, Integer pageSize) {
		
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = inspectProjectDao.getInspectProjectListCount(entity);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<ReportInspectProject> list = 
						inspectProjectDao.getInspectProjectList(entity, offset, pageSize);
				
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(buildInspectProjectApplyCheckList(list));
		return msgPage;
	}
	
	private List<Object[]> buildInspectProjectApplyCheckList(List<ReportInspectProject> list){
		if(list.isEmpty()){
			return Collections.emptyList();
		}
		
		List<Integer> ids = new ArrayList<Integer>(list.size());
		for(ReportInspectProject o: list){
			ids.add(o.getId());
		}
		List<Object[]> data = inspectProjectDao.getInspectProjectProblemCount(ids);
	
		List<Object[]> result = new ArrayList<Object[]>(list.size());
		for(ReportInspectProject o: list){
			Object[] d = getInspectProjectCount(data, o.getId());
			if(d == null){
				result.add(new Object[]{o.getId(), o.getItemName(), o.getItemPerson(), o.getStartTime(), o.getEndTime(), 0, 0, o.getStatus()});
			}else{
				result.add(new Object[]{o.getId(), o.getItemName(), o.getItemPerson(), o.getStartTime(), o.getEndTime(), d[1], d[2], o.getStatus()});
			}
		}
		return result;
	}
	
	private CommitResult saveInspectProjectPlanCheck(ReportInspectProjectPlan entity){
		CommitResult result=new CommitResult();
		/*if(!purchaseDao.savePurchaseRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该季度已经有数据，不能再添加");
			return result;
		}
		if(!purchaseDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}*/
		result.setResult(true);
		return result;
	}
	private Object[] getInspectProjectCount(List<Object[]> data, Integer id){
		for(Object[] i: data){
			if(((Integer)i[0]).intValue() == id.intValue()){
				return i;
			}
		}
		return null;
	}

	@Override
	public CommitResult saveInspectProjectCheck(Integer inspectProjectId, Integer isPass, String content, HhUsers user) {
		
		ReportInspectProject project = inspectProjectDao.getInspectProjectById(inspectProjectId);
		
		CommitResult result=new CommitResult();
		if(project.getStatus() != 80041){
			result.setResult(false);
			result.setExceptionStr("项目已经审核");
			return result;
		}
		
		project.setStatus(isPass == 0 ? 80040 : 80042);
		commonDao.saveOrUpdate(project);
		
		examineService.saveExamine(inspectProjectId, Base.inspect_project, user, content, isPass);
		
		result.setResult(true);
		
		return result;
	}
	
	@Override
	public MsgPage getInspectProjectLeaderCheckListView(Integer year,Integer inspectType, String compId, String parentOrg, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = inspectProjectDao.getInspectProjectLeaderCheckCount(year, inspectType, compId, parentOrg);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<Object[]> list = inspectProjectDao.
				getInspectProjectLeaderCheckList(year, inspectType, compId, parentOrg, offset, pageSize);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}

	@Override
	public CommitResult saveInspectProjectLeaderCheck(List<Integer> ids,
			List<Integer> isPasses, String content, HhUsers user) {
		
		boolean isPassAll = true;
		for(Integer v: isPasses){
			isPassAll = isPassAll && (v == 1);
		}
		
		CommitResult result = new CommitResult();
		List<ReportInspectProject> projects = new ArrayList<ReportInspectProject>(ids.size());
		for(Integer id: ids){
			ReportInspectProject o = inspectProjectDao.getInspectProjectById(id);
			if(o.getStatus() != 80042){
				result.setResult(false);
				result.setExceptionStr("存在未办结项目");
				return result;
			}
			projects.add(inspectProjectDao.getInspectProjectById(id));
		}
		
		for(int i = 0; i < projects.size(); i++){
			Integer isPass = isPasses.get(i);
			Integer status = isPassAll ? 80043: (isPass == 1) ? 80042 : 80040;
			ReportInspectProject project = projects.get(i);
			project.setStatus(status);
			commonDao.saveOrUpdate(project);
			saveLeaderCheck(project.getId(), isPass, content, user);
		}
		
		result.setResult(true);
		return result;
	}
	
	private void saveLeaderCheck(Integer inspectProjectId, Integer isPass, String content, HhUsers user){
		examineService.saveExamine(inspectProjectId, Base.inspect_project, user, content, isPass);
	}
	
	public ReportInspectProjectPlan getReportInspectProjectPlan(ReportInspectProjectPlan entity){
		return  inspectProjectDao.getReportInspectProjectPlan(entity);
	}
	
	@Override
	public CommitResult updateStatus(ReportInspectProject entity, HhUsers use) {
		// TODO Auto-generated method stub
		CommitResult result=new CommitResult();
		/*result=theTimeDocumentCheck(entity);
		if(!result.isResult())
		{
			return result;
		}*/
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setStatus(80041);//办结申请中
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	@Override
	public List<ReportInspectProject> getshixiangQry (ReportInspectProject reportInSpectProject1) {
		List<ReportInspectProject> list = inspectProjectDao
				.getInspectProjectList(reportInSpectProject1, null, null);
		return list;
		
	}

	@Override
	public List<ReportInspectProjectProblem> getshixiangQrypro(
			ReportInspectProjectProblem reportInSpectProjectpro, String parentOrg,  Boolean isChangeTimeout) {
		List<ReportInspectProjectProblem> list = 
				inspectProjectDao.getInspectProjectProblemList(reportInSpectProjectpro, parentOrg, isChangeTimeout, null, null);
		return list;
	}
}
