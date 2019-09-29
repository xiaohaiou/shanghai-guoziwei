package com.softline.controller.report;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.ExcelDataTreating;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.SysExamine;
import com.softline.entity.moneyFlow.DataMoneyFlowMonth;
import com.softline.entity.moneyFlow.DataMoneyFlowMonthCi;
import com.softline.entity.moneyFlow.DataMoneyFlowMonthCo;
import com.softline.entity.moneyFlow.DataMoneyFlowMonthIo;
import com.softline.entity.moneyFlow.DataMoneyFlowWeek;
import com.softline.entity.moneyFlow.DataMoneyFlowWeekCi;
import com.softline.entity.moneyFlow.DataMoneyFlowWeekCo;
import com.softline.entity.moneyFlow.DataMoneyFlowWeekIo;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IMoneyFlowPlanMonthService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/moneyFlowPlanMonth")
/**
 * 
 * @author ky_tian
 *
 */
public class MoneyFlowPlanMonthController {

	@Resource(name = "moneyFlowPlanMonthService")
	private IMoneyFlowPlanMonthService moneyFlowPlanMonthService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	
	/**
	 * 查询条件-结果
	 */
	@RequestMapping("/list")
	public String hrlist(DataMoneyFlowMonth entity,HttpServletRequest request ,Map<String, Object> map,String time) {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);
		//entity.setOrg(systemService.getAllChildrenFinanceOrganal(str,Base.financetype));
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );

		String mapurl=request.getContextPath()+ "/moneyFlowPlanMonth";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=moneyFlowPlanMonthService.findPageList(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/moneyFlowPlanMonth/list");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
	    addData(map);
		return "/report/moneyFlowPlanMonth/moneyFlowPlanMonthFormList";
	}

	/**
	 * 获取moneyFlowPlanMonth
	 * @param id
	 * @return
	 */
	@RequestMapping("/get")
	@ResponseBody
	public DataMoneyFlowMonth get(String pid) {
		DataMoneyFlowMonth dh=moneyFlowPlanMonthService.get(Integer.valueOf(pid));
		return dh;
	}
	
	/**
	 * 添加信息
	 * @param map
	 */
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		map.put("currencyKind",JSONArray.toJSONString(baseService.getBases(Base.currencyKind)));//币种
		map.put("trueOrFalse",JSONArray.toJSONString(baseService.getBases(Base.truefalse)));//是否
		map.put("sequelNew",JSONArray.toJSONString(baseService.getBases(Base.sequelNew)));//续作新增
		map.put("financingType",JSONArray.toJSONString(baseService.getBases(Base.financingType)));//融资类型
		map.put("investType",JSONArray.toJSONString(baseService.getBases(Base.investType)));//投资类型
		map.put("examstatustype",examstatustype);
	}
	
	/**
	 * 保存hr数据
	 */
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveOrUpdate(DataMoneyFlowMonth entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		entity = setDataMoneyFlowMonthX(entity);
		return JSONArray.toJSONString(moneyFlowPlanMonthService.saveMoneyFlowPlanMonth(entity, use ,"save"));
	}
	
	/**
	 * 修改hr数据
	 */
	
	@RequestMapping(value="/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(DataMoneyFlowMonth entity,HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DataMoneyFlowMonth entityTemp=moneyFlowPlanMonthService.get(entity.getPid());
		entityTemp.setStatus(Base.examstatus_1);
		entity = setDataMoneyFlowMonthX(entity);
		entity = this.setData(entityTemp,entity);
		return JSONArray.toJSONString(moneyFlowPlanMonthService.updateMoneyFlowPlanMonth(entity, use ,Base.examstatus_1));
	}
	
	/**
	 * 保存并上报
	 */
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(DataMoneyFlowMonth entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DataMoneyFlowMonth entityTemp = new DataMoneyFlowMonth();
		CommitResult result = new CommitResult();
		if(entity.getPid()!=null){
			entityTemp=moneyFlowPlanMonthService.get(entity.getPid());
			entityTemp.setStatus(Base.examstatus_2);
			entity = setDataMoneyFlowMonthX(entity);
			entity = this.setData(entityTemp,entity);
			result= moneyFlowPlanMonthService.saveMoneyFlowPlanMonth(entity, use , "saveReport");
		}else{
			entity.setStatus(Base.examstatus_2);
			entity = setDataMoneyFlowMonthX(entity);
			result= moneyFlowPlanMonthService.saveMoneyFlowPlanMonth(entity, use , "saveReport");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 查询页上报
	 * @param id
	 * @param request
	 * @param map
	 * @param op
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/postexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _postexam(Integer pid,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DataMoneyFlowMonth entity=moneyFlowPlanMonthService.get(pid);
		entity.setStatus(Base.examstatus_2);
		CommitResult result=moneyFlowPlanMonthService.saveMoneyFlowPlanMonth(entity, use , "report");
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 新增弹窗
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/newmodify")
	public String hrNewModify(DataMoneyFlowMonth entity, HttpServletRequest request ,Map<String, Object> map) {
		HttpSession session=request.getSession();
		//树列表信息
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		
		if(entity.getPid()==null){	
			entity=new DataMoneyFlowMonth();
			DateFormat df = new SimpleDateFormat("yyyy-MM");
			Date date = new Date();
			entity.setDate(df.format(date));
			/*entity.setOrg(org);
			entity.setOrgname(orgname);
			entity.setParentCompany(parentCompany);
			entity.setParentOrganalId(parentOrganalId);*/
		}
		else{
			map.put("op", "modify");
			map.put("monthDate", Base.monthDate);
			entity=moneyFlowPlanMonthService.get(entity.getPid());
			entity.setDate(entity.getDate());
		}
		map.put("entityview", entity);
		map.put("entityList", JSON.toJSONString(entity));
		addData(map);
		return "/report/moneyFlowPlanMonth/moneyFlowPlanMonthFormNewModify";
	}
	
	/**
	 * 跳转新增修改页面
	 * @param id
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/view")
	public String _view(Integer pid,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		DataMoneyFlowMonth entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(pid==null)
		{	
			entity=new DataMoneyFlowMonth();
		}else{
			entity=moneyFlowPlanMonthService.get(pid);
		    a= examineService.getListExamine(entity.getPid(), Base.examkind_hr_moneyFlowPlanMonth);
		}	
		map.put("entity", entity);
		map.put("entityList", JSON.toJSONString(entity));
		map.put("entityExamineviewlist", a);
		map.put("op", "view");
		addData(map);
		return "/report/moneyFlowPlanMonth/moneyFlowPlanMonthView";
	}
	
	/**
	 * 跳转上报页面
	 * @param id
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/report")
	public String report(Integer pid,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/moneyFlowPlanMonth";
		map.put("mapurl", mapurl);
		DataMoneyFlowMonth entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(pid==null)
		{	
			entity=new DataMoneyFlowMonth();
		   
		}
		else
		{
			entity=moneyFlowPlanMonthService.get(pid);
		}	
		a= examineService.getListExamine(entity.getPid(), Base.examkind_hr_moneyFlowPlanMonth);
		map.put("entity", entity);
		map.put("entityList", JSON.toJSONString(entity));
		map.put("entityExamineviewlist", a);
		map.put("op", "report");
		addData(map);
		return "/report/moneyFlowPlanMonth/moneyFlowPlanMonthView";
	}
	
	/**
	 * 审核的列表页面
	 * @param entity
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/examinelist")
	public String _examinelist(DataMoneyFlowMonth entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);
		//entity.setOrg(systemService.getAllChildrenFinanceOrganal(str,Base.financetype));
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		String mapurl=request.getContextPath()+ "/moneyFlowPlanMonth";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=moneyFlowPlanMonthService.findExaminePageList(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/moneyFlowPlanMonth/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/report/moneyFlowPlanMonth/moneyFlowPlanMonthExamineList";
	}
	
	/**
	 * 审核
	 * @param id
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value ="/exam")
	public String _exam(Integer pid,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		DataMoneyFlowMonth entity;
		if(pid==null)
		{
			entity=new DataMoneyFlowMonth();
		}
		else
			entity=moneyFlowPlanMonthService.get(pid);
		Integer status=0;
		status=entity.getStatus();
		map.put("status", status);
		map.put("entity", entity);
		map.put("entityList", JSON.toJSONString(entity));
		addData(map);
		return "/report/moneyFlowPlanMonth/moneyFlowPlanMonthExamine";
	}
	
	/**
	 * 审核提交
	 * @param entityid
	 * @param examStr
	 * @param examResult
	 * @param request
	 * @param map
	 * @param op
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result= moneyFlowPlanMonthService.saveMoneyFlowPlanMonthExamine(entityid, examStr, examResult, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String reportgroupdelete(DataMoneyFlowMonth entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		CommitResult result = new CommitResult();
		if(moneyFlowPlanMonthService.get(entity)){
			result=moneyFlowPlanMonthService.deleteMoneyFlowPlanMonth(entity.getPid(), use);
		}else{
			result.setResult(false); 
		}
		data=JSONArray.toJSONString(result);
		return data;
	}
	/*
	 * chack
	 */
	@RequestMapping("/chackGet")
	@ResponseBody
	public String get(DataMoneyFlowMonth entity) {
		CommitResult result = new CommitResult();
		String data="";
		if(moneyFlowPlanMonthService.get(entity)){
			result.setResult(true); 
		}else{
			result.setResult(false); 
		}
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	public DataMoneyFlowMonth setData(DataMoneyFlowMonth entityTemp,DataMoneyFlowMonth entity){
		//entity.setDate(entity.getDate());
		entity.setPid(entityTemp.getPid());
		entity.setStatus(entityTemp.getStatus());
		entity.setExamineTime(entityTemp.getExamineTime());
		entity.setIsdel(entityTemp.getIsdel());
		entity.setReportPersonName(entityTemp.getReportPersonName());
		entity.setReportPersonId(entityTemp.getReportPersonId());
		entity.setReportDate(entityTemp.getReportDate());
		entity.setAuditorPersonName(entityTemp.getAuditorPersonName());
		entity.setAuditorPersonId(entityTemp.getAuditorPersonId());
		entity.setAuditorDate(entityTemp.getAuditorDate());
		entity.setCreatePersonName(entityTemp.getCreatePersonName());
		entity.setCreatePersonId(entityTemp.getCreatePersonId());
		entity.setCreateDate(entityTemp.getCreateDate());
		entity.setLastModifyPersonId(entityTemp.getLastModifyPersonId());
		entity.setLastModifyPersonName(entityTemp.getLastModifyPersonName());
		entity.setLastModifyDate(entityTemp.getLastModifyDate());
		return entity;
	}

	public DataMoneyFlowMonth setDataMoneyFlowMonthX(DataMoneyFlowMonth entity){
		if(entity.getDataMoneyFlowMonthCiTemp()!=null&&entity.getDataMoneyFlowMonthCiTemp().getCommitmentShallSubject()!=null){
			String [] ciArray = entity.getDataMoneyFlowMonthCiTemp().getCommitmentShallSubject().split(",");
			for(int i = 0;i<ciArray.length;i++){
				DataMoneyFlowMonthCi ci = new DataMoneyFlowMonthCi();
				ci.setCommitmentShallSubject(ciArray[i]);
				ci.setLendingBank(entity.getDataMoneyFlowMonthCiTemp().getLendingBank().split(",")[i]);
				ci.setLoanAmount(entity.getDataMoneyFlowMonthCiTemp().getLoanAmount().split(",")[i]);
				ci.setPlaceOrderDate(entity.getDataMoneyFlowMonthCiTemp().getPlaceOrderDate().split(",")[i]);
				ci.setFinancingType(entity.getDataMoneyFlowMonthCiTemp().getFinancingType().split(",")[i]);
				ci.setNewOrRenewed(entity.getDataMoneyFlowMonthCiTemp().getNewOrRenewed().split(",")[i]);
				ci.setPlaceOrderCurrency(entity.getDataMoneyFlowMonthCiTemp().getPlaceOrderCurrency().split(",")[i]);
				ci.setCompositeCost(entity.getDataMoneyFlowMonthCiTemp().getCompositeCost().split(",")[i]);
				ci.setEquityFinancing(entity.getDataMoneyFlowMonthCiTemp().getEquityFinancing().split(",")[i]);
				ci.setDate(entity.getDate());
				ci.setOrg(entity.getOrg());
				ci.setOrgname(entity.getOrgname());
				ci.setIsdel(0);
				entity.getDataMoneyFlowMonthCi().add(ci);
			}
		}
		if(entity.getDataMoneyFlowMonthCoTemp()!=null&&entity.getDataMoneyFlowMonthCoTemp().getTheLoanBusiness()!=null){
			String [] coArray = entity.getDataMoneyFlowMonthCoTemp().getTheLoanBusiness().split(",");
			for(int i = 0;i<coArray.length;i++){
				DataMoneyFlowMonthCo co = new DataMoneyFlowMonthCo();
				co.setTheLoanBusiness(coArray[i]);
				co.setRepaymentAmount(entity.getDataMoneyFlowMonthCoTemp().getRepaymentAmount().split(",")[i]);
				co.setDateOfPayment(entity.getDataMoneyFlowMonthCoTemp().getDateOfPayment().split(",")[i]);
				co.setPaymentType(entity.getDataMoneyFlowMonthCoTemp().getPaymentType().split(",")[i]);
				co.setFinancingInstitution(entity.getDataMoneyFlowMonthCoTemp().getFinancingInstitution().split(",")[i]);
				co.setNewOrRenewedCo(entity.getDataMoneyFlowMonthCoTemp().getNewOrRenewedCo().split(",")[i]);
				co.setPaymentCurrency(entity.getDataMoneyFlowMonthCoTemp().getPaymentCurrency().split(",")[i]);
				co.setGuaranteeRepaymentProject(entity.getDataMoneyFlowMonthCoTemp().getGuaranteeRepaymentProject().split(",")[i]);
				co.setGuaranteeRepaymentPlan(entity.getDataMoneyFlowMonthCoTemp().getGuaranteeRepaymentPlan().split(",")[i]);
				co.setDate(entity.getDate());
				co.setOrg(entity.getOrg());
				co.setOrgname(entity.getOrgname());
				co.setIsdel(0);
				entity.getDataMoneyFlowMonthCo().add(co);
			}
		}
		if(entity.getDataMoneyFlowMonthIoTemp()!=null&&entity.getDataMoneyFlowMonthIoTemp().getUnitOfInvestment()!=null){
			String [] ioArray = entity.getDataMoneyFlowMonthIoTemp().getUnitOfInvestment().split(",");
			for(int i = 0;i<ioArray.length;i++){
				DataMoneyFlowMonthIo io = new DataMoneyFlowMonthIo();
				io.setUnitOfInvestment(ioArray[i]);
				io.setDateOfInvestment(entity.getDataMoneyFlowMonthIoTemp().getDateOfInvestment().split(",")[i]);
				io.setNameOfInvestmentProject(entity.getDataMoneyFlowMonthIoTemp().getNameOfInvestmentProject().split(",")[i]);
				io.setInvestmentAmount(entity.getDataMoneyFlowMonthIoTemp().getInvestmentAmount().split(",")[i]);
				io.setSpecialFundGuaranteeScheme(entity.getDataMoneyFlowMonthIoTemp().getSpecialFundGuaranteeScheme().split(",")[i]);
				io.setTheInvestmentCurrency(entity.getDataMoneyFlowMonthIoTemp().getTheInvestmentCurrency().split(",")[i]);
				io.setInvestmentType(entity.getDataMoneyFlowMonthIoTemp().getInvestmentType().split(",")[i]);
				io.setGuaranteeRepaymentPlan(entity.getDataMoneyFlowMonthIoTemp().getGuaranteeRepaymentPlan().split(",")[i]);
				io.setDate(entity.getDate());
				io.setOrg(entity.getOrg());
				io.setOrgname(entity.getOrgname());
				io.setIsdel(0);
				entity.getDataMoneyFlowMonthIo().add(io);
			}
		}
		return entity;
	}
	
	/**
	 * 解析报表
	 */
	@RequestMapping(value="/excelReport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String excelReport(@RequestParam("excelFile") MultipartFile file,String fileType,String excelName) throws IOException {
		Map<String,List<ArrayList<String>>> excelMap = new HashMap<String,List<ArrayList<String>>>();
		ArrayList<String> colList = null;
		List<ArrayList<String>> rowList=new ArrayList<ArrayList<String>>();
		ExcelDataTreating tool = new ExcelDataTreating();
		if("s".equals(excelName)){
			try {
				// 一 使用文件输入流 读取 xls文件  
		        InputStream inp = file.getInputStream(); 
		        // 二 获得文档对象  
		        POIFSFileSystem fileSystem = new POIFSFileSystem(inp);  
		        HSSFWorkbook wb = new HSSFWorkbook(fileSystem);  
		        // 三 获得sheet对象  
		        HSSFSheet sheet = wb.getSheetAt(0);  
		        // 操作数据  
		        Iterator<Row> rowIterator = sheet.rowIterator(); 
		        int i = 1;
		        while(rowIterator.hasNext()){  
		        	if((i++)==1){//跳过表头
		        		continue; 
		        	}
		            Row row = rowIterator.next();  
		            if(row==null||row.getFirstCellNum()==i){continue;}  
	            	if("".equals(this.getCellValue(row.getCell(0)))){continue;}//如果序号为空，则判定这一行没有数据
		            Iterator<Cell> cellIterator = row.cellIterator(); 
		            colList = new ArrayList<String>();
		            while(cellIterator.hasNext()){ 
		                Cell cell = cellIterator.next(); 
		                if(cell.getCellType()==Cell.CELL_TYPE_STRING){  
		                    System.out.print(cell.getStringCellValue() + ",");
		                    colList.add(cell.getStringCellValue()+"");
		                }else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
		                	if(DateUtil.isCellDateFormatted(cell)){
		                        //用于转化为日期格式
		                        Date d = cell.getDateCellValue();
		                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		                        colList.add(formater.format(d)+"");
		                        System.out.print(formater.format(d)+",");
	                        }else{
		                    	System.out.print(cell.getNumericCellValue()+",");
		 	                    colList.add(cell.getNumericCellValue()+"");//this.getHValue(cell).trim()
	                        }
		                }
		            }
		            rowList.add(colList);
		            System.out.println();
		        }  	
		        excelMap.put(fileType,rowList);
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }  
			return JSONArray.toJSONString(excelMap);
		}else if("sx".equals(excelName)){
			return tool.readXlsx(file,fileType);
		}else{
		}
		return "";
	}
	
	/**
	 * 导出报表
	 */
	@RequestMapping(value="/excelOutput", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String excelOutput(String pid,Integer numType,HttpServletResponse res) throws IOException {
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = null;
		HSSFRow row = null;
		//导出文件名称
		String fileName = "xxx";
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		if(numType==1){
			sheet = wb.createSheet("筹资性流入明细列表");
			// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
			row = sheet.createRow((int) 0);
			fileName="筹资性流入明细列表";
			String [] titleArray = {"承贷主体","贷款银行","贷款金额（万元）","计划下账日期","融资类型","新增或续作","下账币种","综合成本（%）","是否权益性融资"};
			row = tool.setPlanMonthExcelTitle(styleList.get(0),row,numType,titleArray);
		}else if(numType==2){
			sheet = wb.createSheet("筹资性流出明细列表");
			// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
			row = sheet.createRow((int) 0);
			fileName="筹资性流出明细列表";
		String [] titleArray = {"操作单位","还贷日期","金融机构","还贷类型","还款金额(万元)","是否续作","还款币种","保障还款项目","是否有保障还款计划"};
			row = tool.setPlanMonthExcelTitle(styleList.get(0),row,numType,titleArray);
		}else if(numType==3){
			sheet = wb.createSheet("投资性流出明细列表");
			// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
			row = sheet.createRow((int) 0);
			fileName="投资性流出明细列表";
			String [] titleArray = {"投资单位","投资日期","投资项目名称","投资金额（万元）","投资币种","专项资金保障方案","投资类型","是否有保障还款计划"};
			row = tool.setPlanMonthExcelTitle(styleList.get(0),row,numType,titleArray);
		}
		//存放excel下拉值
		List<List<String[]>> list = this.createSelect();
		DataMoneyFlowMonth dh = new DataMoneyFlowMonth();
		if(Common.notEmpty(pid)){
			dh=moneyFlowPlanMonthService.get(Integer.valueOf(pid));
			sheet = tool.setPlanMonthExcelData(sheet,numType,dh,styleList.get(1),list);
		}else{
			sheet = tool.setPlanMonthExcelData(sheet,numType,dh,styleList.get(1),list);
		}
		tool.outputExcel(wb,fileName,res);
		return null;
	}
	
	/**
	 * 查询下拉框
	 */
	public List<List<String[]>> createSelect(){
		List<String[]> selectList = new ArrayList<String[]>();
		List<String[]> idList = new ArrayList<String[]>();
		List<List<String[]>> list = new ArrayList<List<String[]>>();
		// 币种
		List<HhBase> currencyKind= baseService.getBases(Base.currencyKind);
		String[] arr0 = new String[currencyKind.size()];
		String[] id0 = new String[currencyKind.size()];
	    int i = 0;
	    for(HhBase base:currencyKind) {
	    	id0[i]=String.valueOf(base.getId());
	    	arr0[i++]=base.getDescription();
	    }
		// 是否
	    List<HhBase> truefalse= baseService.getBases(Base.truefalse);
	    String[] arr1 = new String[truefalse.size()];
	    String[] id1 = new String[currencyKind.size()];
	    int t = 0;
	    for(HhBase base:truefalse) {
	    	id1[t]=String.valueOf(base.getId());
	    	arr1[t++]=base.getDescription();
	    }
		// 续作新增
	    List<HhBase> sequelNew= baseService.getBases(Base.sequelNew);
	    String[] arr2 = new String[sequelNew.size()];
	    String[] id2 = new String[currencyKind.size()];
	    int k = 0;
	    for(HhBase base:sequelNew) {
	    	id2[k]=String.valueOf(base.getId());
	    	arr2[k++]=base.getDescription();
	    }
	    // 融资类型
	    List<HhBase> financingType= baseService.getBases(Base.financingType);
	    String[] arr3 = new String[financingType.size()];
	    String[] id3 = new String[currencyKind.size()];
	    int y = 0;
	    for(HhBase base:financingType) {
	    	id3[y]=String.valueOf(base.getId());
	    	arr3[y++]=base.getDescription();
	    }
	 	// 投资类型
	    List<HhBase> investType= baseService.getBases(Base.investType);
	    String[] arr4 = new String[investType.size()];
	    String[] id4 = new String[currencyKind.size()];
	    int a = 0;
	    for(HhBase base:investType) {
	    	id4[a]=String.valueOf(base.getId());
	    	arr4[a++]=base.getDescription();
	    }
	    //贷款类型
		List<HhBase> loanType= baseService.getBases(Base.loanType);
		String[] arr5 = new String[loanType.size()];
		String[] id5 = new String[currencyKind.size()];
	    int j = 0;
	    for(HhBase base:loanType) {
	    	id5[j]=String.valueOf(base.getId());
	    	arr5[j++]=base.getDescription();
	    }
	    idList.add(id0);
	    idList.add(id1);
	    idList.add(id2);
	    idList.add(id3);
	    idList.add(id4);
	    idList.add(id5);
	    selectList.add(arr0);
	    selectList.add(arr1);
	    selectList.add(arr2);
	    selectList.add(arr3);
	    selectList.add(arr4);
	    selectList.add(arr5);
	    list.add(idList);
	    list.add(selectList);
		return list;
	}
	
	//跳转到公司汇总页面
	@RequestMapping("/g_query")
	public String g_query(DataMoneyFlowMonth entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/moneyFlowPlanMonth";
		DataMoneyFlowMonth tempBean = new DataMoneyFlowMonth();
		
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
        Integer pageNum = Integer.valueOf(curPageNum);
          
        MsgPage msgPage=moneyFlowPlanMonthService.getDataMoneyFlowMonthForSumData(entity,pageNum,Base.pagesize,tempBean);

        if(null == entity ||
        		null == entity.getDate() ||
        		"".equals(entity.getDate()))
        	entity.setDate(new SimpleDateFormat("yyyy-MM").format(new Date()));
        
        map.put("entityview", entity);
        map.put("tempBean", tempBean);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/moneyFlowPlanMonth/g_query");
		addData(map);

		return "/report/moneyFlowPlanMonth/moneyFlowPlanMonthFormGroupGuery";
	}
	
	@ResponseBody
	@RequestMapping(value = "sumDataForNew", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String sumDataForNew(DataMoneyFlowMonth entity) throws IOException {

		DataMoneyFlowMonth backInfoBean = new DataMoneyFlowMonth();	
		LinkedHashMap<String,List<LinkedHashMap<String, Object>>> MonthPlanData=new LinkedHashMap<String,List<LinkedHashMap<String, Object>>>();
		
		moneyFlowPlanMonthService.sumForAllChildrenEntity(entity,backInfoBean,MonthPlanData);

		HashMap<String,Object> backMap = new HashMap<String,Object>();
		backMap.put("bean", backInfoBean);
		backMap.put("beanInfo", MonthPlanData);
		
		String data="";
		data=JSONArray.toJSONString(backMap);

		return data;
	}
	
	//现金流计划数据填报数据查询导出
		@RequestMapping("/export")
		public String export(DataMoneyFlowMonth entity,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException {
			HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			//数据权限
	        entity.setParentorg(str);
	        HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getOrg());
			if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
				entity.setOrg("");
			}
			List<DataMoneyFlowMonth> list = moneyFlowPlanMonthService.findPageList(entity);
			
			Set<DataMoneyFlowMonthCi> list1 = new HashSet<DataMoneyFlowMonthCi>();
			Set<DataMoneyFlowMonthCo> list2 = new HashSet<DataMoneyFlowMonthCo>();
			Set<DataMoneyFlowMonthIo> list3 = new HashSet<DataMoneyFlowMonthIo>();
			Set<DataMoneyFlowMonthCi> dataMoneyFlowMonthCi = new HashSet<DataMoneyFlowMonthCi>();
			Set<DataMoneyFlowMonthCo> dataMoneyFlowMonthCo = new HashSet<DataMoneyFlowMonthCo>();
			Set<DataMoneyFlowMonthIo> dataMoneyFlowMonthIo = new HashSet<DataMoneyFlowMonthIo>();
			if(list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					dataMoneyFlowMonthCi=list.get(i).getDataMoneyFlowMonthCi();
					dataMoneyFlowMonthCo=list.get(i).getDataMoneyFlowMonthCo();
					dataMoneyFlowMonthIo=list.get(i).getDataMoneyFlowMonthIo();
					list1.addAll(dataMoneyFlowMonthCi);
					list2.addAll(dataMoneyFlowMonthCo);
					list3.addAll(dataMoneyFlowMonthIo);
				}
			}
			// 1.创建一个workbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
			HSSFSheet sheet1 = null;
			HSSFSheet sheet2 = null;
			HSSFSheet sheet3 = null;
			HSSFRow row1 = null;
			HSSFRow row2 = null;
			HSSFRow row3 = null;
			//导出文件名称
			String fileName =  "现金流月计划数据列表";
			
			// 设置表头
			ExcelDataTreating tool = new ExcelDataTreating();
			// 创建单元格，设置值表头，设置表头居中
			List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
			sheet1 = wb.createSheet("筹资性流入明细");
			sheet2 = wb.createSheet("筹资性流出明细");
			sheet3 = wb.createSheet("投资性流出明细");
			// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
			row1 = sheet1.createRow((int) 0);
			row2 = sheet2.createRow((int) 0);
			row3 = sheet3.createRow((int) 0);
			String [] titleArray1 = {"序号","承贷主体","贷款银行","贷款金额(万元)","计划下账日期","融资类型","新增或续作","下账币种","综合成本(%)","是否权益性融资"};
			String [] titleArray2 = {"序号","操作单位","还贷日期","金融机构","还贷类型","还款金额(万元)","是否续作","还款币种","保障还款项目","是否有保障还款计划"};
			String [] titleArray3 = {"序号","投资单位","投资日期","投资项目名称","投资金额(万元)","投资币种","专项资金保障方案","投资类型","是否有保障还款计划"};
			row1 = tool.setBondExcelTitle(styleList.get(0),row1,titleArray1);
			row2 = tool.setBondExcelTitle(styleList.get(0),row2,titleArray2);
			row3 = tool.setBondExcelTitle(styleList.get(0),row3,titleArray3);
			
			sheet1 = this.setExcelDatasss1(sheet1,list1,styleList.get(1));
			sheet2 = this.setExcelDatasss2(sheet2,list2,styleList.get(1));
			sheet3 = this.setExcelDatasss3(sheet3,list3,styleList.get(1));

			tool.outputExcel(wb,fileName,response);

			
			return null;
	}

	private HSSFSheet setExcelDatasss3(HSSFSheet sheet,Set<DataMoneyFlowMonthIo> entityList, HSSFCellStyle style) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
		HSSFRow row = null;
		//将数据写入Excel
		// 循环将数据写入Excel
		int t = 1;
		for (DataMoneyFlowMonthIo entity:entityList) {
			row = sheet.createRow(t);
			// 创建单元格，设置值
			Cell cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(t++);
			//投资单位
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getUnitOfInvestment());
			//投资日期
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getDateOfInvestment());
			//投资项目名称
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getNameOfInvestmentProject());
			//投资金额(万元)
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getInvestmentAmount());
			//投资币种
			cell = row.createCell(5);
			cell.setCellStyle(style);
			if(entity.getTheInvestmentCurrency().equals("69")){
				cell.setCellValue("人民币");
			}else if(entity.getTheInvestmentCurrency().equals("70")){
				cell.setCellValue("美元");
			}else if(entity.getTheInvestmentCurrency().equals("71")){
				cell.setCellValue("澳元");
			}else if(entity.getTheInvestmentCurrency().equals("72")){
				cell.setCellValue("欧元");
			}else if(entity.getTheInvestmentCurrency().equals("73")){
				cell.setCellValue("日元");
			}else if(entity.getTheInvestmentCurrency().equals("74")){
				cell.setCellValue("澳元");
			}else if(entity.getTheInvestmentCurrency().equals("75")){
				cell.setCellValue("英镑");
			}else if(entity.getTheInvestmentCurrency().equals("76")){
				cell.setCellValue("加元");
			}else if(entity.getTheInvestmentCurrency().equals("77")){
				cell.setCellValue("新台币");
			}else if(entity.getTheInvestmentCurrency().equals("78")){
				cell.setCellValue("泰铢");
			}else{
				cell.setCellValue("越南盾");
			}
			//专项资金保障方案
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getSpecialFundGuaranteeScheme());
			//投资类型
			cell = row.createCell(7);
			cell.setCellStyle(style);
			if(entity.getInvestmentType().equals("250")){
				cell.setCellValue("基础建设");
			}else if(entity.getInvestmentType().equals("251")){
				cell.setCellValue("信息化投资");
			}else if(entity.getInvestmentType().equals("252")){
				cell.setCellValue("固定资产购置");
			}else if(entity.getInvestmentType().equals("253")){
				cell.setCellValue("土地储备");
			}else if(entity.getInvestmentType().equals("254")){
				cell.setCellValue("股权投资");
			}else if(entity.getInvestmentType().equals("255")){
				cell.setCellValue("经营性房产投资");
			}else{
				cell.setCellValue("持有型房产投资");
			}
			//是否有保障还款计划
			cell = row.createCell(8);
			cell.setCellStyle(style);
			if(entity.getGuaranteeRepaymentPlan().equals("108")){
				cell.setCellValue("是");
			}else{
				cell.setCellValue("否");
			}
			
		}
		return sheet;
		}

	private HSSFSheet setExcelDatasss2(HSSFSheet sheet,Set<DataMoneyFlowMonthCo> entityList, HSSFCellStyle style) {
		 // 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
		HSSFRow row = null;
		//将数据写入Excel
		// 循环将数据写入Excel
		int t = 1;
		for (DataMoneyFlowMonthCo entity:entityList) {
			row = sheet.createRow(t);
			// 创建单元格，设置值
			Cell cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(t++);
			//操作单位
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getTheLoanBusiness());
			//还贷日期
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getDateOfPayment());
			//金融机构
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getFinancingInstitution());
			//还贷类型
			cell = row.createCell(4);
			cell.setCellStyle(style);
			if(entity.getPaymentType().equals("150")){
				cell.setCellValue("本金");
			}else if(entity.getPaymentType().equals("151")){
				cell.setCellValue("租金");
			}else if(entity.getPaymentType().equals("152")){
				cell.setCellValue("利息");
			}else{
				cell.setCellValue("票据");
			}
			//还款金额(万元)
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getRepaymentAmount());
			//是否续作
			cell = row.createCell(6);
			cell.setCellStyle(style);
			if(entity.getNewOrRenewedCo().equals("112")){
				cell.setCellValue("新增");
			}else{
				cell.setCellValue("续作");
			}
			//还款币种
			cell = row.createCell(7);
			cell.setCellStyle(style);
			if(entity.getPaymentCurrency().equals("69")){
				cell.setCellValue("人民币");
			}else if(entity.getPaymentCurrency().equals("70")){
				cell.setCellValue("美元");
			}else if(entity.getPaymentCurrency().equals("71")){
				cell.setCellValue("澳元");
			}else if(entity.getPaymentCurrency().equals("72")){
				cell.setCellValue("欧元");
			}else if(entity.getPaymentCurrency().equals("73")){
				cell.setCellValue("日元");
			}else if(entity.getPaymentCurrency().equals("74")){
				cell.setCellValue("澳元");
			}else if(entity.getPaymentCurrency().equals("75")){
				cell.setCellValue("英镑");
			}else if(entity.getPaymentCurrency().equals("76")){
				cell.setCellValue("加元");
			}else if(entity.getPaymentCurrency().equals("77")){
				cell.setCellValue("新台币");
			}else if(entity.getPaymentCurrency().equals("78")){
				cell.setCellValue("泰铢");
			}else{
				cell.setCellValue("越南盾");
			}
			//保障还款项目
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getGuaranteeRepaymentProject());
			//是否有保障还款计划
			cell = row.createCell(9);
			cell.setCellStyle(style);
			if(entity.getGuaranteeRepaymentPlan().equals("108")){
				cell.setCellValue("是");
			}else{
				cell.setCellValue("否");
			}
			
			
		}
		return sheet;
		}

	private HSSFSheet setExcelDatasss1(HSSFSheet sheet,Set<DataMoneyFlowMonthCi> entityList, HSSFCellStyle style) {
		 // 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
		HSSFRow row = null;
		//将数据写入Excel
		// 循环将数据写入Excel
		int t = 1;
		for (DataMoneyFlowMonthCi entity:entityList) {
			row = sheet.createRow(t);
			// 创建单元格，设置值
			Cell cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(t++);
			//承贷主体
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getCommitmentShallSubject());
			//贷款银行
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getLendingBank());
			//贷款金额(万元)
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getLoanAmount());
			//计划下账日期
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getPlaceOrderDate());
			//融资类型
			cell = row.createCell(5);
			cell.setCellStyle(style);
			if(entity.getFinancingType().equals("150")){
				cell.setCellValue("本金");
			}else if(entity.getFinancingType().equals("151")){
				cell.setCellValue("租金");
			}else if(entity.getFinancingType().equals("152")){
				cell.setCellValue("利息");
			}else{
				cell.setCellValue("票据");
			}
			
			//新增或续作
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getNewOrRenewed());
			if(entity.getNewOrRenewed().equals("112")){
				cell.setCellValue("新增");
			}else{
				cell.setCellValue("续作");
			}
			
			//下账币种
			cell = row.createCell(7);
			cell.setCellStyle(style);
			if(entity.getPlaceOrderCurrency().equals("69")){
				cell.setCellValue("人民币");
			}else if(entity.getPlaceOrderCurrency().equals("70")){
				cell.setCellValue("美元");
			}else if(entity.getPlaceOrderCurrency().equals("71")){
				cell.setCellValue("澳元");
			}else if(entity.getPlaceOrderCurrency().equals("72")){
				cell.setCellValue("欧元");
			}else if(entity.getPlaceOrderCurrency().equals("73")){
				cell.setCellValue("日元");
			}else if(entity.getPlaceOrderCurrency().equals("74")){
				cell.setCellValue("澳元");
			}else if(entity.getPlaceOrderCurrency().equals("75")){
				cell.setCellValue("英镑");
			}else if(entity.getPlaceOrderCurrency().equals("76")){
				cell.setCellValue("加元");
			}else if(entity.getPlaceOrderCurrency().equals("77")){
				cell.setCellValue("新台币");
			}else if(entity.getPlaceOrderCurrency().equals("78")){
				cell.setCellValue("泰铢");
			}else{
				cell.setCellValue("越南盾");
			}
			
			//综合成本(%)
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(entity.getCompositeCost());
			//是否权益性融资
			cell = row.createCell(9);
			cell.setCellStyle(style);
			if(entity.getEquityFinancing().equals("108")){
				cell.setCellValue("是");
			}else{
				cell.setCellValue("否");
			}
			
		}
		return sheet;
	}
		
	/** 
     * 描述：对表格中数值进行格式化 
     * @param cell 
     * @return 
     */  
    public  Object getCellValue(Cell cell){  
        Object value = null;  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //日期格式化  
        DecimalFormat df = new DecimalFormat("0.00");  //格式化数字 (单位万元：两位小数) 
        String a = cell.getCellStyle().getDataFormatString();
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_FORMULA:
        	try {  
        		value = String.valueOf(cell.getStringCellValue());  
        	} catch (IllegalStateException e) {  
        		value = df.format(cell.getNumericCellValue());  
        	}  
        	break; 
        case Cell.CELL_TYPE_STRING:  
            value = cell.getRichStringCellValue().getString();  
            break;  
        case Cell.CELL_TYPE_NUMERIC:  
            if("yyyy-mm-dd".equals(cell.getCellStyle().getDataFormatString())){ 
            	value = sdf.format(cell.getDateCellValue());
            }else{  
                value = df.format(cell.getNumericCellValue());  
            }  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            value = cell.getBooleanCellValue();  
            break;  
        case Cell.CELL_TYPE_BLANK:  
            value = "";  
            break;  
        default:  
            break;  
        }  
        return value;  
    }
}

