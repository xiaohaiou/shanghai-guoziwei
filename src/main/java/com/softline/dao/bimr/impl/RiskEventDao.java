package com.softline.dao.bimr.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.bimr.IRiskEventDao;
import com.softline.entity.bimr.BimrRiskEvent;
import com.softline.entity.bimr.BimrRiskEventAdoptstrategy;
import com.softline.entity.bimr.BimrRiskEventAdoptstrategyFeedback;
import com.softline.entity.bimr.BimrRiskEventFeedback;
import com.softline.entity.bimr.BimrRiskEventRelevance;
import com.softline.entity.bimr.BimrRiskEventRelevancetrack;

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
@Repository("riskEventDao")
public class RiskEventDao implements IRiskEventDao {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public int getRiskEventListCount(BimrRiskEvent entity,Integer highunitmeasure) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from BimrRiskEvent s where 1=1 and s.isDel = 0 ");
		if(null!=highunitmeasure){
			hql.append("and s.highunitmeasure = "+highunitmeasure+" and s.ishighunit = 1 ");
		}
		//条件
		getRiskEventListCondition(entity,hql);
		
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	public int getRiskEventMeasureListCount(BimrRiskEvent entity,String insidetrackpersonid) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from BimrRiskEvent s where 1=1 and s.isDel = 0 ");
		hql.append(" and s.insidetrackpersonid ="+insidetrackpersonid+" and s.ishighunit = 1 ");
		//条件
		getRiskEventListCondition(entity,hql);
		
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	public List<BimrRiskEvent> getRiskEventList(
			BimrRiskEvent entity, Integer offsize, Integer pagesize,Integer highunitmeasure) {
		if (entity == null) {
			return new ArrayList<BimrRiskEvent>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEvent s where 1=1 and isDel = 0 ");
		//判断处理方式
		if(null!=highunitmeasure){
			hql.append("and s.highunitmeasure = "+highunitmeasure+" and s.ishighunit = 1 ");
		}
		//条件
		getRiskEventListCondition(entity,hql);
		
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
	
	public List<BimrRiskEvent> getRiskEventMeasureList(
			BimrRiskEvent entity, Integer offsize, Integer pagesize,String insidetrackpersonid) {
		if (entity == null) {
			return new ArrayList<BimrRiskEvent>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEvent s where 1=1 and isDel = 0 ");
		hql.append(" and s.insidetrackpersonid ="+insidetrackpersonid+" and s.ishighunit = 1 ");
		
		//条件
		getRiskEventListCondition(entity,hql);
		
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
	
	@Override
	public List<BimrRiskEvent> getRiskEventByCode(String code,int id) {
		String hql = "from BimrRiskEvent s where s.isDel = 0 ";
		hql += " and s.id !="+id+" ";
    	hql += " and s.eventNum like '"+code+"%' order by s.eventNum desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
	}
	
	@Override
	public int getRiskFeedBackListCount(BimrRiskEvent entity) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from BimrRiskEvent s where 1=1 and isdel=0 ");
		
		if (entity != null) {
			if (Common.notEmpty(entity.getCompId())) {
				hql.append(" and s.compId in (" + entity.getCompId() + ") ");
			}else{
				hql.append("and s.compId in (" + entity.getDataauth() + ")");
			}
			//如果是风险征兆事件反馈界面要看到的是 事件已审核未跟踪 以上的状态。
			if (Common.notEmpty(entity.getOp()) && entity.getOp().equals("feedback")) {
				hql.append(" and s.status  in (82102,82108,82109) ");
			}
			//如果是风险征兆事件反馈'审核'界面要看到的是 事件跟踪中 以上的状态。
			if (Common.notEmpty(entity.getOp()) && entity.getOp().equals("feedbackexamine")) {
				hql.append(" and s.status  in (82108,82109) ");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (Common.notEmpty(entity.getEventNum())) {
				hql.append(" and s.eventNum like '%" + entity.getEventNum() + "%' ");
			}
			if (Common.notEmpty(entity.getEventTitle())) {
				hql.append(" and s.eventTitle like '%" + entity.getEventTitle() + "%' ");
			}
//			应对策略
			if (entity.getCopingStrategy()!=null) {
				hql.append(" and s.copingStrategy = " + entity.getCopingStrategy() + " ");
			}
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<BimrRiskEvent> getRiskFeedBackList(BimrRiskEvent entity,
			Integer offsize, Integer pagesize) {
		if (entity == null) {
			return new ArrayList<BimrRiskEvent>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEvent s where 1=1 and isdel=0 ");
		
		if (entity != null) {
			if (Common.notEmpty(entity.getCompId())) {
				hql.append(" and s.compId in (" + entity.getCompId() + ") ");
			}else{
				hql.append("and s.compId in (" + entity.getDataauth() + ")");
			}
			//如果是风险征兆事件反馈界面要看到的是 事件已审核未跟踪 以上的状态。
			if (Common.notEmpty(entity.getOp()) && entity.getOp().equals("feedback")) {
				hql.append(" and s.status  in (82102,82108,82109) ");
			}
			//如果是风险征兆事件反馈'审核'界面要看到的是 事件跟踪中 以上的状态。
			if (Common.notEmpty(entity.getOp()) && entity.getOp().equals("feedbackexamine")) {
				hql.append(" and s.status  in (82108,82109) ");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (Common.notEmpty(entity.getEventNum())) {
				hql.append(" and s.eventNum like '%" + entity.getEventNum() + "%' ");
			}
			if (Common.notEmpty(entity.getEventTitle())) {
				hql.append(" and s.eventTitle like '%" + entity.getEventTitle() + "%' ");
			}
			if (entity.getCopingStrategy()!=null) {
				hql.append(" and s.copingStrategy = " + entity.getCopingStrategy() + " ");
			}
		}
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
	
	
	@Override
	public int getExamineTracklistCount(BimrRiskEventRelevancetrack entity) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT count(0)  from bimr_risk_event r ");
		hql.append(" INNER JOIN bimr_risk_event_relevancetrack t on r.id=t.eventid and t.is_del=0 and t.status in (82104,82105)  ");
		hql.append(" where 1=1 ");
		if (Common.notEmpty(entity.getCompId())) {
			hql.append(" and r.compId in (" + entity.getCompId() + ") ");
		}else{
			hql.append("and r.compId in (" + entity.getDataauth() + ")");
		}
		if (entity.getStatus() != null) {
			hql.append(" and t.status = " + entity.getStatus() + " ");
		}
		if (Common.notEmpty(entity.getEventNum())) {
			hql.append(" and r.eventNum like '%" + entity.getEventNum() + "%' ");
		}
		if (Common.notEmpty(entity.getEventTitle())) {
			hql.append(" and r.eventTitle like '%" + entity.getEventTitle() + "%' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Object> getExamineTracklist(BimrRiskEventRelevancetrack entity,
			Integer offsize, Integer pagesize) {
		if (entity == null) {
			return new ArrayList<Object>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT t.id,t.eventid,t.takestrategy,t.takestrategytxt,t.takestrategyremark,r.reportTime,r.compName,r.eventTitle,r.eventNum,t.status from bimr_risk_event r  ");
		hql.append(" INNER JOIN bimr_risk_event_relevancetrack t on r.id=t.eventid and t.is_del=0 and t.status !=82103  ");
		hql.append(" where 1=1 ");
		if (Common.notEmpty(entity.getCompId())) {
			hql.append(" and r.compId in (" + entity.getCompId() + ") ");
		}else{
			hql.append("and r.compId in (" + entity.getDataauth() + ")");
		}
		if (entity.getStatus() != null) {
			hql.append(" and t.status = " + entity.getStatus() + " ");
		}
		if (Common.notEmpty(entity.getEventNum())) {
			hql.append(" and r.eventNum like '%" + entity.getEventNum() + "%' ");
		}
		if (Common.notEmpty(entity.getEventTitle())) {
			hql.append(" and r.eventTitle like '%" + entity.getEventTitle() + "%' ");
		}
		hql.append(" order by t.createDate desc ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if (offsize != null) {
			query.setFirstResult(offsize);
		}
		if (pagesize != null) {
			query.setMaxResults(pagesize);
		}
		return query.list();
	}
	
	private void getRiskEventListCondition(BimrRiskEvent entity,StringBuilder hql){
		if (entity != null) {
			if (Common.notEmpty(entity.getCompId())) {
				hql.append(" and s.compId in  (" + entity.getCompId() + ") ");
			}else{
				hql.append("and s.compId in (" + entity.getDataauth() + ")");
			}
			//如果是风险事件审核界面 不能看到待上报的。
			if (Common.notEmpty(entity.getOp()) && entity.getOp().equals("listexamine")) {
				hql.append(" and s.status != 82100 ");
			}
			//如果是关联目录关联界面 事件审核必须是事件已审核以上状态
			if (Common.notEmpty(entity.getOp()) && entity.getOp().equals("relevancelist")) {
				hql.append(" and s.status not in (82100,82101,82106)  ");
			}
			//如果是关联目录审核界面 只能看到目录关联状态待审核 和 已审核，且事件审核必须是事件已审核以上状态
			if (Common.notEmpty(entity.getOp()) && entity.getOp().equals("relevanceexamine")) {
				hql.append(" and s.status not in (82100,82101,82106)  ");
				hql.append(" and s.relevancestatus !=1 and  s.relevancestatus !=2 ");
			}
			if (entity.getRelevancestatus() != null) {
				
				hql.append(" and s.relevancestatus = " + entity.getRelevancestatus() + " ");
			}
			
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (Common.notEmpty(entity.getReportTime())) {
				hql.append(" and s.reportTime like '" + entity.getReportTime() + "%' ");
			}
			if (Common.notEmpty(entity.getEventNum())) {
				hql.append(" and s.eventNum like '%" + entity.getEventNum() + "%' ");
			}
			if (Common.notEmpty(entity.getEventTitle())) {
				hql.append(" and s.eventTitle like '%" + entity.getEventTitle() + "%' ");
			}
			
			if (entity.getCopingStrategy()!=null) {
				hql.append(" and s.copingStrategy = " + entity.getCopingStrategy() + " ");
			}
		}
	}
	
	public BimrRiskEvent getRiskEventById(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEvent s where id="+id+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (BimrRiskEvent)query.uniqueResult();
	}
	
	public List<BimrRiskEventAdoptstrategy> getAdoptstrategy(Integer eventId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEventAdoptstrategy where eventId="+eventId+" and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}

	@Override
	public List<BimrRiskEventFeedback> getRiskEventFeedbackList(Integer eventId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEventFeedback where eventid="+eventId+" and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}
	
	@Override
	public List<BimrRiskEventAdoptstrategyFeedback> getAdoptstrategyFeedbcakList(
			Integer eventId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEventAdoptstrategyFeedback where eventId="+eventId+" and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}
/*	@Override
	public int getRiskEventResultListCount(BimrRiskEvent entity) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from BimrRiskEvent s where 1=1 and isdel=0 ");
		if (entity != null) {

			if (Common.notEmpty(entity.getCoreorg())) {
				hql.append(" and s.coreorg = '" + entity.getCoreorg() + "' ");
			}
			if (Common.notEmpty(entity.getReportTime())) {
				hql.append(" and s.reportTime like '" + entity.getReportTime() + "%' ");
			}
			
		}
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}*/

/*	@Override
	public List<BimrRiskEvent> getRiskEventResultList(BimrRiskEvent entity,
			Integer offsize, Integer pagesize) {
		if (entity == null) {
			return new ArrayList<BimrRiskEvent>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT a.com a.compId,b.compId,c.compId from bimr_risk_event a ");
		hql.append(" LEFT JOIN bimr_risk_event b on a.coreorg=b.coreorg and b.reportTime LIKE '2018-04%' ");
		hql.append(" SELECT a.com a.compId,b.compId,c.compId from bimr_risk_event a ");
		hql.append(" SELECT a.com a.compId,b.compId,c.compId from bimr_risk_event a ");
		if (entity != null) {

		}
		
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
	}*/
	
	@Override
	public List<Object> getRiskEventRelevanceList(BimrRiskEvent entity,
			Integer offsize, Integer pagesize) {
		if (entity == null) {
			return new ArrayList<Object>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT a.reportTime,a.eventTitle,a.compName,GROUP_CONCAT(b.levelthreename),a.relevancePerson,a.relevanceauditor,a.relevancestatus,a.id,a.eventNum from bimr_risk_event a  ");
		hql.append(" LEFT JOIN bimr_risk_event_relevance b on a.id=b.eventid where 1=1 ");
		if (Common.notEmpty(entity.getCompId())) {
			hql.append(" and a.compId in (" + entity.getCompId() + ") ");
		}else{
			hql.append("and a.compId in (" + entity.getDataauth() + ")");
		}
		//如果是关联目录界面 事件审核必须是事件已审核以上状态
		hql.append(" and a.status not in (82100,82101,82106)  ");
		if (entity.getRelevancestatus() != null) {
			hql.append(" and a.relevancestatus = " + entity.getRelevancestatus() + " ");
		}
		if (Common.notEmpty(entity.getEventTitle())) {
			hql.append(" and a.eventTitle like '%" + entity.getEventTitle() + "%' ");
		}
		if (Common.notEmpty(entity.getReportTime())) {
			hql.append(" and a.reportTime like '" + entity.getReportTime() + "%' ");
		}
		//如果是审核界面 只能看到待审核 和 已审核
		if (Common.notEmpty(entity.getOp()) && entity.getOp().equals("examine")) {
			hql.append(" and a.relevancestatus !=1 and  a.relevancestatus !=2 ");
		}
		hql.append(" GROUP BY a.id ");
		hql.append(" order by a.createDate desc ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if (offsize != null) {
			query.setFirstResult(offsize);
		}
		if (pagesize != null) {
			query.setMaxResults(pagesize);
		}
		return query.list();
	}

	@Override
	public List<BimrRiskEventRelevance> getRelevanceList(Integer eventId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEventRelevance where eventid="+eventId+" and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}

	@Override
	public int getRiskEventSecondListCount(BimrRiskEventRelevance entity) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append(" select  count(0) from ( ");
		hql.append(" SELECT a.id as id1, a.createDate as createDate, b.name as bname,b.id as bid,c.name as cname,c.id as cid ,c.define as cdefine,COUNT(a.id) as count from bimr_risk_event_relevance a ");
		hql.append(" INNER JOIN DIM_LIST_MANAGE_RISK b on a.leveloneid = b.id ");
		hql.append(" INNER JOIN DIM_LIST_MANAGE_RISK c on a.leveltwoid = c.id  ");
		hql.append(" INNER JOIN bimr_risk_event d on a.eventid = d.id where 1=1 ");
		if (Common.notEmpty(entity.getCompanyId())) {
			hql.append(" and d.compId in (" + entity.getCompanyId() + ") ");
		}else{
			hql.append("and d.compId in (" + entity.getDataauth() + ")");
		}
		if (entity.getLeveloneid() != null) {
			hql.append(" and a.leveloneid ="+entity.getLeveloneid()+" ");
		}
		if (Common.notEmpty(entity.getCreateDate())) {
			hql.append(" and a.createDate like '%" + entity.getCreateDate() + "%' ");
		}
		hql.append(" GROUP BY a.leveloneid,a.leveltwoid ");
		hql.append(" order by a.createDate desc ) c");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Object> getRiskEventSecondList(BimrRiskEventRelevance entity,Integer offsize, Integer pagesize) {
		if (entity == null) {
			return new ArrayList<Object>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT a.id as id1, a.createDate as createDate, b.name as bname,b.id as bid,c.name as cname,c.id as cid ,c.define as cdefine,COUNT(a.id) as count from bimr_risk_event_relevance a ");
		hql.append(" INNER JOIN DIM_LIST_MANAGE_RISK b on a.leveloneid = b.id ");
		hql.append(" INNER JOIN DIM_LIST_MANAGE_RISK c on a.leveltwoid = c.id  ");
		hql.append(" INNER JOIN bimr_risk_event d on a.eventid = d.id where 1=1 ");
		if (Common.notEmpty(entity.getCompanyId())) {
			hql.append(" and d.compId in " + entity.getCompanyId() + ") ");
		}else{
			hql.append("and d.compId in (" + entity.getDataauth() + ")");
		}
		if (entity.getLeveloneid() != null) {
			hql.append(" and a.leveloneid ="+entity.getLeveloneid()+" ");
		}
		if (Common.notEmpty(entity.getCreateDate())) {
			hql.append(" and a.createDate like '%" + entity.getCreateDate() + "%' ");
		}
		hql.append(" GROUP BY a.leveloneid,a.leveltwoid ");
		hql.append(" order by a.createDate desc ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if (offsize != null) {
			query.setFirstResult(offsize);
		}
		if (pagesize != null) {
			query.setMaxResults(pagesize);
		}
		return query.list();
	}

	@Override
	public int getRiskEventSecondListforDetailCount(
			BimrRiskEventRelevance entity,String idString) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from BimrRiskEvent s where 1=1 and isDel=0  ");
		if (Common.notEmpty(idString)) {
			hql.append(" and s.id in ( "+idString+") ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<BimrRiskEvent> getRiskEventSecondforDetailList(
			BimrRiskEventRelevance entity, Integer offsize, Integer pagesize,String idString) {
		if (entity == null) {
			return new ArrayList<BimrRiskEvent>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEvent s where 1=1 and isdel=0 ");
		if (Common.notEmpty(idString)) {
			hql.append(" and s.id in ( "+idString+") ");
		}
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

	@Override
	public List<Integer> getRiskEventID(BimrRiskEventRelevance entity) {
		if (entity == null) {
			return new ArrayList<Integer>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT a.eventid from bimr_risk_event_relevance a where 1=1 ");
		if (entity.getLeveltwoid() !=null) {
			hql.append(" and a.leveltwoid ="+entity.getLeveltwoid()+" ");
		}
		if (Common.notEmpty(entity.getCreateDate())) {
			hql.append(" and a.createDate like '" + entity.getCreateDate() + "%' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<BimrRiskEventRelevancetrack> getRelevancetrackList(Integer eventId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEventRelevancetrack where eventid="+eventId+" and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}

	@Override
	public BimrRiskEventRelevancetrack getBimrRiskEventRelevancetrack(
			Integer trackId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEventRelevancetrack s where id="+trackId+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (BimrRiskEventRelevancetrack)query.uniqueResult();
	}

	@Override
	public BimrRiskEventFeedback getBimrRiskEventFeedback(Integer feedbackid) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEventFeedback s where id="+feedbackid+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (BimrRiskEventFeedback)query.uniqueResult();
	}

	@Override
	public BimrRiskEventAdoptstrategyFeedback getAdoptstrategyFeedbcak(
			Integer AdoptstrategyFeedbcakId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEventAdoptstrategyFeedback s where id="+AdoptstrategyFeedbcakId+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (BimrRiskEventAdoptstrategyFeedback)query.uniqueResult();
	}

	@Override
	public BimrRiskEventAdoptstrategyFeedback getBimrRiskEventAdoptstrategyFeedback(Integer feedbackid) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrRiskEventAdoptstrategyFeedback s where id="+feedbackid+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (BimrRiskEventAdoptstrategyFeedback)query.uniqueResult();
	}







	

	

	
	
	
	
}
