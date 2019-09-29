package com.softline.service.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.CompanyTree;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.ReportFinancingProjectProgressEnclosure;
import com.softline.entity.ReportFinancingProjectProgressLog;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author ky_tian
 *
 */
public interface IReportFinancingProjectProgressService {

	/**
	 * 导入保存
	 * @throws Exception 
	 * */
	public Map saveReport(ReportFinancingProjectProgress entity,HhUsers use,ArrayList<String> sList) throws Exception;
	
	/**
	 * 报表
	 */
	public HSSFSheet setExcelData(HSSFRow row,HSSFSheet sheet,List<ReportFinancingProjectProgress> entity,HSSFCellStyle style,Integer rowNum,Integer projectProgressFlag,int count);
	
	/**
	 * 查询导出数据
	 */
	public HSSFSheet getEntityForOrg(ReportFinancingProjectProgress entity,HSSFSheet sheet,HSSFCellStyle style,Integer rowNum);
	
	/**
	 * 查询导出数据
	 */
	public HSSFSheet getEntityForTopOrg(ReportFinancingProjectProgress entity,List<CompanyTree> CompanyTreeList,HSSFSheet sheet,HSSFCellStyle style,Integer rowNum);
	
	
	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportFinancingProjectProgressListView(ReportFinancingProjectProgress entity, Integer curPageNum, Integer pageSize, Integer status);
	
	/**
	 * 海航实业融资项目情况汇总(除债券类)
	 */
	public List<String> getProjectProgressList(ReportFinancingProjectProgress entity,String org);
	
	/**
	 * 海航实业各业态推进中融资项目情况（除债券类）
	 */
	public MsgPage getCategoryList(ReportFinancingProjectProgress entity,String org,Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingProjectProgress getReportFinancingProjectProgress(Integer id);
	
	
	/**
	 * 编辑页面保存更新，并保存附件
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingProjectProgressAndEnclosure(ReportFinancingProjectProgress entity,ReportFinancingProjectProgress oldEntity,HhUsers use,MultipartFile[] enclosures);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportFinancingProjectProgress(Integer id,HhUsers use);

	/**
	 * 根据融资项目id获取此项目所绑定的附件列表
	 * @param id
	 * @return
	 */
	public List<ReportFinancingProjectProgressEnclosure> getOldEnclosures(
			Integer id);

	//获取此项目更新日志
	public List<ReportFinancingProjectProgressLog> getLog(Integer id);

	/**
	 * list页面上报时，实体更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingProjectProgress(
			ReportFinancingProjectProgress entity, HhUsers use);

	/**
	 * 提交审核
	 * @param entityid
	 * @param examStr
	 * @param examResult
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingProjectProgressExamine(
			Integer entityid, String examStr, Integer examResult, HhUsers use);

	/**
	 * 计算虚拟公司的汇总值
	 * @param bean
	 * @return
	 */
	public ReportFinancingProjectProgress groupSumDataByEntityCompany(ReportFinancingProjectProgress bean,String allCompanyNames);
	
	public int sumDataForSonCompanyData(ReportFinancingProjectProgress beanIn,MsgPage msgPageOut,ReportFinancingProjectProgress sumBeanOut);

	public HSSFSheet getEntityForOrgs(ReportFinancingProjectProgress entity,HSSFSheet sheet, HSSFCellStyle style, Integer rowNum, String org);

	public boolean isVirtualCompany(String organalId);
}
