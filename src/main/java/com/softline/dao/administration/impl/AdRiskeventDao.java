package com.softline.dao.administration.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.dao.administration.IAdRiskeventDao;
import com.softline.entity.AdRiskevent;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.SysExamine;
@Repository("adRiskeventDao")
public class AdRiskeventDao implements IAdRiskeventDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public Integer getRiskeventListCount(AdRiskevent entity, Integer fun) {
		// TODO Auto-generated method stub
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AdRiskevent s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" and s.status != 50112 ");
		}
		if(entity!=null)
		{
			if(entity.getYear() != null && !"".equals(entity.getYear()))
			{
				hql.append(" and s.year = "+entity.getYear());
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(entity.getSeason() !=null && !"".equals(entity.getSeason()))
			{
				hql.append(" and s.season = "+entity.getSeason());
			}
			if(entity.getRiskCompId() != null && !"".equals(entity.getRiskCompId()))
			{
				hql.append(" and s.riskCompId in (" + entity.getRiskCompId() + ") ");
			}
			if(entity.getId() != null){
				hql.append(" and s.id=" + entity.getId());
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<AdRiskevent> getRiskeventList(AdRiskevent entity,
			Integer offset, Integer pageSize, Integer fun) {
		// TODO Auto-generated method stub
		if(entity==null)
			return new ArrayList<AdRiskevent>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from AdRiskevent s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" and s.status != 50112 ");
		}
		if(entity!=null)
		{
			if(entity.getYear() != null && !"".equals(entity.getYear()))
			{
				hql.append(" and s.year = "+entity.getYear());
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(entity.getSeason() !=null && !"".equals(entity.getSeason()))
			{
				hql.append(" and s.season = "+entity.getSeason());
			}
			if(entity.getRiskCompId() != null && !"".equals(entity.getRiskCompId()))
			{
				hql.append(" and s.riskCompId in (" + entity.getRiskCompId() + ") ");
			}
			if(entity.getId() != null){
				hql.append(" and s.id=" + entity.getId());
			}
		}
		hql.append(" order by year desc , season desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public AdRiskevent getRiskevent(AdRiskevent entity) {
		// TODO Auto-generated method stub
		String hql = " from AdRiskevent s where isdel = 0 and s.id = " + entity.getId();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AdRiskevent)query.uniqueResult();
	}

	@Override
	public boolean saveRiskeventCheck(AdRiskevent entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AdRiskevent s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
			if(entity.getSeason()!=null)
			{
				hql.append(" and s.season = "+entity.getSeason()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id !="+entity.getId()  +"");
			}
			if(entity.getRiskCompId()!=null)
			{
				hql.append(" and s.riskCompId ="+entity.getRiskCompId()  +"");
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
	public AdRiskevent getRiskevent(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from AdRiskevent s where s.isdel = 0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AdRiskevent)query.uniqueResult();
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
	public HhOrganInfo getCoreCompId(String riskCompId) {
		// TODO Auto-generated method stub
		String hql = "from HhOrganInfo h where nnodeId = '" + riskCompId + "' and cflag = 1 ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhOrganInfo)query.uniqueResult();
	}

	@Override
	public boolean theTimeRiskeventCheck(AdRiskevent entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select lastModifyDate,isdel from AdRiskevent s where 1=1 ");
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
