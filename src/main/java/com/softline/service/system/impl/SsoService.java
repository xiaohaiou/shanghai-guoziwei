package com.softline.service.system.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.softline.client.sso.AuthorityServiceService;
import com.softline.client.sso.IAuthorityService;
import com.softline.client.sso.SsoCheckResult;
import com.softline.service.system.ISsoService;

@Service("ssoService")
public class SsoService implements ISsoService{


	public SsoCheckResult checkLogin(HttpServletRequest request,String begin,String end)
	{
		String token=(String) request.getParameter("token");;
		String ip=getIP(request);
		 AuthorityServiceService wsService = new AuthorityServiceService();
         IAuthorityService iw = wsService.getPort(IAuthorityService.class);
         SsoCheckResult result= iw.checkLogin(token, ip, begin, end);
         return result;
	}
	
	public String getIP(HttpServletRequest request)
	{
		String ip = request.getHeader("X-Forwarded-For");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("X-Real-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    return ip;
	}
	
	
	public SsoCheckResult cancelLogin(HttpServletRequest request)
	{
		String token=(String) request.getSession().getAttribute("gzwsession_token");
		String ip=getIP(request);
		 AuthorityServiceService wsService = new AuthorityServiceService();
         IAuthorityService iw = wsService.getPort(IAuthorityService.class);
         SsoCheckResult result= iw.cancelLogin(token, ip);
         return result;
	}
	
	public SsoCheckResult addLogin(String vcAccount,HttpServletRequest request,String systemcode)
	{
		AuthorityServiceService wsService = new AuthorityServiceService();
        IAuthorityService iw = wsService.getPort(IAuthorityService.class);
        String ip=getIP(request);
        SsoCheckResult sso= iw.addNewLogin(vcAccount, ip,systemcode);
        saveUserToSession(sso,request);
        return sso;
	}
	
	
	private void saveUserToSession(SsoCheckResult sso,HttpServletRequest request  )
	{
		HttpSession session = request.getSession();
		if(sso.isResult()!=false)
		{
			session.setAttribute("gzwsession_token", sso.getToken());
			com.softline.entity.HhUsers user=JSON.parseObject(sso.getUserData(), com.softline.entity.HhUsers.class);
			if(user!=null)
			{
				session.setAttribute("gzwsession_users", user);
				session.setAttribute("gzwsession_usersID", user.getVcEmployeeId());
			}
		}
		
	}
}
