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
import com.softline.entity.PortalMsg;
import com.softline.entity.ReportOverseasAsset;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportOverseasAssetService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.impl.SystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportOverseasAsset")
/**
 * 
 * @author tch
 *
 */
public class ReportOverseasAssetController {

	@Resource(name = "reportOverseasAssetService")
	private IReportOverseasAssetService reportOverseasAssetService;
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
			ReportOverseasAsset entityview=reportOverseasAssetService.getReportOverseasAsset(id);
			map.put("reportOverseasAsset", entityview);
		}
	}
	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportOverseasAsset entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportOverseasAsset";
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
        MsgPage msgPage=reportOverseasAssetService.getReportOverseasAssetListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportOverseasAsset/examinelist");
	    map.put("entityview", entity);
	    //数据权限
        String dataauth=systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
		addData(map,dataauth);
		return "/report/reportOverseasAsset/reportOverseasAssetExamineList";
	}
	
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportOverseasAsset entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportOverseasAsset";
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
        entity.setParentorg(str);
        MsgPage msgPage=reportOverseasAssetService.getReportOverseasAssetListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportOverseasAsset/list");
	    map.put("entityview", entity);
	    //数据权限
        String dataauth=systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
		addData(map,dataauth);
		return "/report/reportOverseasAsset/reportOverseasAssetList";
	}
	//数据导出
	@RequestMapping("/reportExport")
	public void reportExport(ReportOverseasAsset entity,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException, ParsePropertyException, InvalidFormatException {
		HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
        entity.setParentorg(str);
     
        List<ReportOverseasAsset> list=reportOverseasAssetService.getEReportOverseasAssetListView(entity);
        
        String Item = Common.getItemPath(DES.overseasasset, request) + Base.overseasassettemplate;
		String Item_temp = Common.getItemPath(DES.overseasasset, request) + Base.getTemp2k3ExcelFileName();
		
		XLSTransformer former = new XLSTransformer();
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("list", list);
		former.transformXLS(Item, beanParams, Item_temp);
	    String expRecReportName ="境外资产占比数据填报.xlsx";
	    commonController.doDownLoad(Item_temp, expRecReportName, response);
	    Base.deleteTempFile(Item_temp);
}
	
	
	   //境外资产占比数据审核导出
		@RequestMapping("/export")
		public void export(ReportOverseasAsset entity,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException, ParsePropertyException, InvalidFormatException {
		
			
			HttpSession session=request.getSession();
	        String str=(String) session.getAttribute("gzwsession_financecompany");
	        entity.setGetOperateType(Base.fun_examine);
	        entity.setParentorg(str);
	        List<ReportOverseasAsset> list=reportOverseasAssetService.getEReportOverseasAssetListViewExport(entity);
	        
	        String Item = Common.getItemPath(DES.overseasasset, request) + Base.overseasassettemplate;
			String Item_temp = Common.getItemPath(DES.overseasasset, request) + Base.getTemp2k3ExcelFileName();
			
			XLSTransformer former = new XLSTransformer();
			Map<String, Object> beanParams = new HashMap<String, Object>();
			beanParams.put("list", list);
			former.transformXLS(Item, beanParams, Item_temp);
		    String expRecReportName ="境外资产占比数据审核.xlsx";
		    commonController.doDownLoad(Item_temp, expRecReportName, response);
		    Base.deleteTempFile(Item_temp);
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportOverseasAsset entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		ReportOverseasAsset entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportOverseasAsset();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
		}
		else
		{	
			entityview=reportOverseasAssetService.getReportOverseasAsset(entity.getId());
			List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_reportOverseasAsset);
			map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);
		//数据权限
        String dataauth=systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
		addData(map,dataauth);
		
		return "/report/reportOverseasAsset/reportOverseasAssetNewModify";
	}
	
	//跳转新增修改页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportOverseasAsset entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportOverseasAsset();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportOverseasAssetService.getReportOverseasAsset(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportOverseasAsset);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/reportOverseasAsset/reportOverseasAssetView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportOverseasAsset reportOverseasAsset,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(reportOverseasAsset==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			reportOverseasAsset.setStatus(Base.examstatus_1);
			result= reportOverseasAssetService.saveReportOverseasAsset(reportOverseasAsset, use);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(ReportOverseasAsset entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			result= reportOverseasAssetService.saveReportOverseasAsset(entity, use);
			if(result.isResult())
			{
				potalMsgService.savePortalMsg(new PortalMsg("境外资产占比数据需要审核","财务",df.format(new Date()),0,0,0,
						use.getVcName(),df.format(new Date()),use.getVcName(),
						df.format(new Date()),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(), Base.financetype),"bimWork_jwzcbsjsh_exam","report_overseas_asset",entity.getId().toString()));
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
		ReportOverseasAsset entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportOverseasAsset();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportOverseasAssetService.getReportOverseasAsset(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_reportOverseasAsset);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/reportOverseasAsset/reportOverseasAssetView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportOverseasAsset entityview;
		if(id==null)
		{
			entityview=new ReportOverseasAsset();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
			entityview=reportOverseasAssetService.getReportOverseasAsset(id);
		Integer status=0;
		status=entityview.getStatus();
		map.put("status",status);
		map.put("entityview", entityview);	
		return "/report/reportOverseasAsset/reportOverseasAssetExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= reportOverseasAssetService.saveReportOverseasAssetExamine(entityid, examStr, examResult, use);
		if(result.isResult())
		{
			potalMsgService.updatePortalMsg("report_overseas_asset", entityid.toString());
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
		CommitResult result=reportOverseasAssetService.deleteReportOverseasAsset(id, use);
		if(result.isResult())
		{
			potalMsgService.updatePortalMsg("report_overseas_asset", id.toString());
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportOverseasAsset entity,String operate,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		result.setResult(true);
		if(entity==null)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}
	
	
}
