package com.softline.service.system.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;


import org.apache.log4j.Logger;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.opendata.api.ODPRequest;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyLevelTree;
import com.softline.common.CompanyTreeState;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.IFinanceHistroyTreeDao;
import com.softline.dao.system.IProcedureOpDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhOrganInfoTreeRelationLog;
import com.softline.entity.HhProcedureOpRecord;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entityTemp.CommitResult;
import com.softline.service.system.ITreeService;
import com.softline.util.Coder;
import com.softline.util.MsgPage;


@Service("treeService")
public class TreeService implements ITreeService{
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	@Autowired
	@Qualifier("procedureOpDao")
	private IProcedureOpDao procedureOpDao;
	@Autowired
	@Qualifier("financeHistroyTreeDao")
	private IFinanceHistroyTreeDao financeHistroyTreeDao;

	
	private static final Logger logger = Logger.getLogger(TreeService.class);
	/**
	 * 公司树的图片
	 */
	public static String[] icon = { "fa fa-file icon-state-danger",
			"fa fa-file icon-state-warning", "fa fa-file icon-state-success",
			"fa fa-file icon-state-info","fa fa-file icon-state-warning", "fa fa-file icon-state-success",
			"fa fa-file icon-state-info","fa fa-file icon-state-warning", "fa fa-file icon-state-success",
			"fa fa-file icon-state-info" };


	/**
	 * 根据type获取树
	 * @param type 树种类
	 * @return
	 */
	@Override
	public CompanyLevelTree getTree(Integer type){
		if(type==null)
			return new CompanyLevelTree();
		CompanyLevelTree tree=	getCompanyLevelTrees(type);
		return tree;
		
	}

	// 组织架构树获取	
	private CompanyLevelTree getCompanyLevelTrees(int type){
		
		HhOrganInfoTreeRelation topTree = systemDao.getTopTreeOrganInfoNode(type);		
		List<HhOrganInfoTreeRelation> childtree= systemDao.getAllChildrenTreeOrganInfos(type);
		if(topTree==null)
			return new CompanyLevelTree();
		CompanyLevelTree topCompanyLevelTree = new CompanyLevelTree(getVcfullName(topTree.getStatus(),topTree.getVcFullName()),
																				topTree.getId().getNnodeId(),icon[0], 
																				new ArrayList<CompanyLevelTree>(),
																				topTree.getBimaId());
		topCompanyLevelTree.setState(new CompanyTreeState(true, false));
		getCompanyChildrenTree(childtree, topTree.getVcOrganId(),topCompanyLevelTree, 1);
		return topCompanyLevelTree;
	}

	// /递归处理公司——将子插入父
	private CompanyLevelTree getCompanyChildrenTree(List<HhOrganInfoTreeRelation> allCompany,String companyID, CompanyLevelTree parent, int num) {
		int max = icon.length;
		for (HhOrganInfoTreeRelation item : allCompany) {
			if (num < max - 2)
				num++;
			if (companyID.equals(item.getVcParentId())) {
				CompanyLevelTree newParentCompanyLevelTree = new CompanyLevelTree(getVcfullName(item.getStatus(),item.getVcFullName()), 
																						item.getId().getNnodeId(), 
																						icon[num],
																						new ArrayList<CompanyLevelTree>(),
																						item.getBimaId());			
				parent.children.add(getCompanyChildrenTree(allCompany, item.getVcOrganId(), newParentCompanyLevelTree, num));
			}
			if(num!=max-2)
				num--;	
		}
		return parent;
	}
		

	@Override
	public CompanyLevelTree getTreeByStr(Integer type,String str)
	{
		if(type==null)
			return new CompanyLevelTree();
		CompanyLevelTree tree=	getCompanyLevelTreesStr(type,str);
		return tree;
		
	}

	// 组织架构树获取
	private CompanyLevelTree getCompanyLevelTreesStr(int type,String str) {
		
		HhOrganInfoTreeRelation topTree = systemDao.getTopTreeOrganInfoNodeStr(type,str);		
		List<HhOrganInfoTreeRelation> childtree= systemDao.getAllChildrenTreeOrganInfos(type);
		if(topTree==null)
			return new CompanyLevelTree();
		CompanyLevelTree topCompanyLevelTree = new CompanyLevelTree(topTree.getVcFullName(),topTree.getId().getNnodeId(),icon[0], new ArrayList<CompanyLevelTree>(),topTree.getBimaId());
		topCompanyLevelTree.setState(new CompanyTreeState(true, false));
		getCompanyChildrenTree(childtree, topTree.getVcOrganId(),topCompanyLevelTree, 1);
		return topCompanyLevelTree;
	}

	
	/**
	 * 修改树的节点
	 * @return
	 */
	public CommitResult updatelevel(int type,String oldParent,String id,String newParent,int sort,HhUsers user){
		
		CommitResult result=new CommitResult();
		
		if((newParent!=null && newParent.equals("#")) || ( oldParent!=null && oldParent.equals("#")))
		{
			result=CommitResult.createErrorResult("不能移动顶节点，也不能将节点置顶");
			return result;
		}
		Date date=new Date();
		HhOrganInfoTreeRelation ChangeCompany =systemDao.getTreeOrganInfoNode(type,id);
		HhOrganInfoTreeRelation newParentCompany =systemDao.getTreeOrganInfoNode(type,newParent);
		HhOrganInfoTreeRelation oldParentCompany =systemDao.getTreeOrganInfoNode(type,oldParent);
		HashMap<String,HhOrganInfoTreeRelation> mySet=new HashMap<String, HhOrganInfoTreeRelation>();
		//之所以有限定99是因为海航这个字段的排序值最大也是99
		// 更新旧的同级排序情况
		List<HhOrganInfoTreeRelation> oldGroup=systemDao.getChildrenTreeOrganInfos(type,oldParentCompany.getVcOrganId());
		int index=1;
		for (HhOrganInfoTreeRelation item : oldGroup) {
			
			//后面的公司前移
			if(!item.getId().getNnodeId().equals(id) )
			{
				if(item.getIshowOrder()>ChangeCompany.getIshowOrder())
				{
					saveChange(item,item.getVcOrganId(),item.getVcParentId(),index,deallongsort(item.getVcshoworder(), index),user,date);
					dealChildSort(item,user,date,type,item.getVcOrganId());
				}
				
				if(index<99)
					index++;
			}
			
		}
		// 更新新的同级排序情况
		List<HhOrganInfoTreeRelation> newGroup=systemDao.getChildrenTreeOrganInfos(type,newParentCompany.getVcOrganId());
		//这里尝试移除是为了解决同父节点移动导致的新的同级排序情况不对的情况
		newGroup.remove(ChangeCompany);
		// 重置排序
		index=0;
		for (HhOrganInfoTreeRelation item : newGroup) {
			if(index<99)
				index++;
			if(sort>index)
			{
				continue;
			}
			if(sort==index)
			{   // 在插入位置之后的公司全部向后移排序
				if(index<99)
					index++;
			}
			saveChange(item,item.getVcOrganId(),item.getVcParentId(),index,deallongsort(item.getVcshoworder(), index),user,date);
			dealChildSort(item,user,date,type,item.getVcOrganId());	
		}
		//保存拖动的节点
		//实业的节点的排序字段怪，和他们不一样，所以在拖动业态时要特殊处理
		String changecompanysortstr=newParentCompany.getVcshoworder();
		if(newParentCompany.getVcshoworder().contains("--"))
		{
			changecompanysortstr=newParentCompany.getVcshoworder().substring(0,newParentCompany.getVcshoworder().length()-1)+String.format("%02d", newParentCompany.getIshowOrder())+"-";
		}
		String oldVcOrganId=ChangeCompany.getVcOrganId();
		saveChange(ChangeCompany,newParentCompany.getVcOrganId()+ChangeCompany.getId().getNnodeId()+"-",newParentCompany.getVcOrganId(),sort,changecompanysortstr+String.format("%02d", sort)+"-",user,date);
		dealChildSort(ChangeCompany,user,date,type,oldVcOrganId);
		//systemDao.saveHhOrganInfoTreeRelation(mySet,type);
		result.setResult(true);
		/*
		 * 财务树移动日志保存
		 * @param nNodeId        公司编号
		 * @param vcFullName     公司全名
		 * @param vcOrganID      公司节点
		 * @param vcParentID     公司父节点
		 * @param operate_type   操作类型
		 * @param operate_desc   操作描述
		 */
		String nNodeIdLog = ChangeCompany.getId().getNnodeId();
		String vcFullNameLog = ChangeCompany.getVcFullName();
		String vcOrganIDLog = ChangeCompany.getVcOrganId();
		String vcParentIDLog = ChangeCompany.getVcParentId();
		String operate_typeLog = "公司位置变动";
		String operate_descLog = "原来父亲公司为："+oldParentCompany.getVcFullName()+";  "+
								 "移动后父亲公司为："+newParentCompany.getVcFullName()+";";
		this.saveHhorganInfoTreeRelationLog(nNodeIdLog, vcFullNameLog, vcOrganIDLog, vcParentIDLog, operate_typeLog, operate_descLog);
		return result;
	}
	
	/**
	 * 修改树的节点
	 * @return
	 */
	public CommitResult updateHistorylevel(int type,String oldParent,String id,String newParent,int sort,HhUsers user,String time)
	{
		CommitResult result=new CommitResult();
		
		if((newParent!=null && newParent.equals("#")) || ( oldParent!=null && oldParent.equals("#")))
		{
			result=CommitResult.createErrorResult("不能移动顶节点，也不能将节点置顶");
			return result;
		}
		Date date=new Date();
		HhOrganInfoTreeRelationHistory ChangeCompany =financeHistroyTreeDao.getTreeOrganInfoNode(type, id, time);
		HhOrganInfoTreeRelationHistory newParentCompany =financeHistroyTreeDao.getTreeOrganInfoNode(type,newParent,time);
		HhOrganInfoTreeRelationHistory oldParentCompany =financeHistroyTreeDao.getTreeOrganInfoNode(type,oldParent,time);
		HashMap<String,HhOrganInfoTreeRelationHistory> mySet=new HashMap<String, HhOrganInfoTreeRelationHistory>();
		//之所以有限定99是因为海航这个字段的排序值最大也是99
		// 更新旧的同级排序情况
		List<HhOrganInfoTreeRelationHistory> oldGroup=financeHistroyTreeDao.getChildrenTreeOrganInfos(type,oldParentCompany.getVcOrganId(),time);
		int index=1;
		for (HhOrganInfoTreeRelationHistory item : oldGroup) {
			
			//后面的公司前移
			if(!item.getId().getNnodeId().equals(id) )
			{
				if(item.getIshowOrder()>ChangeCompany.getIshowOrder())
				{
					saveHistoryChange(item,item.getVcOrganId(),item.getVcParentId(),index,deallongsort(item.getVcshoworder(), index),user,date);
					dealHistroyChildSort(item,user,date,type,item.getVcOrganId(),time);
				}
				
				if(index<99)
					index++;
			}
			
		}
		// 更新新的同级排序情况
		List<HhOrganInfoTreeRelationHistory> newGroup=financeHistroyTreeDao.getChildrenTreeOrganInfos(type,newParentCompany.getVcOrganId(),time);
		//这里尝试移除是为了解决同父节点移动导致的新的同级排序情况不对的情况
		newGroup.remove(ChangeCompany);
		// 重置排序
		index=0;
		for (HhOrganInfoTreeRelationHistory item : newGroup) {
			if(index<99)
				index++;
			if(sort>index)
			{
				continue;
			}
			if(sort==index)
			{   // 在插入位置之后的公司全部向后移排序
				if(index<99)
					index++;
			}
			saveHistoryChange(item,item.getVcOrganId(),item.getVcParentId(),index,deallongsort(item.getVcshoworder(), index),user,date);
			dealHistroyChildSort(item,user,date,type,item.getVcOrganId(),time);	
		}
		//保存拖动的节点
		//实业的节点的排序字段怪，和他们不一样，所以在拖动业态时要特殊处理
		String changecompanysortstr=newParentCompany.getVcshoworder();
		if(newParentCompany.getVcshoworder().contains("--"))
		{
			changecompanysortstr=newParentCompany.getVcshoworder().substring(0,newParentCompany.getVcshoworder().length()-1)+String.format("%02d", newParentCompany.getIshowOrder())+"-";
		}
		String oldVcOrganId=ChangeCompany.getVcOrganId();
		saveHistoryChange(ChangeCompany,newParentCompany.getVcOrganId()+ChangeCompany.getId().getNnodeId()+"-",newParentCompany.getVcOrganId(),sort,changecompanysortstr+String.format("%02d", sort)+"-",user,date);
		dealHistroyChildSort(ChangeCompany,user,date,type,oldVcOrganId,time);
		result.setResult(true);
		return result;
	}
	
	private String deallongsort(String sortstr,int sort)
	{
		sortstr=sortstr.substring(0, sortstr.length()-1);
		int last=sortstr.lastIndexOf("-");
		sortstr=sortstr.substring(0, last+1)+String.format("%02d", sort)+"-";
		return sortstr;
	}

	private void dealChildSort(HhOrganInfoTreeRelation node,HhUsers use,Date date,int type,String oldVcOrganId)
	{
		
		List<HhOrganInfoTreeRelation> child= systemDao.getChildrenTreeOrganInfos(type,oldVcOrganId);
		Integer childsort=0;
		if(child.size()>0)
		{
			for (HhOrganInfoTreeRelation item : child) {
				String oldchildVcOrganId=item.getVcOrganId();
				if(childsort<99)
					childsort++;		
				saveChange( item,node.getVcOrganId()+item.getId().getNnodeId()+"-",node.getVcOrganId(),childsort,node.getVcshoworder()+String.format("%02d", childsort)+"-",use,date);
				dealChildSort(item,use,date,type,oldchildVcOrganId);
			}
		}	
	}
	
	private void dealHistroyChildSort(HhOrganInfoTreeRelationHistory node,HhUsers use,Date date,int type,String oldVcOrganId,String time)
	{
		
		List<HhOrganInfoTreeRelationHistory> child= financeHistroyTreeDao.getChildrenTreeOrganInfos(type,oldVcOrganId,time);
		Integer childsort=0;
		if(child.size()>0)
		{
			for (HhOrganInfoTreeRelationHistory item : child) {
				String oldchildVcOrganId=item.getVcOrganId();
				if(childsort<99)
					childsort++;		
				saveHistoryChange( item,node.getVcOrganId()+item.getId().getNnodeId()+"-",node.getVcOrganId(),childsort,node.getVcshoworder()+String.format("%02d", childsort)+"-",use,date);
				dealHistroyChildSort(item,use,date,type,oldchildVcOrganId,time);
			}
		}	
	}

	
	private HhOrganInfoTreeRelation saveChange(HhOrganInfoTreeRelation node,String vcorgan,String parent,int sort,String sortstr,HhUsers use,Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(Common.notEmpty(vcorgan))
			node.setVcOrganId(vcorgan);
		if(Common.notEmpty(parent))
			node.setVcParentId(parent);
		if(date!=null)
			node.setLastModifyDate(sdf.format(date));
		if(use!=null)
		{
			node.setLastModifyPersonId(use.getVcEmployeeId());
			node.setLastModifyPersonName(use.getVcName());
		}
		node.setIshowOrder(sort);
		if(Common.notEmpty(sortstr))
			node.setVcshoworder(sortstr);
		commonDao.saveOrUpdate(node);
		return node;
	}
	
	private HhOrganInfoTreeRelationHistory saveHistoryChange(HhOrganInfoTreeRelationHistory node,String vcorgan,String parent,int sort,String sortstr,HhUsers use,Date date)
	{
		if(Common.notEmpty(vcorgan))
			node.setVcOrganId(vcorgan);
		if(Common.notEmpty(parent))
			node.setVcParentId(parent);
		node.setIshowOrder(sort);
		if(Common.notEmpty(sortstr))
			node.setVcshoworder(sortstr);
		commonDao.saveOrUpdate(node);
		return node;
	}

	/**
	 * 维护节点
	 * @param node
	 * @param user
	 * @return
	 */
	@Override
	public CommitResult saveeditnode(int type,String vcFullName,String nNodeID,String vcShortName,HhUsers user,int status,
									Integer bimaID,String responsiblePersonName,String vcEmployeeID,String responsiblePersonEmail)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		
		if(systemDao.saveHhOrganInfoTreeRelationcheck(type,vcFullName,nNodeID))
		{
			result=CommitResult.createErrorResult("该公司名称与其他公司重名，保存失败");
			return result;
		}
		HhOrganInfoTreeRelation node=systemDao.getTreeOrganInfoNode(type, nNodeID);
		if(node==null)
		{
			result=CommitResult.createErrorResult("请选择一家公司，保存失败");
			return result;
		}
		//有没有两家公司都关联着一个bima公司
		String repeatBimaName=systemDao.saveHhOrganInfoTreeRelationBimaCheck(nNodeID,bimaID,type);
		if(Common.notEmpty(repeatBimaName))
		{
			result=CommitResult.createErrorResult("执行本次操作该公司会与"+repeatBimaName+"关联了同一家工商企业，所以保存失败");
			return result;
		}	
		node.setStatus(status);
		node.setVcFullName(vcFullName);
		node.setVcShortName(vcShortName);
		node.setLastModifyDate(sdf.format(new Date()));
		node.setLastModifyPersonId(user.getVcEmployeeId());
		node.setLastModifyPersonName(user.getVcName());
		node.setBimaId(bimaID);
		node.setResponsiblePersonName(responsiblePersonName);
		node.setVcEmployeeID(vcEmployeeID);
		node.setResponsiblePersonEmail(responsiblePersonEmail);
		commonDao.saveOrUpdate(node);
		result.setResult(true);
		/*
		 * 财务树移动日志保存
		 * @param nNodeId        公司编号
		 * @param vcFullName     公司全名
		 * @param vcOrganID      公司节点
		 * @param vcParentID     公司父节点
		 * @param operate_type   操作类型
		 * @param operate_desc   操作描述
		 */
		String nNodeIdLog = node.getId().getNnodeId();
		String vcFullNameLog = node.getVcFullName();
		String vcOrganIdLog = node.getVcOrganId();
		String vcParentIDLog = node.getVcParentId();
		String operate_typeLog = "编辑节点";
		String operate_descLog = "公司编辑保存！";
		this.saveHhorganInfoTreeRelationLog(nNodeIdLog, vcFullNameLog, vcOrganIdLog, vcParentIDLog, operate_typeLog, operate_descLog);

		return result;
	}
	
	/**
	 * 新增节点
	 * @param node
	 * @param user
	 * @return
	 */
	@Override
	public CommitResult savenewnode(String parentID,int type,String vcFullName,String vcShortName,HhUsers user,int status,
										Integer bimaID,String responsiblePersonName,String vcEmployeeID,String responsiblePersonEmail)
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		if(systemDao.saveHhOrganInfoTreeRelationcheck(type,vcFullName,null))
		{
			result=CommitResult.createErrorResult("该公司名称与其他公司重名，保存失败");
			return result;
		}
		if(!Common.notEmpty(parentID))
		{
			result=CommitResult.createErrorResult("请选择一家公司作为父公司，保存失败");
			return result;
		}
		//有没有两家公司都关联着一个bima公司
		String repeatBimaName=systemDao.saveHhOrganInfoTreeRelationBimaCheck(null,bimaID,type);
		if(Common.notEmpty(repeatBimaName))
		{
			result=CommitResult.createErrorResult("该公司与"+repeatBimaName+"，保存失败");
			return result;
		}	
		HhOrganInfoTreeRelation node=new HhOrganInfoTreeRelation();
		node.getId().setNnodeId(UUID.randomUUID().toString().replaceAll("-", ""));
		node.getId().setType(type);
		node.setCreateDate(sdf.format(new Date()));
		node.setCreatePersonId(user.getVcEmployeeId());
		node.setCreatePersonName(user.getVcName());
		HhOrganInfoTreeRelation parent=systemDao.getTreeOrganInfoNode( node.getId().getType(),parentID);
		node.setIshowOrder(99);
		node.setCflag("1");
		node.setStatus(status);
		node.setBimaId(bimaID);
		node.setVcOrganId(parent.getVcOrganId()+node.getId().getNnodeId()+"-");
		node.setVcParentId(parent.getVcOrganId());
		node.setVcshoworder(parent.getVcshoworder()+String.format("%02d",  node.getIshowOrder())+"-");
		node.setVcFullName(vcFullName);
		node.setVcShortName(vcShortName);
		node.setLastModifyDate(sdf.format(new Date()));
		node.setLastModifyPersonId(user.getVcEmployeeId());
		node.setLastModifyPersonName(user.getVcName());
		node.setResponsiblePersonName(responsiblePersonName);
		node.setVcEmployeeID(vcEmployeeID);
		node.setResponsiblePersonEmail(responsiblePersonEmail);
		commonDao.saveOrUpdate(node);
		result.setResult(true);
		/*
		 * 财务树移动日志保存
		 * @param nNodeId        公司编号
		 * @param vcFullName     公司全名
		 * @param vcOrganID      公司节点
		 * @param vcParentID     公司父节点
		 * @param operate_type   操作类型
		 * @param operate_desc   操作描述
		 */
		HhOrganInfoTreeRelation parentCompanyLog =systemDao.getTreeOrganInfoNode(type,parentID);		
		String nNodeIdLog = node.getId().getNnodeId();
		String vcFullNameLog = node.getVcFullName();
		String vcOrganIdLog = node.getVcOrganId();
		String vcParentIDLog = node.getVcParentId();
		String operate_typeLog = "新增节点";
		String operate_descLog = "该公司的父亲公司为:"+parentCompanyLog.getVcFullName();
		this.saveHhorganInfoTreeRelationLog(nNodeIdLog, vcFullNameLog, vcOrganIdLog, vcParentIDLog, operate_typeLog, operate_descLog);
		return result;
		
	}
	
	
	/**
	 * 维护节点
	 * @param node
	 * @param user
	 * @return
	 */
	public CommitResult deletenode(int type,String nnodelD,HhUsers user){
		CommitResult result=new CommitResult();
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HhOrganInfoTreeRelation node= systemDao.getTreeOrganInfoNode(type, nnodelD);
		if(node!=null)
		{
			
			List<HhOrganInfoTreeRelation> child=systemDao.getChildrenTreeOrganInfos(type, node.getVcOrganId());
			
			for (HhOrganInfoTreeRelation item : child) {
				deletenode(type,item.getId().getNnodeId(),user);
				item.setCflag("0");
				item.setLastModifyDate(sdf.format(date));
				item.setLastModifyPersonId(user.getVcEmployeeId());
				item.setLastModifyPersonName(user.getVcName());
				commonDao.saveOrUpdate(item);
			}
			node.setCflag("0");
			node.setLastModifyDate(sdf.format(date));
			node.setLastModifyPersonId(user.getVcEmployeeId());
			node.setLastModifyPersonName(user.getVcName());
			commonDao.saveOrUpdate(node);
		}
		result.setResult(true);
		/*
		 * 财务树移动日志保存
		 * @param nNodeId        公司编号
		 * @param vcFullName     公司全名
		 * @param vcOrganID      公司节点
		 * @param vcParentID     公司父节点
		 * @param operate_type   操作类型
		 * @param operate_desc   操作描述
		 */
		String nNodeIdLog = node.getId().getNnodeId();
		String vcFullNameLog = node.getVcFullName();
		String vcOrganIdLog = node.getVcOrganId();
		String vcParentIDLog = node.getVcParentId();
		String operate_typeLog = "删除节点";
		String operate_descLog = "删除节点！";
		this.saveHhorganInfoTreeRelationLog(nNodeIdLog, vcFullNameLog, vcOrganIdLog, vcParentIDLog, operate_typeLog, operate_descLog);
		return result;
	}

	@Override
	public String saveGuahuaTree(String time,HhUsers users) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HhProcedureOpRecord record = procedureOpDao.runGuahuaFinanceTreee(time);
		record.setOpTime(df.format(new Date()));
		record.setOpVcEmployeeId(users.getVcEmployeeId());
		record.setOpVcName(users.getVcName());
		commonDao.saveOrUpdate(record);
		/*
		 * 财务树移动日志保存
		 * @param nNodeId        公司编号
		 * @param vcFullName     公司全名
		 * @param vcOrganID      公司节点
		 * @param vcParentID     公司父节点
		 * @param operate_type   操作类型
		 * @param operate_desc   操作描述
		 */
		String nNodeIdLog = "null";//改字段不能为空
		String vcFullNameLog =  "";
		String vcOrganIdLog =  "";
		String vcParentIDLog =  "";
		String operate_typeLog = "固化财务树";
		String operate_descLog = "固化财务树的时间为："+time;
		this.saveHhorganInfoTreeRelationLog(nNodeIdLog, vcFullNameLog, vcOrganIdLog, vcParentIDLog, operate_typeLog, operate_descLog);
	
		if(record.getOpResult() == 1){
			return "success";
		}else{
			return "error";
		}		
	}
	
	@Override
	public String saveGuahuaHistoryTree(String time,HhUsers users) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HhProcedureOpRecord record = procedureOpDao.runGuahuaFinanceHistoryTreee(time);
		record.setOpTime(df.format(new Date()));
		record.setOpVcEmployeeId(users.getVcEmployeeId());
		record.setOpVcName(users.getVcName());
		commonDao.saveOrUpdate(record);
		if(record.getOpResult() == 1){
			return "success";
		}else{
			return "error";
		}		
	}

	
	/**
	 * 获取历史财务树相关信息
	 * @param type
	 * @return
	 */	
	@Override
	public CompanyLevelTree getHistoryTree(Integer type,String time){
		if(type==null)
			return new CompanyLevelTree();
		CompanyLevelTree tree=	getHistoryCompanyLevelTrees(type,time);
		return tree;
		
	}
	//获取历史财务树信息
	private CompanyLevelTree getHistoryCompanyLevelTrees(int type,String time) {
		
		HhOrganInfoTreeRelation topTree = systemDao.getTopTreeOrganInfoNode(type);
		//获取历史树数据
		HhOrganInfo  hhOrganInfo= financeHistroyTreeDao.getTop(topTree.getId().getNnodeId(),type,time);
		
		String vcFullName = topTree.getVcFullName();
		if(null!=hhOrganInfo)
			vcFullName = hhOrganInfo.getVcFullName();
		
		List<HhOrganInfoTreeRelationHistory> childtree= financeHistroyTreeDao.getChildrenTreeOrganInfos(type,time);
		
		if(topTree==null)
			return new CompanyLevelTree();
		
		vcFullName = getVcfullName(topTree.getStatus(),vcFullName);
		
		CompanyLevelTree topCompanyLevelTree = new CompanyLevelTree(vcFullName,topTree.getId().getNnodeId(),icon[0], new ArrayList<CompanyLevelTree>(),topTree.getBimaId());
		topCompanyLevelTree.setState(new CompanyTreeState(true, false));
		getHistoryCompanyChildrenTree(childtree, topTree.getVcOrganId(),topCompanyLevelTree, 1);
		return topCompanyLevelTree;
	}

	// 递归处理公司——将子插入父
	private CompanyLevelTree getHistoryCompanyChildrenTree(List<HhOrganInfoTreeRelationHistory> allCompany,String companyID, CompanyLevelTree parent, int num) {
		int max = icon.length;
		String vcFullName = "";
		for (HhOrganInfoTreeRelationHistory item : allCompany) {
			if (num < max - 2)
				num++;
			if (companyID.equals(item.getVcParentId())) {				
				vcFullName = getVcfullName(item.getStatus(),item.getVcFullName());				
				CompanyLevelTree newParentCompanyLevelTree = new CompanyLevelTree(vcFullName, item.getId().getNnodeId(), icon[num],new ArrayList<CompanyLevelTree>(),item.getBimaId());			
				parent.children.add(getHistoryCompanyChildrenTree(allCompany, item.getVcOrganId(), newParentCompanyLevelTree, num));
			}
			if(num!=max-2)
				num--;	
		}
		return parent;
	}	
	
	/**
	 * 	获取权限控制的财务树信息
	 * @param type 		财务树种类
	 * @param organStr	财务树权限信息
	 * @return			返回财务树
	 */
	@Override
	public CompanyLevelTree getTreeByOrganStr(Integer type,String organStr){
		//1、单权限信息时
		if(!organStr.contains(",")){
			HhOrganInfoTreeRelation topTree = systemDao.getTopTreeOrganInfoNodeStr(type,organStr);		
			List<HhOrganInfoTreeRelation> childtree= systemDao.getAllChildrenTreeOrganInfos(type,organStr);
			if(topTree==null)
				return new CompanyLevelTree();
			CompanyLevelTree topCompanyLevelTree = new CompanyLevelTree(topTree.getVcFullName(),topTree.getId().getNnodeId(),icon[0], new ArrayList<CompanyLevelTree>(),topTree.getBimaId());
			topCompanyLevelTree.setState(new CompanyTreeState(true, false));
			getCompanyChildrenTree(childtree, topTree.getVcOrganId(),topCompanyLevelTree, 1);
			return topCompanyLevelTree;
		}
		//2、多权限信息时
		List<HhOrganInfoTreeRelation> hhRelationArr = new ArrayList<HhOrganInfoTreeRelation>();
		String[] strArr = organStr.split(",");
		for(String strTemp:strArr){
			HhOrganInfoTreeRelation hhRelationTemp = systemDao.getTopTreeOrganInfoNodeStr(type,strTemp);
			if(null!=hhRelationTemp){
				hhRelationArr.add(hhRelationTemp);
			}
		}
		//设置top公司为海航物流
		HhOrganInfoTreeRelation topTree = systemDao.getTopTreeOrganInfoNode(type);		
		if(topTree==null)
			return new CompanyLevelTree();
		CompanyLevelTree topCompanyLevelTree = new CompanyLevelTree(getVcfullName(topTree.getStatus(),topTree.getVcFullName()),
																				topTree.getId().getNnodeId(),icon[0], 
																				new ArrayList<CompanyLevelTree>(),
																				topTree.getBimaId());
		topCompanyLevelTree.setState(new CompanyTreeState(true, false));
		getCompanyChildrenTreeByOrganStr(hhRelationArr, topTree.getVcOrganId(),topCompanyLevelTree, 1,type);
		return topCompanyLevelTree;
	}
	
	//权限控制，插入下一层级树信息，并递归插入下一层级所有字树的信息
	private void getCompanyChildrenTreeByOrganStr(List<HhOrganInfoTreeRelation> allCompany,String companyID, CompanyLevelTree parent, int num,Integer type){
		int max = icon.length;
		for (HhOrganInfoTreeRelation item : allCompany) {
			if (num < max - 2)
				num++;
			CompanyLevelTree newParentCompanyLevelTree = new CompanyLevelTree(getVcfullName(item.getStatus(),
																			  item.getVcFullName()), 
																			  item.getId().getNnodeId(), 
																			  icon[num],
																			  new ArrayList<CompanyLevelTree>(),
																			  item.getBimaId());
			List<HhOrganInfoTreeRelation> childtree= systemDao.getAllChildrenTreeOrganInfos(type,item.getId().getNnodeId());
			parent.children.add(getCompanyChildrenTree(childtree, item.getVcOrganId(), newParentCompanyLevelTree, num));
			if(num!=max-2)
				num--;	
		}
	}
	
	/**
	 * 根据权限获取历史财务树信息
	 * @param type		财务树种类
	 * @param time		财务树固化时间
	 * @param organStr  权限信息
	 * @return
	 */
	@Override
	public CompanyLevelTree getHistoryTreeByOrganStr(Integer type,String organStr,String time){
		//1、单权限时
		if(!organStr.contains(",")){
			HhOrganInfoTreeRelationHistory topTree = financeHistroyTreeDao.getTreeOrganInfoNode(type,organStr,time);		
			if(topTree==null)
				return new CompanyLevelTree();
			List<HhOrganInfoTreeRelationHistory> childtree= financeHistroyTreeDao.getChildrenAllTreeOrganInfos(type,topTree.getVcOrganId(),time);
			String vcFullName = getVcfullName(topTree.getStatus(),topTree.getVcFullName());
			CompanyLevelTree topCompanyLevelTree = new CompanyLevelTree(vcFullName,topTree.getId().getNnodeId(),icon[0], new ArrayList<CompanyLevelTree>(),topTree.getBimaId());
			topCompanyLevelTree.setState(new CompanyTreeState(true, false));
			getHistoryCompanyChildrenTree(childtree, topTree.getVcOrganId(),topCompanyLevelTree, 1);
			return topCompanyLevelTree;
		}
		//2、多权限信息时
		String[] strArr = organStr.split(",");
		List<HhOrganInfoTreeRelationHistory> hhRelationArr = new ArrayList<HhOrganInfoTreeRelationHistory>();
		for(String strTemp:strArr){
			HhOrganInfoTreeRelationHistory historyRelationTreeTemp = financeHistroyTreeDao.getTreeOrganInfoNode(type,strTemp,time);
			if(null!=historyRelationTreeTemp)
				hhRelationArr.add(historyRelationTreeTemp);
		}
		//将topTree设置为海航物流
		HhOrganInfoTreeRelationHistory topTree = financeHistroyTreeDao.getTreeOrganInfoNode(type,
																							systemDao.getTopTreeOrganInfoNode(type).getId().getNnodeId(),
																							time);		
		if(topTree==null)
			return new CompanyLevelTree();
		CompanyLevelTree topCompanyLevelTree = new CompanyLevelTree(getVcfullName(topTree.getStatus(),topTree.getVcFullName()),
																	topTree.getId().getNnodeId(),icon[0], 
																	new ArrayList<CompanyLevelTree>(),
																	topTree.getBimaId());
		topCompanyLevelTree.setState(new CompanyTreeState(true, false));
		getCompanyHistoryChildrenTreeByOrganStr(hhRelationArr, topTree.getVcOrganId(),topCompanyLevelTree, 1,type,time);
		return topCompanyLevelTree;
	}
	
	//权限控制，插入下一层级历史树信息，并递归插入下一层级所有字树的信息
	private void getCompanyHistoryChildrenTreeByOrganStr(List<HhOrganInfoTreeRelationHistory> allCompany,String companyID, CompanyLevelTree parent, int num,Integer type,String time){
	
		int max = icon.length;
		for (HhOrganInfoTreeRelationHistory item : allCompany) {
			if (num < max - 2)
				num++;
			CompanyLevelTree newParentCompanyLevelTree = new CompanyLevelTree(getVcfullName(item.getStatus(),item.getVcFullName()),
																			  item.getId().getNnodeId(),
																			  icon[num],
																			  new ArrayList<CompanyLevelTree>(),
																			  item.getBimaId());
			List<HhOrganInfoTreeRelationHistory> childtree= financeHistroyTreeDao.getChildrenAllTreeOrganInfos(type,item.getVcOrganId(),time);
			parent.children.add(getHistoryCompanyChildrenTree(childtree, item.getVcOrganId(), newParentCompanyLevelTree, num));
			if(num!=max-2)
				num--;	
		}
	}
	
	//显示公司属性
	public String getVcfullName(Integer status,String vcFullName){
		if(null==status)
			return vcFullName;
		
		switch (status){
		
		case 50500:
			vcFullName+="  (实体公司)";
			break;
		case 50501:
			vcFullName+="  (虚拟公司)";
			break;
		case 50502:
			vcFullName+="  (壳公司)";
			break;	
		}
		return vcFullName;
	}
	
	
	/**
	 * 获取财务树最后修改时间
	 * @return
	 */
	@Override
	public String getFinancialTreeLastModifyTime(){
		return systemDao.getFinancialTreeLastModifyTime();
	}

	@Override
	public CommitResult saveEditHistoryNode(int type, String vcFullName,
			String nNodeID, String vcShortName, HhUsers user, int status,
			Integer bimaID, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		HhOrganInfoTreeRelationHistory node=financeHistroyTreeDao.getTreeOrganInfoNode(type, nNodeID, time);
		if(node==null)
		{
			result=CommitResult.createErrorResult("请选择一家公司，保存失败");
			return result;
		}
		//有没有两家公司都关联着一个bima公司
		String repeatBimaName=systemDao.saveHhOrganInfoTreeRelationBimaCheckInHistory(nNodeID,bimaID,type,time);
		if(Common.notEmpty(repeatBimaName))
		{
			result=CommitResult.createErrorResult("执行本次操作该公司会与"+repeatBimaName+"关联了同一家工商企业，所以保存失败");
			return result;
		}	
		node.setStatus(status);
		node.setVcFullName(vcFullName);
		node.setVcShortName(vcShortName);
		node.setBimaId(bimaID);
		commonDao.saveOrUpdate(node);
		result.setResult(true);
		return result;
	}
	@Override
	public boolean getInfodEmail(){
		
		try {
			String mailBody="<body lang=ZH-CN link=\"#0563C1\" vlink=\"#954F72\" style='text-justify-trim:punctuation;' >"+
							"<h3 style='text-align:center;font-size:14.0pt;'></h3><div class=WordSection1 ><p class=MsoNormal>"+
							"<span style='font-size:14.0pt;font-family:\"微软雅黑\",sans-serif'>各位领导：<br></br>"+
							"&nbsp; &nbsp;&nbsp; 每月的20-31号为海航物流财务管理系统当月组织架构关系维护时间，如有调整需求，请各口于月底之前核对财务树并统一意见，反馈需要修改的财务树信息至物流总部计划财务部稽核中心进行维护，逾期将无法进行组织架构关系修改，组织架构关系是物流财务管理系统的数据根基，请各位领导给予高度重视，谢谢！<br>"+
							"<span lang=EN-US><br></span>&nbsp; &nbsp;&nbsp; 该邮件由海航物流<span lang=EN-US>BIM</span>系统自动发送，请勿回复邮件。为保证信息安全，请勿转发该邮件。<br></br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统涉密，不对外网开放，请在内网操作。<span lang=EN-US><o:p></o:p>"+
							"</span></span><br><b></p></div></body>";
			byte[] body;
			body = mailBody.getBytes("UTF-8");
			if(!Common.notEmpty(Base.financeEmailInfoAccountTo)){
				if(logger.isInfoEnabled())
					logger.error("接收人邮件为空！");
				return false;
			}
			String res = new ODPRequest(Base.EMAIL_URL, Base.EMAIL_APPSECRET)
							.addTextSysPara("Method", Base.EMAIL_METHOD)
							.addTextSysPara("AccessToken", Base.EMAIL_ACCESSTOKEN)
							.addTextSysPara("Format", "json")
							//应用参数
							.addTextAppPara("From", Base.EMAIL_ACCOUNT)//邮件服务发件人参数
							.addTextAppPara("To", Base.financeEmailInfoAccountTo)//邮件服务收件人参数
							.addTextAppPara("Cc", Base.financeEmailInfoAccountCc)//抄送人
							.addTextAppPara("Bcc", Base.financeEmailInfoAccountCc)//密送人
							.addTextAppPara("UserName", Base.EMAIL_USERNAME)//发件人的内网账号
							.addTextAppPara("UserPwd", Base.EMAIL_PWD)//发件人的密码，需要base64编码
							.addTextAppPara("Subject", "[提醒]关于海航物流财务管理系统组织架构关系维护提醒")//邮件标题
							.addTextAppPara("Body", Coder.getBASE64(body))//邮件内容参数，需要base64编码
				      		//.addTextAppPara("Attachments", mapList)//附件
							.post();

			//记录邮件发送日志
			Hashtable<String,Object> backTableInfo =  new Hashtable<String,Object>();
			backTableInfo.put("send_Time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			backTableInfo.put("send_People",  Base.EMAIL_ACCOUNT);			
			backTableInfo.put("receive_People", Base.financeEmailInfoAccountTo);			
			backTableInfo.put("status", 1);	
			backTableInfo.put("type", 50110);	
			backTableInfo.put("email_title", "[提醒]关于海航物流财务管理系统组织架构关系维护提醒");		
			backTableInfo.put("email_result", res);	
			financeHistroyTreeDao.saveTreeEmailInfoEmail(backTableInfo);
		
			
			//记录调用邮件接口信息
			Hashtable<String,Object> backInvokeInfo =  new Hashtable<String,Object>();
			backInvokeInfo.put("inteface_name", "ODPRequest邮件接口");
			backInvokeInfo.put("inteface_alias", "成功");
			backInvokeInfo.put("back_result", "1");			
			backInvokeInfo.put("back_resultInfo", "1");				
			backInvokeInfo.put("remark", "无");				
			backInvokeInfo.put("call_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));	
			backInvokeInfo.put("call_person_name", "");					
			backInvokeInfo.put("call_vcEmployeeId", "");				
			try {
				financeHistroyTreeDao.saveTreeEmailInterface(backInvokeInfo);
			} catch (Exception e) {}
			
			if(logger.isInfoEnabled()){
				logger.info("发送邮件人" + Base.financeEmailInfoAccountTo);
				logger.info("发送邮件结果" + res);	
			}
			return true;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("转码失败！");
			return false;
		}
	}
	
	/**
	 * 判断邮箱是否符合
	 * @param email  邮箱地址
	 * @return {Boolean} 是否符合邮箱地址格式
	 */
	private Boolean isTrueEmail(String email){
		Boolean result = false;
		String[] b = email.split("@");
		if(Common.notEmpty(b[0])){
			result = true;
		}
		return result;
	}
	
	/**
	 * 财务树操作日志记录
	 * @param nNodeId        公司编号
	 * @param vcFullName     公司全名
	 * @param vcOrganID      公司节点
	 * @param vcParentID     公司父节点
	 * @param operate_type   操作类型
	 * @param operate_desc   操作描述
	 */
	@Override
	public void saveHhorganInfoTreeRelationLog(	String nNodeId,
												String vcFullName,
												String vcOrganID,
												String vcParentID,
												String operate_type,
												String operate_desc){		
		if(!Common.notEmpty(nNodeId))
			return;
		
		String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String year = nowTime.substring(0, 4);
		String month = nowTime.substring(5, 7);
		String day = nowTime.substring(8, 10);		
		
		HhOrganInfoTreeRelationLog tempBean = new HhOrganInfoTreeRelationLog();
		tempBean.setYear(year);
		tempBean.setMonth(month);
		tempBean.setDay(day);
		tempBean.setnNodeId(nNodeId);
		if(Common.notEmpty(vcFullName))
			tempBean.setVcFullName(vcFullName);
		if(Common.notEmpty(vcOrganID))
			tempBean.setVcOrganID(vcOrganID);
		if(Common.notEmpty(vcParentID))
			tempBean.setVcParentID(vcParentID);
		tempBean.setIsdel(0);
		tempBean.setOperate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		if(Common.notEmpty(operate_type))
			tempBean.setOperate_type(operate_type);
		if(Common.notEmpty(operate_desc))
			tempBean.setOperate_desc(operate_desc);
		commonDao.saveOrUpdate(tempBean);
	}
	
	@Override
	public MsgPage getTreeOperationLog(HhOrganInfoTreeRelationLog searchBean,Integer curPageNum,Integer pageSize,String date2){
		
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = financeHistroyTreeDao.getTreeOperationLog(searchBean, date2);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HhOrganInfoTreeRelationLog> list = financeHistroyTreeDao.getTreeOperationLog(searchBean, offset, pageSize, date2);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
}
