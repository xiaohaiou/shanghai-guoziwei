package com.softline.controller.bimr;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entity.bimr.BimrDuty;
import com.softline.service.bimr.IDutyService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * 合规培训Controller
 * @author pengguolin
 */
@Controller
@RequestMapping("/bimr/duty")
public class DutyController {
	@Resource(name = "dutyService") private IDutyService dutyService;
	@Resource(name = "systemService") private ISystemService systemService;
	@Resource(name = "baseService") private IBaseService baseService;
	@Resource(name = "examineService") private IExamineService examineService;
	@Resource(name = "commonService") private ICommonService commonService;
	@Resource(name = "selectUserService") private ISelectUserService selectUserService;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("/list")
	public String queryList(BimrDuty entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/bimr/duty";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = dutyService.getDutyList(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/bimr/duty/list");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
		return "/bimr/duty/list";
	}
	
	@RequestMapping("/examineList")
	public String examineList(BimrDuty entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/bimr/duty";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = dutyService.getExamineDutyList(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/bimr/duty/examineList");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getOtherBaseList("50112", Base.examstatustype);
	    map.put("reportStatus",reportStatus);
		return "/bimr/duty/examineList";
	}
	
	@RequestMapping("/addOrModify")
	public String addOrModify(BimrDuty entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
	    BimrDuty bimrDuty;
		if(entity.getId() != null) {
			bimrDuty = dutyService.getBimrDuty(entity);
		}else {
			bimrDuty = new BimrDuty();
		}
		map.put("bimrDuty", bimrDuty);
		HhFile hfile = commonService.getFileByEnIdAndType(entity.getId(), Base.DUTY_ENCLOSURE);
		if (hfile != null) {
			map.put("file_Path", hfile.getFilePath());
			map.put("file_Name", hfile.getFileName());
		} else {
			map.put("file_Path", "");
			map.put("file_Name", "");
		}
		return "/bimr/duty/addOrModify";
	}
	
	@RequestMapping("/view")
	public String view(BimrDuty entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		BimrDuty bimrDuty = dutyService.getBimrDuty(entity);
		map.put("bimrDuty", bimrDuty);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.DUTY_EXAMINE);
		map.put("examineList", examineList);
		HhFile hfile = commonService.getFileByEnIdAndType(entity.getId(), Base.DUTY_ENCLOSURE);
		if (hfile != null) {
			map.put("file_Path", hfile.getFilePath());
			map.put("file_Name", hfile.getFileName());
		} else {
			map.put("file_Path1", "");
			map.put("file_Name1", "");
		}
		return "/bimr/duty/view";
	}
	
	@RequestMapping("/examine")
	public String examine(BimrDuty entity, Map<String, Object> map, HttpServletRequest request) {
		BimrDuty bimrDuty = dutyService.getBimrDuty(entity);
		map.put("bimrDuty", bimrDuty);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.DUTY_EXAMINE);
		map.put("examineList", examineList);
		HhFile hfile = commonService.getFileByEnIdAndType(entity.getId(), Base.DUTY_ENCLOSURE);
		if (hfile != null) {
			map.put("file_Path", hfile.getFilePath());
			map.put("file_Name", hfile.getFileName());
		} else {
			map.put("file_Path", "");
			map.put("file_Name", "");
		}
		return "/bimr/duty/examine";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate")
	public String saveOrUpdate(@ModelAttribute("bimrDuty") BimrDuty entity, HttpServletRequest request,
			@RequestParam(value="file",required=false) MultipartFile multipartFile) throws IOException, ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			String package_path = DES.DUTY_PATH;
			dutyService.save(entity, user, multipartFile, package_path);
		}else {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			String package_path = DES.DUTY_PATH;
			dutyService.update(entity, user, multipartFile, package_path);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndReported(@ModelAttribute("bimrDuty") BimrDuty entity, HttpServletRequest request) throws IOException, ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setStatus(Base.examstatus_2);
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(user.getVcEmployeeId());
			entity.setReportPersonName(user.getVcName());
			dutyService.save(entity);
		}else {
			entity.setStatus(Base.examstatus_2);
			entity.setIsDel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(user.getVcEmployeeId());
			entity.setReportPersonName(user.getVcName());
			dutyService.update(entity);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/reported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reported(Integer id, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		BimrDuty bimrDuty = dutyService.getBimrDutyById(id);
		bimrDuty.setStatus(Base.examstatus_2);
		Date date = new Date();
		bimrDuty.setReportDate(df.format(date));
		bimrDuty.setReportPersonId(user.getVcEmployeeId());
		bimrDuty.setReportPersonName(user.getVcName());
		dutyService.update(bimrDuty);
		return "success";
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String deleteContract(String id, Map<String, Object> map, HttpServletRequest request) {
		String result = "";
		if (Common.notEmpty(id)) {
			BimrDuty bimrDuty = dutyService.getBimrDutyById(Integer.parseInt(id));
			if (bimrDuty != null) {
				if (bimrDuty.getStatus() == 50112 || bimrDuty.getStatus() == 50114) {
					bimrDuty.setIsDel(1);
					dutyService.update(bimrDuty);
					result = "success";
				} else {
					result = "false2";
				}
			} else {
				result = "false";
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/selectCompany", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void selectCompany(String id, PrintWriter print) throws IOException { 
		HhOrganInfo HhOrganInfo = systemService.getOrganInfoById(id);
		List<HhOrganInfo> companyList = systemService.getOrganInfos(HhOrganInfo.getVcOrganId());
		String jsonString = JSON.toJSONString(companyList);
		print.print(jsonString);
		print.flush();
		print.close();
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(String id, String op){
		String result = "";
		if (Common.notEmpty(id)) {
			BimrDuty entity = dutyService.getBimrDutyById(Integer.parseInt(id));
			if(entity == null){
				result = "false";
			} else {
				if (Common.notEmpty(op)) {
					if ("check".equals(op)) {//查看判断
						result = "success";
					} else if ("examine".equals(op)) {//审核判断
						if (entity.getStatus() == 50113) {
							result = "success";
						} else {
							result = "false2";
						}
					} else {
						if (entity.getStatus() == 50112 || entity.getStatus() == 50114) {//修改,上报判断
							result = "success";
						} else {
							result = "false2";
						}
					}
				} else {
					result = "false";
				} 
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String commitExamineContract(Integer entityid, String examStr, Integer examResult, HttpServletRequest request) throws IOException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entityid != null) {
			BimrDuty entity = dutyService.getBimrDutyById(entityid);
			if (entity != null) {
				if (entity.getStatus() == 50113) {
					dutyService.saveExamine(entity, examStr, examResult, user);
					result = "success";
				} else {
					result = "false2";
				}
			} else {
				result = "false";
			}
		} else {
			result = "false";
		}		
		return result;
	}
}
