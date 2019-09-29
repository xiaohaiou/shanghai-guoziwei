package com.softline.controller.system;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.client.bima.IWsSystemService;
import com.softline.client.bima.WsSystemServiceService;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyLevelTree;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.BimcCompany;
import com.softline.entityTemp.CommitResult;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IFinanceHistroyTreeService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.ITreeService;

/**
 * 
 * @author sht
 * 
 * */
@Controller
@RequestMapping("/treeHistory")
public class TreeHistoryController {
	
	@Resource(name = "treeService")
	private ITreeService treeService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "financeHistroyTreeService")
	private IFinanceHistroyTreeService financeHistroyTreeService;
	
	/**
	 * 跳转页面
	 * 
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value = "/leveltree")
	public String leveltree(Integer type,Map<String ,Object> map,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
			map.put("type", type);
			
			
		    List<HhBase> statustype= baseService.getBases(Base.report_organal);
	    	map.put("statustype",statustype);
			
	    	WsSystemServiceService wsService = new WsSystemServiceService();
			IWsSystemService iw = wsService.getPort(IWsSystemService.class);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str=iw.getGSCompanyData();
			List<BimcCompany> data=JSON.parseArray(str, BimcCompany.class);
			
			//初始化时间为上个月
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			map.put("time", year + "-" + String.format("%02d", month));
	    	
			map.put("BimaCompany",data);
	    	
			return "/system/companyHistoryLevel";
	}
	
	/**
	 * 生成公司组织架构树
	 * 
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/_getleveltree", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void basicInfo_getleveltree(String userId, Integer type, String usersEmployeeId,String time,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		String json = "";
		if(Common.notEmpty(time)){
			CompanyLevelTree leveltree = treeService.getHistoryTree(type,time);
			json = JSONArray.toJSONString(leveltree);
		}else{
			CompanyLevelTree leveltree = createTree(type);
			json = JSONArray.toJSONString(leveltree);		
		}
		
		response.getWriter().write(json);
	}

	private CompanyLevelTree  createTree(Integer type)
	{
		
		CompanyLevelTree leveltree = treeService.getTree(type);
		return leveltree;
	}
	
	
	// 组织架构树变动
	@ResponseBody
	@RequestMapping(value = "/_changelevel", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _changelevel(int type ,String oldParent, String id,String newParent,int sort,String time,HttpServletRequest request) {
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		CommitResult result= treeService.updateHistorylevel(type,oldParent, id, newParent,sort,user,time);
		
		String a="";
		if(result!=null)
		{
			a=JSONArray.toJSONString(result);
			
		}
		return a;
	}

	//获取一个节点
	@ResponseBody
	@RequestMapping(value = "/_getnode", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _getnode(String  selectnode,int type,String time,HttpServletRequest request) {
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		HhOrganInfoTreeRelationHistory node=financeHistroyTreeService.getTreeOrganInfoNode(type, selectnode, time);
		String a="";
		if(node!=null)
		{
			a=JSONArray.toJSONString(node);
			
		}
		return a;
	}
	
	//编辑节点
	@ResponseBody
	@RequestMapping(value = "/_editnode", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _editnode(String vcFullName,String nNodeID,String vcShortName,int type,int status,Integer bimaID,String time,HttpServletRequest request) {
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		CommitResult result= treeService.saveEditHistoryNode(type, vcFullName, nNodeID, vcShortName,user,status,bimaID,time);
		
		String a="";
		if(result!=null)
		{
			a=JSONArray.toJSONString(result);
			
		}
		return a;
	}
	
	//固化财务树
	@ResponseBody
	@RequestMapping(value = "/saveGuhua", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveGuhua(String time,HttpServletRequest request) {
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		return treeService.saveGuahuaHistoryTree(time, user);
	}	
	
}