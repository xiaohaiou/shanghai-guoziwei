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
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.Purchase;
import com.softline.entity.ReportOverseasCapitalPool;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportOverseasCapitalPoolService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportOverseasCapitalPool")
/**
 * 
 * @author tch
 *
 */
public class ReportOverseasCapitalPoolController {

	@Resource(name = "reportOverseasCapitalPoolService")
	private IReportOverseasCapitalPoolService reportOverseasCapitalPoolService;
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
			ReportOverseasCapitalPool entityview=reportOverseasCapitalPoolService.getReportOverseasCapitalPool(id);
			map.put("reportOverseasCapitalPool", entityview);
		}
	}
	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportOverseasCapitalPool entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportOverseasCapitalPool";
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
      
        entity.setParentorg(str);
        MsgPage msgPage=reportOverseasCapitalPoolService.getReportOverseasCapitalPoolListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportOverseasCapitalPool/examinelist");
	    map.put("entityview", entity);
	    //数据权限
        String dataauth=systemService.getAllChildrenFinanceOrganal(str,Base.financetype);

		addData(map,dataauth);
		return "/report/reportOverseasCapitalPool/reportOverseasCapitalPoolExamineList";
	}
	
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportOverseasCapitalPool entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportOverseasCapitalPool";
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
        MsgPage msgPage=reportOverseasCapitalPoolService.getReportOverseasCapitalPoolListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportOverseasCapitalPool/list");
	    map.put("entityview", entity);
        //数据权限
        String dataauth=systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
		addData(map,dataauth);
		return "/report/reportOverseasCapitalPool/reportOverseasCapitalPoolList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportOverseasCapitalPool entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		ReportOverseasCapitalPool entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportOverseasCapitalPool();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
		}
		else
		{	
			entityview=reportOverseasCapitalPoolService.getReportOverseasCapitalPool(entity.getId());
			List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportoverseascapitalpool);
			map.put("entityExamineviewlist", a);
		}

        String dataauth=systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
		map.put("entityview", entityview);	
		addData(map,dataauth);
		
		return "/report/reportOverseasCapitalPool/reportOverseasCapitalPoolNewModify";
	}
	
	//跳转新增修改页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportOverseasCapitalPool entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportOverseasCapitalPool();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportOverseasCapitalPoolService.getReportOverseasCapitalPool(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportoverseascapitalpool);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/reportOverseasCapitalPool/reportOverseasCapitalPoolView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportOverseasCapitalPool reportOverseasCapitalPool,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(reportOverseasCapitalPool==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			reportOverseasCapitalPool.setStatus(Base.examstatus_1);
			result= reportOverseasCapitalPoolService.saveReportOverseasCapitalPool(reportOverseasCapitalPool, use);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(ReportOverseasCapitalPool entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			result= reportOverseasCapitalPoolService.saveReportOverseasCapitalPool(entity, use);
			if(result.isResult())
			{
				potalMsgService.savePortalMsg(new PortalMsg("境外资金池建设数据需要审核","财务",df.format(new Date()),0,0,0,
						entity.getCreatePersonName(),entity.getCreateDate(),entity.getLastModifyPersonName(),
						entity.getLastModifyDate(),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_jwzjcjssjsh_exam","report_overseas_capital_pool",entity.getId().toString()));
			}
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
		ReportOverseasCapitalPool entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportOverseasCapitalPool();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportOverseasCapitalPoolService.getReportOverseasCapitalPool(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportoverseascapitalpool);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportOverseasCapitalPool/reportOverseasCapitalPoolView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportOverseasCapitalPool entityview;
		if(id==null)
		{
			entityview=new ReportOverseasCapitalPool();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
			entityview=reportOverseasCapitalPoolService.getReportOverseasCapitalPool(id);
		map.put("entityview", entityview);	
		return "/report/reportOverseasCapitalPool/reportOverseasCapitalPoolExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= reportOverseasCapitalPoolService.saveReportOverseasCapitalPoolExamine(entityid, examStr, examResult, use);
		if(result.isResult())
		{
			potalMsgService.updatePortalMsg("report_overseas_capital_pool", entityid.toString());
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//添加信息
	private void addData(Map<String, Object> map,String authdata)
	{
		
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		List<HhBase> seasontype= baseService.getBases(Base.seasontype);
		List<HhOrganInfoTreeRelation> organltype= systemService.getBusinessCompany(authdata, Base.financetype);
		map.put("examstatustype",examstatustype);
		map.put("seasontype", seasontype);
		map.put("organltype", organltype);
		
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportgroupdelete(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=reportOverseasCapitalPoolService.deleteReportOverseasCapitalPool(id, use);
		if(result.isResult())
		{
			potalMsgService.updatePortalMsg("report_overseas_capital_pool", id.toString());
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportOverseasCapitalPool entity,String operate,HttpServletRequest request ,Map<String, Object> map){
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
	@RequestMapping(value ="/getdata", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getdata(int season,int year,HttpServletRequest request ,Map<String, Object> map){
		String organID=request.getParameter("organID");
		String a=reportOverseasCapitalPoolService.getData(year,season,organID);
		if(a==null)
			a="";
		return a;
	}
	
}
