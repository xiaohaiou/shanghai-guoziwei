package com.softline.dao.project.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.project.IPjApproveDao;
import com.softline.entity.project.PjApprove;
import com.softline.entity.project.PjProject;
import com.softline.entityTemp.VContractChange;
import com.softline.entityTemp.VNodeChange;
import com.softline.entityTemp.VProjectChange;

@Repository("pjApproveDao")
public class PjApproveDao implements IPjApproveDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<PjProject> getPjProjects(String ids,String pjDeptId,PjProject condition, Integer offset,
			Integer pageSize) {
		List<PjProject> result = new ArrayList<PjProject>();
		if(Common.notEmpty(ids)){
			StringBuffer hql = new StringBuffer();
			hql.append("from  PjProject t where t.isdel = 0");
			hql.append(" and t.id in (").append(ids).append(")");
			if(Common.notEmpty(condition.getPjName())){
				hql.append(" and t.pjName like '%" + condition.getPjName()+"%'");
			}
			if(Common.notEmpty(condition.getPjDept())){
				hql.append(" and t.pjDept like '%" + condition.getPjDept() + "%'");
			}
			if(Common.notEmpty(pjDeptId)){
				hql.append(" and t.pjDeptId in(" + pjDeptId + ")");
			}
			hql.append(" and t.reportStatus >= 1 order by t.reportStatus");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			if(offset != null && pageSize!=null){
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
			}
			result = query.list();
		}
		return result;
	}

	@Override
	public List<VProjectChange> getProjectChanges(Integer pjId) {
//		String sql = "select * from v_project_change t where t.id = " + pjId + " and t.report_status = 1";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT `a`.`id` AS `id`, `a`.`pj_name` AS `pj_name`, `a1`.`pj_progress` AS `old_pj_progress`, `a`.`pj_progress` AS `new_pj_progress`, `a1`.`total_contract_progress` AS `old_contract_progress`, `a`.`total_contract_progress` AS `new_contract_progress`, `a`.`report_status` AS `report_status` FROM ( `pj_project` `a` LEFT JOIN ( SELECT b.*, max(`b`.`version`) AS `max(b.version)` FROM `pj_project_history` `b` WHERE (`b`.`report_status` = 2) GROUP BY `b`.`project_id` ) `a1` ON ((`a`.`id` = `a1`.`project_id`))) ");
		sql.append(" where `a`.`id` = "+ pjId+" and `a`.`report_status` = 1");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		List<Object> results = query.list();
		List<VProjectChange> finalResult = new ArrayList<VProjectChange>();
		if(results.size() > 0){
			for(int i = 0; i < results.size(); i++ ){
				Object[] objs =  (Object[]) results.get(i);
				VProjectChange entity = new VProjectChange();
				entity.setId(Common.parseInteger(objs[0].toString()));
				entity.setPjName(objs[1]==null?"":objs[1].toString());
				entity.setOldPjProgress(Common.parseDouble(objs[2]==null?"0.0":objs[2].toString()));
				entity.setNewPjProgress(Common.parseDouble(objs[3]==null?"0.0":objs[3].toString()));
				entity.setOldContractProgress(Common.parseDouble(objs[4]==null?"0.0":objs[4].toString()));
				entity.setNewContractProgress(Common.parseDouble(objs[5]==null?"0.0":objs[5].toString()));
				finalResult.add(entity);
			}
		}
		return finalResult;
	}

	@Override
	public List<VNodeChange> getNodeChanges(Integer pjId) {
//		String sql = "select * from v_node_change t where t.pj_id = " + pjId +" and t.report_status = 1";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT `a`.`id` AS `id`, `a`.`pj_id` AS `pj_id`, `a`.`pn_name` AS `pn_name`, `a`.`pn_order` AS `pn_order`, `a1`.`pn_completion_time` AS `old_completion_time`, `a`.`pn_completion_time` AS `new_completion_time`, `a1`.`pn_progress` AS `old_progress`, `a`.`pn_progress` AS `new_progress`, `a`.`report_status` AS `report_status` FROM ( `pj_node` `a` LEFT JOIN ( SELECT b.*, max(`b`.`version`) AS `max(b.version)` FROM `pj_node_history` `b` WHERE (`b`.`report_status` = 2) GROUP BY `b`.`node_id` ) `a1` ON ((`a`.`id` = `a1`.`node_id`)))");
		sql.append(" where `a`.`pj_id` = "+ pjId +" and `a`.`report_status` = 1 and `a`.`isdel` = 0");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		List<Object> results = query.list();
		List<VNodeChange> finalResult = new ArrayList<VNodeChange>();
		if(results.size() > 0){
			for(int i = 0; i < results.size(); i++ ){
				Object[] objs =  (Object[]) results.get(i);
				VNodeChange entity = new VNodeChange();
				entity.setId(Common.parseInteger(objs[0].toString()));
				entity.setPjId(Common.parseInteger(objs[1].toString()));
				entity.setPnName(objs[2]==null?"":objs[2].toString());
				entity.setPnOrder(Common.parseInteger(objs[3]==null?"0":objs[3].toString()));
				entity.setOldCompletionTime(objs[4]==null?"":objs[4].toString());
				entity.setNewCompletionTime(objs[5]==null?"":objs[5].toString());
				entity.setOldProgress(new BigDecimal(objs[6]==null?"0":objs[6].toString()).doubleValue());
				entity.setNewProgress(new BigDecimal(objs[7]==null?"0":objs[7].toString()).doubleValue());
				finalResult.add(entity);
			}
		}
		return finalResult;
	}

	@Override
	public List<VContractChange> getContractChanges(Integer pjId) {
//		String sql = "select * from v_contract_change t where t.pj_id = " + pjId +" and t.report_status = 1";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT `a`.`id` AS `id`, `a`.`pj_id` AS `pj_id`, `a`.`pc_name` AS `pc_name`, `a`.`pc_body_a` AS `pc_body_a`, `a`.`pc_body_b` AS `pc_body_b`, `a`.`pc_bd_money` AS `pc_bd_money`, `a1`.`pc_paid_money` AS `old_paid_money`, `a`.`pc_paid_money` AS `new_paid_money`, `a`.`report_status` AS `report_status` FROM ( `pj_contract` `a` LEFT JOIN ( SELECT b.*, max(`b`.`version`) AS `max(b.version)` FROM `pj_contract_history` `b` WHERE (`b`.`report_status` = 2) GROUP BY `b`.`contract_id` ) `a1` ON (( `a`.`id` = `a1`.`contract_id` )))");
		sql.append(" where `a`.`pj_id` = "+ pjId +" and `a`.`report_status` = 1 and `a`.`isdel` = 0");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		List<Object> results = query.list();
		List<VContractChange> finalResult = new ArrayList<VContractChange>();
		if(results.size() > 0){
			for(int i = 0; i < results.size(); i++ ){
				Object[] objs =  (Object[]) results.get(i);
				VContractChange entity = new VContractChange();
				entity.setId(Common.parseInteger(objs[0].toString()));
				entity.setPjId(Common.parseInteger(objs[1].toString()));
				entity.setPcName(objs[2]==null?"":objs[2].toString());
				entity.setPcBodyA(objs[3]==null?"":objs[3].toString());
				entity.setPcBodyB(objs[4]==null?"":objs[4].toString());
				entity.setPcBdMoney(Common.parseDouble(objs[5]==null?"0":objs[5].toString()));
				entity.setOldPaidMoney(Common.parseDouble(objs[6]==null?"0":objs[6].toString()));
				entity.setNewPaidMoney(Common.parseDouble(objs[7]==null?"0":objs[7].toString()));
				finalResult.add(entity);
			}
		}
		return finalResult;
	}

	@Override
	public List<PjApprove> getPjApproves(PjApprove pjApprove) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  PjApprove t where 1 = 1");
		if(pjApprove.getPjId() == null){
			hql.append(" and t.pjId = " + pjApprove.getPjId());
		}
		if(pjApprove.getType() == 0){
			hql.append(" and t.type =" + pjApprove.getType());
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	
	
	
}
