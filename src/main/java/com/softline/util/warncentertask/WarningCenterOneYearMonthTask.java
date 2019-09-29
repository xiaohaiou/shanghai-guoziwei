package com.softline.util.warncentertask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import com.softline.entityTemp.DataWarningCenterParameter;
import com.softline.service.warncenter.IWarningCenterOneYearMonthService;
import com.softline.service.warncenter.IWarningCenterService;
import com.softline.service.warncenter.IWarningCenterYearService;

public class WarningCenterOneYearMonthTask {
	
	private IWarningCenterService warningCenterService;
	private IWarningCenterOneYearMonthService warningCenterOneYearMonthService;
	
	public WarningCenterOneYearMonthTask(IWarningCenterService warningCenterService,
										 IWarningCenterOneYearMonthService warningCenterOneYearMonthService){
		this.warningCenterService=warningCenterService;
		this.warningCenterOneYearMonthService=warningCenterOneYearMonthService;
	}
	
	private static final Logger log = Logger.getLogger(WarningCenterOneYearMonthTask.class);
	
	public void run(){
		//获取参数
		List<DataWarningCenterParameter> parameterList = warningCenterService.getAllParameter();
		String type = null;
		// 执行未上报、未审核插入操作
		for(DataWarningCenterParameter tempBean:parameterList){
			type = tempBean.getSql_label().split("_")[tempBean.getSql_label().split("_").length-1];
			//插入上个月没有填报公司
			if("oneYearMonthIn".equals(type)){
				waringCenterInsertUnfilledDataWorkTable(tempBean);
			}
			//删除上个月没有填报公司
			if("oneYearMonthOut".equals(type)){
				waringCenterRemoveUnfilledDataWorkTable(tempBean);
			}
		}	
	}

	/**
	 * 插入未填报公司数据
	 * @param parameterListReportedOrExmineOut
	 */
	public void waringCenterInsertUnfilledDataWorkTable(DataWarningCenterParameter parameterListReportedOrExmineOut){
		/*
		 * 插入当年未及时填报数据 
		 * @param strArr 
		 * 	长度为6
		 * 		参数分别是 	parameterArr[0] sql_element   sql标签
		 * 				parameterArr[1] model_id      模块编号
		 * 				parameterArr[2] page_author   页面权限
		 * 				parameterArr[3] model_name    模块名称
		 * 				parameterArr[4] url           url
		 * 				parameterArr[5] year          年
		 * 				parameterArr[6] month         月
		 * @return
		 */
		String[] passedReportParaArr = new String[7];
		String date = transferDate();
		String year = date.substring(0,4);
		String month = date.substring(5,7);
		if(parameterListReportedOrExmineOut!=null){
			passedReportParaArr[0] = parameterListReportedOrExmineOut.getSql_label();
			passedReportParaArr[1] = parameterListReportedOrExmineOut.getModel_id();
			passedReportParaArr[2] = parameterListReportedOrExmineOut.getPage_author();
			passedReportParaArr[3] = parameterListReportedOrExmineOut.getModel_name();			
			passedReportParaArr[4] = parameterListReportedOrExmineOut.getUrl();				
			passedReportParaArr[5] = year;
			passedReportParaArr[6] = month;	
			warningCenterOneYearMonthService.dataInsertUnfilledDataWorkTable(passedReportParaArr);
		}	
	}
	
	/**
	 * 删除上个月已经填报公司的数据
	 * @param parameterListReportedOrExmineOut
	 */
	public void waringCenterRemoveUnfilledDataWorkTable(DataWarningCenterParameter parameterListReportedOrExmineOut){
		/*
		 * 删除当年已填报数据
		 * 参数分别是 	parameterArr[0] sql_element   sql标签
		 * 			parameterArr[1] model_id      模块编号
		 * 	  		parameterArr[2] model_name    模块名称
		 * 			parameterArr[3] year          年
		 * 			parameterArr[4] month         月
		 */
		String[] passedReportParaArr = new String[5];
		String date = transferDate();
		String year = date.substring(0,4);
		String month = date.substring(5,7);
		if(parameterListReportedOrExmineOut!=null){
			passedReportParaArr[0] = parameterListReportedOrExmineOut.getSql_label();
			passedReportParaArr[1] = parameterListReportedOrExmineOut.getModel_id();
			passedReportParaArr[2] = parameterListReportedOrExmineOut.getModel_name();			
			passedReportParaArr[3] = year;	
			passedReportParaArr[4] = month;				
			warningCenterOneYearMonthService.dataRemoveUnfilledDataWorkTable(passedReportParaArr);
		}
	}
	
	/**
	 * 转换对比时间信息
	 * @param strTime
	 * @return
	 */
	private String transferDate(){
		String nowTime = new SimpleDateFormat("yyyy-MM").format(new Date());
		return nowTime;
	}
	
}
