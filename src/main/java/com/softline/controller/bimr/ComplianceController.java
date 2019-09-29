package com.softline.controller.bimr;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.dao.bimr.impl.ComplianceDao;
import com.softline.entity.AuditProject;
import com.softline.entity.AuditProjectFindQuestion;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
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
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entityTemp.CommitResult;
import com.softline.service.bimr.IComplianceService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * 
 * @author tch
 *
 */
@Controller
@RequestMapping("/bimr/compliance")
public class ComplianceController {

	@Resource(name = "complianceService")
	private IComplianceService complianceService;
//	service层
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	@Resource(name = "commonService")
	private ICommonService commonService;
	@Resource(name = "selectUserService")
    private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,
							@RequestParam(value = "situationId", required = false) Integer situationId,
							@RequestParam(value = "promptId", required = false) Integer promptId,
							@RequestParam(value = "suggestId", required = false) Integer suggestId,
							@RequestParam(value = "progressId", required = false) Integer progressId,
							Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			BimrCompliance entityview=complianceService.getComplianceById(id);
			map.put("bimrCompliance", entityview);
		}
		if (situationId != null) {
			BimrComplianceSituation entityview=complianceService.getComplianceSituationById(situationId);
			map.put("bimrComplianceSituation", entityview);
		}
		if (promptId != null) {
			BimrCompliancePrompt entityview=complianceService.getCompliancePromptById(promptId);
			map.put("bimrCompliancePrompt", entityview);
		}
		if (suggestId != null) {
			BimrComplianceSuggest entityview=complianceService.getComplianceSuggestById(suggestId);
			map.put("bimrComplianceSuggest", entityview);
		}
		if (progressId != null) {
			BimrComplianceProgress entityview=complianceService.getComplianceProgressById(progressId);
			map.put("bimrComplianceProgress", entityview);
		}
	}
	
	/**
	 * 跳转到审核画面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String _examinelist(BimrCompliance entity,String type,String stype, HttpServletRequest request, Map<String, Object> map) throws IOException {
		map.put("type", type);
		map.put("stype", stype);
		
		//type basic基本信息维护  manage数据填报  examine审核合规调查数据
		if(type!=null)
			entity.setSearchType(type);
		
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setResponsiblePersonId(use.getVcEmployeeId());
		
		//调查类型
		List<HhBase> investigationType = baseService.getBases(Base.compliance_investigation_type);
		map.put("investigationType", investigationType);
		
		//项目状态
		List<HhBase> complianceStatus = baseService.getBases(Base.compliance_status);
		map.put("complianceStatus", complianceStatus);
		
		// 路径
		String mapurl = request.getContextPath() + "/bimr/compliance";
		map.put("mapurl", mapurl);
		
		/*String str=(String) session.getAttribute("gzwsession_users");
		String json=JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype));
		map.put("allCompanyTree", json);*/
		
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		MsgPage msgPage = complianceService.getComplianceDaoListView(entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/bimr/compliance/list?type="+type);
		map.put("entity", entity);
		
		
		//type basic基本信息维护  manage数据填报  examine审核合规调查数据
		if(type.equals("basic"))
			return "/bimr/compliance/complianceList";
		else if(type.equals("manage"))
			return "/bimr/compliance/complianceManage";
		else
			return "/bimr/compliance/complianceExamine";
			
	}
	
	@RequestMapping("addOrModify")
	public String addOrModify(BimrCompliance entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		//调查类型
		List<HhBase> investigationType = baseService.getBases(Base.compliance_investigation_type);
		map.put("investigationType", investigationType);
		
		//调查来源
		List<HhBase> investigationFrom = baseService.getBases(Base.compliance_investigation_from);
		map.put("investigationFrom", investigationFrom);
		
		HttpSession session=request.getSession();
		map.put("op", op);
		
		List<BimrCompliancePerson> personList=complianceService.getCompliancePerson(entity.getId());
		map.put("personList", personList);
		
		map.put("entity", entity);
		
		String company = Base.HRTop;
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        HhFile indictmentFile = null;
	    indictmentFile = commonService.getFileByUuid(entity.getIndictment());
	    if(null!=indictmentFile){
        	map.put("indictmentFile", indictmentFile);
        }
		//map.put("organalID", organalID);
		return "/bimr/compliance/complianceAddOrModify";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdate(BimrCompliance entity, HttpServletRequest request,
			String[] employeeName,String[] employeePostion,String[] employeeNumber,
			@RequestParam(value="file1",required=false) MultipartFile file1,@RequestParam(value="indictmentFile",required=false) MultipartFile indictmentFile,String type) throws IOException {
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			List<BimrCompliancePerson> personList=new ArrayList<BimrCompliancePerson>();
			if(employeeName!=null){
				for(int i=0;i<employeeName.length;i++){
					if(Common.notEmpty(employeeName[i])){
						BimrCompliancePerson person=new BimrCompliancePerson();
						person.setEmployeeName(employeeName[i]);
						person.setEmployeePostion(employeePostion[i]);
						person.setEmployeeNumber(employeeNumber[i]);
						personList.add(person);
					}
				}
			}
			
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			result= complianceService.saveCompliance(entity,personList, use,file1,type,indictmentFile);
		}
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String delete(BimrCompliance entity, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		
		CommitResult result=complianceService.deleteCompliance(entity, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("bimr_compliance", entity.getId().toString());
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/updatestatus", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String updateStatus(BimrCompliance entity, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=complianceService.updateStatus(entity, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("bimr_compliance", entity.getId().toString());
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@RequestMapping("/view")
	public String _view(BimrCompliance entity,String type,String stype, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		map.put("type", type);
		map.put("stype", stype);
		map.put("entity", entity);
		HhFile indictmentFile = null;
	    indictmentFile = commonService.getFileByUuid(entity.getIndictment());
	    if(null!=indictmentFile){
        	map.put("indictmentFile", indictmentFile);
        }
		List<BimrCompliancePerson> personList=complianceService.getCompliancePerson(entity.getId());
		map.put("personList", personList);
		//报告办结信息
		BimrComplianceBJInfo bjInfo=complianceService.getBJInfoByComplianceId(entity.getId());
		map.put("bjInfo", bjInfo);
		if(null!=bjInfo){
			HhFile bjfile = commonService.getFileByEnIdAndType(bjInfo.getId(), Base.COMPLIANCE_REPORTFINISH);
			map.put("bjfile",bjfile);
		}
		
		/*List<ReportInspectProjectOrder> orderList=inspectProjectService.getInspectProjectOrder(entity.getId());
		map.put("orderList", orderList);*/
		
		return "/bimr/compliance/complianceView";
	}
	
	@RequestMapping("/manageedit")
	public String manageEdit(BimrCompliance entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		
		map.put("entity", entity);
		
		List<BimrCompliancePerson> personList=complianceService.getCompliancePerson(entity.getId());
		map.put("personList", personList);
		
		//举报投诉反映问题线索核查情况
		List<BimrComplianceSituation> situationList0=complianceService.getComplianceSituation(entity.getId(),0);
		map.put("situationList0", situationList0);
		
		//待调查事项核查情况
		List<BimrComplianceSituation> situationList1=complianceService.getComplianceSituation(entity.getId(),1);
		map.put("situationList1", situationList1);
		
		//调查发现其他违规违纪事件情况
		List<BimrComplianceSituation> situationList2=complianceService.getComplianceSituation(entity.getId(),2);
		map.put("situationList2", situationList2);
				
		//调查发现其他违规违纪事件情况
		List<BimrCompliancePrompt> promptList=complianceService.getCompliancePrompt(entity.getId());
		map.put("promptList", promptList);
		
		//调查发现其他违规违纪事件情况
		List<BimrComplianceSuggest> suggestList=complianceService.getComplianceSuggest(entity.getId());
		map.put("suggestList", suggestList);
		
		//调查发现其他违规违纪事件情况
		List<BimrComplianceProgress> progressList=complianceService.getComplianceProgress(entity.getId());
		map.put("progressList", progressList);
		
		String mapurl = request.getContextPath() + "/bimr/compliance";
		map.put("mapurl", mapurl);
		
		HhFile indictmentFile = null;
	    indictmentFile = commonService.getFileByUuid(entity.getIndictment());
	    if(null!=indictmentFile){
        	map.put("indictmentFile", indictmentFile);
        }
		
		return "/bimr/compliance/complianceManageEdit";
	}
	
	@RequestMapping("/manageView")
	public String viewmanage(BimrCompliance entity, HttpServletRequest request,
			Map<String, Object> map,String type) throws IOException {
		map.put("entity", entity);
		map.put("type", type);
		
		List<BimrCompliancePerson> personList=complianceService.getCompliancePerson(entity.getId());
		map.put("personList", personList);
		
		//举报投诉反映问题线索核查情况
		List<BimrComplianceSituation> situationList0=complianceService.getComplianceSituation(entity.getId(),0);
		map.put("situationList0", situationList0);
		
		//待调查事项核查情况
		List<BimrComplianceSituation> situationList1=complianceService.getComplianceSituation(entity.getId(),1);
		map.put("situationList1", situationList1);
		
		//调查发现其他违规违纪事件情况
		List<BimrComplianceSituation> situationList2=complianceService.getComplianceSituation(entity.getId(),2);
		map.put("situationList2", situationList2);
				
		//调查发现其他违规违纪事件情况
		List<BimrCompliancePrompt> promptList=complianceService.getCompliancePrompt(entity.getId());
		map.put("promptList", promptList);
		
		//调查发现其他违规违纪事件情况
		List<BimrComplianceSuggest> suggestList=complianceService.getComplianceSuggest(entity.getId());
		map.put("suggestList", suggestList);
		
		//调查发现其他违规违纪事件情况
		List<BimrComplianceProgress> progressList=complianceService.getComplianceProgress(entity.getId());
		map.put("progressList", progressList);
		
		//报告办结信息
				BimrComplianceBJInfo bjInfo=complianceService.getBJInfoByComplianceId(entity.getId());
				map.put("bjInfo", bjInfo);
				if(null!=bjInfo){
					HhFile bjfile = commonService.getFileByEnIdAndType(bjInfo.getId(), Base.COMPLIANCE_REPORTFINISH);
					map.put("bjfile",bjfile);
				}
		String mapurl = request.getContextPath() + "/bimr/compliance";
		map.put("mapurl", mapurl);
		
		HhFile indictmentFile = null;
	    indictmentFile = commonService.getFileByUuid(entity.getIndictment());
	    if(null!=indictmentFile){
        	map.put("indictmentFile", indictmentFile);
        }
		
		return "/bimr/compliance/complianceManageView";
	}
	
	@RequestMapping("addOrModifySituation")
	public String addOrModifySituation(BimrComplianceSituation entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		
		map.put("entity", entity);
		map.put("op", op);
		
		List<HhBase> isTruth = baseService.getBases(Base.compliance_situation_istruth);
		map.put("isTruth", isTruth);
		
		List<HhBase> problemType = baseService.getBases(Base.compliance_situation_problemtype);
		map.put("problemType", problemType);
		
		List<BimrComplianceSituationPerson> personList=complianceService.getComplianceSituationPerson(entity.getId());
		map.put("personList", personList);
		
		//map.put("organalID", organalID);
		if(entity.getDataType()==0)
			return "/bimr/compliance/complianceSituationAddOrModify0";
		else if(entity.getDataType()==1)
			return "/bimr/compliance/complianceSituationAddOrModify1";
		else
			return "/bimr/compliance/complianceSituationAddOrModify2";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateSituation", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateSituation(BimrComplianceSituation entity, HttpServletRequest request,
			String[] accountabilityPerson,String[] accountabilityPersonId,String[] accountabilityDefinition,String[] personPostion,
			@RequestParam(value="file1",required=false) MultipartFile file1) throws IOException {
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			List<BimrComplianceSituationPerson> personList=new ArrayList<BimrComplianceSituationPerson>();
			if(accountabilityPerson!=null){
				for(int i=0;i<accountabilityPerson.length;i++){
					if(Common.notEmpty(accountabilityPerson[i])){
						BimrComplianceSituationPerson person=new BimrComplianceSituationPerson();
						person.setAccountabilityPerson(accountabilityPerson[i]);
						person.setAccountabilityPersonId(accountabilityPersonId[i]);
						person.setAccountabilityDefinition(Integer.parseInt(accountabilityDefinition[i]) );
						person.setPersonPostion(Integer.parseInt(personPostion[i]));
						personList.add(person);
					}
				}
			}
			
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			result= complianceService.saveComplianceSituation(entity,personList, use,file1);
		}
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@RequestMapping("addOrModifyPrompt")
	public String addOrModifyPrompt(BimrCompliancePrompt entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		
		map.put("entity", entity);
		map.put("op", op);
		
		
		return "/bimr/compliance/compliancePromptAddOrModify";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdatePrompt", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdatePrompt(BimrCompliancePrompt entity, HttpServletRequest request,
			@RequestParam(value="file1",required=false) MultipartFile file1) throws IOException {
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			result= complianceService.saveCompliancePrompt(entity, use,file1);
		}
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@RequestMapping("addOrModifySuggest")
	public String addOrModifySuggest(BimrComplianceSuggest entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		
		map.put("entity", entity);
		map.put("op", op);
		
		
		return "/bimr/compliance/complianceSuggestAddOrModify";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateSuggest", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateSuggest(BimrComplianceSuggest entity, HttpServletRequest request,
			@RequestParam(value="file1",required=false) MultipartFile file1) throws IOException {
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			result= complianceService.saveComplianceSuggest(entity, use,file1);
		}
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@RequestMapping("addOrModifyProgress")
	public String addOrModifyProgress(BimrComplianceProgress entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		
		map.put("entity", entity);
		map.put("op", op);
		
		
		return "/bimr/compliance/complianceProgressAddOrModify";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateProgress", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateProgress(BimrComplianceProgress entity, HttpServletRequest request,
			@RequestParam(value="file1",required=false) MultipartFile file1) throws IOException {
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			result= complianceService.saveComplianceProgress(entity, use,file1);
		}
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 合规调查报告办结信息维护查询页面
	 * 
	 * @param entity    查询参数对象
	 * @param pageNums  查询页数
	 * @param request   {@link HttpServletRequest}
	 * @param map       页面渲染数据
	 * @return
	 */
	@RequestMapping(value="reportBJList")
	public String reportBJList(BimrCompliance entity,
			@RequestParam(defaultValue="1")Integer pageNums,
			HttpServletRequest request, Map<String, Object> map){
		
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setResponsiblePersonId(use.getVcEmployeeId());
		
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);
		
		MsgPage msgPage = complianceService.getReportBJListView(entity, pageNums, Base.pagesize);
		setListPageCommonResult(map, request, msgPage, entity, "reportBJList");
		setInvestigationTypes(map);
		
		return "/bimr/compliance/reportBJList";
	}
	
	private void setListPageCommonResult(Map<String, Object> map, 
			HttpServletRequest request, MsgPage msgPage, BimrCompliance entity, String pathName){
		
		map.put("msgPage", msgPage);
		String mapurl = request.getContextPath() + "/bimr/compliance";
		map.put("mapurl", mapurl);
		map.put("searchurl", mapurl + "/" + pathName);
		map.put("entity", entity);
	}
	
	private void setInvestigationTypes(Map<String, Object> map){
		List<HhBase> investigationType = baseService.getBases(Base.compliance_investigation_type);
		map.put("investigationType", investigationType);
	}
	
	/**
	 * 查询合规调查查询页面
	 * 
	 * @param entity    查询参数对象
	 * 维护报告办结信息页面
	 * 
	 * @param complianceId 合规调查编号
	  * @param request    {@link HttpServletRequest}
	 * @param map         页面渲染数据
	 * @return
	 */
	@RequestMapping(value="reportBJEdit")
	public String reportBJEdit(@RequestParam Integer complianceId, HttpServletRequest request, Map<String, Object> map){
		BimrCompliance entity=complianceService.getComplianceById(complianceId);
        map.put("entity", entity);
		List<BimrCompliancePerson> personList=complianceService.getCompliancePerson(complianceId);
		map.put("personList", personList);
		String mapurl = request.getContextPath() + "/bimr/compliance";
		map.put("mapurl", mapurl);
		BimrComplianceBJInfo bjInfo = complianceService.getBJInfoByComplianceId(complianceId);
		map.put("bjInfo", bjInfo == null ? new BimrComplianceBJInfo(): bjInfo);
		HhFile hfile = commonService.getFileByEnIdAndType(entity.getId(), Base.DUTY_ENCLOSURE);
		if (hfile != null) {
			map.put("file_Path", hfile.getFilePath());
			map.put("file_Name", hfile.getFileName());
		} else {
			map.put("file_Path1", "");
			map.put("file_Name1", "");
		}
		return "/bimr/compliance/reportBJEdit";
	}
	
	/**
	 * 保存报告办结信息
	 * 
	 * @param entity  办结信息报告
	 * @param request {@link HttpServletRequest}
	 * @return
	 */
	@RequestMapping(value ="/saveReportBJ", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveReportBJ(BimrComplianceBJInfo entity, 
			@RequestParam(value="file1",required=false) MultipartFile file1,HttpServletRequest request){
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		String package_path = DES.REPORTBJ_PATH;
		CommitResult result=complianceService.saveReportBJ(entity, user,file1,package_path);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data; 
	}
	
	/**

	 * @param pageNums  查询页数
	 * @param request   {@link HttpServletRequest}
	 * @param map       页面渲染数据
	 * @return
	 */
	@RequestMapping("queryList")
	public String queryList(BimrCompliance entity,
			@RequestParam(defaultValue="1")Integer pageNums,
			HttpServletRequest request, Map<String, Object> map){
		

		HttpSession session=request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		map.put("use", use);
        String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);
        if (Common.notEmpty(entity.getCompId())) {
        	if (entity.getCompId().split(",").length==1) {
        		entity.setCompId(systemService.getDataauth(entity.getCompId()));
			}
        }
		String startTime = entity.getStartTime();
		String endTime = entity.getEndTime();
		if(StringUtils.isNotBlank(entity.getStartTime())){
			entity.setStartTime(entity.getStartTime() + "-01");
		}
		if(StringUtils.isNotBlank(entity.getEndTime())){
			entity.setEndTime(entity.getEndTime() + "-31");
		}
		
		MsgPage msgPage = complianceService.getQueryListView(entity, pageNums, Base.pagesize);
		entity.setStartTime(startTime);
		entity.setEndTime(endTime);
		setListPageCommonResult(map, request, msgPage, entity, "queryList");
		
		return "/bimr/compliance/queryList";
	}
	
	/**合规调查分配查询页面
	 * 
	 * @param entity    查询参数对象
	 * @param pageNums  查询页数
	 * @param request   {@link HttpServletRequest}
	 * @param map       页面渲染数据
	 * @return
	 */
	@RequestMapping("assignList")
	public String assignList(BimrCompliance entity,
			@RequestParam(defaultValue="1")Integer pageNums,
			HttpServletRequest request, Map<String, Object> map) {
		
		MsgPage msgPage = complianceService.getAssignListView(entity, pageNums, Base.pagesize);
		setListPageCommonResult(map, request, msgPage, entity, "assignList");
		setInvestigationTypes(map);
		
		return "/bimr/compliance/assignList";
	}
	
	/**合规调查整改信息维护查询页面
	 * 
	 * @param entity    查询参数对象
	 * @param pageNums  查询页数
	 * @param request   {@link HttpServletRequest}
	 * @param map       页面渲染数据
	 * @return
	 */
	@RequestMapping("correctList")
	public String correctList(BimrCompliance entity,
			@RequestParam(defaultValue="1")Integer pageNums,
			HttpServletRequest request, Map<String, Object> map){
		HttpSession session=request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		map.put("use", use);
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);
        
		MsgPage msgPage = complianceService.getCorrectListView(entity, pageNums, Base.pagesize);
		setListPageCommonResult(map, request, msgPage, entity, "correctList");
		setInvestigationTypes(map);
		return "/bimr/compliance/correctList";
	}
	@RequestMapping("correctExamineList")
	public String correctExamineList(BimrCompliance entity,
			@RequestParam(defaultValue="1")Integer pageNums,
			HttpServletRequest request, Map<String, Object> map){

		HttpSession session=request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		map.put("use", use);
        String company = (String)session.getAttribute("gzwsession_company");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);
		
		MsgPage msgPage = complianceService.getCorrectListExamineView(entity, pageNums, Base.pagesize);
		setListPageCommonResult(map, request, msgPage, entity, "correctExamineList");
		setInvestigationTypes(map);
		
		return "/bimr/compliance/correctExamineList";
	}
	@ResponseBody
	@RequestMapping(value ="/deletesituation", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deletesituation(BimrComplianceSituation entity, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=new CommitResult();
		
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsDel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonService.saveOrUpdate(entity);
			result.setResult(true);
		}
		
		if(result.isResult())
			potalMsgService.updatePortalMsg("bimr_compliance_situation", entity.getId().toString());
		
		
		result.setEntity(entity);
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/deleteprompt", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deleteprompt(BimrCompliancePrompt entity, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=new CommitResult();
		
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsDel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonService.saveOrUpdate(entity);
			result.setResult(true);
		}
		
		if(result.isResult())
			potalMsgService.updatePortalMsg("bimr_compliance_prompt", entity.getId().toString());
		
		
		result.setEntity(entity);
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/deletesuggest", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deletesuggest(BimrComplianceSuggest entity, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=new CommitResult();
		
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsDel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonService.saveOrUpdate(entity);
			result.setResult(true);
		}
		
		if(result.isResult())
			potalMsgService.updatePortalMsg("bimr_compliance_suggest", entity.getId().toString());
		
		
		result.setEntity(entity);
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/deleteprogress", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deleteprogress(BimrComplianceProgress entity, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=new CommitResult();
		
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsDel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonService.saveOrUpdate(entity);
			result.setResult(true);
		}
		
		if(result.isResult())
			potalMsgService.updatePortalMsg("bimr_compliance_progress", entity.getId().toString());
		
		
		result.setEntity(entity);
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@RequestMapping("/viewsituation")
	public String viewsituation(BimrComplianceSituation entity,Integer dataType, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		map.put("entity", entity);
		
		List<BimrComplianceSituationPerson> personList=complianceService.getComplianceSituationPerson(entity.getId());
		map.put("personList", personList);
		
		if(dataType==0)
			return "/bimr/compliance/complianceSituationView0";
		else if(dataType==1)
			return "/bimr/compliance/complianceSituationView1";
		else
			return "/bimr/compliance/complianceSituationView2";
	}
	
	@RequestMapping("/viewprogress")
	public String viewprogress(BimrComplianceProgress entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		map.put("entity", entity);
		return "/bimr/compliance/complianceProgressView";
	}
	
	/**合规调查整改信息维护查看页面
	 * 
	 * @param entity    查询参数对象
	 * @param request   {@link HttpServletRequest}
	 * @param map       页面渲染数据
	 * @return
	 */
	@RequestMapping("correctView")
	public String correctView(@RequestParam Integer id, @RequestParam(defaultValue="false") Boolean isEdit,
			HttpServletRequest request, Map<String, Object> map){
		
        BimrCompliance entity = complianceService.getComplianceById(id);
		map.put("entity", entity);
		
		//调查涉及人员
		List<BimrCompliancePerson> personList=complianceService.getCompliancePerson(entity.getId());
		map.put("personList", personList);
		
		//报告办结信息
		BimrComplianceBJInfo bjInfo=complianceService.getBJInfoByComplianceId(id);
		map.put("bjInfo", bjInfo);
		if(null!=bjInfo){
			HhFile bjfile = commonService.getFileByEnIdAndType(bjInfo.getId(), Base.COMPLIANCE_REPORTFINISH);
			map.put("bjfile",bjfile);
		}
		//问责人员维护
		List<BimrComplianceSituationPerson> situationPersonList=complianceService.getSituationPersonListByComplianceId(id);
		map.put("situationPersonList", situationPersonList);
		
		//合规整改问题
		List<BimrComplianceSuggest> suggestList=complianceService.getSuggestListByComplianceId(id);
		map.put("suggestList", suggestList);
		
		//审计项目周进展列表
		List<BimrComplianceProgress> progressList=complianceService.getComplianceProgress(entity.getId());
		map.put("progressList", progressList);
		
		String mapurl = request.getContextPath() + "/bimr/compliance";
		map.put("mapurl", mapurl);
		
		return  isEdit ? "/bimr/compliance/correctEdit" : "/bimr/compliance/correctView";
	}
	
	/**合规调查整改信息维护审核查看页面
	 * 66666
	 * @param entity    查询参数对象
	 * @param request   {@link HttpServletRequest}
	 * @param map       页面渲染数据
	 * @return
	 */
	@RequestMapping("correctExamineView")
	public String correctExamineView(@RequestParam Integer id,
			HttpServletRequest request, Map<String, Object> map,String type){
		
        BimrCompliance entity = complianceService.getComplianceById(id);
		map.put("entity", entity);
		
		//调查涉及人员
		List<BimrCompliancePerson> personList=complianceService.getCompliancePerson(entity.getId());
		map.put("personList", personList);
		
		//报告办结信息
		BimrComplianceBJInfo bjInfo=complianceService.getBJInfoByComplianceId(id);
		map.put("bjInfo", bjInfo);
		if(null!=bjInfo){
			HhFile bjfile = commonService.getFileByEnIdAndType(bjInfo.getId(), Base.COMPLIANCE_REPORTFINISH);
			map.put("bjfile",bjfile);
		}
		//问责人员维护
		List<BimrComplianceSituationPerson> situationPersonList=complianceService.getSituationPersonListByComplianceId(id);
		map.put("situationPersonList", situationPersonList);
		
		//合规整改问题
		List<BimrComplianceSuggest> suggestList=complianceService.getSuggestListByComplianceId(id);
		map.put("suggestList", suggestList);
		
		//审计项目周进展列表
		List<BimrComplianceProgress> progressList=complianceService.getComplianceProgress(entity.getId());
		map.put("progressList", progressList);
		
		String mapurl = request.getContextPath() + "/bimr/compliance";
		map.put("mapurl", mapurl);
		
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), 810501);
		if(examineList.size()!=0){
			map.put("examineList", examineList.get(0));
		}else {
			map.put("examineList", null);
		}
        
		
        map.put("type", type);
        
		return "/bimr/compliance/correctExamineView";
	}
	
	/**
	 * 显示合规整改问题维护页面
	 * 
	 * @param id      合规调查编号
	 * @param isEdit  true:显示编辑页面,false:显示查看页面
	 * @param map     页面渲染数据
	 * @return
	 */
	@RequestMapping("correctProblemView")
	public String correctProblemView(@RequestParam Integer id, @RequestParam(defaultValue="false") Boolean isEdit, Map<String, Object> map,HttpServletRequest request){
		BimrComplianceSuggest entity = complianceService.getSuggestById(id);
		map.put("entity", entity);
		HttpSession session = request.getSession();
		String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
		return  isEdit ? "/bimr/compliance/correctProblemEdit" : "/bimr/compliance/correctProblemView";
	}
	
	/**
	 * 保存合规整改问题
	 * 
	 * @param entity 合规问题
	 * @param request  {@link HttpServletRequest}
	 * @return 处理结果{@link CommitResult}
	 */
	@RequestMapping(value = "saveCorrectProblem", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult saveCorrectProblem(BimrComplianceSuggest entity, HttpServletRequest request){
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		return complianceService.saveCorrectProblem(entity, user);
	}
	
	/**
	 * 显示修改问责人员信息页面
	 * 
	 * @param entity   问责人员
	 * @param map 页面渲染数据
	 * @return  处理结果{@link CommitResult}
	 */
	@RequestMapping("correctPersonView")
	public String correctPersonView(@RequestParam Integer id, Map<String, Object> map){
		BimrComplianceSituationPerson entity = complianceService.getSituationPerson(id);
		
		map.put("entity", entity);
		return "/bimr/compliance/correctPersonEdit";
	}
	
	/**
	 * 保存问责人员信息
	 * 
	 * @param entity   问责人员
	 * @param request  {@link HttpServletRequest}
	 * @return  处理结果{@link CommitResult}
	 */
	@RequestMapping(value="saveCorrectPerson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult saveCorrectPerson(BimrComplianceSituationPerson entity, HttpServletRequest request){
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		return complianceService.saveSituationPerson(entity, user);
	}
	
	/**
	 * 合规调查整改信息归档
	 * 
	 * @param id 合规调查编号
	 * @param request {@link HttpServletRequest}
	 * @return 处理结果{@link CommitResult}
	 */
	@RequestMapping(value = "saveCorrectArchive", method = RequestMethod.POST, produces = "application/json;chaset=UTF-8")
	@ResponseBody
	public CommitResult saveCorrectArchive(BimrCompliance entity, HttpServletRequest request,String examStr,Integer examResult){
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		examineService.saveExamine( entity.getId(), Base.compliance_exmine, user, examStr, examResult);
		if(examResult==50116){
			return complianceService.saveCorrectArchive(entity.getId(), user);
		}else{
			return complianceService.saveErrorArchive(entity.getId(), user);
		}
		
	}
	
	@RequestMapping(value = "saveCorrectStatus", method = RequestMethod.POST, produces = "application/json;chaset=UTF-8")
	@ResponseBody
	public CommitResult saveCorrectStatus(@RequestParam Integer id, HttpServletRequest request){
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		return complianceService.saveCorrectStatus(id, user);
	}
	
	
	/**
	 * 显示合规调查分配人员页面
	 * 
	 * @param id 合规调查编号
	 * @param request  {@link HttpServletRequest}
	 * @param map 页面渲染数据
	 * @return
	 */
	@RequestMapping(value = "assignFollowPerson")
	public String assignFollowPerson(@RequestParam Integer id, HttpServletRequest request, Map<String, Object> map){
		BimrCompliance entity = complianceService.getComplianceById(id);
		map.put("entity", entity);
			
		//调查涉及人员
		List<BimrCompliancePerson> personList=complianceService.getCompliancePerson(entity.getId());
		map.put("personList", personList);
			
		//合规整改问题
		List<BimrComplianceSuggest> suggestList=complianceService.getSuggestListByComplianceId(id);
		map.put("suggestList", suggestList);
			
		return "/bimr/compliance/assignEdit";
	}
	
	/**
	 * 保存合规调查分配人员
	 * 
	 * @param id 合规调查编号
	 * @param followPerson 跟踪人员
	 * @param request {@link HttpServletRequest}
	 * @return 处理结果
	 */
	@RequestMapping(value = "saveAssignFollowPerson", method = RequestMethod.POST, produces = "application/json;chaset=UTF-8")
	@ResponseBody
	public CommitResult saveAssignFollowPerson(@RequestParam Integer id, @RequestParam String followPerson, HttpServletRequest request){
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		return complianceService.saveAssignFollowPerson(id, followPerson, user);
	}
	
	@RequestMapping("/searchPerson")
	public String searchPerson(HttpServletRequest request,String inputname,String inputid, Map<String, Object> map){
		map.put("inputname", inputname);
		map.put("inputid", inputid);
		return "/bimr/compliance/PersonSearch";
	}
	
//	合规调查基本信息
	@RequestMapping(value="bimrComplianceExport")
	public void cpreport(BimrCompliance entity,HttpServletRequest request,Map<String,Object> map,HttpServletResponse response)throws IOException, ParsePropertyException, InvalidFormatException{
		 
		String company = (String)request.getSession().getAttribute("gzwsession_company");
	        //获取数据权限
	        String dataauth=systemService.getDataauth(company);
	        entity.setDataauth(dataauth);
		
		List<BimrCompliance> list=complianceService.getcompliance(entity);
//	不止一个数据所以需要用到list集合 
		
//导出
		XSSFWorkbook  workBook=new XSSFWorkbook();
		XSSFCell cell=null;
//		定义单元格属性为空2
		XSSFSheet sheet=workBook.createSheet("123");
//		创建表名3
		XSSFRow row=sheet.createRow((int)0);

		//		定义表头4
		String [] header={"举报投诉、专项调查项目名称","调查类型 ","调查承办部门","调查涉及企业名称","调查涉及经营管理事项","调查来源",
				"举报投诉收到时间","调查交办领导","调查负责人","数据创建人","数据创建时间"};

		for (int i = 0; i < header.length; i++) {
//字段长度
			cell=row.createCell((short) i);
			cell.setCellValue(header[i]);
		}
//		获取表里面的数据的内容
//		多少条数据
		for (int i = 0; i < list.size(); i++) {
			int k=0;
			row=sheet.createRow((int) i + 1);
			BimrCompliance value=list.get(i);
			row.createCell((short)k++).setCellValue(value.getProjectName());
//	    	 举报投诉、专项调查项目名称
		     row.createCell((short)k++).setCellValue(value.getInvestigationType());
//	    	 调查类型
	     row.createCell((short)k++).setCellValue(value.getInvestigationDep());
//	    	 调查承办部门
	     row.createCell((short)k++).setCellValue(value.getCompName());
//    		调查设计企业名称
	     row.createCell((short)k++).setCellValue(value.getItemName());
//    	 调查涉及经营管理事项
     row.createCell((short)k++).setCellValue(value.getInvestigationFrom());
//    	 调查来源
   
     row.createCell((short)k++).setCellValue(value.getAccusationTime());
//	 举报投诉收到时间
     row.createCell((short)k++).setCellValue(value.getAssignLeader());	
//	 调查交办领导
     row.createCell((short)k++).setCellValue(value.getResponsiblePerson());
//	 调查负责人
     row.createCell((short)k++).setCellValue(value.getCreatePersonName());
//	数据创建人
     row.createCell((short)k++).setCellValue(value.getCreateDate());
//	数据创建时间
     
		}
		 // 清空response
        response.reset();
        String _name = "合规调查基本信息维护.xlsx";
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
	
	
	
//	合规调查导出
//	6868
	 @RequestMapping(value="/queryExportExport")
		public void queryExportExport(BimrCompliance entity1,BimrCompliancePerson entity2,
		BimrComplianceSituation entity3,BimrComplianceSituationPerson  entity4,BimrComplianceSuggest entity5,BimrComplianceBJInfo entity6, 
		HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String vcEmployeeId) 
		throws IOException, ParsePropertyException, InvalidFormatException {
			
	    	 ModelAndView mView = new ModelAndView();
	    	 HttpSession session = request.getSession();
	         HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
	         String orgId = (String)session.getAttribute("gzwsession_company");
	         mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(orgId)));
	         String dataAuthority = systemService.getDataauth(orgId);
	         String curPageNum = request.getParameter("pageNums");
		        if (curPageNum == null) {
		        	curPageNum = "1";
		        }
			

	        Integer pageNum = Integer.valueOf(curPageNum);
	        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
	        mView.addObject("projectStatusProgress", list);
	        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
	        vcEmployeeId=user.getVcEmployeeId();
//	        赋值
			
			List<Object[]> list1=new ArrayList<Object[]>();

		    list1=complianceService.getQueryExport(entity1,entity2,entity3,entity4,entity5,entity6,dataAuthority,vcEmployeeId);
				
//		        查询123
		        XSSFWorkbook workBook1 = new XSSFWorkbook();
//				
		        workBook1=complianceService.getQueryExportWorkbook1(list1);
					     
					     // 清空response档案查询
					        response.reset();
					        String _name = "合规调查导出.xlsx";
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
					        workBook1.write(toClient);
					        toClient.flush();
					        toClient.close(); 
				}
//	
	
	
		
//		合规调查导出
//		6868
		 @RequestMapping(value="/queryExportExporttest")
			public void queryExportExporttest(BimrCompliance entity,
			HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String vcEmployeeId) 
			throws IOException, ParsePropertyException, InvalidFormatException {
				
			 HttpSession session=request.getSession();
			 HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			 entity.setResponsiblePersonId(use.getVcEmployeeId());
			 String company = (String)session.getAttribute("gzwsession_company");
			 //获取数据权限
			 String dataauth=systemService.getDataauth(company);
			 entity.setDataauth(dataauth);
//		        赋值
//			        查询123
			        XSSFWorkbook workBook1 = new XSSFWorkbook();
//					
			        workBook1=complianceService.complanceExport(entity);
						     
						     // 清空response档案查询
						        response.reset();
						        String _name = "合规调查导出.xlsx";
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
						        workBook1.write(toClient);
						        toClient.flush();
						        toClient.close(); 
					}

	
	
	
	
	
	
	
	
}
