package com.softline.util;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.softline.entity.bylaw.BylawSynLock;
import com.softline.entityTemp.CommitResult;
import com.softline.service.administration.IAdDocumentService;
import com.softline.service.administration.IAdSuperviseService;
import com.softline.service.bylaw.IBylawInfoService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.ICommonService;


public class  AdSuperviseTimeTask {

	@Resource(name = "adSuperviseService")
	private IAdSuperviseService adSuperviseService;
	 private Logger logger = Logger.getLogger(AdSuperviseTimeTask.class);
	
	
	public void synISupervise() {
		try{
			System.out.println("开始");
			CommitResult result=adSuperviseService.savesynISupervise();
			System.out.println("结束");
			logger.info(result.getExceptionStr());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	
	}


}