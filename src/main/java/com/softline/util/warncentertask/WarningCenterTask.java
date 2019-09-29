package com.softline.util.warncentertask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import com.softline.entityTemp.DataWarningCenterParameter;
import com.softline.service.warncenter.IWarningCenterService;

public class WarningCenterTask {
		
	private IWarningCenterService warningCenterService;
	
	public WarningCenterTask(IWarningCenterService warningCenterService){
		this.warningCenterService=warningCenterService;
		
	}
	
	private static final Logger log = Logger.getLogger(WarningCenterTask.class);
	
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
			if("in".equals(type)){
				warningCenterInsertIn(tempBean);
			}
			//删除表中未及时上报、未及时审核数据
			if("out".equals(type)){
				waringCenterDeleData(tempBean);
			}
			//插入上个月没有填报公司
			if("fillin".equals(type)){
				waringCenterInsertUnfilledDataWorkTable(tempBean);
			}
			//删除上个月没有填报公司
			if("fillout".equals(type)){
				waringCenterRemoveUnfilledDataWorkTable(tempBean);
			}
		}	
		//每月一号删除之前两个月的数据
		deleteUnFilledCompany();
		parameterList.clear();
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
			passedReportParaArr[4] = transferDateTime(parameterListUnReportOrExmineIn.getWarnTime());
			passedReportParaArr[5] = parameterListUnReportOrExmineIn.getModel_name();
			passedReportParaArr[6] = parameterListUnReportOrExmineIn.getUrl();
			warningCenterService.dataInsertDataWorkTable(passedReportParaArr);
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
			warningCenterService.dataInsertDataWorkTable(passedReportParaArr);
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
			passedReportParaArr[4] = transferDateTime(parameterListReportedOrExmineOut.getWarnTime());
			passedReportParaArr[5] = "50113,50115";
			passedReportParaArr[6] = parameterListReportedOrExmineOut.getModel_name();				
			warningCenterService.dataRemoveDataWorkTable(passedReportParaArr);
		}
		//2、执行未及时审核公司信息
		if(parameterListReportedOrExmineOut!=null){
			passedReportParaArr[0] = parameterListReportedOrExmineOut.getSql_label();
			passedReportParaArr[1] = parameterListReportedOrExmineOut.getModel_id();
			passedReportParaArr[2] = parameterListReportedOrExmineOut.getPage_author();
			passedReportParaArr[3] = "50113";
			passedReportParaArr[4] = transferDateTime(parameterListReportedOrExmineOut.getWarnTime());
			passedReportParaArr[5] = "50115";
			passedReportParaArr[6] = parameterListReportedOrExmineOut.getModel_name();				
			warningCenterService.dataRemoveDataWorkTable(passedReportParaArr);
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
		 * 				strArr[6] month         月
		 */
		String[] passedReportParaArr = new String[7];
		String nowDate = new SimpleDateFormat("yyyy-MM").format(new Date());
		String year = nowDate.substring(0,4);
		String month = nowDate.substring(5,7);
		if(Integer.valueOf(month)==1){
			year = (Integer.valueOf(year)-1)+"";
			month = "12";
		}else{
			month = (Integer.valueOf(month)-1)+"";
		}
		if(parameterListReportedOrExmineOut!=null){
			passedReportParaArr[0] = parameterListReportedOrExmineOut.getSql_label();
			passedReportParaArr[1] = parameterListReportedOrExmineOut.getPage_author();
			passedReportParaArr[2] = parameterListReportedOrExmineOut.getModel_id();
			passedReportParaArr[3] = parameterListReportedOrExmineOut.getModel_name();			
			passedReportParaArr[4] = parameterListReportedOrExmineOut.getUrl();				
			passedReportParaArr[5] = year;
			passedReportParaArr[6] = month;		
			warningCenterService.dataInsertUnfilledDataWorkTable(passedReportParaArr);
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
		 * 				strArr[2] month         月
		 * 				strArr[3] model_id      模块编号
		 * 				strArr[4] model_name    模块名称
		 */
		String nowDate = new SimpleDateFormat("yyyy-MM").format(new Date());
		String year = nowDate.substring(0,4);
		String month = nowDate.substring(5,7);
		if(Integer.valueOf(month)==1){
			year = (Integer.valueOf(year)-1)+"";
			month = "12";
		}else{
			month = (Integer.valueOf(month)-1)+"";
		}
		
		String[] passedReportParaArr = new String[5];
		if(parameterListReportedOrExmineOut!=null){
			passedReportParaArr[0] = parameterListReportedOrExmineOut.getSql_label();
			passedReportParaArr[1] = year;
			passedReportParaArr[2] = month;		
			passedReportParaArr[3] = parameterListReportedOrExmineOut.getModel_id();
			passedReportParaArr[4] = parameterListReportedOrExmineOut.getModel_name();			
			warningCenterService.dataRemoveUnfilledDataWorkTable(passedReportParaArr);
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

	/**
	 * 每月一号删除两个月之前的未及时填报的数据
	 */
	public void deleteUnFilledCompany(){
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(new Date());//把当前时间赋给日历
		calendar.add(calendar.MONTH, -2); //设置为前2月
		String lastTwoEarlyMonthDate = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());//获取2个月前的时间
		
		String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String nowDay = nowTime.split("-")[nowTime.split("-").length-1];
		if(1==Integer.valueOf(nowDay)){
			warningCenterService.deleteUnFilledCompany(lastTwoEarlyMonthDate);
			if(log.isInfoEnabled()){
				log.info("删除"+lastTwoEarlyMonthDate+"的未及时填报的数据");	
			}
		}
	}
	
	
}
