package com.softline.dao.report;

import java.util.List;

import com.softline.entity.financing.ReportFinancingBond;
import com.softline.entity.financing.ReportFinancingBondEnclosure;
import com.softline.entity.financing.ReportFinancingBondLog;
/**
 * 
 * @author ky_tian
 *
 */
public interface IReportFinancingBondDao {
	
	/**
	 * 汇总数据查询
	 */
	public List<String> getDataList(ReportFinancingBond entity);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingBond getReportFinancingBond(Integer id);
	
	/**
	 * 查询
	 */
	public List<ReportFinancingBond> getEntityForOrg(ReportFinancingBond entity);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingBond> getReportFinancingBondList(ReportFinancingBond entity ,Integer offsize,Integer pagesize,Integer status); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportFinancingBondListCount(ReportFinancingBond entity,Integer status);

	/**
	 * 根据融资项目id获取此项目所绑定的附件列表
	 * @param id
	 * @return
	 */
	public List<ReportFinancingBondEnclosure> getOldEnclosures(
			Integer id);

	/**
	 * 根据融资项目id，删除此项目下所有的log日志
	 * @param id
	 */
	public void deleteLog(Integer id);

	//获取此项目更新日志
	public List<ReportFinancingBondLog> getLog(Integer id);

	/**
	 * 校验数据是否能更改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportFinancingBond entity); 
	
	/**
	 * 查询
	 * @param name 根据公司名称获取对应的信息。
	 * @return
	 */
	public List<ReportFinancingBond> getReportFinancingBondByCompanyName(String companyName);
	
	public List<ReportFinancingBond> sumDataForSunCompanys(ReportFinancingBond beanIn);
	
	public List<ReportFinancingBond> sumBeansForSunCompanys(ReportFinancingBond beanIn,Integer offsize,Integer pagesize);
	
	public int sumBeansForSunCompanys(ReportFinancingBond beanIn);

	public int isVirtualCompany(String organalId);
}
