package com.softline.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartResolver;

public class XssFilter implements Filter{
	FilterConfig filterConfig = null;
	private MultipartResolver multipartResolver = null; 

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        multipartResolver = ((MultipartResolver)ApplicationContextUtil.getContext().getBean("multipartResolver", MultipartResolver.class));
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	request = getRequest(request);
        chain.doFilter(new XssHttpServletRequestWrapper(
                (HttpServletRequest) request), response);
    }
    
    /* 获取 request 
     * @param req 
     * @return 
     */  
    private ServletRequest getRequest(ServletRequest req){  
        String enctype = req.getContentType();  
        if (StringUtils.isNotBlank(enctype) && enctype.contains("multipart/form-data"))  
            // 返回 MultipartHttpServletRequest 用于获取 multipart/form-data 方式提交的请求中 上传的参数  
            return multipartResolver.resolveMultipart((HttpServletRequest) req);  
        else   
            return req;  
    }  
}
