package com.softline.service.riskcontrol;

import com.softline.entity.RiskControlContract;
import com.softline.entity.HhUsers;
import com.softline.util.MsgPage;

public interface IRiskControlContractService {

	MsgPage getContracts(RiskControlContract entity, Integer curPageNum, Integer pageSize, String contractSignDate2, String dataAuthority);
	
	MsgPage getExamineContracts(RiskControlContract entity, Integer curPageNum, Integer pageSize, String contractSignDate2, String dataAuthority);

	Integer saveContract(RiskControlContract entity);
	
	void updateContract(RiskControlContract entity);

	void deleteContract(Integer id);

	RiskControlContract getContract(Integer id);
	
	RiskControlContract getContract(RiskControlContract entity);
	
	void saveContractExamine(Integer entityId, String examStr, Integer examResult, HhUsers user);
}
