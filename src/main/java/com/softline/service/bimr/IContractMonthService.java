package com.softline.service.bimr;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrContractMonth;
import com.softline.entity.bimr.BimrContractMonthList;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

public interface IContractMonthService {
	
	public MsgPage GetContractMonths(BimrContractMonth qryParams, Integer curPageNum, Integer pageSize);

	public MsgPage GetContractMonthsList(Integer contractMonthId,Integer curPageNum,
			Integer pageSize);
	//合同导出查询
	public List<BimrContractMonth> getContractMonthListExport(
				BimrContractMonth entity);
		
	public List<BimrContractMonthList> getBimrContractMonthListExport(
			Integer contractMonthId);
	CommitResult ImportData(InputStream inStream, String compId,Integer year, Integer month,Integer status,String compName ,HhUsers user);
	
	public BimrContractMonth GetBimrContractMonthById(Integer bid);
	
	public Boolean DelBimrContractMonthById(Integer bid,HhUsers user);
	
	public CommitResult updateStatus(BimrContractMonth entity, HhUsers use);
	
	public MsgPage getContractMonthListView(BimrContractMonthList entity,
			Integer curPageNum, Integer pageSize);
	
	public BimrContractMonthList GetBimrContractMonthListById(Integer id);
	
	/**
	 * 提交审核
	 * 
	 * @param id   提交审核财务编号
	 * @param user 提交用户
	 * @return {@link CommitResult}
	 */
	 CommitResult saveAuditSubmit(Integer id,  HhUsers user);
	 
	/**
	 * 保存审核
	 * 
	 * @param id  提交审核财务编号
	 * @param content 审核内容
	 * @param isPass true:审核通过
	 * @param user 审核用户
	 * @return {@link CommitResult}
	 */
	CommitResult saveAudit(Integer id, String content, boolean isPass, HhUsers user);
	
	
	XSSFWorkbook getExportWorkBook(List<BimrContractMonthList> list);
}
