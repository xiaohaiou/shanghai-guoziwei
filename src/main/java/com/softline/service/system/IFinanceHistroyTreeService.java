package com.softline.service.system;

import java.util.List;

import com.softline.common.CompanyTree;
import com.softline.entity.HhOrganInfoTreeRelationHistory;

public interface IFinanceHistroyTreeService {
	
	/**
	 * 获取财务历史树
	 * @param auth 数据权限
	 * @param type 财务树类别
	 * @param time 时间
	 * @return
	 */
	public List<CompanyTree> getOtherOrganal(String auth,Integer type,String time);
	
	/**
	 * 获取一个子层级的数据权限
	 * @param nnodelID
	 * @param type
	 * @return
	 */
	public String getNextLevelChildAuthDataStr(String nnodelID,Integer type,String time);
	
	/**
	 * 获取历史树的节点
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelationHistory getTreeOrganInfoNode(int type,String nnodelD,String time);
}
