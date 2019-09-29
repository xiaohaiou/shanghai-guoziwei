package com.softline.service.report.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.softline.dao.report.IReportPersonalLoanNewDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportPersonalloan;
import com.softline.entity.ReportPersonalloanNearMonth;
import com.softline.entity.ReportPersonalloanNearweek;
import com.softline.entity.ReportPersonalloanNearweekDetail;
import com.softline.entity.ReportPersonalloanNew;
import com.softline.entity.ReportPersonlloaninfo;
import com.softline.entity.ReportPersonlloaninfoNew;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportPersonalLoanNewService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.DateUtils;
import com.softline.util.ExcelCellValueValidate;
import com.softline.util.ExcelCellValueValidateResult;
import com.softline.util.MsgPage;

@Service("reportPersonalLoanNewService")
/**
 * 
 * @author sht
 *
 */
public class ReportPersonalLoanNewService implements IReportPersonalLoanNewService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	@Autowired
	@Qualifier("reportPersonalLoanNewDao")
	private IReportPersonalLoanNewDao reportPersonalLoanNewDao;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	
	@Override
	public ReportPersonalloanNew getPersonalLoanNewbyID(Integer id) {
		ReportPersonalloanNew entity = reportPersonalLoanNewDao.getPersonalLoanNewbyID(id);
		return entity;
	}


	@Override
	public MsgPage getPersonalloanNewListView(ReportPersonalloanNew entity,
			Integer curPageNum, Integer pageSize,String weekStart,String weekEnd) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportPersonalLoanNewDao.getPersonalloanNewListCount(entity,weekStart,weekEnd);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportPersonalloanNew> list = reportPersonalLoanNewDao.getPersonalloanNewList(entity, offset, pageSize,weekStart,weekEnd);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}


	@Override
	public CommitResult savePersonalloanNew(ReportPersonalloanNew entity,
			HhUsers use, MultipartFile itemfile) throws FileNotFoundException,
			IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=savePersonalloanNewCheck(entity);
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
				result=savePersonalloaninfoNew( itemfile, entity);
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
	private CommitResult savePersonalloanNewCheck(ReportPersonalloanNew entity)
	{
		CommitResult result=new CommitResult();
		if(entity == null && !reportPersonalLoanNewDao.savePersonalloanNewRepeatCheck(entity))//新增
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该期已经有数据，不能再添加");
			return result;
		}
		if(entity != null && !reportPersonalLoanNewDao.checkCanEdit(entity))//修改
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
	private CommitResult savePersonalloaninfoNew(MultipartFile itemfile,ReportPersonalloanNew entity) throws FileNotFoundException, IOException
	{
		ExcelCellValueValidate excelValidate = new ExcelCellValueValidate();
		CommitResult result=new CommitResult();
		reportPersonalLoanNewDao.deletePersonalloaninfoNew(entity.getId().toString());
		ExcelTool exceltool=new ExcelTool();
        //先暂存附件
        List<String> fileStrList = Common.saveFile(itemfile, DES.loanfinance_path);
        //文件路径
        String xlsPath=fileStrList.get(1);
        InputStream is = new FileInputStream(xlsPath);
       List<HhBase> loanType1List = baseService.getBases(53200);//欠款类别
       List<HhBase> loanType2List = baseService.getBases(53300);//欠款类型
        Workbook workbook = null; 
        try { 
        	workbook = new XSSFWorkbook(is); 
        } catch (Exception ex) { 
        	POIFSFileSystem poifsFileSystem= new POIFSFileSystem(is);
        	workbook = new HSSFWorkbook(poifsFileSystem); 
        } 
        Sheet  sheet = workbook.getSheetAt(0);
        Set<String> set = new HashSet<String>();
        HhOrganInfoTreeRelation nodeBusiness = systemDao.getTreeOrganInfoNodeBusiness(Base.financetype, entity.getOrg());
        String coreOrg=nodeBusiness.getId().getNnodeId();
        String coreOrgNm =nodeBusiness.getVcFullName();
        entity.setCoreorg(coreOrg);
        entity.setCoreorgname(coreOrgNm);
        for (int k = 3; k < sheet.getPhysicalNumberOfRows(); k++) {
        	int i=0;//列索引
        	//行
        	Row row = sheet.getRow(k);
        	if(row != null){
        	String responsibleperson=exceltool.getCellValue(workbook, row.getCell(2));//欠款人
        	String loannum=exceltool.getCellValue(workbook, row.getCell(16));
        	if(Common.notEmpty(responsibleperson) && Common.notEmpty(loannum)){
	        	ReportPersonlloaninfoNew item=new ReportPersonlloaninfoNew();
	        	//Cell cell= row.getCell(0);
	        	item.setIsdel(0);
	        	item.setGroupid(entity.getId());
	        	item.setOrg(entity.getOrg());
	        	item.setOrgname(entity.getOrgname());
	        	//业态ID 名称
	        	item.setCoreorg(entity.getCoreorg());
	        	item.setCoreorgname(entity.getCoreorgname());
	        	item.setYear(entity.getYear());
	        	item.setWeek(entity.getWeek());
	        	item.setParentorg(entity.getParentorg());
	        	
	        	String respersonAccount = exceltool.getCellValue(workbook, row.getCell(i)); i++;
	        	ExcelCellValueValidateResult v1 = excelValidate.validate(respersonAccount, "NotEmpty", "varChar", 255);
	        	if(v1.isResult()){
	        		item.setRespersonAccount(respersonAccount);//欠款人唯一标识
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	responsibleperson = exceltool.getCellValue(workbook, row.getCell(i)); i++;
	        	v1 = excelValidate.validate(responsibleperson, "NotEmpty", "varChar", 255);
	        	if(v1.isResult()){
	        		item.setResponsibleperson(responsibleperson);//欠款人
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	String loanType1 = exceltool.getCellValue(workbook, row.getCell(i));i++; //欠款类别
	        	if(Common.notEmpty(loanType1))
	        	{
	        		for (HhBase obj : loanType1List) {
	        			if(obj.getDescription().equals(loanType1.trim()))
	        			{
	        				item.setLoanType1(obj.getId());
	        				break;
	        			}
	        		}
	        	}
	        	
	        	
	        	String loanType2 = exceltool.getCellValue(workbook, row.getCell(i));i++;//欠款类型
	        	if(Common.notEmpty(loanType2))
	        	{
	        		for (HhBase obj : loanType2List) {
	        			if(obj.getDescription().equals(loanType2.trim()))
	        			{
	        				item.setLoanType2(obj.getId());
	        				break;
	        			}
	        		}
	        	}
	        	
	        	String useway=exceltool.getCellValue(workbook, row.getCell(i));i++; //具体事由
	        	v1 = excelValidate.validate(useway, "Empty", "varChar", 1000);
	        	if(v1.isResult()){
	        		item.setUseway(useway);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	
	        	String loanmoney=exceltool.getCellValue(workbook, row.getCell(i));i++;//借款金额
	        	v1 = excelValidate.validate(loanmoney, "NotEmpty", "double", 30);
	        	if(v1.isResult()){
	        		item.setLoanmoney(loanmoney);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	
	        	String loantime=exceltool.getCellValue(workbook, row.getCell(i));i++; //借款时间
	        	v1 = excelValidate.validate(loantime, "Empty", "varChar", 255);
	        	if(v1.isResult()){
	        		item.setLoantime(loantime);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	
	        	String loanMonth=exceltool.getCellValue(workbook, row.getCell(i));i++; //已借款期限(月)
	        	v1 = excelValidate.validate(loanMonth, "NotEmpty", "int", 11);
	        	if(v1.isResult()){
	        		item.setLoanmonth(loanMonth);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	
	        	String isCuishou=exceltool.getCellValue(workbook, row.getCell(i));i++; //是否催收（1 是 0 否）
	        	if(Common.notEmpty(isCuishou))
	        	{
	        		if("是".equals(isCuishou)){
	        			item.setIsCuishou(1);
	        		}else if("否".equals(isCuishou)){
	        			item.setIsCuishou(0);
	        		}
	        	}
	        	String isInLaw=exceltool.getCellValue(workbook, row.getCell(i));i++; //是否进入法律程序
	        	if(Common.notEmpty(isInLaw))
	        	{
	        		if("是".equals(isInLaw)){
	        			item.setIsInLaw(1);
	        		}else if("否".equals(isInLaw)){
	        			item.setIsInLaw(0);
	        		}
	        	}
	        	
	        	String weekBackMoney=exceltool.getCellValue(workbook, row.getCell(i));i++; //本周回收金额
	        	v1 = excelValidate.validate(weekBackMoney, "NotEmpty", "double", 50);
	        	if(v1.isResult()){
	        		item.setWeekBackMoney(weekBackMoney);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	
	        	String totalBackMoney=exceltool.getCellValue(workbook, row.getCell(i));i++; //累计回收金额
	        	v1 = excelValidate.validate(totalBackMoney, "NotEmpty", "double", 50);
	        	if(v1.isResult()){
	        		item.setTotalBackMoney(totalBackMoney);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	
	        	String yjhz=exceltool.getCellValue(workbook, row.getCell(i));i++; //预计坏账
	        	v1 = excelValidate.validate(yjhz, "Empty", "varChar", 50);
	        	if(v1.isResult()){
	        		item.setYjhz(yjhz);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	
	        	String cszrr=exceltool.getCellValue(workbook, row.getCell(i));i++; //催收责任人
	        	v1 = excelValidate.validate(cszrr, "Empty", "varChar", 50);
	        	if(v1.isResult()){
	        		item.setCszrr(cszrr);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	
	        	String zjms=exceltool.getCellValue(workbook, row.getCell(i));i++; //进展描述
	        	v1 = excelValidate.validate(zjms, "Empty", "varChar", 1000);
	        	if(v1.isResult()){
	        		item.setZjms(zjms);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	
	        	String remark=exceltool.getCellValue(workbook, row.getCell(i));i++;//备注
	        	v1 = excelValidate.validate(remark, "Empty", "varChar", 1000);
	        	if(v1.isResult()){
	        		item.setRemark(remark);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	
	        	
	        	loannum=exceltool.getCellValue(workbook, row.getCell(i));i++;
	        	v1 = excelValidate.validate(loannum, "NotEmpty", "varChar", 255);
	        	if(v1.isResult()){
	        		item.setLoannum(loannum);
	        	}else{
	        		result=CommitResult.createErrorResult("第" + (k+1) + "行,第"+i+"列" + v1.getDescription());
					return result;
	        	}
	        	commonDao.saveOrUpdate(item);
	        }
        	}
        }
      
        commonDao.saveOrUpdate(entity);
        result.setResult(true);
        return result;
	}

	@Override
	public CommitResult deletePersonalloanNew(Integer id, HhUsers use) {
		ReportPersonalloanNew entity=reportPersonalLoanNewDao.getPersonalLoanNewbyID(id);
		CommitResult result=new CommitResult();
		if(!reportPersonalLoanNewDao.checkCanEdit(entity))
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
			reportPersonalLoanNewDao.deletePersonalloaninfoNew(entity.getId().toString());
			commonDao.saveOrUpdate(entity);
			reportPersonalLoanNewDao.deleteNearweekAndDetail(entity.getId());
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}


	@Override
	public CommitResult savePersonalloanNewExamine(Integer entityid,
			String examStr, Integer examResult, HhUsers use) {
		CommitResult result=new CommitResult();
		ReportPersonalloanNew entity=reportPersonalLoanNewDao.getPersonalLoanNewbyID(entityid);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		result=savePersonalloanExamineCheck(entityid);
		if(!result.isResult())
		{
			return result;
		}
		//审核不通过
		if(examResult.equals(Base.examresult_1))
			entity.setStatus(Base.examstatus_3);
		//审核通过
		else if(examResult.equals(Base.examresult_2)){
			entity.setStatus(Base.examstatus_4);
			//审核通过，保存统计数据到表report_personloan_nearweek以及report_personloan_nearweek_detail
			saveNearweekAndDetail(entity);
		}
		String a=df.format(new Date());
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		//保存HrPersongroup
		commonDao.saveOrUpdate(entity);
		
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_personalloan_1, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}
	
	/**
	 * 从表report_personloaninfo中分析出数据保存到表report_personloan_nearweek以及report_personloan_nearweek_detail中
	 * @param entity report_personloan的ID
	 */
	private void saveNearweekAndDetail(ReportPersonalloanNew entity){
		//处理当周的数据
		dealNearWeekAndDetail(entity);
		
		//获取下周数据情况 处理下周的数据
		Integer nweek = entity.getWeek()+1;
		Integer nyear = entity.getYear();
		if(nweek >  DateUtils.getMaxWeekNumOfYear(nyear)){
			nyear = nyear + 1;
			nweek = 1;
		}
		ReportPersonalloanNew nextWeekLoan  = reportPersonalLoanNewDao.getReportPersonalloanNew(nyear, nweek, entity.getOrg());
		if(nextWeekLoan != null){//更新下周的report_personloan_nearweek以及report_personloan_nearweek_detail
			dealNearWeekAndDetail(nextWeekLoan);
		}
		
	}
	
	/**
	 * 处理汇总数据
	 * 
	 * @param entity
	 * 
	 */
	private void dealNearWeekAndDetail(ReportPersonalloanNew entity) {
		// 获取上周的数据
		Integer pweek = entity.getWeek() - 1;
		Integer pyear = entity.getYear();
		if (pweek == 0) {
			pyear = pyear - 1;
			pweek = DateUtils.getMaxWeekNumOfYear(pyear);
		}
		// 删除之前创建的数据
		reportPersonalLoanNewDao.deleteNearweekAndDetail(entity.getId());

		// 本周情况
		Double jkze = 0.0; // 借款总额
		Double cqje = 0.0; // 超期金额
		Set<String> nameSet1 = new HashSet<String>();// 借款人数(人)
		Set<String> nameSet2 = new HashSet<String>();// 借款超期人数(人)
		int bzhwkrs = 0;// 本周还完款人数
		int bzxzjkrs = 0; // 本周新增借款人数
		Map<String, ReportPersonalloanNearweekDetail> nearweekDetailMap = new HashMap<String, ReportPersonalloanNearweekDetail>();
		for (ReportPersonlloaninfoNew curInfo : entity.getInfolist()) {
			Double tempjkze = new BigDecimal(curInfo.getLoanmoney())
					.doubleValue();
			Double tempcqje = new BigDecimal(curInfo.getTotalBackMoney())
					.doubleValue();
			jkze = jkze + tempjkze;
			if (Integer.parseInt(curInfo.getLoanmonth()) > 1) { // 已借款期限大于1个月为超期
				cqje = cqje + (tempjkze - tempcqje);
				nameSet2.add(curInfo.getRespersonAccount());
			}
			ReportPersonalloanNearweekDetail nearweekDetail = new ReportPersonalloanNearweekDetail();
			if (!nameSet1.add(curInfo.getRespersonAccount())) {
				nearweekDetail = nearweekDetailMap.get(curInfo
						.getRespersonAccount());
				nearweekDetail.setPersonTotal((new BigDecimal(nearweekDetail
						.getPersonTotal()).doubleValue() + tempjkze) + "");
				nearweekDetail
						.setMonthreturnmoney((new BigDecimal(nearweekDetail
								.getMonthreturnmoney()).doubleValue() + new BigDecimal(
								curInfo.getWeekBackMoney()).doubleValue())
								+ "");
				nearweekDetail
						.setPersonBackTotal((new BigDecimal(nearweekDetail
								.getPersonBackTotal()).doubleValue() + tempcqje)
								+ "");
				nearweekDetail.setEndingbalance((new BigDecimal(nearweekDetail
						.getPersonTotal()).doubleValue() - new BigDecimal(
						nearweekDetail.getPersonBackTotal()).doubleValue())
						+ "");
			} else {
				nearweekDetail.setCoreorg(entity.getCoreorg());
				nearweekDetail.setCoreorgname(entity.getCoreorgname());
				nearweekDetail.setGroupid(entity.getId());
				nearweekDetail.setOrg(entity.getOrg());
				nearweekDetail.setOrgname(entity.getOrgname());
				nearweekDetail.setYear(entity.getYear());
				nearweekDetail.setWeek(entity.getWeek());
				nearweekDetail.setPersonAccount(curInfo.getRespersonAccount());
				nearweekDetail.setPersonname(curInfo.getResponsibleperson());
				nearweekDetail.setMonthreturnmoney(curInfo.getWeekBackMoney());
				nearweekDetail.setEndingbalance((tempjkze - tempcqje) + "");
				nearweekDetail.setPersonTotal(tempjkze + "");
				nearweekDetail.setPersonBackTotal(tempcqje + "");
				nearweekDetailMap.put(curInfo.getRespersonAccount(),
						nearweekDetail);
			}
		}

		Iterator<ReportPersonalloanNearweekDetail> its1 = nearweekDetailMap
				.values().iterator();
		while (its1.hasNext()) {
			ReportPersonalloanNearweekDetail pentity = its1.next();
			if (pentity.getEndingbalance().equals("0.0")) {
				bzhwkrs = bzhwkrs + 1;
			}
			if (reportPersonalLoanNewDao.isNewAddPersonInCurrentWeek(pyear,
					pweek, entity.getOrg(), pentity.getPersonAccount())) {
				bzxzjkrs = bzxzjkrs + 1;
			}
			String[] aa = reportPersonalLoanNewDao.getPreWeekEndingBalance(
					pyear, pweek, entity.getOrg(), pentity.getPersonAccount());
			pentity.setBeginningbalance(aa[0]);

			pentity.setMonthaddmoney((new BigDecimal(pentity.getPersonTotal())
					.doubleValue() - new BigDecimal(aa[1]).doubleValue()) + "");
			pentity.setIsdel(0);
			pentity.setParentorg(entity.getParentorg());
			commonDao.saveOrUpdate(pentity);
		}
		//汇总数据
		ReportPersonalloanNearweek nearweek = new ReportPersonalloanNearweek();
		nearweek.setGroupid(entity.getId());
		nearweek.setCoreorg(entity.getCoreorg());
		nearweek.setCoreorgname(entity.getCoreorgname());
		nearweek.setOrg(entity.getOrg());
		nearweek.setOrgname(entity.getOrgname());
		nearweek.setYear(entity.getYear());
		nearweek.setWeek(entity.getWeek());
		nearweek.setParentorg(entity.getParentorg());
		nearweek.setIsdel(0);
		nearweek.setLoantotalmoney(jkze + "");
		nearweek.setLoansumperson(nameSet1.size());
		nearweek.setLoansumoverperson(nameSet2.size());
		nearweek.setLoantotalovermoney(cqje + "");
		nearweek.setMonthsumfinishperson(bzhwkrs);
		nearweek.setMonthsumaddperson(bzxzjkrs);
		commonDao.saveOrUpdate(nearweek);
	}
	

	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 */
	public CommitResult savePersonalloanExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportPersonalloanNew entity=reportPersonalLoanNewDao.getPersonalLoanNewbyID(entityID);
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
//		if(!entity.getStatus().equals(Base.examstatus_2))
//		{
//			result=CommitResult.createErrorResult("该信息已不用审核");
//			return result;
//		}
		result.setResult(true);
		return result;
	}
	
	@Override
	public MsgPage getPersonalloanInfoNewListView(ReportPersonalloanNew entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportPersonalLoanNewDao.getPersonalLoaninfoNewCount(entity.getId());
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportPersonlloaninfoNew> list = reportPersonalLoanNewDao.getPersonlloaninfoNewList(entity.getId(), offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
//分页
	@Override
	public MsgPage getPersonalloanNearWeekDetailList(
			ReportPersonalloanNearweek entity, Integer curPageNum,
			Integer pageSize) {
		// 查询公司员工个人借款增减变动情况 总记录数
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = reportPersonalLoanNewDao
				.getPersonalloanNearWeekDetailCount(entity);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		List<ReportPersonalloanNearweekDetail> list = reportPersonalLoanNewDao
				.getPersonalloanNearWeekDetailList(entity, offset, pageSize);
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}
	
	@Override
	public List<ReportPersonalloanNearweekDetail> getPersonalloanNearWeekDetailList(
			ReportPersonalloanNearweek entity) {
		List<ReportPersonalloanNearweekDetail> list = reportPersonalLoanNewDao
				.getPersonalloanNearWeekDetailList(entity, null, null);
		return list;
	}


	@Override
	public List<ReportPersonalloanNearweek> getPersonalloanNearWeekList(
			ReportPersonalloanNearweek entity) {
		return reportPersonalLoanNewDao.getPersonalloanNearWeekList(entity);
	}


	@Override
	public MsgPage getPersonnalovertimeDetail(ReportPersonlloaninfoNew entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();  
		//总记录数
        Integer totalRecords = reportPersonalLoanNewDao.getPersonlloaninfoNewCount(entity);
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	
    	List<ReportPersonlloaninfoNew> list = reportPersonalLoanNewDao.getPersonnalovertimeDetailList(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list); 
		return msgPage;
	}
	
	@Override
	public List<ReportPersonlloaninfoNew> getPersonnalovertimeDetail(ReportPersonlloaninfoNew entity){
		List<ReportPersonlloaninfoNew> list = reportPersonalLoanNewDao.getPersonnalovertimeDetailList(entity, null, null);
		return list;
	}

//个人借款信息汇总查询
	@Override
	public MsgPage getcoreComovertimeDetail(ReportPersonlloaninfoNew entity,
			Integer curPageNum, Integer pageSize,String yearStart,String yearEnd) {
		MsgPage msgPage = new MsgPage();  
		//总记录数
        Integer totalRecords = reportPersonalLoanNewDao.getcoreComloaninfoCount(entity,yearStart,yearEnd);
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	
    	List<ReportPersonalloanNearweek> list = reportPersonalLoanNewDao.getcoreComovertimeDetailList(entity, offset, pageSize,yearStart,yearEnd);
    	
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list); 
		return msgPage;
	}
	
	@Override
	public List<ReportPersonalloanNearweek> getcoreComovertimeDetail(ReportPersonlloaninfoNew entity,String yearStart,String yearEnd){
		List<ReportPersonalloanNearweek> list = reportPersonalLoanNewDao.getcoreComovertimeDetailList(entity, null, null,yearStart,yearEnd);
		return list;
	}


	@Override
	public List<ReportPersonlloaninfoNew> getPersonlloaninfoNewList(
			ReportPersonlloaninfoNew entity,String weekStart,String weekEnd) {
		return reportPersonalLoanNewDao.getPersonlloaninfoNewList(entity,weekStart,weekEnd);
	}


}
