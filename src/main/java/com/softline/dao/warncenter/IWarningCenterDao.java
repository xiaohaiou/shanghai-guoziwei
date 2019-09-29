package com.softline.dao.warncenter;

import java.util.List;
import java.util.Map;

import com.softline.entity.DataWorktableWarningCenter;
import com.softline.entityTemp.DataWarningCenterParameter;

public interface IWarningCenterDao {
	
	/**
	 * 工作台数据展示
	 * @param searchBean 查询条件
	 * @param curPageNum 当前页
	 * @param pageSize   每页显示数据
	 * @param authorMap  控制访问数据权限信息
	 * @return
	 */
	public List<DataWorktableWarningCenter> dataShow(DataWorktableWarningCenter searchBean,Integer offsize, Integer pageSize,Map authorMap);
	
	/**
	 * 工作台数据展示，查询所有记录总数。
	 * @param searchBean
	 * @return
	 */
	public Integer dataShow(DataWorktableWarningCenter searchBean,Map authorMap);
	
	/**
	 * 提取未及时上报，未及时审核单位
	 * @param searchBean
	 * @param parameterArr 返回实体类参数设置
	 * 			parameterArr[0] page_author
	 * 			parameterArr[1] model_id
	 * 			parameterArr[2] model_name
	 * 			parameterArr[3] url
	 * @return
	 */
	public List<DataWorktableWarningCenter> getUnreportedCompany(String sql,String[] parameterArr);
	
	/**
	 * 提取已上报、已审核公司查询获取
	 * @param searchBean
	 * @return
	 */
	public List<DataWorktableWarningCenter> getReportedCompany(String sql);
	
	/**
	 * 提取所有未填报公司的数据
	 * 		上个月
	 * @param sql
	 * @param parameterArr
	 * 			parameterArr[0] page_author
	 * 			parameterArr[1] model_id
	 * 			parameterArr[2] model_name
	 * 			parameterArr[3] url
	 * 			parameterArr[4] year
	 * 			parameterArr[5] month
	 * 
	 * @return
	 */
	public List<DataWorktableWarningCenter> getUnFilledCompany(String sql,String[] parameterArr);
	
	
	/**
	 * 删除对应公司的记录
	 * @param bean
	 */
	public void deleteRecordCompany(DataWorktableWarningCenter bean);
		
	/**
	 * 工作台预警数据执行日志记录
	 * @param dataTime
	 * @param modelId
	 * @param msg
	 * @return
	 */
	public int dataWorktableWarningLog(String dataTime,String modelId,String msg);
	
	/**
	 * 获取	1、未及时上报	 UnReportIn
	 * 		2、未及时审核            UnExmineIn
	 * 		3、已上报                    ReportedOut
	 * 		4、已审核                    ExminedOut
	 * @return
	 */
	public List<DataWarningCenterParameter> getAllParameter();
	
	/**
	 * 查询所有转换记录数据
	 * @param model_id
	 * @param offsize
	 * @param pageSize
	 * @return
	 */
	public List<Object> getReportData(String model_id,Integer offsize,Integer pageSize);
	
	/**
	 * 查询所有转换记录总数
	 * @param model_id
	 * @return
	 */
	public Integer getReportData(String model_id);
	
	/**
	 * 添加菜单选择项
	 * @return
	 */
	public List<Object> getSelectOptionsForWarnCenter();
	
	/**
	 * 删除未及时填报数据
	 * @param date
	 */
	public void deleteUnFilledCompany(String date);
	
}
