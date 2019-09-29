package com.softline.controller.settingCenter.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.entity.HhOperationLog;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhGroup;
import com.softline.entity.settingCenter.HhGroupUserRole;
import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.entity.settingCenter.HhPageregister;
import com.softline.entity.settingCenter.HhRole;
import com.softline.entity.settingCenter.HhRoleModel;
import com.softline.entity.settingCenter.HhRoleSys;
import com.softline.entity.settingCenter.PortalHhRolecompany;
import com.softline.entity.settingCenter.PortalHhRolepage;
import com.softline.entity.settingCenter.HhSysRegister;
import com.softline.entity.settingCenter.HhUsersrole;
import com.softline.entity.settingCenter.PortalHhPagecode;
import com.softline.entity.settingCenter.PortalHhRolepagecode;
import com.softline.service.select.ISelectUserService;
import com.softline.service.settingCenter.user.IGroupService;
import com.softline.service.settingCenter.user.IModelRegisterService;
import com.softline.service.settingCenter.user.IPageCodeService;
import com.softline.service.settingCenter.user.IPageRegisterService;
import com.softline.service.settingCenter.user.IRoleService;
import com.softline.service.settingCenter.user.ISysRegisterService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.IWorkWebService;
import com.softline.util.JsonUtil;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/sys/role")
public class RoleController {
	@Resource(name = "roleService")
	private IRoleService roleService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "pageRegisterService")
	private IPageRegisterService pageRegisterService;
	
	@Resource(name = "pageCodeService")
	private IPageCodeService pageCodeService;
	
	@Resource(name = "groupService")
	private IGroupService groupService;
	
	@Resource(name = "modelRegisterService")
	private IModelRegisterService modelRegisterService;
	
	@Resource(name = "sysRegisterService")
	private ISysRegisterService sysRegisterService;
	
	@Resource(name = "workWebService")
	private IWorkWebService workWebService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("_query")
	public String _query(String type, HhRole role, String roleId, String qRoleName, String qRoleStatus,String vcAccount,String vcEmployeeID, Map<String, Object> map, HttpServletRequest request) {
		if (role.getRoleName() != null) {//验证角色是否保存成功，forward转发能获取到role
			map.put("save", "success");
		}
		if (roleId != null) {//验证人员角色是否分配成功，验证角色公司数据是否分配成功,forward转发能获取到roleId
			map.put("save", "success1");
		}
		String curPageNum = request.getParameter("pageNum");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = roleService.getHhRoleList(qRoleName, qRoleStatus,vcAccount, pageNum, 10,vcEmployeeID);
		map.put("msgPage", msgPage);
		List<HhRole> roleList = msgPage.getList();
		map.put("roleList", roleList);
		map.put("qRoleName", qRoleName);
		map.put("qRoleStatus", qRoleStatus);
		map.put("vcAccount", vcAccount);
		return "/settingCenter/user/roleList";

	}
	
	@RequestMapping("_roleAddOrModify")
	public String _roleAddOrModify(String qRoleName, String qRoleStatus,String vcAccount, String pageNum, HhRole hhRole, String op, Map<String, Object> map, HttpServletRequest request) {
		map.put("qRoleName", qRoleName);
		map.put("qRoleStatus", qRoleStatus);
		map.put("pageNum", pageNum);
		map.put("vcAccount", vcAccount);
		//查询也调用此方法：op=check；
		map.put("op", op);
		if (hhRole.getId() != null && !hhRole.getId().equals("")) {
			HhRole theRole = (HhRole)commonService.findById(HhRole.class, hhRole.getId());
			map.put("theRole", theRole);
			//获取此角色已绑定的人员
			List<HhUsers> users = roleService.getSelectHhUsersList(hhRole.getId());
			map.put("users", users);
		}
		return "/settingCenter/user/roleAddOrModify";
	}
	
	@RequestMapping("_roleCopy")
	public String _roleCopy(HhRole hhRole, String op, Map<String, Object> map, HttpServletRequest request) {
		
		if (hhRole.getId() != null && !hhRole.getId().equals("")) {
			HhRole theRole = (HhRole)commonService.findById(HhRole.class, hhRole.getId());
			map.put("theRole", theRole);
			//获取此角色已绑定的人员
			List<HhUsers> users = roleService.getSelectHhUsersList(hhRole.getId());
			map.put("users", users);
		}
		return "/settingCenter/user/roleCopy";
	}
	
	@RequestMapping("_roleSaveOrUpdate")
	public String _roleSaveOrUpdate(HhRole hhRole, String type, Map<String, Object> map, HttpServletRequest request) {
		//获取当前登录session
		HttpSession session = request.getSession();
		//获取当前登录对象
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		//设置时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if (hhRole.getId() != null && !(hhRole.getId().equals(""))) {//id存在时，为编辑状态
			hhRole.setModifyTime(sdf.format(now));
			hhRole.setModifier(user.getVcEmployeeId());
			//roleService.saveOrUpdate(hhRole, sysId, pageIds, codeIds);
		} else {
			hhRole.setCreateTime(sdf.format(now));
			hhRole.setCreator(user.getVcEmployeeId());
			hhRole.setRoleStatus(1);
		}
		hhRole.setIsUse(0);
		hhRole.setIsDel(0);
		roleService.saveOrUpdateRole(hhRole);
		return "forward:/sys/role/_query";
	}
	
	@RequestMapping("_roleSaveCopy")
	public String _roleSaveCopy(HhRole hhRole, Integer copyId, Map<String, Object> map, HttpServletRequest request) {
		//获取当前登录session
		HttpSession session = request.getSession();
		//获取当前登录对象
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		//设置时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		hhRole.setCreateTime(sdf.format(now));
		hhRole.setCreator(user.getVcEmployeeId());
		hhRole.setRoleStatus(1);
		hhRole.setIsUse(0);
		hhRole.setIsDel(0);
		roleService.saveOrUpdateRoleCopy(hhRole,copyId);
		return "forward:/sys/role/_query";
	}
	
	@ResponseBody
	@RequestMapping("_roleStart")
	public String _roleStart(Integer id) {
		HhRole theRole = (HhRole)commonService.findById(HhRole.class, id);
		theRole.setRoleStatus(1);
		roleService.saveOrUpdateRole(theRole);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("_roleStop")
	public String _roleStop(Integer id) {
		HhRole theRole = (HhRole)commonService.findById(HhRole.class, id);
		theRole.setRoleStatus(2);
		roleService.saveOrUpdateRole(theRole);
		return "success";
	}
	
	@RequestMapping("_roleAddFunction")
	public String _roleSaveModel(String pageNum, String qRoleName,String vcAccount, Integer id, String op, Map<String, Object> map, HttpServletRequest request) {
		
		
		map.put("pageNum", pageNum);
		map.put("qRoleName", qRoleName);
		map.put("vcAccount", vcAccount);
		map.put("op", op);
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, id);
		map.put("hhRole", hhRole);
		
		if (op.equals("addSys")) {
			List<HhSysRegister> sysList = roleService.getRegistedList();
			//角色系统分配回显
			StringBuffer sysHtml = new StringBuffer();
			//根据roleId获取绑定的系统
			List<HhRoleSys> hhRoleSysList = roleService.getRoleSysListByRoleId(hhRole.getId());
			if (!sysList.isEmpty()) {
				sysHtml.append("<ul class=\"show\" style=\"margin-top: 30px\">");
				for (int i = 0; i < sysList.size(); i++) {
					boolean flag = false;
					if (!hhRoleSysList.isEmpty()) {
						for (int j = 0; j < hhRoleSysList.size(); j++) {
							if (sysList.get(i).getId().equals(hhRoleSysList.get(j).getSysId())) {
								flag = true;
								hhRoleSysList.remove(j);
								break;
							}
						}
					}
					sysHtml.append("<li class=\"char_li\">");
					if (flag) {
						sysHtml.append("<input class=\"char_check\" type=\"checkbox\" name=\"sysIds\" value='" + sysList.get(i).getId() + "' id='sys_" + sysList.get(i).getId() + "' checked=\"checked\">");
					}else {
						sysHtml.append("<input class=\"char_check\" type=\"checkbox\" name=\"sysIds\" value='" + sysList.get(i).getId() + "' id='sys_" + sysList.get(i).getId() + "'>");
					}
					sysHtml.append("<label class=\"char_label\" for='sys_" + sysList.get(i).getId() + "'></label><span class=\"char_text\">" + sysList.get(i).getSysName() + "</span></li>");
				}
				sysHtml.append("</ul>");
				map.put("sysHtml", sysHtml.toString());
			}
			return "/user/roleAddSysFunction";
		}else if (op.equals("addModel")) {
			//获取已选择的系统
			List<HhSysRegister> roleSysList = sysRegisterService.getRegistedListByRoleId(hhRole.getId());
			if (!roleSysList.isEmpty()) {
				for (int i = 0; i < roleSysList.size(); i++) {
					//将每一个系统上已注册的模块绑定在对应系统上
					List<HhModelRegister> modelList = modelRegisterService.getRegistedModelListBySysId(roleSysList.get(i).getId());
					roleSysList.get(i).setModelList(modelList);
				}
				//角色模块分配回显
				StringBuffer modelHtml = new StringBuffer();
				//根据roleId获取绑定的模块
				List<HhRoleModel> hhRoleModelList = roleService.getRoleModelListByRoleId(hhRole.getId());
				for (int i = 0; i < roleSysList.size(); i++) {
					modelHtml.append("<p class='model_t1'>所属系统:<span>" + roleSysList.get(i).getSysName() + "</span></p>");
					List<HhModelRegister> modelList = roleSysList.get(i).getModelList();
					if (!modelList.isEmpty()) {
						modelHtml.append("<ul class=\"show\">");
						for (int j = 0; j < modelList.size(); j++) {
							boolean flag = false;
							if (!hhRoleModelList.isEmpty()) {
								for (int k = 0; k < hhRoleModelList.size(); k++) {
									if (modelList.get(j).getId().equals(hhRoleModelList.get(k).getModelId())) {
										flag = true;
										hhRoleModelList.remove(k);
										break;
									}
								}
							}
							modelHtml.append("<li class=\"char_li\">");
							if (flag) {
								modelHtml.append("<input class=\"char_check\" type=\"checkbox\" name=\"modelIds\" " +
										"value='" + roleSysList.get(i).getId() + "_" + modelList.get(j).getId() + "' id='model_" + modelList.get(j).getId() + "' checked=\"checked\">");
							}else {
								modelHtml.append("<input class=\"char_check\" type=\"checkbox\" name=\"modelIds\" " +
										"value='" + roleSysList.get(i).getId() + "_" + modelList.get(j).getId() + "' id='model_" + modelList.get(j).getId() + "'>");
							}
							modelHtml.append("<label class=\"char_label\" for='model_" + modelList.get(j).getId() + "'>" +
									"</label><span class=\"char_text\">" + modelList.get(j).getModelName() + "</span></li>");
						}
						modelHtml.append("</ul>");
					}
				}
				map.put("modelHtml", modelHtml.toString());
			}else {
				map.put("modelHtml", "请先选择系统！");
			}
			return "/settingCenter/user/roleAddModelFunction";
		}else if (op.equals("addPage")) {
			//先获取已选中的系统
			List<HhSysRegister> roleSysList = sysRegisterService.getRegistedListByRoleId(hhRole.getId());
			if (!roleSysList.isEmpty()) {
				for (int i = 0; i < roleSysList.size(); i++) {
					//将每一个系统上已选中的模块绑定在对应系统上
					List<HhModelRegister> roleModelList = modelRegisterService.getRoleModelListBySysIdAndRoleId(roleSysList.get(i).getId(), hhRole.getId());
					roleSysList.get(i).setModelList(roleModelList);
					if (!roleModelList.isEmpty()) {
						for (int j = 0; j < roleModelList.size(); j++) {
							//将每一个模块上已注册的页面绑定在对应模块上
							List<HhPageregister> pageList = pageRegisterService.getPagesByModelId(roleModelList.get(j).getId());
							roleModelList.get(j).setPageList(pageList);
						}
					}
				}
				//角色页面分配回显
				StringBuffer pageHtml = new StringBuffer();
				//根据roleId获取绑定的页面
				List<PortalHhRolepage> hhRolepageList = roleService.getRolepageListByRoleId(hhRole.getId());
				for (int i = 0; i < roleSysList.size(); i++) {
					pageHtml.append("<p class='model_t1'>所属系统:<span>" + roleSysList.get(i).getSysName() + "</span></p>");
					List<HhModelRegister> roleModelList = roleSysList.get(i).getModelList();
					if (!roleModelList.isEmpty()) {
						for (int j = 0; j < roleModelList.size(); j++) {
							pageHtml.append("<p class='model_t2'>所属模块:<span>" + roleModelList.get(j).getModelName() + "</span></p>");
							
							/*Hashtable<Integer,String> pageTypeMap=new Hashtable<Integer,String>();
							pageTypeMap.put(1, "分类1");
							pageTypeMap.put(2, "分类2");
							pageTypeMap.put(3, "分类3");
							pageTypeMap.put(4, "分类4");
							pageTypeMap.put(5, "分类5");*/
							
							List<Object> pageTypeList=roleService.getPageTypeGroupBy(roleSysList.get(i).getId(), roleModelList.get(j).getId());
							
							List<HhPageregister> pageList = roleModelList.get(j).getPageList();
							for (int t = 0; t < pageTypeList.size(); t++) {
								Object[] pageType=(Object[])pageTypeList.get(t);
								Boolean typeCount=false;
								for (int k = 0; k < pageList.size(); k++) {
									if(pageList.get(k).getPageType().intValue()==Integer.parseInt( pageType[0].toString())){
										typeCount=true;
										break;
									}
								}
								
								//String pageTypeName=pageTypeMap.get(t);
								pageHtml.append("<p class='model_t3'>所属分类:&nbsp;");
									pageHtml.append("<input class=\"char_check\" type=\"checkbox\" id=\"type_"+j+t+"\">");
									pageHtml.append("<label class=\"char_label\" for=\"type_" + j+t + "\" name=\"chkType\"></label>");
									pageHtml.append("&nbsp;<span class=\"char_text spk\">" + pageType[1] + "</span>");
								pageHtml.append("</p>");
								if (!pageList.isEmpty()) {
									pageHtml.append("<ul class=\"show\">");
									for (int k = 0; k < pageList.size(); k++) {
										if(pageList.get(k).getPageType().intValue()!=Integer.parseInt( pageType[0].toString()))
											continue;
										
										boolean flag = false;
										if (!hhRolepageList.isEmpty()) {
											for (int f = 0; f < hhRolepageList.size(); f++) {
												if (pageList.get(k).getId().equals(hhRolepageList.get(f).getPageId())) {
													flag = true;
													hhRolepageList.remove(f);
													break;
												}
											}
										}
										pageHtml.append("<li class=\"char_li\">");
										if (flag) {
											pageHtml.append("<input class=\"char_check\" type=\"checkbox\" name=\"pageIds\" " +
													"value='" + roleSysList.get(i).getId() + "_" + roleModelList.get(j).getId() + "_" + pageList.get(k).getId() + "' " +
															"id='page_" + pageList.get(k).getId() + "' checked=\"checked\">");
										}else {
											pageHtml.append("<input class=\"char_check\" type=\"checkbox\" name=\"pageIds\" " +
													"value='" + roleSysList.get(i).getId() + "_" + roleModelList.get(j).getId() + "_" + pageList.get(k).getId() + "' " +
															"id='page_" + pageList.get(k).getId() + "'>");
										}
										pageHtml.append("<label class=\"char_label\" for='page_" + pageList.get(k).getId() + "'>" +
												"</label><span class=\"char_text\">" + pageList.get(k).getPageName() + "</span></li>");
									}
									pageHtml.append("</ul>");
								}
							}
						}
					}else {
						pageHtml.append("请选择对应模块！");
					}
				}
				map.put("pageHtml", pageHtml.toString());
			}else {
				map.put("pageHtml", "请先选择系统！");
			}
			return "/settingCenter/user/roleAddPageFunction";
		}else {
			//先获取已选中的系统
			List<HhSysRegister> roleSysList = sysRegisterService.getRegistedListByRoleId(hhRole.getId());
			if (!roleSysList.isEmpty()) {
				for (int i = 0; i < roleSysList.size(); i++) {
					//将每一个系统上已选中的模块绑定在对应系统上
					List<HhModelRegister> roleModelList = modelRegisterService.getRoleModelListBySysIdAndRoleId(roleSysList.get(i).getId(), hhRole.getId());
					roleSysList.get(i).setModelList(roleModelList);
					if (!roleModelList.isEmpty()) {
						for (int j = 0; j < roleModelList.size(); j++) {
							//将每一个模块上已选中的页面绑定在对应模块上
							List<HhPageregister> rolePageList = pageRegisterService.getRolePageListByModelIdAndRoleId(roleModelList.get(j).getId(), hhRole.getId());
							roleModelList.get(j).setPageList(rolePageList);
							if (!rolePageList.isEmpty()) {
								for (int k = 0; k < rolePageList.size(); k++) {
									//将每一个页面上已注册的功能绑定在对应页面上
									List<PortalHhPagecode> pagecodeList = pageCodeService.getCodeList(null, rolePageList.get(k).getId());
									rolePageList.get(k).setCodeList(pagecodeList);
								}
							}
						}
					}
				}
				//角色功能分配回显
				StringBuffer pagecodeHtml = new StringBuffer();
				//根据roleId获取绑定的功能
				List<PortalHhRolepagecode> hhRolepagecodeList = roleService.getRolepagecodeListByRoleId(hhRole.getId());
				for (int i = 0; i < roleSysList.size(); i++) {
					pagecodeHtml.append("<p class='model_t1'>所属系统:<span>" + roleSysList.get(i).getSysName() + "</span></p>");
					List<HhModelRegister> roleModelList = roleSysList.get(i).getModelList();
					if (!roleModelList.isEmpty()) {
						for (int j = 0; j < roleModelList.size(); j++) {
							pagecodeHtml.append("<p class='model_t2'>所属模块:<span>" + roleModelList.get(j).getModelName() + "</span></p>");
							
							/*Hashtable<Integer,String> pageTypeMap=new Hashtable<Integer,String>();
							pageTypeMap.put(1, "分类1");
							pageTypeMap.put(2, "分类2");
							pageTypeMap.put(3, "分类3");
							pageTypeMap.put(4, "分类4");
							pageTypeMap.put(5, "分类5");*/
							
							List<Object> pageTypeList=roleService.getPageTypeGroupBy(roleSysList.get(i).getId(), roleModelList.get(j).getId());
							
							List<HhPageregister> rolePageList = roleModelList.get(j).getPageList();
							for (int t = 0; t < pageTypeList.size(); t++) {
								Object[] pageType=(Object[])pageTypeList.get(t);
								Boolean typeCount=false;
								for (int k = 0; k < rolePageList.size(); k++) {
									if(rolePageList.get(k).getPageType().intValue()==Integer.parseInt( pageType[0].toString())){
										typeCount=true;
										break;
									}
								}
								
								//String pageTypeName=pageTypeMap.get(t);
								pagecodeHtml.append("<p class='model_t3'>所属分类:&nbsp;");
								pagecodeHtml.append("<input class=\"char_check\" type=\"checkbox\" id=\"type_"+j+t+"\">");
								pagecodeHtml.append("<label class=\"char_label\" for=\"type_" + j+t + "\" name=\"chkType\"></label>");
								pagecodeHtml.append("&nbsp;<span class=\"char_text spk\">" + pageType[1] + "</span>");
								pagecodeHtml.append("</p>");
								pagecodeHtml.append("<div>");
								if (!rolePageList.isEmpty()) {
									for (int k = 0; k < rolePageList.size(); k++) {
										if(rolePageList.get(k).getPageType().intValue()!=Integer.parseInt( pageType[0].toString()))
											continue;
										
										pagecodeHtml.append("<p class='model_t4'>所属页面:<span>" + rolePageList.get(k).getPageName() + "</span>");
											/*pagecodeHtml.append("&nbsp;&nbsp;&nbsp;<input class=\"char_check\" type=\"checkbox\" id=\"type_"+j+t+k+"\" "+(typeCount?"checked=\"checked\"":"")+">");
											pagecodeHtml.append("<label class=\"char_label\" for=\"type_" + j+t +k+ "\" name=\"chkPage\">");*/
										pagecodeHtml.append("</p>");
										List<PortalHhPagecode> pagecodeList = rolePageList.get(k).getCodeList();
										if (!pagecodeList.isEmpty()) {
											pagecodeHtml.append("<ul class=\"show\">");
											for (int f = 0; f < pagecodeList.size(); f++) {
												boolean flag = false;
												if (!hhRolepagecodeList.isEmpty()) {
													for (int y = 0; y < hhRolepagecodeList.size(); y++) {
														if (pagecodeList.get(f).getId().equals(hhRolepagecodeList.get(y).getCodeId())) {
															flag = true;
															hhRolepagecodeList.remove(y);
															break;
														}
													}
												}
												pagecodeHtml.append("<li class=\"char_li\">");
												if (flag) {
													pagecodeHtml.append("<input class=\"char_check\" type=\"checkbox\" name=\"codeIds\" " +
															"value='" + roleSysList.get(i).getId() + "_" + roleModelList.get(j).getId() + "_" + rolePageList.get(k).getId() + "_" + pagecodeList.get(f).getId() + "' " +
																	"id='pagecode_" + pagecodeList.get(f).getId() + "' checked=\"checked\">");
												}else {
													pagecodeHtml.append("<input class=\"char_check\" type=\"checkbox\" name=\"codeIds\" " +
															"value='" + roleSysList.get(i).getId() + "_" + roleModelList.get(j).getId() + "_" + rolePageList.get(k).getId() + "_" + pagecodeList.get(f).getId() + "' " +
																	"id='pagecode_" + pagecodeList.get(f).getId() + "'>");
												}
												pagecodeHtml.append("<label class=\"char_label\" for='pagecode_" + pagecodeList.get(f).getId() + "'>" +
														"</label><span class=\"char_text\">" + pagecodeList.get(f).getCodeName() + "</span></li>");
											}
											pagecodeHtml.append("</ul>");
										}
									}
								}else {
									pagecodeHtml.append("请选择对应页面！");
								}
								pagecodeHtml.append("</div>");
							}
						}	
					}else {
						pagecodeHtml.append("请选择对应模块！");
					}
				}
				map.put("pagecodeHtml", pagecodeHtml.toString());
			}else {
				map.put("pagecodeHtml", "请先选择系统！");
			}
			return "/settingCenter/user/roleAddCodeFunction";
		}	
	}
	
	@ResponseBody
	@RequestMapping("_del")
	public String _del(Integer id) {
		roleService.deleteRole(id);
		return "success";
	}
	
	@RequestMapping("_saveRoleSysFunction")
	public String _saveRoleSyslFunction(Integer roleId, String type, String sysIds, Map<String, Object> map, HttpServletRequest request) {
		roleService.saveRoleSysFunction(roleId, sysIds);
		_savehhOperationLog(request);
		return "forward:/sys/role/_query";
	}
	
	@RequestMapping("_saveRoleModelFunction")
	public String _saveRoleModelFunction(Integer roleId, String type, String modelIds, Map<String, Object> map, HttpServletRequest request) {
		roleService.saveRoleModelFunction(roleId, modelIds);
		_savehhOperationLog(request);
		return "forward:/sys/role/_query";
	}
	
	@RequestMapping("_saveRolePageFunction")
	public String _saveRolePageFunction(Integer roleId, String type, String pageIds, Map<String, Object> map, HttpServletRequest request) {
		roleService.saveRolePageFunction(roleId, pageIds);
		_savehhOperationLog(request);
		return "forward:/sys/role/_query";
	}
	
	@RequestMapping("_saveRoleCodeFunction")
	public String _saveRoleCodeFunction(Integer roleId, String type, String codeIds, Map<String, Object> map, HttpServletRequest request) {
		roleService.saveRoleCodeFunction(roleId, codeIds);
		_savehhOperationLog(request);
		return "forward:/sys/role/_query";
	}
	
	public void _savehhOperationLog(HttpServletRequest request) {
		//获取当前登录session
		HttpSession session = request.getSession();
		//获取当前登录对象
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		HhOperationLog hhOperationLog = new HhOperationLog();
		String date = Common.getDate(new Date(),"yyyy-MM-dd HH:mm:ss");
		hhOperationLog.setName(user.getVcAccount());
        hhOperationLog.setOperationTime(date);
        hhOperationLog.setModel("用户管理");
        hhOperationLog.setDescription("用户管理:更新角色权限");
        systemService.saveHhOperationLogList(hhOperationLog);
	}
	
	
	@RequestMapping("_roleCompanyAdd")
	public String _roleCompanyAdd(String pageNum, String qRoleName,String vcAccount, Integer id, String companyType, Map<String, Object> map, HttpServletRequest request) {
		map.put("pageNum", pageNum);
		map.put("qRoleName", qRoleName);
		map.put("companyType", companyType);
		map.put("vcAccount", vcAccount);
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, id);
		map.put("hhRole", hhRole);
		if(companyType.equals("manage")) {//管理口径树
			CompanyTree leveltree = selectUserService.getDepTree();
			String[] transLeveltreeId = leveltree.getId().substring(0, leveltree.getId().length()-1).split("-");
			String leveltreeId = transLeveltreeId[transLeveltreeId.length-1];
			String allCompanyTree = "[";
			allCompanyTree += "{id:'"+leveltreeId+"',pId:'0',name:'"+leveltree.getText()+"'},";
			String shtml = getTreeHTML(leveltree);
			allCompanyTree += shtml;
			allCompanyTree += "]";
			
			//获取所有企业树
//		List<Map<String,String>> allCompanyList = new ArrayList<Map<String,String>>();
//		allCompanyList = roleService.getAllCompanyTree();
//		//将List<Map<>>转成JSON字符串
//		String allCompanyTree = JsonUtil.object2JSON(allCompanyList);
			map.put("allCompanyTree", allCompanyTree);
			//获取该role已选择的企业，进行回显
			List<Map<String, Integer>> checkedCompanyList = new ArrayList<Map<String, Integer>>();
			checkedCompanyList = roleService.getCheckedCompanyList(id);
			String checkedCompany = JsonUtil.object2JSON(checkedCompanyList);
			map.put("checkedCompany", checkedCompany);
		}else {//财务口径树
			//调用bim_work接口获取全体财务树
//			WorkWebServiceService service = new WorkWebServiceService();
//			IWorkWebService iw = service.getPort(IWorkWebService.class);
			String allCompanyTree = workWebService.getAllFinanceTree();
			map.put("allCompanyTree", allCompanyTree);
			//获取该role已选择的企业，进行回显
			List<Map<String, String>> checkedCompanyList = new ArrayList<Map<String, String>>();
			checkedCompanyList = roleService.getCheckedFinanceCompanyList(id);
			String checkedCompany = JsonUtil.object2JSON(checkedCompanyList);
			map.put("checkedCompany", checkedCompany);
		}
		return "/settingCenter/user/roleCompanyAdd";
	}
	
	private String getTreeHTML(CompanyTree cTree){
		StringBuffer buffer = new StringBuffer();
		List<CompanyTree> sonList = cTree.getChildren();
		if(sonList.size()>0){
			for(int i=0;i<sonList.size();i++){
				CompanyTree son = sonList.get(i);
				String[] transSonId = son.getId().substring(0, son.getId().length()-1).split("-");
				String sonId = transSonId[transSonId.length-1];
				String[] transPId = cTree.getId().substring(0, cTree.getId().length()-1).split("-");
				String pId = transPId[transPId.length-1];
				buffer.append("{id:'"+sonId+"',pId:'"+pId+"',name:'"+son.getText()+"'},");
				buffer.append(getTreeHTML(son));
			}
		}
		return buffer.toString();
	}
	
	/*@RequestMapping("_roleUserAddByDep")
	public String roleUserAddByDep(Integer id, Map<String, Object> map, HttpServletRequest request) {
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, id);
		map.put("hhRole", hhRole);
		//获取已经选择的部门
		List<HhGroupUserRole> selectedDepList = roleService.getSelectedDepList(id);
		//构造部门树
		CompanyTree leveltree = selectUserService.getDepTree();
		leveltree.setIcon("active");
		String shtml = getDepTreeHTML(leveltree, selectedDepList);
		map.put("shtml", shtml);
		
		return "/user/roleUserAddByDep";
	}*/
	
	@RequestMapping("_roleUserAddBySearch")
	public String roleUserAddBySearch(String pageNum, String qRoleName,String vcAccount, String qRoleStatus, Integer id, Map<String, Object> map, HttpServletRequest request) {
		map.put("pageNum", pageNum);
		map.put("qRoleName", qRoleName);
		map.put("qRoleStatus", qRoleStatus);
		map.put("vcAccount", vcAccount);
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, id);
		map.put("hhRole", hhRole);
		//获取已选择的人员List
		List<HhUsers> selectedHhUsersList = roleService.getSelectHhUsersList(id);
		map.put("selectedHhUsersList", selectedHhUsersList);
		return "/settingCenter/user/roleUserAddBySearch";
	}
	
	@RequestMapping("_roleUserAddByUser")
	public String roleUserAddByUser(String pageNum, String qRoleName,String vcAccount, String qRoleStatus, Integer id, Map<String, Object> map, HttpServletRequest request) {
		map.put("pageNum", pageNum);
		map.put("qRoleName", qRoleName);
		map.put("qRoleStatus", qRoleStatus);
		map.put("vcAccount", vcAccount);
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, id);
		map.put("hhRole", hhRole);
		//获取已选择的人员List
		List<HhUsers> selectedHhUsersList = roleService.getSelectHhUsersList(id);
		map.put("selectedHhUsersList", selectedHhUsersList);
		//构造部门人员树
		CompanyTree userLeveltree = selectUserService.getDepTree();
		userLeveltree.setIcon("active");
		String userShtml = getUserDepTreeHTML(userLeveltree);
		map.put("userShtml", userShtml);
		return "/settingCenter/user/roleUserAddByUser";
	}
	
	@RequestMapping("_roleUserAddByGroup")
	public String _roleUserAddByGroup(String pageNum, String qRoleName,String vcAccount, String qRoleStatus, Integer id, Map<String, Object> map, HttpServletRequest request) {
		map.put("pageNum", pageNum);
		map.put("qRoleName", qRoleName);
		map.put("qRoleStatus", qRoleStatus);
		map.put("vcAccount", vcAccount);
		HhRole hhRole = (HhRole)commonService.findById(HhRole.class, id);
		map.put("hhRole", hhRole);
		//获取启用的用户组
		Integer groupStatus = 1;
		List<HhGroup> groupList = groupService.getStartGroups(groupStatus);
		map.put("groupList",groupList);
		//获取已经选择的部门
		List<HhGroupUserRole> selectedGroupList = roleService.getSelectedGroupList(id);
		StringBuffer groupHtml = new StringBuffer();
		if (!groupList.isEmpty()) {
			for (int i = 0; i < groupList.size(); i++) {
				boolean flag = false;
				for (int j = 0; j < selectedGroupList.size(); j++) {
					if (groupList.get(i).getId().equals(selectedGroupList.get(j).getGroupId())) {
						selectedGroupList.remove(j);
						flag = true;
						break;
					}
				}
				groupHtml.append("<tr><td>");
				if (flag) {
					groupHtml.append("<input type=\"checkbox\" name=\"groupIds\" value='" + groupList.get(i).getId() + "' checked>");
				}else {
					groupHtml.append("<input type=\"checkbox\" name=\"groupIds\" value='" + groupList.get(i).getId() + "'>");
				}
				groupHtml.append("<td>" + groupList.get(i).getGroupName() + "</td></tr>");	
			}
			map.put("groupHtml", groupHtml.toString());
		}
		return "/settingCenter/user/roleUserAddByGroup";
	}
	
	/*private String getDepTreeHTML(CompanyTree cTree, List<HhGroupUserRole> selectedDepList){
		StringBuffer buffer = new StringBuffer();
		List<CompanyTree> sonList = cTree.getChildren();
		//循环已选部门，进行回显
		boolean flag = false;
		for (int i = 0; i < selectedDepList.size(); i++ ) {
			if (selectedDepList.get(i).getDepId().equals(cTree.getId())) {
				selectedDepList.remove(i);
				flag = true;
				break;
			}
		}
		buffer.append("<li class=\"line-list\">");
		if (flag) {
			if(sonList.size()>0){
				buffer.append("<span class=\"departmentLabel\"><i class=\"iconfont icon-tree-open-2\"></i>" +
						  	  "<label class=\"checkboxList checkbox_checked\" for='" + cTree.getId() + "'>" +
						  	  "<span class=\"icon\"></span>" +
						  	  "<input type=\"checkbox\" id='" + cTree.getId() + "' checked >" + cTree.getText() + "</label>" +
					          "</span>");
				buffer.append("<ul class=\"level-2 active\">");
				for(int i=0;i<sonList.size();i++){
					CompanyTree son = sonList.get(i);
					buffer.append(getDepTreeHTML(son, selectedDepList));
				}
				buffer.append("</ul>");
			}else {
				buffer.append("<label class=\"checkboxList checkbox_checked\" for='" + cTree.getId() + "'>" +
							  "<span class=\"icon\"></span><input type=\"checkbox\" id='" + cTree.getId() + "' checked>"+cTree.getText()+"</label>");
			}
		}else {
			if(sonList.size()>0){
				if("active".equals(cTree.getIcon())){
					buffer.append("<span class=\"departmentLabel\"><i class=\"iconfont icon-tree-open-2\"></i>" +
								  "<label class=\"checkboxList\" for='" + cTree.getId() + "'>" +
							      "<span class=\"icon\"></span>" +
							      "<input type=\"checkbox\" id='" + cTree.getId() + "'>" + cTree.getText() + "</label>" +
							      "</span>");
				}else{
					buffer.append("<span class=\"departmentLabel\"><i class=\"iconfont icon-tree-close-2\"></i>" +
								  "<label class=\"checkboxList\" for='" + cTree.getId() + "'>" +
								  "<span class=\"icon\"></span>" +
								  "<input type=\"checkbox\" id='" + cTree.getId() + "'>" + cTree.getText() + "</label>" +
						      	  "</span>");
				}
				if ("active".equals(cTree.getIcon())) {
					buffer.append("<ul class=\"level-2 "+cTree.getIcon()+"\">");
				}else {
					buffer.append("<ul class=\"level-2\">");
				}
				for(int i=0;i<sonList.size();i++){
					CompanyTree son = sonList.get(i);
					buffer.append(getDepTreeHTML(son, selectedDepList));
				}
				buffer.append("</ul>");
			}else{
				buffer.append("<label class=\"checkboxList\" for='" + cTree.getId() + "'>" +
							  "<span class=\"icon\"></span><input type=\"checkbox\" id='" + cTree.getId() + "'>"+cTree.getText()+"</label>");
			}
		}
		buffer.append("</li>");
		return buffer.toString();
	}*/
	
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
					hql.append("<li class=\"line-list\"><label class=\"checkboxList checkbox_checked\" for='" + item.getVcEmployeeId() + "'>" +
						      "<span class=\"icon\"></span><input type=\"checkbox\" id='" + item.getVcEmployeeId() + "' checked>" + item.getVcName() + "</label></li>");
				}else {
					hql.append("<li class=\"line-list\"><label class=\"checkboxList\" for='" + item.getVcEmployeeId() + "'>" +
						      "<span class=\"icon\"></span><input type=\"checkbox\" id='" + item.getVcEmployeeId() + "'>" + item.getVcName() + "</label></li>");
				}
			}
			hql.append(" </ul>");
			String jsonString = JSON.toJSONString(hql.toString());
			response.getWriter().write(jsonString);
		}
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/_searchPersonByName", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
//	public void _searchPersonByName(String vcEmployeeIds, String userName, HttpServletResponse response, HttpServletRequest request) throws IOException { 
//		String jsonString = "";
//		List<HhUsers> searchPersonList = new ArrayList<HhUsers>();
//		if(userName != null && !"".equals(userName)) {
//			searchPersonList = selectUserService.getUsersByName(userName);
//		}
//		//获取已选择的人员List
//		String[] selectedHhUsersList = !"".equals(vcEmployeeIds)?vcEmployeeIds.substring(0, vcEmployeeIds.length()-1).split(","):new String[0];
//		if (!searchPersonList.isEmpty()) {
//			StringBuffer hql = new StringBuffer();
//			hql.append(" <ul class=\"level-2 active\">");
//			for (HhUsers item : searchPersonList) {
//				//flag用于判断当前公司下的人员是否已被选中
//				boolean flag = false;
//				if (selectedHhUsersList.length > 0) {
//					for (int i = 0; i < selectedHhUsersList.length; i++) {
//						if (selectedHhUsersList[i].equals(item.getVcEmployeeId())) {
//							flag = true;
//							break;
//						}
//					}
//				}
//				if (flag) {
//					hql.append("<li class=\"line-list\"><label class=\"checkboxList checkbox_checked\" for='" + item.getVcEmployeeId() + "'>" +
//						      "<span class=\"icon\"></span><input type=\"checkbox\" id='" + item.getVcEmployeeId() + "' checked>" + item.getVcName() + "-----" + item.getVcAccount() + "-----" + item.getVcFullName() + "</label></li>");
//				}else {
//					hql.append("<li class=\"line-list\"><label class=\"checkboxList\" for='" + item.getVcEmployeeId() + "'>" +
//						      "<span class=\"icon\"></span><input type=\"checkbox\" id='" + item.getVcEmployeeId() + "'>" + item.getVcName() + "-----" + item.getVcAccount() + "-----" + item.getVcFullName() + "</label></li>");
//				}
//			}
//			hql.append(" </ul>");
//			jsonString = JSON.toJSONString(hql.toString());
//		}
//		response.getWriter().write(jsonString);
//	}
	
	@ResponseBody
	@RequestMapping(value = "/_searchPersonByvcAccount", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void _searchPersonByvcAccount(String vcEmployeeIds, String vcAccount,String userName, HttpServletResponse response, HttpServletRequest request) throws IOException { 
		String jsonString = "";
		List<HhUsers> searchPersonList = new ArrayList<HhUsers>();
		if((vcAccount != null && !"".equals(vcAccount))||
				(userName != null && !"".equals(userName))) {
			//searchPersonList = selectUserService.getUsersByName(vcEmployeeId);
			searchPersonList = selectUserService.getUsersByVcEmployeeId(userName,vcAccount);	
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
					hql.append("<li class=\"line-list\"><label class=\"checkboxList checkbox_checked\" for='" + item.getVcEmployeeId() + "'>" +
						      "<span class=\"icon\"></span><input type=\"checkbox\" id='" + item.getVcEmployeeId() + "' checked>" + item.getVcName() + "-----" + item.getVcAccount() + "-----" + item.getVcFullName() + "-----"+(item.getCflag().equals("0")?"离职":"在职")+ "</label></li>");
				}else {
					hql.append("<li class=\"line-list\"><label class=\"checkboxList\" for='" + item.getVcEmployeeId() + "'>" +
						      "<span class=\"icon\"></span><input type=\"checkbox\" id='" + item.getVcEmployeeId() + "'>" + item.getVcName() + "-----" + item.getVcAccount() + "-----" + item.getVcFullName() + "-----"+(item.getCflag().equals("0")?"离职":"在职")+ "</label></li>");
				}
			}
			hql.append(" </ul>");
			jsonString = JSON.toJSONString(hql.toString());
		}
		response.getWriter().write(jsonString);
	}
	
	
	
	
	
	
	
	
	
	/*@RequestMapping("_roleUserByDepSaveOrUpdate")
	public String _roleUserByDepSaveOrUpdate(HhUsersrole userRole, HttpServletRequest request, String depIds) {
		roleService.saveOrUpdateRoleUserByDep(userRole, depIds);
		return "forward:/sys/role/_query";
	}*/
	
	@RequestMapping("_roleUserByUserSaveOrUpdate")
	public String _roleUserByUserSaveOrUpdate(HhUsersrole userRole, String type, HttpServletRequest request, String vcEmployeeIds) {
		roleService.saveOrUpdateRoleUserByUser(userRole, vcEmployeeIds);
		return "forward:/sys/role/_query";
	}
	
	@RequestMapping("_roleUserByGroupSaveOrUpdate")
	public String _roleUserByGroupSaveOrUpdate(HhUsersrole userRole, String type, HttpServletRequest request, String groupIds) {
		roleService.saveOrUpdateRoleUserByGroup(userRole, groupIds);
		return "forward:/sys/role/_query";
	}
	
	@RequestMapping("_roleCompanySaveOrUpdate")
	public String roleCompanySaveOrUpdate(PortalHhRolecompany roleCompany, String type, String companyType, HttpServletRequest request, String companyIds) {
		if(companyType.equals("manage")) {
			roleService.saveOrUpdateRoleCompany(roleCompany, companyIds);
		}else {
			roleService.saveOrUpdateRoleFinanceCompany(roleCompany, companyIds);
		}
		return "forward:/sys/role/_query";
	}
	
	@RequestMapping("_detailList")
	public String _detailList(String roleIds,Map<String, Object> map){
		List<HhRole> roles = roleService.getHhRolesByRoleIds(roleIds);
		map.put("roles", roles);
		return "/settingCenter/user/roleDetailList";
	}
	
}
