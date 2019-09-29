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
import com.softline.dao.warncenter.IWarningCenterTopCompanyDao;
import com.softline.entity.DataWorktableWarningCenter;
import com.softline.service.warncenter.IWarningCenterTopCompanyService;
import com.softline.util.WarningCenterUtil;

@Service("warningCenterTopCompanyService")
public class WarningCenterTopCompanyService implements
		IWarningCenterTopCompanyService {

	@Autowired
	@Qualifier("warningCenterDao")
	private IWarningCenterDao warningCenterDao;
	@Autowired
	@Qualifier("warningCenterTopCompanyDao")
	private IWarningCenterTopCompanyDao warningCenterTopCompanyDao;
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	private static final Logger log = Logger.getLogger(WarningCenterTopCompanyService.class);
	
	/**
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
	@Override
	public int dataInsertDataWorkTable(String[] strArr) {
		// TODO Auto-generated method stub
		if(strArr.length!=9){
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
		sql = sql.replaceAll("parameter1", "'"+strArr[1]+"'");
		sql = sql.replaceAll("parameter2", "'"+strArr[2]+"'");		
		sql = sql.replaceAll("parameter3", strArr[3]);				
		sql = sql.replaceAll("parameter4", "'"+strArr[4]+"'");
		if(log.isInfoEnabled())
			log.info(sql);
		/*
		 * 提取未及时上报，未及时审核单位
		 * @param searchBean
		 * @param parameterArr 返回实体类参数设置
		 * 			parameterArr[0] page_author
		 * 			parameterArr[1] model_id
		 * 			parameterArr[2] model_name
		 * 			parameterArr[3] url
		 * 			parameterArr[4] orgName
		 * 			parameterArr[5] org
		 * @return
		 */
		String[] parameterArr = {strArr[2],strArr[1],strArr[5],strArr[6],strArr[7],strArr[8]};//设置查询返回实体类信息
		List<DataWorktableWarningCenter> listBean = warningCenterTopCompanyDao.getUnreportedCompany(sql,parameterArr);
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
	@Override
	public int dataInsertUnfilledDataWorkTable(String[] parameterArr) {
		// TODO Auto-generated method stub
		if(parameterArr.length!=9)
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
		 * length 7
		 *      parameterArr[0] sql 语句	
		 *      parameterArr[1] page_author 页面权限
		 *      parameterArr[2] model_id 模块编号
		 *      parameterArr[3] model_name 模块名称
		 *      parameterArr[4] url 地址     
		 * 		parameterArr[5] orgName
		 * 		parameterArr[6] org
		 */
		String[] daoArr = {sql,parameterArr[2],parameterArr[1],parameterArr[3],parameterArr[4],parameterArr[7],parameterArr[8]};
		List<DataWorktableWarningCenter> listBean = warningCenterTopCompanyDao.getUnFilledCompany(daoArr);
		for(DataWorktableWarningCenter tempBean:listBean){
			commonDao.saveOrUpdate(tempBean);
		}
		if(log.isInfoEnabled())
			log.info("保存未上报、未审核公司成功！");
		listBean.clear();
		//保存日志
		warningCenterDao.dataWorktableWarningLog(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
												 parameterArr[3],
												 "插入未及时上报，未及时审核公司信息！");	
		return 0;
	}

}
