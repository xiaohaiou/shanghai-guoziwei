package com.softline.util.warncentertask;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.softline.service.warncenter.IWarningCenterOneYearMonthService;
import com.softline.service.warncenter.IWarningCenterService;
import com.softline.service.warncenter.IWarningCenterTopCompanyService;
import com.softline.service.warncenter.IWarningCenterYearService;

public class WarningCenter {
	
	@Resource(name = "warningCenterService")
	private IWarningCenterService warningCenterService;
	@Resource(name = "warningCenterYearService")
	private IWarningCenterYearService warningCenterYearService;
	@Resource(name = "warningCenterOneYearMonthService")
	private IWarningCenterOneYearMonthService warningCenterOneYearMonthService;
	@Resource(name = "warningCenterTopCompanyService")
	private IWarningCenterTopCompanyService warningCenterTopCompanyService;	
	
	private static final Logger log = Logger.getLogger(WarningCenter.class);
	
	public void run(){
		
		WarningCenterTask wct = new WarningCenterTask(warningCenterService);
		WarningCenterYearTask wcyt = new WarningCenterYearTask(warningCenterService,warningCenterYearService);	
		WarningCenterOneYearMonthTask wcoymt = new WarningCenterOneYearMonthTask(warningCenterService,warningCenterOneYearMonthService);
		WarningCenterTopCompanyTask wctct = new WarningCenterTopCompanyTask(warningCenterService,warningCenterTopCompanyService);
		
		if(log.isInfoEnabled()){
			log.info("开始执行预警中心转换程序......");			
		}
		// 执行1
		try {
			wct.run();
		} catch (Exception e) {}
		// 执行2	
		try {
			wcyt.run();
		} catch (Exception e) {}
		// 执行3
		try {
			wcoymt.run();
		} catch (Exception e) {}
		// 执行4
		try {
			wctct.run();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(log.isInfoEnabled()){
			log.info("预警中心转换程序运行结束！");			
		}
	}

}
