package com.softline.service.bimr.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.bimr.IRiskCatalogDao;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrRiskCatalog;
import com.softline.entityTemp.CommitResult;
import com.softline.service.bimr.IRiskCatalogService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

/**
 * 实现风险目录管理服务
 * 
 * @author liu
 */
@Service("riskCatalogService")
public class RiskCatalogService implements IRiskCatalogService {

	@Autowired
	private IRiskCatalogDao riskCatalogDao;
	
	@Autowired
	@Qualifier("commonService")
	private ICommonService commonService;
	
	@Autowired
	@Qualifier("examineService")
	private IExamineService examineService;	
	
	@Override
	public MsgPage getRiskCatalogListView(String name, Integer status, Integer curPageNum, Integer pageSize) {
		
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = riskCatalogDao.getRiskCatalogListCount(name, status);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<Object[]> list = riskCatalogDao.getRiskCatalogList(name, status, offset, pageSize);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}

	@Override
	public BimrRiskCatalog getRiskCatalog(Integer id) {
		return riskCatalogDao.getRiskCatalogOne(id);
	}

	@Override
	public List<BimrRiskCatalog> getRiskCatalogChildren(Integer parentId) {
		return riskCatalogDao.getRiskCatalogChildren(parentId);
	}

	@Override
	public CommitResult saveRiskCatalog(BimrRiskCatalog t, Boolean isSubmitAudit, HhUsers user) {
		CommitResult result = new CommitResult();
		BimrRiskCatalog p = riskCatalogDao.getRiskCatalogOne(t.getParentId());
		if(p != null){
			result.setResult(false);
			result.setExceptionStr("上级目录不存在");
		}
		if(p.getStatus() == 3){
			result.setResult(false);
			result.setExceptionStr("上级目录还未审核通过");
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowStr = df.format(new Date());
		if(t.getId()==null){
			t.setCreateDate(nowStr);
			t.setCreatePersonId(user.getVcEmployeeId());
			t.setCreatePersonName(user.getVcName());
			t.setRiskId(UUID.randomUUID().toString().replaceAll("-", ""));
			t.setRiskParentId(p.getRiskId());
		}else{
			t.setLastModifyDate(nowStr);
			t.setLastModifyPersonId(user.getVcName());
			t.setLastModifyPersonName(user.getVcEmployeeId());
			if(StringUtils.isBlank(t.getRiskId())){
				t.setRiskId(UUID.randomUUID().toString().replaceAll("-", ""));
			}
			t.setRiskParentId(p.getRiskId());
		}
		t.setStatus(0);
		t.setIsDel(0);
		
		
		commonService.saveOrUpdate(t);
		
		if(!isSubmitAudit){
			result.setEntity(t);
			result.setResult(true);
			return result;
		}
		
		return submitAudit(t.getId(), user);
	}

	@Override
	public CommitResult submitAudit(Integer id, HhUsers user) {
		CommitResult result = new  CommitResult();
		BimrRiskCatalog t = riskCatalogDao.getRiskCatalogOne(id);
		if(t== null){
			result.setResult(false);
			result.setExceptionStr("风险目录不存在");
			return result;
		}
		if(t.getStatus() != 0 && t.getStatus() != 2){
			result.setResult(false);
			result.setExceptionStr("风险目录已经提交");
			return result;
		}
		
		t.setStatus(1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowStr = df.format(new Date());
		t.setSubmitAuditDate(nowStr);
		t.setSubmitAuditPersonId(user.getVcEmployeeId());
		t.setSubmitAuditPersonName(user.getVcName());
		t.setLastModifyDate(nowStr);
		t.setLastModifyPersonId(user.getVcName());
		t.setLastModifyPersonName(user.getVcEmployeeId());
		
		commonService.saveOrUpdate(t);
		
		result.setResult(true);
		result.setEntity(t);
		
		return result;
	}

	@Override
	public CommitResult delete(Integer id, HhUsers user) {
		CommitResult result = new  CommitResult();
		BimrRiskCatalog t = riskCatalogDao.getRiskCatalogOne(id);
		if(t== null){
			result.setResult(true);
			return result;
		}
		
		if(t.getStatus() != 0 && t.getStatus() != 2){
			result.setResult(false);
			result.setExceptionStr("风险目录已经提交审核，不能删除。");
			return result;
		}
		
		t.setIsDel(1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		t.setLastModifyDate(df.format(new Date()));
		t.setLastModifyPersonId(user.getVcName());
		t.setLastModifyPersonName(user.getVcEmployeeId());
		commonService.saveOrUpdate(t);
		
		result.setResult(true);
		
		return result;
	}

	@Override
	public MsgPage getRiskCatalogAudidtListView(String name, Integer status, String define,
			Integer curPageNum, Integer pageSize) {
		
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = riskCatalogDao.getRiskCatalogAuditListCount(name, define, status);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<Object[]> list = riskCatalogDao.getRiskCatalogAuditList(name, define, status, offset, pageSize);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}

	@Override
	public CommitResult audit(Integer id, String content, Integer isPass, HhUsers user) {
		CommitResult result = new  CommitResult();
		BimrRiskCatalog t = riskCatalogDao.getRiskCatalogOne(id);
		if(t== null){
			result.setResult(false);
			result.setExceptionStr("风险目录不存在");
			return result;
		}
		if(t.getStatus() != 1){
			result.setResult(false);
			result.setExceptionStr("已结审核");
			return result;
		}
		
		t.setStatus(isPass == 0 ? 2 : 3);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowStr = df.format(new Date());
		t.setAuditDate(nowStr);
		t.setAuditPersonId(user.getVcEmployeeId());
		t.setAuditPersonName(user.getVcName());
		t.setLastModifyDate(nowStr);
		t.setLastModifyPersonId(user.getVcName());
		t.setLastModifyPersonName(user.getVcEmployeeId());
		
		commonService.saveOrUpdate(t);
		
		examineService.saveExamine(t.getId(), Base.examkind_risk_catalog, user, content, isPass);
		result.setResult(true);
		result.setEntity(t);
		
		return result;
	}
	
}
