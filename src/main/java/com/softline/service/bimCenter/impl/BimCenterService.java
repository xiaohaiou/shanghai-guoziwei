package com.softline.service.bimCenter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.bimCenter.IBimCenterDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.BimCenterCompany;
import com.softline.entity.BimCenterSystem;
import com.softline.entityTemp.FinanceRisk;
import com.softline.service.bimCenter.IBimCenterService;

@Service("bimCenterService")
public class BimCenterService implements IBimCenterService{
	
	@Autowired
	@Qualifier("bimCenterDao")
	private IBimCenterDao bimCenterDao;
	
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;


	@Override
	public List<BimCenterCompany> getAllCompanys(HttpServletRequest request) {
		BimCenterCompany condi = new BimCenterCompany();
		List<BimCenterCompany> companys = bimCenterDao.getCompanys(condi, null, null);
		for(int i = 0; i < companys.size(); i++){
			BimCenterCompany entity = companys.get(i);
			BimCenterSystem condi1 = new BimCenterSystem();
			condi1.setSysCompanyId(entity.getId());
			List<BimCenterSystem> systems = bimCenterDao.getSystems(condi1, null, null);
			entity.setSystems(systems);
		}
		return companys;
	}


	@Override
	public List<FinanceRisk> getData(String org, String year) {
		// TODO Auto-generated method stub
		List<FinanceRisk> list = bimCenterDao.getData(year,org);
		return list;
	}


	@Override
	public List<Object> financialRiskCompany(String orgID, String year) {
		// TODO Auto-generated method stub
		return bimCenterDao.financialRiskCompany(orgID,year);
	}


	@Override
	public String getComapnyName(String org) {
		// TODO Auto-generated method stub
		return bimCenterDao.getComapnyName(org);
	}

	
	
	
}
