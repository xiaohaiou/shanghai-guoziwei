package com.softline.dao.bimshow.impl;

import com.softline.common.Common;
import com.softline.dao.bimshow.IBimShowIndustryCommerceDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository("bimShowIndustryCommerceDao")
public class BimShowIndustryCommerceDao implements IBimShowIndustryCommerceDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<Object> getIndustryCommerceDaoList(String year,String month) {
		if(!Common.notEmpty(year) || !Common.notEmpty(month))
			return new ArrayList<Object>();
		String sql= "select orgName from hh_industry_commerce_company where year = "+year+" and month = "+month;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
	
	/**
	 * 获取国资口径和工商口径数据
	 */
	public List<Object> getIndustryCommerceCaliberData(String year,String month,String company){
		if(!Common.notEmpty(year) || !Common.notEmpty(month))
			return new ArrayList<Object>();
		String sql= "select type,legalPerson,manageForm,registeredCapital,paidInCapital,staffSize,workingField,operationPeriodStart,operationPeriodEnd from hh_industry_commerce_caliber_data where year = "
					+year+" and month = "+month+" and company = '"+company+"' ";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();	
	}
	
	/**
	 * 获取董监信息数据
	 */
	public List<Object> getIndustryCommerceSupervisorData(String year,String month,String company){
		if(!Common.notEmpty(year) || !Common.notEmpty(month))
			return new ArrayList<Object>();
		String sql= "select type,name,jobCategorye,personType,inaugurationDate,isDeparture  from hh_industry_commerce_supervisor_data where year = "
				    +year+" and month = "+month+" and company = '"+company+"' ";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
	
	/**
	 * 获取股权信息
	 */
	public List<Object> getIndustryCommerceShareInformation(String year,String month,String company){
		if(!Common.notEmpty(year) || !Common.notEmpty(month))
			return new ArrayList<Object>();
		String sql= "select type,shareholderName,shareholderType,subscribedAmount,subscribedDate,subscribedWay,subscribedDate2,subscribedWay2,currency,proportion from hh_industry_commerce_share_information where year = "
			    +year+" and month = "+month+" and company = '"+company+"' ";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
}
