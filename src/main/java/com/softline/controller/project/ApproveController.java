package com.softline.controller.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.softline.common.Common;
import com.softline.entity.HhUsers;
import com.softline.entity.project.PjApprove;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjWeekreport;
import com.softline.entityTemp.VContractChange;
import com.softline.entityTemp.VNodeChange;
import com.softline.entityTemp.VProjectChange;
import com.softline.service.project.IPjApproveService;
import com.softline.service.project.IWkReportService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/project/approve")
public class ApproveController {
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "pjApproveService")
	private IPjApproveService pjApproveService;
	
	@Resource(name = "wkReportService")
	private IWkReportService wkReportService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@RequestMapping("_list")
	public String _list(PjProject project,String pjDeptId,Map<String , Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
		HttpSession session=request.getSession();
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        //数据权限
        String str=(String) session.getAttribute("gzwsession_company");
//        String dataauth=systemService.getDataauth(str);
//        if(!Common.notEmpty(pjDeptId))
//        	pjDeptId = dataauth;
        Integer pageNum = Integer.valueOf(curPageNum);
        HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
 		MsgPage msgPage =  pjApproveService.getPjProjects(user.getVcEmployeeId(),pjDeptId,project, pageNum, 10);
		map.put("project", project);
		map.put("msgPage", msgPage);
		map.put("searchurl", request.getContextPath()+"/project/approve/_list");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "project/approveList";
	}
	
	@RequestMapping("_approveDetail")
	public String _approveDetail(Integer id,Map<String , Object> map,HttpServletRequest request){
		
		PjProject project = (PjProject) commonService.findById(PjProject.class, id);
		map.put("project", project);
		//得到基本新变化
		List<VProjectChange> projectChanges = pjApproveService.getProjectChanges(id);
		map.put("projectChanges", projectChanges);
		
		//节点信息变化
		List<VNodeChange> nodeChanges = pjApproveService.getNodeChanges(id);
		map.put("nodeChanges", nodeChanges);
		
		//周报
		List<PjWeekreport> wkReports =  wkReportService.getWkReports(id, 1);//得到待审核的周报
		map.put("wkReports", wkReports);
		
		//合同信息变化
		List<VContractChange> contractChanges = pjApproveService.getContractChanges(id);
		map.put("contractChanges", contractChanges);
		
		return "project/approveDetail";
	}
	
	@ResponseBody
	@RequestMapping("_approveproject")
	public String _approveProject(String pjId,PjApprove approve,Map<String , Object> map,HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		approve.setCreateDate(df.format(new Date()));
		approve.setCreatePersonId(user.getVcEmployeeId());
		approve.setCreatePersonName(user.getVcName());
		approve.setApproveIds(pjId+"");
		return pjApproveService.saveProjectApprove(approve);
		
	}
	
	@ResponseBody
	@RequestMapping("_approvenode")
	public String _approveNode(String nodeIds,PjApprove approve,Map<String , Object> map,HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		approve.setCreateDate(df.format(new Date()));
		approve.setCreatePersonId(user.getVcEmployeeId());
		approve.setCreatePersonName(user.getVcName());
		approve.setApproveIds(nodeIds);
		return pjApproveService.saveNodeApprove(nodeIds, approve);
	}
	
	@ResponseBody
	@RequestMapping("_approvewkReport")
	public String _approveWkReport(String wkReportIds,PjApprove approve,Map<String , Object> map,HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		approve.setCreateDate(df.format(new Date()));
		approve.setCreatePersonId(user.getVcEmployeeId());
		approve.setCreatePersonName(user.getVcName());
		approve.setApproveIds(wkReportIds);
		return pjApproveService.saveWkReportApprove(wkReportIds, approve);
	}
	
	@ResponseBody
	@RequestMapping("_approvecontract")
	public String _approveContract(String contractIds,PjApprove approve,Map<String , Object> map,HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		approve.setCreateDate(df.format(new Date()));
		approve.setCreatePersonId(user.getVcEmployeeId());
		approve.setCreatePersonName(user.getVcName());
		approve.setApproveIds(contractIds);
		return pjApproveService.saveContractApprove(contractIds, approve);
	}
	
	
}
