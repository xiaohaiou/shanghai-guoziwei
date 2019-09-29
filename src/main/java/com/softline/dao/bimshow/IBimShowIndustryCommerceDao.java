package com.softline.dao.bimshow;

import java.util.List;

public interface IBimShowIndustryCommerceDao {
	
	/**
	 * 获取上海国资委管辖企业集团名称列表
	 */
	public List<Object> getIndustryCommerceDaoList(String year,String month);
	
	/**
	 * 获取国资口径和工商口径数据
	 */
	public List<Object> getIndustryCommerceCaliberData(String year,String month,String company);
	
	/**
	 * 获取董监信息数据
	 */
	public List<Object> getIndustryCommerceSupervisorData(String year,String month,String company);
	
	/**
	 * 获取股权信息
	 */
	public List<Object> getIndustryCommerceShareInformation(String year,String month,String company);

}
