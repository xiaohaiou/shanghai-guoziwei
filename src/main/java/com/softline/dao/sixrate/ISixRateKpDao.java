package com.softline.dao.sixrate;

import java.util.List;

import com.softline.entity.DataSixRateKp;

public interface ISixRateKpDao {
	

	
	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getHrFormsListViewCount(DataSixRateKp entity);
	
	public Integer getExamineHrFormsListViewCount(DataSixRateKp entity);
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<DataSixRateKp> getHrFormsListView(DataSixRateKp entity,Integer offset,Integer pageSize);
	
	public List<DataSixRateKp> getExamineHrFormsListView(DataSixRateKp entity,Integer offset,Integer pageSize);
	
	/**
	 * id查询
	 */
	public DataSixRateKp get(Integer id);
	
	public boolean get(DataSixRateKp entity);
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveDataSixRateKpCheck(DataSixRateKp entity);
	
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean repeatDataSixRateKpCheck(DataSixRateKp entity);
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean repeatDataCheck(DataSixRateKp entity);
}

