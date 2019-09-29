package com.softline.dao.warncenter.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.warncenter.IWarningCenterTopCompanyDao;
import com.softline.entity.DataWorktableWarningCenter;

@Repository(value = "warningCenterTopCompanyDao")
public class WarningCenterTopCompanyDao implements
		IWarningCenterTopCompanyDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getLogger(WarningCenterTopCompanyDao.class); 
	
	/**
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
	@Override
	public List<DataWorktableWarningCenter> getUnreportedCompany(String sql,String[] parameterArr) {
		// TODO Auto-generated method stub
		if(!Common.notEmpty(sql)||parameterArr.length!=6){
			if(log.isInfoEnabled())
				log.info("传入参数有误，更新未及时填报和未及时审核公司失败！");
			return new ArrayList<DataWorktableWarningCenter>();
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List listInfo= query.list();
		//设置返回的查询数据
		List<DataWorktableWarningCenter> listGetBean = new ArrayList<DataWorktableWarningCenter>();
		String str = null;
		for (Object object : listInfo) {
			int i=0;
			DataWorktableWarningCenter bean = new DataWorktableWarningCenter();
			Object[] row= (Object[]) object;
			bean.setYear(row[i]==null?"":row[i].toString());i++;	
			bean.setMonth(row[i]==null?"":row[i].toString());i++;
			bean.setDay(row[i]==null?"":row[i].toString());i++;
			bean.setStatus(row[i]==null?0:Integer.valueOf(row[i].toString()));i++;
			bean.setCreateDate(row[i]==null?"":row[i].toString());i++;
			//父亲公司编码
			if(row.length>i){
				bean.setParentorg(row[i]==null?"":row[i].toString());		
			}
			//设置页面访问权限信息
			bean.setIsdel(0);
			bean.setPage_author(parameterArr[0]);
			//设置页面访问权限信息
			bean.setModel_id(parameterArr[1]);
			bean.setModel_name(parameterArr[2]);
			bean.setUrl(parameterArr[3]);
			bean.setOrgname(parameterArr[4]);
			bean.setOrg(parameterArr[5]);
			listGetBean.add(bean);
		}
		return listGetBean;
	}

	/**
	 * length 7
	 *      parameterArr[0] sql 语句	
	 *      parameterArr[1] page_author 页面权限
	 *      parameterArr[2] model_id 模块编号
	 *      parameterArr[3] model_name 模块名称
	 *      parameterArr[4] url 地址     
	 * 		parameterArr[5] orgName
	 * 		parameterArr[6] org
	 */
	@Override
	public List<DataWorktableWarningCenter> getUnFilledCompany(String[] parameterArr) {
		// TODO Auto-generated method stub
		if(parameterArr.length != 7)
			return new ArrayList<DataWorktableWarningCenter>(); 
		String year = "";
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(parameterArr[0]);
		List listInfo= query.list();
		
		List<String> listMonth = new ArrayList<String>();
		String date = new SimpleDateFormat("yyyy-MM").format(new Date());
		int month = Integer.valueOf(date.substring(5,7));

		for(int i = 1;i<month;i++){
			listMonth.add(String.valueOf(i));
		}
		// 找出12个月中没有的月份数据
		for (Object object : listInfo) {
			Object[] row= (Object[]) object;
			String tempMonth = String.valueOf(row[0]);	
			year = String.valueOf(row[1]);
			if(listMonth.contains(tempMonth)){
				listMonth.remove(tempMonth);
			}
		}
		
		//设置未填报数据
		List<DataWorktableWarningCenter> listGetBean = new ArrayList<DataWorktableWarningCenter>();
		for(String tempMonth:listMonth){
			DataWorktableWarningCenter bean = new DataWorktableWarningCenter();
			bean.setOrgname(parameterArr[5]);
			bean.setOrg(parameterArr[6]);
			bean.setIsdel(0);
			bean.setYear(year);
			bean.setMonth(tempMonth);
			bean.setStatus(51111);
			bean.setPage_author(parameterArr[1]);
			bean.setModel_id(parameterArr[2]);
			bean.setModel_name(parameterArr[3]);
			bean.setUrl(parameterArr[4]);
			listGetBean.add(bean);
		}
		return listGetBean;
	}
}
