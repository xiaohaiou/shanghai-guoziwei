package com.softline.controller.settingCenter.user;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softline.common.Common;
import com.softline.common.SettingCenterBase;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.entity.settingCenter.HhPageregister;
import com.softline.entity.settingCenter.HhSysRegister;
import com.softline.service.settingCenter.user.IModelRegisterService;
import com.softline.service.settingCenter.user.IPageRegisterService;
import com.softline.service.settingCenter.user.ISysRegisterService;
import com.softline.service.system.IBaseService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/page/register")
public class PageRegisterController {
	
	@Resource(name = "pageRegisterService")
	private IPageRegisterService pageRegisterService;
	
	@Resource(name = "sysRegisterService")
	private ISysRegisterService sysRegisterService;
	
	@Resource(name = "modelRegisterService")
	private IModelRegisterService modelRegisterService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@RequestMapping("_query")
	public String queryPageRegister(HhPageregister register, String qPageNum, String qPageName, String qModelId, Map<String, Object> map, HttpServletRequest request) {
		if (register.getPageName() != null) {
			map.put("save", "success");
		}
		map.put("qPageNum", qPageNum);
		map.put("qPageName", qPageName);
		map.put("qModelId", qModelId);
		//获取已注册模板下拉列表
		List<HhModelRegister> modelRegistedList = modelRegisterService.getRegistedList();
		map.put("modelRegistedList", modelRegistedList);
		
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        
        MsgPage msgPage = pageRegisterService.getPageMsgRegisterList(qPageNum, qPageName, qModelId, pageNum, 10);
		List<HhPageregister> pageRegisterList = msgPage.getList();
		map.put("pageRegisterList", pageRegisterList);
		map.put("msgPage", msgPage);
		return "/settingCenter/user/pageRegisterList";
	}
	
	@RequestMapping("_addOrModify")
	public String addOrModifyPageRegister(String pageNums, String qPageNum, String qPageName, String qModelId, HhPageregister register, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("pageNums", pageNums);
		map.put("qPageNum", qPageNum);
		map.put("qPageName", qPageName);
		map.put("qModelId", qModelId);
		
		//页面类型
		List<HhBase> pageType = baseService.getBases(SettingCenterBase.page_type);
		map.put("pageType", pageType);
		
		//获取已注册系统下拉列表
		List<HhSysRegister> sysRegistedList = sysRegisterService.getRegistedList();
		map.put("sysRegistedList", sysRegistedList);
		map.put("op", op);
		//id存在时，为编辑状态，将id对应的实体进行回显
		if (register.getId() != null && !(register.getId().equals(""))) {
			HhPageregister theRegister = new HhPageregister();
			theRegister = pageRegisterService.getThePageRegister(register.getId());
			map.put("theRegister", theRegister);
			//获取该系统对应的已注册模板下拉列表
			List<HhModelRegister> modelRegistedList = modelRegisterService.getModelsBySysId(theRegister.getSysId());
			map.put("modelRegistedList", modelRegistedList);
		}
		return "/settingCenter/user/pageRegisterAddOrModify";
	}
	
	@RequestMapping("_saveOrUpdate")
	@ResponseBody
	public String saveOrUpdatePageRegister(HhPageregister register, HttpServletRequest request) {
		//获取当前登录session
		HttpSession session = request.getSession();
		//获取当前登录对象
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		//设置时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if (register.getId() != null && !(register.getId().equals(""))) {//id存在时，为编辑状态
			//添加modify_time字段
			register.setModifyTime(sdf.format(now));
			//modifier字段数据(存入user的vcEmployeeId)
			register.setModifier(user.getVcEmployeeId());
		} else {//反之新建
			//添加create_time字段
			register.setCreateTime(sdf.format(now));
			//creator字段(存入user的vcEmployeeId)
			register.setCreator(user.getVcEmployeeId());
		}
		register.setIsDel(0);
		pageRegisterService.saveOrUpdatePageRegister(register);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("_del")
	public String delPageRegister(String id) {
		String msg = pageRegisterService.deletePageRegister(Integer.parseInt(id));
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("_getModels")
	public List<HhModelRegister> getModels(String sysId) {
		List<HhModelRegister> modelRegistedList = modelRegisterService.getModelsBySysId(Integer.parseInt(sysId));
		return modelRegistedList;
	}
	
	@ResponseBody
	@RequestMapping("_checkPageNum")
	public String checkPageNum(String pageNum, Integer id) {
		Integer num = pageRegisterService.checkPageNum(pageNum, id);
		return "{num:" + num + "}";
	}
	
	@RequestMapping("PagePersonQuery")
	public String modelPersonquery(Integer modelId, String vcName, String vcAccount, Map<String, Object> map, HttpServletRequest request) {
		//获取模块信息
		HhPageregister registerModel = pageRegisterService.getThePageRegister(modelId);
		map.put("registerModel", registerModel);
		String curPageNum = request.getParameter("pageNums");
        if (!Common.notEmpty(curPageNum)) { 
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        
        MsgPage msgPage = pageRegisterService.getPageMsgPersonList(modelId,vcName, vcAccount, pageNum, 10);
		List<Object[]> PagePersonList = msgPage.getList();
		map.put("PagePersonList", PagePersonList);
		map.put("msgPage", msgPage);
		map.put("modelId", modelId);
		map.put("vcName", vcName);
		map.put("vcAccount", vcAccount);
		map.put("searchurl", "/bim_portal/page/register/PagePersonQuery");
		return "/settingCenter/user/pagePersonList";
//		pageRegister
	}
	
	@RequestMapping(value="/pagePersonExport")
	public void pageExportExporttest(Integer modelId, String vcName, String vcAccount,
	HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) 
	throws IOException, InvalidFormatException {
	 HhPageregister registerModel = pageRegisterService.getThePageRegister(modelId);
//	        查询123
	        XSSFWorkbook workBook1 = new XSSFWorkbook();
//			
	        workBook1=pageRegisterService.getPageMsgPersonListExport(modelId,vcName, vcAccount);
				     
				     // 清空response档案查询
				        response.reset();
				        String _name = "页面注册已分配人员导出列表.xlsx";
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
	
	
	
}
