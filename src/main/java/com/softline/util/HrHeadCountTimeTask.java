package com.softline.util;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.softline.entityTemp.CommitResult;
import com.softline.service.hr.IHeadCountService;

public class HrHeadCountTimeTask {
	
	@Resource(name = "headCountService")
	private IHeadCountService headCountService;
	 private Logger logger = Logger.getLogger(HrHeadCountTimeTask.class);
	
	
	public void synHeadCount() {
		try{
			System.out.println("开始调用总人数与劳动生产率接口");
			CommitResult result=headCountService.synHeadCount();
			System.out.println("结束调用总人数与劳动生产率接口");
			logger.info(result.getExceptionStr());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	
	}


}
