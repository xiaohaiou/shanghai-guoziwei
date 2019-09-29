package com.softline.dao.administration.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.dao.administration.IAdImportantDao;
import com.softline.entity.AdImportant;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.SysExamine;
@Repository("adImportantDao")
public class AdImportantDao implements IAdImportantDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public Integer getImportantListCount(AdImportant entity, Integer fun) {
		// TODO Auto-generated method stub
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AdImportant s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" and s.status != 50112 ");
		}
		if(entity!=null)
		{
			if(entity.getImportantType() != null && !"".equals(entity.getImportantType()))
			{
				hql.append(" and s.importantType = "+entity.getImportantType());
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
			if(entity.getImportantCompId() != null && !"".equals(entity.getImportantCompId()))
			{
				hql.append(" and s.importantCompId in (" + entity.getImportantCompId() + ") ");
			}
			if(entity.getId() != null){
				hql.append(" and s.id = " + entity.getId());
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<AdImportant> getImportantList(AdImportant entity,
			Integer offset, Integer pageSize, Integer fun) {
		// TODO Auto-generated method stub
		if(entity==null)
			return new ArrayList<AdImportant>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from AdImportant s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" and s.status != 50112 ");
		}
		if(entity!=null)
		{
			if(entity.getImportantType() != null && !"".equals(entity.getImportantType()))
			{
				hql.append(" and s.importantType = "+entity.getImportantType());
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
			if(entity.getImportantCompId() != null && !"".equals(entity.getImportantCompId()))
			{
				hql.append(" and s.importantCompId in (" + entity.getImportantCompId() + ") ");
			}
			if(entity.getId() != null){
				hql.append(" and s.id = " + entity.getId());
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
	public AdImportant getImportant(AdImportant entity) {
		// TODO Auto-generated method stub
		String hql = " from AdImportant s where isdel = 0 and s.id = " + entity.getId();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AdImportant)query.uniqueResult();
	}

	@Override
	public boolean saveImportantCheck(AdImportant entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AdImportant s where 1=1 and isdel=0 ");
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
			if(entity.getImportantCompId()!=null)
			{
				hql.append(" and s.importantCompId ="+entity.getImportantCompId()  +"");
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
	public AdImportant getImportant(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from AdImportant s where s.isdel = 0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AdImportant)query.uniqueResult();
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
	public HhOrganInfo getCoreCompId(String importantCompId) {
		// TODO Auto-generated method stub
		String hql = "from HhOrganInfo h where nnodeId = '" + importantCompId + "' and cflag = 1 ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhOrganInfo)query.uniqueResult();
	}

	@Override
	public boolean theTimeImportantCheck(AdImportant entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select lastModifyDate,isdel from AdImportant s where 1=1 ");
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
}
