package com.softline.controller.queryPage.administration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.AdSupervise;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.service.administration.IAdSuperviseService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/adSupervise")
public class QueryPageAdSuperviseController {
	
	@Resource(name = "adSuperviseService")
	private IAdSuperviseService	adSuperviseService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@RequestMapping("query")
	public String querySuperviseList(AdSupervise entity, String organalID, Map<String, Object> map, HttpServletRequest request,String audiorDateStart,
			String audiorDateEnd, String reportDateStart, String reportDateEnd) throws IOException {
		
		if(audiorDateStart!=null){
			request.setAttribute("audiorDateStart", audiorDateStart);	
		}
		if(audiorDateEnd!=null){	
			request.setAttribute("audiorDateEnd", audiorDateEnd);	
		}
		
		if(audiorDateStart!=null){
			request.setAttribute("reportDateStart", reportDateStart);	
		}
		if(audiorDateEnd!=null){	
			request.setAttribute("reportDateEnd", reportDateEnd);	
		}
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
        if(!Common.notEmpty(entity.getCompId()))
        	entity.setCompId(dataauth);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adSuperviseService.getSuperviseView(entity,pageNum,Base.pagesize,Base.fun_search,audiorDateStart,audiorDateEnd,reportDateStart,reportDateEnd);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/queryPage/adSupervise/query");
	    map.put("entity", entity);
	    addData(map);
	    if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/queryPage/administration/superviseList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> reportStatus= baseService.getBases(Base.examstatustype);
		map.put("reportStatus",reportStatus);
	}
	
	@RequestMapping("view")
	public String viewSupervise(AdSupervise entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		AdSupervise theEntity=adSuperviseService.getSupervise(entity);
		map.put("theEntity", theEntity);
		//查看时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_supervise);
		map.put("examineList", examineList);
		return "/queryPage/administration/superviseView";
	}
	
	
}
