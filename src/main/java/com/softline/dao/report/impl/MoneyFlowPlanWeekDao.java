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

import com.softline.common.Common;
import com.softline.dao.report.IMoneyFlowPlanWeekDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.ReportCashflowWeeklyExecute;
import com.softline.entity.moneyFlow.DataMoneyFlowMonth;
import com.softline.entity.moneyFlow.DataMoneyFlowWeek;

@Repository(value = "moneyFlowPlanWeekDao")
/**
 * 
 * @author ky_tian
 *
 */
public class MoneyFlowPlanWeekDao implements IMoneyFlowPlanWeekDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Integer getHrFormsListViewCount(DataMoneyFlowWeek entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_money_flow_week a left join hh_organInfo_tree_relation hort on hort.nNodeID = a.org where a.isdel=0 ");
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
					hql.append(" and a.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
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
		clearNull();
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	public List<DataMoneyFlowWeek> getHrFormsListView(DataMoneyFlowWeek entity, Integer offset,Integer pageSize) {
		if(entity==null)
			return new ArrayList<DataMoneyFlowWeek>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from DataMoneyFlowWeek d where 1=1 and isdel=0 ");
		
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
	
	public DataMoneyFlowWeek get(Integer id) {
		String hql = " from DataMoneyFlowWeek a where a.isdel=0 and a.pid ="+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (DataMoneyFlowWeek) query.uniqueResult();
	}
	
	public boolean get(DataMoneyFlowWeek entity) {
		boolean b=false;
		String hql = " from DataMoneyFlowWeek a where a.isdel=0 and a.pid ="+entity.getPid();
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
	public boolean saveMoneyFlowPlanWeekCheck(DataMoneyFlowWeek entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataMoneyFlowWeek a where a.isdel=0  ");
		if(entity!=null)
		{
			if(entity.getDate()!=null&&!"".equals(entity.getDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m') =  str_to_date('"+entity.getDate()+"','%Y-%m') ");
			}
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and a.org = '"+entity.getOrg()+ "' ");
			}
			if(entity.getPid()!=null)
			{
				hql.append(" and a.pid !='"+entity.getPid()+"' ");
			}
			if(entity.getWeek()!=null){
				hql.append(" and a.week = '"+entity.getWeek()+"'");
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
	public boolean moneyFlowPlanWeekCheck(DataMoneyFlowWeek entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataMoneyFlowWeek a where a.isdel=0 and a.status in ('50113','50115','50112','50114') ");
		if(entity!=null)
		{
			if(entity.getDate()!=null&&!"".equals(entity.getDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m') =  str_to_date('"+entity.getDate()+"','%Y-%m') ");
			}
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and a.org = '"+entity.getOrg()+ "' ");
			}
			if(entity.getPid()!=null)
			{
				hql.append(" and a.pid !='"+entity.getPid()+"' ");
			}
			if(entity.getWeek()!=null){
				hql.append(" and a.week = '"+entity.getWeek()+"'");
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
	public Integer getExamineHrFormsListViewCount(DataMoneyFlowWeek entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_money_flow_week a left join hh_organInfo_tree_relation hort on hort.nNodeID = a.org where a.isdel=0 and a.status!=50112 ");
		if(entity!=null)
		{
			if(entity.getDate()!=null&&!"".equals(entity.getDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m') =  str_to_date('"+entity.getDate()+"','%Y-%m') ");
			}
			if(Common.notEmpty(entity.getOrg()))
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and a.org = '"+entity.getOrg()+ "' ");
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
	public java.util.List<DataMoneyFlowWeek> getExamineHrFormsListView(
			DataMoneyFlowWeek entity, Integer offset, Integer pageSize) {
		if(entity==null)
			return new ArrayList<DataMoneyFlowWeek>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from DataMoneyFlowWeek d where 1=1 and isdel=0  and d.status!=50112");
		
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

	@Override
	public LinkedHashMap<String,List<LinkedHashMap<String, Object>>> getMonthPlan(String org,String weekStart,String weekEnd) {
		LinkedHashMap<String,List<LinkedHashMap<String, Object>>> MonthPlanData=new LinkedHashMap<String,List<LinkedHashMap<String, Object>>>();
		if(org!=null&&
				weekStart!=null&&
				weekEnd!=null &&
				Common.notEmpty(weekStart)&&
				Common.notEmpty(weekEnd)){
			sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_week_ci where pid is null").executeUpdate();
			String ciSql = "select ci.* from data_money_flow_week_ci ci "+innerJoin("ci")+"  str_to_date(ci.place_order_date,'%Y-%m-%d') between str_to_date('"+weekStart+"','%Y-%m-%d') and str_to_date('"+weekEnd+"','%Y-%m-%d') and ci.organal_id='"+org+"'";
			Query query1 = sessionFactory.getCurrentSession().createSQLQuery(ciSql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<LinkedHashMap<String, Object>> ciList=query1.list();
			MonthPlanData.put("ci",ciList);
			
			sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_week_co where pid is null").executeUpdate();
			String coSql = "select co.* from data_money_flow_week_co co "+innerJoin("co")+" str_to_date(co.date_of_payment,'%Y-%m-%d') between str_to_date('"+weekStart+"','%Y-%m-%d') and str_to_date('"+weekEnd+"','%Y-%m-%d') and co.organal_id='"+org+"'";
			Query query2 = sessionFactory.getCurrentSession().createSQLQuery(coSql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<LinkedHashMap<String, Object>> coList=query2.list();
			MonthPlanData.put("co",coList);
			
			sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_week_io where pid is null").executeUpdate();
			String ioSql = "select io.* from data_money_flow_week_io io "+innerJoin("io")+" str_to_date(io.date_of_investment,'%Y-%m-%d') between str_to_date('"+weekStart+"','%Y-%m-%d') and str_to_date('"+weekEnd+"','%Y-%m-%d') and io.organal_id='"+org+"'";
			Query query3 = sessionFactory.getCurrentSession().createSQLQuery(ioSql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<LinkedHashMap<String, Object>> ioList=query3.list();
			MonthPlanData.put("io",ioList);
		}
		return MonthPlanData;
	}
	
	
	@Override
	public LinkedHashMap<String,List<LinkedHashMap<String, Object>>> getMonthPlanForDetialInfo(Integer pid) {
		LinkedHashMap<String,List<LinkedHashMap<String, Object>>> MonthPlanData=new LinkedHashMap<String,List<LinkedHashMap<String, Object>>>();
		if(pid != null){
			sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_week_ci where pid is null").executeUpdate();
			String ciSql = "select ci.* from data_money_flow_week_ci ci "+" where ci.pid="+pid+" and ci.isdel = 0 and ci.loan_amount >= 1000 ";
			Query query1 = sessionFactory.getCurrentSession().createSQLQuery(ciSql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<LinkedHashMap<String, Object>> ciList=query1.list();
			MonthPlanData.put("ci",ciList);
			
			sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_week_co where pid is null").executeUpdate();
			String coSql = "select co.* from data_money_flow_week_co co where co.pid="+pid+" and co.isdel=0 and co.repayment_amount >= 1000 ";
			Query query2 = sessionFactory.getCurrentSession().createSQLQuery(coSql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<LinkedHashMap<String, Object>> coList=query2.list();
			MonthPlanData.put("co",coList);
			
			sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_week_io where pid is null").executeUpdate();
			String ioSql = "select io.* from data_money_flow_week_io io where io.pid="+pid+" and io.isdel=0 and io.investment_amount >=1000 ";
			Query query3 = sessionFactory.getCurrentSession().createSQLQuery(ioSql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<LinkedHashMap<String, Object>> ioList=query3.list();
			MonthPlanData.put("io",ioList);
		}
		return MonthPlanData;
	}
	
	
	public String innerJoin(String b){
		String sql=" INNER JOIN data_money_flow_week a on a.pid = "+b+".pid where a.status='50115' and ";
		return sql;
	}
	@Override
	public HhOrganInfoTreeRelation getCoreCompId(String organalID, int type) {
		String hql = "from HhOrganInfoTreeRelation where id.nnodeId = '" + organalID + "' and id.type="+type+" and cflag = 1 ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhOrganInfoTreeRelation)query.uniqueResult();
	}
	
	public void clearNull(){
		sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_week_ci where pid is null").executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_week_co where pid is null").executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery("delete from data_money_flow_week_io where pid is null").executeUpdate();
	}
	
	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	@Override
	public List<DataMoneyFlowWeek> getDataMoneyFlowWeekForSumData(DataMoneyFlowWeek entity ,Integer offsize,Integer pagesize){
		
		if(entity==null || null==entity.getOrg())
			return new ArrayList<DataMoneyFlowWeek>();
		
		StringBuilder  hql = new StringBuilder();

		hql.append(" select r from DataMoneyFlowWeek r, HhOrganInfoTreeRelation h where 1=1 and r.org = h.id.nnodeId and r.isdel = 0 "); 
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
		hql.append(" and h.vcParentId in (select ttt.vcOrganId from HhOrganInfoTreeRelation ttt where ttt.id.nnodeId = '"+entity.getOrg()+"')");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();		
	}
	
	/**
	 * 汇总给出虚拟公司下子公司的所有信息
	 * @param info
	 * @return  
	 */
	@Override
	public List<DataMoneyFlowWeek> getSumChildrenData(HhOrganInfoTreeRelation info,DataMoneyFlowWeek entityIn){
		
		if(null==info || null==entityIn)
			return new ArrayList<DataMoneyFlowWeek>();
		
		StringBuilder hql = new StringBuilder();

		hql.append(" select r from DataMoneyFlowWeek r,HhOrganInfoTreeRelation h where h.id.nnodeId=r.org and 1=1 and r.isdel = 0 and h.status in (50500,50502) and r.status = 50115");
		
		if(null != entityIn.getDate())
			hql.append(" and r.date = ").append(Common.dealinStr(entityIn.getDate()));
		
		if(null != entityIn.getWeek())
			hql.append(" and r.week = ").append(Common.dealinStr(entityIn.getWeek()));
		
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
	public List<DataMoneyFlowWeek> findPageList(DataMoneyFlowWeek entity) {
		if(entity==null)
			return new ArrayList<DataMoneyFlowWeek>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from DataMoneyFlowWeek d where 1=1 and isdel=0 ");
		
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
