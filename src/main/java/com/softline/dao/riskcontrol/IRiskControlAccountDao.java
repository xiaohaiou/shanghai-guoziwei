package com.softline.dao.riskcontrol;

import java.util.List;

import com.softline.entity.HhOrganInfo;
import com.softline.entity.RiskControlAccount;

public interface IRiskControlAccountDao {
	
	Integer getAccountListCount(RiskControlAccount entity, String complainReceiveDate2, String dataAuthority);
	
	Integer getExamineAccountListCount(RiskControlAccount entity, String complainReceiveDate2, String dataAuthority);
	
	List<RiskControlAccount> getAccountList(RiskControlAccount entity, Integer offset,	Integer pageSize, String complainReceiveDate2, String dataAuthority);
	
	List<RiskControlAccount> getExamineAccountList(RiskControlAccount entity, Integer offset, Integer pageSize, String complainReceiveDate2, String dataAuthority);

	Integer saveAccount(RiskControlAccount entity);
	
    void updateAccount(RiskControlAccount entity);
	
    void deleteAccount(Integer id);

	RiskControlAccount getAccountById(Integer id);
	
	RiskControlAccount getAccount(RiskControlAccount entity);
	
	HhOrganInfo getCoreCompId(String companyId);
}
