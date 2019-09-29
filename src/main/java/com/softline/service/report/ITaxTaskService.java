package com.softline.service.report;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.taxTask.DataTaxTask;
import com.softline.entity.taxTask.DataTaxTaskDetailFavouredPolicy;
import com.softline.entity.taxTask.DataTaxTaskDetailInTaxReturn;
import com.softline.entity.taxTask.DataTaxTaskDetailTaxPlan;
import com.softline.entity.taxTask.DataTaxTaskDetailTaxReturn;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

/**
 * 
 * @author zy
 * 
 */
public interface ITaxTaskService {

	/**
	 * 一览画面查询
	 * 
	 * @param entity
	 *            查询实体
	 * @param curPageNum
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param fun
	 *            机能分类
	 * @return
	 */
	public MsgPage getTaxTaskListView(DataTaxTask entity, Integer curPageNum,
			Integer pageSize, Integer fun);

	/**
	 * 单个ID查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataTaxTask getTaxTask(Integer id);

	/**
	 * 保存更新
	 * 
	 * @param entity
	 *            实体
	 * @param listOb
	 *            导入数据
	 * @param use
	 *            用户信息
	 * @param isConfirm 
	 * @return
	 */
	public CommitResult saveTaxTask(DataTaxTask entity,
			List<List<Object>> listOb, HhUsers use, String isConfirm);

	/**
	 * 删除
	 * 
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteTaxTask(Integer id, HhUsers use);

	/**
	 * 审核
	 * 
	 * @param entityid
	 *            采购ID
	 * @param examStr
	 *            审核备注
	 * @param examResult
	 *            审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveTaxTaskExamine(Integer entityid, String examStr,
			Integer examResult, HhUsers use);

	/**
	 * 查询汇总数据
	 * @param headCount
	 * @return 
	 */
	public List<DataTaxTask> savefindCollectList(DataTaxTask entity);

	public void savecollect(DataTaxTask entity);
	
	/**
	 * 汇总保存
	 * @param entity
	 * @param use
	 * @param string
	 * @return
	 */

	public Object saveCollectList(DataTaxTask entity, HhUsers use, String string);

	public Object saveReportCollectList(DataTaxTask entity, HhUsers use,
			String string);

	public void saveself(DataTaxTask entity);

	public int getStatus(Integer id);

	public List<DataTaxTaskDetailInTaxReturn> getDetailInt(
			DataTaxTask entityview, Integer id);

	public List<DataTaxTaskDetailFavouredPolicy> getDetailFav(
			DataTaxTask entityview, Integer id);

	public List<DataTaxTaskDetailTaxPlan> getDetailPlan(DataTaxTask entityview,
			Integer id);

	public List<DataTaxTaskDetailTaxReturn> getDetailRet(
			DataTaxTask entityview, Integer id);

	public List<DataTaxTask> getTaxTaskId(DataTaxTask entity);

	public List<DataTaxTask> savefindCollectList2(DataTaxTask entity);

	public void savecollect2(DataTaxTask entity);

	public String getParentOrg(String org);

	public List<DataTaxTask> getSumDataList(DataTaxTask entity);

	public int getSumDataInfo(DataTaxTask entity,DataTaxTask entityBackInfoBean);





}
