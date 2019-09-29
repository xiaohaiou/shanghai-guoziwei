package com.softline.dao.report;

import java.util.List;

import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.fundPosition.DataFundPosition;
import com.softline.util.MsgPage;
/**
 * 
 * @author ky_tian
 *
 */
public interface IReportFundPositionDao {
	
	
	/**
	 * 根据查询实体，获取一个报表列表的数目
	 * @param entity 查询实体
	 * @return
	 */
	public Integer getHrFormsListViewCount(DataFundPosition entity);
	
	public Integer getExamineHrFormsListViewCount(DataFundPosition entity);
	
	/**
	 * 根据查询实体，获取一个报表列表
	 * @param entity 查询实体
	 * @return
	 */
	public List<DataFundPosition> getHrFormsListView(DataFundPosition entity,Integer offset,Integer pageSize);
	
	public List<DataFundPosition> getExamineHrFormsListView(DataFundPosition entity,Integer offset,Integer pageSize);
	
	/**
	 * id查询
	 */
	public DataFundPosition get(Integer id);
	
	public boolean get(DataFundPosition entity);
	
	/**
	 * 获取近日头寸
	 */
	public DataFundPosition getCash(String org);
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportFundPositionCheck(DataFundPosition entity);
	
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean reportFundPositionCheck(DataFundPosition entity);
	
	/**
	 *子公司数据汇总
	 */
	public Integer getAllchildernCompanyData(DataFundPosition entity,HhOrganInfoTreeRelation node);
	
	public List<DataFundPosition> findAllchildernCompany(DataFundPosition entity,HhOrganInfoTreeRelation node,Integer offset,Integer pageSize);
}
