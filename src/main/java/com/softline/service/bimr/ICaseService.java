package com.softline.service.bimr;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrCaseQuery;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrCriminalcaseWeek;
import com.softline.util.MsgPage;


public interface ICaseService {
	
	/**
	 * 民事案件查询
	 * type : "1" 为上报列表查询功能，"2"为审核列表查询功能
	 * @return 
	 */
	public MsgPage findCivilcasePageList(BimrCivilcaseWeek civilcase, Integer curPageNum, Integer pageSize, HhUsers user, String type);
	
	/**
	 * 民事案件查询
	 
	 * @return 
	 */
	public MsgPage findCivilcaseQueryPageList(BimrCivilcaseWeek civilcase, Integer curPageNum, Integer pageSize, HhUsers user,String caseDateStart,String caseDateEnd,String amountSection);
	/**
	 * 民事案件删除
	 * @return 
	 */
	public void deleteCivilcase(Integer id);
	
	/**
	 * 民事案件单条记录详情
	 * @return 
	 */
	public BimrCivilcaseWeek getCivilcaseById(Integer id);
	
	/**
	 * 民事案件审核
	 * @return 
	 */
	public BimrCivilcaseWeek examineCivilcaseById(Integer id, String examStr, Integer examResult, HhUsers user);
	/**
	 * 刑事案件查询
	 * type : "1" 为上报列表查询功能，"2"为审核列表查询功能
	 * @return 
	 */
	public MsgPage findCriminalcaseQueryPageList(BimrCriminalcaseWeek criminalcase, Integer curPageNum, Integer pageSize, HhUsers user);
	/**
	 * 刑事案件查询
	 * type : "1" 为上报列表查询功能，"2"为审核列表查询功能
	 * @return 
	 */
	public MsgPage findCriminalcasePageList(BimrCriminalcaseWeek criminalcase, Integer curPageNum, Integer pageSize, HhUsers user, String type);
	
	/**
	 * 刑事案件删除
	 * @return 
	 */
	public void deleteCriminalcase(Integer id);
	
	/**
	 * 刑事案件单条记录详情
	 * @return 
	 */
	public BimrCriminalcaseWeek getCriminalcaseById(Integer id);
	
	/**
	 * 刑事案件审核
	 * @return 
	 */
	public BimrCriminalcaseWeek examineCriminalcaseById(Integer id, String examStr, Integer examResult, HhUsers user);
	
	/**
	 * 更新上一个版本案件的is_lastest字段值
	 * @return
	 * */
	public void updateLasterCaseState(String type, String state, Object obj,MultipartFile[] indictmentFile,MultipartFile[] judgmentFile,MultipartFile[] oFile);
	
	
	public void updateLasterCaseState(String type, String state, Object obj);
	/**
	 * 案件综合查询
	 * @return
	 * */
	public MsgPage comprehensiveQuery(BimrCaseQuery caseQuery, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询案件编码是否被占用
	 * @return
	 * */
	public List getCaseByCaseNum(String type, String caseNum);
	
	/**
	 * 查询报表-是否生成
	 * @return
	 * */
	public List getCaseReport(String year, String week);
	
	
	
	/**
	 * 生成---报表
	 * @return
	 * */
	public void saveCaseReport(String year, String week,String dataauth);
	
	public List<BimrCivilcaseWeek> getcivilCaseExport(BimrCivilcaseWeek civilcase,String caseDateStart,String caseDateEnd,String amountSection,int type);
	/**
	 * 获取民事案件信息导出
	 */
	
	public List<BimrCriminalcaseWeek> getCriminalCaseExport(BimrCriminalcaseWeek criminal1,Integer state);
	/**
	 * 获取刑事案件信息
	 */
	
	public XSSFWorkbook getcivilCaseExportWorkbook(List<BimrCivilcaseWeek> list1,List<BimrCivilcaseWeek> list2,List<BimrCivilcaseWeek> list3);


	//刑事案件查询最新一周数据
	public List<BimrCriminalcaseWeek> getCriminalcaseNewWeek(String ids);
		//获取最新刑事案件ids
	public String getCriminalCaseNewWeekIDs();
	
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
	 * 查询报表-案件周数据
	 * @return
	 * */
	public List getCaseReportExport(BimrCaseQuery caseQuery,int type);
}
