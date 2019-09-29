package com.softline.dao.report;

import java.util.List;

import com.softline.entity.financing.ReportFinancingBond;
import com.softline.entity.financing.ReportFinancingInnovate;
import com.softline.entity.financing.ReportFinancingInnovateEnclosure;
import com.softline.entity.financing.ReportFinancingInnovateLog;
/**
 * 
 * @author ky_tian
 *
 */
public interface IReportFinancingInnovateDao {
	
	
	/**
	 * 查询
	 */
	public List<ReportFinancingInnovate> getEntityForOrg(ReportFinancingInnovate entity);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingInnovate getReportFinancingInnovate(Integer id);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingInnovate> getReportFinancingInnovateList(ReportFinancingInnovate entity ,Integer offsize,Integer pagesize,Integer status); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportFinancingInnovateListCount(ReportFinancingInnovate entity,Integer status);
	
	public List<ReportFinancingInnovate> getEntityForOrgForRevise(ReportFinancingInnovate entity);

	/**
	 * 根据融资项目id获取此项目所绑定的附件列表
	 * @param id
	 * @return
	 */
	public List<ReportFinancingInnovateEnclosure> getOldEnclosures(
			Integer id);

	/**
	 * 根据融资项目id，删除此项目下所有的log日志
	 * @param id
	 */
	public void deleteLog(Integer id);

	//获取此项目更新日志
	public List<ReportFinancingInnovateLog> getLog(Integer id);

	/**
	 * 校验数据是否能更改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportFinancingInnovate entity); 
	
	/**
	 * 查询
	 * @param name 根据公司名称获取对应的信息。
	 * @return
	 */
	public List<ReportFinancingInnovate> getReportFinancingInnovateByCompanyName(String companyName);
		
	public List<ReportFinancingInnovate> sumDataForSunCompanys(ReportFinancingInnovate beanIn);
	
	public List<ReportFinancingInnovate> sumBeansForSunCompanys(ReportFinancingInnovate beanIn,Integer offsize,Integer pagesize);
	
	public int sumBeansForSunCompanys(ReportFinancingInnovate beanIn);
	
	public int isVirtualCompany(String organalId);
}
