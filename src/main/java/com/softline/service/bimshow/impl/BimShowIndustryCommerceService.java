package com.softline.service.bimshow.impl;

import com.softline.dao.bimshow.IBimShowIndustryCommerceDao;
import com.softline.service.bimshow.IBimShowIndustryCommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("bimShowIndustryCommerceService")
public class BimShowIndustryCommerceService implements IBimShowIndustryCommerceService {
	
	@Autowired
	@Qualifier("bimShowIndustryCommerceDao")
	private IBimShowIndustryCommerceDao bimShowIndustryCommerceDao;

	@Override
	public List<Object> getIndustryCommerceDaoList(String year,String month) {
		return bimShowIndustryCommerceDao.getIndustryCommerceDaoList(year,month);
	}
	
	/**
	 * 获取国资口径和工商口径数据
	 */
	public List<Object> getIndustryCommerceCaliberData(String year,String month,String company){
		return bimShowIndustryCommerceDao.getIndustryCommerceCaliberData(year,month,company);
	}
	
	/**
	 * 获取董监信息数据
	 */
	public List<Object> getIndustryCommerceSupervisorData(String year,String month,String company){
		return bimShowIndustryCommerceDao.getIndustryCommerceSupervisorData(year,month,company);
	}
	
	/**
	 * 获取股权信息
	 */
	public List<Object> getIndustryCommerceShareInformation(String year,String month,String company){
		return bimShowIndustryCommerceDao.getIndustryCommerceShareInformation(year,month,company);
	}
}
