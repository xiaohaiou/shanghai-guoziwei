package com.softline.service.warncenter.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.system.ICommonDao;
import com.softline.dao.warncenter.IWarningCenterDao;
import com.softline.dao.warncenter.IWarningCenterYearDao;
import com.softline.entity.DataWorktableWarningCenter;
import com.softline.service.warncenter.IWarningCenterYearService;
import com.softline.util.WarningCenterUtil;

@Service("warningCenterYearService")
public class WarningCenterYearService implements IWarningCenterYearService {

	@Autowired
	@Qualifier("warningCenterYearDao")
	private IWarningCenterYearDao warningCenterYearDao;
	@Autowired
	@Qualifier("warningCenterDao")
	private IWarningCenterDao warningCenterDao;
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	private static final Logger log = Logger.getLogger(WarningCenterService.class);
	
	/**
	 * 工作台预警提醒数据插入 
	 * @param strArr 
	 * 	长度为5
	 * 		参数分别是 	strArr[0] sql_element   sql标签
	 * 				strArr[1] model_id      模块编号
	 * 				strArr[2] page_author   页面权限
	 * 				strArr[3] status        审核状态
	 * 				strArr[4] year          年
	 * 				strArr[5] model_name    模块名称
	 * 				strArr[6] url           url
	 * @return
	 */
	@Override
	public int dataInsertDataWorkTable(String[] strArr){
		// TODO Auto-generated method stub 
		if(strArr.length!=7){
			if(log.isInfoEnabled())
				log.info("传入参数有误，更新未及时填报和未及时审核公司失败！");
			return -1;
		}
		String sql = null;
		String[] parameterArr = {strArr[2],strArr[1],strArr[5],strArr[6]};//设置查询返回实体类信息
		try {
			Document document = WarningCenterUtil.getInstance().getDocument();
			Element elementroot = document.getRootElement();
			Element element = elementroot.element(strArr[0]);
			sql = element.getText();
		} catch (Exception e) {
			if(log.isInfoEnabled())
				log.info("工作台预警提醒，读取sql错误！");
			// TODO: handle exception
		}	
		sql = sql.replaceAll("parameter1", "'"+strArr[1]+"'");
		sql = sql.replaceAll("parameter2", "'"+strArr[2]+"'");		
		sql = sql.replaceAll("parameter3", strArr[3]);				
		sql = sql.replaceAll("parameter6", "'"+strArr[4]+"'");
		if(log.isInfoEnabled())
			log.info(sql);
		List<DataWorktableWarningCenter> listBean = warningCenterYearDao.getUnreportedCompany(sql,parameterArr);
		for(DataWorktableWarningCenter tempBean:listBean){
			commonDao.saveOrUpdate(tempBean);
		}
		if(log.isInfoEnabled())
			log.info("保存未上报、未审核公司成功！");
		listBean.clear();
		//保存日志
		warningCenterDao.dataWorktableWarningLog(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
												 strArr[5],
												 "插入未及时上报，未及时审核公司信息！");	
		return 0;
	}

	/**
	 * 工作台预警数据,已上报、已审核执行删除更新
	 * @param strArr 
	 * 	长度为6
	 * 		参数分别是 	strArr[0] sql_element   sql标签
	 * 				strArr[1] model_id      模块编号
	 * 				strArr[2] page_author   页面权限
	 * 				strArr[3] status        审核状态
	 * 				strArr[4] year          年
	 *              strArr[5] status2                    审核状态2设置查询条件
	 *              strArr[6] model_id      模块名称  
	 * @return
	 */
	@Override
	public int dataRemoveDataWorkTable(String[] strArr) {
		if(strArr.length!=7){
			if(log.isInfoEnabled())
				log.info("传入参数有误，更新未及时填报和未及时审核公司失败！");
			return -1;
		}
		String sql = null;
		try {
			Document document = WarningCenterUtil.getInstance().getDocument();
			Element elementroot = document.getRootElement();
			Element element = elementroot.element(strArr[0]);
			sql = element.getText();
		} catch (Exception e) {
			if(log.isInfoEnabled())
				log.info("工作台预警提醒，读取sql错误！");
			// TODO: handle exception
		}	
		sql=sql.replace("parameter1", "'"+strArr[1]+"'");
		sql=sql.replace("parameter2", "'"+strArr[2]+"'");		
		sql=sql.replace("parameter3", strArr[3]);				
		sql=sql.replace("parameter5", "'"+strArr[5]+"'");
		sql = sql.replaceAll("parameter6", "'"+strArr[4]+"'");
		List<DataWorktableWarningCenter> listBean = warningCenterYearDao.getReportedCompany(sql);
		for(DataWorktableWarningCenter tempBean:listBean){
			warningCenterDao.deleteRecordCompany(tempBean);
		}
		if(log.isInfoEnabled())
			log.info("删除已上报，已审核公司成功！");
		listBean.clear();
		//保存日志
		warningCenterDao.dataWorktableWarningLog(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
												 strArr[6],
												 "删除未及时上报、审核，公司信息！");
		return 0;
	}

	/**
	 * 工作台预警提醒未填报数据插入
	 * @param strArr 
	 * 	长度为5
	 * 		参数分别是 	strArr[0] sql_element   sql标签
	 * 				strArr[1] page_author   页面权限
	 * 				strArr[2] model_id      模块编号
	 * 				strArr[3] model_name    模块名称
	 * 				strArr[4] url           url
	 * 				strArr[5] year          年
	 * @return
	 */
	@Override
	public int dataInsertUnfilledDataWorkTable(String[] strArr) {
		// TODO Auto-generated method stub 
		if(strArr.length!=6){
			if(log.isInfoEnabled())
				log.info("传入参数有误，更新未及时填报和未及时审核公司失败！");
			return -1;
		}
		String sql = null;
		/*
		 * 			parameterArr[0] page_author
		 * 			parameterArr[1] model_id
		 * 			parameterArr[2] model_name
		 * 			parameterArr[3] url
		 * 			parameterArr[4] year
		 */
		String[] parameterArr = {strArr[1],strArr[2],strArr[3],strArr[4],strArr[5]};//设置查询返回实体类信息
		try {
			Document document = WarningCenterUtil.getInstance().getDocument();
			Element elementroot = document.getRootElement();
			Element element = elementroot.element(strArr[0]);
			sql = element.getText();
		} catch (Exception e) {
			if(log.isInfoEnabled())
				log.info("工作台预警提醒，读取sql错误！");
			// TODO: handle exception
		}	
		sql = sql.replaceAll("parameter6", strArr[5]);			
		sql = sql.replaceAll("parameter1", "'"+strArr[2]+"'");
		if(log.isInfoEnabled())
			log.info(sql);
		List<DataWorktableWarningCenter> listBean = warningCenterYearDao.getUnFilledCompany(sql,parameterArr);
		for(DataWorktableWarningCenter tempBean:listBean){
			commonDao.saveOrUpdate(tempBean);
		}
		if(log.isInfoEnabled())
			log.info("保存未上报、未审核公司成功！");
		listBean.clear();
		//保存日志
		warningCenterDao.dataWorktableWarningLog(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
												 strArr[3],
												 "插入未及时填报数据！");	
		return 0;
	}

	/**
	 * 工作台预警提醒未填报数据删除
	 * @param strArr 
	 * 	长度为4
	 * 		参数分别是 	strArr[0] sql_element   sql标签
	 * 				strArr[1] year          年
	 * 				strArr[2] model_id      模块编号
	 * 				strArr[3] model_name    模块名称
	 * @return
	 */
	@Override
	public int dataRemoveUnfilledDataWorkTable(String[] strArr) {
		if(strArr.length!=4){
			if(log.isInfoEnabled())
				log.info("传入参数有误，更新未及时填报和未及时审核公司失败！");
			return -1;
		}
		String sql = null;
		try {
			Document document = WarningCenterUtil.getInstance().getDocument();
			Element elementroot = document.getRootElement();
			Element element = elementroot.element(strArr[0]);
			sql = element.getText();
		} catch (Exception e) {
			if(log.isInfoEnabled())
				log.info("工作台预警提醒，读取sql错误！");
			// TODO: handle exception
		}	
		sql=sql.replace("parameter6",strArr[1]);	
		sql=sql.replace("parameter1","'"+strArr[2]+"'");				
		List<DataWorktableWarningCenter> listBean = warningCenterYearDao.getReportedCompany(sql);
		for(DataWorktableWarningCenter tempBean:listBean){
			warningCenterDao.deleteRecordCompany(tempBean);
		}
		if(log.isInfoEnabled())
			log.info("删除已上报，已审核公司成功！");
		listBean.clear();
		//保存日志
		warningCenterDao.dataWorktableWarningLog(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
												 strArr[3],
												 "删除未及时填报数据！");
		return 0;
	}

	
}
