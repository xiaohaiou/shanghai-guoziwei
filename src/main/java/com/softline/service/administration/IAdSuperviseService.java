package com.softline.service.administration;

import java.util.List;

import com.softline.entity.AdSupervise;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

public interface IAdSuperviseService {

//	MsgPage getSuperviseView(AdSupervise entity, Integer curPageNum, Integer pageSize, Integer fun);

	AdSupervise getSupervise(AdSupervise entity);

	CommitResult saveOrUpdateSupervise(AdSupervise entity, HhUsers use);

	CommitResult deleteSupervise(AdSupervise entity, HhUsers use);

	CommitResult saveSuperviseExamine(AdSupervise entity, String examStr,
			Integer examResult, HhUsers use);

	AdSupervise getSupervise(Integer id);
	
	public List<AdSupervise> getSuperviseList(String year, String month);
	
	CommitResult savesynISupervise();

	MsgPage getSuperviseView(AdSupervise entity, Integer pageNum,
			Integer pagesize, Integer funSearch, String audiorDateStart,
			String audiorDateEnd, String reportDateStart, String reportDateEnd);

	MsgPage getSuperviseView(AdSupervise entity, Integer pageNum,
			Integer pagesize, Integer funSearch, String audiorDateStart,
			String audiorDateEnd, String reportDateStart, String reportDateEnd,String isAllCompany);

	
}
