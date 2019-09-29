package com.softline.service.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.softline.entity.HhOperationLog;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalWork;
import com.softline.entity.SysUsers;
import com.softline.entityTemp.OutCompareCompany;

public interface ISystemService {
	
	public void saveHhOperationLogList(HhOperationLog hhOperationLog);
	public HhUsers getEmployeeById(String employeeId);
	
	public HhOrganInfo getOrganInfoById(String id);
	
	public List<HhOrganInfo> getOrganInfos(String vcParentID);

	public void SSoSetAuthirity(String vcEmployeeID,HttpServletRequest request);
	
	
	public List<HhOrganInfo> getTopChildrenCompanyOrganInfos(String authdata);
	
	/**
	 * 获取某个人(财务type=Base.financetype)最高的数据权限
	 * @param vcEmployeeID
	 * @param type
	 * @return
	 */
	public String getTopFinanceCompanyData(String vcEmployeeID,int type);
	
	/**
	 * 根据类型获取对应类型的除顶节点之外的所有节点
	 * @param type
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getAllChildrenTreeOrganInfos(int type);
	
	/**
	 * 根据类型和ID获取某个节点
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelation getTreeOrganInfoNode(int type,String nnodelD);
	
	/**
	 * 根据类型和ID获取某个节点所属业态
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelation getTreeOrganInfoNodeBusiness(int type,String nnodelD);
	
	/**
	 * 根据类型和ID获取该节点的子节点(下一个层级)
	 * @param type
	 * @param parentID 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HhOrganInfoTreeRelation> getChildrenTreeOrganInfos(int type,String parentID);
	
	
	/**
	 * 根据类型和ID获取该节点的子节点(所有层级)
	 * @param type
	 * @param parentID 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HhOrganInfoTreeRelation> getAllChildrenTreeOrganInfos(int type,String parentID);
	
	/**
	 * 获取报表权限
	 * @param nnodeID
	 * @param type
	 * @return
	 */
	public String getOtherOrganAuthData(String nnodeID,int type);
	
	
	/**
	 * 根据节点ID和类型获取父节点ID
	 * @param nnodelID
	 * @param type
	 * @return
	 */
	public String getParentBynNodeID(String nnodelID,Integer type);
	
	
	
	
	/**
	 * 获取一个子层级的数据权限
	 * @param nnodelID
	 * @param type
	 * @return
	 */
	public String getNextLevelChildAuthDataStr(String nnodelID,Integer type);
	
	/**
	 * 获取业态公司
	 * @param authdata
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getBusinessCompany(String authdata,Integer type);
	
	/**
	 * 获取业态公司2
	 * @param authdata
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getBusinessCompany(String authdata,Integer type,String topNNodeID);
	
	/**
	 * 获取(财务type=Base.financetype)表中该公司下所有子公司及其自身的主键拼接
	 * @param topcompanyID
	 * @param type
	 * @return 权限
	 */
	public String getAllChildrenFinanceOrganal(String topcompanyID,int type);
	
	/**
	 * 获取上市公司
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getPublicCompany(String str);
	
	/**
	 * 获取树的顶节点
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelation getTopTreeOrganInfoNode(Integer financetype);
	
	/**
	 * 获取数据权限
	 * @param str
	 * @return
	 */
	public String getDataauth(String str);
	
	/**
	 * 获取hh_base中的description
	 * @param id
	 * @return
	 */
	public Object getDescription(Integer id);
	
	/**
	 * 获取海航实业的nnodeID
	 * @return
	 */
	public String getTopNnodeID(Integer financetype);
	
	/**
	 * 获取sessionCompany,用于app调用work接口获取工作提醒
	 * @param vcEmployeeId
	 * @return
	 */
	public String getSessionCompany(String vcEmployeeId);
	public String getCompanyDataByNNodelID(String nnodeId);
	/**
	 * 获取除虚拟公司外的所有公司
	 * @param vcEmployeeId
	 * @return
	 */
	public String getPublicNoVirtualCompany(String org);
	
	/**
	 * 获取最高级企业下面所有公司Nnodeid拼接主键
	 * @param str
	 * @return
	 */
	public String getDataauthNnodeIDs(String nnodeID);
	
	
	/**
	 * 获取hr树 公司名
	 * @param nnodeID
	 * @return
	 */
	public String getHrOrginfoNameBynnodeID(String nnodeID) ;
	
	/**
	 * 获取hr树 公司名
	 * @param vcOrganID
	 * @return
	 */
	public String getHrOrginfoNameByOrgID(String vcOrganID) ;
	
	/**
	 * 登录
	 * 根据用户名获取用户
	 * @param name
	 * @return
	 */
	public HhUsers getEmployeeByName(String name);
	
	
	/**
	 * 获取所有外部对标企业
	 * @param organID
	 * @param time
	 * @param type
	 * @return
	 */
	public List<OutCompareCompany> getAllOutCompareCompanyList();
	
	/**
	 * 获取外部对标对比企业的对应
	 * @param organID
	 * @param time
	 * @param type
	 * @return
	 */
	public List<OutCompareCompany> getOutCompareCompanyList(String organID,String time,int type);
}
