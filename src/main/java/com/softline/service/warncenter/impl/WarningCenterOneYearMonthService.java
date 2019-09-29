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
import com.softline.dao.warncenter.IWarningCenterOneYearMonthDao;
import com.softline.entity.DataWorktableWarningCenter;
import com.softline.service.warncenter.IWarningCenterOneYearMonthService;
import com.softline.util.WarningCenterUtil;
/**
 * @author zl
 *
 */
@Service("warningCenterOneYearMonthService")
public class WarningCenterOneYearMonthService implements IWarningCenterOneYearMonthService {

	@Autowired
	@Qualifier("warningCenterDao")
	private IWarningCenterDao warningCenterDao;
	@Autowired
	@Qualifier("warningCenterOneYearMonthDao")
	private IWarningCenterOneYearMonthDao warningCenterOneYearMonthDao;
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	private static final Logger log = Logger.getLogger(WarningCenterOneYearMonthService.class);
	
	/**
	 * 插入当年未及时填报数据 
	 * @param strArr 
	 * 	长度为7
	 * 		参数分别是 	parameterArr[0] sql_element   sql标签
	 * 				parameterArr[1] model_id      模块编号
	 * 				parameterArr[2] page_author   页面权限
	 * 				parameterArr[3] model_name    模块名称
	 * 				parameterArr[4] url           url
	 * 				parameterArr[5] year          年
	 * 				parameterArr[6] month         月
	 * @return
	 */
	@Override
	public int dataInsertUnfilledDataWorkTable(String[] parameterArr) {
		// TODO Auto-generated method stub
		if(parameterArr.length!=7)
			return -1;
		String sql = "";
		try {
			Document document = WarningCenterUtil.getInstance().getDocument();
			Element elementroot = document.getRootElement();
			Element element = elementroot.element(parameterArr[0]);
			sql = element.getText();
		} catch (Exception e) {
			if(log.isInfoEnabled())
				log.info("工作台预警提醒，读取sql错误！");
			// TODO: handle exception
		}	
		sql = sql.replaceAll("parameter1", "'"+parameterArr[1]+"'");
		sql = sql.replaceAll("parameter6", "'"+parameterArr[5]+"'");
		sql = sql.replaceAll("parameter7", "'"+parameterArr[6]+"'");		
		
		if(log.isInfoEnabled())
			log.info(sql);
		/*
		 * length 5
		 *      parameterArr[0] sql 语句	
		 *      parameterArr[1] page_author 页面权限
		 *      parameterArr[2] model_id 模块编号
		 *      parameterArr[3] model_name 模块名称
		 *      parameterArr[4] url 地址     
		 */
		String[] daoArr = {sql,parameterArr[2],parameterArr[1],parameterArr[3],parameterArr[4]};
		List<DataWorktableWarningCenter> listBean = warningCenterOneYearMonthDao.getInsertUnfilledDataWorkTable(daoArr);
		for(DataWorktableWarningCenter tempBean:listBean){
			commonDao.saveOrUpdate(tempBean);
		}
		if(log.isInfoEnabled())
			log.info("保存未上报、未审核公司成功！");
		listBean.clear();
		//保存日志
		warningCenterDao.dataWorktableWarningLog(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
												 parameterArr[3],
												 "插入未及时填报公司信息！");	
		return 0;
	}

	/**
	 * 删除当年已填报数据
	 * 参数分别是 	parameterArr[0] sql_element   sql标签
	 * 			parameterArr[1] model_id      模块编号
	 * 	  		parameterArr[2] model_name    模块名称
	 * 			parameterArr[3] year          年
	 * 			parameterArr[4] month         月
	 */
	@Override
	public int dataRemoveUnfilledDataWorkTable(String[] parameterArr) {
		// TODO Auto-generated method stub
		if(parameterArr.length!=5)
			return -1;
		String sql = "";
		try {
			Document document = WarningCenterUtil.getInstance().getDocument();
			Element elementroot = document.getRootElement();
			Element element = elementroot.element(parameterArr[0]);
			sql = element.getText();
		} catch (Exception e) {
			if(log.isInfoEnabled())
				log.info("工作台预警提醒，读取sql错误！");
			// TODO: handle exception
		}	
		sql = sql.replaceAll("parameter1", "'"+parameterArr[1]+"'");
		sql = sql.replaceAll("parameter6", "'"+parameterArr[3]+"'");	
		sql = sql.replaceAll("parameter7", "'"+parameterArr[4]+"'");	
		/*
		 * length 1
		 *       parameterArr[0] sql 语句
		 */
		String[] daoArr = {sql};
		List<DataWorktableWarningCenter> listBean = warningCenterOneYearMonthDao.dataRemoveWorkTable(daoArr);
		for(DataWorktableWarningCenter tempBean:listBean){
			warningCenterDao.deleteRecordCompany(tempBean);
		}
		if(log.isInfoEnabled())
			log.info("删除已上报，已审核公司成功！");
		listBean.clear();
		//保存日志
		warningCenterDao.dataWorktableWarningLog(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
												 parameterArr[2],
												 "删除未及填报公司信息！");
		return 0;
	}

	/**
	 * 删除当年已填报数据
	 * 参数分别是 	strArr[0] sql_element   sql标签
	 * 			strArr[1] model_id      模块编号
	 * 		    strArr[1] year          年
	 */
	@Override
	public int removeLastYearData(String[] parameterArr) {
		// TODO Auto-generated method stub
		return 0;
	}

}
