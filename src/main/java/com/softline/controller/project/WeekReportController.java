package com.softline.controller.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjProjectHistory;
import com.softline.entity.project.PjWeekreport;
import com.softline.entity.project.PjWeekreportHistory;
import com.softline.entity.project.PjWeekreportTemp;
import com.softline.entityTemp.ProjectWeekReportESBEntity;
import com.softline.service.project.IWkReportService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/project/wkReport")
public class WeekReportController {
	@Resource(name="commonService")
	private ICommonService commonService;
	
	@Resource(name="wkReportService")
	private IWkReportService wkReportService;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("_addWkReport")
	public String _addWkReport(Integer pjId,Map<String ,Object> map, HttpServletRequest request){
		if(pjId != null){
			PjProject project = (PjProject) commonService.findById(PjProject.class, pjId);
			map.put("project", project);
		}
		return "/project/wkReportEdit";
	}
	
	@RequestMapping("_modifyWkReportTemp")
	public String _modifyWkReportTemp(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjWeekreportTemp wkReport = (PjWeekreportTemp) commonService.findById(PjWeekreportTemp.class, id);
		if(wkReport.getPjId() != null){
			PjProject project = (PjProject) commonService.findById(PjProject.class, wkReport.getPjId());
			map.put("project", project);
		}
		HhFile file = commonService.getFileByEnIdAndType(wkReport.getId(), Base.PROJECT_WK_PDF_TEMP);
		wkReport.setWkPDFFile(file);
		map.put("wkReport", wkReport);
		return "/project/wkReportEdit";
	}
	
	@RequestMapping("_modifyWkReport")
	public String _modifyWkReport(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjWeekreport wkReport = (PjWeekreport) commonService.findById(PjWeekreport.class, id);
		if(wkReport.getPjId() != null){
			PjProject project = (PjProject) commonService.findById(PjProject.class, wkReport.getPjId());
			map.put("project", project);
		}
		HhFile file = commonService.getFileByEnIdAndType(wkReport.getId(), Base.PROJECT_WK_PDF_TEMP);
		wkReport.setWkPDFFile(file);
		map.put("wkReport", wkReport);
		return "/project/wkReportEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/_saveWkReportTemp" ,method=RequestMethod.POST,produces = "application/text; charset=utf-8"	)
	public String _saveWkReportTemp(PjWeekreportTemp wkReportTemp,Map<String ,Object> map, HttpServletRequest request,@RequestParam(value="wrFile",required=false) MultipartFile file){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		String result="";
		if(wkReportTemp.getId()!=null){
			PjWeekreportTemp entity = (PjWeekreportTemp) commonService.findById(PjWeekreportTemp.class, wkReportTemp.getId());
			result = wkReportService.saveWkReportTemp(user, wkReportTemp, file,entity);
		}else{
			result = wkReportService.saveWkReportTemp(user, wkReportTemp, file,null);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/_delWkReportTemp")
	public String _delWkReportTemp(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjWeekreportTemp wkReportTemp = (PjWeekreportTemp) commonService.findById(PjWeekreportTemp.class, id);
		commonService.delete(wkReportTemp);
		return "success";
	}
	@RequestMapping("/_wkReportList")
	public String _wkReportList(Integer pjId,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  wkReportService.getWkReports(pjId, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("pjId", pjId);
		//分页
		map.put("flag","wkReport");
		map.put("searchurl", request.getContextPath()+"/project/wkReport/_wkReportList");
		
		return "project/wkReportList";
	}
	
	@ResponseBody
	@RequestMapping(value="/_saveWkReport" ,method=RequestMethod.POST,produces = "application/text; charset=utf-8"	)
	public String _saveWkReport(PjWeekreport wkReport,Map<String ,Object> map, HttpServletRequest request,@RequestParam(value="wrFile",required=false) MultipartFile file){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		String result="";
		if(wkReport.getId()!=null){
			PjWeekreport entity = (PjWeekreport) commonService.findById(PjWeekreport.class, wkReport.getId());
			wkReportService.saveWkReport(user, wkReport, file,entity);
		}else{
			wkReportService.saveWkReport(user, wkReport, file,null);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("_delWkReport")
	public String _delWkReport(Integer id,Map<String ,Object> map, HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
//		PjWeekreport  weekreport = (PjWeekreport) commonService.findById(PjWeekreport.class, id);
//		weekreport.setIsdel(1);
//		weekreport.setLastModifyDate(df.format(new Date()));
//		weekreport.setLastModifyPersonId(user.getVcEmployeeId());
//		weekreport.setLastModifyPersonName(user.getVcName());
//		commonService.saveOrUpdate(weekreport);
		wkReportService.deleteWkReport(id, user);
		return "success";
	}
	
	@RequestMapping("/_wkReportViewList")
	public String _wkReportViewList(Integer pjId,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  wkReportService.getWkReports(pjId, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("pjId", pjId);
		String view = request.getParameter("view");
		map.put("view", view);
		//分页
		map.put("flag","wkReport");
		map.put("searchurl", request.getContextPath()+"/project/wkReport/_wkReportViewList");
		
		return "project/wkReportViewList";
	}
	
	@RequestMapping("_WkReportView")
	public String _WkReportView(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjWeekreport wkReport = (PjWeekreport) commonService.findById(PjWeekreport.class, id);
		if(wkReport.getPjId() != null){
			PjProject project = (PjProject) commonService.findById(PjProject.class, wkReport.getPjId());
			map.put("project", project);
		}
		HhFile file = commonService.getFileByEnIdAndType(wkReport.getId(), Base.PROJECT_WK_PDF_TEMP);
		wkReport.setWkPDFFile(file);
		map.put("wkReport", wkReport);
		//上报审核历史
		List<PjWeekreportHistory> histories = wkReportService.getPjWeekreportHistories(wkReport.getId());
		map.put("histories",histories);
		
		return "/project/wkReportView";
	}
	
	@ResponseBody
	@RequestMapping(value="/_validate" ,method=RequestMethod.POST,produces = "application/text; charset=utf-8"	)
	public String _validate(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjWeekreport weekReport = (PjWeekreport) commonService.findById(PjWeekreport.class, id);
		String a = (String) JSON.toJSONString(weekReport);
		return a;
	}
	
	/**
	 * 
	 * 工程进度   thisProgress
	 * 工程质量  thisQuality
	 * 工程安全  thisSHE
	 * 招标采购   thisPurchase
	 * @param year
	 * @param week
	 * @param startDate
	 * @param endDate
	 * @param Pid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/weekESBInfo" ,method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	public String getWeekESBInfo(String wrYear, String wrWeek,String Pid,HttpServletRequest request ){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		ProjectWeekReportESBEntity entity =  wkReportService.getWeekReportSubInfo(wrYear, wrWeek,Pid, user);
		return entity.toString();
	}
}
