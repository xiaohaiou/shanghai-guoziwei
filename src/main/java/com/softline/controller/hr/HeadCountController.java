package com.softline.controller.hr;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.softline.entity.HhInterfaceLog;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.HeadCountLaborProduction;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.hr.IHeadCountService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/headCount")
public class HeadCountController {

	@Resource(name = "headCountService")
	private IHeadCountService headCountService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 查询条件-结果
	 */
	@RequestMapping("/list")
	public String hrlist(HeadCountLaborProduction entity,HttpServletRequest request ,Map<String, Object> map,String time) {
		//数据权限
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(dataauth);
        }
        
		String mapurl=request.getContextPath()+ "/headCount";
		//树列表信息
		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=headCountService.findPageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/headCount/list");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
	    addData(map);
		return "/hr/headCount/headCountFormList";
	}
	
	/**
	 * 获取HeadCountLaborProduction
	 */
	@RequestMapping("/get")
	@ResponseBody
	public HeadCountLaborProduction get(String id) {
		HeadCountLaborProduction dh=headCountService.get(Integer.valueOf(id));
		return dh;
	}
	
	/**
	 * 添加信息
	 */
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		map.put("examstatustype",examstatustype);
	}
	
	/**
	 * 保存hr数据
	 */
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveOrUpdate(HeadCountLaborProduction entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		return JSONArray.toJSONString(headCountService.saveHeadCountLaborProduction(entity, use,"save"));
	}
	
	/**
	 * 修改hr数据
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(HeadCountLaborProduction entity,HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		HeadCountLaborProduction entityTemp=headCountService.get(entity.getId());
		entityTemp.setYear(entity.getYear());
		entityTemp.setMonth(entity.getMonth());
		entityTemp.setCompany(entity.getCompany());
		entityTemp.setOrganalId(entity.getOrganalId());
		entityTemp.setWorkersNumber(entity.getWorkersNumber());
		entityTemp.setTworkersNumber(entity.getTworkersNumber());
		entityTemp.setAverageIncome(entity.getAverageIncome());
		entityTemp.setAverageProfit(entity.getAverageProfit());
		entityTemp.setBusinessIncome(entity.getBusinessIncome());
		entityTemp.setBusinessProfit(entity.getBusinessProfit());
		entity.setStatus(Base.examstatus_1);
		return JSONArray.toJSONString(headCountService.updateHeadCountLaborProduction(entityTemp, use ,Base.examstatus_1));
	}
	
	/**
	 * 编辑新增页面的上报
	 */
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(HeadCountLaborProduction entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		HeadCountLaborProduction entityTemp = new HeadCountLaborProduction();
		CommitResult result = new CommitResult();
		if(entity.getId()!=null){
			entityTemp=headCountService.get(entity.getId());
			entityTemp.setYear(entity.getYear());
			entityTemp.setMonth(entity.getMonth());
			entityTemp.setCompany(entity.getCompany());
			entityTemp.setWorkersNumber(entity.getWorkersNumber());
			entityTemp.setTworkersNumber(entity.getTworkersNumber());
			entityTemp.setAverageIncome(entity.getAverageIncome());
			entityTemp.setAverageProfit(entity.getAverageProfit());
			entityTemp.setBusinessIncome(entity.getBusinessIncome());
			entityTemp.setBusinessProfit(entity.getBusinessProfit());
			entityTemp.setStatus(Base.examstatus_2);
			Date date = new Date();
			entityTemp.setReportDate(df.format(date));
			entityTemp.setReportPersonId(use.getVcEmployeeId());
			entityTemp.setReportPersonName(use.getVcName());
			result= headCountService.saveHeadCountLaborProduction(entityTemp, use,"saveReport");
		}else{
			entity.setStatus(Base.examstatus_2);
			result= headCountService.saveHeadCountLaborProduction(entity, use, "saveReport");
		}
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 查询列表页面的上报
	 */
	@ResponseBody
	@RequestMapping(value ="/postexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		CommitResult result=new CommitResult();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		HeadCountLaborProduction entity=headCountService.get(id);
		entity.setStatus(Base.examstatus_2);
		if(entity.getAverageIncome()=="" || entity.getAverageProfit()=="" || entity.getBusinessIncome()=="" || entity.getBusinessProfit()==""){
			result.setResult(false);
			result.setEntity(entity);
			result.setExceptionStr("请补充完善数据后再进行上报");
		}else{
		    result= headCountService.saveHeadCountLaborProduction(entity, use, "report");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 新增弹窗
	 */
	@RequestMapping("/newmodify")
	public String hrNewModify(HeadCountLaborProduction entity, String organalID, HttpServletRequest request ,Map<String, Object> map) {
		HttpSession session=request.getSession();
		if(entity.getId()==null)
		{	
			entity = new HeadCountLaborProduction();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			Date date = new Date();
			Calendar rightNow = Calendar.getInstance();  
	        rightNow.setTime(date);  
	        rightNow.add(Calendar.MONTH, -1);
			entity.setYear(Integer.parseInt(df.format(rightNow.getTime())));
			entity.setMonth(Integer.parseInt(df1.format(rightNow.getTime())));
		}
		else{
			map.put("op", "modify");
			map.put("monthDate", Base.monthDate);
			entity=headCountService.get(entity.getId());
		}	
		map.put("entityview", entity);	
		if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		String str=(String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		addData(map);
		return "/hr/headCount/headCountFormNewModify";
	}
//collect 汇总
	@RequestMapping("/collect")
	public String hrcollect(HeadCountLaborProduction entity, String organalID, HttpServletRequest request ,Map<String, Object> map) {
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
	 
		String mapurl=request.getContextPath()+ "/headCount";
		//树列表信息
		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		map.put("mapurl", mapurl);
		
	    map.put("monthDate", Base.monthDate);
	    addData(map);
	   
	    if(entity.getOrganalId()!=null){
	    	
	    	 List<HeadCountLaborProduction> list=headCountService.findCollectList(entity);
	    	// map.put("entitycollect", list.get(0));
	    	 if(list.get(0).getId()==0){
	    		entity.setBusinessIncome("");
	    		entity.setBusinessProfit("");
	    		entity.setTworkersNumber(0);
	    		entity.setWorkersNumber(0);
	    		entity.setAverageIncome("");
	    		entity.setAverageProfit("");
	    		map.put("entityview", entity);
	    		
	    	 }else{
	    		 map.put("entityview", list.get(0));
	    	 }
	    	 
	    }else{
	    	Date date = new Date();
			// 默认当前日期
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			Calendar rightNow = Calendar.getInstance();  
	        rightNow.setTime(date);  
	        rightNow.add(Calendar.MONTH, -1);
	        entity.setYear(Integer.parseInt(df.format(rightNow.getTime())));
			entity.setMonth(Integer.parseInt(df1.format(rightNow.getTime())));
	    	   map.put("entityview", entity);
	    }
	   
		
		map.put("collecturl", "/shanghai-gzw/headCount/collect");
		
		return "/hr/headCount/headCountFormCollect";
	}
	/**
	 * 跳转新增修改页面
	 */
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map, String op) throws IOException {
		HeadCountLaborProduction entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new HeadCountLaborProduction();
		}
		else
		{
			entity=headCountService.get(id);
		}	
		a= examineService.getListExamine(entity.getId(), Base.examkind_hr_headCount);
		map.put("entity", entity);
		map.put("entityExamineview", a);
		map.put("op", op);
		return "/hr/headCount/headCountView";
	}
	
	/**
	 * 跳转上报页面
	 */
	@RequestMapping("/report")
	public String report(Integer id,HttpServletRequest request ,Map<String, Object> map, String op) throws IOException {
		String mapurl=request.getContextPath()+ "/headCount";
		map.put("mapurl", mapurl);
		HeadCountLaborProduction entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new HeadCountLaborProduction();
		}
		else
		{
			entity=headCountService.get(id);
		}	
		a= examineService.getListExamine(id, Base.examkind_hr_headCount);
		map.put("entity", entity);
		map.put("op", op);
		map.put("entityExamineview", a);
		return "/hr/headCount/headCountView";
	}
	
	/**
	 * 审核的列表页面
	 */
	@RequestMapping("/examinelist")
	public String _examinelist(HeadCountLaborProduction entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);      
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(dataauth);
        }
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		String mapurl=request.getContextPath()+ "/headCount";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=headCountService.findExaminePageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/headCount/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/hr/headCount/headCountExamineList";
	}
	
	/**
	 * 审核
	 */
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HeadCountLaborProduction entity;
		if(id==null)
		{
			entity=new HeadCountLaborProduction();
		}
		else
			entity=headCountService.get(id);
		map.put("entity", entity);	
		return "/hr/headCount/headCountExamine";
	}
	
	/**
	 * 审核提交
	 */
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result= headCountService.saveHeadCountLaborProductionExamine(entityid, examStr, examResult, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String reportgroupdelete(HeadCountLaborProduction entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		CommitResult result = new CommitResult();
		if(headCountService.get(entity)){
			result=headCountService.deleteHeadCountLaborProduction(entity.getId(), use);
		}else{
			result.setResult(false); 
		}
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	/*
	 * chack
	 */
	@RequestMapping("/chackGet")
	@ResponseBody
	public String get(HeadCountLaborProduction entity) {
		CommitResult result = new CommitResult();
		String data="";
		if(headCountService.get(entity)){
			result.setResult(true); 
		}else{
			result.setResult(false); 
		}
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/getOverviewIndustry", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getOverviewIndustry(String year, String month,String NodeId, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		//获取上月年、月
		Calendar calendar = Calendar.getInstance();
		String first=null;
		String last=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		calendar.set(Calendar.YEAR,Integer.parseInt(year));
		calendar.set(Calendar.MONTH,Integer.parseInt(month)-1);
    	first = format.format(calendar.getTime());
        last = format.format(calendar.getTime());
		
		boolean flag=true;
      	CommitResult result=new CommitResult();
		List list = headCountService.getOverviewIndustry(year, month);
		if(list.size() == 0){
			result.setExceptionStr("没有需要同步的数据！");
			result.setResult(false);
			String data=JSONArray.toJSONString(result);
			return data;
		}
		if (Common.notEmpty(NodeId)) {
			for (Object object : list) {
				if(NodeId.equals(object.toString())){
					tb(first, last, NodeId, user, year, month);
					flag=false;
					break;
				}
			}
			if (flag) {
				result.setExceptionStr("同步失败，数据已存在！");
				result.setResult(false);
				String data=JSONArray.toJSONString(result);
				
				return data;
		    }
		}
		else{
				for(Object obj : list){
					tb(first, last, String.valueOf(obj), user, year, month);
				}
			}

		result.setExceptionStr("数据同步成功！");
		result.setResult(true);
		String data=JSONArray.toJSONString(result);
		return data;
	}
	
	
	public void tb(String first,String last,String nnodeId,HhUsers user,String year,String month) {
		HhInterfaceLog hhInterfaceLog=new HhInterfaceLog();
		hhInterfaceLog.setCallPersonName(user.getVcName());
		hhInterfaceLog.setCallVcEmployeeId(user.getVcEmployeeId());
		hhInterfaceLog.setRemark(systemService.getHrOrginfoNameBynnodeID(nnodeId));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		hhInterfaceLog.setCallTime(df.format(new Date()));
		hhInterfaceLog.setIntefaceAlias("总人数与劳动生产率");
		if(nnodeId.equals(Base.HRTop)){
			//传入url与秘钥
			String res=new ODPRequest(Base.ESB_URL,Base.BYLAW_Appsecret)
			.addTextSysPara("AccessToken", Base.BYLAW_AccessToken)
			.addTextSysPara("Method", Base.HEADCOUNT_METHOD)
			.addTextSysPara("Format","json")
			//应用参数
			.addTextAppPara("StartMonths",first)
			.addTextAppPara("EndMonths",last)
			.addTextAppPara("OrgCode","HNA16")		
			.post();
			JSONObject o= JSON.parseObject(res);
			Object cc = o.getJSONObject("MsgResponse").get("ResponseInfo");
			ResponseInfo responseInfo = JSON.parseObject(cc.toString(), ResponseInfo.class);
			if(responseInfo.getResult().equals("1")){
				JSONObject obj =  o.getJSONObject("MsgResponse").getJSONObject("Data").getJSONObject("NewDataSet").getJSONObject("dtRetu");
				if(obj.size() > 0){
					   HeadCountLaborProduction headCountLaborProduction = new HeadCountLaborProduction();
					    
					/*    headCountLaborProduction.setCreatePersonName(user.getVcFullName());
					    headCountLaborProduction.setCreatePersonId(user.getVcEmployeeId());
					    headCountLaborProduction.setCreateDate(df.format(new Date()));*/
					    headCountLaborProduction.setCompany(obj.getString("V_INDUSTRY_NAME"));
					    headCountLaborProduction.setOrganalId(nnodeId);
						headCountLaborProduction.setWorkersNumber(obj.getInteger("N_STAFF_CONTRAT"));
						headCountLaborProduction.setTworkersNumber(obj.getInteger("N_STAFF_OTHER"));
						headCountLaborProduction.setYear(Integer.parseInt(year));
						headCountLaborProduction.setStatus(50112);
						/*headCountLaborProduction.setIsdel(0);*/
						headCountLaborProduction.setMonth(Integer.parseInt(month));
						headCountService.saveHeadCountLaborProduction(headCountLaborProduction, user, "save");
				}
				
			}
			hhInterfaceLog.setBackResult(responseInfo.getResult());
			hhInterfaceLog.setBackResultInfo(cc.toString());
			hhInterfaceLog.setIntefaceName(Base.HEADCOUNT_METHOD);
		}else{
			String res=new ODPRequest(Base.ESB_URL,Base.BYLAW_Appsecret)
			.addTextSysPara("AccessToken", Base.BYLAW_AccessToken)
			.addTextSysPara("Method", Base.HEADCOUNT_BUSINESS_METHOD)
			.addTextSysPara("Format","json")
			//应用参数
			.addTextAppPara("StartMonths",first)
			.addTextAppPara("EndMonths",last)
			.addTextAppPara("OrgCode","HNA16")		
			.post();
			JSONObject o= JSON.parseObject(res);
			Object cc = o.getJSONObject("MsgResponse").get("ResponseInfo");
			ResponseInfo responseInfo = JSON.parseObject(cc.toString(), ResponseInfo.class);
			String dataauth=systemService.getCompanyDataByNNodelID(nnodeId);
			if(responseInfo.getResult().equals("1")){
				JSONArray obj =  o.getJSONObject("MsgResponse").getJSONObject("Data").getJSONArray("NewDataSet");
			     for (int i = 0; i < obj.size(); i++) {
			    	   if(dataauth.equals(obj.getJSONObject(i).getString("V_COMP_NAME"))){
			    		    HeadCountLaborProduction headCountLaborProduction = new HeadCountLaborProduction();
			    		    headCountLaborProduction.setCompany(dataauth);
						    headCountLaborProduction.setOrganalId(nnodeId);
							headCountLaborProduction.setWorkersNumber(obj.getJSONObject(i).getInteger("N_STAFF_CONTRAT"));
							headCountLaborProduction.setTworkersNumber(obj.getJSONObject(i).getInteger("N_STAFF_OTHER"));
							headCountLaborProduction.setYear(Integer.parseInt(year));
							headCountLaborProduction.setStatus(50112);
							/*headCountLaborProduction.setIsdel(0);*/
							headCountLaborProduction.setMonth(Integer.parseInt(month));
							headCountService.saveHeadCountLaborProduction(headCountLaborProduction, user, "save");
			    	   }				
				}	
			}
			hhInterfaceLog.setBackResult(responseInfo.getResult());
			hhInterfaceLog.setBackResultInfo(cc.toString());
			hhInterfaceLog.setIntefaceName(Base.HEADCOUNT_BUSINESS_METHOD);
		}	
		commonService.saveOrUpdate(hhInterfaceLog);
	}
	
	/**
	 * 产权信息汇总统计
	 */
	@RequestMapping("/proRightInfoCollect")
	public String proRightInfoCollect(HeadCountLaborProduction entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);      
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(dataauth);
        }
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		String mapurl=request.getContextPath()+ "/headCount";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=headCountService.findExaminePageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/headCount/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/hr/headCount/employeecareExamineListGather";
	}
	
	
	/**
	 * 产权信息汇总数据交换情况
	 */
	@RequestMapping("/proRightInfoExchange")
	public String proRightInfoExchange(HeadCountLaborProduction entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);      
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getOrganalId())){
        	entity.setOrganalId(dataauth);
        }
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		String mapurl=request.getContextPath()+ "/headCount";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=headCountService.findExaminePageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/headCount/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/hr/headCount/employeecareExamineListGather";
	}
}
