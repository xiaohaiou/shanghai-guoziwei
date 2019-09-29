package com.softline.service.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.softline.client.task.HhUsers;
import com.softline.client.task.TaskInstruction;
import com.softline.entity.ReportFormsUnFilledCompany;
import com.softline.entityTemp.CommitResult;

public interface IReportRemindCompanyService {

	public void deleteReportFormsUnFilledCompany(ReportFormsUnFilledCompany bean);
	
	public CommitResult saveUnfilledCompany(ReportFormsUnFilledCompany bean);
	
	public void setReportFormsUnFilledCompanyUnRemind(ReportFormsUnFilledCompany bean);
	
	/**
	 * @param accountInfo 1.sBSlctPersons 返回选择人公司信息   2.sBAccountNameDeps 返回人账号信息
	 * 				          3.nNodeId 公司编码        4.updateTime 更新时间
	 *	
	 *	 	unInvokeCompanyKind 51111 	//预算
	 *	 						51112	//核算
	 *	 						51113	//税务
	 * @return
	 */
	public int updateReportUnfilledCompanyTable(Map<String,Object> accountInfo,String reportDate,Integer unInvokeCompanyKind);

	public void setAllDataDisRemindplan(String updatetime,Integer formKind);
	
	public void addAllDataToRemindPlan(String reportTime, String authdata,Integer remindFormKind,String treeTab);
	
	/**
	 * @param instruction	指令中心的参数
	 * @param session		
	 * @param sysUsers
	 *  @param title        邮件标题
	 */
	public void setInstructionPara(TaskInstruction instruction,HttpSession session,
			com.softline.client.task.HhUsers sysUsers,String title);
	
	/**
	 * 
	 * 		  核算模块指令下达任务   
	 * @param listIn    本期所有提醒数据
	 *		 0、	vcFullName
	 *		 1、	vcShortName
	 *		 2、	nNodeID
	 *		 3、	responsiblePersonName
	 *		 4、	vcEmployeeID
	 *		 5、	responsiblePersonEmail
	 *		 6、	lastRemindTime
	 *		 7、	isRemind
	 * @param listOut1
	 * @param mapOut2 1.sBSlctPersons 返回选择人公司信息   2.sBAccountNameDeps 返回人账号信息
	 * 				  3.nNodeId 公司编码        4.updateTime 更新时间
	 * @return
	 *    	author zl
	 */
	public int getInstractionVcAccount_account(List<Object[]> listIn,
			   /*返回未填报公司账号信息*/List listOut1,
			   /*返回选中发送人账号和账号描述信息*/Map<String,Object> mapOut2);
	
	
	/**
	 * 
	 * 		  税务模块指令下达任务   
	 * @param listIn    本期所有提醒数据
	 *		0	hotr.vcFullName,
	 *		1	hotr.vcShortName,
	 *		2	(select description from hh_base where id = hotr.status),
	 *		3	hotr.nNodeID,
	 *		4	hotr.responsiblePersonName,
	 *		5	hotr.vcEmployeeID,
	 *		6	hotr.responsiblePersonEmail,
	 *		7	rfc.isRemind,
	 *		8	rfc.lastRemindTime
	 * @param listOut1
	 * @param mapOut2 1.sBSlctPersons 返回选择人公司信息   2.sBAccountNameDeps 返回人账号信息
	 * 				  3.nNodeId 公司编码        4.updateTime 更新时间
	 * @return
	 *    	author zl
	 */
	public int getInstractionVcAccount_Taxpay(List<Object[]> listIn,
									   		  /*返回未填报公司账号信息*/List listOut1,
									   		  /*返回选中发送人账号和账号描述信息*/Map<String,Object> mapOut2);
}
