package com.softline.dao.publicCompany.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.controller.common.commonController;
import com.softline.dao.publicCompany.IOutCompareDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entityTemp.BimfOutCompareData;
@Repository(value = "outcompareDao")
public class OutCompareDao implements IOutCompareDao{
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 外部对标获取数据
	 * @param orgID
	 * @param date 时间范围
	 * @return
	 */
	public List<BimfOutCompareData> getYearData(String orgID,int[] date)
	{
		List<BimfOutCompareData> result=new ArrayList<BimfOutCompareData>();
		if(Common.notEmpty(orgID))
		{
			
			StringBuilder  hql = new StringBuilder();
			hql.append(" select date_y,org,orgNm,A00449,A00450,A00451,A00452,A00453,A00454,A00455,A00456, ");
			hql.append(" A00457,A00458,A00459,A00460,A00461,A00462,A00463,A00464,A00465,A00466,A00488,A00489 ");
			hql.append(" from publicCompanyData where org =("+Common.dealinStr(orgID)+") ");
			hql.append(" and ( ");
			for (int i = 0; i < date.length; i++) {
				hql.append("  date_y="+date[i]+" ");
				if(i!=date.length-1)
					hql.append(" or ");
			}
			hql.append(" ) order by org ,date_y desc");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			List a=query.list();
			for (int j = 0; j < date.length; j++) {
				BimfOutCompareData child=new BimfOutCompareData();
				child.setYear(String.valueOf(date[j]));
				for (Object item : a) {
					Object[] obj=(Object[])item;
					int i=1;
					if(date[j]== Integer.parseInt(obj[0].toString())){
						child.setOrg(obj[i]==null ? "" :obj[i].toString());i++;
						child.setOrgNm(obj[i]==null ? "" :obj[i].toString());i++;
						
						child.setA00449(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00450(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00451(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00452(obj[i]==null ? "" :obj[i].toString());i++;
						
						child.setA00453(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00454(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00455(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00456(obj[i]==null ? "" :obj[i].toString());i++;
						
						child.setA00457(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00458(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00459(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00460(obj[i]==null ? "" :obj[i].toString());i++;
						
						child.setA00461(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00462(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00463(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00464(obj[i]==null ? "" :obj[i].toString());i++;
						
						child.setA00465(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00466(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00488(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00489(obj[i]==null ? "" :obj[i].toString());i++;
					}
				}
				result.add(child);
			}
		}
		
		return result;
		
	}
	
	/**
	 * 外部对标获取数据
	 * @param orgID
	 * @param date 时间范围
	 * @return
	 */
	public List<BimfOutCompareData> getSeasonData(String orgID,int[][] date)
	{
		List<BimfOutCompareData> result=new ArrayList<BimfOutCompareData>();
		if(Common.notEmpty(orgID))
		{
			StringBuilder  hql = new StringBuilder();
			hql.append(" select date_y,date_s,org,orgNm,A00449,A00450,A00451,A00452,A00453,A00454,A00455,A00456, ");
			hql.append(" A00457,A00458,A00459,A00460,A00461,A00462,A00463,A00464,A00465,A00466,A00488,A00489 ");
			hql.append(" from publicCompanyData where org =("+Common.dealinStr(orgID)+") ");
			hql.append(" and ( ");
			for (int i = 0; i < date.length; i++) {
				hql.append(" ( date_y="+date[i][0]+" and date_s="+date[i][1]+" ) ");
				if(i!=date.length-1)
					hql.append(" or ");
			}
			hql.append("  ) order by org ,date_y desc,date_s desc");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			List a=query.list();
			
			for (int j = 0; j < date.length; j++) {
				BimfOutCompareData child=new BimfOutCompareData();
				child.setYear(String.valueOf(date[j][0]));
				child.setSeason(String.valueOf(date[j][1]));
				for (Object item : a) {
					Object[] obj=(Object[])item;
					int i=2;
					if(date[j][0]== Integer.parseInt(obj[0].toString()) && date[j][1]== Integer.parseInt(obj[1].toString())){
						child.setOrg(obj[i]==null ? "" :obj[i].toString());i++;
						child.setOrgNm(obj[i]==null ? "" :obj[i].toString());i++;
						
						child.setA00449(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00450(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00451(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00452(obj[i]==null ? "" :obj[i].toString());i++;
						
						child.setA00453(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00454(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00455(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00456(obj[i]==null ? "" :obj[i].toString());i++;
						
						child.setA00457(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00458(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00459(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00460(obj[i]==null ? "" :obj[i].toString());i++;
						
						child.setA00461(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00462(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00463(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00464(obj[i]==null ? "" :obj[i].toString());i++;
						
						child.setA00465(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00466(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00488(obj[i]==null ? "" :obj[i].toString());i++;
						child.setA00489(obj[i]==null ? "" :obj[i].toString());i++;
					}
				}
				result.add(child);
			}
		}
		return result;
	}
	
	/**
	 * 外部对标获取数据
	 * @param orgID
	 * @param searchtype
	 * @return
	 */
	public String getMaxDataDate(String orgID,int searchtype)
	{
		String date= "";
		if(Common.notEmpty(orgID))
		{
			
			StringBuilder  hql = new StringBuilder();
			if(searchtype==Base.seasonquery)
				hql.append("select max(CONCAT(a.date_y,a.date_s)) from SHOWTAB_S117_FINANCE a where a.org in("+Common.dealinStr(orgID) +" )");
			else if(searchtype==Base.yearquery)
				hql.append("select max(a.date_y) from publicCompanyData a where a.org in("+Common.dealinStr(orgID) +")");
			else 
				return date;
			
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			return (String) query.uniqueResult();
		}
		return date;
	}

	@Override
	public List<Object[]> getPublicCompanyStockList() {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select * from hh_public_company_stockPrice where 1=1");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}
	
}
