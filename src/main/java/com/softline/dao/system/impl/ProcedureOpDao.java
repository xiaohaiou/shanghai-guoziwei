package com.softline.dao.system.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.system.IProcedureOpDao;
import com.softline.entity.HhProcedureOpRecord;
@Repository(value = "procedureOpDao")
public class ProcedureOpDao implements IProcedureOpDao{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public HhProcedureOpRecord runGuahuaFinanceTreee(String time) {
		HhProcedureOpRecord entity = new HhProcedureOpRecord();
		entity.setProcedureName("syn_finance_tree_info");
		entity.setProcedureParam(time);
		String sql = "{CALL syn_finance_tree_info(?)}";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setString(0, time);
		try {
			query.executeUpdate();
			entity.setOpResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			entity.setOpResult(0);
			entity.setOpMessage(e.getMessage());
		}
		return entity;
	}
	
	@Override
	public HhProcedureOpRecord runGuahuaFinanceHistoryTreee(String time) {
		HhProcedureOpRecord entity = new HhProcedureOpRecord();
		entity.setProcedureName("syn_finance_tree_info_history");
		entity.setProcedureParam(time);
		String sql = "{CALL syn_finance_tree_info_history(?)}";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setString(0, time);
		try {
			query.executeUpdate();
			entity.setOpResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			entity.setOpResult(0);
			entity.setOpMessage(e.getMessage());
		}
		return entity;
	}

	@Override
	public HhProcedureOpRecord runProjectAdminSyn() {
		HhProcedureOpRecord entity = new HhProcedureOpRecord();
		entity.setProcedureName("syn_pj_project");
		String sql = "{CALL syn_pj_project()}";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		try {
			query.executeUpdate();
			entity.setOpResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			entity.setOpResult(0);
			entity.setOpMessage(e.getMessage());
		}
		return entity;
	}

	@Override
	public HhProcedureOpRecord runProjectVideoSyn() {
		HhProcedureOpRecord entity = new HhProcedureOpRecord();
		entity.setProcedureName("syn_pj_video");
		String sql = "{CALL syn_pj_video()}";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		try {
			query.executeUpdate();
			entity.setOpResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			entity.setOpResult(0);
			entity.setOpMessage(e.getMessage());
		}
		return entity;
	}
	
	

}
