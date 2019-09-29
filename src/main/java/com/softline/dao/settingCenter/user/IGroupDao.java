package com.softline.dao.settingCenter.user;

import java.util.List;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhGroup;
import com.softline.entity.settingCenter.HhGroupUser;
import com.softline.entity.settingCenter.HhGroupUserRole;
import com.softline.entityTemp.VUserRoles;

public interface IGroupDao {

	Integer getAllRowCount(String qGroupName, Integer qGroupStatus);

	List<HhGroup> getUserGroupList(String qGroupName, Integer qGroupStatus, int offset,
			Integer pageSize);

	HhGroup getTheUserGroup(Integer id);

	void saveOrUpdateUserGroup(HhGroup group);

	void deleteUserGroup(Integer id);

	void deleteGroupUser(Integer groupId, String vcEmployeeIds);

	List<HhUsers> getSelectHhUsersList(Integer groupId);

	List<HhGroup> getStartGroups(Integer groupStatus);

	List<HhGroupUserRole> getTheGroupUserRole(Integer id);

	void deleteUserRole(Integer id, String vcEmployeeIds);

	List<HhGroupUser> getGroupUserList(Integer id);

	void deleteAllUserRole(Integer id);

	void deleteAllGroupUser(Integer id);

	void stopGroup(Integer id);

	void deleteAllGroupUsersRole(Integer id);

	Integer getNewAllRowCount(String vcAccount,String qUserName,Integer cFlag);

	List<VUserRoles> getUserRoleList(String vcAccount,String qUserName,
			Integer offset, Integer pageSize,Integer cFlag);

}
