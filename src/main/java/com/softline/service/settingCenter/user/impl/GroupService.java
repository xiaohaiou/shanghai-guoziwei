package com.softline.service.settingCenter.user.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.softline.client.show.IShowWebservice;
import com.softline.client.show.ShowWebserviceService;
import com.softline.client.task.ITaskWebservice;
import com.softline.client.task.TaskWebserviceService;
import com.softline.dao.settingCenter.user.IGroupDao;
import com.softline.dao.settingCenter.user.IRoleDao;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhGroup;
import com.softline.entity.settingCenter.HhGroupUser;
import com.softline.entity.settingCenter.HhGroupUserRole;
import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.entity.settingCenter.HhRole;
import com.softline.entity.settingCenter.HhUsersrole;
import com.softline.entityTemp.VUserRoles;
import com.softline.service.settingCenter.user.IGroupService;
import com.softline.service.settingCenter.user.IModelRegisterService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IWorkWebService;
import com.softline.util.MsgPage;

@Service("groupService")
public class GroupService implements IGroupService {
	@Autowired
	@Qualifier("groupDao")
	private IGroupDao groupDao;
	
	@Autowired
	@Qualifier("roleDao")
	private IRoleDao roleDao;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "modelRegisterService")
	private IModelRegisterService modelRegisterService;
	
	@Resource(name = "workWebService")
	private IWorkWebService workWebService;
	
	@Override
	public MsgPage getUserMsgGroupList(String qGroupName, Integer qGroupStatus, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = groupDao.getAllRowCount(qGroupName, qGroupStatus);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HhGroup> list = groupDao.getUserGroupList(qGroupName, qGroupStatus, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	@Override
	public MsgPage getUserRoleList(String vcAccount,String qUserName,
			Integer curPageNum, Integer pageSize,Integer cFlag) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = groupDao.getNewAllRowCount(vcAccount,qUserName,cFlag);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<VUserRoles> list = groupDao.getUserRoleList(vcAccount,qUserName, offset, pageSize,cFlag);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	@Override
	public List<VUserRoles> getUserRoleList(String vcAccount,String qUserName,Integer cFlag){
		return groupDao.getUserRoleList(vcAccount, qUserName, null, null,cFlag);
	}

	@Override
	public HhGroup getTheUserGroup(Integer id) {
		// TODO Auto-generated method stub
		return groupDao.getTheUserGroup(id);
	}

	@Override
	public void saveOrUpdateUserGroup(HhGroup group) {
		// TODO Auto-generated method stub
		groupDao.saveOrUpdateUserGroup(group);
	}

	@Override
	public void deleteUserGroup(Integer id) {
		// TODO Auto-generated method stub
		groupDao.deleteUserGroup(id);
		//删除人员组对应的用户
		groupDao.deleteAllGroupUser(id);
	}

	/*@Override
	public void saveOrUpdateGroupUser(HhGroupUser groupUser, String vcEmployeeIds) {
		// TODO Auto-generated method stub
		//先删除原来表中的数据
		groupDao.deleteAllGroupUser(groupUser.getGroupId());
		//如果vcEmployeeIds不为""，再添加新的数据
		if (!"".equals(vcEmployeeIds) && vcEmployeeIds != null) {
			String [] vcEmployeeIdss = (vcEmployeeIds.split(","));
			for (int i = 0; i < vcEmployeeIdss.length; i++) {
				HhGroupUser newGroupUser = new HhGroupUser();
				newGroupUser.setGroupId(groupUser.getGroupId());
				newGroupUser.setVcEmployeeId(vcEmployeeIdss[i]);
				commonService.save(newGroupUser);
			}
		}
	}*/

	@Override
	public List<HhUsers> getSelectHhUsersList(Integer groupId) {
		// TODO Auto-generated method stub
		return groupDao.getSelectHhUsersList(groupId);
	}

	@Override
	public void saveOrUpdateUserGroupAndUser(HhGroup group, String vcEmployeeIds) {
		// TODO Auto-generated method stub
		groupDao.saveOrUpdateUserGroup(group);
		//获取用户组绑定的所有角色
		List<HhGroupUserRole> roles = groupDao.getTheGroupUserRole(group.getId());
		if (!"".equals(vcEmployeeIds) && vcEmployeeIds != null) {
			//获取原来HhGroupUser表中的数据
			List<HhGroupUser> oldGroupUserList = groupDao.getGroupUserList(group.getId());
			//将现在获取vcEmployeeIds进行处理，处理成数组
			String [] vcEmployeeIdss = vcEmployeeIds.split(",");
			//创建一个新的List，存放新的vcEmployeeId
			List<HhGroupUser> newGroupUserList = new ArrayList<HhGroupUser>();
			//循环新的vcEmployeeIdss，与现oldGroupUserList进行对比，有相同的vcEmployeeId就从oldGroupUserList中去掉，最后oldGroupUserList就只剩下这次没有选中的，就进行删除；
			//没有的保留在newGroupUserList中，进行新增
			for (int i = 0; i < vcEmployeeIdss.length; i++) {
				//flag用于判断是否有相同depId
				boolean flag = false;
				if(!oldGroupUserList.isEmpty()) {
					for (int j = 0; j < oldGroupUserList.size(); j++) {
						if (vcEmployeeIdss[i].equals(oldGroupUserList.get(j).getVcEmployeeId())) {
							oldGroupUserList.remove(j);
							flag = true;
							break;
						}
					}
				}
				if(!flag) {
					HhGroupUser newGroupUser = new HhGroupUser();
					newGroupUser.setVcEmployeeId(vcEmployeeIdss[i]);
					newGroupUserList.add(newGroupUser);
				}
			}
			if (!oldGroupUserList.isEmpty()) {
				//过滤之后，oldUserRoleList中剩下没有被选中的vcEmployeeIds，进行删除
				String needDelVcEmployeeIds = "";
				for (HhGroupUser oldGroupUser : oldGroupUserList) {
					needDelVcEmployeeIds += "'" + oldGroupUser.getVcEmployeeId() + "',";
				}
				//先删除原来此用户组绑定的所有角色里的用户（表Hhusersrole）
				groupDao.deleteUserRole(group.getId(), needDelVcEmployeeIds.substring(0,needDelVcEmployeeIds.length()-1));
				//再删除原来HhGroupUser表中的数据
				groupDao.deleteGroupUser(group.getId(), needDelVcEmployeeIds.substring(0,needDelVcEmployeeIds.length()-1));
			}
			if (!newGroupUserList.isEmpty()) {
				//过滤之后，newGroupUserList中为新加入的vcEmployeeIds，进行新增
				for (int i = 0; i < newGroupUserList.size(); i++) {
					HhGroupUser newGroupUser1 = new HhGroupUser();
					newGroupUser1.setGroupId(group.getId());
					newGroupUser1.setVcEmployeeId(newGroupUserList.get(i).getVcEmployeeId());
					commonService.save(newGroupUser1);
					if(!roles.isEmpty()) {
						for (HhGroupUserRole role : roles) {
							HhUsersrole newUsersRole = new HhUsersrole();
							newUsersRole.setRoleId(role.getRoleId());
							newUsersRole.setVcEmployeeId(newGroupUserList.get(i).getVcEmployeeId());
							commonService.save(newUsersRole);
						}
					}
				}
			}
		}else {
			//先删除原来此用户组绑定的所有角色里的用户（表Hhusersrole）
			groupDao.deleteAllUserRole(group.getId());
			//再删除原来HhGroupUser表中的数据
			groupDao.deleteAllGroupUser(group.getId());
		}
		
		/*groupDao.saveOrUpdateUserGroup(group);
		//先删除原来此用户组绑定的所有角色里的用户（表Hhusersrole）
		groupDao.deleteUserRole(group.getId());
		//再删除原来HhGroupUser表中的数据
		groupDao.deleteGroupUser(group.getId());
		
		//获取用户组绑定的所有角色
		List<HhGroupUserRole> roles = groupDao.getTheGroupUserRole(group.getId());
		//如果vcEmployeeIds不为""，再添加新的数据
		if (!"".equals(vcEmployeeIds) && vcEmployeeIds != null) {
			String [] vcEmployeeIdss = vcEmployeeIds.split(",");
			for (int i = 0; i < vcEmployeeIdss.length; i++) {
				//添加HhGroupUser中数据
				HhGroupUser newGroupUser = new HhGroupUser();
				newGroupUser.setGroupId(group.getId());
				newGroupUser.setVcEmployeeId(vcEmployeeIdss[i]);
				commonService.save(newGroupUser);
				//添加HhUsersRole中的数据
				if(!roles.isEmpty()) {
					for (HhGroupUserRole role : roles) {
						HhUsersrole newUsersRole = new HhUsersrole();
						newUsersRole.setRoleId(role.getRoleId());
						newUsersRole.setVcEmployeeId(vcEmployeeIdss[i]);
						commonService.save(newUsersRole);
					}
				}
			}
		}*/
		
		
		if(!roles.isEmpty()) {
			for (HhGroupUserRole role : roles) {
				HhRole hhRole = (HhRole)commonService.findById(HhRole.class, role.getRoleId());
				if(hhRole.getRoleStatus().toString().equals("1")) {
					//用于接口传输数据
					String newVcEmployeeIds = "";
					List<HhUsersrole> nowUserRoleList = roleDao.getUserRoleList(role.getRoleId());
					if (!nowUserRoleList.isEmpty()) {
						for (int i = 0; i < nowUserRoleList.size(); i++) {
							newVcEmployeeIds += nowUserRoleList.get(i).getVcEmployeeId() + ",";
						}
					}
					//调用接口传入对应模块中
					List<HhModelRegister> roleModelList = modelRegisterService.getModelsByRoleId(role.getRoleId());
					if (!roleModelList.isEmpty()) {
						for (int i = 0; i < roleModelList.size(); i++) {
							HhModelRegister roleModel = roleModelList.get(i);
							String modelNum = roleModel.getModelNum();
							if(modelNum.equals("bim_show")){
//								ShowWebserviceService service = new ShowWebserviceService();
//								IShowWebservice is = service.getPort(IShowWebservice.class);
//								Integer roleId = role.getRoleId();
//								is.showUpdaterUsersRole(newVcEmployeeIds, roleId);
							}
							if(modelNum.equals("bim_task")) {
//								TaskWebserviceService service = new TaskWebserviceService();
//								ITaskWebservice it = service.getPort(ITaskWebservice.class);
//								Integer roleId = role.getRoleId();
//								String msg = it.addUserRole(newVcEmployeeIds, roleId);
//								System.out.println(msg);
							}
							if(modelNum.equals("bim_work")) {
//								WorkWebServiceService service = new WorkWebServiceService();
//								IWorkWebService it = service.getPort(IWorkWebService.class);
								Integer roleId = role.getRoleId();
								String msg = workWebService.addUserRole(newVcEmployeeIds, roleId);
								System.out.println(msg);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public List<HhGroup> getStartGroups(Integer groupStatus) {
		// TODO Auto-generated method stub
		return groupDao.getStartGroups(groupStatus);
	}

	@Override
	public List<HhGroupUserRole> getTheGroupUserRole(Integer id) {
		// TODO Auto-generated method stub
		return groupDao.getTheGroupUserRole(id);
	}

	@Override
	public List<HhUsers> getGroupUsersByGroupId(Integer id) {
		// TODO Auto-generated method stub
		return groupDao.getSelectHhUsersList(id);
	}

	@Override
	public String stopGroupRole(Integer id) {
		// TODO Auto-generated method stub
		//用户组为禁用状态
		groupDao.stopGroup(id);
		//获取用户组绑定的所有角色
		List<HhGroupUserRole> roles = groupDao.getTheGroupUserRole(id);
		//先删除原来此用户组绑定的所有角色里的用户（表Hhusersrole）
		groupDao.deleteAllUserRole(id);
		//再删除用户组与所有角色的绑定关系（表HhGroupUserRole）
		groupDao.deleteAllGroupUsersRole(id);
		
		if(!roles.isEmpty()) {
			for (HhGroupUserRole role : roles) {
				HhRole hhRole = (HhRole)commonService.findById(HhRole.class, role.getRoleId());
				if(hhRole.getRoleStatus().toString().equals("1")) {
					//用于接口传输数据
					String newVcEmployeeIds = "";
					List<HhUsersrole> nowUserRoleList = roleDao.getUserRoleList(role.getRoleId());
					if (!nowUserRoleList.isEmpty()) {
						for (int i = 0; i < nowUserRoleList.size(); i++) {
							newVcEmployeeIds += nowUserRoleList.get(i).getVcEmployeeId() + ",";
						}
					}
					//调用接口传入对应模块中
					List<HhModelRegister> roleModelList = modelRegisterService.getModelsByRoleId(role.getRoleId());
					if (!roleModelList.isEmpty()) {
						for (int i = 0; i < roleModelList.size(); i++) {
							HhModelRegister roleModel = roleModelList.get(i);
							String modelNum = roleModel.getModelNum();
							if(modelNum.equals("bim_show")){
//								ShowWebserviceService service = new ShowWebserviceService();
//								IShowWebservice is = service.getPort(IShowWebservice.class);
//								Integer roleId = role.getRoleId();
//								is.showUpdaterUsersRole(newVcEmployeeIds, roleId);
							}
							if(modelNum.equals("bim_task")) {
//								TaskWebserviceService service = new TaskWebserviceService();
//								ITaskWebservice it = service.getPort(ITaskWebservice.class);
//								Integer roleId = role.getRoleId();
//								String msg = it.addUserRole(newVcEmployeeIds, roleId);
//								System.out.println(msg);
							}
							if(modelNum.equals("bim_work")) {
//								WorkWebServiceService service = new WorkWebServiceService();
//								IWorkWebService it = service.getPort(IWorkWebService.class);
								Integer roleId = role.getRoleId();
								String msg = workWebService.addUserRole(newVcEmployeeIds, roleId);
								System.out.println(msg);
							}
						}
					}
				}
			}
		}
		return "success";
	}

}
