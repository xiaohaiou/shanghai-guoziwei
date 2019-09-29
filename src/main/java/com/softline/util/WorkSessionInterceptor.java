package com.softline.util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.softline.client.sso.SsoCheckResult;
import com.softline.entity.HhUsers;
import com.softline.entity.SysUsers;
import com.softline.service.system.ISsoService;
import com.softline.service.system.ISystemService;

public class WorkSessionInterceptor implements HandlerInterceptor {
	private static final String LOGIN_URL = "/";
	@Resource(name = "ssoService")
	private ISsoService ssoService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	@Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler) throws Exception {
		HttpSession session=request.getSession(true);  
	    String url=request.getRequestURI();
        String  token =request.getParameter("token");
        String  systemCode =request.getParameter("systemCode");
        if(token!=null && !token.equals("")&& systemCode!=null && !systemCode.equals("") )
        {
    		SsoCheckResult sso= ssoService.checkLogin(request,systemCode,request.getContextPath().replace("/", "") );
    		if(sso.isResult()!=false)
    		{
    			session.setAttribute("gzwsession_token", sso.getToken());
    			com.softline.entity.HhUsers user=JSON.parseObject(sso.getUserData(), com.softline.entity.HhUsers.class);
    			if(user!=null)
    			{
    				session.setAttribute("gzwsession_users", user);
    				systemService.SSoSetAuthirity(user.getVcEmployeeId(),request);
    				
    				return true;
    			}	
    		}
    		else
    			session.removeAttribute("gzwsession_users");
        }
//        if(null==session || null==session.getAttribute("gzwsession_users")) {
//        	request.getRequestDispatcher("/login.jsp").forward(request, response);
//        	return false;
//        }
        //session中获取用户名信息  
        com.softline.entity.HhUsers users =  null;
        try {
        	users = (com.softline.entity.HhUsers) session.getAttribute("gzwsession_users");
		} catch (Exception e) {
			// TODO: handle exception
		}
        if (users==null) {  
        	String basePath = "http://"+request.getServerName()+"/shanghai-gzw";
        	response.getWriter().print("<html><script type='text/JavaScript'> top.location.href='"+basePath+"'</script></html>");
            response.setContentType("text/html");
        	return false;
        }
        return true;
		/*HhUsers use=new HhUsers();
	    use.setVcEmployeeId("9999999901");
	    use.setVcName("系统管理员");
	    HttpSession session= request.getSession();
	    session.setAttribute("gzwsession_users", use);
	    return true;*/
     }
}
