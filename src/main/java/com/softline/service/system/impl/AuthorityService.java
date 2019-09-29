package com.softline.service.system.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Common;
import com.softline.dao.system.IAuthorityDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhPage;
import com.softline.entity.HhPagecode;
import com.softline.entity.HhRolepage;
import com.softline.entity.HhRolepagecode;
import com.softline.entity.RoleCompany;
import com.softline.entity.RoleFinanceCompany;
import com.softline.entity.SysRole;
import com.softline.entity.SysUsersrole;
import com.softline.service.system.IAuthorityService;
import com.softline.service.system.ISystemService;
@Service("authorityService")
public class AuthorityService implements IAuthorityService{
	@Autowired
	@Qualifier("authorityDao")
	private IAuthorityDao authorityDao;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	private static Logger logger = Logger.getLogger(AuthorityService.class);
	
	public String getRoleCompany(String vcEmployeeID)
	{
		return authorityDao.getRoleCompany(vcEmployeeID);
	}
	
	
	public void deleteRoleCompany(Integer roleId)
	{
		authorityDao.deleteRoleCompany(roleId);
	}
	
	/**
	 * 删除角色对应的财务口数据权限公司
	 * @param roleId
	 */
	public void deleteFinanceRoleCompany(Integer roleId)
	{
		authorityDao.deleteFinanceRoleCompany(roleId);
	}
	
	
	public void showUpdaterRoleCompany(RoleCompany roleCompany)
	{
		 authorityDao.showUpdaterRoleCompany(roleCompany);
	}
	
	
	public void showUpdaterRoleFinanceCompany(RoleFinanceCompany roleCompany)
	{
		 authorityDao.showUpdaterRoleFinanceCompany(roleCompany);
	}
	
	
	@Override
	public SysRole getRoleBySrcId(Integer scrId) {
		return authorityDao.getRoleBySrcId(scrId);
	}

	@Override
	public void deleteUsersrole02(Integer roleId) {
		authorityDao.deleteUsersrole02(roleId);
	}

	@Override
	public void delAddUsersrole(String vcEmployeeIds, Integer roleId) {
		
		authorityDao.deleteUsersrole02(roleId);
		if(Common.notEmpty(vcEmployeeIds)){
			//添加角色对应的用户
			
			String[] vcEmployeeId = vcEmployeeIds.substring(0,vcEmployeeIds.length()-1).split(",");
			for(int i=0;i<vcEmployeeId.length;i++){
				SysUsersrole entity = new SysUsersrole();
				entity.setRoleId(roleId);
				entity.setVcEmployeeId(vcEmployeeId[i]);
				commonDao.saveOrUpdate(entity);
			}
		}
		
	}

	@Override
	public String updateRole(String codeIds,String pageIds,SysRole role) {
		try {
			Integer roleId = role.getSrcId();
			//先删除
			authorityDao.deleteRolePage(roleId);
			authorityDao.deleteRolePagecode(roleId);
			if(role.getIsDel()==1){
				authorityDao.deleteUsersrole02(roleId);
			}
			
			SysRole roleEntity = authorityDao.getRoleBySrcId(roleId);
			if(roleEntity==null){
				commonDao.saveOrUpdate(role);
				
			}else{
				roleEntity.setDescription(role.getDescription());
				roleEntity.setModifierId(role.getModifierId());
				roleEntity.setModifyTime(role.getModifyTime());
				roleEntity.setIsDel(role.getIsDel());
				roleEntity.setName(role.getName());
				commonDao.saveOrUpdate(roleEntity);
			}
			
			//保存修改HhRolepage
			if(Common.notEmpty(pageIds)){
				String[] pageId = pageIds.split(",");
				for(int i = 0;i < pageId.length;i++){
					HhRolepage rolePage = new HhRolepage();
					rolePage.setPageId(Integer.parseInt(pageId[i]));
					HhPage entity1 = authorityDao.getHhPageByPortalId(Integer.parseInt(pageId[i]));
					if(entity1 == null){
						logger.error("select * from hh_page where is_del = 0 and portal_id = "+Integer.parseInt(pageId[i]));
						continue;
					}
					rolePage.setPageNum(entity1.getPageNum());
					//rolePage.setPortalId(portalId);
					rolePage.setRoleId(roleId);
					//rolePage.s
					commonDao.saveOrUpdate(rolePage);
				}
			}
			
			//保存修改HhRolepagecode
			if(Common.notEmpty(codeIds)){
				String[] codeidsTemp = codeIds.split(",");
				for(int i = 0;i<codeidsTemp.length;i++){
					HhRolepagecode rpc = new HhRolepagecode();
					rpc.setRoleId(roleId);
					rpc.setPageId(Integer.parseInt(codeidsTemp[i].split("_")[1]));//设置pageid
					rpc.setCodeId(Integer.parseInt(codeidsTemp[i].split("_")[2]));//设置codeid
					commonDao.saveOrUpdate(rpc);
				}
			}
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	@Override
	public String updateHhPage(HhPage page) {
		HhPage entity = authorityDao.getHhPageByPortalId(page.getPortalId());
		if(page.getIsDel()==1){
			authorityDao.deletePagecodeByPageId(page.getPortalId());
		}
		if(entity != null){
			entity.setIsDel(page.getIsDel());
			entity.setPageDescription(page.getPageDescription());
			entity.setPageName(page.getPageName());
			entity.setPageNum(page.getPageNum());
			entity.setPortalId(page.getPortalId());
			commonDao.saveOrUpdate(entity);
		}else{
			commonDao.saveOrUpdate(page);
		}
		return "success";
	}

	@Override
	public String updateHhPagecode(HhPagecode pageCode) {
		HhPagecode entity =  authorityDao.getHhPagecodeByPortalId(pageCode.getPortalId());
		if(entity!=null){
			entity.setCode(pageCode.getCode());
			entity.setCodeName(pageCode.getCodeName());
			entity.setIsDel(pageCode.getIsDel());
			entity.setPageId(pageCode.getPageId());
			entity.setPageNum(pageCode.getPageNum());
			entity.setPortalId(pageCode.getPortalId());
			entity.setSeqNo(pageCode.getSeqNo());
			commonDao.saveOrUpdate(entity);
		}else{
			commonDao.saveOrUpdate(pageCode);
		}
		return "success";
	}

	@Override
	public String getUserCodeNum(String vcEmployeeId) {
		//获取roles
		String roles = systemDao.getRolesByVcEmployeeId(vcEmployeeId);
		String result = "";
		if(Common.notEmpty(roles)){
			result = authorityDao.getUserCodeNumByRoles(roles);
		}
		return result;
	}

	@Override
	public String getUserPageNum(String vcEmployeeId) {
		//获取roles
		String roles = systemDao.getRolesByVcEmployeeId(vcEmployeeId);
		String result = "";
		
		if(Common.notEmpty(roles)){
			result = authorityDao.getUserPageNumByRoles(roles);
		}
		
		return result;
	}

	
}
