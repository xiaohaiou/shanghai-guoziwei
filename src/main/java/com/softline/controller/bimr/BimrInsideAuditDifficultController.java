package com.softline.controller.bimr;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrInsideAuditDifficult;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.service.bimr.IBimrInsideAuditDifficultService;
import com.softline.service.bimr.IBimrInsideAuditProjectService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * 内部审计项目困难
 * */
@Controller
@RequestMapping("/bimr/difficult")
public class BimrInsideAuditDifficultController {

    private Logger logger = Logger.getLogger(BimrInsideAuditDifficultController.class);

    @Resource(name = "iBimrInsideAuditDifficultService")
    private IBimrInsideAuditDifficultService iBimrInsideAuditDifficultService;

    @Resource(name = "iBimrInsideAuditProjectService")
    private IBimrInsideAuditProjectService iBimrInsideAuditProjectService;

    @Resource(name = "systemService")
    private ISystemService systemService;

    @Resource(name = "baseService")
    private IBaseService baseService;

    @Resource(name = "selectUserService")
    private ISelectUserService selectUserService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    DecimalFormat df = new DecimalFormat("###0.0000");

    /**
     * 内部审计项目问题列表展示.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView fillInsideAuditProject(BimrInsideAuditDifficult entity, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        HttpSession session = request.getSession();
        String str = (String)session.getAttribute("gzwsession_company");
        String projectId = request.getParameter("projectId") == null?"":request.getParameter("projectId");
        entity.setProjectId(Integer.parseInt(projectId));
        mView.addObject("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
        String dataAuthority = systemService.getDataauth(str);
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
            curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = iBimrInsideAuditDifficultService.getBimrInsideAuditDifficults(entity, pageNum, Base.pagesize, dataAuthority);
        mView.addObject("msgPage", msgPage);
        List<HhBase> list = baseService.getBases(Base.auditProjectProperty);
        mView.addObject("auditProperty", list);
        //mView.addObject("searchurl", "/shanghai-gzw/bimr/difficult/list");
        mView.setViewName("bimr/bimrInsideAuditProject/difficultList");
        return mView;
    }

    /**
     * 内部审计项目问题添加或者修改.
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("/newOrModify")
    public ModelAndView newOrModifyProjects(BimrInsideAuditDifficult entity, HttpServletRequest request){
        ModelAndView mView = new ModelAndView();
        String op = request.getParameter("op") == null?"":request.getParameter("op");
        mView.addObject("op", op);
        String projectIds = request.getParameter("projectId") == null?"":request.getParameter("projectId");
        BimrInsideAuditProject project = null;
        if (Common.notEmpty(projectIds)){
            project = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(projectIds));
        }
        mView.addObject("project", project);
        BimrInsideAuditDifficult difficult;
        if (entity.getId() != null) {
            difficult = iBimrInsideAuditDifficultService.getBimrInsideAuditDifficult(entity);
        }else{
            difficult = new BimrInsideAuditDifficult();
        }
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");    
        String datetime = tempDate.format(new java.util.Date());    
        difficult.setCreateDate(datetime);
        mView.addObject("difficult", difficult);
        //List<BimrInsideAuditProject> projects = iBimrInsideAuditProjectService.getBimrInsideAuditProjectsForList(Base.PROJECT_ENABLE);
        List<BimrInsideAuditProject> projects = iBimrInsideAuditProjectService.getBimrInsideAuditProjectsIsOrNotChild(Base.IS_CHILD_PROJECT);
        mView.addObject("projects",projects);
        mView.setViewName("/bimr/bimrInsideAuditProject/diffNewOrModify");
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
    public JSONObject saveOrUpdateQuestions(@ModelAttribute("difficult") BimrInsideAuditDifficult entity, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
        try {
            if (entity.getId() == null) {
                entity.setIsDel(0);
                entity.setCreateDate(sdf.format(new Date()));
                entity.setCreatePersonId(user.getVcEmployeeId());
                entity.setCreatePersonName(user.getVcName());
                iBimrInsideAuditDifficultService.saveBimrInsideAuditDifficult(entity);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目困难和措施保存成功!");
            }else{
                entity.setLastModifyDate(sdf.format(new Date()));
                entity.setLastModifyPersonId(user.getVcEmployeeId());
                entity.setLastModifyPersonName(user.getVcName());
                iBimrInsideAuditDifficultService.updateBimrInsideAuditDifficult(entity);
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目困难和措施更新成功!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部审计项目困难和措施保存更新失败!");
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
                 iBimrInsideAuditDifficultService.deleteBimrInsideAuditDifficult(Integer.parseInt(ids));
                jsonObject.put("result", 1);
                jsonObject.put("message", "内部审计项目困难与措施删除成功!");
            }else{
                jsonObject.put("result", 0);
                jsonObject.put("message", "内部审计项目困难与措施删除失败!");
            }
            jsonObject.put("result", 1);
            jsonObject.put("message", "内部审计项目困难与措施删除成功!");
        } catch (Exception e) {
            logger.error(e.getMessage());
            jsonObject.put("result", 0);
            jsonObject.put("message", "内部审计项目困难与措施删除失败!");
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
        BimrInsideAuditDifficult entity = iBimrInsideAuditDifficultService.getBimrInsideAuditDifficult(Integer.parseInt(ids));
        mView.addObject("entity", entity);
        mView.setViewName("bimr/bimrInsideAuditProject/difficultView");
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
        List<BimrInsideAuditDifficult> problem_detail = null;
        BimrInsideAuditProject entity = null;
        if (Common.notEmpty(ids)){
            problem_detail = iBimrInsideAuditDifficultService.getBimrInsideAuditDifficultForList(Integer.parseInt(ids));
            entity = iBimrInsideAuditProjectService.getBimrInsideAuditProject(Integer.parseInt(ids));
        }
        mView.addObject("entity", entity);
        mView.addObject("problem_detail", problem_detail);
        mView.addObject("difficult_detail", null);
        mView.setViewName("/bimr/maintent/questionMaintentDetailView");
        return mView;
    }
}
