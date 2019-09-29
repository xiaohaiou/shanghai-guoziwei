package com.softline.service.report;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.CompanyTree;
import com.softline.entity.HhUsers;
import com.softline.entity.financing.ReportFinancingShare;
import com.softline.entity.financing.ReportFinancingShareEnclosure;
import com.softline.entity.financing.ReportFinancingShareLog;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
public interface IReportFinancingShareService {
	
	/**
	 * 汇总数据查询
	 */
	public List<String> getDataList(ReportFinancingShare entity);
	
	/**
	 * 报表
	 */
	public HSSFSheet setExcelData(HSSFSheet sheet,List<ReportFinancingShare> entity,HSSFCellStyle style,Integer rowNum);
	
	/**
	 * 查询导出数据
	 */
	public HSSFSheet getEntityForOrg(ReportFinancingShare entity,HSSFSheet sheet,HSSFCellStyle style,Integer rowNum);
	
	/**
	 * 查询导出数据
	 */
	public HSSFSheet getEntityForTopOrg(ReportFinancingShare entity,List<CompanyTree> CompanyTreeList,HSSFSheet sheet,HSSFCellStyle style,Integer rowNum);
	
	
	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportFinancingShareListView(ReportFinancingShare entity, Integer curPageNum, Integer pageSize, Integer status);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingShare getReportFinancingShare(Integer id);
	
	
	/**
	 * 编辑页面保存更新，并保存附件
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingShareAndEnclosure(ReportFinancingShare entity,ReportFinancingShare oldEntity,HhUsers use,MultipartFile[] enclosures);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportFinancingShare(Integer id,HhUsers use);

	/**
	 * 根据融资项目id获取此项目所绑定的附件列表
	 * @param id
	 * @return
	 */
	public List<ReportFinancingShareEnclosure> getOldEnclosures(Integer id);

	//获取此项目更新日志
	public List<ReportFinancingShareLog> getLog(Integer id);

	/**
	 * list页面上报时，实体更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingShare(
			ReportFinancingShare entity, HhUsers use);

	/**
	 * 提交审核
	 * @param entityid
	 * @param examStr
	 * @param examResult
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingShareExamine(
			Integer entityid, String examStr, Integer examResult, HhUsers use);
	
	/**
	 * 计算虚拟公司的汇总值
	 * @param bean
	 * @return
	 */
	public ReportFinancingShare groupSumDataByEntityCompany(ReportFinancingShare bean,String allCompanyNames);

	public int sumDataForSonCompanyData(ReportFinancingShare beanIn,MsgPage msgPageOut,ReportFinancingShare sumBeanOut);
	
	public boolean isVirtualCompany(String organalId);
}
