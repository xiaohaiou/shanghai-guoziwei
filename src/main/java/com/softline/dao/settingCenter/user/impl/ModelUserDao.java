package com.softline.dao.settingCenter.user.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.settingCenter.user.IModelUserDao;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhModelUser;

@Repository(value = "modelUserDao")
public class ModelUserDao implements IModelUserDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void deleteModelUser(Integer modelId) {
		// TODO Auto-generated method stub
		String hql = "delete from HhModelUser h where h.modelId = " + modelId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<HhUsers> getSelectHhUsersList(Integer modelId) {
		// TODO Auto-generated method stub
		String hql = "from HhUsers h where h.vcEmployeeId in (select u.vcEmployeeId from HhModelUser u where u.modelId = " + modelId + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public Integer checkTheId(String vcEmployeeId, Integer modelId) {
		// TODO Auto-generated method stub
		String hql = "from HhModelUser h where h.modelId != " + modelId + " and h.vcEmployeeId = '" + vcEmployeeId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list().size();
	}

	@Override
	public HhModelUser getHhModelUserByVcEmployeeId(String vcEmployeeId) {
		String hql = " from HhModelUser h where h.vcEmployeeId = '" + vcEmployeeId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhModelUser)query.uniqueResult();
	}

}
