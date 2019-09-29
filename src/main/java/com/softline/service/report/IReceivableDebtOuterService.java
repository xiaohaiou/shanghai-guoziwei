package com.softline.service.report;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportReceivabledebt;
import com.softline.entity.ReportReceivabledebtOuter;
import com.softline.entity.ReportReceivabledebtinfo;
import com.softline.entity.ReportReceivabledebtinfoOuter;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;


public interface IReceivableDebtOuterService {
	
	/**
	 * 通过ID查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportReceivabledebtOuter getReceivabledebtbyID(Integer id);
	
	
	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReceivabledebtListView(ReportReceivabledebtOuter entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public CommitResult saveReceivabledebt(ReportReceivabledebtOuter entity,HhUsers use,MultipartFile itemfile) throws FileNotFoundException, IOException;
	
	
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
	public MsgPage getReceivabledebtInfoListView(ReportReceivabledebtOuter entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 *   --------------------------------应收债权(外部)明细查询---------------------------------------
	 */
	/**
	 * 查询应收债权(外部)明细
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReceivabledebtinfoListView(ReportReceivabledebtinfoOuter entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 * 通过ID查询明细
	 * @param id 查询ID
	 * @return
	 */
	public ReportReceivabledebtinfoOuter getReceivabledebtinfobyID(Integer id);
	
	/**
	 *   --------------------------------应收债权(外部)汇总查询---------------------------------------
	 */
	
	/**
	 * 查询应收债权(内部)汇总
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReceivabledebtinfoCollectList(ReportReceivabledebtinfoOuter entity, Integer curPageNum, Integer pageSize);
	
	
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
	public List<ReportReceivabledebtinfoOuter> getReceivabledebtinfoOrgList(ReportReceivabledebtinfoOuter entity, Integer curPageNum, Integer pageSize);
	
	
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
	public MsgPage getOverOutList(ReportReceivabledebtinfoOuter entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 * 导出查询页面数据
	 * @param list
	 * @return
	 */
	public void getExcelData(List<ReportReceivabledebtinfoOuter> list,HttpServletResponse response);

	/**
	 * 查询
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportReceivabledebtinfoOuter> getReportReceivabledebtOuter(ReportReceivabledebtinfoOuter entity);

	/**
	 * 查询
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportReceivabledebtinfoOuter> getReceivabledebtinfoCollectListExport(ReportReceivabledebtinfoOuter entity);

	/**
	 * 查询
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportReceivabledebtinfoOuter> getReceivabledebtinfoOrgExport(ReportReceivabledebtinfoOuter entity);

	/**
	 * 查询
	 * @param entity 查询实体
	 * @return
	 */
	public List<ReportReceivabledebtinfoOuter> getOverOutListExport(ReportReceivabledebtinfoOuter entity);
	
}
