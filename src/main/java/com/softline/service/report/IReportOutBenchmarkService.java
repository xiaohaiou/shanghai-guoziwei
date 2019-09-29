package com.softline.service.report;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportOutBenchmark;
import com.softline.entity.ReportOutCompany;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author yxk
 *
 */
public interface IReportOutBenchmarkService{

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	MsgPage getReportOutBenchmarkListView(ReportOutBenchmark entity,Integer pageNum, Integer pagesize);

	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	ReportOutBenchmark getReportOutBenchmar(Integer id);

	/**
	 * 维护
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	CommitResult saveReportOutBenchmark(ReportOutBenchmark entity);

	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	CommitResult saveReportOutCompany(ReportOutCompany reportOutCompany,HhUsers use);

	/**
	 * 查询外部对标公司数据
	 * @param entity
	 * @param str 
	 * @param use
	 * @return
	 */
	MsgPage getReportOutCompanyListView(ReportOutCompany entity,Integer pageNum, String str, Integer pagesize);

	/**
	 *查看具体信息
	 * @param entity
	 * @param use
	 * @return
	 */
	ReportOutCompany getReportOutCompany(Integer id);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportOutCompany(Integer id, HhUsers use);

	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	CommitResult saveReportOutCompanyExamine(Integer entityid, String examStr,Integer examResult, HhUsers use);


	List<ReportOutBenchmark> getReportOutBenchmarkListView(String str);




}
