package com.softline.dao.settingCenter.user.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.softline.common.Common;
import com.softline.dao.settingCenter.user.IUserHrAndFinanceCompanyDao;

@Repository("userHrAndFinanceCompanyDao")
public class UserHrAndFinanceCompanyDao implements IUserHrAndFinanceCompanyDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Object[]> getUserCompanyList(String userName, String vcAccount,
			String orgs,Integer offset, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from v_usercompany t where 1=1");
		if(Common.notEmpty(userName)){
			sql.append(" and t.vcName like '%").append(userName).append("%'");
		}
		if(Common.notEmpty(vcAccount)){
			sql.append(" and t.vcAccount like '%").append(vcAccount).append("%'");
		}
		if(Common.notEmpty(orgs)){
			sql.append(" and t.company_id in (").append(orgs).append(")");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		if(offset != null && pageSize != null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);  
		}
		return query.list();
	}

	@Override
	public List<Object[]> getUserFinanceCompanyList(String userName,
			String vcAccount, String orgs,Integer offset, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from v_userfinanceCompany t where 1=1");
		if(Common.notEmpty(userName)){
			sql.append(" and t.vcName like '%").append(userName).append("%'");
		}
		if(Common.notEmpty(vcAccount)){
			sql.append(" and t.vcAccount like '%").append(vcAccount).append("%'");
		}
		if(Common.notEmpty(orgs)){
			sql.append(" and t.company_id in (").append(orgs).append(")");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		if(offset != null && pageSize != null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);  
		}
		return query.list();
	}

}
