package com.softline.controller.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.common.ExcelDataTreating;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.ReportFinancingProjectProgressEnclosure;
import com.softline.entity.ReportFinancingProjectProgressLog;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFinancingProjectProgressService;
import com.softline.service.report.IReportService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IFinanceHistroyTreeService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * 
 * @author ky_tian
 *
 */
@Controller
@RequestMapping("/reportFinancingProjectProgress")
public class ReportFinanceProjectProgressController {

	@Resource(name = "reportFinancingProjectProgressService")
	private IReportFinancingProjectProgressService reportFinancingProjectProgressService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "reportService")
	private IReportService reportService;
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "financeHistroyTreeService")
	private IFinanceHistroyTreeService financeHistroyTreeService;	
		
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			ReportFinancingProjectProgress entityview=reportFinancingProjectProgressService.getReportFinancingProjectProgress(id);
			map.put("reportFinancingProjectProgress", entityview);
		}
	}
	
	/**
	 * @param organalID
	 * @param checkedCompanyName
	 * @param grouptype
	 * @param groupID
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 * 		融资项目推进情况
	 * 	页面加载数据量过大，添加分页功能，优化页面数据显示
	 * 			by zl 2018/8/11
	 * 
	 */
	@RequestMapping("/sumList")
	public String _sumList(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
								ReportFinancingProjectProgress entity,HttpServletRequest request ,HashMap<String, Object> map) throws IOException {
	
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        
		//替换业态公司下拉选择框，重新设置请求参数
		entity.setCoreOrgname(request.getParameter("checkedCompanyName"));	
		entity.setCoreOrg(request.getParameter("organalID"));		
		//数据权限
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);						
				
		List<CompanyTree> otherOrganal = selectUserService.getOtherOrganal(str,Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(otherOrganal));
		
		//财务树(获取海航实业下所有的企业)
		//获取海航实业的nnodeID
		String topStr = otherOrganal.get(0).getId();
		
        //海航实业融资项目情况汇总(除债券类)
        List<String> projectProgressList = new ArrayList<String>();
        
        List<String> categoryList = new ArrayList<String>();

    	if(!Common.notEmpty(entity.getCoreOrg())){
    		entity.setCoreOrg(str);
    	}
    	
    	String orgForProgressList = systemService.getAllChildrenFinanceOrganal(str,Base.financetype);;
    	if(null!=entity)
    		orgForProgressList = systemService.getAllChildrenFinanceOrganal(entity.getCoreOrg(),Base.financetype);
    	
    	projectProgressList = reportFinancingProjectProgressService.getProjectProgressList(entity,orgForProgressList);
    	map.put("projectProgressList", projectProgressList);  	
    	
    	
    	//海航实业各业态推进中融资项目情况（除债券类）
    	MsgPage msgPage = new MsgPage();
    	if(!entity.getCoreOrg().equals(topStr)){  		
    		//替换所有实体公司的org字段的拼接  
    		String allChildrenCoreOrg = systemService.getAllChildrenFinanceOrganal(entity.getCoreOrg(),Base.financetype);
    		msgPage = reportFinancingProjectProgressService.getCategoryList(entity,allChildrenCoreOrg,pageNum,Base.pagesize);
    	}else{
    		String allChildrenFinanceOrganal = systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
    		msgPage = reportFinancingProjectProgressService.getCategoryList(entity,allChildrenFinanceOrganal,pageNum,Base.pagesize);
    	}        	
    	map.put("msgPage", msgPage);      	      	                   
       //替换财务树，选择下拉菜单 
		Date now=new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		String Date=df.format(now);
		if(checkedCompanyName != null){
			map.put("checkedCompanyName",checkedCompanyName);
		}		
		if(organalID != null){
			map.put("organalID", organalID);
		}		
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
        map.put("groupid", groupID);  
        map.put("exporturls", "/shanghai-gzw/reportFinancingProjectProgress/sumExport");
		addData(map);
		return "/report/reportFinancingProjectProgress/reportFinancingProjectProgressSumList";
	}		
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
							ReportFinancingProjectProgress entity,HttpServletRequest request ,HashMap<String, Object> map) throws IOException {
		
		String mapurl=request.getContextPath()+ "/reportFinancingProjectProgress";		
		entity.setCoreOrgname(request.getParameter("checkedCompanyName"));	
		entity.setCoreOrg(request.getParameter("organalID"));
		
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		String topStr = systemService.getTopNnodeID(Base.financetype);		
		//获取海航实业的nnodeID
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(topStr,Base.financetype)) );
		map.put("allNotInIds", JSON.toJSONString(selectUserService.getAllBackSelectedTreeOptions(topStr,str,Base.financetype)));
		
        Integer pageNum = Integer.valueOf(curPageNum);
                      
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=reportFinancingProjectProgressService.getReportFinancingProjectProgressListView(entity,pageNum,Base.pagesize,Base.examstatus_1);
	    map.put("msgPage", msgPage);
	    map.put("entityview", entity);
	    
		Date now=new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		String Date=df.format(now);
	
		if(checkedCompanyName != null){
			map.put("checkedCompanyName",checkedCompanyName);
		}		
		if(organalID != null){
			map.put("organalID", organalID);
		}
		
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
        map.put("groupid", groupID);
        map.put("searchurl", "/shanghai-gzw/reportFinancingProjectProgress/list");
        map.put("exporturl", "/shanghai-gzw/reportFinancingProjectProgress/export");
        
		addData(map);
		return "/report/reportFinancingProjectProgress/reportFinancingProjectProgressList";
	}
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
						ReportFinancingProjectProgress entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		entity.setCoreOrgname(request.getParameter("checkedCompanyName"));	
		entity.setCoreOrg(request.getParameter("organalID"));
				
		String mapurl=request.getContextPath()+ "/reportFinancingProjectProgress";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		
		//获取海航实业的nnodeID
//		String topStr = systemService.getTopNnodeID(Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        entity.setGetOperateType(Base.fun_examine);
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=reportFinancingProjectProgressService.getReportFinancingProjectProgressListView(entity,pageNum,Base.pagesize,Base.examstatus_4);
	    map.put("msgPage", msgPage);
	    map.put("entityview", entity);
	    map.put("keyExmineValue", "none");
	    
	    //替换选择下拉框，传后端参数
	    Date now=new Date();
	    DateFormat df = new SimpleDateFormat("yyyy-MM");
	    String Date=df.format(now);

		if(checkedCompanyName != null){
			map.put("checkedCompanyName",checkedCompanyName);
		}
		if(organalID != null){
			map.put("organalID", organalID);
		}
		
		map.put("organalname", null);
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
        map.put("groupid", groupID);
        map.put("searchurl", "/shanghai-gzw/reportFinancingProjectProgress/examinelist");
        
		addData(map);
		return "/report/reportFinancingProjectProgress/reportFinancingProjectProgressExamineList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportFinancingProjectProgress entity,Integer grouptype,
							HttpServletRequest request,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//获取海航实业的nnodeID
//		String topStr = systemService.getTopNnodeID(Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        //数据权限
        entity.setParentorg(str);
		ReportFinancingProjectProgress entityview;
		List<ReportFinancingProjectProgressEnclosure> enclosureList = new ArrayList<ReportFinancingProjectProgressEnclosure>();
		if(entity.getId()==null){	
			entityview=new ReportFinancingProjectProgress();
		}else{	
			entityview=reportFinancingProjectProgressService.getReportFinancingProjectProgress(entity.getId());
			//获取所绑定的附件记录
			enclosureList = reportFinancingProjectProgressService.getOldEnclosures(entity.getId());
		}		
		map.put("enclosureList", enclosureList);
		map.put("entityview", entityview);	
		map.put("entity", entity);
		

        grouptype=Base.reportgroup_finance;
        Date now=new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        String Date=df.format(now);
		
		if(null!=entity && null!=entity.getCoreOrgname()){
			map.put("checkedCompanyName", entity.getCoreOrgname());
			map.put("organalID", entity.getCoreOrg());
		}
		
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
		
		addData(map);	
		return "/report/reportFinancingProjectProgress/reportFinancingProjectProgressNewModify";
	}
	
	//跳转查看页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		ReportFinancingProjectProgress entityview;
		List<ReportFinancingProjectProgressEnclosure> enclosureList = new ArrayList<ReportFinancingProjectProgressEnclosure>();
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null){	
			entityview=new ReportFinancingProjectProgress();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			entityview.setExpectAccountDate(df.format(date));
		}else{
			entityview=reportFinancingProjectProgressService.getReportFinancingProjectProgress(id);
			//获取所绑定的附件记录
			enclosureList = reportFinancingProjectProgressService.getOldEnclosures(id);
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportfinancingprojectprogress);
		}
		map.put("enclosureList", enclosureList);
		map.put("entityExamineviewlist", a);
		map.put("entityview", entityview);
		return "/report/reportFinancingProjectProgress/reportFinancingProjectProgressView";
	}
	
	//跳转查看日志页面
	@RequestMapping("/logView")
	public String logView(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		ReportFinancingProjectProgress entityview;
		if(id==null){	
			entityview=new ReportFinancingProjectProgress();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			entityview.setExpectAccountDate(df.format(date));
		}else{
			String data1 = "";
			entityview=reportFinancingProjectProgressService.getReportFinancingProjectProgress(id);
			//获取所绑定日志记录
			List<ReportFinancingProjectProgressLog> log = reportFinancingProjectProgressService.getLog(id);
			data1=JSONArray.toJSONString(log);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
		}
		map.put("entityview", entityview);
		return "/report/reportFinancingProjectProgress/reportFinancingProjectProgressLogView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportFinancingProjectProgress entity,HttpServletRequest request ,Map<String, Object> map,
											String op, @RequestParam(value = "enclosure", required = false)MultipartFile[] enclosures) throws IOException {
				
		String data="";
		if(null != request.getParameter("checkedCompanyName")){
			entity.setCoreOrgname(request.getParameter("checkedCompanyName"));				
		}
		if(null != request.getParameter("organalID")){
			entity.setCoreOrg(request.getParameter("organalID"));				
		}
		
		CommitResult result=new CommitResult();
		if(entity==null){
			result=CommitResult.createErrorResult("该数据已被删除");
		}else{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			//entity.setOrg((String)session.getAttribute("gzwsession_financecompany"));
			entity.setStatus(Base.examstatus_1);
			ReportFinancingProjectProgress oldEntity = new ReportFinancingProjectProgress();
			if(entity.getId() != null)
			//获取修改之前的融资项目进展实体
				oldEntity = reportFinancingProjectProgressService.getReportFinancingProjectProgress(entity.getId());
			result= reportFinancingProjectProgressService.saveReportFinancingProjectProgressAndEnclosure(entity,oldEntity, use, enclosures);
			examineService.deleteExamineByExamentityId(entity.getId());
		}
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(ReportFinancingProjectProgress entity, HttpServletRequest request ,Map<String, Object> map,
														String op, @RequestParam(value = "enclosure", required = false)MultipartFile[] enclosures) throws IOException {
		if(null != request.getParameter("checkedCompanyName")){
			entity.setCoreOrgname(request.getParameter("checkedCompanyName"));				
		}
		if(null != request.getParameter("organalID")){
			entity.setCoreOrg(request.getParameter("organalID"));				
		}
		CommitResult result=new CommitResult();
		if(entity!=null){
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			entity.setOrg((String)session.getAttribute("gzwsession_financecompany"));
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			entity.setStatus(Base.examstatus_2);
			ReportFinancingProjectProgress oldEntity = new ReportFinancingProjectProgress();
			if(entity.getId() != null)
			//获取修改之前的融资项目进展实体
				oldEntity = reportFinancingProjectProgressService.getReportFinancingProjectProgress(entity.getId());
			result= reportFinancingProjectProgressService.saveReportFinancingProjectProgressAndEnclosure(entity,oldEntity, use, enclosures);
			if(entity.getId() != null){
				examineService.deleteExamineByExamentityId(entity.getId());
				if(result.isResult())
					potalMsgService.savePortalMsg(new PortalMsg("一般类融资数据需要审核","财务",df.format(new Date()),0,0,0,
							use.getVcName(),df.format(new Date()),use.getVcName(),
							df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),
							"bimWork_yblrzxzSh_e","report_financing_project_progress",entity.getId().toString()));
			}
		}else{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//列表页面的上报
	@ResponseBody
	@RequestMapping(value ="/listandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String listandreport(ReportFinancingProjectProgress entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			entity.setOrg((String)session.getAttribute("gzwsession_financecompany"));
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			entity.setStatus(Base.examstatus_2);
			result= reportFinancingProjectProgressService.saveReportFinancingProjectProgress(entity, use);
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("一般类融资数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),
																		"bimWork_yblrzxzSh_e","report_financing_project_progress",entity.getId().toString()));
		}else{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}	
		
	//查询列表页面的上报
	@RequestMapping(value ="/postexam")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		ReportFinancingProjectProgress entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		List<ReportFinancingProjectProgressEnclosure> enclosureList = new ArrayList<ReportFinancingProjectProgressEnclosure>();
		if(id==null){	
			entityview=new ReportFinancingProjectProgress();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			entityview.setExpectAccountDate(df.format(date));
		}else{	
			entityview=reportFinancingProjectProgressService.getReportFinancingProjectProgress(id);
			//获取所绑定的附件记录
			enclosureList = reportFinancingProjectProgressService.getOldEnclosures(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportfinancingprojectprogress);
		    map.put("entityExamineviewlist", a);
		}
		map.put("enclosureList", enclosureList);
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportFinancingProjectProgress/reportFinancingProjectProgressView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<SysExamine> a=new ArrayList<SysExamine>();
		ReportFinancingProjectProgress entityview;
		List<ReportFinancingProjectProgressEnclosure> enclosureList = new ArrayList<ReportFinancingProjectProgressEnclosure>();
		if(id==null)
		{
			entityview=new ReportFinancingProjectProgress();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			entityview.setExpectAccountDate(df.format(date));
		}else{	
			entityview=reportFinancingProjectProgressService.getReportFinancingProjectProgress(id);
			//获取所绑定的附件记录
			enclosureList = reportFinancingProjectProgressService.getOldEnclosures(id);
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportfinancingprojectprogress);
		    map.put("entityExamineviewlist", a);
		}
		map.put("enclosureList", enclosureList);
		map.put("entityview", entityview);	
		return "/report/reportFinancingProjectProgress/reportFinancingProjectProgressExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= reportFinancingProjectProgressService.saveReportFinancingProjectProgressExamine(entityid, examStr, examResult, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_financing_project_progress", entityid.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		//融资类别
		List<HhBase> financingCategory= baseService.getBases(Base.financingCategory);
		//新增或续作
		List<HhBase> sequelNew= baseService.getBases(Base.sequelNew);
		//项目进展
		List<HhBase> projectProgressType= baseService.getBases(Base.projectProgress);
		//融资模式
		List<HhBase> financingPattern= baseService.getBases(Base.financingPattern);
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		
		map.put("financingCategory",financingCategory);
		map.put("projectProgressType",projectProgressType);
		map.put("financingPattern", financingPattern);
		map.put("sequelNew",sequelNew);
		map.put("examstatustype",examstatustype);
		
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportgroupdelete(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=reportFinancingProjectProgressService.deleteReportFinancingProjectProgress(id, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_financing_account", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportFinancingProjectProgress entity,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		result.setResult(true);
		if(entity==null)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}
	
	//跳转查看日志页面
	@RequestMapping("/report")
	public String report(String grouptype,String groupID, Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		
		ReportFinancingProjectProgress entityview;
		if(id==null){
			entityview=new ReportFinancingProjectProgress();
		}
		else{
			entityview=reportFinancingProjectProgressService.getReportFinancingProjectProgress(id);
		}
		
		//数据权限
		entityview.setParentorg(str);
        String Date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM");
		if(!Common.notEmpty(Date))
		{
			Date now=new Date();
			Date=df.format(now);
		}
		
		
		map.put("organalname", null);
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
        map.put("groupid", groupID);
		
		
		map.put("entityview", entityview);
		return "/report/reportFinancingProjectProgress/reportSelFilePage";
	}
	
	/**
	 * 解析报表
	 */
	@RequestMapping(value="/excelReport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String excelReport(@RequestParam("excelFile") MultipartFile file,String fileType,String excelName,HttpServletRequest request,String coreOrg,String coreOrgname) throws IOException {
		
		Map mapBackInfo = null;
		//单位名称选择下拉框替换，更换对应的参数值
		coreOrg = request.getParameter("organalID");
		coreOrgname = request.getParameter("checkedCompanyName");		
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		List<ArrayList<String>> rowList=new ArrayList<ArrayList<String>>();
		ExcelDataTreating tool = new ExcelDataTreating();
		if("s".equals(excelName)){
			rowList=tool.loadXls(file,fileType);
		}else if("sx".equals(excelName)){
			rowList=tool.loadXlsx(file,fileType);
		}
		ReportFinancingProjectProgress rfi = null;
		try {
			for(int i=3;i<rowList.size();i++){
				if(rowList.get(i).size()<3){
					continue;
				}else {
					rfi = new ReportFinancingProjectProgress();
					rfi.setCoreOrg(coreOrg);
					rfi.setCoreOrgname(coreOrgname);	
					mapBackInfo = reportFinancingProjectProgressService.saveReport(rfi,use,rowList.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return JSONArray.toJSONString(mapBackInfo);		
	}

	
	//导出
	@RequestMapping("/export")
	public String export(String organalID,String checkedCompanyName,ReportFinancingProjectProgress entity,String orgType,HttpServletRequest request ,HttpServletResponse res,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
//		if(!Common.notEmpty(entity.getCoreOrg())){
//			entity.setCoreOrg((String)session.getAttribute("gzwsession_financecompany"));
//		}
     	entity.setCoreOrgname(request.getParameter("checkedCompanyName"));	
		entity.setCoreOrg(request.getParameter("organalID"));
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_financecompany");
        entity.setParentorg(str);
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = null;
		HSSFRow row = null;
		//导出文件名称
		String fileName = "融资进展反馈（按已完成审批和未审批反馈）";
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		
		sheet = wb.createSheet("融资进展反馈（按已完成审批和未审批反馈）");
		//设置宽度
		sheet.setColumnWidth((short) 5, (short) 9000);
		sheet.setColumnWidth((short) 7, (short) 6000);
		sheet.setColumnWidth((short) 8, (short) 6000);
		sheet.setColumnWidth((short) 14, (short) 9000);
		sheet.setColumnWidth((short) 15, (short) 9000);
		sheet.setColumnWidth((short) 21, (short) 9000);
//		
		//参数说明：1：开始行 2：结束行  3：开始列 4：结束列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,19)); 
		/*sheet.addMergedRegion(new CellRangeAddress(1,1,10,12)); 
		sheet.addMergedRegion(new CellRangeAddress(1,1,15,20)); */
		// 3.在sheet中添加表头第0行
		row = sheet.createRow(0);
		
		//title1
		Cell cell = row.createCell(0);
		cell.setCellStyle(styleList.get(0));
		cell.setCellValue("融资项目进度数据");
		
		//title2
		row = sheet.createRow(1);
		for(int n=0;n<=25;n++){
			sheet.addMergedRegion(new CellRangeAddress(1,2,n,n));
		}
		String [] titleArray = {"业态公司","序号","类别","操作主体","融资主体","抵质押信息","模式","机构","规模（亿）","期限（月）","成本","其他","综合 ","新/续","条件及结构","进展","后续","问题","预下账时间","已下账","责任人","经办人"};
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
		
		row = sheet.createRow(2);
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
		
		//获取海航实业的nnodeID		
		List<CompanyTree> otherOrganal = selectUserService.getOtherOrganal((String) request.getSession().getAttribute("gzwsession_financecompany"),Base.financetype);
		String topStr = otherOrganal.get(0).getId();
		
		Integer rowNum = 3;
		//判断orgid 海航实业并取下属机构数据/0：导出模板/业态公司取自身公司数据
		if("0".equals(orgType)){
			//导出模板
			sheet = reportFinancingProjectProgressService.setExcelData(row,sheet,null,styleList.get(1),rowNum,Base.projectProgressFalse,0);
			sheet = reportFinancingProjectProgressService.setExcelData(row,sheet,null,styleList.get(1),rowNum,Base.projectProgressTrue,0);
		}else if(topStr.equals(entity.getCoreOrg())){
			//海航实业并取下属机构数据
			//财务树(获取海航实业下所有的企业)
			List<CompanyTree> ct = selectUserService.getOtherOrganal(topStr,Base.financetype);
			//遍历查询并写入数据
			sheet = reportFinancingProjectProgressService.getEntityForTopOrg(entity,ct.get(0).getChildren(),sheet,styleList.get(1),rowNum);
		}else{
			//导出coreOrg自身数据-查询并写入数据
			sheet = reportFinancingProjectProgressService.getEntityForOrg(entity,sheet,styleList.get(1),rowNum);
		}
		//下载
		tool.outputExcel(wb,fileName,res);
		return null;
	}
	
	//一键审核
	@RequestMapping("/keyAudit")
	public String keyAudit(ReportFinancingProjectProgress entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportFinancingProjectProgress";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//核心业态下拉
		//map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype) );
		//财务树(获取海航实业下所有的企业)
		//获取海航实业的nnodeID
		//String topStr = systemService.getTopNnodeID(Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        entity.setGetOperateType(Base.fun_examine);
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=reportFinancingProjectProgressService.getReportFinancingProjectProgressListView(entity,1,10000,Base.examstatus_4);
        for(int i = 0;i<msgPage.getList().size();i++){
        	ReportFinancingProjectProgress bean =(ReportFinancingProjectProgress) msgPage.getList().get(i);
    		CommitResult result= reportFinancingProjectProgressService.saveReportFinancingProjectProgressExamine(bean.getId(), 
    																	"一键审核", 50116,(HhUsers) session.getAttribute("gzwsession_users"));
    		if(result.isResult())
    			potalMsgService.updatePortalMsg("report_financing_project_progress", bean.getId().toString());
        }
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportFinancingProjectProgress/examinelist");
	    map.put("entityview", entity);
	    map.put("keyExmineValue", "keyExmineValue");
		addData(map);
		return "/report/reportFinancingProjectProgress/reportFinancingProjectProgressExamineList";
	}
	
	//跳转到公司汇总页面
	@RequestMapping("/g_query")
	public String g_query(String flag,String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
															ReportFinancingProjectProgress entity,HttpServletRequest request,Map<String, Object> map) throws IOException {
			
		String mapurl=request.getContextPath()+ "/reportFinancingProjectProgress";	
		
		if(null != request.getParameter("organalID") &&
				request.getParameter("organalID").split(",").length>0)
			entity.setCoreOrg(request.getParameter("organalID").split(",")[0]);
		
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        Integer pageNum = Integer.valueOf(curPageNum);
//        //数据权限
//        entity.setParentorg(str);
//        MsgPage msgPage=reportFinancingProjectProgressService.getReportFinancingProjectProgressListView(entity,pageNum,Base.pagesize,Base.examstatus_1);
//        MsgPage msgPageTemp=reportFinancingProjectProgressService.getReportFinancingProjectProgressListView(entity,pageNum,Base.pagesize,Base.examstatus_1);
//        if(null!= flag){       	
//        	List<ReportFinancingProjectProgress> listBeans = new ArrayList<ReportFinancingProjectProgress>();
//        	if(msgPage.getList().size()>0 && 
//        			null!=msgPage.getList().get(0) &&
//        				null != checkedCompanyName ){        		
//        		ReportFinancingProjectProgress beanrfppBean = 
//        				reportFinancingProjectProgressService.groupSumDataByEntityCompany((ReportFinancingProjectProgress)msgPage.getList().get(0),checkedCompanyName);
//        		map.put("sumAlearyAccountMoney", beanrfppBean.getAlreadyAccountAmounts());
//        		map.put("sumScaleMoney", beanrfppBean.getScale());
//        		listBeans.add(beanrfppBean);
//        		for(int i =1;i<msgPage.getList().size();i++){
//        			listBeans.add((ReportFinancingProjectProgress)msgPage.getList().get(i));
//        		}
//        		msgPage.setList(listBeans);  
//        	}       	       	
//        }
//        map.put("flag", "flag");     
//	    map.put("msgPage", msgPageTemp);
//	    map.put("entityview", entity);
//	    //报表导出模板信息
//	    grouptype=Base.reportgroup_finance;
//	    HhFile templet=  reportService.getReportExportTemplet(grouptype,Base.reportgroup_type_simple);
//        map.put("templet", templet);
//        
//
//		Date now=new Date();
//		DateFormat df = new SimpleDateFormat("yyyy-MM");
//		String Date=df.format(now);
        MsgPage msgPage = new MsgPage();
        ReportFinancingProjectProgress beanOut = new ReportFinancingProjectProgress();      
        msgPage.setPageSize(Base.pagesize);
        msgPage.setPageNum(pageNum);
      
        reportFinancingProjectProgressService.sumDataForSonCompanyData(entity,msgPage,beanOut);
        
	    map.put("msgPage", msgPage);
	    map.put("sumAlearyAccountMoney", beanOut.getAlreadyAccountAmounts());
	    map.put("sumScaleMoney", beanOut.getScale());
		
	
        map.put("op", "new");
        map.put("flag", "flag");     
        map.put("entityview", entity);
        map.put("grouptype", grouptype);
        map.put("groupid", groupID);
        map.put("organalID", organalID);
        map.put("checkedCompanyName",checkedCompanyName);
        map.put("searchurl", "/shanghai-gzw/reportFinancingProjectProgress/g_query");     
		addData(map);	

		return "/report/reportFinancingProjectProgress/reportFinancingProjectProgressGroupGuery";
	}
	
	@ResponseBody
	@RequestMapping(value ="/isVirtualCompany", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String isVirtualCompany(String organalId,HttpServletRequest request) throws IOException {
		String backInfo = "none";
		boolean flag = false;
		
		try{			
			flag = reportFinancingProjectProgressService.isVirtualCompany(organalId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(flag)
			backInfo = "success";
		
		return JSONArray.toJSONString(backInfo);
	}
	
	    //导出
		@RequestMapping("/sumExport")
		public String sumExport(String organalID,String checkedCompanyName,ReportFinancingProjectProgress entity,String orgType,HttpServletRequest request ,HttpServletResponse res,Map<String, Object> map) throws IOException {
			HttpSession session=request.getSession();
//			if(!Common.notEmpty(entity.getCoreOrg())){
//				entity.setCoreOrg((String)session.getAttribute("gzwsession_financecompany"));
//			}
	     	entity.setCoreOrgname(request.getParameter("checkedCompanyName"));	
			entity.setCoreOrg(request.getParameter("organalID"));
			 //数据权限
			String str=(String) session.getAttribute("gzwsession_financecompany");
	        entity.setParentorg(str);
			// 1.创建一个workbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
			HSSFSheet sheet = null;
			HSSFRow row = null;
			//导出文件名称
			String fileName = "本年度已完成项目情况";
			// 设置表头
			ExcelDataTreating tool = new ExcelDataTreating();
			// 创建单元格，设置值表头，设置表头居中
			List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
			
			sheet = wb.createSheet("本年度已完成项目情况");
			//设置宽度
			sheet.setColumnWidth((short) 5, (short) 9000);
			sheet.setColumnWidth((short) 7, (short) 6000);
			sheet.setColumnWidth((short) 8, (short) 6000);
			sheet.setColumnWidth((short) 14, (short) 9000);
			sheet.setColumnWidth((short) 15, (short) 9000);
			sheet.setColumnWidth((short) 21, (short) 9000);
//			
			//参数说明：1：开始行 2：结束行  3：开始列 4：结束列  
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,19)); 
			/*sheet.addMergedRegion(new CellRangeAddress(1,1,10,12)); 
			sheet.addMergedRegion(new CellRangeAddress(1,1,15,20)); */
			// 3.在sheet中添加表头第0行
			row = sheet.createRow(0);
			
			//title1
			Cell cell = row.createCell(0);
			cell.setCellStyle(styleList.get(0));
			cell.setCellValue("融资项目进度数据");
			
			//title2
			row = sheet.createRow(1);
			for(int n=0;n<=25;n++){
				sheet.addMergedRegion(new CellRangeAddress(1,2,n,n));
			}
			String [] titleArray = {"业态公司","序号","类别","操作主体","融资主体","抵质押信息","模式","机构","下账金额(亿)","期限（月）","利率","前期/顾问费","综合 ","新/续","条件及结构","难点","解决方式","下账时间","总规模","责任人","经办人"};
			row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
			
			row = sheet.createRow(2);
			row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
			
			//获取海航实业的nnodeID		
			List<CompanyTree> otherOrganal = selectUserService.getOtherOrganal((String) request.getSession().getAttribute("gzwsession_financecompany"),Base.financetype);
			String topStr = otherOrganal.get(0).getId();
			
			Integer rowNum = 3;
		
			String orgForProgressList = systemService.getAllChildrenFinanceOrganal(str,Base.financetype);;
			if(null!=entity)
	    		orgForProgressList = systemService.getAllChildrenFinanceOrganal(entity.getCoreOrg(),Base.financetype);
			
			sheet = reportFinancingProjectProgressService.getEntityForOrgs(entity,sheet,styleList.get(1),rowNum,orgForProgressList);
			//下载
			tool.outputExcel(wb,fileName,res);
			return null;
		}
		
}
