package com.softline.controller.bimShow;

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
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.HhBase;

import com.softline.entityTemp.BimfOutCompare;
import com.softline.entityTemp.HhOrganInfoTreeRelationFinance;
import com.softline.entityTemp.OutCompareCompany;
import com.softline.service.bimshow.IFinancialPropertyService;
import com.softline.service.publicCompany.IOutCompareService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/financialProperty")
public class FinancialPropertyController {

	@Resource(name = "financialPropertyService")
	private IFinancialPropertyService financialPropertyService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	
	/**
	 * 页面跳转 企业对标
	 * @param request
	 * @param year
	 * @param month
	 * @param orgID
	 * @param map
	 * @return
	 */
	@RequestMapping(value="page")
	public String FinancialProperty(HttpServletRequest request,Map<String, Object> map){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		
		// 权限相关信息取得
		HttpSession session = request.getSession();
		//String orgID = (String) session.getAttribute("bimShow_financeorgID");
		String orgID = "d4985136e97d11e7968906a2160000ae";
		String orgName = financialPropertyService.getFinancialPropertyName(orgID);
		Integer orgNumber = financialPropertyService.getFinancialPropertyNumber(orgID);
		List<Object> list = financialPropertyService.getFinancialProperty(orgID);
	    map.put("name", orgName);
	    map.put("number", orgNumber);
	    map.put("list", list);
	    
	    return "home/bimShow/financialProperty";
	}
	
	

	@ResponseBody
	@RequestMapping(value ="/getFinanceData", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getFinanceData(Map<String ,Object> map, HttpServletRequest request,String org){
		if(Common.notEmpty(org)){
			List<Object> data = financialPropertyService.getFinanceData(org);
			String a=JSON.toJSONString(data);
			return a;
		}	

		return "";
	}

	
}
