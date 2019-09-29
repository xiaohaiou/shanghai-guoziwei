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
import com.softline.dao.report.ITaxSaveDao;
import com.softline.entity.DataTaxSave;
import com.softline.entity.DataTaxSaveDetail;
import com.softline.entity.taxTask.DataTaxPay;

/**
 * 
 * @author zy
 *
 */
@Repository(value = "taxSaveDao")
public class TaxSaveDao implements ITaxSaveDao {
	
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
	@Override
	public DataTaxSave getTaxSave(Integer id) {
		if (id == null)
			return new DataTaxSave();
		StringBuilder hql = new StringBuilder();
		hql.append(" from DataTaxSave s where 1=1 and isdel=0 and id=" + id);
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (DataTaxSave) query.uniqueResult();
	}

	@Override
	public String getAccTaxSave(DataTaxSave dataTaxSave){
		
		if(null == dataTaxSave ||
				null == dataTaxSave.getYear() ||
				!dataTaxSave.getYear().contains("-"))
			return null;
		
		String hql = " select sum(s.taxSave) as accTaxSave from DataTaxSave s where 1=1 and isdel=0 and s.org='"+
					 dataTaxSave.getOrg()+"' and SUBSTRING_INDEX(s.year,'-',1) = '"+dataTaxSave.getYear().split("-")[0]+"' and s.month <='"+dataTaxSave.getMonth()+"'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Object backObj = query.uniqueResult();
		if(null == backObj)
			return  "0";
		return String.valueOf(query.uniqueResult().toString());
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
	@Override
	public List<DataTaxSave> getTaxSaveList(DataTaxSave entity,
			Integer offsize, Integer pagesize, Integer fun) {
		if (entity == null)
			return new ArrayList<DataTaxSave>();
		StringBuilder hql = new StringBuilder();
		hql.append(" from DataTaxSave s where 1=1 and isdel=0 ");
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
		hql.append(" order by year desc,month desc");
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
	@Override
	public int getTaxSaveListCount(DataTaxSave entity, Integer fun) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataTaxSave s where 1=1 and isdel=0 ");
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
	@Override
	public boolean saveTaxSaveRepeatCheck(DataTaxSave entity) {

		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataTaxSave s where 1=1 and isdel=0  ");
		if (entity != null) {
			if (Common.notEmpty(entity.getOrg())) {
				hql.append(" and s.org = '" + entity.getOrg() + "' ");
			}
			if (Common.notEmpty(entity.getYear())) {
				hql.append(" and s.year = '"+ entity.getYear()+"' ");
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
	@Override
	public boolean checkCanEdit(DataTaxSave entity) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataTaxSave s where 1=1 and isdel=0 and status in("
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
	

	public int getSaveDetailId(DataTaxSave entity){
		StringBuilder  hql = new StringBuilder();
		hql.append("select a.id  FROM data_taxSave_detail a  where  a.org='"+entity.getOrg()+"'");
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
	 * 虚拟公司数据存在的情况
	 */
	@Override
	public List<DataTaxSave> getHrFormsListCollectView(DataTaxSave dts) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id, a.YEAR,a.MONTH,a.org,a.orgName,t.inTaxReturn,t.taxReturn,t.taxPlan,");
		hql.append("t.favouredPolicy,t.taxSave,a.accTaxSave,a.remark,a.STATUS,a.isdel,a.reportPersonID,");
		hql.append("a.reportPersonName,a.reportDate,a.auditorPersonID,a.auditorPersonName,a.auditorDate,");
		hql.append("a.createPersonID,a.createPersonName,a.createDate,a.lastModifyPersonID,a.lastModifyPersonName,");
		hql.append("a.lastModifyDate,a.parentorg ");
		hql.append("FROM data_taxSave a,hh_organInfo_tree_relation b,( SELECT sum( inTaxReturn ) AS inTaxReturn,");
		hql.append("sum( taxReturn ) AS taxReturn,sum( taxPlan ) AS taxPlan,sum( favouredPolicy ) AS favouredPolicy,");
		hql.append("sum( a.taxSave ) AS taxSave FROM data_taxSave a WHERE a.year = '"+dts.getYear()+"' and a.STATUS= 50115  AND  a.org IN ( SELECT nNodeID FROM ");
		hql.append("hh_organInfo_tree_relation WHERE vcParentID  ");
		hql.append("like '%"+dts.getParentorg()+"%' AND STATUS in (50500,50502) ) ) t ");
		hql.append("WHERE a.org = b.nNodeID  AND b.STATUS = 50501 AND b.nNodeID = '"+dts.getOrg()+"'");


		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());

		
		return query.list();
	}
	
	/**
	 * 汇总
	 * 虚拟公司数据不存在的情况
	 */
	@Override
	public List<DataTaxSave> getHrFormsListCollectView2(DataTaxSave dts) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT sum( inTaxReturn ) AS inTaxReturn,sum( taxReturn ) AS taxReturn,sum( taxPlan ) AS taxPlan,");
		hql.append("sum( favouredPolicy ) AS favouredPolicy,sum( a.taxSave ) AS taxSave,b.vcOrganID");
		hql.append(" FROM data_taxSave a ,hh_organInfo_tree_relation b WHERE a.year = '"+dts.getYear()+"' and a.STATUS= 50115   AND b.nNodeID='"+dts.getOrg()+"' and a.org IN ( SELECT nNodeID FROM ");
		hql.append("hh_organInfo_tree_relation WHERE vcParentID  ");
		hql.append(" like '%"+dts.getParentorg()+"%' and status in (50500,50502) )  ");	
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());		
		return query.list();
	}
	
	
	
	/**
	 * 汇总数据新增获取
	 * 虚拟公司数据存在
	 */
	public List<DataTaxSaveDetail> getnewListCollect(DataTaxSave dts) {
		// TODO Auto-generated method stub
		int newId = getSaveDetailId(dts);
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id, a.pid, a.YEAR,a.MONTH, a.org,a.orgName,sum( a.inTaxReturn ) AS inTaxReturn,sum( a.taxReturn ) AS taxReturn,");
		hql.append("sum( a.taxPlan ) AS taxPlan,sum( a.favouredPolicy ) AS favouredPolicy,sum( a.taxSave ) AS taxSave,a.accTaxSave,a.isdel,a.parentorg ");
		hql.append("FROM data_taxSave_detail a WHERE  a.year =  '"+dts.getYear()+"' AND a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
		hql.append("WHERE vcParentID like '%"+dts.getParentorg()+"%' and status in (50500,50502) )");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxSaveDetail> result = new ArrayList<DataTaxSaveDetail>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxSaveDetail entity1 = new DataTaxSaveDetail();
			entity1.setId(newId);
			entity1.setDataTaxSave(dts);
			entity1.setPid((Integer) (item[1]==null?"":(Integer)item[1]));
			entity1.setYear(item[2]==null?"":item[2].toString());
			entity1.setMonth((item[3]==null?"":item[3].toString()));		
			entity1.setOrg(dts.getOrg());
			entity1.setOrgName(dts.getOrgName());
			entity1.setInTaxReturn(item[6]==null?"":item[6].toString());
			entity1.setTaxReturn(item[7]==null?"":item[7].toString());
			entity1.setTaxPlan(item[8]==null?"":item[8].toString());
			entity1.setFavouredPolicy(item[9]==null?"":item[9].toString());
			entity1.setTaxSave(item[10]==null?"":item[10].toString());			
			entity1.setAccTaxSave(item[11]==null?"":item[11].toString());
			entity1.setIsdel((Integer) (item[12]==null? 0:(Integer)item[12]));
			entity1.setParentorg(dts.getParentorg());
			result.add(entity1);
			
			}
		
		return result;
	}
	
	/**
	 * 虚拟公司数据不存在
	 */
	
	@Override
	public List<DataTaxSaveDetail> getnewListCollect2(DataTaxSave dts) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id, a.pid, a.YEAR,a.MONTH, a.org,a.orgName,sum( a.inTaxReturn ) AS inTaxReturn,sum( a.taxReturn ) AS taxReturn,");
		hql.append("sum( a.taxPlan ) AS taxPlan,sum( a.favouredPolicy ) AS favouredPolicy,sum( a.taxSave ) AS taxSave,a.accTaxSave,a.isdel,a.parentorg ");
		hql.append("FROM data_taxSave_detail a WHERE  a.year = '"+dts.getYear()+"'  AND a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
		hql.append("WHERE vcParentID like '%"+dts.getParentorg()+"%' and status in (50500,50502) )");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxSaveDetail> result = new ArrayList<DataTaxSaveDetail>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxSaveDetail entity1 = new DataTaxSaveDetail();
			entity1.setDataTaxSave(dts);
			entity1.setPid((Integer) (item[1]==null?"":(Integer)item[1]));
			entity1.setYear(item[2]==null?"":item[2].toString());
			entity1.setMonth((item[3]==null?"":item[3].toString()));		
			entity1.setOrg(dts.getOrg());
			entity1.setOrgName(dts.getOrgName());
			entity1.setInTaxReturn(item[6]==null?"":item[6].toString());
			entity1.setTaxReturn(item[7]==null?"":item[7].toString());
			entity1.setTaxPlan(item[8]==null?"":item[8].toString());
			entity1.setFavouredPolicy(item[9]==null?"":item[9].toString());
			entity1.setTaxSave(item[10]==null?"":item[10].toString());			
			entity1.setAccTaxSave(item[11]==null?"":item[11].toString());
			entity1.setIsdel((Integer) (item[12]==null? 0:(Integer)item[12]));
			entity1.setParentorg(dts.getParentorg());
			result.add(entity1);
			
			}
		
		return result;
	}
	
	/**
	 * 验证是否为虚拟公司
	 */
	public boolean isvirtual(DataTaxSave entity) {
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
	public int getStatus(Integer id) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select b.status  FROM data_taxSave a ,hh_organInfo_tree_relation b where  a.id="+id+" and a.org=b.nNodeID");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer status = Integer.parseInt(query.uniqueResult().toString());
		return status;
	}

	@Override
	public List<DataTaxSaveDetail>  getDetail(DataTaxSave dts ,Integer id) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT a.id,a.pid,a.YEAR,a.MONTH,a.org,a.orgName,a.inTaxReturn,a.taxReturn,");
		hql.append("a.taxPlan,a.favouredPolicy,a.taxSave,a.accTaxSave,a.isdel,a.parentorg ");
		hql.append("FROM data_taxSave_detail a WHERE a.year = '"+dts.getYear()+"' AND a.org IN (SELECT nNodeID FROM hh_organInfo_tree_relation ");
		hql.append("WHERE vcParentID IN ( SELECT vcOrganID FROM hh_organInfo_tree_relation WHERE nNodeID = '"+dts.getOrg()+"' ) AND a.YEAR = '"+dts.getYear()+"') ");
		hql.append("UNION SELECT a.id,a.pid,a.YEAR,a.MONTH,a.org,a.orgName,a.inTaxReturn,a.taxReturn,");
		hql.append("a.taxPlan,a.favouredPolicy,a.taxSave,a.accTaxSave,a.isdel,a.parentorg ");
		hql.append("FROM data_taxSave_detail a WHERE a.pid = "+id+" ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<DataTaxSaveDetail> result = new ArrayList<DataTaxSaveDetail>();
		for(Object obj : a) {
			Object[] item = (Object[])obj;
			DataTaxSaveDetail entity1 = new DataTaxSaveDetail();
			entity1.setId((Integer) (item[0]==null?"":(Integer)item[0]));
			entity1.setPid((Integer) (item[1]==null?"":(Integer)item[1]));
			entity1.setYear(item[2]==null?"":item[2].toString());
			entity1.setMonth((item[3]==null?"":item[3].toString()));		
			entity1.setOrg(item[4]==null?"":item[4].toString());
			entity1.setOrgName(item[5]==null?"":item[5].toString());
			entity1.setInTaxReturn(item[6]==null?"":item[6].toString());
			entity1.setTaxReturn(item[7]==null?"":item[7].toString());
			entity1.setTaxPlan(item[8]==null?"":item[8].toString());
			entity1.setFavouredPolicy(item[9]==null?"":item[9].toString());
			entity1.setTaxSave(item[10]==null?"":item[10].toString());			
			entity1.setAccTaxSave(item[11]==null?"":item[11].toString());
			entity1.setIsdel((Integer) (item[12]==null? 0:(Integer)item[12]));
			entity1.setParentorg(item[13]==null?"":item[13].toString());
			result.add(entity1);
			
			}
		
		return  result;
	}

	@Override
	public List<DataTaxSave> getTaxSaveId(DataTaxSave entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select a.id  FROM data_taxSave a  where  a.org='"+entity.getOrg()+"'");
		if(Common.notEmpty(entity.getYear()))
		{
			hql.append(" and a.year = '"+entity.getYear()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	    
		return query.list();
	}

	/**
	 * 汇总
	 * 虚拟公司数据不存在
	 */
	@Override
	public List<DataTaxSave> getDataTaxSaveSonBeanSumData(DataTaxSave beanIn) {
		
		if(null == beanIn)
		return new ArrayList<DataTaxSave>();

		/**
		 * taxSave
		 * inTaxReturn
		 * taxReturn
		 * taxPlan
		 * favouredPolicy      
		 */
		StringBuilder  hql = new StringBuilder();
	    hql.append(" select sum( a.taxSave ) AS taxSave, sum( a.inTaxReturn ) AS inTaxReturn,sum(a.taxReturn) as taxReturn,sum(a.taxPlan) as taxPlan,sum(favouredPolicy) as favouredPolicy");
	    hql.append(" FROM data_taxSave a where 1=1 ");
	    hql.append(" and a.STATUS= 50115 AND a.isdel = 0");
	    if (Common.notEmpty(beanIn.getOrg())) {
			hql.append(" and a.org in( "
					+ Common.dealinStr(beanIn.getOrg()) + ") ");
		}
	    if(Common.notEmpty(beanIn.getStarttime()))
		{
			hql.append(" and a.year >= '"+beanIn.getStarttime()+"' ");
		}
		if(Common.notEmpty(beanIn.getEndtime()))
		{
			hql.append(" and a.year <= '"+beanIn.getEndtime()+"' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}

	public boolean IsExitsTaxTask(DataTaxSave entity){
		
		if(entity==null ||
				!Common.notEmpty(entity.getYear()) ||
				!Common.notEmpty(entity.getOrg()))
			return false;
		
		String year = "";
		if(entity.getYear().length()>=4)
			year = entity.getYear().substring(0, 4);
		
		String sql = "select count(*) from data_taxTask s where s.year = '"+year+"' and s.org = '"+entity.getOrg()+"'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		
		Object obj = query.list().get(0);
		
		if(Common.isNumber(String.valueOf(obj)))
			return Integer.valueOf(String.valueOf(obj))>0;
		
		return false;
	}

	@Override
	public List<DataTaxSave> getSumDataList(DataTaxSave beanIn) {
		 if (!Common.notEmpty(beanIn.getOrg())) {
				return null;
			}
		StringBuilder  hql = new StringBuilder();
	    hql.append(" select a.orgName,sum(a.taxSave),sum(a.inTaxReturn),sum(a.taxReturn),sum(a.taxPlan),sum(favouredPolicy)");
	    hql.append(" FROM data_taxSave a  where 1=1 ");
	    hql.append(" and a.STATUS= 50115 AND a.isdel = 0");
	    if (Common.notEmpty(beanIn.getOrg())) {
			hql.append(" and a.org in( "
					+ Common.dealinStr(beanIn.getOrg()) + ") ");
		}
	    if(Common.notEmpty(beanIn.getStarttime()))
		{
			hql.append(" and a.year >= '"+beanIn.getStarttime()+"' ");
		}
		if(Common.notEmpty(beanIn.getEndtime()))
		{
			hql.append(" and a.year <= '"+beanIn.getEndtime()+"' ");
		}
		hql.append(" group by a.org");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}
}
