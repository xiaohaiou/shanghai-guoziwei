package com.softline.dao.bimCenter.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.bimCenter.IBimCenterDao;
import com.softline.entity.BimCenterCompany;
import com.softline.entity.BimCenterSystem;
import com.softline.entityTemp.FinanceRisk;
@Repository("bimCenterDao")
public class BimCenterDao implements IBimCenterDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<BimCenterCompany> getCompanys(BimCenterCompany condi,
			Integer offset, Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append("from BimCenterCompany t where t.isDel=0");
		if(Common.notEmpty(condi.getCompanyName())){
			hql.append(" and t.companyName like '%").append(condi.getCompanyName()).append("%'");
		}
		hql.append(" order by t.companyOrder");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset != null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

	@Override
	public List<BimCenterSystem> getSystems(BimCenterSystem condi,
			Integer offset, Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append("from BimCenterSystem t where t.isDel=0");
		if(Common.notEmpty(condi.getSysFulName())){
			hql.append(" and t.sysFulName like '%").append(condi.getSysFulName()).append("%'");
		}
		if(Common.notEmpty(condi.getSysShortName())){
			hql.append(" and t.sysShortName like '%").append(condi.getSysShortName()).append("%'");
		}
		if(condi.getSysCompanyId() != null){
			hql.append(" and t.sysCompanyId = ").append(condi.getSysCompanyId());
		}
		hql.append(" order by t.sysOrder");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset != null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

	@Override
	public List<FinanceRisk> getData(String year, String org) {
		List<FinanceRisk> list = new ArrayList<FinanceRisk>();
		StringBuilder  sql = new StringBuilder();
		sql.append("SELECT year,month,org,orgNm,asset_liabilities,quick_ratio,net_assets_ratio,interest_multiplier,cash_totalAssetRatio,inventory_turnover,accounts_receivable_turnover,surplus_cash_protection_mltiple,roe,operating_profit,ope,financial_investment_yield,asset_based_investment_yield,investment_cash_yield FROM `financeRisk` where 1=1 ");
		if(Common.notEmpty(year)){
			sql.append(" and year = "+year+"");
		}
		if(Common.notEmpty(org)){
			sql.append(" and org = '"+org+"'");
		}
		String sqlstr=sql.toString();
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sqlstr);;
		List a = query.list();
		for (Object object : a) {
			Object[] obj=(Object[]) object;
			int i=0;
			FinanceRisk item = new FinanceRisk();
			item.setYear(obj[i]==null? "":obj[i].toString());i++;
			item.setMonth(obj[i]==null? "":obj[i].toString());i++;
			item.setOrg(obj[i]==null? "":obj[i].toString());i++;
			item.setOrgNm(obj[i]==null? "":obj[i].toString());i++;
			item.setAssetLiabilities(Double.parseDouble(obj[i].toString()));i++;
			item.setQuickRatio(Double.parseDouble(obj[i].toString()));i++;
			item.setNetAssetsRatio(Double.parseDouble(obj[i].toString()));i++;
			item.setInterestMultiplier(Double.parseDouble(obj[i].toString()));i++;
			item.setCashTotalAssetRatio(Double.parseDouble(obj[i].toString()));i++;
			item.setInventoryTurnover(Double.parseDouble(obj[i].toString()));i++;
			item.setAccountsReceivableTurnover(Double.parseDouble(obj[i].toString()));i++;
			item.setSurplusCashProtectionMltiple(Double.parseDouble(obj[i].toString()));i++;
			item.setRoe(Double.parseDouble(obj[i].toString()));i++;
			item.setOperatingProfit(Double.parseDouble(obj[i].toString()));i++;
			item.setOpe(Double.parseDouble(obj[i].toString()));i++;
			item.setFinancialInvestmentYield(Double.parseDouble(obj[i].toString()));i++;
			item.setAssetBasedInvestmentYield(Double.parseDouble(obj[i].toString()));i++;
			item.setInvestmentCashYield(Double.parseDouble(obj[i].toString()));i++;
			list.add(item);
		}
		return list;

	}

	@Override
	public List<Object> financialRiskCompany(String orgID, String year) {
		String sql = "SELECT nNodeID, vcFullName FROM	hh_organInfo_tree_relation WHERE vcParentID IN ('0-1-', '0-1-d4985136e97d11e7968906a2160000ae-') and cFlag=1 ORDER BY vcshoworder";
	    Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		//	List<Object[]> list = query.list();
		return query.list();
	}

	@Override
	public String getComapnyName(String org) {
		String sql = " select vcFullName from hh_organInfo_tree_relation where nNodeID = '" + org + "' and cFlag = '1'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.uniqueResult().toString();
	}
}
