package com.softline.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.softline.common.ConstConfig;

public class illegalFiter implements Filter{
	FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

    	HttpServletRequest r = (HttpServletRequest) request;
		Map<String, String[]> params = r.getParameterMap();
		String path=r.getServletPath();
		// System.out.println(request.getContentType()) ;
		
		if(path != null && (path.equalsIgnoreCase("/sys/illegal") ||  path.equalsIgnoreCase("/sys/illegaljson" +ConstConfig.JsonSuffix )) )
		{
			chain.doFilter(request, response);
		}else
		{
			
			/*if(isConcheck(r.getRequestURL().toString()))
			{
				 RequestDispatcher rd = null;
				 if( path.endsWith(ConstConfig.JsonSuffix))
				 {
					 rd=request.getRequestDispatcher("/sys/illegaljson" +ConstConfig.JsonSuffix );
				 }else
					 rd=request.getRequestDispatcher("/sys/illegal");
				  
				 rd.forward(request, response);
				 return ;
			}*/

			 path= java.net.URLDecoder.decode(r.getRequestURL().toString(),"UTF-8");
			/*System.out.println(path);
			System.out.println(r.getQueryString());*/
			String ql=r.getQueryString();
			
//			if(isConcheck(path))
//			{
//				 RequestDispatcher rd = null;
//				 if( path.endsWith(ConstConfig.JsonSuffix))
//				 {
//					 rd=request.getRequestDispatcher("/sys/illegaljson" +ConstConfig.JsonSuffix );
//				 }else
//					 rd=request.getRequestDispatcher("/sys/illegal");
//				  
//				 rd.forward(request, response);
//				 return ;
//			}
/*				if(ql!=null )
				{
					ql= java.net.URLDecoder.decode(ql,"UTF-8");
					
					if(isConcheck(ql))
					{
					 RequestDispatcher rd = null;
					 if( path.endsWith(ConstConfig.JsonSuffix))
					 {
						 rd=request.getRequestDispatcher("/sys/illegaljson" +ConstConfig.JsonSuffix );
					 }else
						 rd=request.getRequestDispatcher("/sys/illegal");
					  
					 rd.forward(request, response);
					 return ;
					}
				}*/
			
			for (String key : params.keySet()) {
				String[] values = params.get(key);
				for (int i = 0; i < values.length; i++) {
					 if( values[i]== null)
						 continue;
					 if(isConcheck(values[i]))
					 {
						 RequestDispatcher rd = null;
						 if( path.endsWith(ConstConfig.JsonSuffix))
						 {
							 rd=request.getRequestDispatcher("/sys/illegaljson" +ConstConfig.JsonSuffix );
						 }else
							 rd=request.getRequestDispatcher("/sys/illegal");
						  
						 rd.forward(request, response);
						 return ;
					 }
				}

			}
			
			chain.doFilter(request, response);
			
		}
        
    }
    
    private boolean  isConcheck(String value)
    {
    	if(value==null)
    		return false;
    	String temp=value.toLowerCase();
    	return  
    			temp.indexOf("\r\n")>-1 
    			|| temp.indexOf("\n")>-1
    			|| temp.indexOf(" and ")>-1 
    			|| temp.indexOf(" exec ")>-1
    			|| temp.indexOf(" count ")>-1
    			|| temp.indexOf(" chr ")>-1
    			|| temp.indexOf(" mid ")>-1
    			|| temp.indexOf(" master ")>-1
    			|| temp.indexOf(" or ")>-1
    			|| temp.indexOf(" truncate ")>-1
    			|| temp.indexOf(" char ")>-1
    			|| temp.indexOf(" declare ")>-1
    			|| temp.indexOf(" join ")>-1
    			
    			
    			|| temp.indexOf("insert")>-1
    			|| temp.indexOf("select")>-1
    			|| temp.indexOf("delete")>-1
    			|| temp.indexOf("update")>-1
    			|| temp.indexOf("create")>-1
    			|| temp.indexOf("drop")>-1
    			
    			
    			
    			|| temp.indexOf("<")>-1
    			|| temp.indexOf(">")>-1
    			|| temp.indexOf("/*")>-1
    			|| temp.indexOf("*/")>-1
    			|| temp.indexOf("’")>-1
    			|| temp.indexOf("|")>-1
    			|| temp.indexOf(";")>-1
    			|| temp.indexOf("&")>-1
    			|| temp.indexOf("$")>-1
    			|| temp.indexOf("%")>-1
    			|| temp.indexOf("\"")>-1
    			|| temp.indexOf("\\'")>-1
    			|| temp.indexOf("\'")>-1
    			|| temp.indexOf("\\\"")>-1
    			|| temp.indexOf("()")>-1
    			|| temp.indexOf("+")>-1
    			|| temp.indexOf(",")>-1
    			|| temp.indexOf("+")>-1
    			
    			|| temp.indexOf("10x0d")>-1
    			|| temp.indexOf("10X0A")>-1
    			|| temp.indexOf("10X0a")>-1
    			|| temp.indexOf("0x0a")>-1
    			|| temp.indexOf("0x0d")>-1
    			//|| temp.indexOf("@")>-1
    			;
    }
    
}
