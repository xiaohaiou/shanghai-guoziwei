/*package com.softline.controller.riskmanagement;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mortbay.util.ajax.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.system.IBaseDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.RiskManagement;
import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsGroup;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.Report_forms_groupView;
import com.softline.service.RiskManagement.IRiskManagementService;
import com.softline.service.report.IReportService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/RiskManagement")
*//**
 * 
 * @author tch
 *
 *//*
public class RiskManagementController {

	@Resource(name = "RiskManagementService")
	private IRiskManagementService RiskManagementService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			RiskManagement entityview=RiskManagementService.getRiskManagement(id);
			map.put("RiskManagement", entityview);
		}

	}
	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(RiskManagement entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/RiskManagement";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=RiskManagementService.getRiskManagementListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/RiskManagement/examinelist");
	    map.put("entityview", entity);
		addData(map);
		return "/RiskManagement/RiskManagementExamineList";
	}
	
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(RiskManagement entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/RiskManagement";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=RiskManagementService.getRiskManagementListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/RiskManagement/list");
	    map.put("entityview", entity);
		addData(map);
		return "/RiskManagement/RiskManagementList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(RiskManagement entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		RiskManagement entityview;
		if(entity.getId()==null)
		{	
			entityview=new RiskManagement();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
		}
		else
			entityview=RiskManagementService.getRiskManagement(entity.getId());
		map.put("entityview", entityview);	
		addData(map);
		return "/RiskManagement/RiskManagementNewModify";
	}
	
	//跳转新增修改页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		RiskManagement entityview;
		SysExamine a=new SysExamine();
		if(id==null)
		{	
			entityview=new RiskManagement();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=RiskManagementService.getRiskManagement(id);
		    a= examineService.getOneExamine(entityview.getId(), Base.examkind_RiskManagement);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineview", a);
		return "/RiskManagement/RiskManagementView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(RiskManagement entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		CommitResult result= RiskManagementService.saveRiskManagement(entity, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(RiskManagement entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_2);
		CommitResult result= RiskManagementService.saveRiskManagement(entity, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//查询列表页面的上报
	@ResponseBody
	@RequestMapping(value ="/postexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		RiskManagement entity=RiskManagementService.getRiskManagement(id);
		entity.setStatus(Base.examstatus_2);
		CommitResult result= RiskManagementService.saveRiskManagement(entity, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		RiskManagement entityview;
		if(id==null)
		{
			entityview=new RiskManagement();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
			entityview=RiskManagementService.getRiskManagement(id);
		map.put("entityview", entityview);	
		return "/RiskManagement/RiskManagementExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result= RiskManagementService.saveRiskManagementExamine(entityid, examStr, examResult, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		List<HhBase> seasontype= baseService.getBases(Base.seasontype);
		map.put("examstatustype",examstatustype);
		map.put("seasontype", seasontype);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String reportgroupdelete(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=RiskManagementService.deleteRiskManagement(id, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	

}
*/