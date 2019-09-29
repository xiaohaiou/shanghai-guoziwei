package com.softline.dao.report;

import java.util.List;
import com.softline.entity.ReportOutBenchmark;
import com.softline.entity.ReportOutCompany;
import com.softline.entityTemp.CommitResult;

public interface IReportOutBenchmarkDao {


	

	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	int getReportOutBenchmarkListCount(ReportOutBenchmark entity);
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	List<ReportOutBenchmark> getReportOutBenchmarkList(ReportOutBenchmark entity, Integer offset, Integer pagesize);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	ReportOutBenchmark getReportOutBenchmar(Integer id);
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	boolean saveReportOverseasCapitalPoolRepeatCheck(ReportOutCompany entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	boolean checkCanEdit(ReportOutCompany entity);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param str 
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	Integer getReportOutComapnyListCount(ReportOutCompany entity, String str);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param str 
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	List<ReportOutCompany> getReportOutCompanyList(ReportOutCompany entity,Integer offset, String str, Integer pagesize);
	
	

	/**
	 * 跳转到查看页面
	 * @param entity
	 * @return
	 */
	ReportOutCompany getReportOutCompany(Integer id);
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	boolean saveReportOutCompanyRepeatCheck(ReportOutCompany entity);

	List<ReportOutBenchmark> getReportOutBenchmarkList(String str);

}
