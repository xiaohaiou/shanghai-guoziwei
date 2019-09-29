package com.softline.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;


import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrComplianceBJInfo;
import com.softline.entity.bimr.BimrCompliancePerson;
import com.softline.entity.bimr.BimrComplianceProgress;
import com.softline.entity.bimr.BimrCompliancePrompt;
import com.softline.entity.bimr.BimrComplianceSituation;
import com.softline.entity.bimr.BimrComplianceSituationPerson;
import com.softline.entity.bimr.BimrComplianceSuggest;
import com.softline.entityTemp.CommitResult;
import com.softline.service.bimr.IComplianceService;
import com.softline.service.bimr.impl.ComplianceService;
import com.sun.org.apache.bcel.internal.generic.NEW;



public class ComplianceTimerTask{
	 private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 private Logger logger = Logger.getLogger(ComplianceTimerTask.class);
	 @Resource
	 private IComplianceService complianceService;
	 //private IComplianceService complianceService = (IComplianceService) ApplicationContextUtil.getContext().getBean("complianceService");
	 
	   /* @Override
	    public void run() {
	        try {
	             //在这里写你要执行的内容
	        	boolean b=complianceService.getSuggestAndEmail();
	        	if(b){
	        		logger.info("发送成功");
	        	}else{
	        		logger.info("发送失败");
	        	}
	        		
	        	logger.info("执行当前时间"+formatter.format(Calendar.getInstance().getTime()));
	        } catch (Exception e) {
	        	logger.error(e.getMessage());
	        }
	    }*/
	 public void synIComplianceEmail() {
			try{
				boolean b=complianceService.getSuggestAndEmail();
				if(b){
	        		logger.info("发送成功");
	        	}else{
	        		logger.info("发送失败");
	        	}
				logger.info("执行当前时间"+formatter.format(Calendar.getInstance().getTime()));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		
		}
}
