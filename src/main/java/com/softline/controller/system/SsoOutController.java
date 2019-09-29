package com.softline.controller.system;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author wyh 
 * 
 * */
@Controller
@RequestMapping("/ssoout")
public class SsoOutController {
	


	@RequestMapping("outsystem")
	public String outsystem(String url, Map<String ,Object> map,HttpServletRequest request) throws MalformedURLException, IOException {
		HttpSession session=request.getSession();
		String token= (String) session.getAttribute("gzwsession_token");
		String systemCode= "shanghai-gzw";
		if(!url.endsWith("?") && !url.contains("?"))
			url+="?";
		else
			url+="&";
		if(!"true".equals(request.getParameter("isopen")))
		{
			Enumeration em = request.getSession().getAttributeNames();
			while(em.hasMoreElements()){
			   request.getSession().removeAttribute(em.nextElement().toString());
			}

		}
		//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		String basePath = "https://"+request.getServerName();
		if(url.startsWith("http://") || url.startsWith("https://")){
			return "redirect:"+url+"token="+token+"&systemCode="+systemCode;
		}
		return "redirect:"+basePath+url+"token="+token+"&systemCode="+systemCode;
	}
}