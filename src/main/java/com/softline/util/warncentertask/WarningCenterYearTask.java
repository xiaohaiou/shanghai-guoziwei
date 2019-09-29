package com.softline.util.warncentertask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import com.softline.entityTemp.DataWarningCenterParameter;
import com.softline.service.warncenter.IWarningCenterService;
import com.softline.service.warncenter.IWarningCenterYearService;

public class WarningCenterYearTask {
	
	private IWarningCenterService warningCenterService;
	private IWarningCenterYearService warningCenterYearService;
	
	public WarningCenterYearTask(IWarningCenterService warningCenterService,
								 IWarningCenterYearService warningCenterYearService){
		this.warningCenterService=warningCenterService;
		this.warningCenterYearService=warningCenterYearService;
		
	}
	
	private static final Logger log = Logger.getLogger(WarningCenterYearTask.class);
	
	public void run(){
		//获取参数
		List<DataWarningCenterParameter> parameterList = warningCenterService.getAllParameter();
		String type = null;
		// 执行未上报、未审核插入操作
		for(DataWarningCenterParameter tempBean:parameterList){
			type = tempBean.getSql_label().split("_")[tempBean.getSql_label().split("_").length-1];
			//未及时上报、未及时审核数据插入
			if("inYear".equals(type)){
				warningCenterInsertIn(tempBean);
			}
			//删除表中未及时上报、未及时审核数据
			if("outYear".equals(type)){
				waringCenterDeleData(tempBean);
			}
			//插入上个月没有填报公司
			if("fillinYear".equals(type)){
				waringCenterInsertUnfilledDataWorkTable(tempBean);
			}
			//删除上个月没有填报公司
			if("filloutYear".equals(type)){
				waringCenterRemoveUnfilledDataWorkTable(tempBean);
			}
		}	
	}
	
	/**
	 * 执行更新操作，插入未及时上报、未及时审核公司信息
	 */
	public void warningCenterInsertIn(DataWarningCenterParameter parameterListUnReportOrExmineIn){
		//1、执行未及时上报公司信息
		/*
		 * 		参数分别是 	strArr[0] sql_element   sql标签
		 * 				strArr[1] model_id      模块编号
		 * 				strArr[2] page_author   页面权限
		 * 				strArr[3] status        审核状态
		 * 				strArr[4] transferDate  转换时间
		 * 				strArr[5] model_name    模块名称
		 * 				strArr[6] url           url
		 * */
		String[] passedReportParaArr = new String[7];
		if(parameterListUnReportOrExmineIn!=null){
			passedReportParaArr[0] = parameterListUnReportOrExmineIn.getSql_label();
			passedReportParaArr[1] = parameterListUnReportOrExmineIn.getModel_id();
			passedReportParaArr[2] = parameterListUnReportOrExmineIn.getPage_author();
			passedReportParaArr[3] = "50112";
			passedReportParaArr[4] = transferDateYear(parameterListUnReportOrExmineIn.getWarnTime());
			passedReportParaArr[5] = parameterListUnReportOrExmineIn.getModel_name();
			passedReportParaArr[6] = parameterListUnReportOrExmineIn.getUrl();
			warningCenterYearService.dataInsertDataWorkTable(passedReportParaArr);
		}
		//2、执行未及时审核公司信息
		if(parameterListUnReportOrExmineIn!=null){
			passedReportParaArr[0] = parameterListUnReportOrExmineIn.getSql_label();
			passedReportParaArr[1] = parameterListUnReportOrExmineIn.getModel_id();
			passedReportParaArr[2] = parameterListUnReportOrExmineIn.getPage_author();
			passedReportParaArr[3] = "50113";
			passedReportParaArr[4] = transferDateYear(parameterListUnReportOrExmineIn.getWarnTime());
			passedReportParaArr[5] = parameterListUnReportOrExmineIn.getModel_name();
			passedReportParaArr[6] = parameterListUnReportOrExmineIn.getUrl();			
			warningCenterYearService.dataInsertDataWorkTable(passedReportParaArr);
		}	
	}
	
	/**
	 * 执行删除操作，按照规定时间删除预警表中已经上报和已经审核公司信息
	 */
	public void waringCenterDeleData(DataWarningCenterParameter parameterListReportedOrExmineOut){
		String[] passedReportParaArr = new String[7];
		//1、执行未及时上报公司信息
		if(parameterListReportedOrExmineOut!=null){
			passedReportParaArr[0] = parameterListReportedOrExmineOut.getSql_label();
			passedReportParaArr[1] = parameterListReportedOrExmineOut.getModel_id();
			passedReportParaArr[2] = parameterListReportedOrExmineOut.getPage_author();
			passedReportParaArr[3] = "50112";
			passedReportParaArr[4] = transferDateYear(parameterListReportedOrExmineOut.getWarnTime());
			passedReportParaArr[5] = "50113,50115";
			passedReportParaArr[6] = parameterListReportedOrExmineOut.getModel_name();				
			warningCenterYearService.dataRemoveDataWorkTable(passedReportParaArr);
		}
		//2、执行未及时审核公司信息
		if(parameterListReportedOrExmineOut!=null){
			passedReportParaArr[0] = parameterListReportedOrExmineOut.getSql_label();
			passedReportParaArr[1] = parameterListReportedOrExmineOut.getModel_id();
			passedReportParaArr[2] = parameterListReportedOrExmineOut.getPage_author();
			passedReportParaArr[3] = "50113";
			passedReportParaArr[4] = transferDateYear(parameterListReportedOrExmineOut.getWarnTime());
			passedReportParaArr[5] = "50115";
			passedReportParaArr[6] = parameterListReportedOrExmineOut.getModel_name();				
			warningCenterYearService.dataRemoveDataWorkTable(passedReportParaArr);
		}		
	}
	
	/**
	 * 插入未填报公司数据
	 * @param parameterListReportedOrExmineOut
	 */
	public void waringCenterInsertUnfilledDataWorkTable(DataWarningCenterParameter parameterListReportedOrExmineOut){
		/*
		 * 		参数分别是 	strArr[0] sql_element   sql标签
		 * 				strArr[1] page_author   页面权限
		 * 				strArr[2] model_id      模块编号
		 * 				strArr[3] model_name    模块名称
		 * 				strArr[4] url           url
		 * 				strArr[5] year          年
		 */
		String[] passedReportParaArr = new String[6];
		String year = new SimpleDateFormat("yyyy").format(new Date());
		if(parameterListReportedOrExmineOut!=null){
			passedReportParaArr[0] = parameterListReportedOrExmineOut.getSql_label();
			passedReportParaArr[1] = parameterListReportedOrExmineOut.getPage_author();
			passedReportParaArr[2] = parameterListReportedOrExmineOut.getModel_id();
			passedReportParaArr[3] = parameterListReportedOrExmineOut.getModel_name();			
			passedReportParaArr[4] = parameterListReportedOrExmineOut.getUrl();				
			passedReportParaArr[5] = year;
			warningCenterYearService.dataInsertUnfilledDataWorkTable(passedReportParaArr);
		}	
	}
	
	/**
	 * 删除上个月已经填报公司的数据
	 * @param parameterListReportedOrExmineOut
	 */
	public void waringCenterRemoveUnfilledDataWorkTable(DataWarningCenterParameter parameterListReportedOrExmineOut){
		/*
		 * 		参数分别是 	strArr[0] sql_element   sql标签
		 * 				strArr[1] year          年
		 * 				strArr[2] model_id      模块编号
		 * 				strArr[3] model_name    模块名称
		 */
		String year = new SimpleDateFormat("yyyy").format(new Date());
		
		String[] passedReportParaArr = new String[4];
		if(parameterListReportedOrExmineOut!=null){
			passedReportParaArr[0] = parameterListReportedOrExmineOut.getSql_label();
			passedReportParaArr[1] = year;	
			passedReportParaArr[2] = parameterListReportedOrExmineOut.getModel_id();
			passedReportParaArr[3] = parameterListReportedOrExmineOut.getModel_name();			
			warningCenterYearService.dataRemoveUnfilledDataWorkTable(passedReportParaArr);
		}
	}
	
	/**
	 * 转换对比时间信息
	 * @param strTime
	 * @return
	 */
	private String transferDateYear(String strTime){
		if(strTime.length()>2)
			return new SimpleDateFormat("yyyy").format(new Date());
		String nowTime = new SimpleDateFormat("yyyy").format(new Date());
		nowTime =String.valueOf(Integer.valueOf(nowTime)+1);
		return nowTime;
	}
	
}
