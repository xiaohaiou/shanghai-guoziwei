package com.softline.service.publicCompany;

import java.util.List;

import com.softline.entityTemp.BimfOutCompare;



public interface IOutCompareService {
	
	/**
	 * 外部对标获取数据
	 * @param orgID
	 * @param type 查询方案年度还是季度
	 * @return
	 */
	public BimfOutCompare getSeasonData(String orgID,String outorgID,int year ,int season);
	
	/**
	 * 外部对标获取数据
	 * @param orgID
	 * @param type 查询方案年度还是季度
	 * @return
	 */
	public BimfOutCompare getYearData(String orgID,String outorgID,int year );
	
	
	/**
	 * 获取上市公司股价信息
	 */
	
	public List<Object[]> getPublicCompanyStockList();
}
