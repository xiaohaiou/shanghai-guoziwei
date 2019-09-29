package com.softline.service.report;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.HrPersonitem;
import com.softline.entity.ReportPersonalloan;
import com.softline.entity.ReportPersonalloanNearMonth;
import com.softline.entity.ReportPersonalloanNearMonthDetail;
import com.softline.entity.ReportPersonlloaninfo;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author lcc
 *
 */
public interface IReportPersonalLoanService {
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportPersonalloan getPersonalLoanbyID(Integer id);
	
	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getPersonalloanListView(ReportPersonalloan entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public CommitResult savePersonalloan(ReportPersonalloan entity,HhUsers use,MultipartFile itemfile) throws FileNotFoundException, IOException;
	
	
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deletePersonalloan(Integer id,HhUsers use);
	
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult savePersonalloanExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
	
	/**
	 * 
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public MsgPage getPersonalloanInfoListView(ReportPersonalloan entity, Integer curPageNum, Integer pageSize);
	
	/**
	 *   --------------------------------公司员工借款相邻月份差异比较查询---------------------------------------
	 */
	
	/**
	 * 查询相邻月份差异比较
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getPersonalloanNearMonthList(ReportPersonalloanNearMonth entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 *   --------------------------------个人超期信息一览---------------------------------------
	 */
	
	
	/**
	 * 获取个人借款超期信息
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public MsgPage getPersonnalovertimeDetail(ReportPersonlloaninfo entity, Integer curPageNum, Integer pageSize);
	
	public ReportPersonlloaninfo getPersonlloaninfobyId(Integer id);
	
	
	/**
	 *   --------------------------------个人超期信息一览---------------------------------------
	 */
	
	/**
	 * 获取汇总超期信息
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public MsgPage getcoreComovertimeDetail(ReportPersonlloaninfo entity, Integer curPageNum, Integer pageSize);
	
}
