package com.softline.controller.queryPage.riskcontrol;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softline.common.Base;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.RiskControlCase;
import com.softline.entity.SysExamine;
import com.softline.service.riskcontrol.IRiskControlCaseService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/riskcontrol/case")
public class QueryPageRiskControlCaseController {
	
	@Resource(name = "riskControlCaseService")
	private IRiskControlCaseService riskControlCaseService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	SimpleDateFormat StingToDate = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat DateToString = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	DecimalFormat df2 = new DecimalFormat("###0.0000"); 

	@RequestMapping("/list")
	public String queryCaseList(RiskControlCase entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
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
	    map.put("searchurl", "/shanghai-gzw/queryPage/riskcontrol/case/list");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
	    List<HhOrganInfo> auditDepts = systemService.getOrganInfos(Base.HRTopPrefix);
		map.put("auditDepts", auditDepts);
		List<HhBase> caseType = baseService.getBases(Base.case_type);
	    map.put("caseType",caseType);
	    map.put("caseHappenDate2", caseHappenDate2);
		return "/queryPage/riskcontrol/case/caseList";
	}
	

	@RequestMapping("/view")
	public String viewCase(RiskControlCase entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		RiskControlCase riskControlCase = riskControlCaseService.getCase(entity);
		map.put("riskControlCase", riskControlCase);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskcontrol_case);
		map.put("examineList", examineList);
		return "/queryPage/riskcontrol/case/caseView";
	}

	
}
