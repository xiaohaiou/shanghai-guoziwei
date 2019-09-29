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
import com.alibaba.fastjson.JSONObject;
import com.opendata.api.ODPRequest;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.AdDocument;
import com.softline.entity.AdSupervise;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.administration.IAdSuperviseService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("adSupervise")
public class AdSuperviseController {
	
	@Resource(name = "adSuperviseService")
	private IAdSuperviseService	adSuperviseService;
	
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
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@RequestMapping("query")
	public String querySuperviseList(AdSupervise entity, String organalID, Map<String, Object> map, HttpServletRequest request,String audiorDateStart,String audiorDateEnd,String reportDateStart,String reportDateEnd) throws IOException {
		
		if(audiorDateStart!=null){
			request.setAttribute("audiorDateStart", audiorDateStart);	
		}
		if(audiorDateEnd!=null){	
			request.setAttribute("audiorDateEnd", audiorDateEnd);	
		}
		
		if(audiorDateStart!=null){
			request.setAttribute("reportDateStart", reportDateStart);	
		}
		if(audiorDateEnd!=null){	
			request.setAttribute("reportDateEnd", reportDateEnd);	
		}
		
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(dataauth);
        }
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adSuperviseService.getSuperviseView(entity,pageNum,Base.pagesize,Base.fun_search,
        															audiorDateStart,audiorDateEnd,reportDateStart,reportDateEnd,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setCompId(null);
        map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adSupervise/query");
	    map.put("entity", entity);
	    addData(map);
	    if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/administration/superviseList";
	}
	//汇总界面增加预算数据汇总统计
	@RequestMapping("query1")
	public String querySuperviseList1(AdSupervise entity, String organalID, Map<String, Object> map, HttpServletRequest request,String audiorDateStart,String audiorDateEnd,String reportDateStart,String reportDateEnd) throws IOException {
		
		if(audiorDateStart!=null){
			request.setAttribute("audiorDateStart", audiorDateStart);	
		}
		if(audiorDateEnd!=null){	
			request.setAttribute("audiorDateEnd", audiorDateEnd);	
		}
		
		if(audiorDateStart!=null){
			request.setAttribute("reportDateStart", reportDateStart);	
		}
		if(audiorDateEnd!=null){	
			request.setAttribute("reportDateEnd", reportDateEnd);	
		}
		
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(dataauth);
        }
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adSuperviseService.getSuperviseView(entity,pageNum,Base.pagesize,Base.fun_search,
        															audiorDateStart,audiorDateEnd,reportDateStart,reportDateEnd,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setCompId(null);
        map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adSupervise/query1");
	    map.put("entity", entity);
	    addData(map);
	    if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/summaryInfo/superviseList";
	}
	
	//汇总界面增加预算数据汇总统计
		@RequestMapping("query2")
		public String querySuperviseList2(AdSupervise entity, String organalID, Map<String, Object> map, HttpServletRequest request,String audiorDateStart,String audiorDateEnd,String reportDateStart,String reportDateEnd) throws IOException {
			
			if(audiorDateStart!=null){
				request.setAttribute("audiorDateStart", audiorDateStart);	
			}
			if(audiorDateEnd!=null){	
				request.setAttribute("audiorDateEnd", audiorDateEnd);	
			}
			
			if(audiorDateStart!=null){
				request.setAttribute("reportDateStart", reportDateStart);	
			}
			if(audiorDateEnd!=null){	
				request.setAttribute("reportDateEnd", reportDateEnd);	
			}
			
			HttpSession session=request.getSession();
			 //数据权限
			String str=(String) session.getAttribute("gzwsession_company");
	        String dataauth=systemService.getDataauth(str);
	        //是否在查询公司时包含该公司下所有子公司的数据
	        String isAllCompany = request.getParameter("isallcompany");
	        map.put("isallcompany", isAllCompany);
	        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
	        	entity.setCompId(str);
	        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
	        	entity.setCompId(str);
	        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
	        	entity.setCompId(dataauth);
	        }
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        Integer pageNum = Integer.valueOf(curPageNum);
	        MsgPage msgPage=adSuperviseService.getSuperviseView(entity,pageNum,Base.pagesize,Base.fun_search,
	        															audiorDateStart,audiorDateEnd,reportDateStart,reportDateEnd,isAllCompany);
	        if(!Common.notEmpty(isAllCompany))
	        	entity.setCompId(null);
	        map.put("msgPage", msgPage);
		    map.put("searchurl", "/shanghai-gzw/adSupervise/query2");
		    map.put("entity", entity);
		    addData(map);
		    if(organalID==null)
			{
				HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
				organalID=use.getNnodeId();
			}
			map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
			return "/dataExchange/superviseList";
		}
	@RequestMapping("examineQuery")
	public String queryExamineDocumentList(AdSupervise entity, String organalID, Map<String, Object> map, HttpServletRequest request,String audiorDateStart,String audiorDateEnd,String reportDateStart,String reportDateEnd) throws IOException {
		if(audiorDateStart!=null){
			request.setAttribute("audiorDateStart", audiorDateStart);
			
		}
		if(audiorDateEnd!=null){
			
			request.setAttribute("audiorDateEnd", audiorDateEnd);
			
		}
		if(audiorDateStart!=null){
			request.setAttribute("reportDateEnd", reportDateEnd);
			
		}
		if(audiorDateEnd!=null){
			
			request.setAttribute("reportDateStart", reportDateStart);
			
		}
		
		
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(dataauth);
        }
		String curPageNum = request.getParameter("pageNums");
		//entity.setStatus(Base.examstatus_2);
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adSuperviseService.getSuperviseView(entity,pageNum,Base.pagesize,Base.fun_examine,
        												audiorDateStart,audiorDateEnd,reportDateStart,reportDateEnd,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setCompId(null);
        map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adSupervise/examineQuery");
	    map.put("entity", entity);
	    addData(map);
	    if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/administration/superviseExamineList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> reportStatus= baseService.getBases(Base.examstatustype);
		map.put("reportStatus",reportStatus);
	}
	
	@RequestMapping("addOrModify")
	public String addOrModifySupervise(AdSupervise entity, String lastModifyDate, String organalID, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		HttpSession session=request.getSession();
		map.put("op", op);
		map.put("lastModifyDate", lastModifyDate);
		AdSupervise theEntity;
		if(entity.getId()!=null) {
			theEntity=adSuperviseService.getSupervise(entity);
		}else {
			theEntity=new AdSupervise();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			theEntity.setYear(Integer.parseInt(df.format(date)));
		}
		map.put("theEntity", theEntity);
		if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		String str=(String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/administration/superviseAddOrModify";
	}
	
	@RequestMapping("view")
	public String viewSupervise(AdSupervise entity, String lastModifyDate, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		map.put("lastModifyDate", lastModifyDate);
		AdSupervise theEntity=adSuperviseService.getSupervise(entity);
		map.put("theEntity", theEntity);
		//查看时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_supervise);
		map.put("examineList", examineList);
		return "/administration/superviseView";
	}
	
	@RequestMapping("examine")
	public String examineSupervise(AdSupervise entity, String lastModifyDate, Map<String, Object> map, HttpServletRequest request) {
		map.put("lastModifyDate", lastModifyDate);
		AdSupervise theEntity=adSuperviseService.getSupervise(entity);
		map.put("theEntity", theEntity);
		//审批时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_document);
		map.put("examineList", examineList);
		return "/administration/superviseExamine";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateSupervise(AdSupervise entity, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		CommitResult result= adSuperviseService.saveOrUpdateSupervise(entity, use);
		String data="";
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndReportedSupervise(AdSupervise entity, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		entity.setStatus(Base.examstatus_2);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setReportDate(df.format(date));
		entity.setReportPersonId(use.getVcEmployeeId());
		entity.setReportPersonName(use.getVcName());
		CommitResult result= adSuperviseService.saveOrUpdateSupervise(entity, use);
		if(result.isResult())
			potalMsgService.savePortalMsg(new PortalMsg(entity.getCompName()+entity.getYear()+"年行政督办办结率数据未审核","行政办公",df.format(new Date()),0,0,0,
					use.getVcName(),df.format(new Date()),use.getVcName(),
					df.format(new Date()),entity.getCompId(),systemService.getParentBynNodeID(entity.getCompId(), null),"bimWork_dbsjsh_exam","ad_supervise",entity.getId().toString(),"/shanghai-gzw/adSupervise/examineQuery?id="+entity.getId()));
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/reported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportedSupervise(Integer id, String lastModifyDate, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		AdSupervise entity = adSuperviseService.getSupervise(id);
		entity.setStatus(Base.examstatus_2);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setReportDate(df.format(date));
		entity.setReportPersonId(use.getVcEmployeeId());
		entity.setReportPersonName(use.getVcName());
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result= adSuperviseService.saveOrUpdateSupervise(entity, use);
		if(result.isResult())
			potalMsgService.savePortalMsg(new PortalMsg(entity.getCompName()+entity.getYear()+"年行政督办办结率数据需要审核","行政办公",df.format(new Date()),0,0,0,
					use.getVcName(),df.format(new Date()),use.getVcName(),
					df.format(new Date()),entity.getCompId(),systemService.getParentBynNodeID(entity.getCompId(), null),"bimWork_dbsjsh_exam","ad_supervise",entity.getId().toString(),"/shanghai-gzw/adSupervise/examineQuery?id="+entity.getId()));
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String lastModifyDate, String examStr,Integer examResult,HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		AdSupervise entity = adSuperviseService.getSupervise(entityid);
		entity.setAuditorDate(lastModifyDate);
		CommitResult result= adSuperviseService.saveSuperviseExamine(entity, examStr, examResult, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("ad_supervise", entityid.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deleteSupervise(Integer id, String lastModifyDate, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		AdSupervise entity = adSuperviseService.getSupervise(id);
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result=adSuperviseService.deleteSupervise(entity, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("ad_supervise", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "checkDelete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String checkDelete(Integer id) throws IOException {
		AdSupervise entity = adSuperviseService.getSupervise(id);
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
	
	@ResponseBody
	@RequestMapping(value ="/synISupervise", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String synISupervise(String year, String month, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		//获取上月年、月
		Calendar calendar = Calendar.getInstance();
		if("0".equals(calendar.get(Calendar.MONTH))){
			month = "12";
			year = (calendar.get(Calendar.YEAR)-1)+"";
		}else{
			month = calendar.get(Calendar.MONTH) + "";
			year = calendar.get(Calendar.YEAR) + "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
    	//获取上月的第一天：
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
    	String first = year + "-1-1";
    	//获取上月的最后一天
    	calendar.add(Calendar.MONTH, 1);
    	calendar.set(Calendar.DAY_OF_MONTH,0);
    	String last = format.format(calendar.getTime());
		
    	CommitResult result=new CommitResult();
		List<AdSupervise> list = adSuperviseService.getSuperviseList(year, month);
		
		if(list.size() == 0){
			result.setExceptionStr("没有需要同步的数据！");
			result.setResult(false);
			String data=JSONArray.toJSONString(result);
			return data;
		}
		else{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(AdSupervise adSupervise : list){
				String nNodeID = adSupervise.getVcOrganID();
				//nNodeID = "0-1-855579-856150-";
				//nNodeID = "0-1-855579-856150-850766-801197-802701-";
				//传入url与秘钥
				String res=new ODPRequest(Base.ESB_URL,Base.BYLAW_Appsecret)
				.addTextSysPara("AccessToken", Base.BYLAW_AccessToken)
				.addTextSysPara("Method", Base.SUPERVISE_METHOD)
				.addTextSysPara("Format","json")
				//应用参数
				.addTextAppPara("SysSource",Base.DOC_SYS_SOURCE)
				.addTextAppPara("OrganID",nNodeID)
				.addTextAppPara("DateTimeStart",first)
				.addTextAppPara("DateTimeEnd",last)
				.addTextAppPara("TaskCreateTimeStart",first)
				.addTextAppPara("TaskCreateTimeEnd",last)
				.post();
				JSONObject o= JSON.parseObject(res);
				Object cc = o.getJSONObject("MsgResponse").get("ResponseInfo");
				ResponseInfo responseInfo = JSON.parseObject(cc.toString(), ResponseInfo.class);
				if(responseInfo.getResult().equals("-1")){
					System.out.println("-123");
					continue;
				}
				if(responseInfo.getResult().equals("1")){
					JSONObject obj =  o.getJSONObject("MsgResponse").getJSONObject("Data").getJSONObject("OrganRateList").getJSONObject("RateInfo");
					if(obj.get("TotalCount") == null || "".equals(obj.get("TotalCount")) || "0".equals(obj.get("TotalCount"))){
						continue;
					}
					adSupervise.setTotalDivision(obj.getInteger("TotalCount"));
					adSupervise.setFinishDivision(obj.getInteger("FinishCount"));
					adSupervise.setPropelDivision(obj.getInteger("ProcessCount"));
					adSupervise.setFinishDivisionRatio(obj.getDouble("FinishRate"));
					adSupervise.setLastModifyPersonName(user.getVcName());
					adSupervise.setLastModifyPersonId(user.getVcEmployeeId());
					adSupervise.setLastModifyDate(df.format(new Date()));
					adSupervise.setYear(Integer.parseInt(year));
					adSupervise.setStatus(50112);
					commonService.saveOrUpdate(adSupervise);
				}
			}
		}
		result.setExceptionStr("数据同步成功！");
		result.setResult(true);
		String data=JSONArray.toJSONString(result);
		return data;
	}
}
