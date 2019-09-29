package com.softline.service.report;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportReceivabledebt;
import com.softline.entity.ReportReceivabledebtinfo;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;


public interface IReceivableDebtService {
	
	/**
	 * 通过ID查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportReceivabledebt getReceivabledebtbyID(Integer id);
	
	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReceivabledebtListView(ReportReceivabledebt entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public CommitResult saveReceivabledebt(ReportReceivabledebt entity,HhUsers use,MultipartFile itemfile) throws FileNotFoundException, IOException;
	
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReceivabledebt(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveReceivabledebtExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
	
	/**
	 * 
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public MsgPage getReceivabledebtInfoListView(ReportReceivabledebt entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 *   --------------------------------应收债权(内部)明细查询---------------------------------------
	 */
	/**
	 * 查询应收债权(内部)明细
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReceivabledebtinfoListView(ReportReceivabledebtinfo entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 * 通过ID查询明细
	 * @param id 查询ID
	 * @return
	 */
	public ReportReceivabledebtinfo getReceivabledebtinfobyID(Integer id);
	
	/**
	 *   --------------------------------应收债权(内部)汇总查询---------------------------------------
	 */
	
	/**
	 * 查询应收债权(内部)汇总
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReceivabledebtinfoCollectList(ReportReceivabledebtinfo entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 *   --------------------------------公司大额应收债权(内部)查询---------------------------------------
	 */
	
	/**
	 * 查询公司大额应收债权(内部)
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReceivabledebtinfoOrgList(ReportReceivabledebtinfo entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 *   --------------------------------超期外部应收账款无催收进展一览---------------------------------------
	 */
	
	
	/**
	 * 查询超期外部应收账款无催收进展一览
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getOverOutList(ReportReceivabledebtinfo entity, Integer curPageNum, Integer pageSize);
	
}
