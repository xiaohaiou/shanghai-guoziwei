package com.softline.dao.publicCompany;

import java.util.List;

import com.softline.entityTemp.BimfOutCompareData;

public interface IOutCompareDao {


	/**
	 * 外部对标获取数据
	 * @param orgID
	 * @param searchtype 按照季度还是年度
	 * @return
	 */
	public List<BimfOutCompareData> getYearData(String orgID,int[] date);
	
	/**
	 * 外部对标获取数据
	 * @param orgID
	 * @param searchtype 按照季度还是年度
	 * @return
	 */
	public List<BimfOutCompareData> getSeasonData(String orgID,int[][] date);
	
	/**
	 * 外部对标该公司最新数据
	 * @param orgID
	 * @param searchtype 按照季度还是年度
	 * @return
	 */
	public String getMaxDataDate(String orgID,int searchtype);

	
	/**
	 * 获取上市公司股价信息
	 */
	
	public List<Object[]> getPublicCompanyStockList();
}
