package com.softline.controller.riskcontrol;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
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
import com.softline.entity.RiskControlAccount;
import com.softline.entity.RiskControlCase;
import com.softline.entity.RiskControlContract;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.service.riskcontrol.IRiskControlContractService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/riskcontrol/contract")
public class RiskControlContractController {
	
	@Resource(name = "riskControlContractService")
	private IRiskControlContractService riskControlContractService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	DecimalFormat df2 = new DecimalFormat("###0.0000");  

	@RequestMapping("/list")
	public String queryContractList(RiskControlContract entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/riskcontrol/contract";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
		String contractSignDate2 = request.getParameter("contractSignDate2");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = riskControlContractService.getContracts(entity,pageNum,Base.pagesize,contractSignDate2,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/riskcontrol/contract/list");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
	    List<HhOrganInfo> auditDepts = systemService.getOrganInfos(Base.HRTopPrefix);
		map.put("auditDepts", auditDepts);
		List<HhBase> contractType = baseService.getBases(Base.contract_type);
	    map.put("contractType",contractType);
	    map.put("contractSignDate2", contractSignDate2);
		return "/riskcontrol/contract/contractList";
	}
	
	@RequestMapping("/examinelist")
	public String queryExamineContractList(RiskControlContract entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/riskcontrol/contract";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
		String contractSignDate2 = request.getParameter("contractSignDate2");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = riskControlContractService.getExamineContracts(entity,pageNum,Base.pagesize,contractSignDate2,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/riskcontrol/contract/examinelist");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
	    List<HhOrganInfo> auditDepts = systemService.getOrganInfos(Base.HRTopPrefix);
		map.put("auditDepts", auditDepts);
		List<HhBase> contractType = baseService.getBases(Base.contract_type);
	    map.put("contractType",contractType);
	    map.put("contractSignDate2", contractSignDate2);
		return "/riskcontrol/contract/contractExamineList";
	}
	
	@RequestMapping("/addOrModify")
	public String addOrModifyContract(RiskControlContract entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		map.put("op", op);
		List<HhOrganInfo> auditDepts = systemService.getOrganInfos(Base.HRTopPrefix);
		map.put("auditDepts", auditDepts);
		List<HhBase> auditType = baseService.getBases(Base.audit_type);
	    map.put("auditType",auditType);
	    List<HhBase> contractType = baseService.getBases(Base.contract_type);
	    map.put("contractType",contractType);
		RiskControlContract riskControlContract;
		if(entity.getId() != null) {
			riskControlContract = riskControlContractService.getContract(entity);
		}else {
			riskControlContract = new RiskControlContract();
		}
		map.put("riskControlContract", riskControlContract);
		return "/riskcontrol/contract/contractAddOrModify";
	}
	
	@RequestMapping("/view")
	public String viewContract(RiskControlContract entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		RiskControlContract riskControlContract = riskControlContractService.getContract(entity);
		map.put("riskControlContract", riskControlContract);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskcontrol_contract);
		map.put("examineList", examineList);
		return "/riskcontrol/contract/contractView";
	}
	
	@ResponseBody
	@RequestMapping(value ="/reported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportedContract(Integer id, HttpServletRequest request) throws IOException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		RiskControlContract contract = riskControlContractService.getContract(id);
		contract.setStatus(Base.examstatus_2);
		Date date = new Date();
		contract.setReportDate(df.format(date));
		contract.setReportPersonId(user.getVcEmployeeId());
		contract.setReportPersonName(user.getVcName());
		riskControlContractService.updateContract(contract);
		result = "success";
		return result;
	}
	
	@RequestMapping("/examine")
	public String examineContract(RiskControlContract entity, Map<String, Object> map, HttpServletRequest request) {
		RiskControlContract riskControlContract = riskControlContractService.getContract(entity);
		map.put("riskControlContract", riskControlContract);
		//审批时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskcontrol_contract);
		map.put("examineList", examineList);
		return "/riskcontrol/contract/contractExamine";
	}
	
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String commitExamineContract(Integer entityid, String examStr, Integer examResult, HttpServletRequest request) throws IOException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entityid != null) {
			RiskControlContract entity = riskControlContractService.getContract(entityid);
			if (entity != null) {
				if (entity.getStatus() == 50113) {
					riskControlContractService.saveContractExamine(entityid, examStr, examResult, user);
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
	public String saveOrUpdateContract(@ModelAttribute("riskControlContract")RiskControlContract entity, HttpServletRequest request) throws IOException, ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			if (Common.notEmpty(entity.getContractTotalAmount())) {
				entity.setContractTotalAmount(df2.format(Double.parseDouble(entity.getContractTotalAmount())));
			}
			if (Common.notEmpty(entity.getOurOverPay())) {
				entity.setOurOverPay(df2.format(Double.parseDouble(entity.getOurOverPay())));
			}
			if (Common.notEmpty(entity.getOurDefaultPay())) {
				entity.setOurDefaultPay(df2.format(Double.parseDouble(entity.getOurDefaultPay())));			
			}
			if (Common.notEmpty(entity.getOtherOverPay())) {
				entity.setOtherOverPay(df2.format(Double.parseDouble(entity.getOtherOverPay())));
			}
			if (Common.notEmpty(entity.getOtherDefaultPay())) {
				entity.setOtherDefaultPay(df2.format(Double.parseDouble(entity.getOtherDefaultPay())));
			}
			riskControlContractService.saveContract(entity);
		}else {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			if (Common.notEmpty(entity.getContractTotalAmount())) {
				entity.setContractTotalAmount(df2.format(Double.parseDouble(entity.getContractTotalAmount())));
			}
			if (Common.notEmpty(entity.getOurOverPay())) {
				entity.setOurOverPay(df2.format(Double.parseDouble(entity.getOurOverPay())));
			}
			if (Common.notEmpty(entity.getOurDefaultPay())) {
				entity.setOurDefaultPay(df2.format(Double.parseDouble(entity.getOurDefaultPay())));			
			}
			if (Common.notEmpty(entity.getOtherOverPay())) {
				entity.setOtherOverPay(df2.format(Double.parseDouble(entity.getOtherOverPay())));
			}
			if (Common.notEmpty(entity.getOtherDefaultPay())) {
				entity.setOtherDefaultPay(df2.format(Double.parseDouble(entity.getOtherDefaultPay())));
			}
			riskControlContractService.updateContract(entity);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndRepotedContract(@ModelAttribute("riskControlContract")RiskControlContract entity, HttpServletRequest request) throws IOException, ParseException {
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
			riskControlContractService.saveContract(entity);
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
			riskControlContractService.updateContract(entity);
			result = "success";
		}
		return result;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String deleteContract(String id, Map<String, Object> map, HttpServletRequest request) {
		String result = "";
		if (Common.notEmpty(id)) {
			RiskControlContract contract = riskControlContractService.getContract(Integer.parseInt(id));
			if (contract != null) {
				if (contract.getStatus() == 50112 || contract.getStatus() == 50114) {
					contract.setIsDel(1);
					riskControlContractService.updateContract(contract);
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
			RiskControlContract entity = riskControlContractService.getContract(Integer.parseInt(id));
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
