package com.softline.service.bimr.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opendata.api.ODPRequest;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.dao.bimr.IComplianceDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhFile;
import com.softline.entity.HhInterfaceLog;
import com.softline.entity.HhUsers;
import com.softline.entity.HhUsersmessageinfo;
import com.softline.entity.ReportInspectProject;

import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrComplianceBJInfo;
import com.softline.entity.bimr.BimrCompliancePerson;
import com.softline.entity.bimr.BimrComplianceProgress;
import com.softline.entity.bimr.BimrCompliancePrompt;
import com.softline.entity.bimr.BimrComplianceSituation;
import com.softline.entity.bimr.BimrComplianceSituationPerson;
import com.softline.entity.bimr.BimrComplianceSuggest;
import com.softline.entity.bimr.BimrDuty;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.bimr.IComplianceService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;

import com.softline.util.Coder;

import com.softline.util.MsgPage;

/**
 * 
 * @author tch
 *
 */
@Service("complianceService")
public class ComplianceService implements IComplianceService{

	@Autowired
	@Qualifier("commonService")
	private ICommonService commonService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Autowired
	@Qualifier("complianceDao")
	private IComplianceDao complianceDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	 private Logger logger = Logger.getLogger(ComplianceService.class);
	
	public MsgPage getComplianceDaoListView(BimrCompliance entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = complianceDao.getComplianceListCount(entity);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrCompliance> list = complianceDao.getComplianceList(entity, offset, pageSize);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}
	
	public BimrCompliance getComplianceById(Integer id){
		BimrCompliance entity=complianceDao.getComplianceById(id);
		/*if(entity!=null)
			entity.setFileOne(commonService.getFileByEnIdAndType(entity.getId(), Base.inspect_project));*/
		
		return entity;
	}
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveCompliance(BimrCompliance entity,List<BimrCompliancePerson> personList,HhUsers use, MultipartFile file1,String type,MultipartFile indictmentFile)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveComplianceCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity.getId()==null)
		{
			entity.setIsDel(0);
			entity.setStatus(81010);//在办中
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
		}
		else
		{
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		if(("manage").equals(type)){
			entity.setProjectCode(getComplianceNum(entity));
		}
		commonDao.saveOrUpdate(entity);
		
		if(type==null || !type.equals("manage")){
			List<BimrCompliancePerson> oldPersonList=complianceDao.getCompliancePerson(entity.getId());
			for(BimrCompliancePerson l:oldPersonList){
				commonDao.delete(l);//物理删除
			}
			
			for(BimrCompliancePerson l:personList){
				l.setComplianceId(entity.getId());
				l.setIsDel(0);
				commonDao.saveOrUpdate(l);//新增
			}
		}
		
		//保存附件
		/*if (file1!= null && !file1.isEmpty()) {
			if (entity.getFileOne() != null) {
				String uuid = entity.getFileOne().getFileUuid();
				Common.deleteFile(DES.report_path, uuid);
				commonDao.delete(entity.getFileOne());
			}
			HhFile f = commonService.saveFile(file1, entity.getId(),
					Base.inspect_project,DES.report_path);
		}*/
		if (indictmentFile!= null && !indictmentFile.isEmpty()) {
			if (entity.getIndictment() != null) {
				String uuid = entity.getIndictment();
				Common.deleteFile(DES.BIMR_COMLPIANCE_REPORT, uuid);
				HhFile file = commonDao.getFileByUuid(uuid);
				if(null!=file)
				commonDao.delete(file);
			}
			HhFile f_1 = commonService.saveFile(indictmentFile, entity.getId(),Base.BIMR_COMLPIANCE_REPORT,DES.BIMR_COMLPIANCE_REPORT);
			entity.setIndictment(f_1.getFileUuid());
		}
		
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	private String getComplianceNum(BimrCompliance entity){
		String code="";
		code = code+"HNALO-";
		String year = entity.getStartTime().substring(2, 4);
		String month = entity.getStartTime().substring(5, 7);
		String day = entity.getStartTime().substring(8);
		String organ = entity.getVcCompOrganID();
		if(organ.contains("0-1-855579-855580-")){
			code += "HNALOHQ-"+year;
		}
		else if(organ.contains("0-1-855579-856081-")){
			code += "HNALODU-"+year;
		}
		else if(organ.contains("0-1-855579-856150-")){
			code += "HNAINF-"+year;
		}
		else if(organ.contains("0-1-855579-856151-")){
			code += "CCOOP-"+year;
		}
		else if(organ.contains("0-1-855579-856152-")){
			code += "HKICIM-"+year;
		}
		else if(organ.contains("0-1-855579-856153-")){
			code += "HNAINV-"+year;
		}
		else if(organ.contains("0-1-855579-856154-")){
			code += "CWT-"+year;
		}
		else if(organ.contains("0-1-855579-856155-")){
			code += "HYGF-"+year;
		}
		else if(organ.contains("0-1-855579-856156-")){
			code += "HNAINFRA-"+year;
		}
		else if(organ.contains("0-1-855579-856157-")){
			code += "SKL-"+year;
		}
		else{
			code = "HNALO-"+year;
		}
		
		code=code+""+month+""+day+"-";
		
		if(entity.getInvestigationType()==81000){
			code=code+"T";
		}else {
			code=code+"R";
		}
		List codelist = complianceDao.getProjectCodelist(code,entity.getId());
		if(codelist.size() > 0){
			BimrCompliance biCompliance = (BimrCompliance)codelist.get(0);
			String n = biCompliance.getProjectCode();
			if("T".equals(code.substring(code.length()-1, code.length()))){
				n = n.substring(n.indexOf("T")+2,n.length());
			}else {
				n = n.substring(n.indexOf("R")+2,n.length());
			}
			
			int seq = Integer.parseInt(n);
			seq = seq + 1;
			if(seq <10){
				n = "00" + seq;
			}else if(seq >=10 && seq < 100){
				n = "0" + seq;
			}else{
				n = seq + "";
			}
			code = code + "-"+n ;
		}
		else{
			code = code + "-001";
		}
		
		return code;
		
	}
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveComplianceCheck(BimrCompliance entity)
	{
		CommitResult result=new CommitResult();
		/*if(!purchaseDao.savePurchaseRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该季度已经有数据，不能再添加");
			return result;
		}
		if(!purchaseDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}*/
		result.setResult(true);
		return result;
	}
	
	
	
	
	@Override
	public CommitResult deleteCompliance(BimrCompliance entity, HhUsers use) {
		// TODO Auto-generated method stub
		CommitResult result=new CommitResult();
		/*result=theTimeDocumentCheck(entity);
		if(!result.isResult())
		{
			return result;
		}*/
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsDel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	public List<BimrCompliancePerson> getCompliancePerson(Integer complianceId){
		return complianceDao.getCompliancePerson(complianceId);
	}

	public List<BimrComplianceSituation> getComplianceSituation(Integer complianceId,Integer dataType){
		return complianceDao.getComplianceSituation(complianceId, dataType);
	}

	@Override
	public MsgPage getQueryListView(BimrCompliance entity, Integer pageNum, Integer pageSize) {
		
		Integer[] defaultStatuses =  new Integer[]{81011, 81012, 81013, 81014, 81015};
		return getListViewByStatuses(entity, defaultStatuses, pageNum, pageSize);
	}
	
	private MsgPage getListViewByStatuses(BimrCompliance entity, 
			Integer[] defaultStatuses, Integer pageNum, Integer pageSize){
		
		
		Integer totalCount = complianceDao.getListCountByStatuses(entity, defaultStatuses);
		int offset = new MsgPage().countOffset(pageNum, pageSize);
		List<BimrCompliance> data = complianceDao.getListByStatuses(entity, defaultStatuses, offset, pageSize);
		return buildMsgPage(pageNum, pageSize, totalCount, data);
		
		
	}
	
	private MsgPage buildMsgPage(Integer pageNum, Integer pageSize, Integer totalCount, List<?> data){
		MsgPage msgPage = new MsgPage();
		msgPage.setPageNum(pageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalCount);
		msgPage.setTotalPage(msgPage.countTotalPage(totalCount, pageSize));
		msgPage.setList(data);
		return msgPage;
	}

	@Override
	public MsgPage getAssignListView(BimrCompliance entity, Integer pageNum,Integer pageSize) {
		
		Integer[] defaultStatuses =  new Integer[]{81014, 81015};
		return getListViewByStatuses(entity, defaultStatuses, pageNum, pageSize);
	}

	@Override
	public MsgPage getCorrectListView(BimrCompliance entity, Integer pageNum,Integer pageSize) {
		
		Integer[] defaultStatuses =  new Integer[]{81014, 81015,81016,81017};
		return getListViewByStatuses(entity, defaultStatuses, pageNum, pageSize);
	} 
	
	
	@Override
	public MsgPage getCorrectListExamineView(BimrCompliance entity, Integer pageNum,Integer pageSize) {
		
		Integer[] defaultStatuses =  new Integer[]{81014, 81015,81016,81017};
		return getListViewByStatuses(entity, defaultStatuses, pageNum, pageSize);
	} 
	
	@Override
	public MsgPage getReportBJListView(BimrCompliance entity,Integer pageNum, Integer pageSize) {

		Integer[] defaultStatuses =  new Integer[]{81013, 81014, 81015};
		return getListViewByStatuses(entity, defaultStatuses, pageNum, pageSize);
	}

	@Override
	public CommitResult saveReportBJ(BimrComplianceBJInfo entity, HhUsers user, MultipartFile multFile, String package_path) {
		CommitResult result = new CommitResult();
		BimrCompliance compliance = complianceDao.getComplianceById(entity.getComplianceId());
		
		if(compliance == null){
			result.setResult(false);
			result.setExceptionStr("合规调查不存在");
			return result;
		}
		
		if(compliance.getStatus().intValue() != 81013){
			result.setResult(false);
			result.setExceptionStr("合规调查还未办结");
			return result;
		}
		
		if(entity.getId() == null){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setCreateDate(dateFormat.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			entity.setIsDel(0);
			commonService.saveOrUpdate(entity);
		}else{
			BimrComplianceBJInfo o = (BimrComplianceBJInfo)commonService.findById(BimrComplianceBJInfo.class, entity.getId());
			o.setOfficeId(entity.getOfficeId());
			o.setName(entity.getName());
			o.setSubmitPersonName(entity.getAuditPersonName());
			o.setSubmitTime(entity.getSubmitTime());
			o.setAuditPersonName(entity.getAuditPersonName());
			o.setAuditContent(entity.getAuditContent());
			commonService.saveOrUpdate(o);
			entity = o;
		}
		
		compliance.setStatus(hasChanage(entity.getComplianceId())?81014: 81015);
		commonService.saveOrUpdate(entity);
		
		
		if (!multFile.isEmpty()) {
			commonService.saveFile(multFile, entity.getId(), Base.COMPLIANCE_REPORTFINISH, package_path);
		}
		result.setResult(true);
		result.setEntity(entity);
		
		return result;
	}
	
	public CommitResult saveComplianceSituation(BimrComplianceSituation entity,List<BimrComplianceSituationPerson> personList,HhUsers use, MultipartFile file1)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveComplianceSituationCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity.getId()==null)
		{
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
		}
		else
		{
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		commonDao.saveOrUpdate(entity);
		
		List<BimrComplianceSituationPerson> oldPersonList=complianceDao.getComplianceSituationPerson(entity.getId());
		for(BimrComplianceSituationPerson l:oldPersonList){
			commonDao.delete(l);//物理删除
		}
		
		for(BimrComplianceSituationPerson l:personList){
			l.setSituationId(entity.getId());
			commonDao.saveOrUpdate(l);//新增
		}
		
		//保存附件
		/*if (file1!= null && !file1.isEmpty()) {
			if (entity.getFileOne() != null) {
				String uuid = entity.getFileOne().getFileUuid();
				Common.deleteFile(DES.report_path, uuid);
				commonDao.delete(entity.getFileOne());
			}
			HhFile f = commonService.saveFile(file1, entity.getId(),
					Base.inspect_project,DES.report_path);
		}*/
		
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	private CommitResult saveComplianceSituationCheck(BimrComplianceSituation entity)
	{
		CommitResult result=new CommitResult();
		/*if(!purchaseDao.savePurchaseRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该季度已经有数据，不能再添加");
			return result;
		}
		if(!purchaseDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}*/
		result.setResult(true);
		return result;
	}
	
	public BimrComplianceSituation getComplianceSituationById(Integer id){
		return complianceDao.getComplianceSituationById(id);
	}
	
	public List<BimrComplianceSituationPerson> getComplianceSituationPerson(Integer situationId){
		return complianceDao.getComplianceSituationPerson(situationId);
	}
	
	private boolean hasChanage(Integer complianceId){
		boolean has = false;
		List<BimrComplianceSuggest> suggests = complianceDao.getComplianceSuggest(complianceId);
		for(BimrComplianceSuggest t: suggests){
			has = has | (t.getIsChange() == 1);
		}
		return has;
	}
	
	public BimrCompliancePrompt getCompliancePromptById(Integer id){
		return complianceDao.getCompliancePromptById(id);
	}
	
	public List<BimrCompliancePrompt> getCompliancePrompt(Integer complianceId){
		return complianceDao.getCompliancePrompt(complianceId);
	}
	
	public CommitResult saveCompliancePrompt(BimrCompliancePrompt entity,HhUsers use, MultipartFile file1)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveCompliancePromptCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity.getId()==null)
		{
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
		}
		else
		{
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		commonDao.saveOrUpdate(entity);
		
		//保存附件
		/*if (file1!= null && !file1.isEmpty()) {
			if (entity.getFileOne() != null) {
				String uuid = entity.getFileOne().getFileUuid();
				Common.deleteFile(DES.report_path, uuid);
				commonDao.delete(entity.getFileOne());
			}
			HhFile f = commonService.saveFile(file1, entity.getId(),
					Base.inspect_project,DES.report_path);
		}*/
		
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	private CommitResult saveCompliancePromptCheck(BimrCompliancePrompt entity)
	{
		CommitResult result=new CommitResult();
		/*if(!purchaseDao.savePurchaseRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该季度已经有数据，不能再添加");
			return result;
		}
		if(!purchaseDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}*/
		result.setResult(true);
		return result;
	}
	
	public List<BimrComplianceSuggest> getComplianceSuggest(Integer complianceId){
		return complianceDao.getComplianceSuggest(complianceId);             
	}
	
	public BimrComplianceSuggest getComplianceSuggestById(Integer id) {
		return complianceDao.getComplianceSuggestById(id);            
	}
	
	public CommitResult saveComplianceSuggest(BimrComplianceSuggest entity,HhUsers use, MultipartFile file1)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveComplianceSuggestCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity.getId()==null)
		{
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
		}
		else
		{
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		commonDao.saveOrUpdate(entity);
		
		//保存附件
		/*if (file1!= null && !file1.isEmpty()) {
			if (entity.getFileOne() != null) {
				String uuid = entity.getFileOne().getFileUuid();
				Common.deleteFile(DES.report_path, uuid);
				commonDao.delete(entity.getFileOne());
			}
			HhFile f = commonService.saveFile(file1, entity.getId(),
					Base.inspect_project,DES.report_path);
		}*/
		
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	private CommitResult saveComplianceSuggestCheck(BimrComplianceSuggest entity)
	{
		CommitResult result=new CommitResult();
		/*if(!purchaseDao.savePurchaseRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该季度已经有数据，不能再添加");
			return result;
		}
		if(!purchaseDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}*/
		result.setResult(true);
		return result;
	}
	
	public List<BimrComplianceProgress> getComplianceProgress(Integer complianceId){
		return complianceDao.getComplianceProgress(complianceId);
	}
	
	public BimrComplianceProgress getComplianceProgressById(Integer id){
		BimrComplianceProgress entity= complianceDao.getComplianceProgressById(id);
		
		if(entity!=null)
			entity.setFileOne(commonService.getFileByEnIdAndType(entity.getId(), Base.compliance_progress));
		
		return entity;
	}
	
	public CommitResult saveComplianceProgress(BimrComplianceProgress entity,HhUsers use, MultipartFile file1)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveComplianceProgressCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity.getId()==null)
		{
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
		}
		else
		{
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		commonDao.saveOrUpdate(entity);
		
		//保存附件
		if (file1!= null && !file1.isEmpty()) {
			if (entity.getFileOne() != null) {
				String uuid = entity.getFileOne().getFileUuid();
				Common.deleteFile(DES.TRAIN_PATH, uuid);
				commonDao.delete(entity.getFileOne());
			}
			HhFile f = commonService.saveFile(file1, entity.getId(),
					Base.compliance_progress,DES.TRAIN_PATH);
		}
		
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	private CommitResult saveComplianceProgressCheck(BimrComplianceProgress entity)
	{
		CommitResult result=new CommitResult();
		/*if(!purchaseDao.savePurchaseRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该季度已经有数据，不能再添加");
			return result;
		}
		if(!purchaseDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}*/
		result.setResult(true);
		return result;
	}
	
	@Override
	public BimrComplianceBJInfo getBJInfoByComplianceId(Integer complianceId) {
		return complianceDao.getBJInfoByComplianceId(complianceId);
	}

	@Override
	public BimrComplianceSuggest getSuggestById(Integer id) {
		return (BimrComplianceSuggest)commonService.findById(BimrComplianceSuggest.class, id);
	}

	@Override
	public List<BimrComplianceSuggest> getSuggestListByComplianceId(Integer complianceId) {
		return complianceDao.getSuggestListByComplianceId(complianceId);
	}

	@Override
	public List<BimrComplianceSituationPerson> getSituationPersonListByComplianceId(Integer complianceId) {
		return complianceDao.getSituationPersonListByByComplianceId(complianceId);
	}

	@Override
	public CommitResult saveCorrectProblem(BimrComplianceSuggest entity, HhUsers user) {
		BimrComplianceSuggest o = getSuggestById(entity.getId());
		BimrCompliance compliance = complianceDao.getComplianceById(o.getComplianceId());
		CommitResult result = new CommitResult();
		if(compliance.getStatus().intValue() != 81014 && compliance.getStatus().intValue() != 81017){
			result.setResult(false);
			result.setExceptionStr("不是整改状态");
			return result;
		}
		
		o.setFollowPerson(entity.getFollowPerson());
		o.setAbutmentPerson(entity.getAbutmentPerson());
		o.setAccountabilityPerson(entity.getAccountabilityPerson());
		o.setAccountabilityDep(entity.getAccountabilityDep());
		o.setChangeLastTime(entity.getChangeLastTime());
		o.setChangeStatus(entity.getChangeStatus());
		o.setChangeProgress(entity.getChangeProgress());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		o.setLastModifyDate(dateFormat.format(new Date()));
		o.setLastModifyPersonId(user.getVcEmployeeId());
		o.setLastModifyPersonName(user.getVcName());
		o.setAccountabilityDepId(entity.getAccountabilityDepId());
		commonService.saveOrUpdate(o);
		
		result.setResult(true);
		result.setEntity(o);
		
		return result;
	}

	@Override
	public BimrComplianceSituationPerson getSituationPerson(Integer id) {
		return (BimrComplianceSituationPerson)commonService.findById(BimrComplianceSituationPerson.class, id);
	}

	@Override
	public CommitResult saveSituationPerson(BimrComplianceSituationPerson entity, HhUsers user) {
		CommitResult result = new CommitResult();
		
		BimrComplianceSituationPerson o = getSituationPerson(entity.getId());
		o.setAccountabilityItem(entity.getAccountabilityItem());
		o.setSuggestInfo(entity.getSuggestInfo());
		o.setSubmitOfficeId(entity.getSubmitOfficeId());
		o.setOfficeId(entity.getOfficeId());
		
		commonService.saveOrUpdate(o);
		
		result.setResult(true);
		result.setEntity(o);
		return result;
	}

	@Override
	public CommitResult saveCorrectArchive(Integer id, HhUsers user) {
		CommitResult result = new CommitResult();
		BimrCompliance t = getComplianceById(id);
		
		
		t.setStatus(81015);
		DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		t.setLastModifyDate(dateFormat.format(new Date()));
		t.setLastModifyPersonId(user.getVcEmployeeId());
		t.setLastModifyPersonName(user.getVcName());
		commonService.saveOrUpdate(t);
		
		result.setResult(true);
		result.setEntity(t);
		
		return result;
	}
	@Override
	public CommitResult saveErrorArchive(Integer id, HhUsers user) {
		CommitResult result = new CommitResult();
		BimrCompliance t = getComplianceById(id);
		
		
		t.setStatus(81017);
		DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		t.setLastModifyDate(dateFormat.format(new Date()));
		t.setLastModifyPersonId(user.getVcEmployeeId());
		t.setLastModifyPersonName(user.getVcName());
		commonService.saveOrUpdate(t);
		
		result.setResult(true);
		result.setEntity(t);
		
		return result;
	}
	@Override
	public CommitResult saveCorrectStatus(Integer id, HhUsers user) {
		CommitResult result = new CommitResult();
		BimrCompliance t = getComplianceById(id);
		t.setStatus(81016);
		DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		t.setLastModifyDate(dateFormat.format(new Date()));
		t.setLastModifyPersonId(user.getVcEmployeeId());
		t.setLastModifyPersonName(user.getVcName());
		commonService.saveOrUpdate(t);
		
		result.setResult(true);
		result.setEntity(t);
		
		return result;
	}
	@Override
	public CommitResult updateStatus(BimrCompliance entity, HhUsers use) {
		// TODO Auto-generated method stub
		CommitResult result=new CommitResult();
		/*result=theTimeDocumentCheck(entity);
		if(!result.isResult())
		{
			return result;
		}*/
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//entity.setStatus(entity);//办结申请中
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
	public CommitResult saveAssignFollowPerson(Integer id, String followPerson, HhUsers user) {
		BimrComplianceSuggest t = (BimrComplianceSuggest)commonService.findById(BimrComplianceSuggest.class, id);
		
		t.setFollowPerson(followPerson);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		t.setLastModifyDate(dateFormat.format(new Date()));
		t.setLastModifyPersonId(user.getVcEmployeeId());
		t.setLastModifyPersonName(user.getVcName());
		commonService.saveOrUpdate(t);
		
		CommitResult result = new CommitResult();
		result.setResult(true);
		result.setEntity(t);
		
		return result;
	}

	@Override
//	合规调查基本信息  查询
	public List<BimrCompliance> getcompliance(BimrCompliance compliance1) {
		// TODO Auto-generated method stub
		List<BimrCompliance> list=complianceDao.getcompliance(compliance1);
		return list;
	}

	@Override
	public boolean getSuggestAndEmail() {
		// TODO Auto-generated method stub
		List<Object[]> list = complianceDao.getgetSuggestAndEmail();
		boolean b=true;
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				if(!isTrueEmail(String.valueOf(list.get(i)[2]))){
					continue;
				}
				boolean b1=sendEmail(String.valueOf(list.get(i)[2]), String.valueOf(list.get(i)[0]), String.valueOf(list.get(i)[1]),String.valueOf(list.get(i)[3]));
				if(!b1){
					logger.error(String.valueOf(list.get(i)[0])+"-"+String.valueOf(list.get(i)[1])+"-"+String.valueOf(list.get(i)[2]));
					b=b1;
				}
			}
		}
		return b;
	}
	
	public boolean sendEmail(String slctPersons,String projectName,String suggestion,String lasttime) {
		//测试
		//slctPersons="mingm.yang@hnair.com";
		//slctPersons="zhuzhou@softline.sh.cn";
			//TODO 找到接收人和抄送人的邮箱，填充邮件主题和邮件内容
			
			//String ccEmail = "";
			/*if(Common.notEmpty(sBSlctPersons)){
				 ccEmail = getToEmail(sBSlctPersons);
			}*/
		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				HhInterfaceLog hhInterfaceLog = new HhInterfaceLog();
				hhInterfaceLog.setCallPersonName("系统管理员");
				hhInterfaceLog.setCallVcEmployeeId("9999999901");
				hhInterfaceLog.setCallTime(df2.format(new Date()));
				hhInterfaceLog.setIntefaceAlias("合规管理整改工作提醒");
				hhInterfaceLog.setIntefaceName(Base.EMAIL_METHOD);
				hhInterfaceLog.setRemark("收件人：" +slctPersons);
				//拼装附件mapList
				List<Object> mapList = new ArrayList<Object>();
				//String filePath = TaskInstructionService.class.getClassLoader().getResource("viewfile.jpg").getPath();
				Map<String,Object> mapAttachment = new HashMap<String,Object>();
				Map<String,String> map = new HashMap<String,String>();
				//map.put("FileName", "QRCode.jpg");
				//map.put("Content", Coder.fileCode(filePath));
				//mapAttachment.put("Attachment", map);
				//mapList.add(mapAttachment);
				String mailBody="<body lang=ZH-CN link=\"#0563C1\" vlink=\"#954F72\" style='text-justify-trim:punctuation;' ><h3 style='text-align:center;font-size:14.0pt;'>合规调查整改提醒</h3><div class=WordSection1 ><p class=MsoNormal><span style='font-size:14.0pt;font-family:\"微软雅黑\",sans-serif'>您好！<br></br>&nbsp; &nbsp;&nbsp; '"+projectName+"'已进入整改阶段，请根据提出的合规建议情况，于"+lasttime+"前跟踪整改单位，反馈整改结果。<br><span lang=EN-US><br></span>&nbsp; &nbsp;&nbsp; 该邮件由海航物流<span lang=EN-US>BIMR</span>系统自动发送，请勿回复邮件。为保证信息安全，请勿转发该邮件。<br></br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统涉密，不对外网开放，请在内网操作。<span lang=EN-US><o:p></o:p></span></span><br><b></p></div></body>";
				byte[] body;
				body = mailBody.getBytes("UTF-8");
				if(!Common.notEmpty(slctPersons)){
					logger.error("接收人邮件为空！");
					return false;
				}
				String res = new ODPRequest(Base.EMAIL_URL, Base.EMAIL_APPSECRET)
				.addTextSysPara("Method", Base.EMAIL_METHOD)
				.addTextSysPara("AccessToken", Base.EMAIL_ACCESSTOKEN)
				.addTextSysPara("Format", "json")
				//应用参数
				.addTextAppPara("From", Base.EMAIL_ACCOUNT)//邮件服务发件人参数
				.addTextAppPara("To", slctPersons)//邮件服务收件人参数
				//.addTextAppPara("Cc", ccEmail)//抄送人
				.addTextAppPara("UserName", Base.EMAIL_USERNAME)//发件人的内网账号
				.addTextAppPara("UserPwd", Base.EMAIL_PWD)//发件人的密码，需要base64编码
				.addTextAppPara("Subject", "海航物流BIMR系统工作提醒")//邮件标题
				.addTextAppPara("Body", Coder.getBASE64(body))//邮件内容参数，需要base64编码
	      		//.addTextAppPara("Attachments", mapList)//附件
				.post();
				
//				JSON.parse(res);
//				System.out.println(res);
				logger.info("发送邮件人" + slctPersons);
				logger.info("发送邮件结果" + res);
				JSONObject o= JSON.parseObject(res);
				Object cc = o.getJSONObject("MsgResponse").get("ResponseInfo");
				ResponseInfo responseInfo = JSON.parseObject(cc.toString(), ResponseInfo.class);
				hhInterfaceLog.setBackResult(responseInfo.getResult());
				hhInterfaceLog.setBackResultInfo(cc.toString());
				commonDao.saveOrUpdate(hhInterfaceLog);
				complianceDao.saveEmailInfo(df.format(new Date()), Base.EMAIL_ACCOUNT, slctPersons, 1, 0, "海航物流BIMR系统工作提醒", res);
				
				return true;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("发送失败！");
				complianceDao.saveEmailInfo(df.format(new Date()), Base.EMAIL_ACCOUNT, slctPersons, 0, 0, "海航物流BIMR系统工作提醒", null);

				return false;
			}
		
	}

	
	/**
	 * 判断邮箱是否符合
	 * @param email  邮箱地址
	 * @return {Boolean} 是否符合邮箱地址格式
	 */
	private Boolean isTrueEmail(String email){
		Boolean result = false;
		String[] b = email.split("@");
		if(Common.notEmpty(b[0])){
			result = true;
		}
		return result;
	}

//	调用导出条件的方法
	@Override
	public List<Object[]> getQueryExport(BimrCompliance entity1,
			BimrCompliancePerson entity2, BimrComplianceSituation entity3,
			BimrComplianceSituationPerson entity4, BimrComplianceSuggest entity5,
			BimrComplianceBJInfo entity6, String dataAuthority,
			String vcEmployeeId) {
		List<Object[]> list=complianceDao.getQueryExportDC(entity1, entity2, entity3, entity4, entity5, entity6, dataAuthority, vcEmployeeId);
		return list;
	}

	
//	导出
	@Override
	public XSSFWorkbook getQueryExportWorkbook1(List<Object[]> list1) {

		
		XSSFWorkbook workBook = new XSSFWorkbook();
		 
		 XSSFCell cell = null;
		 XSSFSheet sheet = workBook.createSheet("1221");
		 XSSFRow row1=sheet.createRow((int)0);
//		 第一行
		 CellStyle style=workBook.createCellStyle();
//		 sheet.addMergedRegion(new CellRangeAddress(3,4,0,0));
//		 设置合并单元格的地方
//		 XSSFFont font = workBook.createFont();
//		 font.setBold(true);
		
//		 XSSFRow row=sheet.createRow((int)1);
		 XSSFFont font = workBook.createFont();
//		 设置字体
		 font.setBold(true);
		 cell = row1.createCell((short) 0);
		 cell.setCellValue("投诉调查数据");
		 font.setFontHeightInPoints((short) 10);
		 style.setFont(font);
		 style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中  
		 style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直  
		 cell.setCellStyle(style);

//		 1  6  23   31    36
		 cell = row1.createCell((short) 5);
		 cell.setCellValue("受理启办阶段");
		 cell.setCellStyle(style);
		 
		 cell = row1.createCell((short) 22);
		 cell.setCellValue("调查处理阶段");
		 cell.setCellStyle(style);
		 
		 cell = row1.createCell((short) 30);
		 cell.setCellValue("报告办结阶段");
		 cell.setCellStyle(style);
		 
		 cell = row1.createCell((short) 35);
		 cell.setCellValue("问责整改阶段");
		 cell.setCellStyle(style);
			 empArch66(list1,sheet,style, 1,font);

		return workBook;
	}
//"判决/调解金额(人民币万元)","已执行款项(人民币万元)","避免/挽回经济损失(人民币万元)","案件编号","案件归属单位"
	public void empArch66(List<Object[]> list,XSSFSheet sheet, CellStyle style,int type,XSSFFont font) {
		
		XSSFCell cell = null;
		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		 style.setFont(font);
//		 审计类型  报告名称  
		 XSSFRow row=sheet.createRow((int)1);
		String [] header={"调查类型","调查承办企业","调查涉及企业名称","处分问责人数","调查项目编号",
				"序号","是否是集团内部投诉人 ","举报投诉、专项调查项目名称",
				"调查承办部门","待调查事项","员工编号 ","调查涉及人员 ","现任职务","调查来源 ",
				" 调查涉及经营管理事项","是否匿名","投诉收到时间","调查交办领导","调查负责人",
				"调查启动时间","调查结束时间","举报投诉问题反映线索/待调查具体事项","问题线索是否属实",
				"调查确认情况","是否需要问责","建议人员问责情况","其他违规违纪事项描述 ",
				"调查确认情况","合规管理建议","调查报告公文编号","报告名称","呈报人","报告呈报时间	",
				"报告最终审批","最终审批人批示意见","是否涉及后续整改","建议问责落实情况",
				"处分问责呈报公文编号","处分问责办文编号","整改责任人","整改完成预计时限","整改工作进展","整改完成状态"};
		 
		 
		
		 for (int i = 0; i < header.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(header[i]);
				cell.setCellStyle(style);

		}
		 
		 
		 
		 int i =0;
//		   
			for(Object obj : list) {
				Object[] item = (Object[])obj;
//				
				int k=0;
				row=sheet.createRow((int) i +2);
				
				int investigationType=0;
				if (item[0]!=null) {
					investigationType=Integer.parseInt(String.valueOf(item[0]));
				}
//				int investigationType=Integer.parseInt(String.valueOf(item[0]));
				String investigationName=(String) item[1];
				String compName=(String) item[2];
				String projectCode=(String) item[3];
				int id=0;
				if (item[4]!=null) {
					id=Integer.parseInt(String.valueOf(item[4]));
				}
				int isInside=0;
				if (item[5]!=null) {
					isInside=Integer.parseInt(String.valueOf(item[5]));
				}
				String projectName=(String) item[6];
				String investigationDep=(String) item[7];
				String problem=(String) item[8];
				String employeeNumber=(String) item[9];
				String employeeName=(String) item[10];
				String employeePostion=(String) item[11];
				int investigationFrom=0;
				if (item[12]!=null) {
					 investigationFrom=Integer.parseInt(String.valueOf(item[12]));
				}
				
				String itemName=(String) item[13];
				int isAnonymous=0;
				if (item[14]!=null) {
					isAnonymous=Integer.parseInt(String.valueOf(item[14]));
				}
				String accusationTime=(String) item[15];
				String assignLeader=(String) item[16];
				String responsiblePerson=(String) item[17];
				String startTime=(String) item[18];
				String endTime=(String) item[19];
				int dataType=0;
				if (item[20]!=null) {
						dataType=Integer.parseInt(String.valueOf(item[20]));
				}
				
//				int isTruth=Integer.parseInt(String.valueOf(item[21]));
				String truth=(String) item[22];
				int isAccountability=0;
				if (item[23]!=null) {
					isAccountability=Integer.parseInt(String.valueOf(item[23]));
				}
				
				String suggestInfo=(String) item[24];
				String suggest=(String) item[25];
				String officeId=(String) item[26];
				String name=(String) item[27];
				String submitPersonName=(String) item[28];
				String submitTime=(String) item[29];
				String auditPersonName=(String) item[30];
				String auditContent=(String) item[31];
				int isChange=0;
				if (item[32]!=null) {
					 isChange=Integer.parseInt(String.valueOf(item[32]));
				}
				
				String bumfNum=(String) item[33];
				String articleNum=(String) item[34];
				String accountabilityPerson=(String) item[35];
				String changeLastTime=(String) item[36];
				String changeProgress=(String) item[37];
				
				int changeStatus=0;
				if (item[38]!=null) {
					changeStatus = Integer.parseInt(String.valueOf(item[38]));
				}
//				int changeStatus=Integer.parseInt(String.valueOf(item[38]));
//				String id1=(String) item[39];
				BigInteger id1 = (BigInteger) item[39];
//				
//			
//
				if (investigationType==81000) {
					row.createCell((short)k++).setCellValue("举报投诉调查");
				}else if (investigationType==81001) {
					row.createCell((short)k++).setCellValue("专项合规检查");
				}
//				row.createCell((short)k++).setCellValue(investigationType);
				row.createCell((short)k++).setCellValue(investigationName);
				if (item[2]!=null) {
					row.createCell((short)k++).setCellValue(compName);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				

				row.createCell((short)k++).setCellValue(id1.toString());
////				处分问责人数
				if (projectCode!=null) {
					row.createCell((short)k++).setCellValue(projectCode);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
//				row.createCell((short)k++).setCellValue(projectCode);
				row.createCell((short)k++).setCellValue(id);
				if (isInside==0) {
					row.createCell((short)k++).setCellValue("是");
				}else if(isInside==1){
					row.createCell((short)k++).setCellValue("否");
				}else {
					row.createCell((short)k++).setCellValue("");
				}
//				row.createCell((short)k++).setCellValue(isInside);
				if (projectName!=null) {
					row.createCell((short)k++).setCellValue(projectName);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
//				row.createCell((short)k++).setCellValue(projectName);
				if (investigationDep!=null) {
					row.createCell((short)k++).setCellValue(investigationDep);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				if (problem!=null) {
					row.createCell((short)k++).setCellValue(problem);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				if (employeeNumber!=null) {
					row.createCell((short)k++).setCellValue(employeeNumber);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				if (employeeName!=null) {
					row.createCell((short)k++).setCellValue(employeeName);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				if (employeePostion!=null) {
					row.createCell((short)k++).setCellValue(employeePostion);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				
				
				if (investigationFrom==81020) {
					row.createCell((short)k++).setCellValue("海航集团交办");
				}else if (investigationFrom==81021) {
					row.createCell((short)k++).setCellValue("海航实业领导交办");
				}else if (investigationFrom==81022) {
					row.createCell((short)k++).setCellValue("监察体系转办");
				}else if (investigationFrom==81023) {
					row.createCell((short)k++).setCellValue("自行启动");
				}else if (investigationFrom==81024) {
					row.createCell((short)k++).setCellValue("海航物流领导交办");
				}else {
					row.createCell((short)k++).setCellValue("");
				}
//				row.createCell((short)k++).setCellValue(investigationFrom);
				row.createCell((short)k++).setCellValue(itemName);
				
				if (isAnonymous==0) {
					row.createCell((short)k++).setCellValue("是");	
				}else if (isAnonymous==1){
					row.createCell((short)k++).setCellValue("否");
				}
//				row.createCell((short)k++).setCellValue(isAnonymous);
				
				row.createCell((short)k++).setCellValue(accusationTime);
				row.createCell((short)k++).setCellValue(assignLeader);
				row.createCell((short)k++).setCellValue(responsiblePerson);
				row.createCell((short)k++).setCellValue(startTime);
				row.createCell((short)k++).setCellValue(endTime);
				/**
				 * 数据类型：0举报投诉反映问题线索核查情况  1待调查事项核查情况   2调查发现其他违规违纪事件情况
				 * row.createCell((short)k++).setCellValue(dataType);
				 */
				if (item[20]!=null) {
					if (dataType==0) {
						row.createCell((short)k++).setCellValue("举报投诉反映问题线索核查情况");
					}else if (dataType==1) {
						row.createCell((short)k++).setCellValue("待调查事项核查情况");
					}else if (dataType==2) {
						row.createCell((short)k++).setCellValue("调查发现其他违规违纪事件情况");
					}
					
					
				}
//				row.createCell((short)k++).setCellValue("");
				if (item[21]!=null) {
					row.createCell((short)k++).setCellValue("");
				}
				if (item[22]!=null) {
					row.createCell((short)k++).setCellValue(truth);
				}
//				
//				
				if (isAccountability==0) {
					row.createCell((short)k++).setCellValue("否");	
				}else if (isAccountability==1){
					row.createCell((short)k++).setCellValue("是");
				}
//				if (item[23]!=null) {
//					row.createCell((short)k++).setCellValue(isAccountability);
//				}
//				
				if (item[24]!=null) {
					row.createCell((short)k++).setCellValue(suggestInfo);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				row.createCell((short)k++).setCellValue(problem);
				
				if (item[22]!=null) {
					row.createCell((short)k++).setCellValue(truth);
				}
				if (suggest!=null) {
					row.createCell((short)k++).setCellValue(suggest);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				if (officeId!=null) {
					row.createCell((short)k++).setCellValue(officeId);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
			
				
			if (name!=null) {
				row.createCell((short)k++).setCellValue(name);
			}else {
				row.createCell((short)k++).setCellValue("");
			}
				
			if (submitPersonName!=null) {
				row.createCell((short)k++).setCellValue(submitPersonName);
			}else {
				row.createCell((short)k++).setCellValue("");
			}
			
			
			if (submitTime!=null) {
				row.createCell((short)k++).setCellValue(submitTime);
			}else {
				row.createCell((short)k++).setCellValue("");
			}
				
			
			if (auditPersonName!=null) {
				row.createCell((short)k++).setCellValue(auditPersonName);
			}else {
				row.createCell((short)k++).setCellValue("");
			}
		
				if (auditContent!=null) {
					row.createCell((short)k++).setCellValue(auditContent);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				
				if (isChange==0) {
					row.createCell((short)k++).setCellValue("否");	
				}else if (isChange==1){
					row.createCell((short)k++).setCellValue("是");
				}
//				row.createCell((short)k++).setCellValue(isChange);
				
				if (item[24]!=null) {
					row.createCell((short)k++).setCellValue(suggestInfo);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				if (item[33]!=null) {
					row.createCell((short)k++).setCellValue(bumfNum);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				if (item[34]!=null) {
					row.createCell((short)k++).setCellValue(articleNum);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				if (item[35]!=null) {
					row.createCell((short)k++).setCellValue(accountabilityPerson);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				if (item[36]!=null) {
					row.createCell((short)k++).setCellValue(changeLastTime);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				if (item[37]!=null) {
					row.createCell((short)k++).setCellValue(changeProgress);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				
			
				
					if (changeStatus==0) {
						row.createCell((short)k++).setCellValue("未整改");
					}else if (changeStatus==1) {
						row.createCell((short)k++).setCellValue("已整改");
					}else if (changeStatus==2) {
						row.createCell((short)k++).setCellValue("整改中");
					}
		
				i++;
			}
	
	}

	@Override
	public XSSFWorkbook complanceExport(BimrCompliance entity) {
		// TODO Auto-generated method stub
		Integer[] defaultStatuses =  new Integer[]{81011, 81012, 81013, 81014, 81015};
		List<BimrCompliance> list = complianceDao.getListByStatusesExport(entity, defaultStatuses);
		if (null==list||list.size()==0) {
			return null;
		}
		
		XSSFWorkbook workBook = new XSSFWorkbook();
		 CellStyle style=workBook.createCellStyle();
		 XSSFFont font = workBook.createFont();
		 
		XSSFCell cell=null;
//		定义单元格属性为空2
		XSSFSheet sheet=workBook.createSheet("合规整改信息导出");
//		创建表名3
		XSSFRow row=sheet.createRow((int)0);
		font.setBold(true);
		 font.setFontHeightInPoints((short) 15);
		 style.setFont(font);
		 style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中  
		 style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直  
		
		cell=row.createCell((short) 0);
		cell.setCellValue("投诉调查数据");
		cell.setCellStyle(style);
		cell=row.createCell((short) 5);
		cell.setCellValue("受理启办阶段");
		cell.setCellStyle(style);
		cell=row.createCell((short) 22);
		cell.setCellValue("调查处理阶段");
		cell.setCellStyle(style);
		cell=row.createCell((short) 30);
		cell.setCellValue("报告办结阶段");
		cell.setCellStyle(style);
		cell=row.createCell((short) 35);
		cell.setCellValue("问责整改阶段");
		cell.setCellStyle(style);
//		 审计类型  报告名称  
		  row=sheet.createRow((int)1);
		String [] header={"调查类型","调查承办企业","调查涉及企业名称","处分问责人数","调查项目编号",
				"序号","是否是集团内部投诉人 ","举报投诉、专项调查项目名称",
				"调查承办部门","待调查事项","员工编号 ","调查涉及人员 ","现任职务","调查来源 ",
				" 调查涉及经营管理事项","是否匿名","投诉收到时间","调查交办领导","调查负责人",
				"调查启动时间","调查结束时间","举报投诉问题反映线索/待调查具体事项","问题线索是否属实",
				"调查确认情况","是否需要问责","建议人员问责情况","其他违规违纪事项描述 ",
				"调查确认情况","合规管理建议","调查报告公文编号","报告名称","呈报人","报告呈报时间",
				"报告最终审批","最终审批人批示意见","是否涉及后续整改","建议问责落实情况",
				"处分问责呈报公文编号","处分问责办文编号","整改责任人","整改完成预计时限","整改工作进展","整改完成状态"};
		for (int i = 0; i < header.length; i++) {
			//字段长度
			cell=row.createCell((short) i);
			cell.setCellValue(header[i]);
			cell.setCellStyle(style);
		}
		
		for (int i = 0; i < list.size(); i++) {
			BimrCompliance bimrCompliance = list.get(i);
			 row=sheet.createRow((int)2+i);
			 /*
			  * 投诉调查数据
			  */
			 //调查类型
			 row.createCell(0).setCellValue(bimrCompliance.getInvestigationTypeName());
			 //调查承办企业
			 row.createCell(1).setCellValue(bimrCompliance.getInvestigationName());
			 //调查涉及企业名称
			 row.createCell(2).setCellValue(bimrCompliance.getCompName());
			 //处分问责人数
			 row.createCell(3).setCellValue(complianceDao.getComplianceAbilityCount(bimrCompliance.getId()));
			 //调查项目编号
			 row.createCell(4).setCellValue(bimrCompliance.getProjectCode());
			 
			 /*
			  * 投诉调查数据
			  */
			//序号
			 row.createCell(5).setCellValue(i+1);
			 //是否是集团内部投诉人
			 row.createCell(6).setCellValue(bimrCompliance.getIsInside()==1?"是":"否");
			 //举报投诉、专项调查项目名称
			 row.createCell(7).setCellValue(bimrCompliance.getProjectName());
			 //调查承办部门
			 row.createCell(8).setCellValue(bimrCompliance.getInvestigationDep());
			 //待调查事项
			 row.createCell(9).setCellValue(bimrCompliance.getInvestigationMatters());
			 
			 List<BimrCompliancePerson> personlist= getCompliancePerson(bimrCompliance.getId());
			 List<Object> PJlist=new ArrayList<Object>();
			 //调查涉及人员
			 for (int j = 0; j < personlist.size(); j++) {
				 PJlist.add(personlist.get(j).getEmployeeNumber());
			 }
			 row.createCell(10).setCellValue(PJZF(PJlist));
			 PJlist.clear();
			//员工编号
			 for (int j = 0; j < personlist.size(); j++) {
				 PJlist.add(personlist.get(j).getEmployeeName());
			 }
			 row.createCell(11).setCellValue(PJZF(PJlist));
			 PJlist.clear();
			 //现任职务
			 for (int j = 0; j < personlist.size(); j++) {
				 PJlist.add(personlist.get(j).getEmployeePostion());
			 }
			 row.createCell(12).setCellValue(PJZF(PJlist));
			 PJlist.clear();
			 
			 //调查来源
			 Integer investigationFrom = bimrCompliance.getInvestigationFrom();
			 if (investigationFrom==81020) {
				 row.createCell(13).setCellValue("海航集团交办");
				}else if (investigationFrom==81021) {
				 row.createCell(13).setCellValue("海航实业领导交办");
				}else if (investigationFrom==81022) {
					 row.createCell(13).setCellValue("监察体系转办");
				}else if (investigationFrom==81023) {
					 row.createCell(13).setCellValue("自行启动");
				}else if (investigationFrom==81024) {
					 row.createCell(13).setCellValue("海航物流领导交办");
				}else {
					 row.createCell(13).setCellValue("");
				}
			 
			  //调查涉及经营管理事项
			  row.createCell(14).setCellValue(bimrCompliance.getItemName());
			  //是否匿名
			  row.createCell(15).setCellValue(bimrCompliance.getIsAnonymous()==0?"否":"是");
			  //投诉收到时间
			  row.createCell(16).setCellValue(bimrCompliance.getAccusationTime());
			  //调查交办领导
			  row.createCell(17).setCellValue(bimrCompliance.getAssignLeader());
			  //调查负责人
			  row.createCell(18).setCellValue(bimrCompliance.getResponsiblePerson());
			  //调查启动时间
			  row.createCell(19).setCellValue(bimrCompliance.getStartTime());
			  //调查结束时间
			  row.createCell(20).setCellValue(bimrCompliance.getEndTime());
			  
			  List<BimrComplianceSituation> situationlist=getComplianceSituation(bimrCompliance.getId(),null);
			  //举报投诉问题反映线索/待调查具体事项
			  for (int j = 0; j < situationlist.size(); j++) {
				  if (situationlist.get(j).getDataType()==2) {
					continue;
				}
				  if (null!=situationlist.get(j).getProblem()) {
					  PJlist.add(situationlist.get(j).getProblem());
				}else {
					PJlist.add("");
				}
				  
			  }
			  row.createCell(21).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  //问题线索是否属实
			  for (int j = 0; j < situationlist.size(); j++) {
			    if (situationlist.get(j).getDataType()==2) {
					continue;
				}
			    if (null!=situationlist.get(j).getIsTruth()) {
			    	 PJlist.add(situationlist.get(j).getIsTruth().toString());
				}else {
					 PJlist.add("");
				}
			  }
			  for (int j = 0; j < PJlist.size(); j++) {
				if (PJlist.get(j).equals("81030")) {
					PJlist.set(j, "属实");
				}else if(PJlist.get(j).equals("81031")){
					PJlist.set(j, "部分属实");
				}else if(PJlist.get(j).equals("81032")){
					PJlist.set(j, "不属实");
				}else {
					PJlist.set(j, "");
				}
			  }
			  row.createCell(22).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  //调查确认情况
			  for (int j = 0; j < situationlist.size(); j++) {
				    if (situationlist.get(j).getDataType()==2) {
						continue;
					}
				    if (null!=situationlist.get(j).getTruth()) {
				    	PJlist.add(situationlist.get(j).getTruth());
					}else {
						PJlist.add(situationlist.get(j).getTruth());
					}
				  }
			  row.createCell(23).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			 //是否需要问责	  		
			 for (int j = 0; j < situationlist.size(); j++) {
					    if (situationlist.get(j).getDataType()==2) {
							continue;
						}
					    if (null!=situationlist.get(j).getIsAccountability()) {
					    	 PJlist.add(situationlist.get(j).getIsAccountability());
						}else {
							 PJlist.add("");
						}
						 
			 }
			 for (int j = 0; j < PJlist.size(); j++) {
						if (PJlist.get(j).toString().equals("1")) {
							PJlist.set(j, "是");
						}else {
							PJlist.set(j, "否");
						}
		     }	  	
			 row.createCell(24).setCellValue(PJZF(PJlist));
			 PJlist.clear();
			 //建议人员问责情况
			  for (int j = 0; j < situationlist.size(); j++) {
				 
				    List<BimrComplianceSituationPerson> sPersonslist=getComplianceSituationPerson(situationlist.get(j).getId());
				    for (int j2 = 0; j2 < sPersonslist.size(); j2++) {
				    	PJlist.add(sPersonslist.get(j2).getAccountabilityPerson());
					}
			  }
			  row.createCell(25).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  
			  //其他违规违纪事项描述
			  for (int j = 0; j < situationlist.size(); j++) {
				    if (situationlist.get(j).getDataType()==2) {
				    	PJlist.add(situationlist.get(j).getProblem());
					}
				  }
			  row.createCell(26).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  
			  //调查确认情况
			  
			  for (int j = 0; j < situationlist.size(); j++) {
				    if (situationlist.get(j).getDataType()==2) {
				    	PJlist.add(situationlist.get(j).getTruth());
					}
			  }
			  row.createCell(27).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  
			  //合规管理建议
			  List<BimrComplianceSuggest> suggests=getComplianceSuggest(bimrCompliance.getId());
			  for (int j = 0; j < suggests.size(); j++) {
				    PJlist.add(suggests.get(j).getSuggest());
			  }
			  row.createCell(28).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  
			  //调查报告公文编号
			  BimrComplianceBJInfo bimrComplianceBJInfo=getBJInfoByComplianceId(bimrCompliance.getId());
			  if (null==bimrComplianceBJInfo) {
				  row.createCell(29).setCellValue("");
				  row.createCell(30).setCellValue("");
				  row.createCell(31).setCellValue("");
				  row.createCell(32).setCellValue("");
				  row.createCell(33).setCellValue("");
				  row.createCell(34).setCellValue("");
			  }else {
				  row.createCell(29).setCellValue(bimrComplianceBJInfo.getOfficeId()!=null?bimrComplianceBJInfo.getOfficeId():"");
				  
				  //报告名称
				  row.createCell(30).setCellValue(bimrComplianceBJInfo.getName());
				  
				  //呈报人
				  row.createCell(31).setCellValue(bimrComplianceBJInfo.getSubmitPersonName());
				  
				  //报告呈报时间
				  row.createCell(32).setCellValue(bimrComplianceBJInfo.getSubmitTime());
				  
				  //报告最终审批人
				  row.createCell(33).setCellValue(bimrComplianceBJInfo.getAuditPersonName());
				  
				  //最终审批人批示意见
				  row.createCell(34).setCellValue(bimrComplianceBJInfo.getAuditContent());
			 }
			 
			  
			  //是否涉及后续整改
			  
			  for (int j = 0; j < suggests.size(); j++) {
				    PJlist.add(suggests.get(j).getIsChange());
		      }	  
			  for (int j = 0; j < PJlist.size(); j++) {
					if (PJlist.get(j).toString().equals("1")) {
						PJlist.set(j, "是");
					}else {
						PJlist.set(j, "否");
					}
	          }	  
			  row.createCell(35).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  
			  //建议问责落实情况
			  for (int j = 0; j < situationlist.size(); j++) {
				    if (situationlist.get(j).getDataType()==2) {
						continue;
					}
				    List<BimrComplianceSituationPerson> sPersonslist=getComplianceSituationPerson(situationlist.get(j).getId());
				    for (int j2 = 0; j2 < sPersonslist.size(); j2++) {
				    	PJlist.add(sPersonslist.get(j2).getSuggestInfo());
					}
			  }
			  row.createCell(36).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  
			  //处分问责呈报公文编号
			  for (int j = 0; j < situationlist.size(); j++) {
				    if (situationlist.get(j).getDataType()==2) {
						continue;
					}
				    List<BimrComplianceSituationPerson> sPersonslist=getComplianceSituationPerson(situationlist.get(j).getId());
				    for (int j2 = 0; j2 < sPersonslist.size(); j2++) {
				    	PJlist.add(sPersonslist.get(j2).getSubmitOfficeId());
					}
			  }
			  row.createCell(37).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  
			  //处分问责呈报公文编号
			  for (int j = 0; j < situationlist.size(); j++) {
				    if (situationlist.get(j).getDataType()==2) {
						continue;
					}
				    List<BimrComplianceSituationPerson> sPersonslist=getComplianceSituationPerson(situationlist.get(j).getId());
				    for (int j2 = 0; j2 < sPersonslist.size(); j2++) {
				    	PJlist.add(sPersonslist.get(j2).getOfficeId());
					}
			  }
			  row.createCell(38).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  
			  //整改责任人
			  for (int j = 0; j < suggests.size(); j++) {
				    PJlist.add(suggests.get(j).getAccountabilityPerson());
			  }
			  row.createCell(39).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  
			//整改完成预计时限
			  for (int j = 0; j < suggests.size(); j++) {
				    PJlist.add(suggests.get(j).getChangeLastTime());
			  }
			  row.createCell(40).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  
			//整改工作进展
			  for (int j = 0; j < suggests.size(); j++) {
				    PJlist.add(suggests.get(j).getChangeProgress());
			  }
			  row.createCell(41).setCellValue(PJZF(PJlist));
			  PJlist.clear();
			  
			//整改完成状态
			  for (int j = 0; j < suggests.size(); j++) {
				  if (null!=suggests.get(j).getChangeStatus()) {
					  PJlist.add(suggests.get(j).getChangeStatus().toString());
				}
			  }
			  for (int j = 0; j < PJlist.size(); j++) {
					if (PJlist.get(j).equals("0")) {
						PJlist.set(j, "未整改");
					}else if(PJlist.get(j).equals("1")){
						PJlist.set(j, "已整改");
					}else {
						PJlist.set(j, "整改中");
					}
	          }	  
			  row.createCell(42).setCellValue(PJZF(PJlist));
			  PJlist.clear();
		}
		
		
		
		
		return workBook;
	}
	
	//将字符串凭借成以逗号分隔
	
	public String PJZF(List<Object> list) {
		String returndata="";
		if(list == null || list.size()<=0)
			returndata = "";
		for (Object object : list) {
			if (object==null) {
				continue;
			}
			if(returndata.equals(""))
				returndata= (object==null? "": String.valueOf(object));
			else
				returndata=returndata+","+ (object==null? " ": String.valueOf(object));
		}
		
		return returndata;
	}

}
