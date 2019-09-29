package com.softline.dao.report.impl;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.report.IInspectProjectDao;
import com.softline.entity.ReportInspectProject;
import com.softline.entity.ReportInspectProjectOrder;
import com.softline.entity.ReportInspectProjectPlan;
import com.softline.entity.ReportInspectProjectProblem;

@Repository(value = "inspectProjectDao")
/**
 * 
 * @author tch
 *
 */
public class InspectProjectDao implements IInspectProjectDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 数据件数查询
	 * 
	 * @param entity
	 * @return
	 */
	public int getInspectProjectListCount(ReportInspectProject entity) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from ReportInspectProject s where 1=1 and isdel=0 ");
		
		//条件
		getInspectProjectListCondition(entity,hql);
		
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	public List<ReportInspectProject> getInspectProjectList(
			ReportInspectProject entity, Integer offsize, Integer pagesize) {
		if (entity == null) {
			return new ArrayList<ReportInspectProject>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from ReportInspectProject s where 1=1 and isdel=0 ");
		
		//条件
		getInspectProjectListCondition(entity,hql);
		
		hql.append(" order by year desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		if (offsize != null) {
			query.setFirstResult(offsize);
		}
		if (pagesize != null) {
			query.setMaxResults(pagesize);
		}
		return query.list();
	}
	
	public ReportInspectProject inspectProjectSameCheck(
			ReportInspectProject entity) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from ReportInspectProject s where year="+entity.getYear()+" and itemType="+entity.getItemType()+" and compId='"+entity.getCompId()+"'  and isdel=0 ");
		
		if(entity.getId()!=null)
			hql.append(" and id!="+entity.getId()+" ");
		
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (ReportInspectProject)query.uniqueResult();
	}
	
	/**
	 * @param entity
	 * @param hql
	 * 
	 * 修改增加查询条件，查询单位以及其子单位
	 *   by zl
	 */
	private void getInspectProjectListCondition(ReportInspectProject entity,StringBuilder hql){
		if (entity != null) {
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
			
			if (entity.getYear() != null) {
				hql.append(" and s.year = " + entity.getYear() + " ");
			}
			if (Common.notEmpty(entity.getCompId())) {
				if(entity.getCompId().contains(",")){
					hql.append(" and s.compId in( "+Common.dealinStr(entity.getCompId())+ ") ");
				}else{
					hql.append(" and s.compId in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
							entity.getCompId()+"'),h.vcOrganId)>0)");
				}
			}
			if (entity.getInspectType() != null) {
				hql.append(" and s.inspectType = " + entity.getInspectType() + " ");
			}
			if (entity.getItemType() != null) {
				hql.append(" and s.itemType = " + entity.getItemType() + " ");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (Common.notEmpty(entity.getItemName())) {
				hql.append(" and s.itemName like '%" + entity.getItemName() + "%' ");
			}		
			if (entity.getIsOrder()!=null) {
				if(entity.getIsOrder()==0)
					hql.append(" and s.isOrder<=0 ");
				else
					hql.append(" and s.isOrder>0 ");
			}
		}
	}
	
	public ReportInspectProject getInspectProjectById(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from ReportInspectProject s where id="+id+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (ReportInspectProject)query.uniqueResult();
	}
	
	public List<ReportInspectProjectProblem> getInspectProjectProblem(Integer inspectProjectId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from ReportInspectProjectProblem where inspectProjectId="+inspectProjectId+" and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}
	
	public ReportInspectProjectProblem getReportInspectProjectProblemById(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from ReportInspectProjectProblem s where id="+id+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (ReportInspectProjectProblem)query.uniqueResult();
	}
	
	public ReportInspectProjectOrder getReportInspectProjectOrderById(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from ReportInspectProjectOrder s where id="+id+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (ReportInspectProjectOrder)query.uniqueResult();
	}
	
	public List<ReportInspectProjectOrder> getInspectProjectOrder(Integer inspect_project_id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from ReportInspectProjectOrder where inspectProjectId="+inspect_project_id+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}
	
	@Override
	public int getInspectProjectProblemListCount(ReportInspectProjectProblem entity, String parentOrg, Boolean isChangeTimeout){
		if (entity == null) {
			return 0;
		}
		
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT count(s.id) FROM ReportInspectProject c, ReportInspectProjectProblem s ");
		buildProjectProblemWhere(entity, parentOrg, isChangeTimeout, hql);
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	private void buildProjectProblemWhere(
			ReportInspectProjectProblem entity, String parentOrg, Boolean isChangeTimeout, StringBuilder hql){
		hql.append(" WHERE c.id = s.inspectProjectId AND c.isDel = 0 AND s.isDel = 0 ");
		
		if(entity.getYear() != null){
			hql.append(" AND c.year = ").append(entity.getYear());
		}
		if(entity.getInspectType() != null && entity.getInspectType() != -1){
			hql.append(" AND c.inspectType = ").append(entity.getInspectType());
		}
		if(StringUtils.isNotBlank(entity.getCompId())){
			if(entity.getCompId().contains(",")){
				hql.append(" and c.compId in( "+Common.dealinStr(entity.getCompId())+ ") ");
			}else{
				hql.append(" and c.compId in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
						entity.getCompId()+"'),h.vcOrganId)>0)");
			}
		}
		if(StringUtils.isNotBlank(entity.getItemName())){
			hql.append(" AND c.itemName LIKE '%").append(entity.getItemName()).append("%' ");
		}
		if(StringUtils.isNotBlank(parentOrg)){
			hql.append(" and (");
			String [] dd = parentOrg.split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					hql.append(" c.authOrg like '%-"+dd[i]+ "-%' )");
				}else{
					hql.append(" c.authOrg like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			hql.append(" and c.authOrg like '%--%' ");
		}
		
		if(entity.getIsChange() != null && entity.getIsChange() != -1){
			hql.append(" AND s.isChange = ").append(entity.getIsChange());
		}
		if(isChangeTimeout){
			hql.append(" AND s.isChange = 1 ");
			if(entity.getIsFinish() != null){
				hql.append(" AND s.isFinish = ").append(entity.getIsFinish()).append(" ");
			}
			if(entity.getIsChangeTimeout() != null ){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String nowStr = dateFormat.format(new Date());
				if(entity.getIsChangeTimeout() == 0){
					hql.append(" AND ((s.isFinish = 0 AND (s.changeLastTime IS NULL OR s.changeLastTime = '')) OR (s.isFinish = 0 AND str_to_date(s.changeLastTime,'%Y-%m-%d') > '").append(nowStr).append("') OR s.isFinish = 1)");
				}
				if(entity.getIsChangeTimeout() == 1){
					hql.append(" AND s.changeLastTime IS NOT NULL AND s.changeLastTime <> '' AND (s.isFinish = 0 AND str_to_date(s.changeLastTime,'%Y-%m-%d') <= '").append(nowStr).append("')");
				}
			}
		}
	}

	@Override
	public List<ReportInspectProjectProblem> getInspectProjectProblemList(
			ReportInspectProjectProblem entity, String parentOrg, Boolean isChangeTimeout, Integer offsize,
			Integer pagesize) {
		
		if (entity == null) {
			return new ArrayList<ReportInspectProjectProblem>();
		}
		
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT c, s FROM ReportInspectProject c, ReportInspectProjectProblem s");
		buildProjectProblemWhere(entity, parentOrg, isChangeTimeout, hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (offsize != null) {
			query.setFirstResult(offsize);
		}
		if (pagesize != null) {
			query.setMaxResults(pagesize);
		}
		List<?> list = query.list();
		List<ReportInspectProjectProblem> data = 
				new ArrayList<ReportInspectProjectProblem>(list.size());
		
		for(Object i : list){
			Object[]  items = (Object[])i;
			ReportInspectProject c = (ReportInspectProject)items[0];
			ReportInspectProjectProblem s = (ReportInspectProjectProblem)items[1];
			s.setYear(c.getYear());
			s.setCompId(c.getCompId());
			s.setCompName(c.getCompName());
			s.setItemName(c.getItemName());
			s.setInspectType(c.getInspectType());
			s.setStatus(c.getStatus());
			data.add(s);
		}
		
		return data;
	}

	@Override
	public int getInspectProjectChangeCount(ReportInspectProject entity) {
		if (entity == null){
			return 0;
		}
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT count(s.id) FROM ReportInspectProject s"); 
		buildProjectChanageWhere(entity, hql);
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return ((Long)query.uniqueResult()).intValue();
	}
	
	private void buildProjectChanageWhere(ReportInspectProject entity, StringBuilder hql){
		hql.append(" WHERE s.isDel=0 AND s.status IN (80043, 80044, 80045) ");
		
		if (entity.getYear() != null) {
			hql.append(" AND s.year = ").append(entity.getYear());
		}
		if (entity.getInspectType() != null) {
			hql.append(" AND s.inspectType = ").append(entity.getInspectType());
		}
		if(StringUtils.isNotBlank(entity.getCompId())){
			if(entity.getCompId().contains(",")){
				hql.append(" and s.compId in( "+Common.dealinStr(entity.getCompId())+ ") ");
			}else{
				hql.append(" and s.compId in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
						entity.getCompId()+"'),h.vcOrganId)>0)");
			}
		}
		if(entity.getStatus() != null){
			hql.append(" AND s.status = ").append(entity.getStatus());
		}
		if(StringUtils.isNotBlank(entity.getItemName())){
			hql.append(" AND s.itemName LIKE '").append("%" + entity.getItemName() + "%").append("'");
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

	@Override
	public List<ReportInspectProject> getInspectProjectChangeList(
			ReportInspectProject entity, Integer offsize, Integer pagesize) {
		if (entity == null){
			return Collections.emptyList();
		}
		
		StringBuilder hql = new StringBuilder();
		hql.append("FROM ReportInspectProject s"); 
		buildProjectChanageWhere(entity, hql);
		hql.append(" ORDER BY status, year ASC");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (offsize != null) {
			query.setFirstResult(offsize);
		}
		if (pagesize != null) {
			query.setMaxResults(pagesize);
		}
		return query.list();
	}

	@Override
	public Integer getInspectProjectProblemStatisticListCount(Integer year,
			Integer inspectType, String compId, String parentOrg) {

		StringBuilder sql = new StringBuilder(200);
		sql.append("SELECT COUNT(*) FROM ");
		sql.append("(SELECT year, inspectType, compId, compName, group_concat(itemType) itemTypes, group_concat(itemTypeCount) itemTypeCounts ");
		sql.append(" FROM ");
		sql.append(" (SELECT t1.year, t1.inspectType, t1.compId, t1.compName, t1.itemType, count(t2.id) itemTypeCount");
		sql.append(" FROM (report_inspect_project t1 left join report_inspect_project_problem t2 on t1.id = t2.inspectProjectId) left join hh_organInfo_tree_relation hort on t1.compId = hort.nNodeID ");
		sql.append(" WHERE t1.isDel = 0 AND t2.isDel = 0");
		if(year != null){
			sql.append(" AND t1.year = ").append(year);
		}
		if(inspectType != null){
			sql.append(" AND t1.inspectType = ").append(inspectType);
		}
		if(StringUtils.isNotBlank(compId)){
			if(compId.contains(",")){
				sql.append(" and t1.compId in ("+Common.dealinStr(compId)+") ");
			}else{
				sql.append(" and t1.compId in ( select nNodeID from hh_organInfo_tree_relation where locate((select vcOrganID from hh_organInfo_tree_relation where nNodeID ='"+
						compId+"'),vcOrganID)>0)");
			}
		}
		if(Common.notEmpty(parentOrg)){
			sql.append(" and (");
			String [] dd = parentOrg.split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					sql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' )");
				}else{
					sql.append("  hort.vcOrganID like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			sql.append(" and  hort.vcOrganID like '%--%' ");
		}
		sql.append(" GROUP BY t1.year,t1.inspectType, t1.compId, t1.compName, t1.itemType) m");
		sql.append(" GROUP BY year, inspectType, compId, compName) t");
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return ((BigInteger)query.uniqueResult()).intValue();
	}

	@Override
	public List<Object[]> getInspectProjectProblemStatisticList(Integer year,
			Integer inspectType, String compId, String parentOrg, Integer offset, Integer pageSize) {
		
		StringBuilder sql = new StringBuilder(200);
		
		sql.append("SELECT year, inspectType, compId, compName, group_concat(itemType) itemTypes, group_concat(itemTypeCount) itemTypeCounts ");
		sql.append(" FROM ");
		sql.append(" (SELECT t1.year, t1.inspectType, t1.compId, t1.compName, t1.itemType, count(t2.id) itemTypeCount");
		sql.append(" FROM (report_inspect_project t1 left join report_inspect_project_problem t2 on t1.id = t2.inspectProjectId) left join hh_organInfo_tree_relation hort on t1.compId = hort.nNodeID ");
		sql.append(" WHERE t1.isDel = 0 AND t2.isDel = 0 ");
		if(year != null){
			sql.append(" AND t1.year = ").append(year);
		}
		if(inspectType != null){
			sql.append(" AND t1.inspectType = ").append(inspectType);
		}
		if(StringUtils.isNotBlank(compId)){
			if(compId.contains(",")){
				sql.append(" and t1.compId in ("+Common.dealinStr(compId)+") ");
			}else{
				sql.append(" and t1.compId in ( select nNodeID from hh_organInfo_tree_relation where locate((select vcOrganID from hh_organInfo_tree_relation where nNodeID ='"+
						compId+"'),vcOrganID)>0)");
			}
		}
		if(Common.notEmpty(parentOrg)){
			sql.append(" and (");
			String [] dd = parentOrg.split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					sql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' )");
				}else{
					sql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			sql.append(" and hort.vcOrganID like '%--%' ");
		}
		
		sql.append(" GROUP BY t1.year,t1.inspectType, t1.compId, t1.compName, t1.itemType) m");
		sql.append(" GROUP BY year, inspectType, compId, compName");
		sql.append(" ORDER BY year, inspectType, compId");
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		if (offset != null) {
			query.setFirstResult(offset);
		}
		if (pageSize != null) {
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

	@Override
	public ReportInspectProject getInspectProjectByStatistic(Integer year,
			Integer inspectType, String compId, Integer itemType) {
		 
		String hql = " FROM ReportInspectProject WHERE year= :year AND inspectType = :inspectType AND compId = :compId AND itemType = :itemType";
		Query query = sessionFactory.
				getCurrentSession().
				createQuery(hql).
				setInteger("year", year).
				setInteger("inspectType", inspectType).
				setString("compId", compId).
				setInteger("itemType", itemType);
		List<ReportInspectProject> list = query.list();
		return list.isEmpty()? new ReportInspectProject() : list.get(0);
	}

	@Override
	public List<Object[]> getInspectProjectProblemCount(
			List<Integer> inspectProjectIds) {
		
		String sql = "SELECT inspectProjectId, SUM(total) total, SUM(changeTotal) changeTotal FROM " +
				" (SELECT inspectProjectId, COUNT(id) total, 0 changeTotal FROM report_inspect_project_problem " +
				"    WHERE isDel = 0 AND inspectProjectId IN (:ids1) GROUP BY inspectProjectId " +
				" UNION ALL " +
				" SELECT inspectProjectId, 0 changeTotal, COUNT(id) total FROM report_inspect_project_problem " +
				"    WHERE isDel = 0 AND inspectProjectId IN (:ids2) AND isChange = 1 GROUP BY inspectProjectId ) m" +
				" GROUP BY  inspectProjectId ";
		
		Query query = sessionFactory.
				getCurrentSession().
				createSQLQuery(sql).
				setParameterList("ids1", inspectProjectIds).
				setParameterList("ids2", inspectProjectIds);
		return query.list();
	}

	@Override
	public Integer getInspectProjectLeaderCheckCount(Integer year,
			Integer inspectType, String compId, String parentOrg) {
		
		StringBuilder sql = new StringBuilder();
	
		sql.append("SELECT COUNT(0) FROM ");
		sql.append(" (SELECT year, inspectType, compId, compName, belongCompId, belongCompName ");
		sql.append(" FROM ");
		sql.append(" (SELECT t1.year, t1.inspectType, t1.compId, t1.compName, t1.belongCompId, t1.belongCompName, COUNT(t2.id) total, 0 changeTotal, Min(t1.status) minStatus, Max(t1.status) maxStatus ");
		sql.append(" FROM (report_inspect_project t1 left join report_inspect_project_problem t2  on t1.id = t2.inspectProjectId) left join hh_organInfo_tree_relation hort on hort.nNodeID = t1.compId");
		sql.append(" WHERE t1.isDel = 0 AND t2.isDel = 0 ");
		buildInspectProjectLeaderCheckCondition(sql, year, inspectType, compId, parentOrg);
		sql.append(" GROUP BY t1.year, t1.inspectType, t1.compId, t1.compName, t1.belongCompId, t1.belongCompName ");
		sql.append(" UNION ALL ");
		sql.append(" SELECT t1.year, t1.inspectType, t1.compId, t1.compName, t1.belongCompId, t1.belongCompName, 0 total, COUNT(t2.id) changeTotal, 0 minStatus, 0 maxStatus ");
		sql.append(" FROM (report_inspect_project t1 left join report_inspect_project_problem t2  on t1.id = t2.inspectProjectId) left join hh_organInfo_tree_relation hort on hort.nNodeID = t1.compId");
		sql.append(" WHERE t1.isDel = 0 AND t2.isDel = 0 AND t2.isChange = 1 ");
		buildInspectProjectLeaderCheckCondition(sql, year, inspectType, compId, parentOrg);
		sql.append(" GROUP BY t1.year, t1.inspectType, t1.compId, t1.compName, t1.belongCompId, t1.belongCompName) m ");
		sql.append(" GROUP BY year, inspectType, compId, compName, belongCompId, belongCompName) n ");
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return ((BigInteger)query.uniqueResult()).intValue();
	}

	@Override
	public List<Object[]> getInspectProjectLeaderCheckList(Integer year,
			Integer inspectType, String compId, String parentOrg, Integer offset, Integer pageSize) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT year, inspectType, '' inspectTypeName, compId, compName, belongCompId, belongCompName, SUM(total) total, SUM(changeTotal) changeTotal, CASE WHEN SUM(minStatus) = 80042 AND SUM(maxStatus) = 80042 THEN 1 ELSE 0 END isCheck ");
		sql.append(" FROM ");
		sql.append(" (SELECT t1.year, t1.inspectType, t1.compId, t1.compName, t1.belongCompId, t1.belongCompName, COUNT(t2.id) total, 0 changeTotal, Min(t1.status) minStatus, Max(t1.status) maxStatus ");
		sql.append(" FROM (report_inspect_project t1 left join report_inspect_project_problem t2  on t1.id = t2.inspectProjectId) left join hh_organInfo_tree_relation hort on hort.nNodeID = t1.compId ");
		sql.append(" WHERE t1.isDel = 0 AND t2.isDel = 0 ");
		buildInspectProjectLeaderCheckCondition(sql, year, inspectType, compId, parentOrg);
		sql.append(" GROUP BY t1.year, t1.inspectType, t1.compId, t1.compName, t1.belongCompId, t1.belongCompName ");
		sql.append(" UNION ALL ");
		sql.append(" SELECT t1.year, t1.inspectType, t1.compId, t1.compName, t1.belongCompId, t1.belongCompName, 0 total, COUNT(t2.id) changeTotal, 0 minStatus, 0 maxStatus ");
		sql.append(" FROM (report_inspect_project t1 left join report_inspect_project_problem t2  on t1.id = t2.inspectProjectId) left join hh_organInfo_tree_relation hort on hort.nNodeID = t1.compId ");
		sql.append(" WHERE t1.isDel = 0 AND t2.isDel = 0 AND t2.isChange = 1 ");
		buildInspectProjectLeaderCheckCondition(sql, year, inspectType, compId, parentOrg);
		sql.append(" GROUP BY t1.year, t1.inspectType, t1.compId, t1.compName, t1.belongCompId, t1.belongCompName) m ");
		sql.append(" GROUP BY year, inspectType, compId, compName, belongCompId, belongCompName ");
		sql.append(" ORDER BY isCheck DESC, year ASC ");
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		if (offset != null) {
			query.setFirstResult(offset);
		}
		if (pageSize != null) {
			query.setMaxResults(pageSize);
		}
		return query.list();
	}
	
	private void buildInspectProjectLeaderCheckCondition(StringBuilder sql, Integer year, Integer inspectType, String compId, String parentOrg){
		if(year != null){
			sql.append(" AND t1.year = ").append(year).append(" ");
		}
		if(inspectType != null){
			sql.append(" AND t1.inspectType = ").append(inspectType).append(" ");
		}
		if(StringUtils.isNotBlank(compId)){
			sql.append(" and t1.compId in ( select nNodeID from hh_organInfo_tree_relation where locate((select vcOrganID from hh_organInfo_tree_relation where nNodeID ='"+
					compId+"'),vcOrganID)>0)");
		}
		if(Common.notEmpty(parentOrg)){
			sql.append(" and (");
			String [] dd = parentOrg.split(",");
			for(int i = 0; i < dd.length;i++){
				if(i == (dd.length -1)){
					sql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' )");
				}else{
					sql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' or ");
				}
			}
		}else{
			sql.append(" and hort.vcOrganID like '%--%' ");
		}
	}
	public ReportInspectProjectPlan getReportInspectProjectPlan(ReportInspectProjectPlan entity) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from ReportInspectProjectPlan s where year="+entity.getYear()+" and inspectType="+entity.getInspectType()+"  ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		query.setFirstResult(0);
		query.setMaxResults(1);
		
		return (ReportInspectProjectPlan)query.uniqueResult();
	}

	@Override
	public List<ReportInspectProject> getshixiangQry1(
			ReportInspectProject criminal1) {
		StringBuilder hql=new StringBuilder();
		hql.append("  from  ReportInspectProject  ");
		Query query=sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<ReportInspectProjectProblem> getshixiangQrypro(
			ReportInspectProjectProblem reportInspectProjectProblem) {
		StringBuilder hql=new StringBuilder();
		hql.append(" from ReportInspectProjectProblem ");
		Query query =sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
}
