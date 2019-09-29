package com.softline.service.report.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.common.DES;
import com.softline.dao.report.IReportFinancingProjectProgressDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.ReportFinancingProjectProgressEnclosure;
import com.softline.entity.ReportFinancingProjectProgressLog;
import com.softline.entity.financing.ReportFinancingBond;
import com.softline.entity.financing.ReportFinancingInnovate;
import com.softline.entity.financing.ReportFinancingShare;
import com.softline.entity.taxTask.DataTaxPay;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFinancingProjectProgressService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.impl.CommonService;
import com.softline.util.MsgPage;

/**
 * 
 * @author ky_tian
 *
 */
@Service("reportFinancingProjectProgressService")
public class ReportFinancingProjectProgressService implements IReportFinancingProjectProgressService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Autowired
	@Qualifier("reportFinancingProjectProgressDao")
	private IReportFinancingProjectProgressDao reportFinancingProjectProgressDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	//小计数据
	public Double scaleNum = 0d;
	
	public Double aggregate = 0.0d;
	
	public MsgPage getReportFinancingProjectProgressListView(ReportFinancingProjectProgress entity, Integer curPageNum, Integer pageSize, Integer status){
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportFinancingProjectProgressDao.getReportFinancingProjectProgressListCount(entity,status);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportFinancingProjectProgress> list = reportFinancingProjectProgressDao.getReportFinancingProjectProgressList(entity, offset, pageSize,status);
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
	public ReportFinancingProjectProgress getReportFinancingProjectProgress(Integer id)
	{
		return reportFinancingProjectProgressDao.getReportFinancingProjectProgress( id);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveReportFinancingProjectProgressCheck(ReportFinancingProjectProgress entity)
	{
		CommitResult result=new CommitResult();
		if(!reportFinancingProjectProgressDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	@SuppressWarnings("finally")
	public CommitResult saveReportFinancingProjectProgressAndEnclosure(ReportFinancingProjectProgress entity,ReportFinancingProjectProgress oldEntity,HhUsers use, MultipartFile[] enclosures)
	{
		CommitResult result = new CommitResult();
		try{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		CommitResult result=saveReportFinancingProjectProgressCheck(entity);
//		if(!result.isResult())
//		{
//			return result;
//		}
		//创建融资项目进展日志对象
		ReportFinancingProjectProgressLog log = new ReportFinancingProjectProgressLog();
		//添加日志信息
		log.setIsdel(0);
		log.setUpdateDate(df.format(new Date()));
		log.setUpdatePerson(use.getVcName());
		String str1 = "变更为:";
		String str2 = ",";
		String str3 = "银行";
		String topUpdateDetail = str3+systemDao.getDescription(entity.getProjectProgress())+str2;
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
			Map<String, String> compareResult = Common.compareEntity(entity,oldEntity, new String[]{"id","org","operateOrg","financingOrg","coreOrgname",//,"operateOrgname","financingOrgname"
					"projectProgress","isdel","status","createPersonName","createPersonId","createDate","lastModifyPersonId",
					"lastModifyPersonName","lastModifyDate","reportPersonName","reportPersonId","reportDate","auditorPersonName","auditorPersonId",
					"auditorDate","parentorg","typeName","categoryName","patternName","getOperateType",
					"projectProgressName","startyear","endyear","startmonth","endmonth","starttime","endtime"});//新的实体，旧的实体，不需要比较的属性
			if(compareResult != null) {
				Set<String> keySet = compareResult.keySet();
				if(keySet.size() != 0) {//有修改
					for(String key : keySet){  
						String updateMsg = compareResult.get(key);//修改后的信息
						if(key.equals("coreOrg"))//根据coreOrg获取公司名称
						{
							updateDetail += "业态公司"+str1+systemDao.getTreeOrganInfoNode(Base.financetype, entity.getCoreOrg()).getVcFullName()+str2;
						}
						else if(key.equals("category"))
						{
							updateDetail += "类别"+str1+systemDao.getDescription(Integer.parseInt(updateMsg))+str2;
						}
						else if(key.equals("operateOrgname"))
						{
							updateDetail += "操作主体"+str1+updateMsg+str2;
						}
						else if(key.equals("financingOrgname"))
						{
							updateDetail += "融资主体"+str1+updateMsg+str2;
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
						else if(key.equals("type"))
						{
							updateDetail += "新或续"+str1+systemDao.getDescription(Integer.parseInt(updateMsg))+str2;
						}
						else if(key.equals("expectAccountDate"))
						{
							updateDetail += "预计下账时间"+str1+updateMsg+str2;
						}
						else if(key.equals("conditionStructure"))
						{
							updateDetail += "条件及结构"+str1+updateMsg+str2;
						}
						else if(key.equals("alreadyAccountAmounts"))
						{
							updateDetail += "已下账金额(亿)"+str1+updateMsg+str2;
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
						else if(key.equals("interestRatio"))
						{
							updateDetail += "利率(%)"+str1+updateMsg+str2;
						}
						else if(key.equals("serviceChargeRatio"))
						{
							updateDetail += "手续费率(%)"+str1+updateMsg+str2;
						}
						else if(key.equals("comprehensiveFinancingCostRatio"))
						{
							updateDetail += "综合融资成本(%)"+str1+updateMsg+str2;
						}
						else if(key.equals("projectProgressDescribe"))
						{
							updateDetail += "项目进展描述"+str1+updateMsg+str2;
						}
						else if(key.equals("projectFollowupAdvance"))
						{
							updateDetail += "项目后续推进"+str1+updateMsg+str2;
						}
						else if(key.equals("problem"))
						{
							updateDetail += "碰到的问题"+str1+updateMsg+str2;
						}
						else if(key.equals("projectDifficulty"))
						{
							updateDetail += "项目难点"+str1+updateMsg+str2;
						}
						else if(key.equals("solution"))
						{
							updateDetail += "解决方式"+str1+updateMsg+str2;
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
		List<ReportFinancingProjectProgressEnclosure> oldEnclosures = getOldEnclosures(entity.getId());
		//获取原来附件记录的总数
		int j = oldEnclosures.size();
		//遍历现在所有附件
		for(int i = 0;i < enclosures.length;i++) {
			MultipartFile file = enclosures[i];
			// 保存附件
			if (file != null && !file.isEmpty()) {
				//拼接附件路径
				String filePath = DES.FINANCING_PROJECT_PROGRESS_ENCLOSURE_PATH + entity.getId()
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
				ReportFinancingProjectProgressEnclosure obj = new ReportFinancingProjectProgressEnclosure();
				obj.setFinancingProjectId(entity.getId());
				obj.setEnclosureName(fileName);
				obj.setEnclosureAddress(filePath+fileName);
				commonDao.saveOrUpdate(obj);
			}
		}
		result.setResult(true);
		result.setEntity(entity);
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			result.setResult(false);
		}finally{
			return result;
		}
	}
	
	/**
	 * list页面上报时，实体更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingProjectProgress(ReportFinancingProjectProgress entity,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportFinancingProjectProgressCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		//创建融资项目进展日志对象
		ReportFinancingProjectProgressLog log = new ReportFinancingProjectProgressLog();
		//添加日志信息
		log.setIsdel(0);
		log.setUpdateDate(df.format(new Date()));
		log.setUpdatePerson(use.getVcName());
		String str1 = ",";
		String str2 = "银行";
		String topUpdateDetail = str2+systemDao.getDescription(entity.getProjectProgress())+str1;
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
	public CommitResult saveReportFinancingProjectProgressExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportFinancingProjectProgress entity=reportFinancingProjectProgressDao.getReportFinancingProjectProgress(entityID);
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
	public CommitResult saveReportFinancingProjectProgressExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		ReportFinancingProjectProgress entity=getReportFinancingProjectProgress(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveReportFinancingProjectProgressExamineCheck(entityID);
		if(!result.isResult())
		{
			return result;
		}
		//创建融资项目进展日志对象
		ReportFinancingProjectProgressLog log = new ReportFinancingProjectProgressLog();
		//添加日志信息
		log.setIsdel(0);
		log.setUpdateDate(df.format(new Date()));
		log.setUpdatePerson(use.getVcName());
		String str1 = ",";
		String str2 = "银行";
		String topUpdateDetail = str2+systemDao.getDescription(entity.getProjectProgress())+str1;
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
		//String a=df.format(new Date());
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

		examineService.saveExamine( examineentityid, Base.examkind_reportfinancingprojectprogress, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportFinancingProjectProgress(Integer id,HhUsers use)
	{
		
		ReportFinancingProjectProgress entity=reportFinancingProjectProgressDao.getReportFinancingProjectProgress(id);
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			//删除项目记录日志
			reportFinancingProjectProgressDao.deleteLog(id);
			
			//删除此项目进展绑定的附件
			//先获取此项目所绑定的附件记录
			List<ReportFinancingProjectProgressEnclosure> oldEnclosures = getOldEnclosures(entity.getId());
			//遍历
			for(ReportFinancingProjectProgressEnclosure oldEnclosure : oldEnclosures) {
				//拼接附件路径
				String filePath = DES.FINANCING_PROJECT_PROGRESS_ENCLOSURE_PATH + entity.getId()
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
	public List<ReportFinancingProjectProgressEnclosure> getOldEnclosures(
			Integer id) {
		return reportFinancingProjectProgressDao.getOldEnclosures(id);
	}


	@Override
	public List<ReportFinancingProjectProgressLog> getLog(Integer id) {
		return reportFinancingProjectProgressDao.getLog(id);
	}

	@Override
	public HSSFSheet getEntityForTopOrg(ReportFinancingProjectProgress entity, List<CompanyTree> CompanyTreeList, HSSFSheet sheet,HSSFCellStyle style, Integer rowNum) {
		//遍历查询并写入数据
		//查询数据
		sheet = this.getEntityForOrg(entity,sheet,style,rowNum);
		for(CompanyTree c:CompanyTreeList){
			entity.setCoreOrg(c.getId());
			rowNum = sheet.getLastRowNum()+1;
			sheet = this.getEntityForOrg(entity,sheet,style,rowNum);
			
			aggregate=0.0d;
		}
		return sheet;
	}
	

	@Override
	public HSSFSheet getEntityForOrg(ReportFinancingProjectProgress entity, HSSFSheet sheet, HSSFCellStyle style, Integer rowNum) {
		//导出模板
		if(entity==null){
			sheet = this.setExcelData(null,sheet,null,style,(rowNum+1),Base.projectProgressFalse,0);
			sheet = this.setExcelData(null,sheet,null,style,(rowNum+1),Base.projectProgressTrue,0);
			return sheet;
		}else{
			List<ReportFinancingProjectProgress> listEntity = new ArrayList<ReportFinancingProjectProgress>();
			List<List<ReportFinancingProjectProgress>> allListEntity= new ArrayList<List<ReportFinancingProjectProgress>>();
			HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getCoreOrg());
			//entity.setParentorg(node.getVcOrganId());
			scaleNum = 0.0d;
			int n = 0;
			//未审批
			listEntity = reportFinancingProjectProgressDao.getEntityForOrg(entity);
			allListEntity.add(listEntity);
		/*	//已审批
			listEntity = reportFinancingProjectProgressDao.getEntityForOrg(entity,Base.projectProgressTrue);
			allListEntity.add(listEntity);*/
			//有数据设置orgname
			HSSFRow row = null;
		/*	if(allListEntity.get(0).size()>0){
				int x = allListEntity.get(1).size()>0?4:2;
				//未审批
				row = sheet.createRow(rowNum);
				Cell cell = row.createCell(0);
				cell.setCellValue(allListEntity.get(0).get(0).getParentOrgName());
				cell.setCellStyle(style);
				//sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum+allListEntity.get(0).size(),0,0));
			}*/
		/*	else if(allListEntity.get(1).size()>0){
				//已审批
				row = sheet.createRow(rowNum);
				Cell cell = row.createCell(0);
				cell.setCellValue(allListEntity.get(1).get(0).getCoreOrgname());
				cell.setCellStyle(style);
				sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum+allListEntity.get(1).size()+2,0,0));
			}*/
			//设置未审核/审核数据
			if(allListEntity.get(0).size()>0){
				sheet = this.setExcelData(row,sheet,allListEntity.get(0),style,rowNum,Base.projectProgressFalse,allListEntity.get(0).size());
//				sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,1,19));
				/*Cell cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue("未审批");*/
				n++;
			}
		/*	if(allListEntity.get(1).size()>0){
				if(allListEntity.get(0).size()>0){
					rowNum = sheet.getLastRowNum()+1;
					row = sheet.createRow(rowNum);
				}
				sheet = this.setExcelData(row,sheet,allListEntity.get(1),style,rowNum,Base.projectProgressTrue,allListEntity.get(0).size()+allListEntity.get(1).size());
				sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,1,19));
				Cell cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue("已审批");
				n++;
			}*/
			/*if(n>0){*/
				rowNum=sheet.getLastRowNum()+1;//获得总行数
				sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,1,7));
				sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,9,19));
				row = sheet.createRow(rowNum);
				Cell cell = row.createCell(0);
				for(int i = 0;i<20;i++){
					cell = row.createCell(i);
					cell.setCellStyle(style);
				}
				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue("合计");
				cell = row.createCell(8);
				cell.setCellStyle(style);
				cell.setCellValue(new java.text.DecimalFormat("##########.0000").format(aggregate));
				cell = row.createCell(9);
				cell.setCellStyle(style);
		
		}
		return sheet;
	}
	
	@Override
	public HSSFSheet setExcelData(HSSFRow row,HSSFSheet sheet,List<ReportFinancingProjectProgress> listEntity, HSSFCellStyle style,Integer rowNum,Integer projectProgressFlag,int count) {
		/*if(listEntity.size()>0&&listEntity.get(0)!=null){
			//设置审批title
			if(Base.projectProgressFalse.equals(projectProgressFlag)){
				//未审批
				//参数说明：1：开始行 2：结束行  3：开始列 4：结束列  
				sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,1,19));
				Cell cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue("未审批");
			}else{
				//已审批
				sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,1,19));
				Cell cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue("已审批");
			}
		}*/
		for(int i = 0;i<listEntity.size();i++){
			row = sheet.createRow(rowNum+i+1);
			this.setCell(listEntity.get(i), row, style ,(i+1));
			scaleNum+=Double.parseDouble(listEntity.get(i).getScale());
		}
		//小计
		if(listEntity.size()>0){
			/*sheet.addMergedRegion(new CellRangeAddress(rowNum+listEntity.size()+1,rowNum+listEntity.size()+1,1,7));
			sheet.addMergedRegion(new CellRangeAddress(rowNum+listEntity.size()+1,rowNum+listEntity.size()+1,9,19));
			row = sheet.createRow(rowNum+listEntity.size()+1);
			Cell cell = null;
			for(int i = 0;i<20;i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			cell = row.createCell(1);
			cell.setCellValue("小计");
			cell.setCellStyle(style);
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(new java.text.DecimalFormat("##########.0000").format(scaleNum));
			cell.setCellStyle(style);*/
			aggregate+=scaleNum;
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
	
	public Cell setCell(ReportFinancingProjectProgress entity,HSSFRow row,HSSFCellStyle style,Integer num){
		Cell cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue(num);//序号
		
		cell=row.createCell(0);cell.setCellStyle(style);cell.setCellValue(entity.getParentOrgName());
		
		cell=row.createCell(2);cell.setCellStyle(style);cell.setCellValue(entity.getCategoryName());

		cell=row.createCell(3);cell.setCellStyle(style);cell.setCellValue(entity.getOperateOrgname());

		cell=row.createCell(4);cell.setCellStyle(style);cell.setCellValue(entity.getFinancingOrgname());

		cell=row.createCell(5);cell.setCellStyle(style);cell.setCellValue(entity.getHypothecationInformation());

		cell=row.createCell(6);cell.setCellStyle(style);cell.setCellValue(entity.getPatternName());

		cell=row.createCell(7);cell.setCellStyle(style);cell.setCellValue(entity.getFinancialInstitution());

		cell=row.createCell(8);cell.setCellStyle(style);cell.setCellValue(entity.getScale());

		cell=row.createCell(9);cell.setCellStyle(style);cell.setCellValue(entity.getTerm());

		cell=row.createCell(10);cell.setCellStyle(style);cell.setCellValue(entity.getInterestRatio());

		cell=row.createCell(11);cell.setCellStyle(style);cell.setCellValue(entity.getServiceChargeRatio());

		cell=row.createCell(12);cell.setCellStyle(style);cell.setCellValue(entity.getComprehensiveFinancingCostRatio());

		cell=row.createCell(13);cell.setCellStyle(style);cell.setCellValue(entity.getTypeName());

		cell=row.createCell(14);cell.setCellStyle(style);cell.setCellValue(entity.getConditionStructure());

		cell=row.createCell(15);cell.setCellStyle(style);cell.setCellValue(entity.getProjectProgressName());
		
		cell=row.createCell(16);cell.setCellStyle(style);cell.setCellValue(entity.getProjectFollowupAdvance());
		
		cell=row.createCell(17);cell.setCellStyle(style);cell.setCellValue(entity.getProblem());

		cell=row.createCell(18);cell.setCellStyle(style);cell.setCellValue(entity.getExpectAccountDate());

		cell=row.createCell(19);cell.setCellStyle(style);cell.setCellValue(entity.getAlreadyAccountAmounts());

		cell=row.createCell(20);cell.setCellStyle(style);cell.setCellValue(entity.getPersonLiable());

		cell=row.createCell(21);cell.setCellStyle(style);cell.setCellValue(entity.getOperator());
		
		return cell;
	}
	
	/**
	 * 导入保存     
	 *  
	 */
	public Map saveReport(ReportFinancingProjectProgress entity,HhUsers use,ArrayList<String> sList) throws Exception{
		
		boolean backInfoFlag = true;
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("backInfo", "导入成功！");
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getCoreOrg());
		entity.setParentorg(node.getVcOrganId());
		entity.setIsdel(0);
		entity.setCreateDate(df.format(new Date()));
		entity.setCreatePersonId(use.getVcEmployeeId());
		entity.setCreatePersonName(use.getVcName());
		entity.setStatus(Base.examstatus_1);
		//设置表格值
		if(null!= sList.get(1) && !"".equals(sList.get(1)))
			entity.setCategory(setSelectValue(sList.get(1),Base.financingCategory));	
		if(null!= sList.get(2) && !"".equals(sList.get(2)))
			entity.setOperateOrgname(sList.get(2));	
		if(null!= sList.get(3) && !"".equals(sList.get(2)))
			entity.setFinancingOrgname(sList.get(3));	
		if(null!= sList.get(4) && !"".equals(sList.get(4)))
			entity.setHypothecationInformation(sList.get(4));	
		if(null!= sList.get(5) && !"".equals(sList.get(5)))
			entity.setPattern(sList.get(5));	
		if(null!= sList.get(6) && !"".equals(sList.get(6)))
			entity.setFinancialInstitution(sList.get(6));	
		if(null!= sList.get(7) && !"".equals(sList.get(7)))
			entity.setScale(sList.get(7));	
		if(null!= sList.get(8) && !"".equals(sList.get(8)))
			entity.setTerm(sList.get(8));	
		if(null!= sList.get(9) && !"".equals(sList.get(9)))
			entity.setInterestRatio(sList.get(9));	
		if(null!= sList.get(10) && !"".equals(sList.get(10)))
			entity.setServiceChargeRatio(sList.get(10));	
		if(null!= sList.get(11) && !"".equals(sList.get(11)))
			entity.setComprehensiveFinancingCostRatio(sList.get(11));	
		if(null!= sList.get(12) && !"".equals(sList.get(12)))
			entity.setType(setSelectValue(sList.get(12),Base.sequelNew));	
		if(null!= sList.get(13) && !"".equals(sList.get(13)))
			entity.setConditionStructure(sList.get(13));	
		if(null!= sList.get(14) && !"".equals(sList.get(14)))
			entity.setProjectProgress(setSelectValue(sList.get(14),Base.projectProgress));	
		if(null!= sList.get(15) && !"".equals(sList.get(15)))
			entity.setExpectAccountDate(sList.get(15));	
		if(null!= sList.get(16) && !"".equals(sList.get(16)))
			entity.setAlreadyAccountAmounts(sList.get(16));	
		if(null!= sList.get(17) && !"".equals(sList.get(17)))
			entity.setPersonLiable(sList.get(17));	
		if(null!= sList.get(18) && !"".equals(sList.get(18)))
			entity.setOperator(sList.get(18));	
		
		if(null==sList.get(7) || !CommonService.isStrParseToNum(sList.get(7))){
			map.put("backInfo", "规模(亿),--输入格式有误！错误信息----"+sList.get(7));
			backInfoFlag = false;
		}
		if(null==sList.get(8) || !CommonService.isStrParseToNum(sList.get(8))){
			map.put("backInfo", "期限(月),--输入格式有误！错误信息----"+sList.get(8));
			backInfoFlag = false;			
		}
		if(null==sList.get(9) || !CommonService.isStrParseToNum(sList.get(9))){
			map.put("backInfo", "利率(%),--输入格式有误！错误信息----"+sList.get(9));
			backInfoFlag = false;			
		}
		if(null==sList.get(10) || !CommonService.isStrParseToNum(sList.get(10))){
			map.put("backInfo", "手续费率(%),--输入格式有误！错误信息----"+sList.get(10));
			backInfoFlag = false;			
		}
//		if(null==sList.get(11) || !CommonService.isStrParseToNum(sList.get(11))){
		if(null==sList.get(11)){
			map.put("backInfo", "综合融资成本(%),--输入格式有误！错误信息----"+sList.get(11));
			backInfoFlag = false;			
		}
		if(null==sList.get(16) || !CommonService.isStrParseToNum(sList.get(16))){
			map.put("backInfo", "已下账金额(亿),--输入格式有误！错误信息----"+sList.get(16));
			backInfoFlag = false;			
		}	
		if(null==sList.get(15) || !CommonService.isValidDate(sList.get(15))){
			map.put("backInfo", "已下账金额(亿),--输入格式有误！错误信息----"+sList.get(15));
			backInfoFlag = false;			
		}
		
		if(backInfoFlag)		
			commonDao.saveOrUpdate(entity);		
		
		return map;
	}
	
	public Integer setSelectValue(String text,Integer type){
		// 续作新增
	    List<HhBase> typeList = null;
	    if(type==Base.sequelNew){
	    	typeList = baseService.getBases(Base.sequelNew);
	    }else if(type==Base.financingCategory){
	    	typeList = baseService.getBases(Base.financingCategory);
	    }else if(type==Base.projectProgress){
	    	typeList = baseService.getBases(Base.projectProgress);
	    }
	    for(HhBase base:typeList) {
	    	if(base.getDescription().equals(text)){
	    		return base.getId();
	    	}
	    }
		return null;
	}
	/**
	 * 海航实业融资项目情况汇总(除债券类)
	 */
	@Override
	public List<String> getProjectProgressList(ReportFinancingProjectProgress entity,String org) {
		return reportFinancingProjectProgressDao.getProjectProgressList(entity,org);
	}

	/**
	 * 海航实业各业态推进中融资项目情况（除债券类）
	 */
	@Override
	public MsgPage getCategoryList(ReportFinancingProjectProgress entity,String org,Integer curPageNum, Integer pageSize) {
		
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportFinancingProjectProgressDao.getCategoryList(entity,org, null, null).size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<String> list = reportFinancingProjectProgressDao.getCategoryList(entity,org, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	/**
	 * 计算虚拟公司的汇总值
	 * @param bean
	 * @return
	 */
	@Override
	public ReportFinancingProjectProgress groupSumDataByEntityCompany(ReportFinancingProjectProgress bean,String allCompanyNames){
		
		if(null == bean)
			return new ReportFinancingProjectProgress();
		
		double sumAlreadyAccountAmounts = 0.0d;
		double sumScale = 0.0d;	
		HhOrganInfoTreeRelation companyInfoBean = null;
		List<ReportFinancingProjectProgress> listTempBean = null;
		
		//虚拟公司汇总该公司 下所有实体公司的汇总值		
		listTempBean = reportFinancingProjectProgressDao.getReportFinancingProjectProgressByCompanyName(allCompanyNames);			
		for(ReportFinancingProjectProgress tempBean:listTempBean){						
			if(null!=tempBean){
				companyInfoBean = systemDao.getTreeOrganInfoNode(Base.financetype,tempBean.getCoreOrg());
				if(null!= companyInfoBean &&
						null!= companyInfoBean.getStatus() &&
						!"".equals(companyInfoBean.getStatus() )){							
					if(companyInfoBean.getStatus()== 50500 || companyInfoBean.getStatus() == 50502){
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
		
		// 清空
		if(null!=listTempBean)
			listTempBean.clear();
		
		return bean;
	}
	
	
	
	public int sumDataForSonCompanyData(ReportFinancingProjectProgress beanIn,MsgPage msgPageOut,ReportFinancingProjectProgress sumBeanOut){
		
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
		List<ReportFinancingProjectProgress> list=reportFinancingProjectProgressDao.sumDataForSunCompanys(beanIn);	
        DecimalFormat df = new DecimalFormat("#.00");
		for(Object obj : list) {
			Object[] item = (Object[])obj;
			sumBeanOut.setAlreadyAccountAmounts(item[0]==null?"0":df.format(item[0]).toString());
			sumBeanOut.setScale(item[1]==null?"0":df.format(item[1]).toString());
		}					
		//返回显示数据
		Integer totalPage = msgPageOut.countTotalPage(reportFinancingProjectProgressDao.sumBeansForSunCompanys(beanIn),
				 									  msgPageOut.getPageSize());
		msgPageOut.setTotalPage(totalPage);		
		msgPageOut.setTotalRecords(reportFinancingProjectProgressDao.sumBeansForSunCompanys(beanIn));
		msgPageOut.setList(reportFinancingProjectProgressDao.sumBeansForSunCompanys(beanIn,
																					msgPageOut.countOffset(msgPageOut.getPageNum(),msgPageOut.getPageSize()),
																					msgPageOut.getPageSize()));   
		return 0;	
	}


	public HSSFSheet getEntityForOrgs(ReportFinancingProjectProgress entity,HSSFSheet sheet, HSSFCellStyle style, Integer rowNum,String org) {
	
			List<ReportFinancingProjectProgress> listEntity = new ArrayList<ReportFinancingProjectProgress>();
			List<ReportFinancingBond> listEntitys = new ArrayList<ReportFinancingBond>();
			List<ReportFinancingInnovate> listEntityss = new ArrayList<ReportFinancingInnovate>();
			List<ReportFinancingShare> listEntitysss = new ArrayList<ReportFinancingShare>();
			
			List<List<ReportFinancingProjectProgress>> allListEntity= new ArrayList<List<ReportFinancingProjectProgress>>();
			List<List<ReportFinancingBond>> allListEntitys= new ArrayList<List<ReportFinancingBond>>();
			List<List<ReportFinancingInnovate>> allListEntityss= new ArrayList<List<ReportFinancingInnovate>>();
			List<List<ReportFinancingShare>> allListEntitysss= new ArrayList<List<ReportFinancingShare>>();
			
	
			listEntity = reportFinancingProjectProgressDao.getEntityForProgress(entity,org);
			listEntitys = reportFinancingProjectProgressDao.getEntityForBond(entity,org);
			listEntityss = reportFinancingProjectProgressDao.getEntityForInvocate(entity,org);
			listEntitysss = reportFinancingProjectProgressDao.getEntityForShare(entity,org);
			
			allListEntity.add(listEntity);
			allListEntitys.add(listEntitys);
			allListEntityss.add(listEntityss);
			allListEntitysss.add(listEntitysss);
			
			HSSFRow row = null;	
			int n = 0;
			if(allListEntity.get(0).size()>0 || allListEntitys.get(0).size()>0 || allListEntitys.get(0).size()>0 || allListEntitys.get(0).size()>0){
				sheet = this.setSumExcelData(row,sheet,allListEntity.get(0),allListEntitys.get(0),allListEntityss.get(0),allListEntitysss.get(0),style,rowNum);
				n++;
			}
			/*	rowNum=sheet.getLastRowNum()+1;//获得总行数
				sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,1,7));
				sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,9,19));
				row = sheet.createRow(rowNum);
				Cell cell = row.createCell(0);
				for(int i = 0;i<20;i++){
					cell = row.createCell(i);
					cell.setCellStyle(style);
				}
				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue("合计");
				cell = row.createCell(8);
				cell.setCellStyle(style);
				cell.setCellValue(new java.text.DecimalFormat("##########.0000").format(aggregate));
				cell = row.createCell(9);
				cell.setCellStyle(style);*/
		
	
		return sheet;
	}
	
	public HSSFSheet setSumExcelData(HSSFRow row,HSSFSheet sheet,List<ReportFinancingProjectProgress> listEntity, List<ReportFinancingBond> listEntitys, List<ReportFinancingInnovate> listEntityss, List<ReportFinancingShare> listEntitysss, HSSFCellStyle style,Integer rowNum) {
		if(listEntity.size()>0){
			for(int i = 0;i<listEntity.size();i++){
				row = sheet.createRow(rowNum+i);
				this.setCellProgress(listEntity.get(i), row, style ,(i+1));
			}
			rowNum = sheet.getLastRowNum()+1;
			row = sheet.createRow(rowNum);
		}
		if(listEntitys.size()>0){
			for(int i = 0;i<listEntitys.size();i++){
				row = sheet.createRow(rowNum+i);
				this.setCellBond(listEntitys.get(i), row, style ,rowNum-2);
			}
			rowNum = sheet.getLastRowNum()+1;
			row = sheet.createRow(rowNum);
		}
		if(listEntityss.size()>0){
			for(int i = 0;i<listEntityss.size();i++){
				row = sheet.createRow(rowNum+i);
				this.setCellInnovate(listEntityss.get(i), row, style ,rowNum-2);
			}
			rowNum = sheet.getLastRowNum()+1;
			row = sheet.createRow(rowNum);
		}
		if(listEntitysss.size()>0){
			for(int i = 0;i<listEntitysss.size();i++){
				row = sheet.createRow(rowNum+i);
				this.setCellShare(listEntitysss.get(i), row, style ,rowNum-2);
			}
			rowNum = sheet.getLastRowNum()+1;
			row = sheet.createRow(rowNum);
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
	private Cell setCellShare(ReportFinancingShare entity,HSSFRow row,HSSFCellStyle style,Integer num) {
		Cell cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue(num);//序号
		
		cell=row.createCell(0);cell.setCellStyle(style);cell.setCellValue(entity.getCoreOrgname());
		
		cell=row.createCell(2);cell.setCellStyle(style);cell.setCellValue(entity.getCategoryName());

		cell=row.createCell(3);cell.setCellStyle(style);cell.setCellValue("");

		cell=row.createCell(4);cell.setCellStyle(style);cell.setCellValue("");

		cell=row.createCell(5);cell.setCellStyle(style);cell.setCellValue(entity.getHypothecationInformation());

		cell=row.createCell(6);cell.setCellStyle(style);cell.setCellValue(entity.getPatternName());

		cell=row.createCell(7);cell.setCellStyle(style);cell.setCellValue(entity.getFinancialInstitution());

		cell=row.createCell(8);cell.setCellStyle(style);cell.setCellValue(entity.getAlreadyAccountAmounts());

		cell=row.createCell(9);cell.setCellStyle(style);cell.setCellValue(entity.getTerm());

		cell=row.createCell(10);cell.setCellStyle(style);cell.setCellValue(entity.getInterestRatio());

		cell=row.createCell(11);cell.setCellStyle(style);cell.setCellValue(entity.getPreConsultantFee());

		cell=row.createCell(12);cell.setCellStyle(style);cell.setCellValue(entity.getComprehensiveFinancingCostRatio());

		cell=row.createCell(13);cell.setCellStyle(style);cell.setCellValue("");
		
		cell=row.createCell(14);cell.setCellStyle(style);cell.setCellValue("");
		
		cell=row.createCell(15);cell.setCellStyle(style);cell.setCellValue("");
		
		cell=row.createCell(16);cell.setCellStyle(style);cell.setCellValue("");
		
		cell=row.createCell(17);cell.setCellStyle(style);cell.setCellValue("");
		
		cell=row.createCell(18);cell.setCellStyle(style);cell.setCellValue(entity.getScale());

		cell=row.createCell(19);cell.setCellStyle(style);cell.setCellValue(entity.getPersonLiable());

		cell=row.createCell(20);cell.setCellStyle(style);cell.setCellValue(entity.getOperator());
		
		return cell;
		
	}

	/**
	 * @param entity 实体
	 * @param row	行
	 * @param cell	列
	 * @param style	样式
	 * @param num	序号
	 */
	private Cell setCellInnovate(ReportFinancingInnovate entity,HSSFRow row,HSSFCellStyle style,Integer num) {
		Cell cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue(num);//序号
		
		cell=row.createCell(0);cell.setCellStyle(style);cell.setCellValue(entity.getCoreOrgname());
		
		cell=row.createCell(2);cell.setCellStyle(style);cell.setCellValue("");

		cell=row.createCell(3);cell.setCellStyle(style);cell.setCellValue(entity.getOperationDepartment());

		cell=row.createCell(4);cell.setCellStyle(style);cell.setCellValue(entity.getFinancialInstitution());

		cell=row.createCell(5);cell.setCellStyle(style);cell.setCellValue(entity.getHypothecationInformation());

		cell=row.createCell(6);cell.setCellStyle(style);cell.setCellValue(entity.getPatternName());

		cell=row.createCell(7);cell.setCellStyle(style);cell.setCellValue("");

		cell=row.createCell(8);cell.setCellStyle(style);cell.setCellValue(entity.getAlreadyAccountAmounts());

		cell=row.createCell(9);cell.setCellStyle(style);cell.setCellValue(entity.getTerm());

		cell=row.createCell(10);cell.setCellStyle(style);cell.setCellValue("");

		cell=row.createCell(11);cell.setCellStyle(style);cell.setCellValue("");

		cell=row.createCell(12);cell.setCellStyle(style);cell.setCellValue(entity.getComprehensiveFinancingCostRatio());

		cell=row.createCell(13);cell.setCellStyle(style);cell.setCellValue("");
		
		cell=row.createCell(14);cell.setCellStyle(style);cell.setCellValue(entity.getFinancingStructure());
		
		cell=row.createCell(15);cell.setCellStyle(style);cell.setCellValue(entity.getProblem());
		
		cell=row.createCell(17);cell.setCellStyle(style);cell.setCellValue("");
		
		cell=row.createCell(16);cell.setCellStyle(style);cell.setCellValue(entity.getExpectAccountDate());
		
		cell=row.createCell(18);cell.setCellStyle(style);cell.setCellValue(entity.getScale());

		cell=row.createCell(19);cell.setCellStyle(style);cell.setCellValue(entity.getPersonLiable());

		cell=row.createCell(20);cell.setCellStyle(style);cell.setCellValue(entity.getOperator());
		
		return cell;
		
		
	}

	/**
	 * @param entity 实体
	 * @param row	行
	 * @param cell	列
	 * @param style	样式
	 * @param num	序号
	 */
	private Cell setCellBond(ReportFinancingBond entity,HSSFRow row,HSSFCellStyle style,Integer num) {
		Cell cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue(num);//序号
		//业态公司
		cell=row.createCell(0);cell.setCellStyle(style);cell.setCellValue(entity.getCoreOrgname());
		//类别
		cell=row.createCell(2);cell.setCellStyle(style);cell.setCellValue(entity.getDebt());
		//操作主体
		cell=row.createCell(3);cell.setCellStyle(style);cell.setCellValue(entity.getResponsibleOrganization());
		//融资主体
		cell=row.createCell(4);cell.setCellStyle(style);cell.setCellValue(entity.getCommitmentShallSubject());
		//质抵押信息
		cell=row.createCell(5);cell.setCellStyle(style);cell.setCellValue(entity.getGuaranteeConditions());
		//模式
		cell=row.createCell(6);cell.setCellStyle(style);cell.setCellValue("");
		//机构
		cell=row.createCell(7);cell.setCellStyle(style);cell.setCellValue(entity.getTheUnderwritingInstitution());
		//下账金额（亿）
		cell=row.createCell(8);cell.setCellStyle(style);cell.setCellValue(entity.getAlreadyAccountAmounts());
		//期限（月）
		cell=row.createCell(9);cell.setCellStyle(style);cell.setCellValue(entity.getTerm());
		//利率
		cell=row.createCell(10);cell.setCellStyle(style);cell.setCellValue(entity.getExpectedInterestRate());
		//前期/顾问费
		cell=row.createCell(11);cell.setCellStyle(style);cell.setCellValue(entity.getAnnualizedUnderwritingRate());
		//综合
		cell=row.createCell(12);cell.setCellStyle(style);cell.setCellValue(entity.getCompositeCost());
		//新/续
		cell=row.createCell(13);cell.setCellStyle(style);cell.setCellValue(entity.getAddOrContinue());
		//条件及结构
		cell=row.createCell(14);cell.setCellStyle(style);cell.setCellValue("");
		//难点
		cell=row.createCell(15);cell.setCellStyle(style);cell.setCellValue("");
		//解决方式
		cell=row.createCell(16);cell.setCellStyle(style);cell.setCellValue("");
		//下账时间
		cell=row.createCell(17);cell.setCellStyle(style);cell.setCellValue("");
		//下账时间
		cell=row.createCell(18);cell.setCellStyle(style);cell.setCellValue(entity.getScale());
		//责任人
		cell=row.createCell(19);cell.setCellStyle(style);cell.setCellValue(entity.getPersonLiable());
		//经办人
		cell=row.createCell(20);cell.setCellStyle(style);cell.setCellValue(entity.getOperator());
		
		return cell;
		
	}

	/**
	 * @param entity 实体
	 * @param row	行
	 * @param cell	列
	 * @param style	样式
	 * @param num	序号
	 */
	private Cell setCellProgress(ReportFinancingProjectProgress entity,HSSFRow row,HSSFCellStyle style,Integer num) {
		Cell cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue(num);//序号
		//业态公司
		cell=row.createCell(0);cell.setCellStyle(style);cell.setCellValue(entity.getParentOrgName());
		//类别
		cell=row.createCell(2);cell.setCellStyle(style);cell.setCellValue(entity.getCategoryName());
		//操作主体
		cell=row.createCell(3);cell.setCellStyle(style);cell.setCellValue(entity.getOperateOrgname());
		//融资主体
		cell=row.createCell(4);cell.setCellStyle(style);cell.setCellValue(entity.getFinancingOrgname());
		//质抵押信息
		cell=row.createCell(5);cell.setCellStyle(style);cell.setCellValue(entity.getHypothecationInformation());
		//模式
		cell=row.createCell(6);cell.setCellStyle(style);cell.setCellValue(entity.getPattern());
		//机构
		cell=row.createCell(7);cell.setCellStyle(style);cell.setCellValue(entity.getFinancialInstitution());
		//下账金额（亿）
		cell=row.createCell(8);cell.setCellStyle(style);cell.setCellValue(entity.getAlreadyAccountAmounts());
		//期限（月）
		cell=row.createCell(9);cell.setCellStyle(style);cell.setCellValue(entity.getTerm());
		//利率
		cell=row.createCell(10);cell.setCellStyle(style);cell.setCellValue(entity.getInterestRatio());
		//前期/顾问费
		cell=row.createCell(11);cell.setCellStyle(style);cell.setCellValue("");
		//综合
		cell=row.createCell(12);cell.setCellStyle(style);cell.setCellValue(entity.getComprehensiveFinancingCostRatio());
		//新/续
		cell=row.createCell(13);cell.setCellStyle(style);cell.setCellValue(entity.getTypeName());
		//条件及结构
		cell=row.createCell(14);cell.setCellStyle(style);cell.setCellValue(entity.getConditionStructure());
		//难点
		cell=row.createCell(15);cell.setCellStyle(style);cell.setCellValue(entity.getProjectDifficulty());
		//解决方式
		cell=row.createCell(16);cell.setCellStyle(style);cell.setCellValue(entity.getSolution());
		//下账时间
		cell=row.createCell(17);cell.setCellStyle(style);cell.setCellValue(entity.getExpectAccountDate());
		//总规模
		cell=row.createCell(18);cell.setCellStyle(style);cell.setCellValue(entity.getScale());
		//责任人
		cell=row.createCell(19);cell.setCellStyle(style);cell.setCellValue(entity.getPersonLiable());
		//经办人
		cell=row.createCell(20);cell.setCellStyle(style);cell.setCellValue(entity.getOperator());
		
		return cell;
		
	}
	
	@Override
	public boolean isVirtualCompany(String organalId){
		
		boolean flag = false;
		
		int backStatus = reportFinancingProjectProgressDao.isVirtualCompany(organalId);
		
		if(backStatus == 50500 || backStatus ==50502)
			flag = true;
		
		return flag;
	}
}
