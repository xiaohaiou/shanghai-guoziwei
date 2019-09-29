package com.softline.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOperationLog;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.OutCompareCompany;
import com.softline.entityTemp.PublicCompany;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IAuthorityService;
import com.softline.service.system.ISystemService;

@Service("systemService")
public class SystemService implements ISystemService {

	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Resource(name = "authorityService")
	private IAuthorityService authorityService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Override
	public void saveHhOperationLogList(HhOperationLog hhOperationLog){
		systemDao.saveHhOperationLogList(hhOperationLog);
	}
	
	public HhUsers getEmployeeById(String employeeId)
	{
		return systemDao.getEmployeeById(employeeId);
	}
	
	public HhOrganInfo getOrganInfoById(String id)
	{
		return systemDao.getOrganInfoById(id);
	}
	
	/**
	 * 获取数据权限的最高层级公司
	 * @param vcEmployeeID
	 * @return
	 */
	public List getTopCompanyData(String vcEmployeeID)
	{
		return systemDao.getTopCompanyData(vcEmployeeID);
	}
	
	/**
	 * 获取某个人(财务type=Base.financetype)最高的数据权限
	 * @param vcEmployeeID
	 * @param type
	 * @return
	 */
	public String getTopFinanceCompanyData(String vcEmployeeID,int type)
	{
		String topFinanceCompanyID="";//财务数据最高权限
		List topfinancecompany= systemDao.getTopFinanceCompanyData(vcEmployeeID,type);
		for (Object object : topfinancecompany) {
			Object[] obj=(Object[]) object;
			if(topFinanceCompanyID.equals(""))
			{	
				
				topFinanceCompanyID=obj[0].toString();
			}
			else
			{
				
				topFinanceCompanyID+=","+obj[0].toString();
			}	
		}
		return topFinanceCompanyID;
	}
	
	public String getCompanyDataByNNodelID(String organID)
	{
		return systemDao.getCompanyDataByNNodelID(organID);
	}
	
	public void SSoSetAuthirity(String vcEmployeeID,HttpServletRequest request)
	{
		HttpSession a= request.getSession();
		HhUsers entityHhUsers=(HhUsers) a.getAttribute("gzwsession_users");
		String vcEmployeeId=entityHhUsers.getVcEmployeeId();
		if(Common.notEmpty(vcEmployeeId)){
			String code=authorityService.getUserCodeNum(vcEmployeeId);
			String page=authorityService.getUserPageNum(vcEmployeeId);
			//获取数据权限
			String company=authorityService.getRoleCompany(vcEmployeeId);
			
			//获取财务数据权限的最高级
			String topFinanceCompanyID=getTopFinanceCompanyData(vcEmployeeID,Base.financetype);
			
			a.setAttribute("gzwsession_financecompany", topFinanceCompanyID);//财务权限顶级公司
			a.setAttribute("gzwsession_code", code);//按钮权限
			a.setAttribute("gzwsession_page", page);//页面权限
			String topcompanyName="";
			String topcompanyID="";//数据最高权限
			if(Common.notEmpty(company))
			{
				//获取数据权限的最高级
				List topcompany=getTopCompanyData(vcEmployeeID);
				
				for (Object object : topcompany) {
					Object[] obj=(Object[]) object;
					if(topcompanyName.equals(""))
					{	
						topcompanyName=obj[1].toString();
						topcompanyID=obj[0].toString();
					}
					else
					{
						topcompanyName+=","+obj[1].toString();
						topcompanyID+=","+obj[0].toString();
					}	
				}
				
				if(!Common.notEmpty(topcompanyID)){
					topcompanyID  = company;
				}
				a.setAttribute("gzwsession_company", topcompanyID);//hr权限顶级公司
			}
			else
			{
				topcompanyID=entityHhUsers.getNnodeId();
				a.setAttribute("gzwsession_company",topcompanyID );
				topcompanyName= getCompanyDataByNNodelID(topcompanyID);
			}
			if(topcompanyName.equals(Base.HRTopName)){
				topcompanyName=Base.HRTopSImpleName;
			}
			a.setAttribute("gzwsession_Topcompany", "（"+topcompanyName+"）");
			a.setAttribute("mutli", request.getParameter("mutli"));
			//数据权限
			//String dataauth= systemDao.getAllChildrenOrganal(topcompanyID);
			//a.setAttribute("gzwsession_Allcompany", dataauth);//hr权限看到的所有公司
			/*//财务数据权限
			String financedataauth= systemDao.getAllChildrenFinanceOrganal(topFinanceCompanyID,Base.financetype);
			a.setAttribute("gzwsession_AllFinancecompany", financedataauth);*/
		}
	}

	@Override
	public List<HhOrganInfo> getOrganInfos(String vcParentID) {
		return systemDao.getOrganInfos(vcParentID);
	}
	
	public List<HhOrganInfo> getCompanyOrganInfos()
	{
		return systemDao.getCompanyOrganInfos();
	}
	
	
	public List<HhOrganInfo> getTopChildrenCompanyOrganInfos(String authdata)
	{
		return systemDao.getTopChildrenCompanyOrganInfos(authdata);
	}
	
	
	/**
	 * 根据类型获取对应类型的除顶节点之外的所有节点
	 * @param type
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getAllChildrenTreeOrganInfos(int type)
	{
		return systemDao.getAllChildrenTreeOrganInfos(type);
	}
	
	/**
	 * 根据类型和ID获取某个节点
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelation getTreeOrganInfoNode(int type,String nnodelD)
	{
		return systemDao.getTreeOrganInfoNode(type,nnodelD);
	}
	
	/**
	 * 根据类型和ID获取某个节点所属业态
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelation getTreeOrganInfoNodeBusiness(int type,String nnodelD)
	{
		return systemDao.getTreeOrganInfoNodeBusiness(type,nnodelD);
	}
	
	/**
	 * 根据类型和ID获取该节点的子节点(下一个层级)
	 * @param type
	 * @param parentID 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HhOrganInfoTreeRelation> getChildrenTreeOrganInfos(int type,String parentID)
	{
		return systemDao.getChildrenTreeOrganInfos(type,parentID);
	}
	
	/**
	 * 根据类型和ID获取该节点的子节点(所有层级)
	 * @param type
	 * @param parentID 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HhOrganInfoTreeRelation> getAllChildrenTreeOrganInfos(int type,String parentID)
	{
		HhOrganInfoTreeRelation a=getTreeOrganInfoNode(type, parentID);
		return systemDao.getAllChildrenTreeOrganInfos(type,a.getVcOrganId());
	}
	
	/**
	 * 获取报表权限
	 * @param nnodeID
	 * @param type
	 * @return
	 */
	public String getOtherOrganAuthData(String nnodeID,int type)
	{
		return systemDao.getOtherOrganAuthData(nnodeID,type);
	}
	
	/**
	 * 根据节点ID和类型获取父节点ID
	 * @param nnodelID
	 * @param type
	 * @return
	 */
	public String getParentBynNodeID(String nnodelID,Integer type)
	{
		return systemDao.getParentBynNodeID(nnodelID,type);
	}
	
	
	
	/**
	 * 获取一个子层级的数据权限
	 * @param nnodelID
	 * @param type
	 * @return
	 */
	public String getNextLevelChildAuthDataStr(String nnodelID,Integer type)
	{
		String a="";
		HhOrganInfoTreeRelation node=	getTreeOrganInfoNode(type,nnodelID);
		if(node!=null)
		{	
			List<HhOrganInfoTreeRelation> childnode=getChildrenTreeOrganInfos(type,node.getVcOrganId());
			
			for (HhOrganInfoTreeRelation item : childnode) {
				if(a.equals(""))
					a=item.getId().getNnodeId();
				else
					a=a+","+item.getId().getNnodeId();
			}
		}
		return a;
	}
	
	
	/**
	 * 获取业态公司
	 * @param authdata
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getBusinessCompany(String authdata,Integer type)
	{
		return systemDao.getBusinessCompany(authdata,type);
	}
	
	/**
	 * 获取业态公司2
	 * @param authdata
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getBusinessCompany(String authdata,Integer type,String topNNodeID){
		
		//判断 topNNodeID 是不是前三级公司
		List<HhOrganInfoTreeRelation> list = this.getBusinessCompany(authdata,type);
		if(list.size()==0){
			return systemDao.getBusinessCompanyForJudje(topNNodeID,type);
		}		
		return list;
	}
	
	
	/**
	 * 获取(财务type=Base.financetype)表中该公司下所有子公司及其自身的主键拼接
	 * @param topcompanyID
	 * @param type
	 * @return
	 */
	public String getAllChildrenFinanceOrganal(String topcompanyID,int type)
	{
		return systemDao.getAllChildrenFinanceOrganal(topcompanyID,type);
	}
	
	
	@Override
	public List<HhOrganInfoTreeRelation> getPublicCompany(String str) {
		// TODO Auto-generated method stub
		return systemDao.getPublicCompany(str);
	}

	@Override
	public HhOrganInfoTreeRelation getTopTreeOrganInfoNode(Integer financetype) {
		// TODO Auto-generated method stub
		return systemDao.getTopTreeOrganInfoNode(financetype);
	}

	/**
	 * 获取数据权限
	 * @param str
	 * @return
	 */
	@Override
	public String getDataauth(String str) {
		// TODO Auto-generated method stub
		//获取进行递归过滤之后的数据权限（可能影响性能）
		/*String dataauth = selectUserService.getDataAuth(str);
        dataauth = !"".equals(dataauth) ? dataauth.substring(0,dataauth.length()-1) : null;*/
		//获取没过滤的数据权限
		String dataauth = systemDao.getDataauth(str);
        return dataauth;
	}

	@Override
	public Object getDescription(Integer id) {
		// TODO Auto-generated method stub
		return systemDao.getDescription(id);
	}

	@Override
	public String getTopNnodeID(Integer financetype) {
		// TODO Auto-generated method stub
		return systemDao.getTopNnodeID(financetype);
	}

	@Override
	public String getSessionCompany(String vcEmployeeId) {
		//获取数据权限
		String company=authorityService.getRoleCompany(vcEmployeeId);
		HhUsers entityHhUsers = systemDao.getEmployeeById(vcEmployeeId);
		String topcompanyName="";
		String topcompanyID="";//数据最高权限
		if(Common.notEmpty(company))
		{
			//获取数据权限的最高级
			List topcompany=getTopCompanyData(vcEmployeeId);
			
			for (Object object : topcompany) {
				Object[] obj=(Object[]) object;
				if(topcompanyName.equals(""))
				{	
					topcompanyName=obj[1].toString();
					topcompanyID=obj[0].toString();
				}
				else
				{
					topcompanyName+=","+obj[1].toString();
					topcompanyID+=","+obj[0].toString();
				}	
			}
		}
		else
		{
			topcompanyID=entityHhUsers.getNnodeId();
			topcompanyName= getCompanyDataByNNodelID(topcompanyID);
			
		}
		if(topcompanyName.equals(Base.HRTopName))
			topcompanyName=Base.HRTopSImpleName;
		return topcompanyID;
	}

	
	/**
	 * 获取除虚拟公司外的所有公司
	 * @param vcEmployeeId
	 * @return
	 */
	@Override
	public String getPublicNoVirtualCompany(String org) {
		// TODO Auto-generated method stub
		return systemDao.getPublicNoVirtualCompany(org);
	}
	
	
	/**
	 * 获取最高级企业下面所有公司Nnodeid拼接主键
	 * @param str
	 * @return
	 */
	@Override
	public String getDataauthNnodeIDs(String nnodeID) {
		if(!Common.notEmpty(nnodeID))
			return "";
		String[] nnodeIDArr = nnodeID.split(",");
		StringBuilder searchSB = new StringBuilder();
		for(String strTemp:nnodeIDArr){   
			HhOrganInfo hhOrganInfo = systemDao.getOrganInfoById(strTemp);
			if(null!=hhOrganInfo){
				searchSB.append(hhOrganInfo.getVcOrganId()).append(",");
			}
		}
		if(searchSB.length()>0){
			searchSB.setLength(searchSB.length()-1);
		}
		List<HhOrganInfo>  listBean = systemDao.getAuthorCompanyList(searchSB.toString());
		StringBuilder backSB = new StringBuilder();
		for(HhOrganInfo tempBean:listBean){
			backSB.append(tempBean.getNnodeId()).append(",");
		}
		if(backSB.length()>0)
			backSB.setLength(backSB.length()-1);
        return backSB.toString();
	}

	@Override
	public String getHrOrginfoNameBynnodeID(String nnodeID) {
		// TODO Auto-generated method stub
		return systemDao.getHrOrginfoNameBynnodeID(nnodeID);
	}

	@Override
	public String getHrOrginfoNameByOrgID(String vcOrganID) {
		// TODO Auto-generated method stub
		return systemDao.getHrOrginfoNameByOrgID(vcOrganID);
	}

	@Override
	public HhUsers getEmployeeByName(String name) {
		HhUsers employee = systemDao.getEmployeeByName(name);
		return employee;
	}
	
	
	/**
	 * 获取该节点下能看到的上市公司
	 * @param organID
	 * @param time
	 * @param type
	 * @return
	 */
	public String getPublicCompany(String organID,String time,int type)
	{
		String publiccompany="";
		List<PublicCompany> data= systemDao.getPublicCompanyList(organID,time,type);
		for (PublicCompany object : data) {
			if(publiccompany.equals(""))
				publiccompany=object.getPublicorg();
			else
				publiccompany+=","+object.getPublicorg();;
		}
			return publiccompany;
	}
	
	/**
	 * 获取所有外部对标企业
	 * @param organID
	 * @param time
	 * @param type
	 * @return
	 */
	public List<OutCompareCompany> getAllOutCompareCompanyList()
	{
		
		return systemDao.getAllOutCompareCompanyList();
	}
	
	
	/**
	 * 获取外部对标对比企业
	 * @param organID
	 * @param time
	 * @param type
	 * @return
	 */
	public List<OutCompareCompany> getOutCompareCompanyList(String organID,String time,int type)
	{
		
		 String orgstr= getPublicCompany( organID, time, type);
		 
		 List<OutCompareCompany> a= systemDao.getOutCompareCompanyList(orgstr);
		 return a;
		 
	}
	
}
