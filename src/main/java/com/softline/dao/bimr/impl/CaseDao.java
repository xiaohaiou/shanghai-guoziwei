package com.softline.dao.bimr.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.bimr.ICaseDao;
import com.softline.entity.MainFinancialIndicator;
import com.softline.entity.bimr.BimrCaseQuery;
import com.softline.entity.bimr.BimrCaseReport;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrCriminalcaseWeek;

@Repository(value = "caseDao")
public class CaseDao implements ICaseDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Integer getCivilcaseCount(BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from bimr_civilcase_week s where 1=1 and is_del = 0 and is_history = 0 and is_lastest = 1 ");
		if(civilcase != null){
			if(civilcase.getCaseDate() != null && !civilcase.getCaseDate().equals("")){
				hql.append("and case_date = '"+civilcase.getCaseDate()+"' ");
			}
			if(civilcase.getApprovalState() != null && !civilcase.getApprovalState().equals("")){
				hql.append("and approval_state = '"+civilcase.getApprovalState()+"' ");
			}
			if(civilcase.getVcCompanyId() != null && !"".equals(civilcase.getVcCompanyId()))
			{
				hql.append("and vc_company_id in (" + civilcase.getVcCompanyId() + ")");
			}
			else{
				hql.append("and vc_company_id in (" + civilcase.getDataauth() + ")");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}
//	分页
	@Override
	public Integer getCivilcaseQueryCount(BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize,String caseDateStart,String caseDateEnd,String amountSection) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from bimr_civilcase_week s where 1=1 and is_del = 0 and is_history = 0 and is_lastest = 1 ");
		if(civilcase != null){
			if(caseDateStart != null && !caseDateStart.equals("")){
				hql.append("and case_date >= '"+caseDateStart+"' ");
			}
			if(caseDateEnd != null && !caseDateEnd.equals("")){
				hql.append("and case_date <= '"+caseDateEnd+"' ");
			}
			if(civilcase.getMediatingStage() != null && !civilcase.getMediatingStage().equals("")){
				hql.append("and mediating_stage = '"+civilcase.getMediatingStage()+"' ");
			}
			if(civilcase.getType() != null && !civilcase.getType().equals("")){
				hql.append("and type = '"+civilcase.getType()+"' ");
			}
			if(Common.notEmpty(civilcase.getVcCompanyId())){
				hql.append("and vc_company_id in (" + civilcase.getVcCompanyId() + ")");
			}else{
				hql.append(" and vc_company_id in (" + civilcase.getDataauth() + ") ");
			}
			
			if(civilcase.getReason() != null && !civilcase.getReason().equals("")){
				hql.append("and reason like '%"+civilcase.getReason()+"%' ");
			}
//			if(Common.notEmpty(civilcase.getReason())) {
//                hql.append(" and reason like '%"+civilcase.getReason()+ "%'");
//            }
			if(amountSection != null && !amountSection.equals("")){
				if(amountSection.equals("1")){
					hql.append("and amount < 10 ");
				}
				if(amountSection.equals("2")){
					hql.append("and amount between 10 And 50 ");
				}
				if(amountSection.equals("3")){
					hql.append("and amount between 50 And 100 ");
				}
				if(amountSection.equals("4")){
					hql.append("and amount between 100 And 500 ");
				}
				if(amountSection.equals("5")){
					hql.append("and amount between 500 And 1000 ");
				}
				if(amountSection.equals("6")){
					hql.append("and amount > 1000 ");
				}
				
			}
//			是否问责
			if(civilcase.getIsAccountability() != null && !civilcase.getIsAccountability().equals("")){
				
					hql.append("and is_accountability = '"+civilcase.getIsAccountability()+"' ");									
			}
			
//			是否进行成因分析
			if(civilcase.getIsAnalysis() != null && !civilcase.getIsAnalysis().equals("")){
				
				hql.append("and is_analysis = '"+civilcase.getIsAnalysis()+"' ");										
		}
			
//			判决结果verdictResult
			
			if(Common.notEmpty(civilcase.getVerdictResult())) {
                hql.append(" and verdict_result like '%"+civilcase.getVerdictResult()+ "%'");
            }

			
//			原告/申请人/第三人  模糊查询
			if(Common.notEmpty(civilcase.getPlaintiff())) {
                hql.append(" and plaintiff like '%"+civilcase.getPlaintiff()+ "%'");
            }
//			被告/被申请人/第三人 （多人）模糊查询  defendant
			if(Common.notEmpty(civilcase.getDefendant())) {
                hql.append(" and defendant like '%"+civilcase.getDefendant()+ "%'");
            }
//			受案单位所在地province
			
			if(Common.notEmpty(civilcase.getProvince())) {
                hql.append(" and province like '%"+civilcase.getProvince()+ "%'");
            }

//			if(civilcase.getAdmissibleCourt() != null && !civilcase.getAdmissibleCourt().equals("")){
//				
//				hql.append("and admissible_court = '"+civilcase.getAdmissibleCourt()+"' ");										
//			}
//			受案机构
			if(Common.notEmpty(civilcase.getAdmissibleCourt())) {
                hql.append(" and admissible_court like '%"+civilcase.getAdmissibleCourt()+ "%'");
            }
			
			if(civilcase.getApprovalState() != null && !civilcase.getApprovalState().equals("")){
				hql.append("and approvalState = '"+civilcase.getApprovalState()+"' ");
			}

//			承办法官/仲裁员
			if(Common.notEmpty(civilcase.getJudge())) {
                hql.append(" and judge like '%"+civilcase.getJudge()+ "%'");
            }
			
//			基本案情
			if(Common.notEmpty(civilcase.getBaseMessage())) {
                hql.append(" and base_message like '%"+civilcase.getBaseMessage()+ "%'");
            }
//			承办法务
			if(Common.notEmpty(civilcase.getLawWork())) {
                hql.append(" and law_work like '%"+civilcase.getLawWork()+ "%'");
            }
			if(civilcase.getLitigation() != null && !civilcase.getLitigation().equals("")){
				hql.append("and litigation = '"+civilcase.getLitigation()+"' ");
			}
			
			if(civilcase.getIsMajorCase() != null && !civilcase.getIsMajorCase().equals("")){
				hql.append("and is_major_case = '"+civilcase.getIsMajorCase()+"' ");
			}
			
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	@Override
	public Integer getCivilcaseCountByUserId(BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize, String CreatorId) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from bimr_civilcase_week s where 1=1 and is_del = 0 and is_history = 0 and is_lastest = 1 and create_person_id = '"+CreatorId+"' ");
		if(civilcase != null){
			if(civilcase.getCaseDate() != null && !civilcase.getCaseDate().equals("")){
				hql.append("and case_date = '"+civilcase.getCaseDate()+"' ");
			}
			if(civilcase.getApprovalState() != null && !civilcase.getApprovalState().equals("")){
				hql.append("and approval_state = '"+civilcase.getApprovalState()+"' ");
			}
			if(civilcase.getVcCompanyId() != null && !"".equals(civilcase.getVcCompanyId()))
			{
				hql.append("and vc_company_id in (" + civilcase.getVcCompanyId() + ")");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}
	
//	上报
	@Override
	public List<BimrCivilcaseWeek> getCivilcaseList(BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCivilcaseWeek s where 1=1 and s.isDel = 0 and isHistory = 0 and isLastest = 1 ");
		if(civilcase != null) {
			if(civilcase.getCaseDate() != null && !civilcase.getCaseDate().equals("")){
				hql.append("and caseDate = '"+civilcase.getCaseDate()+"' ");
			}
			if(civilcase.getApprovalState() != null && !civilcase.getApprovalState().equals("")){
				hql.append("and approvalState = '"+civilcase.getApprovalState()+"' ");
			}
			if(civilcase.getVcCompanyId() != null && !"".equals(civilcase.getVcCompanyId()))
			{
				hql.append("and vc_company_id in (" + civilcase.getVcCompanyId() + ")");
			}
			else{
				hql.append("and vcCompanyId in (" + civilcase.getDataauth() + ")");
			}
		}
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
	
//	查询
	@Override
	public List<BimrCivilcaseWeek> getCivilcaseQueryList(
			BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize,String caseDateStart,String caseDateEnd,String amountSection) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCivilcaseWeek s where 1=1 and s.isDel = 0 and isHistory = 0 and isLastest = 1 ");
		if(civilcase != null){
			if(caseDateStart != null && !caseDateStart.equals("")){
				hql.append("and case_date >= '"+caseDateStart+"' ");
			}
			if(caseDateEnd != null && !caseDateEnd.equals("")){
				hql.append("and case_date <= '"+caseDateEnd+"' ");
			}
			if(civilcase.getMediatingStage() != null && !civilcase.getMediatingStage().equals("")){
				hql.append("and mediating_stage = '"+civilcase.getMediatingStage()+"' ");
			}
			if(civilcase.getType() != null && !civilcase.getType().equals("")){
				hql.append("and type = '"+civilcase.getType()+"' ");
			}
			
			/*if(civilcase.getVcCompanyName() != null && !civilcase.getVcCompanyName().equals("")){
				hql.append("and vc_company_name = '"+civilcase.getVcCompanyName()+"' ");
			}*/
			
			if(civilcase.getVcCompanyId() != null && !"".equals(civilcase.getVcCompanyId()))
			{
				hql.append("and vc_company_id in (" + civilcase.getVcCompanyId() + ")");
			}
			else{
				hql.append(" and vc_company_id in (" + civilcase.getDataauth() + ")");
			}
			
//			if(civilcase.getReason() != null && !civilcase.getReason().equals("")){
//				hql.append("and reason like '%"+civilcase.getReason()+"%' ");
//			}
			if(Common.notEmpty(civilcase.getReason())) {
                hql.append(" and reason like '%"+civilcase.getReason()+ "%'");
            }
			if(amountSection != null && !amountSection.equals("")){
				
				if(amountSection.equals("1")){
					
					hql.append("and amount <10 ");
				}
				if(amountSection.equals("2")){
					hql.append("and amount between 10 And 50 ");
				}
				if(amountSection.equals("3")){
					hql.append("and amount between 50 And 100 ");
				}
				if(amountSection.equals("4")){
					hql.append("and amount between 100 And 500 ");
				}
				if(amountSection.equals("5")){
					hql.append("and amount between 500 And 1000 ");
				}
				if(amountSection.equals("6")){
					hql.append("and amount > 1000 ");
				}
				
			}
//			是否问责
			if(civilcase.getIsAccountability() != null && !civilcase.getIsAccountability().equals("")){
				
					hql.append("and is_accountability = '"+civilcase.getIsAccountability()+"' ");										
			}
//			是否进行成因分析
			if(civilcase.getIsAnalysis() != null && !civilcase.getIsAnalysis().equals("")){
				
				hql.append("and is_analysis = '"+civilcase.getIsAnalysis()+"' ");										
		}
//			判决结果verdictResult
			
			if(Common.notEmpty(civilcase.getVerdictResult())) {
                hql.append(" and verdict_result like '%"+civilcase.getVerdictResult()+ "%'");
            }
//			原告/申请人/第三人  模糊查询
			if(Common.notEmpty(civilcase.getPlaintiff())) {
                hql.append(" and plaintiff like '%"+civilcase.getPlaintiff()+ "%'");
            }
//			被告/被申请人/第三人 （多人）模糊查询  defendant
			if(Common.notEmpty(civilcase.getDefendant())) {
                hql.append(" and defendant like '%"+civilcase.getDefendant()+ "%'");
            }
//			基本案情
			if(Common.notEmpty(civilcase.getBaseMessage())) {
                hql.append(" and base_message like '%"+civilcase.getBaseMessage()+ "%'");
            }
//			承办法务
			if(Common.notEmpty(civilcase.getLawWork())) {
                hql.append(" and law_work like '%"+civilcase.getLawWork()+ "%'");
            }
//			承办法官/仲裁员
			if(Common.notEmpty(civilcase.getJudge())) {
                hql.append(" and judge like '%"+civilcase.getJudge()+ "%'");
            }
			
//			受案单位所在地province
			
			if(Common.notEmpty(civilcase.getProvince())) {
                hql.append(" and province like '%"+civilcase.getProvince()+ "%'");
            }
//			受案机构
			if(Common.notEmpty(civilcase.getAdmissibleCourt())) {
                hql.append(" and admissible_court like '%"+civilcase.getAdmissibleCourt()+ "%'");
            }
//			审核状态
			if(civilcase.getApprovalState() != null && !civilcase.getApprovalState().equals("")){
				hql.append("and approvalState = '"+civilcase.getApprovalState()+"' ");
			}
			
			if(civilcase.getLitigation() != null && !civilcase.getLitigation().equals("")){
				hql.append("and litigation = '"+civilcase.getLitigation()+"' ");
			}
			
			if(civilcase.getIsMajorCase() != null && !civilcase.getIsMajorCase().equals("")){
				hql.append("and is_major_case = '"+civilcase.getIsMajorCase()+"' ");
			}
			
		}
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
	public List<BimrCivilcaseWeek> getCivilcaseListByUserId(BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize, String creatorId){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCivilcaseWeek s where 1=1 and s.isDel = 0 and isHistory = 0 and isLastest = 1 and createPersonId = '"+creatorId+"' ");
		if(civilcase != null) {
			if(civilcase.getCaseDate() != null && !civilcase.getCaseDate().equals("")){
				hql.append("and caseDate = '"+civilcase.getCaseDate()+"' ");
			}
			if(civilcase.getApprovalState() != null && !civilcase.getApprovalState().equals("")){
				hql.append("and approvalState = '"+civilcase.getApprovalState()+"' ");
			}
			if(civilcase.getVcCompanyId() != null && !"".equals(civilcase.getVcCompanyId()))
			{
				hql.append("and vc_company_id in (" + civilcase.getVcCompanyId() + ")");
			}
		}
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
	public void deleteCivilcase(Integer id){
		StringBuilder hql = new StringBuilder();
		hql.append("update bimr_civilcase_week set is_del = 1,is_lastest = 0 where id=" + id);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		query.executeUpdate();
	}
	
	@Override
	public BimrCivilcaseWeek getCivilcaseById(Integer id){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCivilcaseWeek s where id = "+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (BimrCivilcaseWeek)query.list().get(0);
	}
	
	//根据案件号获取最新的审核通过的案件信息
	@Override
	public BimrCivilcaseWeek getLastCivilcaseByCaseNum(String CaseNum){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCivilcaseWeek s where caseNum = '"+CaseNum+"' ");
		hql.append(" and isHistory = 0 and approvalState = 2 and caseNum != '' order by version desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List list = query.list();
		if(list.size() > 0){
			return (BimrCivilcaseWeek)list.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public List getCivilcaseByCaseNum(String CaseNum){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCivilcaseWeek s where caseNum = '"+CaseNum+"' ");
		hql.append(" and isDel = 0");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List list = query.list();
		return list;
	}
	
	@Override
	public List getCivilcaseByLikeCaseNum(String CaseNum){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCivilcaseWeek s where caseNum like '"+CaseNum+"%' ");
		hql.append(" and isDel = 0 and isLastest = 1 ");//and version != ''
		hql.append(" order by caseNum desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List list = query.list();
		return list;
	}
	
	/**
	 * 刑事案件
	 * */
	@Override
	public Integer getCriminalcaseQueryCount(BimrCriminalcaseWeek criminalcase,
			Integer offset, Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from bimr_criminalcase_week s where 1=1 and is_del = 0 and is_history = 0 and is_lastest = 1 ");
		if(criminalcase != null) {
			if(criminalcase.getCaseDate() != null && !criminalcase.getCaseDate().equals("")){
				hql.append("and case_date = '"+criminalcase.getCaseDate()+"' ");
			}
			
			if(criminalcase.getAccusation() != null && !criminalcase.getAccusation().equals("")){
				hql.append("and accusation like '%"+criminalcase.getAccusation()+"%' ");
			}
			
			if(criminalcase.getVcCompanyId() != null && !"".equals(criminalcase.getVcCompanyId()))
			{
				hql.append("and vc_company_id in ( " + criminalcase.getVcCompanyId() +")");
			}
			else{
				hql.append("and vc_company_id in (" + criminalcase.getDataauth() + ")");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	@Override
	public Integer getCriminalcaseCount(BimrCriminalcaseWeek criminalcase, Integer offset, Integer pageSize) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from bimr_criminalcase_week s where 1=1 and is_del = 0 and is_history = 0 and is_lastest = 1 ");
		if(criminalcase != null) {
			if(criminalcase.getCaseDate() != null && !criminalcase.getCaseDate().equals("")){
				hql.append("and case_date = '"+criminalcase.getCaseDate()+"' ");
			}
			if(criminalcase.getApprovalState() != null && !criminalcase.getApprovalState().equals("")){
				hql.append("and approval_state = '"+criminalcase.getApprovalState()+"' ");
			}
			if(criminalcase.getVcCompanyId() != null && !"".equals(criminalcase.getVcCompanyId()))
			{
				hql.append("and vc_company_id in ( " + criminalcase.getVcCompanyId() +")");
			}
			else{
				hql.append("and vc_company_id in (" + criminalcase.getDataauth() + ")");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	@Override
	public Integer getCriminalcaseCountByUserId(BimrCriminalcaseWeek criminalcase, Integer offset, Integer pageSize, String creatorId) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from bimr_criminalcase_week s where 1=1 and is_del = 0 and is_history = 0 and is_lastest = 1 and create_person_id = '"+creatorId+"' ");
		if(criminalcase != null) {
			if(criminalcase.getCaseDate() != null && !criminalcase.getCaseDate().equals("")){
				hql.append("and case_date = '"+criminalcase.getCaseDate()+"' ");
			}
			if(criminalcase.getApprovalState() != null && !criminalcase.getApprovalState().equals("")){
				hql.append("and approval_state = '"+criminalcase.getApprovalState()+"' ");
			}
			if(criminalcase.getVcCompanyId() != null && !"".equals(criminalcase.getVcCompanyId()))
			{
				hql.append("and vc_company_id in ( " + criminalcase.getVcCompanyId() +")");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}
	@Override
	public List<BimrCriminalcaseWeek> getCriminalcaseQueryList(
			BimrCriminalcaseWeek criminalcase, Integer offset, Integer pageSize) {
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCriminalcaseWeek s where 1=1 and s.isDel = 0 and isHistory = 0 and isLastest = 1 ");
		if(criminalcase != null) {
			if(criminalcase.getCaseDate() != null && !criminalcase.getCaseDate().equals("")){
				hql.append("and case_date = '"+criminalcase.getCaseDate()+"' ");
			}
			
			if(criminalcase.getAccusation() != null && !criminalcase.getAccusation().equals("")){
				hql.append("and accusation like '%"+criminalcase.getAccusation()+"%' ");
			}
			
			if(criminalcase.getVcCompanyId() != null && !"".equals(criminalcase.getVcCompanyId()))
			{
				hql.append("and vc_company_id in ( " + criminalcase.getVcCompanyId() +")");
			}
			else{
				hql.append("and vc_company_id in (" + criminalcase.getDataauth() + ")");
			}
		}
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
	public List<BimrCriminalcaseWeek> getCriminalcaseList(BimrCriminalcaseWeek criminalcase, Integer offset, Integer pageSize){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCriminalcaseWeek s where 1=1 and s.isDel = 0 and isHistory = 0 and isLastest = 1 ");
		if(criminalcase != null) {
			if(criminalcase.getCaseDate() != null && !criminalcase.getCaseDate().equals("")){
				hql.append("and caseDate = '"+criminalcase.getCaseDate()+"' ");
			}
			if(criminalcase.getApprovalState() != null && !criminalcase.getApprovalState().equals("")){
				hql.append("and approvalState = '"+criminalcase.getApprovalState()+"' ");
			}
			if(criminalcase.getVcCompanyId() != null && !"".equals(criminalcase.getVcCompanyId()))
			{
				hql.append("and vc_company_id in ( " + criminalcase.getVcCompanyId() +")");
			}
			else{
				hql.append("and vcCompanyId in (" + criminalcase.getDataauth() + ")");
			}
		}
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
	public List<BimrCriminalcaseWeek> getCriminalcaseListByUserId(BimrCriminalcaseWeek criminalcase, Integer offset, Integer pageSize, String creatorId){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCriminalcaseWeek s where 1=1 and s.isDel = 0 and isHistory = 0 and isLastest = 1 and createPersonId = '"+creatorId+"' ");
		if(criminalcase != null) {
			if(criminalcase.getCaseDate() != null && !criminalcase.getCaseDate().equals("")){
				hql.append("and caseDate = '"+criminalcase.getCaseDate()+"' ");
			}
			if(criminalcase.getApprovalState() != null && !criminalcase.getApprovalState().equals("")){
				hql.append("and approvalState = '"+criminalcase.getApprovalState()+"' ");
			}
			if(criminalcase.getVcCompanyId() != null && !"".equals(criminalcase.getVcCompanyId()))
			{
				hql.append("and vc_company_id in ( " + criminalcase.getVcCompanyId() +")");
			}
		}
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
	public void deleteCriminalcase(Integer id){
		StringBuilder hql = new StringBuilder();
		hql.append("update bimr_criminalcase_week set is_del = 1,is_lastest = 0 where id=" + id);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		query.executeUpdate();
	}
	
	@Override
	public BimrCriminalcaseWeek getCriminalcaseById(Integer id){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCriminalcaseWeek s where id = "+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (BimrCriminalcaseWeek)query.list().get(0);
	}
	
	@Override
	public BimrCriminalcaseWeek getLastCriminalcaseByCaseNum(String CaseNum){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCriminalcaseWeek s where caseNum = '"+CaseNum+"' ");
		hql.append(" and isHistory = 0 and approvalState = 2 and caseNum != '' order by version desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List list = query.list();
		if(list.size() > 0){
			return (BimrCriminalcaseWeek)list.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public List getCriminalcaseByCaseNum(String CaseNum){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCriminalcaseWeek s where caseNum = '"+CaseNum+"' ");
		hql.append(" and isDel = 0");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List list = query.list();
		return list;
	}
	
	@Override
	public List getCriminalcaseLikeCaseNum(String CaseNum){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCriminalcaseWeek s where caseNum like '"+CaseNum+"%' ");
		hql.append(" and isDel = 0 and isLastest = 1 ");// and version != ''
		hql.append(" order by caseNum desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List list = query.list();
		return list;
	}
	
	@Override
	public Integer getComprehensiveQueryCount(BimrCaseQuery caseQuery, Integer offset, Integer pageSize) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from v_bimrCase where 1=1 ");
		if(caseQuery != null){
			if(caseQuery.getCaseDate() != null && !caseQuery.getCaseDate().equals("")){
				hql.append("and caseDate = '"+caseQuery.getCaseDate()+"' ");
			}
			/*if(caseQuery.getVcCompanyName() != null && !caseQuery.getVcCompanyName().equals("")){
			hql.append("and vcCompanyName = '"+caseQuery.getVcCompanyName()+"' ");
			}*/
			if (Common.notEmpty(caseQuery.getVcCompanyId())) {
				hql.append("and vcCompanyId in (" + caseQuery.getVcCompanyId() + ") ");
			}
			if(caseQuery.getYear()!=null&& !caseQuery.getYear().equals("")){
				hql.append("and year = '"+caseQuery.getYear()+"' ");
			}else {
				hql.append("and year = '' ");
			}
			if(caseQuery.getWeek()!=null&& !caseQuery.getWeek().equals("")){
				hql.append("and week = '"+caseQuery.getWeek()+"' ");
			}else {
				hql.append("and week = '' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	@Override
	public List getComprehensiveQuery(BimrCaseQuery caseQuery, Integer offset, Integer pageSize){
		StringBuilder hql = new StringBuilder();
/*		hql.append("select cl.id as id,cl.case_num as caseNum,cl.case_category as caseCategory,cl.vc_company_name as vcCompanyName,cl.type as type,");
		hql.append("cl.province as province,cl.amount as amount,cl.case_date as caseDate from bimr_civilcase_week cl where cl.is_del = 0 and cl.is_history = 0 and cl.approval_state = 2");
		hql.append(" union all ");
		hql.append("select cm.id as id,cm.case_num as caseNum,cm.case_category as caseCategory,cm.vc_company_name as vcCompanyName,cm.type as type,");
		hql.append("cm.province as province,cm.amount as amount,cm.case_date as caseDate from bimr_criminalcase_week cm where cm.is_del = 0 and cm.is_history = 0 and cm.approval_state = 2");*/
		hql.append("select * from v_bimrCase where 1=1 ");
		if(caseQuery != null){
			if(caseQuery.getCaseDate() != null && !caseQuery.getCaseDate().equals("")){
				hql.append("and caseDate = '"+caseQuery.getCaseDate()+"' ");
			}
			/*if(caseQuery.getVcCompanyName() != null && !caseQuery.getVcCompanyName().equals("")){
				hql.append("and vcCompanyName = '"+caseQuery.getVcCompanyName()+"' ");
			}*/
			if (Common.notEmpty(caseQuery.getVcCompanyId())) {
				hql.append("and vcCompanyId in (" + caseQuery.getVcCompanyId() + ") ");
			}
			if(caseQuery.getYear()!=null&& !caseQuery.getYear().equals("")){
				hql.append("and year = '"+caseQuery.getYear()+"' ");
			}else {
				hql.append("and year = '' ");
			}
			if(caseQuery.getWeek()!=null&& !caseQuery.getWeek().equals("")){
				hql.append("and week = '"+caseQuery.getWeek()+"' ");
			}else {
				hql.append("and week = '' ");
			}
		}
		hql.append("order by caseDate desc");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offset != null){
			query.setFirstResult(offset);
		}	
		if(pageSize != null){
			query.setMaxResults(pageSize);
		}	
		List list = query.list();
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	
	@Override
	public List getCaseReport(String year, String week){
		StringBuilder hql = new StringBuilder();
		hql.append("select * from bimr_case_report where 1=1 ");
		hql.append("and year = '"+year+"' ");
		hql.append("and week = '"+week+"' ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}
	
	@Override
	public List getCivilcaseIdsList(String dataauth){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCivilcaseWeek s where s.isDel = 0 and s.isHistory = 0 and is_lastest = 1 and s.approvalState in (1,2,3) ");
		if (Common.notEmpty(dataauth)) {
			hql.append("and vc_company_id in (" + dataauth + ")");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List list = query.list();
		return list;
	}
	
	@Override
	public List getCriminalcaseIdsList(String dataauth){
		StringBuilder hql = new StringBuilder();
		hql.append("from BimrCriminalcaseWeek s where s.isDel = 0 and s.isHistory = 0 and is_lastest = 1 and s.approvalState in (1,2,3) ");
		if (Common.notEmpty(dataauth)) {
			hql.append("and vc_company_id in (" + dataauth + ")");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List list = query.list();
		return list;
	}

		
//	 
		@Override
		public List<BimrCivilcaseWeek> getcivilCaseExport(BimrCivilcaseWeek civilcase, String caseDateStart,String caseDateEnd,String amountSection,int type) {
			// TODO Auto-generated method stub
			StringBuilder hql = new StringBuilder();
			hql.append("from BimrCivilcaseWeek s where 1=1 and s.isDel = 0 and isHistory = 0 and isLastest = 1 ");
			hql.append("and approval_state in (1,2,3) ");
			if(civilcase != null){
				if(caseDateStart != null && !caseDateStart.equals("")){
					hql.append("and case_date >= '"+caseDateStart+"' ");
				}
				if(caseDateEnd != null && !caseDateEnd.equals("")){
					hql.append("and case_date <= '"+caseDateEnd+"' ");
				}
				if(civilcase.getMediatingStage() != null && !civilcase.getMediatingStage().equals("")){
					hql.append("and mediating_stage = '"+civilcase.getMediatingStage()+"' ");
				}
				if(civilcase.getType() != null && !civilcase.getType().equals("")){
					hql.append("and type = '"+civilcase.getType()+"' ");
				}
				
				/*if(civilcase.getVcCompanyName() != null && !civilcase.getVcCompanyName().equals("")){
					hql.append("and vc_company_name = '"+civilcase.getVcCompanyName()+"' ");
				}*/
				
//				if(civilcase.getReason() != null && !civilcase.getReason().equals("")){
//					hql.append("and reason like '%"+civilcase.getReason()+"%' ");
//				}
				if(Common.notEmpty(civilcase.getReason())) {
	                hql.append(" and reason like '%"+civilcase.getReason()+ "%'");
	            }
				if(amountSection != null && !amountSection.equals("")){
					
					if(amountSection.equals("1")){
						
						hql.append("and amount <10 ");
					}
					if(amountSection.equals("2")){
						hql.append("and amount between 10 And 50 ");
					}
					if(amountSection.equals("3")){
						hql.append("and amount between 50 And 100 ");
					}
					if(amountSection.equals("4")){
						hql.append("and amount between 100 And 500 ");
					}
					if(amountSection.equals("5")){
						hql.append("and amount between 500 And 1000 ");
					}
					if(amountSection.equals("6")){
						hql.append("and amount > 1000 ");
					}
					
				}
//				是否问责
				if(civilcase.getIsAccountability() != null && !civilcase.getIsAccountability().equals("")){
					
						hql.append("and is_accountability = '"+civilcase.getIsAccountability()+"' ");										
				}
//				是否进行成因分析
				if(civilcase.getIsAnalysis() != null && !civilcase.getIsAnalysis().equals("")){
					
					hql.append("and is_analysis = '"+civilcase.getIsAnalysis()+"' ");										
			}
//				判决结果verdictResult
				
				if(Common.notEmpty(civilcase.getVerdictResult())) {
	                hql.append(" and verdict_result like '%"+civilcase.getVerdictResult()+ "%'");
	            }
//				原告/申请人/第三人  模糊查询
				if(Common.notEmpty(civilcase.getPlaintiff())) {
	                hql.append(" and plaintiff like '%"+civilcase.getPlaintiff()+ "%'");
	            }
//				被告/被申请人/第三人 （多人）模糊查询  defendant
				if(Common.notEmpty(civilcase.getDefendant())) {
	                hql.append(" and defendant like '%"+civilcase.getDefendant()+ "%'");
	            }
//				基本案情
				if(Common.notEmpty(civilcase.getBaseMessage())) {
	                hql.append(" and base_message like '%"+civilcase.getBaseMessage()+ "%'");
	            }
//				承办法务
				if(Common.notEmpty(civilcase.getLawWork())) {
	                hql.append(" and law_work like '%"+civilcase.getLawWork()+ "%'");
	            }
//				承办法官/仲裁员
				if(Common.notEmpty(civilcase.getJudge())) {
	                hql.append(" and judge like '%"+civilcase.getJudge()+ "%'");
	            }
				
//				受案单位所在地province
				
				if(Common.notEmpty(civilcase.getProvince())) {
	                hql.append(" and province like '%"+civilcase.getProvince()+ "%'");
	            }
//				受案机构
				if(Common.notEmpty(civilcase.getAdmissibleCourt())) {
	                hql.append(" and admissible_court like '%"+civilcase.getAdmissibleCourt()+ "%'");
	            }
//				审核状态
				if(civilcase.getApprovalState() != null && !civilcase.getApprovalState().equals("")){
					hql.append("and approvalState = '"+civilcase.getApprovalState()+"' ");
				}
				
				if(civilcase.getLitigation() != null && !civilcase.getLitigation().equals("")){
					hql.append("and litigation = '"+civilcase.getLitigation()+"' ");
				}
				
				if(civilcase.getIsMajorCase() != null && !civilcase.getIsMajorCase().equals("")){
					hql.append("and is_major_case = '"+civilcase.getIsMajorCase()+"' ");
				}
				
				if(civilcase.getVcCompanyId() != null && !"".equals(civilcase.getVcCompanyId()))
				{
					hql.append("and vc_company_id in ( " + civilcase.getVcCompanyId() +") ");
				}
				else{
					hql.append("and vc_company_id in (" + civilcase.getDataauth() + ") ");
				}
				
				if(civilcase.getLitigation() != null && !civilcase.getLitigation().equals("")){
					hql.append(" and litigation = '"+civilcase.getLitigation()+"' ");
				}
				
				if(civilcase.getIsMajorCase() != null && !civilcase.getIsMajorCase().equals("")){
					hql.append("and is_major_case = '"+civilcase.getIsMajorCase()+"' ");
				}
				
				switch (type) {
				case 1:
					hql.append("and mediating_stage in (1,2,3)");
					break;
				case 2:
					hql.append("and mediating_stage in (4)");
					break;
				case 3:
					hql.append("and mediating_stage in (5)");
					break;
				default:
					break;
				}
			
			}
				
				
				
				
			hql.append(" order by s.createDate desc ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			
			return query.list();
//		}
//		
//	
//		return null;
	}
		
//		刑事
		@Override
		public List<BimrCriminalcaseWeek> getCriminalCaseExport(BimrCriminalcaseWeek criminalcase,Integer state) {
			// TODO Auto-generated method stub
			StringBuilder hql = new StringBuilder();
			hql.append("from BimrCriminalcaseWeek s where 1=1 and s.isDel = 0 and isHistory = 0 and isLastest = 1 ");
			hql.append("and approval_state in (1,2,3) ");
			if(criminalcase != null) {
				if(criminalcase.getCaseDate() != null && !criminalcase.getCaseDate().equals("")){
					hql.append("and case_date = '"+criminalcase.getCaseDate()+"' ");
				}
				
				if(criminalcase.getAccusation() != null && !criminalcase.getAccusation().equals("")){
					hql.append("and accusation like '%"+criminalcase.getAccusation()+"%' ");
				}
				
				if(criminalcase.getVcCompanyId() != null && !"".equals(criminalcase.getVcCompanyId()))
				{
					hql.append("and vc_company_id in ( " + criminalcase.getVcCompanyId() +")");
				}
				else{
					hql.append("and vc_company_id in (" + criminalcase.getDataauth() + ")");
				}
			}
			if(1==state){
				hql.append("and ( closingNumber is null or closingNumber = '' )");
			}else {
				hql.append("and ( closingNumber is not null and closingNumber != '' )");
			}
			hql.append(" order by s.createDate desc ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return query.list();
		
	}

		@Override
		public List<BimrCivilcaseWeek> getcivilCaseNewWeek(String ids) {
			// TODO Auto-generated method stub
			StringBuilder hql = new StringBuilder();
			hql.append("from BimrCivilcaseWeek s where 1=1 and s.id in ("+ids+")");
			
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			
			return query.list();
		}

		@Override
		public String getcivilCaseNewWeekIDs() {
			// TODO Auto-generated method stub
			StringBuilder hql = new StringBuilder();
			hql.append("select b.civilcase_ids from bimr_case_report b order by (b.year+0) desc,(b.week+0) desc limit 0,1 ");
			
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			
			return query.list().get(0).toString();
		}

		@Override
		public List<BimrCriminalcaseWeek> getCriminalcaseNewWeek(String ids) {
			// TODO Auto-generated method stub
			StringBuilder hql = new StringBuilder();
			hql.append("from BimrCriminalcaseWeek s where 1=1 and s.id in ("+ids+")");
			
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			
			return query.list();
		}

		@Override
		public String getCriminalCaseNewWeekIDs() {
			// TODO Auto-generated method stub
			StringBuilder hql = new StringBuilder();
			hql.append("select b.criminalcase_ids from bimr_case_report b order by (b.year+0) desc,(b.week+0) desc limit 0,1 ");
			
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			
			return query.list().get(0).toString();
		}

		@Override
		public int saveBimrCaseReporttemp(BimrCaseReport bimrCaseReport,String type,String cid,int month) {
			// TODO Auto-generated method stub
			StringBuilder sql = new StringBuilder();
			sql.append("insert into bimr_case_report_temp values(0,?,?,?,?,?,?)");
			
			Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
			query.setParameter(0, bimrCaseReport.getYear());
			query.setParameter(1, bimrCaseReport.getWeek());
			query.setParameter(2, bimrCaseReport.getId());
			query.setParameter(3, type);
			query.setParameter(4, cid);
			query.setParameter(5, month);
			return query.executeUpdate();
		}
		
		
		@Override
		public List getLastWeekData(String year) {
			// TODO Auto-generated method stub
			StringBuilder hql = new StringBuilder();
			hql.append("select b.* from bimr_case_report b where 1=1 ");
			if (Common.notEmpty(year)) {
				hql.append(" and b.year = '"+year+"' ");
			}
			hql.append(" order by (b.year+0) desc,(b.week+0) desc limit 0,1 ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			
			return query.list();
		}

		@Override
		public List<BimrCivilcaseWeek> getcivilNewCaseExport(String ids,
				int type) {
			// TODO Auto-generated method stub
			StringBuilder hql = new StringBuilder();
			hql.append("from BimrCivilcaseWeek s where 1=1 and s.id in ("+ids+")");
			switch (type) {
			case 1:
				hql.append("and s.mediatingStage in (1,2,3)");
				break;
			case 2:
				hql.append("and s.mediatingStage in (4)");
				break;
			case 3:
				hql.append("and s.mediatingStage in (5)");
				break;
			default:
				break;
			}
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return query.list();
		}
		
		@Override
		public List<BimrCriminalcaseWeek> getCriminalNewCaseExport(String ids,
				int type) {
			// TODO Auto-generated method stub
			StringBuilder hql = new StringBuilder();
			hql.append("from BimrCriminalcaseWeek s where 1=1 and s.id in ("+ids+")");
			if(1==type){
				hql.append("and ( closingNumber is null or closingNumber = '' )");
			}else {
				hql.append("and ( closingNumber is not null and closingNumber != '' )");
			}
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return query.list();
		}
		@Override
		public List getCaseReportExport(BimrCaseQuery caseQuery,int type){
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT t.case_id from bimr_case_report_temp t LEFT JOIN bimr_civilcase_week c on c.id = t.case_id where t.type = "+type);
			if (Common.notEmpty(caseQuery.getYear())) {
				hql.append(" and t.year = '"+caseQuery.getYear()+"' ");
			}
			if (Common.notEmpty(caseQuery.getWeek())) {
				hql.append(" and t.week = '"+caseQuery.getWeek()+"' ");
			}
			if (Common.notEmpty(caseQuery.getVcCompanyId())) {
				hql.append(" and c.vc_company_id in ("+caseQuery.getVcCompanyId()+") ");
			}
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
			return query.list();
		}
}

	
