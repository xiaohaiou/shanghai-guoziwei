package com.softline.controller.appCenter;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softline.entity.BimCenterCompany;
import com.softline.service.bimCenter.IBimCenterService;

@Controller
@RequestMapping("/appCenter")
public class AppcenterController {
	
	@Resource(name="bimCenterService")
	private IBimCenterService bimCenterService;
	
	@RequestMapping("/getAppCenterPart")
	public String getAppCenterData(HttpServletRequest request,Map<String, Object> map){
		
		List<BimCenterCompany> results = bimCenterService.getAllCompanys(request);
		map.put("companys", results);
		return "workbench/appCenterPart";
	}
	
}
