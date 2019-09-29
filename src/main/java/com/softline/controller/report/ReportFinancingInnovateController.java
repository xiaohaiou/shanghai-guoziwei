package com.softline.controller.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.softline.entity.SysExamine;
import com.softline.entity.financing.ReportFinancingInnovate;
import com.softline.entity.financing.ReportFinancingInnovateEnclosure;
import com.softline.entity.financing.ReportFinancingInnovateLog;
import com.softline.entity.financing.ReportFinancingShare;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFinancingInnovateService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IFinanceHistroyTreeService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;
/**
 * @author ky_tian
 */

@Controller
@RequestMapping("/reportFinancingInnovate")
public class ReportFinancingInnovateController {

	@Resource(name = "reportFinancingInnovateService")
	private IReportFinancingInnovateService reportFinancingInnovateService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "financeHistroyTreeService")
	private IFinanceHistroyTreeService financeHistroyTreeService;
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			ReportFinancingInnovate entityview=reportFinancingInnovateService.getReportFinancingInnovate(id);
			map.put("reportFinancingInnovate", entityview);
		}
	}
	
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
											ReportFinancingInnovate entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		entity.setCoreOrgname(request.getParameter("checkedCompanyName"));	
		entity.setCoreOrg(request.getParameter("organalID"));
		
		String mapurl=request.getContextPath()+ "/reportFinancingInnovate";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");

		//获取海航实业的nnodeID
		//String topStr = systemService.getTopNnodeID(Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
  
        String coreOrg = request.getParameter("organalID");
        ArrayList<String> arryListCoreOrg = null;
        if(null != coreOrg){
        	String[] strCoreOrgPara = coreOrg.split(",");
        	arryListCoreOrg = new ArrayList<String>();
        	for(String strPara:strCoreOrgPara){
        		arryListCoreOrg.add(strPara);
        	}
        }
        MsgPage msgPage = null;
        if(null!=arryListCoreOrg){
        	msgPage= reportFinancingInnovateService.getReportFinancingInnovateListView(entity,pageNum,Base.pagesize,Base.examstatus_1);      	
        }else{
        	msgPage=reportFinancingInnovateService.getReportFinancingInnovateListView(entity,pageNum,Base.pagesize,Base.examstatus_1);     	
        }
        map.put("organalID",coreOrg);
	    map.put("msgPage", msgPage);
	    map.put("entityview", entity);
	    
	    
	    
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date now=new Date();
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
        map.put("searchurl", "/shanghai-gzw/reportFinancingInnovate/list");
        map.put("exporturl", "/shanghai-gzw/reportFinancingInnovate/export");
	    
		addData(map);
		return "/report/reportFinancingInnovate/reportFinancingInnovateList";
	}
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
									ReportFinancingInnovate entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		entity.setCoreOrgname(request.getParameter("checkedCompanyName"));	
		entity.setCoreOrg(request.getParameter("organalID"));
		
		String mapurl=request.getContextPath()+ "/reportFinancingInnovate";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//获取海航实业的nnodeID
		//String topStr = systemService.getTopNnodeID(Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
//        entity.setGetOperateType(Base.fun_examine);
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=reportFinancingInnovateService.getReportFinancingInnovateListView(entity,pageNum,Base.pagesize,Base.examstatus_4);
	    map.put("msgPage", msgPage);
	    map.put("entityview", entity);
	    map.put("keyExmineValue", "none");
	    	    
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date now=new Date();
		String Date=df.format(now);
		
		if(checkedCompanyName != null){
			map.put("checkedCompanyName",checkedCompanyName);
		}
		if(organalID != null){
			map.put("organalID", organalID);
		}	    
		map.put("searchurl", "/shanghai-gzw/reportFinancingInnovate/examinelist");    
		addData(map);
		return "/report/reportFinancingInnovate/reportFinancingInnovateExamineList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
									ReportFinancingInnovate entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//获取海航实业的nnodeID
		//String topStr = systemService.getTopNnodeID(Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );	
		//数据权限
		entity.setParentorg(str);
		ReportFinancingInnovate entityview;
		List<ReportFinancingInnovateEnclosure> enclosureList = new ArrayList<ReportFinancingInnovateEnclosure>();
		if(entity.getId()==null)
		{	
			entityview=new ReportFinancingInnovate();
			/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			entityview.setExpectAccountDate((df.format(date)));*/
		}
		else
		{	
			entityview=reportFinancingInnovateService.getReportFinancingInnovate(entity.getId());
			//获取所绑定的附件记录
			enclosureList = reportFinancingInnovateService.getOldEnclosures(entity.getId());
		}
		entityview.setParentorg(str);
		map.put("enclosureList", enclosureList);
		map.put("entityview", entityview);			
		map.put("organalID", entity.getCoreOrg());
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date now=new Date();
		String Date=df.format(now);
		
		map.put("checkedCompanyName", entity.getCoreOrgname());
		
		map.put("entity", entity);
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
				
		addData(map);
		
		return "/report/reportFinancingInnovate/reportFinancingInnovateNewModify";
	}
	
	//跳转查看页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		ReportFinancingInnovate entityview;
		List<ReportFinancingInnovateEnclosure> enclosureList = new ArrayList<ReportFinancingInnovateEnclosure>();
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportFinancingInnovate();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			//entityview.setExpectAccountDate(df.format(date));
		}
		else
		{
			entityview=reportFinancingInnovateService.getReportFinancingInnovate(id);
			//获取所绑定的附件记录
			enclosureList = reportFinancingInnovateService.getOldEnclosures(id);
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportFinancingInnovate);
		}
		map.put("enclosureList", enclosureList);
		map.put("entityExamineviewlist", a);
		map.put("entityview", entityview);
		return "/report/reportFinancingInnovate/reportFinancingInnovateView";
	}
	
	//跳转查看日志页面
	@RequestMapping("/report")
	public String report(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//获取海航实业的nnodeID
		//String topStr = systemService.getTopNnodeID(Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		ReportFinancingInnovate entityview;
		if(id==null){
			entityview=new ReportFinancingInnovate();
		}
		else{
			entityview=reportFinancingInnovateService.getReportFinancingInnovate(id);
		}
		map.put("entityview", entityview);
		return "/report/reportFinancingInnovate/reportSelFilePage";
	}
	
	//跳转查看日志页面
	@RequestMapping("/logView")
	public String logView(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		ReportFinancingInnovate entityview;
		if(id==null)
		{
			entityview=new ReportFinancingInnovate();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			//entityview.setExpectAccountDate(df.format(date));
		}
		else
		{
			String data1 = "";
			entityview=reportFinancingInnovateService.getReportFinancingInnovate(id);
			//获取所绑定日志记录
			List<ReportFinancingInnovateLog> log = reportFinancingInnovateService.getLog(id);
			data1=JSONArray.toJSONString(log);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
		}
		map.put("entityview", entityview);
		return "/report/reportFinancingInnovate/reportFinancingInnovateLogView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportFinancingInnovate entity,HttpServletRequest request ,Map<String, Object> map,String op, 
										@RequestParam(value = "enclosure", required = false)MultipartFile[] enclosures) throws IOException {
		
		if(null != request.getParameter("checkedCompanyName")){
			entity.setCoreOrgname(request.getParameter("checkedCompanyName"));				
		}
		if(null != request.getParameter("organalID")){
			entity.setCoreOrg(request.getParameter("organalID"));				
		}
		
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			//entity.setCoreOrg((String)session.getAttribute("gzwsession_financecompany"));
			entity.setStatus(Base.examstatus_1);
			ReportFinancingInnovate oldEntity = new ReportFinancingInnovate();
			if(entity.getId() != null)
			//获取修改之前的融资项目进展实体
				oldEntity = reportFinancingInnovateService.getReportFinancingInnovate(entity.getId());
			result= reportFinancingInnovateService.saveReportFinancingInnovateAndEnclosure(entity,oldEntity, use, enclosures);
			examineService.deleteExamineByExamentityId(entity.getId());
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(ReportFinancingInnovate entity, HttpServletRequest request ,Map<String, Object> map,String op, @RequestParam(value = "enclosure", required = false)MultipartFile[] enclosures) throws IOException {
		
		if(null != request.getParameter("checkedCompanyName")){
			entity.setCoreOrgname(request.getParameter("checkedCompanyName"));				
		}
		if(null != request.getParameter("organalID")){
			entity.setCoreOrg(request.getParameter("organalID"));				
		}
		
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			//entity.setCoreOrg((String)session.getAttribute("gzwsession_financecompany"));
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			entity.setStatus(Base.examstatus_2);
			ReportFinancingInnovate oldEntity = new ReportFinancingInnovate();
			if(entity.getId() != null)
			//获取修改之前的融资项目进展实体
				oldEntity = reportFinancingInnovateService.getReportFinancingInnovate(entity.getId());
			result= reportFinancingInnovateService.saveReportFinancingInnovateAndEnclosure(entity,oldEntity, use, enclosures);
			examineService.deleteExamineByExamentityId(entity.getId());
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("共享类融资数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getCoreOrg(),systemService.getParentBynNodeID(entity.getCoreOrg(), Base.financetype),
																			"bimWork_cxlrzxzSh_e","report_financing_innovate",entity.getId().toString()));

		}
		else
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//列表页面的上报
	@ResponseBody
	@RequestMapping(value ="/listandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String listandreport(ReportFinancingInnovate entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			//entity.setCoreOrg((String)session.getAttribute("gzwsession_financecompany"));
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			entity.setStatus(Base.examstatus_2);
			result= reportFinancingInnovateService.saveReportFinancingInnovate(entity, use);
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("创新类融资数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getCoreOrg(),systemService.getParentBynNodeID(entity.getCoreOrg(), Base.financetype),
																			"bimWork_cxlrzxzSh_e","report_financing_innovate",entity.getId().toString()));
		}
		else
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//查询列表页面的上报
	@RequestMapping(value ="/postexam")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		ReportFinancingInnovate entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		List<ReportFinancingInnovateEnclosure> enclosureList = new ArrayList<ReportFinancingInnovateEnclosure>();
		if(id==null)
		{	
			entityview=new ReportFinancingInnovate();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			//entityview.setExpectAccountDate(df.format(date));
		}
		else
		{	
			entityview=reportFinancingInnovateService.getReportFinancingInnovate(id);
			//获取所绑定的附件记录
			enclosureList = reportFinancingInnovateService.getOldEnclosures(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportFinancingInnovate);
		    map.put("entityExamineviewlist", a);
		}
		map.put("enclosureList", enclosureList);
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportFinancingInnovate/reportFinancingInnovateView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<SysExamine> a=new ArrayList<SysExamine>();
		ReportFinancingInnovate entityview;
		List<ReportFinancingInnovateEnclosure> enclosureList = new ArrayList<ReportFinancingInnovateEnclosure>();
		if(id==null)
		{
			entityview=new ReportFinancingInnovate();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			//entityview.setExpectAccountDate(df.format(date));
		}
		else
		{	
			entityview=reportFinancingInnovateService.getReportFinancingInnovate(id);
			//获取所绑定的附件记录
			enclosureList = reportFinancingInnovateService.getOldEnclosures(id);
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportFinancingInnovate);
		    map.put("entityExamineviewlist", a);
		}
		map.put("enclosureList", enclosureList);
		map.put("entityview", entityview);	
		return "/report/reportFinancingInnovate/reportFinancingInnovateExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		
		CommitResult result= reportFinancingInnovateService.saveReportFinancingInnovateExamine(entityid, examStr, examResult, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_financing_innovate", entityid.toString());
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
		CommitResult result=reportFinancingInnovateService.deleteReportFinancingInnovate(id, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_financing_innovate", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportFinancingInnovate entity,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		result.setResult(true);
		if(entity==null)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}
	
	/**
	 * 解析报表
	 */
	/*@RequestMapping(value="/excelReport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String excelReport(@RequestParam("excelFile") MultipartFile file,String fileType,String excelName,HttpServletRequest request,String coreOrg,String coreOrgname) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		List<ArrayList<String>> rowList=new ArrayList<ArrayList<String>>();
		ExcelDataTreating tool = new ExcelDataTreating();
		if("s".equals(excelName)){
			rowList=tool.loadXls(file,fileType);
		}else if("sx".equals(excelName)){
			rowList=tool.loadXlsx(file,fileType);
		}
		ReportFinancingInnovate rfi = null;
		int num = 0;
		for(int i=3;i<rowList.size();i++){
			rfi = new ReportFinancingInnovate();
			rfi.setCoreOrg(coreOrg);
			rfi.setCoreOrgname(coreOrgname);
			rfi.setProjectName(rowList.get(i).get(1));
			rfi.setFinancialInstitution(rowList.get(i).get(2));
			rfi.setModelRequires(rowList.get(i).get(3));
			rfi.setHypothecationInformation(rowList.get(i).get(4));
			rfi.setComprehensiveFinancingCostRatio(rowList.get(i).get(5));
			rfi.setOperationDepartment(rowList.get(i).get(6));
			rfi.setFinancingProgress(rowList.get(i).get(7));
			rfi.setExpectAccountDate(rowList.get(i).get(8));
			rfi.setAlreadyAccountAmounts(rowList.get(i).get(9));
			rfi.setPersonLiable(rowList.get(i).get(10));
			rfi.setOperator(rowList.get(i).get(11));
			num+=reportFinancingInnovateService.saveReport(rfi,use);
		}
		
		return num+"";
	}*/

	//导出
	@RequestMapping("/export")
	public String export(ReportFinancingInnovate entity,String orgType,HttpServletRequest request ,HttpServletResponse res,Map<String, Object> map) throws IOException {
		/*if(!Common.notEmpty(entity.getCoreOrg())){
			if(null != request.getParameter("organalID") && 
					!"".equals(request.getParameter("organalID")) &&
					request.getParameter("organalID").contains(",")){
				entity.setCoreOrg(request.getParameter("organalID").split(",")[0]);	
			}else{
				HttpSession session=request.getSession();
				entity.setCoreOrg((String)session.getAttribute("gzwsession_financecompany"));							
			}
		}	*/
		HttpSession session=request.getSession();
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
		String fileName = "创新类推广项目数据";
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		
		sheet = wb.createSheet("创新类推广项目数据列表");
		//参数说明：1：开始行 2：结束行  3：开始列 4：结束列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,5)); 
		sheet.addMergedRegion(new CellRangeAddress(0,0,6,11));
		
		//设置宽度
		sheet.setColumnWidth((short) 0, (short) 2500);
		sheet.setColumnWidth((short) 1, (short) 4000);
		sheet.setColumnWidth((short) 2, (short) 6000);
		sheet.setColumnWidth((short) 6, (short) 6000);
		sheet.setColumnWidth((short) 7, (short) 9000);
		// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
		row = sheet.createRow(0);
		
		//title1
		Cell cell = row.createCell(0);
		cell.setCellStyle(styleList.get(0));
		cell.setCellValue("推广项目概况");
		
		cell = row.createCell(6);
		cell.setCellStyle(styleList.get(0));
		cell.setCellValue("融资进展");
		
		//title2
		row = sheet.createRow(1);
		for(int n=0;n<=12;n++){
			sheet.addMergedRegion(new CellRangeAddress(1,2,n,n));
		}
		String [] titleArray = {"业态公司","项目名称","融资主体","融资模式要求（金额、期限、结构）","项目抵质押条件","融资成本","目前存在的问题","操作主体","融资进展","预计下账时间","已下账","责任人","经办人"};
		row = tool.setExcelTitle(styleList.get(0),row,titleArray);
		String [] titleArray2 = {"","","","","","","","","","","",""};
		row = sheet.createRow(2);
		row = tool.setExcelTitle(styleList.get(0),row,titleArray2);
		//获取海航实业的nnodeID		
		//List<CompanyTree> otherOrganal = selectUserService.getOtherOrganal((String) request.getSession().getAttribute("gzwsession_financecompany"),Base.financetype);
		String topStr = (String)request.getSession().getAttribute("gzwsession_financecompany");

		Integer rowNum = 3;
		//判断orgid 海航实业并取下属机构数据/0：导出模板/业态公司取自身公司数据
		if("0".equals(orgType)){
			//导出模板
			sheet = reportFinancingInnovateService.setExcelData(sheet,null,styleList.get(1),rowNum);
		}else if(topStr.equals(entity.getCoreOrg())){
			//海航实业并取下属机构数据
			//财务树(获取海航实业下所有的企业)
			List<CompanyTree> ct = selectUserService.getOtherOrganal(topStr,Base.financetype);
			//遍历查询并写入数据
			sheet = reportFinancingInnovateService.getEntityForTopOrg(entity,ct.get(0).getChildren(),sheet,styleList.get(1),rowNum);
		}else{
			/*if(null != entity.getCoreOrg() &&  !"".equals(entity.getCoreOrg())){				
				List<CompanyTree> ct = selectUserService.getOtherOrganal(entity.getCoreOrg(),Base.financetype);
				//遍历查询并写入数据
*/				sheet = reportFinancingInnovateService.getEntityForOrg(entity,sheet,styleList.get(1),rowNum);
			/*}*/			
//			//导出coreOrg自身数据-查询并写入数据
//			sheet = reportFinancingInnovateService.getEntityForOrg(entity,sheet,styleList.get(1),rowNum);
		}
		//下载
		tool.outputExcel(wb,fileName,res);
		return null;
	}
	
	//一键审核
	@RequestMapping("/keyExmine")
	public String keyExmine(ReportFinancingInnovate entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportFinancingInnovate";
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
        //entity.setGetOperateType(Base.fun_examine);
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=reportFinancingInnovateService.getReportFinancingInnovateListView(entity,1,10000,Base.examstatus_4);        
        for(int i=0;i<msgPage.getList().size();i++){
        	ReportFinancingInnovate bean =(ReportFinancingInnovate) msgPage.getList().get(i);
    		CommitResult result= reportFinancingInnovateService.saveReportFinancingInnovateExamine(bean.getId(), "一键审核", 50116, (HhUsers) session.getAttribute("gzwsession_users"));
    		if(result.isResult())
    			potalMsgService.updatePortalMsg("report_financing_innovate", bean.getId().toString());
        }
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportFinancingInnovate/examinelist");
	    map.put("keyExmineValue", "keyExmineValue");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportFinancingInnovate/reportFinancingInnovateExamineList";
	}
	
	/**
	 * 共享类融资项目进展数据汇总
	 * @param flag
	 * @param organalID
	 * @param checkedCompanyName
	 * @param grouptype
	 * @param groupID
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/g_query")
	public String g_query(String flag,String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
									ReportFinancingInnovate entity,HttpServletRequest request,Map<String, Object> map) throws IOException {
		
		String mapurl=request.getContextPath()+ "/reportFinancingInnovate";
		map.put("mapurl", mapurl);

		if(null != request.getParameter("organalID") &&
				request.getParameter("organalID").split(",").length>0)
		entity.setCoreOrg(request.getParameter("organalID").split(",")[0]);
			
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//获取海航实业的nnodeID
		//String topStr = systemService.getTopNnodeID(Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        Integer pageNum = Integer.valueOf(curPageNum);
//        //数据权限
//        entity.setParentorg(str);
//        MsgPage msgPage=reportFinancingInnovateService.getReportFinancingInnovateListView(entity,pageNum,Base.pagesize,Base.examstatus_1);
//        MsgPage msgPageTemp=reportFinancingInnovateService.getReportFinancingInnovateListView(entity,pageNum,Base.pagesize,Base.examstatus_1);
//        
//        if(null!= flag){       	
//        	List<ReportFinancingInnovate> listBeans = new ArrayList<ReportFinancingInnovate>();
//        	if(msgPage.getList().size()>0 && 
//        			null!=msgPage.getList().get(0) &&
//        				null != checkedCompanyName ){ 
//        		ReportFinancingInnovate beanrfppBean = 
//        				reportFinancingInnovateService.groupSumDataByEntityCompany((ReportFinancingInnovate)msgPage.getList().get(0),checkedCompanyName);
//        		map.put("sumAlearyAccountMoney", beanrfppBean.getAlreadyAccountAmounts());
//        		map.put("sumScaleMoney", beanrfppBean.getScale());
//        		//reportFinancingInnovateService.saveReportFinancingInnovateExamine(beanrfppBean.getId(), "合计汇总", beanrfppBean.getStatus(),
//        		//																				(HhUsers) session.getAttribute("gzwsession_users"));
//        		listBeans.add(beanrfppBean);
//        		for(int i =1;i<msgPage.getList().size();i++){
//        			listBeans.add((ReportFinancingInnovate)msgPage.getList().get(i));
//        		}
//        		msgPage.setList(listBeans);  
//        	}       	       	
//        }
//	    map.put("msgPage", msgPageTemp);

        MsgPage msgPage = new MsgPage();
        ReportFinancingInnovate beanOut = new ReportFinancingInnovate();      
        msgPage.setPageSize(Base.pagesize);
        msgPage.setPageNum(pageNum);
      
        reportFinancingInnovateService.sumDataForSonCompanyData(entity,msgPage,beanOut);
        
	    map.put("msgPage", msgPage);
	    map.put("sumAlearyAccountMoney", beanOut.getAlreadyAccountAmounts());
	    map.put("sumScaleMoney", beanOut.getScale());
        
		map.put("flag", "flag");     
        map.put("op", "new");
        map.put("checkedCompanyName",checkedCompanyName);
        map.put("organalID", organalID);
        map.put("entityview", entity);
        map.put("grouptype", grouptype);
        map.put("groupid", groupID);
        map.put("searchurl", "/shanghai-gzw/reportFinancingInnovate/g_query");
        
		addData(map);	

		return "/report/reportFinancingInnovate/reportFinancingInnovateGroupGuery";
	}	
	
	@ResponseBody
	@RequestMapping(value ="/isVirtualCompany", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String isVirtualCompany(String organalId,HttpServletRequest request) throws IOException {
		String backInfo = "none";
		boolean flag = false;
		
		try{			
			flag = reportFinancingInnovateService.isVirtualCompany(organalId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(flag)
			backInfo = "success";
		
		return JSONArray.toJSONString(backInfo);
	}
	
}
