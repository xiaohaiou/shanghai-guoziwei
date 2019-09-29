package com.softline.service.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.softline.client.sso.SsoCheckResult;

public interface ISsoService {

	
	public SsoCheckResult addLogin(String vcAccount,HttpServletRequest request,String systemcode);
	public SsoCheckResult checkLogin(HttpServletRequest request,String begin,String end);
	public String getIP(HttpServletRequest request);
	public SsoCheckResult cancelLogin(HttpServletRequest request);
}
