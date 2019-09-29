package com.softline.dao.settingCenter.user.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.softline.common.Common;
import com.softline.dao.settingCenter.user.IRoleDao;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhGroupUserRole;
import com.softline.entity.settingCenter.HhRole;
import com.softline.entity.settingCenter.HhRoleModel;
import com.softline.entity.settingCenter.HhRoleSys;
import com.softline.entity.settingCenter.PortalHhRolecompany;
import com.softline.entity.settingCenter.PortalHhRolefinancecompany;
import com.softline.entity.settingCenter.PortalHhRolepage;
import com.softline.entity.settingCenter.HhUsersrole;
import com.softline.entity.settingCenter.PortalHhRolepagecode;

@Repository(value = "roleDao")
public class RoleDao implements IRoleDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public Integer getAllRowCount(String qRoleName, String qRoleStatus,String vcAccount,String vcEmployeeID) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(t.id) from hh_role t where t.is_del = 0 ");
		roleQueryCondition(sql, qRoleName, qRoleStatus, vcAccount,vcEmployeeID);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()); 
		Integer count = Integer.valueOf((query.uniqueResult().toString()));
		return count;
	}
	
	/**
	 * 角色页面查询条件公共方法
	 * @param sql
	 * @param qRoleName
	 * @param qRoleStatus
	 * @param vcAccount
	 */
	private void roleQueryCondition(StringBuffer sql,String qRoleName, String qRoleStatus,String vcAccount,String vcEmployeeID){
		if(Common.notEmpty(qRoleStatus)){
			sql.append(" and t.role_status=").append(qRoleStatus);
		}
		if(Common.notEmpty(qRoleName)){
			sql.append(" and t.role_name like '%").append(qRoleName).append("%'");
		}
		if(Common.notEmpty(vcAccount)){
			sql.append(" and  t.id in (select t1.role_id from hh_usersrole t1 left join hh_users t2 on  t1.vcEmployeeID = t2.vcEmployeeID where  t2.vcAccount = '").append(vcAccount).append("' )");
		}else if(Common.notEmpty(vcEmployeeID)){
			sql.append(" and  t.id in (select t1.role_id from hh_usersrole t1 left join hh_users t2 on  t1.vcEmployeeID = t2.vcEmployeeID where  t2.vcEmployeeID = '").append(vcEmployeeID).append("' )");
		}
		sql.append(" order by t.create_time desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HhRole> getRoleList(String qRoleName, String qRoleStatus,String vcAccount, Integer offset, Integer pageSize,String vcEmployeeID) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from hh_role t where t.is_del = 0 ");
		roleQueryCondition(sql, qRoleName, qRoleStatus, vcAccount,vcEmployeeID);
		SQLQuery query= (SQLQuery) sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		if(offset!=null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);            
		}
		List<HhRole> results = new ArrayList<HhRole>();
		List<Object[]> aa = query.list();
		for(int i = 0; i < aa.size(); i++ ){
			HhRole tempEntity = new HhRole();
			Object[] a = aa.get(i);
			tempEntity.setId((Integer)a[0]);
			tempEntity.setRoleName(a[3]==null?"":a[3].toString());
			tempEntity.setRoleDescription(a[4]==null?"":a[4].toString());
			tempEntity.setRoleStatus((Integer)a[11]);
			results.add(tempEntity);
		}
		return results;
	}

	@Override
	public int saveOrUpdate(HhRole hhRole) {
		Integer id = (Integer)sessionFactory.getCurrentSession().save(hhRole);
		return id.intValue();
	}

	@Override
	public void deleteRole(Integer id) {
		String hql = "update HhRole h set h.isDel = 1 where h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	@Override
	public void saveRolePage(PortalHhRolepage hhRolepage) {
		sessionFactory.getCurrentSession().save(hhRolepage);
	}

	@Override
	public void saveRolepagecode(PortalHhRolepagecode hhRolepagecode) {
		sessionFactory.getCurrentSession().save(hhRolepagecode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PortalHhRolepage> getRolePageList(Integer modelId, Integer roleId) {
		String hql = " from PortalHhRolepage t where t.modelId = " + modelId + " and t.roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PortalHhRolepagecode> getRolePageCodeList(Integer modelId,
			Integer roleId, String pageIds) {
		String hql = " from PortalHhRolepagecode t where t.modelId = " + modelId + " and t.roleId = " + roleId + " and t.pageId in ("+pageIds+")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<HhUsers> getUsersList(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = "from HhUsers u where u.nnodeId in (select c.companyId from HhRolecompany c where c.roleId = " + roleId + ") and u.cflag = '1' and u.isOut = 0 ";
		Query query= (Query) sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteUsersRole(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = "delete from HhUsersrole h where h.roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HhUsers> getSelectHhUsersList(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = "from HhUsers h where h.vcEmployeeId in (select u.vcEmployeeId from HhUsersrole u where u.roleId = " + roleId + ") ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteHhRolepage(Integer roleId, Integer sysId) {
		String hql = "delete from HhRolepage s where s.roleId = "+roleId +" and s.sysId = "+sysId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.executeUpdate();
	}

	@Override
	public void deleteHhRolepagecode(Integer roleId, Integer sysId,
			String tempPageIds) {
		String hql = "delete from HhRolepagecode s where s.roleId = "+roleId +" and s.sysId = "+sysId;
		if(Common.notEmpty(tempPageIds)){
			hql += " and s.pageId in ("+tempPageIds+")";
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.executeUpdate();
	}
	
	@Override
	public List<Object[]> getCompanyList() {
		// TODO Auto-generated method stub
		String sql = "select v.nNodeID,v.vcParentID,v.vcFullName from v_organInfo v where v.cflag = '1'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
	
	@Override
	public void deleteCompanyRole(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = "delete from PortalHhRolecompany h where h.roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	@Override
	public void deleteFinanceCompanyRole(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = "delete from PortalHhRolefinancecompany h where h.roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<PortalHhRolecompany> getCheckedCompList(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = "from PortalHhRolecompany h where roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<PortalHhRolefinancecompany> getCheckedFinanceCompList(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = "from PortalHhRolefinancecompany h where roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhUsers> getHhUsersListByName(Integer roleId, String vcName) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from HhUsers u where u.nnodeId in (select c.companyId from HhRolecompany c where c.roleId = " + roleId + ") and u.cflag = '1' and u.isOut = 0 ");
		if (vcName != null && !vcName.equals("")) {
			hql.append(" and u.vcName like '%" + vcName + "%'");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HhUsersrole> getHhUsersRoleList(String vcEmployeeId) {
		String hql = " from HhUsersrole t where t.vcEmployeeId = '" + vcEmployeeId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteHhRolepageByRoleId(Integer id) {
		// TODO Auto-generated method stub
		String hql = "delete from PortalHhRolepage h where h.roleId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteHhRolepagecodeByRoleId(Integer id) {
		// TODO Auto-generated method stub
		String hql = "delete from PortalHhRolepagecode h where h.roleId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	/*@Override
	public List<HhGroupUserRole> getSelectedDepList(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HhDepUserRole h where h.roleId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}*/

	@Override
	public void deleteGroupUsersRoleByGroupIds(Integer roleId, String needDelGroupIds) {
		// TODO Auto-generated method stub
		String hql = "delete from HhGroupUserRole h where h.roleId = " + roleId + "and h.groupId in (" + needDelGroupIds + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteOldUserRoleByGroupIds(Integer roleId, String needDelGroupIds) {
		// TODO Auto-generated method stub
		String hql = " delete from HhUsersrole h where h.vcEmployeeId in " 
				   + "(select u.vcEmployeeId from HhGroupUser u where groupId in " 
				   + "(" + needDelGroupIds + ")) " 
				   + " and h.roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	@Override
	public void deleteGroupUsersRoleByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = "delete from HhGroupUserRole h where h.roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteOldUserRoleByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " delete from HhUsersrole h where h.vcEmployeeId in (select u.vcEmployeeId from HhGroupUser u where groupId in " 
				   + "(select g.groupId from HhGroupUserRole g where g.roleId = " + roleId + ")) "
				   + " and h.roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<HhUsersrole> getUserRoleList(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = "from HhUsersrole h where h.roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteUsersRoleByIds(Integer roleId, String ids) {
		// TODO Auto-generated method stub
		String hql = " delete from HhUsersrole h where h.vcEmployeeId in (" + ids + ") and h.roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteOldUserRoleByTheDepId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = "delete from HhUsersrole h where h.vcEmployeeId in (select c.vcEmployeeId from HhUsersCopy c) and h.roleId = " +roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteDepUsersRoleByTheDepId(Integer roleId, String depId) {
		// TODO Auto-generated method stub
		String hql = "delete from HhDepUserRole h where h.roleId = " + roleId + " and h.depId = '" + depId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void saveOrUpdateRole(HhRole hhRole) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(hhRole);
	}

	@Override
	public List<HhGroupUserRole> getSelectedGroupList(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HhGroupUserRole h where h.roleId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhRoleSys> getRoleSysListByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from HhRoleSys h where h.roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhRoleModel> getRoleModelListByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from HhRoleModel h where h.roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteOldRoleSysByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " delete HhRoleSys h where h.roleId = " +roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteOldRoleModelByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " delete HhRoleModel h where h.roleId = " +roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	
	public List<PortalHhRolepage> getRolepageListByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from PortalHhRolepage h where h.roleId = " +roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<PortalHhRolepagecode> getRolepagecodeListByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from PortalHhRolepagecode h where h.roleId = " +roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteHhRoleSysByRoleId(Integer id) {
		// TODO Auto-generated method stub
		String hql = "delete from HhRoleSys h where h.roleId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteHhRoleModelByRoleId(Integer id) {
		// TODO Auto-generated method stub
		String hql = "delete from HhRoleModel h where h.roleId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public HhRole getHhRoleByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from HhRole h where h.isDel = 0 and h.id = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhRole)query.uniqueResult();
	}

	@Override
	public List<PortalHhRolepagecode> getRolePageCodeListNew(Integer modelId,
			Integer roleId, Integer pageId) {
		// TODO Auto-generated method stub
		String hql = " from PortalHhRolepagecode h where h.modelId = " + modelId + " and h.roleId = " + roleId + " and h.pageId = " + pageId; 
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteHhRolepageByRoleIdAndPageIds(Integer roleId,
			String delPageIds) {
		// TODO Auto-generated method stub
		String hql = " delete from PortalHhRolepage h where h.roleId = " + roleId + " and h.pageId in(" + delPageIds + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteHhRolepagecodeByRoleIdAndPageIds(Integer roleId,
			String delPageIds) {
		// TODO Auto-generated method stub
		String hql = " delete from PortalHhRolepagecode h where h.roleId = " + roleId + " and h.pageId in(" + delPageIds + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteHhRoleModelByRoleIdAndModelIds(Integer roleId,
			String delModelIds) {
		// TODO Auto-generated method stub
		String hql = " delete from HhRoleModel h where h.roleId = " + roleId + " and h.modelId in(" + delModelIds + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteHhRolepageByRoleIdAndModelIds(Integer roleId,
			String delModelIds) {
		// TODO Auto-generated method stub
		String hql = " delete from PortalHhRolepage h where h.roleId = " + roleId + " and h.modelId in(" + delModelIds + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteHhRolepagecodeByRoleIdAndModelIds(Integer roleId,
			String delModelIds) {
		// TODO Auto-generated method stub
		String hql = " delete from PortalHhRolepagecode h where h.roleId = " + roleId + " and h.modelId in(" + delModelIds + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteHhRoleSysByRoleIdAndSysIds(Integer roleId,
			String delSysIds) {
		// TODO Auto-generated method stub
		String hql = " delete from HhRoleSys h where h.roleId = " + roleId + " and h.sysId in(" + delSysIds + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteHhRoleModelByRoleIdAndSysIds(Integer roleId,
			String delSysIds) {
		// TODO Auto-generated method stub
		String hql = " delete from HhRoleModel h where h.roleId = " + roleId + " and h.sysId in(" + delSysIds + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteHhRolepageByRoleIdAndSysIds(Integer roleId,
			String delSysIds) {
		// TODO Auto-generated method stub
		String hql = " delete from HhRolepage h where h.roleId = " + roleId + " and h.sysId in(" + delSysIds + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteHhRolepagecodeByRoleIdAndSysIds(Integer roleId,
			String delSysIds) {
		// TODO Auto-generated method stub
		String hql = " delete from HhRolepagecode h where h.roleId = " + roleId + " and h.sysId in(" + delSysIds + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<PortalHhRolecompany> getRoleCompanyList(Integer roleId) {
		String hql = "from HhRolecompany h where roleId = " + roleId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<Object> getPageTypeGroupBy(Integer sysId,Integer modelId) {
		String hql = "select a.page_type,b.description from hh_pageregister a left join hh_base b on a.page_type=b.id where sys_id ="+sysId+" and model_id="+modelId+" group by page_type";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
		return query.list();
	}

	@Override
	public List<HhRole> getHhRolesByRoleIds(String roleIds) {
		String hql = "from HhRole h where h.isDel = 0 and h.id in ("+roleIds+") ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public String financeCompanys(Integer roleId) {
		String sql = "select h.vcFullName from hh_organInfo_tree_relation h where h.nNodeID in (select t.company_id from hh_rolefinancecompany t where t.role_id = "+roleId+")";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<Object[]> result = query.list();
		String b = "";
		for(int i = 0; i < result.size(); i++){
			Object aa = result.get(i);
			b += (aa==null?"":aa.toString()) +',';
		}
		if(Common.notEmpty(b)){
			b = b.substring(0,b.length()-1);
		}
		return b;
	}

	@Override
	public String hrCompanys(Integer roleId) {
		String sql = "select h.vcFullName from hh_organInfo h where h.nNodeID in (select t.company_id from hh_rolecompany t where t.role_id = "+roleId+")";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<Object[]> result = query.list();
		String b = "";
		for(int i = 0; i < result.size(); i++){
			Object aa = result.get(i);
			b += (aa==null?"":aa.toString()) +',';
		}
		if(Common.notEmpty(b)){
			b = b.substring(0,b.length()-1);
		}
		return b;
	}
	
	

}
