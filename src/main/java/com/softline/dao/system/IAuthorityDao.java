package com.softline.dao.system;

import java.util.List;

import com.softline.entity.HhPage;
import com.softline.entity.HhPagecode;
import com.softline.entity.HhRolepage;
import com.softline.entity.HhRolepagecode;
import com.softline.entity.RoleCompany;
import com.softline.entity.RoleFinanceCompany;
import com.softline.entity.SysRole;

public interface IAuthorityDao {
	/**
	 * 根据srcId获取role
	 * @author sht 20170824
	 * @param scrId
	 * @return
	 */
	public SysRole getRoleBySrcId(Integer scrId);
	
	
	/**
	 * 根据查询条件roleId 删除usersRole表中的记录
	 * @author sht 20170824
	 * @param roleId
	 */
	public void deleteUsersrole02(Integer roleId);
	
	
	/**
	 * 
	 * @param portalId 来源项目中的ID
	 * @return
	 */
	public HhPage getHhPageByPortalId(Integer portalId);
	
	/**
	 * 
	 * @param portalId
	 * @return
	 */
	public HhPagecode getHhPagecodeByPortalId(Integer portalId);
	
	/**
	 * 
	 * @param roleId
	 */
	public void deleteRolePage(Integer roleId);
	
	/**
	 * 
	 * @param roleId
	 */
	public void deleteRolePagecode(Integer roleId);
	
	/**
	 * 
	 * @param roles
	 * @return
	 */
	public String getUserCodeNumByRoles(String roles);
	
	/**
	 * 
	 * @param roles
	 * @return
	 */
	public String getUserPageNumByRoles(String roles);
	
	public void deletePagecodeByPageId(Integer pageId);
	
	/**
	 * 同步角色数据表
	 */
	public void showUpdaterRoleCompany(RoleCompany roleCompany);
	
	
	public void showUpdaterRoleFinanceCompany(RoleFinanceCompany roleCompany);
	
	public void deleteRoleCompany(Integer roleId);
	
	/**
	 * 删除角色对应的财务口数据权限公司
	 * @param roleId
	 */
	public void deleteFinanceRoleCompany(Integer roleId);
	
	public String getRoleCompany(String vcEmployeeID);
}
