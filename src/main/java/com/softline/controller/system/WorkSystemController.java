package com.softline.controller.system;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
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

import com.softline.client.show.IShowWebservice;
import com.softline.client.show.ShowWebserviceService;
import com.softline.common.Common;
import com.softline.entity.HhOperationLog;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;
import com.softline.entity.SysUsers;
import com.softline.service.portal.INewsService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISsoService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * 
 * @author wyh 
 * 
 * */
@Controller
@RequestMapping("/system")
public class WorkSystemController {

	@Resource(name = "newsService")
	private INewsService newsService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "ssoService")
	private ISsoService ssoService;
	

	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@RequestMapping("ladp_portal")
	public String  ladp_portal(String vcEmployeeId,String forwardType,Map<String ,Object> map,HttpServletRequest request ) {
		HttpSession session = request.getSession();
		String token= (String) session.getAttribute("gzwsession_token");
		map.put("token", token);

		//tch这里添加页面信息的加载
		
//		HhUsers entityHhUsers = systemService.getEmployeeById(vcEmployeeId);
//		request.getSession().setAttribute("gzwsession_users", entityHhUsers);
//		systemService.SSoSetAuthirity(entityHhUsers.getVcEmployeeId(),request);
		
		//滚动消息
		String curPageNum = request.getParameter("pageNum");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		PortalNews entity  = new PortalNews();
		entity.setIsIssue(1);
		MsgPage msgPage = newsService.getPortalNewsList(entity, pageNum, 10);
		map.put("msgPage", msgPage);
		return "workbench";
	}
	
	@RequestMapping("/menu")
	public String  menu(Integer menu,Map<String ,Object> map,HttpServletRequest request ) {
		map.put("menu", menu);
		if(menu == 2){//财务
			return "/system/financeMenu";
		}
		if(menu == 7){
			return "/system/bimrMenu";
		}
		return "/system/menu";
	}

	@RequestMapping("/reportFormsMenu")
	public String  reportFormsMenu(Integer menu,Map<String ,Object> map,HttpServletRequest request ) {
		map.put("menu", menu);
		return "/system/reportFormsMenu";
	}
	
	@RequestMapping("_exit")
	public String _exit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		HhOperationLog hhOperationLog = new HhOperationLog();
		String date = Common.getDate(new Date(),"yyyy-MM-dd HH:mm:ss");
		hhOperationLog.setName(user.getVcAccount());
        hhOperationLog.setOperationTime(date);
        hhOperationLog.setModel("注销");
        hhOperationLog.setDescription("注销记录:注销！");
        systemService.saveHhOperationLogList(hhOperationLog);
		session.invalidate();
		
		return "redirect:/";
		
	}
	
	@ResponseBody
	@RequestMapping("getAlarmTime")
	public String getAlarmTime(PortalMsg portalMsg, Map<String, Object> map,HttpServletRequest request){
		ShowWebserviceService service = new ShowWebserviceService();
		IShowWebservice is = service.getShowWebservicePort();
		String alarmTimeCount = is.alarmTimeOutCount();
		return alarmTimeCount;
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/_zcYuJing", method = RequestMethod.POST)
	public Map<String, Object> _zcYuJing(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException { 
		Map<String, Object> map = new HashMap<String, Object>();
		SysUsers user = (SysUsers) request.getSession().getAttribute("gzwsession_users");
		WsSystemServiceService wsService = new WsSystemServiceService();
		IWsSystemService iw = wsService.getPort(IWsSystemService.class);
		String vcEmployeeId = user.getVcEmployeeId();
		//获取工商基本信息中经营异常的公司
		String errorCompanyStr = iw.getAbnormalmessage01(user.getVcEmployeeId());
		int errComNum = 0;
		if(Common.notEmpty(errorCompanyStr) && !errorCompanyStr.equals("null") && !errorCompanyStr.equals("")){
			errComNum = Integer.valueOf(errorCompanyStr);
		}
		map.put("errComNum", errComNum);
		//获取董监高风险对比
		String djgStr = iw.getManagerCompare01(user.getVcEmployeeId());
		int djgNum = 0;
		if(Common.notEmpty(djgStr) && !djgStr.equals("null") && !djgStr.equals("")){
			djgNum = Integer.valueOf(djgStr);
		}
		map.put("djgNum", djgNum);
		//获取工商任务待处理
		String gsProcessStr = iw.getGsProcess01(vcEmployeeId);
		int gsProcessNum = 0;
		if(Common.notEmpty(gsProcessStr) && !gsProcessStr.equals("null") && !gsProcessStr.equals("")){
			gsProcessNum = Integer.valueOf(gsProcessStr);
		}
		map.put("gsProcessNum", gsProcessNum);
		//获取商标超时提醒
		String gsBrandStr = iw.getGsBrand01(vcEmployeeId);
		int gsBrandNum = 0;
		if(Common.notEmpty(gsBrandStr) && !gsBrandStr.equals("null")  && !gsBrandStr.equals("")){
			gsBrandNum = Integer.valueOf(gsBrandStr);
		}
		map.put("gsBrandNum", gsBrandNum);
		return map;
	}*/
}
