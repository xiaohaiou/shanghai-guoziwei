package com.softline.controller.system;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.softline.common.Common;
import com.softline.common.ReadProperties;
import com.softline.entity.HhOperationLog;
import com.softline.entity.HhUsers;
import com.softline.service.system.ISsoService;
import com.softline.service.system.ISystemService;
import com.softline.util.LogionUtil;
import com.softline.util.MD5;

@Controller
@RequestMapping("/sys/adConnect")
public class AdConnectController {

	static Logger log = Logger.getLogger(AdConnectController.class
			.getName());
	static ReadProperties config = null;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "ssoService")
	private ISsoService ssoService;
	
	
	@ResponseBody
	@RequestMapping(value = "/_adConnect", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String isLog(String username, String password,HttpServletResponse response,
			HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {	
		HhUsers entityHhUsers = systemService.getEmployeeByName(username);	
		String passwordMd5 = MD5.getMD5(password);
		HttpSession session = request.getSession();
		HhOperationLog hhOperationLog = new HhOperationLog();
		String date = Common.getDate(new Date(),"yyyy-MM-dd HH:mm:ss");
		hhOperationLog.setName(username);
        hhOperationLog.setOperationTime(date);
        hhOperationLog.setModel("登录");
        /**
		 * 不允许重复登入
		 */		
		LogionUtil logionUtil = new LogionUtil();
		if(username!=null && logionUtil.isLogion(username, entityHhUsers)){
//			return "failByTwo";
		}
		System.out.println(session.getId());
		Integer n =(Integer) session.getAttribute(username+"loginTimes");
		if(n==null){
			n=0;
		}
		if(n>5) {
			hhOperationLog.setDescription("登录记录:登录超时！");
	        systemService.saveHhOperationLogList(hhOperationLog);
			return "outOfTimes";
		}
		if(entityHhUsers == null || !(passwordMd5.equals(entityHhUsers.getPassword()))) {
			session.setAttribute(username+"loginTimes", ++n);
			hhOperationLog.setDescription("登录记录:登录失败！");
	        systemService.saveHhOperationLogList(hhOperationLog);
			return "fail";
		}
		
		session.setAttribute("gzwsession_users", entityHhUsers);		
		
		systemService.SSoSetAuthirity(entityHhUsers.getVcEmployeeId(),request);
		LogionUtil.logionUsers.put(username, session);
		return "success";
	}	
	
	 private static Hashtable<String, String> initADServer(String host, String port, String domain,
				String domainNode, String username, String password) {
			String account = username + (StringUtils.isEmpty(domain) ? "" : ("@" + domain));
			Hashtable<String, String> HashEnv = new Hashtable<String, String>();
	        HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别(none,simple,strong)
			HashEnv.put(Context.SECURITY_PRINCIPAL, account); // AD的用户名
			HashEnv.put(Context.SECURITY_CREDENTIALS, password); // AD的密码
			HashEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类
			HashEnv.put("com.sun.jndi.ldap.connect.timeout", "3000");// 连接超时设置为3秒
			HashEnv.put(Context.PROVIDER_URL, "ldap://"+host+":"+port);// 默认端口389
	        return HashEnv;
	    }

	
	
	@ResponseBody
	@RequestMapping(value = "/_toVisit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String toVisit(HttpServletRequest request){
		return "success";
	}
	
	

}
