package com.softline.util;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.softline.entityTemp.CommitResult;
import com.softline.service.hr.ILaborCostService;

public class HrLaborCostTimeTask {
	
	@Resource(name = "laborCostService")
	private ILaborCostService laborCostService;
	 private Logger logger = Logger.getLogger(HrLaborCostTimeTask.class);
	
	
	public void synLaborCost() {
		try{
			System.out.println("开始调用人工成本与资源回报率接口");
			CommitResult result=laborCostService.synLaborCost();
			System.out.println("停止调用人工成本与资源回报率接口");
			logger.info(result.getExceptionStr());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	
	}


}
