package com.softline.service.bimshow;

import java.util.List;

public interface IBimShowDataService {

	/**
	 * 获取财务风险预警数据
	 */
	public List<Object> getFinanceRiskData(String model_id) ;
	
	/**
	 * 替换财务低风险柱状图数据 
	 */
	public List<Object> getFinan_risk_histogram(String model_id);
	
	/**
	 * 替换替换工商银行分析饼图数据
	 */
	public List<Object> geticbc_analysis_num(String model_id);
	
	/**
	 * 替换替换工商银行差异公司
	 */
	public List<Object> geticbc_analysis_diff(String model_id);
	
	/**
	 * 替换替换财务与管理口径总数
	 */
	public List<Object> getFin_manage_total(String model_id);
	
	/**
	 * 替换替换财务与管理口径fin_manage_sasac
	 */
	public List<Object> getFin_manage_sasac(String model_id);
	
	/**
	 * 替换上市公司数据
	 */
	public List<Object> getPublic_company(String model_id);
	
	/**
	 * 替换数据全景分析
	 */
	public List<Object> getData_analysis(String model_id);
	
	/**
	 * 替换数据全景分析2
	 */
	public List<Object> getData_analysis_desc(String model_id);
}
