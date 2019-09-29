package com.softline.service.settingCenter.user;

import java.util.List;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhGroup;
import com.softline.entity.settingCenter.HhGroupUserRole;
import com.softline.entityTemp.VUserRoles;
import com.softline.util.MsgPage;

public interface IGroupService {

	MsgPage getUserMsgGroupList(String qGroupName, Integer qGroupStatus, Integer curPageNum, Integer pageSize);

	HhGroup getTheUserGroup(Integer id);

	void saveOrUpdateUserGroup(HhGroup group);
	
	void saveOrUpdateUserGroupAndUser(HhGroup group, String vcEmployeeIds);

	void deleteUserGroup(Integer id);

	/*void saveOrUpdateGroupUser(HhGroupUser groupUser, String vcEmployeeIds);*/

	List<HhUsers> getSelectHhUsersList(Integer groupId);

	List<HhGroup> getStartGroups(Integer groupStatus);

	List<HhGroupUserRole> getTheGroupUserRole(Integer id);

	List<HhUsers> getGroupUsersByGroupId(Integer id);

	String stopGroupRole(Integer id);

	MsgPage getUserRoleList(String vcAccount,String qUserName,
			Integer curPageNum, Integer pageSize,Integer cFlag);
	
	List<VUserRoles> getUserRoleList(String vcAccount,String qUserName,Integer cFlag);

}
