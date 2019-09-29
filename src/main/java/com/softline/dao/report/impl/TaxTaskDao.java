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
import com.softline.dao.report.ITaxTaskDao;
import com.softline.entity.DataTaxSave;
import com.softline.entity.taxTask.DataTaxTask;
import com.softline.entity.taxTask.DataTaxTaskDetailFavouredPolicy;
import com.softline.entity.taxTask.DataTaxTaskDetailInTaxReturn;
import com.softline.entity.taxTask.DataTaxTaskDetailTaxPlan;
import com.softline.entity.taxTask.DataTaxTaskDetailTaxReturn;

@Repository(value = "taxTaskDao")
/**
 * 
 * @author zy
 *
 */
public class TaxTaskDao implements ITaxTaskDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 单个主键查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataTaxTask getTaxTask(Integer id) {
		if (id == null)
			return new DataTaxTask();
		StringBuilder hql = new StringBuilder();
		hql.append(" from DataTaxTask s where 1=1 and isdel=0 and id=" + id);
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (DataTaxTask) query.uniqueResult();
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
	public List<DataTaxTask> getTaxTaskList(DataTaxTask entity,
			Integer offsize, Integer pagesize, Integer fun) {
		if (entity == null)
			return new ArrayList<DataTaxTask>();
		StringBuilder hql = new StringBuilder();
		hql.append(" from DataTaxTask s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" AND s.status <> 50112 ");
		}
		if (entity != null) {
			if (Common.notEmpty(entity.getOrg())) {
				hql.append(" and s.org in( "
						+ Common.dealinStr(entity.getOrg()) + ") ");
			}

			if (entity.getYear() != null) {
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
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.authOrg like '%--%' ");
			}
//			hql.append(" and s.parentorg like '%-" + entity.getParentorg()
//					+ "-%'");
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
	public int getTaxTaskListCount(DataTaxTask entity, Integer fun) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataTaxTask s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" AND s.status <> 50112 ");
		}
		if (entity != null) {
			if (Common.notEmpty(entity.getOrg())) {
				hql.append(" and s.org in( "
						+ Common.dealinStr(entity.getOrg()) + ") ");
			}
			if (entity.getYear() != null) {
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
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.authOrg like '%--%' ");
			}
//			hql.append(" and (s.parentorg like '%-" + entity.getParentorg()
//					+ "-%' or s.org = '" + entity.getParentorg() + "')");
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
	public boolean saveTaxTaskRepeatCheck(DataTaxTask entity) {

		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataTaxTask s where 1=1 and isdel=0  ");
		if (entity != null) {
			if (Common.notEmpty(entity.getOrg())) {
				hql.append(" and s.org = '" + entity.getOrg() + "' ");
			}
			if (entity.getYear() != null) {
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
	public boolean checkCanEdit(DataTaxTask entity) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataTaxTask s where 1=1 and isdel=0 and status in("
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

	
	
	public int getFavId(DataTaxTask entity){
		StringBuilder  hql = new StringBuilder();
		hql.append("select a.id  FROM data_taxTask_detail_favouredPolicy a  where  a.org='"+entity.getOrg()+"'");
		if(entity.getYear()!=null)
		{
			hql.append(" and a.year = '"+entity.getYear()+"' ");
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer newId = Integer.parseInt(query.uniqueResult().toString());
		return newId;
	}
	
	public int getInTaxReturnId(DataTaxTask entity){
		StringBuilder  hql = new StringBuilder();
		hql.append("select a.id  FROM data_taxTask_detail_inTaxReturn a  where  a.org='"+entity.getOrg()+"'");
		if(entity.getYear()!=null)
		{
			hql.append(" and a.year = '"+entity.getYear()+"' ");
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer newId = Integer.parseInt(query.uniqueResult().toString());
		return newId;
	}
	
	public int getTaxPlanId(DataTaxTask entity){
		StringBuilder  hql = new StringBuilder();
		hql.append("select a.id  FROM data_taxTask_detail_taxPlan a  where  a.org='"+entity.getOrg()+"'");
		if(entity.getYear()!=null)
		{
			hql.append(" and a.year = '"+entity.getYear()+"' ");
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer newId = Integer.parseInt(query.uniqueResult().toString());
		return newId;
	}
	
	public int getTaxReturnId(DataTaxTask entity){
		StringBuilder  hql = new StringBuilder();
		hql.append("select a.id  FROM data_taxTask_detail_taxReturn a  where  a.org='"+entity.getOrg()+"'");
		if(entity.getYear()!=null)
		{
			hql.append(" and a.year = '"+entity.getYear()+"' ");
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer newId = Integer.parseInt(query.uniqueResult().toString());
		return newId;
	}
	
	/**
	 * 汇总
	 * 虚拟公司数据存在的情况
	 */
	@Override
	public List<DataTaxTask> getHrFormsListCollectView(DataTaxTask dtt) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
        hql.append("SELECT a.id, a.YEAR,a.org,a.orgName,t.taxTask,t.taxPlan,t.favouredPolicy,t.taxReturn,");
        hql.append("t.inTaxReturn,a.STATUS,a.isdel,a.reportPersonID,a.reportPersonName,a.reportDate,");
        hql.append("a.auditorPersonID,a.auditorPersonName,a.auditorDate,a.createPersonID,a.createPersonName,");
        hql.append("a.createDate,a.lastModifyPersonID,a.lastModifyPersonName,a.lastModifyDate,a.parentorg ");
        hql.append("FROM data_taxTask a,hh_organInfo_tree_relation b,(SELECT sum( taxTask ) AS taxTask,");
        hql.append("sum( taxPlan ) AS taxPlan, sum( favouredPolicy ) AS favouredPolicy,");
        hql.append("sum( a.taxReturn ) AS taxReturn,sum( a.inTaxReturn ) AS inTaxReturn ");
        hql.append("FROM data_taxTask a WHERE a.year = '"+dtt.getYear()+"' and a.STATUS= 50115  and a.org IN ( SELECT nNodeID FROM hh_organInfo_tree_relation WHERE ");
        hql.append("vcParentID like '%"+dtt.getParentorg()+"%' and STATUS in (50500,50502) )) t ");
        hql.append("WHERE a.org = b.nNodeID AND b.nNodeID = '"+dtt.getOrg()+"'");
		if(dtt.getYear()!=null)
		{
			hql.append(" and a.year = '"+dtt.getYear()+"' ");
		}
	
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}
	
	/**
	 * 汇总
	 * 虚拟公司数据存在的情况
	 */
	@Override
	public List<DataTaxTask> getHrFormsListCollectView2(DataTaxTask dtt) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();

        hql.append("SELECT sum( taxTask ) AS taxTask,sum( taxPlan ) AS taxPlan, sum( favouredPolicy ) AS favouredPolicy,");
        hql.append("sum( a.taxReturn ) AS taxReturn,sum( a.inTaxReturn ) AS inTaxReturn ,b.vcOrganID ");
        hql.append(" FROM data_taxTask a ,hh_organInfo_tree_relation b WHERE a.year = '"+dtt.getYear()+"' and a.STATUS= 50115  and b.nNodeID='"+dtt.getOrg()+"' ");
        hql.append(" and a.org IN ( SELECT nNodeID FROM hh_organInfo_tree_relation WHERE  ");
        hql.append("vcParentID like '%"+dtt.getParentorg()+"%' and STATUS in (50500,50502)) ");	
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}
	
	/**
	 * 汇总数据获取新增
	 */
	/**
	 * 汇总数据新增获取
	 * 优惠政策申请年度节税目标
	 */
	public List<DataTaxTaskDetailFavouredPolicy> getnewListFav(DataTaxTask dtt) {
		// TODO Auto-generated method stub
		int newId = getFavId(dtt);
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.org,a.orgName,sum( a.month1 ) AS month1,sum( a.month2 ) AS month2,sum( a.month3 ) AS month3,");
		hql.append("sum( a.month4 ) AS month4,sum( a.month5 ) AS month5,sum( a.month6 ) AS month6,sum( a.month7 ) AS month7,");
		hql.append("sum( a.month8 ) AS month8,sum( a.month9 ) AS month9,sum( a.month10 ) AS month10,sum( a.month11 ) AS month11,");
		hql.append("sum( a.month12 ) AS month12,sum( a.sum ) AS sum, a.isdel, a.parentorg ");
		hql.append("FROM data_taxTask_detail_favouredPolicy a WHERE  a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
     	hql.append("WHERE vcParentID like '%"+dtt.getParentorg()+"%' and STATUS in (50500,50502)  )");
		if(dtt.getYear()!=null)
		{
			hql.append(" and a.year = '"+dtt.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailFavouredPolicy> result = new ArrayList<DataTaxTaskDetailFavouredPolicy>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailFavouredPolicy entity1 = new DataTaxTaskDetailFavouredPolicy();
			entity1.setId(newId);
			entity1.setDataTaxTask(dtt);	
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(dtt.getOrg());
			entity1.setOrgName(dtt.getOrgName());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(dtt.getParentorg());
			result.add(entity1);		
			}	
		return result;
	}
	//非税收返还年度目标
	public List<DataTaxTaskDetailInTaxReturn> getnewListIntax(DataTaxTask dtt) {
		// TODO Auto-generated method stub
		int newId = getInTaxReturnId(dtt);
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.org,a.orgName,sum( a.month1 ) AS month1,sum( a.month2 ) AS month2,sum( a.month3 ) AS month3,");
		hql.append("sum( a.month4 ) AS month4,sum( a.month5 ) AS month5,sum( a.month6 ) AS month6,sum( a.month7 ) AS month7,");
		hql.append("sum( a.month8 ) AS month8,sum( a.month9 ) AS month9,sum( a.month10 ) AS month10,sum( a.month11 ) AS month11,");
		hql.append("sum( a.month12 ) AS month12,sum( a.sum ) AS sum, a.isdel, a.parentorg ");
		hql.append("FROM data_taxTask_detail_inTaxReturn a WHERE a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
     	hql.append("WHERE vcParentID  like '%"+dtt.getParentorg()+"%' and STATUS in (50500,50502)  )");
		if(dtt.getYear()!=null)
		{
			hql.append(" and a.year = '"+dtt.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailInTaxReturn> result = new ArrayList<DataTaxTaskDetailInTaxReturn>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailInTaxReturn entity1 = new DataTaxTaskDetailInTaxReturn();
			entity1.setId(newId);
			entity1.setDataTaxTask(dtt);	
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(dtt.getOrg());
			entity1.setOrgName(dtt.getOrgName());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(dtt.getParentorg());
			result.add(entity1);		
			}	
		return result;
	}
	
	//税收筹划年度目标
	public List<DataTaxTaskDetailTaxPlan> getnewListPlan(DataTaxTask dtt) {
		// TODO Auto-generated method stub
		int newId = getTaxPlanId(dtt);
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.org,a.orgName,sum( a.month1 ) AS month1,sum( a.month2 ) AS month2,sum( a.month3 ) AS month3,");
		hql.append("sum( a.month4 ) AS month4,sum( a.month5 ) AS month5,sum( a.month6 ) AS month6,sum( a.month7 ) AS month7,");
		hql.append("sum( a.month8 ) AS month8,sum( a.month9 ) AS month9,sum( a.month10 ) AS month10,sum( a.month11 ) AS month11,");
		hql.append("sum( a.month12 ) AS month12,sum( a.sum ) AS sum, a.isdel, a.parentorg ");
		hql.append("FROM data_taxTask_detail_taxPlan a WHERE  a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
     	hql.append("WHERE vcParentID like '%"+dtt.getParentorg()+"%' and STATUS in (50500,50502)  )");
		if(dtt.getYear()!=null)
		{
			hql.append(" and a.year = '"+dtt.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailTaxPlan> result = new ArrayList<DataTaxTaskDetailTaxPlan>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailTaxPlan entity1 = new DataTaxTaskDetailTaxPlan();
			entity1.setId(newId);
			entity1.setDataTaxTask(dtt);
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(dtt.getOrg());
			entity1.setOrgName(dtt.getOrgName());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(dtt.getParentorg());
			result.add(entity1);		
			}	
		return result;
	}
	
	//税收返还年度目标
	public List<DataTaxTaskDetailTaxReturn> getnewListReturn(DataTaxTask dtt) {
		// TODO Auto-generated method stub
		int newId = getTaxReturnId(dtt);
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.org,a.orgName,sum( a.month1 ) AS month1,sum( a.month2 ) AS month2,sum( a.month3 ) AS month3,");
		hql.append("sum( a.month4 ) AS month4,sum( a.month5 ) AS month5,sum( a.month6 ) AS month6,sum( a.month7 ) AS month7,");
		hql.append("sum( a.month8 ) AS month8,sum( a.month9 ) AS month9,sum( a.month10 ) AS month10,sum( a.month11 ) AS month11,");
		hql.append("sum( a.month12 ) AS month12,sum( a.sum ) AS sum, a.isdel, a.parentorg ");
		hql.append("FROM data_taxTask_detail_taxReturn a WHERE a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
     	hql.append("WHERE vcParentID like '%"+dtt.getParentorg()+"%' and STATUS in (50500,50502)  )");
		if(dtt.getYear()!=null)
		{
			hql.append(" and a.year = '"+dtt.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailTaxReturn> result = new ArrayList<DataTaxTaskDetailTaxReturn>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailTaxReturn entity1 = new DataTaxTaskDetailTaxReturn();
			entity1.setId(newId);
			entity1.setDataTaxTask(dtt);	
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(dtt.getOrg());
			entity1.setOrgName(dtt.getOrgName());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(dtt.getParentorg());
			result.add(entity1);		
			}	
		return result;
	}

	@Override
	public List<DataTaxTaskDetailFavouredPolicy> getnewListFav2(
			DataTaxTask dtt) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.org,a.orgName,sum( a.month1 ) AS month1,sum( a.month2 ) AS month2,sum( a.month3 ) AS month3,");
		hql.append("sum( a.month4 ) AS month4,sum( a.month5 ) AS month5,sum( a.month6 ) AS month6,sum( a.month7 ) AS month7,");
		hql.append("sum( a.month8 ) AS month8,sum( a.month9 ) AS month9,sum( a.month10 ) AS month10,sum( a.month11 ) AS month11,");
		hql.append("sum( a.month12 ) AS month12,sum( a.sum ) AS sum, a.isdel, a.parentorg ");
		hql.append("FROM data_taxTask_detail_favouredPolicy a WHERE  a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
     	hql.append("WHERE vcParentID  like '%"+dtt.getParentorg()+"%' and STATUS in (50500,50502) )");
		if(dtt.getYear()!=null)
		{
			hql.append(" and a.year = '"+dtt.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailFavouredPolicy> result = new ArrayList<DataTaxTaskDetailFavouredPolicy>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailFavouredPolicy entity1 = new DataTaxTaskDetailFavouredPolicy();
			entity1.setDataTaxTask(dtt);	
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(dtt.getOrg());
			entity1.setOrgName(dtt.getOrgName());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(dtt.getParentorg());
			result.add(entity1);		
			}	
		return result;
	}

	@Override
	public List<DataTaxTaskDetailInTaxReturn> getnewListIntax2(
			DataTaxTask dtt) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.org,a.orgName,sum( a.month1 ) AS month1,sum( a.month2 ) AS month2,sum( a.month3 ) AS month3,");
		hql.append("sum( a.month4 ) AS month4,sum( a.month5 ) AS month5,sum( a.month6 ) AS month6,sum( a.month7 ) AS month7,");
		hql.append("sum( a.month8 ) AS month8,sum( a.month9 ) AS month9,sum( a.month10 ) AS month10,sum( a.month11 ) AS month11,");
		hql.append("sum( a.month12 ) AS month12,sum( a.sum ) AS sum, a.isdel, a.parentorg ");
		hql.append("FROM data_taxTask_detail_inTaxReturn a WHERE  a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
     	hql.append("WHERE vcParentID like '%"+dtt.getParentorg()+"%' and STATUS in (50500,50502)  )");
		if(dtt.getYear()!=null)
		{
			hql.append(" and a.year = '"+dtt.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailInTaxReturn> result = new ArrayList<DataTaxTaskDetailInTaxReturn>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailInTaxReturn entity1 = new DataTaxTaskDetailInTaxReturn();
			entity1.setDataTaxTask(dtt);	
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(dtt.getOrg());
			entity1.setOrgName(dtt.getOrgName());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(dtt.getParentorg());
			result.add(entity1);		
			}	
		return result;
	}

	@Override
	public List<DataTaxTaskDetailTaxPlan> getnewListPlan2(DataTaxTask dtt) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.org,a.orgName,sum( a.month1 ) AS month1,sum( a.month2 ) AS month2,sum( a.month3 ) AS month3,");
		hql.append("sum( a.month4 ) AS month4,sum( a.month5 ) AS month5,sum( a.month6 ) AS month6,sum( a.month7 ) AS month7,");
		hql.append("sum( a.month8 ) AS month8,sum( a.month9 ) AS month9,sum( a.month10 ) AS month10,sum( a.month11 ) AS month11,");
		hql.append("sum( a.month12 ) AS month12,sum( a.sum ) AS sum, a.isdel, a.parentorg ");
		hql.append("FROM data_taxTask_detail_taxPlan a WHERE  a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
     	hql.append("WHERE vcParentID like '%"+dtt.getParentorg()+"%' and STATUS in (50500,50502) )");
		if(dtt.getYear()!=null)
		{
			hql.append(" and a.year = '"+dtt.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailTaxPlan> result = new ArrayList<DataTaxTaskDetailTaxPlan>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailTaxPlan entity1 = new DataTaxTaskDetailTaxPlan();
			entity1.setDataTaxTask(dtt);
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(dtt.getOrg());
			entity1.setOrgName(dtt.getOrgName());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(dtt.getParentorg());
			result.add(entity1);		
			}	
		return result;
	}

	@Override
	public List<DataTaxTaskDetailTaxReturn> getnewListReturn2(DataTaxTask dtt) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.org,a.orgName,sum( a.month1 ) AS month1,sum( a.month2 ) AS month2,sum( a.month3 ) AS month3,");
		hql.append("sum( a.month4 ) AS month4,sum( a.month5 ) AS month5,sum( a.month6 ) AS month6,sum( a.month7 ) AS month7,");
		hql.append("sum( a.month8 ) AS month8,sum( a.month9 ) AS month9,sum( a.month10 ) AS month10,sum( a.month11 ) AS month11,");
		hql.append("sum( a.month12 ) AS month12,sum( a.sum ) AS sum, a.isdel, a.parentorg ");
		hql.append("FROM data_taxTask_detail_taxReturn a WHERE a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
     	hql.append("WHERE vcParentID like '%"+dtt.getParentorg()+"%' and STATUS in (50500,50502) )");
		if(dtt.getYear()!=null)
		{
			hql.append(" and a.year = '"+dtt.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailTaxReturn> result = new ArrayList<DataTaxTaskDetailTaxReturn>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailTaxReturn entity1 = new DataTaxTaskDetailTaxReturn();
			entity1.setDataTaxTask(dtt);	
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(dtt.getOrg());
			entity1.setOrgName(dtt.getOrgName());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(dtt.getParentorg());
			result.add(entity1);		
			}	
		return result;
	}
	
	
	
	@Override
	public void saveFav(DataTaxTask entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("update data_taxTask_detail_favouredPolicy set  pid = "+entity.getId()+"  where orgName =' "+entity.getOrgName()+" ' ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		query.executeUpdate();
		
	}

	@Override
	public void saveInTax(DataTaxTask entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("update data_taxTask_detail_inTaxReturn set  pid = '"+entity.getId()+" ' where orgName ='"+entity.getOrgName()+"' ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		query.executeUpdate();
	}

	@Override
	public void savePlan(DataTaxTask entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("update data_taxTask_detail_taxPlan set  pid = "+entity.getId()+"  where orgName ='"+entity.getOrgName()+"' ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		query.executeUpdate();
	}

	@Override
	public void saveReturn(DataTaxTask entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("update data_taxTask_detail_taxReturn set  pid = "+entity.getId()+"  where orgName ='"+entity.getOrgName()+"' ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		query.executeUpdate();
	}

	@Override
	public int getStatus(Integer id) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select b.status  FROM data_taxTask a ,hh_organInfo_tree_relation b where  a.id="+id+" and a.org=b.nNodeID");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer status = Integer.parseInt(query.uniqueResult().toString());
		return status;
	}

	@Override
	public List<DataTaxTaskDetailTaxReturn> getDetailRet(
			DataTaxTask dtt, Integer id) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id ,a.pid, a.YEAR, a.org, a.orgName, a.month1, a.month2, a.month3, a.month4, a.month5, a.month6,");
		hql.append("a.month7, a.month8, a.month9, a.month10, a.month11, a.month12, a.sum, a.isdel,a.parentorg ");
		hql.append("FROM data_taxTask_detail_taxReturn a WHERE  a.year ='"+dtt.getYear()+"' and a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
		hql.append("WHERE vcParentID IN ( SELECT vcOrganID FROM hh_organInfo_tree_relation WHERE nNodeID = '"+dtt.getOrg()+"' ) AND a.YEAR = '"+dtt.getYear()+"' )");
		hql.append("union SELECT a.id ,a.pid, a.YEAR, a.org, a.orgName, a.month1, a.month2, a.month3, a.month4, a.month5, a.month6,");
		hql.append("a.month7, a.month8, a.month9, a.month10, a.month11, a.month12, a.sum, a.isdel,a.parentorg ");
		hql.append("FROM data_taxTask_detail_taxReturn a WHERE a.pid = "+id+" ");

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailTaxReturn> result = new ArrayList<DataTaxTaskDetailTaxReturn>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailTaxReturn entity1 = new DataTaxTaskDetailTaxReturn();
			entity1.setId((Integer) (item[0]==null?"":(Integer)item[0]));
			entity1.setDataTaxTask(dtt);	
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(item[3]==null?"":item[3].toString());
			entity1.setOrgName(item[4]==null?"":item[4].toString());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(item[19]==null?"":item[19].toString());
			result.add(entity1);		
			}	
		return result;
	}

	@Override
	public List<DataTaxTaskDetailFavouredPolicy> getDetailFav(
			DataTaxTask dtt, Integer id) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id ,a.pid, a.YEAR, a.org, a.orgName, a.month1, a.month2, a.month3, a.month4, a.month5, a.month6,");
		hql.append("a.month7, a.month8, a.month9, a.month10, a.month11, a.month12, a.sum, a.isdel,a.parentorg ");
		hql.append("FROM data_taxTask_detail_favouredPolicy a WHERE  a.year = '"+dtt.getYear()+"' and a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
		hql.append("WHERE vcParentID IN ( SELECT vcOrganID FROM hh_organInfo_tree_relation WHERE nNodeID = '"+dtt.getOrg()+"' ) AND a.YEAR = '"+dtt.getYear()+"' )");
		hql.append("union SELECT a.id ,a.pid, a.YEAR, a.org, a.orgName, a.month1, a.month2, a.month3, a.month4, a.month5, a.month6,");
		hql.append("a.month7, a.month8, a.month9, a.month10, a.month11, a.month12, a.sum, a.isdel,a.parentorg ");
		hql.append("FROM data_taxTask_detail_favouredPolicy a WHERE a.pid = "+id+" ");

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailFavouredPolicy> result = new ArrayList<DataTaxTaskDetailFavouredPolicy>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailFavouredPolicy entity1 = new DataTaxTaskDetailFavouredPolicy();
			entity1.setId((Integer) (item[0]==null?"":(Integer)item[0]));
			entity1.setDataTaxTask(dtt);	
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(item[3]==null?"":item[3].toString());
			entity1.setOrgName(item[4]==null?"":item[4].toString());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(item[19]==null?"":item[19].toString());
			result.add(entity1);		
			}	
		return result;
	}
	
	@Override
	public List<DataTaxTaskDetailTaxPlan> getDetailPlan(DataTaxTask dtt,
			Integer id) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id ,a.pid, a.YEAR, a.org, a.orgName, a.month1, a.month2, a.month3, a.month4, a.month5, a.month6,");
		hql.append("a.month7, a.month8, a.month9, a.month10, a.month11, a.month12, a.sum, a.isdel,a.parentorg ");
		hql.append("FROM data_taxTask_detail_taxPlan a WHERE a.year = '"+dtt.getYear()+"' and a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
		hql.append("WHERE vcParentID IN ( SELECT vcOrganID FROM hh_organInfo_tree_relation WHERE nNodeID = '"+dtt.getOrg()+"' ) AND a.YEAR = '"+dtt.getYear()+"' )");
		hql.append("union SELECT a.id ,a.pid, a.YEAR, a.org, a.orgName, a.month1, a.month2, a.month3, a.month4, a.month5, a.month6,");
		hql.append("a.month7, a.month8, a.month9, a.month10, a.month11, a.month12, a.sum, a.isdel,a.parentorg ");
		hql.append("FROM data_taxTask_detail_taxPlan a WHERE a.pid = "+id+" ");

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailTaxPlan> result = new ArrayList<DataTaxTaskDetailTaxPlan>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailTaxPlan entity1 = new DataTaxTaskDetailTaxPlan();
			entity1.setId((Integer) (item[0]==null?"":(Integer)item[0]));
			entity1.setDataTaxTask(dtt);	
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(item[3]==null?"":item[3].toString());
			entity1.setOrgName(item[4]==null?"":item[4].toString());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(item[19]==null?"":item[19].toString());
			result.add(entity1);		
			}	
		return result;
	}

	@Override
	public List<DataTaxTaskDetailInTaxReturn> getDetailInt(
			DataTaxTask dtt, Integer id) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id ,a.pid, a.YEAR, a.org, a.orgName, a.month1, a.month2, a.month3, a.month4, a.month5, a.month6,");
		hql.append("a.month7, a.month8, a.month9, a.month10, a.month11, a.month12, a.sum, a.isdel,a.parentorg ");
		hql.append("FROM data_taxTask_detail_inTaxReturn a WHERE a.year = '"+dtt.getYear()+"' and a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
		hql.append("WHERE vcParentID IN ( SELECT vcOrganID FROM hh_organInfo_tree_relation WHERE nNodeID = '"+dtt.getOrg()+"' ) AND a.YEAR = '"+dtt.getYear()+"' )");
		hql.append("union SELECT a.id ,a.pid, a.YEAR, a.org, a.orgName, a.month1, a.month2, a.month3, a.month4, a.month5, a.month6,");
		hql.append("a.month7, a.month8, a.month9, a.month10, a.month11, a.month12, a.sum, a.isdel,a.parentorg ");
		hql.append("FROM data_taxTask_detail_inTaxReturn a WHERE a.pid = "+id+" ");

		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxTaskDetailInTaxReturn> result = new ArrayList<DataTaxTaskDetailInTaxReturn>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxTaskDetailInTaxReturn entity1 = new DataTaxTaskDetailInTaxReturn();
			entity1.setId((Integer) (item[0]==null?"":(Integer)item[0]));
			entity1.setDataTaxTask(dtt);	
			entity1.setYear((Integer) (item[2]==null?"":(Integer)item[2]));	
			entity1.setOrg(item[3]==null?"":item[3].toString());
			entity1.setOrgName(item[4]==null?"":item[4].toString());
			entity1.setMonth1(item[5]==null?"":item[5].toString());
			entity1.setMonth2(item[6]==null?"":item[6].toString());
			entity1.setMonth3(item[7]==null?"":item[7].toString());
			entity1.setMonth4(item[8]==null?"":item[8].toString());
			entity1.setMonth5(item[9]==null?"":item[9].toString());
			entity1.setMonth6(item[10]==null?"":item[10].toString());
			entity1.setMonth7(item[11]==null?"":item[11].toString());
			entity1.setMonth8(item[12]==null?"":item[12].toString());
			entity1.setMonth9(item[13]==null?"":item[13].toString());
			entity1.setMonth10(item[14]==null?"":item[14].toString());
			entity1.setMonth11(item[15]==null?"":item[15].toString());
			entity1.setMonth12(item[16]==null?"":item[16].toString());
			entity1.setSum(item[17]==null?"":item[17].toString());
			entity1.setIsdel((Integer) (item[18]==null? 0:(Integer)item[18]));
			entity1.setParentorg(item[19]==null?"":item[19].toString());
			result.add(entity1);		
			}	
		return result;
	}

	/**
	 * 判断虚拟数据存不存在
	 */
	@Override
	public List<DataTaxTask> getTaxTaskId(DataTaxTask entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select a.id  FROM data_taxTask a  where  a.org='"+entity.getOrg()+"'");
		if(entity.getYear()!=null)
		{
			hql.append(" and a.year = '"+entity.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	    
		return query.list();
	}

	@Override
	public String getParentOrg(String org) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select vcOrganID  FROM hh_organInfo_tree_relation  where  nNodeID='"+org+"'");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		String  Parentorg =query.uniqueResult().toString();
		return Parentorg;
	}

	/**
	 * 判断是否为虚拟公司
	 */
	@Override
	public boolean isvirtual(DataTaxTask entity) {
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
	public List<DataTaxTask> getSumDataInfo(DataTaxTask beanIn) {
		if(null == beanIn)
			return new ArrayList<DataTaxTask>();
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT sum( taxTask ) AS taxTask,sum( taxPlan ) AS taxPlan, sum( favouredPolicy ) AS favouredPolicy,");
        hql.append("sum( a.taxReturn ) AS taxReturn,sum( a.inTaxReturn ) AS inTaxReturn  ");
        hql.append(" FROM data_taxTask a WHERE a.year = '"+beanIn.getYear()+"' and a.STATUS= 50115 ");
        if (Common.notEmpty(beanIn.getOrg())) {
			hql.append(" and a.org in( "+ Common.dealinStr(beanIn.getOrg()) + ") ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());		
		return query.list();
	}


	@Override
	public List<DataTaxTask> getSumDataList(DataTaxTask dtt) {
		if (!Common.notEmpty(dtt.getOrg())) {
			return null;
		}
		StringBuilder  hql = new StringBuilder();
        hql.append("SELECT a.orgName,a.taxTask,a.taxPlan,a.favouredPolicy,");
        hql.append("a.taxReturn,a.inTaxReturn ");
        hql.append(" FROM data_taxTask a  WHERE a.year = '"+dtt.getYear()+"' and a.STATUS= 50115");
        if (Common.notEmpty(dtt.getOrg())) {
			hql.append(" and a.org in( "
					+ Common.dealinStr(dtt.getOrg()) + ") ");
		}
        hql.append(" group by a.org");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}

}
