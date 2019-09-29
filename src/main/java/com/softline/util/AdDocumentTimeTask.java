package com.softline.util;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.softline.entity.bylaw.BylawSynLock;
import com.softline.entityTemp.CommitResult;
import com.softline.service.administration.IAdDocumentService;
import com.softline.service.bylaw.IBylawInfoService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.ICommonService;


public class  AdDocumentTimeTask {

	@Resource(name = "adDocumentService")
	private IAdDocumentService adDocumentService;
	 private Logger logger = Logger.getLogger(AdDocumentTimeTask.class);
	
	
	public void synIDocument() {
		try{
			CommitResult result=adDocumentService.synIDocument();
			logger.info(result.getExceptionStr());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	
	}


}