package com.softline.controller.queryPage.project;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.HhFile;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjProjectHistory;
import com.softline.service.project.IProjectService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/project")
public class QueryPageProjectController {
	
	@Resource(name = "projectService")
	private IProjectService projectService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@RequestMapping("_list")
	public String _list(PjProject condition,String pjDeptId,Map<String ,Object> map, HttpServletRequest request){
		HttpSession session=request.getSession();
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        //数据权限
        String str=(String) session.getAttribute("gzwsession_company");
        String dataauth=systemService.getDataauth(str);
//        if(!Common.notEmpty(pjDeptId))
//        	pjDeptId = dataauth;
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  projectService.getPjProjects("view",pjDeptId,condition, pageNum, Base.pagesize);
		map.put("project", condition);
		map.put("msgPage", msgPage);
		map.put("searchurl", request.getContextPath()+"/queryPage/project/_list");
		
		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/queryPage/project/projectList";
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
		map.put("project", project);
		//上报审核历史
		List<PjProjectHistory> histories = projectService.getPjProjectHistories(project.getId());
		map.put("histories",histories);
		
		return "/queryPage/project/projectDetail";
	}
	
}
