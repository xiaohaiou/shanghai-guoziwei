package com.softline.dao.bimshow;

import java.util.List;

import com.softline.entityTemp.HhOrganInfoTreeRelationFinance;

public interface IFinancialPropertyDao {




	/**
	 * 外部对标获取数据
	 * @param orgID
	 * @param searchtype 按照季度还是年度
	 * @return
	 */
	String getFinancialProperty(String orgID);

	Integer getFinancialPropertyNumber(String orgID);

	List<Object> getFinanceData(String orgID);

	List<Object> getFinanceCompanyData(String org);
}
