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
import com.softline.dao.report.IReportCashflowMonthlyExecuteDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.ReportCashflowWeeklyExecute;
import com.softline.entity.ReportMonthlyFinancingIntoDetail;
import com.softline.entity.ReportMonthlyFinancingOutDetail;
import com.softline.entity.ReportMonthlyInvestmentOutDetail;
import com.softline.entity.ReportWeeklyFinancingIntoDetail;
import com.softline.entity.ReportWeeklyFinancingOutDetail;
import com.softline.entity.ReportWeeklyInvestmentOutDetail;

@Repository(value = "reportCashflowMonthlyExecuteDao")
/**
 * 
 * @author tch
 *
 */
public class ReportCashflowMonthlyExecuteDao implements IReportCashflowMonthlyExecuteDao{
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
	public ReportCashflowMonthlyExecute getReportCashflowMonthlyExecute(Integer id)
	{
		if(id==null)
			return new ReportCashflowMonthlyExecute();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportCashflowMonthlyExecute s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportCashflowMonthlyExecute) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportCashflowMonthlyExecute> getReportCashflowMonthlyExecuteList(ReportCashflowMonthlyExecute entity ,Integer offsize,Integer pagesize)
	{
		if(entity==null)
			return new ArrayList<ReportCashflowMonthlyExecute>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportCashflowMonthlyExecute s where 1=1 and isdel=0 ");
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
		hql.append(" order by s.year desc , s.month desc ");
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
	public int getReportCashflowMonthlyExecuteListCount(ReportCashflowMonthlyExecute entity)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportCashflowMonthlyExecute s where 1=1 and isdel=0 ");
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
	public boolean saveReportCashflowMonthlyExecuteRepeatCheck(ReportCashflowMonthlyExecute entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportCashflowMonthlyExecute s where 1=1 and isdel=0  ");
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
	public boolean checkCanEdit(ReportCashflowMonthlyExecute entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportCashflowMonthlyExecute s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	public List<ReportMonthlyFinancingIntoDetail> getList1(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from ReportMonthlyFinancingIntoDetail s where s.monthId=" + id + "and s.isdel=0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<ReportMonthlyFinancingOutDetail> getList2(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from ReportMonthlyFinancingOutDetail s where s.monthId=" + id + "and s.isdel=0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<ReportMonthlyInvestmentOutDetail> getList3(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from ReportMonthlyInvestmentOutDetail s where s.monthId=" + id + "and s.isdel=0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<ReportWeeklyFinancingIntoDetail> getWeeksList1(String compId,
			Integer year, Integer month) {
		// TODO Auto-generated method stub
		String hql = "from ReportWeeklyFinancingIntoDetail s where s.weekId in( select w.id from ReportCashflowWeeklyExecute w where w.isdel=0 and w.status=50115 and w.year="+year+" and w.month="+month+" and w.org='"+compId+"') and s.isdel=0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<ReportWeeklyFinancingOutDetail> getWeeksList2(String compId,
			Integer year, Integer month) {
		// TODO Auto-generated method stub
		String hql = "from ReportWeeklyFinancingOutDetail s where s.weekId in( select w.id from ReportCashflowWeeklyExecute w where w.isdel=0 and w.status=50115 and w.year="+year+" and w.month="+month+" and w.org='"+compId+"') and s.isdel=0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<ReportWeeklyInvestmentOutDetail> getWeeksList3(String compId,
			Integer year, Integer month) {
		// TODO Auto-generated method stub
		String hql = "from ReportWeeklyInvestmentOutDetail s where s.weekId in( select w.id from ReportCashflowWeeklyExecute w where w.isdel=0 and w.status=50115 and w.year="+year+" and w.month="+month+" and w.org='"+compId+"') and s.isdel=0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	@Override
	public List<ReportCashflowMonthlyExecute> getReportCashflowMonthlyExecuteListForSumData(ReportCashflowMonthlyExecute entity ,Integer offsize,Integer pagesize){
		
		if(entity==null || null==entity.getOrg())
			return new ArrayList<ReportCashflowMonthlyExecute>();
		
		StringBuilder  hql = new StringBuilder();
		
		String date = null;
		if(null == entity.getYear() || null == entity.getMonth())
			return new ArrayList<ReportCashflowMonthlyExecute>();
		date =  String.valueOf(entity.getYear())+"-"+(String.valueOf(entity.getMonth()).length()==2?
														String.valueOf(entity.getMonth()):
															"0"+String.valueOf(entity.getMonth()));

		hql.append(" select r from ReportCashflowMonthlyExecute r, HhOrganInfoTreeRelationHistory h where 1=1 and r.org = h.id.nnodeId and r.isdel = 0 and h.id.updatetime='" + date +"'");
		if(entity.getStatus()!=null){
			hql.append(" and r.status = "+entity.getStatus()+ " ");
		}
		if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
		{
			hql.append(" and r.status != "+Base.examstatus_1+ " ");
		}
		
		if(entity.getYear()!=null)
		{
			hql.append(" and r.year = "+entity.getYear()+ " ");
		}
		
		if(entity.getMonth()!=null)
		{
			hql.append(" and r.month = "+entity.getMonth()+ " ");
		}			
		
		if(Common.notEmpty(entity.getStarttime()))
		{	
			hql.append(" and str_to_date(CONCAT(r.year,r.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
		}
		if(Common.notEmpty(entity.getEndtime()))
		{	
			hql.append(" and str_to_date(CONCAT(r.year,r.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
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
	public Integer getReportCashflowMonthlyExecuteListForSumDataTotal(ReportCashflowMonthlyExecute entity){
		
		if(entity==null || null==entity.getOrg())
			return 0;
		
		StringBuilder  hql = new StringBuilder();
		
		String date = null;
		if(null == entity.getYear() || null == entity.getMonth())
			return 0;
		
		date =  String.valueOf(entity.getYear())+"-"+(String.valueOf(entity.getMonth()).length()==2?
														String.valueOf(entity.getMonth()):
															"0"+String.valueOf(entity.getMonth()));

		hql.append(" select count(r) from ReportCashflowMonthlyExecute r, HhOrganInfoTreeRelationHistory h where 1=1 and r.org = h.id.nnodeId and r.isdel = 0 and h.id.updatetime='" + date +"'");
		if(entity.getStatus()!=null){
			hql.append(" and r.status = "+entity.getStatus()+ " ");
		}
		if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
		{
			hql.append(" and r.status != "+Base.examstatus_1+ " ");
		}
		
		if(entity.getYear()!=null)
		{
			hql.append(" and r.year = "+entity.getYear()+ " ");
		}
		
		if(entity.getMonth()!=null)
		{
			hql.append(" and r.month = "+entity.getMonth()+ " ");
		}			
		
		if(Common.notEmpty(entity.getStarttime()))
		{	
			hql.append(" and str_to_date(CONCAT(r.year,r.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
		}
		if(Common.notEmpty(entity.getEndtime()))
		{	
			hql.append(" and str_to_date(CONCAT(r.year,r.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
		}						
		hql.append(" and h.vcParentId in (select ttt.vcOrganId from HhOrganInfoTreeRelationHistory ttt where ttt.id.nnodeId = '"+entity.getOrg()+"')");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());

		return Integer.valueOf(query.uniqueResult().toString());		
	}
	
	
	
	@Override
	public List<ReportCashflowMonthlyExecute> getReportCashflowMonthlyExecuteListForSumData(ReportCashflowMonthlyExecute entity){
		
		if(entity==null || null==entity.getOrg())
			return new ArrayList<ReportCashflowMonthlyExecute>();
		
		StringBuilder  hql = new StringBuilder();
		
		String date = null;
		if(null == entity.getYear() || null == entity.getMonth())
			return new ArrayList<ReportCashflowMonthlyExecute>();
		
		date =  String.valueOf(entity.getYear())+"-"+(String.valueOf(entity.getMonth()).length()==2?
														String.valueOf(entity.getMonth()):
															"0"+String.valueOf(entity.getMonth()));

		hql.append(" select count(r) from ReportCashflowMonthlyExecute r, HhOrganInfoTreeRelationHistory h where 1=1 and r.org = h.id.nnodeId and r.isdel = 0 and h.id.updatetime='" + date +"'");
		if(entity.getStatus()!=null){
			hql.append(" and r.status = "+entity.getStatus()+ " ");
		}
		if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
		{
			hql.append(" and r.status != "+Base.examstatus_1+ " ");
		}
		
		if(entity.getYear()!=null)
		{
			hql.append(" and r.year = "+entity.getYear()+ " ");
		}
		
		if(entity.getMonth()!=null)
		{
			hql.append(" and r.month = "+entity.getMonth()+ " ");
		}			
		
		if(Common.notEmpty(entity.getStarttime()))
		{	
			hql.append(" and str_to_date(CONCAT(r.year,r.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
		}
		if(Common.notEmpty(entity.getEndtime()))
		{	
			hql.append(" and str_to_date(CONCAT(r.year,r.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
		}						
		hql.append(" and h.vcParentId in (select ttt.vcOrganId from HhOrganInfoTreeRelationHistory ttt where ttt.id.nnodeId = '"+entity.getOrg()+"')");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());

		return query.list();		
	}
	
	
	/**
	 * 汇总数据--查询相关公司信息下一层级公司信息
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	@Override
	public List<HhOrganInfoTreeRelation> getHhOrganInfoTreeRelationSumData(ReportCashflowMonthlyExecute entity ,Integer offsize,Integer pagesize){
		
		if(entity==null || null==entity.getOrg())
			return new ArrayList<HhOrganInfoTreeRelation>();
		
		StringBuilder  hql = new StringBuilder();
		hql.append(" from HhOrganInfoTreeRelation h where h.vcParentId in (select tt.vcOrganId from HhOrganInfoTreeRelation tt where tt.id.nnodeId = '"+entity.getOrg()+"') ");
		//hql.append(" from HhOrganInfoTreeRelation h where h.id.nnodeId in (select tt.id.nnodeId from HhOrganInfoTreeRelation tt where tt.id.nnodeId = '"+entity.getOrg()+"' and tt.vcOrganId = h.vcParentId )");	
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
	public List<ReportCashflowMonthlyExecute> getSumChildrenData(HhOrganInfoTreeRelationHistory info,ReportCashflowMonthlyExecute entityIn){
		
		if(null==info || null==entityIn)
			return new ArrayList<ReportCashflowMonthlyExecute>();
		
		StringBuilder hql = new StringBuilder();
		
		String date = null;
		if(null == entityIn.getYear() || null == entityIn.getMonth())
			return new ArrayList<ReportCashflowMonthlyExecute>();
		
		date =  String.valueOf(entityIn.getYear())+"-"+(String.valueOf(entityIn.getMonth()).length()==2?
														String.valueOf(entityIn.getMonth()):
															"0"+String.valueOf(entityIn.getMonth()));

						
		hql.append(" select r from ReportCashflowMonthlyExecute r,HhOrganInfoTreeRelationHistory h where 1=1 and h.id.nnodeId=r.org and r.isdel = 0 and h.status in (50500,50502) and r.status = 50115 and h.id.updatetime='" + date +"'");
		
		if(null != entityIn.getYear())
			hql.append(" and r.year = ").append(entityIn.getYear());
		
		if(null != entityIn.getMonth())
			hql.append(" and r.month = ").append(entityIn.getMonth());
		
		if(Common.notEmpty(info.getVcOrganId()))
			hql.append(" and r.parentorg like '%").append(info.getVcOrganId()).append("%'");
		else
			return new ArrayList<ReportCashflowMonthlyExecute>();
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return query.list();
		
	}
	
	@Override
	public HhOrganInfoTreeRelation getCoreCompId(String organalID, int type) {
		// TODO Auto-generated method stub
		String hql = "from HhOrganInfoTreeRelation where id.nnodeId = '" + organalID + "' and id.type="+type+" and cflag = 1 ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhOrganInfoTreeRelation)query.uniqueResult();
	}

	@Override
	public List<ReportCashflowMonthlyExecute> getReportCashflowMonthlyExecuteListView(ReportCashflowMonthlyExecute entity) {
		if(entity==null)
			return new ArrayList<ReportCashflowMonthlyExecute>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportCashflowMonthlyExecute s where 1=1 and isdel=0 ");
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
		hql.append(" order by s.year desc , s.month desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
}
