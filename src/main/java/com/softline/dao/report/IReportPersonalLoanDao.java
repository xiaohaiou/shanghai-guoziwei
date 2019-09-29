package com.softline.dao.report;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.softline.entity.HrPersonitem;
import com.softline.entity.ReportPersonalloan;
import com.softline.entity.ReportPersonalloanNearMonth;
import com.softline.entity.ReportPersonlloaninfo;

/**
 * 
 * @author tch
 *
 */

public interface IReportPersonalLoanDao {
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportPersonalloan getPersonalLoanbyID(Integer id);
	
	
	/**
	 * 获取总记录数
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getPersonalloanListCount(ReportPersonalloan entity);
	
	
	/**
	 * 获取记录
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportPersonalloan> getPersonalloanList(ReportPersonalloan entity ,Integer offsize,Integer pagesize); 
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean savePersonalloanRepeatCheck(ReportPersonalloan entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportPersonalloan entity);
	
	/**
	 * 删除个人借款子信息
	 * @param groupID
	 * @return
	 */
	public boolean deletePersonalloaninfo(String groupID);
	
	/**
	 * 保存个人借款子信息
	 * @param groupID
	 * @return
	 */
	public void savePersonlloaninfoitem(List<ReportPersonlloaninfo> a,ReportPersonalloanNearMonth personalloanNearMonth);
	
	
	/**
	 * 查询子信息数量
	 * @param id 查询ID
	 * @return
	 */
	public Integer getPersonalLoaninfoCount(Integer id);
	
	
	/**
	 * 获取人事数据子信息
	 * @param groupID
	 * @return
	 */
	public List<ReportPersonlloaninfo> getPersonlloaninfoList(Integer groupID ,Integer offsize,Integer pagesize);
	
	
	
	/**
	 *   --------------------------------公司员工借款相邻月份差异比较查询---------------------------------------
	 */
	
	/**
	 * 根据公司名，年月份 获取上月该公司借款情况
	 * @return
	 */
	public List<ReportPersonlloaninfo> getLastMonthCompareList(ReportPersonalloan entity);	
	
	
	/**
	 * 获取子记录--公司员工借款总记录数
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getPersonalloanNearMonthDetailCount(ReportPersonalloanNearMonth entity);
	
	
	/**
	 * 获取人事数据子信息
	 * @param groupID
	 * @return
	 */
	public List<ReportPersonalloanNearMonth> getPersonalloanNearMonthList(ReportPersonalloanNearMonth entity ,Integer offsize,Integer pagesize);
	
	
	
	/**
	 *   --------------------------------个人超期信息一览---------------------------------------
	 */
	/**
	 * 个人超期信息数量
	 * @param entity
	 * @return
	 */
	public int getPersonlloaninfoCount(ReportPersonlloaninfo entity);
	
	/**
	 * 获取个人超期信息
	 * @param groupID
	 * @return
	 */
	public List<Object> getPersonnalovertimeDetailList(ReportPersonlloaninfo entity ,Integer offsize,Integer pagesize);
	
	
	public ReportPersonlloaninfo getPersonlloaninfobyId(Integer id);
	
	/**
	 *   --------------------------------个人超期信息一览---------------------------------------
	 */
	
	
	/**
	 * 个人超期信息汇总-数量
	 * @param entity
	 * @return
	 */
	public int getcoreComloaninfoCount(ReportPersonlloaninfo entity);
	
	/**
	 * 获取个人超期汇总-信息
	 * @param groupID
	 * @return
	 */
	public List<Object> getcoreComovertimeDetailList(ReportPersonlloaninfo entity ,Integer offsize,Integer pagesize);
	
}
