package com.softline.controller.warncenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.DataWorktableWarningCenter;
import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;
import com.softline.service.system.ISystemService;
import com.softline.service.warncenter.IWarningCenterService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/warncenter")
public class WarnController {
	
	@Resource(name = "warningCenterService")
	private IWarningCenterService warningCenterService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@ResponseBody
	@RequestMapping(value = "/_getWarnlMsg", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _getTask(HttpServletResponse response, HttpServletRequest request) throws IOException { 
		
//		//获取访问权限和数据权限
//		HttpSession session=request.getSession();
//		String session_company = session.getAttribute("gzwsession_company").toString();//公司权限
//		String financedataauth=(String) session.getAttribute("gzwsession_financecompany");//财务数据权限
//		String pageAuthor=(String) session.getAttribute("gzwsession_page");//页面权限
//		//设置访问权限和数据权限
//		HashMap<String,String> authorMap = new HashMap<String,String>();
//		try {
//			financedataauth = systemService.getAllChildrenFinanceOrganal(financedataauth,Base.financetype);
//			session_company = systemService.getDataauthNnodeIDs(session_company);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		authorMap.put("companyAuthor", session_company);
//		authorMap.put("financeCompanyAuthor", financedataauth);
//		authorMap.put("pageAuthor", pageAuthor);
//		//设置查询条件
//		MsgPage msgPage = warningCenterService.dataShow(new DataWorktableWarningCenter(), 1,5, authorMap);
//
//        String jsonString="";
//        List<DataWorktableWarningCenter> msgList = msgPage.getList();
//        List<String> backInfoList = new ArrayList<String>();
//		if((!msgList.isEmpty()) && msgList.size()>0){
//			StringBuffer msgListStr = new StringBuffer();
//			msgListStr.append("<div class=\"_p\">");
//			for (int i = 0; i < msgList.size(); i++) {
//				DataWorktableWarningCenter temp = (DataWorktableWarningCenter)msgList.get(i);
//				if(Common.notEmpty(temp.getUrl())){
//					msgListStr.append("<a style=\"color: #babcbf;cursor: pointer;word-wrap:break-word;\"  target=\"_blank\">");
//					msgListStr.append(temp.getOrgname()+temp.getYear()+"年"+temp.getMonth()+"月"+temp.getModel_name());
//					if(50112==temp.getStatus())
//						msgListStr.append("未及时上报！");
//					if(50113==temp.getStatus())
//						msgListStr.append("未及时审核！");
//					if(51111==temp.getStatus())
//						msgListStr.append("未及时填报！");					
//					msgListStr.append("</a>");
//					msgListStr.append("<br/>");
//					msgListStr.append("<hr class=\"_hr\"/>");
//				}else{
//					msgListStr.append("<p>");
//					msgListStr.append((i+1)+"、"+temp.getOrgname()+"公司"+temp.getYear()+"年"+temp.getMonth()+"月");
//					msgListStr.append("未及时处理！");
//					msgListStr.append("</p>");
//				}
//			}
//			msgListStr.append("</div>");
//			jsonString = msgListStr.toString();
//			backInfoList.add("true");
//			backInfoList.add(jsonString);
//		}else{
//			StringBuffer msgListStr = new StringBuffer();
//			msgListStr.append("<div class=\"_img\">");
//			msgListStr.append("<img src=\"../img/warncenter.png\"/>");
//			
//			msgListStr.append("</div>");
//			jsonString = msgListStr.toString();		
//			backInfoList.add("false");
//			backInfoList.add(jsonString);
//		}
//		return JSONArray.toJSONString(backInfoList);
		return "";
	}
	
	@RequestMapping(value = "/list")
	public String _query(PortalMsg entity, Map<String ,Object> map, HttpServletRequest request){
//		String mapurl = request.getContextPath()+ "/warncenter";
//		map.put("mapurl", mapurl);
//		map.put("searchurl", "/shanghai-gzw/warncenter/list");
//		 //设置查询条件
//		DataWorktableWarningCenter searchBean = new DataWorktableWarningCenter();
//		String curPageNum = request.getParameter("pageNums");
//		String time = request.getParameter("time");
//		String status = request.getParameter("status");
//		String model_id = request.getParameter("model_id");
//		if (curPageNum == null) { //页码
//        	curPageNum = "1";
//        }
//        Integer pageNum = Integer.valueOf(curPageNum);
//        if(Common.notEmpty(time)){ //时间
//        	String year = time.substring(0,4);
//        	String month = time.substring(5,7);
//        	searchBean.setYear(year);
//        	searchBean.setMonth(month);
//        	map.put("time", time);
//        }
//        if(Common.isNumber(status)){//状态
//        	searchBean.setStatus(Integer.valueOf(status));
//        	map.put("status", status);
//        }
//        if(Common.notEmpty(model_id)){//模块编号
//        	searchBean.setModel_id(model_id);
//        	map.put("model_id", model_id);
//        }
//
//		//获取访问权限和数据权限
//		HttpSession session=request.getSession();
//		String session_company = session.getAttribute("gzwsession_company").toString();//公司权限
//		String financedataauth=(String) session.getAttribute("gzwsession_financecompany");//财务数据权限
//		String pageAuthor=(String) session.getAttribute("gzwsession_page");//页面权限
//		try {
//			//添加下拉选择框，选项
//			List<String[]> warnCenterDataOptions = warningCenterService.getSelectOptionsForWarnCenter();
//			session.setAttribute("warnCenterDataOptions", warnCenterDataOptions);
//			//设置访问权限和数据权限
//			HashMap<String,String> authorMap = new HashMap<String,String>();
//			financedataauth = systemService.getAllChildrenFinanceOrganal(financedataauth,Base.financetype);
//			session_company = systemService.getDataauthNnodeIDs(session_company);
//			authorMap.put("companyAuthor", session_company);
//			authorMap.put("financeCompanyAuthor", financedataauth);
//			authorMap.put("pageAuthor", pageAuthor);
//			//设置查询条件
//			MsgPage msgPage = warningCenterService.dataShow(searchBean, pageNum,10,authorMap);
//			map.put("msgPage", msgPage);
//			List<PortalNews> newsList = msgPage.getList();
//			map.put("newsList", newsList);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		map.put("entity", entity);
		return "/warn/msgList";
	}

	@RequestMapping(value = "/recordlist")
	public String recordlist(Map<String ,Object> map, HttpServletRequest request){
		String mapurl = request.getContextPath()+ "/warncenter";
		map.put("mapurl", mapurl);
		map.put("searchurl", "/shanghai-gzw/warncenter/recordlist");
		//设置查询条件
		String model_id = request.getParameter("model_id");
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) { //页码
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);

		//设置查询条件
		MsgPage msgPage = warningCenterService.getReportData(model_id,pageNum,10);
		map.put("msgPage", msgPage);
		map.put("model_id", model_id);
		return "/warn/msgRecordList";
	}
	
	
}
