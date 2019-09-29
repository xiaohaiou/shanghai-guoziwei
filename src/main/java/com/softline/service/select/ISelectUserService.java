package com.softline.service.select;

import java.util.List;
import java.util.Map;

import com.softline.client.task.TaskInstruction;
import com.softline.common.CompanyTree;
import com.softline.entity.HhUsers;

public interface ISelectUserService {

	
	//begin 生成hr树
	public List<CompanyTree> getHrOrganal(String nnodeId);
	//生成其他树
	public List<CompanyTree> getOtherOrganal(String auth,Integer type);
	//生成其他历史树
	public List<CompanyTree> getOtherHistoryOrganal(String auth, Integer type,String time);

	/**
	 * 同步bima的公司信息
	 */
	public void saveSynBimaCompany();

	
	List<HhUsers> getAllPerson02(String id, String name);
	
	/**
	 * 获取进行递归过滤之后的数据权限，拼接成String，“，”分隔
	 * @param nnodeId
	 * @return
	 */
	String getDataAuth(String nnodeId);
	
	//获取公司树，过滤部门
	CompanyTree getHRTree();
	

	List<HhUsers> getUsersByName(String name);
	
	/**
	 * 设置财务树不可被选择项
	 * @param topNnodeID
	 * @param selectedTopNnodeID
	 * @return
	 */
	public List<String> getAllBackSelectedTreeOptions(String topNnodeID,String selectedTopNnodeID,Integer type);

	//获取部门树
	CompanyTree getDepTree();
	//选择用户组时，传入groupIds
	List<HhUsers> getUsersByGroupIds(String ids);
	//选择人员时，传入depId
	List<HhUsers> getUsersByDepId(String id);
	
	public List<HhUsers> getUsersByVcEmployeeId(String userName,String vcAccount);
	

}
