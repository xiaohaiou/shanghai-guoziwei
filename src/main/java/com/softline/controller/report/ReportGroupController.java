package com.softline.controller.report;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mortbay.util.ajax.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.system.IBaseDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsGroup;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.Report_forms_groupView;
import com.softline.service.report.IReportService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportgroup")
public class ReportGroupController {

	@Resource(name = "reportService")
	private IReportService reportService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@RequestMapping("/list")
	public String reportgrouplist(ReportFormsGroup entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportgroup";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        List<HhBase> reportgroupkind= baseService.getBases(Base.reportgroupkind);
    	map.put("reportgroupkind",reportgroupkind);
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=reportService.getReportformsgroupView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportgroup/list");
	    map.put("entityview", entity);
		return "/report/reportgroup/reportgroupList";
	}
	
	@RequestMapping("/newmodify")
	public String reportgroupNewModify(ReportFormsGroup entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		Report_forms_groupView entityview;
		if(entity.getId()==null)
			entityview=new Report_forms_groupView();
		else
			entityview=reportService.getReport_forms_groupView(entity);
		map.put("entityview", entityview);	
		addData(map);
		return "/report/reportgroup/reportgroupNewModify";
	}
	
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportgroupsave(Report_forms_groupView entity,HttpServletRequest request ,Map<String, Object> map,String op,MultipartFile reportGroupFile,MultipartFile reportGroupExportFile) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		if(Common.notEmpty(entity.getGroup_itemstr()))
			entity.setGroup_item(com.alibaba.fastjson.JSON.parseArray(entity.getGroup_itemstr(), ReportForms.class));
		CommitResult result= reportService.saveReportFormsGroup(entity, use,reportGroupFile,reportGroupExportFile);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	

	@RequestMapping(value ="/view")
	public String view(ReportFormsGroup entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		
		Report_forms_groupView entityview= reportService.getReport_forms_groupView(entity);
		map.put("entityview", entityview);	
		return "/report/reportgroup/reportgroupView";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> reportgroupkind= baseService.getBases(Base.reportgroupkind);
		List<HhBase> reportformkind= baseService.getBases(Base.reportformkind);
		List<HhBase> reportformfre= baseService.getBases(Base.reportformfre);
		List<HhBase> reportgrouptype=baseService.getBases(Base.reportgroup_type);
		map.put("reportgroupkind",reportgroupkind);
		map.put("reportformkind",reportformkind);
		map.put("reportformfre", reportformfre);
		map.put("reportgrouptype", reportgrouptype);
	}
	@ResponseBody
	@RequestMapping("/delete")
	public String reportgroupdelete(ReportFormsGroup entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=reportService.deleteReportFormsGroup(entity.getId(), use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	

}
