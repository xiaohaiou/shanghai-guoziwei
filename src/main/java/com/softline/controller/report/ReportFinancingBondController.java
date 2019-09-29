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
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.SysExamine;
import com.softline.entity.financing.ReportFinancingBond;
import com.softline.entity.financing.ReportFinancingBondEnclosure;
import com.softline.entity.financing.ReportFinancingBondLog;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFinancingBondService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;
/**
 * @author ky_tian
 */

@Controller
@RequestMapping("/reportFinancingBond")
public class ReportFinancingBondController {

	@Resource(name = "reportFinancingBondService")
	private IReportFinancingBondService reportFinancingBondService;
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
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			ReportFinancingBond entityview=reportFinancingBondService.getReportFinancingBond(id);
			map.put("reportFinancingBond", entityview);
		}
	}
	
	//周融资下账情况
	@RequestMapping("/sumList")
	public String _sumList(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
									ReportFinancingBond entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
       
		entity.setCoreOrgname(request.getParameter("checkedCompanyName"));	
		entity.setCoreOrg(request.getParameter("organalID"));
		
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		List<CompanyTree> otherOrganal = selectUserService.getOtherOrganal(str,Base.financetype);
		
		//获取海航实业的nnodeID
		String topStr = otherOrganal.get(0).getId();
		
		map.put("allCompanyTree", JSON.toJSONString(otherOrganal));
        //数据权限
        entity.setParentorg(str);
        //海航实业融资项目情况汇总(除债券类)
        List<String> dataList = new ArrayList<String>();
        map.put("entityview", entity);
        if(!Common.notEmpty(entity.getCoreOrg())){
			entity.setCoreOrg(str);
		}
        if(!entity.getCoreOrg().equals(topStr)){
        	 dataList = reportFinancingBondService.getDataList(entity);
        }else{
        	String allChildrenFinanceOrganal = systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
        	entity.setCoreOrg(allChildrenFinanceOrganal);
        	dataList = reportFinancingBondService.getDataList(entity);
        }
       
        map.put("dataList", dataList);
        
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
        map.put("searchurl", "/shanghai-gzw/reportFinancingBond/sumList");
        map.put("exporturl", "/shanghai-gzw/reportFinancingBond/sumExport");
        
		addData(map);
		return "/report/reportFinancingBond/reportFinancingBondSumList";
	}
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
													ReportFinancingBond entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		entity.setCoreOrgname(request.getParameter("checkedCompanyName"));	
		entity.setCoreOrg(request.getParameter("organalID"));
		
		String mapurl=request.getContextPath()+ "/reportFinancingBond";
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
        MsgPage msgPage=reportFinancingBondService.getReportFinancingBondListView(entity,pageNum,Base.pagesize,Base.examstatus_1);
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
        map.put("searchurl", "/shanghai-gzw/reportFinancingBond/list");
        map.put("exporturl", "/shanghai-gzw/reportFinancingBond/export");
		addData(map);
		return "/report/reportFinancingBond/reportFinancingBondList";
	}
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
							ReportFinancingBond entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		entity.setCoreOrgname(request.getParameter("checkedCompanyName"));	
		entity.setCoreOrg(request.getParameter("organalID"));
		
		String mapurl=request.getContextPath()+ "/reportFinancingBond";
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
        //entity.setGetOperateType(Base.fun_examine);
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=reportFinancingBondService.getReportFinancingBondListView(entity,pageNum,Base.pagesize,Base.examstatus_4);
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
		
		map.put("organalname", null);
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
        map.put("groupid", groupID);
        map.put("searchurl", "/shanghai-gzw/reportFinancingBond/examinelist");
	    
		addData(map);
		return "/report/reportFinancingBond/reportFinancingBondExamineList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(Integer grouptype,ReportFinancingBond entity,HttpServletRequest request ,HashMap<String, Object> map,String op) throws IOException {
		map.put("op", op);

		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//获取海航实业的nnodeID
		//String topStr = systemService.getTopNnodeID(Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)));
		
		ReportFinancingBond entityview;
		List<ReportFinancingBondEnclosure> enclosureList = new ArrayList<ReportFinancingBondEnclosure>();
		if(entity.getId()==null){	
			entityview=new ReportFinancingBond();
		}else{	
			entityview=reportFinancingBondService.getReportFinancingBond(entity.getId());
			//获取所绑定的附件记录
			enclosureList = reportFinancingBondService.getOldEnclosures(entity.getId());
		}
		entityview.setParentorg(str);
		map.put("enclosureList", enclosureList);
		map.put("entityview", entityview);	
		map.put("entity", entity);
		grouptype=Base.reportgroup_finance;
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date now=new Date();
		String Date=df.format(now);
		
		if(null!=entity && null!=entity.getCoreOrgname()){
			map.put("checkedCompanyName", entity.getCoreOrgname());
			map.put("organalID", entity.getCoreOrg());
		}
		
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
		addData(map);
		
		return "/report/reportFinancingBond/reportFinancingBondNewModify";
	}
	
	//跳转查看页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		ReportFinancingBond entityview;
		List<ReportFinancingBondEnclosure> enclosureList = new ArrayList<ReportFinancingBondEnclosure>();
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null){	
			entityview=new ReportFinancingBond();
		}else{
			entityview=reportFinancingBondService.getReportFinancingBond(id);
			//获取所绑定的附件记录
			enclosureList = reportFinancingBondService.getOldEnclosures(id);
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportFinancingBond);
		}
		map.put("enclosureList", enclosureList);
		map.put("entityExamineviewlist", a);
		map.put("entityview", entityview);
		return "/report/reportFinancingBond/reportFinancingBondView";
	}
	
	//跳转查看日志页面
	@RequestMapping("/logView")
	public String logView(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		ReportFinancingBond entityview;
		if(id==null)
		{
			entityview=new ReportFinancingBond();
			/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();*/
			//entityview.setExpectAccountDate(df.format(date));
		}
		else
		{
			String data1 = "";
			entityview=reportFinancingBondService.getReportFinancingBond(id);
			//获取所绑定日志记录
			List<ReportFinancingBondLog> log = reportFinancingBondService.getLog(id);
			data1=JSONArray.toJSONString(log);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
		}
		map.put("entityview", entityview);
		return "/report/reportFinancingBond/reportFinancingBondLogView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportFinancingBond entity,HttpServletRequest request ,Map<String, Object> map,String op, @RequestParam(value = "enclosure", required = false)MultipartFile[] enclosures) throws IOException {
		
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
			ReportFinancingBond oldEntity = new ReportFinancingBond();
			if(entity.getId() != null)
			//获取修改之前的融资项目进展实体
				oldEntity = reportFinancingBondService.getReportFinancingBond(entity.getId());
			result= reportFinancingBondService.saveReportFinancingBondAndEnclosure(entity,oldEntity, use, enclosures);
			examineService.deleteExamineByExamentityId(entity.getId());
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(ReportFinancingBond entity, HttpServletRequest request ,Map<String, Object> map,String op, @RequestParam(value = "enclosure", required = false)MultipartFile[] enclosures) throws IOException {
		
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
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			entity.setStatus(Base.examstatus_2);
			ReportFinancingBond oldEntity = new ReportFinancingBond();
			if(entity.getId() != null)
			//获取修改之前的融资项目进展实体
				oldEntity = reportFinancingBondService.getReportFinancingBond(entity.getId());
			result= reportFinancingBondService.saveReportFinancingBondAndEnclosure(entity,oldEntity, use, enclosures);
			examineService.deleteExamineByExamentityId(entity.getId());
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("债券类融资数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getCoreOrg(),systemService.getParentBynNodeID(entity.getCoreOrg(), Base.financetype),"bimWork_zqlrzxzSh_e","report_financing_bond",entity.getId().toString()));

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
	public String listandreport(ReportFinancingBond entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			result= reportFinancingBondService.saveReportFinancingBond(entity, use);
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("债券类融资数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getCoreOrg(),systemService.getParentBynNodeID(entity.getCoreOrg(), Base.financetype),"bimWork_zqlrzxzSh_e","report_financing_bond",entity.getId().toString()));
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
		ReportFinancingBond entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		List<ReportFinancingBondEnclosure> enclosureList = new ArrayList<ReportFinancingBondEnclosure>();
		if(id==null)
		{	
			entityview=new ReportFinancingBond();
			/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();*/
			//entityview.setExpectAccountDate(df.format(date));
		}else{	
			entityview=reportFinancingBondService.getReportFinancingBond(id);
			//获取所绑定的附件记录
			enclosureList = reportFinancingBondService.getOldEnclosures(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportFinancingBond);
		    map.put("entityExamineviewlist", a);
		}
		map.put("enclosureList", enclosureList);
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportFinancingBond/reportFinancingBondView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<SysExamine> a=new ArrayList<SysExamine>();
		ReportFinancingBond entityview;
		List<ReportFinancingBondEnclosure> enclosureList = new ArrayList<ReportFinancingBondEnclosure>();
		if(id==null)
		{
			entityview=new ReportFinancingBond();
		}
		else
		{	
			entityview=reportFinancingBondService.getReportFinancingBond(id);
			//获取所绑定的附件记录
			enclosureList = reportFinancingBondService.getOldEnclosures(id);
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportFinancingBond);
		    map.put("entityExamineviewlist", a);
		}
		map.put("enclosureList", enclosureList);
		map.put("entityview", entityview);	
		return "/report/reportFinancingBond/reportFinancingBondExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		
		CommitResult result= reportFinancingBondService.saveReportFinancingBondExamine(entityid, examStr, examResult, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_financing_bond", entityid.toString());
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
		CommitResult result=reportFinancingBondService.deleteReportFinancingBond(id, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_financing_bond", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportFinancingBond entity,HttpServletRequest request ,Map<String, Object> map){
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
	public String report(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//核心业态下拉
		map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype));
		//财务树(获取海航实业下所有的企业)
		//获取海航实业的nnodeID
		//String topStr = systemService.getTopNnodeID(Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		ReportFinancingBond entityview;
		if(id==null){
			entityview=new ReportFinancingBond();
		}else{
			entityview=reportFinancingBondService.getReportFinancingBond(id);
		}
		entityview.setParentorg(str);
		map.put("entityview", entityview);
		return "/report/reportFinancingBond/reportSelFilePage";
	}
	
	/**
	 * 解析报表
	 */
/*	@RequestMapping(value="/excelReport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
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
		ReportFinancingBond rfi = null;
		int num = 0;
		for(int i=3;i<rowList.size();i++){
			rfi = new ReportFinancingBond();
			num+=reportFinancingBondService.saveReport(rfi,use,rowList.get(i));
		}
		
		return num+"";
	}
*/
	//导出
	@RequestMapping("/export")
	public String export(String organalID,String checkedCompanyName,ReportFinancingBond entity,String orgType,HttpServletRequest request ,HttpServletResponse res,Map<String, Object> map) throws IOException {
		/*if(!Common.notEmpty(entity.getCoreOrg())){
			HttpSession session=request.getSession();
			entity.setCoreOrg((String)session.getAttribute("gzwsession_financecompany"));
		}*/
		entity.setCoreOrgname(request.getParameter("checkedCompanyName"));	
		entity.setCoreOrg(request.getParameter("organalID"));
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = null;
		HSSFRow row = null;
		//导出文件名称
		String fileName = "债券类推广项目数据";
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		
		sheet = wb.createSheet("债券类推广项目数据列表");
		//设置宽度
		sheet.setColumnWidth((short) 6, (short) 9000);
		sheet.setColumnWidth((short) 14, (short) 9000);
		sheet.setColumnWidth((short) 15, (short) 9000);
		sheet.setColumnWidth((short) 21, (short) 9000);
		
		//参数说明：1：开始行 2：结束行  3：开始列 4：结束列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,25)); 
		sheet.addMergedRegion(new CellRangeAddress(1,1,10,12)); 
		sheet.addMergedRegion(new CellRangeAddress(1,1,15,20)); 
		// 3.在sheet中添加表头第0行
		row = sheet.createRow(0);
		
		//title1
		Cell cell = row.createCell(0);
		cell.setCellStyle(styleList.get(0));
		cell.setCellValue("融资项目数据");
		
		//title2
		row = sheet.createRow(1);
		for(int n=0;n<=25;n++){
			if(n==12||n==10||n==11||n==20||n==15||n==16||n==17||n==18||n==19)
				continue;
			sheet.addMergedRegion(new CellRangeAddress(1,2,n,n));
		}
		String [] titleArray = {"序号","单位名称","债种","责任单位","承贷主体","担保条件","承销机构","审批机构","规模（亿）","期限（月）","成本","","","新增/续作","立项报备情况","进展情况","","","","","","发债阶段","发债时间","已下账","责任人","经办人"};
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
		
		row = sheet.createRow(2);
		String [] titleArray2 = {"","","","","","","","","","","预期发行利率利率","年化承销费","综合","","","承销商审批","审批机构审批","审计报告","法律意见书","债券评级","募集说明书","","","","",""};
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray2);
		
		//获取海航实业的nnodeID		
		List<CompanyTree> otherOrganal = selectUserService.getOtherOrganal((String) request.getSession().getAttribute("gzwsession_financecompany"),Base.financetype);
		String topStr = otherOrganal.get(0).getId();

		Integer rowNum = 3;
		//判断orgid 海航实业并取下属机构数据/0：导出模板/业态公司取自身公司数据
		if("0".equals(orgType)){
			//导出模板
			sheet = reportFinancingBondService.setExcelData(sheet,null,styleList.get(1),rowNum);
		}else if(topStr.equals(entity.getCoreOrg())){
			//获取页面所有数据
			List<CompanyTree> ct = selectUserService.getOtherOrganal(topStr,Base.financetype);
			//遍历查询并写入数据
			sheet = reportFinancingBondService.getEntityForTopOrg(entity,ct,sheet,styleList.get(1),rowNum);
		}else{
			//导出coreOrg自身数据-查询并写入数据
			sheet = reportFinancingBondService.getEntityForOrg(entity,sheet,styleList.get(1),rowNum);
		}
		//下载
		tool.outputExcel(wb,fileName,res);
		return null;
	}
	
	//导出
	@RequestMapping("/sumExport")
	public String sumExport(ReportFinancingBond entity,String orgType,HttpServletRequest request ,HttpServletResponse res,Map<String, Object> map) throws IOException {
		if(!Common.notEmpty(entity.getCoreOrg())){
			HttpSession session=request.getSession();
			entity.setCoreOrg((String)session.getAttribute("gzwsession_financecompany"));
		}
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = null;
		HSSFRow row = null;
		//导出文件名称
		String fileName = "债券类推广项目数据";
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		
		sheet = wb.createSheet("债券类推广项目数据列表");
		//设置宽度
		sheet.setColumnWidth((short) 6, (short) 9000);
		sheet.setColumnWidth((short) 14, (short) 9000);
		sheet.setColumnWidth((short) 15, (short) 9000);
		sheet.setColumnWidth((short) 21, (short) 9000);
		
		//参数说明：1：开始行 2：结束行  3：开始列 4：结束列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,25)); 
		sheet.addMergedRegion(new CellRangeAddress(1,1,10,12)); 
		sheet.addMergedRegion(new CellRangeAddress(1,1,15,20)); 
		// 3.在sheet中添加表头第0行
		row = sheet.createRow(0);
		
		//title1
		Cell cell = row.createCell(0);
		cell.setCellStyle(styleList.get(0));
		cell.setCellValue("债券类数据统计");
		
		//title2
		row = sheet.createRow(1);
		for(int n=0;n<=25;n++){
			if(n==12||n==10||n==11||n==20||n==15||n==16||n==17||n==18||n==19)
				continue;
			sheet.addMergedRegion(new CellRangeAddress(1,2,n,n));
		}
		String [] titleArray = {"业态公司","序号","债种","责任单位","承贷主体","担保条件","承销机构","审批机构","规模（亿）","期限（月）","成本","","","新增/续作","立项报备情况","进展情况","","","","","","发债阶段","发债时间","已下账","责任人","经办人"};
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
		
		row = sheet.createRow(2);
		String [] titleArray2 = {"","","","","","","","","","","预期发行利率利率","年化承销费","综合","","","承销商审批","审批机构审批","审计报告","法律意见书","债券评级","募集说明书","","","","",""};
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray2);
		
		//获取海航实业的nnodeID		
		List<CompanyTree> otherOrganal = selectUserService.getOtherOrganal((String) request.getSession().getAttribute("gzwsession_financecompany"),Base.financetype);
		String topStr = otherOrganal.get(0).getId();

		Integer rowNum = 3;
		//判断orgid 海航实业并取下属机构数据/0：导出模板/业态公司取自身公司数据
		if("0".equals(orgType)){
			//导出模板
			sheet = reportFinancingBondService.setExcelData(sheet,null,styleList.get(1),rowNum);
		}else if(topStr.equals(entity.getCoreOrg())){
			//海航实业并取下属机构数据
			//财务树(获取海航实业下所有的企业)
			List<CompanyTree> ct = selectUserService.getOtherOrganal(topStr,Base.financetype);
			//遍历查询并写入数据
			
			sheet = reportFinancingBondService.getEntityForTopOrg(entity,ct,sheet,styleList.get(1),rowNum);
		}else{
			//导出coreOrg自身数据-查询并写入数据
			sheet = reportFinancingBondService.getEntityForOrg(entity,sheet,styleList.get(1),rowNum);
		}
		//下载
		tool.outputExcel(wb,fileName,res);
		return null;
	}
	
	//一键审核页面
	@RequestMapping("/keyExmine")
	public String keyExmine(ReportFinancingBond entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportFinancingBond";
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
        MsgPage msgPage=reportFinancingBondService.getReportFinancingBondListView(entity,1,10000,Base.examstatus_4);
        for(int i=0;i<msgPage.getList().size();i++){
        	ReportFinancingBond bean =(ReportFinancingBond) msgPage.getList().get(i);	
    		CommitResult result= reportFinancingBondService.saveReportFinancingBondExamine(bean.getId(), "一键审核", 50116, (HhUsers) session.getAttribute("gzwsession_users"));
    		if(result.isResult())
    			potalMsgService.updatePortalMsg("report_financing_bond", bean.getId().toString());
        }
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportFinancingBond/examinelist");
	    map.put("entityview", entity);
	    map.put("keyExmineValue", "keyExmineValue");
		addData(map);
		return "/report/reportFinancingBond/reportFinancingBondExamineList";
	}
		

	/**
	 * 债券类融资项目进展数据汇总
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
									ReportFinancingBond entity,HttpServletRequest request,Map<String, Object> map) throws IOException {
		
		if(null!=request.getParameter("organalID") &&
				request.getParameter("organalID").split(",").length>0)
			entity.setCoreOrg(request.getParameter("organalID").split(",")[0]);
		
		String mapurl=request.getContextPath()+ "/reportFinancingProjectProgress";
		map.put("mapurl", mapurl);
		
		
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//获取海航实业的nnodeID
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        Integer pageNum = Integer.valueOf(curPageNum);
//        //数据权限
//        entity.setParentorg(str);
//        MsgPage msgPage=reportFinancingBondService.getReportFinancingBondListView(entity,pageNum,Base.pagesize,Base.examstatus_1);
//        MsgPage msgPageTemp=reportFinancingBondService.getReportFinancingBondListView(entity,pageNum,Base.pagesize,Base.examstatus_1);
//        if(null!= flag){       	
//        	List<ReportFinancingBond> listBeans = new ArrayList<ReportFinancingBond>();
//        	if(msgPage.getList().size()>0 && 
//        			null!=msgPage.getList().get(0) &&
//        				null != checkedCompanyName ){        		
//        		ReportFinancingBond beanrfppBean = 
//        				reportFinancingBondService.groupSumDataByEntityCompany((ReportFinancingBond)msgPage.getList().get(0),checkedCompanyName);
//        		map.put("sumAlearyAccountMoney", beanrfppBean.getAlreadyAccountAmounts());
//        		map.put("sumScaleMoney", beanrfppBean.getScale());
//        		//reportFinancingBondService.saveReportFinancingBondExamine(beanrfppBean.getId(), "合计汇总", beanrfppBean.getStatus(), (HhUsers) session.getAttribute("gzwsession_users"));
//        		listBeans.add(beanrfppBean);
//        		for(int i =1;i<msgPage.getList().size();i++){
//        			listBeans.add((ReportFinancingBond)msgPage.getList().get(i));
//        		}
//        		msgPage.setList(listBeans);  
//        	}       	       	
//        }
//	    map.put("msgPage", msgPageTemp);

        MsgPage msgPage = new MsgPage();
        ReportFinancingBond beanOut = new ReportFinancingBond();      
        msgPage.setPageSize(Base.pagesize);
        msgPage.setPageNum(pageNum);
      
        reportFinancingBondService.sumDataForSonCompanyData(entity,msgPage,beanOut);
       
	    map.put("msgPage", msgPage);
	    map.put("sumAlearyAccountMoney", beanOut.getAlreadyAccountAmounts());
	    map.put("sumScaleMoney", beanOut.getScale());
	    
	    
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date now=new Date();
		String Date=df.format(now);
		
        map.put("op", "new");
        map.put("flag", "flag");     
        map.put("Date", Date);
        map.put("entityview", entity);
        map.put("organalID", organalID);
        map.put("grouptype", grouptype);
        map.put("groupid", groupID);
        map.put("checkedCompanyName",checkedCompanyName);
        map.put("searchurl", "/shanghai-gzw/reportFinancingBond/g_query");
        
		addData(map);	

		return "/report/reportFinancingBond/reportFinancingBondGroupGuery";
	}	
	
	@ResponseBody
	@RequestMapping(value ="/isVirtualCompany", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String isVirtualCompany(String organalId,HttpServletRequest request) throws IOException {
		String backInfo = "none";
		boolean flag = false;
		
		try{			
			flag = reportFinancingBondService.isVirtualCompany(organalId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(flag)
			backInfo = "success";
		
		return JSONArray.toJSONString(backInfo);
	}
			
		
}
