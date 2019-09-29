package com.softline.util;

import javax.annotation.Resource;

import com.softline.entity.HhUsers;
import com.softline.entity.bylaw.BylawSynLock;
import com.softline.service.bylaw.IBylawInfoService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.ICommonService;


public class  BylawTimeTask {

	@Resource(name = "bylawInfoService")
	private IBylawInfoService bylawInfoService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	public void synBylawInfo(){
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
	}


}