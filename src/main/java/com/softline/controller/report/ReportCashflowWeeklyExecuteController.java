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
import com.softline.entity.ReportReceivabledebtinfoOuter;
import com.softline.entity.ReportWeeklyFinancingIntoDetail;
import com.softline.entity.ReportWeeklyFinancingOutDetail;
import com.softline.entity.ReportWeeklyInvestmentOutDetail;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.administration.IAdImportantService;
import com.softline.service.report.IReportCashflowWeeklyExecuteService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportCashflowWeeklyExecute")
/**
 * 
 * @author tch
 *
 */
public class ReportCashflowWeeklyExecuteController {

	@Resource(name = "reportCashflowWeeklyExecuteService")
	private IReportCashflowWeeklyExecuteService reportCashflowWeeklyExecuteService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			ReportCashflowWeeklyExecute entityview=reportCashflowWeeklyExecuteService.getReportCashflowWeeklyExecute(id);
			map.put("reportCashflowWeeklyExecute", entityview);
		}
	}
	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportCashflowWeeklyExecute entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportCashflowWeeklyExecute";
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
        MsgPage msgPage=reportCashflowWeeklyExecuteService.getReportCashflowWeeklyExecuteListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportCashflowWeeklyExecute/examinelist");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportCashflowWeeklyExecute/reportCashflowWeeklyExecuteExamineList";
	}
	
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportCashflowWeeklyExecute entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportCashflowWeeklyExecute";
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
       /* String dataauth = Common.dealinStr(systemService.getOtherOrganAuthData(str,Base.financetype));
        if(!Common.notEmpty(entity.getOrg()) )
        	entity.setOrg(dataauth);*/
        entity.setParentorg(str);
        MsgPage msgPage=reportCashflowWeeklyExecuteService.getReportCashflowWeeklyExecuteListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportCashflowWeeklyExecute/list");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportCashflowWeeklyExecute/reportCashflowWeeklyExecuteList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportCashflowWeeklyExecute entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );

		ReportCashflowWeeklyExecute entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportCashflowWeeklyExecute();
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
			entityview=reportCashflowWeeklyExecuteService.getReportCashflowWeeklyExecute(entity.getId());
			//获取此记录所绑定的筹资性流入明细列表
			String data1 = "";
			List<ReportWeeklyFinancingIntoDetail> list1 = reportCashflowWeeklyExecuteService.getList1(entity.getId());
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
			//获取此记录所绑定的筹资性流出明细列表
			String data2 = "";
			List<ReportWeeklyFinancingOutDetail> list2 = reportCashflowWeeklyExecuteService.getList2(entity.getId());
			data2=JSONArray.toJSONString(list2);
			if("".equals(data2))
				data2 = "1";
			map.put("data2", data2);
			//获取此记录所绑定的投资性流出明细列表
			String data3 = "";
			List<ReportWeeklyInvestmentOutDetail> list3 = reportCashflowWeeklyExecuteService.getList3(entity.getId());
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
		
		return "/report/reportCashflowWeeklyExecute/reportCashflowWeeklyExecuteNewModify";
	}
	
	//跳转新增修改页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportCashflowWeeklyExecute entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportCashflowWeeklyExecute();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{	
			//获取此记录所绑定的筹资性流入明细列表
			String data1 = "";
			List<ReportWeeklyFinancingIntoDetail> list1 = reportCashflowWeeklyExecuteService.getList1(id);
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
			//获取此记录所绑定的筹资性流出明细列表
			String data2 = "";
			List<ReportWeeklyFinancingOutDetail> list2 = reportCashflowWeeklyExecuteService.getList2(id);
			data2=JSONArray.toJSONString(list2);
			if("".equals(data2))
				data2 = "1";
			map.put("data2", data2);
			//获取此记录所绑定的投资性流出明细列表
			String data3 = "";
			List<ReportWeeklyInvestmentOutDetail> list3 = reportCashflowWeeklyExecuteService.getList3(id);
			data3=JSONArray.toJSONString(list3);
			if("".equals(data3))
				data3 = "1";
			map.put("data3", data3);
			entityview=reportCashflowWeeklyExecuteService.getReportCashflowWeeklyExecute(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_cashflowweeklyexecute);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/reportCashflowWeeklyExecute/reportCashflowWeeklyExecuteView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportCashflowWeeklyExecute entity, String stringList1, String stringList2, String stringList3,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			
			List<ReportWeeklyFinancingIntoDetail> list1 = new ArrayList<ReportWeeklyFinancingIntoDetail>();
			if(stringList1 != null && !"".equals(stringList1))
				entity.setList1(JSON.parseArray(stringList1, ReportWeeklyFinancingIntoDetail.class));
			else
				entity.setList1(new ArrayList<ReportWeeklyFinancingIntoDetail>());
			
			List<ReportWeeklyFinancingOutDetail> list2 = new ArrayList<ReportWeeklyFinancingOutDetail>();
			if(stringList2 != null && !"".equals(stringList2))
				entity.setList2(JSON.parseArray(stringList2, ReportWeeklyFinancingOutDetail.class));
			else
				entity.setList2(new ArrayList<ReportWeeklyFinancingOutDetail>());
				
			List<ReportWeeklyInvestmentOutDetail> list3 = new ArrayList<ReportWeeklyInvestmentOutDetail>();
			if(stringList3 != null && !"".equals(stringList3))
				entity.setList3(JSON.parseArray(stringList3, ReportWeeklyInvestmentOutDetail.class));
			else
				entity.setList3(new ArrayList<ReportWeeklyInvestmentOutDetail>());
			
			//获取之前所保存的列表信息
			if(entity.getId() != null){
				list1 = reportCashflowWeeklyExecuteService.getList1(entity.getId());
				list2 = reportCashflowWeeklyExecuteService.getList2(entity.getId());
				list3 = reportCashflowWeeklyExecuteService.getList3(entity.getId());
			}
			result= reportCashflowWeeklyExecuteService.saveReportCashflowWeeklyExecuteAndDetail(entity, use, list1, list2, list3);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(ReportCashflowWeeklyExecute entity, String stringList1, String stringList2, String stringList3,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			
			List<ReportWeeklyFinancingIntoDetail> list1 = new ArrayList<ReportWeeklyFinancingIntoDetail>();
			if(stringList1 != null && !"".equals(stringList1))
				entity.setList1(JSON.parseArray(stringList1, ReportWeeklyFinancingIntoDetail.class));
			else
				entity.setList1(new ArrayList<ReportWeeklyFinancingIntoDetail>());
			
			List<ReportWeeklyFinancingOutDetail> list2 = new ArrayList<ReportWeeklyFinancingOutDetail>();
			if(stringList2 != null && !"".equals(stringList2))
				entity.setList2(JSON.parseArray(stringList2, ReportWeeklyFinancingOutDetail.class));
			else
				entity.setList2(new ArrayList<ReportWeeklyFinancingOutDetail>());
				
			List<ReportWeeklyInvestmentOutDetail> list3 = new ArrayList<ReportWeeklyInvestmentOutDetail>();
			if(stringList3 != null && !"".equals(stringList3))
				entity.setList3(JSON.parseArray(stringList3, ReportWeeklyInvestmentOutDetail.class));
			else
				entity.setList3(new ArrayList<ReportWeeklyInvestmentOutDetail>());
			
			//获取之前所保存的列表信息
			if(entity.getId() != null) {
				list1 = reportCashflowWeeklyExecuteService.getList1(entity.getId());
				list2 = reportCashflowWeeklyExecuteService.getList2(entity.getId());
				list3 = reportCashflowWeeklyExecuteService.getList3(entity.getId());
			}
			result= reportCashflowWeeklyExecuteService.saveReportCashflowWeeklyExecuteAndDetail(entity, use, list1, list2, list3);
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("现金流周执行数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_xjlzzxsjsh_exam","report_cashflow_weekly_execute",entity.getId().toString()));
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
	public String listandreport(ReportCashflowWeeklyExecute entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			result= reportCashflowWeeklyExecuteService.saveReportCashflowWeeklyExecute(entity, use);
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("现金流周执行数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_xjlzzxsjsh_exam","report_cashflow_weekly_execute",entity.getId().toString()));
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
		ReportCashflowWeeklyExecute entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportCashflowWeeklyExecute();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{	
			//获取此记录所绑定的筹资性流入明细列表
			String data1 = "";
			List<ReportWeeklyFinancingIntoDetail> list1 = reportCashflowWeeklyExecuteService.getList1(id);
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
			//获取此记录所绑定的筹资性流出明细列表
			String data2 = "";
			List<ReportWeeklyFinancingOutDetail> list2 = reportCashflowWeeklyExecuteService.getList2(id);
			data2=JSONArray.toJSONString(list2);
			if("".equals(data2))
				data2 = "1";
			map.put("data2", data2);
			//获取此记录所绑定的投资性流出明细列表
			String data3 = "";
			List<ReportWeeklyInvestmentOutDetail> list3 = reportCashflowWeeklyExecuteService.getList3(id);
			data3=JSONArray.toJSONString(list3);
			if("".equals(data3))
				data3 = "1";
			map.put("data3", data3);
			
			entityview=reportCashflowWeeklyExecuteService.getReportCashflowWeeklyExecute(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_cashflowweeklyexecute);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportCashflowWeeklyExecute/reportCashflowWeeklyExecuteView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<SysExamine> a=new ArrayList<SysExamine>();
		ReportCashflowWeeklyExecute entityview;
		if(id==null)
		{
			entityview=new ReportCashflowWeeklyExecute();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{	
			//获取此记录所绑定的筹资性流入明细列表
			String data1 = "";
			List<ReportWeeklyFinancingIntoDetail> list1 = reportCashflowWeeklyExecuteService.getList1(id);
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
			//获取此记录所绑定的筹资性流出明细列表
			String data2 = "";
			List<ReportWeeklyFinancingOutDetail> list2 = reportCashflowWeeklyExecuteService.getList2(id);
			data2=JSONArray.toJSONString(list2);
			if("".equals(data2))
				data2 = "1";
			map.put("data2", data2);
			//获取此记录所绑定的投资性流出明细列表
			String data3 = "";
			List<ReportWeeklyInvestmentOutDetail> list3 = reportCashflowWeeklyExecuteService.getList3(id);
			data3=JSONArray.toJSONString(list3);
			if("".equals(data3))
				data3 = "1";
			map.put("data3", data3);
			entityview=reportCashflowWeeklyExecuteService.getReportCashflowWeeklyExecute(id);
			a= examineService.getListExamine(entityview.getId(), Base.examkind_cashflowweeklyexecute);
		    map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);	
		return "/report/reportCashflowWeeklyExecute/reportCashflowWeeklyExecuteExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= reportCashflowWeeklyExecuteService.saveReportCashflowWeeklyExecuteExamine(entityid, examStr, examResult, use);
		if(result.isResult())	
			potalMsgService.updatePortalMsg("report_cashflow_weekly_execute", entityid.toString());
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
		CommitResult result=reportCashflowWeeklyExecuteService.deleteReportCashflowWeeklyExecute(id, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_cashflow_weekly_execute", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportCashflowWeeklyExecute entity,String operate,HttpServletRequest request ,Map<String, Object> map){
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
	
	//跳转到公司汇总页面
	@RequestMapping("/g_query")
	public String g_query(ReportCashflowWeeklyExecute entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportCashflowWeeklyExecute";
		ReportCashflowWeeklyExecute tempBean = new ReportCashflowWeeklyExecute();
		
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        Integer pageNum = Integer.valueOf(curPageNum);

        entity.setParentorg(str);
        MsgPage msgPage=reportCashflowWeeklyExecuteService.getReportCashflowWeeklyExecuteForSumData(entity,pageNum,Base.pagesize,tempBean);
        
        map.put("tempBean", tempBean);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportCashflowWeeklyExecute/g_query");
	    map.put("entityview", entity);
		addData(map);

		return "/report/reportCashflowWeeklyExecute/reportCashflowWeeklyExecuteGroupGuery";
	}
	
	@ResponseBody
	@RequestMapping(value = "sumDataForNew", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String sumDataForNew(ReportCashflowWeeklyExecute entity) throws IOException {

		ReportCashflowWeeklyExecute backInfoBean = new ReportCashflowWeeklyExecute();	
		
		reportCashflowWeeklyExecuteService.sumForAllChildrenEntity(entity,backInfoBean);
		
		String data="";
		data=JSONArray.toJSONString(backInfoBean);

		return data;
	}
	
	
	//现金流执行数据填报数据查询导出
	@RequestMapping("/export")
	public String export(ReportCashflowWeeklyExecute entity,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException {

        
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//数据权限
        entity.setParentorg(str);
        HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getOrg());
		if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
			entity.setOrg("");
		}
		List<ReportCashflowWeeklyExecute> list  = reportCashflowWeeklyExecuteService.getReportCashflowWeeklyExecuteListView(entity);
		List<ReportWeeklyFinancingIntoDetail> list1 = new ArrayList<ReportWeeklyFinancingIntoDetail>();
		List<ReportWeeklyFinancingOutDetail> list2 = new ArrayList<ReportWeeklyFinancingOutDetail>();
		List<ReportWeeklyInvestmentOutDetail> list3 = new ArrayList<ReportWeeklyInvestmentOutDetail>();
		List<ReportWeeklyFinancingIntoDetail> reportWeeklyFinancingIntoDetail = new ArrayList<ReportWeeklyFinancingIntoDetail>();
		List<ReportWeeklyFinancingOutDetail> reportWeeklyFinancingOutDetail = new ArrayList<ReportWeeklyFinancingOutDetail>();
		List<ReportWeeklyInvestmentOutDetail> reportWeeklyInvestmentOutDetail = new ArrayList<ReportWeeklyInvestmentOutDetail>();
		if(list.size()>0){			
			
			for (int i = 0; i < list.size(); i++) {
				reportWeeklyFinancingIntoDetail = reportCashflowWeeklyExecuteService.getList1(list.get(i).getId());
		    	reportWeeklyFinancingOutDetail = reportCashflowWeeklyExecuteService.getList2(list.get(i).getId());
		        reportWeeklyInvestmentOutDetail = reportCashflowWeeklyExecuteService.getList3(list.get(i).getId());
				list1.addAll(reportWeeklyFinancingIntoDetail);
				list2.addAll(reportWeeklyFinancingOutDetail);
				list3.addAll(reportWeeklyInvestmentOutDetail);
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
		String fileName =  "现金流周执行数据列表";
		
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


	private HSSFSheet setExcelDatasss3(HSSFSheet sheet,List<ReportWeeklyInvestmentOutDetail> entityList,HSSFCellStyle style) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
		HSSFRow row = null;
		//将数据写入Excel
		// 循环将数据写入Excel
		int t = 1;
		for (ReportWeeklyInvestmentOutDetail entity:entityList) {
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


	private HSSFSheet setExcelDatasss2(HSSFSheet sheet,List<ReportWeeklyFinancingOutDetail> entityList,HSSFCellStyle style) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
				HSSFRow row = null;
				//将数据写入Excel
				// 循环将数据写入Excel
				int t = 1;
				for (ReportWeeklyFinancingOutDetail entity:entityList) {
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


	private HSSFSheet setExcelDatasss1(HSSFSheet sheet,List<ReportWeeklyFinancingIntoDetail> entityList,HSSFCellStyle style) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
				HSSFRow row = null;
				//将数据写入Excel
				// 循环将数据写入Excel
				int t = 1;
				for (ReportWeeklyFinancingIntoDetail entity:entityList) {
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
