package com.softline.service.warncenter.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.softline.common.Common;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.warncenter.IWarningCenterDao;
import com.softline.entity.DataWorktableWarningCenter;
import com.softline.entityTemp.DataWarningCenterParameter;
import com.softline.service.warncenter.IWarningCenterService;
import com.softline.util.MsgPage;
import com.softline.util.WarningCenterUtil;

/**
 * @author zl
 *
 */
@Service("warningCenterService")
public class WarningCenterService implements IWarningCenterService {
		
	@Autowired
	@Qualifier("warningCenterDao")
	private IWarningCenterDao warningCenterDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	private static final Logger log = Logger.getLogger(WarningCenterService.class);
	
	/**
	 * 工作台数据展示
	 * @param searchBean 查询条件
	 * @param curPageNum 当前页
	 * @param pageSize   每页显示数据
	 * @param authorMap  控制页面访问权限
	 * @return
	 */
	@Override
	public MsgPage dataShow(
			DataWorktableWarningCenter searchBean, Integer curPageNum,
			Integer pageSize,Map authorMap) {
		// TODO Auto-generated method stub
		//设置访问权限信息
		String companyAuthor = String.valueOf(authorMap.get("companyAuthor"));
		String financeCompanyAuthor = String.valueOf(authorMap.get("financeCompanyAuthor"));	
		String pageAuthor = String.valueOf(authorMap.get("pageAuthor"));
		authorMap.put("companyAuthor", this.dealWithAuthorstr(companyAuthor));
		authorMap.put("financeCompanyAuthor", this.dealWithAuthorstr(financeCompanyAuthor));
		authorMap.put("pageAuthor", this.dealWithAuthorstr(pageAuthor));
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = warningCenterDao.dataShow(searchBean,authorMap);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataWorktableWarningCenter> list = warningCenterDao.dataShow(searchBean, offset, pageSize,authorMap);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list); 
		return msgPage;
	}
	
	private String dealWithAuthorstr(String str){
		if(!Common.notEmpty(str) || !str.contains(","))
			return "";
		StringBuffer sb = new StringBuffer();
		String[] strArr = str.split(",");
		for(int i=0;i<strArr.length-1;i++){
			sb.append("'").append(strArr[i]).append("',");
		}
		sb.append("'").append(strArr[strArr.length-1]).append("'");
		return sb.toString();
	}
	
	/**
	 * 获取预警数据转换记录
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */	@Override
	public MsgPage getReportData(String model_id,Integer curPageNum, Integer pageSize){
		
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = warningCenterDao.getReportData(model_id);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object> list = warningCenterDao.getReportData(model_id,offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list); 
		return msgPage;
	}
	
	
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
		sql = sql.replaceAll("parameter4", "'"+strArr[4]+"'");
		if(log.isInfoEnabled())
			log.info(sql);
		List<DataWorktableWarningCenter> listBean = warningCenterDao.getUnreportedCompany(sql,parameterArr);
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
	 * 				strArr[4] transferDate  转换时间
	 *              strArr[5] status2                    审核状态2设置查询条件
	 *              strArr[6] model_id      模块名称  
	 * @return
	 */
	@Override
	public int dataRemoveDataWorkTable(String[] strArr) {
		// TODO Auto-generated method stub
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
		sql=sql.replace("parameter4", "'"+strArr[4]+"'");	
		sql=sql.replace("parameter5", "'"+strArr[5]+"'");
		List<DataWorktableWarningCenter> listBean = warningCenterDao.getReportedCompany(sql);
		for(DataWorktableWarningCenter tempBean:listBean){
			warningCenterDao.deleteRecordCompany(tempBean);
		}
		if(log.isInfoEnabled())
			log.info("删除已上报，已审核公司成功！");
		listBean.clear();
		//保存日志
		warningCenterDao.dataWorktableWarningLog(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
												 strArr[6],
												 "删除未及时上报、审核公司信息！");
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
	 * @return
	 */
	public int dataInsertUnfilledDataWorkTable(String[] strArr){
		// TODO Auto-generated method stub 
		if(strArr.length!=7){
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
		 * 			parameterArr[5] month
		 */
		String[] parameterArr = {strArr[1],strArr[2],strArr[3],strArr[4],strArr[5],strArr[6]};//设置查询返回实体类信息
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
		sql = sql.replaceAll("parameter7", strArr[6]);				
		sql = sql.replaceAll("parameter1", "'"+strArr[2]+"'");
		if(log.isInfoEnabled())
			log.info(sql);
		List<DataWorktableWarningCenter> listBean = warningCenterDao.getUnFilledCompany(sql,parameterArr);
		for(DataWorktableWarningCenter tempBean:listBean){
			commonDao.saveOrUpdate(tempBean);
		}
		if(log.isInfoEnabled())
			log.info("保存未上报、未审核公司成功！");
		listBean.clear();
		//保存日志
		warningCenterDao.dataWorktableWarningLog(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
												 strArr[3],
												 "插入未及时填报公司信息！");	
		return 0;
	}
	
	/**
	 * 工作台预警提醒未填报数据删除
	 * @param strArr 
	 * 	长度为5
	 * 		参数分别是 	strArr[0] sql_element   sql标签
	 * 				strArr[1] year          年
	 * 				strArr[2] month         月
	 * 				strArr[3] model_id      模块编号
	 * 				strArr[4] model_name    模块名称
	 * @return
	 */
	public int dataRemoveUnfilledDataWorkTable(String[] strArr){
		// TODO Auto-generated method stub
		if(strArr.length!=5){
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
		sql=sql.replace("parameter7",strArr[2]);		
		sql=sql.replace("parameter1","'"+strArr[3]+"'");				
		List<DataWorktableWarningCenter> listBean = warningCenterDao.getReportedCompany(sql);
		for(DataWorktableWarningCenter tempBean:listBean){
			warningCenterDao.deleteRecordCompany(tempBean);
		}
		if(log.isInfoEnabled())
			log.info("删除已上报，已审核公司成功！");
		listBean.clear();
		//保存日志
		warningCenterDao.dataWorktableWarningLog(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
												 strArr[4],
												 "删除未及时填报公司信息！");
		return 0;
	}

	
	/**
	 * 获取	1、未及时上报	 UnReportIn
	 * 		2、未及时审核            UnExmineIn
	 * 		3、已上报                    ReportedOut
	 * 		4、已审核                    ExminedOut
	 * @return
	 */
	@Override
	public List<DataWarningCenterParameter> getAllParameter(){
		return warningCenterDao.getAllParameter();
	}
	
	/**
	 * 添加菜单选择项
	 * @return
	 */
	@Override
	public List<String[]> getSelectOptionsForWarnCenter(){
		List<String[]> listOptionsStr = new ArrayList<String[]>();
		List<Object> listInfo = warningCenterDao.getSelectOptionsForWarnCenter();
		String[] strArr;
		for(Object tempObj:listInfo){
			if(null != tempObj){
				Object[] objArr = (Object[])tempObj;
				strArr= new String[2];
				strArr[0] = String.valueOf(objArr[0]);
				strArr[1] = String.valueOf(objArr[1]);
				listOptionsStr.add(strArr);
			}
		}
		return listOptionsStr;
	}
	
	/**
	 * 删除未及时填报数据
	 * @param date
	 */
	@Override
	public void deleteUnFilledCompany(String date){
		warningCenterDao.deleteUnFilledCompany(date);
	}
	
}
