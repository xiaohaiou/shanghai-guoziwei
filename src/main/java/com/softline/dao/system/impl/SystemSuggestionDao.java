package com.softline.dao.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.system.ISystemSuggestionDao;
import com.softline.entity.SysSuggestion;
import com.softline.entity.SysUsers;
import com.softline.service.system.ISystemService;
@Repository(value = "systemSuggestionDao")
public class SystemSuggestionDao implements ISystemSuggestionDao{
	@Autowired  
    @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Override
	public List<SysSuggestion> getSysSuggestions(SysSuggestion condition,Integer offset,Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append("from SysSuggestion t where t.isDel = 0 ");
		
		if(Common.notEmpty(condition.getDescription())){
			hql.append(" and t.description like '%" + condition.getDescription()+"%'");
		}
		if(Common.notEmpty(condition.getCreateName())){
			hql.append(" and t.createName like '%" + condition.getCreateName() + "%'");
		}
		if(Common.notEmpty(condition.getCreator())){
			hql.append(" and t.creator = '" + condition.getCreator() + "'");
		}
		hql.append(" order by t.createTime desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset != null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}
	
	
	
}
