package com.softline.service.warncenter;

import java.util.List;
import java.util.Map;

import com.softline.entity.DataWorktableWarningCenter;
import com.softline.entityTemp.DataWarningCenterParameter;
import com.softline.util.MsgPage;

/**
 * @author zl
 * 工作台预警提醒
 */
public interface IWarningCenterService {
	
	/**
	 * 工作台数据展示
	 * @param searchBean 查询条件
	 * @param curPageNum 当前页
	 * @param pageSize   每页显示数据
	 * @param authorMap  控制页面访问权限
	 * @return
	 */
	public MsgPage dataShow(DataWorktableWarningCenter searchBean,Integer curPageNum, Integer pageSize,Map authorMap);
	
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
	
	public List<DataWarningCenterParameter> getAllParameter();
	
	/**
	 * 获取预警数据转换记录
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public MsgPage getReportData(String model_id,Integer curPageNum, Integer pageSize);
	
	/**
	 * 添加菜单选择项
	 * @return
	 */
	public List<String[]> getSelectOptionsForWarnCenter();
	
	/**
	 * 删除未及时填报数据
	 * @param date
	 */
	public void deleteUnFilledCompany(String date);
	
}
