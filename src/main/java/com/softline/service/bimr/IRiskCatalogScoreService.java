package com.softline.service.bimr;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrRiskCatalogScore;
import com.softline.entityTemp.CommitResult;

/**
 * 二级风险目录评分业务服务

 * @author liu
 */
public interface IRiskCatalogScoreService {

	/**
	 * 查询需要评分记录
	 * 
	 * @param year   需要评分的年
	 * @param month  需要评分的月
	 * @return
	 */
	List<BimrRiskCatalogScore> getRiskCatalogScoreList(Integer year, Integer month,String coreOrg,String coreOrgName);

	/**
	 * 批量保存评分记录
	 * 
	 * @param entities 评分记录
	 * @param user     操作用户
	 * @return
	 */
	CommitResult batchSaveAndUpdate(List<BimrRiskCatalogScore> entities, HhUsers user);
	
}
