package com.softline.dao.report;

import java.util.List;

import com.softline.entity.taxTask.DataTaxTask;
import com.softline.entity.taxTask.DataTaxTaskDetailFavouredPolicy;
import com.softline.entity.taxTask.DataTaxTaskDetailInTaxReturn;
import com.softline.entity.taxTask.DataTaxTaskDetailTaxPlan;
import com.softline.entity.taxTask.DataTaxTaskDetailTaxReturn;

/**
 * 
 * @author zy
 * 
 */
public interface ITaxTaskDao {

	/**
	 * 单个主键查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataTaxTask getTaxTask(Integer id);

	/**
	 * 一览查询画面检索
	 * 
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @param fun
	 * @return
	 */
	public List<DataTaxTask> getTaxTaskList(DataTaxTask entity,
			Integer offsize, Integer pagesize, Integer fun);

	/**
	 * 数据件数查询
	 * 
	 * @param entity
	 * @param fun
	 * @return
	 */
	public int getTaxTaskListCount(DataTaxTask entity, Integer fun);

	/**
	 * 保存时校验重复的方法
	 * 
	 * @param entity
	 * @return
	 */
	public boolean saveTaxTaskRepeatCheck(DataTaxTask entity);

	/**
	 * 保存时检查数据是否被能修改
	 * 
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(DataTaxTask entity);
	
	/**
	 * 汇总数据修改
	 * @param payCount
	 * @return
	 */

	public List<DataTaxTask> getHrFormsListCollectView(DataTaxTask payCount);

	public List<DataTaxTaskDetailFavouredPolicy> getnewListFav(
			DataTaxTask payCount);

	public List<DataTaxTaskDetailInTaxReturn> getnewListIntax(
			DataTaxTask payCount);

	public List<DataTaxTaskDetailTaxPlan> getnewListPlan(DataTaxTask payCount);

	public List<DataTaxTaskDetailTaxReturn> getnewListReturn(
			DataTaxTask payCount);

	public void saveFav(DataTaxTask entity);

	public void saveInTax(DataTaxTask entity);

	public void savePlan(DataTaxTask entity);

	public void saveReturn(DataTaxTask entity);

	public int getStatus(Integer id);

	public List<DataTaxTaskDetailTaxReturn> getDetailRet(
			DataTaxTask entityview, Integer id);

	public List<DataTaxTaskDetailTaxPlan> getDetailPlan(DataTaxTask entityview,
			Integer id);

	public List<DataTaxTaskDetailFavouredPolicy> getDetailFav(
			DataTaxTask entityview, Integer id);

	public List<DataTaxTaskDetailInTaxReturn> getDetailInt(
			DataTaxTask entityview, Integer id);

	public List<DataTaxTask> getTaxTaskId(DataTaxTask entity);

	public List<DataTaxTask> getHrFormsListCollectView2(DataTaxTask entity);

	public List<DataTaxTaskDetailFavouredPolicy> getnewListFav2(
			DataTaxTask entity);

	public List<DataTaxTaskDetailInTaxReturn> getnewListIntax2(
			DataTaxTask entity);

	public List<DataTaxTaskDetailTaxPlan> getnewListPlan2(DataTaxTask entity);

	public List<DataTaxTaskDetailTaxReturn> getnewListReturn2(DataTaxTask entity);



	
	/**
	 * 获取parentorg
	 * @param entity
	 * @return
	 */
	public String getParentOrg(String org);

	public boolean isvirtual(DataTaxTask entity);

	public List<DataTaxTask> getSumDataList(DataTaxTask entity);

	public List<DataTaxTask> getSumDataInfo(DataTaxTask beanIn);
}
