package com.softline.dao.bimr;

import java.util.List;

import com.softline.entity.bimr.BimrRiskCatalogScore;

/**
 * 二级风险目录评分数据操作
 * 
 * @author liu
 *
 */
public interface IRiskCatalogScoreDao {

	/**
	 * 查询已经评分二级风险目录评分
	 * 
	 * @param year   年份 
	 * @param month  月份
	 * @param coreOrg 核心企业ID 2018-08-10 添加
	 * @return
	 */
	List<BimrRiskCatalogScore> getRiskCatalogScoreList(Integer year, Integer month,String coreOrg);
	
	/**
	 * 查询需要评分二级目录
	 * 
	 * @param year   年份
	 * @param month  月份
	 * @param coreOrg 核心企业ID 包括海航物流  2018-08-10 添加
	 * @return 返回值 object[0]: 目录编号, object[1]: 目录名, object[2]: 事件个数
	 */
	List<Object[]> getWillScoreRiskCatalogList(Integer year, Integer month,String coreOrg,String coreOrgName);
}
