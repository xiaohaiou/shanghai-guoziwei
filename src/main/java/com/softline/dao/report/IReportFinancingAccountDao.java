package com.softline.dao.report;

import java.util.List;

import com.softline.entity.Purchase;
import com.softline.entity.ReportFinancingAccount;
/**
 * 
 * @author tch
 *
 */
public interface IReportFinancingAccountDao {
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingAccount getReportFinancingAccount(Integer id);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingAccount> getReportFinancingAccountList(ReportFinancingAccount entity ,Integer offsize,Integer pagesize); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportFinancingAccountListCount(ReportFinancingAccount entity); 
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportFinancingAccountRepeatCheck(ReportFinancingAccount entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportFinancingAccount entity);

	public List<ReportFinancingAccount> getSummaryData(String parentOrg,Integer year,Integer month);

	public List<ReportFinancingAccount> getSummaryHistoryData(String compId,Integer year, Integer month);
}
