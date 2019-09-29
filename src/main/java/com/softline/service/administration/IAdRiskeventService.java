package com.softline.service.administration;

import com.softline.entity.AdRiskevent;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

public interface IAdRiskeventService {

	MsgPage getRiskeventView(AdRiskevent entity, Integer curPageNum, Integer pageSize, Integer fun);
	
	public MsgPage getRiskeventView(AdRiskevent entity, Integer curPageNum,
			Integer pageSize, Integer fun,String isAllCompany);

	AdRiskevent getRiskevent(AdRiskevent entity);

	CommitResult saveOrUpdateRiskevent(AdRiskevent entity, HhUsers use);

	CommitResult deleteRiskevent(AdRiskevent entity, HhUsers use);

	AdRiskevent getRiskevent(Integer id);

	CommitResult saveRiskeventExamine(AdRiskevent entity, String examStr,
			Integer examResult, HhUsers use);

	HhOrganInfo getCoreComp(String riskCompId);

}
