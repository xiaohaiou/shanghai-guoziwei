package com.softline.dao.system;

import java.util.List;
import java.util.Map;

import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhOrganInfoTreeRelationLog;

public interface IFinanceHistroyTreeDao {
	
	/**
	 * 获取财务历史树顶级节点
	 * @param id  nnodeId 节点ID 
	 * @param type 数类型
	 * @param time 时间
	 * @return
	 */
	HhOrganInfo  getTop(String id,Integer type,String time); 
	
	/**
	 * 获取所有子节点
	 * @param parent 父节点ID
	 * @param type 财务树类型
	 * @param time 时间
	 * @return
	 */
	public List<HhOrganInfo> getNode(String parent,Integer type,String time);
	
	
	/**
	 * 根据类型和ID获取某个节点
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelationHistory getTreeOrganInfoNode(int type,String nnodelD,String time);
	
	/**
	 * 根据类型和ID获取该节点的子节点
	 * @param type
	 * @param parentID 
	 * @return
	 */
	public List<HhOrganInfoTreeRelationHistory> getChildrenTreeOrganInfos(int type,String parentID,String time);
	
	/**
	 * 根据类型获取所有公司信息
	 * @param type
	 * @param parentID 
	 * @return
	 */
	public List<HhOrganInfoTreeRelationHistory> getChildrenTreeOrganInfos(int type,String time);
	
	public List<HhOrganInfoTreeRelationHistory> getChildrenAllTreeOrganInfos(int type,String parentID,String time);
	
	public void saveTreeEmailInfoEmail(Map info);
	
	public void saveTreeEmailInterface(Map info);
	
	public HhOrganInfoTreeRelationHistory getUpTreeOrganInfoByNnodeID(int type,
			String nnodelD,String time);
	
	/**
	 * 查询财务树历史信息
	 * @param searchBean
	 * @return
	 */
	public List<HhOrganInfoTreeRelationLog> getTreeOperationLog(HhOrganInfoTreeRelationLog searchBean,Integer offsize,Integer pagesize,String date2);
	
	public int getTreeOperationLog(HhOrganInfoTreeRelationLog searchBean,String date2);
}
