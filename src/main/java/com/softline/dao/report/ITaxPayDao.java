package com.softline.dao.report;

import java.util.List;

import com.softline.entity.taxTask.DataTaxPay;
import com.softline.entity.taxTask.DataTaxPayDetailNow;
import com.softline.entity.taxTask.DataTaxPayDetailPrev;

/**
 * 
 * @author zy
 * 
 */
public interface ITaxPayDao {

	/**
	 * 单个主键查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataTaxPay getTaxPay(Integer id);

	/**
	 * 一览查询画面检索
	 * 
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @param fun
	 * @return
	 */
	public List<DataTaxPay> getTaxPayList(DataTaxPay entity,
			Integer offsize, Integer pagesize, Integer fun);

	/**
	 * 数据件数查询
	 * 
	 * @param entity
	 * @param fun
	 * @return
	 */
	public int getTaxPayListCount(DataTaxPay entity, Integer fun);

	/**
	 * 保存时校验重复的方法
	 * 
	 * @param entity
	 * @return
	 */
	public boolean saveTaxPayRepeatCheck(DataTaxPay entity);

	/**
	 * 保存时检查数据是否被能修改
	 * 
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(DataTaxPay entity);
	
	/**
	 * 汇总
	 */
	public List<DataTaxPay> getHrFormsListCollectView(DataTaxPay dtp);
	
	/**
	 * 汇总新增数据
	 */
	public List<DataTaxPayDetailNow> getnewListNow(DataTaxPay dtp,Integer newId);
	public List<DataTaxPayDetailPrev> getnewListPre(DataTaxPay dtp,Integer newId);

	public int getNowId(DataTaxPay entity);

	public int getPrveId(DataTaxPay entity);

	public int getStatus(Integer id);

	public List<DataTaxPayDetailNow> getDetailNow(DataTaxPay entityview,Integer id);

	public List<DataTaxPayDetailPrev> getDetailPre(DataTaxPay entityview,Integer id);

	public List<DataTaxPay> getTaxSaveId(DataTaxPay entity);

	public List<DataTaxPay> getHrFormsListCollectView2(DataTaxPay entity);

	public List<DataTaxPayDetailNow> getnewListNow2(DataTaxPay entity);

	public List<DataTaxPayDetailPrev> getnewListPre2(DataTaxPay entity);

	public boolean isvirtual(DataTaxPay entity);

	public List<Object[]> getTaxNoCreateCompanyList(String reportTime,
			String authdata, String org, Integer formKind, Integer CompanyKind,Integer offset,Integer pageSize);
	
	public boolean isTopCompany(String nNodeID);
	
	public List<DataTaxPay> getAllTaxPayList(DataTaxPay entity, Integer offsize,
			Integer pagesize, Integer fun);
	
	public int getAllTaxPayListCount(DataTaxPay entity, Integer fun);
	
	/**
	 * @param reportTime
	 * @param authdata
	 * @param org
	 * @param formKind
	 * @param CompanyKind
	 * @param offset
	 * @param pageSize
	 * @return
	 * 	未填报公司，指令系统任务提醒
	 */
	public List<Object[]> getTaxNoRemindCompanyList(String reportTime,
			String authdata, String org, Integer formKind, Integer CompanyKind,
			Integer offset, Integer pageSize);

	public List<DataTaxPay> getSumDataLists(DataTaxPay entity);
	
	
}
