package com.softline.dao.report;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.softline.entity.HrPersonitem;
import com.softline.entity.ReportPersonalloan;
import com.softline.entity.ReportPersonalloanNearMonth;
import com.softline.entity.ReportPersonalloanNearweek;
import com.softline.entity.ReportPersonalloanNearweekDetail;
import com.softline.entity.ReportPersonalloanNew;
import com.softline.entity.ReportPersonlloaninfo;
import com.softline.entity.ReportPersonlloaninfoNew;

/**
 * 
 * @author sht
 *
 */

public interface IReportPersonalLoanNewDao {
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportPersonalloanNew getPersonalLoanNewbyID(Integer id);
	
	
	/**
	 * 获取总记录数
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getPersonalloanNewListCount(ReportPersonalloanNew entity,String weekStart,String weekEnd);
	
	
	/**
	 * 获取记录
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportPersonalloanNew> getPersonalloanNewList(ReportPersonalloanNew entity ,Integer offsize,Integer pagesize,String weekStart,String weekEnd); 
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean savePersonalloanNewRepeatCheck(ReportPersonalloanNew entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportPersonalloanNew entity);
	
	/**
	 * 删除个人借款子信息
	 * @param groupID
	 * @return
	 */
	public boolean deletePersonalloaninfoNew(String groupID);
	
	
	/**
	 * 查询子信息数量
	 * @param id 查询ID
	 * @return
	 */
	public Integer getPersonalLoaninfoNewCount(Integer id);
	
	
	/**
	 * 获取个人借款数据子信息
	 * @param groupID
	 * @return
	 */
	public List<ReportPersonlloaninfoNew> getPersonlloaninfoNewList(Integer groupID ,Integer offsize,Integer pagesize);
	
	/**
	 * 获取个人借款详细信息
	 * @param year  年份
	 * @param week 周数
	 * @param org  公司ID
	 * @return
	 */
	public List<ReportPersonlloaninfoNew> getPersonlloaninfoNewList(Integer year,Integer week,String org); 
	
	
	/**
	 * 获取个人借款信息
	 * @param year  年份
	 * @param week 周
	 * @param org 公司ID
	 * @return
	 */
	public ReportPersonalloanNew getReportPersonalloanNew(Integer year,Integer week,String org);
	
	/**
	 * 判断当前周的借款人是否是新增的
	 * @param year 年
	 * @param week 周
	 * @param org  组织机构ID
	 * @param respersonAccount  借款人唯一标识
	 * @return
	 */
	public Boolean isNewAddPersonInCurrentWeek(Integer year,Integer week,String org,String respersonAccount);
	
	/**
	 * 获取某人上周期末余额   以及累计借款金额
	 * @param year 年
	 * @param week 周
	 * @param org  组织机构ID
	 * @param respersonAccount  借款人唯一标识
	 * @return
	 */
	public String[] getPreWeekEndingBalance(Integer year,Integer week,String org,String respersonAccount);
	
	/**
	 * 根据ID删除表reportprosonloannearWeek和reportprosonloannearWeekDetial中的数据
	 * @param parentId
	 * @return
	 */
	public void deleteNearweekAndDetail(Integer parentId);
	
	
	/**
	 * 获取子记录--公司员工借款总记录数
	 * 查询条件 时间 核心企业 企业
	 * @param ReportPersonalloanNearweek
	 * @return分页
	 */
	public int getPersonalloanNearWeekDetailCount(ReportPersonalloanNearweek entity);
	
	/**
	 * 获取子记录--公司员工借款总记录
	 * 查询条件 时间 核心企业 企业
	 * @param ReportPersonalloanNearweek
	 * @return
	 */
	public List<ReportPersonalloanNearweekDetail> getPersonalloanNearWeekDetailList(ReportPersonalloanNearweek entity,Integer offsize,Integer pagesize);
	
	/**
	 * 获取通过汇总信息
	 * @param groupID
	 * @return
	 */
	public List<ReportPersonalloanNearweek> getPersonalloanNearWeekList(ReportPersonalloanNearweek entity);
	
	

	/**
	 *   --------------------------------个人超期信息一览---------------------------------------
	 */
	/**
	 * 个人超期信息数量
	 * @param entity
	 * @return
	 */
	public int getPersonlloaninfoNewCount(ReportPersonlloaninfoNew entity);
	
	/**
	 * 获取个人超期信息
	 * @param groupID
	 * @return
	 */
	public List<ReportPersonlloaninfoNew> getPersonnalovertimeDetailList(ReportPersonlloaninfoNew entity ,Integer offsize,Integer pagesize);
	
	
	/**
	 *   --------------------------------个人超期信息一览---------------------------------------
	 */
	
	
	/**
	 * 个人超期信息汇总-数量
	 * @param entity
	 * @return分页
	 */
	public int getcoreComloaninfoCount(ReportPersonlloaninfoNew entity,String yearStart,String yearEnd);
	
	/**
	 * 获取个人超期汇总-信息
	 * @param groupID
	 * @return
	 */
	public List<ReportPersonalloanNearweek> getcoreComovertimeDetailList(ReportPersonlloaninfoNew entity ,Integer offsize,Integer pagesize,String yearStart,String yearEnd);
	
	
	
	/**
	 * 获取个人借款详细信息 用于导出
	 * @param entity 查询条件
	 * @return
	 */
	public List<ReportPersonlloaninfoNew> getPersonlloaninfoNewList(ReportPersonlloaninfoNew entity,String weekStart,String weekEnd);
	
}
