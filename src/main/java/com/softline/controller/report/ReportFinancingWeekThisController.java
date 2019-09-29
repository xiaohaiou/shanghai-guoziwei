package com.softline.controller.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.softline.entity.financing.ReportFinancingWeekNext;
import com.softline.entity.financing.ReportFinancingWeekThis;
import com.softline.entity.financing.ReportFinancingWeekThisList;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFinancingWeekNextService;
import com.softline.service.report.IReportFinancingWeekThisService;
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
@RequestMapping("/reportFinancingWeekThis")
public class ReportFinancingWeekThisController {

	@Resource(name = "reportFinancingWeekThisService")
	private IReportFinancingWeekThisService reportFinancingWeekThisService;
	@Resource(name = "reportFinancingWeekNextService")
	private IReportFinancingWeekNextService reportFinancingWeekNextService;
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
			ReportFinancingWeekThis entityview=reportFinancingWeekThisService.getReportFinancingWeekThis(id);
			map.put("reportFinancingWeekThis", entityview);
		}
	}
	
	//周融资下账情况
	@RequestMapping("/sumList")
	public String _sumList(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
							ReportFinancingWeekThis entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
       			
		entity.setOrg(request.getParameter("organalID"));
		entity.setOrgname(request.getParameter("checkedCompanyName"));

		HttpSession session=request.getSession();
        Calendar c=Calendar.getInstance();
        if(entity.getWeek()==null){
        	entity.setWeek(c.get(Calendar.WEEK_OF_YEAR));
        }
        if(entity.getYear()==null){
        	entity.setYear(c.get(Calendar.YEAR));
        }
		String str=(String) session.getAttribute("gzwsession_financecompany");
		List<CompanyTree> otherOrganal = selectUserService.getOtherOrganal(str,Base.financetype);
		//获取海航实业的nnodeID
		String topStr = otherOrganal.get(0).getId();
		map.put("allCompanyTree", JSON.toJSONString(otherOrganal));
        //数据权限
        entity.setParentorg(str);
        //海航实业融资项目情况汇总(除债券类)
        List<String> dataList = new ArrayList<String>();
        

        if(!Common.notEmpty(entity.getOrg())){
			entity.setOrg(str);
		}
        //新增合并选项
        String progress = request.getParameter("progress");
        if("waiting".equals(progress)){
        	ReportFinancingWeekNext reportFinancingWeekNext = new ReportFinancingWeekNext();
        	reportFinancingWeekNext.setOrg(entity.getOrg());
        	reportFinancingWeekNext.setYear(entity.getYear());
        	reportFinancingWeekNext.setWeek(entity.getWeek());
            if(!entity.getOrg().equals(topStr)){
           	 dataList = reportFinancingWeekNextService.getDataList(reportFinancingWeekNext);
           }else{
           	String allChildrenFinanceOrganal = systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
           	entity.setOrg(allChildrenFinanceOrganal);
           	dataList = reportFinancingWeekNextService.getDataList(reportFinancingWeekNext);
           }
           map.put("option", "waiting");
        }else{
        	if(!entity.getOrg().equals(topStr)){
        		dataList = reportFinancingWeekThisService.getDataList(entity);
        	}else{
        		String allChildrenFinanceOrganal = systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
        		entity.setOrg(allChildrenFinanceOrganal);
        		dataList = reportFinancingWeekThisService.getDataList(entity);
        	}
        	map.put("option", "done");
        }
        
        map.put("entityview", entity);
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
		
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
        map.put("groupid", groupID);
        map.put("searchurl", "/shanghai-gzw/reportFinancingWeekThis/sumList");
        
        
		addData(map);
		return "/report/reportFinancingWeekThis/reportFinancingWeekSumList";
	}
			
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
									ReportFinancingWeekThis entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		if(null!=request.getParameter("organalID") &&
				!"".equals(request.getParameter("organalID")))			
			entity.setOrg(request.getParameter("organalID"));
		if(null!=request.getParameter("checkedCompanyName") &&
				!"".equals(request.getParameter("checkedCompanyName")))
			entity.setOrgname(request.getParameter("checkedCompanyName"));	
		
		String mapurl=request.getContextPath()+ "/reportFinancingWeekThis";
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
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=reportFinancingWeekThisService.getReportFinancingWeekThisListView(entity,pageNum,Base.pagesize,Base.examstatus_1);
	    map.put("msgPage", msgPage);
	    map.put("entityview", entity);
	    
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date now=new Date();
		String Date=df.format(now);
		
		map.put("checkedCompanyName",entity.getOrgname());
		map.put("organalID", entity.getOrg());
		
		map.put("organalname", null);
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
        map.put("groupid", groupID);
        map.put("searchurl", "/shanghai-gzw/reportFinancingWeekThis/list");
        map.put("exporturl", "/shanghai-gzw/reportFinancingWeekThis/export");
		addData(map);
		return "/report/reportFinancingWeekThis/reportFinancingWeekThisList";
	}
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
							ReportFinancingWeekThis entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		entity.setOrg(request.getParameter("organalID"));
		entity.setOrgname(request.getParameter("checkedCompanyName"));
		
		String mapurl=request.getContextPath()+ "/reportFinancingWeekThis";
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
        MsgPage msgPage=reportFinancingWeekThisService.getReportFinancingWeekThisListView(entity,pageNum,Base.pagesize,Base.examstatus_4);
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
        map.put("searchurl", "/shanghai-gzw/reportFinancingWeekThis/examinelist");
		addData(map);
		return "/report/reportFinancingWeekThis/reportFinancingWeekThisExamineList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(String organalID,String checkedCompanyName,Integer grouptype,Integer id,
						Integer groupID,ReportFinancingWeekThis entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		entity.setOrg(organalID);
		entity.setOrgname(checkedCompanyName);	
		
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//获取海航实业的nnodeID
		//String topStr = systemService.getTopNnodeID(Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        //数据权限
        entity.setParentorg(str); 		
		ReportFinancingWeekThis entityview;
		if(id==null)
		{	
			entityview=new ReportFinancingWeekThis();
		}
		else
		{	
			entityview=reportFinancingWeekThisService.getReportFinancingWeekThis(id);
		}
		map.put("entityview", entityview);
		map.put("setSize", entityview.getWeekThisSet().size());
		//绑定详细记录参数
		String fidArray = "";
		for(ReportFinancingWeekThisList r:entityview.getWeekThisSet()){
			fidArray+="&bzqdTr"+r.getFid()+"&,";
		}
		map.put("fidArray",fidArray);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date now=new Date();
		String Date=df.format(now);
		
		map.put("checkedCompanyName",entityview.getOrgname());
		map.put("organalID", entityview.getOrg());
		
		map.put("organalname", null);
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
        map.put("groupid", groupID);
		addData(map);
		
		return "/report/reportFinancingWeekThis/reportFinancingWeekThisNewModify";
	}
	
	//跳转查看页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		ReportFinancingWeekThis entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportFinancingWeekThis();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			entityview.setAccountDate(df.format(date));
		}
		else
		{
			entityview=reportFinancingWeekThisService.getReportFinancingWeekThis(id);
			//获取所绑定的附件记录
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportFinancingWeekThis);
		}
		map.put("entityExamineviewlist", a);
		map.put("entityview", entityview);
		return "/report/reportFinancingWeekThis/reportFinancingWeekThisView";
	}
	
	//跳转查看日志页面
	@RequestMapping("/logView")
	public String logView(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		ReportFinancingWeekThis entityview;
		if(id==null)
		{
			entityview=new ReportFinancingWeekThis();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			entityview.setAccountDate(df.format(date));
		}
		else
		{
			entityview=reportFinancingWeekThisService.getReportFinancingWeekThis(id);
		}
		map.put("entityview", entityview);
		return "/report/reportFinancingWeekThis/reportFinancingWeekThisLogView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportFinancingWeekThis entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		entity.setOrg(request.getParameter("organalID"));
		entity.setOrgname(request.getParameter("checkedCompanyName"));
		
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			//entity.setOrg((String)session.getAttribute("gzwsession_financecompany"));
			entity.setStatus(Base.examstatus_1);
			result= reportFinancingWeekThisService.saveReportFinancingWeekThisAndUpdate(entity, use);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(ReportFinancingWeekThis entity, HttpServletRequest request ,Map<String, Object> map,String op, @RequestParam(value = "weekThisList", required = false)List<ReportFinancingWeekThisList> weekThisList) throws IOException {
		
		entity.setOrg(request.getParameter("organalID"));
		entity.setOrgname(request.getParameter("checkedCompanyName"));	
		
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
			ReportFinancingWeekThis oldEntity = new ReportFinancingWeekThis();
			//if(entity.getId() != null)
			//获取修改之前的融资项目进展实体
			//oldEntity = reportFinancingWeekThisService.getReportFinancingWeekThis(entity.getId());
			result= reportFinancingWeekThisService.saveReportFinancingWeekThisAndUpdate(entity, use);
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("本周一般类融资数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_bzyblrzxzSh_e","report_financing_week_this",entity.getId().toString()));
		
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
	public String listandreport(ReportFinancingWeekThis entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			result= reportFinancingWeekThisService.saveReportFinancingWeekThis(entity, use);
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("本周一般类融资数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_bzyblrzxzSh_e","report_financing_week_this",entity.getId().toString()));
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
		ReportFinancingWeekThis entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportFinancingWeekThis();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			entityview.setAccountDate(df.format(date));
		}
		else
		{	
			entityview=reportFinancingWeekThisService.getReportFinancingWeekThis(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportFinancingWeekThis);
		    map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportFinancingWeekThis/reportFinancingWeekThisView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<SysExamine> a=new ArrayList<SysExamine>();
		ReportFinancingWeekThis entityview;
		if(id==null)
		{
			entityview=new ReportFinancingWeekThis();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			entityview.setAccountDate(df.format(date));
		}
		else
		{	
			entityview=reportFinancingWeekThisService.getReportFinancingWeekThis(id);
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportFinancingWeekThis);
		    map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);	
		return "/report/reportFinancingWeekThis/reportFinancingWeekThisExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		
		CommitResult result= reportFinancingWeekThisService.saveReportFinancingWeekThisExamine(entityid, examStr, examResult, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_financing_week_this", entityid.toString());
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
		CommitResult result=reportFinancingWeekThisService.deleteReportFinancingWeekThis(id, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_financing_week_this", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportFinancingWeekThis entity,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		result.setResult(true);
		if(entity==null)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}
	
	/**
	 * 查询一般融资对应日期的下账数据
	 */
	@ResponseBody
	@RequestMapping(value ="/getAccountsData", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getAccountsData(String orgnm,HttpServletRequest request ,Map<String, Object> map){
		String data= reportFinancingWeekThisService.getAccountsData(orgnm);
		return data;//JSONArray.toJSONString()
	}
	
	//导出
	@RequestMapping("/export")
	public String export(Integer id,HttpServletRequest request ,HttpServletResponse res,Map<String, Object> map) throws IOException {
		List<ReportFinancingProjectProgress> entityList=reportFinancingWeekThisService.getExportList(id);
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = null;
		HSSFRow row = null;
		//导出文件名称
		String fileName = "本周融资下账数据";
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		sheet = wb.createSheet("本周融资下账数据列表");
		// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
		row = sheet.createRow((int) 0);
		String [] titleArray = {"类别","操作主体","融资主体","抵质押信息","模式","机构","总规模","期限（月）","综合成本","新增/续作","条件及结构","融资进展","下账时间","下账金额（亿）","责任人","经办人"};
		row = tool.setExcelTitle(styleList.get(0),row,titleArray);
		
		sheet = this.setExcelData(sheet,entityList,styleList.get(1));

		tool.outputExcel(wb,fileName,res);
		return null;
	}
		
	
	public HSSFSheet setExcelData(HSSFSheet sheet,List<ReportFinancingProjectProgress> entityList,HSSFCellStyle style) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
		HSSFRow row = null;
		//将数据写入Excel
		// 循环将数据写入Excel
		int t = 1;
		for (ReportFinancingProjectProgress entity:entityList) {
			row = sheet.createRow(t);
			// 创建单元格，设置值
			Cell cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(t++);
			
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getCategoryName());
			
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getOperateOrgname());
			
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getFinancingOrgname());
			
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getHypothecationInformation());
			
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getPatternName());
			
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getFinancialInstitution());
			
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getScale());
			
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getTerm());
			
			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getComprehensiveFinancingCostRatio());
			
			cell = row.createCell(10);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getTypeName());
			
			cell = row.createCell(11);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getConditionStructure());
			
			cell = row.createCell(12);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getProjectProgressDescribe());
			
			cell = row.createCell(13);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getExpectAccountDate());
			
			cell = row.createCell(14);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getAlreadyAccountAmounts());
			
			cell = row.createCell(15);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getPersonLiable());
			
			cell = row.createCell(16);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getOperator());
		}
		return sheet;
	}
	
	//跳转到公司汇总页面
	@RequestMapping("/g_query")
	public String g_query(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
										ReportFinancingWeekThis entity,String category,HttpServletRequest request,
										ReportFinancingProjectProgress rfppBean,Map<String, Object> map) throws IOException{
			
		entity.setOrg(request.getParameter("organalID"));
		entity.setOrgname(request.getParameter("checkedCompanyName"));	
		
		rfppBean.setCoreOrg(request.getParameter("organalID"));
		rfppBean.setCoreOrgname(request.getParameter("checkedCompanyName"));
		if(null != category && !"".equals(category))
			rfppBean.setCategory(Integer.valueOf(category));
		
		Map resultMap ; //返回结果集		
		String mapurl=request.getContextPath()+ "/reportFinancingWeekThis";
		
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
        //数据权限
        entity.setParentorg(str);
        
        /**
         * 条件显示 category
         */
        //查询所有实体类
        resultMap =reportFinancingWeekThisService.getReportFinancingWeekThisListSumDataView(entity,rfppBean,pageNum,Base.pagesize,Base.examstatus_1);

	    map.put("msgPage", resultMap.get("msgPage"));
	    map.put("entityview", entity);
	    map.put("category", category);	    
	    map.put("sumAlreadyAccountAmounts", resultMap.get("sumAlreadyAccountAmounts"));   
	    map.put("sumScale", resultMap.get("sumScale"));
	    
	    
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date now=new Date();
		String Date=df.format(now);
		
		if(checkedCompanyName != null){
			map.put("checkedCompanyName",checkedCompanyName);
		}
		if(organalID != null){
			map.put("organalID", organalID);
		}
		
		map.put("year", entity.getYear());
		map.put("op", "new");
        map.put("op", "new");
        map.put("Date", Date);
        map.put("grouptype", grouptype);
		addData(map);
		return "/report/reportFinancingWeekThis/reportFinancingWeekThisGroupGuery";
	}
	

	//汇总导出
	@RequestMapping("/exportss")
	public String gRxport(String organalID,String checkedCompanyName,Integer grouptype,Integer groupID,
			ReportFinancingWeekThis entity,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException {
	
		entity.setOrg(request.getParameter("organalID"));
		entity.setOrgname(request.getParameter("checkedCompanyName"));	
		
	
        
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//数据权限
        entity.setParentorg(str);
      
        
        List<ReportFinancingProjectProgress> list = new ArrayList<ReportFinancingProjectProgress>();
        //查询所有实体类
        list =reportFinancingWeekThisService.getReportFinancingWeekThisListSumDataView(entity,Base.examstatus_1);

		
//		List<ReportFinancingProjectProgress> entityList=reportFinancingWeekNextService.getExportList(id);
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = null;
		HSSFRow row = null;
		//导出文件名称
		String fileName = "本周融资下账数据";
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		sheet = wb.createSheet("本周融资下账数据列表");
		// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
		row = sheet.createRow((int) 0);
		String [] titleArray = {"业态公司","序列","类别","操作主体","融资主体","抵质押信息","模式","机构","下账金额（亿）","期限（月）","利率","前期/顾问","综合","新/续","条件及结构","难点","解决方式","下账时间","总规模(亿)","责任人","经办人"};
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
		
		sheet = this.setExcelDatas(sheet,list,styleList.get(1));

		tool.outputExcel(wb,fileName,response);
		return null;
	}
	
	
	public HSSFSheet setExcelDatas(HSSFSheet sheet,List<ReportFinancingProjectProgress> entityList,HSSFCellStyle style) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
		HSSFRow row = null;
		//将数据写入Excel
		// 循环将数据写入Excel
		int t = 1;
		for (ReportFinancingProjectProgress entity:entityList) {
			row = sheet.createRow(t);
			// 创建单元格，设置值
			Cell cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getParentOrgName());
			
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(t++);
			
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getCategoryName());
			
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getOperateOrgname());
			
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getFinancingOrgname());
			
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getHypothecationInformation());
			
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getPatternName());
			
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getFinancialInstitution());

			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getAlreadyAccountAmounts());
			
			
			
			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getTerm());
			
			cell = row.createCell(10);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getInterestRatio());
			
			cell = row.createCell(11);
			cell.setCellStyle(style);
			cell.setCellValue("");
			
			cell = row.createCell(12);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getComprehensiveFinancingCostRatio());
			
			
			cell = row.createCell(13);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getTypeName());
			
			cell = row.createCell(14);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getConditionStructure());
			
			cell = row.createCell(15);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getProjectDifficulty());
			
			cell = row.createCell(16);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getSolution());
			

			
			cell = row.createCell(17);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getExpectAccountDate());
			
			cell = row.createCell(18);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getScale());
			
			
			cell = row.createCell(19);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getPersonLiable());
			
			cell = row.createCell(20);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getOperator());
		}
		return sheet;
	}
}
