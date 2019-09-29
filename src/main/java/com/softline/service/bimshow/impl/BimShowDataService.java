package com.softline.service.bimshow.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.softline.dao.bimshow.IBimShowGetDataDao;
import com.softline.dao.bylaw.IBylawDeptDao;
import com.softline.service.bimshow.IBimShowDataService;

@Service("bimShowDataService")
public class BimShowDataService implements IBimShowDataService {
	
	@Autowired
	@Qualifier("bimShowGetDataDao")
	private IBimShowGetDataDao bimShowGetDataDao;
	
	/**
	 * 获取财务风险预警数据
	 */
	@Override
	public List<Object> getFinanceRiskData(String model_id){
		return bimShowGetDataDao.getFinanceRiskData(model_id);
	}

	/**
	 * 替换财务低风险柱状图数据 
	 */
	@Override
	public List<Object> getFinan_risk_histogram(String model_id) {
		// TODO Auto-generated method stub
		return bimShowGetDataDao.getFinan_risk_histogram(model_id);
	}

	/**
	 * 替换替换工商银行分析饼图数据
	 */
	@Override
	public List<Object> geticbc_analysis_num(String model_id) {
		// TODO Auto-generated method stub
		return bimShowGetDataDao.geticbc_analysis_num(model_id);
	}

	/**
	 * 替换替换工商银行差异公司
	 */
	@Override
	public List<Object> geticbc_analysis_diff(String model_id){
		return bimShowGetDataDao.geticbc_analysis_diff(model_id);
	}
	
	/**
	 * 替换替换财务与管理口径总数
	 */
	@Override
	public List<Object> getFin_manage_total(String model_id){
		return bimShowGetDataDao.getFin_manage_total(model_id);
	}
	
	/**
	 * 替换替换财务与管理口径fin_manage_sasac
	 */
	@Override
	public List<Object> getFin_manage_sasac(String model_id){
		return bimShowGetDataDao.getFin_manage_sasac(model_id);
	}
	
	/**
	 * 替换上市公司数据
	 */
	@Override
	public List<Object> getPublic_company(String model_id){
		return bimShowGetDataDao.getPublic_company(model_id);
	}
	
	/**
	 * 替换数据全景分析
	 */
	@Override
	public List<Object> getData_analysis(String model_id){
		return bimShowGetDataDao.getData_analysis(model_id);
	}
	
	/**
	 * 替换数据全景分析2
	 */
	@Override
	public List<Object> getData_analysis_desc(String model_id){
		return bimShowGetDataDao.getData_analysis_desc(model_id);
	}
}
