package com.softline.service.bimr;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrRiskCatalog;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

/**
 * 风险目录管理
 * 
 * @author liu
 */
public interface IRiskCatalogService {

	MsgPage getRiskCatalogListView(String name, Integer status, Integer curPageNum, Integer pageSize);
	
	BimrRiskCatalog getRiskCatalog(Integer id);
	
	List<BimrRiskCatalog> getRiskCatalogChildren(Integer parentId);
	
	CommitResult saveRiskCatalog(BimrRiskCatalog t, Boolean isSubmitAudit, HhUsers user);
	
	CommitResult submitAudit(Integer id, HhUsers user);
	
	CommitResult delete(Integer id, HhUsers user);
	
	MsgPage getRiskCatalogAudidtListView(String name, Integer status, String define, Integer curPageNum, Integer pageSize);
	
	CommitResult audit(Integer id, String content, Integer isPass, HhUsers user);
}
