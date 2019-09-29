package com.softline.service.bimr.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.ExcelTool;
import com.softline.dao.bimr.IContractMonthDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrContractMonth;
import com.softline.entity.bimr.BimrContractMonthList;
import com.softline.entityTemp.CommitResult;
import com.softline.service.bimr.IContractMonthService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.util.ExcelCellValueValidate;
import com.softline.util.ExcelCellValueValidateResult;
import com.softline.util.MsgPage;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Service("contractMonthService")
public class ContractMonthService implements IContractMonthService{
	static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	private static final Logger LOGGER = Logger.getLogger(ContractMonthService.class);
	public static CommitResult result = new CommitResult();
	/**
	 * 审核类型编号
	 */
	public static final Integer EXAMINE_CONTRACT_MONTH_ID = 50000001;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
		
	@Resource(name = "contractMonthDao")
	private IContractMonthDao contractMonthDao;	
	
	@Autowired
	private IExamineService examineService;	
	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;

	@Override
	public MsgPage GetContractMonths(BimrContractMonth qryParams, Integer curPageNum,
			Integer pageSize) {

		MsgPage msgPage = new MsgPage();
		 //总记录数
        Integer totalRecords = contractMonthDao.getContractMonthListCount(qryParams);
        //当前页开始记录
        int offset = msgPage.countOffset(curPageNum,pageSize);
        //分页查询结果集
        List<BimrContractMonth> list = contractMonthDao.getContractMonthList(qryParams,offset,pageSize);
   
        msgPage.setPageNum(curPageNum);
        msgPage.setPageSize(pageSize);
        msgPage.setTotalRecords(totalRecords);
        msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
        msgPage.setList(list);
        return msgPage;

	}
	
	@Override
	public MsgPage GetContractMonthsList(Integer contractMonthId,Integer curPageNum, Integer pageSize)
	{
		//查询参数构造
		BimrContractMonthList qryParams = new BimrContractMonthList();
		qryParams.setContractMonthId(contractMonthId);
		
		MsgPage msgPage = new MsgPage();
		 //总记录数
        Integer totalRecords = contractMonthDao.getBimrContractMonthListCount(qryParams);
        //当前页开始记录
        int offset = msgPage.countOffset(curPageNum,pageSize);
        //分页查询结果集
        List<BimrContractMonthList> list = contractMonthDao.getBimrContractMonthList(qryParams,offset,pageSize);
   
        msgPage.setPageNum(curPageNum);
        msgPage.setPageSize(pageSize);
        msgPage.setTotalRecords(totalRecords);
        msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
        msgPage.setList(list);
        return msgPage;
	}

	@Override
	public BimrContractMonth GetBimrContractMonthById(Integer bid)
	{
		if(bid==null)
			return null;
		return contractMonthDao.GetBimrContractMonthById(bid);
		
	}

	@Override
	public Boolean DelBimrContractMonthById(Integer bid,HhUsers user)
	{
		if( bid==null || user==null)
			return false;
		
		BimrContractMonth qryParams = new BimrContractMonth();
		qryParams.setId(bid);
		
		List<BimrContractMonth> list = contractMonthDao.getContractMonthList(qryParams,0,1);
		if(list.isEmpty())
			return true;
		
		if(list.get(0).getStatus() ==50113 || list.get(0).getStatus() ==50115)
			return false;
		return contractMonthDao.delBimrContractMonthLists(bid, user);
		
	}
	
	@Override
	public CommitResult ImportData(InputStream inStream, String compId,Integer year, Integer month,Integer status,String compName ,HhUsers user){
		if(validateImport(user, status)){
			result.setResult(false);
			result.setExceptionStr("用户不存在或非法状态");
			return result;
		}

		List<BimrContractMonth> importContractMonths= getImportContracts(compId, year, month);
		if(hasImport(importContractMonths)){
			result.setResult(false);
			result.setExceptionStr("已有导入合同");
			return result;
		}
		cleanImport(importContractMonths,user);
		
		Workbook workbook = createWorkbook(inStream);
		if(workbook == null){
			result.setResult(false);
			result.setExceptionStr("只能导入Excel格式文件");
			return result;
		}
		
		String now =Common.getDate(YYYY_MM_DD_HH_MM_SS);
		
		List<BimrContractMonthList> data = readExcel(workbook, status, now, user);
		

		if(result!=null){
			if (!result.isResult()) {
				return result;
			}
		}
				
		if(data.isEmpty()){
			result.setResult(false);
			result.setExceptionStr("未读到合同数据");
			return result;
		}
		
		
		BigDecimal totalMoney= new BigDecimal(0);
		for(BimrContractMonthList o: data){
			totalMoney = totalMoney.add(null!=o.getContractTotalAmount()?o.getContractTotalAmount():new BigDecimal(0));
		}
		Integer id=saveBimrContractMonth(compId, year, month, status, compName, user, now, totalMoney, data.size());
			saveBatchContractList(id, data);
		result.setResult(true);
		return result;
	}
	
	private boolean validateImport(HhUsers user, Integer status){
		return user ==null || status==null || 
				!(status==50112 || status==50113);
	}
	
	private List<BimrContractMonth> getImportContracts(String compId, Integer year, Integer month){
		BimrContractMonth qryParams = new BimrContractMonth();
		qryParams.setCompId(compId);
		qryParams.setYear(year);
		qryParams.setMonth(month);
		return contractMonthDao.getContractMonthList(qryParams,0,1);
	}
	
	private void cleanImport(List<BimrContractMonth> contractMonths, HhUsers user){
		if(!contractMonths.isEmpty()){
			contractMonthDao.delBimrContractMonthLists(contractMonths.get(0).getId(), user);
		}
	}
	
	private boolean hasImport(List<BimrContractMonth> contractMonths){
		if(contractMonths.isEmpty()){
			return false;
		}
		Integer status =contractMonths.get(0).getStatus();
		return status ==Base.examstatus_2 || status == Base.examstatus_4;
	}
	
	private Workbook createWorkbook(InputStream inputStream){
		Workbook workbook = null; 
	    try { 		
	        workbook = WorkbookFactory.create(inputStream);
	        LOGGER.debug("Excel is 2007");
	    } catch (Exception e) {
	    	LOGGER.error("Not excel 2007, exception is " + e.getMessage());
			try {
				workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream)); 
			} catch (Exception ex) {
				LOGGER.error("Not excel 2003, exception is " + e.getMessage());
			}
	    } 
	    return workbook;
	}
	
	private List<BimrContractMonthList> readExcel(Workbook workbook, Integer status, String importDate, HhUsers user){
		ExcelTool exceltool=new ExcelTool();

		LOGGER.debug("start read excel ...");
		
		Sheet  sheet = workbook.getSheetAt(0);
		
		int dataStartRow=getDatastartRow(exceltool, workbook, sheet);
		int dataStartCol = 0;
		int count = sheet.getLastRowNum();
		List<BimrContractMonthList> data=new ArrayList<BimrContractMonthList>(count);
		//在有表头的情况下
		if(dataStartRow==0){
			result.setExceptionStr("未读到合同数据");
			result.setResult(false);
			return data;
		}
		if(dataStartRow <=count){
			for (int i = dataStartRow; i <= count; i++) {
				Row row = sheet.getRow(i);
				if(null==row){
					continue;
				}
				String documentNo= exceltool.getCellValue(workbook, row.getCell(dataStartCol +3));
				if(!valIsEmpty(exceltool,workbook,row,dataStartCol)){
					continue;
				}
				
				BimrContractMonthList t = new BimrContractMonthList();
				t.setStatus(status); //未上报
				t.setIsDel(0);//版本
				t.setVersion(1);
				t.setReportDate(importDate);
				t.setReportPersonId(user.getVcEmployeeId());
				t.setReportPersonName(user.getVcName());
				result=readRow(t, documentNo, exceltool, workbook, sheet, row, dataStartCol,i);
				if (!result.isResult()) {
					return data;
				}
				data.add(t);
			}
		}
		
		return data;
	}
	
	public boolean valIsEmpty(ExcelTool excelTool, Workbook workbook,Row row,int dataStartCol) {
		boolean flag=false;
		for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
			String string= excelTool.getCellValue(workbook, row.getCell(dataStartCol +i));
			if(Common.notEmpty(string)){
				flag =true;
				break;
			}
		}
		return flag;
		
	}
	
	private Integer getDatastartRow(ExcelTool excelTool, Workbook workbook, Sheet  sheet){
		if(sheet.getLastRowNum() == 0){
			return 0;
		}
		int count = sheet.getLastRowNum();
		count = count > 50 ? 50: count;
		for(int i = 0; i < count; i++){
			Row row = sheet.getRow(i);
			String v = excelTool.getCellValue(workbook, row.getCell(0));
			if("序号".equals(v.trim())){
				return i + 1;
			}
		}
		return 0;
	}
	
	private CommitResult readRow(BimrContractMonthList t, String documentNo, ExcelTool exceltool, Workbook workbook, Sheet sheet, Row row, int dataStartCol,int i){
		ExcelCellValueValidate excelValidate = new ExcelCellValueValidate();
		CommitResult result =new CommitResult();
		result.setResult(true);
		
		 //合同类型
        List<HhBase> contractTypeText=baseService.getBases(82202);
		
		//序号
		dataStartCol++;
		//核心企业
		ExcelCellValueValidateResult v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 100);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setContractBelongCompany(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		
		//专业公司
		 v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 100);
			result=judgeexcelValidate(v1,i+1,dataStartCol);
				if (!result.isResult()) {
					return result;
				}
		t.setContractSubordinateCompany(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		
		//合同公文号
		t.setContractDocumentNo(documentNo);
		dataStartCol++;
		//合同名称
		 v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 200);
			result=judgeexcelValidate(v1,i+1,dataStartCol);
				if (!result.isResult()) {
					return result;
				}
		t.setContractName(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//我方合同主体
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 1000);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setOurContractSubject(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		
		//对方合同主体
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 1000);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setOtherContractSubject(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		
		//是否属于集团内部企业间合同
		t.setIsCom("是".equals( exceltool.getCellValue(workbook, row.getCell(dataStartCol++))));
		
		//项目名称
			v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 255);
			result=judgeexcelValidate(v1,i+1,dataStartCol);
				if (!result.isResult()) {
					return result;
				}
		t.setProjectName(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		
		//合同类型
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 50);
		String contractTypeTexts = exceltool.getCellValue(workbook, row.getCell(dataStartCol));
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
	    List<String> list = new ArrayList<String>();
		if(Common.notEmpty(contractTypeTexts)){
				/*for (HhBase obj : contractTypeText) {
					if(!obj.getDescription().equals(contractTypeTexts.trim())){
						result=CommitResult.createErrorResult("第" + (i+1) + "行,第"+(dataStartCol+1)+"列" + "合同类型值不匹配，请重新输入");
						return result;
					}
				}*/
				for (int j = 0; j < contractTypeText.size(); j++) {
					String a = contractTypeText.get(j).getDescription();
					list.add(a);
				}
				if(list.size()>0){
					if(!list.contains(contractTypeTexts)){
						result=CommitResult.createErrorResult("第" + (i+1) + "行,第"+(dataStartCol+1)+"列," + "合同类型不规范，请重新填写");
						return result;
					}
				}
		}
		t.setContractTypeText(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		
		//合同类别
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 50);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setContractKindText(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		
		//行业领域
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 255);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setIndustrySector(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		
		//履行跟踪人
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 50);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setTrackingMan(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		
		//合同承办部门
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 255);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setUndertakeDepartment(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		
		//合同标的总额(人民币)
		String contractTotalAmountStr= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(contractTotalAmountStr, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setContractTotalAmount(Common.notEmpty(contractTotalAmountStr)?BigDecimal.valueOf(parseDouble(contractTotalAmountStr)):null);
		//是否属于重大合同
		t.setIsMajorContract("是".equalsIgnoreCase(exceltool.getCellValue(workbook, row.getCell(dataStartCol++))));
		
		//合同主要内容
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 3000);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setContractContent(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//合同主要履约节点
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 3000);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setContractNode(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//法务审核人
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 50);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setLegalPerson(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		/*//合同签订日期
		 * 
		t.setContractSignDate(exceltool.getCellValue(workbook, row.getCell(dataStartCol +16)));*/
		//生效日期
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 50);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setContractEffectiveDate(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//到期日期
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 50);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setContractDeadlineDate(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//履约阶段
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 300);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setPerformancePhase(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//是否存在违约/异常
		t.setIsDefault("是".equals( exceltool.getCellValue(workbook, row.getCell(dataStartCol++))));
		//我方违约情况
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 300);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setOurDefault(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//逾期违约条款约定
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 500);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setOverdueDefaultClauseAgreement(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//逾期应付6月以下
		String overduePay1String=exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(overduePay1String, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOverduePay1(Common.notEmpty(overduePay1String)?BigDecimal.valueOf(parseDouble(overduePay1String)):null);
		//逾期应付6月~1年
		String overduePay2String=exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(overduePay2String, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOverduePay2(Common.notEmpty(overduePay2String)?BigDecimal.valueOf(parseDouble(overduePay2String)):null);
		//逾期应付1年~两年
		String overduePay3String=exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(overduePay3String, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOverduePay3(Common.notEmpty(overduePay3String)?BigDecimal.valueOf(parseDouble(overduePay3String)):null);
		//逾期应付2年~3年
		String overduePay4String=exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(overduePay4String, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOverduePay4(Common.notEmpty(overduePay4String)?BigDecimal.valueOf(parseDouble(overduePay4String)):null);
		//逾期应付3年以上
		String overduePay5String=exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(overduePay5String, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOverduePay5(Common.notEmpty(overduePay5String)?BigDecimal.valueOf(parseDouble(overduePay5String)):null);
		//我方逾期应付款总额
		String ourOverPayStr= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(ourOverPayStr, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOurOverPay(Common.notEmpty(ourOverPayStr)?BigDecimal.valueOf(parseDouble(ourOverPayStr)):null);
		//我方违约产生的违约金总额
		String ourDefaultPayStr= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(ourDefaultPayStr, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOurDefaultPay(Common.notEmpty(ourDefaultPayStr)?BigDecimal.valueOf(parseDouble(ourDefaultPayStr)):null);
		//对方违约情况
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 300);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setOtherDefault(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//逾期应收6月以下
		String overdueReceivables1String= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(overdueReceivables1String, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOverdueReceivables1(Common.notEmpty(overdueReceivables1String)?BigDecimal.valueOf(parseDouble(overdueReceivables1String)):null);
		//逾期应收6月~1年
		String overdueReceivables2String= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(overdueReceivables2String, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOverdueReceivables2(Common.notEmpty(overdueReceivables2String)?BigDecimal.valueOf(parseDouble(overdueReceivables2String)):null);
		//逾期应收1年~两年
		String overdueReceivables3String= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(overdueReceivables3String, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOverdueReceivables3(Common.notEmpty(overdueReceivables3String)?BigDecimal.valueOf(parseDouble(overdueReceivables3String)):null);
		//逾期应收2年~3年
		String overdueReceivables4String= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(overdueReceivables4String, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOverdueReceivables4(Common.notEmpty(overdueReceivables4String)?BigDecimal.valueOf(parseDouble(overdueReceivables4String)):null);
		//逾期应收3年以上
		String overdueReceivables5String= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(overdueReceivables5String, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOverdueReceivables5(Common.notEmpty(overdueReceivables5String)?BigDecimal.valueOf(parseDouble(overdueReceivables5String)):null);
		//对方逾期应付款总额
		String otherOverPayStr= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(otherOverPayStr, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOtherOverPay(Common.notEmpty(otherOverPayStr)?BigDecimal.valueOf(parseDouble(otherOverPayStr)):null);
		//当期坏账损失
		String currentBadLossesString= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(currentBadLossesString, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setCurrentBadLosses(Common.notEmpty(currentBadLossesString)?BigDecimal.valueOf(parseDouble(currentBadLossesString)):null);
		//对方违约产生的违约金总额
		String otherDefaultPayStr= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(otherDefaultPayStr, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setOtherDefaultPay(Common.notEmpty(otherDefaultPayStr)?BigDecimal.valueOf(parseDouble(otherDefaultPayStr)):null);
		//是否涉诉
		t.setIsInvolving("是".equalsIgnoreCase(exceltool.getCellValue(workbook, row.getCell(dataStartCol++))));
		//涉诉案件标的金额
		String involvingAccountString= exceltool.getCellValue(workbook, row.getCell(dataStartCol++));
		v1 = excelValidate.validate(involvingAccountString, "Empty", "double", 20);
		result=judgeexcelValidate(v1,i+1,dataStartCol-1);
			if (!result.isResult()) {
				return result;
			}
		t.setInvolvingAccount(Common.notEmpty(involvingAccountString)?BigDecimal.valueOf(parseDouble(involvingAccountString)):null);
		//风险等级
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 255);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setRiskGrade(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//是否属于重点关注合同
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 255);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setIsFocus("是".equalsIgnoreCase(exceltool.getCellValue(workbook, row.getCell(dataStartCol++))));
		//风险描述
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 300);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setRiskDescription(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//风险成因
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 300);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setRiskCause(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//风险类型
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 255);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setRiskType(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//预警日期及形式
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 255);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setWarningDateForm(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//风险应对策略
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 255);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setRiskSteategy(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//风险应对措施
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 255);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setRiskCountermeasures(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//风险处置方案及进展
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 1000);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setRiskManage(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//风险是否排除及排除情况
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 255);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setRiskExcluded(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//业务责任人
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 255);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setBusinessOwner(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//法务责任人
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 50);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setLegalResponsibilities(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		//备注
		v1 = excelValidate.validate(exceltool.getCellValue(workbook, row.getCell(dataStartCol)), "Empty", "varChar", 50);
		result=judgeexcelValidate(v1,i+1,dataStartCol);
			if (!result.isResult()) {
				return result;
			}
		t.setRemark(exceltool.getCellValue(workbook, row.getCell(dataStartCol++)));
		
		return result;
	}
	
	public CommitResult judgeexcelValidate(ExcelCellValueValidateResult v1,int i,int j) {
		CommitResult result=new CommitResult();
		if(v1.isResult()){
			result.setResult(true);
			return result;
    	}else{
    		result=CommitResult.createErrorResult("第" + (i) + "行,第"+(j+1)+"列" + v1.getDescription());
			return result;
    	}
	}
	private Double parseDouble(String v){
		return !StringUtils.isEmpty(v) && Common.canparseNumber(v) ? Double.parseDouble(v) : 0d; 
	}

	private Integer saveBimrContractMonth(String compId,Integer year, Integer month,
			Integer status, String compName ,HhUsers user,String reportDate, BigDecimal totalMoney, int count){
		
		BimrContractMonth entity =new BimrContractMonth();
		if(50113==status){
			entity.setReportDate(reportDate);
			entity.setReportPersonId(user.getVcEmployeeId());
			entity.setReportPersonName(user.getVcName());
		}
		entity.setLastModifyDate(reportDate);
		entity.setLastModifyPersonId(user.getVcEmployeeId());
		entity.setLastModifyPersonName(user.getVcName());
		entity.setStatus(status);//未上报
		entity.setIsdel(0);
		entity.setVersion(1);
		entity.setYear(year);
		entity.setMonth(month);
		entity.setCompId(compId);
		entity.setCreateDate(reportDate);
		entity.setCreatePersonId(user.getVcEmployeeId());
		entity.setCreatePersonName(user.getVcName());
		entity.setTotalMoney(totalMoney);
		entity.setTotalContract(count);
		
		return contractMonthDao.addBimrContractMonth(entity);
	}
	
	private void saveBatchContractList(Integer contractMonthId, List<BimrContractMonthList> data){
		for(BimrContractMonthList o: data) {
			o.setContractMonthId(contractMonthId);
		}
			contractMonthDao.addBimrContractMonthLists(data);
	}
	
	@Override
	public CommitResult updateStatus(BimrContractMonth entity, HhUsers use) {
		// TODO Auto-generated method stub
		CommitResult result=new CommitResult();
		/*result=theTimeDocumentCheck(entity);
		if(!result.isResult())
		{
			return result;
		}*/
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
			
			if(entity.getStatus().intValue()==Base.examstatus_2.intValue()){
				entity.setReportPersonName(use.getVcName());
				entity.setReportPersonId(use.getVcEmployeeId());
				entity.setReportDate(df.format(new Date()));
			}
				
			if(entity.getStatus().intValue()==Base.examstatus_3.intValue() ||entity.getStatus().intValue()==Base.examstatus_4.intValue()){
				entity.setAuditorPersonName(use.getVcName());
				entity.setAuditorPersonId(use.getVcEmployeeId());
				entity.setAuditorDate(df.format(new Date()));
			}
			
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	@Override
	public MsgPage getContractMonthListView(BimrContractMonthList entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = contractMonthDao.getContractMonthDetailListCount(entity);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrContractMonthList> list = contractMonthDao.getContractMonthDetailList(entity, offset, pageSize);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}
	
	@Override
	public BimrContractMonthList GetBimrContractMonthListById(Integer id){
		return contractMonthDao.GetBimrContractMonthListById(id);
	}
	
	@Override
	public CommitResult saveAuditSubmit(Integer id, HhUsers user) {
		CommitResult result = new CommitResult();
		BimrContractMonth t = (BimrContractMonth)commonDao.findById(BimrContractMonth.class, id);
		if(t == null){
			result.setResult(false);
			result.setExceptionStr("合同台账不存在");
			return result;
		}
		
		if(t.getStatus().intValue() == Base.examstatus_2.intValue() || 
				t.getStatus().intValue() ==Base.examstatus_4.intValue()){
			
			result.setResult(false);
			result.setExceptionStr("已经提交审核");
			return result;
		}
		
		Integer status =  Base.examstatus_2;
		t.setReportDate(Common.getDate(YYYY_MM_DD_HH_MM_SS));
		t.setReportPersonId(user.getVcEmployeeId());
		t.setReportPersonName(user.getVcName());
		updateContractMonthStatus(t, status, user);
		potalMsgService.updatePortalMsg("bimr_contract_month", String.valueOf(t.getId()));
		
		result.setResult(true);
		result.setEntity(t);
		
		return result;
	}
	
	private void updateContractMonthStatus(BimrContractMonth t, Integer status, HhUsers user){
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		t.setLastModifyDate(dateFormat.format(new Date()));
		t.setLastModifyPersonId(user.getVcEmployeeId());
		t.setLastModifyPersonName(user.getVcName());
		if(status!=Base.examstatus_2){
			t.setAuditorPersonName(user.getVcName());
			t.setAuditorPersonId(user.getVcEmployeeId());
			t.setAuditorDate(dateFormat.format(new Date()));
		}
		
		t.setStatus(status);
		commonDao.saveOrUpdate(t);
	}

	@Override
	public CommitResult saveAudit(Integer id, String content, boolean isPass,HhUsers user) {
		CommitResult result = new CommitResult();
		BimrContractMonth t = (BimrContractMonth)commonDao.findById(BimrContractMonth.class, id);
		if(t == null){
			result.setResult(false);
			result.setExceptionStr("合同台账不存在");
			return result;
		}
		
		if(t.getStatus().intValue() != Base.examstatus_2.intValue() ){
			result.setResult(false);
			result.setExceptionStr("未提交审核");
			return result;
		}
		
		Integer status = isPass? Base.examstatus_4: Base.examstatus_3;
		updateContractMonthStatus(t, status, user);
		examineService.saveExamine(EXAMINE_CONTRACT_MONTH_ID, t.getId(), user, content, isPass? 1: 0);
		
		result.setResult(true);
		result.setEntity(t);
		
		return result;
	}

	@Override
	public List<BimrContractMonth> getContractMonthListExport(
			BimrContractMonth entity) {
		// TODO Auto-generated method stub
		 List<BimrContractMonth> list = contractMonthDao.getContractMonthListExport(entity);
		return list;
	}

	@Override
	public List<BimrContractMonthList> getBimrContractMonthListExport(
			Integer contractMonthId) {
		// TODO Auto-generated method stub
		//查询参数构造
				BimrContractMonthList qryParams = new BimrContractMonthList();
				qryParams.setContractMonthId(contractMonthId);
				List<BimrContractMonthList> list = contractMonthDao.getBimrContractMonthListExport(qryParams);		
		return list;
	}

	@Override
	public XSSFWorkbook getExportWorkBook(List<BimrContractMonthList> exportList) {
		// TODO Auto-generated method stub
		XSSFWorkbook workBook = new XSSFWorkbook();
		 XSSFCell cell = null;
		 XSSFSheet sheet = workBook.createSheet("合同台账数据");
		 CellStyle style=workBook.createCellStyle();
		 
		 sheet.addMergedRegion(new CellRangeAddress(0,0,0,55));
		 sheet.addMergedRegion(new CellRangeAddress(1,2,0,20));
		 sheet.addMergedRegion(new CellRangeAddress(1,1,21,55));
		 sheet.addMergedRegion(new CellRangeAddress(2,2,21,22));
		 sheet.addMergedRegion(new CellRangeAddress(2,2,23,31));
		 sheet.addMergedRegion(new CellRangeAddress(2,2,32,40));
		 sheet.addMergedRegion(new CellRangeAddress(2,2,41,42));
		 sheet.addMergedRegion(new CellRangeAddress(2,2,43,52));
		 sheet.addMergedRegion(new CellRangeAddress(2,2,53,55));
		 XSSFRow row = sheet.createRow((int) 0);  
		 
		 String[] header={"序号","核心企业","专业公司","合同公文号","合同名称","我方合同主体",
				 "对方合同主体","是否属于集团内部企业间合同","项目名称","合同类型","合同类别","行业领域","合同履行跟踪人","合同承办部门","合同标的金额","是否属于重大合同","主要内容","合同主要履约节点","法务审核人",
				 "生效日期","到期日期","履约阶段","是否存在违约/异常","我方违约情况", "逾期违约条款约定","逾期应付6个月以下","逾期应付6个月-1年","逾期应付1年-2年",	"逾期应付2年-3年","逾期应付3年以上","逾期应付款总额","应付违约金总额",
				 "对方违约情况","逾期应收6个月以下","逾期应收6个月-1年","逾期应收1年-2年","逾期应收2年-3年","逾期应收3年以上","逾期应收款总额","当期坏账损失","对方违约产生的违约金总额","是否涉诉","涉诉案件标的金额",
				 "风险等级","是否属于重点关注合同","风险描述","风险成因","风险类型","预警日期及形式","风险应对策略","风险应对措施","风险处置方案及进展","风险是否排除及排除情况","业务责任人","法务责任人","备注"};
		 XSSFFont font = workBook.createFont();
		 font.setBold(true);
		 cell = row.createCell((short) 0);
		 cell.setCellValue("海航物流合同信息统计表（单位：万元）");
		 font.setFontHeightInPoints((short) 15);
		 style.setFont(font);
		 style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中  
		 style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直  
		 cell.setCellStyle(style);
		 
		 font.setFontHeightInPoints((short) 12);
		 style.setFont(font);
		 row=sheet.createRow((int) 1);
		 cell = row.createCell((short) 0);
		 cell.setCellValue("合同基础信息（用印前）");
		 style.setAlignment(HorizontalAlignment.CENTER);
		 cell.setCellStyle(style);
		 
		 cell = row.createCell((short) 21);
		 cell.setCellValue("合同履行及风险处置情况（用印后）");
		 style.setAlignment(HorizontalAlignment.CENTER);
		 cell.setCellStyle(style);
		 
		 
		 row=sheet.createRow((int) 2);
		 cell = row.createCell((short) 21);
		 cell.setCellValue("履行进展");
		 style.setAlignment(HorizontalAlignment.CENTER);
		 cell.setCellStyle(style);
		 
		 cell = row.createCell((short) 23);
		 cell.setCellValue("我方违约情况");
		 style.setAlignment(HorizontalAlignment.CENTER);
		 cell.setCellStyle(style);
		 
		 cell = row.createCell((short) 32);
		 cell.setCellValue("对方违约情况");
		 style.setAlignment(HorizontalAlignment.CENTER);
		 cell.setCellStyle(style);
		 
		 cell = row.createCell((short) 41);
		 cell.setCellValue("涉诉情况");
		 style.setAlignment(HorizontalAlignment.CENTER);
		 cell.setCellStyle(style);
		 
		 cell = row.createCell((short) 43);
		 cell.setCellValue("不正常履行情形及处置进展");
		 style.setAlignment(HorizontalAlignment.CENTER);
		 cell.setCellStyle(style);
		 
		 cell = row.createCell((short) 53);
		 cell.setCellValue("其他");
		 style.setAlignment(HorizontalAlignment.CENTER);
		 cell.setCellStyle(style);
		 
		 row=sheet.createRow((int) 3);
		 style.setAlignment(HorizontalAlignment.CENTER);
		 cell.setCellStyle(style);
		 for (int i = 0; i < header.length; i++) {
			 cell = row.createCell((short) i);
			 cell.setCellValue(header[i]);
			 cell.setCellStyle(style);
		 }
		
		 for (int i = 0; i < exportList.size(); i++) {  
			 	int k=0;
			     row = sheet.createRow((int) i + 4);
			     BimrContractMonthList value = exportList.get(i);
			     //序号
			     row.createCell((short)k++).setCellValue(i+1);
			     //所属核心企业
			     row.createCell((short)k++).setCellValue(value.getContractBelongCompany());
			     //专业公司
			     row.createCell((short)k++).setCellValue(value.getContractSubordinateCompany());
			     //合同公文号
			     row.createCell((short)k++).setCellValue(value.getContractDocumentNo());
			     //合同名称
			     row.createCell((short)k++).setCellValue(value.getContractName());
			   //我方合同主体
			     row.createCell((short)k++).setCellValue(value.getOurContractSubject());
			     //对方合同主体
			     row.createCell((short)k++).setCellValue(value.getOtherContractSubject());
			     //是否属于集团内部企业间合同
			     if (null!=value.getIsCom()) {
			    	 if(value.getIsCom()){
				    	 row.createCell((short)k++).setCellValue("是");
				     }else if (value.getIsCom()) {
				    	 row.createCell((short)k++).setCellValue("否");
					 }else {
						row.createCell((short)k++).setCellValue("");
					 }
				}else {
					row.createCell((short)k++).setCellValue("");
				}
			     
			     //项目名称
			     row.createCell((short)k++).setCellValue(value.getProjectName());
			     //合同类型
			     row.createCell((short)k++).setCellValue(value.getContractTypeText());
			     //合同类别
			     row.createCell((short)k++).setCellValue(value.getContractKindText());
			     //行业领域
			     row.createCell((short)k++).setCellValue(value.getIndustrySector());
			     //履行跟踪人
			     row.createCell((short)k++).setCellValue(value.getTrackingMan());
			     //合同承办部门
			     row.createCell((short)k++).setCellValue(value.getUndertakeDepartment());
			    //合同标的金额(人民币)
			     if(null!=value.getContractTotalAmount()){
				     row.createCell((short)k++).setCellValue(value.getContractTotalAmount().doubleValue());
			     }else{
			    	 row.createCell((short)k++).setCellValue("");
			     }
			     //是否属于重大合同
			     if (null!=value.getIsMajorContract()) {
			    	 if(value.getIsMajorContract()){
				    	 row.createCell((short)k++).setCellValue("是");
				     }else if (value.getIsMajorContract()) {
				    	 row.createCell((short)k++).setCellValue("否");
					 }else {
						row.createCell((short)k++).setCellValue("");
					 }
				}else {
					row.createCell((short)k++).setCellValue("");
				}
			    
			     //合同主要内容
			     row.createCell((short)k++).setCellValue(value.getContractContent());
			     //合同主要履约节点
			     row.createCell((short)k++).setCellValue(value.getContractNode());
			     //法务审核人
			     row.createCell((short)k++).setCellValue(value.getLegalPerson());
			     //合同签订日期
			     //row.createCell((short)k++).setCellValue(value.getContractSignDate());
			     //生效日期
			     row.createCell((short)k++).setCellValue(value.getContractEffectiveDate());
			     //到期日期
			     row.createCell((short)k++).setCellValue(value.getContractDeadlineDate());
			     //履约阶段
			     row.createCell((short)k++).setCellValue(value.getPerformancePhase());
			     //是否违约
			     if (null!=value.getIsDefault()) {
			    	 if(value.getIsDefault()){
				    	 row.createCell((short)k++).setCellValue("是");
				     }else if (value.getIsDefault()) {
				    	 row.createCell((short)k++).setCellValue("否");
					 }else {
						row.createCell((short)k++).setCellValue("");
					 }
				}else {
					row.createCell((short)k++).setCellValue("");
				}
			    
			     //我方违约情况
			     row.createCell((short)k++).setCellValue(value.getOurDefault());
			     //逾期违约条款约定
			     row.createCell((short)k++).setCellValue(value.getOverdueDefaultClauseAgreement());
			   //逾期应付(6个月以下)
			     if(null!=value.getOverduePay1()){
			    	 row.createCell((short)k++).setCellValue(value.getOverduePay1().doubleValue());
			     }else {
			    	 row.createCell((short)k++).setCellValue("");
				}
			   //逾期应付(6个月~1年)
			     if(null!=value.getOverduePay2()){
			    	 row.createCell((short)k++).setCellValue(value.getOverduePay2().doubleValue());
			     }else {
			    	 row.createCell((short)k++).setCellValue("");
				}
			   //逾期应付(1年~2年)
			     if(null!=value.getOverduePay3()){
			    	 row.createCell((short)k++).setCellValue(value.getOverduePay3().doubleValue());
			     }else {
			    	 row.createCell((short)k++).setCellValue("");
				}
			   //逾期应付(2年~3年)
			     if(null!=value.getOverduePay4()){
			    	 row.createCell((short)k++).setCellValue(value.getOverduePay4().doubleValue());
			     }else {
			    	 row.createCell((short)k++).setCellValue("");
				}
			   //逾期应付(3年以上)
			     if(null!=value.getOverduePay5()){
			    	 row.createCell((short)k++).setCellValue(value.getOverduePay5().doubleValue());
			     }else {
			    	 row.createCell((short)k++).setCellValue("");
				}
			     //我方逾期应付款总额
			     if(null!=value.getOurOverPay()){
				     row.createCell((short)k++).setCellValue(value.getOurOverPay().doubleValue());
			     }else{
			    	 row.createCell((short)k++).setCellValue("");
			     }
			     //我方违约产生的违约金总额
			     if(null!=value.getOurDefaultPay()){
				     row.createCell((short)k++).setCellValue(value.getOurDefaultPay().doubleValue());
			     }else{
			    	 row.createCell((short)k++).setCellValue("");
			     }
			     //对方违约情况
			     row.createCell((short)k++).setCellValue(value.getOtherDefault());
			    
			     //逾期应收(6个月以下)
			     if(null!=value.getOverdueReceivables1()){
			    	 row.createCell((short)k++).setCellValue(value.getOverdueReceivables1().doubleValue());
			     }else {
			    	 row.createCell((short)k++).setCellValue("");
				}
			   //逾期应收(6个月~1年)
			     if(null!=value.getOverdueReceivables2()){
			    	 row.createCell((short)k++).setCellValue(value.getOverdueReceivables2().doubleValue());
			     }else {
			    	 row.createCell((short)k++).setCellValue("");
				}
			   //逾期应收(1年~2年)
			     if(null!=value.getOverdueReceivables3()){
			    	 row.createCell((short)k++).setCellValue(value.getOverdueReceivables3().doubleValue());
			     }else {
			    	 row.createCell((short)k++).setCellValue("");
				}
			   //逾期应收(2年~3年)
			     if(null!=value.getOverdueReceivables4()){
			    	 row.createCell((short)k++).setCellValue(value.getOverdueReceivables4().doubleValue());
			     }else {
			    	 row.createCell((short)k++).setCellValue("");
				}
			   //逾期应收(3年以上)
			     if(null!=value.getOverdueReceivables5()){
			    	 row.createCell((short)k++).setCellValue(value.getOverdueReceivables5().doubleValue());
			     }else {
			    	 row.createCell((short)k++).setCellValue("");
				}
			     
			     //对方逾期应收款总额
			     if(null!=value.getOtherOverPay()){
				     row.createCell((short)k++).setCellValue(value.getOtherOverPay().doubleValue());
			     }else{
			    	 row.createCell((short)k++).setCellValue("");
			     }
			     //当期坏账损失
			     if(null!=value.getCurrentBadLosses()){
				     row.createCell((short)k++).setCellValue(value.getCurrentBadLosses().doubleValue());
			     }else{
			    	 row.createCell((short)k++).setCellValue("");
			     }
			     
			     //对方违约产生的违约金总额
			     if(null!=value.getOtherDefaultPay()){
				     row.createCell((short)k++).setCellValue(value.getOtherDefaultPay().doubleValue());
			     }else{
			    	 row.createCell((short)k++).setCellValue("");
			     }
			     //是否涉诉
			     if(null!=value.getIsInvolving()){
			    	 if(value.getIsInvolving()){
				    	 row.createCell((short)k++).setCellValue("是");
				     }else if (value.getIsDefault()) {
				    	 row.createCell((short)k++).setCellValue("否");
				     }else {
				    	 row.createCell((short)k++).setCellValue("");
					}
				 }else {
					row.createCell((short)k++).setCellValue("");
				 }
			   //涉诉案件标的金额
			     if(null!=value.getInvolvingAccount()){
				     row.createCell((short)k++).setCellValue(value.getInvolvingAccount().doubleValue());
			     }else{
			    	 row.createCell((short)k++).setCellValue("");
			     }
			     
			     //风险等级
			     row.createCell((short)k++).setCellValue(value.getRiskGrade());
			     //是否属于重点关注合同
			     if (null!=value.getIsFocus()) {
			    	 if(value.getIsFocus()){
				    	 row.createCell((short)k++).setCellValue("是");
				     }else if (value.getIsDefault()) {
				    	 row.createCell((short)k++).setCellValue("否");
					 }else {
						row.createCell((short)k++).setCellValue("");
					 }
				}else {
					row.createCell((short)k++).setCellValue("");
				}
			     
			   //风险描述
			     row.createCell((short)k++).setCellValue(value.getRiskDescription());
			   //风险成因
			     row.createCell((short)k++).setCellValue(value.getRiskCause());
			   //风险类型
			     row.createCell((short)k++).setCellValue(value.getRiskType());
			   //预警日期及形式
			     row.createCell((short)k++).setCellValue(value.getWarningDateForm());
			   //风险应对策略
			     row.createCell((short)k++).setCellValue(value.getRiskSteategy());
			   //风险应对措施
			     row.createCell((short)k++).setCellValue(value.getRiskCountermeasures());
			     //风险处置方案及进展
			     row.createCell((short)k++).setCellValue(value.getRiskManage());
			   //风险是否排除及排除情况
			     row.createCell((short)k++).setCellValue(value.getRiskExcluded());
			   //业务责任人
			     row.createCell((short)k++).setCellValue(value.getBusinessOwner());
			   //法务责任人
			     row.createCell((short)k++).setCellValue(value.getLegalResponsibilities());
			   //备注
			     row.createCell((short)k++).setCellValue(value.getRemark());
		 } 
		 return workBook;
	}
	
	
}
