package com.softline.dao.hr;

import java.util.List;

import com.softline.entityTemp.LaborcostResourcesreWards;

public interface ILaborCostDao {
	

	
	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getHrFormsListViewCount(LaborcostResourcesreWards entity);
	
	public Integer getExamineHrFormsListViewCount(LaborcostResourcesreWards entity);
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<LaborcostResourcesreWards> getHrFormsListView(LaborcostResourcesreWards entity,Integer offset,Integer pageSize);
	
	public List<LaborcostResourcesreWards> getExamineHrFormsListView(LaborcostResourcesreWards entity,Integer offset,Integer pageSize);
	
	/**
	 * id查询
	 */
	public LaborcostResourcesreWards get(Integer id);
	
	public boolean get(LaborcostResourcesreWards entity);
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveLaborcostResourcesreWardsCheck(LaborcostResourcesreWards entity);
	/**
	 * 保存上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean reportLaborcostResourcesreWardsCheck(LaborcostResourcesreWards entity);
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean reportDataCheck(LaborcostResourcesreWards entity);

	public List getOverviewIndustry(String year, String month);
}

