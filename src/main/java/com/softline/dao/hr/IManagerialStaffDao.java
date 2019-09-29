package com.softline.dao.hr;

import java.util.List;

import com.softline.entity.DataHrManagerialStaff;

public interface IManagerialStaffDao {
	

	
	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getHrFormsListViewCount(DataHrManagerialStaff entity);
	
	public Integer getExamineHrFormsListViewCount(DataHrManagerialStaff entity);
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<DataHrManagerialStaff> getHrFormsListView(DataHrManagerialStaff entity,Integer offset,Integer pageSize);
	
	public List<DataHrManagerialStaff> getExamineHrFormsListView(DataHrManagerialStaff entity,Integer offset,Integer pageSize);
	
	/**
	 * id查询
	 */
	public DataHrManagerialStaff get(Integer id);
	
	public boolean get(DataHrManagerialStaff entity);
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveDataHrManagerialStaffRepeatCheck(DataHrManagerialStaff entity);
	
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean repeatDataHrManagerialStaffCheck(DataHrManagerialStaff entity);
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean repeatDataCheck(DataHrManagerialStaff entity);

	public List getOverviewIndustry(String year, String month);
}

