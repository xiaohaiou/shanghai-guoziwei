package com.softline.dao.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.moneyFlow.DataMoneyFlowMonth;
import com.softline.entity.moneyFlow.DataMoneyFlowWeek;

/**
 * 
 * @author ky_tian
 *
 */
public interface IMoneyFlowPlanMonthDao {
	
	
	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getHrFormsListViewCount(DataMoneyFlowMonth entity);
	
	public Integer getExamineHrFormsListViewCount(DataMoneyFlowMonth entity);
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<DataMoneyFlowMonth> getHrFormsListView(DataMoneyFlowMonth entity,Integer offset,Integer pageSize);
	
	public List<DataMoneyFlowMonth> getExamineHrFormsListView(DataMoneyFlowMonth entity,Integer offset,Integer pageSize);
	
	/**
	 * id查询
	 */
	public DataMoneyFlowMonth get(Integer id);
	
	public boolean get(DataMoneyFlowMonth entity);
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveMoneyFlowPlanMonthCheck(DataMoneyFlowMonth entity);
	
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean moneyFlowPlanMonthCheck(DataMoneyFlowMonth entity);
	
	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	public List<DataMoneyFlowMonth> getDataMoneyFlowMonthForSumData(DataMoneyFlowMonth entity ,Integer offsize,Integer pagesize);
	
	public List<DataMoneyFlowMonth> getSumChildrenData(HhOrganInfoTreeRelationHistory info,DataMoneyFlowMonth entityIn);
	
	public LinkedHashMap<String,List<LinkedHashMap<String, Object>>> getMonthPlanForDetialInfo(Integer pid);
	
	public HhOrganInfoTreeRelation getCoreCompId(String organalID, int type);
	
	public Integer getDataMoneyFlowMonthForSumDataNumber(DataMoneyFlowMonth entity);

	public List<DataMoneyFlowMonth> findPageList(DataMoneyFlowMonth entity);
}
