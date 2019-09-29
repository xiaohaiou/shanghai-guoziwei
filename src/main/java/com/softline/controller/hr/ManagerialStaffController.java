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
import com.softline.entity.DataHrManagerialStaff;
import com.softline.entity.HhBase;
import com.softline.entity.HhInterfaceLog;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.HeadCountLaborProduction;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.hr.IManagerialStaffService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/managerialStaff")
public class ManagerialStaffController {

	@Resource(name = "managerialStaffService")
	private IManagerialStaffService managerialStaffService;
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
	 */
	@RequestMapping("/list")
	public String hrlist(DataHrManagerialStaff entity,HttpServletRequest request ,Map<String, Object> map,String time) {
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
		
		String mapurl=request.getContextPath()+ "/managerialStaff";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=managerialStaffService.findPageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/managerialStaff/list");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
	    addData(map);
		return "/hr/managerialStaff/managerialStaffFormList";
	}
	
	/**
	 * 获取DataHrManagerialstaff
	 * @param id
	 * @return
	 */
	@RequestMapping("/get")
	@ResponseBody
	public DataHrManagerialStaff get(String id) {
		DataHrManagerialStaff dh=managerialStaffService.get(Integer.valueOf(id));
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
	public String saveOrUpdate(DataHrManagerialStaff entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1); 
		return JSONArray.toJSONString(managerialStaffService.saveDataHrManagerialStaff(entity, use ,"save"));
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
	public String update(DataHrManagerialStaff entity,HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DataHrManagerialStaff entityTemp=managerialStaffService.get(entity.getId());
		entityTemp.setId(entity.getId());
		entityTemp.setYear(entity.getYear());
		entityTemp.setMonth(entity.getMonth());
		entityTemp.setCompany(entity.getCompany());
		entityTemp.setOrganalId(entity.getOrganalId());
		entityTemp.setManNumber(entity.getManNumber());
		entityTemp.setWomanNumber(entity.getWomanNumber());
		entityTemp.setSequenceM(entity.getSequenceM());
		entityTemp.setUnSequenceM(entity.getUnSequenceM());
		entityTemp.setStatus(Base.examstatus_1);
		return JSONArray.toJSONString(managerialStaffService.updateDataHrManagerialStaff(entityTemp, use ,Base.examstatus_1));
	}
	
	/**
	 * 保存并上报
	 * @param entity
	 * @param request
	 * @param map
	 * @param op
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(DataHrManagerialStaff entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DataHrManagerialStaff entityTemp = new DataHrManagerialStaff();
		CommitResult result = new CommitResult();
		if(entity.getId()!=null){
			entityTemp=managerialStaffService.get(entity.getId());
			entityTemp.setYear(entity.getYear());
			entityTemp.setMonth(entity.getMonth());
			entityTemp.setCompany(entity.getCompany());
			entityTemp.setManNumber(entity.getManNumber());
			entityTemp.setWomanNumber(entity.getWomanNumber());
			entityTemp.setSequenceM(entity.getSequenceM());
			entityTemp.setUnSequenceM(entity.getUnSequenceM());
			entityTemp.setStatus(Base.examstatus_2);
			result= managerialStaffService.saveDataHrManagerialStaff(entityTemp, use , "saveReport");
		}else{
			entity.setStatus(Base.examstatus_2);
			result= managerialStaffService.saveDataHrManagerialStaff(entity, use , "saveReport");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 查询页上报
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
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DataHrManagerialStaff entity=managerialStaffService.get(id);
		entity.setStatus(Base.examstatus_2);
		CommitResult result= managerialStaffService.saveDataHrManagerialStaff(entity, use , "report");
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
	public String hrNewModify(DataHrManagerialStaff entity,String organalID, HttpServletRequest request ,Map<String, Object> map) {
		HttpSession session=request.getSession();
		if(entity.getId()==null)
		{	
			entity=new DataHrManagerialStaff();
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
			entity=managerialStaffService.get(entity.getId());
		}	
		if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		String str=(String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		map.put("entityview", entity);	
		addData(map);
		return "/hr/managerialStaff/managerialStaffFormNewModify";
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
		DataHrManagerialStaff entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new DataHrManagerialStaff();
		}
		else
		{
			entity=managerialStaffService.get(id);
		    a= examineService.getListExamine(entity.getId(), Base.examkind_hr_managerialstaff);
		}	
		map.put("entity", entity);
		map.put("entityExamineviewlist", a);
		map.put("op", op);
		return "/hr/managerialStaff/managerialStaffView";
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
		String mapurl=request.getContextPath()+ "/managerialStaff";
		map.put("mapurl", mapurl);
		DataHrManagerialStaff entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new DataHrManagerialStaff();
		   
		}
		else
		{
			entity=managerialStaffService.get(id);
		}	
		a= examineService.getListExamine(entity.getId(), Base.examkind_hr_managerialstaff);
		map.put("entity", entity);
		map.put("entityExamineviewlist", a);
		map.put("op", op);
		return "/hr/managerialStaff/managerialStaffView";
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
	public String _examinelist(DataHrManagerialStaff entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
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
		String mapurl=request.getContextPath()+ "/managerialStaff";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=managerialStaffService.findExaminePageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/managerialStaff/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/hr/managerialStaff/managerialStaffExamineList";
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
	public String hz_examinelist(DataHrManagerialStaff entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
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
		String mapurl=request.getContextPath()+ "/managerialStaff";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=managerialStaffService.findExaminePageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/managerialStaff/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/hr/managerialStaff/hzmanagerialStaffExamineList";
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
	public String jh_examinelist(DataHrManagerialStaff entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
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
		String mapurl=request.getContextPath()+ "/managerialStaff";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=managerialStaffService.findExaminePageList(entity,pageNum,Base.pagesize,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setOrganalId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/managerialStaff/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/hr/managerialStaff/jhmanagerialStaffExamineList";
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
		DataHrManagerialStaff entity;
		if(id==null)
		{
			entity=new DataHrManagerialStaff();
		}
		else
			entity=managerialStaffService.get(id);
		map.put("entity", entity);	
		return "/hr/managerialStaff/managerialStaffExamine";
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
		CommitResult result= managerialStaffService.saveDataHrManagerialstaffExamine(entityid, examStr, examResult, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String reportgroupdelete(DataHrManagerialStaff entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		CommitResult result = new CommitResult();
		if(managerialStaffService.get(entity)){
			result=managerialStaffService.deleteDataHrManagerialstaff(entity.getId(), use);
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
	public String get(DataHrManagerialStaff entity) {
		CommitResult result = new CommitResult();
		String data="";
		if(managerialStaffService.get(entity)){
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
		String first=null;
		String last=null;
		if(!Common.notEmpty(month)||!Common.notEmpty(year)){
			if("0".equals(calendar.get(Calendar.MONTH))){
				month = "12";
				year = (calendar.get(Calendar.YEAR)-1)+"";
			}else{
				month = calendar.get(Calendar.MONTH) + "";
				year = calendar.get(Calendar.YEAR) + "";
			}
			//获取前月的第一天：
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	    	 first = format.format(calendar.getTime());
	    	//获取前月的最后一天
	    	calendar.add(Calendar.MONTH, 1);
	    	calendar.set(Calendar.DAY_OF_MONTH,0);
	    	 last = format.format(calendar.getTime());
		}else {
			calendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
			calendar.set(Calendar.YEAR, Integer.parseInt(year));
			calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
			first = format.format(calendar.getTime());
			calendar.add(Calendar.MONTH, 1);
	    	calendar.set(Calendar.DAY_OF_MONTH,0);
	    	 last = format.format(calendar.getTime());
		}
		
		boolean flag=true;
      	CommitResult result=new CommitResult();
		List list = managerialStaffService.getOverviewIndustry(year, month);
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
		CommitResult result=new CommitResult();
		hhInterfaceLog.setCallPersonName(user.getVcName());
		hhInterfaceLog.setCallVcEmployeeId(user.getVcEmployeeId());
		hhInterfaceLog.setRemark(systemService.getHrOrginfoNameBynnodeID(nnodeId));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		hhInterfaceLog.setCallTime(df.format(new Date()));
		hhInterfaceLog.setIntefaceAlias("管理人员数");
		String Is_Mgt = "1";
		if(user.getCifManageLeader()=="1"){
			 Is_Mgt= "1";
		}
		int i = 0;
		//传入url与秘钥
		String ress=new ODPRequest(Base.ESB_URL,Base.BYLAW_Appsecret)
		.addTextSysPara("AccessToken", Base.BYLAW_AccessToken)
		.addTextSysPara("Method", Base.MANAGE_METHOD)
		.addTextSysPara("Format","json")
		//应用参数
		.addTextAppPara("StartDate",first)
		.addTextAppPara("EndDate",last)
		.addTextAppPara("OrganID",nnodeId)	
		.post();
		JSONObject os= JSON.parseObject(ress);
		Object ccs = os.getJSONObject("MsgResponse").get("ResponseInfo");
		ResponseInfo responseInfos = JSON.parseObject(ccs.toString(), ResponseInfo.class);
		DataHrManagerialStaff list = new DataHrManagerialStaff();
		String dataauth=systemService.getCompanyDataByNNodelID(nnodeId);
		list.setYear(Integer.parseInt(year));
		list.setStatus(50112);
		list.setMonth(Integer.parseInt(month));
		list.setOrganalId(nnodeId);
		list.setCompany(dataauth);
		if(responseInfos.getResult().equals("1")){
			JSONObject obj =  os.getJSONObject("MsgResponse").getJSONObject("Data").getJSONObject("NewDataSet").getJSONObject("dtRetu");

			if(obj.size() > 0){
				 if(obj.getInteger("MGR_LEVEL")==0 && obj.getInteger("MGR_NOLEVEL")==0 && obj.getInteger("MGR_MALE")==0 && obj.getInteger("MGR_FEMALE")==0){
					 result.setResult(true);
					 return;
				 }else{
					   list.setSequenceM(obj.getInteger("MGR_LEVEL"));
					   list.setUnSequenceM(obj.getInteger("MGR_NOLEVEL"));
					   list.setManNumber(obj.getInteger("MGR_MALE"));
					   list.setWomanNumber(obj.getInteger("MGR_FEMALE"));
				 }	   	
			}
		}
		hhInterfaceLog.setBackResult(responseInfos.getResult());
		hhInterfaceLog.setBackResultInfo(ccs.toString());
		hhInterfaceLog.setIntefaceName(Base.MANAGE_METHOD);
		   managerialStaffService.saveDataHrManagerialStaff(list, user,"save");	
		   commonService.saveOrUpdate(hhInterfaceLog);
	}
}
