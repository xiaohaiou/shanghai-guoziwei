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
import com.softline.common.ExcelDataTreating;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.ReportCashflowWeeklyExecute;
import com.softline.entity.ReportMonthlyFinancingIntoDetail;
import com.softline.entity.ReportMonthlyFinancingOutDetail;
import com.softline.entity.ReportMonthlyInvestmentOutDetail;
import com.softline.entity.ReportWeeklyFinancingIntoDetail;
import com.softline.entity.ReportWeeklyFinancingOutDetail;
import com.softline.entity.ReportWeeklyInvestmentOutDetail;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.administration.IAdImportantService;
import com.softline.service.report.IReportCashflowMonthlyExecuteService;
import com.softline.service.report.IReportCashflowWeeklyExecuteService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportCashflowMonthlyExecute")
/**
 * 
 * @author tch
 *
 */
public class ReportCashflowMonthlyExecuteController {

	@Resource(name = "reportCashflowMonthlyExecuteService")
	private IReportCashflowMonthlyExecuteService reportCashflowMonthlyExecuteService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "reportCashflowWeeklyExecuteService")
	private IReportCashflowWeeklyExecuteService reportCashflowWeeklyExecuteService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			ReportCashflowMonthlyExecute entityview=reportCashflowMonthlyExecuteService.getReportCashflowMonthlyExecute(id);
			map.put("reportCashflowMonthlyExecute", entityview);
		}
	}
	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportCashflowMonthlyExecute entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportCashflowMonthlyExecute";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        entity.setGetOperateType(Base.fun_examine);
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        /*String dataauth = Common.dealinStr(systemService.getOtherOrganAuthData(str,Base.financetype));
        if(!Common.notEmpty(entity.getOrg()) )
        	entity.setOrg(dataauth);*/
        entity.setParentorg(str);
        MsgPage msgPage=reportCashflowMonthlyExecuteService.getReportCashflowMonthlyExecuteListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportCashflowMonthlyExecute/examinelist");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportCashflowMonthlyExecute/reportCashflowMonthlyExecuteExamineList";
	}
	
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportCashflowMonthlyExecute entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportCashflowMonthlyExecute";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        /*String dataauth = Common.dealinStr(systemService.getOtherOrganAuthData(str,Base.financetype));
        if(!Common.notEmpty(entity.getOrg()) )
        	entity.setOrg(dataauth);*/
        entity.setParentorg(str);
        MsgPage msgPage=reportCashflowMonthlyExecuteService.getReportCashflowMonthlyExecuteListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportCashflowMonthlyExecute/list");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportCashflowMonthlyExecute/reportCashflowMonthlyExecuteList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportCashflowMonthlyExecute entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		ReportCashflowMonthlyExecute entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportCashflowMonthlyExecute();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
			entityview.setMonth(Integer.parseInt (df1.format(date)));
			String data1="1";
			map.put("data1", data1);
			String data2="1";
			map.put("data2", data2);
			String data3="1";
			map.put("data3", data3);
		}
		else
		{	
			entityview=reportCashflowMonthlyExecuteService.getReportCashflowMonthlyExecute(entity.getId());
			//获取此记录所绑定的筹资性流入明细列表
			String data1 = "";
			List<ReportMonthlyFinancingIntoDetail> list1 = reportCashflowMonthlyExecuteService.getList1(entity.getId());
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
			//获取此记录所绑定的筹资性流出明细列表
			String data2 = "";
			List<ReportMonthlyFinancingOutDetail> list2 = reportCashflowMonthlyExecuteService.getList2(entity.getId());
			data2=JSONArray.toJSONString(list2);
			if("".equals(data2))
				data2 = "1";
			map.put("data2", data2);
			//获取此记录所绑定的投资性流出明细列表
			String data3 = "";
			List<ReportMonthlyInvestmentOutDetail> list3 = reportCashflowMonthlyExecuteService.getList3(entity.getId());
			data3=JSONArray.toJSONString(list3);
			if("".equals(data3))
				data3 = "1";
			map.put("data3", data3);
			/*List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_financeaccount);
			map.put("entityExamineviewlist", a);*/
		}
		map.put("entityview", entityview);	
		addData(map);
		
		return "/report/reportCashflowMonthlyExecute/reportCashflowMonthlyExecuteNewModify";
	}
	
	//跳转新增修改页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportCashflowMonthlyExecute entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportCashflowMonthlyExecute();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{	
			//获取此记录所绑定的筹资性流入明细列表
			String data1 = "";
			List<ReportMonthlyFinancingIntoDetail> list1 = reportCashflowMonthlyExecuteService.getList1(id);
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
			//获取此记录所绑定的筹资性流出明细列表
			String data2 = "";
			List<ReportMonthlyFinancingOutDetail> list2 = reportCashflowMonthlyExecuteService.getList2(id);
			data2=JSONArray.toJSONString(list2);
			if("".equals(data2))
				data2 = "1";
			map.put("data2", data2);
			//获取此记录所绑定的投资性流出明细列表
			String data3 = "";
			List<ReportMonthlyInvestmentOutDetail> list3 = reportCashflowMonthlyExecuteService.getList3(id);
			data3=JSONArray.toJSONString(list3);
			if("".equals(data3))
				data3 = "1";
			map.put("data3", data3);
			entityview=reportCashflowMonthlyExecuteService.getReportCashflowMonthlyExecute(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_cashflowmonthlyexecute);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/reportCashflowMonthlyExecute/reportCashflowMonthlyExecuteView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportCashflowMonthlyExecute entity, String stringList1, String stringList2, String stringList3,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			entity.setStatus(Base.examstatus_1);
			
			List<ReportMonthlyFinancingIntoDetail> list1 = new ArrayList<ReportMonthlyFinancingIntoDetail>();
			if(stringList1 != null && !"".equals(stringList1))
				entity.setList1(JSON.parseArray(stringList1, ReportMonthlyFinancingIntoDetail.class));
			else
				entity.setList1(new ArrayList<ReportMonthlyFinancingIntoDetail>());
			
			List<ReportMonthlyFinancingOutDetail> list2 = new ArrayList<ReportMonthlyFinancingOutDetail>();
			if(stringList2 != null && !"".equals(stringList2))
				entity.setList2(JSON.parseArray(stringList2, ReportMonthlyFinancingOutDetail.class));
			else
				entity.setList2(new ArrayList<ReportMonthlyFinancingOutDetail>());
				
			List<ReportMonthlyInvestmentOutDetail> list3 = new ArrayList<ReportMonthlyInvestmentOutDetail>();
			if(stringList3 != null && !"".equals(stringList3))
				entity.setList3(JSON.parseArray(stringList3, ReportMonthlyInvestmentOutDetail.class));
			else
				entity.setList3(new ArrayList<ReportMonthlyInvestmentOutDetail>());
			
			//获取之前所保存的列表信息
			if(entity.getId() != null){
				list1 = reportCashflowMonthlyExecuteService.getList1(entity.getId());
				list2 = reportCashflowMonthlyExecuteService.getList2(entity.getId());
				list3 = reportCashflowMonthlyExecuteService.getList3(entity.getId());
			}
			result= reportCashflowMonthlyExecuteService.saveReportCashflowMonthlyExecuteAndDetail(entity, use, list1, list2, list3);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(ReportCashflowMonthlyExecute entity, String stringList1, String stringList2, String stringList3,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			
			List<ReportMonthlyFinancingIntoDetail> list1 = new ArrayList<ReportMonthlyFinancingIntoDetail>();
			if(stringList1 != null && !"".equals(stringList1))
				entity.setList1(JSON.parseArray(stringList1, ReportMonthlyFinancingIntoDetail.class));
			else
				entity.setList1(new ArrayList<ReportMonthlyFinancingIntoDetail>());
			
			List<ReportMonthlyFinancingOutDetail> list2 = new ArrayList<ReportMonthlyFinancingOutDetail>();
			if(stringList2 != null && !"".equals(stringList2))
				entity.setList2(JSON.parseArray(stringList2, ReportMonthlyFinancingOutDetail.class));
			else
				entity.setList2(new ArrayList<ReportMonthlyFinancingOutDetail>());
				
			List<ReportMonthlyInvestmentOutDetail> list3 = new ArrayList<ReportMonthlyInvestmentOutDetail>();
			if(stringList3 != null && !"".equals(stringList3))
				entity.setList3(JSON.parseArray(stringList3, ReportMonthlyInvestmentOutDetail.class));
			else
				entity.setList3(new ArrayList<ReportMonthlyInvestmentOutDetail>());
			
			//获取之前所保存的列表信息
			if(entity.getId() != null) {
				list1 = reportCashflowMonthlyExecuteService.getList1(entity.getId());
				list2 = reportCashflowMonthlyExecuteService.getList2(entity.getId());
				list3 = reportCashflowMonthlyExecuteService.getList3(entity.getId());
			}
			result= reportCashflowMonthlyExecuteService.saveReportCashflowMonthlyExecuteAndDetail(entity, use, list1, list2, list3);
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("现金流月执行数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_xjlyzxsjsh_exam","report_cashflow_monthly_execute",entity.getId().toString()));

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
	public String listandreport(ReportCashflowMonthlyExecute entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			result= reportCashflowMonthlyExecuteService.saveReportCashflowMonthlyExecute(entity, use);
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("现金流月执行数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_xjlyzxsjsh_exam","report_cashflow_monthly_execute",entity.getId().toString()));
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
		ReportCashflowMonthlyExecute entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportCashflowMonthlyExecute();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{	
			//获取此记录所绑定的筹资性流入明细列表
			String data1 = "";
			List<ReportMonthlyFinancingIntoDetail> list1 = reportCashflowMonthlyExecuteService.getList1(id);
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
			//获取此记录所绑定的筹资性流出明细列表
			String data2 = "";
			List<ReportMonthlyFinancingOutDetail> list2 = reportCashflowMonthlyExecuteService.getList2(id);
			data2=JSONArray.toJSONString(list2);
			if("".equals(data2))
				data2 = "1";
			map.put("data2", data2);
			//获取此记录所绑定的投资性流出明细列表
			String data3 = "";
			List<ReportMonthlyInvestmentOutDetail> list3 = reportCashflowMonthlyExecuteService.getList3(id);
			data3=JSONArray.toJSONString(list3);
			if("".equals(data3))
				data3 = "1";
			map.put("data3", data3);
			
			entityview=reportCashflowMonthlyExecuteService.getReportCashflowMonthlyExecute(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_cashflowmonthlyexecute);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportCashflowMonthlyExecute/reportCashflowMonthlyExecuteView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<SysExamine> a=new ArrayList<SysExamine>();
		ReportCashflowMonthlyExecute entityview;
		if(id==null)
		{
			entityview=new ReportCashflowMonthlyExecute();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{	
			//获取此记录所绑定的筹资性流入明细列表
			String data1 = "";
			List<ReportMonthlyFinancingIntoDetail> list1 = reportCashflowMonthlyExecuteService.getList1(id);
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
			//获取此记录所绑定的筹资性流出明细列表
			String data2 = "";
			List<ReportMonthlyFinancingOutDetail> list2 = reportCashflowMonthlyExecuteService.getList2(id);
			data2=JSONArray.toJSONString(list2);
			if("".equals(data2))
				data2 = "1";
			map.put("data2", data2);
			//获取此记录所绑定的投资性流出明细列表
			String data3 = "";
			List<ReportMonthlyInvestmentOutDetail> list3 = reportCashflowMonthlyExecuteService.getList3(id);
			data3=JSONArray.toJSONString(list3);
			if("".equals(data3))
				data3 = "1";
			map.put("data3", data3);
			entityview=reportCashflowMonthlyExecuteService.getReportCashflowMonthlyExecute(id);
			a= examineService.getListExamine(entityview.getId(), Base.examkind_cashflowmonthlyexecute);
		    map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);	
		return "/report/reportCashflowMonthlyExecute/reportCashflowMonthlyExecuteExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= reportCashflowMonthlyExecuteService.saveReportCashflowMonthlyExecuteExamine(entityid, examStr, examResult, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_cashflow_monthly_execute", entityid.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		List<HhBase> debtType= baseService.getBases(Base.debtType);
		
		
		List<HhBase> currencyKind= baseService.getBases(Base.currencyKind);
		List<HhBase> liabilityTypes= baseService.getBases(Base.liabilityTypes);
		List<HhBase> revitalizeEquityFinancing= baseService.getBases(Base.truefalse);
		List<HhBase> domesticOverseas= baseService.getBases(Base.domesticOverseas);
		List<HhBase> sequelNew= baseService.getBases(Base.sequelNew);
		
		
		map.put("examstatustype",examstatustype);
		map.put("debtType", debtType);
		
		
		map.put("revitalizeEquityFinancing",revitalizeEquityFinancing);
		map.put("coordinationOperation", revitalizeEquityFinancing);
		map.put("liabilityTypes",liabilityTypes);
		map.put("currencyKind", currencyKind);
		map.put("sequelNew",sequelNew);
		map.put("domesticOverseas", domesticOverseas);
		
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportgroupdelete(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=reportCashflowMonthlyExecuteService.deleteReportCashflowMonthlyExecute(id, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_cashflow_monthly_execute", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportCashflowMonthlyExecute entity,String operate,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		result.setResult(true);
		if(entity==null)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}
	
	// 获取下拉框内容
	@ResponseBody
	@RequestMapping(value = "/_childtype", method = RequestMethod.POST)
	/**
	 * 返回类型的json
	 * @param val 类型ID
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	public void getVal(Integer val, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		List<HhBase> baseList = baseService.getChildBases(val);
		String json = JSONArray.toJSONString(baseList);
		response.getWriter().write(json);
	}

	@ResponseBody
	@RequestMapping(value = "getCoreComp", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getCoreComp(String organalID) throws IOException {
		HhOrganInfoTreeRelation entity = new HhOrganInfoTreeRelation();
		HhOrganInfoTreeRelation entityTemp = new HhOrganInfoTreeRelation();
		entity = reportCashflowWeeklyExecuteService.getCoreComp(organalID);		
		//传递所属核心企业信息，设值选择单位的status属性到该实体类当中		
		entityTemp = reportCashflowWeeklyExecuteService.getCompanyRelation(organalID);		
		entity.setStatus(entityTemp.getStatus());	
		String data="";
		data=JSONArray.toJSONString(entity);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="getWeeksData",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String getWeeksData(String compId, Integer year, Integer month) throws IOException {
		//获取此公司此年次月的所有周执行明细表数据
		List<Object> allData = new ArrayList<Object>();
		List<ReportWeeklyFinancingIntoDetail> weekslist1 = reportCashflowMonthlyExecuteService.getWeeksList1(compId, year, month);
		List<ReportWeeklyFinancingOutDetail> weekslist2 = reportCashflowMonthlyExecuteService.getWeeksList2(compId, year, month);
		List<ReportWeeklyInvestmentOutDetail> weekslist3 = reportCashflowMonthlyExecuteService.getWeeksList3(compId, year, month);
		//将ID都设置为null,常规字段也设置为null
		for (ReportWeeklyFinancingIntoDetail detail : weekslist1) {
			detail.setId(null);
			detail.setCreateDate(null);
			detail.setCreatePersonId(null);
			detail.setCreatePersonName(null);
			detail.setLastModifyDate(null);
			detail.setLastModifyPersonId(null);
			detail.setLastModifyPersonName(null);
		}
		for (ReportWeeklyFinancingOutDetail detail : weekslist2) {
			detail.setId(null);
			detail.setCreateDate(null);
			detail.setCreatePersonId(null);
			detail.setCreatePersonName(null);
			detail.setLastModifyDate(null);
			detail.setLastModifyPersonId(null);
			detail.setLastModifyPersonName(null);
		}
		for (ReportWeeklyInvestmentOutDetail detail : weekslist3) {
			detail.setId(null);
			detail.setCreateDate(null);
			detail.setCreatePersonId(null);
			detail.setCreatePersonName(null);
			detail.setLastModifyDate(null);
			detail.setLastModifyPersonId(null);
			detail.setLastModifyPersonName(null);
		}
		allData.add(weekslist1);
		allData.add(weekslist2);
		allData.add(weekslist3);
		String data = "";
		data=JSONArray.toJSONString(allData);
		return data;
	}
	
	//跳转到公司汇总页面
	@RequestMapping("/g_query")
	public String g_query(ReportCashflowMonthlyExecute entity,HttpServletRequest request,String date,Map<String, Object> map) throws IOException {
		
		String mapurl=request.getContextPath()+ "/reportCashflowMonthlyExecute";
		
		ReportCashflowMonthlyExecute tempBean = new ReportCashflowMonthlyExecute();
		
		if(null!=date && date.length()>=7){	//yyyy-mm		
			entity.setYear(Integer.valueOf(date.substring(0,4)));
			entity.setMonth(Integer.valueOf(date.substring(5,7)));			
		}
		
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        Integer pageNum = Integer.valueOf(curPageNum);

//        entity.setParentorg(str);
        MsgPage msgPage=reportCashflowMonthlyExecuteService.getReportCashflowMonthlyExecuteListForSumData(entity,pageNum,Base.pagesize,tempBean);
        
        map.put("tempBean", tempBean);
	    map.put("msgPage", msgPage);
		map.put("date", date);
	    map.put("searchurl", "/shanghai-gzw/reportCashflowMonthlyExecute/g_query");
	    map.put("entityview", entity);
		addData(map);

		return "/report/reportCashflowMonthlyExecute/reportCashflowMonthlyExecuteGroupGuery";
	}
	
	@ResponseBody
	@RequestMapping(value = "sumDataForNew", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String sumDataForNew(ReportCashflowMonthlyExecute entity,Map<String, Object> map) throws IOException {
		
		ReportCashflowMonthlyExecute backInfoBean = new ReportCashflowMonthlyExecute();	
		
		reportCashflowMonthlyExecuteService.sumForAllChildrenEntity(entity,backInfoBean);
		
		String data="";
		
		map.put("searchurl", "/shanghai-gzw/reportCashflowMonthlyExecute/sumDataForNew");
		
		data=JSONArray.toJSONString(backInfoBean);
		return data;
	}
	
	//现金流执行数据填报数据查询导出
	@RequestMapping("/export")
	public String export(ReportCashflowMonthlyExecute entity,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException {

        
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//数据权限
        entity.setParentorg(str);
        HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getOrg());
		if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
			entity.setOrg("");
		}
		List<ReportCashflowMonthlyExecute> list  = reportCashflowMonthlyExecuteService.getReportCashflowMonthlyExecuteListView(entity);
		List<ReportMonthlyFinancingIntoDetail> list1 = new ArrayList<ReportMonthlyFinancingIntoDetail>();
		List<ReportMonthlyFinancingOutDetail>  list2 = new ArrayList<ReportMonthlyFinancingOutDetail>();
		List<ReportMonthlyInvestmentOutDetail> list3 = new ArrayList<ReportMonthlyInvestmentOutDetail>();
		List<ReportMonthlyFinancingIntoDetail> reportMonthlyFinancingIntoDetail = new ArrayList<ReportMonthlyFinancingIntoDetail>();
		List<ReportMonthlyFinancingOutDetail>  reportMonthlyFinancingOutDetail = new ArrayList<ReportMonthlyFinancingOutDetail>();
		List<ReportMonthlyInvestmentOutDetail> reportMonthlyInvestmentOutDetail = new ArrayList<ReportMonthlyInvestmentOutDetail>();
		if(list.size()>0){			
			
			for (int i = 0; i < list.size(); i++) {
				reportMonthlyFinancingIntoDetail = reportCashflowMonthlyExecuteService.getList1(list.get(i).getId());
				reportMonthlyFinancingOutDetail =  reportCashflowMonthlyExecuteService.getList2(list.get(i).getId());
				reportMonthlyInvestmentOutDetail = reportCashflowMonthlyExecuteService.getList3(list.get(i).getId());
				list1.addAll(reportMonthlyFinancingIntoDetail);
				list2.addAll(reportMonthlyFinancingOutDetail);
				list3.addAll(reportMonthlyInvestmentOutDetail);
			}		
		}		
		//list2.addAll(reportWeeklyFinancingOutDetail);
		
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet1 = null;
		HSSFSheet sheet2 = null;
		HSSFSheet sheet3 = null;
		HSSFRow row1 = null;
		HSSFRow row2 = null;
		HSSFRow row3 = null;
		//导出文件名称
		String fileName =  "现金流月执行数据列表";
		
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		sheet1 = wb.createSheet("筹资性流入明细");
		sheet2 = wb.createSheet("筹资性流出明细");
		sheet3 = wb.createSheet("投资性流出明细");
		// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
		row1 = sheet1.createRow((int) 0);
		row2 = sheet2.createRow((int) 0);
		row3 = sheet3.createRow((int) 0);
		String [] titleArray1 = {"序号","承贷主体","贷款银行","贷款金额(万元)","下账时间","到期时间","贷款期限","融资下账成本(%)","新增或续作","贷款类型"};
		String [] titleArray2 = {"序号","承贷主体","还贷银行","融资类型","融资类型明细","还款日期","还款金额(万元)","币种"};
		String [] titleArray3 = {"序号","支付项目名称","支付金额(万元)","支付日期","支付币种","专项资金保障方案","投资类型"};
		row1 = tool.setBondExcelTitle(styleList.get(0),row1,titleArray1);
		row2 = tool.setBondExcelTitle(styleList.get(0),row2,titleArray2);
		row3 = tool.setBondExcelTitle(styleList.get(0),row3,titleArray3);
		
		sheet1 = this.setExcelDatasss1(sheet1,list1,styleList.get(1));
		sheet2 = this.setExcelDatasss2(sheet2,list2,styleList.get(1));
		sheet3 = this.setExcelDatasss3(sheet3,list3,styleList.get(1));

		tool.outputExcel(wb,fileName,response);

		
		return null;
	}


	private HSSFSheet setExcelDatasss3(HSSFSheet sheet,List<ReportMonthlyInvestmentOutDetail> entityList,HSSFCellStyle style) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
				HSSFRow row = null;
				//将数据写入Excel
				// 循环将数据写入Excel
				int t = 1;
				for (ReportMonthlyInvestmentOutDetail entity:entityList) {
					row = sheet.createRow(t);
					// 创建单元格，设置值
					Cell cell = row.createCell(0);
					cell.setCellStyle(style);
					cell.setCellValue(t++);
					//支付项目名称
					cell = row.createCell(1);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getPayProjectName());
					//支付金额(万元)
					cell = row.createCell(2);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getPayAmount());
					//支付日期
					cell = row.createCell(3);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getPayDate());
					//支付币种
					cell = row.createCell(4);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getCurrencyName());
					//专项资金保障方案
					cell = row.createCell(5);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getSpecialFundSupportPlan());
					//投资类型
					cell = row.createCell(6);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getInvestTypeName());
					
				}
				return sheet;
	}


	private HSSFSheet setExcelDatasss2(HSSFSheet sheet,List<ReportMonthlyFinancingOutDetail> entityList,HSSFCellStyle style) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
		HSSFRow row = null;
		//将数据写入Excel
		// 循环将数据写入Excel
		int t = 1;
		for (ReportMonthlyFinancingOutDetail entity:entityList) {
			row = sheet.createRow(t);
			// 创建单元格，设置值
			Cell cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(t++);
			//承贷主体
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getLoanCompName());
			//还贷银行
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getRepayBank());
			//融资类型
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getFinancingTypeName());
			//融资类型明细
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getFinancingTypeDetailName());
			//还款日期
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getRepayDate());
			//还款金额(万元)
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getRepayAmount());
			//币种
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getCurrencyName());
			
		}
		return sheet;
	}


	private HSSFSheet setExcelDatasss1(HSSFSheet sheet,List<ReportMonthlyFinancingIntoDetail> entityList,HSSFCellStyle style) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
		HSSFRow row = null;
		//将数据写入Excel
		// 循环将数据写入Excel
		int t = 1;
		for (ReportMonthlyFinancingIntoDetail entity:entityList) {
			row = sheet.createRow(t);
			// 创建单元格，设置值
			Cell cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(t++);
			//承贷主体
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getLoanCompName());
			//贷款银行
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getLoanBank());
			//贷款金额(万元)
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getLoanAmount());
			//下账时间
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getAccountDate());
			//到期时间
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getDueDate());
			//贷款期限
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getLengthOfMaturity());
			//融资下账成本(%)
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getFinancingAccountCost());
			//新增或续作
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getTypeName());
			//贷款类型
			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getLoanTypeName());
			
			
		}
		return sheet;
	}
}
