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
import com.softline.entity.HhBase;
import com.softline.entity.HhInterfaceLog;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.HeadCountLaborProduction;
import com.softline.entityTemp.LaborcostResourcesreWards;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.hr.ILaborCostService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/laborCost")
public class LaborCostController {

	@Resource(name = "laborCostService")
	private ILaborCostService laborCostService;
	
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
	
	/**
	 * 查询条件-结果
	 * @param entity
	 * @param request
	 * @param map
	 * @param time
	 * @return
	 */
	@RequestMapping("/list")
	public String hrlist(LaborcostResourcesreWards entity,HttpServletRequest request ,Map<String, Object> map,String time) {
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
		
		String mapurl=request.getContextPath()+ "/laborCost";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=laborCostService.findPageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/laborCost/list");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
	    addData(map);
		return "/hr/laborCost/laborCostFormList";
	}
	
	/**
	 * 获取LaborcostResourcesreWards
	 * @param id
	 * @return
	 */
	@RequestMapping("/get")
	@ResponseBody
	public LaborcostResourcesreWards get(String id) {
		LaborcostResourcesreWards dh=laborCostService.get(Integer.valueOf(id));
		return dh;
	}
	
	/**
	 * 添加信息
	 * @param map
	 */
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		map.put("examstatustype",examstatustype);
	}
	
	/**
	 * 保存hr数据
	 * @param entity
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveOrUpdate(LaborcostResourcesreWards entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		return JSONArray.toJSONString(laborCostService.saveLaborcostResourcesreWards(entity, use, "save"));
	}
	
	
	/**
	 * 修改hr数据
	 * @param entity
	 * @param request
	 * @return
	 * @throws IOException
	 */
	
	@RequestMapping(value="/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(LaborcostResourcesreWards entity,HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		LaborcostResourcesreWards entityTemp=laborCostService.get(entity.getId());
		entityTemp.setYear(entity.getYear());
		entityTemp.setMonth(entity.getMonth());
		entityTemp.setCompany(entity.getCompany());
		entityTemp.setOrganalId(entity.getOrganalId());
		entityTemp.setLaborCost(entity.getLaborCost());
		entityTemp.setHrRateReturn(entity.getHrRateReturn());
		entityTemp.setYearlyBudget(entity.getYearlyBudget());
		entityTemp.setMonthlyBudget(entity.getMonthlyBudget());
		entityTemp.setMonthlyPerform(entity.getMonthlyPerform());
		entityTemp.setCapitalization(entity.getCapitalization());
		entityTemp.setRetainedProfits(entity.getRetainedProfits());
		entity.setStatus(Base.examstatus_1);
		return JSONArray.toJSONString(laborCostService.updateLaborcostResourcesreWards(entityTemp, use ,Base.examstatus_1));
	}
	
	/**
	 * 编辑新增页面的上报
	 * @param entity
	 * @param request
	 * @param map
	 * @param op
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(LaborcostResourcesreWards entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result = new CommitResult();
		LaborcostResourcesreWards entityTemp = new LaborcostResourcesreWards();
		if(entity.getId()!=null){
			entityTemp=laborCostService.get(entity.getId());
			entityTemp.setYear(entity.getYear());
			entityTemp.setMonth(entity.getMonth());
			entityTemp.setCompany(entity.getCompany());
			entityTemp.setYearlyBudget(entity.getYearlyBudget());
			entityTemp.setMonthlyPerform(entity.getMonthlyPerform());
			entityTemp.setMonthlyBudget(entity.getMonthlyBudget());
			entityTemp.setCapitalization(entity.getCapitalization());
			entityTemp.setRetainedProfits(entity.getRetainedProfits());
			entityTemp.setLaborCost(entity.getLaborCost());
			entityTemp.setHrRateReturn(entity.getHrRateReturn());
			entityTemp.setStatus(Base.examstatus_2);
			result= laborCostService.saveLaborcostResourcesreWards(entityTemp, use ,"saveReport");
		}else{
			entity.setStatus(Base.examstatus_2);
			result= laborCostService.saveLaborcostResourcesreWards(entity, use ,"saveReport");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 查询列表页面的上报
	 * @param id
	 * @param request
	 * @param map
	 * @param op
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/postexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		CommitResult result=new CommitResult();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		LaborcostResourcesreWards entity=laborCostService.get(id);
		entity.setStatus(Base.examstatus_2);
		if(entity.getYearlyBudget()=="" || entity.getMonthlyBudget()=="" || entity.getCapitalization()=="" || entity.getRetainedProfits()==""){
			result.setResult(false);
			result.setEntity(entity);
			result.setExceptionStr("请补充完善数据后再进行上报");
		}else{
			 result= laborCostService.saveLaborcostResourcesreWards(entity, use ,"report");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
		
	/**
	 * 新增弹窗
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/newmodify")
	public String hrNewModify(LaborcostResourcesreWards entity, String organalID, HttpServletRequest request ,Map<String, Object> map) {
		HttpSession session=request.getSession();
		if(entity.getId()==null)
		{	
			entity = new LaborcostResourcesreWards();
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
			entity=laborCostService.get(entity.getId());
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
		return "/hr/laborCost/laborCostFormNewModify";
	}
	
	/**
	 * 跳转新增修改页面
	 * @param id
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map, String op) throws IOException {
		LaborcostResourcesreWards entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new LaborcostResourcesreWards();
		}
		else
		{
			entity=laborCostService.get(id);
		    a= examineService.getListExamine(entity.getId(), Base.examkind_hr_laborCost);
		}	
		map.put("entity", entity);
		map.put("entityExamineview", a);
		map.put("op", op);
		return "/hr/laborCost/laborCostView";
	}
	
	/**
	 * 跳转上报页面
	 * @param id
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/report")
	public String report(Integer id,HttpServletRequest request ,Map<String, Object> map, String op) throws IOException {
		String mapurl=request.getContextPath()+ "/laborCost";
		map.put("mapurl", mapurl);
		LaborcostResourcesreWards entity;
		List<SysExamine> sysExamine=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new LaborcostResourcesreWards();
		}
		else
		{
			entity=laborCostService.get(id);
		}	
		sysExamine= examineService.getListExamine(id, Base.examkind_hr_laborCost);
		map.put("entity", entity);
		map.put("op", op);
		map.put("entityExamineview", sysExamine);
		return "/hr/laborCost/laborCostView";
	}
	
	/**
	 * 审核的列表页面
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/examinelist")
	public String _examinelist(LaborcostResourcesreWards entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
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
		String mapurl=request.getContextPath()+ "/laborCost";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=laborCostService.findExaminePageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/laborCost/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/hr/laborCost/laborCostExamineList";
	}
	/**
	 * 汇总的列表页面
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/hzexaminelist")
	public String hz_examinelist(LaborcostResourcesreWards entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
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
		String mapurl=request.getContextPath()+ "/laborCost";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=laborCostService.findExaminePageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/laborCost/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/hr/laborCost/hzlaborCostExamineList";
	}
	/**
	 * 交换的列表页面
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/jhexaminelist")
	public String jh_examinelist(LaborcostResourcesreWards entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
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
		String mapurl=request.getContextPath()+ "/laborCost";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=laborCostService.findExaminePageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/laborCost/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/hr/laborCost/jhlaborCostExamineList";
	}
	
	/**
	 * 审核
	 * @param id
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		LaborcostResourcesreWards entity;
		if(id==null)
		{
			entity=new LaborcostResourcesreWards();
		}
		else
			entity=laborCostService.get(id);
		map.put("entity", entity);	
		return "/hr/laborCost/laborCostExamine";
	}
	
	/**
	 * 审核提交
	 * @param entityid
	 * @param examStr
	 * @param examResult
	 * @param request
	 * @param map
	 * @param op
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result= laborCostService.saveLaborcostResourcesreWardsExamine(entityid, examStr, examResult, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String reportgroupdelete(LaborcostResourcesreWards entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		CommitResult result = new CommitResult();
		if(laborCostService.get(entity)){
			result=laborCostService.deleteLaborcostResourcesreWards(entity.getId(), use);
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
	public String get(LaborcostResourcesreWards entity) {
		CommitResult result = new CommitResult();
		String data="";
		if(laborCostService.get(entity)){
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
		List list = laborCostService.getOverviewIndustry(year, month);
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
		hhInterfaceLog.setIntefaceAlias("人工成本执行率与人力资源回报率");
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
					    LaborcostResourcesreWards headCountLaborProduction = new LaborcostResourcesreWards();
					    
					/*    headCountLaborProduction.setCreatePersonName(user.getVcFullName());
					    headCountLaborProduction.setCreatePersonId(user.getVcEmployeeId());
					    headCountLaborProduction.setCreateDate(df.format(new Date()));*/
					    headCountLaborProduction.setCompany(obj.getString("V_INDUSTRY_NAME"));
					    headCountLaborProduction.setOrganalId(nnodeId);
						headCountLaborProduction.setMonthlyBudget(obj.getString("N_COST_BUDGET"));
						headCountLaborProduction.setMonthlyPerform(obj.getString("N_COST_ACTUAL"));
						if(obj.getDouble("N_COST_BUDGET")!=null && obj.getDouble("N_COST_BUDGET")!=0){
						    headCountLaborProduction.setLaborCost(obj.getDouble("N_COST_ACTUAL")/obj.getDouble("N_COST_BUDGET")*100);
						}
						headCountLaborProduction.setYear(Integer.parseInt(year));
						headCountLaborProduction.setStatus(50112);
						/*headCountLaborProduction.setIsdel(0);*/
						headCountLaborProduction.setMonth(Integer.parseInt(month));
						laborCostService.saveLaborcostResourcesreWards(headCountLaborProduction, user, "save");
					
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
			    		        LaborcostResourcesreWards headCountLaborProduction = new LaborcostResourcesreWards();
							    headCountLaborProduction.setCompany(dataauth);
							    headCountLaborProduction.setOrganalId(nnodeId);
							    if(obj.getJSONObject(i).getString("N_COST_BUDGET")!=null){
							    	headCountLaborProduction.setMonthlyBudget(obj.getJSONObject(i).getString("N_COST_BUDGET"));
							    }else{
							    	headCountLaborProduction.setMonthlyBudget("");
							    }						
								headCountLaborProduction.setMonthlyPerform(obj.getJSONObject(i).getString("N_COST_ACTUAL"));
								if(obj.getJSONObject(i).getDouble("N_COST_BUDGET")!=null && obj.getJSONObject(i).getDouble("N_COST_BUDGET")!=0){
								    headCountLaborProduction.setLaborCost(obj.getJSONObject(i).getDouble("N_COST_ACTUAL")/obj.getJSONObject(i).getDouble("N_COST_BUDGET")*100);
								}
								headCountLaborProduction.setYear(Integer.parseInt(year));
								headCountLaborProduction.setStatus(50112);
								headCountLaborProduction.setMonth(Integer.parseInt(month));
								laborCostService.saveLaborcostResourcesreWards(headCountLaborProduction, user, "save");
			    	   }
			     }
			}
			hhInterfaceLog.setBackResult(responseInfo.getResult());
			hhInterfaceLog.setBackResultInfo(cc.toString());
			hhInterfaceLog.setIntefaceName(Base.HEADCOUNT_BUSINESS_METHOD);
		}
		commonService.saveOrUpdate(hhInterfaceLog);
	}
	
}
