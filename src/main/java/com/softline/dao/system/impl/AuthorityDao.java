package com.softline.dao.system.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.system.IAuthorityDao;
import com.softline.entity.HhPage;
import com.softline.entity.HhPagecode;
import com.softline.entity.RoleCompany;
import com.softline.entity.RoleFinanceCompany;
import com.softline.entity.SysRole;
import com.softline.entity.SysUsersrole;

@Repository("authorityDao")
public class AuthorityDao implements IAuthorityDao{
	@Autowired  
    @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public SysRole getRoleBySrcId(Integer srcId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysRole a where  a.srcId = "+srcId+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (SysRole) query.uniqueResult();
	}
	
	@Override
	public void deleteUsersrole02(Integer roleId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" delete from SysUsersrole a where a.roleId = "+roleId+"");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.executeUpdate();
	}

	@Override
	public HhPage getHhPageByPortalId(Integer portalId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhPage a where a.isDel = 0 and a.portalId = "+portalId+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (HhPage) query.uniqueResult();
	}

	@Override
	public HhPagecode getHhPagecodeByPortalId(Integer portalId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhPagecode a where a.isDel = 0 and a.portalId = "+portalId+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (HhPagecode) query.uniqueResult();
	}

	@Override
	public void deleteRolePage(Integer roleId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" delete from HhRolepage a where a.roleId = "+roleId+"");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.executeUpdate();
		
	}

	@Override
	public void deleteRolePagecode(Integer roleId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" delete from HhRolepagecode a where a.roleId = "+roleId+"");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.executeUpdate();
		
	}

	@Override
	public String getUserCodeNumByRoles(String roles) {
		StringBuffer sql = new StringBuffer(); 
		sql.append("select t2.`code` from hh_rolepagecode t1 LEFT JOIN hh_pagecode t2 on t1.code_id = t2.portal_id  where t2.is_del = 0 and t1.role_id IN ("+roles+")");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		List<Object> result = query.list();
		String aa = ",";
		for(int i = 0;i < result.size();i++){
			String a = result.get(i).toString();
			aa =  aa + a+",";
		}
	/*	if(Common.notEmpty(aa))
		aa = aa.substring(0,aa.length()-1);*/
//		System.out.println(aa);
		return aa;
	}

	@Override
	public String getUserPageNumByRoles(String roles) {
		StringBuffer sql = new StringBuffer(); 
		sql.append("select t2.page_num from hh_rolepage t1 LEFT JOIN hh_page t2 on t1.page_id = t2.portal_id  where t2.is_del = 0 and t1.role_id IN ("+roles+")");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		List<Object> result = query.list();
		String aa = ",";
		for(int i = 0;i < result.size();i++){
			String a = result.get(i).toString();
			aa =  aa + a+",";
			
		}
	/*	if(Common.notEmpty(aa))
		aa = aa.substring(0,aa.length()-1);*/
//		System.out.println(aa);
		return aa;
	}

	@Override
	public void deletePagecodeByPageId(Integer pageId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" update HhPagecode a  set a.isDel = 1 where a.pageId = " + pageId + "");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.executeUpdate();
	}
	
	/**
	 * 同步角色数据表
	 */
	@Override
	public void showUpdaterRoleCompany(RoleCompany roleCompany) {
		try{
			sessionFactory.getCurrentSession().save(roleCompany);  
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void showUpdaterRoleFinanceCompany(RoleFinanceCompany roleCompany)
	{
		try{
			sessionFactory.getCurrentSession().save(roleCompany);  
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void deleteRoleCompany(Integer roleId)
	{
		try{
	        String hql = "delete RoleCompany u where u.roleId="+roleId;   
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除角色对应的财务口数据权限公司
	 * @param roleId
	 */
	public void deleteFinanceRoleCompany(Integer roleId)
	{
		try{
	        String hql = "delete RoleFinanceCompany u where u.roleId="+roleId;   
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public String getRoleCompany(String vcEmployeeID)
	{
		if(!Common.notEmpty(vcEmployeeID))
		{
			return "";
		}
		String hql = "from SysUsersrole u where u.vcEmployeeId='"+vcEmployeeID+"'";   
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<SysUsersrole> role= query.list();
		String roleid="";
		for (int i = 0; i < role.size(); i++) {
			if(roleid.equals(""))
			{
				roleid=role.get(i).getRoleId().toString();
			}
			else
			{
				roleid+=","+role.get(i).getRoleId().toString();
			}
			
		}
		String companyids="";
		if(Common.notEmpty(roleid))
		{
			hql = " from RoleCompany  where roleId in("+roleid +")";   
			Query query2 = sessionFactory.getCurrentSession().createQuery(hql);
			List<RoleCompany> roleCompany=query2.list();
			
			for (int i = 0; i < roleCompany.size(); i++) {
				if(companyids.equals(""))
				{
					companyids=roleCompany.get(i).getCompanyId().toString();
				}
				else
				{
					companyids+=","+roleCompany.get(i).getCompanyId().toString();
				}
				
			}
		}
		return companyids;
	}
}
