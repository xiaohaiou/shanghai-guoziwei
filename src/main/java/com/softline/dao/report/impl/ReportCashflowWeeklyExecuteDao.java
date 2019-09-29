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
import com.softline.dao.report.IReportCashflowWeeklyExecuteDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.ReportCashflowWeeklyExecute;
import com.softline.entity.ReportWeeklyFinancingIntoDetail;
import com.softline.entity.ReportWeeklyFinancingOutDetail;
import com.softline.entity.ReportWeeklyInvestmentOutDetail;

@Repository(value = "reportCashflowWeeklyExecuteDao")
/**
 * 
 * @author tch
 *
 */
public class ReportCashflowWeeklyExecuteDao implements IReportCashflowWeeklyExecuteDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportCashflowWeeklyExecute getReportCashflowWeeklyExecute(Integer id)
	{
		if(id==null)
			return new ReportCashflowWeeklyExecute();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportCashflowWeeklyExecute s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportCashflowWeeklyExecute) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportCashflowWeeklyExecute> getReportCashflowWeeklyExecuteList(ReportCashflowWeeklyExecute entity ,Integer offsize,Integer pagesize)
	{
		if(entity==null)
			return new ArrayList<ReportCashflowWeeklyExecute>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportCashflowWeeklyExecute s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
				}else{
					hql.append(" and s.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
							entity.getOrg()+"'),h.vcOrganId)>0)");
				}
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
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
			
//			hql.append(" and s.parentorg like '%-" + entity.getParentorg() + "-%' ");
		}
		hql.append(" order by s.year desc , s.month desc, s.week desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportCashflowWeeklyExecuteListCount(ReportCashflowWeeklyExecute entity)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportCashflowWeeklyExecute s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
				}else{
					hql.append(" and s.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
							entity.getOrg()+"'),h.vcOrganId)>0)");
				}
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
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
//			hql.append(" and s.parentorg like '%-" + entity.getParentorg() + "-%' ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportCashflowWeeklyExecuteRepeatCheck(ReportCashflowWeeklyExecute entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportCashflowWeeklyExecute s where 1=1 and isdel=0  ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and s.org = '"+entity.getOrg()+ "' ");
			}
			
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
			
			if(entity.getMonth()!=null)
			{
				hql.append(" and s.month = "+entity.getMonth()+ " ");
			}
			
			if(entity.getWeek()!=null)
			{
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
		
			if(entity.getId()!=null)
			{
				hql.append(" and s.id != "+entity.getId()+ " ");
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
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportCashflowWeeklyExecute entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportCashflowWeeklyExecute s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
		if(entity!=null)
		{
			
			if(entity.getId()!=null)
			{
				hql.append(" and id ="+entity.getId()  +"");
			}
			else
				return true;
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return false;
		else
			return true;
	}

	@Override
	public ReportWeeklyFinancingIntoDetail getReportReportFinancingIntoDetail(
			Integer id) {
		// TODO Auto-generated method stub
		if(id==null)
			return new ReportWeeklyFinancingIntoDetail();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingIntoDetail s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportWeeklyFinancingIntoDetail) query.uniqueResult();
	}

	@Override
	public List<ReportWeeklyFinancingIntoDetail> getList1(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from ReportWeeklyFinancingIntoDetail s where s.weekId=" + id + "and s.isdel=0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<ReportWeeklyFinancingOutDetail> getList2(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from ReportWeeklyFinancingOutDetail s where s.weekId=" + id + "and s.isdel=0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<ReportWeeklyInvestmentOutDetail> getList3(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from ReportWeeklyInvestmentOutDetail s where s.weekId=" + id + "and s.isdel=0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public HhOrganInfoTreeRelation getCoreCompId(String organalID, int type) {
		// TODO Auto-generated method stub
		String hql = "from HhOrganInfoTreeRelation where id.nnodeId = '" + organalID + "' and id.type="+type+" and cflag = 1 ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhOrganInfoTreeRelation)query.uniqueResult();
	}
	

	
	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	@Override
	public List<ReportCashflowWeeklyExecute> getReportCashflowWeeklyExecuteForSumData(ReportCashflowWeeklyExecute entity ,Integer offsize,Integer pagesize){
		
		if(entity==null || null==entity.getOrg())
			return new ArrayList<ReportCashflowWeeklyExecute>();
		
		StringBuilder  hql = new StringBuilder();

		hql.append(" select r from ReportCashflowWeeklyExecute r, HhOrganInfoTreeRelation h where 1=1 and r.org = h.id.nnodeId and r.isdel = 0 "); 
		if(entity.getStatus()!=null)
		{
			hql.append(" and r.status = "+entity.getStatus()+ " ");
		}
		if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
		{
			hql.append(" and r.status != "+Base.examstatus_1+ " ");
		}
		if(Common.notEmpty(entity.getStarttime()))
		{	
			hql.append(" and str_to_date(CONCAT(r.year,r.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
		}
		if(Common.notEmpty(entity.getEndtime()))
		{	
			hql.append(" and str_to_date(CONCAT(r.year,r.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
		}					
		hql.append(" and h.vcParentId in (select ttt.vcOrganId from HhOrganInfoTreeRelation ttt where ttt.id.nnodeId = '"+entity.getOrg()+"')");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();		
	}
	
	@Override
	public int getReportCashflowWeeklyExecuteForSumDataAllData(ReportCashflowWeeklyExecute entity){
		
		if(entity==null || null==entity.getOrg())
			return 0;
		
		StringBuilder  hql = new StringBuilder();

		hql.append(" select count(r) from ReportCashflowWeeklyExecute r, HhOrganInfoTreeRelation h where 1=1 and r.org = h.id.nnodeId and r.isdel = 0 "); 
		if(entity.getStatus()!=null)
		{
			hql.append(" and r.status = "+entity.getStatus()+ " ");
		}
		if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
		{
			hql.append(" and r.status != "+Base.examstatus_1+ " ");
		}
		if(Common.notEmpty(entity.getStarttime()))
		{	
			hql.append(" and str_to_date(CONCAT(r.year,r.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
		}
		if(Common.notEmpty(entity.getEndtime()))
		{	
			hql.append(" and str_to_date(CONCAT(r.year,r.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
		}					
		hql.append(" and h.vcParentId in (select ttt.vcOrganId from HhOrganInfoTreeRelation ttt where ttt.id.nnodeId = '"+entity.getOrg()+"')");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.valueOf(query.uniqueResult().toString());			
	}
	

	/**
	 * 汇总给出虚拟公司下子公司的所有信息
	 * @param info
	 * @return  
	 */
	@Override
	public List<ReportCashflowWeeklyExecute> getSumChildrenData(HhOrganInfoTreeRelation info,ReportCashflowWeeklyExecute entityIn){
		
		if(null==info || null==entityIn)
			return new ArrayList<ReportCashflowWeeklyExecute>();
		
		StringBuilder hql = new StringBuilder();

		hql.append(" select r from ReportCashflowWeeklyExecute r,HhOrganInfoTreeRelation h where 1=1 and h.id.nnodeId=r.org and r.isdel = 0 and h.status in (50500,50502) and r.status = 50115");
		
		if(null != entityIn.getYear())
			hql.append(" and r.year = ").append(entityIn.getYear());
		
		if(null != entityIn.getMonth())
			hql.append(" and r.month = ").append(entityIn.getMonth());
		
		if(null != entityIn.getWeek())
			hql.append(" and r.week = ").append(entityIn.getWeek());
		
		if(Common.notEmpty(entityIn.getWeekStartDate()))
			hql.append(" and r.weekStartDate >= ").append(entityIn.getWeekStartDate());
		
		if(Common.notEmpty(entityIn.getWeekEndDate()))
			hql.append(" and r.weekEndDate <= ").append(entityIn.getWeekEndDate());
		
		if(Common.notEmpty(info.getVcOrganId()))
			hql.append(" and r.parentorg like '%").append(info.getVcOrganId()).append("%'");
		else
			hql.append(" and r.parentorg like '%----%'");
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return query.list();
		
	}


	@Override
	public List<ReportCashflowWeeklyExecute> getList(ReportCashflowWeeklyExecute entity) {
		if(entity==null)
			return new ArrayList<ReportCashflowWeeklyExecute>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportCashflowWeeklyExecute s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
				}else{
					hql.append(" and s.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
							entity.getOrg()+"'),h.vcOrganId)>0)");
				}
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
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
			
//			hql.append(" and s.parentorg like '%-" + entity.getParentorg() + "-%' ");
		}
		hql.append(" order by s.year desc , s.month desc, s.week desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
}
