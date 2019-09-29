package com.softline.controller.queryPage.administration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.AdImportant;
import com.softline.entity.HhBase;
import com.softline.entity.SysExamine;
import com.softline.service.administration.IAdImportantService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/adImportant")
public class QueryPageAdImportantController {
	
	@Resource(name = "adImportantService")
	private IAdImportantService	adImportantService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	
	@RequestMapping("query")
	public String queryImportantList(AdImportant entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        if(!Common.notEmpty(entity.getImportantCompId()))
        	entity.setImportantCompId(dataauth);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adImportantService.getImportantView(entity,pageNum,Base.pagesize,Base.fun_search);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/queryPage/adImportant/query");
	    map.put("entity", entity);
	    addData(map);
		return "/queryPage/administration/importantList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> reportStatus= baseService.getBases(Base.examstatustype);
		List<HhBase> importantTypes = baseService.getBases(Base.important_type);
		map.put("reportStatus",reportStatus);
		map.put("importantTypes", importantTypes);
	}

	
	@RequestMapping("view")
	public String viewImportant(AdImportant entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		AdImportant theEntity=adImportantService.getImportant(entity);
		map.put("theEntity", theEntity);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_important);
		map.put("examineList", examineList);
		return "/queryPage/administration/importantView";
	}
	
}
