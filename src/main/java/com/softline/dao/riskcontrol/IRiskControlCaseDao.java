package com.softline.dao.riskcontrol;

import java.util.List;

import com.softline.entity.RiskControlCase;

public interface IRiskControlCaseDao {
	
	Integer getCaseListCount(RiskControlCase entity, String caseHappenDate2, String dataAuthority);
	
	Integer getExamineCaseListCount(RiskControlCase entity, String caseHappenDate2, String dataAuthority);
	
	List<RiskControlCase> getCaseList(RiskControlCase entity, Integer offset, Integer pageSize, String caseHappenDate2, String dataAuthority);
	
	List<RiskControlCase> getExamineCaseList(RiskControlCase entity, Integer offset, Integer pageSize, String caseHappenDate2, String dataAuthority);

	Integer saveCase(RiskControlCase entity);
	
    void updateCase(RiskControlCase entity);
	
    void deleteCase(Integer id);

	RiskControlCase getCaseById(Integer id);
	
	RiskControlCase getCase(RiskControlCase entity);
}
