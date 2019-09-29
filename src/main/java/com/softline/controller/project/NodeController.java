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
import com.softline.entity.project.PjContract;
import com.softline.entity.project.PjNode;
import com.softline.entity.project.PjNodeHistory;
import com.softline.entity.project.PjNodeTemp;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjProjectHistory;
import com.softline.service.project.INodeService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/project/node")
public class NodeController {

	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "nodeService")
	private INodeService nodeService;

	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping("_addNode")
	public String _addNode(Integer pjId, Map<String, Object> map,
			HttpServletRequest request) {
		if (pjId != null) {
			PjProject project = (PjProject) commonService.findById(
					PjProject.class, pjId);
			map.put("project", project);
		}
		return "/project/nodeEdit";
	}

	@RequestMapping("_modifyNodeTemp")
	public String _modifyNodeTemp(Integer id, Map<String, Object> map,
			HttpServletRequest request) {
		PjNodeTemp node = (PjNodeTemp) commonService.findById(PjNodeTemp.class,
				id);
		map.put("node", node);
		return "/project/nodeEdit";
	}
	
	@RequestMapping("_modifyNode")
	public String _modifyNode(Integer pjId,Integer id, Map<String, Object> map,
			HttpServletRequest request) {
		PjNode node = (PjNode) commonService.findById(PjNode.class,
				id);
		if (pjId != null) {
			PjProject project = (PjProject) commonService.findById(
					PjProject.class, pjId);
			map.put("project", project);
		}
		map.put("node", node);
		return "/project/nodeEdit";
	}

	@ResponseBody
	@RequestMapping(value = "/_saveNodeTemp", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	public String _saveNodeTemp(PjNodeTemp node, Map<String, Object> map,
			HttpServletRequest request) {
		HhUsers user = (HhUsers) request.getSession().getAttribute(
				"gzwsession_users");
		if (node.getId() == null) {// add
			node.setCreateDate(df.format(new Date()));
			node.setCreatePersonId(user.getVcEmployeeId());
			node.setCreatePersonName(user.getVcName());
			node.setIsdel(0);
			node.setReportStatus(0);
		} else {// modify
			PjNodeTemp entity = (PjNodeTemp) commonService.findById(
					PjNodeTemp.class, node.getId());
			node.setCreateDate(entity.getCreateDate());
			node.setCreatePersonId(entity.getCreatePersonId());
			node.setCreatePersonName(entity.getCreatePersonName());
			node.setLastModifyDate(df.format(new Date()));
			node.setLastModifyPersonId(user.getVcEmployeeId());
			node.setLastModifyPersonName(user.getVcName());
			node.setIsdel(0);
			node.setReportStatus(0);

		}
		commonService.saveOrUpdate(node);
		String resultStr = JSON.toJSONString(node);
		return resultStr;

	}
	@ResponseBody
	@RequestMapping(value = "/_saveNode", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	public String _saveNode(PjNode node, Map<String, Object> map,HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute(
				"gzwsession_users");
		
		if (node.getId() == null) {// add
			node.setCreateDate(df.format(new Date()));
			node.setCreatePersonId(user.getVcEmployeeId());
			node.setCreatePersonName(user.getVcName());
			node.setIsdel(0);
			node.setVersion(0);
			node.setReportStatus(0);//未上报

		} else {// modify
			PjNode entity = (PjNode) commonService.findById(
					PjNode.class, node.getId());
			node.setCreateDate(entity.getCreateDate());
			node.setCreatePersonId(entity.getCreatePersonId());
			node.setCreatePersonName(entity.getCreatePersonName());
			
			node.setLastModifyDate(df.format(new Date()));
			node.setLastModifyPersonId(user.getVcEmployeeId());
			node.setLastModifyPersonName(user.getVcName());
			
			node.setReportStatus(entity.getReportStatus());
			node.setReportPersonId(entity.getReportPersonId());
			node.setReportPersonName(entity.getReportPersonName());
			node.setReportTime(entity.getReportTime());
			
			node.setVersion(entity.getVersion());
			
			node.setIsdel(0);
			//每次审核时将记录的version+1
			if(entity.getReportStatus()>1){//已审核、已退回的节点被修改后，其状态变更为“未上报”
				node.setReportStatus(0);//未上报
			}
		}
		commonService.saveOrUpdate(node);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/_delNode")
	public String _delNode(Integer id, Map<String, Object> map,
			HttpServletRequest request) {
		HhUsers user = (HhUsers) request.getSession().getAttribute(
				"gzwsession_users");
//		PjNode entity = (PjNode) commonService.findById(
//				PjNode.class, id);
//		entity.setIsdel(1);
//		entity.setLastModifyDate(df.format(new Date()));
//		entity.setLastModifyPersonId(user.getVcEmployeeId());
//		entity.setLastModifyPersonName(user.getVcName());
//		commonService.saveOrUpdate(entity);
		nodeService.deleteNode(id, user);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/_delNodeTemp")
	public String _delNodeTemp(Integer id, Map<String, Object> map,
			HttpServletRequest request) {
		PjNodeTemp entity = (PjNodeTemp) commonService.findById(
				PjNodeTemp.class, id);
		commonService.delete(entity);
		return "success";
	}
	
	@RequestMapping(value = "/_nodeList")
	public String _nodeList(Integer pjId,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  nodeService.getNodes(pjId, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("pjId", pjId);
		String view = request.getParameter("view");
		map.put("view", view);
		//分页
		map.put("flag","node");
		map.put("searchurl", request.getContextPath()+"/project/node/_list");
		
		return "project/nodeList";
	}
	
	/**
	 * 进入projectDetail.jsp页面中的nodeList
	 * @param pjId
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/_nodeViewList")
	public String _nodeViewList(Integer pjId,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  nodeService.getNodes(pjId, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("pjId", pjId);
		String view = request.getParameter("view");
		map.put("view", view);
		//分页
		map.put("flag","node");
		map.put("searchurl", request.getContextPath()+"/project/node/_list");
		
		return "project/nodeViewList";
	}
	
	@RequestMapping("_nodeView")
	public String _nodeView(Integer id, Map<String, Object> map,
			HttpServletRequest request) {
		PjNode node = (PjNode) commonService.findById(PjNode.class,
				id);
		if (node.getPjId() != null) {
			PjProject project = (PjProject) commonService.findById(
					PjProject.class, node.getPjId());
			map.put("project", project);
		}
		map.put("node", node);
		//上报审核历史
		List<PjNodeHistory> histories = nodeService.getNodehHistories(node.getId());
		map.put("histories",histories);
		
		return "/project/nodeView";
	}
	
	@ResponseBody
	@RequestMapping(value="/_validate" ,method=RequestMethod.POST,produces = "application/text; charset=utf-8"	)
	public String _validate(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjNode node = (PjNode) commonService.findById(PjNode.class, id);
		String a = (String) JSON.toJSONString(node);
		return a;
	}
}
