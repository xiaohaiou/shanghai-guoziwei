package com.softline.service.warncenter;

public interface IWarningCenterYearService {

	/**
	 * 工作台预警提醒数据插入 
	 * @param strArr 
	 * 	长度为5
	 * 		参数分别是 	strArr[0] sql_element   sql标签
	 * 				strArr[1] model_id      模块编号
	 * 				strArr[2] page_author   页面权限
	 * 				strArr[3] status        审核状态
	 * 				strArr[4] transferDate  转换时间
	 * 				strArr[5] model_name    模块名称
	 * 				strArr[6] url           url
	 * @return
	 */
	public int dataInsertDataWorkTable(String[] strArr);
	
	/**
	 * 工作台预警数据,已上报、已审核执行删除更新
	 * @param strArr 
	 * 	长度为6
	 * 		参数分别是 	strArr[0] sql_element   sql标签
	 * 				strArr[1] model_id      模块编号
	 * 				strArr[2] page_author   页面权限
	 * 				strArr[3] status        审核状态
	 * 				strArr[4] transferDate  转换时间
	 *              strArr[5] status2                    审核状态2设置查询条件
	 *              strArr[6] model_id      模块名称  
	 * @return
	 */
	public int dataRemoveDataWorkTable(String[] strArr) ;
	
	/**
	 * 工作台预警提醒未填报数据插入
	 * @param strArr 
	 * 	长度为5
	 * 		参数分别是 	strArr[0] sql_element   sql标签
	 * 				strArr[1] page_author   页面权限
	 * 				strArr[2] model_id      模块编号
	 * 				strArr[3] model_name    模块名称
	 * 				strArr[4] url           url
	 * 				strArr[5] year          年
	 * 				strArr[6] month         月
	 * @return
	 */
	public int dataInsertUnfilledDataWorkTable(String[] strArr);
	
	public int dataRemoveUnfilledDataWorkTable(String[] strArr);
	
}
