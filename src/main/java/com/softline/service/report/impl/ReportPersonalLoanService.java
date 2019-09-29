package com.softline.service.report.impl;

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import javax.sound.midi.MidiDevice.Info;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.aspectj.weaver.patterns.IfPointcut.IfFalsePointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.common.ExcelTool;
import com.softline.dao.report.IReportPersonalLoanDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportPersonalloan;
import com.softline.entity.ReportPersonalloanNearMonth;
import com.softline.entity.ReportPersonalloanNearMonthDetail;
import com.softline.entity.ReportPersonlloaninfo;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportPersonalLoanService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("reportPersonalLoanService")
/**
 * 
 * @author lcc
 *
 */
public class ReportPersonalLoanService implements IReportPersonalLoanService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	@Autowired
	@Qualifier("reportPersonalLoanDao")
	private IReportPersonalLoanDao reportPersonalLoanDao;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	
	@Override
	public ReportPersonalloan getPersonalLoanbyID(Integer id) {
		ReportPersonalloan entity = reportPersonalLoanDao.getPersonalLoanbyID(id);
		return entity;
	}


	@Override
	public MsgPage getPersonalloanListView(ReportPersonalloan entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportPersonalLoanDao.getPersonalloanListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportPersonalloan> list = reportPersonalLoanDao.getPersonalloanList(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}


	@Override
	public CommitResult savePersonalloan(ReportPersonalloan entity,
			HhUsers use, MultipartFile itemfile) throws FileNotFoundException,
			IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] time=entity.getDate().split("-");
		entity.setYear(Integer.parseInt(time[0]));
		entity.setMonth(Integer.parseInt(time[1]));
		CommitResult result=savePersonalloanCheck(entity);
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
				result=savePersonalloaninfo( itemfile, entity);
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
	private CommitResult savePersonalloanCheck(ReportPersonalloan entity)
	{
		CommitResult result=new CommitResult();
		if(!reportPersonalLoanDao.savePersonalloanRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该期已经有数据，不能再添加");
			return result;
		}
		if(!reportPersonalLoanDao.checkCanEdit(entity))
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
	private CommitResult savePersonalloaninfo(MultipartFile itemfile,ReportPersonalloan entity) throws FileNotFoundException, IOException
	{
		CommitResult result=new CommitResult();
		reportPersonalLoanDao.deletePersonalloaninfo(entity.getId().toString());
		//存入数据库的list
		List<ReportPersonlloaninfo> Personlloaninfo=new ArrayList<ReportPersonlloaninfo>();
		
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
        Map<String, ReportPersonlloaninfo> map = new HashMap<String, ReportPersonlloaninfo>();
       // int financepass=0;
        
        HhOrganInfoTreeRelation nodeBusiness = systemDao.getTreeOrganInfoNodeBusiness(Base.financetype, entity.getOrg());
        String coreOrg=nodeBusiness.getId().getNnodeId();
        String coreOrgNm =nodeBusiness.getVcFullName();
        entity.setCoreorg(coreOrg);
        entity.setCoreorgname(coreOrgNm);
        for (int k = 1; k < sheet.getPhysicalNumberOfRows(); k++) {
        	int i=1;//列索引
        	//行
        	Row row = sheet.getRow(k);
        	String responsibleperson=exceltool.getCellValue(workbook, row.getCell(2));//借款人/责任人
        	if(Common.notEmpty(responsibleperson)){
	        	ReportPersonlloaninfo item=new ReportPersonlloaninfo();
	        	//Cell cell= row.getCell(0);
	        	item.setIsdel(0);
	        	item.setGroupid(entity.getId());
	        	item.setOrg(entity.getOrg());
	        	item.setOrgname(entity.getOrgname());
	        	//业态ID 名称
	        	item.setCoreorg(entity.getCoreorg());
	        	item.setCoreorgname(entity.getCoreorgname());
	        	item.setYear(entity.getYear());
	        	item.setMonth(entity.getMonth());
	        	item.setParentorg(entity.getParentorg());
	        	//填报公司名称位于第一列
	        	String fillcompanyname=exceltool.getCellValue(workbook, row.getCell(i));i++;
	        	item.setFillcompanyname(fillcompanyname);
	        	
	        	item.setResponsibleperson(responsibleperson);i++;//借款人/责任人
	        	
	        	String loanType1 = exceltool.getCellValue(workbook, row.getCell(i));i++;
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
	        	
	        	String loanType2 = exceltool.getCellValue(workbook, row.getCell(i));i++;
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
	        	
	        	String useway=exceltool.getCellValue(workbook, row.getCell(i));i++;
	        	item.setUseway(useway);
	        	
	        	String loantime=exceltool.getCellValue(workbook, row.getCell(i));i++;
	        	item.setLoantime(loantime);
	        	
	        	String loanmoney=exceltool.getCellValue(workbook, row.getCell(i));i++;
	        	item.setLoanmoney(loanmoney);
	        	item.setLoanmoneyforcompare(loanmoney);//用来差异分析的借款金额
	
	        	
	        	try{
	        		String loanmonth=exceltool.getCellValue(workbook, row.getCell(i));i++;
	        		Integer month =(int)Math.round(Double.parseDouble(loanmonth));
	            	item.setLoanmonth(month);
	            	if (month>1) {
	            		item.setLoanovermoney(item.getLoanmoney());
					}else {
						item.setLoanovermoney("0.00");
					}
	        	}catch(Exception e)
	            {}
	        	
	        	String remark=exceltool.getCellValue(workbook, row.getCell(i));i++;
	        	item.setRemark(remark);
	        	
	        	String check=exceltool.getCellValue(workbook, row.getCell(i));i++;
	        	item.setChecktxt(check);
	        	
	        	String loannum=exceltool.getCellValue(workbook, row.getCell(i));i++;
	        	item.setLoannum(loannum);
	        	
	        	//如果无重复的名字
	        	if (!map.containsKey(responsibleperson)) {
	        		map.put(responsibleperson, item);
				}else {//将同一人的借款记录加起来
					ReportPersonlloaninfo info = map.get(responsibleperson);
					Double loanmoney1 =Double.valueOf(info.getLoanmoneyforcompare());
					Double loanmoney2 =Double.valueOf(item.getLoanmoneyforcompare());
					info.setLoanmoneyforcompare(String.valueOf(sum(loanmoney1,loanmoney2)));
					Double overmoney1 =Double.valueOf(info.getLoanovermoney());
					Double overmoney2 =Double.valueOf(item.getLoanovermoney());
					info.setLoanovermoney(String.valueOf(sum(overmoney1,overmoney2)));
					map.put(responsibleperson, info);
				}
	        	
	        	boolean flag=set.add(loannum);
	        	if (flag) {
	        		Personlloaninfo.add(item);
				} else {
					result=CommitResult.createErrorResult("存在重复的借款公文号!");
					return result;
				}
	        	
	        }
        }
        //取出hashmap中value,放入到用于比较的list中，进行比较
        //用于比较的list
  		List<ReportPersonlloaninfo> compareList=new ArrayList<ReportPersonlloaninfo>();
        Collection<ReportPersonlloaninfo> values =  map.values();
        Iterator<ReportPersonlloaninfo> it2 = values.iterator();
        while(it2.hasNext()) {  
        	compareList.add(it2.next());  
        } 
        ReportPersonalloanNearMonth personalloanNearMonth = compareLoanNearMonth(compareList,entity);
        commonDao.saveOrUpdate(personalloanNearMonth);
        reportPersonalLoanDao.savePersonlloaninfoitem(Personlloaninfo,personalloanNearMonth);
        commonDao.saveOrUpdate(entity);
        result.setResult(true);
        return result;
	}

	public ReportPersonalloanNearMonth compareLoanNearMonth(List<ReportPersonlloaninfo> compareList,ReportPersonalloan entity) {
		//业态记录
		ReportPersonalloanNearMonth personalloanNearMonth = new ReportPersonalloanNearMonth();
		double loantotalmoney=0.00;//借款人总额(元)
		int loansumperson=compareList.size();//借款人数(人)
		int loansumoverperson=0;//借款超期人数(人)
		double loantotalovermoney=0.00;//超期金额(元)
		int monthsumaddperson=0;//本月新增借款人数(人)
		int monthsumfinishperson=0;//本月还完借款人数(人)
		
		//业态下属公司记录
		List<ReportPersonalloanNearMonthDetail> detailList = new ArrayList<ReportPersonalloanNearMonthDetail>();
		
		//HhOrganInfoTreeRelation nodeBusiness = systemDao.getTreeOrganInfoNodeBusiness(Base.financetype, entity.getOrg());
		personalloanNearMonth.setCoreorg(entity.getCoreorg());
		personalloanNearMonth.setCoreorgname(entity.getCoreorgname());
		personalloanNearMonth.setYear(entity.getYear());
		personalloanNearMonth.setMonth(entity.getMonth());
		personalloanNearMonth.setParentorg(entity.getParentorg());
		personalloanNearMonth.setDate(entity.getDate());
		personalloanNearMonth.setIsdel(0);
		personalloanNearMonth.setGroupid(entity.getId());
		//上个月个人借款情况
		List<ReportPersonlloaninfo> lastMonthList = reportPersonalLoanDao.getLastMonthCompareList(entity);
		//把上个月借款 同一个名字的 放在一条记录里面
		 Map<String, ReportPersonlloaninfo> map = new HashMap<String, ReportPersonlloaninfo>();
		 for (ReportPersonlloaninfo item : lastMonthList) {
			 String responsibleperson = item.getResponsibleperson();
			 item.setLoanmoneyforcompare(item.getLoanmoney());//把每一个人的借款金额放到 用做比较的借款金额中 用来比较
			 if (Integer.valueOf(item.getLoanmonth()) >1) {
				 item.setLoanovermoney(item.getLoanmoney());
			 }else {
				item.setLoanovermoney("0.00");
			 }
			 if (!map.containsKey(responsibleperson)) {
				 map.put(responsibleperson, item);
			 }else {//将同一人的借款记录加起来
					ReportPersonlloaninfo info = map.get(responsibleperson);
					Double value1 =Double.valueOf(info.getLoanmoneyforcompare());
					Double value2 =Double.valueOf(item.getLoanmoneyforcompare());
					info.setLoanmoneyforcompare(String.valueOf(sum(value1,value2)));
					Double overmoney1 =Double.valueOf(info.getLoanovermoney());
					Double overmoney2 =Double.valueOf(item.getLoanovermoney());
					info.setLoanovermoney(String.valueOf(sum(overmoney1,overmoney2)));
					map.put(responsibleperson, info);
				}
		}
		 List<ReportPersonlloaninfo> lastMonthCompareList=new ArrayList<ReportPersonlloaninfo>();
		 if (!map.isEmpty()) {
	        Collection<ReportPersonlloaninfo> values =  map.values();
	        Iterator<ReportPersonlloaninfo> it2 = values.iterator();
	        while(it2.hasNext()) {  
	        	lastMonthCompareList.add(it2.next());  
	       } 
		}

		//如果上月数据为空，说明本月均为新增数据
		if (lastMonthCompareList == null || lastMonthCompareList.size() ==0) {

			for (ReportPersonlloaninfo reportPersonlloaninfo : compareList) {
				ReportPersonalloanNearMonthDetail detail = new ReportPersonalloanNearMonthDetail();
				detail.setCoreorg(personalloanNearMonth.getCoreorg());
				detail.setCoreorgname(personalloanNearMonth.getCoreorgname());
				detail.setYear(personalloanNearMonth.getYear());
				detail.setMonth(personalloanNearMonth.getMonth());
				
				detail.setOrgname(reportPersonlloaninfo.getOrgname());
				detail.setPersonname(reportPersonlloaninfo.getResponsibleperson());
				detail.setBeginningbalance("0");//期初余额(元)
				detail.setMonthaddmoney(reportPersonlloaninfo.getLoanmoneyforcompare());//本月新增(元)
				detail.setMonthreturnmoney("0");//本月已还(元)
				detail.setEndingbalance(reportPersonlloaninfo.getLoanmoneyforcompare());//期末余额(元)
				detail.setParentorg(entity.getParentorg());
				detail.setIsdel(0);
				loantotalmoney = sum(loantotalmoney, Double.valueOf(reportPersonlloaninfo.getLoanmoneyforcompare()));
				if (Double.valueOf(reportPersonlloaninfo.getLoanovermoney())>0) {
					loansumoverperson++;
					loantotalovermoney= sum(loantotalovermoney, Double.valueOf(reportPersonlloaninfo.getLoanmoneyforcompare()));
				}
				monthsumaddperson++;
				detailList.add(detail);
			}
			
			personalloanNearMonth.setDetaillist(detailList);
		}else {
				List<ReportPersonalloanNearMonthDetail> addList = new ArrayList<ReportPersonalloanNearMonthDetail>();
				//如果上月数据不为空，则分三种情况进行比较：1.本月较上月新增  2.本月较上月较少  3.本月上月均存在
				//处理方法：本月A（本月记录已经去重）,上月B 
				//遍历本月A每一条 如果上月B有，则放进List,A,B均remove掉，迭代完后 List里面存的是两个月都有的
				
				Iterator<ReportPersonlloaninfo> item1 = compareList.iterator(); 
				
				while(item1.hasNext()){
					Iterator<ReportPersonlloaninfo> item2 = lastMonthCompareList.iterator();
					ReportPersonlloaninfo info1 = item1.next();
					String personName = info1.getResponsibleperson();
					ReportPersonalloanNearMonthDetail detail = new ReportPersonalloanNearMonthDetail();
					detail.setCoreorg(personalloanNearMonth.getCoreorg());
					detail.setCoreorgname(personalloanNearMonth.getCoreorgname());
					detail.setYear(personalloanNearMonth.getYear());
					detail.setMonth(personalloanNearMonth.getMonth());
					detail.setOrgname(info1.getOrgname());
					detail.setPersonname(info1.getResponsibleperson());
					detail.setParentorg(entity.getParentorg());
					detail.setIsdel(0);
					while(item2.hasNext()){
						ReportPersonlloaninfo info2 = item2.next();
						//------说明本月 上月 均存在 --------
						//期初余额为上月借款情况，本期新增为（本月借款-上月借款=a 如果a>0,说明本月新增为a,如果a<=0 说明本月新增为0），
						//本月已还为（本月借款-上月借款=a 如果a>0,说明本月借的多，已还为0，如果a<=0，说明本月有还款的情况，还款金额为a的绝对值），
						//期末余额为本月借款数目。
						if (personName.equals(info2.getResponsibleperson())) {
							detail.setBeginningbalance(info2.getLoanmoneyforcompare());//期初余额(元)
							Double addMoney = Double.valueOf(info1.getLoanmoneyforcompare())-Double.valueOf(info2.getLoanmoneyforcompare());
							if (addMoney>0) {
								detail.setMonthaddmoney(String.valueOf(addMoney));//本月新增(元)
								detail.setMonthreturnmoney("0");//本月已还(元)
							}else {
								detail.setMonthaddmoney("0");
								detail.setMonthreturnmoney(String.valueOf(-addMoney));
							}
							detail.setEndingbalance(info1.getLoanmoneyforcompare());//期末余额(元)
							detail.setIsdel(0);
							loantotalmoney = sum(loantotalmoney, Double.valueOf(info1.getLoanmoneyforcompare()));
							if (info1.getLoanmonth()>1) {
								loansumoverperson++;
								loantotalovermoney= sum(loantotalovermoney, Double.valueOf(info1.getLoanmoneyforcompare()));
							}
							
							addList.add(detail);
							item1.remove();
							item2.remove();
						}
					}
				}
				//----------当A遍历完成后，compareList里面剩下的是本月新增的-------------
				
				monthsumaddperson=compareList.size();//本月新增借款人数(人)
				
				//期初余额为0，本月新增金额为本月借款数目，本月已还为0，期末余额为0
				for (ReportPersonlloaninfo info : compareList) {
					ReportPersonalloanNearMonthDetail detail = new ReportPersonalloanNearMonthDetail();
					detail.setCoreorg(personalloanNearMonth.getCoreorg());
					detail.setCoreorgname(personalloanNearMonth.getCoreorgname());
					detail.setYear(personalloanNearMonth.getYear());
					detail.setMonth(personalloanNearMonth.getMonth());
					detail.setOrgname(info.getOrgname());
					detail.setBeginningbalance("0");//期初余额(元)
					detail.setMonthaddmoney(info.getLoanmoneyforcompare());//本月新增(元)
					detail.setMonthreturnmoney("0");//本月已还(元)
					detail.setEndingbalance(info.getLoanmoneyforcompare());//期末余额(元)
					detail.setPersonname(info.getResponsibleperson());
					detail.setParentorg(entity.getParentorg());
					detail.setIsdel(0);
					loantotalmoney = sum(loantotalmoney, Double.valueOf(info.getLoanmoneyforcompare()));
					if (Common.notEmpty(info.getLoanovermoney()) && Double.valueOf(info.getLoanovermoney())>0) {
						loansumoverperson++;
						loantotalovermoney= sum(loantotalovermoney, Double.valueOf(info.getLoanmoneyforcompare()));
					}
					
					addList.add(detail);
				}
				
				//----------lastMonthCompareList里面剩下的是本月减少的-------------
				
				monthsumfinishperson=lastMonthCompareList.size();
				
				//期初余额为上月借款数目，本月新增为0，本月已还为上月借款数目，期末余额为0
				for (ReportPersonlloaninfo info : lastMonthCompareList) {
					ReportPersonalloanNearMonthDetail detail = new ReportPersonalloanNearMonthDetail();
					detail.setCoreorg(personalloanNearMonth.getCoreorg());
					detail.setCoreorgname(personalloanNearMonth.getCoreorgname());
					
					detail.setYear(personalloanNearMonth.getYear());
					detail.setMonth(personalloanNearMonth.getMonth());
					detail.setOrgname(info.getOrgname());
					detail.setBeginningbalance(info.getLoanmoneyforcompare());//期初余额(元)
					detail.setMonthaddmoney("0");//本月新增(元)
					detail.setMonthreturnmoney(info.getLoanmoneyforcompare());//本月已还(元)
					detail.setEndingbalance("0");//期末余额(元)
					detail.setPersonname(info.getResponsibleperson());
					detail.setParentorg(entity.getParentorg());
					detail.setIsdel(0);
					addList.add(detail);
				}
				personalloanNearMonth.setDetaillist(addList);
		}
		personalloanNearMonth.setLoantotalmoney(String.valueOf(loantotalmoney));//借款人总额(元)
		personalloanNearMonth.setLoansumperson(loansumperson);//借款人数(人)
		personalloanNearMonth.setLoansumoverperson(loansumoverperson);//借款超期人数(人)
		personalloanNearMonth.setLoantotalovermoney(String.valueOf(loantotalovermoney));//超期金额(元)
		personalloanNearMonth.setMonthsumaddperson(monthsumaddperson);//本月新增借款人数(人)
		personalloanNearMonth.setMonthsumfinishperson(monthsumfinishperson);//本月还完借款人数(人)
		
		return personalloanNearMonth;
	}
	
	public static double sum(double d1,double d2){ 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.add(bd2).doubleValue(); 
    } 

	@Override
	public CommitResult deletePersonalloan(Integer id, HhUsers use) {
		ReportPersonalloan entity=reportPersonalLoanDao.getPersonalLoanbyID(id);
		CommitResult result=new CommitResult();
		if(!reportPersonalLoanDao.checkCanEdit(entity))
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
			reportPersonalLoanDao.deletePersonalloaninfo(entity.getId().toString());
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}


	/***
	 * 审核
	 */
	@Override
	public CommitResult savePersonalloanExamine(Integer entityid,String examStr, Integer examResult, HhUsers use) {
		CommitResult result=new CommitResult();
		ReportPersonalloan entity=getPersonalLoanbyID(entityid);
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
		else if(examResult.equals(Base.examresult_2))
			entity.setStatus(Base.examstatus_4);
		String a=df.format(new Date());
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		//保存HrPersongroup
		commonDao.saveOrUpdate(entity);
		
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_personalloan, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}

	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 */
	public CommitResult savePersonalloanExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportPersonalloan entity=reportPersonalLoanDao.getPersonalLoanbyID(entityID);
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
	public MsgPage getPersonalloanInfoListView(ReportPersonalloan entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportPersonalLoanDao.getPersonalLoaninfoCount(entity.getId());
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportPersonlloaninfo> list = reportPersonalLoanDao.getPersonlloaninfoList(entity.getId(), offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}



	
	
	/**
	 *   --------------------------------公司员工借款相邻月份差异比较查询---------------------------------------
	 */
	@Override
	public MsgPage getPersonalloanNearMonthList(ReportPersonalloanNearMonth entity, Integer curPageNum,Integer pageSize) {
		//查询公司员工个人借款增减变动情况 总记录数
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportPersonalLoanDao.getPersonalloanNearMonthDetailCount(entity);
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	List<ReportPersonalloanNearMonth> list = reportPersonalLoanDao.getPersonalloanNearMonthList(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list); 
		return msgPage;
	}

	
	/**
	 *   --------------------------------个人超期信息一览---------------------------------------
	 */

	@Override
	public MsgPage getPersonnalovertimeDetail(ReportPersonlloaninfo entity, Integer curPageNum,Integer pageSize) {
		MsgPage msgPage = new MsgPage();  
		//总记录数
        Integer totalRecords = reportPersonalLoanDao.getPersonlloaninfoCount(entity);
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	List<ReportPersonlloaninfo> result = new ArrayList<ReportPersonlloaninfo>();
    	List<Object> list = reportPersonalLoanDao.getPersonnalovertimeDetailList(entity, offset, pageSize);
    	if (list != null && list.size()>0) {
			for (Object object : list) {
				Object[] obj = (Object[])object;
				ReportPersonlloaninfo info = new ReportPersonlloaninfo();
				info.setId(Integer.valueOf(obj[0].toString()));
				info.setYear(obj[1] != null?Integer.valueOf(obj[1].toString()):null);
				info.setMonth(obj[2] != null?Integer.valueOf(obj[2].toString()):null);
				info.setCoreorgname(obj[3] != null?obj[3].toString():"");
				info.setOrgname(obj[4] != null?obj[4].toString():"");
				info.setResponsibleperson(obj[5] != null?obj[5].toString():"");
				info.setLoanmoney(obj[6] != null?obj[6].toString():"");
				info.setLoanovermoney(obj[7] != null?obj[7].toString():"");
				result.add(info);
			}
		}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(result); 
		return msgPage;
	}


	@Override
	public ReportPersonlloaninfo getPersonlloaninfobyId(Integer id) {
		ReportPersonlloaninfo info = reportPersonalLoanDao.getPersonlloaninfobyId(id);
		return info;
	}



	
	/**
	 *   --------------------------------个人超期信息一览汇总---------------------------------------
	 */
	@Override
	public MsgPage getcoreComovertimeDetail(ReportPersonlloaninfo entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();  
		//总记录数
        Integer totalRecords = reportPersonalLoanDao.getcoreComloaninfoCount(entity);
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	List<ReportPersonlloaninfo> result = new ArrayList<ReportPersonlloaninfo>();
    	List<Object> list = reportPersonalLoanDao.getcoreComovertimeDetailList(entity, offset, pageSize);
    	if (list != null && list.size()>0) {
			for (Object object : list) {
				Object[] obj = (Object[])object;
				ReportPersonlloaninfo info = new ReportPersonlloaninfo();
				info.setGroupid(Integer.valueOf(obj[1].toString()));
				info.setYear(obj[2] != null?Integer.valueOf(obj[2].toString()):null);
				info.setMonth(obj[3] != null?Integer.valueOf(obj[3].toString()):null);
				info.setCoreorgname(obj[4] != null?obj[4].toString():"");
				info.setOrgname(obj[5] != null?obj[5].toString():"");
				info.setResponsibleperson(obj[6] != null?obj[6].toString():"");
				info.setLoanmoney(obj[7] != null?obj[7].toString():"");
				
				info.setLoanovermoney(obj[9] != null?obj[9].toString():"");
				result.add(info);
			}
		}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(result); 
		return msgPage;
	}
	
	
}
