package com.softline.controller.settingCenter.portal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softline.common.Common;
import com.softline.entity.HhOperationLog;
import com.softline.entity.HhUsers;
import com.softline.entity.SysOrganInfo;
import com.softline.entity.settingCenter.HhSysRegister;
import com.softline.service.portal.IHhUsersService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.impl.CommonService;
import com.softline.util.MD5;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/portal/user")
public class PortalUserController {
	
	@Resource(name="hhUsersService")
	private IHhUsersService hhUsersService;
	
	@Resource(name="commonService")
	private ICommonService commonService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("_query")
	public String query(HhUsers dd, String qVcAccount,String qVcName, Map<String,Object> map, HttpServletRequest request) {
		if (dd.getVcAccount() != null) {
			map.put("save", "success");
		}
		String curPageNum = request.getParameter("pageNum");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        HhUsers condi = new HhUsers();
        condi.setVcAccount(qVcAccount);
        condi.setVcName(qVcName);
        MsgPage msgPage = hhUsersService.getHhUsersList(condi, pageNum, 10);
        List<HhUsers> msgList = msgPage.getList();
		map.put("msgPage", msgPage);		
     	map.put("userList", msgList);
     	map.put("qVcAccount", qVcAccount);	
     	map.put("qVcName", qVcName);	
     	map.put("searchUrl", request.getContextPath()+"/portal/user/_query");
		return "/settingCenter/portal/userList";
	}
	
	@RequestMapping("_edit")
	public String addOrModifyUser(HhUsers users,String qVcAccount,String qVcName,String pageNum,String op,Map<String,Object> map, HttpServletRequest request) {
		map.put("qVcAccount", qVcAccount);
		map.put("qVcName", qVcName);
		map.put("pageNum", pageNum);
		map.put("op", op);
		if (users.getId() != null) {
			HhUsers thisUsers =  (HhUsers) commonService.findById(HhUsers.class, users.getId());
			map.put("thisUsers", thisUsers);
		}
		return "/settingCenter/portal/userEdit";
	}
	
	@RequestMapping("_saveOrUpdate")
	public String saveOrUpdate(HhUsers users,HttpServletRequest request) {
		//获取当前登录session
		HttpSession session = request.getSession();
		//获取当前登录对象
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		//设置时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		HhOperationLog hhOperationLog = new HhOperationLog();
		String date = Common.getDate(new Date(),"yyyy-MM-dd HH:mm:ss");
		hhOperationLog.setName(user.getVcAccount());
        hhOperationLog.setOperationTime(date);
        hhOperationLog.setModel("用户管理");
		if (users.getId() != null && !(users.getId().equals(""))) {//id存在时，为编辑状态
			//添加modify_time字段
			users.setLastModifyDate(sdf.format(new Date()));
			//modifier字段数据(存入user的vcEmployeeId)
			users.setLastModifyPersonId(user.getVcEmployeeId());
			users.setLastModifyPersonName(user.getVcName());
			hhOperationLog.setDescription("用户管理:更新用户-"+users.getVcAccount());
		} else {//反之新建
			//添加create_time字段
			users.setCreateDate(sdf.format(now));
			//creator字段(存入user的vcEmployeeId)
			users.setCreatePersonId(user.getVcEmployeeId());
			users.setCreatePersonName(user.getVcName());
			hhOperationLog.setDescription("用户管理:新增用户-"+users.getVcAccount());
		}
		String password = users.getPassword();
		String passwordMd5 = MD5.getMD5(password);
		users.setPassword(passwordMd5);
		users.setCflag("1");
		commonService.saveOrUpdate(users);
		systemService.saveHhOperationLogList(hhOperationLog);
		return "forward:/portal/user/_query";
		
	}
	
	@ResponseBody
	@RequestMapping("_del")
	public String delHhusers(Integer id,HttpServletRequest request) {
		HhUsers thisUsers =  (HhUsers) commonService.findById(HhUsers.class, id);
		//获取当前登录session
		HttpSession session = request.getSession();
		//获取当前登录对象
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		//设置时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HhOperationLog hhOperationLog = new HhOperationLog();
		String date = Common.getDate(new Date(),"yyyy-MM-dd HH:mm:ss");
		hhOperationLog.setName(user.getVcAccount());
        hhOperationLog.setOperationTime(date);
        hhOperationLog.setModel("用户管理");
        hhOperationLog.setDescription("用户管理:删除用户-"+thisUsers.getVcAccount());
		//添加modify_time字段
		thisUsers.setLastModifyDate(sdf.format(new Date()));
		//modifier字段数据(存入user的vcEmployeeId)
		thisUsers.setLastModifyPersonId(user.getVcEmployeeId());
		thisUsers.setLastModifyPersonName(user.getVcName());
		thisUsers.setCflag("0");
		commonService.saveOrUpdate(thisUsers);
		systemService.saveHhOperationLogList(hhOperationLog);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("_validateAccountAndEmployeeIdUnique")
	public String  validateAccountAndEmployeeIdUnique(Integer id ,String vcAccount,String vcEmployeeId) {
		if(!hhUsersService.vaildateAccountUnique(id, vcAccount)) {
			return "account_not_unique";
		}
		if(!hhUsersService.validateEmployeeIdUnique(id, vcEmployeeId)) {
			return "employeeId_not_unique";
		}
		return "success"; 
	}
	
}
