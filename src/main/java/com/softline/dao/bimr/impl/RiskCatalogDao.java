package com.softline.dao.bimr.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.softline.dao.bimr.IRiskCatalogDao;
import com.softline.entity.bimr.BimrRiskCatalog;

/**
 * 实现风险目录数据操作
 * 
 * 更新导入表
 * 
 * UPDATE DIM_LIST_MANAGE_RISK t1,
 * (SELECT * FROM DIM_LIST_MANAGE_RISK) t2
 * SET t1.parent_id = t2.id
 * WHERE t1.Risk_parentId = t2.Risk_id
 * 
 * @author liu
 */
@Repository("riskCatalogDao")
public class RiskCatalogDao implements IRiskCatalogDao {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public Integer getRiskCatalogListCount(String name, Integer status) {
		StringBuilder sql = new StringBuilder(200);
		sql.append("SELECT COUNT(id) FROM ");
		buildRiskCatalogListFromSql(sql);
		buildRiskCatalogCondition(sql, name, status);
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return ((BigInteger)query.uniqueResult()).intValue();
	}
	
	private void buildRiskCatalogListFromSql(StringBuilder sql){
		sql.append(" (SELECT t2.id id, t1.id id1, t1.name nm1, t2.id id2, t2.name nm2, '' id3, '' nm3, t2.status status, t2.create_person_name, t2.submit_audit_person_name, t2.audit_person_name, t1.define def1, t2.define def2, '' def3 ");
		sql.append(" FROM (SELECT * FROM DIM_LIST_MANAGE_RISK WHERE level = 1 AND isDel = 0) t1, ");
		sql.append(" (SELECT * FROM DIM_LIST_MANAGE_RISK WHERE level = 2 AND isDel = 0) t2 ");
		sql.append(" WHERE t1.id = t2.parent_id ");
		sql.append(" UNION ALL ");
		sql.append(" SELECT t3.id id, t1.id id1, t1.name nm1, t2.id id2, t2.name nm2, t3.id id3, t3.name nm3, t3.status status, t3.create_person_name, t3.submit_audit_person_name, t3.audit_person_name, t1.define def1, t2.define def2, t3.define def3 ");
		sql.append(" FROM (SELECT * FROM DIM_LIST_MANAGE_RISK WHERE level = 1 AND isDel = 0) t1, ");
		sql.append(" (SELECT * FROM DIM_LIST_MANAGE_RISK WHERE level = 2 AND isDel = 0) t2, ");
		sql.append(" (SELECT * FROM DIM_LIST_MANAGE_RISK WHERE level = 3 AND isDel = 0) t3 ");
		sql.append(" WHERE t1.id = t2.parent_id AND t2.id = t3.parent_id) m");
	}
	
	private void buildRiskCatalogCondition(StringBuilder sql, String name, Integer status){
		sql.append(" WHERE 1=1 ");
		if(StringUtils.hasText(name)){
			String nameLike = "%" + name + "%";
			sql.append(" AND (nm1 LIKE '").append(nameLike).append("' ")
			   .append(" OR nm2 LIKE '").append(nameLike).append("' ")
			   .append(" OR nm3 LIKE '").append(nameLike).append("') ");
		}
		if(status != null){
			sql.append("AND status = ").append(status).append(" ");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRiskCatalogList(String name, Integer status, Integer offset, Integer pageSize) {
		
		StringBuilder sql =  new StringBuilder(200);
		sql.append("SELECT id, id1, nm1, id2, nm2, id3, nm3, status, create_person_name, submit_audit_person_name, audit_person_name FROM ");
		buildRiskCatalogListFromSql(sql);
		buildRiskCatalogCondition(sql, name, status);
		sql.append("ORDER BY status, id1, id2, id3 ASC");
		
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
	public Integer getRiskCatalogAuditListCount(String name, String define, Integer status){
		StringBuilder sql = new StringBuilder(200);
		sql.append("SELECT COUNT(id) FROM ");
		buildRiskCatalogListFromSql(sql);
		buildRiskCatalogAuditListCondition(sql, name, define, status);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return ((BigInteger)query.uniqueResult()).intValue();
	}
	
	private void buildRiskCatalogAuditListCondition(StringBuilder sql, String name, String define, Integer status){
		sql.append(" WHERE 1=1 ");
		if(StringUtils.hasText(name)){
			String nameLike = "%" + name + "%";
			sql.append(" AND (nm1 LIKE '").append(nameLike).append("' ")
			   .append(" OR nm2 LIKE '").append(nameLike).append("' ")
			   .append(" OR nm3 LIKE '").append(nameLike).append("') ");
		}
		if(StringUtils.hasText(define)){
			String defineLike = "%" + name + "%";
			sql.append(" AND (def1 LIKE '").append(defineLike).append("' ")
			   .append(" OR def2 LIKE '").append(defineLike).append("' ")
			   .append(" OR def3 LIKE '").append(defineLike).append("') ");
		}
		if(status != null){
			sql.append("AND status = ").append(status).append(" ");
		}else{
			sql.append("AND status IN (1, 2, 3) ");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRiskCatalogAuditList(String name, String define, Integer status, Integer offset, Integer pageSize){
		StringBuilder sql =  new StringBuilder(200);
		sql.append("SELECT id, id1, nm1, id2, nm2, id3, nm3, status, create_person_name, submit_audit_person_name, audit_person_name FROM ");
		buildRiskCatalogListFromSql(sql);
		buildRiskCatalogAuditListCondition(sql, name, define, status);
		sql.append("ORDER BY status, id1, id2, id3 ASC");
		
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
	public BimrRiskCatalog getRiskCatalogOne(Integer id) {
		String hql = "FROM BimrRiskCatalog WHERE id = :id";
		
		return (BimrRiskCatalog)sessionFactory.
				getCurrentSession().
				createQuery(hql).
				setParameter("id", id).
				uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BimrRiskCatalog> getRiskCatalogChildren(Integer parentId) {
		boolean isRoot = parentId == null;
		String hql = isRoot? "FROM BimrRiskCatalog WHERE parentId IS NULL AND status = 3": "FROM BimrRiskCatalog WHERE parentId = :parentId AND status = 3";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(!isRoot){
			query.setParameter("parentId", parentId);
		}
		return query.list();
	}
}
