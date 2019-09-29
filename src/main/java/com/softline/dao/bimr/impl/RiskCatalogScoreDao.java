package com.softline.dao.bimr.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.bimr.IRiskCatalogScoreDao;
import com.softline.entity.bimr.BimrRiskCatalog;
import com.softline.entity.bimr.BimrRiskCatalogScore;

/**
 * 实现二级风险目录评分数据操作
 * 
 * @author liu
 */
@Repository("RiskCatalogScoreDao")
public class RiskCatalogScoreDao implements IRiskCatalogScoreDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BimrRiskCatalogScore> getRiskCatalogScoreList(Integer year,
			Integer month,String coreOrg) {
		
		String hql = "FROM BimrRiskCatalogScore WHERE year = :year AND month = :month AND isDel = 0 AND coreOrg = :coreOrg";
		
		Query query = sessionFactory.getCurrentSession().
		               createQuery(hql).
		               setParameter("year", year).
		               setParameter("month", month).
		               setParameter("coreOrg", coreOrg);
		
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getWillScoreRiskCatalogList(Integer year, Integer month,String coreOrg,String coreOrgName) {

		String hql = "SELECT t2.id, t2.eventid, t2.leveltwoid, t2.leveltwoname, count(t2.eventid), sum(t3.isImportant), t3.reportTime, t3.isImportant, t3.eventTitle,'"+coreOrg+"','"+coreOrgName+"' FROM (bimr_risk_event_relevance t2 LEFT JOIN bimr_risk_event t3 ON t2.eventid = t3.id) LEFT JOIN hh_organInfo  t4 on t4.nNodeID = t3.compId   WHERE t2.is_del = 0 AND t3.isDel = 0 AND t3.relevancestatus = 4 ";
		if(year != null && month != null ){
			hql = hql + " and t3.reportTime >= '" +getSeasonStartStr(year,month) +  "'"  ;
			hql = hql + " and t3.reportTime <= '" +getSeasonEndStr(year,month) + "'" ;
		}
		hql = hql + "AND t4.vcOrganID like '%-"+coreOrg+"-%'";
		hql = hql + "GROUP BY t2.eventid";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
		
		List<Object[]> list = query.list();
		
		List<Object[]> result = new ArrayList<Object[]>(list.size());
		for(Object[] t: list){
			result.add(new Object[]{t[2], t[3],t[4] ,t[5],t[9],t[10]});
		}
		return result;
	}
	
	
	private String getSeasonStartStr(Integer year,Integer month){
		String result = year + "";
		if(month == 1){
			result = result + "-01";
		}else if(month == 2){
			result = result + "-04";
		}else if(month == 3){
			result = result + "-07";
		}else if(month == 4){
			result = result + "-10";
		}
		return result;
	}
	
	private String getSeasonEndStr(Integer year,Integer month){
		String result = year + "";
		if(month == 1){
			result = result + "-03";
		}else if(month == 2){
			result = result + "-06";
		}else if(month == 3){
			result = result + "-09";
		}else if(month == 4){
			result = result + "-12";
		}
		return result;
	}
	
	
	
}
