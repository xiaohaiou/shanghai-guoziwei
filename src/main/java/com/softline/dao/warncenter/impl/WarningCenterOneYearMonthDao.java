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
import com.softline.dao.warncenter.IWarningCenterOneYearMonthDao;
import com.softline.entity.DataWorktableWarningCenter;

@Repository(value = "warningCenterOneYearMonthDao")
public class WarningCenterOneYearMonthDao implements IWarningCenterOneYearMonthDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getLogger(WarningCenterOneYearMonthDao.class); 
	
	/**
	 * length 5
	 *      parameterArr[0] sql 语句	
	 *      parameterArr[1] page_author 页面权限
	 *      parameterArr[2] model_id 模块编号
	 *      parameterArr[3] model_name 模块名称
	 *      parameterArr[4] url 地址     
	 */
	@Override
	public List<DataWorktableWarningCenter> getInsertUnfilledDataWorkTable(String[] parameterArr) {
		// TODO Auto-generated method stub
		if(parameterArr.length != 5)
			return new ArrayList<DataWorktableWarningCenter>(); 
		
		String companyName = "";
		String org = "";
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
			companyName = String.valueOf(row[0]);
			org = String.valueOf(row[1]);
			String tempMonth = String.valueOf(row[2]);	
			year = String.valueOf(row[3]);
			if(listMonth.contains(tempMonth)){
				listMonth.remove(tempMonth);
			}
		}
		
		//设置未填报数据
		List<DataWorktableWarningCenter> listGetBean = new ArrayList<DataWorktableWarningCenter>();
		for(String tempMonth:listMonth){
			DataWorktableWarningCenter bean = new DataWorktableWarningCenter();
			bean.setOrgname(companyName);
			bean.setOrg(org);
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

	/**
	 * length 2
	 *       parameterArr[0] sql 语句
	 * @param parameterArr
	 * @param date  时间
	 */
	@Override
	public List<DataWorktableWarningCenter> dataRemoveWorkTable(String[] parameterArr) {
		// TODO Auto-generated method stub
		if( parameterArr.length != 1)
			return new ArrayList<DataWorktableWarningCenter>(); 

		Query query = sessionFactory.getCurrentSession().createSQLQuery(parameterArr[0]);
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

	@Override
	public List<DataWorktableWarningCenter> dataRemoveLastYearWorkTable(String tableName,String date) {
		// TODO Auto-generated method stub
		if(!Common.notEmpty(date))
			return new ArrayList<DataWorktableWarningCenter>(); 
		String sql = " select a.* from "+tableName+" a where STR_TO_DATE(CONCAT(a.`year`,a.`month`),'%Y%m') <"+date;
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

}
