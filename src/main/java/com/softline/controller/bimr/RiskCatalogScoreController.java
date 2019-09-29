package com.softline.controller.bimr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrRiskCatalogScore;
import com.softline.entityTemp.CommitResult;
import com.softline.service.bimr.IRiskCatalogScoreService;
import com.softline.service.system.ISystemService;

/**
 * 实现二级风险目录评分页面显示控制
 * 
 * @author liu
 */
@Controller
@RequestMapping("/bimr/riskCatalogScore")
public class RiskCatalogScoreController {
	
	@Autowired
	private IRiskCatalogScoreService service;
	
	@Autowired
	private ISystemService systemService;
	
	

	@RequestMapping("list")
	public String list(Integer year, Integer month,String coreOrg,String coreOrgName,
			HttpServletRequest request, Map<String, Object> map){
		
		Calendar calendar= Calendar.getInstance();
		if(year == null || month == null){
			year = calendar.get(Calendar.YEAR);
			month = getSeason(calendar.get(Calendar.MONTH));
		}
		map.put("year",year);
		map.put("month",month);
		//获取核心企业以及海航物流
		HttpSession session = request.getSession();
		String orgId = (String)session.getAttribute("gzwsession_company");
		 //获取数据权限
        String dataauth=systemService.getDataauth(orgId);
		List<HhOrganInfo>  companys =  systemService.getTopChildrenCompanyOrganInfos(dataauth);
		map.put("companys", companys);
		if(StringUtils.isBlank(coreOrg) && companys.size()>0){
			coreOrg =companys.get(0).getNnodeId();
			coreOrgName = companys.get(0).getVcFullName();
		}
		List<BimrRiskCatalogScore> scores = new ArrayList<BimrRiskCatalogScore>();
		
		if(!StringUtils.isBlank(coreOrg)){
			scores = service.getRiskCatalogScoreList(year, month,coreOrg,coreOrgName);
		}
		
		map.put("scores", scores);
		map.put("scoresCount", scores.size());
		map.put("coreOrg", coreOrg);
		map.put("coreOrgName", coreOrgName);
		
		
		String mapurl = request.getContextPath() + "/bimr/riskCatalogScore";
		map.put("mapurl", mapurl);
		
		return "/bimr/riskctgscore/riskCatalogScoreList";
	}
	
	/**
	 * 
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getSeason(int month) {
 
		int season = 0;
 
		
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}

	
	private Date parseDate(String date){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		try{
			return dateFormat.parse(date);
		}catch (Exception e) {
			return new Date();
		}
	}
	
	@RequestMapping(value="save", method = RequestMethod.POST, consumes="application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult save(@RequestBody List<BimrRiskCatalogScore> scores, HttpServletRequest request){
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		return service.batchSaveAndUpdate(scores, user);
	}
}
