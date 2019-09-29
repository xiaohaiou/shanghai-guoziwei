package com.softline.dao.riskcontrol;

import java.util.List;
import com.softline.entity.RiskControlContract;

public interface IRiskControlContractDao {
	
	Integer getContractListCount(RiskControlContract entity, String contractSignDate2, String dataAuthority);
	
	Integer getExamineContractListCount(RiskControlContract entity, String contractSignDate2, String dataAuthority);
	
	List<RiskControlContract> getContractList(RiskControlContract entity, Integer offset, Integer pageSize, String contractSignDate2, String dataAuthority);
	
	List<RiskControlContract> getExamineContractList(RiskControlContract entity, Integer offset, Integer pageSize, String contractSignDate2, String dataAuthority);

	Integer saveContract(RiskControlContract entity);
	
    void updateContract(RiskControlContract entity);
	
    void deleteContract(Integer id);

	RiskControlContract getContractById(Integer id);
	
	RiskControlContract getContract(RiskControlContract entity);
}
