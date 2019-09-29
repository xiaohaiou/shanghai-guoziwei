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
import com.softline.entity.HhBase;
import com.softline.entity.RiskControlAccount;
import com.softline.entity.SysExamine;
import com.softline.service.riskcontrol.IRiskControlAccountService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/riskcontrol/account")
public class QueryPageRiskControlAccountController {
	
	@Resource(name = "riskControlAccountService")
	private IRiskControlAccountService riskControlAccountService;
	
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
	public String queryAccountList(RiskControlAccount entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		String str = (String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
		String complainReceiveDate2 = request.getParameter("complainReceiveDate2");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = riskControlAccountService.getAccounts(entity,pageNum,Base.pagesize,complainReceiveDate2,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/queryPage/riskcontrol/account/list");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
		map.put("complainReceiveDate2", complainReceiveDate2);
		return "/queryPage/riskcontrol/account/accountList";
	}
	
	@RequestMapping("/view")
	public String viewAccount(RiskControlAccount entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		RiskControlAccount riskControlAccount = riskControlAccountService.getAccount(entity);
		map.put("riskControlAccount", riskControlAccount);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskcontrol_account);
		map.put("examineList", examineList);
		return "/queryPage/riskcontrol/account/accountView";
	}
	
}
