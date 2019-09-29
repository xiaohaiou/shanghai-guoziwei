package com.softline.service.report;

import java.util.List;

import com.softline.entity.DataTaxSave;
import com.softline.entity.DataTaxSaveDetail;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

/**
 * 
 * @author zy
 * 
 */
public interface ITaxSaveService {

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
	public MsgPage getTaxSaveListView(DataTaxSave entity, Integer curPageNum,
			Integer pageSize, Integer fun);

	/**
	 * 单个ID查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataTaxSave getTaxSave(Integer id);

	/**
	 * 保存更新
	 * 
	 * @param entity 实体
	 * @param listOb 导入数据
	 * @param use 用户信息
	 * @param isConfirm 
	 * @return
	 */
	public CommitResult saveTaxSave(DataTaxSave entity,
			List<List<Object>> listOb, HhUsers use, String isConfirm);

	/**
	 * 删除
	 * 
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteTaxSave(Integer id, HhUsers use);

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
	public CommitResult saveTaxSaveExamine(Integer entityid, String examStr,
			Integer examResult, HhUsers use);
	
	/**
	 * 查询汇总数据
	 * @param headCount
	 * @return 
	 */
	public List<DataTaxSave> savefindCollectList(DataTaxSave payCount);
	
	
	/**
	 * 判断是否为虚拟公司
	 */
	public CommitResult isvirtual(DataTaxSave entity);
	
	
	/**
	 * 保存汇总信息
	 */
	public CommitResult saveCollectList(DataTaxSave payCount,HhUsers use,String op);

	public void savecollect(DataTaxSave entity);

	public Object saveReportCollectList(DataTaxSave entity, HhUsers use,
			String string);

	public void saveself(DataTaxSave entity);

	public int getStatus(Integer id);

	public List<DataTaxSaveDetail> getDetail(DataTaxSave entityview, Integer id);

	public List<DataTaxSave> getTaxSaveId(DataTaxSave entity);

	public List<DataTaxSave> savefindCollectList2(DataTaxSave entity);

	public void savecollect2(DataTaxSave entity);

	public int getSumDataTaxSaveSonBeanData(DataTaxSave beanIn,DataTaxSave beanOut);

	public List<DataTaxSave> getSumDataList(DataTaxSave entity);
	
}
