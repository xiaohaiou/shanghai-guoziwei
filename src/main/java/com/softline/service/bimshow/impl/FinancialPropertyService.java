package com.softline.service.bimshow.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.bimshow.IFinancialPropertyDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entityTemp.HhOrganInfoTreeRelationFinance;
import com.softline.service.bimshow.IFinancialPropertyService;
import com.softline.util.MsgPage;

@Service("financialPropertyService")
/**
 * 
 * @author tch
 *
 */
public class FinancialPropertyService implements IFinancialPropertyService{

	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Autowired
	@Qualifier("financialPropertyDao")
	private IFinancialPropertyDao financialPropertyDao;

	@Override
	public List<Object> getFinancialProperty(String orgID) {

		List<Object> a = financialPropertyDao.getFinanceData(orgID);

		return a;
		
	}

	@Override
	public String getFinancialPropertyName(String orgID) {
		// TODO Auto-generated method stub
		return financialPropertyDao.getFinancialProperty(orgID);
	}

	@Override
	public Integer getFinancialPropertyNumber(String orgID) {
		// TODO Auto-generated method stub
		return financialPropertyDao.getFinancialPropertyNumber(orgID);
	}

	@Override
	public List<Object> getFinanceData(String org) {
		List<Object> a = financialPropertyDao.getFinanceCompanyData(org);

		return a;
	}
	

	
}
