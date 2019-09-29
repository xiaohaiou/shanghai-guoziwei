package com.softline.controller.project;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.CompanyTree;
import com.softline.entity.HhUsers;
import com.softline.entity.project.PjAuthorityPerson;
import com.softline.entity.project.PjProject;
import com.softline.service.project.IPjAuthorityPersonService;
import com.softline.service.project.IProjectService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/project/authorityPerson")
public class AuthorityPersonController {
	
	@Resource(name = "pjAuthorityPersonService")
	private IPjAuthorityPersonService pjAuthorityPersonService;
	
	@Resource(name = "projectService")
	private IProjectService projectService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	/**
	 * 获取重点基建工程上报人审核人授权信息列表
	 * @param map
	 * @param condition
	 * @param request
	 * @return
	 */
	@RequestMapping("_list")
	public String getAuthorityPersonList(Map<String, Object> map,PjAuthorityPerson condition,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  pjAuthorityPersonService.getPjAuthorityPersons(condition, pageNum, Base.pagesize);
		PjProject projectCondition = new PjProject();
		List<PjProject> projects = projectService.getAllPjProjects(projectCondition);
		map.put("projects", projects);
		map.put("authorityPerson", condition);
		map.put("msgPage", msgPage);
		map.put("searchurl", request.getContextPath()+"/project/authorityPerson/_list");
		return "project/authorityPersonList";
	} 
	
	/**
	 * 进入新增页面
	 * @param condition
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("_add")
	public String _add(PjAuthorityPerson condition,Map<String ,Object> map, HttpServletRequest request){

		PjProject projectCondition = new PjProject();
		List<PjProject> projects = projectService.getAllPjProjects(projectCondition);
		map.put("projects", projects);
		map.put("op", "add");
		return "/project/authorityPersonAdd";
	}
	
	/**
	 * 进入编辑页面
	 * @param id
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("_edit")
	public String _edit(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjAuthorityPerson authorityPerson = (PjAuthorityPerson) commonService.findById(PjAuthorityPerson.class, id);
		PjProject projectCondition = new PjProject();
		List<PjProject> projects = projectService.getAllPjProjects(projectCondition);
		map.put("projects", projects);
		map.put("authorityPerson", authorityPerson);
		map.put("op", "edit");
		return "/project/authorityPersonAdd";
	}
	
	/**
	 * 保存
	 * @param authorityPerson
	 * @param map
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("_save")
	public String _save(PjAuthorityPerson authorityPerson,Map<String ,Object> map, HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		if(authorityPerson.getId() != null){ //编辑
			PjAuthorityPerson oldEntity = (PjAuthorityPerson) commonService.findById(PjAuthorityPerson.class, authorityPerson.getId());
			pjAuthorityPersonService.saveModifyAuthorityPerson(user, authorityPerson, oldEntity);
		}else{//新增
			pjAuthorityPersonService.saveAddAuthorityPerson(user, authorityPerson);
		}
		return "success";
	}
	
	/**
	 * 删除
	 * @param authorityPerson
	 * @param map
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("_del")
	public String _del(Integer id,Map<String ,Object> map, HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		pjAuthorityPersonService.deleteAuthorityPerson(user, id);
		return "success";
	}
	
	/**
	 * 验证
	 * @param id
	 * @param map
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/_validate" ,method=RequestMethod.POST,produces = "application/text; charset=utf-8"	)
	public String _validate(Integer id,Map<String ,Object> map, HttpServletRequest request){
		PjAuthorityPerson authorityPerson = (PjAuthorityPerson) commonService.findById(PjAuthorityPerson.class, id);
		String a = (String) JSON.toJSONString(authorityPerson);
		return a;
	}
	
	/**
	 * 进入选择人员页面
	 * @param map
	 * @param request
	 * @param vcEmployeeId
	 * @param type
	 * @param usersEmployeeId
	 * @return
	 */
	@RequestMapping("_select")
	public String _select(Map<String, Object> map, HttpServletRequest request,String vcEmployeeId, String type, String usersEmployeeId){
		CompanyTree leveltree = selectUserService.getHRTree();
		leveltree.setIcon("active");
		String shtml = getTreeHTML(leveltree);
		map.put("shtml", shtml);
		return "/project/usersSelectOne";
	}
	
	private String getTreeHTML(CompanyTree cTree){
		StringBuffer buffer = new StringBuffer();
		List<CompanyTree> sonList = cTree.getChildren();
		buffer.append("<li class=\"line-list\">");
			if(sonList.size()>0){
				if("active".equals(cTree.getIcon())){
					buffer.append("<span class=\"departmentLabel\" treeId="+cTree.getId()+"\"><i class=\"iconfont icon-tree-open-2\"></i><a href='#n' onclick = \"itemClick('"+cTree.getId()+"')\">"+cTree.getText()+"</a></span>");
				}else{
					buffer.append("<span class=\"departmentLabel\" treeId="+cTree.getId()+"\"><i class=\"iconfont icon-tree-close-2\"></i><a href='#n' onclick = \"itemClick('"+cTree.getId()+"')\">"+cTree.getText()+"</a></span>");
				}
				buffer.append("<ul class=\"level-2 "+cTree.getIcon()+"\">");
				for(int i=0;i<sonList.size();i++){
					CompanyTree son = sonList.get(i);
					buffer.append(getTreeHTML(son));
				}
				buffer.append("</ul>");
			}else{
				buffer.append("<label class=\"checkboxList\" treeId="+cTree.getId()+"><a href='#n' onclick = \"itemClick('"+cTree.getId()+"')\">"+cTree.getText()+"</a></label>");
			}
		buffer.append("</li>");
		return buffer.toString();
	}
}
