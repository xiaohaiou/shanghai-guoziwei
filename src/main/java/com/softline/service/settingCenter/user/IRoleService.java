package com.softline.service.settingCenter.user;

import java.util.List;
import java.util.Map;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhGroupUserRole;
import com.softline.entity.settingCenter.HhRole;
import com.softline.entity.settingCenter.HhRoleModel;
import com.softline.entity.settingCenter.HhRoleSys;
import com.softline.entity.settingCenter.PortalHhRolecompany;
import com.softline.entity.settingCenter.PortalHhRolepage;
import com.softline.entity.settingCenter.HhSysRegister;
import com.softline.entity.settingCenter.HhUsersrole;
import com.softline.entity.settingCenter.PortalHhRolepagecode;
import com.softline.util.MsgPage;

public interface IRoleService {
	
	/**
	 * 获取角色列表分页
	 * 2018-07-09 添加查询条件vcAccount 
	 * @param qRoleName     角色名称
	 * @param qRoleStatus   角色状态，是否禁用
	 * @param vcAccount     员工账号 ，查询该员工有哪些角色
	 * @param curPageNum    
	 * @param pageSize
	 * @return
	 */
	MsgPage getHhRoleList(String qRoleName, String qRoleStatus,String vcAccount, Integer curPageNum, Integer pageSize,String vcEmployeeID);

	List<HhSysRegister> getRegistedList();

	int saveOrUpdate(HhRole hhRole);

	void deleteRole(Integer id);
	
	String getRolePageStr(HhRole hhRole);

	String getPageCodeIds(HhRole hhRole, String pageIds);

	List<HhUsers> getHhUsersList(Integer roleId);

	List<HhUsers> getSelectHhUsersList(Integer roleId);

	List<Map<String, String>> getAllCompanyTree();

	void saveOrUpdateRoleCompany(PortalHhRolecompany roleCompany, String companyIds);

	List<Map<String, Integer>> getCheckedCompanyList(Integer roleId);
	
	List<PortalHhRolecompany> getRoleCompanyList(Integer roleId);

	List<HhUsers> getHhUsersListByName(Integer roleId, String vcName);

	List<HhUsersrole> getHhUsersRoleList(String vcEmployeeId);

	List<PortalHhRolepage> getRolePageList(HhRole hhRole);

	List<PortalHhRolepagecode> getRolePageCodeList(HhRole hhRole, String pageIds);

	//List<HhGroupUserRole> getSelectedDepList(Integer id);

	//void saveOrUpdateRoleUserByDep(HhUsersrole userRole, String depIds);

	void saveOrUpdateRoleUserByUser(HhUsersrole userRole, String vcEmployeeIds);

	void saveOrUpdateRole(HhRole hhRole);

	void saveRoleModelFunction(Integer roleId, String modelIds);

	void saveRolePageFunction(Integer roleId, String pageIds);

	void saveRoleCodeFunction(Integer roleId, String codeIds);

	List<HhGroupUserRole> getSelectedGroupList(Integer id);

	void saveOrUpdateRoleUserByGroup(HhUsersrole userRole, String groupIds);

	List<HhRoleSys> getRoleSysListByRoleId(Integer roleId);

	List<HhRoleModel> getRoleModelListByRoleId(Integer roleId);

	void saveRoleSysFunction(Integer roleId, String sysIds);

	List<PortalHhRolepage> getRolepageListByRoleId(Integer roleId);

	List<PortalHhRolepagecode> getRolepagecodeListByRoleId(Integer roleId);

	HhRole getHhRoleByRoleId(Integer roleId);

	List<Map<String, String>> getCheckedFinanceCompanyList(Integer id);

	void saveOrUpdateRoleFinanceCompany(PortalHhRolecompany roleCompany,
			String companyIds);

    List<Object> getPageTypeGroupBy(Integer sysId,Integer modelId);
    
    void saveOrUpdateRoleCopy(HhRole hhRole,Integer copyId);
    
    /**
     * 根据IDs获取角色列表 
     * @param roleIds
     * @return
     */
    List<HhRole> getHhRolesByRoleIds(String roleIds);
}
