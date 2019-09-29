package com.softline.dao.select;

import java.util.List;

import com.softline.entity.HhBimaCompany;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.HhUsersCopy;

public interface ISelectUserDao {

	/**
	 * 获取节点
	 * @param hRTop  节点ID
	 * @param type 树种类
	 * @return
	 */
	HhOrganInfo getTop(String hRTop,Integer type);
	/**
	 * 获取该链式字段节点下的所有子节点
	 * @param hRTopPrefix 节点ID的链式字段
	 * @param type 树种类
	 * @return
	 */
	List<HhOrganInfo> getNode(String hRTopPrefix,Integer type);

	List<HhUsers> getAllPerson02(String id, String name);
	
	List<HhUsersCopy> getAllPerson01(String vcOrganId, String vcName);

	/**
	 * 更新bima的公司数据
	 */
	public void updateBimaCompany(List<HhBimaCompany> data);
	
	/**
	 * 只获取公司，cLevel<=3
	 * @param parent
	 * @return
	 */
	List<HhOrganInfo> getNode(String parent);
	

	List<HhUsers> getUsersByName(String name);
	/**
	 * 获取该链式字段节点下的所有子节点
	 * @param hRTopPrefix 节点ID的链式字段
	 * @param type 树种类
	 * @return
	 */
	List<HhOrganInfo> getHistoryNode(String hRTopPrefix,Integer type, String time);
	/**
	 * 获取节点
	 * @param hRTop  节点ID
	 * @param type 树种类
	 * @return
	 */
	HhOrganInfo getHistoryTop(String nnodeId, Integer type, String time);
	
	List<HhOrganInfo> getDepNode(String hRTopPrefix);
	
	List<HhUsers> getAllPerson04(String id);
	
	List<HhUsers> getAllPerson03(String ids);
	
	public List<HhUsers> getUsersByVcEmployeeId(String userName,String vcAccount);
}
