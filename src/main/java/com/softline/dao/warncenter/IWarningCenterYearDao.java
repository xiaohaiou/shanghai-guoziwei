package com.softline.dao.warncenter;

import java.util.List;
import com.softline.entity.DataWorktableWarningCenter;

public interface IWarningCenterYearDao {
	
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
	
}
