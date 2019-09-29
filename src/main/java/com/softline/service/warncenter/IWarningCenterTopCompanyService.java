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
public interface IWarningCenterTopCompanyService {
	
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
	 * 				strArr[7] companyName   公司名称
	 * 				strArr[8] org           公司编码
	 * @return
	 */
	public int dataInsertDataWorkTable(String[] strArr);
	
	
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
}
