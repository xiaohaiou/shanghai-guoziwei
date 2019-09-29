package com.softline.controller.settingCenter.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhSysRegister;
import com.softline.service.settingCenter.user.ISysRegisterService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/sys/register")
public class SysRegisterController {
	@Resource(name = "sysRegisterService")
	private ISysRegisterService sysRegisterService;
	
	@RequestMapping("_query")
	public String querySysRegister(HhSysRegister register, String qSysNum, String qSysName, Map<String, Object> map, HttpServletRequest request) {
		if (register.getSysName() != null) {
			map.put("save", "success");
		}
		map.put("qSysNum", qSysNum);
		map.put("qSysName", qSysName);
		String curPageNum = request.getParameter("pageNum");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        
        MsgPage msgPage = sysRegisterService.getSysMsgRegisterList(qSysNum, qSysName, pageNum, 10);
		List<HhSysRegister> sysRegisterList = msgPage.getList();
		map.put("sysRegisterList", sysRegisterList);
		map.put("msgPage", msgPage);
		return "settingCenter/user/sysRegisterList";
	}
	
	@RequestMapping("_addOrModify")
	public String addOrModifySysRegister(String qSysNum, String qSysName, String pageNum, HhSysRegister register, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("qSysNum", qSysNum);
		map.put("qSysName", qSysName);
		map.put("pageNum", pageNum);
		map.put("op", op);
		//id存在时，为编辑状态，将id对应的实体进行回显
		if (register.getId() != null && !(register.getId().equals(""))) {
			HhSysRegister theRegister = new HhSysRegister();
			theRegister = sysRegisterService.getTheSysRegister(register.getId());
			map.put("theRegister", theRegister);
		}
		return "settingCenter/user/sysRegisterAddOrModify";
	}
	
	@RequestMapping("_saveOrUpdate")
	@ResponseBody
	public String saveOrUpdateSysRegister(HhSysRegister register, HttpServletRequest request) {
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
		sysRegisterService.saveOrUpdateSysRegister(register);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("_del")
	public String delSysRegister(String id) {
		String msg = sysRegisterService.delSysRegister(Integer.parseInt(id));
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("_checkSysNum")
	public String checkSysNum(String sysNum, Integer id) {
		Integer num = sysRegisterService.checkSysNum(sysNum, id);
		return "{num:" + num + "}";
	}
}
