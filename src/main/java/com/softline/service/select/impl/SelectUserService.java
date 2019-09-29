package com.softline.service.select.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSON;
import com.softline.client.bima.IWsSystemService;
import com.softline.client.bima.WsSystemServiceService;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.common.CompanyTreeState;
import com.softline.dao.select.ISelectUserDao;
import com.softline.entity.HhBimaCompany;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.HhUsersCopy;
import com.softline.entityTemp.BimcCompany;
import com.softline.service.select.ISelectUserService;

@Service("selectUserService")
public class SelectUserService implements ISelectUserService {
	@Autowired
	@Qualifier("selectUserDao")
	private ISelectUserDao selectUserDao;
	
	
	
	
	//begin 生成hr树
	public List<CompanyTree> getHrOrganal(String nnodeId)
	{
		List<CompanyTree> leveltree= new ArrayList<CompanyTree>();
		if(nnodeId.contains(","))
		{
			
			String[] each=nnodeId.split(",");
			for (int i = 0; i < each.length; i++) {
				String string = each[i];
				if(Common.notEmpty(string))
				{
					CompanyTree	itemleveltree=getleveltree(string,null);
					if(itemleveltree!=null)
						leveltree.add(itemleveltree);
				}
			}

		}
		else
		{
			leveltree.add(getleveltree(nnodeId,null));
			if(leveltree==null)
				return new ArrayList<CompanyTree>();
		}
		return leveltree ;
	}
	
	private CompanyTree getleveltree(String nnodeId,Integer type)
	{
		CompanyTree leveltree =getTree(nnodeId, type);
		return leveltree;
	}
	

	private CompanyTree getTree(String nnodeId,Integer type) {
		int level = 0;
		HhOrganInfo sdktop = selectUserDao.getTop(nnodeId,type);
		if (sdktop == null)
			return null;
		
		CompanyTree top = new CompanyTree(sdktop.getVcFullName(),sdktop.getNnodeId(),"",sdktop.getVcOrganId(),new ArrayList<CompanyTree>());
		List<HhOrganInfo> AllNode = selectUserDao.getNode(sdktop.getVcOrganId(),type);
		
		getCompanyTreeNode(top, AllNode, level);
		return top;
	}
	
	
	/**
	 * 获取树
	 * @param auth  权限
	 * @param type 树种类
	 */
	public List<CompanyTree> getOtherOrganal(String auth,Integer type)
	{
		List<CompanyTree> leveltree= new ArrayList<CompanyTree>();
		
		String a[]=auth.split(",");
		for (int i = 0; i < a.length; i++) {
			CompanyTree itemleveltree = getleveltree(a[i],type);
			leveltree.add(itemleveltree);
		}
		
		return leveltree;
	}
	
	/**
	 * 递归寻找子节点
	 * @param parent 父
	 * @param AllNode 寻找范围
	 * @param level 层级
	 */
	private void getCompanyTreeNode(CompanyTree parent, List<HhOrganInfo> AllNode,int level) {
		
			for (int i = 0; i < AllNode.size(); i++) {
				HhOrganInfo item = AllNode.get(i);
				if (parent.getVcOrganID()!= null && parent.getVcOrganID().equals(item.getVcParentId())) {

					CompanyTree child = new CompanyTree(item.getVcFullName(),item.getNnodeId(),parent.getId(),item.getVcOrganId(),new ArrayList<CompanyTree>());
					parent.children.add(child);
					AllNode.remove(i);
					i--;
					getCompanyTreeNode(child, AllNode, level);
				}
				if (i + 1 < AllNode.size()) {
					if (parent.getId().equals(AllNode.get(i + 1))) {
						break;
					}
				}
			}
		
	}
	//end 生成树
	
	
	public void saveSynBimaCompany()
	{
		try{
			
			WsSystemServiceService wsService = new WsSystemServiceService();
			IWsSystemService iw = wsService.getPort(IWsSystemService.class);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str=iw.getGSCompanyData();
			List<BimcCompany> data=JSON.parseArray(str, BimcCompany.class);
			if(data!=null && data.size()>0)
			{
				List<HhBimaCompany> bima=new ArrayList<HhBimaCompany>();
				
				for (BimcCompany item : data) {
					
					HhBimaCompany child=new HhBimaCompany();
					child.setBimaId(item.getBimaID());
					child.setName(item.getName());
					child.setRegisteredCapital(item.getRegisteredCapital());
					child.setUnionid(item.getUnionid());
					child.setRegistryState(item.getRegistryState());
					child.setLegalPersonName(item.getLegalPersonName());
					child.setSetTime(item.getSetTime());
					child.setIsExpire(item.getIsExpire());
					child.setExpire(item.getExpire());
					child.setRegistrationNumber(item.getRegistrationNumber());
					child.setAddress(item.getAddress());
					child.setArea(item.getArea());
					child.setType1(item.getType1());
					child.setType2(item.getType2());
					child.setType3(item.getType3());
					child.setBusinessFormat(item.getBusinessFormat());
					child.setEnglishName(item.getEnglishName());
					child.setOperational(item.getOperational());
					child.setOperationalPurpose(item.getOperationalPurpose());
					child.setLastModifyDate(df.format(new Date()));
					bima.add(child);
					
				}
				selectUserDao.updateBimaCompany(bima);
				
			}
			
		}
		catch(Exception e)
		{
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			
		}
	}
	
	

	@Override
	public List<HhUsers> getAllPerson02(String id, String name) {
		if(id.equals(Base.HRTopLeader)){
			List<HhUsersCopy> usersCopyList = selectUserDao.getAllPerson01(id, name);
			List<HhUsers> userList = new ArrayList<HhUsers>();
			if((!usersCopyList.isEmpty())){
				for (int i = 0; i < usersCopyList.size(); i++) {
					HhUsersCopy userCopy = usersCopyList.get(i);
					HhUsers user = new HhUsers();
					user.setId(userCopy.getId());
					user.setVcEmployeeId(userCopy.getVcEmployeeId());
					user.setNnodeId(userCopy.getNnodeId());
					user.setVcName(userCopy.getVcName());
					user.setVcAccount(userCopy.getVcAccount());
					user.setCsex(userCopy.getCsex());
					user.setCflag(userCopy.getCflag());
					user.setDoperatorDate(userCopy.getDoperatorDate());
					user.setVcOrganId(userCopy.getVcOrganId());
					user.setVcFullName(userCopy.getVcFullName());
					user.setNadminLevelId(userCopy.getNadminLevelId());
					user.setVcAdminLevelName(userCopy.getVcAdminLevelName());
					user.setCifManageLeader(userCopy.getCifManageLeader());
					user.setTbLNNodeId(userCopy.getTbLNNodeId());
					user.setVcName1(userCopy.getVcName1());
					user.setVcSecondName(userCopy.getVcSecondName());
					user.setCreatePersonName(userCopy.getCreatePersonName());
					user.setCreatePersonId(userCopy.getCreatePersonId());
					user.setCreateDate(userCopy.getCreateDate());
					user.setLastModifyPersonId(userCopy.getLastModifyPersonId());
					user.setLastModifyPersonName(userCopy.getLastModifyPersonName());
					user.setLastModifyDate(userCopy.getLastModifyDate());
					user.setIsOut(userCopy.getIsOut());
					user.setPersontype(userCopy.getPersontype()) ;
					user.setPersontypeName(userCopy.getPersontypeName());
					userList.add(user);
				}
			}
			return userList;
		}else{
			return selectUserDao.getAllPerson02(id, name);
		}
	}
	
	/**
	 * 获取进行递归过滤之后的数据权限
	 */
	@Override
	public String getDataAuth(String nnodeId) {
		// TODO Auto-generated method stub
		String dataauth = "";
		if(nnodeId.contains(","))
		{
			
			String[] each=nnodeId.split(",");
			for (int i = 0; i < each.length; i++) {
				String string = each[i];
				if(Common.notEmpty(string))
				{
					String	itemleveltree = getStringTree(string,null);
					if(!"".equals(itemleveltree))
						dataauth += itemleveltree;
				}
			}

		}
		else
		{
			dataauth = getStringTree(nnodeId,null);
		}
		return dataauth ;
	}

	private String getStringTree(String nnodeId,Integer type) {
		StringBuffer dataauth = new StringBuffer();
		int level = 0;
		HhOrganInfo sdktop = selectUserDao.getTop(nnodeId,type);
		if (sdktop == null)
			return "";
		dataauth.append(sdktop.getNnodeId() + ",");
		CompanyTree top = new CompanyTree(sdktop.getVcFullName(),sdktop.getNnodeId(),"",sdktop.getVcOrganId(),new ArrayList<CompanyTree>());
		List<HhOrganInfo> AllNode = selectUserDao.getNode(sdktop.getVcOrganId(),type);
		
		getStringCompanyTreeNode(top, AllNode, level, dataauth);
		String a = dataauth.toString();
		return dataauth.toString();
	}
	
	private void getStringCompanyTreeNode(CompanyTree parent, List<HhOrganInfo> AllNode,int level, StringBuffer dataauth) {
		
		for (int i = 0; i < AllNode.size(); i++) {
			HhOrganInfo item = AllNode.get(i);
			if (parent.getVcOrganID().equals(item.getVcParentId())) {
				dataauth.append(item.getNnodeId() + ",");
				CompanyTree child = new CompanyTree(item.getVcFullName(),item.getNnodeId(),parent.getId(),item.getVcOrganId(),new ArrayList<CompanyTree>());
				parent.children.add(child);
				AllNode.remove(i);
				i--;
				getStringCompanyTreeNode(child, AllNode, level, dataauth);
			}
			if (i + 1 < AllNode.size()) {
				if (parent.getId().equals(AllNode.get(i + 1))) {
					break;
				}
			}
		}
	}
	
	@Override
	public CompanyTree getHRTree() {
		int level = 0;
		HhOrganInfo sdktop = selectUserDao.getTop(Base.HRTop,null);
		if (sdktop == null)
			return null;
		
		CompanyTree top = new CompanyTree(sdktop.getVcFullName(),
				sdktop.getVcOrganId(),null,sdktop.getVcOrganId(),new ArrayList<CompanyTree>());
		List<HhOrganInfo> AllNode = selectUserDao.getNode(Base.HRTopPrefix);
		
		getCompanyTreeNode(top, AllNode, level);
		return top;
	}
	
	@Override
	public List<HhUsers> getUsersByName(String name) {
		return selectUserDao.getUsersByName(name);
	}
	
	/**
	 * 设置财务树不可被选择项
	 * @param topNnodeID
	 * @param selectedTopNnodeID
	 * @return
	 */
	@Override
	public List<String> getAllBackSelectedTreeOptions(String topNnodeID,String selectedTopNnodeID,Integer type){
		
		if(null==topNnodeID ||
				"".equals(topNnodeID) ||
				null == selectedTopNnodeID ||
				"".equals(selectedTopNnodeID))
			return new ArrayList<String>();
		
		List<CompanyTree> listall = this.getOtherOrganal(topNnodeID,Base.financetype);		
		List<CompanyTree> listselected = this.getOtherOrganal(selectedTopNnodeID,Base.financetype);
		
		if(null==listselected ||
				listselected.size()==0 ||
				null == listall ||
				listall.size() ==0)
			return new ArrayList<String>();
		
		
		ArrayList<String> listSelectedID = new ArrayList<String>();
		ArrayList<String> listAllID = new ArrayList<String>();
		
		for(CompanyTree companyTree:listselected){
			this.getAllCompanyTreeids(companyTree,listSelectedID);	
		}
		
		for(CompanyTree companyTree:listall){
			this.getAllCompanyTreeids(companyTree,listAllID);	
		}
		
		listAllID.removeAll(listSelectedID);
		
		return listAllID;
	}
	
	
	public void getAllCompanyTreeids(CompanyTree companyTree,List<String> list){
		
		if(null == companyTree)
			return;
		
		list.add(companyTree.getId());
		
		for(CompanyTree companyTreeTempBean:companyTree.getChildren()){
			this.getAllCompanyTreeids(companyTreeTempBean,list);
		}		
	}

	/**
	 * 获取树
	 * @param auth  权限
	 * @param type 树种类
	 * @param time 树时间
	 */
	public List<CompanyTree> getOtherHistoryOrganal(String auth, Integer type,String time) {
        List<CompanyTree> leveltree= new ArrayList<CompanyTree>();
		
		String a[]=auth.split(",");
		for (int i = 0; i < a.length; i++) {
			CompanyTree itemleveltree = getlevelHistorytree(a[i],type,time);
			leveltree.add(itemleveltree);
		}
		
		return leveltree;
	}

	private CompanyTree getlevelHistorytree(String nnodeId,Integer type,String time) {
		CompanyTree leveltree =getHistoryTree(nnodeId, type,time);
		return leveltree;
	}

	private CompanyTree getHistoryTree(String nnodeId, Integer type, String time) {
		int level = 0;
		HhOrganInfo sdktop = selectUserDao.getHistoryTop(nnodeId,type,time);
		if (sdktop == null)
			return null;
		
		CompanyTree top = new CompanyTree(sdktop.getVcFullName(),sdktop.getNnodeId(),"",sdktop.getVcOrganId(),new ArrayList<CompanyTree>());
		List<HhOrganInfo> AllNode = selectUserDao.getHistoryNode(sdktop.getVcOrganId(),type,time);
		
		getCompanyTreeNode(top, AllNode, level);
		return top;
	}

	@Override
	public CompanyTree getDepTree() {
		int level = 0;
		HhOrganInfo sdktop = selectUserDao.getTop(Base.HRTop,null);
		if (sdktop == null)
			return null;
		
		CompanyTree top = new CompanyTree(sdktop.getVcFullName(),
				sdktop.getVcOrganId(),Base.icon[level],new ArrayList<CompanyTree>(),new CompanyTreeState(true,false),null);
		List<HhOrganInfo> AllNode = selectUserDao.getDepNode(Base.HRTopPrefix);
		
		getCompanyTreeNode1(top, AllNode, level);
		return top;
	}
	
	private void getCompanyTreeNode1(CompanyTree parent, List<HhOrganInfo> AllNode,int level) {
		if (level < Base.icon.length-1) {
			level++;
		 }
			for (int i = 0; i < AllNode.size(); i++) {
				HhOrganInfo item = AllNode.get(i);
				if (parent.getId().equals(item.getVcParentId())) {

					CompanyTree child = new CompanyTree(item.getVcFullName(),
							item.getVcOrganId(), Base.icon[level],new ArrayList<CompanyTree>(),new CompanyTreeState(false,false),null);
					parent.children.add(child);
					AllNode.remove(i);
					i--;
					getCompanyTreeNode1(child, AllNode, level);
				}
				if (i + 1 < AllNode.size()) {
					if (parent.getId().equals(AllNode.get(i + 1))) {
						break;
					}
				}
			}
		if (level < Base.icon.length-1) {
			level--;
		}
	}

	@Override
	public List<HhUsers> getUsersByDepId(String id) {
		if(id.equals(Base.HRTopLeader)){
			List<HhUsersCopy> usersCopyList = selectUserDao.getAllPerson01(id, null);
			List<HhUsers> userList = new ArrayList<HhUsers>();
			if((!usersCopyList.isEmpty())){
				for (int i = 0; i < usersCopyList.size(); i++) {
					HhUsersCopy userCopy = usersCopyList.get(i);
					HhUsers user = new HhUsers();
					user.setId(userCopy.getId());
					user.setVcEmployeeId(userCopy.getVcEmployeeId());
					user.setNnodeId(userCopy.getNnodeId());
					user.setVcName(userCopy.getVcName());
					user.setVcAccount(userCopy.getVcAccount());
					user.setCsex(userCopy.getCsex());
					user.setCflag(userCopy.getCflag());
					user.setDoperatorDate(userCopy.getDoperatorDate());
					user.setVcOrganId(userCopy.getVcOrganId());
					user.setVcFullName(userCopy.getVcFullName());
					user.setNadminLevelId(userCopy.getNadminLevelId());
					user.setVcAdminLevelName(userCopy.getVcAdminLevelName());
					user.setCifManageLeader(userCopy.getCifManageLeader());
					user.setTbLNNodeId(userCopy.getTbLNNodeId());
					user.setVcName1(userCopy.getVcName1());
					user.setVcSecondName(userCopy.getVcSecondName());
					user.setCreatePersonName(userCopy.getCreatePersonName());
					user.setCreatePersonId(userCopy.getCreatePersonId());
					user.setCreateDate(userCopy.getCreateDate());
					user.setLastModifyPersonId(userCopy.getLastModifyPersonId());
					user.setLastModifyPersonName(userCopy.getLastModifyPersonName());
					user.setLastModifyDate(userCopy.getLastModifyDate());
					user.setIsOut(userCopy.getIsOut());
					user.setPersontype(userCopy.getPersontype()) ;
					user.setPersontypeName(userCopy.getPersontypeName());
					userList.add(user);
				}
			}
			return userList;
		}else{
			return selectUserDao.getAllPerson04(id);
		}
	}

	@Override
	public List<HhUsers> getUsersByGroupIds(String ids) {
		// TODO Auto-generated method stub
		return selectUserDao.getAllPerson03(ids);
	}
	
	@Override
	public List<HhUsers> getUsersByVcEmployeeId(String userName,String vcAccount) {
		// TODO Auto-generated method stub
		return selectUserDao.getUsersByVcEmployeeId(userName,vcAccount);
	}
	
	
}
