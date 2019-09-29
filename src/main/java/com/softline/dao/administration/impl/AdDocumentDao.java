package com.softline.dao.administration.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.administration.IAdDocumentDao;
import com.softline.entity.AdDocument;
import com.softline.entity.SysExamine;
@Repository("adDocumentDao")
public class AdDocumentDao implements IAdDocumentDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public Integer getDocumentListCount(AdDocument entity, Integer fun) {
		// TODO Auto-generated method stub
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AdDocument s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" and s.status != 50112 ");
		}
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCompId()))
			{
				hql.append(" and s.compId in ("+entity.getCompId()+ ") ");
			}
			if(entity.getYear() != null && !"".equals(entity.getYear()))
			{
				hql.append(" and s.year = "+entity.getYear());
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(entity.getMonth() !=null && !"".equals(entity.getMonth()))
			{
				hql.append(" and s.month = "+entity.getMonth());
			}
			if(entity.getId()!=null){
				hql.append(" and s.id = "+entity.getId());
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<AdDocument> getDocumentList(AdDocument entity,
			Integer offset, Integer pageSize, Integer fun) {
		// TODO Auto-generated method stub
		if(entity==null)
			return new ArrayList<AdDocument>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from AdDocument s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" and s.status != 50112 ");
		}
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCompId()))
			{
				hql.append(" and s.compId in ("+entity.getCompId()+ ") ");
			}
			if(entity.getYear() != null && !"".equals(entity.getYear()))
			{
				hql.append(" and s.year = "+entity.getYear());
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(entity.getMonth() !=null && !"".equals(entity.getMonth()))
			{
				hql.append(" and s.month = "+entity.getMonth());
			}
			if(entity.getId()!=null){
				hql.append(" and s.id = "+entity.getId());
			}
		}
		hql.append(" order by year desc , month desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public AdDocument getDocument(AdDocument entity) {
		// TODO Auto-generated method stub
		String hql = " from AdDocument s where isdel = 0 and s.id = " + entity.getId();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AdDocument)query.uniqueResult();
	}

	@Override
	public boolean saveDocumentCheck(AdDocument entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AdDocument s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
			if(entity.getMonth()!=null)
			{
				hql.append(" and s.month = "+entity.getMonth()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id !="+entity.getId()  +"");
			}
			if(entity.getCompId()!=null)
			{
				hql.append(" and s.compId ="+entity.getCompId()  +"");
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
	public AdDocument getDocument(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from AdDocument s where s.isdel = 0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AdDocument)query.uniqueResult();
	}

	@Override
	public SysExamine getOneExamine(Integer examineentityid, int examinekind) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		if(examineentityid != null){
			hql.append("from SysExamine t where t.isdel = 0 and t.examentityid = "+examineentityid);
			hql.append(" and t.examKind ="+examinekind+" ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return (SysExamine) query.uniqueResult();
		}else{
			return new SysExamine();
		}
	}

	@Override
	public boolean theTimeDocumentCheck(AdDocument entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select lastModifyDate,isdel from AdDocument s where 1=1 ");
		if(entity!=null)
		{
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = " + entity.getId() +"");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Object[] dateAndStatus = (Object[])query.uniqueResult();
		String newModifyDate = dateAndStatus[0] == null?"":dateAndStatus[0].toString();
		String newStatus = dateAndStatus[1].toString();
		String oldModifyDate = entity.getLastModifyDate() == null?"":entity.getLastModifyDate();
		if(newStatus.equals("0")) {
			if (!"".equals(newModifyDate)) {
				if(oldModifyDate.equals(newModifyDate)) {
					return true;
				}else {
					return false;
				}
			}else {
				if("".equals(oldModifyDate)) {
					return true;
				}else {
					return false;
				}
			}
		}else {
			return false;
		}
	}
	
	public List getVcCompanyId(String year, String month){
		StringBuilder hql = new StringBuilder();
		hql.append("select org.nNodeID from hh_organInfo org ");
		hql.append("where org.cLevel <= 3 and org.nNodeID not in ( ");
		hql.append("select doc.comp_id from ad_document doc where doc.isdel = 0 and doc.year = " + year + " and doc.month = " + month);
		hql.append(")");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}
}
