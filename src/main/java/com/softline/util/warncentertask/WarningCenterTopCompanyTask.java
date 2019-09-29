package com.softline.util.warncentertask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.softline.entityTemp.DataWarningCenterParameter;
import com.softline.service.warncenter.IWarningCenterService;
import com.softline.service.warncenter.IWarningCenterTopCompanyService;

public class WarningCenterTopCompanyTask {

	private IWarningCenterService warningCenterService;
	private IWarningCenterTopCompanyService warningCenterTopCompanyService;
	
	public WarningCenterTopCompanyTask(IWarningCenterService warningCenterService,
									   IWarningCenterTopCompanyService warningCenterTopCompanyService){
		this.warningCenterService=warningCenterService;
		this.warningCenterTopCompanyService=warningCenterTopCompanyService;
	}
	private static final Logger log = Logger.getLogger(WarningCenterTopCompanyTask.class);
	
	/**
	 * 提取参数，执行插入和删除操作
	 * 		当日期为每月一号时，删除每上上个月为及时填报的数据。
	 */
	public void run(){
		//获取参数
		List<DataWarningCenterParameter> parameterList = warningCenterService.getAllParameter();
		String type = null;
		// 执行未上报、未审核插入操作
		for(DataWarningCenterParameter tempBean:parameterList){
			type = tempBean.getSql_label().split("_")[tempBean.getSql_label().split("_").length-1];
			//未及时上报、未及时审核数据插入
			if("topCompanyIn".equals(type)){
				warningCenterInsertIn(tempBean);
			}
			//插入上个月未及时填报的数据
			if("topCompanyFillIn".equals(type)){
				waringCenterInsertUnfilledDataWorkTable(tempBean);
			}
		}	
	}
	
	/**
	 * 执行更新操作，插入未及时上报、未及时审核公司信息
	 */
	public void warningCenterInsertIn(DataWarningCenterParameter parameterListUnReportOrExmineIn){
		//1、执行未及时上报公司信息
		/*
		 * 工作台预警提醒数据插入 
		 * @param strArr 
		 * 	长度为5
		 * 		参数分别是 	strArr[0] sql_element   sql标签
		 * 				strArr[1] model_id      模块编号
		 * 				strArr[2] page_author   页面权限
		 * 				strArr[3] status        审核状态
		 * 				strArr[4] transferDate  转换时间
		 * 				strArr[5] model_name    模块名称
		 * 				strArr[6] url           url
		 * 				strArr[7] companyName   公司名称
		 * 				strArr[8] org           公司编码
		 * @return
		 */
		String[] passedReportParaArr = new String[9];
		if(parameterListUnReportOrExmineIn!=null){
			passedReportParaArr[0] = parameterListUnReportOrExmineIn.getSql_label();
			passedReportParaArr[1] = parameterListUnReportOrExmineIn.getModel_id();
			passedReportParaArr[2] = parameterListUnReportOrExmineIn.getPage_author();
			passedReportParaArr[3] = "50112";
			passedReportParaArr[4] = transferDateTime(parameterListUnReportOrExmineIn.getWarnTime());
			passedReportParaArr[5] = parameterListUnReportOrExmineIn.getModel_name();
			passedReportParaArr[6] = parameterListUnReportOrExmineIn.getUrl();
			passedReportParaArr[7] = "海航物流";
			passedReportParaArr[8] = "855579";
			warningCenterTopCompanyService.dataInsertDataWorkTable(passedReportParaArr);
		}
		//2、执行未及时审核公司信息
		if(parameterListUnReportOrExmineIn!=null){
			passedReportParaArr[0] = parameterListUnReportOrExmineIn.getSql_label();
			passedReportParaArr[1] = parameterListUnReportOrExmineIn.getModel_id();
			passedReportParaArr[2] = parameterListUnReportOrExmineIn.getPage_author();
			passedReportParaArr[3] = "50113";
			passedReportParaArr[4] = transferDateTime(parameterListUnReportOrExmineIn.getWarnTime());
			passedReportParaArr[5] = parameterListUnReportOrExmineIn.getModel_name();
			passedReportParaArr[6] = parameterListUnReportOrExmineIn.getUrl();		
			passedReportParaArr[7] = "海航物流";
			passedReportParaArr[8] = "855579";
			warningCenterTopCompanyService.dataInsertDataWorkTable(passedReportParaArr);
		}	
	}
	
	
	/**
	 * 插入未填报公司数据
	 * @param parameterListReportedOrExmineOut
	 */
	public void waringCenterInsertUnfilledDataWorkTable(DataWarningCenterParameter parameterListReportedOrExmineOut){
		/*
		 * 工作台预警提醒未填报数据插入
		 * @param strArr 
		 * 	长度为5
		 * 		参数分别是 	strArr[0] sql_element   sql标签
		 * 				strArr[1] page_author   页面权限
		 * 				strArr[2] model_id      模块编号
		 * 				strArr[3] model_name    模块名称
		 * 				strArr[4] url           url
		 * 				strArr[5] year          年
		 * 				strArr[6] month         月
		 * 				strArr[7] companyName   公司名称
		 * 				strArr[8] org           公司编码
		 * @return
		 */
		String[] passedReportParaArr = new String[9];
		String date = new SimpleDateFormat("yyyy-MM").format(new Date());
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
			passedReportParaArr[7] = "海航物流";
			passedReportParaArr[8] = "855579";
			warningCenterTopCompanyService.dataInsertUnfilledDataWorkTable(passedReportParaArr);
		}		
	}

	/**
	 * 转换对比时间信息
	 * @param strTime
	 * @return
	 */
	private String transferDateTime(String strTime){
		if(strTime.length()>2)
			return new SimpleDateFormat("yyyy-MM").format(new Date())+"-15";
		String nowTime = new SimpleDateFormat("yyyy-MM").format(new Date());
//		return nowTime+"-"+strTime;
		return nowTime+"-00";
	}
	
}
