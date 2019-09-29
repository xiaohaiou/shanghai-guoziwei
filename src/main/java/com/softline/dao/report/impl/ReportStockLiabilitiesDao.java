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
import com.softline.dao.report.IReportStockLiabilitiesDao;
import com.softline.entity.ReportOverseasLiabilitiesDetail;
import com.softline.entity.ReportStockLiabilities;

@Repository(value = "reportStockLiabilitiesDao")
/**
 * 
 * @author tch
 *
 */
public class ReportStockLiabilitiesDao implements IReportStockLiabilitiesDao{
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
	public ReportStockLiabilities getReportStockLiabilities(Integer id)
	{
		if(id==null)
			return new ReportStockLiabilities();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportStockLiabilities s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportStockLiabilities) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportStockLiabilities> getReportStockLiabilitiesList(ReportStockLiabilities entity ,Integer offsize,Integer pagesize)
	{
		if(entity==null)
			return new ArrayList<ReportStockLiabilities>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportStockLiabilities s where 1=1 and isdel=0 ");
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
	public int getReportStockLiabilitiesListCount(ReportStockLiabilities entity)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportStockLiabilities s where 1=1 and isdel=0 ");
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
	public boolean saveReportStockLiabilitiesRepeatCheck(ReportStockLiabilities entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportStockLiabilities s where 1=1 and isdel=0  ");
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
	public boolean checkCanEdit(ReportStockLiabilities entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportStockLiabilities s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	public List<ReportOverseasLiabilitiesDetail> getList1(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from ReportOverseasLiabilitiesDetail r where r.isdel = 0 and r.stockLiabilitiesId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * 汇总各种币种值
	 * @param ids
	 * @return
	 */
	@Override
	public List<ReportOverseasLiabilitiesDetail> getList1(String ids){
		if(null == ids)
			return new ArrayList<ReportOverseasLiabilitiesDetail>();
		// SELECT * FROM report_overseas_liabilities_detail t WHERE t.`stock_liabilities_id` IN (4,5) GROUP BY t.`overseas_currency` ;
		String hql = "from ReportOverseasLiabilitiesDetail r where r.isdel = 0 and r.stockLiabilitiesId in (" + ids+") group by r.overseasCurrency";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(null == query.list())
			return new ArrayList<ReportOverseasLiabilitiesDetail>();
		return query.list();	
	}
	
	/**
	 * 汇总查询负载数据
	 */
	@Override
	public List<ReportStockLiabilities> getSumBeanList(String companyName,Integer year,Integer month,String status){

		// SELECT a.* FROM report_stock_liabilities a LEFT JOIN hh_organInfo_tree_relation b ON a.org = b.nNodeID WHERE a.year= 2018 AND a.month= 1 AND b.status = 50502;
		//select store from Store store left join store.user user with user.userId=:id";  
		// 查询当前财务树的最新数据
//		String hql = "select a from ReportStockLiabilities a, HhOrganInfoTreeRelation b where a.org = b.id.nnodeId and b.status in ("+
//																					status+") and a.year="+year+" and a.month="+ month+" and a.org in( "+Common.dealinStr(companyName)+ ") ";	
		
		if(null == year || 
				null ==month)
			return new ArrayList<ReportStockLiabilities>();
		
		String updateTime = String.valueOf(year)+"-"+(String.valueOf(month).length()==2?
																		(String.valueOf(month)):
																			("0"+String.valueOf(month))); // 历史树查找日期
				
		// 查询历史财务树的信息
		String hql = " select a from ReportStockLiabilities a, HhOrganInfoTreeRelationHistory b where a.org = b.id.nnodeId ";
		
		if(null ==status || "".equals(status))
				hql+=" and b.status in ("+status+") ";
		
		hql+=" and a.year="+year+" and a.month="+ month+" and a.org in( "+Common.dealinStr(companyName)+ ") and b.id.updatetime in ('"+updateTime+"')";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}
}
