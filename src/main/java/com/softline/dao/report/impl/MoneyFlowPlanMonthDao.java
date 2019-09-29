package com.softline.dao.report.impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.report.IMoneyFlowPlanMonthDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.moneyFlow.DataMoneyFlowMonth;
import com.softline.entity.moneyFlow.DataMoneyFlowWeek;

@Repository(value = "moneyFlowPlanMonthDao")
/**
 * 
 * @author ky_tian
 *
 */
public class MoneyFlowPlanMonthDao implements IMoneyFlowPlanMonthDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Integer getHrFormsListViewCount(DataMoneyFlowMonth entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_money_flow_month a left join hh_organInfo_tree_relation hort on hort.nNodeID = a.org where a.isdel=0 ");
		if(entity!=null)
		{
			if(entity.getStartDate()!=null&&!"".equals(entity.getStartDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m') >= str_to_date('"+entity.getStartDate()+"','%Y-%m') ");
			}
			if(entity.getEndDate()!=null&&!"".equals(entity.getEndDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m') <= str_to_date('"+entity.getEndDate()+"','%Y-%m') ");
			}
			if(Common.notEmpty(entity.getOrg()))
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and a.org = '"+entity.getOrg()+"' ");
				}else{
					hql.append(" and a.org in ( select nNodeID from hh_organInfo_tree_relation where locate((select vcOrganID from hh_organInfo_tree_relation where nNodeID ='"+
												entity.getOrg()+"'),vcOrganID)>0)");
				}
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and a.status = '"+entity.getStatus()+ "' ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and hort.vcOrganID like '%--%' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		deleteNull();
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	public List<DataMoneyFlowMonth> getHrFormsListView(DataMoneyFlowMonth entity, Integer offset,Integer pageSize) {
		if(entity==null)
			return new ArrayList<DataMoneyFlowMonth>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from DataMoneyFlowMonth d where 1=1 and isdel=0 ");
		
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()))
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and d.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
				}else{
					hql.append(" and d.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
							entity.getOrg()+"'),h.vcOrganId)>0)");
				}
			}
			if(entity.getStartDate()!=null&&!"".equals(entity.getStartDate()))
			{
				hql.append(" and  str_to_date(d.date,'%Y-%m') >= str_to_date('"+entity.getStartDate()+"','%Y-%m') ");
			}
			if(entity.getEndDate()!=null&&!"".equals(entity.getEndDate()))
			{
				hql.append(" and  str_to_date(d.date,'%Y-%m') <= str_to_date('"+entity.getEndDate()+"','%Y-%m') ");
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and d.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" d.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" d.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and d.authOrg like '%--%' ");
			}
		}
		hql.append(" order by date desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}
	
	public DataMoneyFlowMonth get(Integer id) {
		String hql = " from DataMoneyFlowMonth a where a.isdel=0 and a.pid ="+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (DataMoneyFlowMonth) query.uniqueResult();
	}
	
	public boolean get(DataMoneyFlowMonth entity) {
		boolean b=false;
		String hql = " from DataMoneyFlowMonth a where a.isdel=0 and a.pid ="+entity.getPid();
		if(entity.getStatus()!=null&&(entity.getStatus()==50112||entity.getStatus()==50114)){
			hql+=" and a.status="+entity.getStatus();
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(query.uniqueResult()!=null){
			b=true;
		}else{}
		return b;
	}
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveMoneyFlowPlanMonthCheck(DataMoneyFlowMonth entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataMoneyFlowMonth a where a.isdel=0  ");
		if(entity!=null)
		{
			if(entity.getDate()!=null&&!"".equals(entity.getDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m') =  str_to_date('"+entity.getDate()+"','%Y-%m') ");
			}
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and a.org = '"+entity.getOrg()+"' ");
			}
			if(entity.getPid()!=null)
			{
				hql.append(" and a.pid !='"+entity.getPid()+"' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return true;
		else
			return false;
	}

	/**
	 * 保存上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean moneyFlowPlanMonthCheck(DataMoneyFlowMonth entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataMoneyFlowMonth a where a.isdel=0 and a.status in ('50113','50115','50112','50114') ");
		if(entity!=null)
		{
			if(entity.getDate()!=null&&!"".equals(entity.getDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m') =  str_to_date('"+entity.getDate()+"','%Y-%m') ");
			}
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and a.org = '"+entity.getOrg()+"' ");
			}
			if(entity.getPid()!=null)
			{
				hql.append(" and a.pid !='"+entity.getPid()+"' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return true;
		else
			return false;
	}

	@Override
	public Integer getExamineHrFormsListViewCount(DataMoneyFlowMonth entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_money_flow_month a  left join hh_organInfo_tree_relation hort on hort.nNodeID = a.org where a.isdel=0 and a.status!=50112 ");
		if(entity!=null)
		{
			if(entity.getStartDate()!=null&&!"".equals(entity.getStartDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m') >=  str_to_date('"+entity.getStartDate()+"','%Y-%m') ");
			}
			if(entity.getEndDate()!=null&&!"".equals(entity.getEndDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m') <=  str_to_date('"+entity.getEndDate()+"','%Y-%m') ");
			}
			if(Common.notEmpty(entity.getOrg()))
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and a.org = '"+entity.getOrg()+"' ");
				}else{
					hql.append(" and a.org in ( select nNodeID from hh_organInfo_tree_relation where locate((select vcOrganID from hh_organInfo_tree_relation where nNodeID ='"+
												entity.getOrg()+"'),vcOrganID)>0)");
				}
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and a.status = '"+entity.getStatus()+ "' ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and hort.vcOrganID like '%--%' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public java.util.List<DataMoneyFlowMonth> getExamineHrFormsListView(
			DataMoneyFlowMonth entity, Integer offset, Integer pageSize) {
		if(entity==null)
			return new ArrayList<DataMoneyFlowMonth>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from DataMoneyFlowMonth d where 1=1 and isdel=0  and d.status!=50112");
		
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()))
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and d.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
				}else{
					hql.append(" and d.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
							entity.getOrg()+"'),h.vcOrganId)>0)");
				}
			}
			if(entity.getStartDate()!=null&&!"".equals(entity.getStartDate()))
			{
				hql.append(" and str_to_date(d.date,'%Y-%m') >=  str_to_date('"+entity.getStartDate()+"','%Y-%m') ");
			}
			if(entity.getEndDate()!=null&&!"".equals(entity.getEndDate()))
			{
				hql.append(" and str_to_date(d.date,'%Y-%m') <=  str_to_date('"+entity.getEndDate()+"','%Y-%m') ");
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and d.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" d.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" d.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and d.authOrg like '%--%' ");
			}
		}
		hql.append(" order by date desc  ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}
	public void deleteNull(){
		sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_month_ci where pid is null").executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_month_co where pid is null").executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_month_io where pid is null").executeUpdate();
	}

	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	@Override
	public List<DataMoneyFlowMonth> getDataMoneyFlowMonthForSumData(DataMoneyFlowMonth entity ,Integer offsize,Integer pagesize){
		
		if(entity==null || 
				null==entity.getOrg())
			return new ArrayList<DataMoneyFlowMonth>();
		
		StringBuilder  hql = new StringBuilder();

		hql.append(" select r from DataMoneyFlowMonth r, HhOrganInfoTreeRelationHistory h where 1=1 and r.org = h.id.nnodeId and r.isdel = 0 ");
		if(entity.getStartDate()!=null&&!"".equals(entity.getStartDate()))
		{
			hql.append(" and  str_to_date(r.date,'%Y-%m') >= str_to_date('"+entity.getStartDate()+"','%Y-%m') ");
		}
		if(entity.getEndDate()!=null&&!"".equals(entity.getEndDate()))
		{
			hql.append(" and  str_to_date(r.date,'%Y-%m') <= str_to_date('"+entity.getEndDate()+"','%Y-%m') ");
		}
		if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
		{
			hql.append(" and r.status = "+entity.getStatus());
		}
		
		if(entity.getDate() !=null && !"".equals(entity.getDate()))
		{
			hql.append(" and r.date = '"+entity.getDate()+"' ");
			hql.append(" and h.id.updatetime='" + entity.getDate() +"'");
		}			

		hql.append(" and h.vcParentId in (select ttt.vcOrganId from HhOrganInfoTreeRelationHistory ttt where ttt.id.nnodeId = '"+entity.getOrg()+"')");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();		
	}
	
	@Override
	public Integer getDataMoneyFlowMonthForSumDataNumber(DataMoneyFlowMonth entity){
		
		if(entity==null || 
				null==entity.getOrg() || 
				null == entity.getDate())
			return 0;
		
		StringBuilder  hql = new StringBuilder();

		hql.append(" select count(r) from DataMoneyFlowMonth r, HhOrganInfoTreeRelationHistory h where 1=1 and r.org = h.id.nnodeId and r.isdel = 0 ");
		if(entity.getStartDate()!=null&&!"".equals(entity.getStartDate()))
		{
			hql.append(" and  str_to_date(r.date,'%Y-%m') >= str_to_date('"+entity.getStartDate()+"','%Y-%m') ");
		}
		if(entity.getEndDate()!=null&&!"".equals(entity.getEndDate()))
		{
			hql.append(" and  str_to_date(r.date,'%Y-%m') <= str_to_date('"+entity.getEndDate()+"','%Y-%m') ");
		}
		if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
		{
			hql.append(" and r.status = "+entity.getStatus());
		}
		
		if(entity.getDate() !=null && !"".equals(entity.getDate()))
		{
			hql.append(" and r.date = '"+entity.getDate()+"' ");
			hql.append(" and h.id.updatetime='" + entity.getDate() +"'");
		}			

		hql.append(" and h.vcParentId in (select ttt.vcOrganId from HhOrganInfoTreeRelationHistory ttt where ttt.id.nnodeId = '"+entity.getOrg()+"')");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());	
	}
	
	/**
	 * 汇总给出虚拟公司下子公司的所有信息
	 * @param info
	 * @return  
	 */
	@Override
	public List<DataMoneyFlowMonth> getSumChildrenData(HhOrganInfoTreeRelationHistory info,DataMoneyFlowMonth entityIn){
		
		if(null==info ||
				null==entityIn ||
				null == entityIn.getDate())
			return new ArrayList<DataMoneyFlowMonth>();

		StringBuilder hql = new StringBuilder();

		hql.append(" select r from DataMoneyFlowMonth r,HhOrganInfoTreeRelationHistory h where 1=1 and h.id.nnodeId=r.org and r.isdel = 0 and h.status in (50500,50502) and r.status = 50115 and h.id.updatetime='" + entityIn.getDate() +"'");
		
		if(null != entityIn.getDate())
			hql.append(" and r.date = ").append(Common.dealinStr(entityIn.getDate()));	
		
		if(Common.notEmpty(entityIn.getStartDate()))
			hql.append(" and r.startDate >= ").append(Common.dealinStr(entityIn.getStartDate()));
		
		if(Common.notEmpty(entityIn.getEndDate()))
			hql.append(" and r.endDate <= ").append(Common.dealinStr(entityIn.getEndDate()));
		
		if(Common.notEmpty(info.getVcOrganId()))
			hql.append(" and r.parentorg like '%").append(info.getVcOrganId()).append("%'");
		else
			hql.append(" and r.parentorg like '%----%'");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return query.list();
		
	}
	
	@Override
	public LinkedHashMap<String,List<LinkedHashMap<String, Object>>> getMonthPlanForDetialInfo(Integer pid) {
		LinkedHashMap<String,List<LinkedHashMap<String, Object>>> MonthPlanData=new LinkedHashMap<String,List<LinkedHashMap<String, Object>>>();
		if(pid != null){
			sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_month_ci where pid is null").executeUpdate();
			String ciSql = "select ci.* from data_money_flow_month_ci ci "+" where ci.pid="+pid+" and ci.isdel = 0 and ci.loan_amount >= 3000 ";
			Query query1 = sessionFactory.getCurrentSession().createSQLQuery(ciSql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<LinkedHashMap<String, Object>> ciList=query1.list();
			MonthPlanData.put("ci",ciList);
			
			sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_month_co where pid is null").executeUpdate();
			String coSql = "select co.* from data_money_flow_month_co co where co.pid="+pid+" and co.isdel=0 and co.repayment_amount >= 3000 ";
			Query query2 = sessionFactory.getCurrentSession().createSQLQuery(coSql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<LinkedHashMap<String, Object>> coList=query2.list();
			MonthPlanData.put("co",coList);
			
			sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_month_io where pid is null").executeUpdate();
			String ioSql = "select io.* from data_money_flow_month_io io where io.pid="+pid+" and io.isdel=0 and io.investment_amount >=3000 ";
			Query query3 = sessionFactory.getCurrentSession().createSQLQuery(ioSql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<LinkedHashMap<String, Object>> ioList=query3.list();
			MonthPlanData.put("io",ioList);
		}
		return MonthPlanData;
	}
	
	@Override
	public HhOrganInfoTreeRelation getCoreCompId(String organalID, int type) {
		String hql = "from HhOrganInfoTreeRelation where id.nnodeId = '" + organalID + "' and id.type="+type+" and cflag = 1 ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhOrganInfoTreeRelation)query.uniqueResult();
	}

	@Override
	public List<DataMoneyFlowMonth> findPageList(DataMoneyFlowMonth entity) {
		if(entity==null)
			return new ArrayList<DataMoneyFlowMonth>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from DataMoneyFlowMonth d where 1=1 and isdel=0 ");
		
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()))
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and d.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
				}else{
					hql.append(" and d.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
							entity.getOrg()+"'),h.vcOrganId)>0)");
				}
			}
			if(entity.getStartDate()!=null&&!"".equals(entity.getStartDate()))
			{
				hql.append(" and  str_to_date(d.date,'%Y-%m') >= str_to_date('"+entity.getStartDate()+"','%Y-%m') ");
			}
			if(entity.getEndDate()!=null&&!"".equals(entity.getEndDate()))
			{
				hql.append(" and  str_to_date(d.date,'%Y-%m') <= str_to_date('"+entity.getEndDate()+"','%Y-%m') ");
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and d.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" d.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" d.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and d.authOrg like '%--%' ");
			}
		}
		hql.append(" order by date desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
}
