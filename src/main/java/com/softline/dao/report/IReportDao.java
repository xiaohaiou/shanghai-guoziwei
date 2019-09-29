package com.softline.dao.report;

import java.util.HashMap;
import java.util.List;

import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportExportout;
import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsCell;
import com.softline.entity.ReportFormsGroup;
import com.softline.entity.ReportFormsOrganal;
import com.softline.entity.ReportFormsPattend;
import com.softline.entity.ReportFormsUnFilledCompany;
import com.softline.entity.ReportIndex;
import com.softline.entityTemp.ReportIndexAndData;
import com.softline.entityTemp.ReportQueryView;
import com.softline.entityTemp.Report_formsView;



/**
 * @author hwx
 *
 */
/**
 * @author hwx
 *
 */
/**
 * @author hwx
 *
 */
/**
 * @author hwx
 *
 */
/**
 * @author hwx
 *
 */
/**
 * @author hwx
 *
 */
public interface IReportDao {
	
	
	//针对ReportFormsGroup 报表组
	/**
	 * 校验ReportFormsGroup的保存
	 * @param entity
	 * @return
	 */
	public String checkReportFormsGroups(ReportFormsGroup entity);
	/**
	 * 删除报表组
	 * @param groupID 报表组ID
	 * @param user 操作人
	 */
	public void deleteReportFormsGroups(int groupID,HhUsers user);
	/**
	 * 根据查询实体，获取一个报表组
	 * @param entity 查询实体
	 * @return
	 */
	public ReportFormsGroup getReportFormsGroup(ReportFormsGroup entity);
	
	
	/**
	 * 根据查询实体，获取报表组列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public  Integer getReportFormsGroupsListCount(ReportFormsGroup entity);
	/**
	 * 根据查询实体，获取报表组列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportFormsGroup> getReportFormsGroupsList(ReportFormsGroup entity, Integer offset,Integer pageSize);

	
	/**
	 * 获取该种报表组的最新组ID（模板下载用，因为默认下载最新的样式）
	 * @param grouptype
	 * @return
	 */
	public Integer getLastGroup(Integer grouptype,Integer type);
	
	/**
	 * 根据年月获取核算单体报表的ID,用于附件下载
	 * @param year
	 * @param month
	 * @return
	 */
	public Integer getAdjustGroup(Integer year,Integer month );
	
	//针对ReportForms 报表
	
	/**
	 * 查询报表
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public List<ReportForms> getReportformsList(ReportForms entity);
	
	/**
	 * 根据报表ID删除报表相关数据
	 * @param entity 查询实体
	 * @return
	 */
	public void deleteReportForms(String formsID,HhUsers user);
	/**
	 * 根据查询实体，获取一个报表
	 * @param entity 查询实体
	 * @return
	 */
	public ReportForms getReportForms(ReportForms entity);
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportForms> getReportFormsList(ReportForms entity , Integer offset,Integer pageSize);

	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getReportFormsListCount(ReportForms entity);
	
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<Report_formsView> getReportFormsListView(Report_formsView entity , Integer offset,Integer pageSize);
	
	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getReportFormsListViewCount(Report_formsView entity);
	
	//针对Reportpattend 报表样式
	
	/**
	 * 添加样式
	 * @param formsID 报表ID
	 * @param user 用户
	 */
	public void  saveReportPattend(List<ReportFormsPattend> pattend);
	
	/**
	 * 删除对应的样式
	 * @param formsID 报表ID
	 * @param user 用户
	 */
	public void  deleteReportPattend(String formsID,HhUsers user);
	/**
	 * 根据查询实体，获取一个报表的样式
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportFormsPattend> getReportFormsPattend(ReportFormsPattend entity);
		
	//针对ReportIndex 报表指标
	/**
	 * 删除报表与指标的关联
	 * @param formsID 报表ID
	 * @param user 用户
	 */
	public void  deleteReportIndex(String formsID,HhUsers user);
	/**
	 * 根据查询实体，获取报表指标
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportIndex> getReportFormsIndexList(ReportIndex entity);
	
	//针对ReportFormsOrganal 报表实例
	/**
	 * 删除报表与机构的关联 
	 * @param formsID 报表ID
	 * @param user 用户
	 */
	public void  deleteReportOrganal(String formsID,HhUsers user);
	
	/**
	 * 删除报表与机构的关联
	 * @param ID 报表实例ID
	 * @param user 用户
	 */
	public void  deleteReportOrganalByID(String ID,HhUsers user);
	
	/**
	 * 获取机构和报表的关联
	 * @param entity
	 * @return
	 */
	public List<ReportFormsOrganal> getReportFormsOrganal(ReportFormsOrganal entity);
	/**
	 * 获取机构和报表的关联
	 * @param OrganalId 机构ID
	 * @param formsId 报表类型
	 * @param reporttime 报表时间
	 * @return
	 */
	public ReportFormsOrganal getReportFormsOrganal(String OrganalId,Integer formsId,String reporttime);
	
	
	
	
	/**
	 * 上报报表
	 * @param OrganalId 机构ID
	 * @param groupID 报表类型
	 * @param reporttime 报表时间
	 * @return
	 */
	public void saveReportFormReport(String OrganalId,Integer groupID,String reporttime,Integer reportstatus,HhUsers use,String parentorg);
	
	/**
	 * 审核报表
	 * @param OrganalId 机构ID
	 * @param groupID 报表类型
	 * @param reporttime 报表时间
	 * @return
	 */
	public void saveReportFormExamine(String OrganalId,Integer groupID,String reporttime,Integer reportstatus,HhUsers use);

	/**
	 * 根据报表formID获取机构和报表的关联
	 * @param formsId
	 * @return
	 */
	public List<ReportFormsOrganal> getReportFormsOrganalByFormsID(String formsId);
	
	/**
	 * 根据报表formID和机构ID或者reporttime获取机构和报表的关联
	 * @param formsId in匹配
	 * @param OrganID in匹配
	 * @param date  等号匹配
	 * @return
	 */
	public String getReportFormsOrganalByFormIDAndOrganIDOrDate(String formsId,String OrganID,String date);
	
	/**
	 * 根据ID获取报表实例 reportformOrganal
	 * @param id
	 * @return
	 */
	public ReportFormsOrganal getFormOrganal(Integer id);
	
	/**
	 * 根据报表ID和公司ID获取报表实例 reportformOrganal
	 * @param id
	 * @return
	 */
	public ReportFormsOrganal getFormOrganal(Integer formid,String organalID,String date);
	
	/**
	 * 根据查询公司和报表的关联
	 * @return
	 */
	public List<ReportQueryView> getReportFormsOrganalList(Integer pagenum,Integer pagesize,ReportQueryView entity,String status);
	
	/**
	 * 根据查询实体，获取企业与报表关联列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getReportFormsOrganalListCount(ReportQueryView entity,String status);
	
	
	//针对ReportCell
	/**
	 * 保存某个报表组之前进行校验,返回true说明已经审核通过,或者待审核不能再修改，返回false说明还可以修改
	 * @param reportTime 报表时间
	 * @param organalID 机构ID
	 * @param grouptype 报表组类型
	 * @return
	 */
	public boolean saveReportValueCheck(String reportTime,String organalID,Integer grouptype);
	/**
	 * 根据报表和公司的关联ID删除该公司该报表的内容
	 * @param formOrganalID
	 * @param user
	 */
	public void deleteReportCell(String formOrganalID,HhUsers user);
	/**
	 * 保存一张表的值
	 * @param formsID 报表ID
	 * @param user 用户
	 */
	public void  saveReportCell(List<ReportFormsCell> cell);
	
	/**
	 * 预算单体在审核结束后，叶子节点生成汇总数据用
	 * @param oldrelation
	 * @param newrelation
	 */
	public void  saveChangeReportCell(Integer oldrelation,Integer newrelation);
	
	/**
	 * 预算单体在审核结束后，叶子节点生成汇总数据时根据单体的报表ID获取汇总的ID
	 * @param oldformsID
	 * @return
	 */
	public Integer getChangeNewformsID(String oldformsID,Integer groupid);
	
	/**
	 * 获取一张要汇总表的数据
	 * @param organID
	 * @param reporttime
	 * @param formID
	 * @return 
	 */
	public HashMap<String, String> getCollectReportCell(String childrelactionID);
	
	

	
	/**
	 * 汇总报表的检查，子公司的相应报表组是否都上传且审核通过
	 * @param organalID
	 * @param groupID
	 * @return
	 */
	public boolean saveCollectReportCellCheck(String organalID,int groupID,String reporttime);
	
	/**
	 * 汇总时，需要子的汇总数据和自己的单体，这个方法就是获取自己单体用的
	 * @param formID
	 * @param oldgroup
	 * @param organID
	 * @param reportTime
	 * @return
	 */
	public String getCollectMyRelationID(Integer formID,Integer oldgroup,String organID,String reportTime);
	
	/**
	 * 根据查询实体获取，相关报表格子
	 * @param entity
	 * @return
	 */
	public List<ReportFormsCell> getReportFormsCell(ReportFormsCell entity);

	
	/**
	 * 导出模板
	 * @param Date 时间
	 * @param grouptype 模板种类
	 * @return
	 */
	public ReportExportout getReportExportTemplet(String Date,Integer grouptype);
	
	/**
	 * 根据报表种类、机构id、报表时间获取数据
	 * @param reportTime
	 * @param organl
	 * @param formKind
	 * @return
	 */
	public List<ReportIndexAndData> getReportValueBy(String reportTime,String organl,int[] formKind);

	
	
	public int getReportId(int time,int type,int nameId);

	
	/**
	 * 核算股权口径没有填报的公司
	 * @param reportTime 填报时间
	 * @return
	 */
	public List<Object[]> getHsNoCreateCompanyList(String reportTime,String authdata,String org,Integer offset,Integer pageSize);
	
	/**
	 * 预算报表未填报公司查询
	 * @param reportTime 上报期间
	 * @param authdata   数据权限
	 * @param org        选择的公司
	 * @param formKind   
				//报表组属性单户
				public static final int reportgroup_type_simple=52004;
				//报表组属性汇总
				public static final int reportgroup_type_collect=52005;
	 * @param CompanyKind
				public static final Integer report_organal_entity=50500;
				//虚拟
				public static final Integer report_organal_invent=50501;
				//壳公司
				public static final Integer report_organal_ke=50502; ）
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Object[]> getYsNoCreateCompanyList(String reportTime,String authdata,String org,Integer formKind,Integer CompanyKind,Integer offset,Integer pageSize);
	
	public List<Object[]> getRemindCompanyList(String reportTime,
			String authdata, String org, Integer formKind, Integer CompanyKind,
			Integer offset, Integer pageSize);
	
	public void deleteReportFormExamine(String OrganalId,Integer groupID,String reporttime);

	public List<ReportFormsOrganal> getUpCompanyInfoByHistoryTree(String date,String nNodeIds,Integer groupId);
	
	public void setReportFormsOrganalStatus(ReportFormsOrganal reportFormsOrganal);
	
	public HhOrganInfoTreeRelationHistory getUpHistoryCompanyInfo(HhOrganInfoTreeRelationHistory beanIn);

	public int isVirtualCompany(String organalId);
	
	/**
	 * @param reportTime   报告时间
	 * @param authdata     数据权限
	 * @param org          公司编号
	 * @param formKind     
	 * 			//报表组属性单户
				public static final int reportgroup_type_simple=52004;
				//报表组属性汇总
				public static final int reportgroup_type_collect=52005;
	 * @param CompanyKind
	 * 			public static final Integer report_organal_entity=50500;
				//虚拟
				public static final Integer report_organal_invent=50501;
				//壳公司
				public static final Integer report_organal_ke=50502; ）
	 * 
	 */
	public void addAllDataToRemindPlan(String reportTime,String authdata,String org, Integer formKind, Integer CompanyKind);

	public List<Object[]> getAdjustAccountRemindCompanyList(String reportTime,String authdata,String org,Integer offset,Integer pageSize);
	
	
	
}
