package com.softline.controller.queryPage.riskcontrol;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.entity.AuditProject;
import com.softline.entity.AuditProjectAbarbeitungQuestion;
import com.softline.entity.AuditProjectFindQuestion;
import com.softline.entity.HhBase;
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
@RequestMapping("/queryPage/riskcontrol/auditProject")
public class QueryPageAuditProjectController {
	
	@Resource(name = "auditProjectService")
	private IAuditProjectService auditProjectService;
	
	@Resource(name = "auditProjectFindQuestionService")
	private IAuditProjectFindQuestionService auditProjectFindQuestionService;
	
	@Resource(name = "auditProjectAbarbeitungQuestionService")
	private IAuditProjectAbarbeitungQuestionService auditProjectAbarbeitungQuestionService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	SimpleDateFormat StingToDate = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat DateToString = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping("/list")
	public String queryAuditProjectList(AuditProject entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
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
	    map.put("searchurl", "/shanghai-gzw/queryPage/riskcontrol/auditProject/list");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
		List<HhBase> auditType = baseService.getBases(Base.audit_type);
	    map.put("auditType",auditType);
		return "/queryPage/riskcontrol/auditProject/auditProjectList";
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
		return "/queryPage/riskcontrol/auditProject/auditProjectView";
	}
	
	

}
