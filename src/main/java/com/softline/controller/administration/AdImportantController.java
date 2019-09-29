package com.softline.controller.administration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.AdImportant;
import com.softline.entity.AdSupervise;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.administration.IAdImportantService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * @author tang
 *
 */
/**
 * @author tang
 *
 */
/**
 * @author tang
 *
 */
/**
 * @author tang
 *
 */
@Controller
@RequestMapping("adImportant")
public class AdImportantController {
	
	@Resource(name = "adImportantService")
	private IAdImportantService	adImportantService;
	
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
	
	@RequestMapping("query")
	public String queryImportantList(AdImportant entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);        
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(dataauth);
        }
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adImportantService.getImportantView(entity,pageNum,Base.pagesize,Base.fun_search,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setImportantCompId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adImportant/query");
	    map.put("entity", entity);
	    addData(map);
	    map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/administration/importantList";
	}
	
	@RequestMapping("examineQuery")
	public String queryExamineImportantList(AdImportant entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany); 
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(dataauth);
        }
		String curPageNum = request.getParameter("pageNums");
		//entity.setStatus(Base.examstatus_2);
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adImportantService.getImportantView(entity,pageNum,Base.pagesize,Base.fun_examine,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setImportantCompId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adImportant/examineQuery");
	    map.put("entity", entity);
	    addData(map);
	    map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/administration/importantExamineList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> reportStatus= baseService.getBases(Base.examstatustype);
		List<HhBase> importantTypes = baseService.getBases(Base.important_type);
		map.put("reportStatus",reportStatus);
		map.put("importantTypes", importantTypes);
	}
	
	@RequestMapping("addOrModify")
	public String addOrModifyImportant(AdImportant entity, String lastModifyDate, String organalID, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		HttpSession session = request.getSession();
		map.put("op", op);
		map.put("lastModifyDate", lastModifyDate);
		AdImportant theEntity;
		if(entity.getId()!=null) {
			theEntity=adImportantService.getImportant(entity);
		}else {
			theEntity=new AdImportant();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			Date date=new Date();
			Calendar rightNow = Calendar.getInstance();  
	        rightNow.setTime(date);  
	        rightNow.add(Calendar.MONTH, -1);
			theEntity.setYear(Integer.parseInt(df.format(rightNow.getTime())));
			theEntity.setMonth(Integer.parseInt(df1.format(rightNow.getTime())));
		}
		addData(map);
		map.put("theEntity", theEntity);
		if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		String str=(String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/administration/importantAddOrModify";
	}
	
	@RequestMapping("view")
	public String viewImportant(AdImportant entity, String lastModifyDate, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		map.put("lastModifyDate", lastModifyDate);
		AdImportant theEntity=adImportantService.getImportant(entity);
		map.put("theEntity", theEntity);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_important);
		map.put("examineList", examineList);
		return "/administration/importantView";
	}
	
	@RequestMapping("examine")
	public String examineImportant(AdImportant entity, String lastModifyDate, Map<String, Object> map, HttpServletRequest request) {
		map.put("lastModifyDate", lastModifyDate);
		AdImportant theEntity=adImportantService.getImportant(entity);
		map.put("theEntity", theEntity);
		//审批时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_document);
		map.put("examineList", examineList);
		return "/administration/importantExamine";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateImportant(AdImportant entity, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		CommitResult result= adImportantService.saveOrUpdateImportant(entity, use);
		String data="";
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndRepotedImportant(AdImportant entity, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		entity.setStatus(Base.examstatus_2);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setReportDate(df.format(date));
		entity.setReportPersonId(use.getVcEmployeeId());
		entity.setReportPersonName(use.getVcName());
		CommitResult result= adImportantService.saveOrUpdateImportant(entity, use);
		if(result.isResult())
			potalMsgService.savePortalMsg(new PortalMsg(entity.getCoreCompName()+entity.getYear()+"年"+entity.getMonth()+"月要情台账数据未审核","行政办公",df.format(new Date()),0,0,0,
					use.getVcName(),df.format(new Date()),use.getVcName(),
					df.format(new Date()),entity.getImportantCompId(),systemService.getParentBynNodeID(entity.getImportantCompId(), null),"bimWork_yqsjsjsh_exam","ad_important",entity.getId().toString(),"/shanghai-gzw/adImportant/examineQuery?id="+entity.getId()));
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/reported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportedImportant(Integer id, String lastModifyDate, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		AdImportant entity=adImportantService.getImportant(id);
		entity.setStatus(Base.examstatus_2);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setReportDate(df.format(date));
		entity.setReportPersonId(use.getVcEmployeeId());
		entity.setReportPersonName(use.getVcName());
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result= adImportantService.saveOrUpdateImportant(entity, use);
		if(result.isResult())
			potalMsgService.savePortalMsg(new PortalMsg(entity.getCoreCompName()+entity.getYear()+"年"+entity.getMonth()+"月要情台账数据未审核","行政办公",df.format(new Date()),0,0,0,
					use.getVcName(),df.format(new Date()),use.getVcName(),
					df.format(new Date()),entity.getImportantCompId(),systemService.getParentBynNodeID(entity.getImportantCompId(), null),"bimWork_yqsjsjsh_exam","ad_important",entity.getId().toString(),"/shanghai-gzw/adImportant/examineQuery?id="+entity.getId()));
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid, String lastModifyDate, String examStr, Integer examResult, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		AdImportant entity = adImportantService.getImportant(entityid);
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result= adImportantService.saveImportantExamine(entity, examStr, examResult, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("ad_important", entityid.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deleteImportant(Integer id, String lastModifyDate, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		AdImportant entity = adImportantService.getImportant(id);
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result=adImportantService.deleteImportant(entity, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("ad_important", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "getCoreComp", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getCoreComp(String importantCompId) throws IOException {
		HhOrganInfo entity = new HhOrganInfo();
		entity = adImportantService.getCoreComp(importantCompId);
		String data="";
		data=JSONArray.toJSONString(entity);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "checkDelete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String checkDelete(Integer id) throws IOException {
		AdImportant entity = adImportantService.getImportant(id);
		CommitResult result = new CommitResult();
		if(entity == null){
			result.setResult(false);
		}else {
			result.setResult(true);
		}
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	/**
	 * 
	 企业财务数据汇总统计
	 */
	@RequestMapping("examineQueryGather")
	public String examineQueryGather(AdImportant entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany); 
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(dataauth);
        }
		String curPageNum = request.getParameter("pageNums");
		//entity.setStatus(Base.examstatus_2);
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adImportantService.getImportantView(entity,pageNum,Base.pagesize,Base.fun_examine,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setImportantCompId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adImportant/examineQuery");
	    map.put("entity", entity);
	    addData(map);
	    map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/dataStatistic/examineListGather";		
	}
	
	
	/**
	 * 
	企业财务数据交换情况
	 */
	@RequestMapping("examineQueryExchange")
	public String examineQueryExchange(AdImportant entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany); 
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getImportantCompId())){
        	entity.setImportantCompId(dataauth);
        }
		String curPageNum = request.getParameter("pageNums");
		//entity.setStatus(Base.examstatus_2);
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adImportantService.getImportantView(entity,pageNum,Base.pagesize,Base.fun_examine,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setImportantCompId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adImportant/examineQuery");
	    map.put("entity", entity);
	    addData(map);
	    map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/dataExchange/examineListExchange";		
	}
}
