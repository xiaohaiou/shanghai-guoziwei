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
import com.softline.entity.ReportPersonalloanNearweek;
import com.softline.entity.ReportPersonalloanNearweekDetail;
import com.softline.entity.ReportPersonalloanNew;
import com.softline.entity.ReportPersonlloaninfo;
import com.softline.entity.ReportPersonlloaninfoNew;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author lcc
 *
 */
public interface IReportPersonalLoanNewService {
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportPersonalloanNew getPersonalLoanNewbyID(Integer id);
	
	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getPersonalloanNewListView(ReportPersonalloanNew entity, Integer curPageNum, Integer pageSize,String weekStart,String weekEnd);
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public CommitResult savePersonalloanNew(ReportPersonalloanNew entity,HhUsers use,MultipartFile itemfile) throws FileNotFoundException, IOException;
	
	
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deletePersonalloanNew(Integer id,HhUsers use);
	
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult savePersonalloanNewExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
	
	/**
	 * 
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public MsgPage getPersonalloanInfoNewListView(ReportPersonalloanNew entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 获取个人借款详细信息
	 * @param entity 查询条件
	 * @return
	 */
	public List<ReportPersonlloaninfoNew> getPersonlloaninfoNewList(ReportPersonlloaninfoNew entity,String weekStart,String weekEnd);
	
	
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
	public MsgPage getPersonalloanNearWeekDetailList(ReportPersonalloanNearweek entity, Integer curPageNum, Integer pageSize);
	
	public List<ReportPersonalloanNearweek> getPersonalloanNearWeekList(ReportPersonalloanNearweek entity);
	
	/**
	 * 
	 * @Description 查询相邻月份差异比较 返回list
	 * @author S.H.T
	 * @date 2018-5-25上午10:45:49
	 * @param entity
	 * @return
	 */
	public List<ReportPersonalloanNearweekDetail> getPersonalloanNearWeekDetailList(ReportPersonalloanNearweek entity);
	
	

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
	public MsgPage getPersonnalovertimeDetail(ReportPersonlloaninfoNew entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询个人借款超期信息记录
	 *
	 * @author S.H.T
	 * @date 2018-5-25下午1:54:28
	 * @param entity
	 * @return
	 */
	public List<ReportPersonlloaninfoNew> getPersonnalovertimeDetail(ReportPersonlloaninfoNew entity);
	
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
	public MsgPage getcoreComovertimeDetail(ReportPersonlloaninfoNew entity, Integer curPageNum, Integer pageSize,String yearStart,String yearEnd);
	
	/**
	 * 获取汇总超期信息
	 *
	 * @author S.H.T
	 * @date 2018-5-25下午2:14:59
	 * @param entity
	 * @return
	 */
	public List<ReportPersonalloanNearweek> getcoreComovertimeDetail(ReportPersonlloaninfoNew entity,String yearStart,String yearEnd);
}
