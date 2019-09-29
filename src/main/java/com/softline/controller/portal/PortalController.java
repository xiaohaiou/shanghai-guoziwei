package com.softline.controller.portal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.softline.client.task.TaskInstruction;
import com.softline.client.task.TaskProgress;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.AuditProject;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/protal")
public class PortalController {
	@Resource(name = "potalMsgService")
	private IPortalMsgService portalMsgService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@ResponseBody
	@RequestMapping(value = "/_getPortalMsg", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _getTask(HttpServletResponse response, HttpServletRequest request) throws IOException { 
		HhUsers hhUsers = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		String session_code = request.getSession().getAttribute("gzwsession_code").toString();
		String session_company = request.getSession().getAttribute("gzwsession_company").toString();
		 //财务数据权限
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
        String financedataauth=systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
        if(Common.notEmpty(session_company) && Common.notEmpty(financedataauth))
        	session_company=session_company+","+financedataauth;
        else if(Common.notEmpty(financedataauth))
        	session_company=financedataauth;
        else if(!Common.notEmpty(session_company) && !Common.notEmpty(financedataauth))
        	return "";
        String jsonString="";
		List<PortalMsg> msgList = new ArrayList();
		msgList = portalMsgService.getProtalMsgList(session_code, session_company, hhUsers);
		if((!msgList.isEmpty()) && msgList.size()>0){
			StringBuffer msgListStr = new StringBuffer();
			for (int i = 0; i < msgList.size(); i++) {
				PortalMsg temp = (PortalMsg)msgList.get(i);
				if(Common.notEmpty(temp.getUrl())){
					msgListStr.append("<p style=\"cursor: pointer;\" title=\""+temp.getTitle()+"\">");
					msgListStr.append("<a style=\"color: #babcbf;cursor: pointer;\"  target=\"_blank\">");
					msgListStr.append(temp.getTitle());
					msgListStr.append("</a>");
				}else{
					msgListStr.append("<p>");
					msgListStr.append(temp.getTitle());
				}
				msgListStr.append("</p>");
			}
			jsonString = msgListStr.toString();
		}else {
			StringBuffer msgListStr = new StringBuffer();
			msgListStr.append("<div class=\"_img\">");
			msgListStr.append("<img src=\"../img/warncenter.png\"/>");
			
			msgListStr.append("</div>");
			jsonString = msgListStr.toString();	
		}
		return jsonString;
	}
	
	@RequestMapping(value = "/list")
	public String _query(PortalMsg entity, Map<String ,Object> map, HttpServletRequest request){
		String mapurl = request.getContextPath()+ "/protal";
		map.put("mapurl", mapurl);
		 map.put("searchurl", "/shanghai-gzw/protal/list");
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        HhUsers hhUsers = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		String session_code = request.getSession().getAttribute("gzwsession_code").toString();
		String session_company = request.getSession().getAttribute("gzwsession_company").toString();
		 //财务数据权限
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
        String financedataauth=systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
        if(Common.notEmpty(session_company) && Common.notEmpty(financedataauth))
        	session_company=session_company+","+financedataauth;
        else if(Common.notEmpty(financedataauth))
        	session_company=financedataauth;
        else if(!Common.notEmpty(session_company) && !Common.notEmpty(financedataauth))
        	session_company = "";
        if (entity.getDelFlag()==null) {
			entity.setDelFlag(0);
		}
        MsgPage msgPage = portalMsgService.getPortalMsgsList3(session_code, session_company, hhUsers, entity, pageNum, 10);
		map.put("msgPage", msgPage);
		List<PortalNews> newsList = msgPage.getList();
		map.put("newsList", newsList);
		map.put("entity", entity);
		return "/portal/msgList";
	}
	
	@RequestMapping(value ="/searchMsg")
	public String searchMsg(@ModelAttribute("auditProject")PortalMsg entity, Map<String ,Object> map, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String title = new String(request.getParameter("title").getBytes("iso8859-1"),"utf-8");
		String description = new String(request.getParameter("description").getBytes("iso8859-1"),"utf-8");
		String delFlag = new String(request.getParameter("delFlag").getBytes("iso8859-1"),"utf-8");
		if(Common.notEmpty(title)){
			entity.setTitle(title);
		}
		if(Common.notEmpty(description)){
			entity.setDescription(description);
		}
		if(Common.notEmpty(delFlag)){
			entity.setDelFlag(Integer.parseInt(delFlag));
		}
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HhUsers hhUsers = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		String session_code = request.getSession().getAttribute("gzwsession_code").toString();
		String session_company = request.getSession().getAttribute("gzwsession_company").toString();
		 //财务数据权限
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
        String financedataauth=systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
        if(Common.notEmpty(session_company) && Common.notEmpty(financedataauth))
        	session_company=session_company+","+financedataauth;
        else if(Common.notEmpty(financedataauth))
        	session_company=financedataauth;
        else if(!Common.notEmpty(session_company) && !Common.notEmpty(financedataauth))
        	session_company = "";
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = portalMsgService.getPortalMsgsList3(session_code, session_company, hhUsers, entity, pageNum, 10);
		map.put("msgPage", msgPage);
		return "/portal/msgSearchList";
	}

}
