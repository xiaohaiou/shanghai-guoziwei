package com.softline.dao.bimCenter;

import java.util.ArrayList;
import java.util.List;

import com.softline.entity.BimCenterCompany;
import com.softline.entity.BimCenterSystem;
import com.softline.entityTemp.FinanceRisk;

public interface IBimCenterDao {
	
	/**
	 * 获取bim中心的公司
	 * @param condi 条件实体
	 * @param offset 当前定位
	 * @param pageSize 每页的显示记录条数
	 * @return
	 */
	List<BimCenterCompany> getCompanys(BimCenterCompany condi,Integer offset,Integer pageSize);
	
	
	/**
	 * 获取bim中心的系统
	 * @param condi
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	List<BimCenterSystem> getSystems(BimCenterSystem condi,Integer offset,Integer pageSize);


	List<FinanceRisk> getData(String year, String org);


	List<Object> financialRiskCompany(String orgID, String year);


	String getComapnyName(String org);
	
	
	
	
}
