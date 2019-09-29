package com.softline.controller.bimShow;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.softline.common.Common;
import com.softline.entity.BimCenterCompany;
import com.softline.entity.BimCenterSystem;
import com.softline.entity.SysUsers;
import com.softline.entityTemp.FinanceRisk;
import com.softline.service.bimCenter.IBimCenterService;
import com.softline.service.bimshow.IBimShowDataService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;

@Controller
@RequestMapping("/index")
public class BimShowIndexController {

	@Resource(name = "systemService")
	private ISystemService systemService;

	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "bimCenterService")
	private IBimCenterService bimCenterService;

	@Resource(name = "bimShowDataService")
	private IBimShowDataService bimShowDataService;	
	
	/*
	 * @ModelAttribute public void getCommodity(@RequestParam(value =
	 * "vcEmployeeId", required = false) String vcEmployeeId, Map<String
	 * ,Object> map, HttpServletRequest request) { if
	 * (Common.notEmpty(vcEmployeeId)) { SysUsers usersEntity =
	 * systemService.getUsersEntity01(vcEmployeeId); map.put("sysUsers",
	 * usersEntity); } }
	 */

	/**
	 * @param usersEntity
	 * @param map
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/index")
	public String index(SysUsers usersEntity, Map<String, Object> map,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);
		com.softline.entity.HhUsers users = (com.softline.entity.HhUsers) session.getAttribute("show_session_users");
		//TODO 权限相关信息取得
		//commonService.sessionAd(users.getVcEmployeeId(), request);
		Date now = new Date();
		SimpleDateFormat dateFormatyear = new SimpleDateFormat("yyyy");
		SimpleDateFormat dateFormatmonth = new SimpleDateFormat("MM");
		String year=dateFormatyear.format(now);
		String month=dateFormatmonth.format(now);
		if (month.length() > 1){
			month = month.substring(1);
		}
		// 取上个月
		month = (Integer.parseInt(month) -1) + "";
		if (month.equals("0")){
			year = (Integer.parseInt(year) -1) + "";
			month = "12";
		}
		// 跳转页面
		if(request.getParameter("mutli") != null){
			session.setAttribute("multi", request.getParameter("mutli"));
		}
		
		String page = request.getParameter("pageId");
		if ("1".equals(page)) {
			return "home/bimShow/index";
		} else if ("2".equals(page)) {
			return "alarm/alarmIndex";
		} else if ("4".equals(page)) {
			return "forward:/home/getTBDataByDept1?year="+year+"&month="+month+"&deptid=DeptShow_2";
		} else if ("bimf".equals(page)) {
			return "forward:/finance/financeIndex";
		} else if ("bimr".equals(page)) {
			return "forward:/risk/riskIndex";
		} else {
			return "forward:/project/_list";
		}
	}

	@RequestMapping("/bimIndex")
	public String bimIndex(Map<String ,Object> map, HttpServletRequest request){
		List<BimCenterCompany> results = bimCenterService.getAllCompanys(request);
		BimCenterCompany firstCompany = new BimCenterCompany();
		if(results.size() > 0){
			firstCompany = results.get(0);
			results.remove(0);
		}
		map.put("otherCompanys", results);
		map.put("firstCompany", firstCompany);
		return "home/bimappcenter";
	}
	
	@RequestMapping("/bimDetailPart")
	public String bimDetailPart(Map<String ,Object> map, HttpServletRequest request,Integer systemId){
		BimCenterSystem system = (BimCenterSystem) commonService.findById(BimCenterSystem.class, systemId);
		map.put("system", system);
		return "home/bimappsysdetail";
	}
	
	@ResponseBody
	@RequestMapping(value ="/getFinan_risk_top10", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getFinan_risk_top10(Map<String ,Object> map, HttpServletRequest request,String model_id){
		if(Common.notEmpty(model_id)){
			List<Object> data = bimShowDataService.getFinanceRiskData(model_id);
			String a=JSON.toJSONString(data);
			return a;
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value ="/getFinan_risk_histogram", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getFinan_risk_histogram(Map<String ,Object> map, HttpServletRequest request,String model_id){
		if(Common.notEmpty(model_id)){
			List<Object> data = bimShowDataService.getFinan_risk_histogram(model_id);
			String a=JSON.toJSONString(data);
			return a;
		}		
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value ="/getIcbc_model", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getIcbc_model(Map<String ,Object> map, HttpServletRequest request,String model_id){
		if("icbc_analysis_num".equals(model_id)){
			List<Object> data = bimShowDataService.geticbc_analysis_num(model_id);
			String a=JSON.toJSONString(data);
			return a;
		}	
		if("icbc_analysis_diffCompany".equals(model_id)){
			List<Object> data = bimShowDataService.geticbc_analysis_diff(model_id);
			String a=JSON.toJSONString(data);
			return a;
		}			
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value ="/getFin_manage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getFin_manage(Map<String ,Object> map, HttpServletRequest request,String model_id){
		if("fin_manage_total".equals(model_id)){
			List<Object> data = bimShowDataService.getFin_manage_total(model_id);
			String a=JSON.toJSONString(data);
			return a;
		}	
		if("fin_manage_sasac".equals(model_id)){
			List<Object> data = bimShowDataService.getFin_manage_sasac(model_id);
			String a=JSON.toJSONString(data);
			return a;
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value ="/getpublic_company", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getpublic_company(Map<String ,Object> map, HttpServletRequest request,String model_id){
		if(Common.notEmpty(model_id)){
			List<Object> data = bimShowDataService.getPublic_company(model_id);
			String a=JSON.toJSONString(data);
			return a;
		}	
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value ="/getData_analysis", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getData_analysis(Map<String ,Object> map, HttpServletRequest request,String model_id){
		if(Common.notEmpty(model_id)&&"data_analysis".equals(model_id)){
			List<Object> data = bimShowDataService.getData_analysis(model_id);
			String a=JSON.toJSONString(data);
			return a;
		}	
		if(Common.notEmpty(model_id)&&"data_analysis_desc".equals(model_id)){
			List<Object> data = bimShowDataService.getData_analysis_desc(model_id);
			String a=JSON.toJSONString(data);
			return a;
		}	
		return "";
	}
	

	@RequestMapping(value = "/financial_risk")
	public String financialRisk(Map<String, Object> map,HttpServletRequest request,String year,String orgID) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		// 权限相关信息取得
		HttpSession session = request.getSession();
		//String orgID = (String) session.getAttribute("bimShow_financeorgID");
		String orgName ="";
		if(Common.notEmpty(orgID)) {
			map.put("orgID",orgID);
			orgName=bimCenterService.getComapnyName(orgID);
			map.put("orgName", orgName);
		}else {
			orgID = "d4985136e97d11e7968906a2160000ae";
			 orgName=bimCenterService.getComapnyName(orgID);
			 map.put("orgName", orgName);
		}
		orgID = "d4985136e97d11e7968906a2160000ae";
		List<Object> list = bimCenterService.financialRiskCompany(orgID,year);
		map.put("list", list);
		map.put("year",year);
		return "home/bimShow/financial_risk";
	}
	
	@RequestMapping(value = "/analysis", produces = "text/html;charset=UTF-8")
	public String analysis(Map<String, Object> map, HttpServletRequest request) throws Exception {
		String date = request.getParameter("date");
		map.put("date",date);
		return "home/bimShow/analysis";
	}
	
	
	
	/**
	 * 获取数据
	 */
	@ResponseBody
	@RequestMapping(value ="/getData", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getData(HttpServletRequest request,String org,String year){//,int searchtype
		// 权限相关信息取得
		HttpSession session = request.getSession();
	/*	if(!Common.notEmpty(orgID)){
			orgID = (String) session.getAttribute("bimShow_orgID");// 组织机构编号
		}*/
		
		
		if(Common.notEmpty(year) && Common.notEmpty(org)) {
			List<FinanceRisk> data =  bimCenterService.getData(org,year);//
			if(data!=null)
			{
				String a=JSON.toJSONString(data);
				return a;
			}
		}
		
		return "";
	}
}
