package com.softline.service.report.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.common.ExcelTool;
import com.softline.dao.report.IReceivableDebtDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportPersonlloaninfo;
import com.softline.entity.ReportReceivabledebt;
import com.softline.entity.ReportReceivabledebtinfo;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReceivableDebtService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.ExcelCellValueValidate;
import com.softline.util.ExcelCellValueValidateResult;
import com.softline.util.MsgPage;

@Service("receivabledebtService")
public class ReceivableDebtService implements IReceivableDebtService{
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	@Autowired
	@Qualifier("receivabledebtDao")
	private IReceivableDebtDao receivabledebtDao;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	
	@Override
	public ReportReceivabledebt getReceivabledebtbyID(Integer id) {
		ReportReceivabledebt entity = receivabledebtDao.getReceivabledebtbyID(id);
		return entity;
	}


	@Override
	public MsgPage getReceivabledebtListView(ReportReceivabledebt entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = receivabledebtDao.getReceivabledebtListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportReceivabledebt> list = receivabledebtDao.getReceivabledebtList(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}


	@Override
	public CommitResult saveReceivabledebt(ReportReceivabledebt entity,
			HhUsers use, MultipartFile itemfile) throws FileNotFoundException,
			IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] time=entity.getDate().split("-");
		entity.setYear(Integer.parseInt(time[0]));
		entity.setMonth(Integer.parseInt(time[1]));
		CommitResult result=saveReceivabledebtCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		
		if(entity.getId()==null)
		{
			entity.setIsdel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			
		}
		else
		{
			entity.setIsdel(0);
			entity.setRecheckDate(df.format(new Date()));
			entity.setRecheckPersonId(use.getVcEmployeeId());
			entity.setRecheckPersonName(use.getVcName());
		}
		
		HhOrganInfoTreeRelation a= systemService.getTreeOrganInfoNode(Base.financetype, entity.getDebtorg());
		entity.setParentorg(a.getVcOrganId());
		
		commonDao.saveOrUpdate(entity);
		if(itemfile!=null && itemfile.getSize()>0)
		{
			try{
				result=saveReceivabledebtinfo( itemfile, entity);
			}catch(Exception e)
			{
				result=CommitResult.createErrorResult("文件不是xls或者xlsx文档或者文档损坏，保存失败");
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;		
			}
			if(result.isResult()==false)
			{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;
			}
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveReceivabledebtCheck(ReportReceivabledebt entity)
	{
		CommitResult result=new CommitResult();
		if(!receivabledebtDao.saveReceivabledebtRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该期已经有数据，不能再添加");
			return result;
		}
		if(!receivabledebtDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 维护人事数据
	 * @param itemfile
	 * @param groupID
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private CommitResult saveReceivabledebtinfo(MultipartFile itemfile,ReportReceivabledebt entity) throws FileNotFoundException, IOException
	{
		CommitResult result=new CommitResult();
		ExcelCellValueValidate excelValidate = new ExcelCellValueValidate();
		receivabledebtDao.deleteReceivabledebtinfo(entity.getId().toString());
		//存入数据库的list
		List<ReportReceivabledebtinfo> Receivabledebtinfo=new ArrayList<ReportReceivabledebtinfo>();
		
		ExcelTool exceltool=new ExcelTool();
        //先暂存附件
        List<String> fileStrList = Common.saveFile(itemfile, DES.receivabledebt_path);
        //文件路径
        String xlsPath=fileStrList.get(1);
        InputStream is = new FileInputStream(xlsPath);
       
        Workbook workbook = null; 
        try { 
        	workbook = new XSSFWorkbook(is); 
        } catch (Exception ex) { 
        	POIFSFileSystem poifsFileSystem= new POIFSFileSystem(is);
        	workbook = new HSSFWorkbook(poifsFileSystem); 
        } 
        Sheet  sheet = workbook.getSheetAt(0);
        Set<String> set = new HashSet<String>();
        
        HhOrganInfoTreeRelation nodeBusiness = systemDao.getTreeOrganInfoNodeBusiness(Base.financetype, entity.getDebtorg());
        String coreOrg=nodeBusiness.getId().getNnodeId();
        String coreOrgNm =nodeBusiness.getVcFullName();
        entity.setCoreorg(coreOrg);
        entity.setCoreorgname(coreOrgNm);
        for (int k = 1; k < sheet.getPhysicalNumberOfRows(); k++) {
        	int i=1;//列索引
        	//行
        	Row row = sheet.getRow(k);
        	ReportReceivabledebtinfo item=new ReportReceivabledebtinfo();
        	//Cell cell= row.getCell(0);
        	item.setIsdel(0);
        	item.setGroupid(entity.getId());
        	//业态ID 名称
        	item.setCoreorg(entity.getCoreorg());
        	item.setCoreorgname(entity.getCoreorgname());
        	item.setDebtorg(entity.getDebtorg());
        	item.setDebtorgname(entity.getDebtorgname());
        	item.setYear(entity.getYear());
        	item.setMonth(entity.getMonth());
        	item.setParentorg(entity.getParentorg());
        	item.setType(entity.getType());
        	//对方公司名称位于第一列
        	String companyname=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setOppositeorgname(companyname);
        	
        	String Collectionperson=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setCollectionperson(Collectionperson);

        	
        	String leadership=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setLeadership(leadership);
        	
        	String accountingsubject=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setAccountingsubject(accountingsubject);
        	
        	String beginningbalance=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	ExcelCellValueValidateResult v1 = excelValidate.validate(beginningbalance, "NotEmpty", "double", 50);
        	if(v1.isResult()){
        		item.setBeginningbalance(beginningbalance.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}
        	
        	
        	String beginningcreditperiodbalance=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(beginningcreditperiodbalance, "NotEmpty", "double", 50);
        	if(v1.isResult()){
        		item.setBeginningcreditperiodbalance(beginningcreditperiodbalance.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}
        	
        	
        	String beginningbalancethirtydays=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(beginningbalancethirtydays, "NotEmpty", "double", 50);
        	if(v1.isResult()){
            	item.setBeginningbalancethirtydays(beginningbalancethirtydays.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}
        	

        	
        	String beginningbalancesixtydays=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(beginningbalancesixtydays, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setBeginningbalancesixtydays(beginningbalancesixtydays.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String beginningbalanceninetydays=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(beginningbalanceninetydays, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setBeginningbalanceninetydays(beginningbalanceninetydays.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String beginningbalanceoverdays=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(beginningbalanceoverdays, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setBeginningbalanceoverdays(beginningbalanceoverdays.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	
        	String monthnewaccout=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(monthnewaccout, "NotEmpty", "double", 30);
        	if(v1.isResult()){
        		item.setMonthnewaccout(monthnewaccout.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}
        	
        	
        	String monthreturnaccout=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(monthreturnaccout, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setMonthreturnaccout(monthreturnaccout.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String momthreturnoveraccout=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(momthreturnoveraccout, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setMomthreturnoveraccout(momthreturnoveraccout.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String endingbalance=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(endingbalance, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setEndingbalance(endingbalance.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String endingcreditperiodbalance=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(endingcreditperiodbalance, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setEndingcreditperiodbalance(endingcreditperiodbalance.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String endingbalancethirtydays=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(endingbalancethirtydays, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setEndingbalancethirtydays(endingbalancethirtydays.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String endingbalancesixtydays=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(endingbalancesixtydays, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setEndingbalancesixtydays(endingbalancesixtydays.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String endingbalanceninetydays=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(endingbalanceninetydays, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setEndingbalanceninetydays(endingbalanceninetydays.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String endingbalanceoverdays=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(endingbalanceoverdays, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setEndingbalanceoverdays(endingbalanceoverdays.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String endingbalancetwoyears=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(endingbalancetwoyears, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setEndingbalancetwoyears(endingbalancetwoyears.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String endingbalancethreeyears=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(endingbalancethreeyears, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setEndingbalancethreeyears(endingbalancethreeyears.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String endingbalancefiveyears=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(endingbalancefiveyears, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setEndingbalancefiveyears(endingbalancefiveyears.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String endingbalanceoveryears=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	v1 = excelValidate.validate(endingbalanceoveryears, "NotEmpty", "double", 30);
        	if(v1.isResult()){
            	item.setEndingbalanceoveryears(endingbalanceoveryears.replace(",", ""));
        	}else{
        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
				return result;
        	}

        	
        	String reasonorremarks=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setReasonorremarks(reasonorremarks);
        	
        	String debttype=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setDebttype(debttype);
        	
        	String cashdepositdeadline=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setCashdepositdeadline(cashdepositdeadline);
        	
        	String collectionmeasures=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setCollectionmeasures(collectionmeasures);
        	
        	String planreturntime=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setPlanreturntime(planreturntime);
        	
        	String is_finish=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setIsFinish(is_finish);
        	
        	String is_send=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setIsSend(is_send);
        	
        	String is_promise=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setIsPromise(is_promise);
        	
        	String is_turnover=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setIsTurnover(is_turnover);
        	
        	Receivabledebtinfo.add(item);
        	
        }
        receivabledebtDao.saveReceivabledebtinfoitem(Receivabledebtinfo);
        double beginningbalance = 0.00; double endingbalance = 0.00;
        if (Receivabledebtinfo !=null && Receivabledebtinfo.size()>0) {
			for (ReportReceivabledebtinfo info : Receivabledebtinfo) {
				beginningbalance =sum(Double.valueOf(beginningbalance), Double.valueOf(info.getBeginningbalance()));
				endingbalance = sum(Double.valueOf(endingbalance), Double.valueOf(info.getEndingbalance()));
			}
		}
        entity.setBeginningbalance(String.valueOf(beginningbalance));
        entity.setEndingbalance(String.valueOf(endingbalance));
        commonDao.saveOrUpdate(entity);
        result.setResult(true);
        return result;
	}

	public static double sum(double d1,double d2){ 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.add(bd2).doubleValue(); 
    } 

	@Override
	public CommitResult deleteReceivabledebt(Integer id, HhUsers use) {
		ReportReceivabledebt entity=receivabledebtDao.getReceivabledebtbyID(id);
		CommitResult result=new CommitResult();
		if(!receivabledebtDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		if(entity!=null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			receivabledebtDao.deleteReceivabledebtinfo(entity.getId().toString());
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}


	@Override
	public CommitResult saveReceivabledebtExamine(Integer entityid,
			String examStr, Integer examResult, HhUsers use) {
		CommitResult result=new CommitResult();
		ReportReceivabledebt entity=getReceivabledebtbyID(entityid);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		result=saveReceivabledebtExamineCheck(entityid);
		if(!result.isResult())
		{
			return result;
		}
		//审核不通过
		if(examResult.equals(Base.examresult_1))
			entity.setStatus(Base.examstatus_3);
		//审核通过
		else if(examResult.equals(Base.examresult_2))
			entity.setStatus(Base.examstatus_4);
		String a=df.format(new Date());
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		//保存HrPersongroup
		commonDao.saveOrUpdate(entity);
		
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_receivabledebtinner, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}
	
	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 */
	public CommitResult saveReceivabledebtExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportReceivabledebt entity=receivabledebtDao.getReceivabledebtbyID(entityID);
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
		if(!entity.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}
		result.setResult(true);
		return result;
	}


	@Override
	public MsgPage getReceivabledebtInfoListView(ReportReceivabledebt entity, Integer curPageNum,Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = receivabledebtDao.getReceivabledebtinfoCount(entity.getId());
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportReceivabledebtinfo> list = receivabledebtDao.getReceivabledebtinfoList(entity.getId(), offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}



	
	/**
	 *   --------------------------------应收债权(内部)明细查询---------------------------------------
	 */
	
	@Override
	public MsgPage getReceivabledebtinfoListView(ReportReceivabledebtinfo entity, Integer curPageNum,Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = receivabledebtDao.getReceivabledebtinfoDetailCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportReceivabledebtinfo> list = receivabledebtDao.getReceivabledebtinfoDetailList(entity,offset,pageSize);
    	if (list != null && list.size()>0) {
    		for (ReportReceivabledebtinfo info : list) {
    			Double endingbalance = info.getEndingbalance() != null?Double.valueOf(info.getEndingbalance()):0.00;
    			Double endingcreditperiodbalance = info.getEndingcreditperiodbalance() != null?Double.valueOf(info.getEndingcreditperiodbalance()):0.00;
    			info.setOvermoney(String.valueOf(sub(endingbalance,endingcreditperiodbalance)));
			}
		}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	//求两个double类型的差值
	public static double sub(double v1,double v2){   
		BigDecimal b1 = new BigDecimal(Double.toString(v1));   
		BigDecimal b2 = new BigDecimal(Double.toString(v2));   
		return b1.subtract(b2).doubleValue();  
	}


	@Override
	public ReportReceivabledebtinfo getReceivabledebtinfobyID(Integer id) {
		ReportReceivabledebtinfo entity = receivabledebtDao.getReceivabledebtinfobyID(id);
		return entity;
	}



	
	/**
	 *   --------------------------------应收债权(内部)汇总查询---------------------------------------
	 */
	
	@Override
	public MsgPage getReceivabledebtinfoCollectList(ReportReceivabledebtinfo entity,Integer curPageNum,Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = receivabledebtDao.getReceivabledebtinfoCollectCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object> list = receivabledebtDao.getReceivabledebtinfoCollectList(entity,offset,pageSize);
    	List<ReportReceivabledebtinfo> returnList = new ArrayList<ReportReceivabledebtinfo>();
    	if (list != null && list.size()>0) {
			for (Object object : list) {
				Object[] obj = (Object[])object;
				ReportReceivabledebtinfo info = new ReportReceivabledebtinfo();
				info.setGroupid(Integer.valueOf(obj[0].toString()));
				info.setYear(obj[1] != null?Integer.valueOf(obj[1].toString()):null);
				info.setMonth(obj[2] != null?Integer.valueOf(obj[2].toString()):null);
				info.setCoreorgname(obj[3] != null?obj[3].toString():"");
				info.setDebtorgname(obj[4] != null?obj[4].toString():"");
				//info.setOppositeorgname(obj[5] != null?obj[5].toString():"");
				info.setBeginningbalance(obj[5] != null?obj[5].toString():"");
				info.setEndingbalance(obj[6] != null?obj[6].toString():"");
				info.setOvermoney(obj[7] != null?obj[7].toString():"");
				info.setBadmoney(obj[8] != null?obj[8].toString():"");
				returnList.add(info);
			}
		}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(returnList);    
        return msgPage;
	}

	
	/**
	 *   --------------------------------公司大额应收债权(内部)查询---------------------------------------
	 */

	@Override
	public MsgPage getReceivabledebtinfoOrgList(ReportReceivabledebtinfo entity, Integer curPageNum,Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
       // Integer totalRecords = receivabledebtDao.getReceivabledebtinfoOrgCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object> list = receivabledebtDao.getReceivabledebtinfoOrgList(entity,offset,pageSize);
    	List<ReportReceivabledebtinfo> returnList = new ArrayList<ReportReceivabledebtinfo>();
    	if (list != null && list.size()>0) {
			for (Object object : list) {
				Object[] obj = (Object[])object;
				ReportReceivabledebtinfo info = new ReportReceivabledebtinfo();
				info.setId(Integer.valueOf(obj[0].toString()));
				info.setYear(obj[1] != null?Integer.valueOf(obj[1].toString()):null);
				info.setMonth(obj[2] != null?Integer.valueOf(obj[2].toString()):null);
				info.setCoreorgname(obj[3] != null?obj[3].toString():"");
				info.setDebtorgname(obj[4] != null?obj[4].toString():"");
				info.setOppositeorgname(obj[5] != null?obj[5].toString():"");
				info.setEndingbalance(obj[6] != null?obj[6].toString():"");
				info.setOvermoney(obj[7] != null?obj[7].toString():"");
				info.setCollectionperson(obj[8] != null?obj[8].toString():"");
				returnList.add(info);
			}
		}
    	List<ReportReceivabledebtinfo> top5 = new ArrayList<ReportReceivabledebtinfo>();
    	int i=0;
    	while (i<returnList.size()) {
    		 ReportReceivabledebtinfo info1 = returnList.get(i);
    		 String year = String.valueOf(info1.getYear());
    		 String month = String.valueOf(info1.getMonth());
    		 String coreorgName = info1.getCoreorgname();
    		 String debtorgname = info1.getDebtorgname();
    		 List<ReportReceivabledebtinfo> topList = new ArrayList<ReportReceivabledebtinfo>();
    		 topList.add(info1);
    		 int j = i+1;
    		 i =i+1;
    		 while (j<returnList.size()) {
    			 ReportReceivabledebtinfo info2 = returnList.get(j);
    			 String year1 = String.valueOf(info2.getYear());
        		 String month1 = String.valueOf(info2.getMonth());
        		 String coreorgName1 = info2.getCoreorgname();
        		 String debtorgname1 = info2.getDebtorgname();
    			 if (info1.getId() !=info2.getId() && year.equals(year1) && month.equals(month1)  && coreorgName.equals(coreorgName1)  && debtorgname.equals(debtorgname1)) {
    				 if (topList.size()<5) {
    					 topList.add(info2);
    					 j++;
    					 i++;
					}else {
						i++;
						j++;
						continue;
					}
    				 
    			 }else {
					break;
				}
				
    			
    				 
    				 
			}
    		 top5.addAll(topList);
		}
    	Integer totalRecords = top5.size();
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	int fromIndex = (curPageNum - 1) * pageSize;
    	int toIndex = curPageNum * pageSize;
    	if (toIndex >= totalRecords) {
    		toIndex = totalRecords;
		}
    	List<ReportReceivabledebtinfo> ls = top5.subList(fromIndex, toIndex);
    	msgPage.setList(ls);    
        return msgPage;
	}



	
	

	/**
	 *   --------------------------------超期外部应收账款无催收进展一览---------------------------------------
	 */
	@Override
	public MsgPage getOverOutList(ReportReceivabledebtinfo entity,Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = receivabledebtDao.getOverOutCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object> list = receivabledebtDao.getOverOutList(entity,offset,pageSize);
    	List<ReportReceivabledebtinfo> returnList = new ArrayList<ReportReceivabledebtinfo>();
    	if (list != null && list.size()>0) {
			for (Object object : list) {
				Object[] obj = (Object[])object;
				ReportReceivabledebtinfo info = new ReportReceivabledebtinfo();
				info.setGroupid(Integer.valueOf(obj[0].toString()));
				info.setYear(obj[1] != null?Integer.valueOf(obj[1].toString()):null);
				info.setMonth(obj[2] != null?Integer.valueOf(obj[2].toString()):null);
				info.setCoreorgname(obj[3] != null?obj[3].toString():"");
				info.setDebtorgname(obj[4] != null?obj[4].toString():"");
				//info.setOppositeorgname(obj[5] != null?obj[5].toString():"");
				info.setBeginningbalance(obj[5] != null?obj[5].toString():"");
				info.setEndingbalance(obj[6] != null?obj[6].toString():"");
				info.setOvermoney(obj[7] != null?obj[7].toString():"");
				//info.setBadmoney(obj[8] != null?obj[8].toString():"");
				returnList.add(info);
			}
		}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(returnList);    
        return msgPage;
	}
	
}
