package com.softline.dao.system.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhFile;

@Repository(value = "commonDao")
public class CommonDao implements ICommonDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public HhFile getFileByEnIdAndType(Integer entityId, Integer typeId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhFile s where s.releaseId = "+entityId+" and s.typeId ="+ typeId);
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return  (HhFile) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HhFile> getFile(String entityIds, String types){
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhFile s where s.releaseId in ("+entityIds+") and s.typeId in ("+types+") ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}
	@Override
	public <T> void saveOrUpdate(T t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public <T> void delete(T t) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(t);
	}
	
	public Object findById(Class a ,Integer id){
		return sessionFactory.getCurrentSession().get(a, id);
	}

	public HhFile getFileById(Integer fileID)
	{
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhFile s where s.id ="+fileID+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (HhFile) query.uniqueResult();
	}
	
	public HhFile getFileByUuid(String uuid)
	{
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhFile s where s.fileUuid = '"+uuid+"' ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (HhFile) query.uniqueResult();
	}
	
	@Override
	public void deleteChildDetail(Integer id, String type, String tbName,
			String lastModifyDate, String lastModifyPersonId,
			String lastModifyPersonName) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" update "+tbName+" set isdel=1,lastModifyDate='"+lastModifyDate+"',lastModifyPersonID='"+lastModifyPersonId+"',lastModifyPersonName='"+lastModifyPersonName+"' where isdel=0 ");
		if(type.equals("weekly")) {
			sql.append(" and week_id="+id);
		}else if(type.equals("monthly")){
			sql.append(" and month_id="+id);
		}else {
			sql.append(" and stock_liabilities_id="+id);
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		query.executeUpdate();
	}
	@Override
	public <T> void save(T t) {
		sessionFactory.getCurrentSession().save(t);
	}
	
	
	
}
