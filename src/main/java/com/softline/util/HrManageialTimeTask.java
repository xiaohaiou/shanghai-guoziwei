package com.softline.util;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.softline.entityTemp.CommitResult;
import com.softline.service.hr.IManagerialStaffService;

public class HrManageialTimeTask {
	
	@Resource(name = "managerialStaffService")
	private IManagerialStaffService managerialStaffService;
	 private Logger logger = Logger.getLogger(HrManageialTimeTask.class);
	
	
	public void synManageial() {
		try{
			 System.out.println("开始调用管理干部人数接口");
			CommitResult result=managerialStaffService.synManageial();
			System.out.println("停止调用管理干部人数接口");
			logger.info(result.getExceptionStr());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	
	}


}
