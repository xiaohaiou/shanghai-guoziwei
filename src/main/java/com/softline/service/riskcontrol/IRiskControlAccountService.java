package com.softline.service.riskcontrol;

import com.softline.entity.HhOrganInfo;
import com.softline.entity.RiskControlAccount;
import com.softline.entity.HhUsers;
import com.softline.util.MsgPage;

public interface IRiskControlAccountService {

	MsgPage getAccounts(RiskControlAccount entity, Integer curPageNum, Integer pageSize, String complainReceiveDate2, String dataAuthority);
	
	MsgPage getExamineAccounts(RiskControlAccount entity, Integer curPageNum, Integer pageSize, String complainReceiveDate2, String dataAuthority);

	Integer saveAccount(RiskControlAccount entity);
	
	void updateAccount(RiskControlAccount entity);

	void deleteAccount(Integer id);

	RiskControlAccount getAccount(Integer id);
	
	RiskControlAccount getAccount(RiskControlAccount entity);
	
	void saveAccountExamine(Integer entityId, String examStr, Integer examResult, HhUsers user);
	
	HhOrganInfo getCoreComp(String companyId);
}
