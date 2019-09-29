package com.softline.controller.sixrate;

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
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.DataSixRateDc;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.select.ISelectUserService;
import com.softline.service.sixrate.ISixRateDcService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/sixRateDc")
public class SixRateDcController {

	@Resource(name = "sixRateDcService")
	private ISixRateDcService sixRateDcService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	/**
	 * 查询条件-结果
	 * @param entity
	 * @param request
	 * @param map
	 * @param time
	 * @return
	 */
	@RequestMapping("/list")
	public String hrlist(DataSixRateDc entity,HttpServletRequest request ,Map<String, Object> map,String time) {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
	    if(!Common.notEmpty(entity.getOrganalId()))
	    	entity.setOrganalId(dataauth);
		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		
		String mapurl=request.getContextPath()+ "/sixRateDc";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=sixRateDcService.findPageList(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/sixRateDc/list");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
	    addData(map);
		return "/sixRate/sixRateDc/sixRateDcFormList";
	}
	
	/**
	 * 获取DataSixRateDc
	 * @param id
	 * @return
	 */
	@RequestMapping("/get")
	@ResponseBody
	public DataSixRateDc get(String id) {
		DataSixRateDc dh=sixRateDcService.get(Integer.valueOf(id));
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
	public String saveOrUpdate(DataSixRateDc entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1); 
		return JSONArray.toJSONString(sixRateDcService.saveDataSixRateDc(entity, use ,"save"));
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
	public String update(DataSixRateDc entity,HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DataSixRateDc entityTemp=sixRateDcService.get(entity.getId());
		if(entityTemp==null){
			 entityTemp=new DataSixRateDc();
		}
		entityTemp.setId(entity.getId());
		entityTemp.setYear(entity.getYear());
		entityTemp.setMonth(entity.getMonth());
		entityTemp.setCompany(entity.getCompany());
		entityTemp.setOrganalId(entity.getOrganalId());
		entityTemp.setMarketingAmountHh(entity.getMarketingAmountHh());
		entityTemp.setMarketingAmountNationwide(entity.getMarketingAmountNationwide());
		entityTemp.setMarketShareDc(entity.getMarketShareDc());
		entityTemp.setMarketShareYears(entity.getMarketShareYears());
		entityTemp.setStatus(Base.examstatus_1);
		return JSONArray.toJSONString(sixRateDcService.updateDataSixRateDc(entityTemp, use ,Base.examstatus_1));
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
	public String _saveandreport(DataSixRateDc entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DataSixRateDc entityTemp = new DataSixRateDc();
		CommitResult result = new CommitResult();
		if(entity.getId()!=null){
			entityTemp=sixRateDcService.get(entity.getId());
			entityTemp.setYear(entity.getYear());
			entityTemp.setMonth(entity.getMonth());
			entityTemp.setCompany(entity.getCompany());
			entityTemp.setMarketingAmountHh(entity.getMarketingAmountHh());
			entityTemp.setMarketingAmountNationwide(entity.getMarketingAmountNationwide());
			entityTemp.setMarketShareDc(entity.getMarketShareDc());
			entityTemp.setMarketShareYears(entity.getMarketShareYears());
			entityTemp.setStatus(Base.examstatus_2);
			result= sixRateDcService.saveDataSixRateDc(entityTemp, use , "saveReport");
		}else{
			entity.setStatus(Base.examstatus_2);
			result= sixRateDcService.saveDataSixRateDc(entity, use , "saveReport");
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
		DataSixRateDc entity=sixRateDcService.get(id);
		entity.setStatus(Base.examstatus_2);
		CommitResult result= sixRateDcService.saveDataSixRateDc(entity, use , "report");
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
	public String hrNewModify(DataSixRateDc entity,String organalID, HttpServletRequest request ,Map<String, Object> map) {
		HttpSession session=request.getSession();
		if(entity.getId()==null)
		{	
			entity=new DataSixRateDc();
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
			entity=sixRateDcService.get(entity.getId());
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
		return "/sixRate/sixRateDc/sixRateDcFormNewModify";
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
		DataSixRateDc entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new DataSixRateDc();
		}
		else
		{
			entity=sixRateDcService.get(id);
		    a= examineService.getListExamine(entity.getId(), Base.examkind_six_rateDc);
		}	
		map.put("entity", entity);
		map.put("entityExamineviewlist", a);
		map.put("op", op);
		return "/sixRate/sixRateDc/sixRateDcView";
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
		String mapurl=request.getContextPath()+ "/sixRateDc";
		map.put("mapurl", mapurl);
		DataSixRateDc entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new DataSixRateDc();
		   
		}
		else
		{
			entity=sixRateDcService.get(id);
		}	
		a= examineService.getListExamine(entity.getId(), Base.examkind_six_rateDc);
		map.put("entity", entity);
		map.put("entityExamineviewlist", a);
		map.put("op", op);
		return "/sixRate/sixRateDc/sixRateDcView";
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
	public String _examinelist(DataSixRateDc entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
	    if(!Common.notEmpty(entity.getOrganalId()))
	    	entity.setOrganalId(dataauth);
		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		String mapurl=request.getContextPath()+ "/sixRateDc";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=sixRateDcService.findExaminePageList(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/sixRateDc/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/sixRate/sixRateDc/sixRateDcExamineList";
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
		DataSixRateDc entity;
		if(id==null)
		{
			entity=new DataSixRateDc();
		}
		else
			entity=sixRateDcService.get(id);
		map.put("entity", entity);	
		return "/sixRate/sixRateDc/sixRateDcExamine";
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
		CommitResult result= sixRateDcService.saveDataSixRateDcExamine(entityid, examStr, examResult, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String reportgroupdelete(DataSixRateDc entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		CommitResult result = new CommitResult();
		if(sixRateDcService.get(entity)){
			result=sixRateDcService.deleteDataSixRateDc(entity.getId(), use);
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
	public String get(DataSixRateDc entity) {
		CommitResult result = new CommitResult();
		String data="";
		if(sixRateDcService.get(entity)){
			result.setResult(true); 
		}else{
			result.setResult(false); 
		}
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	/**
	 * 审核的列表页面
	 * 信访
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/datapetitionlist")
	public String datapetitionlist(DataSixRateDc entity, HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		// 树列表信息
		HttpSession session = request.getSession();
		// 数据权限
		String str = (String) session.getAttribute("gzwsession_company");
		String dataauth = systemService.getDataauth(str);
		if (!Common.notEmpty(entity.getOrganalId()))
			entity.setOrganalId(dataauth);

		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
		String mapurl = request.getContextPath() + "/sixRateDc";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = sixRateDcService.findExaminePageList(entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/sixRateDc/datapetitionlist");
		map.put("entityview", entity);
		map.put("monthDate", Base.monthDate);
		addData(map);
		return "/dataExchange/datapetitionlist";
	}
	/**
	 * 审核的列表页面
	 * 
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/summarypetitionlist")
	public String summarypetitionlist(DataSixRateDc entity, HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		// 树列表信息
		HttpSession session = request.getSession();
		// 数据权限
		String str = (String) session.getAttribute("gzwsession_company");
		String dataauth = systemService.getDataauth(str);
		if (!Common.notEmpty(entity.getOrganalId()))
			entity.setOrganalId(dataauth);
		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
		String mapurl = request.getContextPath() + "/sixRateDc";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = sixRateDcService.findExaminePageList(entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/sixRateDc/summarypetitionlist");
		map.put("entityview", entity);
		map.put("monthDate", Base.monthDate);
		addData(map);
		return "/summaryInfo/summarypetitionlist";
	}


	
}
