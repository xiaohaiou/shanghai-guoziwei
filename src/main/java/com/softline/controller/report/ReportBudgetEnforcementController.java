package com.softline.controller.report;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.client.bima.IWsSystemService;
import com.softline.client.bima.WsSystemServiceService;
import com.softline.client.task.TaskInstruction;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyLevelTree;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.ReportFormsUnFilledCompany;
import com.softline.entityTemp.BimcCompany;
import com.softline.entityTemp.ReportQueryView;
import com.softline.service.report.IReportRemindCompanyService;
import com.softline.service.report.IReportService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.ITreeService;
import com.softline.service.task.ITaskService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportbudgetenforcement")
public class ReportBudgetEnforcementController {

	@Resource(name = "reportService")
	private IReportService reportService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;	
	@Resource(name = "taskService")
	private ITaskService taskService;
	@Resource(name = "treeService")
	private ITreeService treeService;
	@Resource(name = "reportRemindCompanyService")
	private IReportRemindCompanyService reportRemindCompanyService;	
	
	/*@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			Purchase entityview=reportService.getPurchase(id);
			map.put("purchase", entityview);
		}
	}
	*/
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportQueryView entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportbudgetenforcement";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer grouptype=Base.reportgroup_BudgetEnforcement;
        Integer type=Base.reportgroup_type_simple;
        Integer pageNum = Integer.valueOf(curPageNum);
        map.put("grouptype", grouptype);
        map.put("type", type);
        HttpSession session=request.getSession();
  		String str=(String) session.getAttribute("gzwsession_financecompany");
  		//获取上月年、月
		Calendar calendar = Calendar.getInstance();
		String time=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    	time = format.format(calendar.getTime());
    	if(Common.notEmpty(entity.getFinanceTime())){
    		/*map.put("financeTime", entity.getFinanceTime());
        	int res = entity.getFinanceTime().compareTo(time);
        	if(res==0){
        		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        	}else{*/
        		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherHistoryOrganal(str,Base.financetype,entity.getFinanceTime())) );
        /*	}*/
    	}else{
      		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
    	}
        entity.setGetOperateType(Base.fun_examine);
        entity.setGroupType(grouptype);
        String dataauth=(String) session.getAttribute("gzwsession_AllFinancecompany");
        /*if(!Common.notEmpty(entity.getOrganalID()) )
        	entity.setOrganalID(systemService.getOtherOrganAuthData(str, Base.financetype));*/
        entity.setType(type);
        entity.setParentorg(str);
        MsgPage msgPage=reportService.getReportFormsOrganalList(pageNum, Base.pagesize, entity,"50500,50502");//"50500,50502"-----实体公司和壳公司

        map.put("msgPage", msgPage);
	    addData(map);
	    map.put("searchurl", "/shanghai-gzw/reportbudgetenforcement/examinelist");
	    map.put("changeFinaneUrl", "/shanghai-gzw/reportbudgetenforcement/examinelist");
	    map.put("entityview", entity);
		return "/report/budgetenforcement/budgetenforcementExamineList";
	}
	
	@RequestMapping("/list")
	public String list(ReportQueryView entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportbudgetenforcement";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer grouptype=Base.reportgroup_BudgetEnforcement;
        Integer type=Base.reportgroup_type_simple;
        HttpSession session=request.getSession();
  		String str=(String) session.getAttribute("gzwsession_financecompany");
  		
  	    //获取上月年、月
		Calendar calendar = Calendar.getInstance();
		String time=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    	time = format.format(calendar.getTime());
    	if(Common.notEmpty(entity.getFinanceTime())){
    		/*map.put("financeTime", entity.getFinanceTime());
        	int res = entity.getFinanceTime().compareTo(time);
        	if(res==0){
        		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        	}else{*/
        		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherHistoryOrganal(str,Base.financetype,entity.getFinanceTime())) );
       /* 	}*/
    	}else{
      		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
    	}
  		map.put("type", type);
        map.put("grouptype", grouptype);
        Integer pageNum = Integer.valueOf(curPageNum);
        entity.setGroupType(grouptype);
        String dataauth=(String) session.getAttribute("gzwsession_AllFinancecompany");
       /* if(!Common.notEmpty(entity.getOrganalID()) )
        	entity.setOrganalID(systemService.getOtherOrganAuthData(str, Base.financetype));*/
        entity.setParentorg(str);
        entity.setType(type);
        MsgPage msgPage=reportService.getReportFormsOrganalList(pageNum, Base.pagesize, entity,"50500,50502");//"50500,50502"-----实体公司和壳公司
        HhFile templet=  reportService.getReportExportTemplet(grouptype,Base.reportgroup_type_simple);
        map.put("templet", templet);
	    map.put("msgPage", msgPage);
	    addData(map);
	    map.put("searchurl", "/shanghai-gzw/reportbudgetenforcement/list");
	    map.put("changeFinaneUrl", "/shanghai-gzw/reportbudgetenforcement/list");
	    map.put("entityview", entity);
		return "/report/budgetenforcement/budgetenforcementList";
	}
	

	
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		List<HhBase> reportformkind= baseService.getBases(Base.reportformkind);
		List<HhBase> reportformfre= baseService.getBases(Base.reportformfre);
		map.put("examstatustype",examstatustype);
		map.put("reportformkind",reportformkind);
		map.put("reportformfre", reportformfre);
	}
		
	/**
	 * 预算未创建公司查询页面
	 * @param reportTime  上报时间
	 * @param organalID   选择的公司ID
	 * @param organalname 公司名称
	 * @param formKind    报表类型（单体OR合并）
	 * @param CompanyKind 公司类型（实体  虚拟 壳公司）
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("ysNoCreateCompanyList")
	public String ysNoCreateCompanyList(String reportTime,String organalID,String organalname,Integer formKind,Integer CompanyKind,HttpServletRequest request ,Map<String, Object> map){
		String mapurl=request.getContextPath()+ "/reportbudgetenforcement";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null || "".equals(curPageNum)) {
        	curPageNum = "1";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        if(!Common.notEmpty(reportTime)){
        	reportTime = df.format(new Date());
        }
        if(formKind == null){
        	formKind = Base.reportgroup_type_simple;
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        MsgPage msgPage=reportService.getYsNoCreateCompanyList(reportTime, str, organalID, formKind, CompanyKind, pageNum, Base.pagesize);
        map.put("msgPage", msgPage);
        map.put("pageNums", curPageNum);
	    map.put("reportTime",reportTime);
	    map.put("organalID",organalID);
	    map.put("organalname", organalname);
	    map.put("formKind", formKind);
	    map.put("CompanyKind", CompanyKind);
	    map.put("searchurl", "/shanghai-gzw/reportbudgetenforcement/ysNoCreateCompanyList");
		return "/report/budgetenforcement/ysNoCreateCompanyList";
	}
	
	@RequestMapping("ysNoCreateCompanyListExport")
	public void ysNoCreateCompanyListExport(String reportTime,
			String organalID, String organalname, Integer formKind,
			Integer CompanyKind, HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map)
			throws Exception {
		HttpSession session = request.getSession();
		String str = (String) session
				.getAttribute("gzwsession_financecompany");
		 DateFormat df = new SimpleDateFormat("yyyy-MM");
        if(!Common.notEmpty(reportTime)){
        	reportTime = df.format(new Date());
        }
        if(formKind == null){
        	formKind = Base.reportgroup_type_simple;
        }
        String formKindName = "单体";
        if(formKind ==  Base.reportgroup_type_collect){
        	formKindName = "汇总";
        }
		List result = reportService.getYsNoCreateCompanyList(reportTime, str,
				organalID, formKind, CompanyKind);
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFCell cell = null;
		XSSFSheet sheet = workBook.createSheet();
		XSSFRow row = sheet.createRow((int) 0);

		String[] header = { "公司名称", "公司简称", "公司类型", "报表类型", "时间"};
		for (int i = 0; i < header.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header[i]);
		}
		for (int i = 0; i < result.size(); i++) {
			Object[] aa = (Object[]) result.get(i);
			row = sheet.createRow(i + 1);
			int k = 0;
			for (; k < 3; k++) {
				row.createCell(k).setCellValue(
						aa[k] == null ? "" : aa[k].toString());
			}
			row.createCell(k).setCellValue(formKindName);k++;
			row.createCell(k).setCellValue(reportTime);
		}

		// 清空response
		response.reset();
		String _name = "未创建预算报表公司查询.xlsx";
		OutputStream toClient = new BufferedOutputStream(
				response.getOutputStream());
		String codedfilename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
				&& -1 != agent.indexOf("Trident")) {// ie

			String name = java.net.URLEncoder.encode(_name, "UTF8");

			codedfilename = name;
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

			codedfilename = new String(_name.getBytes("UTF-8"), "iso-8859-1");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ codedfilename);
		workBook.write(toClient);
		toClient.flush();
		toClient.close();
	}
	
	@RequestMapping("remindCompanyList")
	public String remindCompanyList(String reportTime,String organalID,String organalname,
											Integer formKind,Integer CompanyKind,HttpServletRequest request ,Map<String, Object> map){
		String mapurl=request.getContextPath()+ "/remindCompanyList";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (!Common.notEmpty(curPageNum)){
        	curPageNum = "1";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        if(!Common.notEmpty(reportTime)){
        	reportTime = df.format(new Date());
        }
        if(formKind == null){
        	formKind = Base.reportgroup_type_simple;
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        MsgPage msgPage=reportService.getRemindCompanyList(reportTime, str, organalID, formKind, CompanyKind, pageNum, Base.pagesize);
        map.put("msgPage", msgPage);
	    map.put("reportTime",reportTime);
	    map.put("organalID",organalID);
	    map.put("organalname", organalname);
	    map.put("formKind", formKind);
	    map.put("CompanyKind", CompanyKind);  
		return "/report/budgetenforcement/remindCompanyList";
	}
	
	@RequestMapping("/addRemindCompanyList")
	public String addRemindCompanyList(String reportTime,String organalID,String organalname,Integer formKind,Integer CompanyKind,HttpServletRequest request ,Map<String, Object> map){
		String mapurl=request.getContextPath()+ "/addRemindCompanyList";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null || "".equals(curPageNum)) {
        	curPageNum = "1";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        if(!Common.notEmpty(reportTime)){
        	reportTime = df.format(new Date());
        }
        if(formKind == null){
        	formKind = Base.reportgroup_type_simple;
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)));
        /*
         * 添加、删除本期提醒计划  需要提醒的公司到  report_forms_unFilled_company 表
         * nNodeID
         * updateTime
         * formKind
         */
        String orgTemp = request.getParameter("orgTemp");
        String remindKind = request.getParameter("remindKind");
        //添加
        if(null!= orgTemp &&
        		null!= reportTime &&
        		"add".equals(remindKind)){
        	ReportFormsUnFilledCompany beanNew= new ReportFormsUnFilledCompany();
        	beanNew.setnNodeID(orgTemp);
        	beanNew.setUpdatetime(reportTime);
        	beanNew.setFormKind(Base.reportUnfill_type_budget);      	
        	reportRemindCompanyService.saveUnfilledCompany(beanNew);
        }
        //删除本期提醒计划
        if(null!= orgTemp &&
        		null!= reportTime &&
        		"remove".equals(remindKind)){
        	ReportFormsUnFilledCompany beanOld= new ReportFormsUnFilledCompany();
        	beanOld.setnNodeID(orgTemp);
        	beanOld.setUpdatetime(reportTime);
        	beanOld.setFormKind(Base.reportUnfill_type_budget);      	
        	reportRemindCompanyService.setReportFormsUnFilledCompanyUnRemind(beanOld);
        }  
        MsgPage msgPage=reportService.getYsNoCreateCompanyList(reportTime, str, organalID, formKind, CompanyKind, pageNum, Base.pagesize);    
        map.put("msgPage", msgPage);
        map.put("pageNums", curPageNum);
	    map.put("reportTime",reportTime);
	    map.put("organalID",organalID);
	    map.put("organalname", organalname);
	    map.put("formKind", formKind);
	    map.put("CompanyKind", CompanyKind);
	    map.put("searchurl", "/shanghai-gzw/reportbudgetenforcement/ysNoCreateCompanyList");
		return "/report/budgetenforcement/ysNoCreateCompanyList";
	}
	
	@RequestMapping("/remindAllCompanyList")
	public String remindAllCompanyList(String reportTime,String organalID,String organalname,Integer formKind,
											String remindAllKind,Integer CompanyKind,HttpServletRequest request ,Map<String, Object> map){
		String mapurl=request.getContextPath()+ "/remindAllCompanyList";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null || "".equals(curPageNum)) {
        	curPageNum = "1";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        if(!Common.notEmpty(reportTime)){
        	reportTime = df.format(new Date());
        }
        if(formKind == null){
        	formKind = Base.reportgroup_type_simple;
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)));
        /*
         * 添加、删除所有数据到本期提醒计划  需要提醒的公司到  report_forms_unFilled_company 表
         * nNodeID
         * updateTime
         * formKind
         */
        if(null!=remindAllKind && "addAll".equals(remindAllKind)){
        	reportService.addAllDataToRemindPlan(reportTime, str, organalID, formKind, CompanyKind);
        }     
		if(null!=remindAllKind &&"removeAll".equals(remindAllKind)){
			reportRemindCompanyService.setAllDataDisRemindplan(reportTime, Base.reportUnfill_type_budget);
		}   
        MsgPage msgPage=reportService.getYsNoCreateCompanyList(reportTime, str, organalID, formKind, CompanyKind, pageNum, Base.pagesize);    
        map.put("msgPage", msgPage);
        map.put("pageNums", curPageNum);
	    map.put("reportTime",reportTime);
	    map.put("organalID",organalID);
	    map.put("organalname", organalname);
	    map.put("formKind", formKind);
	    map.put("CompanyKind", CompanyKind);
	    map.put("searchurl", "/shanghai-gzw/reportbudgetenforcement/ysNoCreateCompanyList");
		return "/report/budgetenforcement/ysNoCreateCompanyList";
	}
	

	@RequestMapping("commondDown")
	public String commondDown(String reportTime,String organalID,String organalname,
												Integer formKind,Integer CompanyKind,HttpServletRequest request ,Map<String, Object> map){
		String mapurl=request.getContextPath()+ "/commondDown";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        if(!Common.notEmpty(reportTime)){
        	reportTime = df.format(new Date());
        }
        if(formKind == null){
        	formKind = Base.reportgroup_type_simple;
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
        MsgPage msgPage=reportService.getRemindCompanyList(reportTime, str, organalID, formKind, CompanyKind, pageNum, Base.pagesize);
        /*
         * 保存需要下达的指令
         * slctPersons     接收人   员工id
         * taskDescription 接收人
         * taskName        指令名称
         * deadLine        截止日期
         */
        TaskInstruction instruction = new TaskInstruction();
        String InstructionTitle ="预算未填报公司提醒("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+")"; 
        List sendEmails = new ArrayList<Object[]>(); //已发送邮件
        Map backAccounyInfo = new HashMap<String,Object>();//返回选中发送人账号和账号描述信息
		com.softline.client.task.HhUsers sysUsers = new com.softline.client.task.HhUsers();
        // 设置指令中心新加指令参数
        reportRemindCompanyService.setInstructionPara(instruction,session,sysUsers,InstructionTitle);
		//获取要下达指令员工账号信息，返回未获取到的信息	
		reportService.getInstractionVcAccount(msgPage.getList(),sendEmails,backAccounyInfo);	
		//返回页面要显示数据
		//调用远程接口，保存发送指令任务
		if(null!=backAccounyInfo.get("sBSlctPersons")){
			instruction.setSlctPersons(String.valueOf(backAccounyInfo.get("sBSlctPersons")));
			taskService.save(instruction,sysUsers,
									     String.valueOf(backAccounyInfo.get("sBSlctPersons")),
									     String.valueOf(backAccounyInfo.get("sBAccountNameDeps")));
		}		
		//更新发送公司的临时表，设置已发送的标志位
		reportRemindCompanyService.updateReportUnfilledCompanyTable(backAccounyInfo,reportTime,Base.reportUnfill_type_budget);
		map.put("msgPage", reportService.getRemindCompanyList(reportTime, str, organalID, formKind, CompanyKind, pageNum, Base.pagesize));
	    map.put("reportTime",reportTime);
	    map.put("pageNums", curPageNum);
	    map.put("backInfo", true);//返回提醒信息    
		return "/report/budgetenforcement/remindCompanyList";
	}
	
	/**
	 * 跳转页面
	 * 
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value = "/leveltree")
	public String leveltree(Integer type,Map<String ,Object> map,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
			map.put("type", type);	
			List<HhBase> statustype= baseService.getBases(Base.report_organal);
	    	map.put("statustype",statustype);
			
	    	WsSystemServiceService wsService = new WsSystemServiceService();
			IWsSystemService iw = wsService.getPort(IWsSystemService.class);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str=iw.getGSCompanyData();
			List<BimcCompany> data=JSON.parseArray(str, BimcCompany.class);
	    	
			map.put("BimaCompany",data);
			return "/report/budgetenforcement/companyLevel";
	}
	
	/**
	 * 生成公司组织架构树
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/_getleveltree", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void basicInfo_getleveltree(String userId, Integer type, String usersEmployeeId,String time,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		String json = "";
        HttpSession session=request.getSession();
  		String str=(String) session.getAttribute("gzwsession_financecompany");
		if(Common.notEmpty(time)){
			CompanyLevelTree leveltree = treeService.getHistoryTreeByOrganStr(type,str,time);
			json = JSONArray.toJSONString(leveltree);
		}else{
			CompanyLevelTree leveltree = treeService.getTreeByOrganStr(type,str);
			json = JSONArray.toJSONString(leveltree);		
		}
		response.getWriter().write(json);
	}
	
}
