package com.softline.dao.administration;

import java.util.List;

import com.softline.entity.AdSupervise;

public interface IAdSuperviseDao {

	Integer getSuperviseListCount(AdSupervise entity, Integer fun,String audiorDateStart,
			String audiorDateEnd,String reportDateStart,String reportDateEnd);

	List<AdSupervise> getSuperviseList(AdSupervise entity, Integer offset,
			Integer pageSize, Integer fun, String audiorDateStart,
			String audiorDateEnd,String reportDateStart,String reportDateEnd);

	AdSupervise getSupervise(AdSupervise entity);

	boolean saveSuperviseCheck(AdSupervise entity);

	AdSupervise getSupervise(Integer id);

	boolean theTimeSuperviseCheck(AdSupervise entity);
	
	public List<String> getSuperviseList(String year, String month);

	public List<AdSupervise> getSuperviseExistList(String year);
}
