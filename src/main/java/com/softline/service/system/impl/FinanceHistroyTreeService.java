package com.softline.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.CompanyTree;
import com.softline.dao.system.IFinanceHistroyTreeDao;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.service.system.IFinanceHistroyTreeService;
@Service("financeHistroyTreeService")
public class FinanceHistroyTreeService implements IFinanceHistroyTreeService {
	@Autowired
	@Qualifier("financeHistroyTreeDao")
	private IFinanceHistroyTreeDao financeHistroyTreeDao;
	
	/**
	 * 获取树
	 * @param auth  权限
	 * @param type 树种类
	 */
	public List<CompanyTree> getOtherOrganal(String auth,Integer type,String time)
	{
		List<CompanyTree> leveltree= new ArrayList<CompanyTree>();
		
		String a[]=auth.split(",");
		for (int i = 0; i < a.length; i++) {
			CompanyTree itemleveltree = getleveltree(a[i],type,time);
			leveltree.add(itemleveltree);
		}
		
		return leveltree;
	}
	
	private CompanyTree getleveltree(String nnodeId,Integer type,String time)
	{
		CompanyTree leveltree =getTree(nnodeId, type,time);
		return leveltree;
	}
	
	private CompanyTree getTree(String nnodeId,Integer type,String time) {
		int level = 0;
		HhOrganInfo sdktop = financeHistroyTreeDao.getTop(nnodeId,type,time);
		if (sdktop == null)
			return null;
		
		CompanyTree top = new CompanyTree(sdktop.getVcFullName(),sdktop.getNnodeId(),"",sdktop.getVcOrganId(),new ArrayList<CompanyTree>());
		List<HhOrganInfo> AllNode = financeHistroyTreeDao.getNode(sdktop.getVcOrganId(),type,time);
		
		getCompanyTreeNode(top, AllNode, level);
		return top;
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
				if (parent.getVcOrganID().equals(item.getVcParentId())) {

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
	
	
	/**
	 * 获取一个子层级的数据权限
	 * @param nnodelID
	 * @param type
	 * @return
	 */
	public String getNextLevelChildAuthDataStr(String nnodelID,Integer type,String time)
	{
		String a="";
		HhOrganInfoTreeRelationHistory node = getTreeOrganInfoNode(type,nnodelID,time);
		if(node!=null)
		{	
			List<HhOrganInfoTreeRelationHistory> childnode = getChildrenTreeOrganInfos(type,node.getVcOrganId(),time);
			
			for (HhOrganInfoTreeRelationHistory item : childnode) {
				if(a.equals(""))
					a=item.getId().getNnodeId();
				else
					a=a+","+item.getId().getNnodeId();
			}
		}
		return a;
	}
	
	/**
	 * 根据类型和ID获取某个节点
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelationHistory getTreeOrganInfoNode(int type,String nnodelD,String time)
	{
		return financeHistroyTreeDao.getTreeOrganInfoNode(type,nnodelD, time);
	}
	
	/**
	 * 根据类型和ID获取该节点的子节点(下一个层级)
	 * @param type
	 * @param parentID 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HhOrganInfoTreeRelationHistory> getChildrenTreeOrganInfos(int type,String parentID,String time)
	{
		return financeHistroyTreeDao.getChildrenTreeOrganInfos(type,parentID,time);
	}
}
