package com.softline.service.warncenter;

public interface IWarningCenterOneYearMonthService {
	
	/**
	 * 插入当年未及时填报数据 
	 * @param strArr 
	 * 	长度为6
	 * 		参数分别是 	strArr[0] sql_element   sql标签
	 * 				strArr[1] model_id      模块编号
	 * 				strArr[2] page_author   页面权限
	 * 				strArr[3] model_name    模块名称
	 * 				strArr[4] url           url
	 * 				strArr[5] year          年
	 * @return
	 */
	public int dataInsertUnfilledDataWorkTable(String[] parameterArr);
	
	/**
	 * 删除当年已填报数据
	 * 参数分别是 	strArr[0] sql_element   sql标签
	 * 			strArr[1] model_id      模块编号
	 * 		    strArr[1] year          年
	 */
	public int dataRemoveUnfilledDataWorkTable(String[] parameterArr);
	
	/**
	 * 删除当年已填报数据
	 * 参数分别是 	strArr[0] sql_element   sql标签
	 * 			strArr[1] model_id      模块编号
	 * 		    strArr[1] year          年
	 */
	public int removeLastYearData(String[] parameterArr);
	
}
