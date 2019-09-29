

package com.softline.dao.hr;

import java.util.List;

import com.softline.entityTemp.HeadCountLaborProduction;

public interface IHeadCountDao {
	

	
	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getHrFormsListViewCount(HeadCountLaborProduction hr);
	
	public Integer getExamineHrFormsListViewCount(HeadCountLaborProduction hr);
	
	
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<HeadCountLaborProduction> getHrFormsListView(HeadCountLaborProduction hr,Integer offset,Integer pageSize);
	
	public List<HeadCountLaborProduction> getHrFormsListCollectView(HeadCountLaborProduction hr);
	
	
	public List<HeadCountLaborProduction> getExamineHrFormsListView(HeadCountLaborProduction hr,Integer offset,Integer pageSize);
	/**
	 * id查询
	 */
	public HeadCountLaborProduction get(Integer id);
	
	public boolean get(HeadCountLaborProduction entity);
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveHeadCountLaborProductionRepeatCheck(HeadCountLaborProduction hr);
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean reporHeadCountLaborProductionRepeatCheck(HeadCountLaborProduction hr);
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean repeatDataCheck(HeadCountLaborProduction hr);

	public List getOverviewIndustry(String year,
			String month);
}
