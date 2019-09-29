package com.softline.dao.bimr.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.bimr.IEmpArchivesDao;
import com.softline.entity.EmployeeAccountabilityViewId;
import com.softline.entity.bimr.BimrAccountable;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrCriminalcaseWeek;
import com.softline.entity.bimr.BimrDuty;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrRiskEvent;
import com.softline.entityTemp.CompliancePersionAbility;


/**
 * 干部员工风控档案DaoImpl
 * 
 */
@Repository(value = "empArchivesDao")
public class EmpArchivesDao implements IEmpArchivesDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public BimrDuty getById(Integer id) {
		String hql = " from BimrDuty h where h.isDel = 0 and h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (BimrDuty)query.uniqueResult();
	}

	@Override
	public List<BimrDuty> getByPersonId(String personId) {
		// TODO Auto-generated method stub
		String hql = " from BimrDuty h where h.isDel = 0 and h.personId = " + personId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public Integer getEmpAchivesListCount(BimrDuty entity, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append(" from BimrDuty s where 1=1 and isDel=0 ");
		if(entity != null) {
			
			if(Common.notEmpty(entity.getPerson())) {
				hql.append(" and s.person like '%"+entity.getPerson()+ "%'");
			}
			
			if(dataAuthority != null) {
				hql.append(" and s.personId in (select vcEmployeeId from HhUsers where nnodeId in ("+dataAuthority+"))");
			}
		}
		hql.append(" group by s.personId ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BimrDuty> getEmpAchivesList(BimrDuty entity, Integer offset,Integer pageSize, String dataAuthority) {
		if(entity == null)
			return new ArrayList<BimrDuty>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from BimrDuty s where 1=1 and isDel=0 ");
		if(entity != null) {
			
			if(Common.notEmpty(entity.getPerson())) {
				hql.append(" and s.person like '%"+entity.getPerson()+ "%'");
			}
			
			if(dataAuthority != null) {
				hql.append(" and s.personId in (select vcEmployeeId from HhUsers where nnodeId in ("+dataAuthority+"))");
			}
		}
		hql.append(" group by s.personId ");
		hql.append(" order by s.createDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset != null){
			query.setFirstResult(offset);
		}	
		if(pageSize != null){
			query.setMaxResults(pageSize);
		}	
		return query.list();
	}

	

	@Override
	public List getPersionAccountability(int projectId,int projectType) {
		// TODO Auto-generated method stub

		StringBuilder  hql = new StringBuilder();
		hql.append("from BimrAccountable where 1=1 and isDel = 0");
		if(projectId != 0) {
				hql.append(" and projectId="+projectId);
		}
		if(projectType != 0) {
			hql.append(" and projectType="+projectType);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return query.list();
	}

	@Override
	public List<CompliancePersionAbility> getCompliancePersionAbility(
			int complianceId) {
		// TODO Auto-generated method stub
		//String sqlString="select c.projectName,s.dataType,s.problem,p.accountabilityPerson,p.accountabilityPersonId,s.complianceId,p.situationId,p.id from bimr_compliance c,bimr_compliance_situation s,bimr_compliance_situation_person p where c.id=s.complianceId and p.situationId=s.id and c.id=?";
		StringBuilder  sql = new StringBuilder();
		sql.append("select m.*,n.`status` FROM( ");
		sql.append("select c.projectName,s.dataType,s.problem,p.accountabilityPerson,p.accountabilityPersonId,s.complianceId,p.situationId,p.id ");
		sql.append(" from bimr_compliance c,bimr_compliance_situation s,bimr_compliance_situation_person p ");
		sql.append("where c.id=s.complianceId and p.situationId=s.id and c.id=? ) m ");
		sql.append(" LEFT JOIN bimr_duty n ON m.complianceId=n.projectId and m.situationId = n.questionId and m.id = n.accountableId");
		Query query=sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		query.setParameter(0, complianceId);
		
		return query.list();
	}




	@Override
	public List<EmployeeAccountabilityViewId> getEmpAccidList(
			EmployeeAccountabilityViewId entity, Integer offset,
			Integer pageSize,String dataAuthority) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select * from (select id, '审计项目' typename,audit_project_code project_code, audit_project_name project_name,'1' projecttype,accountabilityStatus from bimr_inside_audit_project where is_del = 0 and status !=50223 and id in (select project_id from bimr_inside_audit_project_appendix where audit_impl_dept_id in ("+dataAuthority+")) union all select id,'合规项目' typename,'' project_code, projectName project_name,'2' projecttype,accountabilityStatus from bimr_compliance where isDel=0 and investigationId in ("+dataAuthority+") union all select id,'民事案件' typename,case_num project_code,base_message project_name,'3' projecttype,accountability_Status from bimr_civilcase_week where is_del = 0 and is_accountability = 1 and is_history = 0 and is_lastest = 1 and approval_state = 2 and vc_company_id in (" + dataAuthority + ") union all select id,'刑事案件' typename,case_num project_code,base_message project_name,'4' projecttype,accountability_Status from bimr_criminalcase_week where is_del = 0 and is_accountability = 1 and is_history = 0 and is_lastest = 1 and approval_state = 2 and vc_company_id in (" + dataAuthority + ") ) a where 1=1 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getProjectName())) {
				hql.append(" and a.project_name like '%"+entity.getProjectName()+ "%'");
			}
			if(Common.notEmpty(entity.getTypename())) {
				hql.append(" and a.typename = '"+entity.getTypename()+ "'");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offset != null){
			query.setFirstResult(offset);
		}	
		if(pageSize != null){
			query.setMaxResults(pageSize);
		}	
		List<Object[]> objlist=(List<Object[]>)query.list();
		List<EmployeeAccountabilityViewId> returnList=new ArrayList<EmployeeAccountabilityViewId>();
		for (Object[] obj : objlist) {
			EmployeeAccountabilityViewId employeeAccountabilityViewId=new EmployeeAccountabilityViewId();
			employeeAccountabilityViewId.setId(Integer.parseInt(String.valueOf(obj[0])));
			employeeAccountabilityViewId.setTypename(String.valueOf(obj[1]));
			employeeAccountabilityViewId.setProjectCode(String.valueOf(obj[2]));
			employeeAccountabilityViewId.setProjectName(String.valueOf(obj[3]));
			employeeAccountabilityViewId.setProjecttype(Integer.parseInt(String.valueOf(obj[4])));
			if(null==obj[5]){
				employeeAccountabilityViewId.setStatus(0);
			}else {
				employeeAccountabilityViewId.setStatus(Integer.parseInt(String.valueOf(obj[5])));
			}
			returnList.add(employeeAccountabilityViewId);
		}
		return returnList;
	}

	@Override
	public Integer getEmpAccsidListCount(EmployeeAccountabilityViewId entity,String dataAuthority) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from (select id, '审计项目' typename,audit_project_code project_code, audit_project_name project_name,'1' projecttype,accountabilityStatus from bimr_inside_audit_project where is_del = 0 and status !=50223 and id in (select project_id from bimr_inside_audit_project_appendix where audit_impl_dept_id in ("+dataAuthority+")) union all select id,'合规项目' typename,'' project_code, projectName project_name,'2' projecttype,accountabilityStatus from bimr_compliance where isDel=0 and investigationId in ("+dataAuthority+") union all select id,'民事案件' typename,case_num project_code,base_message project_name,'3' projecttype,accountability_Status from bimr_civilcase_week where is_del = 0 and is_accountability = 1 and is_history = 0 and is_lastest = 1 and approval_state = 2 and vc_company_id in (" + dataAuthority + ") union all select id,'刑事案件' typename,case_num project_code,base_message project_name,'4' projecttype,accountability_Status from bimr_criminalcase_week where is_del = 0 and is_accountability = 1 and is_history = 0 and is_lastest = 1 and approval_state = 2 and vc_company_id in (" + dataAuthority + ") ) a where 1=1 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getProjectName())) {
				hql.append(" and a.project_name like '%"+entity.getProjectName()+ "%'");
			}
			if(Common.notEmpty(entity.getTypename())) {
				hql.append(" and a.typename = '"+entity.getTypename()+ "'");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public BimrAccountable getBimrAccountableById(int id) {
		// TODO Auto-generated method stub

		StringBuilder  hql = new StringBuilder();
		hql.append("from BimrAccountable where 1=1 and isDel = 0");
		if(id != 0) {
				hql.append("and id="+id);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (BimrAccountable) query.uniqueResult();
	}

	@Override
	public List<BimrRiskEvent> getRisklist(String orgId, Integer offset, Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuilder  sql = new StringBuilder();
		sql.append("from BimrRiskEvent s where 1=1 and isDel = 0  and compId = "+orgId);
		Query query = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(offset != null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

	@Override
	public List<BimrInsideAuditProject> getFkAuditProjects(String orgId, Integer offset, Integer pageSize) {
		// TODO Auto-generated method stub
		 StringBuilder  hql = new StringBuilder();
	        hql.append("from BimrInsideAuditProject s where 1=1 and s.isDel=0 and auditDepted = "+orgId);
	        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
	        if(offset != null && pageSize!=null){
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
			}
		return query.list();
	}

	@Override
	public List<BimrCompliance> getFkCompliance(String orgId, Integer offset, Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrCompliance s where 1=1 and isdel=0 and compId = "+orgId);
		 Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		 if(offset != null && pageSize!=null){
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
			}
			return query.list();
	}

	@Override
	public List<BimrCivilcaseWeek> getFkCivilcaseWeek(String orgId, Integer offset, Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrCivilcaseWeek s where 1=1 and isDel = 0 and isHistory = 0 and isLastest = 1 and vcCompanyId = "+orgId);
		 Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		 if(offset != null && pageSize!=null){
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
			}
			return query.list();
		
	}

	@Override
	public List<BimrCriminalcaseWeek> getFkCriminalcaseWeek(String orgId,Integer offset, Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrCriminalcaseWeek s where 1=1 and isDel = 0 and isHistory = 0 and isLastest = 1 and vcCompanyId = "+orgId);
		 Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		 if(offset != null && pageSize!=null){
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
			}
			return query.list();
	}

	@Override
	public List<Object[]> getFkContractMonth(String orgId, Integer offset, Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		hql.append("select a.id,a.year, a.total_contract ,SUM(CASE WHEN b.isDefault=1 THEN 1 ELSE 0 END)/COUNT(0) from bimr_contract_month a LEFT JOIN bimr_contract_month_list b on a.id=b.contractMonthId where a.id in (SELECT id from bimr_contract_month a  where a.month = (select MAX(month) from bimr_contract_month where year = a.year and comp_id = ?) and a.comp_id = ? and a.isdel = 0 GROUP BY a.year ) GROUP BY a.id");
		 Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		 query.setParameter(0, orgId);
		 query.setParameter(1, orgId);
		 if(offset != null && pageSize!=null){
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
			}
			return query.list();
	}

	@Override
	public List<BimrDuty> getempArchivesCaseExport(BimrDuty entity,String dataAuthority) {
		if(entity == null)
			return new ArrayList<BimrDuty>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from BimrDuty s where 1=1 and isDel=0 ");
		if(entity != null) {
			
//			员工姓名
			if(Common.notEmpty(entity.getPerson())) {
				hql.append(" and s.person like '%"+entity.getPerson()+ "%'");
			}
			
//			
			if(dataAuthority != null) {
				hql.append(" and s.personId in (select vcEmployeeId from HhUsers where nnodeId in ("+dataAuthority+"))");
			}
		}
		hql.append(" order by s.personId asc ");
//		createDate
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return query.list();
	}
}
