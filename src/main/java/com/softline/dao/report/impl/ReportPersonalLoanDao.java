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
import com.softline.controller.common.commonController;
import com.softline.dao.report.IReportPersonalLoanDao;
import com.softline.entity.ReportPersonalloan;
import com.softline.entity.ReportPersonalloanNearMonth;
import com.softline.entity.ReportPersonalloanNearMonthDetail;
import com.softline.entity.ReportPersonlloaninfo;

@Repository(value = "reportPersonalLoanDao")
/**
 * 
 * @author lcc
 *
 */
public class ReportPersonalLoanDao implements IReportPersonalLoanDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ReportPersonalloan getPersonalLoanbyID(Integer id) {
		
		if(id==null)
			return new ReportPersonalloan();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportPersonalloan s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		ReportPersonalloan a= (ReportPersonalloan) query.uniqueResult();
		
		hql = new StringBuilder();
		hql.append(" from ReportPersonlloaninfo s where 1=1 and isdel=0 and groupid="+id);
		query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<ReportPersonlloaninfo> b=query.list();
		a.setInfolist(b);
		return a;
	}

	@Override
	public int getPersonalloanListCount(ReportPersonalloan entity) {
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportPersonalloan s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
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
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.parentorg like '%--%' ");
			}
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<ReportPersonalloan> getPersonalloanList(
			ReportPersonalloan entity, Integer offsize, Integer pagesize) {
		if(entity==null)
			return new ArrayList<ReportPersonalloan>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportPersonalloan s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
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
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.parentorg like '%--%' ");
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

	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	@Override
	public boolean savePersonalloanRepeatCheck(ReportPersonalloan entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportPersonalloan s where 1=1 and isdel=0  ");
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

	@Override
	public boolean checkCanEdit(ReportPersonalloan entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportPersonalloan s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	public boolean deletePersonalloaninfo(String groupID) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder hql = new StringBuilder();
		hql.append("update report_personlloaninfo set isdel=1  ");	
		if(Common.notEmpty(groupID))
		{
			hql.append(" where groupid = "+groupID+ " ");
		}
		else 
			return true;
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		
		StringBuilder hql1 = new StringBuilder();
		hql1.append("update report_personalloan_nearMonth set isdel=1  ");	
		if(Common.notEmpty(groupID))
		{
			hql1.append(" where groupid = "+groupID+ " ");
		}
		else 
			return true;
		
		Query query1 = sessionFactory.getCurrentSession().createSQLQuery(hql1.toString());
		StringBuilder hql2 = new StringBuilder();
		hql2.append("update report_personalloan_nearMonth_detail set isdel=1  ");	
		if(Common.notEmpty(groupID))
		{
			hql2.append(" where groupid = "+groupID+ " ");
		}
		else 
			return true;
		Query query2 = sessionFactory.getCurrentSession().createSQLQuery(hql2.toString());
		
		return query.executeUpdate()+query1.executeUpdate()+query2.executeUpdate()>0;
	}

	@Override
	public void savePersonlloaninfoitem(List<ReportPersonlloaninfo> a,ReportPersonalloanNearMonth personalloanNearMonth) {
		if(a==null || a.size()==0)
			return;
		StringBuilder  hql = new StringBuilder();
		hql.append("INSERT INTO `report_personlloaninfo` (`isdel`,`groupid`,year,month,coreorg,coreorgname,org,`orgname`,fillcompanyname, `responsibleperson`, `useway`, `loantime`, `loanmoney`, `loanmonth`, `remark`, `checktxt`, `loannum`,parentorg) VALUES");
		
		for (ReportPersonlloaninfo item : a) {
			hql.append(" ( "+item.getIsdel()+","+item.getGroupid()+","+item.getYear()+","+item.getMonth()+",'"+item.getCoreorg()+"','"+item.getCoreorgname()+"','"+item.getOrg()+"', '"+item.getOrgname()+"','"+item.getFillcompanyname()+"', '"+item.getResponsibleperson()+"', '"+item.getUseway()+"', '"+item.getLoantime()+"', '"
							+item.getLoanmoney()+"',"+item.getLoanmonth()+",'"+item.getRemark()+"','"+item.getChecktxt()+"','"+item.getLoannum()+"','"+item.getParentorg()+"'),");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString().substring(0, hql.toString().length()-1));
		query.executeUpdate();
		
		if(personalloanNearMonth==null)
			return;
		StringBuilder  sql = new StringBuilder();
		sql.append("INSERT INTO `report_personalloan_nearMonth_detail` (`groupid`, `coreorg`, `coreorgname`, `orgname`, `year`, `month`, `personname`, `beginningbalance`, `monthaddmoney`, `monthreturnmoney`, `endingbalance`, `isdel`, `parentorg`) VALUES");
		
		for (ReportPersonalloanNearMonthDetail item : personalloanNearMonth.getDetaillist()) {
			sql.append(" ( "+personalloanNearMonth.getGroupid()+", '"+item.getCoreorg()+"', '"+item.getCoreorgname()+"', '"+item.getOrgname()+"', '"+item.getYear()+"', '"
							+item.getMonth()+"','"+item.getPersonname()+"','"+item.getBeginningbalance()+"','"+item.getMonthaddmoney()+"','"+item.getMonthreturnmoney()+"','"+item.getEndingbalance()+"','"+item.getIsdel()+"','"+item.getParentorg()+"'),");
		}
		Query query1 = sessionFactory.getCurrentSession().createSQLQuery(sql.toString().substring(0, sql.toString().length()-1));
		query1.executeUpdate();
		
	}

	@Override
	public Integer getPersonalLoaninfoCount(Integer groupID) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportPersonlloaninfo s where 1=1 and isdel=0  ");	
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
	public List<ReportPersonlloaninfo> getPersonlloaninfoList(Integer groupID,
			Integer offsize, Integer pagesize) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportPersonlloaninfo s where 1=1 and isdel=0  ");	
		if(groupID!=null)
		{
			hql.append(" and s.groupid = "+groupID+ " order by id ");
		}
		else
			return new ArrayList<ReportPersonlloaninfo>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}


	
	/**
	 *   --------------------------------公司员工借款相邻月份差异比较查询---------------------------------------
	 */
	
	
	@Override
	public int getPersonalloanNearMonthDetailCount(ReportPersonalloanNearMonth entity) {
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportPersonalloanNearMonthDetail s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()))
			{
				hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
			}
			if (Common.notEmpty(entity.getOrgname())) {
				
				hql.append(" and s.orgname = '"+entity.getOrgname()+"' ");
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
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.parentorg like '%--%' ");
			}
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<ReportPersonalloanNearMonth> getPersonalloanNearMonthList(ReportPersonalloanNearMonth entity, Integer offsize,Integer pagesize) {
		List<ReportPersonalloanNearMonth> list = new ArrayList<ReportPersonalloanNearMonth>();
		ReportPersonalloanNearMonth reportPersonalloanNearMonth = new ReportPersonalloanNearMonth();
		if(entity==null)
			return list;
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT s.year,s.month,s.coreorgname, SUM(loantotalmoney), SUM(loansumperson),  ");
		hql.append(" SUM(loansumoverperson), SUM(loantotalovermoney),SUM(monthsumaddperson),SUM(monthsumfinishperson) ");
		hql.append(" FROM report_personalloan_nearMonth s  where 1=1 ");
		
		hql.append("  ");
		if(entity!=null){
			if (Common.notEmpty(entity.getCoreorg()) ) {
				hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
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
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.parentorg like '%--%' ");
			}
			hql.append(" GROUP BY s.year,s.month,s.coreorg ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List<Object> result=query.list();
		if (result != null && result.size()>0) {
			for (Object object : result) {
				Object[] obj = (Object[]) object;
				ReportPersonalloanNearMonth info = new ReportPersonalloanNearMonth();
				info.setYear(obj[0] != null?Integer.valueOf(obj[0].toString())  : null);
				info.setMonth(obj[1] != null?Integer.valueOf(obj[1].toString())  : null);
				info.setCoreorgname(obj[2] != null?obj[2].toString()  : "");
				info.setLoantotalmoney(obj[3] != null?obj[3].toString()  : "");
				info.setLoansumperson(obj[4] != null?Integer.valueOf(obj[4].toString())  : null);
				info.setLoansumoverperson(obj[5] != null?Integer.valueOf(obj[5].toString())  : null);
				info.setLoantotalovermoney(obj[6] != null?obj[6].toString()  : "");
				info.setMonthsumaddperson(obj[7] != null?Integer.valueOf(obj[7].toString())  : null);
				info.setMonthsumfinishperson(obj[8] != null?Integer.valueOf(obj[8].toString())  : null);
				list.add(info);
			}
		}
		hql = new StringBuilder();
		hql.append(" from ReportPersonalloanNearMonthDetail s where 1=1 and isdel=0");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()))
			{
				hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
			}
			if (Common.notEmpty(entity.getOrgname())) {
				
				hql.append(" and s.orgname = '"+entity.getOrgname()+"' ");
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
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.parentorg like '%--%' ");
			}
		}
		query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<ReportPersonalloanNearMonthDetail> detailList = new ArrayList<ReportPersonalloanNearMonthDetail>();
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		detailList=query.list();
		
		if (list !=null && list.size()>0) { // 如果整体情况上无数据 则下面也不显示   如果整体情况上有数据 把下面的数据放到list的第一个里面
			list.get(0).setDetaillist(detailList);	
		}
		
		return list;
	}

	@Override
	public List<ReportPersonlloaninfo> getLastMonthCompareList(ReportPersonalloan entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportPersonlloaninfo s where 1=1 and isdel=0  ");	
		hql.append(" and s.groupid =  ");
		hql.append(" (select r.id from ReportPersonalloan r where isdel=0 ");	
		if(Common.notEmpty(entity.getOrg()) )
		{
			hql.append(" and r.org in( "+ Common.dealinStr( entity.getOrg())+ ") ");
		}
		
		if(Common.notEmpty(entity.getDate()) )
		{
			String date=entity.getDate();
			int year =Integer.parseInt(date.split("-")[0]);
			int month =Integer.parseInt(date.split("-")[1])-1;
			if (month == 0) {
				year=year-1;
				month=12;
			}
			hql.append(" and r.year = "+ year +" ");
			hql.append(" and r.month = "+ month + " ) ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public int getPersonlloaninfoCount(ReportPersonlloaninfo entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select COUNT(result.id) from (SELECT id from report_personlloaninfo  s where 1=1 and isdel=0  ");	
		if(Common.notEmpty(entity.getCoreorg()))
		{
			hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
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
					hql.append(" parentorg like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append(" parentorg like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			hql.append(" and parentorg like '%--%' ");
		}
		hql.append(" GROUP BY year,month,orgname,responsibleperson) as result ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Object> getPersonnalovertimeDetailList(ReportPersonlloaninfo entity, Integer offsize, Integer pagesize) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT s1.*,s2.m2 from    ");	
		hql.append("  (SELECT id,year,month,coreorgname,orgname, responsibleperson,SUM(loanmoney) as m1 from report_personlloaninfo where 1=1 and isdel =0  ");
		if(Common.notEmpty(entity.getCoreorg()))
		{
			hql.append(" and coreorg = '"+entity.getCoreorg()+"' ");
		}
		if(Common.notEmpty(entity.getDate()) )
		{
			String date=entity.getDate();
			hql.append(" and year = "+ Integer.parseInt(date.split("-")[0]) + " ");
			hql.append(" and month = "+Integer.parseInt(date.split("-")[1])+ " ");
		}
		if(Common.notEmpty(entity.getParentorg())){
			hql.append(" and (");
			String [] dd = entity.getParentorg().split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					hql.append(" parentorg like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append(" parentorg like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			hql.append(" and parentorg like '%--%' ");
		}
		hql.append("  GROUP BY year desc ,month desc ,orgname,responsibleperson )  as s1 LEFT JOIN ");	
		hql.append("  (SELECT id, b.responsibleperson,SUM(b.loanmoney )as m2 from report_personlloaninfo b ");	
		hql.append(" where b.loanmonth>1 and isdel = 0 ");	
		if(Common.notEmpty(entity.getCoreorg()))
		{
			hql.append(" and coreorg = '"+entity.getCoreorg()+"' ");
		}
		if(Common.notEmpty(entity.getDate()) )
		{
			String date=entity.getDate();
			hql.append(" and year = "+ Integer.parseInt(date.split("-")[0]) + " ");
			hql.append(" and month = "+Integer.parseInt(date.split("-")[1])+ " ");
		}
		if(Common.notEmpty(entity.getParentorg())){
			hql.append(" and (");
			String [] dd = entity.getParentorg().split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					hql.append(" parentorg like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append(" parentorg like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			hql.append(" and parentorg like '%--%' ");
		}
		hql.append(" GROUP BY year desc,month desc,orgname,b.responsibleperson ) as s2  ON s1.id = s2.id  ");	
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	@Override
	public ReportPersonlloaninfo getPersonlloaninfobyId(Integer id) {
		if(id==null)
			return new ReportPersonlloaninfo();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportPersonlloaninfo s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		ReportPersonlloaninfo a= (ReportPersonlloaninfo) query.uniqueResult();
		return a ;
	}

	
	
	@Override
	public int getcoreComloaninfoCount(ReportPersonlloaninfo entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select COUNT(result.id) from (SELECT id from report_personlloaninfo  s where 1=1 and isdel=0  ");	
		if(Common.notEmpty(entity.getCoreorg()))
		{
			hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
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
					hql.append(" parentorg like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append(" parentorg like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			hql.append(" and parentorg like '%--%' ");
		}
		hql.append(" GROUP BY coreorgname,year,month,orgname) as result ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Object> getcoreComovertimeDetailList(
			ReportPersonlloaninfo entity, Integer offsize, Integer pagesize) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT s1.*,s2.m2 from    ");	
		hql.append("  (SELECT id,groupid,year,month,coreorgname,orgname, responsibleperson,SUM(loanmoney) as m1,coreorg from report_personlloaninfo where 1=1 and isdel = 0 ");
		if(Common.notEmpty(entity.getCoreorg()))
		{
			hql.append(" and coreorg = '"+entity.getCoreorg()+"' ");
		}
		if(Common.notEmpty(entity.getDate()) )
		{
			String date=entity.getDate();
			hql.append(" and year = "+ Integer.parseInt(date.split("-")[0]) + " ");
			hql.append(" and month = "+Integer.parseInt(date.split("-")[1])+ " ");
		}
		if(Common.notEmpty(entity.getParentorg())){
			hql.append(" and (");
			String [] dd = entity.getParentorg().split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					hql.append(" parentorg like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append(" parentorg like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			hql.append(" and parentorg like '%--%' ");
		}
		hql.append("  GROUP BY coreorgname,year,month,orgname )  as s1 LEFT JOIN ");	
		hql.append("  (SELECT b.id, b.responsibleperson,SUM(b.loanmoney )as m2,coreorg from report_personlloaninfo b ");	
		hql.append(" where b.loanmonth>1  ");	
		if(Common.notEmpty(entity.getCoreorg()))
		{
			hql.append(" and coreorg = '"+entity.getCoreorg()+"' ");
		}
		if(Common.notEmpty(entity.getDate()) )
		{
			String date=entity.getDate();
			hql.append(" and year = "+ Integer.parseInt(date.split("-")[0]) + " ");
			hql.append(" and month = "+Integer.parseInt(date.split("-")[1])+ " ");
		}
		if(Common.notEmpty(entity.getParentorg())){
			hql.append(" and (");
			String [] dd = entity.getParentorg().split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					hql.append(" parentorg like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append(" parentorg like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			hql.append(" and parentorg like '%--%' ");
		}
		hql.append(" GROUP BY b.coreorgname,year,month,orgname ) as s2  on s1.id = s2.id  ");	
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	
}
