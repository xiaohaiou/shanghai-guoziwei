package com.softline.util;

import java.util.Hashtable;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import com.softline.common.Common;
import com.softline.entity.HhUsers;

/**
 * 先用自定义的缓存类来判断用户
 * 	是否重复登入
 * 
 * @author hwx
 */
public class LogionUtil implements HttpSessionListener{
	
	public static Hashtable<String,Object> logionUsers=new Hashtable<String,Object>();
		
	public boolean isLogion(String userName,HhUsers entityHhUsers) {
		if(!Common.notEmpty(userName))
			return false;
		
		HttpSession session = (HttpSession)logionUsers.get(userName);
		
		if(session == null) {
			return false;
		}else {
			try {
				session.invalidate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		logionUsers.remove(userName);
		return true;
	}

	@Override
    public void sessionCreated(HttpSessionEvent se){}
   
    @Override    
    public void sessionDestroyed(HttpSessionEvent event){
//		HttpSession session=event.getSession();
//		HhUsers hhUsers=(HhUsers)session.getAttribute("gzwsession_users");
//		if(null!=hhUsers && null!=hhUsers.getVcAccount()) 
//			logionUsers.remove(hhUsers.getVcAccount());
    	
    }
    
}
