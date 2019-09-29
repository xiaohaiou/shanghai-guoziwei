package com.softline.controller.riskcontrol;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.RiskControlCase;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.RiskControlContract;
import com.softline.entity.SysExamine;
import com.softline.service.riskcontrol.IRiskControlCaseService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/riskcontrol/case")
public class RiskControlCaseController {
	
	@Resource(name = "riskControlCaseService")
	private IRiskControlCaseService riskControlCaseService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	DecimalFormat df2 = new DecimalFormat("###0.0000"); 

	@RequestMapping("/list")
	public String queryCaseList(RiskControlCase entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/riskcontrol/case";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
		String caseHappenDate2 = request.getParameter("caseHappenDate2");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = riskControlCaseService.getCases(entity,pageNum,Base.pagesize,caseHappenDate2,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/riskcontrol/case/list");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
	    List<HhOrganInfo> auditDepts = systemService.getOrganInfos(Base.HRTopPrefix);
		map.put("auditDepts", auditDepts);
		List<HhBase> caseType = baseService.getBases(Base.case_type);
	    map.put("caseType",caseType);
	    map.put("caseHappenDate2", caseHappenDate2);
		return "/riskcontrol/case/caseList";
	}
	
	@RequestMapping("/examinelist")
	public String queryExamineCaseList(RiskControlCase entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/riskcontrol/case";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
		String caseHappenDate2 = request.getParameter("caseHappenDate2");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = riskControlCaseService.getExamineCases(entity,pageNum,Base.pagesize,caseHappenDate2,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/riskcontrol/case/examinelist");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
	    List<HhOrganInfo> auditDepts = systemService.getOrganInfos(Base.HRTopPrefix);
		map.put("auditDepts", auditDepts);
		List<HhBase> caseType = baseService.getBases(Base.case_type);
	    map.put("caseType",caseType);
	    map.put("caseHappenDate2", caseHappenDate2);
		return "/riskcontrol/case/caseExamineList";
	}
	
	@RequestMapping("/addOrModify")
	public String addOrModifyCase(RiskControlCase entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		map.put("op", op);
		List<HhOrganInfo> auditDepts = systemService.getOrganInfos(Base.HRTopPrefix);
		map.put("auditDepts", auditDepts);
		List<HhBase> auditType = baseService.getBases(Base.audit_type);
	    map.put("auditType",auditType);
	    List<HhBase> caseType = baseService.getBases(Base.case_type);
	    map.put("caseType",caseType);
	    List<HhBase> currentState = baseService.getBases(Base.case_status);
	    map.put("currentState",currentState);
		RiskControlCase riskControlCase;
		if(entity.getId() != null) {
			riskControlCase = riskControlCaseService.getCase(entity);
		}else {
			riskControlCase = new RiskControlCase();
		}
		map.put("riskControlCase", riskControlCase);
		return "/riskcontrol/case/caseAddOrModify";
	}
	
	@RequestMapping("/view")
	public String viewCase(RiskControlCase entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		RiskControlCase riskControlCase = riskControlCaseService.getCase(entity);
		map.put("riskControlCase", riskControlCase);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskcontrol_case);
		map.put("examineList", examineList);
		return "/riskcontrol/case/caseView";
	}
	
	@ResponseBody
	@RequestMapping(value ="/reported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportedCase(Integer id, HttpServletRequest request) throws IOException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		RiskControlCase riskControlCase = riskControlCaseService.getCase(id);
		riskControlCase.setStatus(Base.examstatus_2);
		Date date = new Date();
		riskControlCase.setReportDate(df.format(date));
		riskControlCase.setReportPersonId(user.getVcEmployeeId());
		riskControlCase.setReportPersonName(user.getVcName());
		riskControlCaseService.updateCase(riskControlCase);
		result = "success";
		return result;
	}
	
	@RequestMapping("/examine")
	public String examineCase(RiskControlCase entity, Map<String, Object> map, HttpServletRequest request) {
		RiskControlCase riskControlCase = riskControlCaseService.getCase(entity);
		map.put("riskControlCase", riskControlCase);
		//审批时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskcontrol_case);
		map.put("examineList", examineList);
		return "/riskcontrol/case/caseExamine";
	}
	
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String commitExamineCase(Integer entityid, String examStr, Integer examResult, HttpServletRequest request) throws IOException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entityid != null) {
			RiskControlCase entity = riskControlCaseService.getCase(entityid);
			if (entity != null) {
				if (entity.getStatus() == 50113) {
					riskControlCaseService.saveCaseExamine(entityid, examStr, examResult, user);
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
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateCase(@ModelAttribute("riskControlCase")RiskControlCase entity, HttpServletRequest request) throws IOException, ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			if (Common.notEmpty(entity.getLitigationAmountMoney())) {
				entity.setLitigationAmountMoney(df2.format(Double.parseDouble(entity.getLitigationAmountMoney())));
			}
			riskControlCaseService.saveCase(entity);
		}else {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			if (Common.notEmpty(entity.getLitigationAmountMoney())) {
				entity.setLitigationAmountMoney(df2.format(Double.parseDouble(entity.getLitigationAmountMoney())));
			}
			riskControlCaseService.updateCase(entity);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndRepotedCase(@ModelAttribute("riskControlCase")RiskControlCase entity, HttpServletRequest request) throws IOException, ParseException {
		String result = "";
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
			riskControlCaseService.saveCase(entity);
			result = "success";
		}else {
			entity.setStatus(Base.examstatus_2);
			entity.setIsDel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(user.getVcEmployeeId());
			entity.setReportPersonName(user.getVcName());
			riskControlCaseService.updateCase(entity);
			result = "success";
		}
		return result;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String deleteCase(String id, Map<String, Object> map, HttpServletRequest request) {
		String result = "";
		if (Common.notEmpty(id)) {
			RiskControlCase riskControlCase = riskControlCaseService.getCase(Integer.parseInt(id));
			if (riskControlCase != null) {
				if (riskControlCase.getStatus() == 50112 || riskControlCase.getStatus() == 50114) {
					riskControlCase.setIsDel(1);
					riskControlCaseService.updateCase(riskControlCase);
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
			RiskControlCase entity = riskControlCaseService.getCase(Integer.parseInt(id));
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
	
}
