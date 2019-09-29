package com.softline.service.report;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.taxTask.DataTaxPay;
import com.softline.entity.taxTask.DataTaxPayDetailNow;
import com.softline.entity.taxTask.DataTaxPayDetailPrev;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

/**
 * 
 * @author zy
 * 
 */
public interface ITaxPayService {

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
	public MsgPage getTaxPayListView(DataTaxPay entity, Integer curPageNum,
			Integer pageSize, Integer fun);

	/**
	 * 单个ID查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataTaxPay getTaxPay(Integer id);

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
	public CommitResult saveTaxPay(DataTaxPay entity,
			List<List<Object>> listOb, HhUsers use, String isConfirm);

	/**
	 * 删除
	 * 
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteTaxPay(Integer id, HhUsers use);

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
	public CommitResult saveTaxPayExamine(Integer entityid, String examStr,
			Integer examResult, HhUsers use);
	
	
	/**
	 * 查询汇总数据
	 * @param headCount
	 * @return 
	 */
	public List<DataTaxPay> savefindCollectList(DataTaxPay payCount);
	public void savecollect(DataTaxPay entity);
	
	/**
	 * 保存汇总信息
	 */
	public CommitResult saveCollectList(DataTaxPay payCount,HhUsers use,String op);

	public Object saveReportCollectList(DataTaxPay entity, HhUsers use,
			String string);

	public void saveself(DataTaxPay entity);

	public int getStatus(Integer id);

	public List<DataTaxPayDetailNow> getDetailNow(DataTaxPay entityview,Integer id);

	public List<DataTaxPayDetailPrev> getDetailPre(DataTaxPay entityview,Integer id);

	public List<DataTaxPay> getTaxSaveId(DataTaxPay entity);

	public List<DataTaxPay> savefindCollectList2(DataTaxPay entity);

	public void savecollect2(DataTaxPay entity);
	/**
	 * 税务没有填报的公司
	 * @param reportTime 填报时间
	 * @return
	 */
	public MsgPage getTaxNoCreateCompanyList(String reportTime, String authdata,String org, Integer formKind, Integer CompanyKind,
			Integer curPageNum, Integer pageSize);

	/**
	 * 获取核算未创建公司列表
	 * @param reportTime  上报时间
	 * @param authdata  数据权限
	 * @param org    公司ID
	 * @return
	 */
	public List getTaxNoCreateCompanyList(String reportTime, String str,
			String organalID);
	
	public int getSumDataInfo(DataTaxPay beanIn,DataTaxPay beanOut);
	
	/**
	 * @param reportTime
	 * @param authdata
	 * @param org
	 * @param formKind
	 * @param CompanyKind
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 * 	指令系统提醒页面
	 */
	public MsgPage getTaxNoRemindCompanyList(String reportTime, String authdata,
			String org, Integer formKind, Integer CompanyKind,
			Integer curPageNum, Integer pageSize,String isShowAll);

	public List<DataTaxPay> getSumDataList(DataTaxPay entity);
}
