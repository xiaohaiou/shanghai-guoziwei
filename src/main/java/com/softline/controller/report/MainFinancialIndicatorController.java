package com.softline.controller.report;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.controller.common.commonController;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.impl.CommonDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.MainFinancialIndicator;
import com.softline.entity.MainFinancialIndicatorStock;
import com.softline.entity.ReportManageAdjust;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IMainFinancialIndicatorService;
import com.softline.service.report.IReportService;
import com.softline.service.report.impl.ReportService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/mainFinancialIndicators")
/**
 * 
 * @author tch
 *
 */
public class MainFinancialIndicatorController {

	@Resource(name = "mainFinancialIndicatorsService")
	private IMainFinancialIndicatorService mainFinancialIndicatorsService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "commonService")
	private ICommonService commonService;
	@Resource(name = "reportService")
	private IReportService reportService;
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			MainFinancialIndicator entityview=mainFinancialIndicatorsService.getMainFinancialIndicator(id);
			map.put("mainFinancialIndicator", entityview);
		}
	}
	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(MainFinancialIndicator entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/mainFinancialIndicators";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
        entity.setGetOperateType(Base.fun_examine);
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        String dataauth=(String) session.getAttribute("gzwsession_AllFinancecompany");
        entity.setParentorg(str);
        MsgPage msgPage=mainFinancialIndicatorsService.getMainFinancialIndicatorListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/mainFinancialIndicators/examinelist");
	    map.put("entityview", entity);
		addData(map);
		return "/report/mainFinancialIndicator/mainFinancialIndicatorExamineList";
	}
	
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(MainFinancialIndicator entity,HttpServletRequest request ,Map<String, Object> map,String yearAndMonth) throws IOException {
		String mapurl=request.getContextPath()+ "/mainFinancialIndicators";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        String dataauth=(String) session.getAttribute("gzwsession_AllFinancecompany");
        entity.setParentorg(str);
        if(Common.notEmpty(yearAndMonth)){
        	entity.setYear(Common.parseInteger(yearAndMonth.substring(0,4)));
        	entity.setMonth(Common.parseInteger(yearAndMonth.substring(5,7)));
        }
        MsgPage msgPage=mainFinancialIndicatorsService.getMainFinancialIndicatorListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/mainFinancialIndicators/list");
	    map.put("entityview", entity);
	    map.put("yearAndMonth", yearAndMonth);
		addData(map);
		return "/report/mainFinancialIndicator/mainFinancialIndicatorList";
	}
	
	@RequestMapping("/reportExport")
	public void reportExport(MainFinancialIndicator entity,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String yearAndMonth) throws IOException, ParsePropertyException, InvalidFormatException {
		
		if(Common.notEmpty(yearAndMonth)){
			entity.setYear(Common.parseInteger(yearAndMonth.substring(0,4)));
			entity.setMonth(Common.parseInteger(yearAndMonth.substring(5,7)));
	        } 
			HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			
	        List<MainFinancialIndicator> list=mainFinancialIndicatorsService.getMainFinancialIndicatorExportList(entity);
	        List<MainFinancialIndicator> exlist=new ArrayList<MainFinancialIndicator>();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			
	       /* for (int i = 0; i < list.size(); i++) {
	        	//int groupid=reportService.getReportId(Common.parseInteger(yearAndMonth.substring(0,4)), list.get(i).getReportType(), 52002);
	        	MainFinancialIndicator mainFinancialIndicatorExport=reportService.AdjustMainFinancialIndicatorExport(yearAndMonth, list.get(i).getOrg(), 28, use,list.get(i).getReportType());
	        	 exlist.add(mainFinancialIndicatorExport);
	        }*/
			
			for (int j = 0; j < list.size(); j++) {
				
				/*list.get(j).setStick1(mainFinancialIndicatorsService.getSszbData(list.get(j)));
				list.get(j).setStick2(mainFinancialIndicatorsService.getZczbData(list.get(j)));*/
				list.get(j).setStick1(mainFinancialIndicatorsService.getSszbDataFT(list.get(j)));
				list.get(j).setStick2(mainFinancialIndicatorsService.getZczbDataFT(list.get(j)));
			}
	        
	        
			 XSSFWorkbook workBook = new XSSFWorkbook();
			 XSSFCell cell = null;
			 XSSFSheet sheet = workBook.createSheet("主要财务指标数据");
			 XSSFRow row = sheet.createRow((int) 0);  
			 
			 String[] header={"序号","所属期间","所属核心企业","单位名称","负责人","电话","注册地","主运营地","会计师事务所名称","合并/单体","总资产（单位：元）","总负债（单位：元）","所有者权益（单位：元） ","实收资本（单位：元）","资产负债率","营业总收入（单位：元）","营业成本（单位：元）","利润总额（单位：元）","净利润（单位：元）"
					 ,"数据-货币资金（单位：元）","数据-存货（单位：元）"," 数据-其他应收款（单位：元）","数据-投资性房地产（单位：元）","数据-固定资产（单位：元）","数据-在建工程（单位：元）","数据-无形资产（单位：元）"," 数据-其他应付款（单位：元）","数据-短期借款（单位：元）","数据-应付票据（单位：元）"," 数据-一年内到期的非流动负债 （单位：元）",
					 " 数据-长期借款（单位：元）","数据-应付债券（单位：元）","数据-长期应付款（单位：元）","数据-未分配利润（单位：元）","数据-归属母公司的所有者权益（单位：元）","数据-少数股东权益（单位：元）","数据-营业税金及附加（单位：元）"," 数据-销售费用（单位：元）","数据-管理费用（单位：元）","数据-财务费用（单位：元）","数据-公允价值变动收益（单位：元）","数据-投资收益（单位：元）","数据-所得税费用（单位：元）","实收资本（单位：元）",
					 "股东1","持股比例1","股东2","持股比例2","股东3","持股比例3","股东4","持股比例4","股东5","持股比例5","股东6","持股比例6","股东7","持股比例7","注册资本（单位：元）","股东1","持股比例1","股东2","持股比例2","股东3","持股比例3","股东4","持股比例4","股东5","持股比例5","股东6","持股比例6","股东7","持股比例7"};
			 
		for (int i = 0; i < header.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header[i]);

		}
			 
			 for (int i = 0; i < list.size(); i++) {  
				 	int k=0;
				     row = sheet.createRow((int) i + 1);
				     MainFinancialIndicator value = list.get(i);
				     row.createCell((short)k++).setCellValue(i+1);
				     row.createCell((short)k++).setCellValue(value.getYear()+"-"+value.getMonth());
				     row.createCell((short)k++).setCellValue(value.getBusinessorgname());
				     row.createCell((short)k++).setCellValue(value.getOrgname());
				     row.createCell((short)k++).setCellValue(value.getChargePerson());
				     row.createCell((short)k++).setCellValue(value.getPhoneNumber());
				     row.createCell((short)k++).setCellValue(value.getAddress());
				     row.createCell((short)k++).setCellValue(value.getMainCamp());
				     row.createCell((short)k++).setCellValue(value.getAccountingFirm());
				     row.createCell((short)k++).setCellValue(value.getReportTypeName());
				     row.createCell((short)k++).setCellValue(value.getTotalAssets());
				     row.createCell((short)k++).setCellValue(value.getTotalLiabilities());
				     row.createCell((short)k++).setCellValue(value.getOwnerEquity());
				     row.createCell((short)k++).setCellValue(value.getPaidCapital());
				     if(null!=value.getAssetLiabilityRatio()){
				    	 row.createCell((short)k++).setCellValue(value.getAssetLiabilityRatio());
				     }else if (null==value.getAssetLiabilityRatio()&&null!=value.getTotalAssets()&&null!=value.getTotalLiabilities()&&value.getTotalAssets()!="0") {
				    	 row.createCell((short)k++).setCellValue(Double.parseDouble(value.getTotalLiabilities())/Double.parseDouble(value.getTotalAssets()));
					}else{
						row.createCell((short)k++).setCellValue("");
					}
				     
				     row.createCell((short)k++).setCellValue(value.getTotalRevenue());
				     row.createCell((short)k++).setCellValue(value.getOperatingCost());
				     row.createCell((short)k++).setCellValue(value.getTotalProfit());
				     row.createCell((short)k++).setCellValue(value.getNetProfit());
				     row.createCell((short)k++).setCellValue(value.getMonetaryFund());
				     row.createCell((short)k++).setCellValue(value.getStock());
				     row.createCell((short)k++).setCellValue(value.getOtherReceivables());
				     row.createCell((short)k++).setCellValue(value.getInvestmentTypeRealEstate());
				     row.createCell((short)k++).setCellValue(value.getFixedAssets());
				     row.createCell((short)k++).setCellValue(value.getUnderConstructionProject());
				     row.createCell((short)k++).setCellValue(value.getIntangibleAssets());
				     row.createCell((short)k++).setCellValue(value.getOtherPayable());
				     row.createCell((short)k++).setCellValue(value.getShortTermLoan());
				     row.createCell((short)k++).setCellValue(value.getNotessPayable());
				     row.createCell((short)k++).setCellValue(value.getNonCurrentLiabilities());
				     row.createCell((short)k++).setCellValue(value.getLongTermLoan());
				     row.createCell((short)k++).setCellValue(value.getBondsPayable());
				     row.createCell((short)k++).setCellValue(value.getLongTermPayables());
				     row.createCell((short)k++).setCellValue(value.getUndistributedProfit());
				     row.createCell((short)k++).setCellValue(value.getOwnerEquityAttributableToTheParentCompany());
				     row.createCell((short)k++).setCellValue(value.getMinorityShareholdersEquity());
				     row.createCell((short)k++).setCellValue(value.getBusinessTaxesAndSurcharges());
				     row.createCell((short)k++).setCellValue(value.getSellingExpenses());
				     row.createCell((short)k++).setCellValue(value.getManagementExpenses());
				     row.createCell((short)k++).setCellValue(value.getFinancialExpenses());
				     row.createCell((short)k++).setCellValue(value.getFairValueChangeIncome());
				     row.createCell((short)k++).setCellValue(value.getIncomeFromInvestment());
				     row.createCell((short)k++).setCellValue(value.getIncomeTaxExpense());
				     row.createCell((short)k++).setCellValue(value.getPaidCapital());
				     for (int j = 0; j < 7; j++) {
				    	 if(j<value.getStick1().size()){
				    		 row.createCell((short)k++).setCellValue(value.getStick1().get(j).getName());
					    	 row.createCell((short)k++).setCellValue(value.getStick1().get(j).getPrecent());
				    	 }
				    	 else{
				    		 row.createCell((short)k++).setCellValue("");
					    	 row.createCell((short)k++).setCellValue("");
				    	 }
				    	
					}
				     row.createCell((short)k++).setCellValue(value.getRegisteredCapital());
				     for (int j = 0; j < 7; j++) {
				    	 if(j<value.getStick2().size()){
				    		 row.createCell((short)k++).setCellValue(value.getStick2().get(j).getName());
					    	 row.createCell((short)k++).setCellValue(value.getStick2().get(j).getPrecent());
				    	 }
				    	 else{
				    		 row.createCell((short)k++).setCellValue("");
					    	 row.createCell((short)k++).setCellValue("");
				    	 }
				    	
					}
				     
			 }  
			 
			 
			 
		        // 清空response
		        response.reset();
		        String _name = "主要财务指标数据.xlsx";
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

			
			
			
	      /*  String Item = Common.getItemPath(DES.MainFinancialIndicator, request) + Base.MainFinancialIndicatortemplate;
			String Item_temp = Common.getItemPath(DES.MainFinancialIndicator, request) + Base.getTemp2k3ExcelFileName();
	       
			XLSTransformer former = new XLSTransformer();
			Map<String, Object> beanParams = new HashMap<String, Object>();
			beanParams.put("list", exlist);
			former.transformXLS(Item, beanParams, Item_temp);
		    String expRecReportName ="主要财务指标数据.xlsx";
		    commonController.doDownLoad(Item_temp, expRecReportName, response);
		    Base.deleteTempFile(Item_temp);*/
	}
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(MainFinancialIndicator entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		MainFinancialIndicator entityview;
		if(entity.getId()==null)
		{	
			entityview=new MainFinancialIndicator();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
		}
		else
		{	
			entityview=mainFinancialIndicatorsService.getMainFinancialIndicator(entity.getId());
			
			List<MainFinancialIndicatorStock> stock=mainFinancialIndicatorsService.getStockData(entity.getId());
			if(stock!=null && stock.size()>0)
				map.put("stock", stock);
			
			List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_mainfinanceindicator);
			map.put("entityExamineviewlist", a);
			
		}
		map.put("entityview", entityview);	
		addData(map);
		
		return "/report/mainFinancialIndicator/mainFinancialIndicatorNewModify";
	}
	
	//跳转新增修改页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		MainFinancialIndicator entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new MainFinancialIndicator();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			
			
			entityview=mainFinancialIndicatorsService.getMainFinancialIndicator(id);
			List<MainFinancialIndicatorStock> stock=mainFinancialIndicatorsService.getZczbData(entityview);
			if(stock!=null && stock.size()>0)
				map.put("stock", stock);//注册资本股东占比信息
			List<MainFinancialIndicatorStock> sszb=mainFinancialIndicatorsService.getSszbData(entityview);
			if(sszb!=null && sszb.size()>0)
				map.put("sszb", sszb);//实收资本股东占比信息
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_mainfinanceindicator);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/mainFinancialIndicator/mainFinancialIndicatorView";
	}
	
	
	
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/_report", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _report(MainFinancialIndicator entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			result= mainFinancialIndicatorsService.saveMainFinancialIndicatorCheck(entity);
			if(result.isResult())
			{
				HttpSession session=request.getSession();
				HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				entity.setReportDate(df.format(new Date()));
				entity.setReportPersonId(use.getVcEmployeeId());
				entity.setReportPersonName(use.getVcName());
				entity.setStatus(Base.examstatus_2);
				commonService.saveOrUpdate(entity);
			}
		}
		else
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//查询列表页面的上报
	@RequestMapping(value ="/postexam")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		MainFinancialIndicator entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new MainFinancialIndicator();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			List<MainFinancialIndicatorStock> stock=mainFinancialIndicatorsService.getStockData(id);
			map.put("stock", stock);
			entityview=mainFinancialIndicatorsService.getMainFinancialIndicator(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_mainfinanceindicator);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/mainFinancialIndicator/mainFinancialIndicatorView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		MainFinancialIndicator entityview;
		if(id==null)
		{
			entityview=new MainFinancialIndicator();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
			entityview=mainFinancialIndicatorsService.getMainFinancialIndicator(id);
		map.put("entityview", entityview);	
		return "/report/mainFinancialIndicator/mainFinancialIndicatorExamine";
	}
	
	
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		List<HhBase> ReportType= baseService.getBases(Base.reportgroup_type);
		
		map.put("examstatustype",examstatustype);
		map.put("reportType", ReportType);
		
	}
	

	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(MainFinancialIndicator entity,String operate,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		result.setResult(true);
		if(entity==null)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}
	
	// 获取下拉框内容
	@ResponseBody
	@RequestMapping(value = "/_childtype", method = RequestMethod.POST)
	/**
	 * 返回类型的json
	 * @param val 类型ID
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	public void getVal(Integer val, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		List<HhBase> baseList = baseService.getChildBases(val);
		String json = JSONArray.toJSONString(baseList);
		response.getWriter().write(json);
	}

	
	
}
