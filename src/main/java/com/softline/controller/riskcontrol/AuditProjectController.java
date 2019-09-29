package com.softline.controller.riskcontrol;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.AuditProject;
import com.softline.entity.AuditProjectAbarbeitungQuestion;
import com.softline.entity.AuditProjectFindQuestion;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.RiskControlAccount;
import com.softline.entity.RiskControlCase;
import com.softline.entity.SysExamine;
import com.softline.service.riskcontrol.IAuditProjectAbarbeitungQuestionService;
import com.softline.service.riskcontrol.IAuditProjectFindQuestionService;
import com.softline.service.riskcontrol.IAuditProjectService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/riskcontrol/auditProject")
public class AuditProjectController {
	
	@Resource(name = "auditProjectService")
	private IAuditProjectService auditProjectService;
	
	@Resource(name = "auditProjectFindQuestionService")
	private IAuditProjectFindQuestionService auditProjectFindQuestionService;
	
	@Resource(name = "auditProjectAbarbeitungQuestionService")
	private IAuditProjectAbarbeitungQuestionService auditProjectAbarbeitungQuestionService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	DecimalFormat df2 = new DecimalFormat("###0.0000");

	@RequestMapping("/list")
	public String queryAuditProjectList(AuditProject entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/riskcontrol/auditProject";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str = (String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = auditProjectService.getAuditProjects(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/riskcontrol/auditProject/list");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
		List<HhBase> auditType = baseService.getBases(Base.audit_type);
	    map.put("auditType",auditType);
		return "/riskcontrol/auditProject/auditProjectList";
	}
	
	@RequestMapping("/examinelist")
	public String queryExamineAuditProjectList(AuditProject entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/riskcontrol/auditProject";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str = (String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = auditProjectService.getExamineAuditProjects(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/riskcontrol/auditProject/examinelist");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
		List<HhBase> auditType = baseService.getBases(Base.audit_type);
	    map.put("auditType",auditType);
		return "/riskcontrol/auditProject/auditProjectExamineList";
	}
	
	@RequestMapping("/addOrModify")
	public String addOrModifyAuditProject(AuditProject entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		map.put("op", op);
		HttpSession session = request.getSession();
		String str = (String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
		List<HhBase> auditType = baseService.getBases(Base.audit_type);
	    map.put("auditType",auditType);
	    List<HhBase> auditType2 = baseService.getBases(Base.audit_type2);
	    map.put("auditType2",auditType2);
		AuditProject auditProject;
		Integer findQuestionNum;
		Integer abarbeitungQuestionNum;
		if(entity.getId() != null) {
			auditProject = auditProjectService.getAuditProject(entity);
			AuditProjectFindQuestion AuditProjectFindQuestion = auditProjectFindQuestionService.getAuditProjectFindQuestionByAuditProjectId(auditProject.getId());
			auditProject.setAuditProjectFindQuestion(AuditProjectFindQuestion);
			findQuestionNum = (AuditProjectFindQuestion.getCorporateStrategyMakeAndExecute() + AuditProjectFindQuestion.getFinancialControl()
					+ AuditProjectFindQuestion.getHumanResourceManagement() + AuditProjectFindQuestion.getPurchaseManagement()
					+ AuditProjectFindQuestion.getInfrastructure() + AuditProjectFindQuestion.getProjectInvestment()
					+ AuditProjectFindQuestion.getProductionOrganizationAndSales() + AuditProjectFindQuestion.getAdministrativeAffairs()
					+ AuditProjectFindQuestion.getExternalEnvironmentAndCompetition() + AuditProjectFindQuestion.getOthers());
			map.put("findQuestionNum", findQuestionNum);
			List<AuditProjectAbarbeitungQuestion> abarbeitungQuestionList = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestionByAuditProjectId(auditProject.getId());
			auditProject.setAuditProjectAbarbeitungQuestionList(abarbeitungQuestionList);
			abarbeitungQuestionNum = abarbeitungQuestionList.size();
			map.put("abarbeitungQuestionNum", abarbeitungQuestionNum);
		}else {
			auditProject = new AuditProject();
		}
		map.put("auditProject", auditProject);
		return "/riskcontrol/auditProject/auditProjectAddOrModify";
	}
	
	@RequestMapping("/view")
	public String viewAuditProject(AuditProject entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		AuditProject auditProject = auditProjectService.getAuditProject(entity);
		Integer findQuestionNum;
		Integer abarbeitungQuestionNum;
		AuditProjectFindQuestion AuditProjectFindQuestion = auditProjectFindQuestionService.getAuditProjectFindQuestionByAuditProjectId(auditProject.getId());
		if (AuditProjectFindQuestion != null) {
			auditProject.setAuditProjectFindQuestion(AuditProjectFindQuestion);
			findQuestionNum = (AuditProjectFindQuestion.getCorporateStrategyMakeAndExecute() + AuditProjectFindQuestion.getFinancialControl()
					+ AuditProjectFindQuestion.getHumanResourceManagement() + AuditProjectFindQuestion.getPurchaseManagement()
					+ AuditProjectFindQuestion.getInfrastructure() + AuditProjectFindQuestion.getProjectInvestment()
					+ AuditProjectFindQuestion.getProductionOrganizationAndSales() + AuditProjectFindQuestion.getAdministrativeAffairs()
					+ AuditProjectFindQuestion.getExternalEnvironmentAndCompetition() + AuditProjectFindQuestion.getOthers());
			map.put("findQuestionNum", findQuestionNum);
		} else {
			map.put("findQuestionNum", 0);
		}
		List<AuditProjectAbarbeitungQuestion> abarbeitungQuestionList = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestionByAuditProjectId(auditProject.getId());
		if (abarbeitungQuestionList != null) {
			auditProject.setAuditProjectAbarbeitungQuestionList(abarbeitungQuestionList);
			abarbeitungQuestionNum = abarbeitungQuestionList.size();
			map.put("abarbeitungQuestionNum", abarbeitungQuestionNum);
		} else {
			map.put("abarbeitungQuestionNum", 0);
		}
		map.put("auditProject", auditProject);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskcontrol_auditProject);
		map.put("examineList", examineList);
		return "/riskcontrol/auditProject/auditProjectView";
	}
	
	@ResponseBody
	@RequestMapping(value ="/reported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportedAuditProject(Integer id, HttpServletRequest request) throws IOException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		AuditProject auditProject = auditProjectService.getAuditProject(id);
		auditProject.setStatus(Base.examstatus_2);
		Date date = new Date();
		auditProject.setReportDate(df.format(date));
		auditProject.setReportPersonId(user.getVcEmployeeId());
		auditProject.setReportPersonName(user.getVcName());
		auditProjectService.updateAuditProject(auditProject);
		result = "success";
		return result;
	}
	
	@RequestMapping("/examine")
	public String examineAuditProject(AuditProject entity, Map<String, Object> map, HttpServletRequest request) {
		AuditProject auditProject = auditProjectService.getAuditProject(entity);
		Integer findQuestionNum;
		Integer abarbeitungQuestionNum;
		AuditProjectFindQuestion AuditProjectFindQuestion = auditProjectFindQuestionService.getAuditProjectFindQuestionByAuditProjectId(auditProject.getId());
		if (AuditProjectFindQuestion != null) {
			auditProject.setAuditProjectFindQuestion(AuditProjectFindQuestion);
			findQuestionNum = (AuditProjectFindQuestion.getCorporateStrategyMakeAndExecute() + AuditProjectFindQuestion.getFinancialControl()
					+ AuditProjectFindQuestion.getHumanResourceManagement() + AuditProjectFindQuestion.getPurchaseManagement()
					+ AuditProjectFindQuestion.getInfrastructure() + AuditProjectFindQuestion.getProjectInvestment()
					+ AuditProjectFindQuestion.getProductionOrganizationAndSales() + AuditProjectFindQuestion.getAdministrativeAffairs()
					+ AuditProjectFindQuestion.getExternalEnvironmentAndCompetition() + AuditProjectFindQuestion.getOthers());
			map.put("findQuestionNum", findQuestionNum);
		} else {
			map.put("findQuestionNum", 0);
		}
		List<AuditProjectAbarbeitungQuestion> abarbeitungQuestionList = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestionByAuditProjectId(auditProject.getId());
		if (abarbeitungQuestionList != null) {
			auditProject.setAuditProjectAbarbeitungQuestionList(abarbeitungQuestionList);
			abarbeitungQuestionNum = abarbeitungQuestionList.size();
			map.put("abarbeitungQuestionNum", abarbeitungQuestionNum);
		} else {
			map.put("abarbeitungQuestionNum", 0);
		}
		map.put("auditProject", auditProject);
		//审批时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskcontrol_auditProject);
		map.put("examineList", examineList);
		return "/riskcontrol/auditProject/auditProjectExamine";
	}
	
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String commitExamineAuditProject(Integer entityid, String examStr, Integer examResult, HttpServletRequest request) throws IOException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entityid != null) {
			AuditProject entity = auditProjectService.getAuditProject(entityid);
			if (entity != null) {
				if (entity.getStatus() == 50113) {
					auditProjectService.saveAuditProjectExamine(entityid, examStr, examResult, user);	
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
	public String saveOrUpdateAuditProject(@ModelAttribute("auditProject")AuditProject entity, HttpServletRequest request) throws IOException, ParseException {
		String corporateStrategyMakeAndExecute = request.getParameter("corporateStrategyMakeAndExecute");
		String financialControl = request.getParameter("financialControl");
		String humanResourceManagement = request.getParameter("humanResourceManagement");
		String purchaseManagement = request.getParameter("purchaseManagement");
		String infrastructure = request.getParameter("infrastructure");
		String projectInvestment = request.getParameter("projectInvestment");
		String productionOrganizationAndSales = request.getParameter("productionOrganizationAndSales");
		String administrativeAffairs = request.getParameter("administrativeAffairs");
		String externalEnvironmentAndCompetition = request.getParameter("externalEnvironmentAndCompetition");
		String others = request.getParameter("others");
		//整改问题
		String abarbeitungQuestionList = request.getParameter("abarbeitungQuestionList");
		JSONArray jarr = JSONArray.parseArray(abarbeitungQuestionList); 
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			if (Common.notEmpty(entity.getCaseTotalAmount())) {
				entity.setCaseTotalAmount(df2.format(Double.parseDouble(entity.getCaseTotalAmount())));
			}
			if (Common.notEmpty(entity.getSaveLoss())) {
				entity.setSaveLoss(df2.format(Double.parseDouble(entity.getSaveLoss())));
			}
			int auditProjectId = auditProjectService.saveAuditProject(entity);
			//保存审计项目发现问题
			AuditProjectFindQuestion auditProjectFindQuestion = new AuditProjectFindQuestion();
			if (Common.notEmpty(corporateStrategyMakeAndExecute)) {
				auditProjectFindQuestion.setCorporateStrategyMakeAndExecute(Integer.parseInt(corporateStrategyMakeAndExecute));
			}
			if (Common.notEmpty(financialControl)) {
				auditProjectFindQuestion.setFinancialControl(Integer.parseInt(financialControl));
			}
			if (Common.notEmpty(humanResourceManagement)) {
				auditProjectFindQuestion.setHumanResourceManagement(Integer.parseInt(humanResourceManagement));
			}
			if (Common.notEmpty(purchaseManagement)) {
				auditProjectFindQuestion.setPurchaseManagement(Integer.parseInt(purchaseManagement));
			}
			if (Common.notEmpty(infrastructure)) {
				auditProjectFindQuestion.setInfrastructure(Integer.parseInt(infrastructure));
			}
			if (Common.notEmpty(projectInvestment)) {
				auditProjectFindQuestion.setProjectInvestment(Integer.parseInt(projectInvestment));
			}
			if (Common.notEmpty(productionOrganizationAndSales)) {
				auditProjectFindQuestion.setProductionOrganizationAndSales(Integer.parseInt(productionOrganizationAndSales));
			}
			if (Common.notEmpty(administrativeAffairs)) {
				auditProjectFindQuestion.setAdministrativeAffairs(Integer.parseInt(administrativeAffairs));
			}
			if (Common.notEmpty(externalEnvironmentAndCompetition)) {
				auditProjectFindQuestion.setExternalEnvironmentAndCompetition(Integer.parseInt(externalEnvironmentAndCompetition));
			}
			if (Common.notEmpty(others)) {
				auditProjectFindQuestion.setOthers(Integer.parseInt(others));
			}
			auditProjectFindQuestion.setAuditProjectId(auditProjectId);
			auditProjectFindQuestion.setIsDel(0);
			auditProjectFindQuestion.setCreateDate(df.format(new Date()));
			auditProjectFindQuestion.setCreatePersonId(user.getVcEmployeeId());
			auditProjectFindQuestion.setCreatePersonName(user.getVcName());
			auditProjectFindQuestionService.saveAuditProjectFindQuestion(auditProjectFindQuestion);
			//保存审计项目整改问题
			for (Iterator iterator = jarr.iterator(); iterator.hasNext();) { 
				JSONObject job = (JSONObject) iterator.next(); 
				AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion = new AuditProjectAbarbeitungQuestion();
				auditProjectAbarbeitungQuestion.setAuditProjectId(auditProjectId);
				auditProjectAbarbeitungQuestion.setAuditProjectName(entity.getAuditProjectName());
				if (job.get("abarbeitungStatus").toString().trim().equals("未完成")) {
					auditProjectAbarbeitungQuestion.setAbarbeitungStatus(0);
				}else if (job.get("abarbeitungStatus").toString().trim().equals("已完成")) {
					auditProjectAbarbeitungQuestion.setAbarbeitungStatus(1);
				}
				auditProjectAbarbeitungQuestion.setAbarbeitungOfficer(job.get("abarbeitungOfficer").toString().trim());
				auditProjectAbarbeitungQuestion.setAbarbeitungTimeLimit(job.get("abarbeitungTimeLimit").toString().trim());
				auditProjectAbarbeitungQuestion.setExistentialQuestion(job.get("existentialQuestion").toString().trim());
				auditProjectAbarbeitungQuestion.setAuditSuggestion(job.get("auditSuggestion").toString().trim());
				auditProjectAbarbeitungQuestion.setAbarbeitungMeasures(job.get("abarbeitungMeasures").toString().trim());
				auditProjectAbarbeitungQuestion.setRemark(job.get("remark").toString().trim());
				auditProjectAbarbeitungQuestion.setIsDel(0);
				auditProjectAbarbeitungQuestion.setCreateDate(df.format(new Date()));
				auditProjectAbarbeitungQuestion.setCreatePersonId(user.getVcEmployeeId());
				auditProjectAbarbeitungQuestion.setCreatePersonName(user.getVcName());
				auditProjectAbarbeitungQuestionService.saveAuditProjectAbarbeitungQuestion(auditProjectAbarbeitungQuestion);
	        }
		}else {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			if (Common.notEmpty(entity.getCaseTotalAmount())) {
				entity.setCaseTotalAmount(df2.format(Double.parseDouble(entity.getCaseTotalAmount())));
			}
			if (Common.notEmpty(entity.getSaveLoss())) {
				entity.setSaveLoss(df2.format(Double.parseDouble(entity.getSaveLoss())));
			}
			auditProjectService.updateAuditProject(entity);
			AuditProjectFindQuestion auditProjectFindQuestion = auditProjectFindQuestionService.getAuditProjectFindQuestionByAuditProjectId(entity.getId());
			if (auditProjectFindQuestion != null) {
				if (Common.notEmpty(corporateStrategyMakeAndExecute)) {
					auditProjectFindQuestion.setCorporateStrategyMakeAndExecute(Integer.parseInt(corporateStrategyMakeAndExecute));
				}
				if (Common.notEmpty(financialControl)) {
					auditProjectFindQuestion.setFinancialControl(Integer.parseInt(financialControl));
				}
				if (Common.notEmpty(humanResourceManagement)) {
					auditProjectFindQuestion.setHumanResourceManagement(Integer.parseInt(humanResourceManagement));
				}
				if (Common.notEmpty(purchaseManagement)) {
					auditProjectFindQuestion.setPurchaseManagement(Integer.parseInt(purchaseManagement));
				}
				if (Common.notEmpty(infrastructure)) {
					auditProjectFindQuestion.setInfrastructure(Integer.parseInt(infrastructure));
				}
				if (Common.notEmpty(projectInvestment)) {
					auditProjectFindQuestion.setProjectInvestment(Integer.parseInt(projectInvestment));
				}
				if (Common.notEmpty(productionOrganizationAndSales)) {
					auditProjectFindQuestion.setProductionOrganizationAndSales(Integer.parseInt(productionOrganizationAndSales));
				}
				if (Common.notEmpty(administrativeAffairs)) {
					auditProjectFindQuestion.setAdministrativeAffairs(Integer.parseInt(administrativeAffairs));
				}
				if (Common.notEmpty(externalEnvironmentAndCompetition)) {
					auditProjectFindQuestion.setExternalEnvironmentAndCompetition(Integer.parseInt(externalEnvironmentAndCompetition));
				}
				if (Common.notEmpty(others)) {
					auditProjectFindQuestion.setOthers(Integer.parseInt(others));
				}
				auditProjectFindQuestion.setIsDel(0);
				auditProjectFindQuestion.setLastModifyDate(df.format(new Date()));
				auditProjectFindQuestion.setLastModifyPersonId(user.getVcEmployeeId());
				auditProjectFindQuestion.setLastModifyPersonName(user.getVcName());
				auditProjectFindQuestionService.updateAuditProjectFindQuestion(auditProjectFindQuestion);
			}else{
				if (Common.notEmpty(corporateStrategyMakeAndExecute)) {
					auditProjectFindQuestion.setCorporateStrategyMakeAndExecute(Integer.parseInt(corporateStrategyMakeAndExecute));
				}
				if (Common.notEmpty(financialControl)) {
					auditProjectFindQuestion.setFinancialControl(Integer.parseInt(financialControl));
				}
				if (Common.notEmpty(humanResourceManagement)) {
					auditProjectFindQuestion.setHumanResourceManagement(Integer.parseInt(humanResourceManagement));
				}
				if (Common.notEmpty(purchaseManagement)) {
					auditProjectFindQuestion.setPurchaseManagement(Integer.parseInt(purchaseManagement));
				}
				if (Common.notEmpty(infrastructure)) {
					auditProjectFindQuestion.setInfrastructure(Integer.parseInt(infrastructure));
				}
				if (Common.notEmpty(projectInvestment)) {
					auditProjectFindQuestion.setProjectInvestment(Integer.parseInt(projectInvestment));
				}
				if (Common.notEmpty(productionOrganizationAndSales)) {
					auditProjectFindQuestion.setProductionOrganizationAndSales(Integer.parseInt(productionOrganizationAndSales));
				}
				if (Common.notEmpty(administrativeAffairs)) {
					auditProjectFindQuestion.setAdministrativeAffairs(Integer.parseInt(administrativeAffairs));
				}
				if (Common.notEmpty(externalEnvironmentAndCompetition)) {
					auditProjectFindQuestion.setExternalEnvironmentAndCompetition(Integer.parseInt(externalEnvironmentAndCompetition));
				}
				if (Common.notEmpty(others)) {
					auditProjectFindQuestion.setOthers(Integer.parseInt(others));
				}
				auditProjectFindQuestion.setIsDel(0);
				auditProjectFindQuestion.setAuditProjectId(entity.getId());
				auditProjectFindQuestion.setCreateDate(df.format(new Date()));
				auditProjectFindQuestion.setCreatePersonId(user.getVcEmployeeId());
				auditProjectFindQuestion.setCreatePersonName(user.getVcName());
				auditProjectFindQuestionService.saveAuditProjectFindQuestion(auditProjectFindQuestion);
			} 
			List<AuditProjectAbarbeitungQuestion> auditProjectAbarbeitungQuestionList = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestionByAuditProjectId(entity.getId());
			if (auditProjectAbarbeitungQuestionList.size() > 0) {
				for (AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion : auditProjectAbarbeitungQuestionList) {
					auditProjectAbarbeitungQuestionService.deleteAuditProjectAbarbeitungQuestion(auditProjectAbarbeitungQuestion.getId());
				}
			}
			//保存审计项目整改问题
			for (Iterator iterator = jarr.iterator(); iterator.hasNext();) { 
				JSONObject job = (JSONObject) iterator.next(); 
				AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion = new AuditProjectAbarbeitungQuestion();
				auditProjectAbarbeitungQuestion.setAuditProjectId(entity.getId());
				auditProjectAbarbeitungQuestion.setAuditProjectName(entity.getAuditProjectName());
				if (job.get("abarbeitungStatus").toString().trim().equals("未完成")) {
					auditProjectAbarbeitungQuestion.setAbarbeitungStatus(0);
				}else if (job.get("abarbeitungStatus").toString().trim().equals("已完成")) {
					auditProjectAbarbeitungQuestion.setAbarbeitungStatus(1);
				}
				auditProjectAbarbeitungQuestion.setAbarbeitungOfficer(job.get("abarbeitungOfficer").toString().trim());
				auditProjectAbarbeitungQuestion.setAbarbeitungTimeLimit(job.get("abarbeitungTimeLimit").toString().trim());
				auditProjectAbarbeitungQuestion.setExistentialQuestion(job.get("existentialQuestion").toString().trim());
				auditProjectAbarbeitungQuestion.setAuditSuggestion(job.get("auditSuggestion").toString().trim());
				auditProjectAbarbeitungQuestion.setAbarbeitungMeasures(job.get("abarbeitungMeasures").toString().trim());
				auditProjectAbarbeitungQuestion.setRemark(job.get("remark").toString().trim());
				auditProjectAbarbeitungQuestion.setIsDel(0);
				auditProjectAbarbeitungQuestion.setCreateDate(df.format(new Date()));
				auditProjectAbarbeitungQuestion.setCreatePersonId(user.getVcEmployeeId());
				auditProjectAbarbeitungQuestion.setCreatePersonName(user.getVcName());
				auditProjectAbarbeitungQuestionService.saveAuditProjectAbarbeitungQuestion(auditProjectAbarbeitungQuestion);
	        }
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndRepotedAuditProject(@ModelAttribute("auditProject")AuditProject entity, HttpServletRequest request) throws IOException, ParseException {
		String result = "";
		String corporateStrategyMakeAndExecute = request.getParameter("corporateStrategyMakeAndExecute");
		String financialControl = request.getParameter("financialControl");
		String humanResourceManagement = request.getParameter("humanResourceManagement");
		String purchaseManagement = request.getParameter("purchaseManagement");
		String infrastructure = request.getParameter("infrastructure");
		String projectInvestment = request.getParameter("projectInvestment");
		String productionOrganizationAndSales = request.getParameter("productionOrganizationAndSales");
		String administrativeAffairs = request.getParameter("administrativeAffairs");
		String externalEnvironmentAndCompetition = request.getParameter("externalEnvironmentAndCompetition");
		String others = request.getParameter("others");
		//整改问题
		String abarbeitungQuestionList = request.getParameter("abarbeitungQuestionList");
		JSONArray jarr = JSONArray.parseArray(abarbeitungQuestionList); 
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
			int auditProjectId = auditProjectService.saveAuditProject(entity);
			//保存审计项目发现问题
			AuditProjectFindQuestion auditProjectFindQuestion = new AuditProjectFindQuestion();
			if (Common.notEmpty(corporateStrategyMakeAndExecute)) {
				auditProjectFindQuestion.setCorporateStrategyMakeAndExecute(Integer.parseInt(corporateStrategyMakeAndExecute));
			}
			if (Common.notEmpty(financialControl)) {
				auditProjectFindQuestion.setFinancialControl(Integer.parseInt(financialControl));
			}
			if (Common.notEmpty(humanResourceManagement)) {
				auditProjectFindQuestion.setHumanResourceManagement(Integer.parseInt(humanResourceManagement));
			}
			if (Common.notEmpty(purchaseManagement)) {
				auditProjectFindQuestion.setPurchaseManagement(Integer.parseInt(purchaseManagement));
			}
			if (Common.notEmpty(infrastructure)) {
				auditProjectFindQuestion.setInfrastructure(Integer.parseInt(infrastructure));
			}
			if (Common.notEmpty(projectInvestment)) {
				auditProjectFindQuestion.setProjectInvestment(Integer.parseInt(projectInvestment));
			}
			if (Common.notEmpty(productionOrganizationAndSales)) {
				auditProjectFindQuestion.setProductionOrganizationAndSales(Integer.parseInt(productionOrganizationAndSales));
			}
			if (Common.notEmpty(administrativeAffairs)) {
				auditProjectFindQuestion.setAdministrativeAffairs(Integer.parseInt(administrativeAffairs));
			}
			if (Common.notEmpty(externalEnvironmentAndCompetition)) {
				auditProjectFindQuestion.setExternalEnvironmentAndCompetition(Integer.parseInt(externalEnvironmentAndCompetition));
			}
			if (Common.notEmpty(others)) {
				auditProjectFindQuestion.setOthers(Integer.parseInt(others));
			}
			auditProjectFindQuestion.setAuditProjectId(auditProjectId);
			auditProjectFindQuestion.setIsDel(0);
			auditProjectFindQuestion.setCreateDate(df.format(new Date()));
			auditProjectFindQuestion.setCreatePersonId(user.getVcEmployeeId());
			auditProjectFindQuestion.setCreatePersonName(user.getVcName());
			auditProjectFindQuestion.setReportDate(df.format(new Date()));
			auditProjectFindQuestion.setReportPersonId(user.getVcEmployeeId());
			auditProjectFindQuestion.setReportPersonName(user.getVcName());
			auditProjectFindQuestionService.saveAuditProjectFindQuestion(auditProjectFindQuestion);
			//保存审计项目整改问题
			for (Iterator iterator = jarr.iterator(); iterator.hasNext();) { 
				JSONObject job = (JSONObject) iterator.next(); 
				AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion = new AuditProjectAbarbeitungQuestion();
				auditProjectAbarbeitungQuestion.setAuditProjectId(auditProjectId);
				auditProjectAbarbeitungQuestion.setAuditProjectName(entity.getAuditProjectName());
				if (job.get("abarbeitungStatus").toString().trim().equals("未完成")) {
					auditProjectAbarbeitungQuestion.setAbarbeitungStatus(0);
				}else if (job.get("abarbeitungStatus").toString().trim().equals("已完成")) {
					auditProjectAbarbeitungQuestion.setAbarbeitungStatus(1);
				}
				auditProjectAbarbeitungQuestion.setAbarbeitungOfficer(job.get("abarbeitungOfficer").toString().trim());
				auditProjectAbarbeitungQuestion.setAbarbeitungTimeLimit(job.get("abarbeitungTimeLimit").toString().trim());
				auditProjectAbarbeitungQuestion.setExistentialQuestion(job.get("existentialQuestion").toString().trim());
				auditProjectAbarbeitungQuestion.setAuditSuggestion(job.get("auditSuggestion").toString().trim());
				auditProjectAbarbeitungQuestion.setAbarbeitungMeasures(job.get("abarbeitungMeasures").toString().trim());
				auditProjectAbarbeitungQuestion.setRemark(job.get("remark").toString().trim());
				auditProjectAbarbeitungQuestion.setIsDel(0);
				auditProjectAbarbeitungQuestion.setCreateDate(df.format(new Date()));
				auditProjectAbarbeitungQuestion.setCreatePersonId(user.getVcEmployeeId());
				auditProjectAbarbeitungQuestion.setCreatePersonName(user.getVcName());
				auditProjectAbarbeitungQuestionService.saveAuditProjectAbarbeitungQuestion(auditProjectAbarbeitungQuestion);
	        }
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
			auditProjectService.updateAuditProject(entity);
			AuditProjectFindQuestion auditProjectFindQuestion = auditProjectFindQuestionService.getAuditProjectFindQuestionByAuditProjectId(entity.getId());
			if (auditProjectFindQuestion != null) {
				if (Common.notEmpty(corporateStrategyMakeAndExecute)) {
					auditProjectFindQuestion.setCorporateStrategyMakeAndExecute(Integer.parseInt(corporateStrategyMakeAndExecute));
				}
				if (Common.notEmpty(financialControl)) {
					auditProjectFindQuestion.setFinancialControl(Integer.parseInt(financialControl));
				}
				if (Common.notEmpty(humanResourceManagement)) {
					auditProjectFindQuestion.setHumanResourceManagement(Integer.parseInt(humanResourceManagement));
				}
				if (Common.notEmpty(purchaseManagement)) {
					auditProjectFindQuestion.setPurchaseManagement(Integer.parseInt(purchaseManagement));
				}
				if (Common.notEmpty(infrastructure)) {
					auditProjectFindQuestion.setInfrastructure(Integer.parseInt(infrastructure));
				}
				if (Common.notEmpty(projectInvestment)) {
					auditProjectFindQuestion.setProjectInvestment(Integer.parseInt(projectInvestment));
				}
				if (Common.notEmpty(productionOrganizationAndSales)) {
					auditProjectFindQuestion.setProductionOrganizationAndSales(Integer.parseInt(productionOrganizationAndSales));
				}
				if (Common.notEmpty(administrativeAffairs)) {
					auditProjectFindQuestion.setAdministrativeAffairs(Integer.parseInt(administrativeAffairs));
				}
				if (Common.notEmpty(externalEnvironmentAndCompetition)) {
					auditProjectFindQuestion.setExternalEnvironmentAndCompetition(Integer.parseInt(externalEnvironmentAndCompetition));
				}
				if (Common.notEmpty(others)) {
					auditProjectFindQuestion.setOthers(Integer.parseInt(others));
				}
				auditProjectFindQuestion.setIsDel(0);
				auditProjectFindQuestion.setLastModifyDate(df.format(new Date()));
				auditProjectFindQuestion.setLastModifyPersonId(user.getVcEmployeeId());
				auditProjectFindQuestion.setLastModifyPersonName(user.getVcName());
				auditProjectFindQuestionService.updateAuditProjectFindQuestion(auditProjectFindQuestion);
			}else{
				if (Common.notEmpty(corporateStrategyMakeAndExecute)) {
					auditProjectFindQuestion.setCorporateStrategyMakeAndExecute(Integer.parseInt(corporateStrategyMakeAndExecute));
				}
				if (Common.notEmpty(financialControl)) {
					auditProjectFindQuestion.setFinancialControl(Integer.parseInt(financialControl));
				}
				if (Common.notEmpty(humanResourceManagement)) {
					auditProjectFindQuestion.setHumanResourceManagement(Integer.parseInt(humanResourceManagement));
				}
				if (Common.notEmpty(purchaseManagement)) {
					auditProjectFindQuestion.setPurchaseManagement(Integer.parseInt(purchaseManagement));
				}
				if (Common.notEmpty(infrastructure)) {
					auditProjectFindQuestion.setInfrastructure(Integer.parseInt(infrastructure));
				}
				if (Common.notEmpty(projectInvestment)) {
					auditProjectFindQuestion.setProjectInvestment(Integer.parseInt(projectInvestment));
				}
				if (Common.notEmpty(productionOrganizationAndSales)) {
					auditProjectFindQuestion.setProductionOrganizationAndSales(Integer.parseInt(productionOrganizationAndSales));
				}
				if (Common.notEmpty(administrativeAffairs)) {
					auditProjectFindQuestion.setAdministrativeAffairs(Integer.parseInt(administrativeAffairs));
				}
				if (Common.notEmpty(externalEnvironmentAndCompetition)) {
					auditProjectFindQuestion.setExternalEnvironmentAndCompetition(Integer.parseInt(externalEnvironmentAndCompetition));
				}
				if (Common.notEmpty(others)) {
					auditProjectFindQuestion.setOthers(Integer.parseInt(others));
				}
				auditProjectFindQuestion.setIsDel(0);
				auditProjectFindQuestion.setAuditProjectId(entity.getId());
				auditProjectFindQuestion.setCreateDate(df.format(new Date()));
				auditProjectFindQuestion.setCreatePersonId(user.getVcEmployeeId());
				auditProjectFindQuestion.setCreatePersonName(user.getVcName());
				auditProjectFindQuestionService.saveAuditProjectFindQuestion(auditProjectFindQuestion);
			}
			List<AuditProjectAbarbeitungQuestion> auditProjectAbarbeitungQuestionList = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestionByAuditProjectId(entity.getId());
			if (auditProjectAbarbeitungQuestionList.size() > 0) {
				for (AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion : auditProjectAbarbeitungQuestionList) {
					auditProjectAbarbeitungQuestionService.deleteAuditProjectAbarbeitungQuestion(auditProjectAbarbeitungQuestion.getId());
				}
			}
			//保存审计项目整改问题
			for (Iterator iterator = jarr.iterator(); iterator.hasNext();) { 
				JSONObject job = (JSONObject) iterator.next(); 
				AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion = new AuditProjectAbarbeitungQuestion();
				auditProjectAbarbeitungQuestion.setAuditProjectId(entity.getId());
				auditProjectAbarbeitungQuestion.setAuditProjectName(entity.getAuditProjectName());
				if (job.get("abarbeitungStatus").toString().trim().equals("未完成")) {
					auditProjectAbarbeitungQuestion.setAbarbeitungStatus(0);
				}else if (job.get("abarbeitungStatus").toString().trim().equals("已完成")) {
					auditProjectAbarbeitungQuestion.setAbarbeitungStatus(1);
				}
				auditProjectAbarbeitungQuestion.setAbarbeitungOfficer(job.get("abarbeitungOfficer").toString().trim());
				auditProjectAbarbeitungQuestion.setAbarbeitungTimeLimit(job.get("abarbeitungTimeLimit").toString().trim());
				auditProjectAbarbeitungQuestion.setExistentialQuestion(job.get("existentialQuestion").toString().trim());
				auditProjectAbarbeitungQuestion.setAuditSuggestion(job.get("auditSuggestion").toString().trim());
				auditProjectAbarbeitungQuestion.setAbarbeitungMeasures(job.get("abarbeitungMeasures").toString().trim());
				auditProjectAbarbeitungQuestion.setRemark(job.get("remark").toString().trim());
				auditProjectAbarbeitungQuestion.setIsDel(0);
				auditProjectAbarbeitungQuestion.setCreateDate(df.format(new Date()));
				auditProjectAbarbeitungQuestion.setCreatePersonId(user.getVcEmployeeId());
				auditProjectAbarbeitungQuestion.setCreatePersonName(user.getVcName());
				auditProjectAbarbeitungQuestionService.saveAuditProjectAbarbeitungQuestion(auditProjectAbarbeitungQuestion);
	        }
			result = "success";
		}
		return result;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String deleteAuditProject(String id, Map<String, Object> map, HttpServletRequest request) {
		String result = "";
		if (Common.notEmpty(id)) {
			AuditProject auditProject = auditProjectService.getAuditProject(Integer.parseInt(id));
			if (auditProject != null) {
				if (auditProject.getStatus() == 50112 || auditProject.getStatus() == 50114) {
					auditProject.setIsDel(1);
					auditProjectService.updateAuditProject(auditProject);
					//删除关联数据
					AuditProjectFindQuestion auditProjectFindQuestion = auditProject.getAuditProjectFindQuestion();
					if (auditProjectFindQuestion != null) {
						auditProjectFindQuestion.setIsDel(1);
						auditProjectFindQuestionService.updateAuditProjectFindQuestion(auditProjectFindQuestion);
					}
					List<AuditProjectAbarbeitungQuestion> auditProjectAbarbeitungQuestionList = auditProject.getAuditProjectAbarbeitungQuestionList();
					if (auditProjectAbarbeitungQuestionList != null) {
						if (auditProjectAbarbeitungQuestionList.size() > 0) {
							for (AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion : auditProjectAbarbeitungQuestionList) {
								auditProjectAbarbeitungQuestion.setIsDel(1);
								auditProjectAbarbeitungQuestionService.updateAuditProjectAbarbeitungQuestion(auditProjectAbarbeitungQuestion);
							}
						}
					}
					List<SysExamine> examineList = examineService.getListExamine(auditProject.getId(), Base.examkind_riskcontrol_auditProject);
					if (examineList != null) {
						if (examineList.size() > 0) {
							for (SysExamine sysExamine : examineList) {
								examineService.deleteExamine(sysExamine.getId());
							}
						}
					}
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
	
	@RequestMapping("/addOrModifyAbarbeitungQuestion")
	public String addOrModifyAbarbeitungQuestion(@ModelAttribute("auditProjectAbarbeitungQuestion") AuditProjectAbarbeitungQuestion entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		String nRow = request.getParameter("nRow");
		map.put("nRow", nRow);
		AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestion(entity.getId());
		map.put("auditProjectAbarbeitungQuestion", auditProjectAbarbeitungQuestion);
		return "/riskcontrol/auditProject/abarbeitungQuestionAddOrModify";
	}
	
	@RequestMapping(value ="/modifyAbarbeitungQuestion")
	public String modifyAbarbeitungQuestion(Map<String, Object> map, HttpServletRequest request, String op) throws UnsupportedEncodingException {
		map.put("op", op);
		request.setCharacterEncoding("utf-8");
		String id = new String(request.getParameter("id").getBytes("iso8859-1"),"utf-8");
		AuditProjectAbarbeitungQuestion question = null;
		if (Common.notEmpty(id)) {
			question = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestion(Integer.parseInt(id));
		}
		String abarbeitungStatus = new String(request.getParameter("abarbeitungStatus").getBytes("iso8859-1"),"utf-8");
		String abarbeitungOfficer = new String(request.getParameter("abarbeitungOfficer").getBytes("iso8859-1"),"utf-8");
		String abarbeitungTimeLimit = new String(request.getParameter("abarbeitungTimeLimit").getBytes("iso8859-1"),"utf-8");
		String existentialQuestion = new String(request.getParameter("existentialQuestion").getBytes("iso8859-1"),"utf-8");
		String auditSuggestion = new String(request.getParameter("auditSuggestion").getBytes("iso8859-1"),"utf-8");
		String abarbeitungMeasures = new String(request.getParameter("abarbeitungMeasures").getBytes("iso8859-1"),"utf-8");
		String remark = new String(request.getParameter("remark").getBytes("iso8859-1"),"utf-8");
		String nRow = request.getParameter("nRow");
		map.put("nRow", nRow);
		AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion = new AuditProjectAbarbeitungQuestion();
		if (Common.notEmpty(abarbeitungStatus)) {
			if ("未完成".equals(abarbeitungStatus)) {
				auditProjectAbarbeitungQuestion.setAbarbeitungStatus(0);
			}
			if ("已完成".equals(abarbeitungStatus)) {
				auditProjectAbarbeitungQuestion.setAbarbeitungStatus(1);
			}
		}
		auditProjectAbarbeitungQuestion.setAbarbeitungOfficer(abarbeitungOfficer);
		auditProjectAbarbeitungQuestion.setAbarbeitungTimeLimit(abarbeitungTimeLimit);
		auditProjectAbarbeitungQuestion.setExistentialQuestion(existentialQuestion);
		auditProjectAbarbeitungQuestion.setAuditSuggestion(auditSuggestion);
		auditProjectAbarbeitungQuestion.setAbarbeitungMeasures(abarbeitungMeasures);
		auditProjectAbarbeitungQuestion.setRemark(remark);
		if (question != null) {
			auditProjectAbarbeitungQuestion.setAuditProjectName(question.getAuditProjectName());
		}
		map.put("auditProjectAbarbeitungQuestion", auditProjectAbarbeitungQuestion);
		return "/riskcontrol/auditProject/abarbeitungQuestionAddOrModify";
	}
	
	@RequestMapping("/viewAbarbeitungQuestion")
	public String viewAbarbeitungQuestion(Map<String, Object> map, HttpServletRequest request, String op) throws UnsupportedEncodingException {
		map.put("op", op);
		request.setCharacterEncoding("utf-8");
		String id = new String(request.getParameter("id").getBytes("iso8859-1"),"utf-8");
		AuditProjectAbarbeitungQuestion question = null;
		if (Common.notEmpty(id)) {
			question = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestion(Integer.parseInt(id));
		}
		String abarbeitungStatus = new String(request.getParameter("abarbeitungStatus").getBytes("iso8859-1"),"utf-8");
		String abarbeitungOfficer = new String(request.getParameter("abarbeitungOfficer").getBytes("iso8859-1"),"utf-8");
		String abarbeitungTimeLimit = new String(request.getParameter("abarbeitungTimeLimit").getBytes("iso8859-1"),"utf-8");
		String existentialQuestion = new String(request.getParameter("existentialQuestion").getBytes("iso8859-1"),"utf-8");
		String auditSuggestion = new String(request.getParameter("auditSuggestion").getBytes("iso8859-1"),"utf-8");
		String abarbeitungMeasures = new String(request.getParameter("abarbeitungMeasures").getBytes("iso8859-1"),"utf-8");
		String remark = new String(request.getParameter("remark").getBytes("iso8859-1"),"utf-8");
		String nRow = request.getParameter("nRow");
		map.put("nRow", nRow);
		AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion = new AuditProjectAbarbeitungQuestion();
		if (Common.notEmpty(abarbeitungStatus)) {
			if ("未完成".equals(abarbeitungStatus)) {
				auditProjectAbarbeitungQuestion.setAbarbeitungStatus(0);
			}
			if ("已完成".equals(abarbeitungStatus)) {
				auditProjectAbarbeitungQuestion.setAbarbeitungStatus(1);
			}
		}
		auditProjectAbarbeitungQuestion.setAbarbeitungOfficer(abarbeitungOfficer);
		auditProjectAbarbeitungQuestion.setAbarbeitungTimeLimit(abarbeitungTimeLimit);
		auditProjectAbarbeitungQuestion.setExistentialQuestion(existentialQuestion);
		auditProjectAbarbeitungQuestion.setAuditSuggestion(auditSuggestion);
		auditProjectAbarbeitungQuestion.setAbarbeitungMeasures(abarbeitungMeasures);
		auditProjectAbarbeitungQuestion.setRemark(remark);
		if (question != null) {
			auditProjectAbarbeitungQuestion.setAuditProjectName(question.getAuditProjectName());
		}
		map.put("auditProjectAbarbeitungQuestion", auditProjectAbarbeitungQuestion);
		return "/riskcontrol/auditProject/abarbeitungQuestionView";
	}
	
	@RequestMapping(value = "/deleteAbarbeitungQuestion")
	@ResponseBody
	public String deleteAbarbeitungQuestion(String id, Map<String, Object> map, HttpServletRequest request) {
		if (Common.notEmpty(id)) {
			AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestion(Integer.parseInt(id));
			auditProjectAbarbeitungQuestion.setIsDel(1);
			auditProjectAbarbeitungQuestionService.updateAuditProjectAbarbeitungQuestion(auditProjectAbarbeitungQuestion);
		}
		return "success";
	}
	
	@RequestMapping("/updateAbarbeitungQuestion")
	@ResponseBody
	public String updateAbarbeitungQuestion(@ModelAttribute("auditProjectAbarbeitungQuestion") AuditProjectAbarbeitungQuestion entity, HttpServletRequest request) {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() != null) {
			AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestion(entity.getId());
			auditProjectAbarbeitungQuestion.setAbarbeitungStatus(entity.getAbarbeitungStatus());
			auditProjectAbarbeitungQuestion.setAbarbeitungOfficer(entity.getAbarbeitungOfficer());
			auditProjectAbarbeitungQuestion.setAbarbeitungTimeLimit(entity.getAbarbeitungTimeLimit());
			auditProjectAbarbeitungQuestion.setExistentialQuestion(entity.getExistentialQuestion());
			auditProjectAbarbeitungQuestion.setAuditSuggestion(entity.getAuditSuggestion());
			auditProjectAbarbeitungQuestion.setAbarbeitungMeasures(entity.getAbarbeitungMeasures());
			auditProjectAbarbeitungQuestion.setRemark(entity.getRemark());
			auditProjectAbarbeitungQuestion.setLastModifyDate(df.format(new Date()));
			auditProjectAbarbeitungQuestion.setLastModifyPersonId(user.getVcEmployeeId());
			auditProjectAbarbeitungQuestion.setLastModifyPersonName(user.getVcName());
			auditProjectAbarbeitungQuestionService.updateAuditProjectAbarbeitungQuestion(auditProjectAbarbeitungQuestion);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(String id, String op){
		String result = "";
		if (Common.notEmpty(id)) {
			AuditProject entity = auditProjectService.getAuditProject(Integer.parseInt(id));
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
