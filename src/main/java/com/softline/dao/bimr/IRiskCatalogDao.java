package com.softline.dao.bimr;

import java.util.List;

import com.softline.entity.bimr.BimrRiskCatalog;

/**
 * 风险目录数据操作
 * 
 * @author liu
 */
public interface IRiskCatalogDao {

	Integer getRiskCatalogListCount(String name, Integer status);
	
	List<Object[]> getRiskCatalogList(String name, Integer status, Integer offset, Integer pageSize);
	
	BimrRiskCatalog getRiskCatalogOne(Integer id);
	
	List<BimrRiskCatalog> getRiskCatalogChildren(Integer parentId);
	
	Integer getRiskCatalogAuditListCount(String name, String define, Integer status);
	
	List<Object[]> getRiskCatalogAuditList(String name, String define, Integer status, Integer offset, Integer pageSize);
}
