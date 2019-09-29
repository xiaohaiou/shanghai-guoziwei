package com.softline.controller.bimr;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.StringFieldDeserializer;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.controller.common.commonController;
import com.softline.entity.AuditProject;
import com.softline.entity.AuditProjectFindQuestion;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.bimr.BimrInsideAuditDifficult;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditWeekly;
import com.softline.service.bimr.IBimrInsideAuditDifficultService;
import com.softline.service.bimr.IBimrInsideAuditProjectService;
import com.softline.service.bimr.IBimrInsideAuditQuestionService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * 
 * 审计项目建立
 * 
 * */
@Controller
@RequestMapping("/bimr/inside")
public class BimrInsideAuditProjectController {

	private Logger logger = Logger.getLogger(BimrInsideAuditProjectController.class);
	
    @Resource(name = "iBimrInsideAuditProjectService")
    private IBimrInsideAuditProjectService iBimrInsideAuditProjectService;

    @Resource(name = "iBimrInsideAuditDifficultService")
	private IBimrInsideAuditDifficultService iBimrInsideAuditDifficultService;

    @Resource(name = "iBimrInsideAuditQuestionService")
    private IBimrInsideAuditQuestionService iBimrInsideAuditQuestionService;

    @Resource(name = "systemService")
    private ISystemService systemService;
    
    @Resource(name = "commonService")
    private ICommonService commonService;

    @Resource(name = "baseService")
    private IBaseService baseService;

    @Resource(name = "selectUserService")
    private ISelectUserService selectUserService;
    
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    DecimalFormat df = new DecimalFormat("###0.0000");

    /**
     * 内部审计项目填报列表.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/fill")
    public ModelAndView fillInsideAuditProject(BimrInsideAuditProject entity, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        String orgId = (String)session.getAttribute("gzwsession_company");
        mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(orgId)));
        //数据权限范围内的企业信息
        String dataAuthority = systemService.getDataauth(orgId);
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
            curPageNum = "1";
        }
        if (Common.notEmpty(entity.getAuditImplDeptId())) {
        	if (entity.getAuditImplDeptId().split(",").length==1) {
        		entity.setAuditImplDeptId(systemService.getDataauth(entity.getAuditImplDeptId()));
			}
        }	
        Integer pageNum = Integer.valueOf(curPageNum);
        entity.setIsChildren(Base.IS_NOT_CHILD_PROJECT);
        MsgPage msgPage = iBimrInsideAuditProjectService.getBimrInsideAuditProjects(entity, pageNum, Base.pagesize, dataAuthority, "", user.getVcEmployeeId());
        mView.addObject("msgPage", msgPage);
        mView.addObject("entity", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("auditProjectProperty", list);
        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
        mView.setViewName("/bimr/bimrInsideAuditProject/bimrInsideAuditProjectStartList");
        return mView;
    }
   
    
//  审计结果应用细化指标反馈表导出auditResultsAppRefineExport**********    @ZZ
  @RequestMapping(value="/auditResultsAppRefineExport")
	public void auditResultsAppRefineExport(BimrInsideAuditProject entity,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String vcEmployeeId) throws IOException, ParsePropertyException, InvalidFormatException {
		
  	 ModelAndView mView = new ModelAndView();
  	 HttpSession session = request.getSession();
       HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
       String orgId = (String)session.getAttribute("gzwsession_company");
       mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(orgId)));
       String dataAuthority = systemService.getDataauth(orgId);
       if (Common.notEmpty(entity.getAuditImplDeptId())) {
       	if (entity.getAuditImplDeptId().split(",").length==1) {
       		entity.setAuditImplDeptId(systemService.getDataauth(entity.getAuditImplDeptId()));
			}
       }
      List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
      vcEmployeeId=user.getVcEmployeeId();
//      赋值
		
		List<Object[]> list1=new ArrayList<Object[]>();

			
//	        查询123
	        XSSFWorkbook workBook1 = new XSSFWorkbook();
//			
	        workBook1=iBimrInsideAuditProjectService.getResultsAppRefineExportWorkbook(entity,dataAuthority,vcEmployeeId);
				     
				     // 清空response档案查询
				        response.reset();
				        String _name = "审计结果应用细化指标反馈表.xlsx";
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
    
    /**
     * 审计项目进场.
     */
    @ResponseBody
    @RequestMapping("/march-into")
    public JSONObject _marchInto(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            String ids = request.getParameter("id");
            if (Common.notEmpty(ids)){
                BimrInsideAuditProject entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
                entity.setStatus(Base.PROJECT_ENABLE);
                iBimrInsideAuditProjectService.updateBimrProjectCode(entity);
                jsonObject.put("result", 1);
                jsonObject.put("message", "审计项目启动成功!");
            }else{
                jsonObject.put("result", 0);
                jsonObject.put("message", "审计项目启动失败!");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "审计项目启动失败!");
        }
        return jsonObject;
    }
    
    /**
     * 内部审计项目添加或者修改.
     * @param entity
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/newOrModify")
    public ModelAndView newOrModifyProjects(BimrInsideAuditProject entity,@RequestParam(required=false, defaultValue="")String op,
    		@RequestParam(required=false, defaultValue="")String type,@RequestParam(required=false, defaultValue="")String projectisstart,Map<String, Object> map, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
//        String op = request.getParameter("op") == null?"":request.getParameter("op");
        mView.addObject("op", op);
//        String type = request.getParameter("type") == null?"":request.getParameter("type");
        mView.addObject("type",type);
        HttpSession session = request.getSession();
        String mainProjectId = "";
        if(request.getParameter("mainId") != null){
        	mainProjectId = request.getParameter("mainId").toString();
        }
        //String company = (String)session.getAttribute("gzwsession_company");
        String company=Base.HRTop;
        mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        BimrInsideAuditProject auditProject;
        if (entity.getId() != null) {
            auditProject = iBimrInsideAuditProjectService.getBimrInsideAuditProject(entity);
        }else{
            auditProject = new BimrInsideAuditProject();
        }
        if(!mainProjectId.equals("")){
        	BimrInsideAuditProject mainProject = new BimrInsideAuditProject();
        	mainProject.setId(Integer.parseInt(mainProjectId));
        	mainProject = iBimrInsideAuditProjectService.getBimrInsideAuditProject(mainProject);
            mView.addObject("mainProject", mainProject);
        }
        if(projectisstart.equals("y")){
        	mView.addObject("projectisstart",true);
        }
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("auditProjectProperty", list);
        mView.addObject("auditProject", auditProject);
        mView.setViewName("/bimr/bimrInsideAuditProject/bimrInsideAuditProjectNewOrModify");
        return mView;
    }
    
	//审计填报导出
	@RequestMapping(value = "/export_InsideAuditProject", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void export_InsideAuditProject(BimrInsideAuditProject entity,HttpServletRequest request, HttpServletResponse response) throws ParsePropertyException, InvalidFormatException, IOException {
		HttpSession session = request.getSession();
	    HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
	    String str = (String)session.getAttribute("gzwsession_company");
	    String dataAuthority = systemService.getDataauth(str);
	    if (Common.notEmpty(entity.getAuditImplDeptId())) {
        	if (entity.getAuditImplDeptId().split(",").length==1) {
        		entity.setAuditImplDeptId(systemService.getDataauth(entity.getAuditImplDeptId()));
			}
        }
	    entity.setIsChildren(Base.IS_NOT_CHILD_PROJECT);
	    List<BimrInsideAuditProject> list = iBimrInsideAuditProjectService.getBimrInsideAuditProjectList(entity,null,null,dataAuthority,"");
	    
		String Item = Common.getItemPath(DES.audit, request) + Base.audit_fill;
		String Item_temp = Common.getItemPath(DES.audit, request) + Base.getTemp2k3ExcelFileName();
		XLSTransformer former = new XLSTransformer();
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("list", list);
		former.transformXLS(Item, beanParams, Item_temp);
		String expRecReportName ="审计填报明细数据.xls";
		commonController.doDownLoad(Item_temp, expRecReportName, response);
		Base.deleteTempFile(Item_temp);
		
	}
	
	
//	审计项目填报导出666
	 @RequestMapping(value="/InsideXMExport")
		public void InsideExportXM(BimrInsideAuditProject entity,BimrInsideAuditWeekly entity1,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String vcEmployeeId) throws IOException, ParsePropertyException, InvalidFormatException {
			
	    	 ModelAndView mView = new ModelAndView();
	    	 HttpSession session = request.getSession();
	         HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
	         String orgId = (String)session.getAttribute("gzwsession_company");
	         mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(orgId)));
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
	        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
	        mView.addObject("projectStatusProgress", list);
	        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
	        vcEmployeeId=user.getVcEmployeeId();
//	        赋值
			
			List<Object[]> list1=new ArrayList<Object[]>();

		    list1=iBimrInsideAuditProjectService.getInsideExportXM(entity,entity1, dataAuthority,vcEmployeeId);
				
//		        查询123
		        XSSFWorkbook workBook1 = new XSSFWorkbook();
//				
		        workBook1=iBimrInsideAuditProjectService.getInsideExportWorkbook1(list1);
					     
					     // 清空response档案查询
					        response.reset();
					        String _name = "审计项目台账.xlsx";
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
	

    /**
     * 查询办结审计项目.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/closed")
    public ModelAndView closedInsideAuditProject(BimrInsideAuditProject entity, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        String company = (String)session.getAttribute("gzwsession_company");
        String allCompanyTree = JSON.toJSONString(selectUserService.getHrOrganal(company));
        mView.addObject("allCompanyTree", allCompanyTree);
        String dataAuthority = systemService.getDataauth(company);
        if (Common.notEmpty(entity.getAuditImplDeptId())) {
        	if (entity.getAuditImplDeptId().split(",").length==1) {
        		entity.setAuditImplDeptId(systemService.getDataauth(entity.getAuditImplDeptId()));
			}
        }else {
			entity.setAuditImplDeptId(dataAuthority);
		}
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null){
            curPageNum = "1";
        }
        //找出项目状态为办结申请中  已退回 和 已完成的 的记录
        entity.setMorestatus("50229,50226,50230,50231");
        
        Integer pageNum = Integer.parseInt(curPageNum);
        entity.setIsChildren(Base.IS_CHILD_PROJECT);
        MsgPage msgPage = iBimrInsideAuditProjectService.getBimrInsideAuditProjects(entity, pageNum, Base.pagesize, dataAuthority, "", user.getVcEmployeeId());
        mView.addObject("msgPage", msgPage);
        mView.addObject("entity", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("auditProjectProperty", list);
        mView.setViewName("bimr/seo/closedProjectList");
        return mView;
    }

    
    /**
     * 内部审计项目保存或者更新.
     * @param entity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public JSONObject saveOrUpdateProjects(@ModelAttribute("auditProject") BimrInsideAuditProject entity, HttpServletRequest request){
    	JSONObject jsonObject = new JSONObject();
    	HttpSession session = request.getSession();
    	HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
    	try {
    		if (entity.getId() == null) {
        		entity.setIsDel(0);
    			entity.setCreateDate(sdf.format(new Date()));
    			entity.setCreatePersonId(user.getVcEmployeeId());
    			entity.setCreatePersonName(user.getVcName());
    			entity.setEstatus(Base.examstatus_2);
    			entity.setStatus(Base.PROJECT_NO_ENABLE);
    			//父级的id为空则为主项目.
    			if (entity.getAuditParentId() == null){
    			    entity.setIsChildren(Base.IS_NOT_CHILD_PROJECT);
                }else{
    			    entity.setIsChildren(Base.IS_CHILD_PROJECT);
                }
    			iBimrInsideAuditProjectService.saveBimrInsideAuditProject(entity);
    			jsonObject.put("result", 1);
    			jsonObject.put("message", "审计项目保存成功!");
    		}else{
    			entity.setLastModifyDate(sdf.format(new Date()));
    			entity.setLastModifyPersonId(user.getVcEmployeeId());
    			entity.setLastModifyPersonName(user.getVcName());
                //父级的id为空则为主项目.
                if (entity.getAuditParentId() == null){
                    entity.setIsChildren(Base.IS_NOT_CHILD_PROJECT);
                }else{
                    entity.setIsChildren(Base.IS_CHILD_PROJECT);
                }
    			iBimrInsideAuditProjectService.updateBimrInsideAuditProject(entity);
    			jsonObject.put("result", 1);
    			jsonObject.put("message", "内部审计项目更新成功!");
    		}
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonObject.put("result", 0);
			jsonObject.put("message", "内部审计项目保存更新失败!");
		}
    	
    	return jsonObject;
    }
    
    /**
     * 删除内部审计项目.
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
    			iBimrInsideAuditProjectService.deleteBimrInsideAuditProject(Integer.parseInt(ids));
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
        //审计项目基本信息.
        BimrInsideAuditProject entity = null;
        if (Common.notEmpty(ids)){
            entity =  iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
        }
    	mView.addObject("entity", entity);
    	String type = request.getParameter("type") == null?"":request.getParameter("type");
    	mView.addObject("type", type);
    	if ("child".equals(type)) {
    		BimrInsideAuditProject parent = iBimrInsideAuditProjectService.getBimrInsideAuditProject(entity.getAuditParentId());
    		mView.addObject("parent", parent);
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
                    for (String id : qids){
                        String tmp = Base.getAuditQuestionTypes(Integer.parseInt(id));
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
                    for(String id : rids){
                        String tmp = Base.getRiskDriverTypes(Integer.parseInt(id));
                        rtypeStr.append(tmp).append(";");
                    }
                    biaq.setRiskDriverTypes(rtypeStr.substring(0,rtypeStr.lastIndexOf(";")));
                }else{
                    biaq.setRiskDriverTypes("");
                }
            }
        }
        mView.addObject("problem_list", problem_list);
        //困难与措施.
        List<BimrInsideAuditDifficult> diffList = null;
        if (entity.getId() != null){
            diffList = iBimrInsideAuditDifficultService.getBimrInsideAuditDifficultForList(entity.getId());
        }
        mView.addObject("difficult_list", diffList);
    	mView.setViewName("/bimr/bimrInsideAuditProject/bimrInsideAuditProjectView");
    	return mView;
    }

    /**
     * 查看内部审计项目-全部信息.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/mview")
    public ModelAndView _mview(HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String ids = request.getParameter("id");
        BimrInsideAuditProject entity = null;
        if (Common.notEmpty(ids)){
            entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
        }
        mView.addObject("entity", entity);
        mView.setViewName("/bimr/question/mView");
        return mView;
    }

    /**
     * 查看办结审计项目.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/closedView")
    public ModelAndView _closedView(HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String ids = request.getParameter("id");
        //审计项目基本信息.
        BimrInsideAuditProject entity = null;
        if (Common.notEmpty(ids)){
            entity =  iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
        }
        mView.addObject("entity", entity);
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
                    for (String id : qids){
                        String tmp = Base.getAuditQuestionTypes(Integer.parseInt(id));
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
                    for(String id : rids){
                        String tmp = Base.getRiskDriverTypes(Integer.parseInt(id));
                        rtypeStr.append(tmp).append(";");
                    }
                    biaq.setRiskDriverTypes(rtypeStr.substring(0,rtypeStr.lastIndexOf(";")));
                }else{
                    biaq.setRiskDriverTypes("");
                }
            }
        }
        mView.addObject("problem_list", problem_list);
        mView.setViewName("/bimr/seo/closedView");
        return mView;
    }

    
    /**
     * 填写申请办结备注
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/applyCloseRemarks")
    public ModelAndView newOrModifyProjects(Integer id, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
      /*  BimrInsideAuditProject entity =iBimrInsideAuditProjectService.getBimrInsideAuditProject(id);
        mView.addObject("entity",entity);*/
        BimrInsideAuditProject entity = new BimrInsideAuditProject();
        mView.addObject("bimr", entity);
        mView.addObject("id",id);
        mView.setViewName("/bimr/maintent/applyCloseRemarks");
        return mView;
    }
    
    /**
     * 审计项目填报-申请办结.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/close", method = RequestMethod.POST)
    public JSONObject _close(HttpServletRequest request,BimrInsideAuditProject bimr){
        JSONObject jsonObject = new JSONObject();
        try {
              String ids = request.getParameter("id");
              BimrInsideAuditProject entity = null;
              if (Common.notEmpty(ids)){
                  entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
                  entity.setStatus(Base.PROJECT_IS_APPLYING);
                  entity.setEstatus(Base.examstatus_2);
                  entity.setApplycloseremarks(bimr.getApplycloseremarks());
                  iBimrInsideAuditProjectService.updateBimrInsideAuditProject(entity);
                  jsonObject.put("result", 1);
                  jsonObject.put("message","项目申请办结成功!");
              }else{
                  jsonObject.put("result", 0);
                  jsonObject.put("message","项目申请办结失败!");
              }
        }catch (Exception e){
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message","项目申请办结失败!");
        }
        return jsonObject;
    }
    
    /**
     * 审计项目填报-申请办结.
     * @param request
     * @return
     */
    @RequestMapping(value ="/projectCloseApply")
    public String projectCloseApply(BimrInsideAuditProject entity, Map<String, Object> map, HttpServletRequest request){
        String id = request.getParameter("id")==null?"":request.getParameter("id").toString();
        entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(id));
        map.put("auditProject",entity);
        List<HhFile> sjFiles = new ArrayList<HhFile>();
        HhFile mailFile = null;
        if(!id.equals("")){
        	if (null!=entity.getSjFileId()) {
        		String[] sjFileIds=entity.getSjFileId().split(",");
            	for (int i = 0; i < sjFileIds.length; i++) {
            		sjFiles.add(commonService.getFileByUuid(sjFileIds[i]));
    			}
			}
            mailFile = commonService.getFileByUuid(entity.getMailFileId());
        }
        map.put("sjFiles", sjFiles);
        map.put("mailFile", mailFile);
        return "/bimr/bimrInsideAuditProject/bimrInsideAuditProjectCloseApply";
    }
    
    /**
     * 内部审计项目办结申请保存.
     * @param entity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveCloseApply",method = RequestMethod.POST)
    public JSONObject saveCloseApply(@ModelAttribute("auditProject") BimrInsideAuditProject entity, HttpServletRequest request,@RequestParam(value="sjFile",required=true) MultipartFile[] sjFile,@RequestParam(value="mailFile",required=false) MultipartFile mailFile){
    	JSONObject jsonObject = new JSONObject();
    	HttpSession session = request.getSession();
    	HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
    	try {
    			iBimrInsideAuditProjectService.saveCloseApply(entity,sjFile,mailFile);
			
			jsonObject.put("result", 1);
			jsonObject.put("message", "内部审计项目更新成功!");
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonObject.put("result", 0);
			jsonObject.put("message", "内部审计项目保存更新失败!");
		}
    	return jsonObject;
    }
    
    /**
     * 审计项目填报-申请办结.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/submitCloseApply",method = RequestMethod.POST)
    public JSONObject submitCloseApply(@ModelAttribute("auditProject") BimrInsideAuditProject entity, HttpServletRequest request,
    		@RequestParam(value="sjFile",required=true) MultipartFile[] sjFile,@RequestParam(value="mailFile",required=false) MultipartFile mailFile){
        JSONObject jsonObject = new JSONObject();
    	HttpSession session = request.getSession();
    	HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
    	entity.setStatus(Base.PROJECT_IS_APPLYING);
    	try {
			iBimrInsideAuditProjectService.saveCloseApply(entity,sjFile,mailFile);
			jsonObject.put("result", 1);
			jsonObject.put("message", "内部审计项目更新成功!");
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonObject.put("result", 0);
			jsonObject.put("message", "内部审计项目保存更新失败!");
		}
    	return jsonObject;
    }  

    /**
     * 查看内部审计项目.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/eview")
    public ModelAndView _eview(HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String ids = request.getParameter("id");
        BimrInsideAuditProject entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
        mView.addObject("entity", entity);
        
        if (entity.getIsChildren() == 1) {
        	BimrInsideAuditProject parent = iBimrInsideAuditProjectService.getBimrInsideAuditProject(entity.getAuditParentId());
    		mView.addObject("parent", parent);
		}
        List<HhFile> sjFiles = new ArrayList<HhFile>();
        HhFile mailFile = null;
        if(!ids.equals("")){
        	if (null!=entity.getSjFileId()) {
        	String[] sjFileIds=entity.getSjFileId().split(",");
        	for (int i = 0; i < sjFileIds.length; i++) {
        		sjFiles.add(commonService.getFileByUuid(sjFileIds[i]));
			}
        	}
            mailFile = commonService.getFileByUuid(entity.getMailFileId());
        }
        mView.addObject("sjFiles", sjFiles);
        mView.addObject("mailFile", mailFile);
        mView.setViewName("/bimr/bimrInsideAuditProject/bimrInsideAuditProjectExamineView");
        
        return mView;
    }

    /**
     * 内部项目审核列表.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/examine")
    public ModelAndView _examine(BimrInsideAuditProject entity, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        String str = (String)session.getAttribute("gzwsession_company");
        mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
        String dataAuthority = systemService.getDataauth(str);
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
        entity.setIsChildren(Base.IS_CHILD_PROJECT);
        //找出项目状态为办结申请中  已退回 和 已完成的 的记录
        //entity.setMorestatus("50227,"+"50228,"+"50229");
        //entity.setStatus(Base.PROJECT_IS_APPLYING);
        entity.setMorestatus("");
        MsgPage msgPage = iBimrInsideAuditProjectService.getBimrInsideAuditProjects(entity, pageNum, Base.pagesize, dataAuthority, "", user.getVcEmployeeId());
        mView.addObject("msgPage", msgPage);
        mView.addObject("entity", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("auditProperty", list);
        List<HhBase> rstatus = baseService.getBases(Base.examstatustype);
        mView.addObject("rstatus", rstatus);
        mView.setViewName("/bimr/bimrInsideAuditProject/bimrInsideAuditProjectExamineList");
        return mView;
    }
    
    
//	审计报告导出888
	 @RequestMapping(value="/ExamineXMExport")
		public void ExamineXMExport(BimrInsideAuditProject entity,BimrInsideAuditQuestion entity1,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String vcEmployeeId) throws IOException, ParsePropertyException, InvalidFormatException {
			
	    	 ModelAndView mView = new ModelAndView();
	    	 HttpSession session = request.getSession();
	         HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
	         String orgId = (String)session.getAttribute("gzwsession_company");
	         mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(orgId)));
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
	        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
	        mView.addObject("projectStatusProgress", list);
	        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
	        vcEmployeeId=user.getVcEmployeeId();
//	        赋值
			
			List<Object[]> list1=new ArrayList<Object[]>();

		    list1=iBimrInsideAuditProjectService.getInsideExportXM1(entity,entity1, dataAuthority,vcEmployeeId);
				
		        
		        XSSFWorkbook workBook1 = new XSSFWorkbook();
//				
		        workBook1=iBimrInsideAuditProjectService.getInsideExportWorkbook2(list1);
					     
					     // 清空response档案查询
					        response.reset();
					        String _name = "审计报告台账.xlsx";
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

    @ResponseBody
    @RequestMapping("/examineView")
    public ModelAndView _examineView(HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String ids = request.getParameter("id");
        BimrInsideAuditProject entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
        mView.addObject("entity", entity);
        List<HhFile> sjFiles = new ArrayList<HhFile>();
        HhFile mailFile = null;
        if(!ids.equals("")){
        	if (null!=entity.getSjFileId()) {
        	String[] sjFileIds=entity.getSjFileId().split(",");
        	for (int i = 0; i < sjFileIds.length; i++) {
        		sjFiles.add(commonService.getFileByUuid(sjFileIds[i]));
			}
        	}
            mailFile = commonService.getFileByUuid(entity.getMailFileId());
        }
        mView.setViewName("/bimr/bimrInsideAuditProject/bimrInsideAuditProjectExamine");
        mView.addObject("sjFiles", sjFiles);
        mView.addObject("mailFile", mailFile);
        return mView;
    }

    /**
     * 内部项目审核通过.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/pass")
    public JSONObject _pass(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
        try {
            String ids = request.getParameter("id");
            String op = request.getParameter("op");
            if (Common.notEmpty(ids)){
                BimrInsideAuditProject entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
                entity.setEstatus(Base.examstatus_4);
                entity.setStatus(Base.PROJECT_IS_RECTIFYING);
                entity.setExaminePersonId(user.getVcEmployeeId());
                entity.setExaminePersonName(user.getVcName());
                entity.setExamineDate(sdf.format(new Date()));
                entity.setOpinion(op);
                iBimrInsideAuditProjectService.updateBimrInsideAuditProject(entity);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部项目审核通过!");
            }else{
                jsonObject.put("result", 0);
                jsonObject.put("message", "内部项目审核异常!");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部项目审核异常!");
        }
        return jsonObject;
    }

    /**
     * 内部项目审核退回.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/reject")
    public JSONObject _reject(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
        try {
            String ids = request.getParameter("id");
            String op = request.getParameter("op");
            if (Common.notEmpty(ids)){
                BimrInsideAuditProject entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
                entity.setEstatus(Base.examstatus_3);
                entity.setStatus(Base.PROJECT_IS_RETURN);
                entity.setExaminePersonId(user.getVcEmployeeId());
                entity.setExaminePersonName(user.getVcName());
                entity.setExamineDate(sdf.format(new Date()));
                entity.setOpinion(op);
                iBimrInsideAuditProjectService.updateBimrInsideAuditProject(entity);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部项目审核退回!");
            }else{
                jsonObject.put("result", 0);
                jsonObject.put("message", "内部项目审核异常!");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部项目审核异常!");
        }
        return jsonObject;
    }

    /**
     * 审计项目维护-问题维护.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/questionMaintent")
    public ModelAndView _questionMaintent(HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String ids = request.getParameter("id");
        BimrInsideAuditProject entity = null;
        if (Common.notEmpty(ids)){
            entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
        }
        mView.addObject("entity", entity);
        //主项目对应子项目的记录.
        List<BimrInsideAuditProject> children_list = iBimrInsideAuditProjectService.getBimrInsideAuditProjectForChildren(Integer.parseInt(ids));
        mView.addObject("children", children_list);

        mView.setViewName("/bimr/maintent/questionMaintentView");
        return mView;
    }

    /**
     * 审计项目维护-子项目查看基本信息.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/childrenView")
    public ModelAndView _childrenView(HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String ids = request.getParameter("id");
        BimrInsideAuditProject child = null;
        if (Common.notEmpty(ids)){
            child = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
        }
        mView.addObject("entity",child);
        List<BimrInsideAuditQuestion> child_question_list =  iBimrInsideAuditQuestionService.getBimrInsideAuditQuestionForList(Integer.parseInt(ids),null);
        mView.addObject("child_questions", child_question_list);
        mView.setViewName("/bimr/maintent/childView");
        return mView;
    }
    
    @RequestMapping("/searchPerson")
	public String searchPerson(HttpServletRequest request,String personname,String personid, Map<String, Object> map){
    	map.put("personname", personname);
    	map.put("personid", personid);
		return "/bimr/bimrInsideAuditProject/PersonSearch";
	}
    
    @RequestMapping("/download")
	public void download(HttpServletRequest request,String path,String fileName,HttpServletResponse response) throws IOException{
    	 response.setContentType("application/x-msdownload");
         response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
         
         InputStream inputStream = new FileInputStream(path);
         byte[] buffer = new byte[1024];
         int len = 0;
         while ((len = inputStream.read()) != -1) {
             inputStream.read(buffer, 0, len);
         }
         inputStream.close();
	}
    

    /**
     * 内部审计项目填报列表.
     * @param entity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/finished")
    public JSONObject _finishedInsideAuditProject(Integer id, HttpServletRequest request){
    	JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
        try {
            String ids = request.getParameter("id");
            String op = request.getParameter("op");
            if (Common.notEmpty(ids)){
                BimrInsideAuditProject entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
                entity.setEstatus(Base.examstatus_2);
                entity.setStatus(Base.PROJECT_FINISH_APPLY);
                entity.setExaminePersonId(user.getVcEmployeeId());
                entity.setExaminePersonName(user.getVcName());
                entity.setExamineDate(sdf.format(new Date()));
                entity.setOpinion(op);
                iBimrInsideAuditProjectService.updateBimrInsideAuditProject(entity);
                PortalMsg msg = new PortalMsg();
        		msg.setTitle("内部审计项目关闭申请需要审核！");
        		msg.setBusiness("bimWork_bimr_insideAuditProject");
        		msg.setDate(sdf.format(new Date()));
        		msg.setDescription("内部审计项目关闭申请需要审核！");
        		msg.setTableName("bimr_inside_audit_project");
        		msg.setRecordId(entity.getId().toString());
        		msg.setDelFlag(0);
        		msg.setType(0);
        		potalMsgService.savePortalMsg(msg);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目关闭申请成功!");
            }else{
                jsonObject.put("result", 0);
                jsonObject.put("message", "内部审计项目关闭申请异常!");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部审计项目关闭申请异常!");
        }
        return jsonObject;
    }
    

    /**
     * 内部审计项目关闭审核列表.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/examineFinishedList")
    public ModelAndView _examineFinishedList(BimrInsideAuditProject entity, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        String compId = (String)session.getAttribute("gzwsession_company");
        mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(compId)));
        String dataAuthority = systemService.getDataauth(compId);
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
        entity.setIsChildren(Base.IS_CHILD_PROJECT);
        //找出项目状态为办结申请中  已退回 和 已完成的 的记录
        //entity.setMorestatus("50227,"+"50228,"+"50229");
        //entity.setStatus(Base.PROJECT_IS_APPLYING);
        entity.setMorestatus("");
        MsgPage msgPage = iBimrInsideAuditProjectService.getBimrInsideAuditProjects(entity, pageNum, Base.pagesize, dataAuthority, "", user.getVcEmployeeId());
        mView.addObject("msgPage", msgPage);
        mView.addObject("entity", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("auditProperty", list);
        List<HhBase> rstatus = baseService.getBases(Base.examstatustype);
        mView.addObject("rstatus", rstatus);
        mView.setViewName("/bimr/bimrInsideAuditProject/bimrInsideAuditProjectFinishedExamineList");
        return mView;
    }
    
    @ResponseBody
    @RequestMapping("/examineFinishedView")
    public ModelAndView _examineFinishedView(HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String ids = request.getParameter("id");
        BimrInsideAuditProject entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
        mView.addObject("entity", entity);
        List<HhFile> sjFiles = new ArrayList<HhFile>();
        HhFile mailFile = null;
        if(!ids.equals("")){
        	if (null!=entity.getSjFileId()) {
        	String[] sjFileIds=entity.getSjFileId().split(",");
        	for (int i = 0; i < sjFileIds.length; i++) {
        		sjFiles.add(commonService.getFileByUuid(sjFileIds[i]));
			}
        	}
            mailFile = commonService.getFileByUuid(entity.getMailFileId());
        }
        mView.setViewName("/bimr/bimrInsideAuditProject/bimrInsideAuditProjectFinishedExamine");
        mView.addObject("sjFiles", sjFiles);
        mView.addObject("mailFile", mailFile);
        return mView;
    }
    
    /**
     * 内部项目审核通过.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/finishedPass")
    public JSONObject _finishedPass(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
        try {
            String ids = request.getParameter("id");
            String op = request.getParameter("op");
            if (Common.notEmpty(ids)){
                BimrInsideAuditProject entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
                entity.setEstatus(Base.examstatus_4);
                entity.setStatus(Base.PROJECT_FINISH);
                entity.setExaminePersonId(user.getVcEmployeeId());
                entity.setExaminePersonName(user.getVcName());
                entity.setExamineDate(sdf.format(new Date()));
                entity.setOpinion(op);
                iBimrInsideAuditProjectService.updateBimrInsideAuditProject(entity);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部项目关闭审核通过!");
            }else{
                jsonObject.put("result", 0);
                jsonObject.put("message", "内部项目关闭审核异常!");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部项目关闭审核异常!");
        }
        return jsonObject;
    }
    
    /**
     * 内部项目审核退回.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/finishedReject")
    public JSONObject _finishedReject(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
        try {
            String ids = request.getParameter("id");
            String op = request.getParameter("op");
            if (Common.notEmpty(ids)){
                BimrInsideAuditProject entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
                entity.setEstatus(Base.examstatus_3);
                entity.setStatus(Base.PROJECT_IS_RECTIFYING);
                entity.setExaminePersonId(user.getVcEmployeeId());
                entity.setExaminePersonName(user.getVcName());
                entity.setExamineDate(sdf.format(new Date()));
                entity.setOpinion(op);
                iBimrInsideAuditProjectService.updateBimrInsideAuditProject(entity);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部项目关闭审核退回!");
            }else{
                jsonObject.put("result", 0);
                jsonObject.put("message", "内部项目关闭审核异常!");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部项目关闭审核异常!");
        }
        return jsonObject;
    }
    @ResponseBody
    @RequestMapping("/delfile")
    public JSONObject delfile(HttpServletRequest request,Integer id,String uuid){
        JSONObject jsonObject = new JSONObject();
      
        try {
        	 BimrInsideAuditProject bimrInsideAuditProject=iBimrInsideAuditProjectService.getBimrInsideAuditProject(id);
             String sjFileId = bimrInsideAuditProject.getSjFileId();
             String newsjFileId=null; 
             if(null!=sjFileId){
             	for (int i = 0; i < sjFileId.split(",").length; i++) {
         			if(!sjFileId.split(",")[i].equals(uuid)){
         				if(null!=newsjFileId){
         					newsjFileId=newsjFileId+","+sjFileId.split(",")[i];
         				}else {
         					newsjFileId=sjFileId.split(",")[i];
     					}
         				
         			}
         		}
             }
             bimrInsideAuditProject.setSjFileId(newsjFileId);
             Common.deleteFile(DES.INSIDE_AUDIT_PROJECT, uuid);
			HhFile file = commonService.getFileByUuid(uuid);
				if(null!=file)
					commonService.delete(file);
             commonService.saveOrUpdate(bimrInsideAuditProject);
             jsonObject.put("result", 1);
	         jsonObject.put("message", "删除成功!");
		} catch (Exception e) {
			// TODO: handle exception
			 jsonObject.put("result", 0);
	         jsonObject.put("message", "内部项目文件删除异常!");
		}
       
        return jsonObject;
    }
}