package com.softline.service.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportCashflowWeeklyExecute;
import com.softline.entity.moneyFlow.DataMoneyFlowMonth;
import com.softline.entity.moneyFlow.DataMoneyFlowWeek;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IMoneyFlowPlanWeekService {

	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public MsgPage findPageList(DataMoneyFlowWeek entity, Integer curPageNum, Integer pageSize);
	
	public MsgPage findExaminePageList(DataMoneyFlowWeek entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public DataMoneyFlowWeek get(Integer id);
	
	public boolean get(DataMoneyFlowWeek entity);
	
	/**
	 * 修改
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult updateMoneyFlowPlanWeek(DataMoneyFlowWeek entity,HhUsers use,Integer i);
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveMoneyFlowPlanWeek(DataMoneyFlowWeek entity,HhUsers use,String op);
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteMoneyFlowPlanWeek(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  人资ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveMoneyFlowPlanWeekExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
	/**
	 * 获取月计划
	 */
	public LinkedHashMap<String,List<LinkedHashMap<String, Object>>> getMonthPlan(String org,String weekStart,String weekEnd);

	public HhOrganInfoTreeRelation getParentCompany(String organalID);
	
	public HhOrganInfoTreeRelation getCompanyRelation(String organalID);
	
	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	public MsgPage getDataMoneyFlowWeekForSumData(DataMoneyFlowWeek entity ,Integer curPageNum, Integer pageSize,DataMoneyFlowWeek backTempBean);
	
	public int sumForAllChildrenEntity(DataMoneyFlowWeek entityIn,DataMoneyFlowWeek entityOut,LinkedHashMap<String,List<LinkedHashMap<String, Object>>> MonthPlanData);

	public List<DataMoneyFlowWeek> findPageList(DataMoneyFlowWeek entity);
	
}
