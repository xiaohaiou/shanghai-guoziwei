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

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.softline.client.task.TaskInstruction;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFormsUnFilledCompany;
import com.softline.entityTemp.ReportQueryView;
import com.softline.service.report.IReportRemindCompanyService;
import com.softline.service.report.IReportService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ISystemService;
import com.softline.service.task.ITaskService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportadjust")
public class ReportAdjustController {

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
		String mapurl=request.getContextPath()+ "/reportadjust";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer grouptype=Base.reportgroup_adjust;
        Integer type=Base.reportgroup_type_simple;
        Integer pageNum = Integer.valueOf(curPageNum);
        map.put("grouptype", grouptype);
        map.put("type", type);
        HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        
        entity.setGetOperateType(Base.fun_examine);
        entity.setGroupType(grouptype);
        //数据权限
        /*String dataauth=(String) session.getAttribute("gzwsession_AllFinancecompany");
          if(!Common.notEmpty(entity.getOrganalID()) )
        	entity.setOrganalID(systemService.getOtherOrganAuthData(str, Base.financetype));*/
        entity.setParentorg(str);
        entity.setType(type);
        MsgPage msgPage=reportService.getReportFormsOrganalList(pageNum, Base.pagesize, entity,null);
	    map.put("msgPage", msgPage);
	    addData(map);
	    map.put("searchurl", "/shanghai-gzw/reportadjust/examinelist");
	    map.put("entityview", entity);
		return "/report/adjust/adjustExamineList";
	}
	
	@RequestMapping("/list")
	public String list(ReportQueryView entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportadjust";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer grouptype=Base.reportgroup_adjust;
        Integer type=Base.reportgroup_type_simple;
        HttpSession session=request.getSession();
  		String str=(String) session.getAttribute("gzwsession_financecompany");
  		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
  		map.put("grouptype", grouptype);
        map.put("type", type);
        Integer pageNum = Integer.valueOf(curPageNum);
        entity.setGroupType(grouptype);
        //String dataauth=(String) session.getAttribute("gzwsession_AllFinancecompany");
       /* if(!Common.notEmpty(entity.getOrganalID()) )
        	entity.setOrganalID(systemService.getOtherOrganAuthData(str, Base.financetype));*/
        entity.setType(type);
        entity.setParentorg(str);
        MsgPage msgPage=reportService.getReportFormsOrganalList(pageNum, Base.pagesize, entity,null);
        HhFile templet=  reportService.getReportExportTemplet(grouptype,Base.reportgroup_type_simple);
        map.put("templet", templet);
	    map.put("msgPage", msgPage);
	    addData(map);
	    map.put("searchurl", "/shanghai-gzw/reportadjust/list");
	    map.put("entityview", entity);
		return "/report/adjust/adjustList";
	}
	
	@RequestMapping("/adjustTemplet")
	public String adjustTemplet(String Date,HttpServletRequest request ,Map<String, Object> map){
		
		if(StringUtils.isBlank(Date) ){
			DateFormat df = new SimpleDateFormat("yyyy-MM");
			Date = df.format(new Date());
		}
		String[] aa = Date.split("-");
		Integer year = Integer.valueOf(aa[0]);
		Integer month = Integer.valueOf(aa[1]);
		 HhFile templet = reportService.getReportExportAdjustTemplet(year, month);
		 map.put("templet", templet);
		 map.put("Date", Date);
		return "report/adjust/adjustTemplet";
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
	 * 查询未创建核算报表的公司，提供的查询条件有：上报时间，公司orgId
	 * 在某个上报时间中，某某公司下没有创建核算报表的公司，包括本公司，公司不填默认为权限下的公司
	 * @param reportTime
	 * @param org
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/noCreateCompanyList")
	public String noCreateCompanyList(String reportTime,String organalID,String organalname,HttpServletRequest request ,Map<String, Object> map) throws IOException {

		String mapurl=request.getContextPath()+ "/reportadjust";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null || "".equals(curPageNum)) {
        	curPageNum = "1";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        if(!Common.notEmpty(reportTime)){
        	reportTime = df.format(new Date());
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        /*
         * remindKind 通过remindKind 判断提醒公司的操作
         *  1、add 添加单个公司提醒    2、remove 删除单个公司提醒    3、addAll 添加所有公司提醒
         *  4、removeAll 删除所有提醒公司
         *  
         *  remindOrg 提醒公司编号 
         */
        String remindKind = request.getParameter("remindKind");
        String remindOrg  = request.getParameter("remindOrg");
        if(Common.isNumber(remindKind) && 
        		Common.notEmpty(remindOrg) && Common.notEmpty(reportTime)){
        	ReportFormsUnFilledCompany reportFormsUnFilledCompany = new ReportFormsUnFilledCompany();
        	reportFormsUnFilledCompany.setFormKind(Base.reportUnfill_type_account);
        	reportFormsUnFilledCompany.setUpdatetime(reportTime);
        	reportFormsUnFilledCompany.setnNodeID(remindOrg);
       		switch(Integer.valueOf(remindKind)){
       			case 1:
       				reportRemindCompanyService.saveUnfilledCompany(reportFormsUnFilledCompany);
       				break;
       			case 2:
       				reportRemindCompanyService.deleteReportFormsUnFilledCompany(reportFormsUnFilledCompany);
       				break;
       			case 3:
       				//查询的历史树   hh_organInfo_tree_relation_history
       				String treeTab = "hh_organInfo_tree_relation_history";
       				reportRemindCompanyService.addAllDataToRemindPlan(reportTime,str, Base.reportUnfill_type_account,treeTab);
       				break;
       			case 4:
       				reportRemindCompanyService.setAllDataDisRemindplan(reportTime, Base.reportUnfill_type_account);
       				break;
       			default:
       				break;
        	}
        }
        MsgPage msgPage=reportService.getHsNoCreateCompanyList(reportTime, str,organalID,pageNum,Base.pagesize);
        map.put("msgPage", msgPage);
	    map.put("reportTime",reportTime);
	    map.put("organalID",organalID);
	    map.put("organalname", organalname);
	    map.put("pageNums", curPageNum);
	    map.put("searchurl", "/shanghai-gzw/reportadjust/noCreateCompanyList");
		return "/report/adjust/noCreateCompanyList";
	}
		
	@RequestMapping(value="/noCreateExport")
	public void noCreateexport1(String reportTime,String organalID,String organalname,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) throws IOException, ParsePropertyException, InvalidFormatException {
		 HttpSession session=request.getSession();
	     String str=(String) session.getAttribute("gzwsession_financecompany");
	     List result =  reportService.getHsNoCreateCompanyList(reportTime, str, organalID);
		 XSSFWorkbook workBook = new XSSFWorkbook();
		 XSSFCell cell = null;
		 XSSFSheet sheet = workBook.createSheet("123");
		 XSSFRow row = sheet.createRow((int) 0);  
		 String [] header={"公司名称","公司简称","时间"};
		 for (int i = 0; i < header.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header[i]);
		 }
		 for (int i = 0; i < result.size(); i++) {  
		 	int k=0;
		     row = sheet.createRow((int) i + 1);
		     Object[] value = (Object[]) result.get(i);
	//				     row.createCell((short)k++).setCellValue(i+1);
		     if (value[0]!=null) {
		    	 row.createCell((short)k++).setCellValue((String)value[0]);
	//					      单位名称
			}else {
				row.createCell((short)k++).setCellValue("");
			}
		     if (value[1]!=null) {
		    	 row.createCell((short)k++).setCellValue((String)value[1]);
	//					      单位名称
			}else {
				row.createCell((short)k++).setCellValue("");
			}
		     
		     if (reportTime!=null) {
		    	 row.createCell((short)k++).setCellValue(reportTime);
	//					      单位名称
			}else {
				row.createCell((short)k++).setCellValue("");
			}
		 }
			     // 清空response
		        response.reset();
		        String _name = "未创建核算报表公司查询.xlsx";
		        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		        String codedfilename = null;
				String agent = request.getHeader("USER-AGENT");
				if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
						&& -1 != agent.indexOf("Trident")) {// ie

					String name = java.net .URLEncoder.encode(_name, "UTF8");

					codedfilename = name;
				} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

					codedfilename = new String(_name.getBytes("UTF-8"),
							"iso-8859-1");
				}
		        response.setContentType("application/octet-stream");
		        response.setHeader("Content-Disposition", "attachment;filename=" + codedfilename);
		        workBook.write(toClient);
		        toClient.flush();
		        toClient.close(); 
	}
	
	
	@RequestMapping("/remindCompanyList")
	public String remindCompanyList(String reportTime,String organalID,String organalname,
							Integer formKind,Integer CompanyKind,HttpServletRequest request ,Map<String, Object> map) throws IOException {

		String mapurl=request.getContextPath()+ "/reportadjust";
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
        MsgPage msgPage=reportService.getAdjustAccountRemindCompanyList(reportTime, str,organalID,pageNum,Base.pagesize,null);
        map.put("msgPage", msgPage);
        map.put("pageNums", curPageNum);
  	    map.put("reportTime",reportTime);
  	    map.put("organalID",organalID);
  	    map.put("organalname", organalname);
  	    map.put("formKind", formKind);
  	    map.put("CompanyKind", CompanyKind);  
		return "/report/adjust/adjustRemindCompanyList";
	}

	@RequestMapping("commondDown")
	public String commondDown(String reportTime,String organalID,String organalname,
												Integer formKind,Integer CompanyKind,HttpServletRequest request ,Map<String, Object> map){
		String mapurl=request.getContextPath()+ "/remindCreateCompanyList";
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
        
        MsgPage msgPage=reportService.getAdjustAccountRemindCompanyList(reportTime, str,organalID,pageNum,Base.pagesize,"all");

        /*
         * 保存需要下达的指令
         * slctPersons     接收人   员工id
         * taskDescription 接收人
         * taskName        指令名称
         * deadLine        截止日期
         */
        TaskInstruction instruction = new TaskInstruction();
        String InstructionTitle ="核算未填报公司提醒("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+")"; 
        List unEmailedCompanyList = new ArrayList<Object[]>(); //未提醒公司
        Map backAccounyInfo = new HashMap<String,Object>();//返回选中发送人账号和账号描述信息
        com.softline.client.task.HhUsers sysUsers = new com.softline.client.task.HhUsers();
       
        // 设置指令中心新加指令参数
        reportRemindCompanyService.setInstructionPara(instruction,session,sysUsers,InstructionTitle);
		//获取要下达指令员工账号信息，返回未获取到的信息	
        reportRemindCompanyService.getInstractionVcAccount_account(msgPage.getList(),unEmailedCompanyList,backAccounyInfo);		
		//保存发送指令任务
		if(null!=backAccounyInfo.get("sBSlctPersons")){
			instruction.setSlctPersons(String.valueOf(backAccounyInfo.get("sBSlctPersons")));
			taskService.save(instruction,
						     sysUsers,
						     String.valueOf(backAccounyInfo.get("sBSlctPersons")),
						     String.valueOf(backAccounyInfo.get("sBAccountNameDeps")));
		}		
		//更新发送公司的临时表，设置已发送的标志位
		reportRemindCompanyService.updateReportUnfilledCompanyTable(backAccounyInfo,reportTime,Base.reportUnfill_type_account);
		//页面需要显示的数据
		MsgPage msgPageForShow=reportService.getAdjustAccountRemindCompanyList(reportTime, str,organalID,pageNum,Base.pagesize,null);
	    map.put("msgPage", msgPageForShow);
	    map.put("reportTime", reportTime);
	    map.put("backInfo", true);
		return "/report/adjust/adjustRemindCompanyList";
	}
	
}
