package com.softline.service.riskcontrol;

import com.softline.entity.RiskControlCase;
import com.softline.entity.HhUsers;
import com.softline.util.MsgPage;

public interface IRiskControlCaseService {

	MsgPage getCases(RiskControlCase entity, Integer curPageNum, Integer pageSize, String caseHappenDate2, String dataAuthority);
	
	MsgPage getExamineCases(RiskControlCase entity, Integer curPageNum, Integer pageSize, String caseHappenDate2, String dataAuthority);

	Integer saveCase(RiskControlCase entity);
	
	void updateCase(RiskControlCase entity);

	void deleteCase(Integer id);

	RiskControlCase getCase(Integer id);
	
	RiskControlCase getCase(RiskControlCase entity);
	
	void saveCaseExamine(Integer entityId, String examStr, Integer examResult, HhUsers user);
}
