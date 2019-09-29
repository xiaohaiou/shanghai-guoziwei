package com.softline.dao.bimr;

import java.util.List;

import com.softline.entity.bimr.BimrCaseQuery;
import com.softline.entity.bimr.BimrCaseReport;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrCriminalcaseWeek;

public interface ICaseDao {
	
	/**
	 * 民事案件
	 * */
	public Integer getCivilcaseCount(BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize);
	
	public Integer getCivilcaseQueryCount(BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize,String caseDateStart,String caseDateEnd,String amountSection);
	
	public Integer getCivilcaseCountByUserId(BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize, String CreatorId);
	
	public List<BimrCivilcaseWeek> getCivilcaseQueryList(BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize,String caseDateStart,String caseDateEnd,String amountSection);
	
	public List<BimrCivilcaseWeek> getCivilcaseList(BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize);
	
	public List<BimrCivilcaseWeek> getCivilcaseListByUserId(BimrCivilcaseWeek civilcase, Integer offset, Integer pageSize, String CreatorId);
	
	public void deleteCivilcase(Integer id);
	
	public BimrCivilcaseWeek getCivilcaseById(Integer id);	
	
	public BimrCivilcaseWeek getLastCivilcaseByCaseNum(String CaseNum);

	public List getCivilcaseByCaseNum(String CaseNum);
	
	public List getCivilcaseByLikeCaseNum(String CaseNum);
	//民事案件导出
	public List<BimrCivilcaseWeek> getcivilCaseExport(BimrCivilcaseWeek civilcase,String caseDateStart,String caseDateEnd,String amountSection,int type);
	//民事案件查询最新一周数据
	public List<BimrCivilcaseWeek> getcivilCaseNewWeek(String ids);
	//获取最新民事案件ids
	public String getcivilCaseNewWeekIDs();
	
	
	/**
	 * 获取民事案件信息
	 */
	
	/**
	 * 刑事案件
	 * */
	public Integer getCriminalcaseCount(BimrCriminalcaseWeek civilcase, Integer offset, Integer pageSize);

	public Integer getCriminalcaseQueryCount(BimrCriminalcaseWeek criminalcase, Integer offset, Integer pageSize);
	
	public Integer getCriminalcaseCountByUserId(BimrCriminalcaseWeek civilcase, Integer offset, Integer pageSize, String creatorId);
	
	public List<BimrCriminalcaseWeek> getCriminalcaseQueryList(BimrCriminalcaseWeek criminalcase, Integer offset, Integer pageSize);

	
	public List<BimrCriminalcaseWeek> getCriminalcaseList(BimrCriminalcaseWeek civilcase, Integer offset, Integer pageSize);

	public List<BimrCriminalcaseWeek> getCriminalcaseListByUserId(BimrCriminalcaseWeek civilcase, Integer offset, Integer pageSize, String creatorId);
	
	public void deleteCriminalcase(Integer id);
	
	public BimrCriminalcaseWeek getCriminalcaseById(Integer id);
	
	public BimrCriminalcaseWeek getLastCriminalcaseByCaseNum(String CaseNum);
	
	public List getCriminalcaseByCaseNum(String CaseNum);
	
	public List getCriminalcaseLikeCaseNum(String CaseNum);
	//刑事案件导出
	public List<BimrCriminalcaseWeek> getCriminalCaseExport(BimrCriminalcaseWeek civilcase,Integer state);
	/**
	 * 案件--综合查询
	 * */
	public List getComprehensiveQuery(BimrCaseQuery caseQuery, Integer offset, Integer pageSize);
	
	public Integer getComprehensiveQueryCount(BimrCaseQuery caseQuery, Integer offset, Integer pageSize);
	
	public List getCaseReport(String year, String week);
	
	public List getCivilcaseIdsList(String dataauth);
	
	public List getCriminalcaseIdsList(String dataauth);

	//刑事案件查询最新一周数据
		public List<BimrCriminalcaseWeek> getCriminalcaseNewWeek(String ids);
		//获取最新刑事案件ids
		public String getCriminalCaseNewWeekIDs();
	
	//插入数据到bimr_case_report_temp
		
		public int saveBimrCaseReporttemp(BimrCaseReport bimrCaseReport,String type,String cid,int month);
		
		/**
		 * 获取最新周数据
		 */
		public List getLastWeekData(String year);
		/**
		 * 获取民事案件本周新增数据
		 */
		public List<BimrCivilcaseWeek> getcivilNewCaseExport(String ids,int type);	
		/**
		 * 获取刑事案件本周新增数据
		 */
		public List<BimrCriminalcaseWeek> getCriminalNewCaseExport(String ids,int type);
		
		/**
		 * 查询报表-案件周数据 type =0 民事
		 * @return
		 * */
		public List getCaseReportExport(BimrCaseQuery caseQuery,int type);
}
