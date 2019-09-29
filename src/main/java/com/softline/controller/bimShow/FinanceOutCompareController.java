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
import com.softline.entityTemp.OutCompareCompany;
import com.softline.service.publicCompany.IOutCompareService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ISystemService;

@Controller
@RequestMapping("/outcompare")
public class FinanceOutCompareController {

	@Resource(name = "outcompareService")
	private IOutCompareService outcompareService;
	
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
	public String OutCompare(HttpServletRequest request,Map<String, Object> map,int year,int season){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		
		// 权限相关信息取得
		HttpSession session = request.getSession();
		//String orgID = (String) session.getAttribute("bimShow_financeorgID");
		String orgID = "d4985136e97d11e7968906a2160000ae";
		String upCaseSeason = "一";
		int month=0;
		switch (season) {
			case 1:
				month=3;
				upCaseSeason = "一";
				break;
			case 2:
				month=6;
				upCaseSeason = "二";
				break;
			case 3:
				month=9;
				upCaseSeason = "三";
				break;
			case 4:
				month=12;
				upCaseSeason = "四";
				break;
		}
		 
		//String time=year+"-"+String.format("%02d", month );
		String time = "2017-12";
		map.put("season", season);
		map.put("year", year);
		map.put("upCaseSeason", upCaseSeason);
		List<OutCompareCompany>Data=new ArrayList<OutCompareCompany>();
		Data = systemService.getOutCompareCompanyList(orgID,time, Base.financetype);
		map.put("Inselectorg", Data);
		if(Data!=null){
			String a=JSON.toJSONString(Data);
			map.put("inSelectData", a);
		}
		List<OutCompareCompany>OutData=new ArrayList<OutCompareCompany>();
		OutData = systemService.getAllOutCompareCompanyList();
		map.put("Outselectorg", OutData);
		if(Data!=null && Data.size()>0)
			map.put("selectedOut", Data.get(0).getOutorg());
		return "/publicCompany/outcompare";
	}
	
	
	/**
	 * 获取年度的外部对标数据
	 * @param year
	 * @param month
	 * @param orgID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/year", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getData(HttpServletRequest request,String orgID,String outorgID,int year)
	{
		
		// 权限相关信息取得
		HttpSession session = request.getSession();
		if(!Common.notEmpty(orgID))
			return null;
		BimfOutCompare Data= outcompareService.getYearData(orgID,outorgID,year);
		if(Data!=null)
		{
			String a=JSON.toJSONString(Data);
			return a;
		}
		return "";
	}
	
	/**
	 * 获取季度的外部对标数据
	 * @param year
	 * @param month
	 * @param orgID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/season", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getSeasonData(HttpServletRequest request,String orgID,String outorgID,int year,int season)
	{
		
		// 权限相关信息取得
		HttpSession session = request.getSession();
		if(!Common.notEmpty(orgID))
			return null;
		BimfOutCompare Data= outcompareService.getSeasonData(orgID,outorgID, year, season);
		if(Data!=null)
		{
			String a=JSON.toJSONString(Data);
			return a;
		}
		return "";
	}
	
	/**
	 * 企业代码
	 */
	
	@RequestMapping(value="outCompareCode")
	public String OutCompareCode(HttpServletRequest request,Map<String, Object> map,Integer year,Integer season){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		
		// 权限相关信息取得
		/*HttpSession session = request.getSession();
		//String orgID = (String) session.getAttribute("bimShow_financeorgID");
		String orgID = "d4985136e97d11e7968906a2160000ae";
		String upCaseSeason = "一";*/
		
		map.put("season", season);
		map.put("year", year);
		//String time=year+"-"+String.format("%02d", month );
		/*List<OutCompareCompany>Data=new ArrayList<OutCompareCompany>();
		Data = systemService.getOutCompareCompanyList(orgID,time, Base.financetype);
		map.put("Inselectorg", Data);
		if(Data!=null){
			String a=JSON.toJSONString(Data);
			map.put("inSelectData", a);
		}
		List<OutCompareCompany>OutData=new ArrayList<OutCompareCompany>();
		OutData = systemService.getAllOutCompareCompanyList();
		map.put("Outselectorg", OutData);
		if(Data!=null && Data.size()>0)
			map.put("selectedOut", Data.get(0).getOutorg());*/
		List<Object[]> OutCompareCodeList=outcompareService.getPublicCompanyStockList();
		map.put("OutCompareCodeList", OutCompareCodeList);
		return "/publicCompany/publicCompanyStock";
	}
	
	
}
