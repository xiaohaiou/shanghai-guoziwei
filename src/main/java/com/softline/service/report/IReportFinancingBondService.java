package com.softline.service.report;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.CompanyTree;
import com.softline.entity.HhUsers;
import com.softline.entity.financing.ReportFinancingBond;
import com.softline.entity.financing.ReportFinancingBondEnclosure;
import com.softline.entity.financing.ReportFinancingBondLog;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
public interface IReportFinancingBondService {

	/**
	 * 汇总数据查询
	 */
	public List<String> getDataList(ReportFinancingBond entity);
	
	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportFinancingBondListView(ReportFinancingBond entity, Integer curPageNum, Integer pageSize, Integer status);
	
	/**
	 * 报表
	 */
	public HSSFSheet setExcelData(HSSFSheet sheet,List<ReportFinancingBond> entity,HSSFCellStyle style,Integer rowNum);
	
	/**
	 * 查询导出数据
	 */
	public HSSFSheet getEntityForOrg(ReportFinancingBond entity,HSSFSheet sheet,HSSFCellStyle style,Integer rowNum);
	
	/**
	 * 查询导出数据
	 */
	public HSSFSheet getEntityForTopOrg(ReportFinancingBond entity,List<CompanyTree> CompanyTreeList,HSSFSheet sheet,HSSFCellStyle style,Integer rowNum);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingBond getReportFinancingBond(Integer id);
	
	/**
	 * 上报保存
	public Integer saveReport(ReportFinancingBond entity,HhUsers use,ArrayList<String> sList);*/
	
	
	/**
	 * 编辑页面保存更新，并保存附件
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingBondAndEnclosure(ReportFinancingBond entity,ReportFinancingBond oldEntity,HhUsers use,MultipartFile[] enclosures);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportFinancingBond(Integer id,HhUsers use);

	/**
	 * 根据融资项目id获取此项目所绑定的附件列表
	 * @param id
	 * @return
	 */
	public List<ReportFinancingBondEnclosure> getOldEnclosures(Integer id);

	//获取此项目更新日志
	public List<ReportFinancingBondLog> getLog(Integer id);

	/**
	 * list页面上报时，实体更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingBond(
			ReportFinancingBond entity, HhUsers use);

	/**
	 * 提交审核
	 * @param entityid
	 * @param examStr
	 * @param examResult
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingBondExamine(
			Integer entityid, String examStr, Integer examResult, HhUsers use);
	
	/**
	 * 计算虚拟公司的汇总值
	 * @param bean
	 * @return
	 */
	public ReportFinancingBond groupSumDataByEntityCompany(ReportFinancingBond bean,String allCompanyNames);
	
	public int sumDataForSonCompanyData(ReportFinancingBond beanIn,MsgPage msgPageOut,ReportFinancingBond sumBeanOut);
	
	public boolean isVirtualCompany(String organalId);
}
