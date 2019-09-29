package com.softline.controller.bimr;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrInsideAuditMeasures;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditRectify;
import com.softline.service.bimr.IBimrInsideAuditProjectService;
import com.softline.service.bimr.IBimrInsideAuditQuestionService;
import com.softline.service.bimr.IBimrInsideAuditRectifyService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * 内部审计发现问题
 * */
@Controller
@RequestMapping("/bimr/question")
public class BimrInsideAuditQuestionController {

    private Logger logger = Logger.getLogger(BimrInsideAuditQuestionController.class);

    @Resource(name = "iBimrInsideAuditQuestionService")
    private IBimrInsideAuditQuestionService iBimrInsideAuditQuestionService;

    @Resource(name = "iBimrInsideAuditProjectService")
    private IBimrInsideAuditProjectService iBimrInsideAuditProjectService;
    
    @Resource(name = "bimrInsideAuditRectifyService")
    private IBimrInsideAuditRectifyService bimrInsideAuditRectifyService;
    
    @Resource(name = "systemService")
    private ISystemService systemService;

    @Resource(name = "baseService")
    private IBaseService baseService;

    @Resource(name = "selectUserService")
    private ISelectUserService selectUserService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    DecimalFormat df = new DecimalFormat("###0.0000");

    @RequestMapping("/list")
    public ModelAndView fillInsideAuditProject(BimrInsideAuditProject entity, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        String orgId = (String)session.getAttribute("gzwsession_company");
        mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(orgId)));
        //数据权限范围内的企业信息
        String dataAuthority = systemService.getDataauth(orgId);
        if (Common.notEmpty(entity.getAuditImplDeptId())) {
        	if (entity.getAuditImplDeptId().split(",").length==1) {
        		entity.setAuditImplDeptId(systemService.getDataauth(entity.getAuditImplDeptId()));
			}
        }
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
            curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = iBimrInsideAuditProjectService.getInsideAuditProjectQuestion(entity, pageNum, Base.pagesize, dataAuthority, "", user.getVcEmployeeId());        
        mView.addObject("msgPage", msgPage);
        mView.addObject("entity", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("projectStatusProgress", list);
        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
        //mView.addObject("searchurl", "/shanghai-gzw/bimr/question/list");
        mView.setViewName("/bimr/bimrInsideAuditProject/insideAuditProjectQuestionList");
        return mView;
    }

    /**
     * 内部审计项目问题列表展示.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/questionList")
    public ModelAndView questionList(BimrInsideAuditQuestion entity, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        String projectIds = "";
        if(request.getParameter("projectId") != null){
        	projectIds = request.getParameter("projectId").toString();
            mView.addObject("projectId", projectIds);
        }
        if(request.getParameter("projectId") == null){
            mView.addObject("projectId", projectIds);
        }
        String str = (String)session.getAttribute("gzwsession_company");
        mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
        String dataAuthority = systemService.getDataauth(str);
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
            curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = iBimrInsideAuditQuestionService.getBimrInsideAuditQuestionts(entity, pageNum, Base.pagesize, dataAuthority);
        mView.addObject("msgPage", msgPage);
        mView.addObject("auditQuestion", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("auditProperty", list);
        mView.addObject("searchurl", "/shanghai-gzw/bimr/question/questionList?entity="+projectIds);
        mView.setViewName("bimr/bimrInsideAuditProject/questionList");
        return mView;
    }

    /**
     * 未完成审计项目发现问题查询查看.
     * @param request
     * @return
     */
  /* @RequestMapping(value="unclosedView")
    public ModelAndView _un_closed_view(HttpServletRequest request,Integer id){
        ModelAndView mView = new ModelAndView();
      
        if (id!=null){
        	
        }
        return mView;
    }*/

    /**
     * 审计项目发现问题类型与成因分析查询.
     * @param entity
     * @param request
     * @return
     */
    public ModelAndView _analysis(BimrInsideAuditQuestion entity, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        mView.setViewName("/bimr/seo/unclosedQuestionList");
        return mView;
    }

    /**
     * 内部审计项目问题添加或者修改.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/newOrModify")
    public ModelAndView newOrModifyProjects(BimrInsideAuditQuestion entity, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String op = request.getParameter("op") == null?"":request.getParameter("op");
        mView.addObject("op", op);
        String projectIds = request.getParameter("projectId");
        BimrInsideAuditProject project = null;
        if (Common.notEmpty(projectIds)){
            project = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(projectIds));
        }
        HttpSession session = request.getSession();
        //String company = (String)session.getAttribute("gzwsession_company");
        String company=Base.HRTop;
        mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        BimrInsideAuditQuestion ap;
        if (entity.getId() != null) {
            ap = iBimrInsideAuditQuestionService.getBimrInsideAuditQuestion(entity);
        }else{
            ap = new BimrInsideAuditQuestion();
        }
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("auditProperty", list);
        if(project != null && project.getAuditDeptedName()!=null){
       	 ap.setProblemAttrUnitName(project.getAuditDeptedName());
       }
       if(project == null){
    	   project = new BimrInsideAuditProject();
    	   project.setId(ap.getProjectId());
    	   project.setAuditProjectName(ap.getProjectName());
       }
       if(ap.getRectifyTrackName()==null&&ap.getRectifyTrackId()==null){
       	ap.setRectifyTrackId(project.getRectifyTrackId());
       	ap.setRectifyTrackName(project.getRectifyTrackName());
       	
       }
       mView.addObject("auditQuestion", ap);
       mView.addObject("project", project);
      
        //List<BimrInsideAuditProject> projects = iBimrInsideAuditProjectService.getBimrInsideAuditProjectsForList(Base.PROJECT_ENABLE);
        List<BimrInsideAuditProject> projects = iBimrInsideAuditProjectService.getBimrInsideAuditProjectsIsOrNotChild(Base.IS_CHILD_PROJECT);
        mView.addObject("projects",projects);
        mView.setViewName("/bimr/bimrInsideAuditProject/questionNewOrModify");
        return mView;
    }

    /**
     * 内部审计项目问题保存或者更新.
     * @param entity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public JSONObject saveOrUpdateQuestions(@ModelAttribute("auditQuestion") BimrInsideAuditQuestion entity, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
        try {
            if (entity.getId() == null) {
                entity.setIsDel(0);
                entity.setCreateDate(sdf.format(new Date()));
                entity.setCreatePersonId(user.getVcEmployeeId());
                entity.setCreatePersonName(user.getVcName());
                iBimrInsideAuditQuestionService.saveBimrInsideAuditQuestion(entity);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目问题保存成功!");
            }else{
                entity.setLastModifyDate(sdf.format(new Date()));
                entity.setLastModifyPersonId(user.getVcEmployeeId());
                entity.setLastModifyPersonName(user.getVcName());
                iBimrInsideAuditQuestionService.updateBimrInsideAuditQuestion(entity);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目问题更新成功!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部审计项目问题保存更新失败!");
        }

        return jsonObject;
    }

    /**
     * 删除内部审计项目问题.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public JSONObject _delete(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            String ids = request.getParameter("id");
            if (Common.notEmpty(ids)) {
                 iBimrInsideAuditQuestionService.deleteBimrInsideAuditQuestion(Integer.parseInt(ids));
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目删除成功!");
            }else{
                jsonObject.put("result", 0);
                jsonObject.put("message", "内部审计项目删除失败!");
            }
            jsonObject.put("result", 1);
            jsonObject.put("message", "内部审计项目删除成功!");
        } catch (Exception e) {
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部审计项目删除失败!");
        }
        return jsonObject;
    }

    /**
     * 查看内部审计项目.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/view")
    public ModelAndView _view(HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String ids = request.getParameter("id");
        BimrInsideAuditQuestion entity = iBimrInsideAuditQuestionService.getBimrInsideAuditQuestion(Integer.parseInt(ids));
        mView.addObject("entity", entity);
        StringBuilder qtypeStr = new StringBuilder();
        String qtypes = entity.getAuditQuestionTypes();
        if (Common.notEmpty(qtypes)) {
        	String[] qids = qtypes.split(",");
            for (String id : qids){
                String tmp = Base.getAuditQuestionTypes(Integer.parseInt(id));
                qtypeStr.append(tmp).append(" | ");
            }
            mView.addObject("qtypes",qtypeStr.substring(0,qtypeStr.lastIndexOf(" | ")));
		}else{			
			mView.addObject("qtypes","");
		}
        
        StringBuilder rtypeStr = new StringBuilder();
        String rtypes = entity.getRiskDriverTypes();
        if (Common.notEmpty(rtypes)) {
        	String[] rids = rtypes.split(",");
            for(String id : rids){
                String tmp = Base.getRiskDriverTypes(Integer.parseInt(id));
                rtypeStr.append(tmp).append(" | ");
            }
            mView.addObject("rtypes",rtypeStr.substring(0,rtypeStr.lastIndexOf(" | ")));
		}else{			
			mView.addObject("rtypes","");
		}
        
        
        mView.setViewName("bimr/bimrInsideAuditProject/questionView");
        return mView;
    }

    /**
     * 内部审计问题维护.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/question_maintent")
    public ModelAndView _revise(HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String ids = request.getParameter("id");
        List<BimrInsideAuditQuestion> problem_detail = null;
        BimrInsideAuditProject entity = null;
        if (Common.notEmpty(ids)){
            problem_detail = iBimrInsideAuditQuestionService.getBimrInsideAuditQuestionForList(Integer.parseInt(ids),null);
            entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
        }
        mView.addObject("entity", entity);
        mView.addObject("problem_detail", problem_detail);
        mView.addObject("difficult_detail", null);
        mView.setViewName("/bimr/maintent/questionMaintentDetailView");
        return mView;
    }
    
    /**
     * 未完成审计项目发现问题查询.
     * 
     * @param entity  {@link BimrInsideAuditProject}查询参数
     * @param yearMonth 查询年月
     * @param pageNums 查询数据页数
     * @param request {@link HttpServletRequest}
     * @param map 渲染页面数据
     * @return
     */
//    未完成审计项目问题发现查询
    @RequestMapping("/unclosed")
    public String unclosed(BimrInsideAuditProject entity, 
    		@RequestParam(required=false) String yearMonth,
    		@RequestParam(defaultValue = "1")Integer pageNums,
    		HttpServletRequest request, 
    		Map<String, Object> map){
    	
        HttpSession session = request.getSession();
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        String company = (String)session.getAttribute("gzwsession_company");
        String allCompanyTree = JSON.toJSONString(selectUserService.getHrOrganal(company));
        map.put("allCompanyTree", allCompanyTree);
        String dataAuthority = systemService.getDataauth(company);
        if (Common.notEmpty(entity.getAuditImplDeptId())) {
        	if (entity.getAuditImplDeptId().split(",").length==1) {
        		entity.setAuditImplDeptId(systemService.getDataauth(entity.getAuditImplDeptId()));
			}
        }
        MsgPage msgPage = iBimrInsideAuditQuestionService.getUnFinishListView(yearMonth, entity, pageNums, Base.pagesize,dataAuthority,user.getVcEmployeeId());
        map.put("msgPage", msgPage);
        map.put("entity", entity);
        List<HhBase> auditProps = baseService.getBases(Base.auditProjectProperty);
        map.put("auditProps", auditProps);
        map.put("yearMonth", yearMonth);
        
        return "/bimr/bimrInsideAuditProject/questionUnclosedList";
    }
    
//    审计未完成项目发现问题查询。bimr_inside_audit_project
    @RequestMapping(value="/unFinishExport")
	public void unFinishExport(BimrInsideAuditProject entity,BimrInsideAuditQuestion entity1,BimrInsideAuditRectify entity2,BimrInsideAuditMeasures entity3,String yearMonth,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String vcEmployeeId) throws IOException, ParsePropertyException, InvalidFormatException {
		
    	 ModelAndView mView = new ModelAndView();
    	 HttpSession session = request.getSession();
         HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
         String orgId = (String)session.getAttribute("gzwsession_company");
         mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(orgId)));
         String dataAuthority = systemService.getDataauth(orgId);
         if (Common.notEmpty(entity.getAuditImplDeptId())) {
 			entity.setAuditImplDeptId(systemService.getDataauth(entity.getAuditImplDeptId()));
 		}
         String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
		

        Integer pageNum = Integer.valueOf(curPageNum);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        
        mView.addObject("projectStatusProgress", list);
        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
        vcEmployeeId=user.getVcEmployeeId();
//        赋值
		
		List<Object[]> list1=new ArrayList<Object[]>();

	    list1=iBimrInsideAuditQuestionService.getUnfinishExport(entity,entity1,entity2,entity3,yearMonth,dataAuthority,vcEmployeeId);
			
//	        查询123
	        XSSFWorkbook workBook1 = new XSSFWorkbook();
//			
	        workBook1=iBimrInsideAuditQuestionService.getgetUnfinishExportWorkbook1(list1);
				     
				     // 清空response档案查询
				        response.reset();
				        String _name = "审计未完成整改项目统计表导出.xlsx";
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
    
    
    
    
    
   @RequestMapping("/unclosedview")
    public String unclosedView(@RequestParam(required=false) Integer id, HttpServletRequest request, Map<String, Object> map){
    	
        List<BimrInsideAuditRectify> bimrInsideAuditRectifylist = bimrInsideAuditRectifyService.getRectifyQuestionByAnswerIdList(id);
        
        BimrInsideAuditQuestion bimrInsideAuditQuestion=iBimrInsideAuditQuestionService.getBimrInsideAuditQuestion(id);
        
        BimrInsideAuditProject bimrInsideAuditProject=iBimrInsideAuditProjectService.getBimrInsideAuditProject(bimrInsideAuditQuestion.getProjectId());
        
        List<BimrInsideAuditMeasures> bimrInsideAuditMeasurelist=new ArrayList<BimrInsideAuditMeasures>();
        for (int i = 0; i < bimrInsideAuditRectifylist.size(); i++) {
        	bimrInsideAuditMeasurelist.addAll(bimrInsideAuditRectifyService.getBimrInsideRectifyMeasures(bimrInsideAuditRectifylist.get(i).getId()));
		}
        map.put("bimrInsideAuditRectifylist", bimrInsideAuditRectifylist);
        map.put("bimrInsideAuditMeasurelist", bimrInsideAuditMeasurelist);
        map.put("bimrInsideAuditQuestion", bimrInsideAuditQuestion);
        map.put("bimrInsideAuditProject", bimrInsideAuditProject);
        return "/bimr/bimrInsideAuditProject/questionUnclosedView";
    }
    
    /**
     * 未完成审计项目发现问题查询.
     * 
     * @param entity  {@link BimrInsideAuditProject}查询参数
     * @param yearMonth 查询年月
     * @param pageNums 查询数据页数
     * @param request {@link HttpServletRequest}
     * @param map 渲染页面数据
     * @return
     */
    @RequestMapping("/analysis")
    public String getQuestionAnalyzeList(BimrInsideAuditProject entity, 
    		@RequestParam(required=false) String yearMonth,
    		@RequestParam(defaultValue = "1")Integer pageNums,
    		HttpServletRequest request, 
    		Map<String, Object> map){
    	
    	if(StringUtils.isBlank(yearMonth)){
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
    		yearMonth = dateFormat.format(new Date());
    	}
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        String allCompanyTree = JSON.toJSONString(selectUserService.getHrOrganal(company));
        map.put("allCompanyTree", allCompanyTree);
        String dataAuthority = systemService.getDataauth(company);
        if (Common.notEmpty(entity.getAuditImplDeptId())) {
        	if (entity.getAuditImplDeptId().split(",").length==1) {
        		entity.setAuditImplDeptId(systemService.getDataauth(entity.getAuditImplDeptId()));
			}
        }
        MsgPage msgPage = iBimrInsideAuditQuestionService.getQuestionAnalyzeListView(yearMonth, entity, pageNums, Base.pagesize,dataAuthority,user.getVcEmployeeId() );
        map.put("msgPage", msgPage);
        map.put("entity", entity);
        List<HhBase> auditProps = baseService.getBases(Base.auditProjectProperty);
        map.put("auditProps", auditProps);
        map.put("yearMonth", yearMonth);
        
        return "/bimr/bimrInsideAuditProject/questionAnalyzeList";
    }
    
    /**
     * 内部审计问题维护.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getQuestionDetail",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String getQuestDetail(Integer id, HttpServletRequest request){
    	request.getParameter("id");
        BimrInsideAuditQuestion problem = iBimrInsideAuditQuestionService.getBimrInsideAuditQuestion(id);
        if(problem == null){
        	return "";
        }
        else{
        	String questionStr = JSON.toJSONString(problem);
        	return questionStr;
        }
    }
    
}
