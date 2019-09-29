package com.softline.controller.bimr;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.common.DES;
import com.softline.dao.bimr.impl.CaseDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.MainFinancialIndicator;
import com.softline.entity.PortalMsg;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.SysExamine;
import com.softline.entity.bimr.BimrCaseQuery;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrCriminalcaseWeek;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.service.bimr.ICaseService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/bimr/case")
public class CaseController {

	@Resource(name = "caseService")
	private ICaseService caseService;

	@Resource(name = "commonService")
	private ICommonService commonService;

	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
    @Resource(name = "selectUserService")
    private ISelectUserService selectUserService;
    
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name="baseService")
	private IBaseService baseService;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//民事案件
	@RequestMapping(value = "/civilcaseList")
	public String civilcasePagelist(BimrCivilcaseWeek entity, Map<String, Object> map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        if(!Common.notEmpty(entity.getVcCompanyId())){
        	entity.setDataauth(dataauth);
        }
        
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        //只能查询自己上报的数据
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        //end
		MsgPage msgPage = caseService.findCivilcasePageList(entity, pageNum, Base.pagesize, user, "1");
		map.put("msgPage", msgPage);
		String mapurl = request.getContextPath()+ "/bimr/case";
		map.put("mapurl", mapurl);
	    map.put("searchurl", "/shanghai-gzw/bimr/case/civilcaseList");
	    map.put("entity", entity);
		return "/bimr/case/civilcaseWeekList";
	}
	
	//新增民事案件
	@RequestMapping(value = "/addCivilcase")
	public String addCivilcase(Map<String, Object> map, HttpServletRequest request) {
		BimrCivilcaseWeek entity = new BimrCivilcaseWeek();
        map.put("entity", entity);
        HttpSession session = request.getSession();
        //String company = (String)session.getAttribute("gzwsession_company");
        List<HhBase> reasonlist = baseService.getBases(Base.CASE_REASON);
        String company = Base.HRTop;
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        map.put("op", "addNew");
        map.put("reasonlist", reasonlist);
        
		return "/bimr/case/civilcaseWeekModify";
	}
	
	//保存民事案件
	@ResponseBody
	@RequestMapping(value = "/saveCivilcase", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveCivilcase(BimrCivilcaseWeek entity, Map<String, Object> map, HttpServletRequest request,@RequestParam(value="indictmentFile",required=true) MultipartFile[] indictmentFile,@RequestParam(value="judgmentFile",required=true) MultipartFile[] judgmentFile,@RequestParam(value="oFile",required=true) MultipartFile[] oFile) {
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setCaseCategory("1");
		entity.setIsDel("0");
		entity.setApprovalState("0");//暂存
		entity.setIsHistory("0");
		entity.setIsLastest("1");
		if(entity.getId() == null){
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			entity.setCreateDate(df.format(new Date()));
		}
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(user.getVcName());
		entity.setLastModifyPersonName(user.getVcEmployeeId());
		caseService.updateLasterCaseState("1",null,entity,indictmentFile,judgmentFile,oFile);
		return "success";
	}
	
	//更新民事案件
	@RequestMapping(value = "/updateCivilcase")
	public String updateCivilcase(String id, Map<String, Object> map, HttpServletRequest request) {
		BimrCivilcaseWeek entity = caseService.getCivilcaseById(Integer.parseInt(id));
        map.put("entity", entity);
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        map.put("op", "update");
        List<HhFile> indictmentFile = new ArrayList<HhFile>();
        List<HhFile> judgmentFile = new ArrayList<HhFile>();
        List<HhFile> oFile = new ArrayList<HhFile>();
        if (Common.notEmpty(entity.getIndictment())) {
    		String[] indictments=entity.getIndictment().split(",");
        	for (int i = 0; i < indictments.length; i++) {
        		if (Common.notEmpty(indictments[i])) {
        		indictmentFile.add(commonService.getFileByUuid(indictments[i]));
        		}
			}
		}
        if (Common.notEmpty(entity.getJudgment())) {
    		String[] judgments=entity.getJudgment().split(",");
        	for (int i = 0; i < judgments.length; i++) {
        		if (Common.notEmpty(judgments[i])&&null!=commonService.getFileByUuid(judgments[i])) {
        			judgmentFile.add(commonService.getFileByUuid(judgments[i]));
        		}	
			}
		}
        if (Common.notEmpty(entity.getOtherFile())) {
    		String[] otherments=entity.getOtherFile().split(",");
        	for (int i = 0; i < otherments.length; i++) {
        		if (Common.notEmpty(otherments[i])) {
            		oFile.add(commonService.getFileByUuid(otherments[i]));
				}
			}
		}
        if(null!=indictmentFile&&indictmentFile.size()!=0){
        	map.put("indictmentFile", indictmentFile);
        }else{
        	map.put("indictmentFile", null);
        }
        if(null!=judgmentFile&&judgmentFile.size()!=0){
        	map.put("judgmentFile", judgmentFile);
        }else{
        	map.put("judgmentFile", null);
        }
        if(null!=oFile&&oFile.size()!=0){
        	map.put("oFile", oFile);
        }else{
        	map.put("oFile", null);
        }
        List<HhBase> reasonlist = baseService.getBases(Base.CASE_REASON);
        map.put("reasonlist", reasonlist);
		return "/bimr/case/civilcaseWeekModify";
	}
	
	//查看民事案件详情
	@RequestMapping(value = "/viewCivilcase")
	public String viewCivilcase(String id, Map<String, Object> map, HttpServletRequest request) {
		BimrCivilcaseWeek entity = caseService.getCivilcaseById(Integer.parseInt(id));
        map.put("entity", entity);
        //获取案件历史审核记录
        List<SysExamine> examineList = examineService.getListExamine(entity.getId(), 50149);
        map.put("examineList", examineList);
        List<HhFile> indictmentFile = new ArrayList<HhFile>();
        List<HhFile> judgmentFile = new ArrayList<HhFile>();
        List<HhFile> oFile = new ArrayList<HhFile>();
        if (null!=entity.getIndictment()) {
    		String[] indictments=entity.getIndictment().split(",");
        	for (int i = 0; i < indictments.length; i++) {
        		indictmentFile.add(commonService.getFileByUuid(indictments[i]));
			}
		}
        if (null!=entity.getJudgment()) {
    		String[] judgments=entity.getJudgment().split(",");
        	for (int i = 0; i < judgments.length; i++) {
        		judgmentFile.add(commonService.getFileByUuid(judgments[i]));
			}
		}
        if (null!=entity.getOtherFile()) {
    		String[] otherments=entity.getOtherFile().split(",");
        	for (int i = 0; i < otherments.length; i++) {
        		oFile.add(commonService.getFileByUuid(otherments[i]));
			}
		}
        if(null!=indictmentFile&&indictmentFile.size()!=0){
        	map.put("indictmentFile", indictmentFile);
        }else{
        	map.put("indictmentFile", null);
        }
        if(null!=judgmentFile&&judgmentFile.size()!=0){
        	map.put("judgmentFile", judgmentFile);
        }else{
        	map.put("judgmentFile", null);
        }
        if(null!=oFile&&oFile.size()!=0){
        	map.put("oFile", oFile);
        }else{
        	map.put("oFile", null);
        }
        List<HhBase> reasonlist = baseService.getBases(Base.CASE_REASON);
        map.put("reasonlist", reasonlist);
		return "/bimr/case/civilcaseWeekView";
	}
	
	//提交审核民事案件
	@ResponseBody
	@RequestMapping(value = "/commitCivilcase", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String commitCivilcase(BimrCivilcaseWeek entity, Map<String, Object> map, HttpServletRequest request,@RequestParam(value="indictmentFile",required=true) MultipartFile[] indictmentFile,@RequestParam(value="judgmentFile",required=true) MultipartFile[] judgmentFile,@RequestParam(value="oFile",required=true) MultipartFile[] oFile) {
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setIsDel("0");
		entity.setCaseCategory("1");
		entity.setApprovalState("1");//审核中
		entity.setIsHistory("0");
		entity.setIsLastest("1");
		if(entity.getId() == null){
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			entity.setCreateDate(df.format(new Date()));
		}
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(user.getVcName());
		entity.setLastModifyPersonName(user.getVcEmployeeId());
		entity.setReportPersonId(user.getVcEmployeeId());
		entity.setReportPersonName(user.getVcName());
		entity.setReportDate(df.format(new Date()));
		//commonService.saveOrUpdate(entity);
		caseService.updateLasterCaseState("1",null,entity,indictmentFile,judgmentFile,oFile);
		PortalMsg msg = new PortalMsg();
		msg.setTitle("民事案件需要审核！");
		msg.setBusiness("bimWork_bimr_civilcase");
		msg.setDate(df.format(new Date()));
		msg.setDescription("风控民事案件需要审核！");
		msg.setTableName("bimr_civilcase_week");
		msg.setRecordId(entity.getId().toString());
		msg.setDelFlag(0);
		msg.setType(0);
		potalMsgService.savePortalMsg(msg);
		return "success";
	}
	
	//变更民事案件
	@RequestMapping(value = "/exchangeCivilcase")
	public String exchangeCivilcase(String id, Map<String, Object> map, HttpServletRequest request) {
		BimrCivilcaseWeek entity = caseService.getCivilcaseById(Integer.parseInt(id));
		entity.setVersion(null);
		entity.setCreateDate(null);
		entity.setCreatePersonId(null);
		entity.setCreatePersonName(null);
		entity.setLastModifyDate(null);
		entity.setLastModifyPersonId(null);
		entity.setLastModifyPersonName(null);
		entity.setReportPersonId(null);
		entity.setReportPersonName(null);
		entity.setReportDate(null);
		entity.setAuditorDate(null);
		entity.setAuditorPersonId(null);
		entity.setAuditorPersonName(null);
		entity.setId(null);
		entity.setIsLastest(null);
        map.put("entity", entity);
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        List<HhFile> indictmentFile = new ArrayList<HhFile>();
        List<HhFile> judgmentFile = new ArrayList<HhFile>();
        List<HhFile> oFile = new ArrayList<HhFile>();
        if (null!=entity.getIndictment()) {
    		String[] indictments=entity.getIndictment().split(",");
        	for (int i = 0; i < indictments.length; i++) {
        		indictmentFile.add(commonService.getFileByUuid(indictments[i]));
			}
		}
        if (null!=entity.getJudgment()) {
    		String[] judgments=entity.getJudgment().split(",");
        	for (int i = 0; i < judgments.length; i++) {
        		judgmentFile.add(commonService.getFileByUuid(judgments[i]));
			}
		}
        if (null!=entity.getOtherFile()) {
    		String[] otherments=entity.getOtherFile().split(",");
        	for (int i = 0; i < otherments.length; i++) {
        		oFile.add(commonService.getFileByUuid(otherments[i]));
			}
		}
        if(null!=indictmentFile&&indictmentFile.size()!=0){
        	map.put("indictmentFile", indictmentFile);
        }else{
        	map.put("indictmentFile", null);
        }
        if(null!=judgmentFile&&judgmentFile.size()!=0){
        	map.put("judgmentFile", judgmentFile);
        }else{
        	map.put("judgmentFile", null);
        }
        if(null!=oFile&&oFile.size()!=0){
        	map.put("oFile", oFile);
        }else{
        	map.put("oFile", null);
        }
        List<HhBase> reasonlist = baseService.getBases(Base.CASE_REASON);
        map.put("reasonlist", reasonlist);
		return "/bimr/case/civilcaseWeekModify";
	}
	
	@RequestMapping(value = "/delCivilcase", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String delCivilcase(BimrCivilcaseWeek entity, String id, Map<String, Object> map, HttpServletRequest request) {
		caseService.deleteCivilcase(Integer.parseInt(id));
		return "redirect:/bimr/case/civilcaseList";
	}
	
	
		//民事案件导出
		
		@RequestMapping(value="/civilcaseExport")
		public void reportExport(BimrCivilcaseWeek entity,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String caseDateStart,String caseDateEnd,String amountSection) throws IOException, ParsePropertyException, InvalidFormatException {
				
			HttpSession session = request.getSession();
	        String company = (String)session.getAttribute("gzwsession_company");
	        //获取数据权限
	        String dataauth=systemService.getDataauth(company);
	        if(!Common.notEmpty(entity.getVcCompanyId())){
	        	entity.setDataauth(dataauth);
	        }else {
	        	entity.setVcCompanyId(systemService.getDataauth(entity.getVcCompanyId()));
			}
			List<BimrCivilcaseWeek> list1=new ArrayList<BimrCivilcaseWeek>();
			List<BimrCivilcaseWeek> list2=new ArrayList<BimrCivilcaseWeek>();
			List<BimrCivilcaseWeek> list3=new ArrayList<BimrCivilcaseWeek>();
		    list1=caseService.getcivilCaseExport(entity, caseDateStart, caseDateEnd, amountSection, 1);
		    list2=caseService.getcivilCaseExport(entity, caseDateStart, caseDateEnd, amountSection, 2);
		    list3=caseService.getcivilCaseExport(entity, caseDateStart, caseDateEnd, amountSection, 3);
				
		        
		        XSSFWorkbook workBook = new XSSFWorkbook();
//				
		        workBook=caseService.getcivilCaseExportWorkbook(list1, list2, list3);
					     
					     // 清空response
					        response.reset();
					        String _name = "民事案件台账.xlsx";
					        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					        String codedfilename = null;
							String agent = request.getHeader("USER-AGENT");
							if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
									&& -1 != agent.indexOf("Trident")) {// ie

								String name = java.net .URLEncoder.encode(_name, "UTF8");

								codedfilename = name;
							} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

								codedfilename = new String(_name.getBytes("UTF-8"),
										"iso-8859-1");
							}
					        response.setContentType("application/octet-stream");
					        response.setHeader("Content-Disposition", "attachment;filename=" + codedfilename);
					        workBook.write(toClient);
					        toClient.flush();
					        toClient.close(); 
				}
	
	
	//civilcaseQuery
	//民事案件查询
	@RequestMapping(value = "/civilcaseQuery")
	public String civilcaseQuery(BimrCivilcaseWeek entity, Map<String, Object> map, HttpServletRequest request, String caseDateStart,String caseDateEnd,String amountSection) {
		
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
       
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        String orgId=null;
        if(!Common.notEmpty(entity.getVcCompanyId())){
        	 String dataauth=systemService.getDataauth(company);
        	entity.setDataauth(dataauth);
        }else {
        	orgId=entity.getVcCompanyId();
        		entity.setVcCompanyId(systemService.getDataauth(entity.getVcCompanyId()));
		}
        //只能查询自己上报的数据
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        //end
        MsgPage msgPage = caseService.findCivilcaseQueryPageList(entity, pageNum, Base.pagesize, user,caseDateStart,caseDateEnd,amountSection);
        entity.setVcCompanyId(orgId);
		map.put("msgPage", msgPage);
		String mapurl = request.getContextPath()+ "/bimr/case";
		map.put("mapurl", mapurl);
	    map.put("searchurl", "/shanghai-gzw/bimr/case/civilcaseQuery");
	    map.put("entity", entity);
	    map.put("caseDateStart", caseDateStart);
	    map.put("caseDateEnd", caseDateEnd);
	    map.put("amountSection", amountSection);
		return "/bimr/case/civilcaseQueryList";
	}
	
	//民事案件审核列表
	@RequestMapping(value = "/civilcaseCheckList")
	public String civilcaseChecklist(BimrCivilcaseWeek entity, Map<String, Object> map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        if(!Common.notEmpty(entity.getVcCompanyId())){
        	entity.setDataauth(dataauth);
        }
        
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);

        //只能查询自己上报的数据
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        //end
		MsgPage msgPage = caseService.findCivilcasePageList(entity, pageNum, Base.pagesize, user, "2");
		map.put("msgPage", msgPage);
		String mapurl = request.getContextPath()+ "/bimr/case";
		map.put("mapurl", mapurl);
	    map.put("searchurl", "/shanghai-gzw/bimr/case/civilcaseCheckList");
	    map.put("entity", entity);
		return "/bimr/case/civilcaseWeekCheckList";
	}
	
	//民事案件审核
	@RequestMapping(value = "/viewCivilcaseCheck")
	public String viewCivilcaseCheck(String id, Map<String, Object> map, HttpServletRequest request) {
		BimrCivilcaseWeek entity = caseService.getCivilcaseById(Integer.parseInt(id));
		BimrCivilcaseWeek entity_old = new BimrCivilcaseWeek();
        map.put("entity", entity);
        List civilcaseList = caseService.getCaseByCaseNum("1", entity.getCaseNum());
        if(civilcaseList.size() > 1){
        	for(int i=0;i<civilcaseList.size();i++){
        		BimrCivilcaseWeek entity_1 = (BimrCivilcaseWeek)civilcaseList.get(i);
            	if(entity_1.getIsHistory().equals("1")){
            		continue;
            	}
            	else if(entity_1.getVersion().equals("")){
            		continue;
            	}
            	entity_old = entity_1;
        	}
        }
        map.put("entity_old", entity_old);
        //获取案件历史审核记录
        List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_civilcase);
        map.put("examineList", examineList);
        List<HhFile> indictmentFile = new ArrayList<HhFile>();
        List<HhFile> judgmentFile = new ArrayList<HhFile>();
        List<HhFile> oFile = new ArrayList<HhFile>();
        if (null!=entity.getIndictment()) {
    		String[] indictments=entity.getIndictment().split(",");
        	for (int i = 0; i < indictments.length; i++) {
        		indictmentFile.add(commonService.getFileByUuid(indictments[i]));
			}
		}
        if (null!=entity.getJudgment()) {
    		String[] judgments=entity.getJudgment().split(",");
        	for (int i = 0; i < judgments.length; i++) {
        		judgmentFile.add(commonService.getFileByUuid(judgments[i]));
			}
		}
        if (null!=entity.getOtherFile()) {
    		String[] otherments=entity.getOtherFile().split(",");
        	for (int i = 0; i < otherments.length; i++) {
        		oFile.add(commonService.getFileByUuid(otherments[i]));
			}
		}
        if(null!=indictmentFile&&indictmentFile.size()!=0){
        	map.put("indictmentFile", indictmentFile);
        }else{
        	map.put("indictmentFile", null);
        }
        if(null!=judgmentFile&&judgmentFile.size()!=0){
        	map.put("judgmentFile", judgmentFile);
        }else{
        	map.put("judgmentFile", null);
        }
        if(null!=oFile&&oFile.size()!=0){
        	map.put("oFile", oFile);
        }else{
        	map.put("oFile", null);
        }
        List<HhBase> reasonlist = baseService.getBases(Base.CASE_REASON);
        map.put("reasonlist", reasonlist);
		return "/bimr/case/civilcaseWeekCheckView";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		BimrCivilcaseWeek entity = caseService.examineCivilcaseById(entityid, examStr, examResult, user);
		if(entity != null){
			return JSON.toJSONString("success");
		}else{
			return JSON.toJSONString("failed");
		}
	}
	
	/**
	 * 刑事案件
	 * */
	

//	//刑事案件*导出
//	
	@RequestMapping(value="/criminalcaseExport")
	public void criminalCaseExport(BimrCriminalcaseWeek entity,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) throws IOException, ParsePropertyException, InvalidFormatException {
		HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        if(!Common.notEmpty(entity.getVcCompanyId())){
        	entity.setDataauth(dataauth);
        }else {
			entity.setVcCompanyId(systemService.getDataauth(entity.getVcCompanyId()));
		}
        	//刑事案件在办
	        List<BimrCriminalcaseWeek> list1=caseService.getCriminalCaseExport(entity,1);
	       //刑事案件办结
	        List<BimrCriminalcaseWeek> list2=caseService.getCriminalCaseExport(entity,2);
			 XSSFWorkbook workBook = new XSSFWorkbook();
			 Map<String, XSSFCellStyle> styleMap=createStyles(workBook);  
			 CellStyle style=workBook.createCellStyle();
			 XSSFCell cell = null;
			 XSSFSheet sheet = workBook.createSheet("刑事案件");
			 XSSFFont font1 = workBook.createFont();
			 sheet.addMergedRegion(new CellRangeAddress(0,0,0,21));
			 XSSFRow row = sheet.createRow((int) 0);  
			 cell = row.createCell((short) 0);
			 cell.setCellValue("刑事案件台账");
			 cell.setCellStyle(styleMap.get("title_style"));
			 criminalCaseceaterows(list1,sheet,styleMap,1,0,style,font1);
			 sheet.addMergedRegion(new CellRangeAddress(list1.size()+2,list1.size()+2,0,21));
			 row = sheet.createRow(list1.size()+2);
			 cell = row.createCell((short) 0);
			 cell.setCellValue("办结案件");
			 cell.setCellStyle(styleMap.get("title_style"));
			 criminalCaseceaterows(list2,sheet,styleMap,list1.size()+3,1,style,font1);
				     // 清空response
				        response.reset();
				        String _name = "刑事案件台账.xlsx";
				        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				        String codedfilename = null;
						String agent = request.getHeader("USER-AGENT");
						if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
								&& -1 != agent.indexOf("Trident")) {// ie

							String name = java.net .URLEncoder.encode(_name, "UTF8");

							codedfilename = name;
						} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

							codedfilename = new String(_name.getBytes("UTF-8"),
									"iso-8859-1");
						}
				        response.setContentType("application/octet-stream");
				        response.setHeader("Content-Disposition", "attachment;filename=" + codedfilename);
				        workBook.write(toClient);
				        toClient.flush();
				        toClient.close(); 

			}
	
	public void criminalCaseceaterows(List<BimrCriminalcaseWeek> list,XSSFSheet sheet,Map<String, XSSFCellStyle> styleMap,int count,int type,CellStyle style,XSSFFont font1) {
		String [] header1={"序号","承办单位","犯罪嫌疑人","职务","所在单位","涉嫌罪名",
				 "基本案情","涉案金额（万元）","是否清案遗留案件"	,"是否新增案件(回头看)",	"是否因人员优化工作引发案件","案龄（年）",
				 "最新进展","办案单位","办案人","集团内部承办单位","法务承办人","是否已问责","是否进行成因分析",
				 "案件成因","拟问责建议（若无需问责，请说明具体原因）（实业）","问责进展情况（含问责人数及名单）（实业）","规划结案时间"
		 };
		 String [] header2={"序号","承办单位","犯罪嫌疑人","职务","所在单位","涉嫌罪名",
				 "基本案情","涉案金额（万元）","是否清案遗留案件"	,"是否新增案件(回头看)",	"是否因人员优化工作引发案件","案龄（年）",
				 "最新进展","办案单位","办案人","集团内部承办单位","法务承办人","是否已问责","是否进行成因分析",
				 "案件成因","拟问责建议（若无需问责，请说明具体原因）（实业）","问责进展情况（含问责人数及名单）（实业）","结案时间及结案报告公文号（实业）"
		 };
		 XSSFRow row = sheet.createRow(count);  
		 XSSFCell cell = null;
		 String [] header=null;
		 if (type==0) {
			  header=header1;
		}else {
			header=header2;
		}
	for (int i = 0; i < header.length; i++) {
		cell = row.createCell((short) i);
		cell.setCellValue(header[i]);
		cell.setCellStyle(styleMap.get("head1style"));
	}
	
	String ids=caseService.getCriminalCaseNewWeekIDs();
	List<BimrCriminalcaseWeek> oldlist=caseService.getCriminalcaseNewWeek(ids);
	font1.setColor(IndexedColors.RED.index);
	 style.setFont(font1);
		 for (int i = 0; i < list.size(); i++) {  
			 	int k=0;
			     row = sheet.createRow(i + count+1);
			     BimrCriminalcaseWeek value = list.get(i);
			     BimrCriminalcaseWeek oldvalue=null;
			     for (BimrCriminalcaseWeek b : oldlist) {
			    	 if(null==value.getCaseNum()||"".equals(value.getCaseNum())){
			    		 break;
			    	 }
					if(b.getCaseNum().equals(value.getCaseNum())){
						oldvalue=b;
						break;
					}
				}
			     //序号
			     row.createCell((short)k++).setCellValue(i+1);
//			       承办单位
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getVcCompanyName());
			     if(null!=oldvalue){
			    	 if(null!=value.getVcCompanyName()){
			    		 if(!value.getVcCompanyName().equals(oldvalue.getVcCompanyName())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
			     
			     
//			     犯罪嫌疑人
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getSuspect());
			     if(null!=oldvalue){
			    	 if(null!=value.getSuspect()){
			    		 if(!value.getSuspect().equals(oldvalue.getSuspect())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     职务
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getPost());
			     if(null!=oldvalue){
			    	 if(null!=value.getPost()){
			    		 if(!value.getPost().equals(oldvalue.getPost())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     所在单位
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getBelongCompany());
			     if(null!=oldvalue){
			    	 if(null!=value.getBelongCompany()){
			    		 if(!value.getBelongCompany().equals(oldvalue.getBelongCompany())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     涉嫌罪名
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getAccusation());
			     if(null!=oldvalue){
			    	 if(null!=value.getAccusation()){
			    		 if(!value.getAccusation().equals(oldvalue.getAccusation())){
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
//			     涉案金额
			     cell=row.createCell((short)k++);

			     if (null!=value.getAmount()) {
			    	 cell.setCellValue(value.getAmount().doubleValue());
				}else {
					cell.setCellValue("");
				}
			     if(null!=oldvalue){
			    	 if(null==oldvalue.getAmount()){
			    		 cell.setCellStyle(style);
			    	 }else
			    	 if(null!=value.getAmount()){
			    		 if(value.getAmount().compareTo(oldvalue.getAmount())!=0){
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
			    
//					案龄（年）
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
			    
//			     最新进展
			    cell=row.createCell((short)k++);
			     cell.setCellValue(value.getLatestProgress());
			     if(null!=oldvalue){
			    	 if(null!=value.getLatestProgress()){
			    		 if(!value.getLatestProgress().equals(oldvalue.getLatestProgress())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     办案单位
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getCaseHandlingUnit());
			     if(null!=oldvalue){
			    	 if(null!=value.getCaseHandlingUnit()){
			    		 if(!value.getCaseHandlingUnit().equals(oldvalue.getCaseHandlingUnit())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     办案人
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getCaseHandlingPerson());
			     if(null!=oldvalue){
			    	 if(null!=value.getCaseHandlingPerson()){
			    		 if(!value.getCaseHandlingPerson().equals(oldvalue.getCaseHandlingPerson())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     集团内部承办单位
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getGroupCompany());
			     if(null!=oldvalue){
			    	 if(null!=value.getGroupCompany()){
			    		 if(!value.getGroupCompany().equals(oldvalue.getGroupCompany())){
				    		 cell.setCellStyle(style);
				    	 }
			    	 }
			     }
//			     案发日期
//			     row.createCell((short)k++).setCellValue(value.getCaseDate());
//			     法务承办人
			     cell=row.createCell((short)k++);
			     cell.setCellValue(value.getLawWork());
			     if(null!=oldvalue){
			    	 if(null!=value.getLawWork()){
			    		 if(!value.getLawWork().equals(oldvalue.getLawWork())){
				    		 cell.setCellStyle(style);
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
//			     是否进行/完成成因分析
			      cell=row.createCell((short)k++);
			      if (value.getIsAnalysisReason().equals("1")==true) {
			    	  cell.setCellValue("是");
				}else {
					cell.setCellValue("否");
				}
			      if(null!=oldvalue){
				    	 if(null!=value.getIsAnalysisReason()){
				    		 if(!value.getIsAnalysisReason().equals(oldvalue.getIsAnalysisReason())){
					    		 cell.setCellStyle(style);
					    	 }
				    	 }
				     }
			    //  "案件成因（物流）"
			      cell=row.createCell((short)k++);
				     cell.setCellValue(value.getAnalysisCause());
				     if(null!=oldvalue){
				    	 if(null!=value.getAnalysisCause()){
				    		 if(!value.getAnalysisCause().equals(oldvalue.getAnalysisCause())){
					    		 cell.setCellStyle(style);
					    	 }
				    	 }
				     }
				 //  "拟问责建议（若无需问责，请说明具体原因）（实业）"
				      cell=row.createCell((short)k++);
					     cell.setCellValue(value.getAccountabilitySuggest());
					     if(null!=oldvalue){
					    	 if(null!=value.getAccountabilitySuggest()){
					    		 if(!value.getAccountabilitySuggest().equals(oldvalue.getAccountabilitySuggest())){
						    		 cell.setCellStyle(style);
						    	 }
					    	 }
					     }
			      //"问责结果（含问责文件编号、人数及人员名单）（物流）"
			      cell=row.createCell((short)k++);
				     cell.setCellValue(value.getAccountabilityResults());
				     if(null!=oldvalue){
				    	 if(null!=value.getAccountabilityResults()){
				    		 if(!value.getAccountabilityResults().equals(oldvalue.getAccountabilityResults())){
					    		 cell.setCellStyle(style);
					    	 }
				    	 }
				     }
				     if (type==0) {
				    	 //案件规划时间
					      cell=row.createCell((short)k++);
						     cell.setCellValue(value.getCasePlanDate());
						     if(null!=oldvalue){
						    	 if(null!=value.getCasePlanDate()){
						    		 if(!value.getCasePlanDate().equals(oldvalue.getCasePlanDate())){
							    		 cell.setCellStyle(style);
							    	 }
						    	 }
						     }
					 }else {
						 //"案件办结公文编号"
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
		 }
		 for (int i = 0; i < header.length; i++) {
				//设置列的宽度自适应
				sheet.autoSizeColumn(i);
			}
	}
	
	
	//刑事案件
	@RequestMapping(value = "/criminalcaseList")
	public String criminalcasePagelist(BimrCriminalcaseWeek entity, Map<String, Object> map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        if(!Common.notEmpty(entity.getVcCompanyId())){
        	entity.setDataauth(dataauth);
        }
		 String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }

        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = caseService.findCriminalcasePageList(entity, pageNum, Base.pagesize, user, "1");
		map.put("msgPage", msgPage);
		String mapurl = request.getContextPath()+ "/bimr/case";
		map.put("mapurl", mapurl);
	    map.put("searchurl", "/shanghai-gzw/bimr/case/criminalcaseList");
	    map.put("entity", entity);
		return "/bimr/case/criminalcaseWeekList";
	}
	
	//新增刑事案件
	@RequestMapping(value = "/addCriminalcase")
	public String addCriminalcase(Map<String, Object> map, HttpServletRequest request) {
		BimrCriminalcaseWeek entity = new BimrCriminalcaseWeek();
        map.put("entity", entity);
        HttpSession session = request.getSession();
        //String company = (String)session.getAttribute("gzwsession_company");
        String company = Base.HRTop;
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        map.put("op", "addNew");
		return "/bimr/case/criminalcaseWeekModify";
	}
	
	//保存刑事案件
	@ResponseBody
	@RequestMapping(value = "/saveCriminalcase", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveCriminalcase(BimrCriminalcaseWeek entity, Map<String, Object> map, HttpServletRequest request,@RequestParam(value="reportmentFile",required=true) MultipartFile[] reportmentFile,@RequestParam(value="judgmentFile",required=true) MultipartFile[] judgmentFile,@RequestParam(value="oFile",required=true) MultipartFile[] oFile) {
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setCaseCategory("2");//2为刑事案件
		entity.setIsDel("0");
		entity.setApprovalState("0");//暂存
		entity.setIsHistory("0");
		entity.setIsLastest("1");
		if(entity.getId() == null){
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			entity.setCreateDate(df.format(new Date()));
		}
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(user.getVcEmployeeId());
		entity.setLastModifyPersonName(user.getVcName());
//		entity.setCreatePersonId(user.getVcEmployeeId());
//		entity.setCreatePersonName(user.getVcName());
//		entity.setCreateDate(df.format(new Date()));
		//commonService.saveOrUpdate(entity);
		caseService.updateLasterCaseState("2",null,entity,reportmentFile,judgmentFile,oFile);
		return "success";
	}
	
	@RequestMapping(value = "/updateCriminalcase")
	public String updateCriminalcase(String id, Map<String, Object> map, HttpServletRequest request) {
		BimrCriminalcaseWeek entity = caseService.getCriminalcaseById(Integer.parseInt(id));
        map.put("entity", entity);
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        map.put("op", "update");
        List<HhFile> reportmentFile = new ArrayList<HhFile>();
        List<HhFile> judgmentFile = new ArrayList<HhFile>();
        List<HhFile> oFile = new ArrayList<HhFile>();
        if (null!=entity.getReportment()) {
    		String[] reportments=entity.getReportment().split(",");
        	for (int i = 0; i < reportments.length; i++) {
        		reportmentFile.add(commonService.getFileByUuid(reportments[i]));
			}
		}
        if (null!=entity.getJudgment()) {
    		String[] judgments=entity.getJudgment().split(",");
        	for (int i = 0; i < judgments.length; i++) {
        		judgmentFile.add(commonService.getFileByUuid(judgments[i]));
			}
		}
        if (null!=entity.getOtherFile()) {
    		String[] otherments=entity.getOtherFile().split(",");
        	for (int i = 0; i < otherments.length; i++) {
        		oFile.add(commonService.getFileByUuid(otherments[i]));
			}
		}
        if(null!=reportmentFile&&reportmentFile.size()!=0){
        	map.put("reportmentFile", reportmentFile);
        }else{
        	map.put("reportmentFile", null);
        }
        if(null!=judgmentFile&&judgmentFile.size()!=0){
        	map.put("judgmentFile", judgmentFile);
        }else{
        	map.put("judgmentFile", null);
        }
        if(null!=oFile&&oFile.size()!=0){
        	map.put("oFile", oFile);
        }else{
        	map.put("oFile", null);
        }
		return "/bimr/case/criminalcaseWeekModify";
	}
	
	@RequestMapping(value = "/viewCriminalcase")
	public String viewCriminalcase(String id, Map<String, Object> map, HttpServletRequest request) {
		BimrCriminalcaseWeek entity = caseService.getCriminalcaseById(Integer.parseInt(id));
        map.put("entity", entity);
        //获取案件历史审核记录
        List<SysExamine> examineList = examineService.getListExamine(entity.getId(), 50150);
        map.put("examineList", examineList);
        List<HhFile> reportmentFile = new ArrayList<HhFile>();
        List<HhFile> judgmentFile = new ArrayList<HhFile>();
        List<HhFile> oFile = new ArrayList<HhFile>();
        if (null!=entity.getReportment()) {
    		String[] reportments=entity.getReportment().split(",");
        	for (int i = 0; i < reportments.length; i++) {
        		reportmentFile.add(commonService.getFileByUuid(reportments[i]));
			}
		}
        if (null!=entity.getJudgment()) {
    		String[] judgments=entity.getJudgment().split(",");
        	for (int i = 0; i < judgments.length; i++) {
        		judgmentFile.add(commonService.getFileByUuid(judgments[i]));
			}
		}
        if (null!=entity.getOtherFile()) {
    		String[] otherments=entity.getOtherFile().split(",");
        	for (int i = 0; i < otherments.length; i++) {
        		oFile.add(commonService.getFileByUuid(otherments[i]));
			}
		}
        if(null!=reportmentFile&&reportmentFile.size()!=0){
        	map.put("reportmentFile", reportmentFile);
        }else{
        	map.put("reportmentFile", null);
        }
        if(null!=judgmentFile&&judgmentFile.size()!=0){
        	map.put("judgmentFile", judgmentFile);
        }else{
        	map.put("judgmentFile", null);
        }
        if(null!=oFile&&oFile.size()!=0){
        	map.put("oFile", oFile);
        }else{
        	map.put("oFile", null);
        }
		return "/bimr/case/criminalcaseWeekView";
	}
	
	//提交审核刑事案件
	@ResponseBody
	@RequestMapping(value = "/commitCriminalcase", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String commitCriminalcase(BimrCriminalcaseWeek entity, Map<String, Object> map, HttpServletRequest request,@RequestParam(value="reportmentFile",required=true) MultipartFile[] reportmentFile,@RequestParam(value="judgmentFile",required=true) MultipartFile[] judgmentFile,@RequestParam(value="oFile",required=true) MultipartFile[] oFile) {
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setIsDel("0");
		entity.setCaseCategory("2");
		entity.setApprovalState("1");//审核中
		entity.setIsHistory("0");
		entity.setIsLastest("1");
		if(entity.getId() == null){
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			entity.setCreateDate(df.format(new Date()));
		}
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(user.getVcName());
		entity.setLastModifyPersonName(user.getVcEmployeeId());
		entity.setReportPersonId(user.getVcEmployeeId());
		entity.setReportPersonName(user.getVcName());
		entity.setReportDate(df.format(new Date()));
		//commonService.saveOrUpdate(entity);
		caseService.updateLasterCaseState("2",null,entity,reportmentFile,judgmentFile,oFile);
		PortalMsg msg = new PortalMsg();
		msg.setTitle("刑事案件需要审核");
		msg.setBusiness("bimWork_bimr_criminalcase");
		msg.setDate(df.format(new Date()));
		msg.setDescription("风控刑事案件需要审核");
		msg.setTableName("bimr_criminalcase_week");
		msg.setRecordId(entity.getId().toString());
		msg.setDelFlag(0);
		msg.setType(0);
		potalMsgService.savePortalMsg(msg);
		return "success";
	}
	
	//刑事案件变更
	@RequestMapping(value = "/exchangeCriminalcase")
	public String exchangeCriminalcase(String id, Map<String, Object> map, HttpServletRequest request) {
		BimrCriminalcaseWeek entity = caseService.getCriminalcaseById(Integer.parseInt(id));
		entity.setVersion(null);
		entity.setCreateDate(null);
		entity.setCreatePersonId(null);
		entity.setCreatePersonName(null);
		entity.setLastModifyDate(null);
		entity.setLastModifyPersonId(null);
		entity.setLastModifyPersonName(null);
		entity.setReportPersonId(null);
		entity.setReportPersonName(null);
		entity.setReportDate(null);
		entity.setAuditorDate(null);
		entity.setAuditorPersonId(null);
		entity.setAuditorPersonName(null);
		entity.setId(null);
		entity.setIsLastest(null);
        map.put("entity", entity);
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        List<HhFile> reportmentFile = new ArrayList<HhFile>();
        List<HhFile> judgmentFile = new ArrayList<HhFile>();
        List<HhFile> oFile = new ArrayList<HhFile>();
        if (null!=entity.getReportment()) {
    		String[] reportments=entity.getReportment().split(",");
        	for (int i = 0; i < reportments.length; i++) {
        		reportmentFile.add(commonService.getFileByUuid(reportments[i]));
			}
		}
        if (null!=entity.getJudgment()) {
    		String[] judgments=entity.getJudgment().split(",");
        	for (int i = 0; i < judgments.length; i++) {
        		judgmentFile.add(commonService.getFileByUuid(judgments[i]));
			}
		}
        if (null!=entity.getOtherFile()) {
    		String[] otherments=entity.getOtherFile().split(",");
        	for (int i = 0; i < otherments.length; i++) {
        		oFile.add(commonService.getFileByUuid(otherments[i]));
			}
		}
        if(null!=reportmentFile&&reportmentFile.size()!=0){
        	map.put("reportmentFile", reportmentFile);
        }else{
        	map.put("reportmentFile", null);
        }
        if(null!=judgmentFile&&judgmentFile.size()!=0){
        	map.put("judgmentFile", judgmentFile);
        }else{
        	map.put("judgmentFile", null);
        }
        if(null!=oFile&&oFile.size()!=0){
        	map.put("oFile", oFile);
        }else{
        	map.put("oFile", null);
        }
		return "/bimr/case/criminalcaseWeekModify";
	}
	
	//删除刑事案件
	@ResponseBody
	@RequestMapping(value = "/delCriminalcase", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String delCriminalcase(BimrCriminalcaseWeek entity, String id, Map<String, Object> map, HttpServletRequest request) {
		caseService.deleteCriminalcase(Integer.parseInt(id));
		return "redirect:/bimr/case/criminalcaseList";
	}
	//刑事案件查询
	@RequestMapping(value = "/criminalcaseQuery")
	public String criminalcaseQuerylist(BimrCriminalcaseWeek entity, Map<String, Object> map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        if(!Common.notEmpty(entity.getVcCompanyId())){
        	entity.setDataauth(dataauth);
        }
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        if (Common.notEmpty(entity.getVcCompanyId())) {
        	if (entity.getVcCompanyId().split(",").length==1) {
        		entity.setVcCompanyId(systemService.getDataauth(entity.getVcCompanyId()));
			}
		}
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = caseService.findCriminalcaseQueryPageList(entity, pageNum, Base.pagesize, user);
		map.put("msgPage", msgPage);
		String mapurl = request.getContextPath()+ "/bimr/case";
		map.put("mapurl", mapurl);
	    map.put("searchurl", "/shanghai-gzw/bimr/case/criminalcaseQuery");
	    map.put("entity", entity);
		return "/bimr/case/criminalcaseQueryList";
	}
	//刑事案件审核列表
	@RequestMapping(value = "/criminalcaseCheckList")
	public String criminalcaseChecklist(BimrCriminalcaseWeek entity, Map<String, Object> map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        if(!Common.notEmpty(entity.getVcCompanyId())){
        	entity.setDataauth(dataauth);
        }
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = caseService.findCriminalcasePageList(entity, pageNum, Base.pagesize, user, "2");
		map.put("msgPage", msgPage);
		String mapurl = request.getContextPath()+ "/bimr/case";
		map.put("mapurl", mapurl);
	    map.put("searchurl", "/shanghai-gzw/bimr/case/criminalcaseCheckList");
	    map.put("entity", entity);
	    
		return "/bimr/case/criminalcaseWeekCheckList";
	}
	
	//民事案件审核
	@RequestMapping(value = "/viewCriminalcaseCheck")
	public String viewCriminalcaseCheck(String id, Map<String, Object> map, HttpServletRequest request) {
		BimrCriminalcaseWeek entity = caseService.getCriminalcaseById(Integer.parseInt(id));
		BimrCriminalcaseWeek entity_old = new BimrCriminalcaseWeek();
        map.put("entity", entity);
        List criminalcaseList = caseService.getCaseByCaseNum("2", entity.getCaseNum());
        if(criminalcaseList.size() > 1){
        	for(int i=0;i<criminalcaseList.size();i++){
        		BimrCriminalcaseWeek entity_1 = (BimrCriminalcaseWeek)criminalcaseList.get(i);
            	if(entity_1.getIsHistory().equals("1")){
            		continue;
            	}
            	else if(entity_1.getVersion().equals("")){
            		continue;
            	}
            	entity_old = entity_1;
        	}
        }
        map.put("entity_old", entity_old);
        //获取案件历史审核记录
        List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_criminalcase);
        map.put("examineList", examineList);
        List<HhFile> reportmentFile = new ArrayList<HhFile>();
        List<HhFile> judgmentFile = new ArrayList<HhFile>();
        List<HhFile> oFile = new ArrayList<HhFile>();
        if (null!=entity.getReportment()) {
    		String[] reportments=entity.getReportment().split(",");
        	for (int i = 0; i < reportments.length; i++) {
        		reportmentFile.add(commonService.getFileByUuid(reportments[i]));
			}
		}
        if (null!=entity.getJudgment()) {
    		String[] judgments=entity.getJudgment().split(",");
        	for (int i = 0; i < judgments.length; i++) {
        		judgmentFile.add(commonService.getFileByUuid(judgments[i]));
			}
		}
        if (null!=entity.getOtherFile()) {
    		String[] otherments=entity.getOtherFile().split(",");
        	for (int i = 0; i < otherments.length; i++) {
        		oFile.add(commonService.getFileByUuid(otherments[i]));
			}
		}
        if(null!=reportmentFile&&reportmentFile.size()!=0){
        	map.put("reportmentFile", reportmentFile);
        }else{
        	map.put("reportmentFile", null);
        }
        if(null!=judgmentFile&&judgmentFile.size()!=0){
        	map.put("judgmentFile", judgmentFile);
        }else{
        	map.put("judgmentFile", null);
        }
        if(null!=oFile&&oFile.size()!=0){
        	map.put("oFile", oFile);
        }else{
        	map.put("oFile", null);
        }
        
		return "/bimr/case/criminalcaseWeekCheckView";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexamCriminalcase", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexamCriminalcase(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		BimrCriminalcaseWeek entity = caseService.examineCriminalcaseById(entityid, examStr, examResult, user);
		if(entity != null){
			return JSON.toJSONString("success");
		}else{
			return JSON.toJSONString("failed");
		}
	}
	
	//案件综合查询
	@RequestMapping(value = "/comprehensiveQuery")
	public String comprehensiveQuery(BimrCaseQuery caseQuery, String id, Map<String, Object> map, HttpServletRequest request) {
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        Integer pageNum = Integer.valueOf(curPageNum);
        if (Common.notEmpty(caseQuery.getVcCompanyId())) {
        	if (caseQuery.getVcCompanyId().split(",").length==1) {
        		String orgIds=systemService.getDataauth(caseQuery.getVcCompanyId());
           	 caseQuery.setVcCompanyId(orgIds);
			}
		}else {
        	 caseQuery.setVcCompanyId(dataauth);
		}
        /*List<Object[]> lastWeeklist=caseService.getLastWeekData();
        if (!Common.notEmpty(caseQuery.getWeek())&&!Common.notEmpty(caseQuery.getYear())) {
			caseQuery.setYear(lastWeeklist.get(0)[1].toString());
			caseQuery.setWeek(lastWeeklist.get(0)[2].toString());
		}*/
		MsgPage msgPage = caseService.comprehensiveQuery(caseQuery, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		String mapurl = request.getContextPath()+ "/bimr/case";
		map.put("mapurl", mapurl);
	    map.put("searchurl", "/shanghai-gzw/bimr/case/comprehensiveQuery");
	    map.put("exporturl", "/shanghai-gzw/bimr/case/newCaseExport");
	    //map.put("entity", entity);
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        map.put("entity", caseQuery);
		return "/bimr/case/caseComprehensiveQuery";
	}
	
	//案件综合查询
		@RequestMapping(value = "/newCaseExport")
		public void newCaseExport(BimrCaseQuery caseQuery,Integer type, Map<String, Object> map, HttpServletRequest request,HttpServletResponse response) throws IOException {
	        HttpSession session = request.getSession();
	        String company = (String)session.getAttribute("gzwsession_company");
	        //获取数据权限
	        String dataauth=systemService.getDataauth(company);
	        if (Common.notEmpty(caseQuery.getVcCompanyId())) {
	        	if (caseQuery.getVcCompanyId().split(",").length==1) {
	        		String orgIds=systemService.getDataauth(caseQuery.getVcCompanyId());
	           	 caseQuery.setVcCompanyId(orgIds);
				}
			}else {
	        	 caseQuery.setVcCompanyId(dataauth);
			}
	        List<Object[]> lastWeeklist=caseService.getLastWeekData(caseQuery.getYear());
	        if (!Common.notEmpty(caseQuery.getWeek())&&!Common.notEmpty(caseQuery.getYear())) {
				caseQuery.setYear(lastWeeklist.get(0)[1].toString());
				caseQuery.setWeek(lastWeeklist.get(0)[2].toString());
			}
	        List<Object[]> lastOldWeeklist=caseService.getLastWeekData(Integer.parseInt(caseQuery.getYear())-1+"");
	        List<Object[]> civinowList=caseService.getCaseReportExport(caseQuery, 0);
	        List<Object[]> criminalnowList=caseService.getCaseReportExport(caseQuery, 1);
	        //List<Object[]> nowList=caseService.getCaseReport(caseQuery.getYear(),caseQuery.getWeek());
	        String nowYear=caseQuery.getYear();
	        String nowWeek=caseQuery.getWeek();
	        List<Object[]> civilastList=new ArrayList<Object[]>();
	        List<Object[]> criminallastList=new ArrayList<Object[]>();
	        if (type==1) {
	        	if (caseQuery.getWeek().equals("1")) {
		        	 Calendar c = new GregorianCalendar();
		             c.set(Integer.parseInt(caseQuery.getYear())-1, Calendar.DECEMBER, 31, 23, 59, 59);
		             caseQuery.setYear(Integer.parseInt(caseQuery.getYear())-1+"");
		             caseQuery.setWeek(c.get(Calendar.WEEK_OF_YEAR)+"");
		             civilastList=caseService.getCaseReportExport(caseQuery,0);
		             criminallastList=caseService.getCaseReportExport(caseQuery,1);
				}else {
		             caseQuery.setWeek(Integer.parseInt(caseQuery.getWeek())-1+"");
		             civilastList=caseService.getCaseReportExport(caseQuery,0);
		             criminallastList=caseService.getCaseReportExport(caseQuery,1);
				}
			}else {
				if (lastOldWeeklist.size()>0) {
					caseQuery.setYear(lastOldWeeklist.get(0)[1].toString());
		             caseQuery.setWeek(lastOldWeeklist.get(0)[2].toString());
					//lastList=caseService.getCaseReport(lastOldWeeklist.get(0)[1].toString(),lastOldWeeklist.get(0)[2].toString());
		             civilastList=caseService.getCaseReportExport(caseQuery,0);
		             criminallastList=caseService.getCaseReportExport(caseQuery,1);
				}
			}
	        
	        
	       /* String ids1=(0==nowList.size()?"":nowList.get(0)[3].toString());
	        String ids2=(0==lastList.size()?"":lastList.get(0)[3].toString());
	        String ids3=(0==nowList.size()?"":nowList.get(0)[4].toString());
	        String ids4=(0==lastList.size()?"":lastList.get(0)[4].toString());*/
	        String idString=null;
	        String idString2=null;
	        for (int i = 0; i < civinowList.size(); i++) {
				if (civilastList.contains(civinowList.get(i))) {
					continue;
				}
				if(idString==null)
					idString= (String) (civinowList.get(i)==null? "": civinowList.get(i));
				else
					idString=idString+","+ String.valueOf(civinowList.get(i));
			}
	        for (int i = 0; i < criminalnowList.size(); i++) {
				if (criminallastList.contains(criminalnowList.get(i))) {
					continue;
				}
				if(idString2==null)
					idString2= (String) (criminalnowList.get(i)==null? "": criminalnowList.get(i));
				else
					idString2=idString2+","+ String .valueOf(criminalnowList.get(i));
			}
	        
	        List<BimrCivilcaseWeek> list1 = caseService.getcivilNewCaseExport(idString, 1);
	        List<BimrCivilcaseWeek> list2 = caseService.getcivilNewCaseExport(idString, 2);
	        List<BimrCivilcaseWeek> list3 = caseService.getcivilNewCaseExport(idString, 3);
	        
	        XSSFWorkbook workBook = new XSSFWorkbook();
//			
	        workBook=caseService.getcivilCaseExportWorkbook(list1, list2, list3);
	        
	      //刑事案件在办
	        List<BimrCriminalcaseWeek> list4=caseService.getCriminalNewCaseExport(idString2,1);
	       //刑事案件办结
	        List<BimrCriminalcaseWeek> list5=caseService.getCriminalNewCaseExport(idString2,2);
	        
				     
	        XSSFSheet sheet = workBook.createSheet("刑事案件");
	        Map<String, XSSFCellStyle> styleMap=createStyles(workBook);  
			 CellStyle style=workBook.createCellStyle();
			 XSSFCell cell = null;
			 XSSFFont font1 = workBook.createFont();
			 sheet.addMergedRegion(new CellRangeAddress(0,0,0,21));
			 XSSFRow row = sheet.createRow((int) 0);  
			 cell = row.createCell((short) 0);
			 cell.setCellValue("刑事案件台账");
			 cell.setCellStyle(styleMap.get("title_style"));
			 criminalCaseceaterows(list4,sheet,styleMap,1,0,style,font1);
			 sheet.addMergedRegion(new CellRangeAddress(list4.size()+2,list4.size()+2,0,21));
			 row = sheet.createRow(list4.size()+2);
			 cell = row.createCell((short) 0);
			 cell.setCellValue("办结案件");
			 cell.setCellStyle(styleMap.get("title_style"));
			 criminalCaseceaterows(list5,sheet,styleMap,list4.size()+3,1,style,font1);
			 
	        
				     // 清空response
				        response.reset();
				        String _name=null;
				        if (1==type) {
					         _name = nowYear+"年"+nowWeek+"周新增案件台账.xlsx";
						}else {
					         _name = nowYear+"年新增案件台账.xlsx";
						}
				        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				        String codedfilename = null;
						String agent = request.getHeader("USER-AGENT");
						if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
								&& -1 != agent.indexOf("Trident")) {// ie

							String name = java.net .URLEncoder.encode(_name, "UTF8");

							codedfilename = name;
						} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

							codedfilename = new String(_name.getBytes("UTF-8"),
									"iso-8859-1");
						}
				        response.setContentType("application/octet-stream");
				        response.setHeader("Content-Disposition", "attachment;filename=" + codedfilename);
				        workBook.write(toClient);
				        toClient.flush();
				        toClient.close(); 
		}
	
	//新增时验证案件编码是否重复
	@ResponseBody
	@RequestMapping(value ="/validateNum", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _validateNum(String caseNum, String type, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		List list = new ArrayList();
		if(type.equals("1")){
			list = caseService.getCaseByCaseNum("1", caseNum);
		}
		if(type.equals("2")){
			list = caseService.getCaseByCaseNum("2", caseNum);
		}
		if(list.size() > 0){
			return JSON.toJSONString("failed");
		}else{
			return JSON.toJSONString("success");
		}
	}
	
	//生成案件周报，以供下载使用
	@RequestMapping(value = "/createReport")
	public String createReport(Map<String, Object> map, HttpServletRequest request){
		return "/bimr/case/report";
	}
	
	//验证周报是否已经存在
	@ResponseBody
	@RequestMapping(value ="/validateReport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _validateReport(String year, String week, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
		List list = new ArrayList();
		list = caseService.getCaseReport(year, week);
		if(list.size() > 0){
			return JSON.toJSONString("exist");
		}else{
			caseService.saveCaseReport(year, week,dataauth);
			return JSON.toJSONString("success");
		}
	}
	
	 @ResponseBody
	    @RequestMapping("/delfile")
	    public JSONObject delfile(HttpServletRequest request,Integer id,String uuid,String type){
	        JSONObject jsonObject = new JSONObject();
	        //indictment
	        try {
	        	 BimrCivilcaseWeek bimrCivilcaseWeek=caseService.getCivilcaseById(id);
	        	 
	        	 String fileString = null;
	        	 String newFilesString=null;
	        	 if ("1".equals(type)) {
	        		 fileString= bimrCivilcaseWeek.getIndictment();
	        		 newFilesString=delonefile(fileString, uuid);
	        		 bimrCivilcaseWeek.setIndictment(newFilesString);
				 }else if("2".equals(type)){
					 fileString= bimrCivilcaseWeek.getJudgment();
					 newFilesString=delonefile(fileString, uuid);
					 bimrCivilcaseWeek.setJudgment(newFilesString);
				}else {
					fileString= bimrCivilcaseWeek.getOtherFile();
					newFilesString=delonefile(fileString, uuid);
					bimrCivilcaseWeek.setOtherFile(newFilesString);
				}
	            
	             Common.deleteFile(DES.BIMR_CIVIL_CASE, uuid);
				HhFile file = commonService.getFileByUuid(uuid);
					if(null!=file)
						commonService.delete(file);
	             commonService.saveOrUpdate(bimrCivilcaseWeek);
	             jsonObject.put("result", 1);
		         jsonObject.put("message", "删除成功!");
			} catch (Exception e) {
				// TODO: handle exception
				 jsonObject.put("result", 0);
		         jsonObject.put("message", "内部项目文件删除异常!");
			}
	       
	        return jsonObject;
	    }
	 
	 @ResponseBody
	    @RequestMapping("/delfileCirminal")
	    public JSONObject delfileCirminal(HttpServletRequest request,Integer id,String uuid,String type){
	        JSONObject jsonObject = new JSONObject();
	        //indictment
	        try {
	        	 BimrCriminalcaseWeek bimrCriminalcaseWeek=caseService.getCriminalcaseById(id);
	        	 
	        	 String fileString = null;
	        	 String newFilesString=null;
	        	 if ("1".equals(type)) {
	        		 fileString= bimrCriminalcaseWeek.getReportment();
	        		 newFilesString=delonefile(fileString, uuid);
	        		 bimrCriminalcaseWeek.setReportment(newFilesString);
				 }else if("2".equals(type)){
					 fileString= bimrCriminalcaseWeek.getJudgment();
					 newFilesString=delonefile(fileString, uuid);
					 bimrCriminalcaseWeek.setJudgment(newFilesString);
				}else {
					fileString= bimrCriminalcaseWeek.getOtherFile();
					newFilesString=delonefile(fileString, uuid);
					bimrCriminalcaseWeek.setOtherFile(newFilesString);
				}
	            
	             Common.deleteFile(DES.BIMR_CRIMINAL_CASE, uuid);
				HhFile file = commonService.getFileByUuid(uuid);
					if(null!=file)
						commonService.delete(file);
	             commonService.saveOrUpdate(bimrCriminalcaseWeek);
	             jsonObject.put("result", 1);
		         jsonObject.put("message", "删除成功!");
			} catch (Exception e) {
				// TODO: handle exception
				 jsonObject.put("result", 0);
		         jsonObject.put("message", "内部项目文件删除异常!");
			}
	       
	        return jsonObject;
	    }
	 public String delonefile(String fileString,String uuid) {
		 String newFilesString=null;
		 if(Common.notEmpty(fileString)){
          	for (int i = 0; i < fileString.split(",").length; i++) {
      			if(!fileString.split(",")[i].equals(uuid)){
      				if(null!=newFilesString){
      					newFilesString=newFilesString+","+fileString.split(",")[i];
      				}else {
      					newFilesString=fileString.split(",")[i];
  					}
      			}
      		}
          }
		 return newFilesString;
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
			
			return styles;
		}
}
