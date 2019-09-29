package com.softline.common;

import java.lang.reflect.Field;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class LoginLogLogAspect {
	@Resource(name ="operationLogService")
//	private IOperationLogService operationLogService;

	private static final Log logger = LogFactory.getLog(OperationLogAspect.class);
	
	// 声明一个切入点,切入点的名称其实是一个方法
/*	@Pointcut("execution (* com.softline.service..*.getHhUsersByEntity(..))")
	private void getMethod() {
	}
	
	@AfterReturning("getMethod()")
	public void doAfterReturningGet(JoinPoint joinPoint) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		this.logOperation(joinPoint, session, "登陆");
	}*/
	
/*	public void logOperation(JoinPoint joinPoint,HttpSession session,String op) throws Exception{
		String method = joinPoint.getSignature().getName();
		if(method.equals("getHhUsersByEntity")){
//			String met = "请求方法:" + joinPoint.getTarget().getClass().getName() 
//					+ "." + joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
	        if(arguments.length > 0){
	        	HhUsers user = (HhUsers)arguments[0];
	        	String date = Common.getDate(new Date(),"yyyy-MM-dd HH:mm:ss");
	        	String met = user.getVcAccount()+"{"+user.getVcName()+ "}"+"在"+date+"登陆系统";
	        	Class c = arguments[0].getClass();
	        	Field[] fields = c.getDeclaredFields();
		        for(Field f : fields){
		        	f.setAccessible(true);
		        	String name = f.getName();
		        	if(name.equalsIgnoreCase("id")||name.equalsIgnoreCase("basicId")||name.equalsIgnoreCase("financeId")||name.equalsIgnoreCase("pledgeId")||name.equalsIgnoreCase("manageId")){
		        		HhOperationLog hhOperationLog = new HhOperationLog();
		        		String field = f.toString().substring(f.toString().lastIndexOf(".")+1);
		                hhOperationLog.setName(user.getVcAccount());
		                hhOperationLog.setOperationTime(date);
		                hhOperationLog.setModel(c.getName());
		                hhOperationLog.setDescription(op+"记录:"+met);
		                operationLogService.logHhOperationLogList(hhOperationLog);
		        	}
		        }
	        }
		}
	
	}*/
}
