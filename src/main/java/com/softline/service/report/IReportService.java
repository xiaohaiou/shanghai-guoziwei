package com.softline.service.report;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.MainFinancialIndicator;
import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsGroup;
import com.softline.entity.ReportFormsOrganal;
import com.softline.entity.ReportFormsUnFilledCompany;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.ReportQueryView;
import com.softline.entityTemp.Report_Forms_DetailView;
import com.softline.entityTemp.Report_formsView;
import com.softline.entityTemp.Report_forms_groupView;
import com.softline.util.MsgPage;

public interface IReportService {

	////针对reportformsGroup
	/**
	 * 根据查询实体，获取某个报表组及其组中的报表
	 * @param entity
	 * @return
	 */
	public Report_forms_groupView getReport_forms_groupView(ReportFormsGroup entity);
	
	/**
	 * 根据查询实体，获取报表组列表
	 * @param entity
	 * @return
	 */
	public MsgPage getReportformsgroupView(ReportFormsGroup entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 保存报表组以及其下的所有报表
	 * @param entity
	 * @param reportGroupFile 整体导出文件
	 * @param reportGroupExportFile 导入文件
	 * @return
	 */
	public CommitResult saveReportFormsGroup(Report_forms_groupView entity,HhUsers use,MultipartFile reportGroupFile,MultipartFile reportGroupExportFile);
	/**
	 * 删除报表组
	 * @param groupID
	 * @param user
	 * @return 
	 */
	public CommitResult deleteReportFormsGroup(int groupID,HhUsers user);
	
	/**
	 * 根据查询实体，获取报表组列表
	 * @param entity
	 * @return
	 */
	public List<ReportFormsGroup> getReportformsgroupList(ReportFormsGroup entity);
	
	/**
	 * 根据查询实体，获取一个报表组 (通过ID，或者类型和时间的组合键能唯一确定)
	 * @param entity
	 * @return
	 */
	public ReportFormsGroup getReportFormsGroup(ReportFormsGroup entity);
	////针对reportform 和reportpattend 因为这两个一个报表对于一套样式所以查样式的时候查的是reportform的列表
	
	/**
	 * 查询报表列表
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public MsgPage getReportformsView(Report_formsView entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 * 查询报表列表
	 * @param entity
	 * @return
	 */
	public List<ReportForms> getReportformsList(ReportForms entity);
	
	/**
	 * 查询报表
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public ReportForms getReportforms(ReportForms entity);
	
	/**
	 * 保存一张报表的样式
	 * @param formsId 报表ID
	 * @param use 用户
	 * @param pattendFile 样式文件
	 * @param outFile 导出文件
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CommitResult saveReportPattend(Integer formsId,HhUsers use,MultipartFile pattendFile,MultipartFile outFile) throws Exception;

	
	////针对企业和报表关联的
	/**
	 * 获取组织机构和报表关联
	 * @param Date
	 * @param organal
	 * @param groupID
	 * @return
	 */
	public List<ReportFormsOrganal>getReportFormsOrganalData(String Date,String organal,Integer groupID);
	
	/**
	 * 上报检查（都处于未上报才能上报）
	 * @param Date
	 * @param use
	 * @param organal
	 * @param groupID
	 * @param reportstatus
	 * @return
	 */
	public CommitResult saveReportFormReportCheck(String Date,String organal,Integer groupID);

	/**
	 * 上报
	 * @param Date 报表日期
	 * @param use 上报人
	 * @param organal 企业ID
	 * @param groupID 组ID
	 * @return
	 */
	public CommitResult saveReportFormReport(String Date,HhUsers use,String organal,Integer groupID);
	/**
	 * 审核检查（都处于待审核才能审核）
	 * @param Date
	 * @param organal
	 * @param groupID
	 * @return
	 */
	public CommitResult saveReportFormExamineCheck(String Date,String organal,Integer groupID);
	/**
	 * 审核
	 * @param Date 报表时间
	 * @param use 用户
	 * @param organal 公司ID
	 * @param groupID 组ID
	 * @param examineresult 审核结果
	 * @param examineStr 审核意见
	 * @return
	 */
	public CommitResult saveReportFormExamine(String Date,HhUsers use,String organal,Integer groupID,Integer examineresult,String examineStr);
	
	/**
	 * 根据查询公司和报表的关联
	 * @param pagenum
	 * @param pagesize
	 * @param entity
	 * @param Status  公司种类（虚拟、实体）
	 * @return
	 * 
	 */
	public MsgPage getReportFormsOrganalList(Integer pagenum,Integer pagesize,ReportQueryView entity,String status);
	
	
	/**
	 * 根据查询公司和报表的关联用于预算汇总页面
	 * @return
	 */
	public MsgPage getReportFormsOrganalCollectList(Integer pagenum,Integer pagesize,ReportQueryView entity);
	
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
	
	////reportformsCell
	/**
	 * 保存某个报表组之前进行校验,返回true说明已经审核通过,或者待审核不能再修改，返回false说明还可以修改
	 * @param use
	 * @param organalID
	 * @param formID
	 * @param reportFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CommitResult saveReportValue(String reportTime, HhUsers use,String organalID,Integer grouptype,Integer[] fileformsID,CommonsMultipartFile files[],int type) throws FileNotFoundException, IOException;

	/**
	 * 删除一个报表组所有报表的值
	 * @param use
	 * @param organalID
	 * @param formID
	 * @param Date 
	 * @return
	 */
	public CommitResult deleteReportValue(String Date,HhUsers use,String organalID,Integer groupid);
	
	
	/**
	 * 获取机构和报表的关联
	 * @param entity
	 * @return
	 */
	public List<ReportFormsOrganal> getReportFormsOrganal(ReportFormsOrganal entity);
	
	/**
	 * 获取某个报表实例的报表详细数据
	 * @param id 报表实例ID
	 * @return
	 */
	public Report_Forms_DetailView getReportDetailViewByID(Integer id);
	
	
    //获取上传页面需要展示的组内报表，如果组ID为空那么根据另两个条件查询
	/**
	 * 
	 * @param groupid 报表组ID
	 * @param grouptype 报表组类型
	 * @param Date 时间
	 * @return
	 */
	public List<ReportForms> getGroupForm(Integer groupid,Integer grouptype,String Date,Integer type);
	
	/**
	 * 导出模板
	 * @param Date 时间
	 * @param grouptype 模板种类
	 * @return
	 */
	public HhFile getReportExportTemplet(Integer grouptype,int type);
	
	/**
	 * 根据年月获取核算模板
	 * @param year
	 * @param month
	 * @return
	 */
	public HhFile getReportExportAdjustTemplet(Integer year,Integer month);

	
	/**
	 * 报表汇总
	 * @param date
	 * @param use
	 * @param organalID
	 * @param grouptype
	 * @return
	 */
	public CommitResult savecollectReportValue(String date, HhUsers use,String organalID, Integer grouptype);

	
	
	public MainFinancialIndicator AdjustMainFinancialIndicatorExport(String Date,String organal,Integer groupID,HhUsers use,int type);
	
	
	public int getReportId(int time,int type,int nameId);

	
	/**
	 * 核算股权口径没有填报的公司
	 * @param reportTime 填报时间
	 * @return
	 */
	public MsgPage getHsNoCreateCompanyList(String reportTime,String authdata,String org,Integer curPageNum, Integer pageSize);
	
	/**
	 * 
	 * @param type 单体（52004） 汇总/合并（52005）
	 * @param Date 报表时间 yyyy-MM 例如2018-01
	 * @param organalID 公司组织机构ID
	 * @param groupid	报表组ID
	 * @return
	 */
	public XSSFWorkbook getExportExcel(int type,String Date,String organalID,Integer groupid,Integer grouptype);
	
	/**
	 * 获取核算未创建公司列表
	 * @param reportTime  上报时间
	 * @param authdata  数据权限
	 * @param org    公司ID
	 * @return
	 */
	public List  getHsNoCreateCompanyList(String reportTime,String authdata,String org);
	
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
	 * @param curPageNum  
	 * @param pageSize
	 * @return
	 */
	public MsgPage getYsNoCreateCompanyList(String reportTime,String authdata,String org,Integer formKind,Integer CompanyKind,Integer curPageNum, Integer pageSize);
	
	public MsgPage getRemindCompanyList(String reportTime, String authdata,
			String org, Integer formKind, Integer CompanyKind,
			Integer curPageNum, Integer pageSize);

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
	 * @return
	 */
	public List getYsNoCreateCompanyList(String reportTime,String authdata,String org,Integer formKind,Integer CompanyKind);
	
	public boolean isVirtualCompany(String organalId);
	
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
	**/
	public void addAllDataToRemindPlan(String reportTime, String authdata,String org, Integer formKind, Integer CompanyKind);
	
	/**
	 * @param listIn
	 * @param listOut1
	 * @param mapOut2 1.sBSlctPersons 返回选择人公司信息   2.sBAccountNameDeps 返回人账号信息
	 * @return
	 *    author zl
	 */
	public int getInstractionVcAccount(List<Object[]> listIn,
									   /*返回未填报公司账号信息*/List listOut1,
									   /*返回选中发送人账号和账号描述信息*/Map<String,Object> mapOut2);

	/**
	 * @param reportTime
	 * @param authdata
	 * @param org
	 * @param curPageNum
	 * @param pageSize
	 * @param isAddAll
	 * @return
	 * 	用于显示所有需要添加到指令系统的公司
	 */
	public MsgPage getAdjustAccountRemindCompanyList(String reportTime,
			String authdata,String org,Integer curPageNum, Integer pageSize,String isAddAll);
	
	/**
	 * 	预算审核通过50115、回退操作、返回回退公司上层级所有公司信息
	 * @param Date
	 * @param use
	 * @param organal
	 * @param groupID
	 * @param examineresult
	 * @param examineStr
	 * @return
	 */
	public CommitResult setBackExamineStatus(String Date,String organal,Integer groupID,Integer examineresult);
	
	
}
