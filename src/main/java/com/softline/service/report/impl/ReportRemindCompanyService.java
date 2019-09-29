package com.softline.service.report.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.client.task.TaskInstruction;
import com.softline.dao.report.IReportRemindCompanyDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISDKDao;
import com.softline.entity.HhUsers;
import com.softline.entity.HhUsersmessageinfo;
import com.softline.entity.ReportFormsUnFilledCompany;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportRemindCompanyService;

@Service("reportRemindCompanyService")
public class ReportRemindCompanyService implements IReportRemindCompanyService{
	
	@Autowired
	@Qualifier("sdkDao")
	private ISDKDao sdkDao;
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;	
	@Autowired
	@Qualifier("reportRemindCompanyDao")
	private IReportRemindCompanyDao reportRemindCompanyDao;

	public CommitResult checkIsExits(ReportFormsUnFilledCompany bean){
		CommitResult result = new CommitResult();
		boolean backinfo = reportRemindCompanyDao.isExitsReportFormsUnFilledCompany(bean);
		if(!backinfo){
			result.setExceptionStr("已存在数据！");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 	删除未填报公司选择信息
	 * 	nNodeID 	公司编码
	 * 	updateTime  更新时间
	 * 	formKind	表单类型
	 */
	@Override
	public void deleteReportFormsUnFilledCompany(ReportFormsUnFilledCompany bean){
		if(this.checkIsExits(bean).isResult()){
			reportRemindCompanyDao.deleteReportFormsUnFilledCompany(bean);
		}
	}

	/**
	 * 	保存未填报公司选择信息
	 * 	nNodeID 	公司编码
	 * 	updateTime  更新时间
	 * 	formKind	表单类型
	 */
	@Override
	public CommitResult saveUnfilledCompany(ReportFormsUnFilledCompany bean){   
		
		CommitResult result=new CommitResult();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=df.format(new Date());
		
		if(null == bean || 
				null == bean.getnNodeID() ||
				null == bean.getUpdatetime() ||
				null == bean.getFormKind()){
			result.setExceptionStr("缺少关键字段！");
			return result;
		}
		
		//判断数据库中是否已存在数据
		if(this.checkIsExits(bean).isResult()){
			ReportFormsUnFilledCompany beanOld = reportRemindCompanyDao.getReportFormsUnFilledCompany(bean);
//			commonDao.delete(beanOld);
//			bean.setIsdel(0);
//			bean.setIsRemind(0);
//			bean.setLastRemindTime(time);
//			commonDao.saveOrUpdate(bean);
			reportRemindCompanyDao.setReportFormsUnFilledCompanyRemind(bean);
			result.setResult(true);
			return result;
		}
		
		bean.setIsdel(0);
		bean.setIsRemind(0);
		commonDao.saveOrUpdate(bean);
		result.setResult(true);
		return result;	
	}

	/**
	 * 	设置未填报公司为提醒状态
	 * 	nNodeID 	公司编码
	 * 	updateTime  更新时间
	 * 	formKind	表单类型
	 */
	@Override
	public void setReportFormsUnFilledCompanyUnRemind(ReportFormsUnFilledCompany bean){
		if(this.checkIsExits(bean).isResult()){
			reportRemindCompanyDao.setReportFormsUnFilledCompanyUnRemind(bean);
		}
	}

	
	/**
	 * @param accountInfo 1.sBSlctPersons 返回选择人公司信息   2.sBAccountNameDeps 返回人账号信息
	 * 				          3.nNodeId 公司编码        4.updateTime 更新时间
	 *	
	 *	 	unInvokeCompanyKind 51111 	//预算
	 *	 						51112	//核算
	 *	 						51113	//税务
	 * @return
	 */
	@Override
	public int updateReportUnfilledCompanyTable(Map<String,Object> accountInfo,String reportDate,Integer unInvokeCompanyKind){
		
		if(null == accountInfo)
			return -1;
		
		List<Object> nNodeIdArray = (List<Object>)accountInfo.get("nNodeId");
		ReportFormsUnFilledCompany tempBean = new ReportFormsUnFilledCompany();
		int i =0;		
		for(Object obj:nNodeIdArray){
			if(null !=obj){
				tempBean.setnNodeID(String.valueOf(obj));
				tempBean.setUpdatetime(reportDate);
				tempBean.setFormKind(unInvokeCompanyKind);
				tempBean.setLastRemindTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				reportRemindCompanyDao.setReportFormsUnFilledCompanyUnRemind(tempBean);
			}
		}
		accountInfo.clear();
		return 0;
	}

	/**
	 * 更新所有的数据为未提醒状态
	 * 	unInvokeCompanyKind 51111 	//预算
	 *	 					51112	//核算
	 *	 					51113	//税务
	 */
	@Override
	public void setAllDataDisRemindplan(String updatetime,Integer formKind){
		// 更新本期已有数据为未提醒计划
		reportRemindCompanyDao.setAllHavenDataDisRemind(updatetime, formKind);
	}

	/**
	 * @param reportTime         上报时间
	 * @param authdata      	    数据权限
	 * @param remindFormKind     未填报公司类别
	 * 
	 *  该公司为 核算类未填报公司
	 */
	@Override
	public void addAllDataToRemindPlan(String reportTime, String authdata,Integer remindFormKind,String treeTab){
		// 更新本期已有数据为提醒计划
		reportRemindCompanyDao.setAllHavenDataRemind(reportTime, remindFormKind);
		// 添加所有的数据到本期提醒计划
		reportRemindCompanyDao.addAllDataToRemindPlan(reportTime,authdata,remindFormKind,treeTab);
	}
	
	
	/**
	 * @param instruction	指令中心的参数
	 * @param session		
	 * @param sysUsers
	 *  @param title        邮件标题
	 */
	@Override
	public void setInstructionPara(TaskInstruction instruction,HttpSession session,
															com.softline.client.task.HhUsers sysUsers,String title){
        HhUsers users=(HhUsers) session.getAttribute("gzwsession_users");
		instruction.setCreaterTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		instruction.setCreator(users.getVcEmployeeId());
		instruction.setCreatorName(users.getVcName());
		instruction.setCreatorAccount(users.getVcAccount());
		instruction.setCreatorDep(users.getVcFullName());
		instruction.setIsDel(0);
		instruction.setTaskStatus(0);//新增任务默认状态为 (0 任务未下发) 
		instruction.setTaskHandler(users.getVcEmployeeId());//任务创建默认处理人为创建人
		instruction.setHandlerName(users.getVcName());
		instruction.setHandlerAccount(users.getVcAccount());
		instruction.setHandlerDep(users.getVcFullName());
		instruction.setTaskLevel(1);//通过新增操作的任务，任务层级为1
		instruction.setTaskName(title);
		instruction.setTaskDescription("未填报公司到期提醒功能");
		instruction.setTaskNode2("");
		instruction.setDeadLine(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		instruction.setIsTerminate(0);
		sysUsers.setVcAccount(users.getVcAccount());
		sysUsers.setVcEmployeeId(users.getVcEmployeeId());
		sysUsers.setVcName(users.getVcName());
		sysUsers.setVcOrganId(users.getVcOrganId());
	}
	
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
	@Override
	public int getInstractionVcAccount_account(List<Object[]> listIn,
									   		  /*返回已发送填报公司邮箱信息*/List listOut1,
									   		  /*返回选中发送人账号和账号描述信息*/Map<String,Object> mapOut2){			
		if(null ==listIn)
			return -1;
		StringBuilder sBSlctPersons = new StringBuilder();		//记录需要发送公司负责人的账号信息
		StringBuilder sBAccountNameDeps = new StringBuilder();	//记录发送公司负责人的描述
		List<Object> nNodeIdList = new ArrayList<Object>(); 	//标识已发送公司编码信息
		for(Object[] objArr:listIn){	
			HhUsersmessageinfo hhUserMessageInfoTemp;
//			= this.getHhUserMessageInfo(String.valueOf(objArr[4])); //通过员工号获取账号信息			
			if(objArr[4]!=null &&
					(hhUserMessageInfoTemp = this.getHhUserMessageInfo(String.valueOf(objArr[4])))!=null){
				sBSlctPersons.append(String.valueOf(objArr[4])).append(",");
				sBAccountNameDeps.append("无/"+String.valueOf(objArr[0])+"(财务树负责人)/无").append(",");
				nNodeIdList.add(objArr[2]);
				listOut1.add(hhUserMessageInfoTemp.getVcEmail());		
			}	
		}	
		//设置返选择公司信息
		if(sBSlctPersons.length()==0)
			mapOut2.put("sBSlctPersons", null);
		else{
			sBSlctPersons.setLength(sBSlctPersons.length()-1);
			mapOut2.put("sBSlctPersons", sBSlctPersons.toString());
		}
		//设置返回公司名称信息
		if(sBAccountNameDeps.length()==0)
			mapOut2.put("sBAccountNameDeps", null);
		else{
			sBAccountNameDeps.setLength(sBAccountNameDeps.length()-1);
			mapOut2.put("sBAccountNameDeps", sBAccountNameDeps.toString());
		}	
		mapOut2.put("nNodeId", nNodeIdList);
		//清空传入list集合中无用对象
		listIn.clear();
		return 0;
	}
	
	
	/**
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
	@Override
	public int getInstractionVcAccount_Taxpay(List<Object[]> listIn,
									   		  /*返回已发送填报公司邮箱信息*/List listOut1,
									   		  /*返回选中发送人账号和账号描述信息*/Map<String,Object> mapOut2){			
		if(null ==listIn)
			return -1;
		StringBuilder sBSlctPersons = new StringBuilder();		//记录需要发送公司负责人的账号信息
		StringBuilder sBAccountNameDeps = new StringBuilder();	//记录发送公司负责人的描述
		List<Object> nNodeIdList = new ArrayList<Object>(); 	//标识已发送公司编码信息
		for(Object[] objArr:listIn){		
			HhUsersmessageinfo hhUserMessageInfoTemp;
//			= this.getHhUserMessageInfo(String.valueOf(objArr[5])); //通过员工号获取账号信息
			if(objArr[5]!=null &&
					(hhUserMessageInfoTemp= this.getHhUserMessageInfo(String.valueOf(objArr[5])))!=null){
				sBSlctPersons.append(String.valueOf(objArr[5])).append(",");
				sBAccountNameDeps.append("无/"+String.valueOf(objArr[0])+"(财务树负责人)/无").append(",");
				nNodeIdList.add(objArr[3]); 
				listOut1.add(hhUserMessageInfoTemp.getVcEmail());			
			}	
		}	
		//设置返选择公司信息
		if(sBSlctPersons.length()==0)
			mapOut2.put("sBSlctPersons", "");
		else{
			sBSlctPersons.setLength(sBSlctPersons.length()-1);
			mapOut2.put("sBSlctPersons", sBSlctPersons.toString());
		}
		//设置返回公司名称信息
		if(sBAccountNameDeps.length()==0)
			mapOut2.put("sBAccountNameDeps", "");
		else{
			sBAccountNameDeps.setLength(sBAccountNameDeps.length()-1);
			mapOut2.put("sBAccountNameDeps", sBAccountNameDeps.toString());
		}	
		mapOut2.put("nNodeId", nNodeIdList);
		//清空传入list集合中无用对象
		listIn.clear();
		return 0;
	}
	
	public HhUsersmessageinfo getHhUserMessageInfo(String vcEmployeeId){
		return sdkDao.checkmessageinfoRepeat(vcEmployeeId);
	}
}
