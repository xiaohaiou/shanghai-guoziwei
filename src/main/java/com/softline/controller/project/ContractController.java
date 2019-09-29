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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.entity.HhUsers;
import com.softline.entity.project.PjCode;
import com.softline.entity.project.PjContract;
import com.softline.entity.project.PjContractHistory;
import com.softline.entity.project.PjContractTemp;
import com.softline.entity.project.PjPcPayrecord;
import com.softline.entity.project.PjPcPayrecordTemp;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjWeekreportHistory;
import com.softline.service.project.IContractService;
import com.softline.service.project.IPjCodeService;
import com.softline.service.project.IPjPcPayrecordService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/project/contract")
public class ContractController {
	
	@Resource(name = "commonService")
	private ICommonService commonService;

	@Resource(name = "pjPcPayrecordService")
	private IPjPcPayrecordService pjPcPayrecordService;
	
	@Resource(name = "pjCodeService")
	private IPjCodeService pjCodeService;
	
	@Resource(name = "contractService")
	private IContractService contractService;

	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping("_addContract")
	public String _addContract(Integer pjId, Map<String, Object> map,
			HttpServletRequest request) {
		if (pjId != null) {
			PjProject project = (PjProject) commonService.findById(
					PjProject.class, pjId);
			map.put("project", project);
		}
		map.put("isTemp", false);
		// 初始化选项数据
		List<PjCode> contractTypes = pjCodeService
				.getPjCodes(Base.PJ_CONTRACT_TYPE);
		map.put("contractTypes", contractTypes);
		List<PjCode> moneyUnits = pjCodeService.getPjCodes(Base.PJ_MONEY_UNIT);
		map.put("moneyUnits", moneyUnits);
		List<PjCode> pcPayTypes = pjCodeService.getPjCodes(Base.PC_PAY_TYPE);
		map.put("pcPayTypes", pcPayTypes);
		
		return "/project/contractEdit";
	}
	
	@RequestMapping("_addContractTemp")
	public String _addContractTemp(Integer pjId, Map<String, Object> map,
			HttpServletRequest request) {
		if (pjId != null) {
			PjProject project = (PjProject) commonService.findById(
					PjProject.class, pjId);
			map.put("project", project);
		}
		
		map.put("isTemp", true);
		// 初始化选项数据
		List<PjCode> contractTypes = pjCodeService
				.getPjCodes(Base.PJ_CONTRACT_TYPE);
		map.put("contractTypes", contractTypes);
		List<PjCode> moneyUnits = pjCodeService.getPjCodes(Base.PJ_MONEY_UNIT);
		map.put("moneyUnits", moneyUnits);
		List<PjCode> pcPayTypes = pjCodeService.getPjCodes(Base.PC_PAY_TYPE);
		map.put("pcPayTypes", pcPayTypes);

		return "/project/contractEdit";
	}

	@RequestMapping("_modifyContractTemp")
	public String _modifyContractTemp(Integer id, Map<String, Object> map,
			HttpServletRequest request) {
		PjContractTemp contractTemp = (PjContractTemp) commonService.findById(
				PjContractTemp.class, id);
		map.put("contract", contractTemp);
		
		map.put("isTemp", true);

		// 计划付款信息
		List<PjPcPayrecordTemp> payrecords = pjPcPayrecordService
				.getPayrecordTemps(contractTemp.getId());
		map.put("payRecords", payrecords);
		// 初始化选项数据
		List<PjCode> contractTypes = pjCodeService
				.getPjCodes(Base.PJ_CONTRACT_TYPE);
		map.put("contractTypes", contractTypes);
		List<PjCode> moneyUnits = pjCodeService.getPjCodes(Base.PJ_MONEY_UNIT);
		map.put("moneyUnits", moneyUnits);
		List<PjCode> pcPayTypes = pjCodeService.getPjCodes(Base.PC_PAY_TYPE);
		map.put("pcPayTypes", pcPayTypes);

		return "/project/contractEdit";
	}

	@ResponseBody
	@RequestMapping(value = "_saveContractTemp", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	public String _saveContractTemp(String payRecordIds,
			PjContractTemp contract, Map<String, Object> map,
			HttpServletRequest request) {
		HhUsers user = (HhUsers) request.getSession().getAttribute(
				"gzwsession_users");
		String result = "";
		if (contract.getId() != null) {
			PjContractTemp entity = (PjContractTemp) commonService.findById(
					PjContractTemp.class, contract.getId());
			result = contractService.saveContractTemp(user, payRecordIds,
					contract, entity);
		} else {
			result = contractService.saveContractTemp(user, payRecordIds,
					contract, null);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("_delContractTemp")
	public String _delContractTemp(Integer id, Map<String, Object> map,
			HttpServletRequest request) {
		contractService.deleteContractTemp(id);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/_saveContract", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	public String _saveContract(String payRecordIds,PjContract contract ,Map<String, Object> map,HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		
		if (contract.getId() != null) {
			PjContract entity = (PjContract) commonService.findById(
					PjContract.class, contract.getId());
			contractService.saveContract(user, payRecordIds,
					contract, entity);
		} else {
			contractService.saveContract(user, payRecordIds,
					contract, null);
		}
		return "success";
	}
	
	@RequestMapping("_contractList")
	public String _contractList(Integer pjId,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  contractService.getContracts(pjId, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("pjId", pjId);
		//分页
		map.put("flag","contract");
		map.put("searchurl", request.getContextPath()+"/project/contract/_contractList");
		return "project/contractList";
	}
	
	@RequestMapping("_modifyContract")
	public String _modifyContract(Integer id, Map<String, Object> map,HttpServletRequest request){
		PjContract contract = (PjContract) commonService.findById(
				PjContract.class, id);
		
		if(contract.getPjId() != null){
			PjProject project = (PjProject) commonService.findById(PjProject.class, contract.getPjId());
			map.put("project", project);
		}
		map.put("contract", contract);
		map.put("isTemp", false);
		
		// 计划付款信息
		List<PjPcPayrecord> payrecords = pjPcPayrecordService
				.getPayrecords(contract.getId());
		map.put("payRecords", payrecords);
		
		// 初始化选项数据
		List<PjCode> contractTypes = pjCodeService
				.getPjCodes(Base.PJ_CONTRACT_TYPE);
		map.put("contractTypes", contractTypes);
		List<PjCode> moneyUnits = pjCodeService.getPjCodes(Base.PJ_MONEY_UNIT);
		map.put("moneyUnits", moneyUnits);
		List<PjCode> pcPayTypes = pjCodeService.getPjCodes(Base.PC_PAY_TYPE);
		map.put("pcPayTypes", pcPayTypes);

		return "/project/contractEdit";
	}
	
	@ResponseBody
	@RequestMapping("_delContract")
	public String _delContract(Integer id,Map<String, Object> map,HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
//		PjContract contract = (PjContract) commonService.findById(PjContract.class, id);
//		contract.setIsdel(1);
//		contract.setLastModifyDate(df.format(new Date()));
//		contract.setLastModifyPersonId(user.getVcEmployeeId());
//		contract.setLastModifyPersonName(user.getVcName());
//		commonService.saveOrUpdate(contract);
		contractService.deleteContract(id, user);
		return "success";
	}
	
	/**
	 * 项目详细中的合同列表
	 * @param pjId
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("_contractViewList")
	public String _contractViewList(Integer pjId,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  contractService.getContracts(pjId, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("pjId", pjId);
		String view = request.getParameter("view");
		map.put("view", view);
		//分页
		map.put("flag","contract");
		map.put("searchurl", request.getContextPath()+"/project/contract/_contractViewList");
		return "project/contractViewList";
	}
	
	@RequestMapping("_contractView")
	public String _contractView(Integer id, Map<String, Object> map,HttpServletRequest request){
		PjContract contract = (PjContract) commonService.findById(
				PjContract.class, id);
		
		if(contract.getPjId() != null){
			PjProject project = (PjProject) commonService.findById(PjProject.class, contract.getPjId());
			map.put("project", project);
		}
		map.put("contract", contract);
		map.put("isTemp", false);
		
		// 计划付款信息
		List<PjPcPayrecord> payrecords = pjPcPayrecordService
				.getPayrecords(contract.getId());
		map.put("payRecords", payrecords);
		
		// 初始化选项数据
		List<PjCode> contractTypes = pjCodeService
				.getPjCodes(Base.PJ_CONTRACT_TYPE);
		map.put("contractTypes", contractTypes);
		List<PjCode> moneyUnits = pjCodeService.getPjCodes(Base.PJ_MONEY_UNIT);
		map.put("moneyUnits", moneyUnits);
		List<PjCode> pcPayTypes = pjCodeService.getPjCodes(Base.PC_PAY_TYPE);
		map.put("pcPayTypes", pcPayTypes);

		//上报审核历史
		List<PjContractHistory> histories = contractService.getContractHistories(contract.getId());
		map.put("histories",histories);
				
		return "/project/contractView";
	}
	
	@ResponseBody
	@RequestMapping(value="/_validate" ,method=RequestMethod.POST,produces = "application/text; charset=utf-8"	)
	public String _validate(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjContract contract = (PjContract) commonService.findById(PjContract.class, id);
		String a = (String) JSON.toJSONString(contract);
		return a;
	}

}
