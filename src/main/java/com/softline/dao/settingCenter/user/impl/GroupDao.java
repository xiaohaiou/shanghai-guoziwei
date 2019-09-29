package com.softline.dao.settingCenter.user.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.mysql.fabric.xmlrpc.base.Array;
import com.softline.dao.settingCenter.user.IGroupDao;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhGroup;
import com.softline.entity.settingCenter.HhGroupUser;
import com.softline.entity.settingCenter.HhGroupUserRole;
import com.softline.entityTemp.VUserRoles;

@Repository(value = "groupDao")
public class GroupDao implements IGroupDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Integer getAllRowCount(String qGroupName , Integer qGroupStatus) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(id) from hh_group h where h.is_del=0 ");
		if (qGroupName != null && !qGroupName.equals("")) {
			sql.append(" and h.group_name like '%" + qGroupName + "%' ");
		}
		if (qGroupStatus != null && !qGroupStatus.equals("")) {
			sql.append(" and h.group_status = " + qGroupStatus);
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public List<HhGroup> getUserGroupList(String qGroupName, Integer qGroupStatus, int offset,
			Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhGroup h where h.isDel=0 ");
		if (qGroupName != null && !qGroupName.equals("")) {
			hql.append(" and h.groupName like '%" + qGroupName + "%' ");
		}
		if (qGroupStatus != null && !qGroupStatus.equals("")) {
			hql.append(" and h.groupStatus = " + qGroupStatus);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	@Override
	public Integer getNewAllRowCount(String vcAccount,String qUserName,Integer cFlag) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(id) from v_user_roles h where 1=1 ");
		if (qUserName != null && !qUserName.equals("")) {
			sql.append(" and h.vcName like '%" + qUserName + "%' ");
		}
		if (vcAccount != null && !vcAccount.equals("")) {
			sql.append(" and h.vcAccount like '%" + vcAccount + "%' ");
		}
		if (cFlag != null && cFlag!=99) {
			sql.append(" and h.cFlag ="+cFlag+" ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public List<VUserRoles> getUserRoleList(String vcAccount,String qUserName,
			Integer offset, Integer pageSize,Integer cFlag) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append(" select * from v_user_roles h where 1=1 ");
		if (qUserName != null && !qUserName.equals("")) {
			hql.append(" and h.vcName like '%" + qUserName + "%' ");
		}
		if (vcAccount != null && !vcAccount.equals("")) {
			hql.append(" and h.vcAccount like '%" + vcAccount + "%' ");
		}
		if (cFlag != null && cFlag!=99) {
			hql.append(" and h.cFlag ="+cFlag+" ");
		}
		hql.append(" order by h.vcEmployeeId ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offset !=null && pageSize != null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		List<VUserRoles> result = new ArrayList<VUserRoles>();
		List<Object[]> tempResult = query.list();
		for(int i = 0; i < tempResult.size(); i++ ){
			Object[] tempObj = tempResult.get(i);
			VUserRoles entity = new VUserRoles();
			entity.setId(tempObj[0]==null?"":tempObj[0].toString());
			entity.setVcEmployeeID(tempObj[1]==null?"":tempObj[1].toString());
			entity.setVcName(tempObj[2]==null?"":tempObj[2].toString());
			entity.setVcFullName(tempObj[3]==null?"":tempObj[3].toString());
			entity.setVcAccount(tempObj[4]==null?"":tempObj[4].toString());
			entity.setRoleIds(tempObj[5]==null?"":tempObj[5].toString());
			entity.setRoleNames(tempObj[6]==null?"":tempObj[6].toString());
			entity.setcFlag(tempObj[7]==null?99:Integer.parseInt(tempObj[7].toString()));
			result.add(entity);
		}
		return result;
	}

	@Override
	public HhGroup getTheUserGroup(Integer id) {
		// TODO Auto-generated method stub
		String hql = (" from HhGroup h where h.isDel = 0 and h.id = " + id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhGroup)query.uniqueResult();
	}

	@Override
	public void saveOrUpdateUserGroup(HhGroup group) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(group);
	}

	@Override
	public void deleteUserGroup(Integer id) {
		// TODO Auto-generated method stub
		String hql = "update HhGroup h set h.isDel = 1 where h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteGroupUser(Integer groupId, String vcEmployeeIds) {
		// TODO Auto-generated method stub
		String hql = "delete from HhGroupUser h where h.vcEmployeeId in (" + vcEmployeeIds + ") and h.groupId = " + groupId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<HhUsers> getSelectHhUsersList(Integer groupId) {
		// TODO Auto-generated method stub
		String hql = "from HhUsers h where h.vcEmployeeId in (select u.vcEmployeeId from HhGroupUser u where u.groupId = " + groupId + ")" +
				"and h.cflag = '1' and h.isOut = 0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhGroup> getStartGroups(Integer groupStatus) {
		// TODO Auto-generated method stub
		String hql = " from HhGroup h where h.isDel = 0 and h.groupStatus = " + groupStatus;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhGroupUserRole> getTheGroupUserRole(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HhGroupUserRole h where h.groupId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteUserRole(Integer id, String vcEmployeeIds) {
		// TODO Auto-generated method stub
		String hql = "delete from HhUsersrole h where h.vcEmployeeId in (" + vcEmployeeIds + ") and h.roleId in "+
					 "(select g.roleId from HhGroupUserRole g where g.groupId = " + id + " ) ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<HhGroupUser> getGroupUserList(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from HhGroupUser h where h.groupId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteAllUserRole(Integer id) {
		// TODO Auto-generated method stub
		String hql = "delete from HhUsersrole h where h.vcEmployeeId in (select u.vcEmployeeId from HhGroupUser u where u.groupId = " + id + ") and h.roleId in "+
				 	 "(select g.roleId from HhGroupUserRole g where g.groupId = " + id + " ) ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteAllGroupUser(Integer id) {
		// TODO Auto-generated method stub
		String hql = "delete from HhGroupUser h where h.groupId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	@Override
	public void stopGroup(Integer id) {
		// TODO Auto-generated method stub
		String hql = "update HhGroup h set h.groupStatus = 2 where h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteAllGroupUsersRole(Integer id) {
		// TODO Auto-generated method stub
		String hql = "delete from HhGroupUserRole h where h.groupId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

}
