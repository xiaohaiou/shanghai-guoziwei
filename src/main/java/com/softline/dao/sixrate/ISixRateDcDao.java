package com.softline.dao.sixrate;

import java.util.List;

import com.softline.entity.DataSixRateDc;

public interface ISixRateDcDao {
	

	
	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getHrFormsListViewCount(DataSixRateDc entity);
	
	public Integer getExamineHrFormsListViewCount(DataSixRateDc entity);
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<DataSixRateDc> getHrFormsListView(DataSixRateDc entity,Integer offset,Integer pageSize);
	
	public List<DataSixRateDc> getExamineHrFormsListView(DataSixRateDc entity,Integer offset,Integer pageSize);
	
	/**
	 * id查询
	 */
	public DataSixRateDc get(Integer id);
	
	public boolean get(DataSixRateDc entity);
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveDataSixRateDcCheck(DataSixRateDc entity);
	
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean repeatDataSixRateDcCheck(DataSixRateDc entity);
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean repeatDataCheck(DataSixRateDc entity);
}

