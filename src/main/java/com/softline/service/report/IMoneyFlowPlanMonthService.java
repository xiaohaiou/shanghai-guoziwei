package com.softline.service.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.moneyFlow.DataMoneyFlowMonth;
import com.softline.entity.moneyFlow.DataMoneyFlowWeek;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IMoneyFlowPlanMonthService {

	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public MsgPage findPageList(DataMoneyFlowMonth entity, Integer curPageNum, Integer pageSize);
	
	public MsgPage findExaminePageList(DataMoneyFlowMonth entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public DataMoneyFlowMonth get(Integer id);
	
	public boolean get(DataMoneyFlowMonth entity);
	
	/**
	 * 修改
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult updateMoneyFlowPlanMonth(DataMoneyFlowMonth entity,HhUsers use,Integer i);
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveMoneyFlowPlanMonth(DataMoneyFlowMonth entity,HhUsers use,String op);
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteMoneyFlowPlanMonth(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  人资ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveMoneyFlowPlanMonthExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	public MsgPage getDataMoneyFlowMonthForSumData(DataMoneyFlowMonth entity ,Integer curPageNum, Integer pageSize,DataMoneyFlowMonth backTempBean);
	
	public int sumForAllChildrenEntity(DataMoneyFlowMonth entityIn,DataMoneyFlowMonth entityOut,LinkedHashMap<String,List<LinkedHashMap<String, Object>>> MonthPlanData);

	public List<DataMoneyFlowMonth> findPageList(DataMoneyFlowMonth entity);
	
}
