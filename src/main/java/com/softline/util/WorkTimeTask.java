package com.softline.util;

import javax.annotation.Resource;

import com.softline.entity.bylaw.BylawSynLock;
import com.softline.service.bylaw.IBylawInfoService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.ICommonService;


public class  WorkTimeTask {

	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "bylawInfoService")
	private IBylawInfoService bylawInfoService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	public void SynBimaCompany() {
		try{
			//同步规章制度信息
			BylawSynLock lock = (BylawSynLock) commonService.findById(BylawSynLock.class, 1);
			if(lock.getStatus() == 0){
				try{
					lock.setStatus(1);
					commonService.saveOrUpdate(lock);
					bylawInfoService.xsaveSynBylawInfo(null);
				}finally{
					lock.setStatus(0);
					commonService.saveOrUpdate(lock);
				}
			}
			//selectUserService.saveSynBimaCompany();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}


}