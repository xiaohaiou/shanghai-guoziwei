package com.softline.service.system.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.dao.select.ISelectUserDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhPage;
import com.softline.entity.HhPagecode;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;
import com.softline.entity.PortalWork;
import com.softline.entity.RoleCompany;
import com.softline.entity.RoleFinanceCompany;
import com.softline.entity.SysRole;
import com.softline.service.portal.INewsService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IAuthorityService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.IWorkWebService;
import com.softline.util.MsgPage;
/*@WebService(endpointInterface="com.softline.webService.IPortalService",serviceName="portalService")
@WebService*/
@Service("workWebService")
public class WorkWebService implements IWorkWebService {
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	@Resource(name = "authorityService")
	private IAuthorityService authorityService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "potalMsgService")
	private IPortalMsgService portalMsgService;
	@Resource(name = "newsService")
	private INewsService newsService;
	
	@Override
	public String updateRole(String pageIds,String codeIds,SysRole role) {
		return authorityService.updateRole(codeIds,pageIds,role);
	}


	@Override
	public String addUserRole(String vcEmployeeIds, Integer roleId) {
		try {
			authorityService.delAddUsersrole(vcEmployeeIds, roleId);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	

	@Override
	public String updateHhPage(HhPage page) {
		try {
			authorityService.updateHhPage(page);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	@Override
	public String updateHhPagecode(HhPagecode pageCode) {
		try {
			authorityService.updateHhPagecode(pageCode);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	@Override
	public String UpdaterRoleCompany(String companyId,Integer roleId) {
		String message = "未选择数据";
		try{
			//删除RoleCompany
			authorityService.deleteRoleCompany(roleId);
			//同步数据
			if(Common.notEmpty(companyId)){
				String[] companyIds = companyId.substring(0,companyId.length()-1).split(",");
				for(int i = 0;i<companyIds.length;i++){
					RoleCompany roleCompany = new RoleCompany();
					roleCompany.setRoleId(roleId);
					roleCompany.setCompanyId(Integer.parseInt(companyIds[i]));
					authorityService.showUpdaterRoleCompany(roleCompany);
				}
			}
			message = "修改成功！";
		}catch(Exception e){
			message = "修改失败！";
		}
		return message;
	}
	
	public String getAllFinanceTree()
	{
		
		HhOrganInfoTreeRelation topTree = systemService.getTopTreeOrganInfoNode(Base.financetype);	
		List<CompanyTree> a=selectUserService.getOtherOrganal(topTree.getId().getNnodeId(),Base.financetype);
		if(a!=null && a.size()>0)
		{
			return JSON.toJSONString(a);
		}
		else
			return "";
	}
	
	//根据权限获取财务树
	public String getAllFinanceTreeByAuth(String auth)
	{
		List<CompanyTree> a=selectUserService.getOtherOrganal(auth,Base.financetype);
		if(a!=null && a.size()>0)
		{
			return JSON.toJSONString(a);
		}
		else
			return "";
	}
	
	/**
	 * 更新财务的数据权限
	 * @param companyId
	 * @param roleId
	 * @return
	 */
	public String updateFinanceRoleCompany(String companyId,Integer roleId)
	{
		String message = "未选择数据";
		try{
			//删除RoleCompany
			authorityService.deleteFinanceRoleCompany(roleId);
			//同步数据
			if(Common.notEmpty(companyId)){
				String[] companyIds = companyId.substring(0,companyId.length()-1).split(",");
				for(int i = 0;i<companyIds.length;i++){
					RoleFinanceCompany roleCompany = new RoleFinanceCompany();
					roleCompany.setRoleId(roleId);
					roleCompany.setCompanyId(companyIds[i]);
					authorityService.showUpdaterRoleFinanceCompany(roleCompany);
				}
			}
			message = "修改成功！";
		}catch(Exception e){
			message = "修改失败！";
		}
		return message;
	}


	@Override
	public MsgPage getPortalNews(String vcEmployeeId,
			PortalNews entity, Integer pageNums) {
			if (pageNums == null) {
				pageNums = 1;
	        }
			entity.setIsIssue(1);
			MsgPage msgPage = newsService.getPortalNewsList(entity, pageNums, 10);
			return msgPage;
	}


	@Override
	public MsgPage getPortalMsgs(String vcEmployeeId, Integer pageNums,PortalMsg entity) {
		
        if (pageNums == null) {
        	pageNums = 1;
        }
        entity.setDelFlag(0);//不显示删除的
        HhUsers hhUsers = null;
		String session_code = authorityService.getUserCodeNum(vcEmployeeId);
		String session_company = systemService.getSessionCompany(vcEmployeeId);
		//财务数据权限
		String str=systemService.getTopFinanceCompanyData(vcEmployeeId,Base.financetype);;
        String financedataauth=systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
        if(Common.notEmpty(session_company) && Common.notEmpty(financedataauth))
        	session_company=session_company+","+financedataauth;
        else if(Common.notEmpty(financedataauth))
        	session_company=financedataauth;
        else if(!Common.notEmpty(session_company) && !Common.notEmpty(financedataauth))
        	session_company = "";
        MsgPage msgPage = portalMsgService.getPortalMsgsList3(session_code, session_company, hhUsers, entity, pageNums, 10);
		return msgPage;
	}
	

}
