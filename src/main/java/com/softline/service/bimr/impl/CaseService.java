package com.softline.service.bimr.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.dao.bimr.ICaseDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.MainFinancialIndicator;
import com.softline.entity.bimr.BimrCaseQuery;
import com.softline.entity.bimr.BimrCaseReport;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrCriminalcaseWeek;
import com.softline.service.bimr.ICaseService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.util.MsgPage;

@Service("caseService")
public class CaseService implements ICaseService{
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "caseDao")
	private ICaseDao caseDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;	

	@Resource(name="baseService")
	private IBaseService baseService;
	@Override
	public MsgPage findCivilcasePageList(BimrCivilcaseWeek civilcase, Integer curPageNum, Integer pageSize, HhUsers user, String type) {
		MsgPage msgPage = new MsgPage();
		if(type.equals("2")){
			//总记录数
			Integer totalRecords = caseDao.getCivilcaseCount(civilcase, null, null);
	        //当前页开始记录
	    	Integer offset = msgPage.countOffset(curPageNum, pageSize);  
	    	//分页查询结果集
	    	List<BimrCivilcaseWeek> list = caseDao.getCivilcaseList(civilcase, offset, pageSize);
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);   
		}
		if(type.equals("1")){
			//总记录数
			Integer totalRecords = caseDao.getCivilcaseCountByUserId(civilcase, null, null, user.getVcEmployeeId());
	        //当前页开始记录
	    	Integer offset = msgPage.countOffset(curPageNum, pageSize);  
	    	//分页查询结果集
	    	List<BimrCivilcaseWeek> list = caseDao.getCivilcaseListByUserId(civilcase, offset, pageSize, user.getVcEmployeeId());
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);   
		}
        return msgPage;
	}
	@Override
	public MsgPage findCivilcaseQueryPageList(BimrCivilcaseWeek civilcase,
			Integer curPageNum, Integer pageSize, HhUsers user,String caseDateStart,String caseDateEnd,String amountSection) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();
		//总记录数

		Integer totalRecords = caseDao.getCivilcaseQueryCount(civilcase, null, null,caseDateStart,caseDateEnd,amountSection);
        //当前页开始记录
    	Integer offset = msgPage.countOffset(curPageNum, pageSize);  
    	//分页查询结果集
    	List<BimrCivilcaseWeek> list = caseDao.getCivilcaseQueryList(civilcase, offset, pageSize,caseDateStart,caseDateEnd,amountSection);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);   
    	 return msgPage;
	}
	@Override
	public void deleteCivilcase(Integer id){
		BimrCivilcaseWeek entity = getCivilcaseById(id);
		this.updateLasterCaseState("1", null, entity);
		if ( null!= entity.getIndictment()) {
			String uuid = entity.getIndictment();
			Common.deleteFile(DES.BIMR_CIVIL_CASE, uuid);
			HhFile file = commonDao.getFileByUuid(uuid);
			if(null!=file)
			commonDao.delete(file);
		}
		if ( null!= entity.getJudgment()) {
			String uuid = entity.getJudgment();
			Common.deleteFile(DES.BIMR_CIVIL_CASE, uuid);
			HhFile file = commonDao.getFileByUuid(uuid);
			if(null!=file)
			commonDao.delete(file);
		}
		if (null != entity.getOtherFile()) {
			String uuid = entity.getOtherFile();
			Common.deleteFile(DES.BIMR_CIVIL_CASE, uuid);
			HhFile file = commonDao.getFileByUuid(uuid);
			if(null!=file)
			commonDao.delete(file);
		}
		caseDao.deleteCivilcase(id);
	}
	
	@Override
	public BimrCivilcaseWeek getCivilcaseById(Integer id){
		return caseDao.getCivilcaseById(id);
	}
	
	/**
	 * 
	 * 民事案件审核
	 * 
	 * */
	@Override
	public BimrCivilcaseWeek examineCivilcaseById(Integer id, String examStr, Integer examResult, HhUsers user){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BimrCivilcaseWeek civilcase = caseDao.getCivilcaseById(id);
		BimrCivilcaseWeek civilcase_1 = caseDao.getLastCivilcaseByCaseNum(civilcase.getCaseNum());
		civilcase.setAuditorDate(df.format(new Date()));
		civilcase.setAuditorPersonId(user.getVcEmployeeId());
		civilcase.setAuditorPersonName(user.getVcName());
		if(examResult.toString().equals("50116")){
			civilcase.setApprovalState("2");
			if(civilcase_1 == null){
				if(civilcase.getVersion() == null || civilcase.getVersion().equals("")){
					civilcase.setVersion("1.0");
					String organ = civilcase.getVcOrganID();
					String code = "";
					//自动生成案件编码
					Calendar c = Calendar.getInstance();
					int year = c.get(Calendar.YEAR);
					//产业集团：海航实业；
					//核心企业：以编号代替：0101（海航物流总部、资产管理部）；0102（海航基础）；0103（供销大集）；0104（海航金融）；0105（海航教育医疗）；0106（海航实业国际总部）；
					//案件类型：民（民事案件）、刑（刑事案件）
					/*if(organ.contains("0-1-26655-847044-") || organ.contains("0-1-26655-26658-")){
						code = "0101";
					}
					if(organ.contains("0-1-26655-27534-")){
						code = "0102";
					}
					if(organ.contains("0-1-26655-4010-")){
						code = "0103";
					}
					if(organ.contains("0-1-26655-101351-")){
						code = "0104";
					}
					if(organ.contains("0-1-26655-848600-")){
						code = "0105";
					}
					if(organ.contains("0-1-26655-847147-")){
						code = "0106";
					}
					String num = "（" + year + "）" + "海航实业" + code + "民";*/

		    		if(organ.contains("0-1-855579-855580-")){
						code = "HNALOHQ-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856081-")){
						code = "HNALODU-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856150-")){
						code = "HNAINF-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856151-")){
						code = "CCOOP-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856152-")){
						code = "HKICIM-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856153-")){
						code = "HNAINV-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856154-")){
						code = "CWT-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856155-")){
						code = "HYGF-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856156-")){
						code = "HNAINFRA-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856157-")){
						code = "SKL-"+year+"-";
					}
					else{
						code = "HNALO-"+year+"-";
					}
		    		String num = code + "民";
					
					List list = caseDao.getCivilcaseByLikeCaseNum(num);
					if(list.size() > 0){
						BimrCivilcaseWeek caseWeek = (BimrCivilcaseWeek)list.get(0);
						String n = caseWeek.getCaseNum();
						n = n.substring(n.indexOf("民")+1,n.length()-1);
						int seq = Integer.parseInt(n);
						seq = seq + 1;
						if(seq <10){
							n = "00" + seq;
						}else if(seq >=10 && seq < 100){
							n = "0" + seq;
						}else{
							n = seq + "";
						}
						num = num + n + "号";
					}
					else{
						num = num + "001号";
					}
					civilcase.setCaseNum(num);
				}
			}else{
				int ver = Integer.parseInt(civilcase_1.getVersion().substring(0,civilcase_1.getVersion().indexOf(".")));
				ver = ver + 1;
				civilcase.setVersion(ver+".0");
				civilcase_1.setIsHistory("1");
				civilcase_1.setIsLastest(null);
				commonService.saveOrUpdate(civilcase_1);
			}
		}
		if(examResult.toString().equals("50117")){
			civilcase.setApprovalState("3");
		}
		civilcase.setAuditorDate(df.format(new Date()));
		civilcase.setAuditorPersonId(user.getVcEmployeeId());
		civilcase.setAuditorPersonName(user.getVcName());
		commonService.saveOrUpdate(civilcase);
		
		examineService.saveExamine( civilcase.getId(), Base.examkind_civilcase, user, examStr, examResult);
		potalMsgService.updatePortalMsg("bimWork_bimr_civilcase", civilcase.getId().toString());
		return civilcase;
	}
	
	/**
	 * 刑事案件
	 * */
	
	@Override
	public MsgPage findCriminalcaseQueryPageList(
			BimrCriminalcaseWeek criminalcase, Integer curPageNum,
			Integer pageSize, HhUsers user) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();
		//总记录数
		Integer totalRecords = caseDao.getCriminalcaseQueryCount(criminalcase,null,null);
        //当前页开始记录
    	Integer offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<BimrCriminalcaseWeek> list = caseDao.getCriminalcaseQueryList(criminalcase, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);
    	return msgPage;
	}
	@Override
	public MsgPage findCriminalcasePageList(BimrCriminalcaseWeek criminalcase, Integer curPageNum, Integer pageSize, HhUsers user, String type) {
		MsgPage msgPage = new MsgPage();
		if(type.equals("2")){
			//总记录数
			Integer totalRecords = caseDao.getCriminalcaseCount(criminalcase,null,null);
	        //当前页开始记录
	    	Integer offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<BimrCriminalcaseWeek> list = caseDao.getCriminalcaseList(criminalcase, offset, pageSize);
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);  
		}
		if(type.equals("1")){
			//总记录数
			Integer totalRecords = caseDao.getCriminalcaseCountByUserId(criminalcase,null,null,user.getVcEmployeeId());
	        //当前页开始记录
	    	Integer offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<BimrCriminalcaseWeek> list = caseDao.getCriminalcaseListByUserId(criminalcase, offset, pageSize, user.getVcEmployeeId());
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);  
		}
        return msgPage;
	}
	
	@Override
	public void deleteCriminalcase(Integer id){
		BimrCriminalcaseWeek entity = getCriminalcaseById(id);
		this.updateLasterCaseState("2", null, entity);
		if ( null!= entity.getReportment()) {
			String uuid = entity.getReportment();
			Common.deleteFile(DES.BIMR_CIVIL_CASE, uuid);
			HhFile file = commonDao.getFileByUuid(uuid);
			if(null!=file)
			commonDao.delete(file);
		}
		if ( null!= entity.getJudgment()) {
			String uuid = entity.getJudgment();
			Common.deleteFile(DES.BIMR_CIVIL_CASE, uuid);
			HhFile file = commonDao.getFileByUuid(uuid);
			if(null!=file)
			commonDao.delete(file);
		}
		if (null != entity.getOtherFile()) {
			String uuid = entity.getOtherFile();
			Common.deleteFile(DES.BIMR_CIVIL_CASE, uuid);
			HhFile file = commonDao.getFileByUuid(uuid);
			if(null!=file)
			commonDao.delete(file);
		}
		caseDao.deleteCriminalcase(id);
	}
	
	@Override
	public BimrCriminalcaseWeek getCriminalcaseById(Integer id){
		return caseDao.getCriminalcaseById(id);
	}
	
	@Override
	public BimrCriminalcaseWeek examineCriminalcaseById(Integer id, String examStr, Integer examResult, HhUsers user){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BimrCriminalcaseWeek criminalcase = caseDao.getCriminalcaseById(id);
		BimrCriminalcaseWeek criminalcase_1 = caseDao.getLastCriminalcaseByCaseNum(criminalcase.getCaseNum());
		if(examResult.toString().equals("50116")){
			criminalcase.setApprovalState("2");
			if(criminalcase_1 == null){
				if(criminalcase.getVersion() == null || criminalcase.getVersion().equals("")){
					criminalcase.setVersion("1.0");
					//自动生成案件编码					
					String organ = criminalcase.getVcOrganID();
					String code = "";
					//自动生成案件编码
					Calendar c = Calendar.getInstance();
					int year = c.get(Calendar.YEAR);
					//产业集团：海航实业；
					//核心企业：以编号代替：0101（海航物流总部、资产管理部）；0102（海航基础）；0103（供销大集）；0104（海航金融）；0105（海航教育医疗）；0106（海航实业国际总部）；
					//案件类型：民（民事案件）、刑（刑事案件）
					/*if(organ.contains("0-1-26655-847044-") || organ.contains("0-1-26655-26658-")){
						code = "0101";
					}
					if(organ.contains("0-1-26655-27534-")){
						code = "0102";
					}
					if(organ.contains("0-1-26655-4010-")){
						code = "0103";
					}
					if(organ.contains("0-1-26655-101351-")){
						code = "0104";
					}
					if(organ.contains("0-1-26655-848600-")){
						code = "0105";
					}
					if(organ.contains("0-1-26655-847147-")){
						code = "0106";
					}
					String num = "（" + i + "）" + "海航实业" + code + "刑";*/
					if(organ.contains("0-1-855579-855580-")){
						code = "HNALOHQ-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856081-")){
						code = "HNALODU-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856150-")){
						code = "HNAINF-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856151-")){
						code = "CCOOP-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856152-")){
						code = "HKICIM-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856153-")){
						code = "HNAINV-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856154-")){
						code = "CWT-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856155-")){
						code = "HYGF-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856156-")){
						code = "HNAINFRA-"+year+"-";
					}
		    		else if(organ.contains("0-1-855579-856157-")){
						code = "SKL-"+year+"-";
					}
					else{
						code = "HNALO-"+year+"-";
					}
		    		String num = code + "刑";
					List list = caseDao.getCriminalcaseLikeCaseNum(num);
					if(list.size() > 0){
						BimrCriminalcaseWeek caseWeek = (BimrCriminalcaseWeek)list.get(0);
						String n = caseWeek.getCaseNum();
						n = n.substring(n.indexOf("刑")+1,n.length()-1);
						int seq = Integer.parseInt(n);
						seq = seq + 1;
						if(seq <10){
							n = "00" + seq;
						}else if(seq >=10 && seq < 100){
							n = "0" + seq;
						}else{
							n = seq + "";
						}
						num = num + n + "号";
					}
					else{
						num = num + "001号";
					}
					criminalcase.setCaseNum(num);
				}
			}else{
				int ver = Integer.parseInt(criminalcase_1.getVersion().substring(0,criminalcase_1.getVersion().indexOf(".")));
				ver = ver + 1;
				criminalcase.setVersion(ver+".0");
				criminalcase_1.setIsHistory("1");
				criminalcase_1.setIsLastest(null);
				commonService.saveOrUpdate(criminalcase_1);
			}
		}
		if(examResult.toString().equals("50117")){
			criminalcase.setApprovalState("3");
		}
		criminalcase.setAuditorDate(df.format(new Date()));
		criminalcase.setAuditorPersonId(user.getVcEmployeeId());
		criminalcase.setAuditorPersonName(user.getVcName());
		commonService.saveOrUpdate(criminalcase);
		
		examineService.saveExamine( criminalcase.getId(), Base.examkind_criminalcase, user, examStr, examResult);
		potalMsgService.updatePortalMsg("bimWork_bimr_criminalcase", criminalcase.getId().toString());
		return criminalcase;
	}
	
	@Override
	public void updateLasterCaseState(String type, String state, Object obj,MultipartFile[] indictmentFile,MultipartFile[] judgmentFile,MultipartFile[] oFile){
		//民事案件
		if(type.equals("1")){
			BimrCivilcaseWeek civilcase = (BimrCivilcaseWeek)obj;
			commonService.saveOrUpdate(civilcase);
			
			if (indictmentFile!= null && indictmentFile.length!=0) {
				for (int i = 0; i < indictmentFile.length; i++) {
					HhFile file = commonService.saveFile(indictmentFile[i], civilcase.getId(),
							Base.BIMR_CIVIL_CASE, DES.BIMR_CIVIL_CASE);
					if (!Common.notEmpty(civilcase.getIndictment())) {
						civilcase.setIndictment(file.getFileUuid());
					} else {
						civilcase.setIndictment(civilcase.getIndictment() + ","
								+ file.getFileUuid());
					}
				}
			}
			if (judgmentFile!= null && judgmentFile.length!=0) {
				for (int i = 0; i < judgmentFile.length; i++) {
					HhFile file2 = commonService.saveFile(judgmentFile[i], civilcase.getId(),
							Base.BIMR_CIVIL_CASE, DES.BIMR_CIVIL_CASE);
					if (!Common.notEmpty(civilcase.getJudgment())) {
						civilcase.setJudgment(file2.getFileUuid());
					} else {
						civilcase.setJudgment(civilcase.getJudgment() + ","
								+ file2.getFileUuid());
					}
				}
				
			}
			if (oFile!= null && oFile.length!=0) {
				for (int i = 0; i < oFile.length; i++) {
					HhFile file3 = commonService.saveFile(oFile[i], civilcase.getId(),
							Base.BIMR_CIVIL_CASE, DES.BIMR_CIVIL_CASE);
					if (!Common.notEmpty(civilcase.getOtherFile())) {
						civilcase.setOtherFile(file3.getFileUuid());
					} else {
						civilcase.setOtherFile(civilcase.getOtherFile() + ","
								+ file3.getFileUuid());
					}
				}
			}
			BimrCivilcaseWeek civilcase_1 = caseDao.getLastCivilcaseByCaseNum(civilcase.getCaseNum());
			if(civilcase_1 != null){
				civilcase_1.setIsLastest(state);
				commonService.saveOrUpdate(civilcase_1);
			}
		}
		//刑事案件
		if(type.equals("2")){
			BimrCriminalcaseWeek criminalcase = (BimrCriminalcaseWeek)obj; 
			commonService.saveOrUpdate(criminalcase);
			if (indictmentFile!= null && indictmentFile.length!=0) {
				for (int i = 0; i < indictmentFile.length; i++) {
					HhFile file = commonService.saveFile(indictmentFile[i], criminalcase.getId(),
							Base.BIMR_CRIMINAL_CASE, DES.BIMR_CRIMINAL_CASE);
					if (!Common.notEmpty(criminalcase.getReportment())) {
						criminalcase.setReportment(file.getFileUuid());
					} else {
						criminalcase.setReportment(criminalcase.getReportment() + ","
								+ file.getFileUuid());
					}
				}
			}
			
			
			
			
			
			if (judgmentFile!= null && judgmentFile.length!=0) {
				for (int i = 0; i < judgmentFile.length; i++) {
					HhFile file = commonService.saveFile(judgmentFile[i], criminalcase.getId(),
							Base.BIMR_CRIMINAL_CASE, DES.BIMR_CRIMINAL_CASE);
					if (!Common.notEmpty(criminalcase.getJudgment())) {
						criminalcase.setJudgment(file.getFileUuid());
					} else {
						criminalcase.setJudgment(criminalcase.getJudgment() + ","
								+ file.getFileUuid());
					}
				}
			}
			
			if (oFile!= null && oFile.length!=0) {
				
				for (int i = 0; i < oFile.length; i++) {
					HhFile file = commonService.saveFile(oFile[i], criminalcase.getId(),
							Base.BIMR_CRIMINAL_CASE, DES.BIMR_CRIMINAL_CASE);
					if (!Common.notEmpty(criminalcase.getOtherFile())) {
						criminalcase.setOtherFile(file.getFileUuid());
					} else {
						criminalcase.setOtherFile(criminalcase.getOtherFile() + ","
								+ file.getFileUuid());
					}
				}
			}
			
			BimrCriminalcaseWeek criminalcase_1 = caseDao.getLastCriminalcaseByCaseNum(criminalcase.getCaseNum());
			if(criminalcase_1 != null){
				criminalcase_1.setIsLastest(state);
				commonService.saveOrUpdate(criminalcase_1);
			}
		}
	}
	
	@Override
	public void updateLasterCaseState(String type, String state, Object obj){
		//民事案件
		if(type.equals("1")){
			BimrCivilcaseWeek civilcase = (BimrCivilcaseWeek)obj;
			commonService.saveOrUpdate(civilcase);
			BimrCivilcaseWeek civilcase_1 = caseDao.getLastCivilcaseByCaseNum(civilcase.getCaseNum());
			if(civilcase_1 != null){
				civilcase_1.setIsLastest(state);
				commonService.saveOrUpdate(civilcase_1);
			}
		}
		//刑事案件
		if(type.equals("2")){
			BimrCriminalcaseWeek criminalcase = (BimrCriminalcaseWeek)obj; 
			commonService.saveOrUpdate(criminalcase);
			BimrCriminalcaseWeek criminalcase_1 = caseDao.getLastCriminalcaseByCaseNum(criminalcase.getCaseNum());
			if(criminalcase_1 != null){
				criminalcase_1.setIsLastest(state);
				commonService.saveOrUpdate(criminalcase_1);
			}
		}
	}
	
	@Override
	public MsgPage comprehensiveQuery(BimrCaseQuery caseQuery, Integer curPageNum, Integer pageSize){
		MsgPage msgPage = new MsgPage();
		//总记录数
		Integer totalRecords = caseDao.getComprehensiveQueryCount(caseQuery,null,null);
        //当前页开始记录
    	Integer offset = msgPage.countOffset(curPageNum,pageSize);
    	//分页查询结果集
    	List<String> list = caseDao.getComprehensiveQuery(caseQuery, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);
        return msgPage;
	}
	
	
	
	
	@Override
	public List getCaseByCaseNum(String type, String caseNum){
		List list = new ArrayList();
		if(type.equals("1")){
			list = caseDao.getCivilcaseByCaseNum(caseNum);
		}
		if(type.equals("2")){
			list = caseDao.getCriminalcaseByCaseNum(caseNum);
		}
		return list;
	}
	
	
	@Override
//	民事  查询 导出
	public List<BimrCivilcaseWeek> getcivilCaseExport(BimrCivilcaseWeek civilcase,String caseDateStart,String caseDateEnd,String amountSection,int type) {
		// TODO Auto-generated method stub
		List<BimrCivilcaseWeek> list = caseDao.getcivilCaseExport(civilcase,caseDateStart,caseDateEnd,amountSection,type);
		return list;
	}
	@Override
//	刑事  查询
	public List<BimrCriminalcaseWeek> getCriminalCaseExport(BimrCriminalcaseWeek entity,Integer state) {
		// TODO Auto-generated method stub
		List<BimrCriminalcaseWeek> list =caseDao.getCriminalCaseExport(entity,state);
		return list;
	}
	@Override
	public List getCaseReport(String year, String week){
		return caseDao.getCaseReport(year, week);
	}
	
	@Override
	public void saveCaseReport(String year, String week,String dataauth){
		BimrCaseReport rep = new BimrCaseReport();
		String civilcaseIds = "";
		String criminalcaseIds = "";
		rep.setYear(year);
		rep.setWeek(week);
		//查询出最新的民事案件id号
		List list_1 = caseDao.getCivilcaseIdsList(dataauth);
		for(int i=0; i<list_1.size(); i++){
			BimrCivilcaseWeek civil = (BimrCivilcaseWeek)list_1.get(i);
			civilcaseIds = civilcaseIds + civil.getId() + ",";
		}
		//查询出最新的刑事事案件id号
		List list_2 = caseDao.getCriminalcaseIdsList(dataauth);
		for(int i=0; i<list_2.size(); i++){
			BimrCriminalcaseWeek criminal = (BimrCriminalcaseWeek)list_2.get(i);
			criminalcaseIds = criminalcaseIds + criminal.getId() + ",";
		}
		if(civilcaseIds.length() > 0){
			civilcaseIds = civilcaseIds.substring(0,civilcaseIds.length()-1);
		}
		if(criminalcaseIds.length() > 0){
			criminalcaseIds = criminalcaseIds.substring(0,criminalcaseIds.length()-1);
		}
		rep.setCivilcaseIds(civilcaseIds);
		rep.setCriminalcaseIds(criminalcaseIds);
		commonService.saveOrUpdate(rep);
		
		 
		int month=getMonthByYearAndWeek(Integer.parseInt(rep.getYear()),Integer.parseInt(rep.getWeek()));
		
		String[] cids=rep.getCivilcaseIds().split(",");
		for (int i = 0; i < cids.length; i++) {
			caseDao.saveBimrCaseReporttemp(rep, "0", cids[i],month);
		}
		cids=rep.getCriminalcaseIds().split(",");
		for (int i = 0; i < cids.length; i++) {
			caseDao.saveBimrCaseReporttemp(rep, "1", cids[i],month);
		}
	}
	
	public int getMonthByYearAndWeek(int year,int week){
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        int month=cal.get(Calendar.MONTH);
		return month;
	}
	
	
	
	@Override
	public XSSFWorkbook getcivilCaseExportWorkbook(
			List<BimrCivilcaseWeek> list1, List<BimrCivilcaseWeek> list2,
			List<BimrCivilcaseWeek> list3) {
		   XSSFWorkbook workBook = new XSSFWorkbook();
		   Map<String, XSSFCellStyle> styleMap=createStyles(workBook);  
		   CellStyle style=workBook.createCellStyle();
		   CellStyle style1=workBook.createCellStyle();
		   XSSFFont font = workBook.createFont();
		   XSSFFont font1 = workBook.createFont();
		   font.setBold(true);
//			 首先我们需要创建一个工作簿
			 XSSFCell cell = null;
			 int count=0;
//			 设置单元格为空
			 XSSFSheet sheet = workBook.createSheet("民事案件");
			 sheet.addMergedRegion(new CellRangeAddress(0,0,0,45));
			 XSSFRow row = sheet.createRow((int) 0);  
			 cell = row.createCell((short) 0);
			 cell.setCellStyle(styleMap.get("title_style"));
			 cell.setCellValue("重大民事案件台账");
			 sheet.addMergedRegion(new CellRangeAddress(1,1,0,41));
			 row = sheet.createRow((int) 1); 
			 cell = row.createCell((short)0);
			 cell.setCellValue("一、在办案件");
			 cell.setCellStyle(styleMap.get("title_style4"));
			 count=2;
			 civilCaseceaterows(list1,sheet,count,style1,1,font1,styleMap);
			 count=list1.size()+count+1;
			 sheet.addMergedRegion(new CellRangeAddress(count,count,0,41));
			 row = sheet.createRow((int) count); 
			 cell = row.createCell((short)0);
			 cell.setCellValue("二、执行案件");
			 cell.setCellStyle(styleMap.get("title_style4"));
			 count=count+1;
			 civilCaseceaterows(list2,sheet,count,style1,2,font1,styleMap);
			 count=count+list2.size()+1;
			 sheet.addMergedRegion(new CellRangeAddress(count,count,0,41));
			 row = sheet.createRow((int) count); 
			 cell = row.createCell((short)0);
			 cell.setCellValue("三、办结案件");
			 cell.setCellStyle(styleMap.get("title_style4"));
			 count=count+1;
			 civilCaseceaterows(list3,sheet,count,style1,3,font1,styleMap);
		// TODO Auto-generated method stub
		return workBook;
	}
//"判决/调解金额(人民币万元)","已执行款项(人民币万元)","避免/挽回经济损失(人民币万元)","案件编号","案件归属单位"
	public void civilCaseceaterows(List<BimrCivilcaseWeek> list,XSSFSheet sheet,int count, CellStyle style,int type,XSSFFont font1,Map<String, XSSFCellStyle> styleMap) {
		 String [] header1={"序号","承办单位","原告/申请人/第三方","被告/被申请人/第三人","我方诉讼地位（主诉/被诉）","受理法院/仲裁机构","受案单位所在地(省份/城市)","承办法官/仲裁员",
				 "涉案金额（万元）","案件类型","案由","基本案情", "是否为重大案件(根据集团重大案件标准)","是否清案遗留案件","是否新增案件(回头看)","是否因人员优化工作引发案件","调处阶段",
				"最新进展","合作开始时间（示例：XX年XX月XX日）","纠纷发生时间（示例：XX年XX月XX日）","业务部门解决纠纷的过程","成诉原因",
				 "案发时间（示例：XX年XX月XX日）","案龄（年）","承办法务","案件调处负责人","外聘律所及律师","律师费总额(人民币万元)", "已支付金额(人民币万元)",
				 "对方外聘律所及律师","预测结果","调处时间计划表（说明一审、二审的调处时间计划）","预计结果","是否问责","是否进行/完成成因分析","/",
				 "案件成因（实业）","拟问责建议（若无需问责，请说明具体原因）（实业）","问责进展情况（含问责人数及名单）（实业）","办案思路、调处重点及工作计划（实业）","协办人（实业）","双方保全情况（实业）","一案双处实施情况 （实业）","规划结案时间",
				 "备注(实业)"
		 };
		 String [] header2={"序号","承办单位","原告/申请人/第三方","被申请人","我方诉讼地位（申请人/被申请人）","受理法院/仲裁机构","受案单位所在地(省份/城市)","承办法官/仲裁员",
				 "涉案金额（万元）","案件类型","案由","基本案情", "是否为重大案件(根据集团重大案件标准)","是否清案遗留案件","是否新增案件(回头看)","是否因人员优化工作引发案件","调处阶段",
				"最新进展","合作开始时间（示例：XX年XX月XX日）","纠纷发生时间（示例：XX年XX月XX日）","业务部门解决纠纷的过程","成诉原因",
				 "案发时间（示例：XX年XX月XX日）","案龄（年）","判决/调解金额(万元)","已执行款项(人民币万元)","避免/挽回经济损失(人民币万元)","承办法务","案件调处责任人","外聘律所及律师","律师费总额(人民币万元)", "已支付金额(人民币万元)",
				 "对方外聘律所及律师","是否问责","是否进行/完成成因分析","判决结果",
				 "案件成因（实业）","拟问责建议（若无需问责，请说明具体原因）（实业）","问责进展情况（含问责人数及名单）（实业）","办案思路、调处重点及工作计划（实业）","协办人（实业）","双方保全情况（实业）","一案双处实施情况 （实业）","规划结案时间","裁判/调解/和解主文（实业）","备注(实业)"
		 };
		 String [] header3={"序号","承办单位","原告/申请人/第三方","被告/被申请人/第三人","我方诉讼地位（主诉/被诉）","受理法院/仲裁机构","受案单位所在地(省份/城市)","承办法官/仲裁员",
				 "涉案金额（万元）","案件类型","案由","基本案情", "是否为重大案件(根据集团重大案件标准)","是否清案遗留案件","是否新增案件(回头看)","是否因人员优化工作引发案件","调处阶段",
				"最新进展","合作开始时间（示例：XX年XX月XX日）","纠纷发生时间（示例：XX年XX月XX日）","业务部门解决纠纷的过程","成诉原因",
				 "案发时间（示例：XX年XX月XX日）","案龄（年）","判决/调解金额(人民币万元)","已执行款项(人民币万元)","避免/挽回经济损失(人民币万元)","承办法务","案件调处责任人","外聘律所及律师","律师费总额(人民币万元)", "已支付金额(人民币万元)",
				 "对方外聘律所及律师","是否问责","是否进行/完成成因分析","判决结果",
				 "案件成因（实业）","拟问责建议（若无需问责，请说明具体原因）（实业）","问责进展情况（含问责人数及名单）（实业）","办案思路、调处重点及工作计划（实业）","协办人（实业）","双方保全情况（实业）","一案双处实施情况 （实业）","裁判/调解/和解主文（实业）","结案时间及结案报告公文号（实业）","备注(实业)"
		 };
		 String[] header=null;
		 if(1==type){
			 header=header1;
		 }else if (2==type) {
			  header=header2;
		}else {
			  header=header3;
		}
		
		String ids=caseDao.getcivilCaseNewWeekIDs();
		List<BimrCivilcaseWeek> oldlist=caseDao.getcivilCaseNewWeek(ids);
		
		 
		 XSSFRow row = sheet.createRow((int) count);  
		 XSSFCell cell = null;
		 for (int i = 0; i < header.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(header[i]);
				cell.setCellStyle(styleMap.get("head1style"));
		}
		/* style.setFillForegroundColor(IndexedColors.RED.getIndex());  
	        style.setFillPattern(CellStyle.SOLID_FOREGROUND); */
		 font1.setColor(IndexedColors.RED.index);
		 style.setFont(font1);
		 for (int i = 0; i < list.size(); i++) { 
//			 所有的数据的长度
			 	int k=0;
			 	BimrCivilcaseWeek oldvalue=null;
			     row = sheet.createRow(count+(int) i + 1);
			     BimrCivilcaseWeek value = list.get(i);
			     
			     
			     for (BimrCivilcaseWeek b : oldlist) {
			    	 if(null==value.getCaseNum()||"".equals(value.getCaseNum())){
			    		 break;
			    	 }
						if(b.getCaseNum().equals(value.getCaseNum())){
							oldvalue=b;
							break;
						}
					}
			     
			     
			     
			     
			   /*  List civilcaseList = getCaseByCaseNum("1", value.getCaseNum());
			        if(civilcaseList.size() > 1){
			        	for(int j=0;j<civilcaseList.size();j++){
			        		BimrCivilcaseWeek entity_1 = (BimrCivilcaseWeek)civilcaseList.get(j);
			            	if(entity_1.getIsHistory().equals("0")){
			            		continue;
			            	}
			            	else if(entity_1.getVersion().equals("")){
			            		continue;
			            	}
			            	oldvalue = entity_1;
			        	}
			        }*/
			     
			     
			     
			     
			     
			   
		//	     row.setRowStyle(style);
//			     row.createCell((short)k++).setCellValue(i+1);
			     
			     //row.createCell((short)k++).setCellValue(i+1);
			    // 序号1
			     cell = row.createCell((short)k++);
			     cell.setCellValue(i+1);
	//		     cell.setCellStyle(style);
//		    	
			     //承办单位
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getVcCompanyName());
			     if(null!=oldvalue){
			    	 if(null!=value.getVcCompanyName()){
			    		 if(!value.getVcCompanyName().equals(oldvalue.getVcCompanyName())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//		    	 原告    
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getPlaintiff());
			     if(null!=oldvalue){
			    	 if(null!=value.getPlaintiff()){
			    		 if(!value.getPlaintiff().equals(oldvalue.getPlaintiff())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//		    	 被告
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getDefendant());
			     if(null!=oldvalue){
			    	 if(null!=value.getDefendant()){
			    		 if(!value.getDefendant().equals(oldvalue.getDefendant())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
			     //	 	我方诉讼地位
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getDefendant());
			     if (value.getLitigation().equals("1")==true) {
			    	 cell.setCellValue("主诉");
				}else {
					cell.setCellValue("被诉");
				}
			     if(null!=oldvalue){
			    	 if(null!=value.getDefendant()){
			    		 if(!value.getDefendant().equals(oldvalue.getDefendant())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
		   
//	     		受理法院
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getAdmissibleCourt());
			     if(null!=oldvalue){
			    	 if(null!=value.getAdmissibleCourt()){
			    		 if(!value.getAdmissibleCourt().equals(oldvalue.getAdmissibleCourt())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//		    	 受案单位所在地	   
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getProvince());
			     if(null!=oldvalue){
			    	 if(null!=value.getProvince()){
			    		 if(!value.getProvince().equals(oldvalue.getProvince())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     承办法官/仲裁员
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getJudge());
			     if(null!=oldvalue){
			    	 if(null!=value.getJudge()){
			    		 if(!value.getJudge().equals(oldvalue.getJudge())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//		    	 涉案金额
			     cell=row.createCell((short)k++);
			     if(null!=value.getAmount()){
			    	 cell.setCellValue(value.getAmount().doubleValue());
			     }else{
			    	 cell.setCellValue("");
			     }
			     if(null!=oldvalue){
			    	 if(null!=value.getAmount()){
			    		 if(value.getAmount().compareTo(oldvalue.getAmount())!=0){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
			    
			     
//		    	 案件类型
			     cell=row.createCell((short)k++);
			     if (value.getType().equals("1")) {
			    	 cell.setCellValue("合同纠纷");
				}else if (value.getType().equals("2")) {
					cell.setCellValue("劳动纠纷");
				}else if (value.getType().equals("3")) {
					cell.setCellValue("侵权纠纷");
				}else if (value.getType().equals("4")) {
					cell.setCellValue("行政纠纷");
				}else if (value.getType().equals("5")) {
					cell.setCellValue("其他纠纷");
				}else {
					cell.setCellValue("");
				}
			     if(null!=oldvalue){
			    	 if(null!=value.getType()){
			    		 if(!value.getType().equals(oldvalue.getType())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     案由
			     cell=row.createCell((short)k++);
			     List<HhBase> reasonlist = baseService.getBases(Base.CASE_REASON);
			     if(value.getType().equals("1")){
			    	 int n=Integer.parseInt(value.getReason());
			    	 for (HhBase hhBase : reasonlist) {
						if(n==hhBase.getNum()){
							cell.setCellValue(hhBase.getDescription());
							break;
						}
					}
			     }else {
			    	 cell.setCellValue(value.getReason());
				}
			     if(null!=oldvalue){
			    	 if(null!=value.getReason()){
			    		 if(!value.getReason().equals(oldvalue.getReason())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
			     
			    
//			     基本案情	
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getBaseMessage());
			     if(null!=oldvalue){
			    	 if(null!=value.getBaseMessage()){
			    		 if(!value.getBaseMessage().equals(oldvalue.getBaseMessage())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//		    	是否为重大案件
			     cell=row.createCell((short)k++);
			     if (value.getIsMajorCase().equals("1")==true) {
			    	 cell.setCellValue("是");
				}else{
					cell.setCellValue("否");
				}
			     if(null!=oldvalue){
			    	 if(null!=value.getIsMajorCase()){
			    		 if(!value.getIsMajorCase().equals(oldvalue.getIsMajorCase())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
			     //是否清案遗留案件	
			     cell=row.createCell((short)k++);
				    if(null!=value.getIsLeftoverCases()){
				    	 if (value.getIsLeftoverCases().equals("1")==true) {
				    		 cell.setCellValue("是");
						}else{
							cell.setCellValue("否");
						}
				    }else {
				    	cell.setCellValue("");
					}
				    if(null!=oldvalue){
				    	if(null!=value.getIsLeftoverCases()){
				    		if(!value.getIsLeftoverCases().equals(oldvalue.getIsLeftoverCases())){
					    		 cell.setCellStyle(style);
					    	 }
				    	}
				     }
				    
				     //"是否新 增案件(回头看)"
				    cell=row.createCell((short)k++);
				    if(null!=value.getIsNewadd()){
				    	 if (value.getIsNewadd().equals("1")==true) {
				    		 cell.setCellValue("是");
						}else{
							cell.setCellValue("否");
						}
				    }else {
				    	cell.setCellValue("");
					}
				    if(null!=oldvalue){
				    	if(null!=value.getIsNewadd()){
				    		if(!value.getIsNewadd().equals(oldvalue.getIsNewadd())){
					    		 cell.setCellStyle(style);
					    	 }
				    	}
				     }
				     //是否因人员优化工作引发案件
				    cell=row.createCell((short)k++);
				    if (null!=value.getIsStaffOptimization()) {
				    	 if (value.getIsStaffOptimization().equals("1")==true) {
				    		 cell.setCellValue("是");
						}else{
							cell.setCellValue("否");
						}
					}else {
						cell.setCellValue("");
					}
				    if(null!=oldvalue){
				    	if(null!=value.getIsStaffOptimization()){
				    		 if(!value.getIsStaffOptimization().equals(oldvalue.getIsStaffOptimization())){
					    		 cell.setCellStyle(style);
					    	 }
				    	}
				     }
//		   		  调处阶段
				    cell=row.createCell((short)k++);
			     if (value.getMediatingStage().equals("1")) {
			    	 cell.setCellValue("一审");
				}else if (value.getMediatingStage().equals("2")) {
					cell.setCellValue("二审");
				}else if (value.getMediatingStage().equals("3")) {
					cell.setCellValue("再审");
				}else if (value.getMediatingStage().equals("4")) {
					cell.setCellValue("执行");
				}else  {
					cell.setCellValue("结案");
				}
			     if(null!=oldvalue){
			    	 if(null!=value.getMediatingStage()){
			    		 if(!value.getMediatingStage().equals(oldvalue.getMediatingStage())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     最新进展
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getLastProgress());
			     if(null!=oldvalue){
			    	 if(null!=value.getLastProgress()){
			    		 if(!value.getLastProgress().equals(oldvalue.getLastProgress())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     合作开始时间
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getCooperationDate());
			     if(null!=oldvalue){
			    	 if(null!=value.getCooperationDate()){
			    		 if(!value.getCooperationDate().equals(oldvalue.getCooperationDate())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     纠纷发生时间
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getDisputeDate());
			     if(null!=oldvalue){
			    	 if(null!=value.getDisputeDate()){
			    		 if(!value.getDisputeDate().equals(oldvalue.getDisputeDate())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     业务部门解决纠纷的过程
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getDealDisputeProcess());
			     if(null!=oldvalue){
			    	 if(null!=value.getDealDisputeProcess()){
			    		 if(!value.getDealDisputeProcess().equals(oldvalue.getDealDisputeProcess())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     成诉原因
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getCaseCause());
			     if(null!=oldvalue){
			    	 if(null!=value.getCaseCause()){
			    		 if(!value.getCaseCause().equals(oldvalue.getCaseCause())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     案发时间
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getCaseDate());
			     if(null!=oldvalue){
			    	 if(null!=value.getCaseDate()){
			    		 if(!value.getCaseDate().equals(oldvalue.getCaseDate())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
			     //			案龄（年）
			     cell=row.createCell((short)k++);
			     if(null!=value.getCaseAge()){
			    	 cell.setCellValue(value.getCaseAge());
			     }else {
			    	 cell.setCellValue("");
				}
			     if(null!=oldvalue){
			    	 if(null!=value.getCaseAge()){
			    		 if(!value.getCaseAge().equals(oldvalue.getCaseAge())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
			     
			     if(type==2||type==3){
//				     判决/调解金额(人民币万元)
			    	 cell=row.createCell((short)k++);
				     if(null!=value.getVerdictAmount()){
				    	 cell.setCellValue(value.getVerdictAmount().doubleValue());
				     }else{
				    	 cell.setCellValue("");
				     }
				     if(null!=oldvalue){
				    	 if(null==oldvalue.getVerdictAmount()){
				    		 cell.setCellStyle(style);
				    	 }else
				    	 if(null!=value.getVerdictAmount()){
				    		 if(value.getVerdictAmount().compareTo(oldvalue.getVerdictAmount())!=0){
					    		 cell.setCellStyle(style);
					    	 }
				    	 }
				     }
//				     已执行款项(人民币万元)
				     cell=row.createCell((short)k++);
				     if(null!=value.getImplementMoney()){
				    	 cell.setCellValue(value.getImplementMoney().doubleValue());
				     }else{
				    	 cell.setCellValue("");
				     }
				     if(null!=oldvalue){
				    	 if(null==oldvalue.getImplementMoney()){
				    		 cell.setCellStyle(style);
				    	 }else
				    	 if(null!=value.getImplementMoney()){
				    		 if(value.getImplementMoney().compareTo(oldvalue.getImplementMoney())!=0){
					    		 cell.setCellStyle(style);
					    	 }
				    	 }
				     }
//				     避免/挽回经济损失(人民币万元)	
				     cell=row.createCell((short)k++);
				     if(null!=value.getSaveLoss()){
				    	 cell.setCellValue(value.getSaveLoss().doubleValue());
				     }else{
				    	 cell.setCellValue("");
				     }
				     if(null!=oldvalue){
				    	 if(null==oldvalue.getSaveLoss()){
				    		 cell.setCellStyle(style);
				    	 }else
				    	 if(null!=value.getSaveLoss()){
				    		 if(value.getSaveLoss().compareTo(oldvalue.getSaveLoss())!=0){
					    		 cell.setCellStyle(style);
					    	 }
				    	 }
				     }
			     }
//			     承办法务
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getLawWork());
			     if(null!=oldvalue){
			    	 if(null!=value.getLawWork()){
			    		 if(!value.getLawWork().equals(oldvalue.getLawWork())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			            案件调处负责人
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getResponsiblePerson());
			     if(null!=oldvalue){
			    	 if(null!=value.getResponsiblePerson()){
			    		 if(!value.getResponsiblePerson().equals(oldvalue.getResponsiblePerson())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//				    外聘律所及律师
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getExternalLaws());
			     if(null!=oldvalue){
			    	 if(null!=value.getExternalLaws()){
			    		 if(!value.getExternalLaws().equals(oldvalue.getExternalLaws())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     律师费总额(人民币万元)
			     cell=row.createCell((short)k++);
			     if(null!=value.getLawsAmount()){
			    	 //是否为数字
			    	 if(Common.canparseNumber(value.getLawsAmount())){
			    		 cell.setCellValue(Double.parseDouble(value.getLawsAmount()));
			    	 }else {
			    		 cell.setCellValue(value.getLawsAmount().toString());
					}
			    	
			     }else{
			    	 cell.setCellValue("");
			     }
			     if(null!=oldvalue){
			    	 if(null==oldvalue.getLawsAmount()){
			    		 cell.setCellStyle(style);
			    	 }else
			    	 if(null!=value.getLawsAmount()){
			    		 if(value.getLawsAmount().compareTo(oldvalue.getLawsAmount())!=0){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     已支付金额(人民币万元)
			     cell=row.createCell((short)k++);
			     if(null!=value.getPayAmount()){
			    	 cell.setCellValue(value.getPayAmount().doubleValue());
			     }else{
			    	 cell.setCellValue("");
			     }
			     if(null!=oldvalue){
			    	 if(null==oldvalue.getPayAmount()){
			    		 cell.setCellStyle(style);
			    	 }else
			    	 if(null!=value.getPayAmount()){
			    		 if(value.getPayAmount().compareTo(oldvalue.getPayAmount())!=0){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     对方外聘律所及律师
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getForeignLaw());
			     if(null!=oldvalue){
			    	 if(null!=value.getForeignLaw()){
			    		 if(!value.getForeignLaw().equals(oldvalue.getForeignLaw())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
			     if (type==1) {
				     //			     预测结果 
			    	 cell=row.createCell((short)k++);
			    	 cell.setCellValue(value.getPredictionResluts());
			    	 if(null!=oldvalue){
			    		 if(null!=value.getPredictionResluts()){
			    			 if(!value.getPredictionResluts().equals(oldvalue.getPredictionResluts())){
					    		 cell.setCellStyle(style);
					    	 }
			    		 }
				     }
//					     调处时间计划表     
			    	 cell=row.createCell((short)k++);
			    	 cell.setCellValue(value.getPlanTime());
			    	 if(null!=oldvalue){
			    		 if(null!=value.getPlanTime()){
			    			 if(!value.getPlanTime().equals(oldvalue.getPlanTime())){
					    		 cell.setCellStyle(style);
					    	 }
			    		 }
				     }
//				     预计结果
			    	 cell=row.createCell((short)k++);
			    	 cell.setCellValue(value.getExpectedResults());
			    	 if(null!=oldvalue){
			    		 if(null!=value.getExpectedResults()){
			    			 if(!value.getExpectedResults().equals(oldvalue.getExpectedResults())){
					    		 cell.setCellStyle(style);
					    	 }
			    		 }
				     }
			     }
//			     是否问责
			     cell=row.createCell((short)k++);
			      if (value.getIsAccountability().equals("1")==true) {
			    	  cell.setCellValue("是");
				}else {
					cell.setCellValue("否");
				}
			      if(null!=oldvalue){
			    	  if(null!=value.getIsAccountability()){
			    		  if(!value.getIsAccountability().equals(oldvalue.getIsAccountability())){
					    		 cell.setCellStyle(style);
					    	 }
			    	  }
				  }
//				     是否进行/完成成因分析   
			      cell=row.createCell((short)k++);
			      if (value.getIsAnalysis().equals("1")==true) {
			    	  cell.setCellValue("是");
				}else {
					cell.setCellValue("否");
				}
			      if(null!=oldvalue){
			    	  if(null!=value.getIsAnalysis()){
			    		  if(!value.getIsAnalysis().equals(oldvalue.getIsAnalysis())){
					    		 cell.setCellStyle(style);
					    	 }
			    	  }
				  }
			     if (type==2||type==3) {
//				     判决结果
			    	 cell=row.createCell((short)k++);
			    	 cell.setCellValue(value.getVerdictResult());
			    	 if(null!=oldvalue){
			    		 if(null!=value.getVerdictResult()){
			    			 if(!value.getVerdictResult().equals(oldvalue.getVerdictResult())){
					    		 cell.setCellStyle(style);
					    	 }
			    		 }
			    	 }
				}else {
					cell=row.createCell((short)k++);
					cell.setCellValue("");
				}
			     //案件成因（物流）
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getAnalysisCause());
			     if(null!=oldvalue){
			    	 if(null!=value.getAnalysisCause()){
			    		 if(null!=value.getAnalysisCause()){
			    			 if(!value.getAnalysisCause().equals(oldvalue.getAnalysisCause())){
					    		 cell.setCellStyle(style);
					    	 }
			    		 }
			    	 }
			    	 
		    	 }
			   //拟问责建议（若无需问责，请说明具体原因）（实业）
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getAccountabilitySuggest());
			     if(null!=oldvalue){
			    	 if(null!=value.getAccountabilitySuggest()){
			    		 if(null!=value.getAccountabilitySuggest()){
			    			 if(!value.getAccountabilitySuggest().equals(oldvalue.getAccountabilitySuggest())){
					    		 cell.setCellStyle(style);
					    	 }
			    		 }
			    	 }
			    	 
		    	 }
			     //问责进展情况（含问责人数及名单）（实业）
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getAccountabilityResults());
			     if(null!=oldvalue){
			    	 if(null!=value.getAccountabilityResults()){
			    		 if(!value.getAccountabilityResults().equals(oldvalue.getAccountabilityResults())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
		    	 }
			     //办案思路、调处重点及工作计划（物流）
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getCaseThoughtFocalPoint());
			     if(null!=oldvalue){
			    	 if(null!=value.getCaseThoughtFocalPoint()){
			    		 if(!value.getCaseThoughtFocalPoint().equals(oldvalue.getCaseThoughtFocalPoint())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
		    	 }
			     //协办人（物流）	
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getAssistingPeople());
			     if(null!=oldvalue){
			    	 if(null!=value.getAssistingPeople()){
			    		 if(!value.getAssistingPeople().equals(oldvalue.getAssistingPeople())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
		    	 }
			     //双方保全情况（物流）	
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getBothPreserveSituation());
			     if(null!=oldvalue){
			    	 if(null!=value.getBothPreserveSituation()){
			    		 if(!value.getBothPreserveSituation().equals(oldvalue.getBothPreserveSituation())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
		    	 }
			     
			   //一案双处实施情况 （实业）	
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getTwoCasesImplementation());
			     if(null!=oldvalue){
			    	 if(null!=value.getTwoCasesImplementation()){
			    		 if(!value.getTwoCasesImplementation().equals(oldvalue.getTwoCasesImplementation())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
		    	 }
			    
			     if(type==1||type==2){
			    	 //规划结案时间
			    	 cell=row.createCell((short)k++);
				     cell.setCellValue(value.getCasePlanDate());
				     if(null!=oldvalue){
				    	 if(null!=value.getCasePlanDate()){
				    		 if(!value.getCasePlanDate().equals(oldvalue.getCasePlanDate())){
					    		 cell.setCellStyle(style);
					    	 }
				    	 }
			    	 }
			     }
			     if (type==3||type==2) {
			    	//裁判/调解/和解主文（物流）
			    	 cell=row.createCell((short)k++);
				     cell.setCellValue(value.getReconciliationDocument());
				     if(null!=oldvalue){
				    	 if(null!=value.getReconciliationDocument()){
				    		 if(!value.getReconciliationDocument().equals(oldvalue.getReconciliationDocument())){
					    		 cell.setCellStyle(style);
					    	 }
				    	 }
			    	 }
				}
			     if (type==3) {
			    	 // 结案时间及结案报告公文号（物流）
			    	 cell=row.createCell((short)k++);
				     cell.setCellValue(value.getClosingNumber());
				     if(null!=oldvalue){
				    	 if(null!=value.getClosingNumber()){
				    		 if(!value.getClosingNumber().equals(oldvalue.getClosingNumber())){
					    		 cell.setCellStyle(style);
					    	 }
				    	 }
			    	 }
				}
//			      备注1	
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getRemark1());
			     if(null!=oldvalue){
			    	 if(null!=value.getRemark1()){
			    		 if(!value.getRemark1().equals(oldvalue.getRemark1())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
		    	 }
		 }
		 
	}
	@Override
	public List<BimrCriminalcaseWeek> getCriminalcaseNewWeek(String ids) {
		// TODO Auto-generated method stub
		return caseDao.getCriminalcaseNewWeek(ids);
	}
	@Override
	public String getCriminalCaseNewWeekIDs() {
		// TODO Auto-generated method stub
		return caseDao.getCriminalCaseNewWeekIDs();
	}
	/**
	 * 创建样式
	 * @param workBook
	 * @return
	 */
	private Map<String, XSSFCellStyle> createStyles(XSSFWorkbook workBook){
		Map<String, XSSFCellStyle> styles = new HashMap<String, XSSFCellStyle>();
		
		XSSFCellStyle style;
		//创建样式1 
		style = workBook.createCellStyle();
		//对齐方式
		style.setAlignment(HorizontalAlignment.CENTER);//水平居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
		style.setWrapText(true);//自动换行
		//字体
		XSSFFont font = workBook.createFont();
		font.setFontName("微软雅黑");//设置字体名称
		font.setFontHeightInPoints((short)16);//设置字号
		font.setBold(true);
		style.setFont(font);
		styles.put("title_style", style);
		
		//创建样式2 
		style = workBook.createCellStyle();
		//边框
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		//对齐方式
		style.setAlignment(HorizontalAlignment.CENTER);//水平居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
		style.setWrapText(true);//自动换行
		//字体
		XSSFFont font1 = workBook.createFont();
		font1.setFontName("微软雅黑");//设置字体名称
		font1.setFontHeightInPoints((short)9);//设置字号
		font1.setBold(true);
		style.setFont(font1);
		//背景颜色
		style.setFillForegroundColor(new XSSFColor(new java.awt.Color(198, 239, 243)));
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    styles.put("head1style", style);
	    
	    //创建样式2 
		style = workBook.createCellStyle();
		//边框
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		//对齐方式
		style.setAlignment(HorizontalAlignment.CENTER);//水平居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
		style.setWrapText(true);//自动换行
		style.setFont(font1);
		//背景颜色
		style.setFillForegroundColor(new XSSFColor(new java.awt.Color(186, 230, 201)));
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    styles.put("head2style", style);
		
		
		//创建样式3 
		style=workBook.createCellStyle();
		//边框
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		//对齐方式
		style.setAlignment(HorizontalAlignment.CENTER);//水平居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
		style.setWrapText(true);//自动换行
		//字体
		XSSFFont font2 = workBook.createFont();
		font2.setFontName("微软雅黑");//设置字体名称
		font2.setFontHeightInPoints((short)9);//设置字号
		style.setFont(font2);
		styles.put("valuestyle", style);
		
		//创建样式4 
				style = workBook.createCellStyle();
				//对齐方式
				style.setAlignment(HorizontalAlignment.LEFT);//水平居中
				style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
				style.setWrapText(true);//自动换行
				//字体
				XSSFFont font4 = workBook.createFont();
				font4.setFontName("微软雅黑");//设置字体名称
				font4.setFontHeightInPoints((short)15);//设置字号
				font4.setBold(true);
				style.setFont(font4);
				styles.put("title_style4", style);
		
		return styles;
	}
	@Override
	public List getLastWeekData(String year) {
		// TODO Auto-generated method stub
		return caseDao.getLastWeekData(year);
	}
	@Override
	public List<BimrCivilcaseWeek> getcivilNewCaseExport(String ids, int type) {
		// TODO Auto-generated method stub
		return caseDao.getcivilNewCaseExport(ids,type);
	}
	
	@Override
	public List<BimrCriminalcaseWeek> getCriminalNewCaseExport(String ids, int type) {
		// TODO Auto-generated method stub
		return caseDao.getCriminalNewCaseExport(ids,type);
	}
	@Override
	public List getCaseReportExport(BimrCaseQuery caseQuery,int type) {
		// TODO Auto-generated method stub
		return caseDao.getCaseReportExport(caseQuery, type);
	}
	
}
