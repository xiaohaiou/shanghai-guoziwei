package com.softline.controller.riskcontrol;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.KnowledgeBase;
import com.softline.entity.RiskControlAccount;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.RiskControlCase;
import com.softline.entity.SysExamine;
import com.softline.service.riskcontrol.IRiskControlAccountService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/riskcontrol/account")
public class RiskControlAccountController {
	
	@Resource(name = "riskControlAccountService")
	private IRiskControlAccountService riskControlAccountService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping("/list")
	public String queryAccountList(RiskControlAccount entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/riskcontrol/account";
		map.put("mapurl", mapurl);
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
	    map.put("searchurl", "/shanghai-gzw/riskcontrol/account/list");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
		map.put("complainReceiveDate2", complainReceiveDate2);
		return "/riskcontrol/account/accountList";
	}
	
	@RequestMapping("/examinelist")
	public String queryExamineAccountList(RiskControlAccount entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/riskcontrol/account";
		map.put("mapurl", mapurl);
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
        MsgPage msgPage = riskControlAccountService.getExamineAccounts(entity,pageNum,Base.pagesize,complainReceiveDate2,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/riskcontrol/account/examinelist");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
		map.put("complainReceiveDate2", complainReceiveDate2);
		return "/riskcontrol/account/accountExamineList";
	}
	
	@RequestMapping("/addOrModify")
	public String addOrModifyAccount(RiskControlAccount entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		HttpSession session = request.getSession();
		map.put("op", op);
		List<HhBase> auditType = baseService.getBases(Base.audit_type);
	    map.put("auditType",auditType);
	    List<HhBase> personManageLevel = baseService.getBases(Base.person_manage_level);
	    map.put("personManageLevel",personManageLevel);
		RiskControlAccount riskControlAccount;
		if(entity.getId() != null) {
			riskControlAccount = riskControlAccountService.getAccount(entity);
		}else {
			riskControlAccount = new RiskControlAccount();
		}
		map.put("riskControlAccount", riskControlAccount);
		String str = (String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
		return "/riskcontrol/account/accountAddOrModify";
	}
	
	@RequestMapping("/view")
	public String viewAccount(RiskControlAccount entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		RiskControlAccount riskControlAccount = riskControlAccountService.getAccount(entity);
		map.put("riskControlAccount", riskControlAccount);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskcontrol_account);
		map.put("examineList", examineList);
		return "/riskcontrol/account/accountView";
	}
	
	@ResponseBody
	@RequestMapping(value ="/reported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportedAccount(Integer id, HttpServletRequest request) throws IOException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		RiskControlAccount riskControlAccount = riskControlAccountService.getAccount(id);
		riskControlAccount.setStatus(Base.examstatus_2);
		Date date = new Date();
		riskControlAccount.setReportDate(df.format(date));
		riskControlAccount.setReportPersonId(user.getVcEmployeeId());
		riskControlAccount.setReportPersonName(user.getVcName());
		riskControlAccountService.updateAccount(riskControlAccount);
		result = "success";
		return result;
	}
	
	@RequestMapping("/examine")
	public String examineAccount(RiskControlAccount entity, Map<String, Object> map, HttpServletRequest request) {
		RiskControlAccount riskControlAccount = riskControlAccountService.getAccount(entity);
		map.put("riskControlAccount", riskControlAccount);
		//审批时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskcontrol_account);
		map.put("examineList", examineList);
		return "/riskcontrol/account/accountExamine";
	}
	
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String commitExamineAccount(Integer entityid, String examStr, Integer examResult, HttpServletRequest request) throws IOException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entityid != null) {
			RiskControlAccount entity = riskControlAccountService.getAccount(entityid);
			if (entity != null) {
				if (entity.getStatus() == 50113) {
					riskControlAccountService.saveAccountExamine(entityid, examStr, examResult, user);	
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
	public String saveOrUpdateAccount(@ModelAttribute("riskControlAccount")RiskControlAccount entity, HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			riskControlAccountService.saveAccount(entity);
		}else {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			riskControlAccountService.updateAccount(entity);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndRepotedAccount(@ModelAttribute("riskControlAccount")RiskControlAccount entity, HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		String result = "";
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
			riskControlAccountService.saveAccount(entity);
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
			riskControlAccountService.updateAccount(entity);
			result = "success";
		}
		return result;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String deleteAccount(String id, Map<String, Object> map, HttpServletRequest request) {
		String result = "";
		if (Common.notEmpty(id)) {
			RiskControlAccount riskControlAccount = riskControlAccountService.getAccount(Integer.parseInt(id));
			if (riskControlAccount != null) {
				if (riskControlAccount.getStatus() == 50112 || riskControlAccount.getStatus() == 50114) {
					riskControlAccount.setIsDel(1);
					riskControlAccountService.updateAccount(riskControlAccount);
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
	
	@ResponseBody
	@RequestMapping(value = "/getCoreComp", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getCoreComp(String companyId) throws IOException {
		HhOrganInfo entity = new HhOrganInfo();
		entity = riskControlAccountService.getCoreComp(companyId);
		String data = "";
		data = JSONArray.toJSONString(entity);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(String id, String op){
		String result = "";
		if (Common.notEmpty(id)) {
			RiskControlAccount entity = riskControlAccountService.getAccount(Integer.parseInt(id));
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
