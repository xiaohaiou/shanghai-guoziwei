package com.softline.dao.warncenter.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.warncenter.IWarningCenterYearDao;
import com.softline.entity.DataWorktableWarningCenter;

@Repository(value = "warningCenterYearDao")
public class WarningCenterYearDao implements IWarningCenterYearDao{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getLogger(WarningCenterDao.class); 
	
	@Override
	public List<DataWorktableWarningCenter> getUnreportedCompany(String sql,String[] parameterArr) {
		if(!Common.notEmpty(sql)||parameterArr.length!=4){
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
			bean.setOrgname(row[i]==null?"":row[i].toString());i++;
			bean.setOrg(row[i]==null?"":row[i].toString());i++;
			bean.setStatus(row[i]==null?0:Integer.valueOf(row[i].toString()));i++;
			bean.setCreateDate(row[i]==null?"":row[i].toString());i++;
			//父亲公司编码
			if(row.length>i){
				bean.setParentorg(row[i]==null?"":row[i].toString());		
			}
			//设置页面访问权限信息
			bean.setPage_author(parameterArr[0]);
			//设置页面访问权限信息
			bean.setIsdel(0);
			bean.setModel_id(parameterArr[1]);
			bean.setModel_name(parameterArr[2]);
			bean.setUrl(parameterArr[3]);
			listGetBean.add(bean);
		}
		return listGetBean;
	}

	@Override
	public List<DataWorktableWarningCenter> getReportedCompany(String sql) {
		if(!Common.notEmpty(sql))
			return new ArrayList<DataWorktableWarningCenter>();
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List listInfo= query.list();
		//设置返回的查询数据
		List<DataWorktableWarningCenter> listGetBean = new ArrayList<DataWorktableWarningCenter>();
		for (Object object : listInfo) {
			int i=0;
			DataWorktableWarningCenter bean = new DataWorktableWarningCenter();
			Object[] row= (Object[]) object;
			bean.setId(row[i]==null?0:Integer.valueOf(row[i].toString()));i++;	
			bean.setYear(row[i]==null?"":row[i].toString());i++;	
			bean.setMonth(row[i]==null?"":row[i].toString());i++;
			bean.setDay(row[i]==null?"":row[i].toString());i++;
			bean.setOrgname(row[i]==null?"":row[i].toString());i++;	
			bean.setOrg(row[i]==null?"":row[i].toString());i++;	
			bean.setCreatePersonName(row[i]==null?"":row[i].toString());i++;	
			bean.setIsdel(row[i]==null?0:Integer.valueOf(row[i].toString()));i++;	
			bean.setReportPersonName(row[i]==null?"":row[i].toString());i++;	
			bean.setAuditorPersonName(row[i]==null?"":row[i].toString());i++;	
			bean.setStatus(row[i]==null?0:Integer.valueOf(row[i].toString()));i++;	
			bean.setCreateDate(row[i]==null?"":row[i].toString());i++;	
			bean.setReportDate(row[i]==null?"":row[i].toString());i++;	
			bean.setAuditorDate(row[i]==null?"":row[i].toString());i++;	
			bean.setParentorg(row[i]==null?"":row[i].toString());i++;	
			bean.setPage_author(row[i]==null?"":row[i].toString());i++;	
			bean.setModel_id(row[i]==null?"":row[i].toString());i++;	
			bean.setModel_name(row[i]==null?"":row[i].toString());i++;
			listGetBean.add(bean);
		}
		return listGetBean;
	}

	/**
	 * 提取所有未填报公司的数据
	 * 		上个月
	 * @param sql
	 * @param parameterArr
	 * 			parameterArr[0] page_author
	 * 			parameterArr[1] model_id
	 * 			parameterArr[2] model_name
	 * 			parameterArr[3] url
	 * 			parameterArr[4] year
	 * 
	 * @return
	 */
	@Override
	public List<DataWorktableWarningCenter> getUnFilledCompany(String sql,String[] parameterArr) {
		if(parameterArr.length!=5 || !Common.notEmpty(sql)){
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
			bean.setOrgname(row[i]==null?"":row[i].toString());i++;
			bean.setOrg(row[i]==null?"":row[i].toString());i++;
			//父亲公司编码
			if(row.length>i){
				bean.setParentorg(row[i]==null?"":row[i].toString());		
			}
			//设置时间信息
			bean.setYear(parameterArr[4]);	
			//设置页面访问权限信息
			bean.setPage_author(parameterArr[0]);
			//设置页面访问权限信息
			bean.setModel_id(parameterArr[1]);
			bean.setModel_name(parameterArr[2]);
			bean.setUrl(parameterArr[3]);
			bean.setStatus(51111);
			listGetBean.add(bean);
		}
		return listGetBean;
	}


}
