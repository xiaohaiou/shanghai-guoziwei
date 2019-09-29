package com.softline.controller.settingCenter.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.softline.common.CompanyTree;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.entity.settingCenter.HhModelUser;
import com.softline.entity.settingCenter.HhSysRegister;
import com.softline.service.select.ISelectUserService;
import com.softline.service.settingCenter.user.IModelRegisterService;
import com.softline.service.settingCenter.user.IModelUserService;
import com.softline.service.settingCenter.user.ISysRegisterService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/model/user")
public class MdelUserController {
	
	@Resource(name = "modelUserService")
	private IModelUserService modelUserService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "sysRegisterService")
	private ISysRegisterService sysRegisterService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "modelRegisterService")
	private IModelRegisterService modelRegisterService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("_query")
	public String _query(HhModelUser modelUser, String qModelNum, String qModelName, String qSysId, Map<String, Object> map, HttpServletRequest request) {
		if (modelUser.getModelId() != null) {
			map.put("save", "success");
		}
		map.put("qModelNum", qModelNum);
		map.put("qModelName", qModelName);
		map.put("qSysId", qSysId);
		//获取已注册系统下拉列表
		List<HhSysRegister> sysRegistedList = sysRegisterService.getRegistedList();
		map.put("sysRegistedList", sysRegistedList);
		
		String curPageNum = request.getParameter("pageNum");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = modelRegisterService.getModelMsgRegisterList(qModelNum, qModelName, qSysId, pageNum, 10);
		List<HhModelRegister> modelRegisterList = msgPage.getList();
		map.put("modelRegisterList", modelRegisterList);
		map.put("msgPage", msgPage);
		return "/settingCenter/user/modelUserList";
	}
	
	@RequestMapping("_modelUserCheck")
	public String _modelUserCheck(Integer id, Map<String, Object> map, HttpServletRequest request) {
		HhModelRegister register = (HhModelRegister)commonService.findById(HhModelRegister.class, id);
		map.put("register", register);
		//获取已选择的人员List
		List<HhUsers> selectedHhUsersList = modelUserService.getSelectHhUsersList(id);
		map.put("selectedHhUsersList", selectedHhUsersList);
		return "/settingCenter/user/checkModelUsers";
	}
	
	@RequestMapping("_modelUserAdd")
	public String roleUserAdd(String pageNum, String qModelNum, String qModelName, String qSysId, Integer id, Map<String, Object> map, HttpServletRequest request) {
		map.put("pageNum", pageNum);
		map.put("qModelNum", qModelNum);
		map.put("qModelName", qModelName);
		map.put("qSysId", qSysId);
		HhModelRegister register = (HhModelRegister)commonService.findById(HhModelRegister.class, id);
		map.put("register", register);
		//获取已选择的人员List
		List<HhUsers> selectedHhUsersList = modelUserService.getSelectHhUsersList(id);
		map.put("selectedHhUsersList", selectedHhUsersList);
		//构造部门人员树
		CompanyTree userLeveltree = selectUserService.getDepTree();
		userLeveltree.setIcon("active");
		String userShtml = getUserDepTreeHTML(userLeveltree);
		map.put("userShtml", userShtml);
		return "/settingCenter/user/modelUserAddByUser";
	}
	
	@RequestMapping("_modelUserAddBySearch")
	public String roleUserAddBySearch(String pageNum, String qModelNum, String qModelName, String qSysId, Integer id, Map<String, Object> map, HttpServletRequest request) {
		map.put("pageNum", pageNum);
		map.put("qModelNum", qModelNum);
		map.put("qModelName", qModelName);
		map.put("qSysId", qSysId);
		HhModelRegister register = (HhModelRegister)commonService.findById(HhModelRegister.class, id);
		map.put("register", register);
		//获取已选择的人员List
		List<HhUsers> selectedHhUsersList = modelUserService.getSelectHhUsersList(id);
		map.put("selectedHhUsersList", selectedHhUsersList);
		return "settingCenter/user/modelUserAddBySearch";
	}
	
	private String getUserDepTreeHTML(CompanyTree cTree){
		StringBuffer buffer = new StringBuffer();
		List<CompanyTree> sonList = cTree.getChildren();
		buffer.append("<li class=\"line-list\">");
			if(sonList.size()>0){
				if("active".equals(cTree.getIcon())){
					buffer.append("<span class=\"departmentLabel\"><i class=\"iconfont icon-tree-open-2\"></i>" +
							      "<a id='a" + cTree.getId() + "' onclick=\"searchdata('" + cTree.getId() + "')\" >" + cTree.getText() + "</a></span>");
				}else{
					buffer.append("<span class=\"departmentLabel\"><i class=\"iconfont icon-tree-close-2\"></i>" +
								  "<a id='a" + cTree.getId() + "' onclick=\"searchdata('" + cTree.getId() + "')\" >" + cTree.getText() + "</a></span>");
				}
				if ("active".equals(cTree.getIcon())) {
					buffer.append("<ul class=\"level-2 "+cTree.getIcon()+"\">");
				}else {
					buffer.append("<ul class=\"level-2\">");
				}
				for(int i=0;i<sonList.size();i++){
					CompanyTree son = sonList.get(i);
					buffer.append(getUserDepTreeHTML(son));
				}
				buffer.append("</ul>");
			}else{
				buffer.append("<span class=\"departmentLabel\"><a id='a" + cTree.getId() + "' onclick=\"searchdata('" + cTree.getId() + "')\" >"+cTree.getText()+"</a></span>");
			}
		buffer.append("</li>");
		return buffer.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/_searchPerson", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void _searchPerson(String id, String vcEmployeeIds, String name, HttpServletResponse response, HttpServletRequest request) throws IOException { 
		List<HhUsers> selectPersonList = selectUserService.getUsersByDepId(id);
		//获取已选择的人员List
		String[] selectedHhUsersList = !"".equals(vcEmployeeIds)?vcEmployeeIds.substring(0, vcEmployeeIds.length()-1).split(","):new String[0];
		if ((!selectPersonList.isEmpty())) {
			StringBuffer hql = new StringBuffer();
			hql.append(" <ul class=\"level-2 active\">");
			for (HhUsers item : selectPersonList) {
				//flag用于判断当前公司下的人员是否已被选中
				boolean flag = false;
				if (selectedHhUsersList.length > 0) {
					for (int i = 0; i < selectedHhUsersList.length; i++) {
						if (selectedHhUsersList[i].equals(item.getVcEmployeeId())) {
							flag = true;
							break;
						}
					}
				}
				if (flag) {
					hql.append("<li class=\"line-list\"><label id='label_" + item.getVcEmployeeId() + "' class=\"checkboxList checkbox_checked\" for='" + item.getVcEmployeeId() + "'>" +
						      "<span class=\"icon\"></span><input type=\"checkbox\" id='" + item.getVcEmployeeId() + "' value='" + item.getVcEmployeeId() + "'>" + item.getVcName() + "</label></li>");
				}else {
					hql.append("<li class=\"line-list\"><label id='label_" + item.getVcEmployeeId() + "' class=\"checkboxList\" for='" + item.getVcEmployeeId() + "'>" +
						      "<span class=\"icon\"></span><input type=\"checkbox\" id='" + item.getVcEmployeeId() + "' value='" + item.getVcEmployeeId() + "'>" + item.getVcName() + "</label></li>");
				}
			}
			hql.append(" </ul>");
			String jsonString = JSON.toJSONString(hql.toString());
			response.getWriter().write(jsonString);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/_searchPersonByName", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void _searchPersonByName(String vcEmployeeIds, String userName, HttpServletResponse response, HttpServletRequest request) throws IOException { 
		String jsonString = "";
		List<HhUsers> searchPersonList = new ArrayList<HhUsers>();
		if(userName != null && !"".equals(userName)) {
			searchPersonList = selectUserService.getUsersByName(userName);
		}
		//获取已选择的人员List
		String[] selectedHhUsersList = !"".equals(vcEmployeeIds)?vcEmployeeIds.substring(0, vcEmployeeIds.length()-1).split(","):new String[0];
		if (!searchPersonList.isEmpty()) {
			StringBuffer hql = new StringBuffer();
			hql.append(" <ul class=\"level-2 active\">");
			for (HhUsers item : searchPersonList) {
				//flag用于判断当前公司下的人员是否已被选中
				boolean flag = false;
				if (selectedHhUsersList.length > 0) {
					for (int i = 0; i < selectedHhUsersList.length; i++) {
						if (selectedHhUsersList[i].equals(item.getVcEmployeeId())) {
							flag = true;
							break;
						}
					}
				}
				if (flag) {
					hql.append("<li class=\"line-list\"><label id='label_" + item.getVcEmployeeId() + "' class=\"checkboxList checkbox_checked\" for='" + item.getVcEmployeeId() + "'>" +
						      "<span class=\"icon\"></span><input type=\"checkbox\" id='" + item.getVcEmployeeId() + "' checked>" + item.getVcName() + "-----" + item.getVcAccount() + "-----" + item.getVcFullName() + "</label></li>");
				}else {
					hql.append("<li class=\"line-list\"><label id='label_" + item.getVcEmployeeId() + "' class=\"checkboxList\" for='" + item.getVcEmployeeId() + "'>" +
						      "<span class=\"icon\"></span><input type=\"checkbox\" id='" + item.getVcEmployeeId() + "'>" + item.getVcName() + "-----" + item.getVcAccount() + "-----" + item.getVcFullName() + "</label></li>");
				}
			}
			hql.append(" </ul>");
			jsonString = JSON.toJSONString(hql.toString());
		}
		response.getWriter().write(jsonString);
	}
	
	@RequestMapping("_modelUserSaveOrUpdate")
	public String _modelUserSaveOrUpdate(HhModelUser modelUser, HttpServletRequest request, String vcEmployeeIds) {
		modelUserService.saveOrUpdateModelUser(modelUser, vcEmployeeIds);
		return "forward:/model/user/_query";
	}
	
	@ResponseBody
	@RequestMapping("_checkPerson")
	public String checkTheId(String vcEmployeeId, Integer modelId) {
		Integer num = modelUserService.checkTheId(vcEmployeeId,modelId);
		if (num >= 1) {
			return null;
		}else {
			return "success";
		}
	}
	
}
