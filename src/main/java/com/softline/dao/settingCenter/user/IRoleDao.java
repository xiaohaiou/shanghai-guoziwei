package com.softline.dao.settingCenter.user;

import java.util.List;


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

public interface IRoleDao {

	Integer getAllRowCount(String qRoleName, String qRoleStatus,String vcAccount,String vcEmployeeID);

	List<HhRole> getRoleList(String qRoleName, String qRoleStatus,String vcAccount, Integer offset, Integer pageSize,String vcEmployeeID);

	int saveOrUpdate(HhRole hhRole);

	void deleteRole(Integer id);

	List<HhUsers> getUsersList(Integer roleId);

	void deleteUsersRole(Integer roleId);

	List<HhUsers> getSelectHhUsersList(Integer roleId);
	
	void saveRolePage(PortalHhRolepage hhRolesyspage);

	void saveRolepagecode(PortalHhRolepagecode hhRolepagecode);

	List<PortalHhRolepage> getRolePageList(Integer modelId, Integer roleId);

	List<PortalHhRolepagecode> getRolePageCodeList(Integer modelId, Integer roleId,
			String pageIds);

	void deleteHhRolepage(Integer roleId, Integer sysId);

	void deleteHhRolepagecode(Integer roleId, Integer sysId, String tempPageIds);
	
	List<Object[]> getCompanyList();
	
	void deleteCompanyRole(Integer roleId);

	List<PortalHhRolecompany> getCheckedCompList(Integer roleId);
	
	List<PortalHhRolecompany> getRoleCompanyList(Integer roleId);

	List<HhUsers> getHhUsersListByName(Integer roleId, String vcName);

	List<HhUsersrole> getHhUsersRoleList(String vcEmployeeId);

	void deleteHhRolepageByRoleId(Integer id);

	void deleteHhRolepagecodeByRoleId(Integer id);

	//List<HhGroupUserRole> getSelectedDepList(Integer id);

	void deleteGroupUsersRoleByGroupIds(Integer roleId, String needDelGroupIds);

	void deleteOldUserRoleByGroupIds(Integer roleId, String needDelGroupIds);

	void deleteOldUserRoleByRoleId(Integer roleId);

	void deleteGroupUsersRoleByRoleId(Integer roleId);

	List<HhUsersrole> getUserRoleList(Integer roleId);

	void deleteUsersRoleByIds(Integer roleId, String ids);

	void deleteOldUserRoleByTheDepId(Integer roleId);

	void deleteDepUsersRoleByTheDepId(Integer roleId, String depId);

	void saveOrUpdateRole(HhRole hhRole);

	List<HhGroupUserRole> getSelectedGroupList(Integer id);

	List<HhRoleSys> getRoleSysListByRoleId(Integer roleId);

	List<HhRoleModel> getRoleModelListByRoleId(Integer roleId);

	void deleteOldRoleSysByRoleId(Integer roleId);

	void deleteOldRoleModelByRoleId(Integer roleId);

	List<PortalHhRolepage> getRolepageListByRoleId(Integer roleId);

	List<PortalHhRolepagecode> getRolepagecodeListByRoleId(Integer roleId);

	void deleteHhRoleSysByRoleId(Integer id);

	void deleteHhRoleModelByRoleId(Integer id);

	HhRole getHhRoleByRoleId(Integer roleId);

	List<PortalHhRolepagecode> getRolePageCodeListNew(Integer modelId, Integer roleId,
			Integer pageId);

	void deleteHhRolepageByRoleIdAndPageIds(Integer roleId, String delPageIds);

	void deleteHhRolepagecodeByRoleIdAndPageIds(Integer roleId,
			String delPageIds);

	void deleteHhRoleModelByRoleIdAndModelIds(Integer roleId, String delModelIds);

	void deleteHhRolepageByRoleIdAndModelIds(Integer roleId, String delModelIds);

	void deleteHhRolepagecodeByRoleIdAndModelIds(Integer roleId,
			String delModelIds);

	void deleteHhRoleSysByRoleIdAndSysIds(Integer roleId, String delSysIds);

	void deleteHhRoleModelByRoleIdAndSysIds(Integer roleId, String delSysIds);

	void deleteHhRolepageByRoleIdAndSysIds(Integer roleId, String delSysIds);

	void deleteHhRolepagecodeByRoleIdAndSysIds(Integer roleId, String delSysIds);

	List<PortalHhRolefinancecompany> getCheckedFinanceCompList(Integer roleId);

	void deleteFinanceCompanyRole(Integer roleId);

    List<Object> getPageTypeGroupBy(Integer sysId,Integer modelId);
    
    /**
     * 通过角色IDS获取角色列表
     * @param roleIds 
     * @return
     */
    List<HhRole> getHhRolesByRoleIds(String roleIds);
    
    /**
     * 获取该角色的财务数据权限
     * @param roleId
     * @return
     */
    String financeCompanys(Integer roleId);
    /**
     * 获取改角色的管理公司权限
     * @param roleId
     * @return
     */
    String hrCompanys(Integer roleId);
  
}
