package com.softline.controller.bimr;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.softline.common.Base;
import com.softline.common.CValue;
import com.softline.common.Common;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportPersonlloaninfoNew;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrDuty;
import com.softline.entity.bimr.BimrInsideAuditFeedback;
import com.softline.entity.bimr.BimrInsideAuditMeasures;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditRectify;
import com.softline.entity.bimr.BimrInsideAuditWeekly;
import com.softline.service.bimr.IBimrInsideAuditProjectService;
import com.softline.service.bimr.IBimrInsideAuditQuestionService;
import com.softline.service.bimr.IBimrInsideAuditRectifyService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * 内部审计项目整改
 * */
@Controller
@RequestMapping("/bimr/rectify")
public class BimrInsideAuditRectifyController {

    private Logger logger = Logger.getLogger(BimrInsideAuditRectifyController.class);

    @Resource(name = "bimrInsideAuditRectifyService")
    private IBimrInsideAuditRectifyService bimrInsideAuditRectifyService;

    @Resource(name = "iBimrInsideAuditQuestionService")
    private IBimrInsideAuditQuestionService iBimrInsideAuditQuestionService;

    @Resource(name = "iBimrInsideAuditProjectService")
    private IBimrInsideAuditProjectService iBimrInsideAuditProjectService;

    @Resource(name = "systemService")
    private ISystemService systemService;
    
    @Resource(name = "commonService")
    private ICommonService commonService;

    @Resource(name = "baseService")
    private IBaseService baseService;

    @Resource(name = "selectUserService")
    private ISelectUserService selectUserService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    DecimalFormat df = new DecimalFormat("###0.0000");
    
    /**
     * wyh
     * 内部审计项目整改列表.
     * 
     */
    @RequestMapping("/list")
    public ModelAndView insideAuditProjectList(BimrInsideAuditProject entity, HttpServletRequest request){
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
        Integer pageNum = Integer.valueOf(curPageNum);
        entity.setStatus(Base.PROJECT_IS_RECTIFYING);
        MsgPage msgPage = iBimrInsideAuditProjectService.getBimrInsideAuditProjects(entity, pageNum, Base.pagesize, dataAuthority, "", user.getVcEmployeeId());
        mView.addObject("msgPage", msgPage);
        mView.addObject("entity", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("projectStatusProgress", list);
        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
        mView.setViewName("bimr/bimrInsideAuditProject/rectifyStartList");
        return mView;
    }
    
    /**
     * wyh
     * 发起整改.
     * 
     */
    @RequestMapping(value = "/newOrModify")
    public String newOrModifyProjects(BimrInsideAuditRectify entity,  Map<String, Object> map, HttpServletRequest request, String op, String projectId, String id){
    	HttpSession session = request.getSession();
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        map.put("Huser", user);
        String company = (String)session.getAttribute("gzwsession_company");
        BimrInsideAuditRectify ap = new BimrInsideAuditRectify();
        if(op.equals("add")){
	        if (entity.getId() != null) {
	            ap = bimrInsideAuditRectifyService.getBimrInsideRectifyQuestion(entity);
	        }
	        BimrInsideAuditProject project;
            if(projectId != null && !projectId.equals("")){
                project = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(projectId));
                ap.setProjectId(Integer.parseInt(projectId));
                ap.setProjectName(project.getAuditProjectName());
            }
        }
        if(op.equals("modify")){
        	if(id != null && !id.equals("")){
        		entity.setId(Integer.parseInt(id));
        		ap = bimrInsideAuditRectifyService.getBimrInsideRectifyQuestion(entity);
        	}
        	List measuresList = bimrInsideAuditRectifyService.getBimrInsideRectifyMeasures(entity.getId());
        	map.put("measuresList", measuresList);
        }
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        List<BimrInsideAuditQuestion> qList = new ArrayList();
        
        qList = iBimrInsideAuditQuestionService.getBimrInsideAuditQuestionForList(ap.getProjectId(),null);
        
        List<BimrInsideAuditRectify> plist=bimrInsideAuditRectifyService.getBimrInsideRectifyQuestionList(Integer.parseInt(projectId));
        for (int i = 0; i < qList.size(); i++) {
			for (int j = 0; j < plist.size(); j++) {
				if(qList.get(i).getId()==plist.get(j).getAnswerId()){
					qList.remove(i);
					i--;
					break;
				}
			}
		}
        
        map.put("problems",qList);
        map.put("auditProperty", list);
        //map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        map.put("op", op);
        map.put("rectify", ap);
        return "bimr/bimrInsideAuditProject/rectifyNewOrModify";
    }
    
    /**
     * 内部审计项目问题整改保存或者更新.
     * @param entity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public JSONObject saveOrUpdateRectify(@ModelAttribute("rectify") BimrInsideAuditRectify entity, String[] cs, String[] csTime, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
        try {
            if (entity.getId() == null) {
                entity.setIsDel("0");
                entity.setCreateDate(sdf.format(new Date()));
                entity.setCreatePersonId(user.getVcEmployeeId());
                entity.setCreatePersonName(user.getVcName());
                entity.setStatus("1");
                bimrInsideAuditRectifyService.saveBimrInsideRectify(entity,cs,csTime);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目整改发起保存成功!");
            }else{
                entity.setLastModifyDate(sdf.format(new Date()));
                entity.setLastModifyPersonId(user.getVcEmployeeId());
                entity.setLastModifyPersonName(user.getVcName());
                entity.setStatus("1");
                bimrInsideAuditRectifyService.updateBimrInsideRectify(entity,cs,csTime);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目整改发起更新成功!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部审计项目整改发起更新失败!");
        }
        return jsonObject;
    }
    
    /**
     * 内部审计项目问题整改发起.
     * @param entity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    public JSONObject commit(@ModelAttribute("rectify") BimrInsideAuditRectify entity, String[] cs, String[] csTime, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
        try {
            if (entity.getId() == null) {
                entity.setIsDel("0");
                entity.setCreateDate(sdf.format(new Date()));
                entity.setCreatePersonId(user.getVcEmployeeId());
                entity.setCreatePersonName(user.getVcName());
                entity.setStatus("2");
                bimrInsideAuditRectifyService.saveBimrInsideRectify(entity,cs,csTime);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目整改发起保存成功!");
            }else{
                entity.setLastModifyDate(sdf.format(new Date()));
                entity.setLastModifyPersonId(user.getVcEmployeeId());
                entity.setLastModifyPersonName(user.getVcName());
                entity.setStatus("2");
                bimrInsideAuditRectifyService.updateBimrInsideRectify(entity,cs,csTime);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目整改发起更新成功!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部审计项目整改发起更新失败!");
        }
        return jsonObject;
    }

    /**
     * 内部审计项目问题整改列表展示.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/fill")
    public ModelAndView insideAuditRectifyList(Integer projectId, HttpServletRequest request){
    	BimrInsideAuditRectify entity = new BimrInsideAuditRectify();
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        String str = (String)session.getAttribute("gzwsession_company");
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        entity.setRectifyResponseId(user.getVcEmployeeId());
        entity.setProjectId(projectId);
        String dataAuthority = systemService.getDataauth(str);
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
            curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = bimrInsideAuditRectifyService.getBimrInsideRectify(entity, pageNum, Base.pagesize, dataAuthority);
        mView.addObject("msgPage", msgPage);
        mView.addObject("auditQuestion", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("auditProperty", list);
        mView.addObject("projectId", projectId);
        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
        mView.setViewName("bimr/bimrInsideAuditProject/rectifyList");
        return mView;
    }

    /**
     * 删除内部审计项目问题整改.
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
            	bimrInsideAuditRectifyService.deleteBimrInsideRectifyQuestion(Integer.parseInt(ids));
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目问题整改删除成功!");
            }else{
                jsonObject.put("result", 0);
                jsonObject.put("message", "内部审计项目问题整改删除失败!");
            }
            jsonObject.put("result", 1);
            jsonObject.put("message", "内部审计项目问题整改删除成功!");
        } catch (Exception e) {
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部审计项目问题整改删除失败!");
        }
        return jsonObject;
    }
    
    /**
     * 查看内部审计项目.
     * @param request
     * @return
     */
    @RequestMapping(value="/view")
    public ModelAndView _view(HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String ids = request.getParameter("id");
        BimrInsideAuditRectify entity = bimrInsideAuditRectifyService.getBimrInsideRectify(Integer.parseInt(ids));
        List measuresList = bimrInsideAuditRectifyService.getBimrInsideRectifyMeasures(entity.getId());
        mView.addObject("entity", entity);
        mView.addObject("measuresList", measuresList);
        mView.setViewName("bimr/bimrInsideAuditProject/rectifyView");
        return mView;
    }
    
    /**
     * 审计发现问题是否整改完成
     * */
    @ResponseBody
    @RequestMapping(value = "/applyFinishedRectify", method = RequestMethod.POST)
    public JSONObject applyFinishedRectify(Integer rectifyId, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
        try {
        	List<BimrInsideAuditMeasures> measuresList = bimrInsideAuditRectifyService.getBimrInsideRectifyMeasures(rectifyId);
        	int measureCount = measuresList.size();
        	if(measureCount == 0){
        		jsonObject.put("result", 0);
                jsonObject.put("message", "内部审计项目发现问题整改失败!");
        	}
        	else{
        		int finishCount = 0;
            	for(BimrInsideAuditMeasures measure : measuresList){
            		if(measure.getStatus().equals("2")){
            			finishCount = finishCount + 1;
            		}
            	}
            	if(finishCount == measureCount){
            		BimrInsideAuditRectify rectify = bimrInsideAuditRectifyService.getBimrInsideRectify(rectifyId);
            		rectify.setStatus("3");
            		commonService.saveOrUpdate(rectify);
                    jsonObject.put("result", 1);
                    jsonObject.put("message", "内部审计项目发现问题整改完成提交审核成功!");
            	}
            	else{
            		jsonObject.put("result", 0);
                    jsonObject.put("message", "内部审计项目发现问题整未全部整改完成!");
            	}
        	}
        } catch (Exception e) {
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部审计项目发现问题未全部整改完成!");
        }
        return jsonObject;
    }
    
    /**
     * wyh
     * 内部审计项目整改列表.
     * 
     */
    @RequestMapping("/checkList")
    public ModelAndView insideAuditProjectCheckList(BimrInsideAuditProject entity, HttpServletRequest request){
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
        Integer pageNum = Integer.valueOf(curPageNum);
        entity.setStatus(Base.PROJECT_IS_RECTIFYING);
        MsgPage msgPage = iBimrInsideAuditProjectService.getBimrInsideAuditProjects(entity, pageNum, Base.pagesize, dataAuthority, "", user.getVcEmployeeId());
        mView.addObject("msgPage", msgPage);
        mView.addObject("entity", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("projectStatusProgress", list);
        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
        mView.setViewName("bimr/bimrInsideAuditProject/rectifyStartList");
        return mView;
    }

    /**
     * 审计跟踪
     * TODO
     * */
    /**
     * wyh
     * 内部审计项目跟踪列表.
     * 
     */
    @RequestMapping("/trackList")
    public ModelAndView insideAuditProjectTrackList(BimrInsideAuditProject entity, HttpServletRequest request){
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
        Integer pageNum = Integer.valueOf(curPageNum);
        entity.setStatus(Base.PROJECT_IS_RECTIFYING);
        MsgPage msgPage = iBimrInsideAuditProjectService.getBimrInsideAuditProjects(entity, pageNum, Base.pagesize, dataAuthority, "", user.getVcEmployeeId());
        mView.addObject("msgPage", msgPage);
        mView.addObject("entity", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("projectStatusProgress", list);
        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
        mView.setViewName("bimr/bimrInsideAuditProject/rectifyTrackList");
        return mView;
    }
//    审计整改验收单（时点维度）导出
	 @RequestMapping(value="/TimeDimension")
		public void TimeDimensionExport(BimrInsideAuditRectify entity,BimrInsideAuditMeasures entity1,BimrInsideAuditProject entity2,BimrInsideAuditQuestion entity4,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String vcEmployeeId) throws IOException, ParsePropertyException, InvalidFormatException {
			
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

		    list1=bimrInsideAuditRectifyService.getTimeDimension(entity,entity1,entity2,entity4,dataAuthority,vcEmployeeId);
				
//		        查询123
		        XSSFWorkbook workBook1 = new XSSFWorkbook();
//				
		        workBook1=bimrInsideAuditRectifyService.getTimeDimensionWorkbook1(list1);
					     
					     // 清空response档案查询
					        response.reset();
					        String _name = "审计整改验收单（单一时点维度）.xlsx";
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
	 
//   审计整改验收单（项目维度）导出
	 @RequestMapping(value="/ProjectDimension")
		public void ProjectDimensionExport(BimrInsideAuditRectify entity,BimrInsideAuditMeasures entity1,BimrInsideAuditProject entity2,BimrInsideAuditFeedback entity3,BimrInsideAuditQuestion entity4,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String vcEmployeeId) throws IOException, ParsePropertyException, InvalidFormatException {
			
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

		    list1=bimrInsideAuditRectifyService.getProjectDimension(entity,entity1,entity2,entity3,entity4,dataAuthority,vcEmployeeId);
				
//		        查询123
		        XSSFWorkbook workBook1 = new XSSFWorkbook();
//				
		        workBook1=bimrInsideAuditRectifyService.getProjectDimensionWorkbook1(list1);
					     
					     // 清空response档案查询
					        response.reset();
					        String _name = "审计整改验收单（单一项目维度）.xlsx";
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
     * 内部审计项目问题整改列表展示.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/feedBackList")
    public ModelAndView insideAuditRectifyFeedBackList(Integer projectId, HttpServletRequest request){
    	BimrInsideAuditRectify entity = new BimrInsideAuditRectify();
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        String str = (String)session.getAttribute("gzwsession_company");
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        mView.addObject("Huser",user);
        entity.setRectifyResponseId(user.getVcEmployeeId());
        entity.setProjectId(projectId);
        String dataAuthority = systemService.getDataauth(str);
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
            curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = bimrInsideAuditRectifyService.getBimrInsideRectify(entity, pageNum, Base.pagesize, dataAuthority);
        mView.addObject("msgPage", msgPage);
        mView.addObject("auditQuestion", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("auditProperty", list);
        mView.addObject("projectId", projectId);
        mView.setViewName("bimr/bimrInsideAuditProject/rectifyFeedbackList");
        return mView;
    }
    
    /**
     * 
     * 整改反馈
     * 
     * */
    @RequestMapping(value = "/feedBackModify")
    public String feedBackModify(Integer rectifyId,  Map<String, Object> map, HttpServletRequest request){
    	BimrInsideAuditRectify entity = bimrInsideAuditRectifyService.getBimrInsideRectify(rectifyId);
        List<BimrInsideAuditMeasures> measuresList = bimrInsideAuditRectifyService.getBimrInsideRectifyMeasures(entity.getId());
    	String curPageNum = request.getParameter("pageNums");
    	if (curPageNum == null) {
    	        curPageNum = "1";
    	    }
    	Integer pageNum = Integer.valueOf(curPageNum);
    	MsgPage msgPage = bimrInsideAuditRectifyService.getFeedbackMsg(rectifyId+"", pageNum, Base.pagesize);
    	List<BimrInsideAuditFeedback> list= msgPage.getList();
    
    
    	if(list.size()>0){
    	   	for (int i = 0; i < measuresList.size(); i++) {
        		
    			if(list.get(i).getMeasuresId() ==  measuresList.get(i).getId()){			
//    				measuresList.add(list.get(i).getFeedbackDesc());
    				measuresList.get(i).setFeedbackDesc(list.get(i).getFeedbackDesc());
    			}
    		}
    	}
        map.put("entity", entity);
        map.put("measuresList", measuresList);
    	return "bimr/bimrInsideAuditProject/rectifyFeedbackModify";
    }
    

    
    /**
     * 
     * 整改反馈
     * 
     * */
    @ResponseBody
    @RequestMapping(value = "/feedBackSaveOrUpdate")
    public JSONObject feedBackSaveOrUpdate(String tableVal, HttpServletRequest request){
    	JSONObject jsonObject = new JSONObject();
    	String str = request.getParameter("tableVal");
    	bimrInsideAuditRectifyService.saveFeedBack(str);
    	jsonObject.put("result", 1);
        jsonObject.put("message", "整改反馈填写成功!");
    	return jsonObject;
    }
    
    /**
     * 内部审计项目问题整改列表展示.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/feedBackMsgList")
    public ModelAndView insideAuditRectifyFeedBackMsgList(String rectifyId, HttpServletRequest request){
    	BimrInsideAuditRectify entity = new BimrInsideAuditRectify();
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        String str = (String)session.getAttribute("gzwsession_company");
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
            curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = bimrInsideAuditRectifyService.getFeedbackMsg(rectifyId, pageNum, Base.pagesize);
        mView.addObject("msgPage", msgPage);
        mView.setViewName("bimr/bimrInsideAuditProject/rectifyFeedbackMsgList");
        return mView;
    }
    
    /**
     * wyh
     * 内部审计项目反馈审核.
     * 
     * 审计整改反馈审核
     * 	index 
		auditProjectCode
		auditDeptedName     audit_depted
		auditProjectName																								
     */
    @RequestMapping("/trackCheckList")
    public ModelAndView insideAuditProjectTrackCheckList(BimrInsideAuditProject entity, HttpServletRequest request){
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
        Integer pageNum = Integer.valueOf(curPageNum);
        entity.setStatus(Base.PROJECT_IS_RECTIFYING);
        MsgPage msgPage = iBimrInsideAuditProjectService.getBimrInsideAuditProjects(entity, pageNum, Base.pagesize, dataAuthority, "", user.getVcEmployeeId());
        mView.addObject("msgPage", msgPage);
        mView.addObject("entity", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("projectStatusProgress", list);
        mView.addObject("vcEmployeeId", user.getVcEmployeeId());
        mView.setViewName("bimr/bimrInsideAuditProject/rectifyTrackCheckList");
        return mView;
    }
    
    @RequestMapping(value="/InsideExport")
	public void InsideExport1(BimrInsideAuditProject entity,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String vcEmployeeId) throws IOException, ParsePropertyException, InvalidFormatException {
		
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
//        赋值
		
		List<BimrInsideAuditProject> list1=new ArrayList<BimrInsideAuditProject>();

	    list1=iBimrInsideAuditProjectService.getInsideExport(entity, dataAuthority,vcEmployeeId);
			
	        
	        XSSFWorkbook workBook = new XSSFWorkbook();
//			
	        workBook=iBimrInsideAuditProjectService.getInsideExportWorkbook(list1);
				     
				     // 清空response档案查询
				        response.reset();
				        String _name = "海航物流审计整改未完成项目统计.xlsx";
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
    
    
    

    
    /**
     * 内部审计项目问题整改列表展示.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/feedBackCheckList")
    public ModelAndView insideAuditRectifyFeedBackCheckList(Integer projectId, HttpServletRequest request){
    	BimrInsideAuditRectify entity = new BimrInsideAuditRectify();
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        String str = (String)session.getAttribute("gzwsession_company");
        HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
        entity.setRectifyResponseId(user.getVcEmployeeId());
        entity.setProjectId(projectId);
        String dataAuthority = systemService.getDataauth(str);
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
            curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = bimrInsideAuditRectifyService.getBimrInsideRectify(entity, pageNum, Base.pagesize, dataAuthority);
        mView.addObject("msgPage", msgPage);
        mView.addObject("auditQuestion", entity);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("auditProperty", list);
        mView.addObject("projectId", projectId);
        mView.setViewName("bimr/bimrInsideAuditProject/rectifyFeedbackCheckList");
        return mView;
    }
    
    /**
     * 
     * 整改审核
     * 
     * */
    @RequestMapping(value = "/feedBackCheckView")
    public String feedBackCheckView(Integer rectifyId,  Map<String, Object> map, HttpServletRequest request){
    	BimrInsideAuditRectify entity = bimrInsideAuditRectifyService.getBimrInsideRectify(rectifyId);
        List feedList = bimrInsideAuditRectifyService.getBimrInsideAuditFeedbackByRectifyId(rectifyId);
        map.put("entity", entity);
        map.put("feedList", feedList);
    	return "bimr/bimrInsideAuditProject/rectifyFeedbackCheckView";
    }
    
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		BimrInsideAuditRectify entity = bimrInsideAuditRectifyService.examineInsideProjectRectifyFeedBack(entityid, examStr, examResult, user);
		if(entity != null){
			return JSON.toJSONString("success");
		}else{
			return JSON.toJSONString("failed");
		}
	}
}
