package com.softline.controller.settingCenter.user;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softline.common.Common;
import com.softline.entity.settingCenter.PortalHhPagecode;
import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.entity.settingCenter.HhPageregister;
import com.softline.entity.settingCenter.HhSysRegister;
import com.softline.service.settingCenter.user.IModelRegisterService;
import com.softline.service.settingCenter.user.IPageCodeService;
import com.softline.service.settingCenter.user.IPageRegisterService;
import com.softline.service.settingCenter.user.ISysRegisterService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/page/code")
public class PageCodeController {
	
	@Resource(name = "pageRegisterService")
	private IPageRegisterService pageRegisterService;
	
	@Resource(name = "sysRegisterService")
	private ISysRegisterService sysRegisterService;
	
	@Resource(name = "pageCodeService")
	private IPageCodeService pageCodeService;
	
	@Resource(name = "modelRegisterService")
	private IModelRegisterService modelRegisterService;
	
	@RequestMapping("_query")
	public String queryPageCode(PortalHhPagecode pageCode, String qCodeName, String qModelId, String qPageId, Map<String, Object> map, HttpServletRequest request) {
		if (pageCode.getCodeName() != null) {
			map.put("save", "success");
		}
		map.put("qCodeName", qCodeName);
		map.put("qModelId", qModelId);
		map.put("qPageId", qPageId);
		//获取已注册模板下拉列表
		List<HhModelRegister> modelRegistedList = modelRegisterService.getRegistedList();
		map.put("modelRegistedList", modelRegistedList);
		//获取已注册页面下拉列表
		List<HhPageregister> pageRegistedList = new ArrayList<HhPageregister>();
		if(qModelId != null && !"".equals(qModelId))
			pageRegistedList = pageRegisterService.getPagesByModelId(Integer.parseInt(qModelId));
		else
			pageRegistedList = pageRegisterService.getRegistedList();
		map.put("pageRegistedList", pageRegistedList);
		
		String curPageNum = request.getParameter("pageNum");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        
        MsgPage msgPage = pageCodeService.getPageMsgCodeList(qCodeName, qModelId, qPageId, pageNum, 10);
		List<PortalHhPagecode> pageCodeList = msgPage.getList();
		map.put("pageCodeList", pageCodeList);
		map.put("msgPage", msgPage);
		return "settingCenter/user/pageCodeList";
	}
	
	@RequestMapping("_addOrModify")
	public String addOrModifyPageCode(String pageNum, String qCodeName, String qModelId, String qPageId, PortalHhPagecode register, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("pageNum", pageNum);
		map.put("qCodeName", qCodeName);
		map.put("qModelId", qModelId);
		map.put("qPageId", qPageId);
		//获取已注册系统下拉列表
		List<HhSysRegister> sysRegistedList = sysRegisterService.getRegistedList();
		map.put("sysRegistedList", sysRegistedList);
		map.put("op", op);
		//id存在时，为编辑状态，将id对应的实体进行回显
		if (register.getId() != null && !(register.getId().equals(""))) {
			PortalHhPagecode theRegister = new PortalHhPagecode();
			theRegister = pageCodeService.getThePageCode(register.getId());
			map.put("theRegister", theRegister);
			//获取该系统对应的已注册模板下拉列表
			List<HhModelRegister> modelRegistedList = modelRegisterService.getModelsBySysId(theRegister.getSysId());
			map.put("modelRegistedList", modelRegistedList);
			//获取该模板下对应的已注册页面下拉列表
			List<HhPageregister> pageRegistedList = pageRegisterService.getPagesByModelId(theRegister.getModelId());
			map.put("pageRegistedList", pageRegistedList);
		}
		return "/settingCenter/user/pageCodeAddOrModify";
	}
	
	@RequestMapping("_saveOrUpdate")
	@ResponseBody
	public String saveOrUpdatePageRegister(PortalHhPagecode register, HttpServletRequest request) {
		register.setIsDel(0);
		pageCodeService.saveOrUpdatePageCode(register);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("_del")
	public String delPageRegister(String id) {
		String msg = pageCodeService.deletePageCode(Integer.parseInt(id));
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("_getPages")
	public List<HhPageregister> getPages(String modelId) {
		List<HhPageregister> pageRegisterList = new ArrayList<HhPageregister>();
		if(!"".equals(modelId))
			pageRegisterList = pageRegisterService.getPagesByModelId(Integer.parseInt(modelId));
		else
			//获取已注册页面下拉列表
			pageRegisterList = pageRegisterService.getRegistedList();
		return pageRegisterList;
	}
	
	@ResponseBody
	@RequestMapping("_checkPagecodeNum")
	public String checkPagecodeNum(String pagecodeNum, Integer id) {
		Integer num = pageCodeService.checkPagecodeNum(pagecodeNum, id);
		return "{num:" + num + "}";
	}
	
	
//	功能注册
	@RequestMapping("PageCodePersonQuery")
	public String PageCodPersonquery(Integer modelId, String vcName, String vcAccount, Map<String, Object> map, HttpServletRequest request) {
		//获取模块信息
		PortalHhPagecode registerModel = pageCodeService.getThePageCode(modelId);
		map.put("registerModel", registerModel);
		String curPageNum = request.getParameter("pageNums");
        if (!Common.notEmpty(curPageNum)) { 
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        
        MsgPage msgPage = pageCodeService.getPageCodePersonList(modelId,vcName, vcAccount, pageNum, 10);
		List<Object[]> PageCodePersonList = msgPage.getList();
		map.put("PageCodePersonList", PageCodePersonList);
		map.put("msgPage", msgPage);
		map.put("modelId", modelId);
		map.put("vcName", vcName);
		map.put("vcAccount", vcAccount);
		map.put("searchurl", "/bim_portal/page/code/PageCodePersonQuery");
		return "settingCenter/user/PageCodePersonList";
	}
	
	@RequestMapping(value="/pageCodePersonExport")
	public void pageCodeExportExporttest(Integer modelId, String vcName, String vcAccount,
	HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) 
	throws IOException, InvalidFormatException {
	 PortalHhPagecode registerModel = pageCodeService.getThePageCode(modelId);
//	        查询123
	        XSSFWorkbook workBook1 = new XSSFWorkbook();
//			
	        workBook1=pageCodeService.getPageCodePersonListExport(modelId,vcName, vcAccount);
				     
				     // 清空response档案查询
				        response.reset();
				        String _name = registerModel.getModelName()+"页面已分配人员导出列表.xlsx";
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
