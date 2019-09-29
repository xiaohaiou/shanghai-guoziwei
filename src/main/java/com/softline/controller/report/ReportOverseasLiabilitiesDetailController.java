package com.softline.controller.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.entity.HhBase;
import com.softline.entity.ReportOverseasLiabilitiesDetail;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportOverseasLiabilitiesDetail")
/**
 * 
 * @author tch
 *
 */
public class ReportOverseasLiabilitiesDetailController {

	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	//维护的列表页面
	@RequestMapping(value ="/list", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _list(String stringList, Integer pageNums, String op, HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("companyTrees", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		
		List<ReportOverseasLiabilitiesDetail> list = new ArrayList<ReportOverseasLiabilitiesDetail>();
		if(stringList != null && !"".equals(stringList))
			list=JSON.parseArray(stringList, ReportOverseasLiabilitiesDetail.class);
        //根据list，设置分页
		MsgPage msgPage=new MsgPage();
    	msgPage.setPageSize(5);
    	msgPage.setTotalRecords(list.size());
    	msgPage.setTotalPage(msgPage.countTotalPage(list.size(), 5));
		if(op.equals("add")) {
			pageNums = msgPage.getTotalPage();
		}
		if(op.equals("del")) {
			pageNums = 1;
		}
		List<ReportOverseasLiabilitiesDetail> newList = new ArrayList<ReportOverseasLiabilitiesDetail>();
    	if(pageNums < msgPage.getTotalPage()) {
    		int size = (pageNums - 1)*5+5;
    		for(int i = (pageNums - 1)*5; i < size; i++) {
    			if(list != null && list.size()!=0)
    				newList.add(list.get(i));
			}
    	}else {
    		for(int i = (pageNums - 1)*5; i < list.size(); i++) {
    			if(list != null && list.size()!=0)
    				newList.add(list.get(i));
    		}
    	}
    	msgPage.setPageNum(pageNums);
    	msgPage.setList(newList);
    	map.put("sizeNum", newList.size());
	    map.put("msgPage", msgPage);
	    //控制分页
	    map.put("flag", "reportOverseasLiabilitiesDetail");
	    map.put("searchurl", "/shanghai-gzw/reportOverseasLiabilitiesDetail/list");
		addData(map);
		return "/report/reportStockLiabilities/reportOverseasLiabilitiesDetail/reportOverseasLiabilitiesDetailList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/viewList")
	public String _view(String stringList, Integer pageNums,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<ReportOverseasLiabilitiesDetail> list = new ArrayList<ReportOverseasLiabilitiesDetail>();
		if(stringList != null && !"".equals(stringList))
			list=JSON.parseArray(stringList, ReportOverseasLiabilitiesDetail.class);
        //根据list，设置分页
		MsgPage msgPage=new MsgPage();
    	msgPage.setPageSize(5);
    	msgPage.setTotalRecords(list.size());
    	msgPage.setTotalPage(msgPage.countTotalPage(list.size(), 5));
		List<ReportOverseasLiabilitiesDetail> newList = new ArrayList<ReportOverseasLiabilitiesDetail>();
    	if(pageNums < msgPage.getTotalPage()) {
    		int size = (pageNums - 1)*5+5;
    		for(int i = (pageNums - 1)*5; i < size; i++) {
    			if(list != null && list.size()!=0)
    				newList.add(list.get(i));
			}
    	}else {
    		for(int i = (pageNums - 1)*5; i < list.size(); i++) {
    			if(list != null && list.size()!=0)
    				newList.add(list.get(i));
    		}
    	}
    	msgPage.setPageNum(pageNums);
    	msgPage.setList(newList);
    	map.put("sizeNum", newList.size());
	    map.put("msgPage", msgPage);
	    //控制分页
	    map.put("flag", "reportOverseasLiabilitiesDetail");
	    map.put("searchurl", "/shanghai-gzw/reportOverseasLiabilitiesDetail/viewList");
		return "/report/reportStockLiabilities/reportOverseasLiabilitiesDetail/reportOverseasLiabilitiesDetailViewList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> currencyKind= baseService.getBases(Base.currencyKind);
		map.put("currencyKind",currencyKind);
		
	}
	
}
