package com.softline.service.report.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.common.DES;
import com.softline.dao.report.IReportFinancingInnovateDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.financing.ReportFinancingInnovate;
import com.softline.entity.financing.ReportFinancingInnovateEnclosure;
import com.softline.entity.financing.ReportFinancingInnovateLog;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFinancingInnovateService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.impl.CommonService;
import com.softline.util.MsgPage;

@Service("reportFinancingInnovateService")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFinancingInnovateService implements IReportFinancingInnovateService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Autowired
	@Qualifier("reportFinancingInnovateDao")
	private IReportFinancingInnovateDao reportFinancingInnovateDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	public MsgPage getReportFinancingInnovateListView(ReportFinancingInnovate entity, Integer curPageNum, Integer pageSize, Integer status)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportFinancingInnovateDao.getReportFinancingInnovateListCount(entity,status);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportFinancingInnovate> list = reportFinancingInnovateDao.getReportFinancingInnovateList(entity, offset, pageSize,status);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingInnovate getReportFinancingInnovate(Integer id)
	{
		return reportFinancingInnovateDao.getReportFinancingInnovate( id);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveReportFinancingInnovateCheck(ReportFinancingInnovate entity)
	{
		CommitResult result=new CommitResult();
		if(!reportFinancingInnovateDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 导入保存
	 */
	public Integer saveReport(ReportFinancingInnovate entity,HhUsers use){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getCoreOrg());
		entity.setParentorg(node.getVcOrganId());
		entity.setIsdel(0);
		entity.setCreateDate(df.format(new Date()));
		entity.setCreatePersonId(use.getVcEmployeeId());
		entity.setCreatePersonName(use.getVcName());
		entity.setStatus(Base.examstatus_1);
		CommitResult result=saveReportFinancingInnovateCheck(entity);
		if(!result.isResult())
		{
			return 1;
		}
		commonDao.saveOrUpdate(entity);
		return 0;
	}
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingInnovateAndEnclosure(ReportFinancingInnovate entity,ReportFinancingInnovate oldEntity,HhUsers use, MultipartFile[] enclosures)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportFinancingInnovateCheck(entity);
//		if(!result.isResult())
//		{
//			return result;
//		}
		//创建融资项目进展日志对象
		ReportFinancingInnovateLog log = new ReportFinancingInnovateLog();
		//添加日志信息
		log.setIsdel(0);
		log.setUpdateDate(df.format(new Date()));
		log.setUpdatePerson(use.getVcName());
		String str1 = "变更为:";
		String str2 = ",";
		String str3 = "银行";
		String topUpdateDetail = str3+systemDao.getDescription(entity.getStatus())+str2;
		String updateDetail = "";
		if(entity.getId()==null) 
		{
			entity.setIsdel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			updateDetail = "创建此融资项目进展数据";
			if((Base.examstatus_2).equals(entity.getStatus()))
				updateDetail += topUpdateDetail+"并上报";
			log.setUpdateDetail(updateDetail);
		}
		else
		{
			entity.setIsdel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			
			//修改之前的融资项目进展实体并与现在修改后的融资项目进展实体进行比较，记录在log中
			Map<String, String> compareResult = Common.compareEntity(entity,oldEntity, new String[]{"id","org","coreOrgname","coreOrg",
					"projectProgress","isdel","status","createPersonName","createPersonId","createDate","lastModifyPersonId",
					"lastModifyPersonName","lastModifyDate","reportPersonName","reportPersonId","reportDate","auditorPersonName","auditorPersonId",
					"auditorDate","parentorg","statusName","patternName"});//新的实体，旧的实体，不需要比较的属性
			if(compareResult != null) {
				Set<String> keySet = compareResult.keySet();
				if(keySet.size() != 0) {//有修改
					for(String key : keySet){  
						String updateMsg = compareResult.get(key);//修改后的信息
						if(key.equals("coreOrg"))//根据coreOrg获取公司名称
						{
							updateDetail += "业态公司"+str1+systemDao.getFinanceCompName(updateMsg, Base.financetype)+str2;
						}
						else if(key.equals("projectName"))
						{
							updateDetail += "项目名称"+str1+updateMsg+str2;
						}
						else if(key.equals("modelRequires"))
						{
							updateDetail += "融资模式要求"+str1+updateMsg+str2;
						}
						else if(key.equals("hypothecationInformation"))
						{
							updateDetail += "抵质押信息"+str1+updateMsg+str2;
						}
						else if(key.equals("pattern"))
						{
							updateDetail += "模式"+str1+updateMsg+str2;
						}
						else if(key.equals("financialInstitution"))
						{
							updateDetail += "金融机构"+str1+updateMsg+str2;
						}
						else if(key.equals("scale"))
						{
							updateDetail += "规模(亿)"+str1+updateMsg+str2;
						}
						else if(key.equals("term"))
						{
							updateDetail += "期限(月)"+str1+updateMsg+str2;
						}
						else if(key.equals("comprehensiveFinancingCostRatio"))
						{
							updateDetail += "综合成本(%)"+str1+updateMsg+str2;
						}
						else if(key.equals("operationDepartment"))
						{
							updateDetail += "操作主体"+str1+updateMsg+str2;
						}
						else if(key.equals("financingStructure"))
						{
							updateDetail += "融资结构"+str1+updateMsg+str2;
						}
						else if(key.equals("financingProgress"))
						{
							updateDetail += "融资进展"+str1+updateMsg+str2;
						}
						else if(key.equals("problem"))
						{
							updateDetail += "目前存在的问题"+str1+updateMsg+str2;
						}
						else if(key.equals("alreadyAccountAmounts"))
						{
							updateDetail += "已下账金额(亿)"+str1+updateMsg+str2;
						}
						else if(key.equals("expectAccountDate"))
						{
							updateDetail += "预计下账时间"+str1+updateMsg+str2;
						}
						else if(key.equals("personLiable"))
						{
							updateDetail += "责任人"+str1+updateMsg+str2;
						}
						else if(key.equals("operator"))
						{
							updateDetail += "经办人"+str1+updateMsg+str2;
						}
						else if(key.equals("operatorNum"))
						{
							updateDetail += "经办人联系方式"+str1+updateMsg+str2;
						}
					} 
				}
				//处理更新的信息
				updateDetail = "".equals(updateDetail) ? "无进展" : updateDetail.substring(0,updateDetail.length() -1);
				if((Base.examstatus_2).equals(entity.getStatus()))
					updateDetail += ",并上报";
				log.setUpdateDetail(topUpdateDetail + updateDetail);
			}
		}
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getCoreOrg());
		entity.setParentorg(node.getVcOrganId());
		commonDao.saveOrUpdate(entity);
		
		//保存日志信息
		log.setFinancingProjectId(entity.getId());
		commonDao.saveOrUpdate(log);
		
		//先获取原来所绑定的附件记录
		List<ReportFinancingInnovateEnclosure> oldEnclosures = getOldEnclosures(entity.getId());
		//获取原来附件记录的总数
		int j = oldEnclosures.size();
		//遍历现在所有附件
		for(int i = 0;i < enclosures.length;i++) {
			MultipartFile file = enclosures[i];
			// 保存附件
			if (file != null && !file.isEmpty()) {
				//拼接附件路径
				String filePath = DES.REPORT_FINANCING_INNOVATE_ENCLOSURE_PATH + entity.getId()
						+ File.separator;
				String fileName = file.getOriginalFilename();
				if(i < j) {//说明原来绑定在这个索引的附件被更换
					//现将原来此索引绑定的附件删除
					String oldFileName = oldEnclosures.get(i).getEnclosureName();
					Common.deleteFile(filePath, oldFileName);
					//并删除此附件记录
					commonDao.delete(oldEnclosures.get(i));
				}else {
					Common.deleteFile(filePath, fileName);
				}
				Common.saveWkFile(file, filePath, fileName);
				//保存新的附件记录
				ReportFinancingInnovateEnclosure obj = new ReportFinancingInnovateEnclosure();
				obj.setFinancingProjectId(entity.getId());
				obj.setEnclosureName(fileName);
				obj.setEnclosureAddress(filePath+fileName);
				commonDao.saveOrUpdate(obj);
			}
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	/**
	 * list页面上报时，实体更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingInnovate(ReportFinancingInnovate entity,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportFinancingInnovateCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		//创建融资项目进展日志对象
		ReportFinancingInnovateLog log = new ReportFinancingInnovateLog();
		//添加日志信息
		log.setIsdel(0);
		log.setUpdateDate(df.format(new Date()));
		log.setUpdatePerson(use.getVcName());
		String str1 = ",";
		String str2 = "银行";
		String topUpdateDetail = str2+systemDao.getDescription(entity.getStatus())+str1;
		String updateDetail = "";
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
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			if((Base.examstatus_2).equals(entity.getStatus()))
				updateDetail = topUpdateDetail+"并上报";
			log.setUpdateDetail(updateDetail);
			
		}
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getCoreOrg());
		entity.setParentorg(node.getVcOrganId());
		commonDao.saveOrUpdate(entity);
		//保存日志信息
		log.setFinancingProjectId(entity.getId());
		commonDao.saveOrUpdate(log);
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 */
	public CommitResult saveReportFinancingInnovateExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportFinancingInnovate entity=reportFinancingInnovateDao.getReportFinancingInnovate(entityID);
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
	
	/**
	 * 审核
	 * @param entityID 采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingInnovateExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		ReportFinancingInnovate entity=getReportFinancingInnovate(entityID);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveReportFinancingInnovateExamineCheck(entityID);
		if(!result.isResult())
		{
			return result;
		}
		//创建融资项目进展日志对象
		ReportFinancingInnovateLog log = new ReportFinancingInnovateLog();
		//添加日志信息
		log.setIsdel(0);
		log.setUpdateDate(df.format(new Date()));
		log.setUpdatePerson(use.getVcName());
		String str1 = ",";
		String str2 = "银行";
		String topUpdateDetail = str2+systemDao.getDescription(entity.getStatus())+str1;
		String updateDetail = "";
		
		//审核不通过
		if(examResult.equals(Base.examresult_1)) {
			entity.setStatus(Base.examstatus_3);
			updateDetail = "此数据审核不通过";
		}
		//审核通过
		else if(examResult.equals(Base.examresult_2)) {
			entity.setStatus(Base.examstatus_4);
			updateDetail = "此数据审核通过";
		}
		log.setUpdateDetail(topUpdateDetail+updateDetail);
		String a=df.format(new Date());
		entity.setIsdel(0);
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(use.getVcEmployeeId());
		entity.setLastModifyPersonName(use.getVcName());
		//保存
		commonDao.saveOrUpdate(entity);
		//保存日志信息
		log.setFinancingProjectId(entity.getId());
		commonDao.saveOrUpdate(log);
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_reportFinancingInnovate, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportFinancingInnovate(Integer id,HhUsers use)
	{
		
		ReportFinancingInnovate entity=reportFinancingInnovateDao.getReportFinancingInnovate(id);
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			//删除项目记录日志
			reportFinancingInnovateDao.deleteLog(id);
			
			//删除此项目进展绑定的附件
			//先获取此项目所绑定的附件记录
			List<ReportFinancingInnovateEnclosure> oldEnclosures = getOldEnclosures(entity.getId());
			//遍历
			for(ReportFinancingInnovateEnclosure oldEnclosure : oldEnclosures) {
				//拼接附件路径
				String filePath = DES.REPORT_FINANCING_INNOVATE_ENCLOSURE_PATH + entity.getId()
						+ File.separator;
				String fileName = oldEnclosure.getEnclosureName();
				//删除附件
				Common.deleteFile(filePath, fileName);
				//并删除此附件记录
				commonDao.delete(oldEnclosure);
			}
			
			//再删除项目记录
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsdel(1);
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
	public List<ReportFinancingInnovateEnclosure> getOldEnclosures(
			Integer id) {
		return reportFinancingInnovateDao.getOldEnclosures(id);
	}


	@Override
	public List<ReportFinancingInnovateLog> getLog(Integer id) {
		return reportFinancingInnovateDao.getLog(id);
	}
	
	@Override
	public HSSFSheet getEntityForTopOrg(ReportFinancingInnovate entity,List<CompanyTree> CompanyTreeList,HSSFSheet sheet, HSSFCellStyle style, Integer rowNum) {
		//遍历查询并写入数据
		//查询数据
		sheet = this.getEntityForOrg(entity,sheet,style,rowNum);
		for(CompanyTree c:CompanyTreeList){
			entity.setCoreOrg(c.getId());
			sheet = this.getEntityForOrg(entity,sheet,style,rowNum);
			rowNum = sheet.getLastRowNum()+1;
		}
		return sheet;
	}
	
	@Override
	public HSSFSheet getEntityForOrg(ReportFinancingInnovate entity,HSSFSheet sheet,HSSFCellStyle style,Integer rowNum){
		List<ReportFinancingInnovate> listEntity = new ArrayList<ReportFinancingInnovate>();
		/*HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getCoreOrg());
		entity.setParentorg(node.getVcOrganId());*/
		listEntity = reportFinancingInnovateDao.getEntityForOrgForRevise(entity);
		if(listEntity.size()<1){
			return sheet;
		}
		sheet = this.setExcelData(sheet,listEntity,style,rowNum++);
		return sheet;
	}
	
	public HSSFSheet setExcelData(HSSFSheet sheet,List<ReportFinancingInnovate> listEntity,HSSFCellStyle style,Integer rowNum) {
		HSSFRow row = null;
		//设置模板第一行空数据-导出模板
		if(listEntity==null){
			row = sheet.createRow(rowNum);
			Cell cell = row.createCell(0);
			cell.setCellStyle(style);
			for(int i = 1;i<=11;i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			return sheet;
		}
		for(int i = 0;i<listEntity.size();i++){
			row = sheet.createRow(rowNum+i);
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
	 * @return
	 */
	
	public Cell setCell(ReportFinancingInnovate entity,HSSFRow row,HSSFCellStyle style,Integer num){
		// 创建单元格，设置值
		Cell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(num);//序号
		
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getParentOrgName());
		
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getProjectName());
		
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getFinancialInstitution());
		
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getPatternName());
		
		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getHypothecationInformation());
		
		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getComprehensiveFinancingCostRatio());
		
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getProblem());
		
		cell = row.createCell(8);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getOperationDepartment());
		
		cell = row.createCell(9);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getFinancingProgress());
		
		cell = row.createCell(10);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getExpectAccountDate());
		
		cell = row.createCell(11);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getAlreadyAccountAmounts());
		
		
		cell = row.createCell(12);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getPersonLiable());
		
		cell = row.createCell(13);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getOperator());
		
		return cell;
	}

	/**
	 * 计算虚拟公司的汇总值
	 * @param bean
	 * @return
	 */
	@Override
	public ReportFinancingInnovate groupSumDataByEntityCompany(ReportFinancingInnovate bean,String allCompanyNames){
		
		if(null==bean)
			return new ReportFinancingInnovate();
		
		String nNodeId = bean.getCoreOrg();
		HhOrganInfoTreeRelation companyInfoBean = null;
		double sumAlreadyAccountAmounts = 0;
		double sumScale = 0;	
		List<ReportFinancingInnovate> listTempBean = null;
		
		//首先根据coreOrg 判断公司是否为 实体公司 
		if(null != nNodeId){									
			//虚拟公司汇总该公司 下所有实体公司的汇总值	
			listTempBean = reportFinancingInnovateDao.getReportFinancingInnovateByCompanyName(allCompanyNames);			
			for(ReportFinancingInnovate tempBean:listTempBean){						
				if(null!=tempBean){
					companyInfoBean = null;
					companyInfoBean = systemDao.getTreeOrganInfoNode(Base.financetype,tempBean.getCoreOrg());	
					if(null!= companyInfoBean &&
							null!= companyInfoBean.getStatus() &&
							!"".equals(companyInfoBean.getStatus() )){	
						if(companyInfoBean.getStatus()== 50500 || companyInfoBean.getStatus()== 50502){
							if(CommonService.isStrParseToNum(tempBean.getAlreadyAccountAmounts()))
								sumAlreadyAccountAmounts+= Double.valueOf(tempBean.getAlreadyAccountAmounts());
							if(CommonService.isStrParseToNum(tempBean.getScale()))					
								sumScale+= Double.valueOf(tempBean.getScale());
						}						
					}
				}							
			}							
			bean.setAlreadyAccountAmounts(new java.text.DecimalFormat("0.000").format(sumAlreadyAccountAmounts));
			bean.setScale(new java.text.DecimalFormat("0.000").format(sumScale));
		}
		
		//清空
		if(null!=listTempBean)
			listTempBean.clear();
		
		return bean;
	}
	
	@Override
	public int sumDataForSonCompanyData(ReportFinancingInnovate beanIn,MsgPage msgPageOut,ReportFinancingInnovate sumBeanOut){
		
		if(null==beanIn || 
				null == beanIn.getCoreOrg() ||
				"".equals(beanIn.getCoreOrg()))
			return -1;	
		
		HhOrganInfoTreeRelation hhOrganInfoTreeRelation =systemDao.getTopTreeOrganInfoNodeStr(Base.financetype,beanIn.getCoreOrg());		
		if(null == hhOrganInfoTreeRelation || 
				null == hhOrganInfoTreeRelation.getVcOrganId())
			return -1;	
		
		beanIn.setParentorg(hhOrganInfoTreeRelation.getVcOrganId());	
		//返回汇总数据
		List<ReportFinancingInnovate> list=reportFinancingInnovateDao.sumDataForSunCompanys(beanIn);	
        DecimalFormat df = new DecimalFormat("#.00");
		for(Object obj : list) {
			Object[] item = (Object[])obj;
			sumBeanOut.setAlreadyAccountAmounts(item[0]==null?"0":df.format(item[0]).toString());
			sumBeanOut.setScale(item[1]==null?"0":df.format(item[1]).toString());
		}					
		//返回显示数据
		Integer totalPage = msgPageOut.countTotalPage(reportFinancingInnovateDao.sumBeansForSunCompanys(beanIn),
				 									  msgPageOut.getPageSize());
		msgPageOut.setTotalPage(totalPage);		
		msgPageOut.setTotalRecords(reportFinancingInnovateDao.sumBeansForSunCompanys(beanIn));
		msgPageOut.setList(reportFinancingInnovateDao.sumBeansForSunCompanys(beanIn,
																					msgPageOut.countOffset(msgPageOut.getPageNum(),msgPageOut.getPageSize()),
																					msgPageOut.getPageSize()));   
		return 0;	
	}
	
	@Override
	public boolean isVirtualCompany(String organalId){
		
		boolean flag = false;
		
		int backStatus = reportFinancingInnovateDao.isVirtualCompany(organalId);
		
		if(backStatus == 50500 || backStatus ==50502)
			flag = true;
		
		return flag;
	}
	
}
