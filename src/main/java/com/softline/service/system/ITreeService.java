package com.softline.service.system;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.softline.common.CompanyLevelTree;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationLog;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

public interface ITreeService {
	
	/**
	 * 获取树
	 * @param type 类别
	 * @return
	 */
	public CompanyLevelTree getTree(Integer type);
	
	/**
	 * 修改树层级
	 * @param type
	 * @param oldParent
	 * @param id
	 * @param newParent
	 * @param sort
	 * @param user
	 * @return
	 */
	public CommitResult updatelevel(int type,String oldParent,String id,String newParent,int sort,HhUsers user);
	
	/**
	 * 修改树层级
	 * @param type
	 * @param oldParent
	 * @param id
	 * @param newParent
	 * @param sort
	 * @param user
	 * @param time
	 * @return
	 */
	public CommitResult updateHistorylevel(int type,String oldParent,String id,String newParent,int sort,HhUsers user,String time);
	
	/**
	 * 维护节点
	 * @param node
	 * @param user
	 * @return
	 */
	public CommitResult saveeditnode(int type,String vcFullName,String nNodeID,String vcShortName,HhUsers user,int status,
			Integer bimaID,String responsiblePersonName,String vcEmployeeID,String responsiblePersonEmail);
/**
* 新增节点
* @param node
* @param user
* @return
*/
public CommitResult savenewnode(String parentID,int type,String vcFullName,String vcShortName,HhUsers user,int status,
			Integer bimaID,String responsiblePersonName,String vcEmployeeID,String responsiblePersonEmail);
/**
	 * 维护节点
	 * @param node
	 * @param user
	 * @return
	 */
	public CommitResult deletenode(int type,String nnodelD,HhUsers user);
	
	public CompanyLevelTree getTreeByStr(Integer type,String str);
	
	/**
	 * 时间 固化财务树
	 * @param time
	 * @return
	 */
	public String saveGuahuaTree(String time,HhUsers users);
	
	/**
	 * 时间 固化历史财务树
	 * @param time
	 * @return
	 */
	public String saveGuahuaHistoryTree(String time,HhUsers users);
	
	/**
	 * 获取历史财务树相关信息
	 * @param type
	 * @return
	 */	
	public CompanyLevelTree getHistoryTree(Integer type,String time);
	
	/**
	 * 获取财务树最后修改时间
	 * @return
	 */
	public String getFinancialTreeLastModifyTime();
	
	/**
	 * 维护历史树节点
	 * @param node
	 * @param user
	 * @return
	 */
	public CommitResult saveEditHistoryNode(int type,String vcFullName,String nNodeID,String vcShortName,HhUsers user,int status,Integer bimaID,String time);
	
	/**
	 * 根据权限获取财务树信息
	 * @param type
	 * @param organStr
	 * @return
	 */
	public CompanyLevelTree getTreeByOrganStr(Integer type,String organStr);
	
	/**
	 * 根据权限获取历史财务树信息
	 * @param type
	 * @param time
	 * @param organStr
	 * @return
	 */
	public CompanyLevelTree getHistoryTreeByOrganStr(Integer type,String organStr,String time);

	public boolean getInfodEmail();
	
	/**
	 * 财务树操作日志记录
	 * @param nNodeId        公司编号
	 * @param vcFullName     公司全名
	 * @param vcOrganID      公司节点
	 * @param vcParentID     公司父节点
	 * @param operate_type   操作类型
	 * @param operate_desc   操作描述
	 */
	public void saveHhorganInfoTreeRelationLog(String nNodeId,
												String vcFullName,
												String vcOrganID,
												String vcParentID,
												String operate_type,
												String operate_desc);
	
	public MsgPage getTreeOperationLog(HhOrganInfoTreeRelationLog searchBean,Integer curPageNum,Integer pageSize,String date2);
} 
