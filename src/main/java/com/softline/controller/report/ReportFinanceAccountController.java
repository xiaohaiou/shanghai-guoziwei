package com.softline.controller.report;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.Purchase;
import com.softline.entity.ReportFinancingAccount;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFinancingAccountService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportFinancingAccount")
/**
 * 
 * @author tch
 *
 */
public class ReportFinanceAccountController {

	@Resource(name = "reportFinancingAccountService")
	private IReportFinancingAccountService reportFinancingAccountService;
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
			ReportFinancingAccount entityview=reportFinancingAccountService.getReportFinancingAccount(id);
			map.put("reportFinancingAccount", entityview);
		}
	}
	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportFinancingAccount entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportFinancingAccount";
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
        MsgPage msgPage=reportFinancingAccountService.getReportFinancingAccountListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportFinancingAccount/examinelist");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportFinancingAccount/reportFinancingAccountExamineList";
	}
	
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportFinancingAccount entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportFinancingAccount";
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
        /*String dataauth = systemService.getOtherOrganAuthData(str,Base.financetype);
        if(!Common.notEmpty(entity.getOrg()) )
        	entity.setOrg(dataauth);*/
        entity.setParentorg(str);
        MsgPage msgPage=reportFinancingAccountService.getReportFinancingAccountListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportFinancingAccount/list");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportFinancingAccount/reportFinancingAccountList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportFinancingAccount entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		ReportFinancingAccount entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportFinancingAccount();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
			entityview.setMonth(Integer.parseInt (df1.format(date)));
		}
		else
		{	
			entityview=reportFinancingAccountService.getReportFinancingAccount(entity.getId());
			/*List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_financeaccount);
			map.put("entityExamineviewlist", a);*/
		}
		List<HhBase> reportFinanceAccountType = baseService.getBases(Base.report_finance_account_type);
		map.put("reportFinanceAccountType", reportFinanceAccountType);
		map.put("entityview", entityview);	
		addData(map);
		
		return "/report/reportFinancingAccount/reportFinancingAccountNewModify";
	}
	
	//跳转新增修改页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportFinancingAccount entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportFinancingAccount();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportFinancingAccountService.getReportFinancingAccount(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_financeaccount);
		}
		List<HhBase> reportFinanceAccountType = baseService.getBases(Base.report_finance_account_type);
		map.put("reportFinanceAccountType", reportFinanceAccountType);
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/reportFinancingAccount/reportFinancingAccountView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportFinancingAccount reportFinancingAccount,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(reportFinancingAccount==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			reportFinancingAccount.setStatus(Base.examstatus_1);
			result= reportFinancingAccountService.saveReportFinancingAccount(reportFinancingAccount, use);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(ReportFinancingAccount entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			result= reportFinancingAccountService.saveReportFinancingAccount(entity, use);
			if(result.isResult())
				potalMsgService.savePortalMsg(new PortalMsg("融资下账数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_rzxzsjsh_exam","report_financing_account",entity.getId().toString()));
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
		ReportFinancingAccount entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportFinancingAccount();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportFinancingAccountService.getReportFinancingAccount(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_financeaccount);
		    map.put("entityExamineviewlist", a);
		}	
		List<HhBase> reportFinanceAccountType = baseService.getBases(Base.report_finance_account_type);
		map.put("reportFinanceAccountType", reportFinanceAccountType);
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportFinancingAccount/reportFinancingAccountView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<SysExamine> a=new ArrayList<SysExamine>();
		ReportFinancingAccount entityview;
		if(id==null)
		{
			entityview=new ReportFinancingAccount();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else{
		    entityview=reportFinancingAccountService.getReportFinancingAccount(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_financeaccount);
		    map.put("entityExamineviewlist", a);
		}
		List<HhBase> reportFinanceAccountType = baseService.getBases(Base.report_finance_account_type);
		map.put("reportFinanceAccountType", reportFinanceAccountType);
		map.put("entityview", entityview);	
		return "/report/reportFinancingAccount/reportFinancingAccountExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= reportFinancingAccountService.saveReportFinancingAccountExamine(entityid, examStr, examResult, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_financing_account", entityid.toString());
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
		CommitResult result=reportFinancingAccountService.deleteReportFinancingAccount(id, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_financing_account", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportFinancingAccount entity,String operate,HttpServletRequest request ,Map<String, Object> map){
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
	@RequestMapping(value="getSummaryData",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String getSummaryData(ReportFinancingAccount entity,String compId,Integer year,Integer month,Map<String, Object> map) throws IOException {
		//获取此公司此年次月的所有周执行明细表数据
		BigDecimal a = new BigDecimal(0);
		BigDecimal b = new BigDecimal(0);
		BigDecimal c = new BigDecimal(0);
		BigDecimal d = new BigDecimal(0);
		BigDecimal e = new BigDecimal(0);
		List<Object> allData = new ArrayList<Object>();
		List<ReportFinancingAccount> summaryData  =new ArrayList<ReportFinancingAccount>();
		Calendar cal = Calendar.getInstance();
		int newYear = cal.get(Calendar.YEAR);
		int newMonth = cal.get(Calendar.MONTH )+1;
		if(year==newYear && month==newMonth){
             summaryData = reportFinancingAccountService.getSummaryData(compId,year,month);
		}else{
			 summaryData = reportFinancingAccountService.getSummaryHistoryData(compId,year,month);
		}
		
        if(null != summaryData){
        	for (int i = 0; i < summaryData.size(); i++) {
        	    a = a.add(new BigDecimal(summaryData.get(i).getDomesticAccountAmounts()));
        	    b = b.add(new BigDecimal(summaryData.get(i).getOverseasAccountAmounts()));
        	    c = c.add(new BigDecimal(summaryData.get(i).getEquityFinancing()));
        	    d = d.add(new BigDecimal(summaryData.get(i).getStockEquityFinancing()));
        	    e = e.add(new BigDecimal(summaryData.get(i).getBondFinancing()));
        	entity.setDomesticAccountAmounts(a+"");
         	entity.setOverseasAccountAmounts(b+"");
        	entity.setEquityFinancing(c+"");
        	entity.setStockEquityFinancing(d+"");
        	entity.setBondFinancing(e+"");
        	map.put("entity", entity);
        }

        }
		return JSON.toJSONString(entity);
	}
	
	//跳转新增修改页面    reportFinancingAccountSumDetialList.jsp
	@RequestMapping("/sumDetialList")
	public String sumDetialList(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		String curPageNum = request.getParameter("pageNums");
		
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        
        Integer pageNum = Integer.valueOf(curPageNum);
        
		ReportFinancingAccount entityview = new ReportFinancingAccount();
		
		if(id!=null)
			entityview=reportFinancingAccountService.getReportFinancingAccount(id);
		
        MsgPage msgPage=reportFinancingAccountService.getReportFinancingAccountSumDetialList(entityview,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    

		return "/report/reportFinancingAccount/reportFinancingAccountSumDetialList";
	}
	
	
	
	
}
