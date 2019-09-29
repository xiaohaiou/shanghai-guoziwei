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
import com.softline.dao.report.IReportPersonalLoanNewDao;
import com.softline.entity.ReportPersonalloan;
import com.softline.entity.ReportPersonalloanNearMonth;
import com.softline.entity.ReportPersonalloanNearweek;
import com.softline.entity.ReportPersonalloanNearweekDetail;
import com.softline.entity.ReportPersonalloanNew;
import com.softline.entity.ReportPersonlloaninfo;
import com.softline.entity.ReportPersonlloaninfoNew;

@Repository(value = "reportPersonalLoanNewDao")
public class ReportPersonalLoanNewDao implements IReportPersonalLoanNewDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ReportPersonalloanNew getPersonalLoanNewbyID(Integer id) {
		
		if(id==null)
			return new ReportPersonalloanNew();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportPersonalloanNew s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		ReportPersonalloanNew a= (ReportPersonalloanNew) query.uniqueResult();
		
		hql = new StringBuilder();
		hql.append(" from ReportPersonlloaninfoNew s where 1=1 and isdel=0 and groupid="+id);
		query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<ReportPersonlloaninfoNew> b=query.list();
		a.setInfolist(b);
		return a;
	}
//第一个 分页
	@Override
	public int getPersonalloanNewListCount(ReportPersonalloanNew entity,String weekStart,String weekEnd) {
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportPersonalloanNew s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(weekStart != null && !weekStart.equals("")&&weekEnd != null && !weekEnd.equals("")){
				hql.append("and week BETWEEN '"+weekStart+"' And '"+weekEnd+"' ");
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
//第二个
	@Override
	public List<ReportPersonalloanNew> getPersonalloanNewList(
			ReportPersonalloanNew entity, Integer offsize, Integer pagesize,String weekStart,String weekEnd) {
		if(entity==null)
			return new ArrayList<ReportPersonalloanNew>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportPersonalloanNew s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			
			if(weekStart != null && !weekStart.equals("")&&weekEnd != null && !weekEnd.equals("")){
				hql.append("and week BETWEEN '"+weekStart+"' And '"+weekEnd+"' ");
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
	public boolean savePersonalloanNewRepeatCheck(ReportPersonalloanNew entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportPersonalloanNew s where 1=1 and isdel=0  ");
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

	@Override
	public boolean checkCanEdit(ReportPersonalloanNew entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportPersonalloanNew s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	public boolean deletePersonalloaninfoNew(String groupID) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder hql = new StringBuilder();
		hql.append("update report_personlloaninfo_new set isdel=1  ");	
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
	public Integer getPersonalLoaninfoNewCount(Integer groupID) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportPersonlloaninfoNew s where 1=1 and isdel=0  ");	
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
	public List<ReportPersonlloaninfoNew> getPersonlloaninfoNewList(
			Integer groupID, Integer offsize, Integer pagesize) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportPersonlloaninfoNew s where 1=1 and isdel=0  ");	
		if(groupID!=null)
		{
			hql.append(" and s.groupid = "+groupID+ " order by id ");
		}
		else
			return new ArrayList<ReportPersonlloaninfoNew>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	@Override
	public List<ReportPersonlloaninfoNew> getPersonlloaninfoNewList(
			Integer year, Integer week, String org) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportPersonlloaninfoNew s where 1=1 and isdel=0  ");	
		if(Common.notEmpty(org))
		{
			hql.append(" and s.org = '"+org+ "' ");
		}
		if(year!=null)
		{
			hql.append(" and s.year = "+year+ " ");
		}
		if(week!=null)
		{
			hql.append(" and s.week = "+week+ " ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
//	daochu
	@Override
	public List<ReportPersonlloaninfoNew> getPersonlloaninfoNewList(ReportPersonlloaninfoNew entity,String weekStart,String weekEnd){
		StringBuilder  hql = new StringBuilder();
		hql.append("from ReportPersonlloaninfoNew s where 1=1 and isdel=0  ");	
		if(weekStart != null && !weekStart.equals("")&&weekEnd != null && !weekEnd.equals("")){
			hql.append("and week BETWEEN '"+weekStart+"' And '"+weekEnd+"' ");
		}
		
		if(Common.notEmpty(entity.getOrg()))
		{
			hql.append(" and s.org in ("+Common.dealinStr( entity.getOrg())+ ") ");
		}
		if(entity.getYear()!=null)
		{
			hql.append(" and s.year = "+entity.getYear()+ " ");
		}
		if(entity.getWeek()!=null)
		{
			hql.append(" and s.week = "+entity.getWeek()+ " ");
		}
		if(entity.getStatus()!=null){
			hql.append(" and s.status = "+entity.getStatus()+ " ");
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
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public ReportPersonalloanNew getReportPersonalloanNew(Integer year,
			Integer week, String org) {
		if(year !=null && week !=null && Common.notEmpty(org)){
			StringBuilder  hql = new StringBuilder();
			hql.append("from ReportPersonalloanNew s where 1=1 and isdel=0  ");	
			hql.append(" and s.org = '"+org+ "' ");
			hql.append(" and s.year = "+year+ " ");
			hql.append(" and s.week = "+week+ " ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			ReportPersonalloanNew a = (ReportPersonalloanNew) query.uniqueResult();
			if(a != null){
				hql = new StringBuilder();
				hql.append(" from ReportPersonlloaninfoNew s where 1=1 and isdel=0 and groupid="+a.getId());
				query = sessionFactory.getCurrentSession().createQuery(hql.toString());
				List<ReportPersonlloaninfoNew> b=query.list();
				a.setInfolist(b);
			}
			return a;
		}else{
			return null;
		}
	}

	@Override
	public Boolean isNewAddPersonInCurrentWeek(Integer year, Integer week,
			String org, String respersonAccount) {
		Boolean result = false;
		if(year !=null && week != null && Common.notEmpty(org) && Common.notEmpty(respersonAccount)){
			StringBuffer hql = new StringBuffer();
			hql.append(" from ReportPersonlloaninfoNew s where 1=1 and s.isdel=0 and s.year=").append(year);
			hql.append(" and s.week = ").append(week);
			hql.append(" and s.org = '").append(org).append("'");
			hql.append(" and s.respersonAccount = '").append(respersonAccount).append("'");
			//审核通过状态
			hql.append(" and s.status = ").append(Base.examstatus_4);
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			List<ReportPersonlloaninfoNew> b=query.list();
			if(b.size()<=0){
				result = true;
			}
		}
		return result;
	}

	@Override
	public String[] getPreWeekEndingBalance(Integer year, Integer week,
			String org, String respersonAccount) {
		String[] result = {"0","0"};
		if(year !=null && week != null && Common.notEmpty(org) && Common.notEmpty(respersonAccount)){
			StringBuffer hql = new StringBuffer();
			hql.append("select sum(s.loanmoney)-sum(s.totalBackMoney),sum(s.loanmoney) from ReportPersonlloaninfoNew s where 1=1 and s.isdel=0 and s.year=").append(year);
			hql.append(" and s.week = ").append(week);
			hql.append(" and s.org = '").append(org).append("'");
			hql.append(" and s.respersonAccount = '").append(respersonAccount).append("'");
			//审核通过状态
			hql.append(" and s.status = ").append(Base.examstatus_4);
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			Object[]  a =  (Object[]) query.uniqueResult();
			if(a !=null){
				result[0] = a[0]==null?"0":(String)a[0];
				result[1] = a[1]==null?"0":(String)a[1];
			}
		}
		return result;
	}

	@Override
	public void deleteNearweekAndDetail(Integer parentId) {
		StringBuilder hql = new StringBuilder();
		hql.append("update report_personalloan_nearweek t set t.isdel=1  where t.isdel = 0 and t.groupid=").append(parentId);	
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		StringBuilder hql1 = new StringBuilder();
		hql1.append("update report_personalloan_nearweek_detail t set t.isdel=1  where t.isdel = 0 and t.groupid=").append(parentId);	
		Query query1 = sessionFactory.getCurrentSession().createSQLQuery(hql1.toString());
		query.executeUpdate();
		query1.executeUpdate();
	}

	@Override
	public int getPersonalloanNearWeekDetailCount(
			ReportPersonalloanNearweek entity) {
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportPersonalloanNearweekDetail s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()))
			{
				hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
				hql.append(" and s.coreorg is not null ");
				hql.append(" and s.authOrg like concat('%',(select vcOrganId from HhOrganInfoTreeRelation where id.nnodeId = '"+entity.getCoreorg()+"'),'%')");
			}
			if (Common.notEmpty(entity.getOrg())) {
				
				hql.append(" and s.org = '"+entity.getOrg()+"' ");
			}
			
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
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
	public List<ReportPersonalloanNearweekDetail> getPersonalloanNearWeekDetailList(
			ReportPersonalloanNearweek entity, Integer offsize, Integer pagesize) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportPersonalloanNearweekDetail s where 1=1 and s.isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreorg()))
			{
				//hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
				hql.append(" and s.coreorg is not null ");
				hql.append(" and s.authOrg like concat('%',(select vcOrganId from HhOrganInfoTreeRelation where id.nnodeId = '"+entity.getCoreorg()+"'),'%')");
			}
			if (Common.notEmpty(entity.getOrg())) {
				
				hql.append(" and s.org = '"+entity.getOrg()+"' ");
			}
			
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
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
		if(offsize !=null && pagesize!=null){
			query.setFirstResult(offsize);
			query.setMaxResults(pagesize);
		}
		
		return query.list();
	}

	@Override
	public List<ReportPersonalloanNearweek> getPersonalloanNearWeekList(
			ReportPersonalloanNearweek entity){
		List<ReportPersonalloanNearweek> list = new ArrayList<ReportPersonalloanNearweek>();
		ReportPersonalloanNearweek reportPersonalloanNearweek = new ReportPersonalloanNearweek();
		if(entity==null)
			return list;
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT s.year,s.week,s.coreorgname,SUM(loantotalmoney),SUM(loansumperson),");
		hql.append(" SUM(loansumoverperson), SUM(loantotalovermoney),SUM(monthsumaddperson),SUM(monthsumfinishperson) ");
		hql.append(" FROM report_personalloan_nearweek s left join hh_organInfo_tree_relation hotr on s.org = hotr.nNodeID where 1=1 and s.isDel = 0");
		hql.append("  ");
		if(entity!=null){
			if (Common.notEmpty(entity.getCoreorg()) ) {
				//hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
				hql.append(" and s.coreorg is not null ");
				hql.append(" and hotr.vcOrganID like concat('%',(select vcOrganID from hh_organInfo_tree_relation where nNodeID = '"+entity.getCoreorg()+"'),'%')");
			}
			if(entity.getYear() != null)
			{
				hql.append(" and s.year = "+ entity.getYear() + " ");
			}
			if(entity.getWeek() != null){
				hql.append(" and s.week = "+entity.getWeek()+ " ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and hotr.vcOrganID like '%--%' ");
			}
			hql.append(" GROUP BY s.year,s.week,s.coreorg ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List<Object> result=query.list();
		if (result != null && result.size()>0) {
			for (Object object : result) {
				Object[] obj = (Object[]) object;
				ReportPersonalloanNearweek info = new ReportPersonalloanNearweek();
				info.setYear(obj[0] != null?Integer.valueOf(obj[0].toString())  : null);
				info.setWeek(obj[1] != null?Integer.valueOf(obj[1].toString())  : null);
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
		return list;
	}

	@Override
	public int getPersonlloaninfoNewCount(ReportPersonlloaninfoNew entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT count(t.id) from (report_personlloaninfo_new  s left join report_personalloan_new t on s.groupid = t.id) left join hh_organInfo_tree_relation hotr on s.org = hotr.nNodeID  where 1=1 and s.isdel=0  ");	
		if(Common.notEmpty(entity.getCoreorg()))
		{
			//hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
			hql.append(" and s.coreorg is not null ");
			hql.append(" and hotr.vcOrganID like concat('%',(select vcOrganID from hh_organInfo_tree_relation where nNodeID = '"+entity.getCoreorg()+"'),'%')");
		}
		if(entity.getYear() != null)
		{
			hql.append(" and s.year = "+ entity.getYear() + " ");
		}
		if(entity.getWeek() != null){
			hql.append(" and s.week = "+entity.getWeek()+ " ");
		}
		hql.append(" and t.status = "+Base.examstatus_4+ " ");
		if(Common.notEmpty(entity.getParentorg())){
			hql.append(" and (");
			String [] dd = entity.getParentorg().split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append(" hotr.vcOrganID like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			hql.append(" and hotr.vcOrganID like '%--%' ");
		}
		
		hql.append(" and s.loanmonth > 1");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<ReportPersonlloaninfoNew> getPersonnalovertimeDetailList(
			ReportPersonlloaninfoNew entity, Integer offsize, Integer pagesize) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportPersonlloaninfoNew s where s.isdel = 0");
		if(Common.notEmpty(entity.getCoreorg()))
		{
			//hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
			hql.append(" and s.coreorg is not null ");
			hql.append(" and s.authOrg like concat('%',(select vcOrganId from HhOrganInfoTreeRelation where id.nnodeId = '"+entity.getCoreorg()+"'),'%')");
		}
		if(entity.getYear() != null)
		{
			hql.append(" and s.year = "+ entity.getYear() + " ");
		}
		if(entity.getWeek() != null){
			hql.append(" and s.week = "+entity.getWeek()+ " ");
		}
		//审核通过状态
		hql.append(" and s.status = ").append(Base.examstatus_4);
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
		
		hql.append(" and s.loanmonth > 1");	
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	@Override
	public int getcoreComloaninfoCount(ReportPersonlloaninfoNew entity,String yearStart,String yearEnd) {
		StringBuilder  hql = new StringBuilder();
		hql.append("SELECT count(s.id) from report_personalloan_nearweek  s  left join hh_organInfo_tree_relation hort on s.org = hort.nNodeID where 1=1 and s.isdel=0  ");	
		if(Common.notEmpty(entity.getCoreorg()))
		{
			//hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
			hql.append(" and s.coreorg is not null ");
			hql.append(" and hort.vcOrganID like concat('%',(select vcOrganID from hh_organInfo_tree_relation where nNodeID = '"+entity.getCoreorg()+"'),'%')");
		}
		if(entity.getYear() != null)
		{
			hql.append(" and s.year = "+ entity.getYear() + " ");
		}
		if(yearStart != null && !yearStart.equals("")&&yearEnd != null && !yearEnd.equals("")){
			hql.append("and year BETWEEN '"+yearStart+"' And '"+yearEnd+"' ");
		}
		if(entity.getWeek() != null){
			hql.append(" and s.week = "+entity.getWeek()+ " ");
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
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
//个人借款信息汇总查询
	@Override
	public List<ReportPersonalloanNearweek> getcoreComovertimeDetailList(
			ReportPersonlloaninfoNew entity, Integer offsize, Integer pagesize,String yearStart,String yearEnd) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportPersonalloanNearweek s where s.isdel = 0");
		
//		时间段查询语句
		if(yearStart != null && !yearStart.equals("")&&yearEnd != null && !yearEnd.equals("")){
			hql.append("and year BETWEEN '"+yearStart+"' And '"+yearEnd+"' ");
		}
		
		if(Common.notEmpty(entity.getCoreorg()))
		{
			//hql.append(" and s.coreorg = '"+entity.getCoreorg()+"' ");
			hql.append(" and s.coreorg is not null ");
			hql.append(" and s.authOrg like concat('%',(select vcOrganId from HhOrganInfoTreeRelation where id.nnodeId = '"+entity.getCoreorg()+"'),'%')");			
		}
		if(entity.getYear() != null)
		{
			hql.append(" and s.year = "+ entity.getYear() + " ");
		}
		if(entity.getWeek() != null){
			hql.append(" and s.week = "+entity.getWeek()+ " ");
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
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

}
