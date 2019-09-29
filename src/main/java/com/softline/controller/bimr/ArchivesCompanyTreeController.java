package com.softline.controller.bimr;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.drools.compiler.lang.dsl.DSLMapParser.mapping_file_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.common.DES;
import com.softline.entity.EmployeeAccountabilityViewId;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entity.bimr.BimrAccountable;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrCompliancePerson;
import com.softline.entity.bimr.BimrComplianceProgress;
import com.softline.entity.bimr.BimrCompliancePrompt;
import com.softline.entity.bimr.BimrComplianceSituation;
import com.softline.entity.bimr.BimrComplianceSituationPerson;
import com.softline.entity.bimr.BimrComplianceSuggest;
import com.softline.entity.bimr.BimrCriminalcaseWeek;
import com.softline.entity.bimr.BimrDuty;
import com.softline.entity.bimr.BimrInsideAuditDifficult;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrRiskEvent;

import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.CompliancePersionAbility;
import com.softline.service.bimr.IBimrInsideAuditDifficultService;
import com.softline.service.bimr.IBimrInsideAuditProjectService;
import com.softline.service.bimr.IBimrInsideAuditQuestionService;
import com.softline.service.bimr.ICaseService;
import com.softline.service.bimr.IComplianceService;
import com.softline.service.bimr.IDutyService;
import com.softline.service.bimr.IEmpArchivesService;
import com.softline.service.bimr.impl.BimrInsideAuditPojectService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.impl.CommonService;
import com.softline.util.MsgPage;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 企业风控档案页面控制
 * 
 * @author liu
 */
@Controller
@RequestMapping("/bimr/archives/companyTree")
public class ArchivesCompanyTreeController {
	
	@Autowired
	private ISelectUserService selectUserService;
	
	@Autowired
	private ISystemService systemService;
	
	@Autowired
	private IEmpArchivesService empArchivesService;
	
	 @Resource(name = "iBimrInsideAuditProjectService")
	    private IBimrInsideAuditProjectService iBimrInsideAuditProjectService;

	    @Resource(name = "iBimrInsideAuditDifficultService")
		private IBimrInsideAuditDifficultService iBimrInsideAuditDifficultService;

	    @Resource(name = "iBimrInsideAuditQuestionService")
	    private IBimrInsideAuditQuestionService iBimrInsideAuditQuestionService;
	    @Resource(name = "complianceService")
		private IComplianceService complianceService;
	    @Resource(name = "examineService")
		private IExamineService examineService;
	    @Resource(name = "dutyService")
		private IDutyService dutyService;
	    @Resource(name = "commonService")
		private ICommonService commonService;
	    @Resource(name="caseService")
	    private ICaseService caseService;
	    @Resource(name="baseService")
		private IBaseService baseService;
	    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@RequestMapping("list")
	public String list(HttpServletRequest request, Map<String, Object> map) {
		 HttpSession session = request.getSession();
	        String orgId = (String)session.getAttribute("gzwsession_company");
		List<CompanyTree> tree = selectUserService.getHrOrganal(orgId);
		//String json=JSON.toJSONString(new Object[]{tree});
		String json=JSON.toJSONString(tree);
		map.put("companyTree", json);
		return "/bimr/archives/companyTree";
	}
	
	@RequestMapping("companyContent")
	public String companyContent(@RequestParam String compId, Map<String, Object> map){
		HhOrganInfo t = systemService.getOrganInfoById(compId);
		map.put("company", t);
		/*map.put("auditList", getAuditList(compId));
		map.put("complianeList", getComplianceList(compId));
		map.put("contractCivilcaseWeekList", getContractListCivilcaseWeek(compId));
		map.put("contractCriminalcaseWeekList", getContractListCriminalcaseWeek(compId));
		map.put("contractMonthList", getContractMonthList(compId));
		map.put("riskList", getRiskList(compId));*/
		
		return "/bimr/archives/companyTreeContent";
	}
	
	//涉及审计项目情况
	@RequestMapping("companyContentAuditp")
	public String getAuditList(@RequestParam String compId,HttpServletRequest request,Map<String, Object> map){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  empArchivesService.getFkAuditProjects(compId, pageNum, Base.pagesize/2);
		map.put("msgPage", msgPage);
		map.put("flag","companyContentAuditp");
		map.put("searchurl", request.getContextPath()+"/bimr/archives/companyTree/companyContentAuditp");
		return "/bimr/archives/companyContentAuditpView";
	}
	
	//涉及合规调查情况
	@RequestMapping("companyContentCompliance")
	public String getComplianceList(@RequestParam String compId,HttpServletRequest request,Map<String, Object> map){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  empArchivesService.getFkCompliance(compId, pageNum, Base.pagesize/2);
		map.put("msgPage", msgPage);
		map.put("flag","companyContentCompliance");
		map.put("searchurl", request.getContextPath()+"/bimr/archives/companyTree/companyContentCompliance");
		return "/bimr/archives/companyContentComplianceView";
	}
	
	//法务管理民事案件情况
	@RequestMapping("companyContentCivilcaseWeek")
	public String getContractListCivilcaseWeek(@RequestParam String compId,HttpServletRequest request,Map<String, Object> map){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  empArchivesService.getFkCivilcaseWeek(compId, pageNum, Base.pagesize/2);
		map.put("msgPage", msgPage);
		map.put("flag","companyContentCivilcaseWeek");
		map.put("searchurl", request.getContextPath()+"/bimr/archives/companyTree/companyContentCivilcaseWeek");
		return "/bimr/archives/companyContentCivilcaseWeekView";
	}
	//法务管理刑事案件情况
		@RequestMapping("companyContentCriminalcaseWeek")
		public String getContractListCriminalcaseWeek(@RequestParam String compId,HttpServletRequest request,Map<String, Object> map){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  empArchivesService.getFkCriminalcaseWeek(compId, pageNum, Base.pagesize/2);
		map.put("msgPage", msgPage);
		map.put("flag","companyContentCriminalcaseWeek");
		map.put("searchurl", request.getContextPath()+"/bimr/archives/companyTree/companyContentCriminalcaseWeek");
		return "/bimr/archives/companyContentCriminalcaseWeekView";	
		}
	//法务管理合同情况
		@RequestMapping("companyContentContractMonth")
		public String getContractMonthList(@RequestParam String compId,HttpServletRequest request,Map<String, Object> map){
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        Integer pageNum = Integer.valueOf(curPageNum);
			MsgPage msgPage =  empArchivesService.getFkContractMonth(compId, pageNum, Base.pagesize/2);
			map.put("msgPage", msgPage);
			map.put("flag","companyContentContractMonth");
			map.put("searchurl", request.getContextPath()+"/bimr/archives/companyTree/companyContentCriminalcaseWeek");
			return "/bimr/archives/companyContentContractMonthView";	
		}
	//报告风险事件情况
		@RequestMapping("companyContentRisk")
		public String getRiskList(@RequestParam String compId,HttpServletRequest request,Map<String, Object> map){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  empArchivesService.getRisklist(compId, pageNum, Base.pagesize/2);
		map.put("msgPage", msgPage);
		map.put("flag","companyContentRisk");
		map.put("searchurl", request.getContextPath()+"/bimr/archives/companyTree/companyContentRisk");
		return "/bimr/archives/companyContentRiskView";	
		
	}
	
	
	@RequestMapping("EmployeeAccountabilitylist")
	public String EmployeeAccountabilitylist(EmployeeAccountabilityViewId employeeAccountabilityViewId,HttpServletRequest request, Map<String, Object> map,String projectName,String typename,String t) {
		
		//EmployeeAccountabilityViewId employeeAccountabilityViewId=new EmployeeAccountabilityViewId();
		if("query".equals(t)){
			
			if(null!=projectName){
				employeeAccountabilityViewId.setProjectName(projectName);
			}
			if(null!=typename){
				employeeAccountabilityViewId.setTypename(typename);
			}
		}
		HttpSession session = request.getSession();
		 String orgId = (String)session.getAttribute("gzwsession_company");
		 String dataAuthority = systemService.getDataauth(orgId);
		String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        Integer pageNum = Integer.valueOf(curPageNum);
	      // MsgPage msgPage =   empArchivesService.getEmpAccountAbility(entity, pageNum, Base.pagesize);
	        MsgPage msgPage =   empArchivesService.getEmpAccountAbilityId(employeeAccountabilityViewId, pageNum, Base.pagesize,dataAuthority);
	        String mapurl = "/shanghai-gzw/bimr/archives/companyTree/EmployeeAccountabilitylist";
			map.put("searchurl", mapurl);
	       map.put("msgPage", msgPage);
	       map.put("entity", employeeAccountabilityViewId);
		return "/bimr/archives/EmployeeAccountabilitylist";
	}
	@RequestMapping("EmployeeAccountabilityExaminelist")
	public String EmployeeAccountabilityExaminelist(EmployeeAccountabilityViewId employeeAccountabilityViewId,HttpServletRequest request, Map<String, Object> map,String projectName,String typename,String t) {
		
		//EmployeeAccountabilityViewId employeeAccountabilityViewId=new EmployeeAccountabilityViewId();
		if("query".equals(t)){
			
			if(null!=projectName){
				employeeAccountabilityViewId.setProjectName(projectName);
			}
			if(null!=typename){
				employeeAccountabilityViewId.setTypename(typename);
			}
		}
		HttpSession session = request.getSession();
		 String orgId = (String)session.getAttribute("gzwsession_company");
		 String dataAuthority = systemService.getDataauth(orgId);
		
		String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        Integer pageNum = Integer.valueOf(curPageNum);
	      // MsgPage msgPage =   empArchivesService.getEmpAccountAbility(entity, pageNum, Base.pagesize);
	        MsgPage msgPage =   empArchivesService.getEmpAccountAbilityId(employeeAccountabilityViewId, pageNum, Base.pagesize,dataAuthority);
	       map.put("msgPage", msgPage);
	       map.put("entity", employeeAccountabilityViewId);
	       String mapurl = "/shanghai-gzw/bimr/archives/companyTree/EmployeeAccountabilityExaminelist";
			map.put("searchurl", mapurl);
		return "/bimr/archives/EmployeeAccountabilityExaminelist";
	}
	
	@RequestMapping("EmployeeAccountabilityView")
	public String EmployeeAccountabilitylist(HttpServletRequest request, Map<String, Object> map,int id,int projecttype) {
		if(projecttype==1){
	        //审计项目基本信息.
	        BimrInsideAuditProject entity = null;
	        if (id!=0){
	            entity =  iBimrInsideAuditProjectService.getBimrInsideAuditProject(id);
	        }
	    	
	    	map.put("entity", entity);
	    	String type = request.getParameter("type") == null?"":request.getParameter("type");
	    	map.put("type", type);
	    	if ("child".equals(type)) {
	    		BimrInsideAuditProject parent = iBimrInsideAuditProjectService.getBimrInsideAuditProject(entity.getAuditParentId());
	    		map.put("parent", parent);
			}
	        //审计项目发现问题清单.
	        List<BimrInsideAuditQuestion> problem_list =  null;
	        if (entity.getId() != null){
	            problem_list = iBimrInsideAuditQuestionService.getBimrInsideAuditQuestionForList(entity.getId(),null);
	            for (BimrInsideAuditQuestion biaq : problem_list){
	                //审计发现问题类型.
	                StringBuilder qtypeStr = new StringBuilder();
	                String qtypes = biaq.getAuditQuestionTypes();
	                if (Common.notEmpty(qtypes)){
	                    String[] qids = qtypes.split(",");
	                    for (String qid : qids){
	                        String tmp = Base.getAuditQuestionTypes(Integer.parseInt(qid));
	                        qtypeStr.append(tmp).append(";");
	                    }
	                    biaq.setAuditQuestionTypes(qtypeStr.substring(0,qtypeStr.lastIndexOf(";")));
	                }else{
	                    biaq.setAuditQuestionTypes("");
	                }
	                //风险动因类型.
	                StringBuilder rtypeStr = new StringBuilder();
	                String rtypes = biaq.getRiskDriverTypes();
	                if (Common.notEmpty(rtypes)) {
	                    String[] rids = rtypes.split(",");
	                    for(String rid : rids){
	                        String tmp = Base.getRiskDriverTypes(Integer.parseInt(rid));
	                        rtypeStr.append(tmp).append(";");
	                    }
	                    biaq.setRiskDriverTypes(rtypeStr.substring(0,rtypeStr.lastIndexOf(";")));
	                }else{
	                    biaq.setRiskDriverTypes("");
	                }
	            }
	        }
	        map.put("problem_list", problem_list);
	        List<SysExamine> examineList = examineService.getListExamine(id, Base.EmployeeAccountability_insideproject);
	        map.put("examineList", examineList);
	      List<BimrAccountable> accountable_list=empArchivesService.getPersionAccountability(id, projecttype);
	        map.put("accountable_list", accountable_list);
			return "/bimr/archives/EmployeeAccountabilityinsidepView";
		}else if (projecttype==2) {
			BimrCompliance bimrCompliance=complianceService.getComplianceById(id);
			map.put("entity", bimrCompliance);
			
			List<BimrCompliancePerson> personList=complianceService.getCompliancePerson(id);
			map.put("personList", personList);
			
			//举报投诉反映问题线索核查情况
			List<BimrComplianceSituation> situationList0=complianceService.getComplianceSituation(id,0);
			map.put("situationList0", situationList0);
			
			//待调查事项核查情况
			List<BimrComplianceSituation> situationList1=complianceService.getComplianceSituation(id,1);
			map.put("situationList1", situationList1);
			
			//调查发现其他违规违纪事件情况
			List<BimrComplianceSituation> situationList2=complianceService.getComplianceSituation(id,2);
			map.put("situationList2", situationList2);
					
			//调查发现其他违规违纪事件情况
			List<BimrCompliancePrompt> promptList=complianceService.getCompliancePrompt(id);
			map.put("promptList", promptList);
			
			//调查发现其他违规违纪事件情况
			List<BimrComplianceSuggest> suggestList=complianceService.getComplianceSuggest(id);
			map.put("suggestList", suggestList);
			
			//调查发现其他违规违纪事件情况
			List<BimrComplianceProgress> progressList=complianceService.getComplianceProgress(id);
			map.put("progressList", progressList);
			
			//问责人员维护
			List<BimrComplianceSituationPerson> situationPersonList=complianceService.getSituationPersonListByComplianceId(id);
			map.put("situationPersonList", situationPersonList);
			String mapurl = request.getContextPath() + "/bimr/compliance";
			map.put("mapurl", mapurl);
			List<CompliancePersionAbility> compliancePersionAbilities=empArchivesService.getCompliancePersionAbility(id);
			map.put("compliancePersionAbilities", compliancePersionAbilities);
			List<SysExamine> examineList = examineService.getListExamine(id, Base.EmployeeAccountability_complience);
	        map.put("examineList", examineList);
			return "/bimr/archives/EmployeeAccountabilitycomplianceView";
		}else if(projecttype==3){
			BimrCivilcaseWeek entity = caseService.getCivilcaseById(id);
	        map.put("entity", entity);
	       
	        HhFile indictmentFile = null;
	        HhFile judgmentFile = null;
	        HhFile oFile = null;
	        indictmentFile = commonService.getFileByUuid(entity.getIndictment());
	        judgmentFile = commonService.getFileByUuid(entity.getJudgment());
	        oFile = commonService.getFileByUuid(entity.getOtherFile());
	        if(null!=indictmentFile){
	        	map.put("indictmentFile", indictmentFile);
	        }else{
	        	map.put("indictmentFile", null);
	        }
	        if(null!=judgmentFile){
	        	map.put("judgmentFile", judgmentFile);
	        }else{
	        	map.put("judgmentFile", null);
	        }
	        if(null!=oFile){
	        	map.put("oFile", oFile);
	        }else{
	        	map.put("oFile", null);
	        }
	        List<HhBase> reasonlist = baseService.getBases(Base.CASE_REASON);
	        map.put("reasonlist", reasonlist);
	        List<BimrAccountable> accountable_list=empArchivesService.getPersionAccountability(id, projecttype);
	        for (BimrAccountable bimrAccountable : accountable_list) {
				BimrCivilcaseWeek bimrCivilcaseWeek=caseService.getCivilcaseById(bimrAccountable.getProjectId());
				bimrAccountable.setProjectName(bimrCivilcaseWeek.getCaseNum());
			}
	        map.put("accountable_list", accountable_list);
	        return "/bimr/archives/EmployeeAccountabilityciviView";
		}else if (projecttype==4) {
			BimrCriminalcaseWeek entity = caseService.getCriminalcaseById(id);
	        map.put("entity", entity);
	        HhFile reportmentFile = null;
	        HhFile judgmentFile = null;
	        HhFile oFile = null;
	        reportmentFile = commonService.getFileByUuid(entity.getReportment());
	        judgmentFile = commonService.getFileByUuid(entity.getJudgment());
	        oFile = commonService.getFileByUuid(entity.getOtherFile());
	        if(null!=reportmentFile){
	        	map.put("reportmentFile", reportmentFile);
	        }else{
	        	map.put("reportmentFile", null);
	        }
	        if(null!=judgmentFile){
	        	map.put("judgmentFile", judgmentFile);
	        }else{
	        	map.put("judgmentFile", null);
	        }
	        if(null!=oFile){
	        	map.put("oFile", oFile);
	        }else{
	        	map.put("oFile", null);
	        }
	        List<BimrAccountable> accountable_list=empArchivesService.getPersionAccountability(id, projecttype);
	        for (BimrAccountable bimrAccountable : accountable_list) {
				BimrCriminalcaseWeek bimrCriminalcaseWeek=caseService.getCriminalcaseById(bimrAccountable.getProjectId());
				bimrAccountable.setProjectName(bimrCriminalcaseWeek.getCaseNum());
			}
	        map.put("accountable_list", accountable_list);
	        return "/bimr/archives/EmployeeAccountabilitycriminalView";
		}
		
		return null;
	}
	
	
	@RequestMapping("addinsidepAccountable")
	public String addinsidepAccountable(@RequestParam int projectId, Map<String, Object> map){
		
		BimrInsideAuditProject bimrInsideAuditProject=iBimrInsideAuditProjectService.getBimrInsideAuditProject(projectId);
		
		map.put("bimrInsideAuditProject", bimrInsideAuditProject);
		List<BimrInsideAuditQuestion> problem_list =  null;
        if (projectId != 0){
            problem_list = iBimrInsideAuditQuestionService.getBimrInsideAuditQuestionForList(projectId,null);
            for (BimrInsideAuditQuestion biaq : problem_list){
                //审计发现问题类型.
                StringBuilder qtypeStr = new StringBuilder();
                String qtypes = biaq.getAuditQuestionTypes();
                if (Common.notEmpty(qtypes)){
                    String[] qids = qtypes.split(",");
                    for (String qid : qids){
                        String tmp = Base.getAuditQuestionTypes(Integer.parseInt(qid));
                        qtypeStr.append(tmp).append(";");
                    }
                    biaq.setAuditQuestionTypes(qtypeStr.substring(0,qtypeStr.lastIndexOf(";")));
                }else{
                    biaq.setAuditQuestionTypes("");
                }
                //风险动因类型.
                StringBuilder rtypeStr = new StringBuilder();
                String rtypes = biaq.getRiskDriverTypes();
                if (Common.notEmpty(rtypes)) {
                    String[] rids = rtypes.split(",");
                    for(String rid : rids){
                        String tmp = Base.getRiskDriverTypes(Integer.parseInt(rid));
                        rtypeStr.append(tmp).append(";");
                    }
                    biaq.setRiskDriverTypes(rtypeStr.substring(0,rtypeStr.lastIndexOf(";")));
                }else{
                    biaq.setRiskDriverTypes("");
                }
            }
        }
      
      
        map.put("newList", problem_list);
		
		
		return "/bimr/archives/addinsideptAccountable";
	}
	@RequestMapping("addCaseAccountable")
	public String addCaseAccountable(@RequestParam int projectId, Map<String, Object> map,@RequestParam int projectType){
		
		BimrAccountable entity = new BimrAccountable();
		entity.setProjectId(projectId);
		entity.setProjectType(projectType);
		map.put("entity", entity);
		
		return "/bimr/archives/addCaseAccountable";
	}
	@RequestMapping("EmployeeAccountabilityCheckView")
	public String EmployeeAccountabilityCheckView(HttpServletRequest request, Map<String, Object> map,int id,int projecttype,String showtype) {
			
	        if(projecttype==1){
		        //审计项目基本信息.
		        BimrInsideAuditProject entity = null;
		        if (id!=0){
		            entity =  iBimrInsideAuditProjectService.getBimrInsideAuditProject(id);
		        }
		    	
		    	map.put("entity", entity);
		    	String type = request.getParameter("type") == null?"":request.getParameter("type");
		    	map.put("type", type);
		    	if ("child".equals(type)) {
		    		BimrInsideAuditProject parent = iBimrInsideAuditProjectService.getBimrInsideAuditProject(entity.getAuditParentId());
		    		map.put("parent", parent);
				}
		        //审计项目发现问题清单.
		        List<BimrInsideAuditQuestion> problem_list =  null;
		        if (entity.getId() != null){
		            problem_list = iBimrInsideAuditQuestionService.getBimrInsideAuditQuestionForList(entity.getId(),null);
		            for (BimrInsideAuditQuestion biaq : problem_list){
		                //审计发现问题类型.
		                StringBuilder qtypeStr = new StringBuilder();
		                String qtypes = biaq.getAuditQuestionTypes();
		                if (Common.notEmpty(qtypes)){
		                    String[] qids = qtypes.split(",");
		                    for (String qid : qids){
		                        String tmp = Base.getAuditQuestionTypes(Integer.parseInt(qid));
		                        qtypeStr.append(tmp).append(";");
		                    }
		                    biaq.setAuditQuestionTypes(qtypeStr.substring(0,qtypeStr.lastIndexOf(";")));
		                }else{
		                    biaq.setAuditQuestionTypes("");
		                }
		                //风险动因类型.
		                StringBuilder rtypeStr = new StringBuilder();
		                String rtypes = biaq.getRiskDriverTypes();
		                if (Common.notEmpty(rtypes)) {
		                    String[] rids = rtypes.split(",");
		                    for(String rid : rids){
		                        String tmp = Base.getRiskDriverTypes(Integer.parseInt(rid));
		                        rtypeStr.append(tmp).append(";");
		                    }
		                    biaq.setRiskDriverTypes(rtypeStr.substring(0,rtypeStr.lastIndexOf(";")));
		                }else{
		                    biaq.setRiskDriverTypes("");
		                }
		            }
		        }
		        //区分审核页面的查看与审核
		        if("show".equals(showtype)){
		        	map.put("showtype", showtype);
		        }else {
		        	map.put("showtype", null);
				}
		        map.put("problem_list", problem_list);
		      
		        
		      List<BimrAccountable> accountable_list=empArchivesService.getPersionAccountability(id,projecttype);
		        map.put("accountable_list", accountable_list);
		        List<SysExamine> examineList = examineService.getListExamine(id, Base.EmployeeAccountability_insideproject);
		        map.put("examineList", examineList);
		       
		        
				return "/bimr/archives/EmployeeAccountabilityinsidepExamineView";
				}else if (projecttype==2) {
				
				map.put("projectId", id);
				BimrCompliance bimrCompliance=complianceService.getComplianceById(id);
				map.put("entity", bimrCompliance);
				
				List<BimrCompliancePerson> personList=complianceService.getCompliancePerson(id);
				map.put("personList", personList);
				
				//举报投诉反映问题线索核查情况
				List<BimrComplianceSituation> situationList0=complianceService.getComplianceSituation(id,0);
				map.put("situationList0", situationList0);
				
				//待调查事项核查情况
				List<BimrComplianceSituation> situationList1=complianceService.getComplianceSituation(id,1);
				map.put("situationList1", situationList1);
				
				//调查发现其他违规违纪事件情况
				List<BimrComplianceSituation> situationList2=complianceService.getComplianceSituation(id,2);
				map.put("situationList2", situationList2);
						
				//调查发现其他违规违纪事件情况
				List<BimrCompliancePrompt> promptList=complianceService.getCompliancePrompt(id);
				map.put("promptList", promptList);
				
				//调查发现其他违规违纪事件情况
				List<BimrComplianceSuggest> suggestList=complianceService.getComplianceSuggest(id);
				map.put("suggestList", suggestList);
				
				//调查发现其他违规违纪事件情况
				List<BimrComplianceProgress> progressList=complianceService.getComplianceProgress(id);
				map.put("progressList", progressList);
				
				
				List<SysExamine> examineList = examineService.getListExamine(id, Base.EmployeeAccountability_complience);
		        map.put("examineList", examineList);
		        List<CompliancePersionAbility> compliancePersionAbilities=empArchivesService.getCompliancePersionAbility(id);
				map.put("compliancePersionAbilities", compliancePersionAbilities);
				if("show".equals(showtype)){
		        	map.put("showtype", showtype);
		        }else {
		        	map.put("showtype", null);
				}
				return "/bimr/archives/EmployeeAccountabilitycomplianceExamineView";
			}else if(projecttype==3){
				BimrCivilcaseWeek entity = caseService.getCivilcaseById(id);
		        map.put("entity", entity);
		       
		        HhFile indictmentFile = null;
		        HhFile judgmentFile = null;
		        HhFile oFile = null;
		        indictmentFile = commonService.getFileByUuid(entity.getIndictment());
		        judgmentFile = commonService.getFileByUuid(entity.getJudgment());
		        oFile = commonService.getFileByUuid(entity.getOtherFile());
		        if(null!=indictmentFile){
		        	map.put("indictmentFile", indictmentFile);
		        }else{
		        	map.put("indictmentFile", null);
		        }
		        if(null!=judgmentFile){
		        	map.put("judgmentFile", judgmentFile);
		        }else{
		        	map.put("judgmentFile", null);
		        }
		        if(null!=oFile){
		        	map.put("oFile", oFile);
		        }else{
		        	map.put("oFile", null);
		        }
		        List<HhBase> reasonlist = baseService.getBases(Base.CASE_REASON);
		        map.put("reasonlist", reasonlist);
		        List<BimrAccountable> accountable_list=empArchivesService.getPersionAccountability(id, projecttype);
		        for (BimrAccountable bimrAccountable : accountable_list) {
					BimrCivilcaseWeek bimrCivilcaseWeek=caseService.getCivilcaseById(bimrAccountable.getProjectId());
					bimrAccountable.setProjectName(bimrCivilcaseWeek.getCaseNum());
				}
		        map.put("accountable_list", accountable_list);
		        if("show".equals(showtype)){
		        	map.put("showtype", showtype);
		        }else {
		        	map.put("showtype", null);
				}
		        List<SysExamine> examineList = examineService.getListExamine(id, Base.EmployeeAccountability_civicase);
		        map.put("examineList", examineList);
		        return "/bimr/archives/EmployeeAccountabilityciviExamineView";
			}else if (projecttype==4) {
				BimrCriminalcaseWeek entity = caseService.getCriminalcaseById(id);
		        map.put("entity", entity);
		        HhFile reportmentFile = null;
		        HhFile judgmentFile = null;
		        HhFile oFile = null;
		        reportmentFile = commonService.getFileByUuid(entity.getReportment());
		        judgmentFile = commonService.getFileByUuid(entity.getJudgment());
		        oFile = commonService.getFileByUuid(entity.getOtherFile());
		        if(null!=reportmentFile){
		        	map.put("reportmentFile", reportmentFile);
		        }else{
		        	map.put("reportmentFile", null);
		        }
		        if(null!=judgmentFile){
		        	map.put("judgmentFile", judgmentFile);
		        }else{
		        	map.put("judgmentFile", null);
		        }
		        if(null!=oFile){
		        	map.put("oFile", oFile);
		        }else{
		        	map.put("oFile", null);
		        }
		        List<BimrAccountable> accountable_list=empArchivesService.getPersionAccountability(id, projecttype);
		        for (BimrAccountable bimrAccountable : accountable_list) {
					BimrCriminalcaseWeek bimrCriminalcaseWeek=caseService.getCriminalcaseById(bimrAccountable.getProjectId());
					bimrAccountable.setProjectName(bimrCriminalcaseWeek.getCaseNum());
				}
		        if("show".equals(showtype)){
		        	map.put("showtype", showtype);
		        }else {
		        	map.put("showtype", null);
				}
		        List<SysExamine> examineList = examineService.getListExamine(id, Base.EmployeeAccountability_criminal);
		        map.put("examineList", examineList);
		        map.put("accountable_list", accountable_list);
		        return "/bimr/archives/EmployeeAccountabilitycriminalExamineView";
			}
			
	        return "";
	}
	
	@RequestMapping(value="saveAccountable", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveAccountable(int projectId,String accountablePeople,String questioncollect,String levelcollect,String accountablePeopleID){
		if(null!=questioncollect&&null!=questioncollect&&!"".equals(questioncollect)&&!"".equals(questioncollect)){
			for (int i = 0; i < questioncollect.split(",").length; i++) {
				BimrAccountable bimrPersionAccountability=new BimrAccountable();
				bimrPersionAccountability.setAccountabilityPersion(accountablePeople);
				bimrPersionAccountability.setAccountabilityPersionId(accountablePeopleID);
				bimrPersionAccountability.setProjectId(projectId);
				bimrPersionAccountability.setQuestionId(Integer.parseInt(questioncollect.split(",")[i]));
				bimrPersionAccountability.setStatus(1);
				bimrPersionAccountability.setReponsibilityLevel(Integer.parseInt(levelcollect.split(",")[i]));
				bimrPersionAccountability.setProjectType(1);
				bimrPersionAccountability.setIsDel(0);
				empArchivesService.saveAcountable(bimrPersionAccountability);
			}
			BimrInsideAuditProject bimrInsideAuditProject=iBimrInsideAuditProjectService.getBimrInsideAuditProject(projectId);
			 bimrInsideAuditProject.setAccountabilityStatus(5);
			  commonService.saveOrUpdate(bimrInsideAuditProject);
			return "success";
		}else {
			return "false";
		}
	}
	
	@RequestMapping(value="saveCaseAccountable", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveCaseAccountable(BimrAccountable bimrAccountable){
			bimrAccountable.setStatus(1);
			bimrAccountable.setIsDel(0);
			commonService.saveOrUpdate(bimrAccountable);
			if(3==bimrAccountable.getProjectType()){
				BimrCivilcaseWeek bimrCivilcaseWeek=caseService.getCivilcaseById(bimrAccountable.getProjectId());
				bimrCivilcaseWeek.setAccountabilityStatus(5);
				commonService.saveOrUpdate(bimrCivilcaseWeek);
			}else {
				BimrCriminalcaseWeek bimrCriminalcaseWeek=caseService.getCriminalcaseById(bimrAccountable.getProjectId());
				bimrCriminalcaseWeek.setAccountabilityStatus(5);
				commonService.saveOrUpdate(bimrCriminalcaseWeek);
			}
			
			return "success";
		
	}
	
	
	
	
	@RequestMapping(value="saveEmployeeAccountabilityInsidepExamine", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveEmployeeAccountabilityInsidepExamine(int projectId,String examStr,HttpServletRequest request,int examResult){
		  List<BimrAccountable> accountable_list=empArchivesService.getPersionAccountability(projectId,1);
		  HttpSession session=request.getSession();
		  HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		  examineService.saveExamine(projectId, Base.EmployeeAccountability_insideproject, user, examStr, examResult);
		  BimrInsideAuditProject bimrInsideAuditProject=iBimrInsideAuditProjectService.getBimrInsideAuditProject(projectId);
		  if(examResult==50116){
			  for (int i = 0; i < accountable_list.size(); i++) {
				  if (accountable_list.get(i).getStatus()==5) {
					continue;
				}
				  accountable_list.get(i).setStatus(3);
				  accountable_list.get(i).setExamineContent(examStr);
				  
				  empArchivesService.saveAcountable(accountable_list.get(i));
			}
			  bimrInsideAuditProject.setAccountabilityStatus(2);
			  commonService.saveOrUpdate(bimrInsideAuditProject);
		  }else{
			  for (int i = 0; i < accountable_list.size(); i++) {
				  if(accountable_list.get(i).getStatus()==3||accountable_list.get(i).getStatus()==5){
					  continue;
				  }
				  accountable_list.get(i).setStatus(4);
				  accountable_list.get(i).setExamineContent(examStr);
				  empArchivesService.saveAcountable(accountable_list.get(i));
			}
			  bimrInsideAuditProject.setAccountabilityStatus(3);
			  commonService.saveOrUpdate(bimrInsideAuditProject);
		  }
		 
		 return "success";
	}
	@RequestMapping(value="saveEmployeeAccountabilityComplianceExamine", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveEmployeeAccountabilityComplianceExamine(int projectId,String examStr,int examResult,HttpServletRequest request){
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		examineService.saveExamine(projectId, Base.EmployeeAccountability_complience, user, examStr, examResult);
		BimrCompliance compliance =complianceService.getComplianceById(projectId);
		if(50116==examResult){
			compliance.setAccountabilityStatus(2);
		}else {
			compliance.setAccountabilityStatus(3);
		}
		
		commonService.saveOrUpdate(compliance);
		 return "success";
	}
	
	@RequestMapping(value="saveEmployeeAccountabilityCaseExamine", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveEmployeeAccountabilityCaseExamine(int projectId,String examStr,HttpServletRequest request,int examResult,int projectType){
		  List<BimrAccountable> accountable_list=empArchivesService.getPersionAccountability(projectId,projectType);
		  HttpSession session=request.getSession();
		  HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		  if(3==projectType){
			  examineService.saveExamine(projectId, Base.EmployeeAccountability_civicase, user, examStr, examResult);
			  BimrCivilcaseWeek bimrCivilcaseWeek=caseService.getCivilcaseById(projectId);
			  
			  if(examResult==50116){
				  for (int i = 0; i < accountable_list.size(); i++) {
					  if (accountable_list.get(i).getStatus()==5) {
						continue;
					}
					  accountable_list.get(i).setStatus(3);
					  accountable_list.get(i).setExamineContent(examStr);
					  
					  empArchivesService.saveAcountable(accountable_list.get(i));
					  bimrCivilcaseWeek.setAccountabilityStatus(2);
					  commonService.saveOrUpdate(bimrCivilcaseWeek);
				}
				  
			  }else{
				  for (int i = 0; i < accountable_list.size(); i++) {
					  if(accountable_list.get(i).getStatus()==3||accountable_list.get(i).getStatus()==5){
						  continue;
					  }
					  accountable_list.get(i).setStatus(4);
					  accountable_list.get(i).setExamineContent(examStr);
					  empArchivesService.saveAcountable(accountable_list.get(i));
					  bimrCivilcaseWeek.setAccountabilityStatus(3);
					  commonService.saveOrUpdate(bimrCivilcaseWeek);
				}
			  }
		  }else {
			  examineService.saveExamine(projectId, Base.EmployeeAccountability_criminal, user, examStr, examResult);
			  BimrCriminalcaseWeek bimrCriminalcaseWeek=caseService.getCriminalcaseById(projectId);
			 
			  if(examResult==50116){
				  for (int i = 0; i < accountable_list.size(); i++) {
					  if (accountable_list.get(i).getStatus()==5) {
						continue;
					}
					  accountable_list.get(i).setStatus(3);
					  accountable_list.get(i).setExamineContent(examStr);
					  
					  empArchivesService.saveAcountable(accountable_list.get(i));
					  bimrCriminalcaseWeek.setAccountabilityStatus(2);
					  commonService.saveOrUpdate(bimrCriminalcaseWeek);
				}
				  
			  }else{
				  for (int i = 0; i < accountable_list.size(); i++) {
					  if(accountable_list.get(i).getStatus()==3||accountable_list.get(i).getStatus()==5){
						  continue;
					  }
					  accountable_list.get(i).setStatus(4);
					  accountable_list.get(i).setExamineContent(examStr);
					  empArchivesService.saveAcountable(accountable_list.get(i));
					  bimrCriminalcaseWeek.setAccountabilityStatus(3);
					  commonService.saveOrUpdate(bimrCriminalcaseWeek);
				}
			  }
		}
		 
		 
		 return "success";
	}
	
	
	@RequestMapping("EmployeeAbilityInformation")
	public String EmployeeAbilityInformation(String accountablePeopleId,String accountablePeople,HttpServletRequest request, Map<String, Object> map,int projectId,int projecttype,int id,int questionId){
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		
		BimrDuty entity=new BimrDuty();
		
		entity.setPerson(accountablePeople);
		entity.setPersonId(accountablePeopleId);
		entity.setAccountableId(id);
		entity.setProjectId(projectId);
		entity.setQuestionId(questionId);
		if(projecttype==1){
			entity.setSource(4);
		}else if(projecttype==2){
			entity.setSource(5);
		}else {
			entity.setSource(2);
		}
		map.put("entity", entity);
		 map.put("projectId", projectId);
		 map.put("projecttype", projecttype);
		 map.put("accountablePeopleId", id);
		 return "/bimr/archives/EmployeeAccountabilityInformation";
	}
	
	@RequestMapping(value="commitEmployeeAccountabilityExamine", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String commitEmployeeAccountabilityExamine(int projectId,int projecttype,HttpServletRequest request){
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		List<BimrAccountable> bimrAccountablelist=empArchivesService.getPersionAccountability(projectId, projecttype);
		if(1==projecttype){
			 BimrInsideAuditProject bimrInsideAuditProject=iBimrInsideAuditProjectService.getBimrInsideAuditProject(projectId);
			 bimrInsideAuditProject.setAccountabilityStatus(1);
			 commonService.saveOrUpdate(bimrInsideAuditProject);
			 for (BimrAccountable bimrAccountable : bimrAccountablelist) {
				 if (bimrAccountable.getStatus()==1||bimrAccountable.getStatus()==4) {
					 bimrAccountable.setStatus(2);
				}
				 commonService.saveOrUpdate(bimrAccountable);
			}
		}else if(3==projecttype){
			BimrCivilcaseWeek bimrCivilcaseWeek=caseService.getCivilcaseById(projectId);
			bimrCivilcaseWeek.setAccountabilityStatus(1);
			commonService.saveOrUpdate(bimrCivilcaseWeek);
			 for (BimrAccountable bimrAccountable : bimrAccountablelist) {
				 if (bimrAccountable.getStatus()==1||bimrAccountable.getStatus()==4) {
					 bimrAccountable.setStatus(2);
				}
				 commonService.saveOrUpdate(bimrAccountable);
			}
		}else if(4==projecttype){
			BimrCriminalcaseWeek bimrCriminalcaseWeek=caseService.getCriminalcaseById(projectId);
			bimrCriminalcaseWeek.setAccountabilityStatus(1);
			
			 for (BimrAccountable bimrAccountable : bimrAccountablelist) {
				 if (bimrAccountable.getStatus()==1||bimrAccountable.getStatus()==4) {
					 bimrAccountable.setStatus(2);
					 commonService.saveOrUpdate(bimrAccountable);
				}
			}
			 commonService.saveOrUpdate(bimrCriminalcaseWeek);
		}
		
		
		 return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate")
	public String saveOrUpdate(@ModelAttribute("bimrDuty") BimrDuty entity, HttpServletRequest request,
			@RequestParam(value="file",required=false) MultipartFile multipartFile,int projecttype,int accountablePeopleId) throws IOException, ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		
		if (entity.getId() == null) {
			entity.setStatus(Base.examstatus_1);
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			String package_path = DES.DUTY_PATH;
			dutyService.save(entity, user, multipartFile, package_path);
		}
		if(2!=projecttype){
			BimrAccountable bimrAccountable =empArchivesService.getBimrAccountableById(accountablePeopleId);
			//设置状态，已保存问责信息
			bimrAccountable.setStatus(5);
			commonService.saveOrUpdate(bimrAccountable);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndReported(@ModelAttribute("bimrDuty") BimrDuty entity, HttpServletRequest request,@RequestParam(value="file",required=false) MultipartFile multipartFile,int projecttype,int accountablePeopleId) throws IOException, ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setStatus(Base.examstatus_2);
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			entity.setReportPersonId(user.getVcEmployeeId());
			entity.setReportPersonName(user.getVcName());
			entity.setReportDate(df.format(new Date()));
			String package_path = DES.DUTY_PATH;
			dutyService.save(entity, user, multipartFile, package_path);
		}
		if(2!=projecttype){
			BimrAccountable bimrAccountable =empArchivesService.getBimrAccountableById(accountablePeopleId);
			//设置状态，已保存问责信息
			bimrAccountable.setStatus(5);
			commonService.saveOrUpdate(bimrAccountable);
		}
		return "success";
	}
	 @RequestMapping("/searchPerson")
		public String searchPerson(HttpServletRequest request,String personname,String personid, Map<String, Object> map){
	    	map.put("personname", personname);
	    	map.put("personid", personid);
			return "/bimr/bimrInsideAuditProject/PersonSearch";
	}
	 
	 @RequestMapping("/showDuty")
		public String showDuty(HttpServletRequest request,Integer accountableId, Map<String, Object> map){
	    	
		 
		 
		 	map.put("op", "check");
			BimrDuty bimrDuty = dutyService.getBimrDutyByAccountableId(accountableId);
			map.put("bimrDuty", bimrDuty);
			//查看、上报时，可以查看到审批记录
			if(null!=bimrDuty){
				List<SysExamine> examineList = examineService.getListExamine(bimrDuty.getId(), Base.DUTY_EXAMINE);
				map.put("examineList", examineList);
				HhFile hfile = commonService.getFileByEnIdAndType(bimrDuty.getId(), Base.DUTY_ENCLOSURE);
				if (hfile != null) {
					map.put("file_Path", hfile.getFilePath());
					map.put("file_Name", hfile.getFileName());
				} else {
					map.put("file_Path1", "");
					map.put("file_Name1", "");
				}
			}
			
			return "/bimr/duty/view";
		 
	}
	 
	    @RequestMapping(value = "/deleteAccountable")
		@ResponseBody
		public String deleteAccountable(Integer id, Map<String, Object> map, HttpServletRequest request) {
			BimrAccountable bimrAccountable=empArchivesService.getBimrAccountableById(id);
			bimrAccountable.setIsDel(1);
			commonService.saveOrUpdate(bimrAccountable);
			return "success";
		} 
	    
	    @RequestMapping(value = "/updateAccountable")
		public String updateAccountable(Integer id, Map<String, Object> map, HttpServletRequest request) {
			BimrAccountable bimrAccountable=empArchivesService.getBimrAccountableById(id);
			map.put("entity", bimrAccountable);
			return "/bimr/archives/accountableModify";
		} 
}
