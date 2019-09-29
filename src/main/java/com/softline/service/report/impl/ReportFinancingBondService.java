package com.softline.service.report.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.common.DES;
import com.softline.dao.report.IReportFinancingBondDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.financing.ReportFinancingBond;
import com.softline.entity.financing.ReportFinancingBondEnclosure;
import com.softline.entity.financing.ReportFinancingBondLog;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFinancingBondService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.impl.CommonService;
import com.softline.util.MsgPage;

@Service("reportFinancingBondService")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFinancingBondService implements IReportFinancingBondService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Autowired
	@Qualifier("reportFinancingBondDao")
	private IReportFinancingBondDao reportFinancingBondDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	public Double aggregate = 0.0d;
	
	public MsgPage getReportFinancingBondListView(ReportFinancingBond entity, Integer curPageNum, Integer pageSize, Integer status)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportFinancingBondDao.getReportFinancingBondListCount(entity,status);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportFinancingBond> list = reportFinancingBondDao.getReportFinancingBondList(entity, offset, pageSize,status);
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
	public ReportFinancingBond getReportFinancingBond(Integer id)
	{
		return reportFinancingBondDao.getReportFinancingBond( id);
	}
	
	/**
	 * 汇总数据查询
	 */
	@Override
	public List<String> getDataList(ReportFinancingBond entity) {
		List<String> list = reportFinancingBondDao.getDataList(entity);
		return list;
	}
	
	/**
	 * 导入保存
	 */
	/*public Integer saveReport(ReportFinancingBond entity,HhUsers use,ArrayList<String> sList){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getCoreOrg());
		entity.setParentorg(node.getVcOrganId());
		entity.setIsdel(0);
		entity.setCreateDate(df.format(new Date()));
		entity.setCreatePersonId(use.getVcEmployeeId());
		entity.setCreatePersonName(use.getVcName());
		entity.setStatus(Base.examstatus_1);
		//设置表格值
		entity.setDebt(sList.get(1));						
		entity.setResponsibleOrganization(sList.get(2));	
		entity.setCommitmentShallSubject(sList.get(3));		
		entity.setGuaranteeConditions(sList.get(4));		
		entity.setTheUnderwritingInstitution(sList.get(5));	
		entity.setApprovalAgencies(sList.get(6));			
		entity.setScale(sList.get(7));						
		entity.setTerm(sList.get(8));						
		entity.setExpectedInterestRate(Integer.parseInt(sList.get(9)));		
		entity.setAnnualizedUnderwritingRate(Integer.parseInt(sList.get(10)));	
		entity.setCompositeCost(Integer.parseInt(sList.get(11)));				
		entity.setAddOrContinue(sList.get(12));				
		entity.setPrepareACaseReport(sList.get(13));			
		entity.setUnderwritersApproval(sList.get(14));		
		entity.setApprovalAgenciesOpinion(sList.get(15));	
		entity.setAuditReportOpinion(sList.get(16));			
		entity.setLegalOpinion(sList.get(17));				
		entity.setBondRatingOpinion(sList.get(18));			
		entity.setProspectusOpinion(sList.get(19));			
		entity.setIssuanceOfPhase(sList.get(20));			
		entity.setIssuanceOfTime(sList.get(21));				
		entity.setAlreadyAccountAmounts(sList.get(22));		
		entity.setPersonLiable(sList.get(23));				
		entity.setOperator(sList.get(24));					
		CommitResult result=saveReportFinancingBondCheck(entity);
		if(!result.isResult())
		{
			return 1;
		}
		commonDao.saveOrUpdate(entity);
		return 0;
	}*/
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveReportFinancingBondCheck(ReportFinancingBond entity)
	{
		CommitResult result=new CommitResult();
		if(!reportFinancingBondDao.checkCanEdit(entity))
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
	public CommitResult saveReportFinancingBondAndEnclosure(ReportFinancingBond entity,ReportFinancingBond oldEntity,HhUsers use, MultipartFile[] enclosures){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportFinancingBondCheck(entity);
//		if(!result.isResult()) {
//			return result;
//		}
		//创建融资项目进展日志对象
		ReportFinancingBondLog log = new ReportFinancingBondLog();
		//添加日志信息
		log.setIsdel(0);
		log.setUpdateDate(df.format(new Date()));
		log.setUpdatePerson(use.getVcName());
		String topUpdateDetail = "募集到资金";
		String updateDetail = "";
		if(entity.getId()==null) {
			entity.setIsdel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			updateDetail = "创建此融资项目进展数据";
			/*if((Base.examstatus_2).equals(entity.getStatus()))
				updateDetail += topUpdateDetail+"并上报";*/
			log.setUpdateDetail(updateDetail);
		}
		else {
			entity.setIsdel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			log.setUpdateDetail(topUpdateDetail + entity.getAlreadyAccountAmounts()+"亿");
		}
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getCoreOrg());
		entity.setParentorg(node.getVcOrganId());
		commonDao.saveOrUpdate(entity);
		
		//保存日志信息
		log.setFinancingProjectId(entity.getId());
		commonDao.saveOrUpdate(log);
		
		//先获取原来所绑定的附件记录
		List<ReportFinancingBondEnclosure> oldEnclosures = getOldEnclosures(entity.getId());
		//获取原来附件记录的总数
		int j = oldEnclosures.size();
		//遍历现在所有附件
		for(int i = 0;i < enclosures.length;i++) {
			MultipartFile file = enclosures[i];
			// 保存附件
			if (file != null && !file.isEmpty()) {
				//拼接附件路径
				String filePath = DES.REPORT_FINANCING_SHARE_ENCLOSURE_PATH + entity.getId()
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
				ReportFinancingBondEnclosure obj = new ReportFinancingBondEnclosure();
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
	public CommitResult saveReportFinancingBond(ReportFinancingBond entity,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportFinancingBondCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		//创建融资项目进展日志对象
		ReportFinancingBondLog log = new ReportFinancingBondLog();
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
	public CommitResult saveReportFinancingBondExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportFinancingBond entity=reportFinancingBondDao.getReportFinancingBond(entityID);
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
	public CommitResult saveReportFinancingBondExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		ReportFinancingBond entity=getReportFinancingBond(entityID);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveReportFinancingBondExamineCheck(entityID);
		if(!result.isResult())
		{
			return result;
		}
		//创建融资项目进展日志对象
		ReportFinancingBondLog log = new ReportFinancingBondLog();
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

		examineService.saveExamine( examineentityid, Base.examkind_reportFinancingBond, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportFinancingBond(Integer id,HhUsers use)
	{
		
		ReportFinancingBond entity = reportFinancingBondDao.getReportFinancingBond(id);
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			//删除项目记录日志
			reportFinancingBondDao.deleteLog(id);
			
			//删除此项目进展绑定的附件
			//先获取此项目所绑定的附件记录
			List<ReportFinancingBondEnclosure> oldEnclosures = getOldEnclosures(entity.getId());
			//遍历
			for(ReportFinancingBondEnclosure oldEnclosure : oldEnclosures) {
				//拼接附件路径
				String filePath = DES.REPORT_FINANCING_SHARE_ENCLOSURE_PATH + entity.getId()
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
	public List<ReportFinancingBondEnclosure> getOldEnclosures(
			Integer id) {
		return reportFinancingBondDao.getOldEnclosures(id);
	}


	@Override
	public List<ReportFinancingBondLog> getLog(Integer id) {
		return reportFinancingBondDao.getLog(id);
	}
	
	private void getcompanyChildernOrg(List<CompanyTree> CompanyTreeList,List<String> list){
		for(CompanyTree c:CompanyTreeList){
			list.add(c.getId());
			if(c.getChildren().size()>0)
				getcompanyChildernOrg(c.getChildren(),list);
		}
	}
	
	
	@Override
	public HSSFSheet getEntityForTopOrg(ReportFinancingBond entity,List<CompanyTree> CompanyTreeList,HSSFSheet sheet, HSSFCellStyle style, Integer rowNum) {
		//遍历查询并写入数据
		//查询数据
		aggregate=0.0d;
		sheet = this.getEntityForOrg(entity,sheet,style,rowNum);
		
		List<String> listForCompanyTreeOrg = new ArrayList<String>();
		getcompanyChildernOrg(CompanyTreeList,listForCompanyTreeOrg);
		
//		for(CompanyTree c:CompanyTreeList){
//			entity.setCoreOrg(c.getId());
//			sheet = this.getEntityForOrg(entity,sheet,style,rowNum);
//			rowNum = sheet.getLastRowNum()+1;
//	
//		}
		
		StringBuilder sb = new StringBuilder();
		for(String str_temp:listForCompanyTreeOrg){
			sb.append(str_temp).append(",");
		}
		if(sb.length()>1)
			sb.setLength(sb.length()-1);
		
//		ReportFinancingBond entityForTempList = new ReportFinancingBond();
//		entityForTempList.setCoreOrg(sb.toString());
//		sheet = this.getEntityForOrg(entityForTempList,sheet,style,rowNum);
		rowNum = sheet.getLastRowNum()+1;
		
	
		//设置合计
//		rowNum=sheet.getLastRowNum()+1;//获得总行数
//		sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,1,7));
//		sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,9,25));
//		HSSFRow row = sheet.createRow(rowNum);
//		Cell cell = row.createCell(0);
//		
//		for(int i = 0;i<26;i++){
//			cell = row.createCell(i);
//			cell.setCellStyle(style);
//		}
//		
//		cell = row.createCell(1);
//		cell.setCellStyle(style);
//		cell.setCellValue("合计");
//		cell = row.createCell(8);
//		cell.setCellStyle(style);
//		cell.setCellValue(aggregate);
//		cell = row.createCell(9);
//		cell.setCellStyle(style);
		return sheet;
	}
	
	@Override
	public HSSFSheet getEntityForOrg(ReportFinancingBond entity,HSSFSheet sheet,HSSFCellStyle style,Integer rowNum) {
		List<ReportFinancingBond> listEntity = new ArrayList<ReportFinancingBond>();
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getCoreOrg());
		if(null!=node && !"".equals(node.getVcOrganId()))
			entity.setParentorg(node.getVcOrganId());
		listEntity = reportFinancingBondDao.getEntityForOrg(entity);
		if(listEntity.size()<1){
			return sheet;
		}
		sheet = this.setExcelData(sheet,listEntity,style,rowNum++);
		return sheet;
	}
	
	public HSSFSheet setExcelData(HSSFSheet sheet,List<ReportFinancingBond> listEntity,HSSFCellStyle style,Integer rowNum) {
		HSSFRow row = null;
		//小计数据
		Double scaleNum = 0d;
		//设置模板第一行空数据
//		if(listEntity==null){
//			row = sheet.createRow(rowNum);
//			Cell cell = row.createCell(0);
//			cell.setCellStyle(style);
//			for(int i = 1;i<=11;i++){
//				cell = row.createCell(i);
//				cell.setCellStyle(style);
//			}
//			return sheet;
//		}else if(listEntity.size()>0&&listEntity.get(0)!=null){
//			// 创建单元格，设置值
//			row = sheet.createRow(rowNum);
//			Cell cell = row.createCell(0);
//			cell.setCellStyle(style);
//			//参数说明：1：开始行 2：结束行  3：开始列 4：结束列  
//			sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum+listEntity.size(),0,0));
//			cell.setCellValue(listEntity.get(0).getCoreOrgname());
//			scaleNum+=Double.parseDouble(listEntity.get(0).getScale());//小计
//			this.setCell(listEntity.get(0), row, style,1);
//		}
		for(int i = 0;i<listEntity.size();i++){
			row = sheet.createRow(rowNum+i);
			this.setCell(listEntity.get(i), row, style ,(i+1));
			scaleNum+=Integer.parseInt(listEntity.get(i).getScale());
		}
		//小计
		if(listEntity.size()>0){
			sheet.addMergedRegion(new CellRangeAddress(rowNum+listEntity.size(),rowNum+listEntity.size(),1,7));
			sheet.addMergedRegion(new CellRangeAddress(rowNum+listEntity.size(),rowNum+listEntity.size(),9,25));
			row = sheet.createRow(rowNum+listEntity.size());
			Cell cell = null;
			for(int i = 0;i<26;i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			cell = row.createCell(1);
//			cell.setCellValue("小计");
			cell.setCellValue("合计");						
			cell.setCellStyle(style);
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(scaleNum);
			cell.setCellStyle(style);
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
	 * @return
	 */
	
	public Cell setCell(ReportFinancingBond entity,HSSFRow row,HSSFCellStyle style,Integer num){
		Cell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(num);//序号
		
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getParentOrgName());//单位名称
		
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getDebt());//债种
		
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getResponsibleOrganization());//责任单位
		
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getCommitmentShallSubject());//承贷主体
		
		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getGuaranteeConditions());//担保条件
		
		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getTheUnderwritingInstitution());//承销机构
		
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getApprovalAgencies());//审批机构
		
		cell = row.createCell(8);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getScale());//规模(亿)
		
		cell = row.createCell(9);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getTerm());//期限(月)
		
		cell = row.createCell(10);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getExpectedInterestRate());//预期发行利率(%)
		
		cell = row.createCell(11);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getAnnualizedUnderwritingRate());//年化承销费率(%)
		
		cell = row.createCell(12);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getCompositeCost());//综合成本(%)
		
		cell = row.createCell(13);
		cell.setCellStyle(style);
		cell.setCellValue("");//新增或续作
		
		cell = row.createCell(14);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getPrepareACaseReport());//立项报备情况
		
		cell = row.createCell(15);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getUnderwritersApproval());//承销商审批意见
		
		cell = row.createCell(16);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getApprovalAgenciesOpinion());//审批机构审批意见
		
		cell = row.createCell(17);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getAuditReportOpinion());//审计报告意见
		
		cell = row.createCell(18);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getLegalOpinion());//法律意见书
		
		cell = row.createCell(19);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getBondRatingOpinion());//债券评级意见
		
		cell = row.createCell(20);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getProspectusOpinion());//募集说明书意见
		
		cell = row.createCell(21);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getIssuanceOfPhase());//发债阶段
		
		cell = row.createCell(22);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getIssuanceOfTime());//发债时间
		
		cell = row.createCell(23);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getAlreadyAccountAmounts());//已下账(亿)
		
		cell = row.createCell(24);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getPersonLiable());//责任人
		
		cell = row.createCell(25);
		cell.setCellStyle(style);
		cell.setCellValue(entity.getOperator());//经办人
		
		return cell;
	}

	/**
	 * 计算虚拟公司的汇总值
	 * @param bean
	 * @return
	 */
	public ReportFinancingBond groupSumDataByEntityCompany(ReportFinancingBond bean,String allCompanyNames){
		
		if(null==bean)
			return new ReportFinancingBond();
		
		String nNodeId = bean.getCoreOrg();
		double sumAlreadyAccountAmounts = 0;
		double sumScale = 0;	
		HhOrganInfoTreeRelation companyInfoBean = null;
		List<ReportFinancingBond> listTempBean = null;
		
		//首先根据coreOrg 判断公司是否为 实体公司 
		if(null != nNodeId){								
			//虚拟公司汇总该公司 下所有实体公司的汇总值		
			listTempBean = reportFinancingBondDao.getReportFinancingBondByCompanyName(allCompanyNames);			
			for(ReportFinancingBond tempBean:listTempBean){		
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
		
		// 清空
		if(null!=listTempBean)
			listTempBean.clear();
		
		return bean;
	}
	
	public int sumDataForSonCompanyData(ReportFinancingBond beanIn,MsgPage msgPageOut,ReportFinancingBond sumBeanOut){
		
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
		List<ReportFinancingBond> list=reportFinancingBondDao.sumDataForSunCompanys(beanIn);	
        DecimalFormat df = new DecimalFormat("#.00");
		for(Object obj : list) {
			Object[] item = (Object[])obj;
			sumBeanOut.setAlreadyAccountAmounts(item[0]==null?"0":df.format(item[0]).toString());
			sumBeanOut.setScale(item[1]==null?"0":df.format(item[1]).toString());
		}					
		//返回显示数据
		Integer totalPage = msgPageOut.countTotalPage(reportFinancingBondDao.sumBeansForSunCompanys(beanIn),
				 									  msgPageOut.getPageSize());
		msgPageOut.setTotalPage(totalPage);		
		msgPageOut.setTotalRecords(reportFinancingBondDao.sumBeansForSunCompanys(beanIn));
		msgPageOut.setList(reportFinancingBondDao.sumBeansForSunCompanys(beanIn,
																					msgPageOut.countOffset(msgPageOut.getPageNum(),msgPageOut.getPageSize()),
																					msgPageOut.getPageSize()));   
		return 0;	
	}
	
	@Override
	public boolean isVirtualCompany(String organalId){
		
		boolean flag = false;
		
		int backStatus = reportFinancingBondDao.isVirtualCompany(organalId);
		
		if(backStatus == 50500 || backStatus ==50502)
			flag = true;
		
		return flag;
	}
}
