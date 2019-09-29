package com.softline.common;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.softline.entity.HhOperationLog;
import com.softline.entity.SysUsers;
import com.softline.service.system.ISystemService;

/**
 * 
 * 记录用户增、删、改操作
 * 
 * @author wyh
 * 
 */

@Aspect
@Component
public class OperationLogAspect {
	
@Resource(name ="systemService")

	private ISystemService systemService;

	private static final Log logger = LogFactory.getLog(OperationLogAspect.class);

	// 声明一个切入点,切入点的名称其实是一个方法
	@Pointcut("execution (* com.softline.controller.system.AdConnectController.isLog(..))")
	private void logMethod() {
	}
	
	// 声明一个切入点,切入点的名称其实是一个方法
	@Pointcut("execution (* com.softline.controller.system.AdConnectController.toVisit(..))")
	private void visitMethod() {
	}
	
	// 声明一个切入点,切入点的名称其实是一个方法
//	@Pointcut("execution (* com.softline.service..*.getHhUsersByEntity*(..))")
//	private void getMethod() {
//	}
	
//	@AfterReturning("logMethod()")
//	public void doAfterReturningGet(JoinPoint joinPoint) throws Exception {
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpSession session = request.getSession();
//		this.logOperation(joinPoint, session, "登录");
//	}
	
	@Before("logMethod()")
	public void doBeforeLogin(JoinPoint joinPoint) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
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
		
		this.logOperation(joinPoint, session, "登录", ip);
	}
	
	public void logOperation(JoinPoint joinPoint,HttpSession session,String op,String address) throws Exception{
		String met = "ip地址为:" + address + "的用户尝试登陆本系统！";
        Object[] arguments = joinPoint.getArgs();
        
        if(arguments.length > 0){
        	String date = Common.getDate(new Date(),"yyyy-MM-dd HH:mm:ss");
        	Class c = arguments[0].getClass();
        	HhOperationLog hhOperationLog = new HhOperationLog();
        	hhOperationLog.setName(arguments[0]==null?"":arguments[0].toString());
            hhOperationLog.setOperationTime(date);
            hhOperationLog.setModel(c.getName());
            hhOperationLog.setDescription(op+"记录:"+met);
            systemService.saveHhOperationLogList(hhOperationLog);
        }
		
//		if(session.getAttribute("session_HhUsers") != null){
//			HhUsers loginuser = (HhUsers) session.getAttribute("session_HhUsers");
//		
//			String met = "请求方法:" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
//	        Object[] arguments = joinPoint.getArgs();
//	        
//	        if(arguments.length > 0){
//	        	HhUsers user = (HhUsers)arguments[0];
//	        	String date = Common.getDate(new Date(),"yyyy-MM-dd HH:mm:ss");
//	        	Class c = arguments[0].getClass();
//	        	HhOperationLog hhOperationLog = new HhOperationLog();
//	        	hhOperationLog.setName(user.getVcAccount());
//                hhOperationLog.setOperationTime(date);
//                hhOperationLog.setModel(c.getName());
//                hhOperationLog.setDescription(op+"记录:"+met);
//                systemService.saveHhOperationLogList(hhOperationLog);
//	        }
//		}
//	        if(arguments.length > 0){
//	        	Class c = arguments[0].getClass();
//	        	Field[] fields = c.getDeclaredFields();
//		        for(Field f : fields){
//		        	f.setAccessible(true);
//		        	String name = f.getName();
//		        	if(name.equalsIgnoreCase("id")||name.equalsIgnoreCase("basicId")||name.equalsIgnoreCase("financeId")||name.equalsIgnoreCase("pledgeId")||name.equalsIgnoreCase("manageId")){
//		        		HhOperationLog hhOperationLog = new HhOperationLog();
//		        		String field = f.toString().substring(f.toString().lastIndexOf(".")+1);
//		                hhOperationLog.setName(loginuser.getVcName());
//		                hhOperationLog.setOperationTime(Common.getDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
//		                hhOperationLog.setModel(c.getName());
//		                hhOperationLog.setDescription(op+field+"为"+f.get(arguments[0])+"的记录,"+met);
//		                operationLogService.logHhOperationLogList(hhOperationLog);
//		        	}
//		        }
//	        }
	} 
		
//		String method = joinPoint.getSignature().getName();
//		if(method.equals("getHhUsersByEntity")){
////			String met = "请求方法:" + joinPoint.getTarget().getClass().getName() 
////					+ "." + joinPoint.getSignature().getName();
//			Object[] arguments = joinPoint.getArgs();
//	        if(arguments.length > 0){
//	        	HhUsers user = (HhUsers)arguments[0];
//	        	String date = Common.getDate(new Date(),"yyyy-MM-dd HH:mm:ss");
//	        	String met = user.getVcAccount()+"在"+date+"登录系统";
//	        	Class c = arguments[0].getClass();
//	        	Field[] fields = c.getDeclaredFields();
//		        for(Field f : fields){
//		        	f.setAccessible(true);
//		        	String name = f.getName();
//		        	if(name.equalsIgnoreCase("id")||name.equalsIgnoreCase("basicId")||name.equalsIgnoreCase("financeId")||name.equalsIgnoreCase("pledgeId")||name.equalsIgnoreCase("manageId")){
//		        		HhOperationLog hhOperationLog = new HhOperationLog();
//		        		String field = f.toString().substring(f.toString().lastIndexOf(".")+1);
//		                hhOperationLog.setName(user.getVcAccount());
//		                hhOperationLog.setOperationTime(date);
//		                hhOperationLog.setModel(c.getName());
//		                hhOperationLog.setDescription(op+"记录:"+met);
//		                operationLogService.logHhOperationLogList(hhOperationLog);
//		        	}
//		        }
//	        }
//		}
//	}
	@Before("visitMethod()")
	public void doBeforeVisit(JoinPoint joinPoint) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
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
		
		this.visitOperation(joinPoint, session, "登录", ip);
	}
	
	public void visitOperation(JoinPoint joinPoint,HttpSession session,String op,String address) throws Exception{
		String met = "ip地址为:" + address + "的用户尝试访问本系统！";
        Object[] arguments = joinPoint.getArgs();
        
        if(arguments.length > 0){
        	String date = Common.getDate(new Date(),"yyyy-MM-dd HH:mm:ss");
        	Class c = arguments[0].getClass();
        	HhOperationLog hhOperationLog = new HhOperationLog();
        	hhOperationLog.setName(arguments[0]==null?"":arguments[0].toString());
            hhOperationLog.setOperationTime(date);
            hhOperationLog.setModel(c.getName());
            hhOperationLog.setDescription(op+"记录:"+met);
            systemService.saveHhOperationLogList(hhOperationLog);
        }
	}
}
	
	
	
