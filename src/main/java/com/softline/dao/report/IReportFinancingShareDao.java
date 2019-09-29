package com.softline.dao.report;

import java.util.List;

import com.softline.entity.financing.ReportFinancingBond;
import com.softline.entity.financing.ReportFinancingShare;
import com.softline.entity.financing.ReportFinancingShareEnclosure;
import com.softline.entity.financing.ReportFinancingShareLog;
/**
 * 
 * @author ky_tian
 *
 */
public interface IReportFinancingShareDao {
	
	/**
	 * 汇总数据查询
	 */
	public List<String> getDataList(ReportFinancingShare entity);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingShare getReportFinancingShare(Integer id);
	
	/**
	 * 查询导出
	 */
	public List<ReportFinancingShare> getEntityForOrg(ReportFinancingShare entity);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingShare> getReportFinancingShareList(ReportFinancingShare entity ,Integer offsize,Integer pagesize,Integer status); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportFinancingShareListCount(ReportFinancingShare entity,Integer status);

	/**
	 * 根据融资项目id获取此项目所绑定的附件列表
	 * @param id
	 * @return
	 */
	public List<ReportFinancingShareEnclosure> getOldEnclosures(
			Integer id);

	/**
	 * 根据融资项目id，删除此项目下所有的log日志
	 * @param id
	 */
	public void deleteLog(Integer id);

	//获取此项目更新日志
	public List<ReportFinancingShareLog> getLog(Integer id);

	/**
	 * 校验数据是否能更改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportFinancingShare entity); 
	
	/**
	 * 查询
	 * @param name 根据公司名称获取对应的信息。
	 * @return
	 */
	public List<ReportFinancingShare> getReportFinancingShareByCompanyName(String companyName);
	
	public List<ReportFinancingShare> sumDataForSunCompanys(ReportFinancingShare beanIn);
	
	public List<ReportFinancingShare> sumBeansForSunCompanys(ReportFinancingShare beanIn,Integer offsize,Integer pagesize);
	
	public int sumBeansForSunCompanys(ReportFinancingShare beanIn);
	
	public int isVirtualCompany(String organalId);
}
