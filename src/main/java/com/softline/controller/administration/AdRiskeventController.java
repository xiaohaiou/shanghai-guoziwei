package com.softline.controller.administration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.softline.entity.AdRiskevent;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.administration.IAdRiskeventService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("adRiskevent")
public class AdRiskeventController {
	
	@Resource(name = "adRiskeventService")
	private IAdRiskeventService	adRiskeventService;
	
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
	public String queryRiskeventList(AdRiskevent entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
        	entity.setRiskCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
        	entity.setRiskCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
        	entity.setRiskCompId(dataauth);
        }
        
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adRiskeventService.getRiskeventView(entity,pageNum,Base.pagesize,Base.fun_search,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setRiskCompId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adRiskevent/query");
	    map.put("entity", entity);
	    addData(map);
	    map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/administration/riskeventList";
	}
	//汇总界面增加监管预警数据汇总统计
	@RequestMapping("query1")
	public String queryRiskeventList1(AdRiskevent entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
        	entity.setRiskCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
        	entity.setRiskCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
        	entity.setRiskCompId(dataauth);
        }
        
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adRiskeventService.getRiskeventView(entity,pageNum,Base.pagesize,Base.fun_search,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setRiskCompId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adRiskevent/query1");
	    map.put("entity", entity);
	    addData(map);
	    map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/summaryInfo/riskeventList";
	}
	
	//汇总界面增加监管预警数据汇总统计
		@RequestMapping("query2")
		public String queryRiskeventList2(AdRiskevent entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
			HttpSession session=request.getSession();
			 //数据权限
			String str=(String) session.getAttribute("gzwsession_company");
	        String dataauth=systemService.getDataauth(str);
	        //是否在查询公司时包含该公司下所有子公司的数据
	        String isAllCompany = request.getParameter("isallcompany");
	        map.put("isallcompany", isAllCompany);
	        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
	        	entity.setRiskCompId(str);
	        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
	        	entity.setRiskCompId(str);
	        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
	        	entity.setRiskCompId(dataauth);
	        }
	        
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        Integer pageNum = Integer.valueOf(curPageNum);
	        MsgPage msgPage=adRiskeventService.getRiskeventView(entity,pageNum,Base.pagesize,Base.fun_search,isAllCompany);
	        if(!Common.notEmpty(isAllCompany))
	        	entity.setRiskCompId(null);
		    map.put("msgPage", msgPage);
		    map.put("searchurl", "/shanghai-gzw/adRiskevent/query2");
		    map.put("entity", entity);
		    addData(map);
		    map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
			return "/dataExchange/riskeventList";
		}
	@RequestMapping("examineQuery")
	public String queryExamineRiskeventList(AdRiskevent entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
        	entity.setRiskCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
        	entity.setRiskCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getRiskCompId())){
        	entity.setRiskCompId(dataauth);
        }
		String curPageNum = request.getParameter("pageNums");
		//entity.setStatus(Base.examstatus_2);
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adRiskeventService.getRiskeventView(entity,pageNum,Base.pagesize,Base.fun_examine,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setRiskCompId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adRiskevent/examineQuery");
	    map.put("entity", entity);
	    addData(map);
	    map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/administration/riskeventExamineList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> reportStatus= baseService.getBases(Base.examstatustype);
		List<HhBase> seasontype= baseService.getBases(Base.seasontype);
		map.put("reportStatus",reportStatus);
		map.put("seasontype", seasontype);
	}
	
	@RequestMapping("addOrModify")
	public String addOrModifyRiskevent(AdRiskevent entity, String lastModifyDate, String organalID, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		HttpSession session = request.getSession();
		map.put("op", op);
		map.put("lastModifyDate", lastModifyDate);
		AdRiskevent theEntity;
		if(entity.getId()!=null) {
			theEntity=adRiskeventService.getRiskevent(entity);
		}else {
			theEntity=new AdRiskevent();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			theEntity.setYear(Integer.parseInt(df.format(date)));
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
		return "/administration/riskeventAddOrModify";
	}
	
	@RequestMapping("view")
	public String viewRiskevent(AdRiskevent entity, String lastModifyDate, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		map.put("lastModifyDate", lastModifyDate);
		AdRiskevent theEntity=adRiskeventService.getRiskevent(entity);
		map.put("theEntity", theEntity);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskevent);
		map.put("examineList", examineList);
		return "/administration/riskeventView";
	}
	
	@RequestMapping("examine")
	public String examineRiskevent(AdRiskevent entity, String lastModifyDate, Map<String, Object> map, HttpServletRequest request) {
		map.put("lastModifyDate", lastModifyDate);
		AdRiskevent theEntity=adRiskeventService.getRiskevent(entity);
		map.put("theEntity", theEntity);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_document);
		map.put("examineList", examineList);
		return "/administration/riskeventExamine";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateRiskevent(AdRiskevent entity, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		if(entity.getSeasonName().equals("第一季度")){
			entity.setSeason(50108);
		}else if(entity.getSeasonName().equals("第二季度")){
			entity.setSeason(50109);
		}else if(entity.getSeasonName().equals("第三季度")){
			entity.setSeason(50110);
		}else{
			entity.setSeason(50111);
		}
		CommitResult result= adRiskeventService.saveOrUpdateRiskevent(entity, use);
		String data="";
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndRepotedRiskevent(AdRiskevent entity, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		entity.setStatus(Base.examstatus_2);
		if(entity.getSeasonName().equals("第一季度")){
			entity.setSeason(50108);
		}else if(entity.getSeasonName().equals("第二季度")){
			entity.setSeason(50109);
		}else if(entity.getSeasonName().equals("第三季度")){
			entity.setSeason(50110);
		}else{
			entity.setSeason(50111);
		}
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setReportDate(df.format(date));
		entity.setReportPersonId(use.getVcEmployeeId());
		entity.setReportPersonName(use.getVcName());
		CommitResult result= adRiskeventService.saveOrUpdateRiskevent(entity, use);
		if(result.isResult())
			potalMsgService.savePortalMsg(new PortalMsg(entity.getCoreCompName()+entity.getYear()+"年"+entity.getSeasonName()+"保密风险事件数据需要审核","行政办公",df.format(new Date()),0,0,0,
					use.getVcName(),df.format(new Date()),use.getVcName(),
					df.format(new Date()),entity.getRiskCompId(),systemService.getParentBynNodeID(entity.getRiskCompId(), null),"bimWork_bmfxsjsjsh_exam","ad_riskevent",entity.getId().toString(),"/shanghai-gzw/adRiskevent/examineQuery?id="+entity.getId()));
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/reported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportedRiskevent(Integer id, String lastModifyDate, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		AdRiskevent entity=adRiskeventService.getRiskevent(id);
		entity.setStatus(Base.examstatus_2);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setReportDate(df.format(date));
		entity.setReportPersonId(use.getVcEmployeeId());
		entity.setReportPersonName(use.getVcName());
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result= adRiskeventService.saveOrUpdateRiskevent(entity, use);
		if(result.isResult())
			potalMsgService.savePortalMsg(new PortalMsg(entity.getCoreCompName()+entity.getYear()+"年"+entity.getSeasonName()+"保密风险事件数据需要审核","行政办公",df.format(new Date()),0,0,0,
					use.getVcName(),df.format(new Date()),use.getVcName(),
					df.format(new Date()),entity.getRiskCompId(),systemService.getParentBynNodeID(entity.getRiskCompId(), null),"bimWork_bmfxsjsjsh_exam","ad_riskevent",entity.getId().toString(),"/shanghai-gzw/adRiskevent/examineQuery?id="+entity.getId()));
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid, String lastModifyDate, String examStr, Integer examResult, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		AdRiskevent entity = adRiskeventService.getRiskevent(entityid);
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result= adRiskeventService.saveRiskeventExamine(entity, examStr, examResult, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("ad_riskevent", entityid.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deleteRiskevent(Integer id, String lastModifyDate, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		AdRiskevent entity = adRiskeventService.getRiskevent(id);
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result=adRiskeventService.deleteRiskevent(entity, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("ad_riskevent", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "getCoreComp", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getCoreComp(String riskCompId) throws IOException {
		HhOrganInfo entity = new HhOrganInfo();
		entity = adRiskeventService.getCoreComp(riskCompId);
		String data="";
		data=JSONArray.toJSONString(entity);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "checkDelete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String checkDelete(Integer id) throws IOException {
		AdRiskevent entity = adRiskeventService.getRiskevent(id);
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
}
