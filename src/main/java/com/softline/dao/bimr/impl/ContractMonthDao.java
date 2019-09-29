package com.softline.dao.bimr.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mvel2.asm.tree.IntInsnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.bimr.IContractMonthDao;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrContractMonth;
import com.softline.entity.bimr.BimrContractMonthList;

@Repository("contractMonthDao")
public class ContractMonthDao implements IContractMonthDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	/*
	 * public List<DataSocialVoluntary> getSocialVoluntaryList(
	 * DataSocialVoluntary entity, Integer offsize, Integer pagesize, Integer
	 * fun) { if (entity == null) { return new ArrayList<DataSocialVoluntary>();
	 * } StringBuilder hql = new StringBuilder();
	 * hql.append(" from DataSocialVoluntary s where 1=1 and isdel=0 "); if
	 * (fun.equals(Base.fun_examine)) { hql.append(" AND s.status <> 50112 "); }
	 * if (entity != null) { if (entity.getMonth() != null) {
	 * hql.append(" and s.month = " + entity.getMonth() + " "); } if
	 * (entity.getYear() != null) { hql.append(" and s.year = " +
	 * entity.getYear() + " "); } if (entity.getStatus() != null) {
	 * hql.append(" and s.status = " + entity.getStatus() + " "); } if
	 * (entity.getId() != null) { hql.append(" and s.id = " + entity.getId() +
	 * " "); } } hql.append(" order by year desc,month desc "); Query query =
	 * sessionFactory.getCurrentSession().createQuery( hql.toString()); if
	 * (offsize != null) { query.setFirstResult(offsize); } if (pagesize !=
	 * null) { query.setMaxResults(pagesize); } return query.list(); }
	 */
	@Override
	public List<BimrContractMonth> getContractMonthList(
			BimrContractMonth entity, Integer offsize, Integer pagesize) {
		if (entity == null) {
			return new ArrayList<BimrContractMonth>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrContractMonth s where  isdel=0 ");
		/*
		 * if (fun.equals(Base.fun_examine)) {
		 * hql.append(" AND s.status <> 50112 "); }
		 */
		
		appendQueryContractMonth(entity, hql);
		hql.append(" order by year desc,month desc,comp_id ");
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

	/**
	 * @param entity
	 * @param hql
	 * @since 2018-02-26
	 */
	void appendQueryContractMonth(BimrContractMonth entity, StringBuilder hql) {
		if (entity != null) {
			if (Common.notEmpty(entity.getCompId())) {
				hql.append(" and s.compId in ( " + entity.getCompId()+" ) ");
			}
			if (entity.getMonth() != null) {
				hql.append(" and s.month = " + entity.getMonth() + " ");
			}
			if (entity.getYear() != null) {
				hql.append(" and s.year = " + entity.getYear() + " ");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (entity.getId() != null) {
				hql.append(" and s.id = " + entity.getId() + " ");
			}
			if (Common.notEmpty(entity.getDataAuth())) {
				hql.append(" and s.compId in (" + entity.getDataAuth() + ") ");
			}
		}
	}

	@Override
	public int getContractMonthListCount(BimrContractMonth entity) {
		if (entity == null) {
			return 0;
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" select count(0) from BimrContractMonth s where  isdel=0 ");
		appendQueryContractMonth(entity, hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
//	合同信息查询
	@Override
	public List<BimrContractMonthList> getBimrContractMonthList(
			BimrContractMonthList entity, Integer offsize, Integer pagesize) {
		if (entity == null) {
			return new ArrayList<BimrContractMonthList>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrContractMonthList s where  isdel=0 ");
		if(entity.getIsCom() != null && !entity.getIsCom().equals("")){	
			hql.append("and isCom = '"+entity.getIsCom()+"' ");										
	}
		/*
		 * if (fun.equals(Base.fun_examine)) {
		 * hql.append(" AND s.status <> 50112 "); }
		 */
		getInspectProjectListCondition(entity,hql);

		hql.append(" order by id ");
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

	@Override
	public int getBimrContractMonthListCount(BimrContractMonthList entity) {
		if (entity == null) {
			return 0;
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" select count(0) from BimrContractMonthList s where  isdel=0 ");
//		if(entity.getIsCom() != null && !entity.getIsCom().equals("")){	
//			hql.append("and isCom = '"+entity.getIsCom()+"' ");										
//	}
		getInspectProjectListCondition(entity,hql);

		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public BimrContractMonth GetBimrContractMonthById(Integer bid) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrContractMonth s where  isdel=0 and id=" + bid);
		List<BimrContractMonth> l = sessionFactory.getCurrentSession()
				.createQuery(hql.toString()).list();
		if (l.isEmpty())
			return null;
		return l.get(0);
	}

	@Override
	public Integer addBimrContractMonth(BimrContractMonth entity) {
		if (entity == null)
			return null;
		if ((Integer) sessionFactory.getCurrentSession().save(entity) > 0)
			return entity.getId();
		return null;
	}

	@Override
	public Boolean addBimrContractMonthLists(List<BimrContractMonthList> entitys) {
		
		Session session = sessionFactory.getCurrentSession();
		for (int i = 0; i < entitys.size() ; i++){
			session.save(entitys.get(i));
			if(i % 200 == 0){
				session.flush();
				session.clear();
			}
		}
				
		return true;
	}

	@Override
	public Boolean updateBimrContractMonth(BimrContractMonth entity) {
		if (entity == null)
			return false;
		sessionFactory.getCurrentSession().update(entity);
		return true;
	}

	@Override
	public Boolean delBimrContractMonthLists(Integer bimrContractId,
			HhUsers user) {
		if (bimrContractId == null)
			return false;
		String dateStr = Common.getDate("yyyy-MM-dd HH:mm:ss");
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"update bimr_contract_month_list set isdel=1 "
								+ ", lastModifyPersonID=:lastModifyPersonID "
								+ ", lastModifyPersonName=:lastModifyPersonName "
								+ ", lastModifyDate=:lastModifyDate "
								+ " where contractMonthId=:contractMonthId and isdel=0");
		query.setParameter("lastModifyPersonID", user.getLastModifyPersonId());
		query.setParameter("lastModifyPersonName",
				user.getLastModifyPersonName());
		query.setParameter("lastModifyDate", dateStr);
		query.setParameter("contractMonthId", bimrContractId);
		query.executeUpdate();
		Query query1 = sessionFactory.getCurrentSession().createSQLQuery(
				"update bimr_contract_month set isdel=1 "
						+ ", lastModifyPersonID=:lastModifyPersonID "
						+ ", lastModifyPersonName=:lastModifyPersonName "
						+ ", lastModifyDate=:lastModifyDate "
						+ " where id=:contractMonthId  and isdel=0");
		query1.setParameter("lastModifyPersonID", user.getLastModifyPersonId());
		query1.setParameter("lastModifyPersonName",
				user.getLastModifyPersonName());
		query1.setParameter("lastModifyDate", dateStr);
		query1.setParameter("contractMonthId", bimrContractId);
		query1.executeUpdate();
		return true;
	}

	@Override
	public Boolean ReportContractMonthById(Integer bimrContractId,
			Integer status, HhUsers user) {
		String dateStr = Common.getDate("yyyy-MM-dd HH:mm:ss");
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"update bimr_contract_month_list set status=@status "
								+ ", lastModifyPersonID=:lastModifyPersonID "
								+ ", lastModifyPersonName=:lastModifyPersonName "
								+ ", lastModifyDate=:lastModifyDate "
								+ " where contractMonthId=:contractMonthId and isdel=0");

		query.setParameter("lastModifyPersonID", user.getLastModifyPersonId());
		query.setParameter("lastModifyPersonName",
				user.getLastModifyPersonName());
		query.setParameter("lastModifyDate", dateStr);
		query.setParameter("contractMonthId", bimrContractId);
		query.setParameter("status", status);
		query.executeUpdate();
		Query query1 = sessionFactory.getCurrentSession().createSQLQuery(
				"update bimr_contract_month set status=@status "
						+ ", lastModifyPersonID=:lastModifyPersonID "
						+ ", lastModifyPersonName=:lastModifyPersonName "
						+ ", lastModifyDate=:lastModifyDate "
						+ " where id=:contractMonthId  and isdel=0");
		query1.setParameter("lastModifyPersonID", user.getLastModifyPersonId());
		query1.setParameter("lastModifyPersonName",
				user.getLastModifyPersonName());
		query1.setParameter("lastModifyDate", dateStr);
		query1.setParameter("status", status);
		query1.setParameter("contractMonthId", bimrContractId);
		query1.executeUpdate();
		return true;
	}
//合同信息查询页面分页
	public int getContractMonthDetailListCount(BimrContractMonthList entity) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from BimrContractMonthList s where 1=1 and isdel=0 ");
//		if(entity.getIsCom() != null && !entity.getIsCom().equals("")){	
//			hql.append("and isCom = "+entity.getIsCom()+" ");										
//	}
		//条件
		getInspectProjectListCondition(entity,hql);
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
//合同信息查询
	public List<BimrContractMonthList> getContractMonthDetailList(
			BimrContractMonthList entity, Integer offsize, Integer pagesize) {
		if (entity == null) {
			return new ArrayList<BimrContractMonthList>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrContractMonthList s where 1=1 and isdel=0 ");
		
		//条件
		getInspectProjectListCondition(entity,hql);
		
		hql.append(" order by createDate desc ");
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
	
	private void getInspectProjectListCondition(BimrContractMonthList entity,StringBuilder hql){
		if (entity != null) {
			if (Common.notEmpty(entity.getStartTime()) ) {
				hql.append(" and s.contractSignDate >= '" + entity.getStartTime() + "' ");
			}
			
			if (Common.notEmpty(entity.getEndTime())) {
				hql.append(" and s.contractSignDate <= '" + entity.getEndTime() + "' ");
			}
			
			/*if (Common.notEmpty(entity.getCompName())) {
				hql.append(" and s.ourContractSubject like '%" + entity.getCompName() + "%' ");
			}*/
			if (entity.getIsDefault() != null) {
				hql.append(" and s.isDefault = " + entity.getIsDefault() + " ");
			}
			
			if (entity.getIsMajorContract() != null) {
				hql.append(" and s.isMajorContract = " + entity.getIsMajorContract() );
			}

			if (entity.getId() != null) {
				hql.append(" and s.id = " + entity.getId() + " ");
			}
			if (entity.getContractMonthId() != null) {
				hql.append(" and s.contractMonthId = " + entity.getContractMonthId());
			}
			if (Common.notEmpty(entity.getCompId())) {
				hql.append(" and s.contractMonthId in (select m.id from BimrContractMonth m where m.compId in ("+entity.getCompId()+")) ");
			}
			if (entity.getDataauth() != null && !"".equals(entity.getDataauth())){
				hql.append(" and s.contractMonthId in (select m.id from BimrContractMonth m where m.compId in ("+entity.getDataauth()+")) ");
			}
			else {
				if(entity.getContractMonthId() == null){
					hql.append(" and s.contractMonthId = 0 ");
				}
			}
			if(entity.getIsCom() != null && !entity.getIsCom().equals("")){	
				hql.append("and s.isCom = "+entity.getIsCom()+" ");										
		}
			
//			合同类型
			 if(Common.notEmpty(entity.getContractTypeText())) {
	                hql.append(" and s.contractTypeText like '%"+entity.getContractTypeText()+ "%'");
	            }
//			 合同类别
			 if(Common.notEmpty(entity.getContractKindText())) {
	                hql.append(" and s.contractKindText like '%"+entity.getContractKindText()+ "%'");
	            }
//			 风险等级
//			 if(Common.notEmpty(entity.getRiskGrade())) {
//	                hql.append(" and s.riskGrade like '%"+entity.getRiskGrade()+ "%'");
//	            }
			 
			 if(entity.getRiskGrade() != null && !entity.getRiskGrade().equals("")){
					
					if(entity.getRiskGrade().equals("1")){
						
						hql.append("and s.riskGrade  = '"+entity.getRiskGrade()+"' ");
					}
					if(entity.getRiskGrade().equals("2")){
						
						hql.append("and s.riskGrade  = '"+entity.getRiskGrade()+"' ");
					}
					if(entity.getRiskGrade().equals("3")){
						
						hql.append("and s.riskGrade  = '"+entity.getRiskGrade()+"' ");
					}
					if(entity.getRiskGrade().equals("4")){
						
						hql.append("and s.riskGrade  = '"+entity.getRiskGrade()+"' ");
					}
					if(entity.getRiskGrade().equals("5")){
						
						hql.append("and s.riskGrade  = '"+entity.getRiskGrade()+"' ");
					}
					
					
				}
			 
//			 我方违约情况
			 if(Common.notEmpty(entity.getOurDefault())) {
	                hql.append(" and s.ourDefault like '%"+entity.getOurDefault()+ "%'");
	            }
//			 是否属于重点关注合同
			 if (entity.getIsFocus() != null && !entity.getIsFocus().equals("")) {
					hql.append(" and s.isFocus = " + entity.getIsFocus() );
				}
			
//			 是否涉诉
			 if (entity.getIsInvolving() != null && !entity.getIsInvolving().equals("")) {
					hql.append(" and s.isInvolving = " + entity.getIsInvolving() );
				}
			 
//				对方违约情况
			 if(Common.notEmpty(entity.getOtherDefault())) {
	               hql.append(" and s.otherDefault like '%"+entity.getOtherDefault()+ "%'");
	           }
		
//			 风险描述
			 if(Common.notEmpty(entity.getRiskDescription())) {
	                hql.append(" and s.riskDescription like '%"+entity.getRiskDescription()+ "%'");
	            }
			 
//			 风险成因
			 if(Common.notEmpty(entity.getRiskCause())) {
	                hql.append(" and s.riskCause like '%"+entity.getRiskCause()+ "%'");
	            }
//			 风险处置方案及进展
			 if(Common.notEmpty(entity.getRiskManage())) {
	                hql.append(" and s.riskManage like '%"+entity.getRiskManage()+ "%'");
	            }
//			 
////			 风险是否排除及排除情况
			 if(Common.notEmpty(entity.getRiskExcluded())) {
	                hql.append(" and s.riskExcluded like '%"+entity.getRiskExcluded()+ "%'");
	            }
//			 if(entity.getIsCom() != null && !entity.getIsCom().equals("")){	
//					hql.append("and s.isCom = "+entity.getIsCom()+" ");										
//			}
//			 我方逾期应付款
			 if(entity.getOurOverPay() != null && !entity.getOurOverPay().equals("")){
					
					if(entity.getOurOverPay().compareTo(BigDecimal.valueOf(1))==0){
						
						hql.append("and s.ourOverPay < 1000000 ");
					}
					if(entity.getOurOverPay().compareTo(BigDecimal.valueOf(2))==0){
						hql.append("and s.ourOverPay between 1000000 And 5000000 ");
					}
					if(entity.getOurOverPay().compareTo(BigDecimal.valueOf(3))==0){
						hql.append("and s.ourOverPay between 5000000 And 10000000 ");
					}
					if(entity.getOurOverPay().compareTo(BigDecimal.valueOf(4))==0){
						hql.append("and s.ourOverPay between 10000000 And 50000000 ");
					}
					if(entity.getOurOverPay().compareTo(BigDecimal.valueOf(5))==0){
						hql.append("and s.ourOverPay > 50000000");
					}
					
					
				}
//			 对方逾期应收款总额
			 if(entity.getOtherOverPay() != null && !entity.getOtherOverPay().equals("")){
					
					if(entity.getOtherOverPay().compareTo(BigDecimal.valueOf(1))==0){
						
						hql.append("and s.otherOverPay < 1000000 ");
					}
					if(entity.getOtherOverPay().compareTo(BigDecimal.valueOf(2))==0){
						hql.append("and s.otherOverPay between 1000000 And 5000000 ");
					}
					if(entity.getOtherOverPay().compareTo(BigDecimal.valueOf(3))==0){
						hql.append("and s.otherOverPay between 5000000 And 10000000 ");
					}
					if(entity.getOtherOverPay().compareTo(BigDecimal.valueOf(4))==0){
						hql.append("and s.otherOverPay between 10000000 And 50000000 ");
					}
					if(entity.getOtherOverPay().compareTo(BigDecimal.valueOf(5))==0){
						hql.append("and s.otherOverPay > 50000000");
					}
					
					
				}
		 
		}
		}
		

	
	@Override
	public BimrContractMonthList GetBimrContractMonthListById(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrContractMonthList s where  isdel=0 and id=" + id);
		List<BimrContractMonthList> l = sessionFactory.getCurrentSession()
				.createQuery(hql.toString()).list();
		if (l.isEmpty())
			return null;
		return l.get(0);
	}

	@Override
	public List<BimrContractMonth> getContractMonthListExport(
			BimrContractMonth entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			return new ArrayList<BimrContractMonth>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrContractMonth s where  isdel=0 ");
		/*
		 * if (fun.equals(Base.fun_examine)) {
		 * hql.append(" AND s.status <> 50112 "); }
		 */
		
		appendQueryContractMonth(entity, hql);
		hql.append(" order by year desc,month desc,comp_id ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		
		return query.list();
	}

	@Override
	public List<BimrContractMonthList> getBimrContractMonthListExport(
			BimrContractMonthList entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			return new ArrayList<BimrContractMonthList>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrContractMonthList s where  isdel=0 ");
		/*
		 * if (fun.equals(Base.fun_examine)) {
		 * hql.append(" AND s.status <> 50112 "); }
		 */
		getInspectProjectListCondition(entity,hql);

		hql.append(" order by id ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		
		return query.list();
	}
}
