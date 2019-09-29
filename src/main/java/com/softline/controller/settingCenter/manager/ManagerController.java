 package com.softline.controller.settingCenter.manager;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softline.entity.HhUsers;


/**
 * 
 * @author wyh 
 * 
 * */
@Controller
@RequestMapping("/settingCenter/manager")
public class ManagerController {
	
	@RequestMapping(value = "/_frame")
	public String _frame(Map<String, Object> map, HttpServletRequest request) { 
		return "settingCenter/mager/manager";
	}
}
