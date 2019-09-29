package com.softline.dao.bimshow.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.bimshow.IFinancialPropertyDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationId;
import com.softline.entityTemp.HhOrganInfoTreeRelationFinance;

@Repository(value = "financialPropertyDao")
public class FinancialPropertyDao implements IFinancialPropertyDao{
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//查看公司名字
	@Override
	public String getFinancialProperty(String orgID) {
		// TODO Auto-generated method stub
				String sql = " select vcFullName from hh_organInfo_tree_relation where nNodeID = '" + orgID + "' and cFlag = '1'";
				Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				return query.uniqueResult().toString();
	}

	@Override
	public Integer getFinancialPropertyNumber(String orgID) {
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT COUNT(0) FROM hh_organInfo_tree_relation WHERE vcParentID = ( SELECT vcOrganID FROM hh_organInfo_tree_relation WHERE nNodeID = '"+orgID+"'  and cFlag=1) and cFlag=1 "); 
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Object> getFinanceData(String orgID) {
	/*	StringBuilder  hql = new StringBuilder();
		hql.append("SELECT t.vcFullname,(SELECT count(0)	FROM hh_organInfo_tree_relation a WHERE a.cFlag = 1 AND a.vcParentID LIKE CONCAT('%-', t.nNodeID, '-%') ) AS m,(");
		hql.append("SELECT count(0) FROM hh_organInfo_tree_relation a LEFT JOIN hh_organInfo_tree_relation_finance b ON a.nNodeID = b.nNodeID ");
		hql.append("WHERE a.cFlag = 1 AND a.vcParentID LIKE CONCAT('%-', t.nNodeID, '-%') AND b.isMistake = 1) AS n ");
		hql.append("FROM hh_organInfo_tree_relation t WHERE t.vcParentID = '0-1-d4985136e97d11e7968906a2160000ae-' AND t.cFlag = 1");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List b=query.list();
		for (Object object : b) {
			Object[] obj=(Object[]) object;
			HhOrganInfoTreeRelationFinance item=new HhOrganInfoTreeRelationFinance();
			item.setnNodeID(obj[0].toString());
			item.setVcVullName(obj[1].toString());
			item.setIsMistake(Integer.parseInt(obj[2].toString()));	
			
			b.add(item);
		}	
		return b;*/
		String sql = "SELECT t.vcFullname,(SELECT count(0)	FROM hh_organInfo_tree_relation a WHERE a.cFlag = 1 AND a.vcParentID LIKE CONCAT('%-', t.nNodeID, '-%') ) AS m,(";
				sql += "SELECT count(0) FROM hh_organInfo_tree_relation a LEFT JOIN hh_organInfo_tree_relation_finance b ON a.nNodeID = b.nNodeID ";
				sql += "WHERE a.cFlag = 1 AND a.vcParentID LIKE CONCAT('%-', t.nNodeID, '-%') AND b.isMistake = 1) AS n,t.nNodeID ";
				sql += "FROM hh_organInfo_tree_relation t WHERE t.vcParentID = '0-1-d4985136e97d11e7968906a2160000ae-' AND t.cFlag = 1";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
	//	List<Object[]> list = query.list();
		return query.list();
	}

	@Override
	public List<Object> getFinanceCompanyData(String org) {
			StringBuilder  hql = new StringBuilder();
			hql.append(" SELECT vcFullname FROM hh_organInfo_tree_relation WHERE vcParentID LIKE (select CONCAT((SELECT vcOrganID FROM hh_organInfo_tree_relation WHERE nNodeID = '"+org+"'), '%')) and cFlag=1");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			List a=query.list();
			return a;
	}
	

}
