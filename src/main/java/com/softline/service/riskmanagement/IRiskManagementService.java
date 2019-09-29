/*package com.softline.service.riskmanagement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.softline.client.sso.SsoCheckResult;
import com.softline.common.Base;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhUsers;
import com.softline.entity.RiskManagement;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.RiskManagementView;
import com.softline.util.MsgPage;
*//**
 * 
 * @author tch
 *
 *//*
public interface IRiskManagementService {

	*//**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 *//*
	public MsgPage getRiskManagementListView(RiskManagement entity, Integer curPageNum, Integer pageSize);
	
	*//**
	 * 查询
	 * @param id 查询ID
	 * @return
	 *//*
	public RiskManagement getRiskManagement(Integer id);
	
	
	*//**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 *//*
	public CommitResult saveRiskManagement(RiskManagement entity,HhUsers use);

	*//**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 *//*
	public CommitResult deleteRiskManagement(Integer id,HhUsers use);
	
	*//**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 *//*
	public CommitResult saveRiskManagementExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
}
*/