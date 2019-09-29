package com.softline.dao.report;

import java.util.List;

import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.ReportFinancingProjectProgressEnclosure;
import com.softline.entity.ReportFinancingProjectProgressLog;
import com.softline.entity.financing.ReportFinancingBond;
import com.softline.entity.financing.ReportFinancingInnovate;
import com.softline.entity.financing.ReportFinancingShare;
/**
 * 
 * @author ky_tian
 *
 */
public interface IReportFinancingProjectProgressDao {
	
	/**
	 * 查询org导出数据
	 */
	public List<ReportFinancingProjectProgress> getEntityForOrg(ReportFinancingProjectProgress entity);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingProjectProgress getReportFinancingProjectProgress(Integer id);

	/**
	 * 查询
	 * @param name 根据公司名称获取对应的信息。
	 * @return
	 */
	public List<ReportFinancingProjectProgress> getReportFinancingProjectProgressByCompanyName(String companyName);
	
	
	/**
	 * 海航实业融资项目情况汇总(除债券类)
	 */
	public List<String> getProjectProgressList(ReportFinancingProjectProgress entity,String org);
	
	/**
	 * 海航实业各业态推进中融资项目情况（除债券类）
	 * 
	 * 由于单位选择修改为多选框，数据量过大，修改分页
	 * 
	 * 	author zl 
	 */
	public List<String> getCategoryList(ReportFinancingProjectProgress entity,String orgList,Integer offset, Integer pageSize);
	
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingProjectProgress> getReportFinancingProjectProgressList(ReportFinancingProjectProgress entity ,Integer offsize,Integer pagesize,Integer status); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportFinancingProjectProgressListCount(ReportFinancingProjectProgress entity,Integer status);

	/**
	 * 根据融资项目id获取此项目所绑定的附件列表
	 * @param id
	 * @return
	 */
	public List<ReportFinancingProjectProgressEnclosure> getOldEnclosures(
			Integer id);

	/**
	 * 根据融资项目id，删除此项目下所有的log日志
	 * @param id
	 */
	public void deleteLog(Integer id);

	//获取此项目更新日志
	public List<ReportFinancingProjectProgressLog> getLog(Integer id);

	/**
	 * 校验数据是否能更改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportFinancingProjectProgress entity); 
	
	public List<ReportFinancingProjectProgress> sumBeansForSunCompanys(ReportFinancingProjectProgress beanIn,Integer offsize,Integer pagesize);
	
	public int sumBeansForSunCompanys(ReportFinancingProjectProgress beanIn);
	
	public List<ReportFinancingProjectProgress> sumDataForSunCompanys(ReportFinancingProjectProgress beanIn);

	public List<ReportFinancingBond> getEntityForBond(ReportFinancingProjectProgress entity, String org);

	public List<ReportFinancingInnovate> getEntityForInvocate(ReportFinancingProjectProgress entity, String org);

	public List<ReportFinancingShare> getEntityForShare(ReportFinancingProjectProgress entity, String org);

	public List<ReportFinancingProjectProgress> getEntityForProgress(ReportFinancingProjectProgress entity, String org);
	
	public int isVirtualCompany(String organalId);
}
