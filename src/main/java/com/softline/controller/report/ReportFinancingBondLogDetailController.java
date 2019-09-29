package com.softline.controller.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.softline.entity.financing.ReportFinancingBondLog;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportFinancingBondLogDetail")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFinancingBondLogDetailController {

	//跳转查看页面
	@RequestMapping("/viewList")
	public String _view(String stringList, Integer pageNums,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		List<ReportFinancingBondLog> list = new ArrayList<ReportFinancingBondLog>();
		if(stringList != null && !"".equals(stringList))
			list=JSON.parseArray(stringList, ReportFinancingBondLog.class);
        //根据list，设置分页
		MsgPage msgPage=new MsgPage();
    	msgPage.setPageSize(10);
    	msgPage.setTotalRecords(list.size());
    	msgPage.setTotalPage(msgPage.countTotalPage(list.size(), 10));
		List<ReportFinancingBondLog> newList = new ArrayList<ReportFinancingBondLog>();
    	if(pageNums < msgPage.getTotalPage()) {
    		int size = (pageNums - 1)*10+10;
    		for(int i = (pageNums - 1)*10; i < size; i++) {
    			if(list != null && list.size() !=0)
    				newList.add(list.get(i));
			}
    	}else {
    		for(int i = (pageNums - 1)*10; i < list.size(); i++) {
    			if(list != null && list.size() !=0)
    				newList.add(list.get(i));
    		}
    	}
    	msgPage.setPageNum(pageNums);
    	msgPage.setList(newList);
    	map.put("sizeNum", newList.size());
	    map.put("msgPage", msgPage);
	    //控制分页
	    map.put("flag", "reportFinancingBondLogDetail");
	    map.put("searchurl", "/shanghai-gzw/reportFinancingBondLogDetail/viewList");
		return "/report/reportFinancingBond/reportFinancingBondLogDetailView/reportFinancingBondLogDetailViewList";
	}
	
}
