package com.softline.service.bimshow;


import java.util.List;

import com.softline.entityTemp.HhOrganInfoTreeRelationFinance;
import com.softline.util.MsgPage;



public interface IFinancialPropertyService {


	
	/**
	 * 外部对标获取数据
	 * @param orgID
	 * @param type 查询方案年度还是季度
	 * @return
	 */
	List<Object> getFinancialProperty(String orgID);

	String getFinancialPropertyName(String orgID);

	Integer getFinancialPropertyNumber(String orgID);

	List<Object> getFinanceData(String org);
}
