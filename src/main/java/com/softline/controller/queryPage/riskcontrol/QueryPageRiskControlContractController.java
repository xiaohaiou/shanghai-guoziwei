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
import com.softline.entity.RiskControlContract;
import com.softline.entity.SysExamine;
import com.softline.service.riskcontrol.IRiskControlContractService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/riskcontrol/contract")
public class QueryPageRiskControlContractController {
	
	@Resource(name = "riskControlContractService")
	private IRiskControlContractService riskControlContractService;
	
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
	public String queryContractList(RiskControlContract entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
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
	    map.put("searchurl", "/shanghai-gzw/queryPage/riskcontrol/contract/list");
	    map.put("entity", entity);
	    List<HhBase> reportStatus = baseService.getBases(Base.examstatustype);
	    map.put("reportStatus",reportStatus);
	    List<HhOrganInfo> auditDepts = systemService.getOrganInfos(Base.HRTopPrefix);
		map.put("auditDepts", auditDepts);
		List<HhBase> contractType = baseService.getBases(Base.contract_type);
	    map.put("contractType",contractType);
	    map.put("contractSignDate2", contractSignDate2);
		return "/queryPage/riskcontrol/contract/contractList";
	}
	
	@RequestMapping("/view")
	public String viewContract(RiskControlContract entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		RiskControlContract riskControlContract = riskControlContractService.getContract(entity);
		map.put("riskControlContract", riskControlContract);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_riskcontrol_contract);
		map.put("examineList", examineList);
		return "/queryPage/riskcontrol/contract/contractView";
	}
	
}
