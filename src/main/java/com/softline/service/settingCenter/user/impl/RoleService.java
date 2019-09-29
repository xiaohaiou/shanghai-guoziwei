package com.softline.service.settingCenter.user.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.softline.client.show.IShowWebservice;
import com.softline.client.show.Role;
import com.softline.client.show.ShowWebserviceService;
import com.softline.client.task.ITaskWebservice;
import com.softline.entity.SysRole;
import com.softline.client.task.TaskWebserviceService;
import com.softline.common.Common;
import com.softline.dao.settingCenter.user.IRoleDao;
import com.softline.entity.HhRolepage;
import com.softline.entity.HhRolepagecode;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhGroupUserRole;
import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.entity.settingCenter.HhRole;
import com.softline.entity.settingCenter.HhRoleModel;
import com.softline.entity.settingCenter.HhRoleSys;
import com.softline.entity.settingCenter.PortalHhRolecompany;
import com.softline.entity.settingCenter.PortalHhRolefinancecompany;
import com.softline.entity.settingCenter.PortalHhRolepage;
import com.softline.entity.settingCenter.HhSysRegister;
import com.softline.entity.settingCenter.HhUsersrole;
import com.softline.entity.settingCenter.PortalHhRolepagecode;
import com.softline.entityTemp.SysAuthTree;
import com.softline.service.select.ISelectUserService;
import com.softline.service.settingCenter.user.IModelRegisterService;
import com.softline.service.settingCenter.user.IRoleService;
import com.softline.service.settingCenter.user.ISysRegisterService;
import com.softline.service.system.IAuthorityService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IWorkWebService;
import com.softline.util.MsgPage;

@Service("roleService")
public class RoleService implements IRoleService {
	@Autowired
	@Qualifier("roleDao")
	private IRoleDao roleDao;
	
	@Resource(name = "sysRegisterService")
	private ISysRegisterService sysRegisterService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;

	@Resource(name = "modelRegisterService")
	private IModelRegisterService modelRegisterService;
	
	@Resource(name = "authorityService")
	private IAuthorityService authorityService;
	
	@Resource(name = "workWebService")
	private IWorkWebService workWebService;
	
	@Override
	public MsgPage getHhRoleList(String qRoleName, String qRoleStatus,String vcAccount, Integer curPageNum, Integer pageSize,String vcEmployeeID) {
		MsgPage msgPage = new MsgPage();
        //总记录数
        Integer totalRecords = roleDao.getAllRowCount(qRoleName,qRoleStatus,vcAccount,vcEmployeeID);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HhRole> list = roleDao.getRoleList(qRoleName,qRoleStatus,vcAccount, offset, pageSize,vcEmployeeID);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public List<HhSysRegister> getRegistedList() {
		List<HhSysRegister> sysList = sysRegisterService.getRegistedList();
		return sysList;
	}

	@Override
	public int saveOrUpdate(HhRole hhRole) {
		int roleId = roleDao.saveOrUpdate(hhRole);
		return roleId;
	}

	@Override
	public void deleteRole(Integer id) {
		
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, id);
		//调用接口删除角色
		//先获取角色所绑定的模块
		List<HhModelRegister> roleModelList = modelRegisterService.getModelsByRoleId(hhRole.getId());
		if (!roleModelList.isEmpty()) {
			for (int i = 0; i < roleModelList.size(); i++) {
				HhModelRegister roleModel = roleModelList.get(i);
				String modelNum = roleModel.getModelNum();
				if(modelNum.equals("bim_show")){
//					ShowWebserviceService service = new ShowWebserviceService();
//					IShowWebservice is = service.getPort(IShowWebservice.class);
//					Role role = new Role();
//					role.setCreateTime(hhRole.getCreateTime());
//					role.setCreator(hhRole.getCreator());
//					role.setIsDel(1);
//					role.setIsUse(hhRole.getIsUse());
//					role.setModifier(hhRole.getModifier());
//					role.setModifyTime(hhRole.getModifyTime());
//					role.setPortalId(hhRole.getId());
//					role.setRoleDescription(hhRole.getRoleDescription());
//					role.setRoleName(hhRole.getRoleName());
//					role.setRoleNum(hhRole.getRoleNum());
//					is.showUpdaterUsersRole("", id);
//					is.showUpdaterRoleCompany("", id);
//					is.showUpdateRoleFinanceCompany("", id);
//					String msg = is.showUpdateRole(role, "", "");
				}
				if (modelNum.equals("bim_task")) {
//					TaskWebserviceService service = new TaskWebserviceService();
//					ITaskWebservice it = service.getPort(ITaskWebservice.class);
//					SysRole sysRole = new SysRole();
//					sysRole.setCreater(hhRole.getCreator());
//					sysRole.setCreateTime(hhRole.getCreateTime());
//					sysRole.setIsDel(1);
//					sysRole.setModifierId(hhRole.getModifier());
//					sysRole.setModifyTime(hhRole.getModifyTime());
//					sysRole.setDescription(hhRole.getRoleDescription());
//					sysRole.setName(hhRole.getRoleName());
//					sysRole.setSrcId(hhRole.getId());
//					it.addUserRole("", id);
//					String mesg = it.updateRole("","", sysRole);
//					System.out.println(mesg);
				}
				if (modelNum.equals("bim_work")) {
//					WorkWebServiceService service = new WorkWebServiceService();
//					IWorkWebService it = service.getPort(IWorkWebService.class);
					SysRole sysRole = new SysRole();
					sysRole.setCreater(hhRole.getCreator());
					sysRole.setCreateTime(hhRole.getCreateTime());
					sysRole.setIsDel(1);
					sysRole.setModifierId(hhRole.getModifier());
					sysRole.setModifyTime(hhRole.getModifyTime());
					sysRole.setDescription(hhRole.getRoleDescription());
					sysRole.setName(hhRole.getRoleName());
					sysRole.setSrcId(hhRole.getId());
					
					workWebService.addUserRole("", id);
					workWebService.UpdaterRoleCompany("", id);
					workWebService.updateFinanceRoleCompany("", id);
					String mesg = workWebService.updateRole("","", sysRole);
					System.out.println(mesg);
				}
			}
		}
		roleDao.deleteRole(id);
		
		//删除角色对应系统
		roleDao.deleteHhRoleSysByRoleId(id);
		//删除角色对应模块
		roleDao.deleteHhRoleModelByRoleId(id);
		//删除角色对应页面表
		roleDao.deleteHhRolepageByRoleId(id);
		//删除角色对应页面选中的功能表
		roleDao.deleteHhRolepagecodeByRoleId(id);
		//删除角色对应的数据权限
		roleDao.deleteCompanyRole(id);
		//删除角色对应的用户权限
		roleDao.deleteUsersRole(id);
		//删除角色对应用户组
		roleDao.deleteGroupUsersRoleByRoleId(id);
		//删除角色对应财务的数据权限
		roleDao.deleteFinanceCompanyRole(id);
		
	}
	
	private void saveRolePage(PortalHhRolepage hhRolepage) {
		roleDao.saveRolePage(hhRolepage);
	}

	private void saveRolepagecode(PortalHhRolepagecode hhRolepagecode) {
		roleDao.saveRolepagecode(hhRolepagecode);
	}

	@Override
	public String getRolePageStr(HhRole hhRole) {
		StringBuffer tempPageIds = new StringBuffer();
		Integer sysId = hhRole.getSysId();
		Integer roleId = hhRole.getId();
		List<PortalHhRolepage> rolePageList = roleDao.getRolePageList(sysId,roleId);
		if(!rolePageList.isEmpty()){
			int size = rolePageList.size();
			for (int i = 0; i < size; i++) {
				PortalHhRolepage hhRolepage = rolePageList.get(i);
				if(i == (size-1)){
					tempPageIds.append(hhRolepage.getPageId());
				}else{
					tempPageIds.append(hhRolepage.getPageId() + ",");
				}
			}
		}
		return tempPageIds.toString();
	}

	@Override
	public String getPageCodeIds(HhRole hhRole, String pageIds) {
		if (pageIds.equals("")) {
			return null;
		}else {
			StringBuffer tempPageCodeIds = new StringBuffer();
			Integer sysId = hhRole.getSysId();
			Integer roleId = hhRole.getId();
			List<PortalHhRolepagecode> rolePageCodeList = roleDao.getRolePageCodeList(sysId,roleId,pageIds);
			if(!rolePageCodeList.isEmpty()){
				for (int i = 0; i < rolePageCodeList.size(); i++) {
					PortalHhRolepagecode rolepageCode = rolePageCodeList.get(i);
					tempPageCodeIds.append(rolepageCode.getCodeId() + ",");
				}
			}
			return tempPageCodeIds.toString();
		}
	}

	@Override
	public List<HhUsers> getHhUsersList(Integer roleId) {
		// TODO Auto-generated method stub
    	List<HhUsers> list = roleDao.getUsersList(roleId);
        return list;
	}

	@Override
	public List<HhUsers> getSelectHhUsersList(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.getSelectHhUsersList(roleId);
	}

	@Override
	public List<Map<String, String>> getAllCompanyTree() {
		// TODO Auto-generated method stub
		List<Object[]> companyList = roleDao.getCompanyList();
		List<Map<String, String>> companysList = new ArrayList<Map<String,String>>();
		for (int i = 0; i < companyList.size(); i++) {
			Object[] objects = companyList.get(i);
			String[] company = new String[3];
			for (int j = 0; j < objects.length; j++) {
				company[j] = objects[j].toString();
			}
			Map<String, String> companyMap = new LinkedHashMap<String, String>();
			companyMap.put("id", company[0]);
			//获取parentId
			String[] parentIdNum = (company[1].substring(0, company[1].length()-1)).split("-");
			companyMap.put("pId", parentIdNum[parentIdNum.length-1]);
			companyMap.put("name", company[2]);
			companysList.add(companyMap);
		}
		return companysList;
	}
	
	@Override
	public void saveOrUpdateRoleCompany(PortalHhRolecompany roleCompany,
			String companyIds) {
		// TODO Auto-generated method stub
		//先删除原来表中的数据
		roleDao.deleteCompanyRole(roleCompany.getRoleId());
		//再删除
		//如果companyIds不为""，再添加新的数据
		if (!"".equals(companyIds) && companyIds != null) {
			String [] companyIdss = (companyIds.substring(0, companyIds.length()-1)).split(",");
			for (int i = 0; i < companyIdss.length; i++) {
				PortalHhRolecompany newRoleCompany = new PortalHhRolecompany();
				newRoleCompany.setRoleId(roleCompany.getRoleId());
				newRoleCompany.setSysId(roleCompany.getSysId());
				newRoleCompany.setCompanyId(Integer.parseInt(companyIdss[i]));
				commonService.save(newRoleCompany);
			}
		}
		
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, roleCompany.getRoleId());
		if(hhRole.getRoleStatus().toString().equals("1")) {
			//调用接口传入对应模块中
			List<HhModelRegister> roleModelList = modelRegisterService.getModelsByRoleId(roleCompany.getRoleId());
			if (!roleModelList.isEmpty()) {
				for (int i = 0; i < roleModelList.size(); i++) {
					HhModelRegister roleModel = roleModelList.get(i);
					String modelNum = roleModel.getModelNum();
					if(modelNum.equals("bim_show")){
//						ShowWebserviceService service = new ShowWebserviceService();
//						IShowWebservice is = service.getPort(IShowWebservice.class);
//						Integer roleId = roleCompany.getRoleId();
//						is.showUpdaterRoleCompany(companyIds, roleId);
					}
					if(modelNum.equals("bim_work")){
//						WorkWebServiceService service = new WorkWebServiceService();
//						IWorkWebService is = service.getPort(IWorkWebService.class);
						Integer roleId = roleCompany.getRoleId();
						workWebService.UpdaterRoleCompany(companyIds, roleId);
					}
				}
			}
		}
	}
	
	@Override
	public void saveOrUpdateRoleFinanceCompany(PortalHhRolecompany roleCompany,
			String companyIds) {
		// TODO Auto-generated method stub
		//先删除原来表中的数据
		roleDao.deleteFinanceCompanyRole(roleCompany.getRoleId());
		//再删除
		//如果companyIds不为""，再添加新的数据
		if (!"".equals(companyIds) && companyIds != null) {
			String [] companyIdss = (companyIds.substring(0, companyIds.length()-1)).split(",");
			for (int i = 0; i < companyIdss.length; i++) {
				PortalHhRolefinancecompany newRoleFinanceCompany = new PortalHhRolefinancecompany();
				newRoleFinanceCompany.setRoleId(roleCompany.getRoleId());
				newRoleFinanceCompany.setSysId(roleCompany.getSysId());
				newRoleFinanceCompany.setCompanyId(companyIdss[i]);
				commonService.save(newRoleFinanceCompany);
			}
		}
		
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, roleCompany.getRoleId());
		if(hhRole.getRoleStatus().toString().equals("1")) {
			//调用接口传入对应模块中
			List<HhModelRegister> roleModelList = modelRegisterService.getModelsByRoleId(roleCompany.getRoleId());
			if (!roleModelList.isEmpty()) {
				for (int i = 0; i < roleModelList.size(); i++) {
					HhModelRegister roleModel = roleModelList.get(i);
					String modelNum = roleModel.getModelNum();
					if(modelNum.equals("bim_show")){
//						ShowWebserviceService service = new ShowWebserviceService();
//						IShowWebservice is = service.getPort(IShowWebservice.class);
//						Integer roleId = roleCompany.getRoleId();
//						is.showUpdateRoleFinanceCompany(companyIds, roleId);
					}
					if(modelNum.equals("bim_work")){
//						WorkWebServiceService service = new WorkWebServiceService();
//						IWorkWebService is = service.getPort(IWorkWebService.class);
						Integer roleId = roleCompany.getRoleId();
						workWebService.updateFinanceRoleCompany(companyIds, roleId);
					}
				}
			}
		}
	}

	@Override
	public List<Map<String, Integer>> getCheckedCompanyList(Integer roleId) {
		// TODO Auto-generated method stub
		List<PortalHhRolecompany> checkedCompList = roleDao.getCheckedCompList(roleId);
		List<Map<String, Integer>> checkedCompanyList = new ArrayList<Map<String, Integer>>();
		for (int i = 0; i < checkedCompList.size(); i++) {
			Map<String, Integer> map = new LinkedHashMap<String, Integer>();
			map.put("id", checkedCompList.get(i).getCompanyId());
			checkedCompanyList.add(map);
		}
		return checkedCompanyList;
	}
	
	@Override
	public List<Map<String, String>> getCheckedFinanceCompanyList(Integer roleId) {
		// TODO Auto-generated method stub
		List<PortalHhRolefinancecompany> checkedCompList = roleDao.getCheckedFinanceCompList(roleId);
		List<Map<String, String>> checkedCompanyList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < checkedCompList.size(); i++) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("id", checkedCompList.get(i).getCompanyId());
			checkedCompanyList.add(map);
		}
		return checkedCompanyList;
	}

	@Override
	public List<HhUsers> getHhUsersListByName(Integer roleId, String vcName) {
		// TODO Auto-generated method stub
		return roleDao.getHhUsersListByName(roleId, vcName);
	}

	@Override
	public List<HhUsersrole> getHhUsersRoleList(String vcEmployeeId) {
		return roleDao.getHhUsersRoleList(vcEmployeeId);
	}

	@Override
	public List<PortalHhRolepage> getRolePageList(HhRole hhRole) {
		Integer sysId = hhRole.getSysId();
		Integer roleId = hhRole.getId();
		List<PortalHhRolepage> rolePageList = roleDao.getRolePageList(sysId,roleId);
		return rolePageList;
	}

	@Override
	public List<PortalHhRolepagecode> getRolePageCodeList(HhRole hhRole,
			String pageIds) {
		Integer sysId = hhRole.getSysId();
		Integer roleId = hhRole.getId();
		String tempPageIds = pageIds.substring(0,pageIds.length() - 1);
		List<PortalHhRolepagecode> rolePageCodeList = roleDao.getRolePageCodeList(sysId,roleId,tempPageIds);
		return rolePageCodeList;
	}

	@Override
	public void saveOrUpdateRoleUserByUser(HhUsersrole userRole,
			String vcEmployeeIds) {
		// TODO Auto-generated method stub
		if (!"".equals(vcEmployeeIds) && vcEmployeeIds != null) {
			//获取原来hh_usersrole表中的数据
			List<HhUsersrole> oldUserRoleList = roleDao.getUserRoleList(userRole.getRoleId());
			//将现在获取vcEmployeeIds进行处理，处理成数组
			String [] vcEmployeeIdss = vcEmployeeIds.split(",");
			//创建一个新的List，存放新的vcEmployeeId
			List<HhUsersrole> newUserRoleList = new ArrayList<HhUsersrole>();
			//循环新的vcEmployeeIdss，与现oldUserRoleList进行对比，有相同的vcEmployeeId就从oldUserRoleList中去掉，最后oldUserRoleList就只剩下这次没有选中的，就进行删除；
			//没有的保留在newUserRoleList中，进行新增
			for (int i = 0; i < vcEmployeeIdss.length; i++) {
				//flag用于判断是否有相同depId
				boolean flag = false;
				for (int j = 0; j < oldUserRoleList.size(); j++) {
					if (vcEmployeeIdss[i].equals(oldUserRoleList.get(j).getVcEmployeeId())) {
						oldUserRoleList.remove(j);
						flag = true;
						break;
					}
				}
				if(!flag) {
					HhUsersrole newUserRole = new HhUsersrole();
					newUserRole.setVcEmployeeId(vcEmployeeIdss[i]);
					newUserRoleList.add(newUserRole);
				}
			}
			if (!oldUserRoleList.isEmpty()) {
				//过滤之后，oldUserRoleList中剩下没有被选中的vcEmployeeIds，进行删除
				String needDelVcEmployeeIds = "";
				for (HhUsersrole oldUserRole : oldUserRoleList) {
					needDelVcEmployeeIds += "'" + oldUserRole.getVcEmployeeId() + "',";
				}
				//先删除原来vcEmployeeIds对应人员在的hh_usersrole表中的数据
				roleDao.deleteUsersRoleByIds(userRole.getRoleId(), needDelVcEmployeeIds.substring(0,needDelVcEmployeeIds.length()-1));
			}
			if (!newUserRoleList.isEmpty()) {
				//过滤之后，newUserRoleList中为新加入的vcEmployeeIds，进行新增
				for (int i = 0; i < newUserRoleList.size(); i++) {
					HhUsersrole newUserRole1 = new HhUsersrole();
					newUserRole1.setRoleId(userRole.getRoleId());
					newUserRole1.setSysId(userRole.getSysId());
					newUserRole1.setVcEmployeeId(newUserRoleList.get(i).getVcEmployeeId());
					commonService.save(newUserRole1);
				}
			}
		}else {
			//删除原来表中的数据
			roleDao.deleteUsersRole(userRole.getRoleId());
		}
		
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, userRole.getRoleId());
		if(hhRole.getRoleStatus().toString().equals("1")) {
			//调用接口传入对应模块中
			List<HhModelRegister> roleModelList = modelRegisterService.getModelsByRoleId(userRole.getRoleId());
			String newVcEmployeeIds = vcEmployeeIds+",";//接口接收的数据格式，以“，”结尾
			if (!roleModelList.isEmpty()) {
				for (int i = 0; i < roleModelList.size(); i++) {
					HhModelRegister roleModel = roleModelList.get(i);
					String modelNum = roleModel.getModelNum();
//					if(modelNum.equals("bim_show")){
//						ShowWebserviceService service = new ShowWebserviceService();
//						IShowWebservice is = service.getPort(IShowWebservice.class);
//						Integer roleId = userRole.getRoleId();
//						is.showUpdaterUsersRole(newVcEmployeeIds, roleId);
//					}
//					if(modelNum.equals("bim_task")) {
//						TaskWebserviceService service = new TaskWebserviceService();
//						ITaskWebservice it = service.getPort(ITaskWebservice.class);
//						Integer roleId = userRole.getRoleId();
//						String msg = it.addUserRole(newVcEmployeeIds, roleId);
//					}
					if(modelNum.equals("bim_work")) {
//						WorkWebServiceService service = new WorkWebServiceService();
//						IWorkWebService it = service.getPort(IWorkWebService.class);
						Integer roleId = userRole.getRoleId();
						String msg = workWebService.addUserRole(newVcEmployeeIds, roleId);
					}
				}
			}
		}
	}

	@Override
	public void saveOrUpdateRole(HhRole hhRole) {
		roleDao.saveOrUpdateRole(hhRole);
		/**
		 * 新增角色的时候将系统编码配置进去开始
		 * */
		List<HhSysRegister> sysList = this.getRegistedList();
		List<HhSysRegister> roleSysList = sysRegisterService.getRegistedListByRoleId(hhRole.getId());
		if(roleSysList.size() == 0){
			if(sysList.size() > 0){
				String sysId = sysList.get(0).getId().toString();
				this.saveRoleSysFunction(hhRole.getId(), sysId);
			}
		}
		
		/**
		 * 新增角色的时候将系统编码配置进去结束
		 * */
		//调用接口保存角色
		//先获取角色所绑定的模块
		List<HhModelRegister> roleModelList = modelRegisterService.getModelsByRoleId(hhRole.getId());
		//绑定此角色的人员
		String newVcEmployeeIds = "";
		//绑定此角色的数据权限
		String compIds = "";
		//绑定此角色的财务数据权限
		String financeCompIds = "";
		if(hhRole.getRoleStatus().toString().equals("1")) {
			//获取此角色绑定的人员
			List<HhUsersrole> userList = roleDao.getUserRoleList(hhRole.getId());
			//获取此角色的数据权限
			List<PortalHhRolecompany> compList = roleDao.getCheckedCompList(hhRole.getId());
			//获取此角色的财务数据权限
			List<PortalHhRolefinancecompany> compFinanceList = roleDao.getCheckedFinanceCompList(hhRole.getId());
			//拼接角色绑定的人员
			if(!userList.isEmpty()) {
				for (HhUsersrole user : userList) {
					newVcEmployeeIds += user.getVcEmployeeId() + ",";
				}
			}
			//拼接角色数据权限
			if(!compList.isEmpty()) {
				for (PortalHhRolecompany comp : compList) {
					compIds += comp.getCompanyId() + ",";
				}
			}
			//拼接角色财务数据权限
			if(!compFinanceList.isEmpty()) {
				for (PortalHhRolefinancecompany financeComp : compFinanceList) {
					financeCompIds += financeComp.getCompanyId() + ",";
				}
			}
		}
		if (!roleModelList.isEmpty()) {
			for (int i = 0; i < roleModelList.size(); i++) {
				HhModelRegister roleModel = roleModelList.get(i);
				String modelNum = roleModel.getModelNum();
//				if(modelNum.equals("bim_show")){
//					//获取此角色在此模块下绑定的页面
//					List<HhRolepage> rolePageList = roleDao.getRolePageList(roleModel.getId(), hhRole.getId());
//					String pageIds = "";
//					String codeIds = "";
//					if(hhRole.getRoleStatus().toString().equals("1")) {
//						//拼接此角色绑定的页面和功能
//						if (!rolePageList.isEmpty()) {
//							for (int j = 0; j < rolePageList.size(); j++) {
//								//获取此角色在此模块下绑定的功能
//								List<HhRolepagecode> roleCodeList = roleDao.getRolePageCodeListNew(roleModel.getId(), hhRole.getId(), rolePageList.get(j).getPageId());
//								if (!roleCodeList.isEmpty()) {
//									for (int k = 0; k < roleCodeList.size(); k++) {
//										if (k == roleCodeList.size()-1 && j == rolePageList.size()-1) {
//											codeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId();
//										}else {
//											codeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId() + ",";
//										}
//									}
//								}
//								if (j == rolePageList.size()-1) {
//									pageIds += rolePageList.get(j).getPageId();
//								}else {
//									pageIds += rolePageList.get(j).getPageId()+",";
//								}
//							}
//						}
//					}
//					ShowWebserviceService service = new ShowWebserviceService();
//					IShowWebservice is = service.getPort(IShowWebservice.class);
//					Role role = new Role();
//					role.setCreateTime(hhRole.getCreateTime());
//					role.setCreator(hhRole.getCreator());
//					if(hhRole.getRoleStatus().toString().equals("1")) {
//						role.setIsDel(0);
//					}else {
//						role.setIsDel(1);
//					}
//					role.setIsUse(hhRole.getIsUse());
//					role.setModifier(hhRole.getModifier());
//					role.setModifyTime(hhRole.getModifyTime());
//					role.setPortalId(hhRole.getId());
//					role.setRoleDescription(hhRole.getRoleDescription());
//					role.setRoleName(hhRole.getRoleName());
//					role.setRoleNum(hhRole.getRoleNum());
//					Integer roleId = hhRole.getId();
//					is.showUpdaterUsersRole(newVcEmployeeIds, roleId);
//					is.showUpdaterRoleCompany(compIds, roleId);
//					is.showUpdateRoleFinanceCompany(financeCompIds, roleId);
//					String msg = is.showUpdateRole(role, pageIds, codeIds);
//				}
//				if (modelNum.equals("bim_task")) {
//					//获取此角色在此模块下绑定的页面
//					List<HhRolepage> rolePageList = roleDao.getRolePageList(roleModel.getId(), hhRole.getId());
//					String pageIds = "";
//					String codeIds = "";
//					if(hhRole.getRoleStatus().toString().equals("1")) {
//						if (!rolePageList.isEmpty()) {
//							for (int j = 0; j < rolePageList.size(); j++) {
//								//获取此角色在此模块下绑定的功能
//								List<HhRolepagecode> roleCodeList = roleDao.getRolePageCodeListNew(roleModel.getId(), hhRole.getId(), rolePageList.get(j).getPageId());
//								if (!roleCodeList.isEmpty()) {
//									for (int k = 0; k < roleCodeList.size(); k++) {
//										if (k == roleCodeList.size()-1 && j == rolePageList.size()-1) {
//											codeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId();
//										}else {
//											codeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId() + ",";
//										}
//									}
//								}
//								if (j == rolePageList.size()-1) {
//									pageIds += rolePageList.get(j).getPageId();
//								}else {
//									pageIds += rolePageList.get(j).getPageId()+",";
//								}
//							}
//						}
//					}
//					TaskWebserviceService service = new TaskWebserviceService();
//					ITaskWebservice it = service.getPort(ITaskWebservice.class);
//					SysRole sysRole = new SysRole();
//					sysRole.setCreater(hhRole.getCreator());
//					sysRole.setCreateTime(hhRole.getCreateTime());
//					if(hhRole.getRoleStatus().toString().equals("1")) {
//						sysRole.setIsDel(0);
//					}else {
//						sysRole.setIsDel(1);
//					}
//					sysRole.setModifierId(hhRole.getModifier());
//					sysRole.setModifyTime(hhRole.getModifyTime());
//					sysRole.setDescription(hhRole.getRoleDescription());
//					sysRole.setName(hhRole.getRoleName());
//					sysRole.setSrcId(hhRole.getId());
//					Integer roleId = hhRole.getId();
//					it.addUserRole(newVcEmployeeIds, roleId);
//					String mesg = it.updateRole(pageIds, codeIds, sysRole);
//					System.out.println(mesg);
//				}
				if (modelNum.equals("bim_work")) {
					//获取此角色在此模块下绑定的页面
					List<PortalHhRolepage> rolePageList = roleDao.getRolePageList(roleModel.getId(), hhRole.getId());
					String pageIds = "";
					String codeIds = "";
					if(hhRole.getRoleStatus().toString().equals("1")) {
						if (!rolePageList.isEmpty()) {
							for (int j = 0; j < rolePageList.size(); j++) {
								//获取此角色在此模块下绑定的功能
								List<PortalHhRolepagecode> roleCodeList = roleDao.getRolePageCodeListNew(roleModel.getId(), hhRole.getId(), rolePageList.get(j).getPageId());
								if (!roleCodeList.isEmpty()) {
									for (int k = 0; k < roleCodeList.size(); k++) {
										if (k == roleCodeList.size()-1 && j == rolePageList.size()-1) {
											codeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId();
										}else {
											codeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId() + ",";
										}
									}
								}
								if (j == rolePageList.size()-1) {
									pageIds += rolePageList.get(j).getPageId();
								}else {
									pageIds += rolePageList.get(j).getPageId()+",";
								}
							}
						}
					}
//					WorkWebServiceService service = new WorkWebServiceService();
//					IWorkWebService it = service.getPort(IWorkWebService.class);
					SysRole sysRole = new SysRole();
					sysRole.setCreater(hhRole.getCreator());
					sysRole.setCreateTime(hhRole.getCreateTime());
					if(hhRole.getRoleStatus().toString().equals("1")) {
						sysRole.setIsDel(0);
					}else {
						sysRole.setIsDel(1);
					}
					sysRole.setModifierId(hhRole.getModifier());
					sysRole.setModifyTime(hhRole.getModifyTime());
					sysRole.setDescription(hhRole.getRoleDescription());
					sysRole.setName(hhRole.getRoleName());
					sysRole.setSrcId(hhRole.getId());
					Integer roleId = hhRole.getId();
					workWebService.addUserRole(newVcEmployeeIds, roleId);
					workWebService.UpdaterRoleCompany(compIds, roleId);
					workWebService.updateFinanceRoleCompany(financeCompIds, roleId);
					String mesg = workWebService.updateRole(pageIds, codeIds, sysRole);
					//System.out.println("工作台保存rolePageCode的结果为："+mesg);
				}
			}
		}
	}

	@Override
	public void saveRoleModelFunction(Integer roleId, String modelIds) {
		// TODO Auto-generated method stub
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, roleId);
		//获取角色已绑定的Model
		List<HhModelRegister> roleModelList = modelRegisterService.getModelsByRoleId(roleId);
		//保存角色对应页面表
		if(modelIds != null && !"".equals(modelIds)){//modelIds格式：sysId_modelId,.....
			String[] modelIdss = modelIds.split(",");
			if (roleModelList.isEmpty()) {
				for (int i = 0; i < modelIdss.length; i++) {
					String[] sysIdAndModelId = modelIdss[i].split("_");
					HhRoleModel roleModel = new HhRoleModel();
					roleModel.setRoleId(roleId);
					roleModel.setSysId(Integer.parseInt(sysIdAndModelId[0]));
					roleModel.setModelId(Integer.parseInt(sysIdAndModelId[1]));
					commonService.saveOrUpdate(roleModel);
				}
			}else {
				for (int i = 0; i < modelIdss.length; i++) {
					boolean flag = false;
					for (int j = 0; j < roleModelList.size(); j++) {
						String[] ids = modelIdss[i].split("_");
						Integer modelId = Integer.parseInt(ids[1]);
						if (modelId.equals(roleModelList.get(j).getId())) {
							flag = true;
							//去除这次绑定和之前绑定相同的model，剩下需要删除的model
							roleModelList.remove(j);
							break;
						}
					}
					if (!flag) {
						String[] sysIdAndModelId = modelIdss[i].split("_");
						HhRoleModel roleModel = new HhRoleModel();
						roleModel.setRoleId(roleId);
						roleModel.setSysId(Integer.parseInt(sysIdAndModelId[0]));
						roleModel.setModelId(Integer.parseInt(sysIdAndModelId[1]));
						commonService.saveOrUpdate(roleModel);
					}
				}
				//删除上次绑定这次却没有绑定的Model及其对应页面和功能
				if (!roleModelList.isEmpty()) {
					String delModelIds = "";
					for (int i = 0; i < roleModelList.size(); i++) {
						if(hhRole.getRoleStatus().toString().equals("1")) {
							//调用接口，删除对应模块的rolepage和rolepagecode
							if(roleModelList.get(i).getModelNum().equals("bim_show")) {
//								ShowWebserviceService service = new ShowWebserviceService();
//								IShowWebservice is = service.getPort(IShowWebservice.class);
//								Role role = new Role();
//								role.setCreateTime(hhRole.getCreateTime());
//								role.setCreator(hhRole.getCreator());
//								role.setIsDel(1);
//								role.setIsUse(hhRole.getIsUse());
//								role.setModifier(hhRole.getModifier());
//								role.setModifyTime(hhRole.getModifyTime());
//								role.setPortalId(hhRole.getId());
//								role.setRoleDescription(hhRole.getRoleDescription());
//								role.setRoleName(hhRole.getRoleName());
//								role.setRoleNum(hhRole.getRoleNum());
//								//角色设置isDel为1，清空rolepage和rolepagecode
//								String msg = is.showUpdateRole(role, "", "");
//								//清空角色、人员表
//								is.showUpdaterUsersRole("", roleId);
//								//清空角色数据表
//								is.showUpdaterRoleCompany("", roleId);
//								//清空角色财务数据表
//								is.showUpdateRoleFinanceCompany("", roleId);
							}
							if (roleModelList.get(i).getModelNum().equals("bim_task")) {
//								TaskWebserviceService service = new TaskWebserviceService();
//								ITaskWebservice it = service.getPort(ITaskWebservice.class);
//								SysRole sysRole = new SysRole();
//								sysRole.setCreater(hhRole.getCreator());
//								sysRole.setCreateTime(hhRole.getCreateTime());
//								sysRole.setIsDel(1);
//								sysRole.setModifierId(hhRole.getModifier());
//								sysRole.setModifyTime(hhRole.getModifyTime());
//								sysRole.setDescription(hhRole.getRoleDescription());
//								sysRole.setName(hhRole.getRoleName());
//								sysRole.setSrcId(hhRole.getId());
//								//角色设置isDel为1，清空rolepage和rolepagecode
//								String mesg = it.updateRole("","", sysRole);
//								//清空角色、人员表
//								it.addUserRole("", roleId);
							}
							if (roleModelList.get(i).getModelNum().equals("bim_work")) {
//								WorkWebServiceService service = new WorkWebServiceService();
//								IWorkWebService it = service.getPort(IWorkWebService.class);
								SysRole sysRole = new SysRole();
								sysRole.setCreater(hhRole.getCreator());
								sysRole.setCreateTime(hhRole.getCreateTime());
								sysRole.setIsDel(1);
								sysRole.setModifierId(hhRole.getModifier());
								sysRole.setModifyTime(hhRole.getModifyTime());
								sysRole.setDescription(hhRole.getRoleDescription());
								sysRole.setName(hhRole.getRoleName());
								sysRole.setSrcId(hhRole.getId());
								//角色设置isDel为1，清空rolepage和rolepagecode
								String mesg = workWebService.updateRole("","", sysRole);
								workWebService.addUserRole("", roleId);
								workWebService.UpdaterRoleCompany("", roleId);
								workWebService.updateFinanceRoleCompany("", roleId);
							}
						}
						if (i == roleModelList.size()-1) {
							delModelIds += roleModelList.get(i).getId();
						}else {
							delModelIds += roleModelList.get(i).getId() + ",";
						}
					}
					roleDao.deleteHhRoleModelByRoleIdAndModelIds(roleId, delModelIds);
					roleDao.deleteHhRolepageByRoleIdAndModelIds(roleId, delModelIds);
					roleDao.deleteHhRolepagecodeByRoleIdAndModelIds(roleId, delModelIds);
				}
			}
		}else {
			if(hhRole.getRoleStatus().toString().equals("1")) {
				if (!roleModelList.isEmpty()) {
					for (int i = 0; i < roleModelList.size(); i++) {
						//调用接口，删除对应模块的rolepage和rolepagecode
						if(roleModelList.get(i).getModelNum().equals("bim_show")) {
//							ShowWebserviceService service = new ShowWebserviceService();
//							IShowWebservice is = service.getPort(IShowWebservice.class);
//							Role role = new Role();
//							role.setCreateTime(hhRole.getCreateTime());
//							role.setCreator(hhRole.getCreator());
//							role.setIsDel(1);
//							role.setIsUse(hhRole.getIsUse());
//							role.setModifier(hhRole.getModifier());
//							role.setModifyTime(hhRole.getModifyTime());
//							role.setPortalId(hhRole.getId());
//							role.setRoleDescription(hhRole.getRoleDescription());
//							role.setRoleName(hhRole.getRoleName());
//							role.setRoleNum(hhRole.getRoleNum());
//							//角色设置isDel为1，清空rolepage和rolepagecode
//							String msg = is.showUpdateRole(role, "", "");
//							//清空角色、人员表
//							is.showUpdaterUsersRole("", roleId);
//							//清空角色数据表
//							is.showUpdaterRoleCompany("", roleId);
//							//清空角色财务数据表
//							is.showUpdateRoleFinanceCompany("", roleId);
						}
						if (roleModelList.get(i).getModelNum().equals("bim_task")) {
//							TaskWebserviceService service = new TaskWebserviceService();
//							ITaskWebservice it = service.getPort(ITaskWebservice.class);
//							SysRole sysRole = new SysRole();
//							sysRole.setCreater(hhRole.getCreator());
//							sysRole.setCreateTime(hhRole.getCreateTime());
//							sysRole.setIsDel(1);
//							sysRole.setModifierId(hhRole.getModifier());
//							sysRole.setModifyTime(hhRole.getModifyTime());
//							sysRole.setDescription(hhRole.getRoleDescription());
//							sysRole.setName(hhRole.getRoleName());
//							sysRole.setSrcId(hhRole.getId());
//							//角色设置isDel为1，清空rolepage和rolepagecode
//							String mesg = it.updateRole("","", sysRole);
//							//清空角色、人员表
//							it.addUserRole("", roleId);
						}
						if (roleModelList.get(i).getModelNum().equals("bim_work")) {
//							WorkWebServiceService service = new WorkWebServiceService();
//							IWorkWebService it = service.getPort(IWorkWebService.class);
							SysRole sysRole = new SysRole();
							sysRole.setCreater(hhRole.getCreator());
							sysRole.setCreateTime(hhRole.getCreateTime());
							sysRole.setIsDel(1);
							sysRole.setModifierId(hhRole.getModifier());
							sysRole.setModifyTime(hhRole.getModifyTime());
							sysRole.setDescription(hhRole.getRoleDescription());
							sysRole.setName(hhRole.getRoleName());
							sysRole.setSrcId(hhRole.getId());
							//角色设置isDel为1，清空rolepage和rolepagecode
							String mesg = workWebService.updateRole("","", sysRole);
							workWebService.addUserRole("", roleId);
							workWebService.UpdaterRoleCompany("", roleId);
							workWebService.updateFinanceRoleCompany("", roleId);
						}
					}
				}
			}
			//先删除原来的模块分配
			roleDao.deleteOldRoleModelByRoleId(roleId);
			//删除角色对应页面表
			roleDao.deleteHhRolepageByRoleId(roleId);
			//删除角色对应功能
			roleDao.deleteHhRolepagecodeByRoleId(roleId);
		}
	}

	@Override
	public void saveRolePageFunction(Integer roleId, String pageIds) {
		// TODO Auto-generated method stub
		//保存角色对应页面表
		if(pageIds != null && !"".equals(pageIds)){//pageIds格式：sysId_modelId_pageId,.....
			String[] pageIdss = pageIds.split(",");
			//获取角色已绑定的pages
			List<PortalHhRolepage> rolePageList = roleDao.getRolepageListByRoleId(roleId);
			if (rolePageList.isEmpty()) {
				for (int i = 0; i < pageIdss.length; i++) {
					String[] ids = pageIdss[i].split("_");
					PortalHhRolepage hhRolepage = new PortalHhRolepage();
					hhRolepage.setSysId(Integer.parseInt(ids[0]));
					hhRolepage.setModelId(Integer.parseInt(ids[1]));
					hhRolepage.setRoleId(roleId);
					hhRolepage.setPageId(Integer.parseInt(ids[2]));
					saveRolePage(hhRolepage);
				}
			}else {
				for (int i = 0; i < pageIdss.length; i++) {
					boolean flag = false;
					for (int j = 0; j < rolePageList.size(); j++) {
						String[] ids = pageIdss[i].split("_");
						Integer pageId = Integer.parseInt(ids[2]);
						if (pageId.equals(rolePageList.get(j).getPageId())) {
							flag = true;
							//去除这次绑定和之前绑定相同的page，剩下需要删除的page
							rolePageList.remove(j);
							break;
						}
					}
					if (!flag) {
						String[] ids = pageIdss[i].split("_");
						PortalHhRolepage hhRolepage = new PortalHhRolepage();
						hhRolepage.setSysId(Integer.parseInt(ids[0]));
						hhRolepage.setModelId(Integer.parseInt(ids[1]));
						hhRolepage.setRoleId(roleId);
						hhRolepage.setPageId(Integer.parseInt(ids[2]));
						saveRolePage(hhRolepage);
					}
				}
				//删除上次绑定这次却没有绑定的页面及其对功能
				if (!rolePageList.isEmpty()) {
					String delPageIds = "";
					for (int i = 0; i < rolePageList.size(); i++) {
						if (i == rolePageList.size()-1) {
							delPageIds += rolePageList.get(i).getPageId();
						}else {
							delPageIds += rolePageList.get(i).getPageId() + ",";
						}
					}
					roleDao.deleteHhRolepageByRoleIdAndPageIds(roleId, delPageIds);
					roleDao.deleteHhRolepagecodeByRoleIdAndPageIds(roleId, delPageIds);
				}
			}
		}else {
			//删除角色对应页面表
			roleDao.deleteHhRolepageByRoleId(roleId);
			//删除角色对应功能
			roleDao.deleteHhRolepagecodeByRoleId(roleId);
		}
		
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, roleId);
		if(hhRole.getRoleStatus().toString().equals("1")) {
			//如果此角色为启用状态，并且先配置的数据权限和人员权限，则将数据权限和人员权限传输到对应模块中
			//绑定此角色的人员
			String newVcEmployeeIds = "";
			//绑定此角色的数据权限
			String compIds = "";
			//绑定此角色的财务数据权限
			String financeCompIds = "";
			//获取此角色绑定的人员
			List<HhUsersrole> userList = roleDao.getUserRoleList(hhRole.getId());
			//获取此角色的数据权限
			List<PortalHhRolecompany> compList = roleDao.getCheckedCompList(hhRole.getId());
			//获取此角色的财务数据权限
			List<PortalHhRolefinancecompany> compFinanceList = roleDao.getCheckedFinanceCompList(hhRole.getId());
			//拼接角色绑定的人员
			if(!userList.isEmpty()) {
				for (HhUsersrole user : userList) {
					newVcEmployeeIds += user.getVcEmployeeId() + ",";
				}
			}
			//拼接角色数据权限
			if(!compList.isEmpty()) {
				for (PortalHhRolecompany comp : compList) {
					compIds += comp.getCompanyId() + ",";
				}
			}
			//拼接角色财务数据权限
			if(!compFinanceList.isEmpty()) {
				for (PortalHhRolefinancecompany financeComp : compFinanceList) {
					financeCompIds += financeComp.getCompanyId() + ",";
				}
			}
			//调整数据，传给对应模块的接口
			//先获取角色所绑定的模块
			List<HhModelRegister> roleModelList = modelRegisterService.getModelsByRoleId(roleId);
			if (!roleModelList.isEmpty()) {
				for (int i = 0; i < roleModelList.size(); i++) {
					HhModelRegister roleModel = roleModelList.get(i);
					String modelNum = roleModel.getModelNum();
					if(modelNum.equals("bim_show")){
						//获取此角色在此模块下绑定的页面
//						List<HhRolepage> rolePageList = roleDao.getRolePageList(roleModel.getId(), roleId);
//						String thePageIds = "";
//						String theCodeIds = "";
//						if (!rolePageList.isEmpty()) {
//							for (int j = 0; j < rolePageList.size(); j++) {
//								//获取此角色在此模块下绑定的功能
//								List<HhRolepagecode> roleCodeList = roleDao.getRolePageCodeListNew(roleModel.getId(), roleId, rolePageList.get(j).getPageId());
//								if (!roleCodeList.isEmpty()) {
//									for (int k = 0; k < roleCodeList.size(); k++) {
//										if (k == roleCodeList.size()-1 && j == rolePageList.size()-1) {
//											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId();
//										}else {
//											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId() + ",";
//										}
//									}
//								}
//								if (j == rolePageList.size()-1) {
//									thePageIds += rolePageList.get(j).getPageId();
//								}else {
//									thePageIds += rolePageList.get(j).getPageId()+",";
//								}
//							}
//						}
//						ShowWebserviceService service = new ShowWebserviceService();
//						IShowWebservice is = service.getPort(IShowWebservice.class);
//						Role role = new Role();
//						role.setCreateTime(hhRole.getCreateTime());
//						role.setCreator(hhRole.getCreator());
//						role.setIsDel(0);
//						role.setIsUse(hhRole.getIsUse());
//						role.setModifier(hhRole.getModifier());
//						role.setModifyTime(hhRole.getModifyTime());
//						role.setPortalId(hhRole.getId());
//						role.setRoleDescription(hhRole.getRoleDescription());
//						role.setRoleName(hhRole.getRoleName());
//						role.setRoleNum(hhRole.getRoleNum());
//						String msg = is.showUpdateRole(role, thePageIds, theCodeIds);
//						is.showUpdaterUsersRole(newVcEmployeeIds, roleId);
//						is.showUpdaterRoleCompany(compIds, roleId);
//						is.showUpdateRoleFinanceCompany(financeCompIds, roleId);
					}
					if (modelNum.equals("bim_task")) {
						//获取此角色在此模块下绑定的页面
//						List<HhRolepage> rolePageList = roleDao.getRolePageList(roleModel.getId(), hhRole.getId());
//						String thePageIds = "";
//						String theCodeIds = "";
//						if (!rolePageList.isEmpty()) {
//							for (int j = 0; j < rolePageList.size(); j++) {
//								//获取此角色在此模块下绑定的功能
//								List<HhRolepagecode> roleCodeList = roleDao.getRolePageCodeListNew(roleModel.getId(), hhRole.getId(), rolePageList.get(j).getPageId());
//								if (!roleCodeList.isEmpty()) {
//									for (int k = 0; k < roleCodeList.size(); k++) {
//										if (k == roleCodeList.size()-1 && j == rolePageList.size()-1) {
//											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId();
//										}else {
//											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId() + ",";
//										}
//									}
//								}
//								if (j == rolePageList.size()-1) {
//									thePageIds += rolePageList.get(j).getPageId();
//								}else {
//									thePageIds += rolePageList.get(j).getPageId()+",";
//								}
//							}
//						}
//						TaskWebserviceService service = new TaskWebserviceService();
//						ITaskWebservice it = service.getPort(ITaskWebservice.class);
//						SysRole sysRole = new SysRole();
//						sysRole.setCreater(hhRole.getCreator());
//						sysRole.setCreateTime(hhRole.getCreateTime());
//						sysRole.setIsDel(0);
//						sysRole.setModifierId(hhRole.getModifier());
//						sysRole.setModifyTime(hhRole.getModifyTime());
//						sysRole.setDescription(hhRole.getRoleDescription());
//						sysRole.setName(hhRole.getRoleName());
//						sysRole.setSrcId(hhRole.getId());
//						String mesg = it.updateRole(thePageIds, theCodeIds, sysRole);
//						it.addUserRole(newVcEmployeeIds, roleId);
					}
					if (modelNum.equals("bim_work")) {
						//获取此角色在此模块下绑定的页面
						List<PortalHhRolepage> rolePageList = roleDao.getRolePageList(roleModel.getId(), hhRole.getId());
						String thePageIds = "";
						String theCodeIds = "";
						if (!rolePageList.isEmpty()) {
							for (int j = 0; j < rolePageList.size(); j++) {
								//获取此角色在此模块下绑定的功能
								List<PortalHhRolepagecode> roleCodeList = roleDao.getRolePageCodeListNew(roleModel.getId(), hhRole.getId(), rolePageList.get(j).getPageId());
								if (!roleCodeList.isEmpty()) {
									for (int k = 0; k < roleCodeList.size(); k++) {
										if (k == roleCodeList.size()-1 && j == rolePageList.size()-1) {
											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId();
										}else {
											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId() + ",";
										}
									}
								}
								if (j == rolePageList.size()-1) {
									thePageIds += rolePageList.get(j).getPageId();
								}else {
									thePageIds += rolePageList.get(j).getPageId()+",";
								}
							}
						}
//						WorkWebServiceService service = new WorkWebServiceService();
//						IWorkWebService is = service.getPort(IWorkWebService.class);
						SysRole sysRole = new SysRole();
						sysRole.setCreater(hhRole.getCreator());
						sysRole.setCreateTime(hhRole.getCreateTime());
						sysRole.setIsDel(0);
						sysRole.setModifierId(hhRole.getModifier());
						sysRole.setModifyTime(hhRole.getModifyTime());
						sysRole.setDescription(hhRole.getRoleDescription());
						sysRole.setName(hhRole.getRoleName());
						sysRole.setSrcId(hhRole.getId());
						String mesg = workWebService.updateRole(thePageIds, theCodeIds, sysRole);
						workWebService.addUserRole(newVcEmployeeIds, roleId);
						workWebService.UpdaterRoleCompany(compIds, roleId);
						workWebService.updateFinanceRoleCompany(financeCompIds, roleId);
					}
				}
			}
		}
	}

	@Override
	public void saveRoleCodeFunction(Integer roleId, String codeIds) {
		// TODO Auto-generated method stub
		//删除角色对应页面选中的功能表
		roleDao.deleteHhRolepagecodeByRoleId(roleId);
		
		//保存角色对应页面选择的功能
		if(codeIds != null && !"".equals(codeIds)){//codeIds格式:sysId_modelId_pageId_codeId
			String[] codeIdss = codeIds.split(",");
			for (int i = 0; i < codeIdss.length; i++) {
				String[] tempCodeIds = codeIdss[i].split("_");
				PortalHhRolepagecode hhRolepagecode = new PortalHhRolepagecode();
				hhRolepagecode.setSysId(Integer.parseInt(tempCodeIds[0]));
				hhRolepagecode.setModelId(Integer.parseInt(tempCodeIds[1]));
				hhRolepagecode.setPageId(Integer.valueOf(tempCodeIds[2]));
				hhRolepagecode.setCodeId(Integer.valueOf(tempCodeIds[3]));
				hhRolepagecode.setRoleId(roleId);
				saveRolepagecode(hhRolepagecode);
			}
		}
		
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, roleId);
		if(hhRole.getRoleStatus().toString().equals("1")) {
			//调整数据，传给对应模块的接口
			//先获取角色所绑定的模块
			List<HhModelRegister> roleModelList = modelRegisterService.getModelsByRoleId(roleId);
			if (!roleModelList.isEmpty()) {
				for (int i = 0; i < roleModelList.size(); i++) {
					HhModelRegister roleModel = roleModelList.get(i);
					String modelNum = roleModel.getModelNum();
					if(modelNum.equals("bim_show")){
						//获取此角色在此模块下绑定的页面
//						List<HhRolepage> rolePageList = roleDao.getRolePageList(roleModel.getId(), roleId);
//						String thePageIds = "";
//						String theCodeIds = "";
//						if (!rolePageList.isEmpty()) {
//							for (int j = 0; j < rolePageList.size(); j++) {
//								//获取此角色在此模块下绑定的功能
//								List<HhRolepagecode> roleCodeList = roleDao.getRolePageCodeListNew(roleModel.getId(), roleId, rolePageList.get(j).getPageId());
//								if (!roleCodeList.isEmpty()) {
//									for (int k = 0; k < roleCodeList.size(); k++) {
//										if (k == roleCodeList.size()-1 && j == rolePageList.size()-1) {
//											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId();
//										}else {
//											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId() + ",";
//										}
//									}
//								}
//								if (j == rolePageList.size()-1) {
//									thePageIds += rolePageList.get(j).getPageId();
//								}else {
//									thePageIds += rolePageList.get(j).getPageId()+",";
//								}
//							}
//						}
//						ShowWebserviceService service = new ShowWebserviceService();
//						IShowWebservice is = service.getPort(IShowWebservice.class);
//						Role role = new Role();
//						role.setCreateTime(hhRole.getCreateTime());
//						role.setCreator(hhRole.getCreator());
//						role.setIsDel(0);
//						role.setIsUse(hhRole.getIsUse());
//						role.setModifier(hhRole.getModifier());
//						role.setModifyTime(hhRole.getModifyTime());
//						role.setPortalId(hhRole.getId());
//						role.setRoleDescription(hhRole.getRoleDescription());
//						role.setRoleName(hhRole.getRoleName());
//						role.setRoleNum(hhRole.getRoleNum());
//						String msg = is.showUpdateRole(role, thePageIds, theCodeIds);
					}
					if (modelNum.equals("bim_task")) {
						//获取此角色在此模块下绑定的页面
//						List<HhRolepage> rolePageList = roleDao.getRolePageList(roleModel.getId(), hhRole.getId());
//						String thePageIds = "";
//						String theCodeIds = "";
//						if (!rolePageList.isEmpty()) {
//							for (int j = 0; j < rolePageList.size(); j++) {
//								//获取此角色在此模块下绑定的功能
//								List<HhRolepagecode> roleCodeList = roleDao.getRolePageCodeListNew(roleModel.getId(), hhRole.getId(), rolePageList.get(j).getPageId());
//								if (!roleCodeList.isEmpty()) {
//									for (int k = 0; k < roleCodeList.size(); k++) {
//										if (k == roleCodeList.size()-1 && j == rolePageList.size()-1) {
//											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId();
//										}else {
//											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId() + ",";
//										}
//									}
//								}
//								if (j == rolePageList.size()-1) {
//									thePageIds += rolePageList.get(j).getPageId();
//								}else {
//									thePageIds += rolePageList.get(j).getPageId()+",";
//								}
//							}
//						}
//						TaskWebserviceService service = new TaskWebserviceService();
//						ITaskWebservice it = service.getPort(ITaskWebservice.class);
//						SysRole sysRole = new SysRole();
//						sysRole.setCreater(hhRole.getCreator());
//						sysRole.setCreateTime(hhRole.getCreateTime());
//						sysRole.setIsDel(0);
//						sysRole.setModifierId(hhRole.getModifier());
//						sysRole.setModifyTime(hhRole.getModifyTime());
//						sysRole.setDescription(hhRole.getRoleDescription());
//						sysRole.setName(hhRole.getRoleName());
//						sysRole.setSrcId(hhRole.getId());
//						String mesg = it.updateRole(thePageIds, theCodeIds, sysRole);
//						System.out.println(mesg);
					}
					if (modelNum.equals("bim_work")) {
						//获取此角色在此模块下绑定的页面
						List<PortalHhRolepage> rolePageList = roleDao.getRolePageList(roleModel.getId(), hhRole.getId());
						String thePageIds = "";
						String theCodeIds = "";
						if (!rolePageList.isEmpty()) {
							for (int j = 0; j < rolePageList.size(); j++) {
								//获取此角色在此模块下绑定的功能
								List<PortalHhRolepagecode> roleCodeList = roleDao.getRolePageCodeListNew(roleModel.getId(), hhRole.getId(), rolePageList.get(j).getPageId());
								if (!roleCodeList.isEmpty()) {
									for (int k = 0; k < roleCodeList.size(); k++) {
										if (k == roleCodeList.size()-1 && j == rolePageList.size()-1) {
											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId();
										}else {
											theCodeIds += roleModel.getId() + "_" + rolePageList.get(j).getPageId() + "_" + roleCodeList.get(k).getCodeId() + ",";
										}
									}
								}
								if (j == rolePageList.size()-1) {
									thePageIds += rolePageList.get(j).getPageId();
								}else {
									thePageIds += rolePageList.get(j).getPageId()+",";
								}
							}
						}
//						WorkWebServiceService service = new WorkWebServiceService();
//						IWorkWebService it = service.getPort(IWorkWebService.class);
						SysRole sysRole = new SysRole();
						sysRole.setCreater(hhRole.getCreator());
						sysRole.setCreateTime(hhRole.getCreateTime());
						sysRole.setIsDel(0);
						sysRole.setModifierId(hhRole.getModifier());
						sysRole.setModifyTime(hhRole.getModifyTime());
						sysRole.setDescription(hhRole.getRoleDescription());
						sysRole.setName(hhRole.getRoleName());
						sysRole.setSrcId(hhRole.getId());
						String mesg = workWebService.updateRole(thePageIds, theCodeIds, sysRole);
						System.out.println(mesg);
					}
				}
			}
		}
	}

	@Override
	public List<HhGroupUserRole> getSelectedGroupList(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.getSelectedGroupList(id);
	}

	@Override
	public void saveOrUpdateRoleUserByGroup(HhUsersrole userRole,
			String groupIds) {
		// TODO Auto-generated method stub
		//获取原来hh_groupuserrole表中的数据
		List<HhGroupUserRole> oldGroupList = roleDao.getSelectedGroupList(userRole.getRoleId());
		//如果depIds不为""，再进行数据处理
		if (!"".equals(groupIds) && groupIds != null) {
			//将现在获取groupIds进行处理，处理成数组
			String [] groupIdss = groupIds.split(",");
			//创建一个新的List，存放新的groupId
			List<HhGroupUserRole> newGroupList = new ArrayList<HhGroupUserRole>();
			//循环新的groupIdss，与现oldGroupList进行对比，有相同的groupId就从oldGroupList中去掉，最后oldGroupList就只剩下这次没有选中的，就进行删除；没有的保留在newGroupList中，进行新增
			for (int i = 0; i < groupIdss.length; i++) {
				//flag用于判断是否有相同groupId
				boolean flag = false;
				for (int j = 0; j < oldGroupList.size(); j++) {
					if (groupIdss[i].equals(oldGroupList.get(j).getGroupId().toString())) {
						oldGroupList.remove(j);
						flag = true;
						break;
					}
				}
				if(!flag) {
					HhGroupUserRole newGroup = new HhGroupUserRole();
					newGroup.setGroupId(Integer.parseInt(groupIdss[i]));
					newGroupList.add(newGroup);
				}
			}
			if (!oldGroupList.isEmpty()) {
				//过滤之后，oldGroupList中剩下没有被选中的groupIds，进行删除
				String needDelGroupIds = "";
				for (HhGroupUserRole oldGroup : oldGroupList) {
					needDelGroupIds += "'" + oldGroup.getGroupId() + "',";
					
				}
				if (!needDelGroupIds.equals("")) {
					//先删除原来groupId对应人员在的hh_usersrole表中的数据
					roleDao.deleteOldUserRoleByGroupIds(userRole.getRoleId(), needDelGroupIds.substring(0,needDelGroupIds.length()-1));
					//再删除原来hh_groupuserrole表中的数据
					roleDao.deleteGroupUsersRoleByGroupIds(userRole.getRoleId(), needDelGroupIds.substring(0,needDelGroupIds.length()-1));
				}
			}
			if (!newGroupList.isEmpty()) {
				//过滤之后，newDepList中为新加入的depIds，进行新增
				String newGroupIds = "";
				List<HhUsers> users1 = new ArrayList<HhUsers>();
				for (int i = 0; i < newGroupList.size(); i++) {
					//先更新hh_groupuserrole表中数据
					HhGroupUserRole newGroupUserRole = new HhGroupUserRole();
					newGroupUserRole.setRoleId(userRole.getRoleId());
					newGroupUserRole.setGroupId(newGroupList.get(i).getGroupId());
					commonService.save(newGroupUserRole);
					//为更新hh_usersrole表做准备
					newGroupIds += "'" + newGroupList.get(i).getGroupId() + "',";
				}
				if (!newGroupIds.equals("")) {
					users1 = selectUserService.getUsersByGroupIds(newGroupIds.substring(0,newGroupIds.length()-1));
				}
				
				for (int i = 0; i < users1.size(); i++) {
						HhUsersrole newUserRole = new HhUsersrole();
						newUserRole.setRoleId(userRole.getRoleId());
						newUserRole.setSysId(userRole.getSysId());
						newUserRole.setVcEmployeeId(users1.get(i).getVcEmployeeId());
						commonService.save(newUserRole);
				}
			}
		}else {
			//先删除原来groupId对应人员在的hh_usersrole表中的数据
			roleDao.deleteOldUserRoleByRoleId(userRole.getRoleId());
			//再删除原来hh_groupuserrole表中的数据
			roleDao.deleteGroupUsersRoleByRoleId(userRole.getRoleId());
		}
		
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, userRole.getRoleId());
		if(hhRole.getRoleStatus().toString().equals("1")) {
			//用于接口传输数据
			String vcEmployeeIds = "";
			List<HhUsersrole> nowUserRoleList = roleDao.getUserRoleList(userRole.getRoleId());
			if (!nowUserRoleList.isEmpty()) {
				for (int i = 0; i < nowUserRoleList.size(); i++) {
					vcEmployeeIds += nowUserRoleList.get(i).getVcEmployeeId() + ",";
				}
			}
			
			//调用接口传入对应模块中
			List<HhModelRegister> roleModelList = modelRegisterService.getModelsByRoleId(userRole.getRoleId());
			if (!roleModelList.isEmpty()) {
				for (int i = 0; i < roleModelList.size(); i++) {
					HhModelRegister roleModel = roleModelList.get(i);
					String modelNum = roleModel.getModelNum();
					if(modelNum.equals("bim_show")){
//						ShowWebserviceService service = new ShowWebserviceService();
//						IShowWebservice is = service.getPort(IShowWebservice.class);
//						Integer roleId = userRole.getRoleId();
//						is.showUpdaterUsersRole(vcEmployeeIds, roleId);
					}
					if(modelNum.equals("bim_task")) {
//						TaskWebserviceService service = new TaskWebserviceService();
//						ITaskWebservice it = service.getPort(ITaskWebservice.class);
//						Integer roleId = userRole.getRoleId();
//						String msg = it.addUserRole(vcEmployeeIds, roleId);
					}
					if(modelNum.equals("bim_work")) {
//						WorkWebServiceService service = new WorkWebServiceService();
//						IWorkWebService it = service.getPort(IWorkWebService.class);
						Integer roleId = userRole.getRoleId();
						String msg = workWebService.addUserRole(vcEmployeeIds, roleId);
					}
				}
			}
		}
	}

	@Override
	public List<HhRoleSys> getRoleSysListByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.getRoleSysListByRoleId(roleId);
	}

	@Override
	public List<HhRoleModel> getRoleModelListByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.getRoleModelListByRoleId(roleId);
	}

	@Override
	public void saveRoleSysFunction(Integer roleId, String sysIds) {
		// TODO Auto-generated method stub
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, roleId);
		//获取角色已绑定的系统
		List<HhSysRegister> roleSysList = sysRegisterService.getRegistedListByRoleId(roleId);
		//保存角色系统分配
		if (sysIds !=null && !"".equals(sysIds)) {
			String [] sysIdss = sysIds.split(",");
			if (roleSysList.isEmpty()) {
				for (int i = 0; i < sysIdss.length; i++) {
					HhRoleSys roleSys = new HhRoleSys();
					roleSys.setRoleId(roleId);
					roleSys.setSysId(Integer.parseInt(sysIdss[i]));
					commonService.saveOrUpdate(roleSys);
				}
			}else {
				for (int i = 0; i < sysIdss.length; i++) {
					boolean flag = false;
					for (int j = 0; j < roleSysList.size(); j++) {
						Integer sysId = Integer.parseInt(sysIdss[i]);
						if (sysId.equals(roleSysList.get(j).getId())) {
							flag = true;
							//去除这次绑定和之前绑定相同的sys，剩下需要删除的sys
							roleSysList.remove(j);
							break;
						}
					}
					if (!flag) {
						HhRoleSys roleSys = new HhRoleSys();
						roleSys.setRoleId(roleId);
						roleSys.setSysId(Integer.parseInt(sysIdss[i]));
						commonService.saveOrUpdate(roleSys);
					}
				}
				//删除上次绑定这次却没有绑定的sys及其对应模块、页面和功能
				if (!roleSysList.isEmpty()) {
					String delSysIds = "";
					for (int i = 0; i < roleSysList.size(); i++) {
						//为启用状态时，调用接口
						if(hhRole.getRoleStatus().toString().equals("1")) {
							//调用接口，删除角色、角色对应模块、rolepage和rolepagecode、人员权限、数据权限和财务数据权限
							if(roleSysList.get(i).getSysNum().equals("bimc")) {
//								ShowWebserviceService service = new ShowWebserviceService();
//								IShowWebservice is = service.getPort(IShowWebservice.class);
//								Role role = new Role();
//								role.setCreateTime(hhRole.getCreateTime());
//								role.setCreator(hhRole.getCreator());
//								role.setIsDel(1);
//								role.setIsUse(hhRole.getIsUse());
//								role.setModifier(hhRole.getModifier());
//								role.setModifyTime(hhRole.getModifyTime());
//								role.setPortalId(hhRole.getId());
//								role.setRoleDescription(hhRole.getRoleDescription());
//								role.setRoleName(hhRole.getRoleName());
//								role.setRoleNum(hhRole.getRoleNum());
//								//角色设置isDel为1，清空rolepage和rolepagecode
//								String msg = is.showUpdateRole(role, "", "");
//								//清空角色、人员表
//								is.showUpdaterUsersRole("", roleId);
//								//清空角色数据表
//								is.showUpdaterRoleCompany("", roleId);
//								//清空角色财务数据表
//								is.showUpdateRoleFinanceCompany("", roleId);
//								
//								TaskWebserviceService service1 = new TaskWebserviceService();
//								ITaskWebservice it = service1.getPort(ITaskWebservice.class);
//								SysRole sysRole = new SysRole();
//								sysRole.setCreater(hhRole.getCreator());
//								sysRole.setCreateTime(hhRole.getCreateTime());
//								sysRole.setIsDel(1);
//								sysRole.setModifierId(hhRole.getModifier());
//								sysRole.setModifyTime(hhRole.getModifyTime());
//								sysRole.setDescription(hhRole.getRoleDescription());
//								sysRole.setName(hhRole.getRoleName());
//								sysRole.setSrcId(hhRole.getId());
//								//角色设置isDel为1，清空rolepage和rolepagecode
//								String mesg = it.updateRole("","", sysRole);
//								//清空角色、人员表
//								it.addUserRole("", roleId);
//								System.out.println(mesg);
//								
//								
//								WorkWebServiceService service2 = new WorkWebServiceService();
//								IWorkWebService it2 = service2.getPort(IWorkWebService.class);
//								com.softline.client.work.SysRole sysRole2 = new com.softline.client.work.SysRole();
//								sysRole2.setCreater(hhRole.getCreator());
//								sysRole2.setCreateTime(hhRole.getCreateTime());
//								sysRole2.setIsDel(1);
//								sysRole2.setModifierId(hhRole.getModifier());
//								sysRole2.setModifyTime(hhRole.getModifyTime());
//								sysRole2.setDescription(hhRole.getRoleDescription());
//								sysRole2.setName(hhRole.getRoleName());
//								sysRole2.setSrcId(hhRole.getId());
//								//角色设置isDel为1，清空rolepage和rolepagecode
//								it2.updateRole("","", sysRole2);
//								it2.addUserRole("", roleId);
//								it2.updaterRoleCompany("", roleId);
//								it2.updateFinanceRoleCompany("", roleId);
								
							}
						}
						if (i == roleSysList.size()-1) {
							delSysIds += roleSysList.get(i).getId();
						}else {
							delSysIds += roleSysList.get(i).getId() + ",";
						}
					}
					roleDao.deleteHhRoleSysByRoleIdAndSysIds(roleId, delSysIds);
					roleDao.deleteHhRoleModelByRoleIdAndSysIds(roleId, delSysIds);
					roleDao.deleteHhRolepageByRoleIdAndSysIds(roleId, delSysIds);
					roleDao.deleteHhRolepagecodeByRoleIdAndSysIds(roleId, delSysIds);
				}
			}
		}else {
			//为启用状态时，调用接口
			if(hhRole.getRoleStatus().toString().equals("1")) {
				if (!roleSysList.isEmpty()) {
					for (int i = 0; i < roleSysList.size(); i++) {
						//调用接口，删除对应系统的模块、rolepage和rolepagecode
						if(roleSysList.get(i).getSysNum().equals("bimc")) {
//							ShowWebserviceService service = new ShowWebserviceService();
//							IShowWebservice is = service.getPort(IShowWebservice.class);
//							Role role = new Role();
//							role.setCreateTime(hhRole.getCreateTime());
//							role.setCreator(hhRole.getCreator());
//							role.setIsDel(1);
//							role.setIsUse(hhRole.getIsUse());
//							role.setModifier(hhRole.getModifier());
//							role.setModifyTime(hhRole.getModifyTime());
//							role.setPortalId(hhRole.getId());
//							role.setRoleDescription(hhRole.getRoleDescription());
//							role.setRoleName(hhRole.getRoleName());
//							role.setRoleNum(hhRole.getRoleNum());
//							//角色设置isDel为1，清空rolepage和rolepagecode
//							String msg = is.showUpdateRole(role, "", "");
//							//清空角色、人员表
//							is.showUpdaterUsersRole("", roleId);
//							//清空角色数据表
//							is.showUpdaterRoleCompany("", roleId);
//							//清空角色财务数据表
//							is.showUpdateRoleFinanceCompany("", roleId);
//							
//							TaskWebserviceService service1 = new TaskWebserviceService();
//							ITaskWebservice it = service1.getPort(ITaskWebservice.class);
//							SysRole sysRole = new SysRole();
//							sysRole.setCreater(hhRole.getCreator());
//							sysRole.setCreateTime(hhRole.getCreateTime());
//							sysRole.setIsDel(1);
//							sysRole.setModifierId(hhRole.getModifier());
//							sysRole.setModifyTime(hhRole.getModifyTime());
//							sysRole.setDescription(hhRole.getRoleDescription());
//							sysRole.setName(hhRole.getRoleName());
//							sysRole.setSrcId(hhRole.getId());
//							//角色设置isDel为1，清空rolepage和rolepagecode
//							String mesg = it.updateRole("","", sysRole);
//							//清空角色、人员表
//							it.addUserRole("", roleId);
//							System.out.println(mesg);
//							
//							WorkWebServiceService service2 = new WorkWebServiceService();
//							IWorkWebService it2 = service2.getPort(IWorkWebService.class);
//							com.softline.client.work.SysRole sysRole2 = new com.softline.client.work.SysRole();
//							sysRole2.setCreater(hhRole.getCreator());
//							sysRole2.setCreateTime(hhRole.getCreateTime());
//							sysRole2.setIsDel(1);
//							sysRole2.setModifierId(hhRole.getModifier());
//							sysRole2.setModifyTime(hhRole.getModifyTime());
//							sysRole2.setDescription(hhRole.getRoleDescription());
//							sysRole2.setName(hhRole.getRoleName());
//							sysRole2.setSrcId(hhRole.getId());
//							//角色设置isDel为1，清空rolepage和rolepagecode
//							it2.updateRole("","", sysRole2);
//							it2.addUserRole("", roleId);
//							it2.updaterRoleCompany("", roleId);
//							it2.updateFinanceRoleCompany("", roleId);
						}
					}
				}
			}
			//先删除原来的模块分配
			roleDao.deleteOldRoleModelByRoleId(roleId);
			//删除角色对应页面表
			roleDao.deleteHhRolepageByRoleId(roleId);
			//删除角色对应功能
			roleDao.deleteHhRolepagecodeByRoleId(roleId);
			//先删除原来的系统分配
			roleDao.deleteOldRoleSysByRoleId(roleId);
		}
	}

	@Override
	public List<PortalHhRolepage> getRolepageListByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.getRolepageListByRoleId(roleId);
	}

	@Override
	public List<PortalHhRolepagecode> getRolepagecodeListByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.getRolepagecodeListByRoleId(roleId);
	}

	@Override
	public HhRole getHhRoleByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.getHhRoleByRoleId(roleId);
	}

	@Override
	public List<PortalHhRolecompany> getRoleCompanyList(Integer roleId) {
		return roleDao.getRoleCompanyList(roleId);
	}
	
	public List<Object> getPageTypeGroupBy(Integer sysId,Integer modelId){
		return roleDao.getPageTypeGroupBy(sysId, modelId);
	}
	
	@Override
	public void saveOrUpdateRoleCopy(HhRole hhRole,Integer copyId) {
		// TODO Auto-generated method stub
		roleDao.saveOrUpdateRole(hhRole);
		
		//复制系统
		List<HhRoleSys> roleSysList= roleDao.getRoleSysListByRoleId(copyId);
		StringBuilder sb = new StringBuilder();  
	    for (int i = 0; i < roleSysList.size(); i++) {  
	        sb.append(roleSysList.get(i).getSysId()).append(",");  
	    }  
	    String sysIds= roleSysList.isEmpty()?"":sb.toString().substring(0, sb.toString().length() - 1); 
		saveRoleSysFunction(hhRole.getId(),sysIds);
		
		//复制模块
		List<HhRoleModel> roleModelList= roleDao.getRoleModelListByRoleId(copyId);
		StringBuilder sb1 = new StringBuilder();  
	    for (int i = 0; i < roleModelList.size(); i++) {
	    	HhRoleModel m = roleModelList.get(i);
	        sb1.append(m.getSysId()+"_"+m.getModelId()).append(",");  
	    }  
	    String modelIds= roleModelList.isEmpty()?"":sb1.toString().substring(0, sb1.toString().length() - 1); 
	    saveRoleModelFunction(hhRole.getId(),modelIds);
	    
	    //复制页面
	    List<PortalHhRolepage> rolePageList = roleDao.getRolepageListByRoleId(copyId);
	    StringBuilder sb2 = new StringBuilder();  
	    for (int i = 0; i < rolePageList.size(); i++) {
	    	PortalHhRolepage m = rolePageList.get(i);
	        sb2.append(m.getSysId()+"_"+m.getModelId()+"_"+m.getPageId()).append(",");  
	    }  
	    String pageIds= rolePageList.isEmpty()?"":sb2.toString().substring(0, sb2.toString().length() - 1); 
	    saveRolePageFunction(hhRole.getId(),pageIds);
	    
	    //复制按钮
	    List<PortalHhRolepagecode> rolePagecodeList=roleDao.getRolepagecodeListByRoleId(copyId);
	    StringBuilder sb3 = new StringBuilder();  
	    for (int i = 0; i < rolePagecodeList.size(); i++) {
	    	PortalHhRolepagecode m = rolePagecodeList.get(i);
	        sb3.append(m.getSysId()+"_"+m.getModelId()+"_"+m.getPageId()+"_"+m.getCodeId()).append(",");  
	    }  
	    String codeIds= rolePagecodeList.isEmpty()?"":sb3.toString().substring(0, sb3.toString().length() - 1); 
	    saveRoleCodeFunction(hhRole.getId(),codeIds);
	    
	}

	@Override
	public List<HhRole> getHhRolesByRoleIds(String roleIds) {
		List<HhRole> roles = roleDao.getHhRolesByRoleIds(roleIds);
		for(int i = 0; i < roles.size(); i++){
			HhRole entity = roles.get(i);
			entity.setFinanceCompanyNames(roleDao.financeCompanys(entity.getId()));
			entity.setCompanyNames(roleDao.hrCompanys(entity.getId()));
			entity.setUsers(roleDao.getSelectHhUsersList(entity.getId()));
			entity.setAuthTreeData(getAuthTreeData(entity.getId()));
		}
		return roles;
	}
	
	/**
	 * 根据roleId获取权限
	 * @param roleId
	 * @param modelId
	 * @return
	 */
	private String getAuthTreeData(Integer roleId){
		List<SysAuthTree> sysAuthTrees = new ArrayList<SysAuthTree>();
		List<HhRoleModel> roleModel = roleDao.getRoleModelListByRoleId(roleId);
		for(int i = 0; i < roleModel.size(); i++){
			HhRoleModel entity = roleModel.get(i);
			SysAuthTree temp = new SysAuthTree();
			temp.setId("model"+entity.getModelId());
			temp.setPId("0");
			temp.setName(entity.getModelName());
			sysAuthTrees.add(temp);
		}
		List<PortalHhRolepage> rolePage = roleDao.getRolepageListByRoleId(roleId);
		for(int i = 0; i < rolePage.size(); i++){
			PortalHhRolepage entity = rolePage.get(i);
			SysAuthTree temp = new SysAuthTree();
			temp.setId("page" + entity.getPageId());
			temp.setPId("model"+entity.getModelId());
			temp.setName(entity.getPageName());
			sysAuthTrees.add(temp);
		}
		List<PortalHhRolepagecode> rolePageCode = roleDao.getRolepagecodeListByRoleId(roleId);
		for(int i = 0; i < rolePageCode.size(); i++){
			PortalHhRolepagecode entity = rolePageCode.get(i);
			SysAuthTree temp = new SysAuthTree();
			temp.setId("code" + entity.getCodeId());
			temp.setPId("page" + entity.getPageId());
			temp.setName(entity.getCodeName());
			sysAuthTrees.add(temp);
		}
		return JSON.toJSONString(sysAuthTrees);
	}

}
