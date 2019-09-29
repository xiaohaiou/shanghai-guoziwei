package com.softline.controller.settingCenter.portal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.softline.entity.HhOperationLog;
import com.softline.service.portal.ILogService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/portal/log")
public class PortalLogController {
	
	@Resource(name = "logService")
	private ILogService logService;	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/_query")
	public String _query(HhOperationLog portalLog, Map<String ,Object> map, HttpServletRequest request){
		String curPageNum = request.getParameter("pageNum");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        
        //进入时默认时间为当天
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if(portalLog.getStarttime() == null || "".equals(portalLog.getStarttime()))
        	portalLog.setStarttime(df.format(date));
        MsgPage msgPage = logService.getPortalMsgLogList(portalLog, pageNum, 10);
        List<HhOperationLog> msgList = msgPage.getList();
		map.put("msgPage", msgPage);		
        map.put("portalLog", portalLog);
		map.put("logList", msgList);
		return "/settingCenter/portal/logList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/_list", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _list(HhOperationLog portalLog, Map<String, Object> map, Integer pageNum, HttpServletResponse response, HttpServletRequest request) throws IOException {
		MsgPage msgPage = logService.getPortalMsgLogList(portalLog, pageNum, 10);
		map.put("msgPage", msgPage);
		request.setAttribute("", msgPage);
		List<HhOperationLog> msgList = msgPage.getList();
		
		if((!msgList.isEmpty()) && msgList.size()>0){
			StringBuffer msgListStr = new StringBuffer();
			msgListStr.append("<input type='hidden' id='pageNum' name='pageNum' value='"+pageNum+"'>");
			msgListStr.append("<input type='hidden' id='totalPage' name='totalPage' value='"+msgPage.getTotalPage()+"'>");
			String jsonString = JSON.toJSONString(msgListStr.toString());
			return jsonString;
		}
		return "";
	}
	
}
