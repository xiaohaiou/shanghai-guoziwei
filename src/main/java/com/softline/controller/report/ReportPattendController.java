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
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsGroup;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.Report_formsView;
import com.softline.entityTemp.Report_forms_groupView;
import com.softline.service.report.IReportService;
import com.softline.service.system.IBaseService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportpattend")
public class ReportPattendController {

	@Resource(name = "reportService")
	private IReportService reportService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	
	@RequestMapping("/list")
	public String reportpattendlist(Report_formsView entityview,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=reportService.getReportformsView(entityview,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportpattend/list");
	    map.put("entityview", entityview);
	    addData(map);
		return "/report/reportpattend/reportPattendList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> reportgroupkind= baseService.getBases(Base.reportgroupkind);
		List<HhBase> reportformkind= baseService.getBases(Base.reportformkind);
		List<HhBase> reportformfre= baseService.getBases(Base.reportformfre);
		map.put("reportgroup",reportgroupkind);
		map.put("reportformkind",reportformkind);
		map.put("reportformfre", reportformfre);
	}
	
	@RequestMapping("/newmodify")
	public String reportpattendNewModify(Integer id, HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
	    ReportForms	entity=new ReportForms();
	    entity.setId(id);
	    entity= reportService.getReportforms(entity);
	    
	    ReportFormsGroup group=new ReportFormsGroup();
	    group.setId(entity.getGroupId());
	    group=  reportService.getReportFormsGroup(group);
	    
	    map.put("entity", entity);
	    map.put("group", group);
		map.put("formID", id);
		return "/report/reportpattend/reportPattendNewModify";
	}
	
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String pattend(Integer formID,@RequestParam("pattendFile") MultipartFile pattendFile,@RequestParam("outFile") MultipartFile outFile,HttpServletRequest request) throws Exception {
		    HttpSession session= request.getSession();
		    CommitResult returnData =new CommitResult();
		    if(pattendFile!=null && outFile!=null)
		    {   	
			    HhUsers use= (HhUsers) session.getAttribute("gzwsession_users");
			    returnData=reportService.saveReportPattend( formID,use,pattendFile,outFile);
		    }
		    else
		    {
		    	returnData.setResult(false);
		    	returnData.setExceptionStr("请上传样式文件和导出文件");
		    }
		    String data="";
			data=JSONArray.toJSONString(returnData); 			
			return data;
	}
}
