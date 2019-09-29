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

import com.softline.common.Base;
import com.softline.entity.HhUsers;
import com.softline.entity.project.PjCode;
import com.softline.entity.project.PjContract;
import com.softline.entity.project.PjContractTemp;
import com.softline.entity.project.PjPcPayrecord;
import com.softline.entity.project.PjPcPayrecordTemp;
import com.softline.entity.project.PjProject;
import com.softline.service.project.IContractService;
import com.softline.service.project.IPjCodeService;
import com.softline.service.project.IPjPcPayrecordService;
import com.softline.service.system.ICommonService;

@Controller
@RequestMapping("/project/payRecord")
public class PayRecordController {
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "pjCodeService")
	private IPjCodeService pjCodeService;
	
	@Resource(name = "pjPcPayrecordService")
	private IPjPcPayrecordService pjPcPayrecordService;
	

	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("_addPayRecord")
	public String _addPayRecord(Integer pcId,Map<String ,Object> map, HttpServletRequest request){
		if(pcId != null){
			PjContract contract = (PjContract) commonService.findById(PjContract.class, pcId);
			map.put("contract", contract);
		}
		
		//初始化选项数据
		List<PjCode> pcPayCounts = pjCodeService.getPjCodes(Base.PC_PAY_COUNT); 
		map.put("pcPayCounts", pcPayCounts);
		List<PjCode> moneyUnits = pjCodeService.getPjCodes(Base.PJ_MONEY_UNIT); 
		map.put("moneyUnits", moneyUnits);
		map.put("isTemp",false);
		return "/project/conPayRecordEdit";
	}
	
	@RequestMapping("_addPayRecordTemp")
	public String _addPayRecordTemp(Integer pcId,Map<String ,Object> map, HttpServletRequest request){
		if(pcId != null){
			PjContractTemp contract = (PjContractTemp) commonService.findById(PjContractTemp.class, pcId);
			map.put("contract", contract);
		}
		
		//初始化选项数据
		List<PjCode> pcPayCounts = pjCodeService.getPjCodes(Base.PC_PAY_COUNT); 
		map.put("pcPayCounts", pcPayCounts);
		List<PjCode> moneyUnits = pjCodeService.getPjCodes(Base.PJ_MONEY_UNIT); 
		map.put("moneyUnits", moneyUnits);
		map.put("isTemp",true);
		return "/project/conPayRecordEdit";
	}
	
	@RequestMapping("_modifyPayRecordTemp")
	public String _modifyPayRecordTemp(Integer id,Map<String ,Object> map,HttpServletRequest request){
		PjPcPayrecordTemp payRecord = (PjPcPayrecordTemp) commonService.findById(PjPcPayrecordTemp.class, id);
		map.put("payRecord", payRecord);
		
		if(payRecord.getPcId() != null){
			PjContractTemp contract = (PjContractTemp) commonService.findById(PjContractTemp.class, payRecord.getPcId());
			map.put("contract", contract);
		}
		
		map.put("isTemp",true);
		
		//初始化选项数据
		List<PjCode> pcPayCounts = pjCodeService.getPjCodes(Base.PC_PAY_COUNT); 
		map.put("pcPayCounts", pcPayCounts);
		List<PjCode> moneyUnits = pjCodeService.getPjCodes(Base.PJ_MONEY_UNIT); 
		map.put("moneyUnits", moneyUnits);
		return "/project/conPayRecordEdit";
	}
	
	@RequestMapping("_modifyPayRecord")
	public String _modifyPayRecord(Integer id,Map<String ,Object> map,HttpServletRequest request){
		PjPcPayrecord payRecord = (PjPcPayrecord) commonService.findById(PjPcPayrecord.class, id);
		map.put("payRecord", payRecord);
		
		if(payRecord.getPcId() != null){
			PjContract contract = (PjContract) commonService.findById(PjContract.class, payRecord.getPcId());
			map.put("contract", contract);
		}
		
		map.put("isTemp",false);
		
		//初始化选项数据
		List<PjCode> pcPayCounts = pjCodeService.getPjCodes(Base.PC_PAY_COUNT); 
		map.put("pcPayCounts", pcPayCounts);
		List<PjCode> moneyUnits = pjCodeService.getPjCodes(Base.PJ_MONEY_UNIT); 
		map.put("moneyUnits", moneyUnits);
		
		return "/project/conPayRecordEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/_savePayRecordTemp" ,method=RequestMethod.POST,produces = "application/text; charset=utf-8"	)
	public String _savePayRecordTemp(PjPcPayrecordTemp payRecord ,Map<String ,Object> map, HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		String result = "";
		if(payRecord.getId()!=null){
			PjPcPayrecordTemp oldPayRecord = (PjPcPayrecordTemp) commonService.findById(PjPcPayrecordTemp.class, payRecord.getId());
			result = pjPcPayrecordService.savePayRecordTemp(user, payRecord,oldPayRecord);
		}else{
			result = pjPcPayrecordService.savePayRecordTemp(user, payRecord, null);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/_savePayRecord" ,method=RequestMethod.POST,produces = "application/text; charset=utf-8"	)
	public String _savePayRecord(PjPcPayrecord payRecord ,Map<String ,Object> map, HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		
		if(payRecord.getId()!=null){
			PjPcPayrecord oldPayRecord = (PjPcPayrecord) commonService.findById(PjPcPayrecord.class, payRecord.getId());
			pjPcPayrecordService.savePayRecord(user, payRecord, oldPayRecord);
		}else{
			pjPcPayrecordService.savePayRecord(user, payRecord, null);
		}
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("_delPayRecordTemp")
	public String _delPayRecordTemp(Integer id,Map<String ,Object> map,HttpServletRequest request){
		PjPcPayrecordTemp payRecord = (PjPcPayrecordTemp) commonService.findById(PjPcPayrecordTemp.class, id);
		commonService.delete(payRecord);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("_delPayRecord")
	public String _delPayRecord(Integer id,Map<String ,Object> map,HttpServletRequest request){
		PjPcPayrecord payRecord = (PjPcPayrecord) commonService.findById(PjPcPayrecord.class, id);
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		payRecord.setIsdel(1);
		payRecord.setLastModifyDate(df.format(new Date()));
		payRecord.setLastModifyPersonId(user.getVcEmployeeId());
		payRecord.setLastModifyPersonName(user.getVcName());
		commonService.saveOrUpdate(payRecord);
		return "success";
	}
	
	@RequestMapping("_payRecordList")
	public String _payRecordList(Integer pcId,Map<String ,Object> map,HttpServletRequest request){
		List<PjPcPayrecord> payRecords = pjPcPayrecordService.getPayrecords(pcId);
		map.put("pcId", pcId);
		map.put("payRecords", payRecords);
		return "project/payRecordList";
	}

}
