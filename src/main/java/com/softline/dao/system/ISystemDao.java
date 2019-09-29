package com.softline.dao.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import com.softline.entity.HhOperationLog;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entityTemp.OutCompareCompany;
import com.softline.entityTemp.PublicCompany;


public interface ISystemDao {
	
	public void saveHhOperationLogList(HhOperationLog hhOperationLog);
	public HhUsers getEmployeeById(String employeeId);
	
	public HhOrganInfo getOrganInfoById(String id);
	
	public List<HhOrganInfo> getOrganInfos(String vcParentID);
	
	/**
	 * 获取某个人最高的数据权限
	 * @param vcEmployeeID
	 * @return
	 */
	public List getTopCompanyData(String vcEmployeeID);
	/**
	 * 获取某个人(财务type=Base.financetype)最高的数据权限
	 * @param vcEmployeeID
	 * @param type
	 * @return
	 */
	public List getTopFinanceCompanyData(String vcEmployeeID,int type);
	/**
	 * 获取某个机构所属公司
	 * @param organal
	 * @return
	 */
	public String getCompanyDataByNNodelID(String organal);
	
	/**
	 * 根据用户ID获取角色 ，一个用户可以对应多个角色,用逗号拼接起来。
	 * @param userId
	 * @return
	 */
	public String getRolesByVcEmployeeId(String vcEmployeeId);
	
	/**
	 * 获取该公司下所有子公司及其自身的主键拼接
	 * @param topcompanyID
	 * @return
	 */
	public String getAllChildrenOrganal(String topcompanyID);
	
	/**
	 * 获取(财务type=Base.financetype)表中该公司下所有子公司及其自身的主键拼接
	 * @param topcompanyID
	 * @param type
	 * @return
	 */
	public String getAllChildrenFinanceOrganal(String topcompanyID,int type);
	
	/**
	 * 获取所有有效的公司
	 * @return
	 */
	public List<HhOrganInfo> getCompanyOrganInfos();
	
	public List<HhOrganInfo> getTopChildrenCompanyOrganInfos(String authdata);
	
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
	 * 获取树的顶节点
	 * @param type
	 * @return
	 */
	public HhOrganInfoTreeRelation getTopTreeOrganInfoNode(int type);
	
	/**
	 * 根据类型和ID获取该节点的子节点
	 * @param type
	 * @param parentID 
	 * @return
	 */
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
	 * 保存树节点前的重复性校验
	 * @param type
	 * @param vcFullName
	 * @param nNodeID
	 * @return
	 */
	public boolean saveHhOrganInfoTreeRelationcheck(int type,String vcFullName, String nNodeID);
	
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
	 * 获取业态公司 type取null读HR有type取type
	 * @param authdata
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getBusinessCompany(String authdata,Integer type);
	
	/**
	 * 获取业态公司 type取null读HR有type取type2
	 * @param authdata
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getBusinessCompany(String authdata,Integer type,String topNNodeID);
	
	/**
	 * 获取数据权限下所有上市公司
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getPublicCompany(String str);
	
	/**
	 * 获取此上市公司
	 * @param org
	 * @return
	 */
	public HhOrganInfoTreeRelation getThePublicCompany(String org);
	
	/**
	 * 获取该公司下所有子公司及其自身的主键拼接,数据未过滤
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
	 * 对于两家财务机构关联一家bima的判断
	 * @param object
	 * @param bimaID
	 * @param type
	 * @return
	 */

	public String saveHhOrganInfoTreeRelationBimaCheck(String id,Integer bimaID,int type);
	
	/**
	 * 对于两家财务机构关联一家bima的判断 在历史树中
	 * @param object
	 * @param bimaID
	 * @param type
	 * @return
	 */
	public String saveHhOrganInfoTreeRelationBimaCheckInHistory(String id,Integer bimaID,int type,String time);
	
	/**
	 * 获取海航实业的nnodeID
	 * @return
	 */
	public String getTopNnodeID(Integer financetype);
	
	/**
	 * 根据nNodeID获取对应财务公司的名称
	 * @return
	 */
	public String getFinanceCompName(String nNodeID, Integer financetype);
	
	/**
	 * 根据公司名称获取对应财务公司的nnodeID
	 * @return
	 */
	public String getFinancenNodeID(String vcFullName, Integer financetype);
	
	public HhOrganInfoTreeRelation getTopTreeOrganInfoNodeStr(int type,String str);
	
	/**
	 * 获取财务树最后修改时间
	 * @return
	 */
	public String getFinancialTreeLastModifyTime();
	/**
	 * 获取除虚拟公司外的所有公司
	 * @param vcEmployeeId
	 * @return
	 */
	public String getPublicNoVirtualCompany(String org);
	
	/**
	 * 获取hr公司 上级公司NnodeId 
	 */
	
	public String getHrorginfoParentNnodeId(String org);
	
	public List<HhOrganInfo> getAuthorCompanyList(String vcOrganID);
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
	
	public List<HhOrganInfoTreeRelation> getBusinessCompanyForJudje(String authdata,Integer type);
	
	/**
	 *  登录
	 * 根据用户名获取用户
	 * @param name
	 * @return
	 */
	public HhUsers getEmployeeByName(String name);
	

	public List<OutCompareCompany> getOutCompareCompanyList(String orgstr);
	
	/**
	 * 获取所有外部对标企业
	 * @param organID
	 * @param time
	 * @param type
	 * @return
	 */
	public List<OutCompareCompany> getAllOutCompareCompanyList();
	
	/**
	 * 获取该节点下能看到的上市公司列表
	 * @param organID
	 * @param time
	 * @param type
	 * @return
	 */
	public List<PublicCompany> getPublicCompanyList(String organID,String time,int type);
		
}
