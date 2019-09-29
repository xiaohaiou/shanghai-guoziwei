package com.softline.dao.report.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.report.ITaxPayDao;
import com.softline.entity.taxTask.DataTaxPay;
import com.softline.entity.taxTask.DataTaxPayDetailNow;
import com.softline.entity.taxTask.DataTaxPayDetailPrev;

/**
 * 
 * @author zy
 *
 */
@Repository(value = "taxPayDao")
public class TaxPayDao implements ITaxPayDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean isTopCompany(String nNodeID){
		if(null == nNodeID || "".equals(nNodeID))
			return false;
		String hql = " select count(h) from HhOrganInfoTreeRelation h where h.id.nnodeId = '"+nNodeID+
				"' and h.vcParentId in (select hh.vcOrganId from HhOrganInfoTreeRelation hh)";		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult().toString()) == 0;		
	}

	/**
	 * 单个主键查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataTaxPay getTaxPay(Integer id) {
		if (id == null)
			return new DataTaxPay();
		StringBuilder hql = new StringBuilder();
		hql.append(" from DataTaxPay s where 1=1 and isdel=0 and id=" + id);
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (DataTaxPay) query.uniqueResult();
	}

	/**
	 * 一览查询画面检索
	 * 
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @param fun
	 * @return
	 */
	public List<DataTaxPay> getTaxPayList(DataTaxPay entity, Integer offsize,
			Integer pagesize, Integer fun) {
		if (entity == null)
			return new ArrayList<DataTaxPay>();
		StringBuilder hql = new StringBuilder();
		hql.append(" from DataTaxPay s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" AND s.status <> 50112 ");
		}
		if (entity != null) {
			if (Common.notEmpty(entity.getOrg())) {
				hql.append(" and s.org in( "
						+ Common.dealinStr(entity.getOrg()) + ") ");
			}
			if (Common.notEmpty(entity.getYear())) {
				hql.append(" and s.year = '"+entity.getYear()+"' ");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (entity.getId() != null) {
				hql.append(" and s.id = " + entity.getId() + " ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						/*
						 * hql.append(" s.authOrg like '%-"+dd[i]+ "-%' )"); 
						 * 	 by zl
						 */
						hql.append(" locate('-"+dd[i]+"-',s.authOrg)>0)");
					}else{
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' or");
					}
				}
			}else{
				return new ArrayList<DataTaxPay>();
			}
		}
		hql.append(" order by year desc");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		if (offsize != null)
			query.setFirstResult(offsize);
		if (pagesize != null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	
	
	public List<DataTaxPay> getAllTaxPayList(DataTaxPay entity, Integer offsize,
			Integer pagesize, Integer fun) {
		if (entity == null)
			return new ArrayList<DataTaxPay>();
		StringBuilder hql = new StringBuilder();
		hql.append(" from DataTaxPay s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" AND s.status <> 50112 ");
		}
		if (entity != null) {
			if (Common.notEmpty(entity.getOrg())) {
				hql.append(" and s.org in( "
						+ Common.dealinStr(entity.getOrg()) + ") ");
			}
			if (Common.notEmpty(entity.getYear())) {
				hql.append(" and s.year = '"+entity.getYear()+"' ");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (entity.getId() != null) {
				hql.append(" and s.id = " + entity.getId() + " ");
			}
		}
		hql.append(" order by year desc");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		if (offsize != null)
			query.setFirstResult(offsize);
		if (pagesize != null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * 数据件数查询
	 * 
	 * @param entity
	 * @param fun
	 * @return
	 */
	public int getTaxPayListCount(DataTaxPay entity, Integer fun) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataTaxPay s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" AND s.status <> 50112 ");
		}
		if (entity != null) {
			if (Common.notEmpty(entity.getOrg())) {
				hql.append(" and s.org in( "
						+ Common.dealinStr(entity.getOrg()) + ") ");
			}
			if (Common.notEmpty(entity.getYear())) {
				hql.append(" and s.year = '"+entity.getYear()+"'");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (entity.getId() != null) {
				hql.append(" and s.id = " + entity.getId() + " ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						/*
						 * hql.append(" s.authOrg like '%-"+dd[i]+ "-%' )"); 
						 * 	 by zl
						 */
						hql.append(" locate('-"+dd[i]+"-',s.authOrg)>0)");
					}else{
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' or");
					}
				}
			}else{
				return 0;
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	
	public int getAllTaxPayListCount(DataTaxPay entity, Integer fun) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataTaxPay s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" AND s.status <> 50112 ");
		}
		if (entity != null) {
			if (Common.notEmpty(entity.getOrg())) {
				hql.append(" and s.org in( "
						+ Common.dealinStr(entity.getOrg()) + ") ");
			}
			if (Common.notEmpty(entity.getYear())) {
				hql.append(" and s.year = '"+entity.getYear()+"'");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (entity.getId() != null) {
				hql.append(" and s.id = " + entity.getId() + " ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	
	/**
	 * 保存时校验重复的方法
	 * 
	 * @param entity
	 * @return
	 */
	public boolean saveTaxPayRepeatCheck(DataTaxPay entity) {

		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataTaxPay s where 1=1 and isdel=0  ");
		if (entity != null) {
			if (Common.notEmpty(entity.getOrg())) {
				hql.append(" and s.org = '" + entity.getOrg() + "' ");
			}
			if (Common.notEmpty(entity.getYear())) {
				hql.append(" and s.year = '"+entity.getYear()+"' ");
			}
			if (entity.getId() != null) {
				hql.append(" and s.id != " + entity.getId() + " ");
			}

		}
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		Integer a = Integer.parseInt(query.uniqueResult().toString());
		if (a != null && a.equals(0))
			return true;
		else
			return false;

	}

	/**
	 * 保存时检查数据是否被能修改
	 * 
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(DataTaxPay entity) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataTaxPay s where 1=1 and isdel=0 and status in("
				+ Base.examCanEdit + ") ");
		if (entity != null) {

			if (entity.getId() != null) {
				hql.append(" and id =" + entity.getId() + "");
			} else
				return true;
		}

		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		Integer a = Integer.parseInt(query.uniqueResult().toString());
		if (a != null && a.equals(0))
			return false;
		else
			return true;
	}
	
	
	public int getNowId(DataTaxPay entity){
		StringBuilder  hql = new StringBuilder();
		hql.append("select a.id  FROM data_taxPay_detail_now a  where  a.org='"+entity.getOrg()+"'");
		if(Common.notEmpty(entity.getYear()))
		{
			hql.append(" and a.year = '"+entity.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer newId = Integer.parseInt(query.uniqueResult().toString());
		return newId;
	}
	
	public int getPrveId(DataTaxPay entity){
		StringBuilder  hql = new StringBuilder();
		hql.append("select a.id  FROM data_taxPay_detail_prev a  where a.org='"+entity.getOrg()+"'");
		if(Common.notEmpty(entity.getYear()))
		{
			hql.append(" and a.year = '"+entity.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer newId = Integer.parseInt(query.uniqueResult().toString());
		return newId;
	}
	
	/**
	 * 汇总
	 * 虚拟公司数据存在
	 * @param hr
	 * @return
	 */
	@Override
	public List<DataTaxPay> getHrFormsListCollectView(DataTaxPay dtp) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
	    hql.append("SELECT a.id,a.YEAR,a.MONTH,a.org,a.orgName,t.nowPay,t.prevPay,a.MoM,a.accNowPay,");
	    hql.append("a.accPrevPay,a.accMoM,a.hnTax,a.remark,a.STATUS,a.isdel,a.reportPersonID,");
	    hql.append("a.reportPersonName,a.reportDate,a.auditorPersonID,a.auditorPersonName,");
	    hql.append("a.auditorDate, a.createPersonID,a.createPersonName,a.createDate,");
	    hql.append("a.lastModifyPersonID,a.lastModifyPersonName,a.lastModifyDate,a.parentorg ");
	    hql.append(" FROM data_taxPay a,hh_organInfo_tree_relation b,( SELECT ");
	    hql.append("sum( a.nowPay ) AS nowPay, sum( a.prevPay ) AS prevPay FROM data_taxPay a");
	    hql.append(" WHERE a.year =  '"+dtp.getYear()+"' and a.STATUS= 50115  AND a.org IN ( SELECT nNodeID FROM ");
	    hql.append(" hh_organInfo_tree_relation WHERE vcParentID ");
	    hql.append(" like '%"+dtp.getParentorg()+"%' AND a.STATUS in (50500,50502) ) ) t ");
	    hql.append(" WHERE a.org = b.nNodeID AND b.STATUS = 50501 AND b.nNodeID = '"+dtp.getOrg()+"'");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}
	/**
	 * 汇总
	 * 虚拟公司数据不存在
	 */
	@Override
	public List<DataTaxPay> getHrFormsListCollectView2(DataTaxPay dtp) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
	    hql.append(" select sum( a.nowPay ) AS nowPay, sum( a.prevPay ) AS prevPay ");
	    hql.append(" FROM data_taxPay a,hh_organInfo_tree_relation b  where 1=1 ");
	    hql.append(" and a.STATUS= 50115 AND a.isdel = 0 AND a.org=b.nNodeID");
	    if (Common.notEmpty(dtp.getOrg())) {
			hql.append(" and a.org in( "
					+ Common.dealinStr(dtp.getOrg()) + ") ");
		}
	    if(Common.notEmpty(dtp.getStarttime()))
		{
			hql.append(" and a.year >= '"+dtp.getStarttime()+"' ");
		}
		if(Common.notEmpty(dtp.getEndtime()))
		{
			hql.append(" and a.year <= '"+dtp.getEndtime()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}
	
	/**
	 * 汇总
	 * 虚拟公司数据不存在
	 */
	public List<DataTaxPay> getSumDataLists(DataTaxPay dtp) {
		 if (!Common.notEmpty(dtp.getOrg())) {
				return null;
			}
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
	    hql.append(" select a.orgName,sum(a.nowPay),sum(a.prevPay) ");
	    hql.append(" FROM data_taxPay a,hh_organInfo_tree_relation b  where 1=1 ");
	    hql.append(" and a.STATUS= 50115 AND a.isdel = 0 AND a.org=b.nNodeID");
	    if (Common.notEmpty(dtp.getOrg())) {
			hql.append(" and a.org in( "
					+ Common.dealinStr(dtp.getOrg()) + ") ");
		}
	    if(Common.notEmpty(dtp.getStarttime()))
		{
			hql.append(" and a.year >= '"+dtp.getStarttime()+"' ");
		}
		if(Common.notEmpty(dtp.getEndtime()))
		{
			hql.append(" and a.year <= '"+dtp.getEndtime()+"' ");
		}
		hql.append(" group by a.org");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}
	/**
	 * 汇总数据时关联表虚拟数据新增获取
	 * 虚拟公司数据存在
	 */
	public List<DataTaxPayDetailNow> getnewListNow(DataTaxPay dtp, Integer newId) {
		// TODO Auto-generated method stub
		
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.MONTH,a.org,a.orgName,sum(a.businessTax) AS businessTax,sum(a.valueAddedTax) AS valueAddedTax,");
		hql.append("sum( a.consumptionTax ) AS consumptionTax,sum(a.cesTax) AS cesTax,sum(a.importVAT) AS importVAT,sum( a.tariff ) AS tariff,");
		hql.append("sum(a.eIncomeTax ) AS eIncomeTax,sum( a.withholdTax ) AS withholdTax,sum( a.pIncomeTax ) AS pIncomeTax,sum( a.housingTax ) AS housingTax,");
		hql.append("sum( a.deedTax ) AS deedTax,sum( a.landTax ) AS landTax,sum( a.landVAT ) AS landVAT,sum( a.stampTax ) AS stampTax,");
		hql.append("sum( a.otherTax ) AS otherTax,sum( a.sum ) AS sum,a.isdel,a.parentorg,a.type ");
		hql.append("FROM data_taxPay_detail_now a WHERE a.year =  '"+dtp.getYear()+"' AND  a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
		hql.append("WHERE vcParentID like '%"+dtp.getParentorg()+"%' and STATUS in (50500,50502)  )");

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxPayDetailNow> result = new ArrayList<DataTaxPayDetailNow>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxPayDetailNow entity1 = new DataTaxPayDetailNow();
			entity1.setId(newId);
            entity1.setDataTaxPay(dtp);
        	entity1.setYear(item[2]==null?"":item[2].toString());	
        	entity1.setMonth(item[3]==null?"":item[3].toString());	
			entity1.setOrg(dtp.getOrg());
			entity1.setOrgName(dtp.getOrgName());
			entity1.setBusinessTax(item[6]==null?"":item[6].toString());
			entity1.setValueAddedTax(item[7]==null?"":item[7].toString());
			entity1.setConsumptionTax(item[8]==null?"":item[8].toString());
			entity1.setCesTax(item[9]==null?"":item[9].toString());
			entity1.setImportVat(item[10]==null?"":item[10].toString());
			entity1.setTariff(item[11]==null?"":item[11].toString());
			entity1.setEincomeTax(item[12]==null?"":item[12].toString());
			entity1.setWithholdTax(item[13]==null?"":item[13].toString());
			entity1.setPincomeTax(item[14]==null?"":item[13].toString());
			entity1.setHousingTax(item[15]==null?"":item[15].toString());
			entity1.setDeedTax(item[16]==null?"":item[16].toString());
			entity1.setLandTax(item[17]==null?"":item[17].toString());
			entity1.setLandVat(item[18]==null?"":item[18].toString());
			entity1.setStampTax(item[19]==null?"":item[19].toString());
			entity1.setOtherTax(item[20]==null?"":item[20].toString());
			entity1.setSum(item[21]==null?"":item[21].toString());
			entity1.setIsdel((Integer) (item[22]==null? 0:(Integer)item[22]));
			entity1.setParentorg(dtp.getParentorg());
			entity1.setType((Integer) (item[24]==null? 3:(Integer)item[24]));
			result.add(entity1);
			
			}
		
		return result;
	}
	
	
	public List<DataTaxPayDetailPrev> getnewListPre(DataTaxPay dtp,Integer newId) {
		// TODO Auto-generated method stub
		
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.MONTH,a.org,a.orgName,sum(a.businessTax) AS businessTax,sum(a.valueAddedTax) AS valueAddedTax,");
		hql.append("sum( a.consumptionTax ) AS consumptionTax,sum(a.cesTax) AS cesTax,sum(a.importVAT) AS importVAT,sum( a.tariff ) AS tariff,");
		hql.append("sum(a.eIncomeTax ) AS eIncomeTax,sum( a.withholdTax ) AS withholdTax,sum( a.pIncomeTax ) AS pIncomeTax,sum( a.housingTax ) AS housingTax,");
		hql.append("sum( a.deedTax ) AS deedTax,sum( a.landTax ) AS landTax,sum( a.landVAT ) AS landVAT,sum( a.stampTax ) AS stampTax,");
		hql.append("sum( a.otherTax ) AS otherTax,sum( a.sum ) AS sum,a.isdel,a.parentorg ");
		hql.append("FROM data_taxPay_detail_prev a WHERE a.year =  '"+dtp.getYear()+"' AND  a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
		hql.append("WHERE vcParentID like '%"+dtp.getParentorg()+"%'  and STATUS in (50500,50502) )");

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxPayDetailPrev> result = new ArrayList<DataTaxPayDetailPrev>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxPayDetailPrev entity1 = new DataTaxPayDetailPrev();
			entity1.setId(newId);
			entity1.setDataTaxPay(dtp);
			entity1.setYear(item[2]==null?"":item[2].toString());	
			entity1.setMonth(item[3]==null?"":item[3].toString());	
			entity1.setOrg(dtp.getOrg());
			entity1.setOrgName(dtp.getOrgName());
			entity1.setBusinessTax(item[6]==null?"":item[6].toString());
			entity1.setValueAddedTax(item[7]==null?"":item[7].toString());
			entity1.setConsumptionTax(item[8]==null?"":item[8].toString());
			entity1.setCesTax(item[9]==null?"":item[9].toString());
			entity1.setImportVat(item[10]==null?"":item[10].toString());
			entity1.setTariff(item[11]==null?"":item[11].toString());
			entity1.setEincomeTax(item[12]==null?"":item[12].toString());
			entity1.setWithholdTax(item[13]==null?"":item[13].toString());
			entity1.setPincomeTax(item[14]==null?"":item[13].toString());
			entity1.setHousingTax(item[15]==null?"":item[15].toString());
			entity1.setDeedTax(item[16]==null?"":item[16].toString());
			entity1.setLandTax(item[17]==null?"":item[17].toString());
			entity1.setLandVat(item[18]==null?"":item[18].toString());
			entity1.setStampTax(item[19]==null?"":item[19].toString());
			entity1.setOtherTax(item[20]==null?"":item[20].toString());
			entity1.setSum(item[21]==null?"":item[21].toString());
			entity1.setIsdel((Integer) (item[22]==null? 0:(Integer)item[22]));
			entity1.setParentorg(dtp.getParentorg());
			result.add(entity1);
			}
		
		return result;
	}
	
	/**
	 * 汇总数据时关联表虚拟数据新增获取
	 * 虚拟公司数据不存在
	 */
	@Override
	public List<DataTaxPayDetailNow> getnewListNow2(DataTaxPay dtp) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.MONTH,a.org,a.orgName,sum(a.businessTax) AS businessTax,sum(a.valueAddedTax) AS valueAddedTax,");
		hql.append("sum( a.consumptionTax ) AS consumptionTax,sum(a.cesTax) AS cesTax,sum(a.importVAT) AS importVAT,sum( a.tariff ) AS tariff,");
		hql.append("sum(a.eIncomeTax ) AS eIncomeTax,sum( a.withholdTax ) AS withholdTax,sum( a.pIncomeTax ) AS pIncomeTax,sum( a.housingTax ) AS housingTax,");
		hql.append("sum( a.deedTax ) AS deedTax,sum( a.landTax ) AS landTax,sum( a.landVAT ) AS landVAT,sum( a.stampTax ) AS stampTax,");
		hql.append("sum( a.otherTax ) AS otherTax,sum( a.sum ) AS sum,a.isdel,a.parentorg,a.type ");
		hql.append("FROM data_taxPay_detail_now a WHERE a.year =  '"+dtp.getYear()+"'  AND a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
		hql.append("WHERE vcParentID like '%"+dtp.getParentorg()+"%' and STATUS in (50500,50502) )");

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxPayDetailNow> result = new ArrayList<DataTaxPayDetailNow>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxPayDetailNow entity1 = new DataTaxPayDetailNow();
            entity1.setDataTaxPay(dtp);
            entity1.setYear(item[2]==null?"":item[2].toString());	
        	entity1.setMonth(item[3]==null?"":item[3].toString());	
			entity1.setOrg(dtp.getOrg());
			entity1.setOrgName(dtp.getOrgName());
			entity1.setBusinessTax(item[6]==null?"":item[6].toString());
			entity1.setValueAddedTax(item[7]==null?"":item[7].toString());
			entity1.setConsumptionTax(item[8]==null?"":item[8].toString());
			entity1.setCesTax(item[9]==null?"":item[9].toString());
			entity1.setImportVat(item[10]==null?"":item[10].toString());
			entity1.setTariff(item[11]==null?"":item[11].toString());
			entity1.setEincomeTax(item[12]==null?"":item[12].toString());
			entity1.setWithholdTax(item[13]==null?"":item[13].toString());
			entity1.setPincomeTax(item[14]==null?"":item[13].toString());
			entity1.setHousingTax(item[15]==null?"":item[15].toString());
			entity1.setDeedTax(item[16]==null?"":item[16].toString());
			entity1.setLandTax(item[17]==null?"":item[17].toString());
			entity1.setLandVat(item[18]==null?"":item[18].toString());
			entity1.setStampTax(item[19]==null?"":item[19].toString());
			entity1.setOtherTax(item[20]==null?"":item[20].toString());
			entity1.setSum(item[21]==null?"":item[21].toString());
			entity1.setIsdel((Integer) (item[22]==null? 0:(Integer)item[22]));
			entity1.setParentorg(dtp.getParentorg());
			entity1.setType((Integer) (item[24]==null? 3:(Integer)item[24]));
			result.add(entity1);
			
			}
		
		return result;
	}

	@Override
	public List<DataTaxPayDetailPrev> getnewListPre2(DataTaxPay dtp) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.MONTH,a.org,a.orgName,sum(a.businessTax) AS businessTax,sum(a.valueAddedTax) AS valueAddedTax,");
		hql.append("sum( a.consumptionTax ) AS consumptionTax,sum(a.cesTax) AS cesTax,sum(a.importVAT) AS importVAT,sum( a.tariff ) AS tariff,");
		hql.append("sum(a.eIncomeTax ) AS eIncomeTax,sum( a.withholdTax ) AS withholdTax,sum( a.pIncomeTax ) AS pIncomeTax,sum( a.housingTax ) AS housingTax,");
		hql.append("sum( a.deedTax ) AS deedTax,sum( a.landTax ) AS landTax,sum( a.landVAT ) AS landVAT,sum( a.stampTax ) AS stampTax,");
		hql.append("sum( a.otherTax ) AS otherTax,sum( a.sum ) AS sum,a.isdel,a.parentorg ");
		hql.append("FROM data_taxPay_detail_prev a WHERE a.year =  '"+dtp.getYear()+"' AND  a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
		hql.append("WHERE vcParentID like '%"+dtp.getParentorg()+"%' and STATUS in (50500,50502) )");

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxPayDetailPrev> result = new ArrayList<DataTaxPayDetailPrev>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxPayDetailPrev entity1 = new DataTaxPayDetailPrev();
			entity1.setDataTaxPay(dtp);
			entity1.setYear(item[2]==null?"":item[2].toString());
			entity1.setMonth(item[3]==null?"":item[3].toString());	
			entity1.setOrg(dtp.getOrg());
			entity1.setOrgName(dtp.getOrgName());
			entity1.setBusinessTax(item[6]==null?"":item[6].toString());
			entity1.setValueAddedTax(item[7]==null?"":item[7].toString());
			entity1.setConsumptionTax(item[8]==null?"":item[8].toString());
			entity1.setCesTax(item[9]==null?"":item[9].toString());
			entity1.setImportVat(item[10]==null?"":item[10].toString());
			entity1.setTariff(item[11]==null?"":item[11].toString());
			entity1.setEincomeTax(item[12]==null?"":item[12].toString());
			entity1.setWithholdTax(item[13]==null?"":item[13].toString());
			entity1.setPincomeTax(item[14]==null?"":item[13].toString());
			entity1.setHousingTax(item[15]==null?"":item[15].toString());
			entity1.setDeedTax(item[16]==null?"":item[16].toString());
			entity1.setLandTax(item[17]==null?"":item[17].toString());
			entity1.setLandVat(item[18]==null?"":item[18].toString());
			entity1.setStampTax(item[19]==null?"":item[19].toString());
			entity1.setOtherTax(item[20]==null?"":item[20].toString());
			entity1.setSum(item[21]==null?"":item[21].toString());
			entity1.setIsdel((Integer) (item[22]==null? 0:(Integer)item[22]));
			entity1.setParentorg(dtp.getParentorg());
			result.add(entity1);
			}
		
		return result;
	}

	
	
	/**
	 * 获取该公司是否为虚拟公司
	 */
	@Override
	public int getStatus(Integer id) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select b.status  FROM data_taxPay a ,hh_organInfo_tree_relation b where  a.id="+id+" and a.org=b.nNodeID");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer status = Integer.parseInt(query.uniqueResult().toString());
		return status;
	}

	@Override
	public List<DataTaxPayDetailNow> getDetailNow(DataTaxPay dtp,Integer id) {
		// TODO Auto-aa method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id, a.pid, a.YEAR, a.MONTH, a.org, a.orgName, a.businessTax , a.valueAddedTax, a.consumptionTax, a.cesTax, a.importVAT,");
		hql.append("a.tariff,a.eIncomeTax,a.withholdTax,a.pIncomeTax,a.housingTax,a.deedTax,a.landTax,a.landVAT,a.stampTax,a.otherTax,a.sum,a.isdel,a.parentorg,a.type ");
		hql.append("FROM data_taxPay_detail_now a WHERE a.year = '"+dtp.getYear()+"' AND a.org IN ( select nNodeID from hh_organInfo_tree_relation where vcParentID in( SELECT vcOrganID FROM hh_organInfo_tree_relation WHERE nNodeID ='"+dtp.getOrg()+"')  and  a.year = '"+dtp.getYear()+"' )");
		hql.append("union SELECT a.id, a.pid, a.YEAR, a.MONTH, a.org, a.orgName, a.businessTax , a.valueAddedTax, a.consumptionTax, a.cesTax, a.importVAT,");
		hql.append("a.tariff,a.eIncomeTax,a.withholdTax,a.pIncomeTax,a.housingTax,a.deedTax,a.landTax,a.landVAT,a.stampTax,a.otherTax,a.sum,a.isdel,a.parentorg,a.type ");
		hql.append("FROM data_taxPay_detail_now a where pid = "+id+"  and a.year ='"+dtp.getYear()+"' ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxPayDetailNow> result = new ArrayList<DataTaxPayDetailNow>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxPayDetailNow entity1 = new DataTaxPayDetailNow();
			entity1.setId((Integer) (item[0]==null?"":(Integer)item[0]));
            entity1.setDataTaxPay(dtp);
			entity1.setYear(item[2]==null?"":item[2].toString());
			entity1.setMonth(item[3]==null?"":item[3].toString());	
			entity1.setOrg(item[4]==null?"":item[4].toString());
			entity1.setOrgName(item[5]==null?"":item[5].toString());
			entity1.setBusinessTax(item[6]==null?"":item[6].toString());
			entity1.setValueAddedTax(item[7]==null?"":item[7].toString());
			entity1.setConsumptionTax(item[8]==null?"":item[8].toString());
			entity1.setCesTax(item[9]==null?"":item[9].toString());
			entity1.setImportVat(item[10]==null?"":item[10].toString());
			entity1.setTariff(item[11]==null?"":item[11].toString());
			entity1.setEincomeTax(item[12]==null?"":item[12].toString());
			entity1.setWithholdTax(item[13]==null?"":item[13].toString());
			entity1.setPincomeTax(item[14]==null?"":item[13].toString());
			entity1.setHousingTax(item[15]==null?"":item[15].toString());
			entity1.setDeedTax(item[16]==null?"":item[16].toString());
			entity1.setLandTax(item[17]==null?"":item[17].toString());
			entity1.setLandVat(item[18]==null?"":item[18].toString());
			entity1.setStampTax(item[19]==null?"":item[19].toString());
			entity1.setOtherTax(item[20]==null?"":item[20].toString());
			entity1.setSum(item[21]==null?"":item[21].toString());
			entity1.setIsdel((Integer) (item[22]==null? 0:(Integer)item[22]));
			entity1.setParentorg(item[23]==null?"":item[23].toString());
			entity1.setType((Integer) (item[24]==null? 3:(Integer)item[24]));
			result.add(entity1);
			
			}
		
		return result;
	}

	@Override
	public List<DataTaxPayDetailPrev> getDetailPre(DataTaxPay dtp,Integer id) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id, a.pid, a.YEAR, a.MONTH, a.org, a.orgName, a.businessTax , a.valueAddedTax, a.consumptionTax, a.cesTax, a.importVAT,");
		hql.append("a.tariff,a.eIncomeTax,a.withholdTax,a.pIncomeTax,a.housingTax,a.deedTax,a.landTax,a.landVAT,a.stampTax,a.otherTax,a.sum,a.isdel,a.parentorg ");
		hql.append("FROM data_taxPay_detail_prev a WHERE a.year = '"+dtp.getYear()+"' AND  a.org IN ( select nNodeID from hh_organInfo_tree_relation where vcParentID in( SELECT vcOrganID FROM hh_organInfo_tree_relation WHERE nNodeID ='"+dtp.getOrg()+"')  and  a.year ='"+dtp.getYear()+"' )");
		hql.append("union SELECT a.id, a.pid, a.YEAR, a.MONTH, a.org, a.orgName, a.businessTax , a.valueAddedTax, a.consumptionTax, a.cesTax, a.importVAT,");
		hql.append("a.tariff,a.eIncomeTax,a.withholdTax,a.pIncomeTax,a.housingTax,a.deedTax,a.landTax,a.landVAT,a.stampTax,a.otherTax,a.sum,a.isdel,a.parentorg ");
		hql.append("FROM data_taxPay_detail_prev a where pid = "+id+"  and a.year ='"+dtp.getYear()+"' ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxPayDetailPrev> result = new ArrayList<DataTaxPayDetailPrev>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxPayDetailPrev entity1 = new DataTaxPayDetailPrev();
			entity1.setId((Integer) (item[0]==null?"":(Integer)item[0]));
            entity1.setDataTaxPay(dtp);
			entity1.setYear(item[2]==null?"":item[2].toString());	
			entity1.setMonth(item[3]==null?"":item[3].toString());	
			entity1.setOrg(item[4]==null?"":item[4].toString());
			entity1.setOrgName(item[5]==null?"":item[5].toString());
			entity1.setBusinessTax(item[6]==null?"":item[6].toString());
			entity1.setValueAddedTax(item[7]==null?"":item[7].toString());
			entity1.setConsumptionTax(item[8]==null?"":item[8].toString());
			entity1.setCesTax(item[9]==null?"":item[9].toString());
			entity1.setImportVat(item[10]==null?"":item[10].toString());
			entity1.setTariff(item[11]==null?"":item[11].toString());
			entity1.setEincomeTax(item[12]==null?"":item[12].toString());
			entity1.setWithholdTax(item[13]==null?"":item[13].toString());
			entity1.setPincomeTax(item[14]==null?"":item[13].toString());
			entity1.setHousingTax(item[15]==null?"":item[15].toString());
			entity1.setDeedTax(item[16]==null?"":item[16].toString());
			entity1.setLandTax(item[17]==null?"":item[17].toString());
			entity1.setLandVat(item[18]==null?"":item[18].toString());
			entity1.setStampTax(item[19]==null?"":item[19].toString());
			entity1.setOtherTax(item[20]==null?"":item[20].toString());
			entity1.setSum(item[21]==null?"":item[21].toString());
			entity1.setIsdel((Integer) (item[22]==null? 0:(Integer)item[22]));
			entity1.setParentorg(item[23]==null?"":item[23].toString());
			result.add(entity1);
			}
		
		return result;
	}

	/**
	 * 判断虚拟公司是否存在
	 */
	@Override
	public List<DataTaxPay> getTaxSaveId(DataTaxPay entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select a.id  FROM data_taxPay a where a.org='"+entity.getOrg()+"'");
		if(Common.notEmpty(entity.getYear()))
		{
			hql.append(" and a.year = '"+entity.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	    
		return query.list();
	}

	/**
	 * 判断虚拟公司
	 */
	@Override
	public boolean isvirtual(DataTaxPay entity) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		hql.append("select status from  hh_organInfo_tree_relation where nNodeID='"+entity.getOrg()+"'");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer a = Integer.parseInt(query.uniqueResult().toString());
		if(a.equals(50501))
		   return true;
		else
		   return false;
	}

	@Override
	public List<Object[]> getTaxNoCreateCompanyList(String reportTime,
			String authdata, String org, Integer formKind, Integer CompanyKind,
			Integer offset, Integer pageSize) {
		String year1 = "";
		if (Common.notEmpty(reportTime)) {
			year1 = reportTime.substring(0, 4);
		}
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT hotr.vcFullName,hotr.vcShortName,(select description from hh_base where id = hotr.status),hotr.nNodeID,").
			append("hotr.responsiblePersonName,hotr.vcEmployeeID,hotr.responsiblePersonEmail,rfc.isRemind,rfc.lastRemindTime").
			append(" FROM hh_organInfo_tree_relation hotr LEFT JOIN ( SELECT t.org FROM data_taxPay t WHERE ");
		hql.append("  t.isdel = 0 AND t.year = '").append(reportTime)
				.append("'");
		hql.append(") temp1 ON temp1.org = hotr.nNodeID ").
			append(" left join (select * from report_forms_unFilled_company rfc_temp where rfc_temp.isdel =0 and rfc_temp.updatetime='"+
					reportTime+"' and rfc_temp.formkind=51113)rfc on hotr.nNodeID = rfc.nNodeID "); 
		hql.append(" WHERE hotr.cFlag = 1 AND temp1.org IS NULL");

		if(Common.notEmpty(org)){
			hql.append(" and  hotr.vcOrganID like '%-"+org+ "-%'");
		}else{
			if(Common.notEmpty(authdata)){
				hql.append(" and (");
				String [] dd = authdata.split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and hotr.vcOrganID like '%--%' ");
			}
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				hql.toString());
		if (offset != null && pageSize != null) {
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}
	
	@Override
	public List<Object[]> getTaxNoRemindCompanyList(String reportTime,
			String authdata, String org, Integer formKind, Integer CompanyKind,
			Integer offset, Integer pageSize) {
		String year1 = "";
		if (Common.notEmpty(reportTime)) {
			year1 = reportTime.substring(0, 4);
		}
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT hotr.vcFullName,hotr.vcShortName,(select description from hh_base where id = hotr.status),hotr.nNodeID,").
			append("hotr.responsiblePersonName,hotr.vcEmployeeID,hotr.responsiblePersonEmail,rfc.isRemind,rfc.lastRemindTime").
			append(" FROM hh_organInfo_tree_relation hotr LEFT JOIN ( SELECT t.org FROM data_taxPay t WHERE ");
		hql.append("  t.isdel = 0 AND t.year = '").append(reportTime)
				.append("'");
		hql.append(") temp1 ON temp1.org = hotr.nNodeID ").
			append(" left join (select * from report_forms_unFilled_company rfc_temp where rfc_temp.isdel =0 and rfc_temp.updatetime='"+
					reportTime+"' and rfc_temp.formkind=51113)rfc on hotr.nNodeID = rfc.nNodeID "); 
		hql.append(" WHERE hotr.cFlag = 1 AND temp1.org IS NULL AND  rfc.isRemind = 0");
		if(Common.notEmpty(authdata)){
			hql.append(" and (");
			String [] dd = authdata.split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			hql.append(" and ort.vcOrganID like '%--%' ");
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				hql.toString());
		if (offset != null && pageSize != null) {
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}
	

}
