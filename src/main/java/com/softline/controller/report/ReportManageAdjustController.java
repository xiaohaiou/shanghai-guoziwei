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

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import com.softline.common.DES;
import com.softline.controller.common.commonController;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.Purchase;
import com.softline.entity.ReportManageAdjust;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportManageAdjustService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportManageAdjust")
/**
 * 
 * @author tch
 *
 */
public class ReportManageAdjustController {

	@Resource(name = "reportManageAdjustService")
	private IReportManageAdjustService reportManageAdjustService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			ReportManageAdjust entityview=reportManageAdjustService.getReportManageAdjust(id);
			map.put("reportManageAdjust", entityview);
		}
	}
	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportManageAdjust entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportManageAdjust";
		if(null != request.getParameter("organalID") && !"".equals(request.getParameter("organalID")))
			entity.setOrg(request.getParameter("organalID"));
		if(null!=request.getParameter("checkedCompanyName") && !"".equals(request.getParameter("checkedCompanyName")))
			entity.setOrgname(request.getParameter("checkedCompanyName"));
		
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)));
		map.put("op", "new");
		
		/*map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );*/
		//获取单位下拉
		//业态公司
		List<HhOrganInfoTreeRelation> businessCompanys = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype);
		//上市公司
		List<HhOrganInfoTreeRelation> publicCompanys = systemService.getPublicCompany(str);
		businessCompanys.addAll(publicCompanys);
		map.put("allCompanys",businessCompanys);
		
        entity.setGetOperateType(Base.fun_examine);
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        //String dataauth=(String) session.getAttribute("gzwsession_AllFinancecompany");
        /*String dataauth = "";
        for (HhOrganInfoTreeRelation company : businessCompanys ) {
        	dataauth += company.getId().getNnodeId() + ",";
        }*/
        /*dataauth = dataauth.substring(0, dataauth.length()-1);
        if(!Common.notEmpty(entity.getOrg()) )
        	entity.setOrg(dataauth);*/
        
		map.put("organalID", request.getParameter("organalID"));       
		map.put("checkedCompanyName",request.getParameter("checkedCompanyName"));
        
        entity.setParentorg(str);
        MsgPage msgPage=reportManageAdjustService.getReportManageAdjustListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportManageAdjust/examinelist");
	    map.put("entityview", entity);
		addData(map,entity.getFre());
		return "/report/reportManageAdjust/reportManageAdjustExamineList";
	}
	//导出管理口径核算数据reportExport
	@RequestMapping("/reportExport")
	public void reportExport(ReportManageAdjust entity,HttpServletRequest request ,HttpServletResponse response,Map<String, Object> map) throws IOException, ParsePropertyException, InvalidFormatException {
		
		if(null != request.getParameter("organalID") && !"".equals(request.getParameter("organalID")))
			entity.setOrg(request.getParameter("organalID"));
		if(null!=request.getParameter("checkedCompanyName") && !"".equals(request.getParameter("checkedCompanyName")))
			entity.setOrgname(request.getParameter("checkedCompanyName"));
		
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		
		entity.setParentorg(str);
        List<ReportManageAdjust> list=reportManageAdjustService.getExportReportManageAdjustListView(entity);
        String Item = Common.getItemPath(DES.reportmanageadjust, request) + Base.reportmanageadjusttemplate;
		String Item_temp = Common.getItemPath(DES.reportmanageadjust, request) + Base.getTemp2k3ExcelFileName();
       
		XLSTransformer former = new XLSTransformer();
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("list", list);
		former.transformXLS(Item, beanParams, Item_temp);
	    String expRecReportName ="管理口径核算数据.xlsx";
	    commonController.doDownLoad(Item_temp, expRecReportName, response);
	    Base.deleteTempFile(Item_temp);
	  
		
	}
	
	
	//导出管理口径核算数据reportExport
	@RequestMapping("/export")
	public void export(ReportManageAdjust entity,HttpServletRequest request ,HttpServletResponse response,Map<String, Object> map) throws IOException, ParsePropertyException, InvalidFormatException {
		
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		
		if(null != request.getParameter("organalID") && !"".equals(request.getParameter("organalID")))
			entity.setOrg(request.getParameter("organalID"));
		if(null!=request.getParameter("checkedCompanyName") && !"".equals(request.getParameter("checkedCompanyName")))
			entity.setOrgname(request.getParameter("checkedCompanyName"));
		
		
		entity.setGetOperateType(Base.fun_examine);
		entity.setParentorg(str);
        List<ReportManageAdjust> list=reportManageAdjustService.getExportReportManageAdjustExamineListView(entity);
        String Item = Common.getItemPath(DES.reportmanageadjust, request) + Base.reportmanageadjusttemplate;
		String Item_temp = Common.getItemPath(DES.reportmanageadjust, request) + Base.getTemp2k3ExcelFileName();
       
		XLSTransformer former = new XLSTransformer();
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("list", list);
		former.transformXLS(Item, beanParams, Item_temp);
	    String expRecReportName ="管理口径核算数据.xlsx";
	    commonController.doDownLoad(Item_temp, expRecReportName, response);
	    Base.deleteTempFile(Item_temp);
	  
		
	}
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportManageAdjust entity,HttpServletRequest request ,Map<String, Object> map ) throws IOException {
		
		if(null != request.getParameter("organalID") && !"".equals(request.getParameter("organalID")))
			entity.setOrg(request.getParameter("organalID"));
		if(null!=request.getParameter("checkedCompanyName") && !"".equals(request.getParameter("checkedCompanyName")))
			entity.setOrgname(request.getParameter("checkedCompanyName"));
		String mapurl=request.getContextPath()+ "/reportManageAdjust";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");		
		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)));
		map.put("op", "new");
		//获取单位下拉
		//业态公司
		List<HhOrganInfoTreeRelation> businessCompanys = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype);
		//上市公司
		List<HhOrganInfoTreeRelation> publicCompanys = systemService.getPublicCompany(str);
		businessCompanys.addAll(publicCompanys);
		map.put("allCompanys",businessCompanys);
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        //String dataauth=(String) session.getAttribute("gzwsession_AllFinancecompany");
       /* String dataauth = "";
        for (HhOrganInfoTreeRelation company : businessCompanys ) {
        	dataauth += company.getId().getNnodeId() + ",";
        }
        dataauth = dataauth.substring(0, dataauth.length()-1);
        if(!Common.notEmpty(entity.getOrg()) )
        	entity.setOrg(dataauth);*/
        entity.setParentorg(str);
        MsgPage msgPage=reportManageAdjustService.getReportManageAdjustListView(entity,pageNum,Base.pagesize);
	    
		map.put("organalID", request.getParameter("organalID"));       
		map.put("checkedCompanyName",request.getParameter("checkedCompanyName"));
        map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportManageAdjust/list");
	    map.put("entityview", entity);
		addData(map,entity.getFre());
		return "/report/reportManageAdjust/reportManageAdjustList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportManageAdjust entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		
		if(null != request.getParameter("organalID") && !"".equals(request.getParameter("organalID")))
			entity.setOrg(request.getParameter("organalID"));
		if(null!=request.getParameter("checkedCompanyName") && !"".equals(request.getParameter("checkedCompanyName")))
			entity.setOrgname(request.getParameter("checkedCompanyName"));
		
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		/*map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );*/
		//获取单位下拉
		//业态公司
		List<HhOrganInfoTreeRelation> businessCompanys = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype);
		//上市公司
		List<HhOrganInfoTreeRelation> publicCompanys = systemService.getPublicCompany(str);
		businessCompanys.addAll(publicCompanys);
		map.put("allCompanys",businessCompanys);
		
		ReportManageAdjust entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportManageAdjust();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
		}
		else
		{	
			entityview=reportManageAdjustService.getReportManageAdjust(entity.getId());
			/*List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportmanageadjust);
			map.put("entityExamineviewlist", a);*/
		}
		
		if(null!=entity && null!=entity.getOrg()){
			map.put("checkedCompanyName", entity.getOrgname());
		}
		
		if(null!=entity && null!=entity.getOrg()){
			map.put("organalID", entity.getOrg());
		}
        map.put("op", "new");
		map.put("entityview", entityview);	
		addData(map,entity.getFre());
		
		return "/report/reportManageAdjust/reportManageAdjustNewModify";
	}
	
	//跳转新增修改页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
			
		ReportManageAdjust entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportManageAdjust();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportManageAdjustService.getReportManageAdjust(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportmanageadjust);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/reportManageAdjust/reportManageAdjustView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportManageAdjust reportManageAdjust,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		if(null != request.getParameter("organalID") && !"".equals(request.getParameter("organalID")))
			reportManageAdjust.setOrg(request.getParameter("organalID"));
		if(null!=request.getParameter("checkedCompanyName") && !"".equals(request.getParameter("checkedCompanyName")))
			reportManageAdjust.setOrgname(request.getParameter("checkedCompanyName"));
		
		CommitResult result=new CommitResult();
		if(reportManageAdjust==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			reportManageAdjust.setStatus(Base.examstatus_1);
			result= reportManageAdjustService.saveReportManageAdjust(reportManageAdjust, use);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(ReportManageAdjust entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(null != request.getParameter("organalID") && !"".equals(request.getParameter("organalID")))
			entity.setOrg(request.getParameter("organalID"));
		if(null!=request.getParameter("checkedCompanyName") && !"".equals(request.getParameter("checkedCompanyName")))
			entity.setOrgname(request.getParameter("checkedCompanyName"));	
		
		if(entity!=null)
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			entity.setStatus(Base.examstatus_2);
			result= reportManageAdjustService.saveReportManageAdjust(entity, use);
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
		ReportManageAdjust entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportManageAdjust();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportManageAdjustService.getReportManageAdjust(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportmanageadjust);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportManageAdjust/reportManageAdjustView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<SysExamine> a=new ArrayList<SysExamine>();
		ReportManageAdjust entityview;
		if(id==null)
		{
			entityview=new ReportManageAdjust();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else {
			entityview=reportManageAdjustService.getReportManageAdjust(id);
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportmanageadjust);
		    map.put("entityExamineviewlist", a);
		}
		Integer status=0;
		status=entityview.getStatus();
		map.put("status",status);
		map.put("entityview", entityview);	
		return "/report/reportManageAdjust/reportManageAdjustExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= reportManageAdjustService.saveReportManageAdjustExamine(entityid, examStr, examResult, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//添加信息
	private void addData(Map<String, Object> map,Integer fre)
	{
		List<HhBase> fredata;
		List<HhBase> fretype=baseService.getOtherBaseList("50129,50130,50133", Base.fre_type);
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		List<HhBase> seasontype= baseService.getBases(Base.seasontype);
		if(fre!=null)
		{
			fredata=baseService.getChildBases(fre);
		}
		else
		{
			fredata=baseService.getChildBases(fretype.get(0).getId());
		}
		map.put("freDatatype", fredata);
		map.put("fretype",fretype);
		map.put("examstatustype",examstatustype);
		map.put("seasontype", seasontype);
		
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportgroupdelete(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=reportManageAdjustService.deleteReportManageAdjust(id, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportManageAdjust entity,String operate,HttpServletRequest request ,Map<String, Object> map){
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
	@RequestMapping("_getBeginningData")
	public String _getBeginningData(String org, Integer year) {
		//获取年初的数据（去年12月份数据）
		ReportManageAdjust entity = reportManageAdjustService.getBeginningData(org, year);
		String data = "";
		data = entity != null ? JSONArray.toJSONString(entity) : "1";
		return data;
	}
	
}
