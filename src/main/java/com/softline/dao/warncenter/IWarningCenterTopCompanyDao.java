package com.softline.dao.warncenter;

import java.util.List;
import java.util.Map;

import com.softline.entity.DataWorktableWarningCenter;
import com.softline.entityTemp.DataWarningCenterParameter;

public interface IWarningCenterTopCompanyDao {
		
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
	public List<DataWorktableWarningCenter> getUnFilledCompany(String[] parameterArr);
}
