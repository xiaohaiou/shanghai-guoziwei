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
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.entity.settingCenter.HhPageregister;
import com.softline.entity.settingCenter.HhSysRegister;
import com.softline.service.settingCenter.user.IModelRegisterService;
import com.softline.service.settingCenter.user.ISysRegisterService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/model/register")
public class ModelRegisterController {
	@Resource(name = "modelRegisterService")
	private IModelRegisterService modelRegisterService;
	@Resource(name = "sysRegisterService")
	private ISysRegisterService sysRegisterService;
	
	@RequestMapping("_query")
	public String queryModelRegister(HhModelRegister register, String qModelNum, String qModelName, String qSysId, Map<String, Object> map, HttpServletRequest request) {
		if (register.getModelName() != null) {
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
		List<HhPageregister> modelRegisterList = msgPage.getList();
		map.put("modelRegisterList", modelRegisterList);
		map.put("msgPage", msgPage);
		return "/settingCenter/user/modelRegisterList";
	}
	
	@RequestMapping("_addOrModify")
	public String addOrModifyModelRegister(String pageNum, String qModelNum, String qModelName,String qSysId, HhModelRegister register, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("pageNum", pageNum);
		map.put("qModelNum", qModelNum);
		map.put("qModelName", qModelName);
		map.put("qSysId", qSysId);
		//获取已注册系统下拉列表
		List<HhSysRegister> sysRegistedList = sysRegisterService.getRegistedList();
		map.put("sysRegistedList", sysRegistedList);
		map.put("op", op);
		//id存在时，为编辑状态，将id对应的实体进行回显
		if (register.getId() != null && !(register.getId().equals(""))) {
			HhModelRegister theRegister = new HhModelRegister();
			theRegister = modelRegisterService.getTheModelRegister(register.getId());
			map.put("theRegister", theRegister);
		}
		return "/settingCenter/user/modelRegisterAddOrModify";
	}
	
	@RequestMapping("_saveOrUpdate")
	@ResponseBody
	public String saveOrUpdateModelRegister(HhModelRegister register, HttpServletRequest request) {
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
		modelRegisterService.saveOrUpdateModelRegister(register);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("_del")
	public String delPageRegister(String id) {
		String msg = modelRegisterService.deleteModelRegister(Integer.parseInt(id));
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("_checkModelNum")
	public String checkModelNum(String modelNum, Integer id) {
		Integer num = modelRegisterService.checkModelNum(modelNum, id);
		return "{num:" + num + "}";
	}
	
	@RequestMapping("modelPersonQuery")
	public String modelPersonquery(Integer modelId, String vcName, String vcAccount, Map<String, Object> map, HttpServletRequest request) {
		//获取模块信息
		HhModelRegister registerModel = modelRegisterService.getTheModelRegister(modelId);
		map.put("registerModel", registerModel);
		String curPageNum = request.getParameter("pageNums");
        if (!Common.notEmpty(curPageNum)) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        
        MsgPage msgPage = modelRegisterService.getModelMsgPersonList(modelId,vcName, vcAccount, pageNum, 10);
		List<Object[]> modelPersonList = msgPage.getList();
		map.put("modelPersonList", modelPersonList);
		map.put("msgPage", msgPage);
		map.put("modelId", modelId);
		map.put("vcName", vcName);
		map.put("vcAccount", vcAccount);
		map.put("searchurl", "/bim_portal/model/register/modelPersonQuery");
		return "/settingCenter/user/modelPersonList";
	}
	
	
//	人员导出

	 @RequestMapping(value="/modelPersonExport")
		public void queryExportExporttest(Integer modelId, String vcName, String vcAccount,
		HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) 
		throws IOException, InvalidFormatException {
		 HhModelRegister registerModel = modelRegisterService.getTheModelRegister(modelId);
//		        查询123
		        XSSFWorkbook workBook1 = new XSSFWorkbook();
//				
		        workBook1=modelRegisterService.getModelMsgPersonListExport(modelId,vcName, vcAccount);
					     
					     // 清空response档案查询
					        response.reset();
					        String _name = registerModel.getModelName()+"模块已分配人员导出列表.xlsx";
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
