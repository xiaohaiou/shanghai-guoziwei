package com.softline.dao.report;

import java.util.List;

import com.softline.entity.DataTaxSave;
import com.softline.entity.DataTaxSaveDetail;

/**
 * 
 * @author zy
 * 
 */
public interface ITaxSaveDao {

	/**
	 * 单个主键查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataTaxSave getTaxSave(Integer id);
	

	/**
	 * 一览查询画面检索
	 * 
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @param fun
	 * @return
	 */
	public List<DataTaxSave> getTaxSaveList(DataTaxSave entity,
			Integer offsize, Integer pagesize, Integer fun);

	/**
	 * 数据件数查询
	 * 
	 * @param entity
	 * @param fun
	 * @return
	 */
	public int getTaxSaveListCount(DataTaxSave entity, Integer fun);

	/**
	 * 保存时校验重复的方法
	 * 
	 * @param entity
	 * @return
	 */
	public boolean saveTaxSaveRepeatCheck(DataTaxSave entity);

	/**
	 * 保存时检查数据是否被能修改
	 * 
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(DataTaxSave entity);
	


	/**
	 * 汇总
	 */
	public List<DataTaxSave> getHrFormsListCollectView(DataTaxSave dts);
	
	public List<DataTaxSave> getHrFormsListCollectView2(DataTaxSave entity);
	
	/**
	 * 汇总新增数据
	 */
	public List<DataTaxSaveDetail> getnewListCollect(DataTaxSave dts);
	
   /**
    * 判断是否为虚拟公司
    * @param entity
    * @return
    */
	public boolean isvirtual(DataTaxSave entity);

    /**
     * 
     * @param id
     * @return
     */
	public int getStatus(Integer id);

	public List<DataTaxSaveDetail> getDetail(DataTaxSave entityview, Integer id);

	public List<DataTaxSaveDetail> getnewListCollect2(DataTaxSave entity);

	public List<DataTaxSave> getTaxSaveId(DataTaxSave entity);

	public List<DataTaxSave> getDataTaxSaveSonBeanSumData(DataTaxSave beanIn);
	
	public String getAccTaxSave(DataTaxSave dataTaxSave);

	public boolean IsExitsTaxTask(DataTaxSave entity);


	public List<DataTaxSave> getSumDataList(DataTaxSave entity);
}
