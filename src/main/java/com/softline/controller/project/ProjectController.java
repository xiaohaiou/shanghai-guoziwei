package com.softline.controller.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.HhFile;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.project.PjCode;
import com.softline.entity.project.PjContractTemp;
import com.softline.entity.project.PjNodeTemp;
import com.softline.entity.project.PjPcPayrecordTemp;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjProjectHistory;
import com.softline.entity.project.PjProjectvideo;
import com.softline.entity.project.PjWeekreportTemp;
import com.softline.service.project.IPjCodeService;
import com.softline.service.project.IPjPcPayrecordService;
import com.softline.service.project.IProjectService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Resource(name = "projectService")
	private IProjectService projectService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("_list")
	public String _list(PjProject condition,String pjDeptId,Map<String ,Object> map, HttpServletRequest request){
		HttpSession session=request.getSession();
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        //数据权限
        String str=(String) session.getAttribute("gzwsession_company");
//        String dataauth=systemService.getDataauth(str);
//        if(!Common.notEmpty(pjDeptId))
//        	pjDeptId = dataauth;
        Integer pageNum = Integer.valueOf(curPageNum);
        HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		MsgPage msgPage =  projectService.getPjProjects(user.getVcEmployeeId(),pjDeptId,condition, pageNum, Base.pagesize);
		map.put("project", condition);
		map.put("msgPage", msgPage);
		map.put("searchurl", request.getContextPath()+"/project/_list");
		
		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/project/projectList";
	}
	
	@RequestMapping("_add")
	public String _add(PjProject condition,Map<String ,Object> map, HttpServletRequest request){
		map.put("entityview", condition);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/project/projectAdd";
	}
	
	@RequestMapping("_edit")
	public String _edit(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjProject project = (PjProject) commonService.findById(PjProject.class, id);
		HhFile file = commonService.getFileByEnIdAndType(project.getId(), Base.PROJECT_FILE_TYPE);
		project.setProjectImgFile(file);
		PjProjectvideo video = projectService.getProjectVideoByPjId(id);
		project.setVideo(video);
		map.put("project", project);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/project/projectModify";
	}
	
	/**
	 * 重点项目 新增页面的保存操作
	 * @param project 重点项目
	 * @param nodeIds 重点项目中的节点IDs
	 * @param wkReportIds 重点项目中的周报IDs
	 * @param contractIds  重点项目中的合同IDs
	 * @param map
	 * @param request
	 * @param file 重点项目的图片，用于展示。
	 * @return
	 */
	@ResponseBody
	@RequestMapping("_saveAdd")
	public String _saveAdd(PjProject project,String nodeIds,String wkReportIds,String contractIds,Map<String ,Object> map, HttpServletRequest request,@RequestParam(value="pjFile",required=false) MultipartFile file,@RequestParam(value="pjVideo",required=false) MultipartFile videoFile){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		projectService.saveAddProject(user, project,nodeIds,wkReportIds,contractIds, file,videoFile,0);
		return "success";
	}
	
	/**
	 * 项目编辑页面的保存操作
	 * @param project
	 * @param map
	 * @param request
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("_saveModify")
	public String _saveModify(PjProject project,Map<String ,Object> map, HttpServletRequest request,@RequestParam(value="pjFile",required=false) MultipartFile file,@RequestParam(value="pjVideo",required=false) MultipartFile videoFile){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		
		PjProject oldEntity  = (PjProject) commonService.findById(PjProject.class,project.getId() );
		HhFile file2 = commonService.getFileByEnIdAndType(oldEntity.getId(), Base.PROJECT_FILE_TYPE);
		oldEntity.setProjectImgFile(file2);
		
		projectService.saveModifyProject(user, project,oldEntity, file,videoFile);
		return "success";
	}
	
	/**
	 * 新增页面的操作，保存和上报重大工程
	 * @param project
	 * @param nodeIds 重点项目中的节点IDs
	 * @param wkReportIds 重点项目中的周报IDs
	 * @param contractIds 重点项目中的合同IDs
	 * @param map
	 * @param request
	 * @param file 重点项目的图片，用于展示。
	 * @return
	 */
	@ResponseBody
	@RequestMapping("_saveandreport1")
	public String _saveandreport1(PjProject project,String nodeIds,String wkReportIds,String contractIds,Map<String ,Object> map, HttpServletRequest request,@RequestParam(value="pjFile",required=false) MultipartFile file,@RequestParam(value="pjVideo",required=false) MultipartFile videoFile){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		projectService.saveandreport1(user, project, nodeIds, wkReportIds, contractIds, file,videoFile, 0);
		return "success";
	}
	
	
	/**
	 * 编辑页面的操作，保存和上报重大工程
	 * @param project
	 * @param map
	 * @param request
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("_saveandreport2")
	public String _saveandreport2(PjProject project,Map<String ,Object> map, HttpServletRequest request,@RequestParam(value="pjFile",required=false) MultipartFile file,@RequestParam(value="pjVideo",required=false) MultipartFile videoFile){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		PjProject oldEntity  = (PjProject) commonService.findById(PjProject.class,project.getId() );
		HhFile file2 = commonService.getFileByEnIdAndType(oldEntity.getId(), Base.PROJECT_FILE_TYPE);
		oldEntity.setProjectImgFile(file2);
		projectService.saveandreport2(user, project, oldEntity, file,videoFile);
		return "success";
		
	}

	/**
	 * 进入重点项目查看页面
	 * @param id
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("_detail")
	public String _detail(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjProject project = (PjProject) commonService.findById(PjProject.class, id);
		//将附件信息放入
		HhFile file = commonService.getFileByEnIdAndType(project.getId(), Base.PROJECT_FILE_TYPE);
		project.setProjectImgFile(file);
		
		PjProjectvideo video = projectService.getProjectVideoByPjId(id);
		project.setVideo(video);
		map.put("project", project);
		//上报审核历史
		List<PjProjectHistory> histories = projectService.getPjProjectHistories(project.getId());
		map.put("histories",histories);
		
		return "project/projectDetail";
	}
	
	@ResponseBody
	@RequestMapping("_del")
	public String _del(Integer id,Map<String ,Object> map, HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
//		PjProject project = (PjProject) commonService.findById(PjProject.class, id);
//		project.setIsdel(1);
//		project.setLastModifyDate(df.format(new Date()));
//		project.setLastModifyPersonId(user.getVcEmployeeId());
//		project.setLastModifyPersonName(user.getVcName());
//		commonService.saveOrUpdate(project);
		projectService.deleteProject(id, user);
		return "success";
		
	}
	
	@RequestMapping("_reportDetail")
	public String _reportDetail(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjProject project = (PjProject) commonService.findById(PjProject.class, id);
		//将附件信息放入
		HhFile file = commonService.getFileByEnIdAndType(project.getId(), Base.PROJECT_FILE_TYPE);
		project.setProjectImgFile(file);
		PjProjectvideo video = projectService.getProjectVideoByPjId(id);
		project.setVideo(video);
		
		map.put("project", project);
		return "project/projectReport";
	}
	
	@ResponseBody
	@RequestMapping("_report")
	public String _report(Integer id,Map<String ,Object> map, HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		String result = projectService.saveReport(user, id);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/_validate" ,method=RequestMethod.POST,produces = "application/text; charset=utf-8"	)
	public String _validate(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjProject project = (PjProject) commonService.findById(PjProject.class, id);
		String a = (String) JSON.toJSONString(project);
		return a;
	}
	
	/**
	 * 管理员操作，用于操作排序和离线视频的操作
	 * @param condition
	 * @param pjDeptId
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("adminList")
	public String administartorList(PjProject condition,String pjDeptId,Map<String ,Object> map, HttpServletRequest request){
		HttpSession session=request.getSession();
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        //数据权限
        String str=(String) session.getAttribute("gzwsession_company");
        Integer pageNum = Integer.valueOf(curPageNum);
        HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		MsgPage msgPage =  projectService.getPjProjects("view",pjDeptId,condition, pageNum, Base.pagesize);
		for(PjProject pa : (List<PjProject>)msgPage.getList()){
			PjProjectvideo video = projectService.getProjectVideoByPjId(pa.getId());
			pa.setVideo(video);
		}
		map.put("project", condition);
		map.put("msgPage", msgPage);
		map.put("searchurl", request.getContextPath()+"/project/adminList");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/project/adminProjectList";
	}
	
	@RequestMapping("_adminEdit")
	public String _adminEdit(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjProject project = (PjProject) commonService.findById(PjProject.class, id);
		HhFile file = commonService.getFileByEnIdAndType(project.getId(), Base.PROJECT_FILE_TYPE);
		project.setProjectImgFile(file);
		PjProjectvideo video = projectService.getProjectVideoByPjId(id);
		project.setVideo(video);
		map.put("project", project);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/project/adminEdit";
	}
	
	/**
	 * 项目编辑页面的保存操作
	 * @param project
	 * @param map
	 * @param request
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("_saveAdminEdit")
	public String _saveAdminEdit(PjProject project,Map<String ,Object> map, HttpServletRequest request,@RequestParam(value="pjVideo",required=false) MultipartFile videoFile){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		projectService.saveAdminOPProject(user, project.getId(), project.getPjSort(),project.getOuterPjId(), videoFile);
		return "success";
	}
	
	/**
	 * 管理员操作页面没同步按钮
	 * 同步重点基建工程基本信息以及重点基建工程的视频信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="synInAdmin", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String synInAdmin(HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		return projectService.saveProjectAdminSyn(user);
	}
	
}
