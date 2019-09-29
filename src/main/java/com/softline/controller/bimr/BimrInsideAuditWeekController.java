package com.softline.controller.bimr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.softline.common.Base;
import com.softline.common.CValue;
import com.softline.common.Common;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditWeekly;
import com.softline.service.bimr.IBimrInsideAuditDifficultService;
import com.softline.service.bimr.IBimrInsideAuditProjectService;
import com.softline.service.bimr.IBimrInsideAuditQuestionService;
import com.softline.service.bimr.IBimrInsideAuditWeeklyService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 项目周报管理
 * */
@Controller
@RequestMapping("/bimr/weekly")
public class BimrInsideAuditWeekController {

	private Logger logger = Logger.getLogger(BimrInsideAuditWeekController.class);
	
    @Resource(name = "iBimrInsideAuditProjectService")
    private IBimrInsideAuditProjectService iBimrInsideAuditProjectService;

    @Resource(name = "iBimrInsideAuditWeelyService")
    private IBimrInsideAuditWeeklyService iBimrInsideAuditWeeklyService;

    @Resource(name = "iBimrInsideAuditDifficultService")
	private IBimrInsideAuditDifficultService iBimrInsideAuditDifficultService;

    @Resource(name = "iBimrInsideAuditQuestionService")
    private IBimrInsideAuditQuestionService iBimrInsideAuditQuestionService;

    @Resource(name = "systemService")
    private ISystemService systemService;

    @Resource(name = "baseService")
    private IBaseService baseService;

    @Resource(name = "selectUserService")
    private ISelectUserService selectUserService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    DecimalFormat df = new DecimalFormat("###0.0000");

    /**
     * 内部审计项目列表
     * @param entity
     * @param request
     * @return
     */
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
        MsgPage msgPage = iBimrInsideAuditProjectService.getInsideAuditProjectWeeekReport(entity, pageNum, Base.pagesize, dataAuthority, "0", user.getVcEmployeeId());        
        mView.addObject("msgPage", msgPage);
        mView.addObject("entity", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("projectStatusProgress", list);
        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
        mView.setViewName("/bimr/bimrInsideAuditProject/insideAuditProjectWeekReportList");
        return mView;
    }
    
    
//    总监工作日报导出999
	 @RequestMapping(value="/WorkXMExport")
		public void WorkXMExport(BimrInsideAuditWeekly entity,BimrInsideAuditProject entity1,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String vcEmployeeId) throws IOException, ParsePropertyException, InvalidFormatException {
			
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

		    list1=iBimrInsideAuditWeeklyService.getInsideExportXM1(entity,entity1, dataAuthority,vcEmployeeId);
				
		        
		        XSSFWorkbook workBook1 = new XSSFWorkbook();
//				
		        workBook1=iBimrInsideAuditWeeklyService.getInsideExportWorkbook3(list1);
					     
					     // 清空response档案查询
					        response.reset();
					        String _name = "总监工作周报.xlsx";
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
     * 内部审计项目周报填报
     * @param entity
     * @param request
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("/weekList")
    public ModelAndView weekList(BimrInsideAuditWeekly entity, HttpServletRequest request) throws UnsupportedEncodingException{
       
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        String company = (String)session.getAttribute("gzwsession_company");
        String allCompanyTree = JSON.toJSONString(selectUserService.getHrOrganal(company));
        request.setCharacterEncoding("UTF-8");
        String projectId = request.getParameter("projectId") == null?"":request.getParameter("projectId");
        String projectPrincipal = request.getParameter("projectPrincipal") == null?"":request.getParameter("projectPrincipal").toString();
        //String projectPrincipalname = new String(projectPrincipal.getBytes("iso-8859-1"),"utf-8");
        
        mView.addObject("projectPrincipalname", projectPrincipal);
        entity.setProjectId(Integer.parseInt(projectId));
        mView.addObject("allCompanyTree", allCompanyTree);
        String dataAuthority = systemService.getDataauth(company);
        
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
            curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = iBimrInsideAuditWeeklyService.getBimrInsideAuditWeeklys(entity, pageNum, Base.pagesize, dataAuthority);
        mView.addObject("msgPage", msgPage);
        mView.addObject("entity", entity);
        mView.setViewName("/bimr/bimrInsideAuditProject/reportList");
        return mView;
    }

    /**
     * 内部审计项目添加或者修改.
     * @param entity
     * @param request
     * @return
     * @throws ParseException 
     */
    @RequestMapping("/newOrModify")
    public ModelAndView newOrModifyProjects(BimrInsideAuditWeekly entity, HttpServletRequest request) throws ParseException{
       
        
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        String op = request.getParameter("op") == null?"":request.getParameter("op");
        mView.addObject("op", op);
        String projectId = request.getParameter("projectId") == null?"":request.getParameter("projectId");
        String company = (String)session.getAttribute("gzwsession_company");
        BimrInsideAuditProject auditProject = new BimrInsideAuditProject();
        if(op.equals("add")){
            auditProject = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(projectId));
        }
        String dataAuthority = systemService.getDataauth(company);
        BimrInsideAuditWeekly weekly;
        
        List<BimrInsideAuditProject> main_projects = iBimrInsideAuditProjectService.getBimrInsideAuditProjectHasNoChild(Base.PROJECT_ENABLE);
        mView.addObject("mainProjects", main_projects);
        Calendar c = Calendar.getInstance();
        List<CValue> yearList = Common.getCircleYears(c.get(Calendar.YEAR),5);
        mView.addObject("year",yearList);
        List<CValue> weekList = Common.getEveryWeekFromCurrentYear(c.get(Calendar.YEAR));
        if (entity.getId() != null) {
            weekly = iBimrInsideAuditWeeklyService.getBimrInsideAuditWeekly(entity);
        }else{
            weekly = new BimrInsideAuditWeekly();
           
            List<BimrInsideAuditWeekly> slist=iBimrInsideAuditWeeklyService.getBimrInsideAuditWeeklyList(Integer.parseInt(projectId));
            
            if(slist.size()==0){
            	weekly.setWeekStartDate(auditProject.getAuditStartDate());
            }else{
            	BimrInsideAuditWeekly newweekly=slist.get(0);
            	Date date=null; 
            
            	date = new SimpleDateFormat("yy-MM-dd").parse(newweekly.getWeekEndDate()); 
            	
            	c.setTime(date); 
            	int day=c.get(Calendar.DATE); 
            	c.set(Calendar.DATE,day+1); 
            	String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
            	weekly.setWeekStartDate(dayBefore);
            }
            
            weekly.setProjectEndDate(auditProject.getAuditEndDate());
            weekly.setProjectId(auditProject.getId());
            weekly.setProjectName(auditProject.getAuditProjectName());
        }
        
        mView.addObject("weeks",weekList);
        mView.addObject("weekly", weekly);
        List<HhBase> list = baseService.getBases(Base.projectStatusProgress);
        mView.addObject("projectStatusProgress", list);
        mView.setViewName("/bimr/bimrInsideAuditProject/reportNewOrModify");
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
    public JSONObject saveOrUpdateProjects(@ModelAttribute("weekly") BimrInsideAuditWeekly entity, @RequestParam(value="file1",required=false) MultipartFile file1, HttpServletRequest request){
    	JSONObject jsonObject = new JSONObject();
    	HttpSession session = request.getSession();
    	HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
    	try {
    		if (entity.getId() == null) {
        		entity.setIsDel(0);
    			entity.setCreateDate(sdf.format(new Date()));
    			entity.setCreatePersonId(user.getVcEmployeeId());
    			entity.setCreatePersonName(user.getVcName());
    			//iBimrInsideAuditWeeklyService.saveBimrInsideAuditWeekly(entity,file1);
                iBimrInsideAuditWeeklyService.saveBimrInsideAuditWeekly(entity);
    			jsonObject.put("result", 1);
    			jsonObject.put("message", "内部审计项目周报保存成功!");
    		}else{
    			entity.setLastModifyDate(sdf.format(new Date()));
    			entity.setLastModifyPersonId(user.getVcEmployeeId());
    			entity.setLastModifyPersonName(user.getVcName());
    			iBimrInsideAuditWeeklyService.updateBimrInsideAuditWeekly(entity);
    			jsonObject.put("result", 1);
    			jsonObject.put("message", "内部审计项目周报更新成功!");
    		}
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonObject.put("result", 0);
			jsonObject.put("message", "内部审计项目周报保存更新失败!");
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
    			iBimrInsideAuditWeeklyService.deleteBimrInsideAuditWeekly(Integer.parseInt(ids));
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
     * 查看内部审计项目周报.
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/view")
    public ModelAndView _view(HttpServletRequest request){
    	ModelAndView mView = new ModelAndView();
    	String ids = request.getParameter("id");
        BimrInsideAuditWeekly entity = null;
    	if (Common.notEmpty(ids)){
            entity = iBimrInsideAuditWeeklyService.getBimrInsideAuditWeekly(Integer.parseInt(ids));
            HhBase hhBase = baseService.getBase(entity.getProjectStatusProgress());
            entity.setProjectStatusProgressName(hhBase.getDescription());
            String week = entity.getWeek();
            entity.setWeek("第"+week+"周");
        }
    	mView.addObject("entity", entity);
    	mView.setViewName("/bimr/bimrInsideAuditProject/reportDetailView");
    	return mView;
    }
    
//    周报导出
    
    
    
    
    
    
    
}