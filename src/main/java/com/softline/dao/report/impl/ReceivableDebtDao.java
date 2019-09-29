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
import com.softline.dao.report.IReceivableDebtDao;
import com.softline.entity.ReportPersonlloaninfo;
import com.softline.entity.ReportReceivabledebt;
import com.softline.entity.ReportReceivabledebtinfo;


@Repository(value = "receivabledebtDao")

public class ReceivableDebtDao implements IReceivableDebtDao {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ReportReceivabledebt getReceivabledebtbyID(Integer id) {
		if(id==null)
			return new ReportReceivabledebt();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportReceivabledebt s where 1=1 and isdel=0 and id="+id+"");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		ReportReceivabledebt a= (ReportReceivabledebt) query.uniqueResult();
		
		hql = new StringBuilder();
		hql.append(" from ReportReceivabledebtinfo s where 1=1 and isdel=0 and groupid="+id+"");
		query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<ReportReceivabledebtinfo> b=query.list();
		a.setInfolist(b);
		return a;
	}

	@Override
	public int getReceivabledebtListCount(ReportReceivabledebt entity) {
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportReceivabledebt s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
			if(Common.notEmpty(entity.getDebtorg()) )
			{
				hql.append(" and s.debtorg in( "+ Common.dealinStr( entity.getDebtorg())+ ") ");
			}
			
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
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
	public List<ReportReceivabledebt> getReceivabledebtList(
			ReportReceivabledebt entity, Integer offsize, Integer pagesize) {
		if(entity==null)
			return new ArrayList<ReportReceivabledebt>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportReceivabledebt s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
			if(Common.notEmpty(entity.getDebtorg()) )
			{
				hql.append(" and s.debtorg in( "+ Common.dealinStr( entity.getDebtorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
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
		hql.append(" order by year desc,month desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	@Override
	public boolean saveReceivabledebtRepeatCheck(ReportReceivabledebt entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportReceivabledebt s where 1=1 and isdel=0  ");
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
			if(Common.notEmpty(entity.getDebtorg()))
			{
				hql.append(" and s.debtorg = '"+entity.getDebtorg()+ "' ");
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

	@Override
	public boolean checkCanEdit(ReportReceivabledebt entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportReceivabledebt s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
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
		hql.append("update report_receivabledebtinfo set isdel=1  ");	
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
	public void saveReceivabledebtinfoitem(List<ReportReceivabledebtinfo> a) {
		if(a==null || a.size()==0)
			return;
		StringBuilder  hql = new StringBuilder();
		hql.append("INSERT INTO `report_receivabledebtinfo` (`groupid`, `year`, `month`, `coreorg`, `coreorgname`,debtorg,debtorgname, `oppositeorgname`, `collectionperson`, " +
				"`leadership`, `accountingsubject`, `beginningbalance`, `beginningcreditperiodbalance`, `beginningbalancethirtydays`, `beginningbalancesixtydays`, `beginningbalanceninetydays`, " +
				"`beginningbalanceoverdays`, `monthnewaccout`, `monthreturnaccout`, `momthreturnoveraccout`, `endingbalance`, `endingcreditperiodbalance`, `endingbalancethirtydays`, " +
				"`endingbalancesixtydays`, `endingbalanceninetydays`, `endingbalanceoverdays`, `endingbalancetwoyears`, `endingbalancethreeyears`, `endingbalancefiveyears`, " +
				"`endingbalanceoveryears`, `reasonorremarks`, `debttype`, `cashdepositdeadline`, `collectionmeasures`, `planreturntime`, " +
				"`is_finish`, `is_send`, `is_promise`, `is_turnover`, `isdel`, `parentorg`,type) VALUES");
		
		for (ReportReceivabledebtinfo item : a) {
			hql.append(" ( "+item.getGroupid()+","+item.getYear()+","+item.getMonth()+",'"+item.getCoreorg()+"','"+item.getCoreorgname()+"','"+item.getDebtorg()+"','"+item.getDebtorgname()+"', '"+item.getOppositeorgname()+"', '"+item.getCollectionperson()+"', " +
							"'"+item.getLeadership()+"', '"+item.getAccountingsubject()+"',"+item.getBeginningbalance()+",'"+item.getBeginningcreditperiodbalance()+"','"+item.getBeginningbalancethirtydays()+"','"+item.getBeginningbalancesixtydays()+"','"+item.getBeginningbalanceninetydays()+"'," +
							"'"+item.getBeginningbalanceoverdays()+"','"+item.getMonthnewaccout()+"','"+item.getMonthreturnaccout()+"','"+item.getMomthreturnoveraccout()+"','"+item.getEndingbalance()+"','"+item.getEndingcreditperiodbalance()+"','"+item.getEndingbalancethirtydays()+"'," +
							"'"+item.getEndingbalancesixtydays()+"','"+item.getEndingbalanceninetydays()+"','"+item.getEndingbalanceoverdays()+"','"+item.getEndingbalancetwoyears()+"','"+item.getEndingbalancethreeyears()+"','"+item.getEndingbalancefiveyears()+"'," +
							"'"+item.getEndingbalanceoveryears()+"','"+item.getReasonorremarks()+"','"+item.getDebttype()+"','"+item.getCashdepositdeadline()+"','"+item.getCollectionmeasures()+"','"+item.getPlanreturntime()+"'," +
							"'"+item.getIsFinish()+"','"+item.getIsSend()+"','"+item.getIsPromise()+"','"+item.getIsTurnover()+"','"+item.getIsdel()+"','"+item.getParentorg()+"',"+item.getType()+"),");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString().substring(0, hql.toString().length()-1));
		query.executeUpdate();
		
	}

	@Override
	public Integer getReceivabledebtinfoCount(Integer groupID) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportReceivabledebtinfo s where 1=1 and isdel=0  ");	
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
	public List<ReportReceivabledebtinfo> getReceivabledebtinfoList(Integer groupID, Integer offsize, Integer pagesize) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportReceivabledebtinfo s where 1=1 and isdel=0  ");	
		if(groupID!=null)
		{
			hql.append(" and s.groupid = "+groupID+ " order by id ");
		}
		else
			return new ArrayList<ReportReceivabledebtinfo>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}


	
	
	/**
	 *   --------------------------------应收债权(内部)明细查询---------------------------------------
	 */
	@Override
	public Integer getReceivabledebtinfoDetailCount(ReportReceivabledebtinfo entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportReceivabledebtinfo s where 1=1 and isdel=0  ");	
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDebtorg()) )
			{
				hql.append(" and s.debtorg in( "+ Common.dealinStr( entity.getDebtorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
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
	public List<ReportReceivabledebtinfo> getReceivabledebtinfoDetailList(ReportReceivabledebtinfo entity, Integer offsize, Integer pagesize) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportReceivabledebtinfo s where 1=1 and isdel=0  ");	
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDebtorg()) )
			{
				hql.append(" and s.debtorg in( "+ Common.dealinStr( entity.getDebtorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
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
			hql.append(" order by coreorg,year desc,month desc");
		}
		else
			return new ArrayList<ReportReceivabledebtinfo>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	@Override
	public ReportReceivabledebtinfo getReceivabledebtinfobyID(Integer id) {
		if(id==null)
			return new ReportReceivabledebtinfo();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportReceivabledebtinfo s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		ReportReceivabledebtinfo a= (ReportReceivabledebtinfo) query.uniqueResult();
		return a;
	}

	
	
	/**
	 *   --------------------------------应收债权(内部)汇总查询---------------------------------------
	 */
	
	
	@Override
	public Integer getReceivabledebtinfoCollectCount(ReportReceivabledebtinfo entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT COUNT(a.groupid) from (SELECT s.groupid from (report_receivabledebtinfo s left join report_receivabledebt t on s.groupid = t.id) left join hh_organInfo_tree_relation hort on s.debtorg = hort.nNodeID  where 1=1   ");	
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				//hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
				hql.append(" and s.coreorg is not null ");
				hql.append(" and hort.vcOrganID like concat('%',(select vcOrganID from hh_organInfo_tree_relation where nNodeID = '"+entity.getCoreorg()+"'),'%')");
			}
			if(Common.notEmpty(entity.getDebtorg()) )
			{
				hql.append(" and s.debtorg in( "+ Common.dealinStr( entity.getDebtorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
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
			hql.append("GROUP BY s.coreorg,s.debtorg,s.year,s.month ) as a ");
		}
		else
			return 0;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Object> getReceivabledebtinfoCollectList(ReportReceivabledebtinfo entity, Integer offsize, Integer pagesize) {
		if(entity==null)
			return new ArrayList<Object>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT s.groupid,s.year,s.month,s.coreorgname,s.debtorgname,SUM(s.beginningbalance),SUM(s.endingbalance),SUM(s.endingbalance-s.endingcreditperiodbalance)," +
				"SUM(s.endingbalanceoveryears) FROM (report_receivabledebtinfo s left join report_receivabledebt t on s.groupid = t.id) left join hh_organInfo_tree_relation hort on s.debtorg = hort.nNodeID where 1=1  ");
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				//hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
				hql.append(" and s.coreorg is not null");
				hql.append(" and hort.vcOrganID like concat('%',(select vcOrganID from hh_organInfo_tree_relation where nNodeID = '"+entity.getCoreorg()+"'),'%')");
			}
			if(Common.notEmpty(entity.getDebtorg()) )
			{
				hql.append(" and s.debtorg in( "+ Common.dealinStr( entity.getDebtorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
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
			hql.append("GROUP BY s.coreorg,s.debtorg,s.year,s.month ");
			hql.append(" order by s.coreorg,s.year desc,s.month desc");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	
	
	/**
	 *   --------------------------------公司大额应收债权(内部)查询---------------------------------------
	 */
	
	@Override
	public Integer getReceivabledebtinfoOrgCount(ReportReceivabledebtinfo entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT COUNT(result.id) from (SELECT s.id,s.year,s.month,s.coreorgname,s.debtorgname,s.oppositeorgname FROM report_receivabledebtinfo s left join hh_organInfo_tree_relation hort on s.debtorg = hort.nNodeID where 1=1    ");	
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDebtorg()) )
			{
				hql.append(" and s.debtorg in( "+ Common.dealinStr( entity.getDebtorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
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
			hql.append("GROUP BY year,month,coreorg,debtorg,oppositeorgname ) as result ");
		}
		else
			return 0;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Object> getReceivabledebtinfoOrgList(
			ReportReceivabledebtinfo entity, Integer offsize, Integer pagesize) {
		if(entity==null)
			return new ArrayList<Object>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT s.id,s.year,s.month,s.coreorgname,s.debtorgname,s.oppositeorgname,s.endingbalance as s1,(s.endingbalance-s.endingcreditperiodbalance),s.collectionperson" +
				" FROM (report_receivabledebtinfo s left join report_receivabledebt t on s.groupid = t.id) left join hh_organInfo_tree_relation hort on s.debtorg = hort.nNodeID where 1=1  ");
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDebtorg()) )
			{
				hql.append(" and s.debtorg in( "+ Common.dealinStr( entity.getDebtorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
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
			hql.append("GROUP BY s.year,s.month,s.coreorg,s.debtorg,s.oppositeorgname ");
			hql.append("ORDER BY s.year desc, s.month desc ,s.coreorg,s.debtorgname,(s1+0) desc ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
/*		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);*/
		return query.list();
	}


	
	
	/**
	 *   --------------------------------超期外部应收账款无催收进展一览---------------------------------------
	 */
	
	@Override
	public Integer getOverOutCount(ReportReceivabledebtinfo entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT COUNT(a.id) from (SELECT * from report_receivabledebtinfo s left join hh_organInfo_tree_relation hort on s.debtorg = hort.nNodeID where 1=1   ");	
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
			}
			hql.append(" and (s.endingbalance-s.beginningbalance)=0 ");
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
			hql.append("GROUP BY coreorg,debtorg,year,month ) as a ");
		}
		else
			return 0;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Object> getOverOutList(ReportReceivabledebtinfo entity,
			Integer offsize, Integer pagesize) {
		if(entity==null)
			return new ArrayList<Object>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT s.groupid,s.year,s.month,s.coreorgname,s.debtorgname,SUM(s.beginningbalance),SUM(s.endingbalance),SUM(s.endingbalance-s.endingcreditperiodbalance)" +
				" FROM report_receivabledebtinfo s left join hh_organInfo_tree_relation hort on s.debtorg = hort.nNodeID where 1=1  ");
		if(entity!=null)
		{
			if (entity.getType() !=null) {
				hql.append(" and s.type = "+entity.getType()+ " ");
			}
			if(Common.notEmpty(entity.getCoreorg()) )
			{
				hql.append(" and s.coreorg in( "+ Common.dealinStr( entity.getCoreorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDebtorg()) )
			{
				hql.append(" and s.debtorg in( "+ Common.dealinStr( entity.getDebtorg())+ ") ");
			}
			if(Common.notEmpty(entity.getDate()) )
			{
				String date=entity.getDate();
				hql.append(" and s.year = "+ Integer.parseInt(date.split("-")[0]) + " ");
				hql.append(" and s.month = "+Integer.parseInt(date.split("-")[1])+ " ");
			}
			hql.append(" and (s.endingbalance-s.beginningbalance)=0 ");
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
			hql.append("GROUP BY coreorg,debtorg,year,month ");
			hql.append(" order by coreorg,year desc,month desc");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	
	
}
