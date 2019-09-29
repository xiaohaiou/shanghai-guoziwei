package com.softline.controller.bimShow;

import com.alibaba.fastjson.JSON;
import com.softline.service.bimshow.IBimShowIndustryCommerceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/industryCommerce")
public class BimShowIndustryCommerceController {

	@Resource(name = "bimShowIndustryCommerceService")
	private IBimShowIndustryCommerceService bimShowIndustryCommerceService;

	@ResponseBody
	@RequestMapping(value ="/getCommpanyList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getFinan_risk_top10(Map<String ,Object> map, HttpServletRequest request, HttpServletResponse response,
									 String year, String month,String company){
			String a = "";
			List<Object> data = bimShowIndustryCommerceService.getIndustryCommerceDaoList(year,month);
			a=JSON.toJSONString(data);
			return a;
	}
	
	@ResponseBody
	@RequestMapping(value ="/getIndustryCommerceCaliberData", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getIndustryCommerceCaliberData(Map<String ,Object> map, HttpServletRequest request, HttpServletResponse response,
												String year, String month,String company){
			String a = "";
			List<Object> data = bimShowIndustryCommerceService.getIndustryCommerceCaliberData(year,month,company);
			a=JSON.toJSONString(data);
			return a;
	}
	
	@ResponseBody
	@RequestMapping(value ="/getIndustryCommerceSupervisorData", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getIndustryCommerceSupervisorData(Map<String ,Object> map, HttpServletRequest request, HttpServletResponse response,
													String year, String month,String company){
			String a = "";
			List<Object> data = bimShowIndustryCommerceService.getIndustryCommerceSupervisorData(year,month,company);
			a=JSON.toJSONString(data);
			return a;
	}
	
	@ResponseBody
	@RequestMapping(value ="/getIndustryCommerceShareInformation", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getIndustryCommerceShareInformation(Map<String ,Object> map, HttpServletRequest request, HttpServletResponse response,
													String year, String month,String company){
			String a = "";
			List<Object> data = bimShowIndustryCommerceService.getIndustryCommerceShareInformation(year,month,company);
			a=JSON.toJSONString(data);
			return a;
	}
	
	
}
