package com.softline.service.bimCenter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.softline.entity.BimCenterCompany;
import com.softline.entityTemp.FinanceRisk;

public interface IBimCenterService {
	
	List<BimCenterCompany> getAllCompanys(HttpServletRequest request);

	List<FinanceRisk> getData(String org, String year);

	List<Object> financialRiskCompany(String orgID, String year);

	String getComapnyName(String org);
	
}
