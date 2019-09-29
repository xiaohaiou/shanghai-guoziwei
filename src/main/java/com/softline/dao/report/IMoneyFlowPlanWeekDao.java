package com.softline.dao.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.moneyFlow.DataMoneyFlowMonth;
import com.softline.entity.moneyFlow.DataMoneyFlowWeek;

/**
 * 
 * @author ky_tian
 *
 */
public interface IMoneyFlowPlanWeekDao {
	
	
	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getHrFormsListViewCount(DataMoneyFlowWeek entity);
	
	public Integer getExamineHrFormsListViewCount(DataMoneyFlowWeek entity);
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<DataMoneyFlowWeek> getHrFormsListView(DataMoneyFlowWeek entity,Integer offset,Integer pageSize);
	
	public List<DataMoneyFlowWeek> getExamineHrFormsListView(DataMoneyFlowWeek entity,Integer offset,Integer pageSize);
	
	/**
	 * id查询
	 */
	public DataMoneyFlowWeek get(Integer id);
	
	public boolean get(DataMoneyFlowWeek entity);
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveMoneyFlowPlanWeekCheck(DataMoneyFlowWeek entity);
	
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean moneyFlowPlanWeekCheck(DataMoneyFlowWeek entity);
	
	/**
	 * 获取月计划
	 */
	public LinkedHashMap<String,List<LinkedHashMap<String, Object>>> getMonthPlan(String org,String weekStart,String weekEnd);
	
	public HhOrganInfoTreeRelation getCoreCompId(String organalID, int type);

	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	public List<DataMoneyFlowWeek> getDataMoneyFlowWeekForSumData(DataMoneyFlowWeek entity ,Integer offsize,Integer pagesize);

	public List<DataMoneyFlowWeek> getSumChildrenData(HhOrganInfoTreeRelation info,DataMoneyFlowWeek entityIn);
	
	public LinkedHashMap<String,List<LinkedHashMap<String, Object>>> getMonthPlanForDetialInfo(Integer pid);

	public List<DataMoneyFlowWeek> findPageList(DataMoneyFlowWeek entity);
}
