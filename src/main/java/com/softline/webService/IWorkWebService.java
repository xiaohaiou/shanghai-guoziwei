package com.softline.webService;

import java.util.List;

import javax.jws.WebService;

import com.softline.entity.HhPage;
import com.softline.entity.HhPagecode;
import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;
import com.softline.entity.PortalWork;
import com.softline.entity.RoleCompany;
import com.softline.entity.SysRole;
import com.softline.util.MsgPage;

@WebService
public interface  IWorkWebService {
	

	public String updateRole(String pageIds,String codeIds,SysRole role);

	public String addUserRole(String vcEmployeeIds, Integer roleId);

	public String updateHhPage(HhPage page);

	public String updateHhPagecode(HhPagecode pageCode);

	public String UpdaterRoleCompany(String companyId,Integer roleId);
	
	//获取全体财务树
	public String getAllFinanceTree();
	
	
	/**
	 * 根据权限获取财务树 auth为权限的顶节点
	 * @param auth
	 * @return
	 */
	public String getAllFinanceTreeByAuth(String auth);
	
	/**
	 * 更新财务的数据权限
	 * @param companyId
	 * @param roleId
	 * @return
	 */
	public String updateFinanceRoleCompany(String companyId,Integer roleId);
	
	/**
	 * 获取最新动态信息
	 * @param vcEmployeeId 员工编号
	 * @param queryStartTime 查询开始时间
	 * @param queryEndTime 查询结束时间
	 * @param pageNums 当前页
	 * @return
	 */
	public MsgPage getPortalNews(String vcEmployeeId,PortalNews entity,Integer pageNums);
	
	/**
	 * 获取工作提醒
	 * @param vcEmployeeId 员工编号
	 * @param pageNums 当前页
	 * @return
	 */
	public MsgPage getPortalMsgs(String vcEmployeeId,Integer pageNums,PortalMsg entity);
	
}
