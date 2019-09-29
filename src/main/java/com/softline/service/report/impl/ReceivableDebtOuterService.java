package com.softline.service.report.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.common.DES;
import com.softline.common.ExcelDataTreating;
import com.softline.common.ExcelTool;
import com.softline.dao.report.IReceivableDebtOuterDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.ReportReceivabledebtOuter;
import com.softline.entity.ReportReceivabledebtinfo;
import com.softline.entity.ReportReceivabledebtinfoOuter;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReceivableDebtOuterService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.ExcelCellValueValidate;
import com.softline.util.ExcelCellValueValidateResult;
import com.softline.util.MsgPage;

@Service("receivabledebtOuterService")
public class ReceivableDebtOuterService implements IReceivableDebtOuterService{
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	@Autowired
	@Qualifier("receivabledebtOuterDao")
	private IReceivableDebtOuterDao receivabledebtOuterDao;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	
	@Override
	public ReportReceivabledebtOuter getReceivabledebtbyID(Integer id) {
		ReportReceivabledebtOuter entity = receivabledebtOuterDao.getReceivabledebtbyID(id);
		return entity;
	}

	

	@Override
	public MsgPage getReceivabledebtListView(ReportReceivabledebtOuter entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = receivabledebtOuterDao.getReceivabledebtListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportReceivabledebtOuter> list = receivabledebtOuterDao.getReceivabledebtList(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}


	@Override
	public CommitResult saveReceivabledebt(ReportReceivabledebtOuter entity,
			HhUsers use, MultipartFile itemfile) throws FileNotFoundException,
			IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
		
		HhOrganInfoTreeRelation a= systemService.getTreeOrganInfoNode(Base.financetype, entity.getOrg());
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
	private CommitResult saveReceivabledebtCheck(ReportReceivabledebtOuter entity)
	{
		CommitResult result=new CommitResult();
		if(!receivabledebtOuterDao.saveReceivabledebtRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该期已经有数据，不能再添加");
			return result;
		}
		if(!receivabledebtOuterDao.checkCanEdit(entity))
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
	private CommitResult saveReceivabledebtinfo(MultipartFile itemfile,ReportReceivabledebtOuter entity) throws FileNotFoundException, IOException
	{
		CommitResult result=new CommitResult();
		ExcelCellValueValidate excelValidate = new ExcelCellValueValidate();
		receivabledebtOuterDao.deleteReceivabledebtinfo(entity.getId().toString());
		
		List<HhBase> debtTypeList = baseService.getBases(53400);//欠款类别
		
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
        HhOrganInfoTreeRelation nodeBusiness = systemDao.getTreeOrganInfoNodeBusiness(Base.financetype, entity.getOrg());
        String coreOrg=nodeBusiness.getId().getNnodeId();
        String coreOrgNm =nodeBusiness.getVcFullName();
        entity.setCoreorg(coreOrg);
        entity.setCoreorgname(coreOrgNm);
        for (int k = 3; k < sheet.getPhysicalNumberOfRows(); k++) {
        	int i=0;//列索引
        	//行
        	Row row = sheet.getRow(k);
        	//欠款单位
        	String debtOrgname=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(debtOrgname)){
        		ReportReceivabledebtinfoOuter item=new ReportReceivabledebtinfoOuter();
        		//Cell cell= row.getCell(0);
        		item.setIsdel(0);
        		item.setGroupid(entity.getId());
        		//业态ID 名称
        		item.setCoreorg(entity.getCoreorg());
        		item.setCoreorgname(entity.getCoreorgname());
        		item.setOrg(entity.getOrg());
        		item.setOrgname(entity.getOrgname());
        		item.setYear(entity.getYear());
        		item.setWeek(entity.getWeek());
        		item.setParentorg(entity.getParentorg());
        		//欠款单位
        		ExcelCellValueValidateResult v1 = excelValidate.validate(debtOrgname, "NotEmpty", "varChar", 255);
        		if(v1.isResult()){
        			item.setDebtOrgname(debtOrgname);  //欠款单位
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//欠款类型
        		String debtType = exceltool.getCellValue(workbook, row.getCell(i));i++;
	        	if(Common.notEmpty(debtType))
	        	{
	        		for (HhBase obj : debtTypeList) {
	        			if(obj.getDescription().equals(debtType.trim()))
	        			{
	        				item.setDebtType(obj.getId());
	        				break;
	        			}
	        		}
	        	}
	        	
	        	//具体事由
	        	String jtsy = exceltool.getCellValue(workbook, row.getCell(i));i++;
	        	v1 = excelValidate.validate(jtsy, "Empty", "varChar", 1000);
        		if(v1.isResult()){
        			item.setJtsy(jtsy);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//欠款金额
        		String loanMoney = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		v1 = excelValidate.validate(loanMoney, "NotEmpty", "double", 255);
        		if(v1.isResult()){
        			item.setLoanMoney(loanMoney);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//其中超期欠款金额
        		String cqLoanMoney = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		v1 = excelValidate.validate(cqLoanMoney, "NotEmpty", "double", 255);
        		if(v1.isResult()){
        			item.setCqLoanMoney(cqLoanMoney);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//欠款时间
        		String loanTime = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		v1 = excelValidate.validate(loanTime, "NotEmpty", "varChar", 255);
        		if(v1.isResult()){
        			item.setLoanTime(loanTime);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//是否超期
        		String isOvertime =  exceltool.getCellValue(workbook, row.getCell(i));i++;
        		if(Common.notEmpty(isOvertime)){
        			if("是".equals(isOvertime)){
        				item.setIsOvertime(1);
        			}
        			if("否".equals(isOvertime)){
        				item.setIsOvertime(0);
        			}
        		}        		
        		//已超期时限（月）
        		String overtimeMonth = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		v1 = excelValidate.validate(overtimeMonth, "NotEmpty", "int", 255);
        		if(v1.isResult()){
        			item.setOvertimeMonth(overtimeMonth);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//是否催收
        		String isCuishou =  exceltool.getCellValue(workbook, row.getCell(i));i++;
        		if(Common.notEmpty(isCuishou)){
        			if("是".equals(isCuishou)){
        				item.setIsCuishou(1);
        			}
        			if("否".equals(isCuishou)){
        				item.setIsCuishou(0);
        			}
        		}
        		
        		//未催收原因
        		String noCuishouReason = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		if(item.getIsOvertime() == 1 && item.getIsCuishou() == 0){
        			v1 = excelValidate.validate(noCuishouReason, "NotEmpty", "varChar", 1000);
        		}else{
        			v1 = excelValidate.validate(noCuishouReason, "Empty", "varChar", 1000);
        		}
        		
        		if(v1.isResult()){
        			item.setNoCuishoureason(noCuishouReason);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//回款计划
        		String hkjh = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		if(item.getIsOvertime() == 1 && item.getIsCuishou() == 0){
        			v1 = excelValidate.validate(hkjh, "NotEmpty", "varChar", 1000);
        		}else{
        			v1 = excelValidate.validate(hkjh, "Empty", "varChar", 1000);
        		}
        		if(v1.isResult()){
        			item.setHkjh(hkjh);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//是否进入法律程序
        		String isInlaw = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		if(Common.notEmpty(isInlaw)){
        			if("是".equals(isInlaw)){
        				item.setIsInlaw(1);
        			}
        			if("否".equals(isInlaw)){
        				item.setIsInlaw(0);
        			}
        		}
        		
        		//本周回收金额
        		String weekBackMoney = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		v1 = excelValidate.validate(weekBackMoney, "NotEmpty", "double", 255);
        		if(v1.isResult()){
        			item.setWeekBackMoney(weekBackMoney);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//累计收回金额
        		String totalBackMoney = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		v1 = excelValidate.validate(totalBackMoney, "NotEmpty", "double", 255);
        		if(v1.isResult()){
        			item.setTotalBackMoney(totalBackMoney);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//预计坏账
        		String yjhz = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		v1 = excelValidate.validate(yjhz, "Empty", "double", 255);
        		if(v1.isResult()){
        			item.setYjhz(yjhz);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//催收责任人
        		String cuishouPerson = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		v1 = excelValidate.validate(cuishouPerson, "Empty", "varChar", 255);
        		if(v1.isResult()){
        			item.setCuishouPerson(cuishouPerson);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//进展描述
        		String jzDescription = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		v1 = excelValidate.validate(jzDescription, "Empty", "varChar", 1000);
        		if(v1.isResult()){
        			item.setJzDescription(jzDescription);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		
        		//备注
        		String remark = exceltool.getCellValue(workbook, row.getCell(i));i++;
        		v1 = excelValidate.validate(remark, "Empty", "varChar", 1000);
        		if(v1.isResult()){
        			item.setRemark(remark);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
        		commonDao.saveOrUpdate(item);
        	}
        }
        result.setResult(true);
        return result;
	}


	@Override
	public CommitResult deleteReceivabledebt(Integer id, HhUsers use) {
		ReportReceivabledebtOuter entity=receivabledebtOuterDao.getReceivabledebtbyID(id);
		CommitResult result=new CommitResult();
		if(!receivabledebtOuterDao.checkCanEdit(entity))
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
			receivabledebtOuterDao.deleteReceivabledebtinfo(entity.getId().toString());
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
		ReportReceivabledebtOuter entity=getReceivabledebtbyID(entityid);
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

		examineService.saveExamine( examineentityid, Base.examkind_receivabledebtout, use, examStr, examResult);
		
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
		ReportReceivabledebtOuter entity=receivabledebtOuterDao.getReceivabledebtbyID(entityID);
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
	/*	if(!entity.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}*/
		result.setResult(true);
		return result;
	}


	@Override
	public MsgPage getReceivabledebtInfoListView(ReportReceivabledebtOuter entity, Integer curPageNum,Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = receivabledebtOuterDao.getReceivabledebtinfoCount(entity.getId());
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportReceivabledebtinfoOuter> list = receivabledebtOuterDao.getReceivabledebtinfoList(entity.getId(), offset, pageSize);
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
	public MsgPage getReceivabledebtinfoListView(ReportReceivabledebtinfoOuter entity, Integer curPageNum,Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = receivabledebtOuterDao.getReceivabledebtinfoDetailCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportReceivabledebtinfoOuter> list = receivabledebtOuterDao.getReceivabledebtinfoDetailList(entity,offset,pageSize);
     	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	

	@Override
	public ReportReceivabledebtinfoOuter getReceivabledebtinfobyID(Integer id) {
		ReportReceivabledebtinfoOuter entity = receivabledebtOuterDao.getReceivabledebtinfobyID(id);
		return entity;
	}



	
	/**
	 *   --------------------------------应收债权(外部)汇总查询---------------------------------------
	 */
	
	@Override
	public MsgPage getReceivabledebtinfoCollectList(ReportReceivabledebtinfoOuter entity,Integer curPageNum,Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = receivabledebtOuterDao.getReceivabledebtinfoCollectCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object> list = receivabledebtOuterDao.getReceivabledebtinfoCollectList(entity,offset,pageSize);
    	List<ReportReceivabledebtinfoOuter> returnList = new ArrayList<ReportReceivabledebtinfoOuter>();
    	if (list != null && list.size()>0) {
			for (Object object : list) {
				Object[] obj = (Object[])object;
				ReportReceivabledebtinfoOuter info = new ReportReceivabledebtinfoOuter();
				info.setGroupid(Integer.valueOf(obj[0].toString()));
				info.setYear(obj[1] != null?Integer.valueOf(obj[1].toString()):null);
				info.setWeek(obj[2] != null?Integer.valueOf(obj[2].toString()):null);
				info.setCoreorgname(obj[3] != null?obj[3].toString():"");
				info.setOrgname(obj[4] != null?obj[4].toString():"");
				info.setLoanMoney(obj[5] != null?obj[5].toString():"");
				info.setCqLoanMoney(obj[6] != null?obj[6].toString():"");
				info.setHkjh(obj[7] != null?obj[7].toString():"");
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
	 *   --------------------------------公司大额应收债权(外部)查询---------------------------------------
	 */

	@Override
	public List<ReportReceivabledebtinfoOuter> getReceivabledebtinfoOrgList(ReportReceivabledebtinfoOuter entity, Integer curPageNum,Integer pageSize) {
		
    	List<Object> list = receivabledebtOuterDao.getReceivabledebtinfoOrgList(entity,null,pageSize);
    	List<ReportReceivabledebtinfoOuter> returnList = new ArrayList<ReportReceivabledebtinfoOuter>();
    	if (list != null && list.size()>0) {
			for (Object object : list) {
				Object[] obj = (Object[])object;
				ReportReceivabledebtinfoOuter info = new ReportReceivabledebtinfoOuter();
				info.setId(Integer.valueOf(obj[0].toString()));
				info.setYear(obj[1] != null?Integer.valueOf(obj[1].toString()):null);
				info.setWeek(obj[2] != null?Integer.valueOf(obj[2].toString()):null);
				info.setCoreorgname(obj[3] != null?obj[3].toString():"");
				info.setOrgname(obj[4] != null?obj[4].toString():"");
				info.setDebtOrgname(obj[5] != null?obj[5].toString():"");
				info.setLoanMoney(obj[6] != null?obj[6].toString():"");
				info.setCqLoanMoney(obj[7] != null?obj[7].toString():"");
				returnList.add(info);
			}
		}
    	if(list.size() > 5){
    		return returnList.subList(0, 5);
    	}else{
    		return returnList;
    	}
    	
	}



	
	

	/**
	 *   --------------------------------超期外部应收账款无催收进展一览---------------------------------------
	 */
	@Override
	public MsgPage getOverOutList(ReportReceivabledebtinfoOuter entity,Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = receivabledebtOuterDao.getOverOutCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportReceivabledebtinfoOuter> list = receivabledebtOuterDao.getOverOutList(entity,offset,pageSize);
    	
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	/**
	 * 导出查询页面数据
	 * @param list
	 * @return
	 */
	@Override
	public void getExcelData(List<ReportReceivabledebtinfoOuter> list,HttpServletResponse response){	
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = null;
		HSSFRow row = null;
		//导出文件名称
		String fileName = "应收债权(外部)数据填报";
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		
		sheet = wb.createSheet("应收债权(外部)数据填报");
		//设置宽度
		//sheet.setColumnWidth((short) 5, (short) 9000);
		//sheet.setColumnWidth((short) 7, (short) 6000);
		sheet.setColumnWidth((short) 9, (short) 6000);
		sheet.setColumnWidth((short) 10, (short) 9000);
		sheet.setColumnWidth((short) 16, (short) 9000);
		sheet.setColumnWidth((short) 17, (short) 9000);
		
		//参数说明：1：开始行 2：结束行  3：开始列 4：结束列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,17)); 
		/*sheet.addMergedRegion(new CellRangeAddress(1,1,10,12)); 
		sheet.addMergedRegion(new CellRangeAddress(1,1,15,20)); */
		// 3.在sheet中添加表头第0行
		row = sheet.createRow(0);
		
		//title1
		Cell cell = row.createCell(0);
		cell.setCellStyle(styleList.get(0));
		cell.setCellValue("海航集团外部债权统计表(万元)");
		
		//title2
		row = sheet.createRow(1);
		for(int n=0;n<=25;n++){
			sheet.addMergedRegion(new CellRangeAddress(1,2,n,n));
		}
		
		String [] titleArray = {"序号","欠款单位","欠款类型","具体事由","欠款金额","其中超期欠款金额","欠款时间","是否超期","已超期时限（月）","是否催收","未催收原因",
																		"回款计划","是否进入法律程序","本周收回金额 ","累计收回金额","预计坏账","催收责任人","进展描述","备注"};
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);	
		row = sheet.createRow(2);
		//row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
		
		Integer rowNum = 3;
		//导出coreOrg自身数据-查询并写入数据
		sheet = this.getEntityForOrg(list,sheet,styleList.get(1),rowNum);
		//下载
		try {
			tool.outputExcel(wb,fileName,response);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	public HSSFSheet getEntityForOrg(List<ReportReceivabledebtinfoOuter> list, HSSFSheet sheet, HSSFCellStyle style, Integer rowNum) {
		sheet = this.setExcelData(sheet,list,style,rowNum);
		return sheet;
	}
	

	public HSSFSheet setExcelData(HSSFSheet sheet,List<ReportReceivabledebtinfoOuter> listEntity, HSSFCellStyle style,Integer rowNum){
		HSSFRow row = null;
		for(int i = 0;i<listEntity.size();i++){
			row = sheet.createRow(rowNum+i+1);
			this.setCell(listEntity.get(i), row, style ,(i+1));
		}
		return sheet;
	}
	
	/**
	 * @param entity 实体
	 * @param row	行
	 * @param cell	列
	 * @param style	样式
	 * @param num	序号
	 */
	public Cell setCell(ReportReceivabledebtinfoOuter entity,HSSFRow row,HSSFCellStyle style,Integer num){
		Cell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(num);//序号
		
		cell=row.createCell(1);cell.setCellStyle(style);cell.setCellValue(entity.getDebtOrgname());  //debtOrgname
		cell=row.createCell(2);cell.setCellStyle(style);cell.setCellValue(entity.getDebtType());	 //debtType
		cell=row.createCell(3);cell.setCellStyle(style);cell.setCellValue(entity.getJtsy());		 //jtsy
		cell=row.createCell(4);cell.setCellStyle(style);cell.setCellValue(entity.getLoanMoney());//loanMoney
		cell=row.createCell(5);cell.setCellStyle(style);cell.setCellValue(entity.getCqLoanMoney());//cqLoanMoney
		cell=row.createCell(6);cell.setCellStyle(style);cell.setCellValue(entity.getLoanTime());//loanTime
		cell=row.createCell(7);cell.setCellStyle(style);cell.setCellValue(entity.getIsOvertime());//isOvertime
		cell=row.createCell(8);cell.setCellStyle(style);cell.setCellValue(entity.getOvertimeMonth());//overtimeMonth
		cell=row.createCell(9);cell.setCellStyle(style);cell.setCellValue(entity.getIsCuishou());//isCuishou
		cell=row.createCell(10);cell.setCellStyle(style);cell.setCellValue(entity.getNoCuishoureason());//noCuishoureason
		cell=row.createCell(11);cell.setCellStyle(style);cell.setCellValue(entity.getHkjh());//hkjh
		cell=row.createCell(12);cell.setCellStyle(style);cell.setCellValue(entity.getIsInlaw());//isInlaw
		cell=row.createCell(13);cell.setCellStyle(style);cell.setCellValue(entity.getWeekBackMoney());//weekBackMoney
		cell=row.createCell(14);cell.setCellStyle(style);cell.setCellValue(entity.getTotalBackMoney());//totalBackMoney
		cell=row.createCell(15);cell.setCellStyle(style);cell.setCellValue(entity.getYjhz());//yjhz
		cell=row.createCell(16);cell.setCellStyle(style);cell.setCellValue(entity.getCuishouPerson());//cuishouPerson
		cell=row.createCell(17);cell.setCellStyle(style);cell.setCellValue(entity.getJzDescription());//jzDescription
		cell=row.createCell(18);cell.setCellStyle(style);cell.setCellValue(entity.getRemark());//remark
	
		return cell;
	}



	@Override
	public List<ReportReceivabledebtinfoOuter> getReportReceivabledebtOuter(ReportReceivabledebtinfoOuter entity) {
		//设置组织结构的父类编号
		HhOrganInfoTreeRelation hhOrganInfoTreeRelation =systemDao.getTopTreeOrganInfoNodeStr(Base.financetype,entity.getOrg());
		if(null != hhOrganInfoTreeRelation &&
				null!=hhOrganInfoTreeRelation.getVcOrganId() &&
				!"".equals(hhOrganInfoTreeRelation.getVcOrganId())){		
			//设置组织结构的父类编号
			entity.setParentorg(hhOrganInfoTreeRelation.getVcOrganId());
		}
		List<ReportReceivabledebtinfoOuter> list = null;
		list = receivabledebtOuterDao.getReportReceivabledebtOuter(entity);
		return list;
	}



	@Override
	public List<ReportReceivabledebtinfoOuter> getReceivabledebtinfoCollectListExport(ReportReceivabledebtinfoOuter entity) {
		//设置组织结构的父类编号
		HhOrganInfoTreeRelation hhOrganInfoTreeRelation =systemDao.getTopTreeOrganInfoNodeStr(Base.financetype,entity.getOrg());
		if(null != hhOrganInfoTreeRelation &&
				null!=hhOrganInfoTreeRelation.getVcOrganId() &&
				!"".equals(hhOrganInfoTreeRelation.getVcOrganId())){		
			//设置组织结构的父类编号
			entity.setParentorg(hhOrganInfoTreeRelation.getVcOrganId());
		}
		List<ReportReceivabledebtinfoOuter> list = null;
		list = receivabledebtOuterDao.getReceivabledebtinfoCollectListExport(entity);
		List<ReportReceivabledebtinfoOuter> returnList = new ArrayList<ReportReceivabledebtinfoOuter>();
    	if (list != null && list.size()>0) {
			for (Object object : list) {
				Object[] obj = (Object[])object;
				ReportReceivabledebtinfoOuter info = new ReportReceivabledebtinfoOuter();
				info.setGroupid(Integer.valueOf(obj[0].toString()));
				info.setYear(obj[1] != null?Integer.valueOf(obj[1].toString()):null);
				info.setWeek(obj[2] != null?Integer.valueOf(obj[2].toString()):null);
				info.setCoreorgname(obj[3] != null?obj[3].toString():"");
				info.setOrgname(obj[4] != null?obj[4].toString():"");
				info.setLoanMoney(obj[5] != null?obj[5].toString():"");
				info.setCqLoanMoney(obj[6] != null?obj[6].toString():"");
				info.setHkjh(obj[7] != null?obj[7].toString():"");
				returnList.add(info);
			}
		}
		return returnList;
	}



	@Override
	public List<ReportReceivabledebtinfoOuter> getReceivabledebtinfoOrgExport(ReportReceivabledebtinfoOuter entity) {
		//设置组织结构的父类编号
		HhOrganInfoTreeRelation hhOrganInfoTreeRelation =systemDao.getTopTreeOrganInfoNodeStr(Base.financetype,entity.getOrg());
		if(null != hhOrganInfoTreeRelation &&
				null!=hhOrganInfoTreeRelation.getVcOrganId() &&
				!"".equals(hhOrganInfoTreeRelation.getVcOrganId())){		
			//设置组织结构的父类编号
			entity.setParentorg(hhOrganInfoTreeRelation.getVcOrganId());
		}
		List<ReportReceivabledebtinfoOuter> list = null;
		list = receivabledebtOuterDao.getReceivabledebtinfoOrgExport(entity);
		List<ReportReceivabledebtinfoOuter> returnList = new ArrayList<ReportReceivabledebtinfoOuter>();
		if (list != null && list.size()>0) {
			for (Object object : list) {
				Object[] obj = (Object[])object;
				ReportReceivabledebtinfoOuter info = new ReportReceivabledebtinfoOuter();
				info.setId(Integer.valueOf(obj[0].toString()));
				info.setYear(obj[1] != null?Integer.valueOf(obj[1].toString()):null);
				info.setWeek(obj[2] != null?Integer.valueOf(obj[2].toString()):null);
				info.setCoreorgname(obj[3] != null?obj[3].toString():"");
				info.setOrgname(obj[4] != null?obj[4].toString():"");
				info.setDebtOrgname(obj[5] != null?obj[5].toString():"");
				info.setLoanMoney(obj[6] != null?obj[6].toString():"");
				info.setCqLoanMoney(obj[7] != null?obj[7].toString():"");
				returnList.add(info);
			}
		}
		return returnList;
	}



	@Override
	public List<ReportReceivabledebtinfoOuter> getOverOutListExport(ReportReceivabledebtinfoOuter entity) {
		//设置组织结构的父类编号
		HhOrganInfoTreeRelation hhOrganInfoTreeRelation =systemDao.getTopTreeOrganInfoNodeStr(Base.financetype,entity.getOrg());
		if(null != hhOrganInfoTreeRelation &&
				null!=hhOrganInfoTreeRelation.getVcOrganId() &&
				!"".equals(hhOrganInfoTreeRelation.getVcOrganId())){		
			//设置组织结构的父类编号
			entity.setParentorg(hhOrganInfoTreeRelation.getVcOrganId());
		}
		List<ReportReceivabledebtinfoOuter> list = null;
		list = receivabledebtOuterDao.getOverOutListExport(entity);
		
		return list;
	}
	
	
}
