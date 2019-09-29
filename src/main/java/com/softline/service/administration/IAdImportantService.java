package com.softline.service.administration;

import com.softline.entity.AdImportant;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

public interface IAdImportantService {

	MsgPage getImportantView(AdImportant entity, Integer curPageNum, Integer pageSize, Integer fun);
	
	public MsgPage getImportantView(AdImportant entity, Integer curPageNum,
			Integer pageSize, Integer fun,String isAllCompany);

	AdImportant getImportant(AdImportant entity);

	CommitResult saveOrUpdateImportant(AdImportant entity, HhUsers use);

	CommitResult deleteImportant(AdImportant entity, HhUsers use);

	AdImportant getImportant(Integer id);

	CommitResult saveImportantExamine(AdImportant entity, String examStr,
			Integer examResult, HhUsers use);

	HhOrganInfo getCoreComp(String importantCompId);

}
