package com.softline.controller.system;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.softline.entity.HhOrganInfoTreeRelationLog;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.BimcCompany;
import com.softline.entityTemp.CommitResult;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.ITreeService;
import com.softline.util.MsgPage;

/**
 * 
 * @author wyh 
 * 
 * */
@Controller
@RequestMapping("/tree")
public class TreeController {
	
	@Resource(name = "treeService")
	private ITreeService treeService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	
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
	    	
			map.put("BimaCompany",data);
	    	
			return "/system/companyLevel";
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
	public String _changelevel(int type ,String oldParent, String id,String newParent,int sort,HttpServletRequest request) {
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		CommitResult result= treeService.updatelevel(type,oldParent, id, newParent,sort,user);
		
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
	public String _getnode(String  selectnode,int type,HttpServletRequest request) {
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		HhOrganInfoTreeRelation node=systemService.getTreeOrganInfoNode(type, selectnode);
		String a="";
		if(node!=null)
		{
			a=JSONArray.toJSONString(node);
			
		}
		return a;
	}
	
	//新增节点
	@ResponseBody
	@RequestMapping(value = "/_newnode", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _addnode(String responsiblePersonName,String vcEmployeeID,String responsiblePersonEmail,
		    String vcFullName,String parentID,String vcShortName,int type,int status,Integer bimaID,HttpServletRequest request) {
HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
CommitResult result= treeService.savenewnode(parentID,type, vcFullName, vcShortName,user,status,
							bimaID,responsiblePersonName,vcEmployeeID,responsiblePersonEmail);

		String a="";
		if(result!=null)
		{
			a=JSONArray.toJSONString(result);
			
		}
		return a;
	}
	
	
	//编辑节点
	@ResponseBody
	@RequestMapping(value = "/_editnode", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _editnode(String responsiblePersonName,String vcEmployeeID,String responsiblePersonEmail,
			String vcFullName,String nNodeID,String vcShortName,int type,int status,Integer bimaID,HttpServletRequest request) {
HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
CommitResult result= treeService.saveeditnode(type, vcFullName, nNodeID, vcShortName,user,status,
			bimaID,responsiblePersonName,vcEmployeeID,responsiblePersonEmail);

		String a="";
		if(result!=null)
		{
			a=JSONArray.toJSONString(result);
			
		}
		return a;
	}
	
	//删除节点
	@ResponseBody
	@RequestMapping(value = "/_delnode", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _delnode(int type,String selectnode,HttpServletRequest request) {
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		CommitResult result= treeService.deletenode(type,selectnode,user);
		String a="";
		if(result!=null)
		{
			a=JSONArray.toJSONString(result);
			
		}
		return a;
	}
	
	
	/**
	 * 进入财务树固化操作页面
	 * @param type
	 * @param map
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/guhuaPage")
	public String guhuaPage(Map<String ,Object> map,String starttime,
			HttpServletResponse response, HttpServletRequest request){
		
		if(null!=starttime){
			map.put("time", starttime);				
		}
		return "/system/guhuaPage";
	}
	
	//固化财务树
	@ResponseBody
	@RequestMapping(value = "/saveGuhua", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveGuhua(String time,HttpServletRequest request) {
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		return treeService.saveGuahuaTree(time, user);
	}	
	
	// 组织架构树变动
	@ResponseBody
	@RequestMapping(value = "/getLastModifyTime", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getLastModifyTime(HttpServletRequest request) {
		String lastModifyTime = "";
		
		lastModifyTime = treeService.getFinancialTreeLastModifyTime();
		
		
		String a="";
		if(lastModifyTime!=null)
		{
			a=JSONArray.toJSONString(lastModifyTime);
			
		}
		return a;
	}
	
	@RequestMapping(value = "/getTreeLog")
	public String getFinanceTreeLog(String date1,
									String date2,
									String orgName,
									String operate_type,
									Map<String ,Object> map,
									HttpServletResponse response, 
									HttpServletRequest request){
		
		String mapurl=request.getContextPath()+ "/getTreeLog";				
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
		
        HhOrganInfoTreeRelationLog searchBean = new HhOrganInfoTreeRelationLog();
        String year = null;
        String month = null;
        String day = null;
        if(Common.notEmpty(date1)){
        	year = date1.substring(0, 4);
        	month = date1.substring(5,7);
        	day = date1.substring(8,10);
        }		
        searchBean.setYear(year);
        searchBean.setMonth(month);
        searchBean.setDay(day);
        searchBean.setVcFullName(orgName);
        searchBean.setOperate_type(operate_type);
        
        MsgPage msgPage = treeService.getTreeOperationLog(searchBean,Integer.valueOf(curPageNum),Base.pagesize,date2);
		
        map.put("msgPage", msgPage);
        map.put("date1", date1);
        map.put("date2", date2);
        map.put("orgName", orgName);
        map.put("operate_type", operate_type);
		return "/system/companyLevelTreeLog";
	}
	
}