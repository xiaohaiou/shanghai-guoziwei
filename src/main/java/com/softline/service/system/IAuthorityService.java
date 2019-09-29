package com.softline.service.system;

import com.softline.entity.HhPage;
import com.softline.entity.HhPagecode;
import com.softline.entity.RoleCompany;
import com.softline.entity.RoleFinanceCompany;
import com.softline.entity.SysRole;

public interface IAuthorityService {
	
	public String getRoleCompany(String vcEmployeeID);
	
	/**
	 * 删除角色对应的管理口数据权限公司
	 * @param roleId
	 */
	public void deleteRoleCompany(Integer roleId);
	
	
	/**
	 * 删除角色对应的财务口数据权限公司
	 * @param roleId
	 */
	public void deleteFinanceRoleCompany(Integer roleId);
	
	
	
	public void showUpdaterRoleCompany(RoleCompany roleCompany);
	
	
	public void showUpdaterRoleFinanceCompany(RoleFinanceCompany roleCompany);
	
	/**
	 * @author sht 20170824
	 * @param scrId
	 * @return
	 */
	public SysRole getRoleBySrcId(Integer scrId);
	
	/**
	 * @author sht 20170824
	 * @param roleId
	 */
	public void deleteUsersrole02(Integer roleId);
	
	/**
	 * 根据角色，先删除，然后添加
	 * @author sht
	 * @param vcEmployeeIds
	 * @param roleId
	 */
	public void delAddUsersrole(String vcEmployeeIds, Integer roleId);
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	public String updateRole(String codeIds,String pageIds,SysRole role);
	
	/**
	 * 
	 * @param portalId 来源项目中的ID
	 * @return
	 */
	public String updateHhPage(HhPage page);
	
	/**
	 * 
	 * @param portalId
	 * @return
	 */
	public String updateHhPagecode(HhPagecode pageCode);
	
	/**
	 * 
	 * @param vcEmployeeId
	 * @return
	 */
	public String getUserCodeNum(String vcEmployeeId);
	
	/**
	 * 
	 * @param vcEmployeeId
	 * @return
	 */
	public String getUserPageNum(String vcEmployeeId);

	
}
