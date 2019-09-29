package com.softline.dao.portal.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.portal.IMsgDao;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;

@Repository(value = "msgDao")
public class MsgDao implements IMsgDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public MsgPage getPortalMsgList(PortalMsg portalMsg, Integer curPageNum, Integer pageSize) {
		
		int allRow = getAllRowCount(portalMsg);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		int offset = (curPageNum - 1) * pageSize;
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.list();
	}*/
	
	@Override
	public Integer getAllRowCount(PortalMsg portalMsg) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(t.id) from portal_msg t where t.del_flag = 0 ");
		if(portalMsg.getIsIssue() != null){
			sql.append(" and t.is_issue = " + portalMsg.getIsIssue());
		}
		if(Common.notEmpty(portalMsg.getTitle())){
			sql.append(" and t.title like '%" + portalMsg.getTitle() + "%'");
		}
		if(Common.notEmpty(portalMsg.getDescription())){
			sql.append(" and t.description like '%" + portalMsg.getDescription() + "%'");
		}
		if(Common.notEmpty(portalMsg.getCreator())){
			sql.append(" and t.creator = '" + portalMsg.getCreator() + "'");
		}
		sql.append(" order by t.create_time desc");
		Query query= (Query) sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	@SuppressWarnings("unchecked")
	public List<PortalMsg> getPortalMsgList(PortalMsg portalMsg, int offset, int pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from PortalMsg t where t.delFlag = 0 ");
		if(portalMsg.getIsIssue() != null){
			hql.append(" and t.isIssue = " + portalMsg.getIsIssue());
		}
		if(Common.notEmpty(portalMsg.getTitle())){
			hql.append(" and t.title like '%" + portalMsg.getTitle() + "%'");
		}
		if(Common.notEmpty(portalMsg.getDescription())){
			hql.append(" and t.description like '%" + portalMsg.getDescription() + "%'");
		}
		if(Common.notEmpty(portalMsg.getCreator())){
			hql.append(" and t.creator = '" + portalMsg.getCreator() + "'");
		}
		hql.append(" order by t.createTime desc");
		Query query= (Query) sessionFactory.getCurrentSession().createQuery(hql.toString());    
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);            
		return query.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public Integer updatePortalMsg(String table,String recordId){
		String hql = "update PortalMsg p set p.delFlag = 1 where p.tableName = '" + table + "' and p.recordId = '" + recordId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.executeUpdate();
	}

	@Override
	public PortalMsg getPortMsg(String table, String recordId) {
		String hql =" from PortalMsg t where t.delFlag = 0 and t.tableName = '"+ table+"' and t.recordId = '"+ recordId+ "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (PortalMsg) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<PortalMsg> getProtalMsgList(String session_code,String session_company,HhUsers hhUsers){
		String[] code = session_code.split(",");
		String myCode = "";
		for(int i=0; i<code.length; i++){
			Object obj = code[i];
			if(obj == null || obj.equals("")){
				continue;
			}
			myCode = myCode + "'"+obj.toString()+"'";
			if(i < code.length - 1){
				myCode = myCode + ",";
			}
		}
		String[] company = session_company.split(",");
		String myCompany = "";
		for(int i=0; i<company.length; i++){
			Object obj = company[i];
			if(obj == null || obj.equals("")){
				continue;
			}
			myCompany = myCompany + obj.toString();
			if(i < company.length - 1){
				myCompany = myCompany + ",";
			}
		}
		if(myCode.equals("")){
			myCode = "'0'";
		}
		if(myCompany.equals("") || myCompany.equals("1")){
			return new ArrayList<PortalMsg>();
		}
		String hql =" from PortalMsg t where t.delFlag = 0 and t.business in ("+ myCode +") and (t.parentCompany in ("+Common.dealinStr(myCompany) + ") or t.company in ("+Common.dealinStr(myCompany) + "))";
		hql = hql + " and t.url is not null ";
		hql = hql + " order by t.createTime desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<PortalMsg> getPortalMsgsList(PortalMsg portalMsg, int offset,
			int pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from PortalMsg t where 1=1");
		if(portalMsg.getDelFlag() != null){
			hql.append(" and t.delFlag = " + portalMsg.getDelFlag());
		}
		if(Common.notEmpty(portalMsg.getTitle())){
			hql.append(" and t.title like '%" + portalMsg.getTitle() + "%'");
		}
		if(Common.notEmpty(portalMsg.getDescription())){
			hql.append(" and t.description like '%" + portalMsg.getDescription() + "%'");
		}
		if(Common.notEmpty(portalMsg.getCreator())){
			hql.append(" and t.creator = '" + portalMsg.getCreator() + "'");
		}
		hql.append(" order by t.createTime desc");
		Query query= (Query) sessionFactory.getCurrentSession().createQuery(hql.toString());    
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);            
		return query.list(); 
	}

	@Override
	public Integer getAllRowsCount(PortalMsg portalMsg) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(t.id) from portal_msg t where 1=1 ");
		if(portalMsg.getDelFlag() != null){
			sql.append(" and t.del_flag = " + portalMsg.getDelFlag());
		}
		if(Common.notEmpty(portalMsg.getTitle())){
			sql.append(" and t.title like '%" + portalMsg.getTitle() + "%'");
		}
		if(Common.notEmpty(portalMsg.getDescription())){
			sql.append(" and t.description like '%" + portalMsg.getDescription() + "%'");
		}
		if(Common.notEmpty(portalMsg.getCreator())){
			sql.append(" and t.creator = '" + portalMsg.getCreator() + "'");
		}
		sql.append(" order by t.create_time desc");
		Query query= (Query) sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public List<PortalMsg> getProtalMsgList(String session_code,
			String session_company, HhUsers hhUsers, PortalMsg portalMsg,
			Integer offset, Integer pageSize) {
		List<PortalMsg> result = new ArrayList<PortalMsg>();
		String[] code = session_code.split(",");
		String myCode = "";
		for(int i=0; i<code.length; i++){
			Object obj = code[i];
			if(obj == null || obj.equals("")){
				continue;
			}
			myCode = myCode + "'"+obj.toString()+"'";
			if(i < code.length - 1){
				myCode = myCode + ",";
			}
		}
		String myCompany = "";
		if(Common.notEmpty(session_company)){
			String[] company = session_company.split(",");
			myCompany = "";
			for(int i=0; i<company.length; i++){
				Object obj = company[i];
				if(obj == null || obj.equals("")){
					continue;
				}
				myCompany = myCompany + obj.toString();
				if(i < company.length - 1){
					myCompany = myCompany + ",";
				}
			}
		}else{
			return result;
		}
		if(myCode.equals("")){
			myCode = "'0'";
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" from PortalMsg t where 1=1");
		if(portalMsg.getDelFlag() != 2){
			hql.append(" and t.delFlag = " + portalMsg.getDelFlag());
		}
		if(Common.notEmpty(portalMsg.getTitle())){
			hql.append(" and t.title like '%" + portalMsg.getTitle() + "%'");
		}
		if(Common.notEmpty(portalMsg.getDescription())){
			hql.append(" and t.description like '%" + portalMsg.getDescription() + "%'");
		}
		if(Common.notEmpty(portalMsg.getCreator())){
			hql.append(" and t.creator = '" + portalMsg.getCreator() + "'");
		}
		hql.append(" and  t.business in (").append(myCode).append(") and (t.parentCompany in (").append(Common.dealinStr(myCompany)).append(") or t.company in ("+Common.dealinStr(myCompany) + "))");
		hql.append(" and t.url is not null");
		hql.append(" order by t.createTime desc");
		Query query= (Query) sessionFactory.getCurrentSession().createQuery(hql.toString()); 
		if(offset!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);   
		}
		result =  query.list();
		return result; 
	}
}
