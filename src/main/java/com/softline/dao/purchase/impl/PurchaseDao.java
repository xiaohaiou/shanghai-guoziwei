package com.softline.dao.purchase.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.purchase.IPurchaseDao;
import com.softline.dao.system.IBaseDao;
import com.softline.dao.system.IExamineDao;
import com.softline.entity.HhBase;
import com.softline.entity.Purchase;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;

@Repository(value = "purchaseDao")
/**
 * 
 * @author tch
 *
 */
public class PurchaseDao implements IPurchaseDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public Purchase getPurchase(Integer id)
	{
		if(id==null)
			return new Purchase();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from Purchase s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (Purchase) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<Purchase> getPurchaseList(Purchase entity ,Integer offsize,Integer pagesize)
	{
		if(entity==null)
			return new ArrayList<Purchase>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from Purchase s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getSeason()!=null)
			{
				hql.append(" and s.season = "+entity.getSeason()+ " ");
			}
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
		}
		hql.append(" order by year desc,season desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getPurchaseListCount(Purchase entity)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from Purchase s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getSeason()!=null)
			{
				hql.append(" and s.season = "+entity.getSeason()+ " ");
			}
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = '"+entity.getYear()+ "' ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = '"+entity.getStatus()+ "' ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(entity.getId() != null){
				hql.append(" and s.id = " + entity.getId());
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean savePurchaseRepeatCheck(Purchase entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from Purchase s where 1=1 and isdel=0  ");
		if(entity!=null)
		{
			if(entity.getSeason()!=null)
			{
				hql.append(" and s.season = "+entity.getSeason()+ " ");
			}
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and id !="+entity.getId()  +"");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return true;
		else
			return false;
	}
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(Purchase entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from Purchase s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
		if(entity!=null)
		{
			
			if(entity.getId()!=null)
			{
				hql.append(" and id ="+entity.getId()  +"");
			}
			else
				return true;
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return false;
		else
			return true;
	}
}
