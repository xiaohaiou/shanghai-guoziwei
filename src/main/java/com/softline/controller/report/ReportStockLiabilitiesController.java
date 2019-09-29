package com.softline.controller.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
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
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.ReportOverseasLiabilitiesDetail;
import com.softline.entity.ReportStockLiabilities;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportStockLiabilitiesService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportStockLiabilities")
/**
 * 
 * @author tch
 *
 */
public class ReportStockLiabilitiesController {

	@Resource(name = "reportStockLiabilitiesService")
	private IReportStockLiabilitiesService reportStockLiabilitiesService;
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
			ReportStockLiabilities entityview=reportStockLiabilitiesService.getReportStockLiabilities(id);
			map.put("reportStockLiabilities", entityview);
		}
	}
	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportStockLiabilities entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportStockLiabilities";
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
        MsgPage msgPage=reportStockLiabilitiesService.getReportStockLiabilitiesListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportStockLiabilities/examinelist");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportStockLiabilities/reportStockLiabilitiesExamineList";
	}
	
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportStockLiabilities entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportStockLiabilities";
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
        MsgPage msgPage=reportStockLiabilitiesService.getReportStockLiabilitiesListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportStockLiabilities/list");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportStockLiabilities/reportStockLiabilitiesList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportStockLiabilities entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		ReportStockLiabilities entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportStockLiabilities();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
			entityview.setMonth(Integer.parseInt (df1.format(date)));
			map.put("data1","1");
		}
		else
		{	
			entityview=reportStockLiabilitiesService.getReportStockLiabilities(entity.getId());
			//获取此记录所绑定的境外负债按币种分类明细表
			String data1 = "";
			List<ReportOverseasLiabilitiesDetail> list1 = reportStockLiabilitiesService.getList1(entity.getId());
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
			/*List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_stockliabilities);
			map.put("entityExamineviewlist", a);*/
		}
		map.put("entityview", entityview);	
		addData(map);
		
		return "/report/reportStockLiabilities/reportStockLiabilitiesNewModify";
	}
	
	//跳转新增修改页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportStockLiabilities entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportStockLiabilities();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			//获取此记录所绑定的境外负债按币种分类明细表
			String data1 = "";
			List<ReportOverseasLiabilitiesDetail> list1 = reportStockLiabilitiesService.getList1(id);
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
			entityview=reportStockLiabilitiesService.getReportStockLiabilities(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_stockliabilities);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/reportStockLiabilities/reportStockLiabilitiesView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportStockLiabilities reportStockLiabilities,  String stringList1,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(reportStockLiabilities==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			List<ReportOverseasLiabilitiesDetail> list1 = new ArrayList<ReportOverseasLiabilitiesDetail>();
			if(stringList1 != null && !"".equals(stringList1))
				reportStockLiabilities.setList1(JSON.parseArray(stringList1, ReportOverseasLiabilitiesDetail.class));
			else
				reportStockLiabilities.setList1(new ArrayList<ReportOverseasLiabilitiesDetail>());
			
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			reportStockLiabilities.setStatus(Base.examstatus_1);
			//获取之前所保存的列表信息
			if(reportStockLiabilities.getId() != null)
				list1 = reportStockLiabilitiesService.getList1(reportStockLiabilities.getId());
			
			result= reportStockLiabilitiesService.saveReportStockLiabilitiesAndDetail(reportStockLiabilities, use, list1);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(ReportStockLiabilities entity, String stringList1, HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			List<ReportOverseasLiabilitiesDetail> list1 = new ArrayList<ReportOverseasLiabilitiesDetail>();
			if(stringList1 != null && !"".equals(stringList1))
				entity.setList1(JSON.parseArray(stringList1, ReportOverseasLiabilitiesDetail.class));
			else
				entity.setList1(new ArrayList<ReportOverseasLiabilitiesDetail>());
			
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			entity.setStatus(Base.examstatus_2);
			
			//获取之前所保存的列表信息
			if(entity.getId() != null)
				list1 = reportStockLiabilitiesService.getList1(entity.getId());
			result= reportStockLiabilitiesService.saveReportStockLiabilitiesAndDetail(entity, use, list1);
			if(result.isResult()) 
				potalMsgService.savePortalMsg(new PortalMsg("存量负债数据需要审核","财务",df.format(new Date()),0,0,0,
					use.getVcName(),df.format(new Date()),use.getVcName(),
					df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_clfzsjsh_exam","report_stock_liabilities",entity.getId().toString()));
		}
		else
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//查看页面的上报
	@ResponseBody
	@RequestMapping(value ="/listandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String listandreport(ReportStockLiabilities entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			result= reportStockLiabilitiesService.saveReportStockLiabilities(entity, use);
			if(result.isResult()) 
				potalMsgService.savePortalMsg(new PortalMsg("存量负债数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_clfzsjsh_exam","report_stock_liabilities",entity.getId().toString()));
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
		ReportStockLiabilities entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportStockLiabilities();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportStockLiabilitiesService.getReportStockLiabilities(id);
			//获取此记录所绑定的境外负债按币种分类明细表
			String data1 = "";
			List<ReportOverseasLiabilitiesDetail> list1 = reportStockLiabilitiesService.getList1(entityview.getId());
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_stockliabilities);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportStockLiabilities/reportStockLiabilitiesView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<SysExamine> a=new ArrayList<SysExamine>();
		ReportStockLiabilities entityview;
		if(id==null)
		{
			entityview=new ReportStockLiabilities();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else{
			entityview=reportStockLiabilitiesService.getReportStockLiabilities(id);
			//获取此记录所绑定的境外负债按币种分类明细表
			String data1 = "";
			List<ReportOverseasLiabilitiesDetail> list1 = reportStockLiabilitiesService.getList1(entityview.getId());
			data1=JSONArray.toJSONString(list1);
			if("".equals(data1))
				data1 = "1";
			map.put("data1", data1);
			a= examineService.getListExamine(entityview.getId(), Base.examkind_stockliabilities);
		    map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);	
		return "/report/reportStockLiabilities/reportStockLiabilitiesExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= reportStockLiabilitiesService.saveReportStockLiabilitiesExamine(entityid, examStr, examResult, use);
		if(result.isResult()) 
			potalMsgService.updatePortalMsg("report_stock_liabilities", entityid.toString());
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
		CommitResult result=reportStockLiabilitiesService.deleteReportStockLiabilities(id, use);
		if(result.isResult()) 
			potalMsgService.updatePortalMsg("report_stock_liabilities", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportStockLiabilities entity,String operate,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		result.setResult(true);
		if(entity==null)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}
	
	//获取下拉框内容
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

	//数据汇总页面
	@ResponseBody
	@RequestMapping(value ="/sumData", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String sumData(HttpServletRequest request,ReportStockLiabilities entity,String year,String month) throws IOException {
		
		List<ReportStockLiabilities> list = null;
		Hashtable<String,Object> backMap = null;	

		String data = "";
		
		if(null==entity || null ==year || null == month)
			return "";
		
		String status = "50500,50502"; //汇总公司种类
		list = reportStockLiabilitiesService.getSumBeanList(entity, Integer.valueOf(year), Integer.valueOf(month), status);
		
		backMap = new Hashtable<String,Object>();
		reportStockLiabilitiesService.getSumResult(list, backMap);
		
		data=JSONArray.toJSONString(backMap); 			
		return data;
	}
	
}
