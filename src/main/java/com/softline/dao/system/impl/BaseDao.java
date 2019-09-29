package com.softline.dao.system.impl;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.system.IBaseDao;
import com.softline.entity.HhBase;
import com.softline.service.system.ISystemService;

@Repository(value = "baseDao")
public class BaseDao implements IBaseDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public  HhBase getBase(Integer id)
	{
		StringBuffer hql = new StringBuffer();
		hql.append("from HhBase s where  s.id="+id);
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (HhBase) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HhBase> getBases(Integer type){
		StringBuffer hql = new StringBuffer();
		
		hql.append("from HhBase s where s.isDel = 0 and s.type="+type +" order by num");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HhBase> getChildBases(Integer parentID){
		StringBuffer hql = new StringBuffer();
		
		hql.append("from HhBase s where 1=1 and s.parentID="+parentID +" order by num");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	
	/**
	 * 获取账户下的所属业态
	 * @param comIds
	 * @return
	 */
	@Override
	public List<HhBase> getUserBusinessFormat(String ComIDs){
		StringBuffer hql = new StringBuffer();
		if(ComIDs!=null && !ComIDs.equals("")){
			
			hql.append("FROM HhBase WHERE ID IN (SELECT DISTINCT businessFormat FROM GsCompanybasic WHERE id IN ("+ ComIDs +") )");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	
	public String getOtherBase(String hasStr,int type)
	{
		StringBuffer hql = new StringBuffer();
		
		hql.append("SELECT GROUP_CONCAT(id) FROM `hh_base` where parentID="+type+" ");
		if(hasStr!=null && !hasStr.equals(""))
			hql.append(" and id not in ("+hasStr+")");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return (String) query.uniqueResult();
	}
	
	//读取type相同，但是ID not in (hasStr)的内容
	public List<HhBase> getOtherBaseList(String hasStr,int type)
	{
		StringBuffer hql = new StringBuffer();
		
		hql.append("from HhBase s where s.isDel = 0 and id not in ("+hasStr+") and s.type="+type +" order by num");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
}
