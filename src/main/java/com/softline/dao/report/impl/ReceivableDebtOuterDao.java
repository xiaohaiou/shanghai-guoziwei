package com.softline.dao.report.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.report.IReceivableDebtOuterDao;
import com.softline.entity.ReportReceivabledebtOuter;
import com.softline.entity.ReportReceivabledebtinfoOuter;


@Repository(value = "receivabledebtOuterDao")

public class ReceivableDebtOuterDao implements IReceivableDebtOuterDao {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ReportReceivabledebtOuter getReceivabledebtbyID(Integer id) {
		if(id==null)
			return new ReportReceivabledebtOuter();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportReceivabledebtOuter s where 1=1 and isdel=0 and id="+id+"");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		ReportReceivabledebtOuter a= (ReportReceivabledebtOuter) query.uniqueResult();
		
		hql = new StringBuilder();
		hql.append(" from ReportReceivabledebtinfoOuter s where 1=1 and isdel=0 and groupid="+id+"");
		query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<ReportReceivabledebtinfoOuter> b=query.list();
		a.setInfolist(b);
		return a;
	}

	@Override
	public int getReceivabledebtListCount(ReportReceivabledebtOuter entity) {
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportReceivabledebtOuter s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
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
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<ReportReceivabledebtOuter> getReceivabledebtList(
			ReportReceivabledebtOuter entity, Integer offsize, Integer pagesize) {
		if(entity==null)
			return new ArrayList<ReportReceivabledebtOuter>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportReceivabledebtOuter s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
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
		}
		hql.append(" order by year desc,week desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	@Override
	public boolean saveReceivabledebtRepeatCheck(ReportReceivabledebtOuter entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportReceivabledebtOuter s where 1=1 and isdel=0  ");
		if(entity!=null)
		{
			
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and s.org = '"+entity.getOrg()+ "' ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
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

	@Override
	public boolean checkCanEdit(ReportReceivabledebtOuter entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportReceivabledebtOuter s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	public boolean deleteReceivabledebtinfo(String groupID) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder  hql = new StringBuilder();
		hql.append("update report_receivabledebtinfo_outer set isdel=1  ");	
		if(Common.notEmpty(groupID))
		{
			hql.append(" where groupid = "+groupID+ " ");
		}
		
		else
			return true;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.executeUpdate()>0;
	}



	@Override
	public Integer getReceivabledebtinfoCount(Integer groupID) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportReceivabledebtinfoOuter s where 1=1 and s.isdel=0  ");	
		if(groupID!=null)
		{
			hql.append(" and s.groupid = "+groupID+ "   ");
		}
		else
			return 0;
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<ReportReceivabledebtinfoOuter> getReceivabledebtinfoList(Integer groupID, Integer offsize, Integer pagesize) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportReceivabledebtinfoOuter s where 1=1 and isdel=0  ");	
		if(groupID!=null)
		{
			hql.append(" and s.groupid = "+groupID+ " order by id ");
		}
		else
			return new ArrayList<ReportReceivabledebtinfoOuter>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}


	
	
	/**
	 *   --------------------------------应收债权(外部)明细查询---------------------------------------
	 */
	@Override
	public Integer getReceivabledebtinfoDetailCount(ReportReceivabledebtinfoOuter entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportReceivabledebtinfoOuter s  where 1=1 and isdel=0  ");	
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			hql.append(" and s.status = 50115");  // 审核通过
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
		}
		else
			return 0;
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<ReportReceivabledebtinfoOuter> getReceivabledebtinfoDetailList(ReportReceivabledebtinfoOuter entity, Integer offsize, Integer pagesize) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportReceivabledebtinfoOuter s where 1=1 and isdel=0  ");	
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			hql.append(" and s.status = 50115");  // 审核通过
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

			hql.append(" order by s.coreorg,s.year desc,s.week desc");
		}
		else
			return new ArrayList<ReportReceivabledebtinfoOuter>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	@Override
	public ReportReceivabledebtinfoOuter getReceivabledebtinfobyID(Integer id) {
		if(id==null)
			return new ReportReceivabledebtinfoOuter();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportReceivabledebtinfoOuter s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		ReportReceivabledebtinfoOuter a= (ReportReceivabledebtinfoOuter) query.uniqueResult();
		return a;
	}

	
	
	/**
	 *   --------------------------------应收债权(内部)汇总查询---------------------------------------
	 */
	
	
	@Override
	public Integer getReceivabledebtinfoCollectCount(ReportReceivabledebtinfoOuter entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT COUNT(a.groupid) from (SELECT s.groupid from (report_receivabledebtinfo_outer s left join  report_receivabledebt_outer t on s.groupid = t.id) left join hh_organInfo_tree_relation hort on hort.nNodeID = s.org where 1=1   ");	
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				//hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
				hql.append(" and s.coreorg is not null ");
				hql.append(" and hort.vcOrganID like concat('%',(select vcOrganID from hh_organInfo_tree_relation where nNodeID = '"+entity.getCoreorg()+"'),'%')");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			hql.append(" and t.status = 50115");  // 审核通过
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
			
			hql.append("GROUP BY s.coreorg,s.org,s.year,s.week ) as a ");
		}
		else
			return 0;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Object> getReceivabledebtinfoCollectList(ReportReceivabledebtinfoOuter entity, Integer offsize, Integer pagesize) {
		if(entity==null)
			return new ArrayList<Object>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT s.groupid,s.year,s.week,s.coreorgname,s.orgname,SUM(s.loan_money),SUM(s.cq_loan_money),SUM(s.yjhz) " +
				"  FROM (report_receivabledebtinfo_outer s left join  report_receivabledebt_outer t on s.groupid = t.id) left join hh_organInfo_tree_relation hort on hort.nNodeID = s.org  where 1=1  ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				//hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
				hql.append(" and s.coreorg is not null ");
				hql.append(" and hort.vcOrganID like concat('%',(select vcOrganID from hh_organInfo_tree_relation where nNodeID = '"+entity.getCoreorg()+"'),'%')");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			hql.append(" and t.status = 50115");  // 审核通过
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
			hql.append("GROUP BY s.coreorg,s.org,s.year,s.week ");
			hql.append(" order by s.coreorg,s.year desc,s.week desc");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	
	
	/**
	 *   --------------------------------公司大额应收债权(外部)查询---------------------------------------
	 */
	
	@Override
	public Integer getReceivabledebtinfoOrgCount(ReportReceivabledebtinfoOuter entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT COUNT(result.id) from (SELECT s.id,s.year,s.week,s.coreorgname,s.debtorgname,s.debt_orgname FROM (report_receivabledebtinfo_outer s left join  report_receivabledebt_outer t on s.groupid = t.id) left join hh_organInfo_tree_relation hort on hort.nNodeID = s.org  where 1=1    ");	
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			hql.append(" and t.status = 50115");  // 审核通过
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
			hql.append("GROUP BY s.year,s.week,s.coreorg,s.org,s.debt_orgname ) as result ");
		}
		else
			return 0;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Object> getReceivabledebtinfoOrgList(
			ReportReceivabledebtinfoOuter entity, Integer offsize, Integer pagesize) {
		if(entity==null)
			return new ArrayList<Object>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT s.id,s.year,s.week,s.coreorgname,s.orgname,s.debt_orgname,sum(s.loan_money) as s1 ,sum(s.cq_loan_money)" +
				" FROM (report_receivabledebtinfo_outer s left join report_receivabledebt_outer t on s.groupid = t.id) left join hh_organInfo_tree_relation hort on hort.nNodeID = s.org where 1=1  ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			hql.append(" and t.status = 50115");  // 审核通过
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
			hql.append("GROUP BY s.year,s.week,s.coreorg,s.org,s.debt_orgname ");
			hql.append("ORDER BY s.year desc, s.week desc ,s.coreorg,s.debt_orgname,s1 desc ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}


	
	
	/**
	 *   --------------------------------超期外部应收账款无催收进展一览---------------------------------------
	 */
	
	@Override
	public Integer getOverOutCount(ReportReceivabledebtinfoOuter entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT count(s.id) from (report_receivabledebtinfo_outer s left join report_receivabledebt_outer t on s.groupid = t.id) left join hh_organInfo_tree_relation hort on hort.nNodeID = s.org  where 1=1   ");	
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
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
			hql.append(" and s.is_overtime = 1");  // 超期
			hql.append(" and s.is_cuishou = 0");   //未催收
			hql.append(" and t.status = 50115");  // 审核通过
		}
		else
			return 0;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<ReportReceivabledebtinfoOuter> getOverOutList(ReportReceivabledebtinfoOuter entity,
			Integer offsize, Integer pagesize) {
		if(entity==null)
			return new ArrayList<ReportReceivabledebtinfoOuter>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportReceivabledebtinfoOuter s where 1=1   ");	
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			hql.append(" and s.isOvertime = 1");  // 超期
			hql.append(" and s.isCuishou = 0 ");   //未催收
			hql.append(" and s.status = 50115");
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
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	@Override
	public List<ReportReceivabledebtinfoOuter> getReportReceivabledebtOuter(ReportReceivabledebtinfoOuter entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportReceivabledebtinfoOuter s where 1=1 and isdel=0  ");	
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			hql.append(" and s.status = 50115");  // 审核通过
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

			hql.append(" order by s.coreorg,s.year desc,s.week desc");
		}
		else
			return new ArrayList<ReportReceivabledebtinfoOuter>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<ReportReceivabledebtinfoOuter> getReceivabledebtinfoCollectListExport(ReportReceivabledebtinfoOuter entity) {
		if(entity==null)
			return new ArrayList<ReportReceivabledebtinfoOuter>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT s.groupid,s.year,s.week,s.coreorgname,s.orgname,SUM(s.loan_money),SUM(s.cq_loan_money),SUM(s.yjhz) " +
				"  FROM (report_receivabledebtinfo_outer s left join  report_receivabledebt_outer t on s.groupid = t.id) left join hh_organInfo_tree_relation hort on hort.nNodeID = s.org  where 1=1  ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			hql.append(" and t.status = 50115");  // 审核通过
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
			hql.append("GROUP BY s.coreorg,s.org,s.year,s.week ");
			hql.append(" order by s.coreorg,s.year desc,s.week desc");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());

		return query.list();
	}

	@Override
	public List<ReportReceivabledebtinfoOuter> getReceivabledebtinfoOrgExport(ReportReceivabledebtinfoOuter entity) {
		if(entity==null)
			return new ArrayList<ReportReceivabledebtinfoOuter>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT s.id,s.year,s.week,s.coreorgname,s.orgname,s.debt_orgname,sum(s.loan_money) as s1 ,sum(s.cq_loan_money)" +
				" FROM (report_receivabledebtinfo_outer s left join report_receivabledebt_outer t on s.groupid = t.id) left join hh_organInfo_tree_relation hort on hort.nNodeID = s.org where 1=1  ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			hql.append(" and t.status = 50115");  // 审核通过
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
			hql.append("GROUP BY s.year,s.week,s.coreorg,s.org,s.debt_orgname ");
			hql.append("ORDER BY s.year desc, s.week desc ,s.coreorg,s.debt_orgname,s1 desc ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<ReportReceivabledebtinfoOuter> getOverOutListExport(ReportReceivabledebtinfoOuter entity) {
		if(entity==null)
			return new ArrayList<ReportReceivabledebtinfoOuter>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportReceivabledebtinfoOuter s where 1=1   ");	
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
			}
			hql.append(" and s.isOvertime = 1");  // 超期
			hql.append(" and s.isCuishou = 0 ");   //未催收
			hql.append(" and s.status = 50115");
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
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	
}
