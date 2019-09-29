package com.softline.dao.report;

import java.util.List;

import com.softline.entity.ReportOverseasLiabilitiesDetail;
import com.softline.entity.ReportStockLiabilities;
/**
 * 
 * @author tch
 *
 */
public interface IReportStockLiabilitiesDao {
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportStockLiabilities getReportStockLiabilities(Integer id);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportStockLiabilities> getReportStockLiabilitiesList(ReportStockLiabilities entity ,Integer offsize,Integer pagesize); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportStockLiabilitiesListCount(ReportStockLiabilities entity); 
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportStockLiabilitiesRepeatCheck(ReportStockLiabilities entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportStockLiabilities entity);

	/**
	 * 查找实体类对应的明细情况
	 * @param id
	 * @return
	 */
	public List<ReportOverseasLiabilitiesDetail> getList1(Integer id);
	
	/**
	 * 汇总各种币种值
	 * @param ids
	 * @return
	 */
	public List<ReportOverseasLiabilitiesDetail> getList1(String ids);
	
	/**
	 * 汇总查询负载数据
	 */
	public List<ReportStockLiabilities> getSumBeanList(String companyName,Integer year,Integer month,String status);
	
}
