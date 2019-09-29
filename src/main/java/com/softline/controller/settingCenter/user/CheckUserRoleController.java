package com.softline.controller.settingCenter.user;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softline.entity.settingCenter.HhUsersrole;
import com.softline.entityTemp.VUserRoles;
import com.softline.service.settingCenter.user.IGroupService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("check/userRole")
public class CheckUserRoleController {
	
	@Resource(name = "groupService")
	private IGroupService groupService;
	
	@RequestMapping("_query")
	public String query(String vcAccount,String qUserName,Integer cFlag, Map<String, Object> map, HttpServletRequest request) {
		String curPageNum = request.getParameter("pageNum");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = groupService.getUserRoleList(vcAccount,qUserName, pageNum, 10,cFlag);
		map.put("msgPage", msgPage);
		List<HhUsersrole> users = msgPage.getList();
		map.put("users", users);
		map.put("qUserName", qUserName);
		map.put("vcAccount", vcAccount);		
		map.put("cFlag", cFlag);	
		return "/settingCenter/user/checkUserRoleList";
	}
	
	@RequestMapping("_export")
	public void export(String vcAccount, String qUserName,Integer cFlag,
			Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<VUserRoles> results = groupService.getUserRoleList(vcAccount,
				qUserName,cFlag);
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFCell cell = null;
		XSSFSheet sheet = workBook.createSheet("sheet1");

		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("人员名称");
		row.createCell(1).setCellValue("用户账号");
		row.createCell(2).setCellValue("所属部门");
		row.createCell(3).setCellValue("角色");
		
		for(int i = 0; i < results.size(); i++){
			row =  sheet.createRow(i+1);
			VUserRoles vUserRole = results.get(i);
			row.createCell(0).setCellValue(vUserRole.getVcName());
			row.createCell(1).setCellValue(vUserRole.getVcAccount());
			row.createCell(2).setCellValue(vUserRole.getVcFullName());
			row.createCell(3).setCellValue(vUserRole.getRoleNames());
		}
		// 清空response
		response.reset();
		String _name = "用户角色.xlsx";
		OutputStream toClient = new BufferedOutputStream(
				response.getOutputStream());
		String codedfilename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
				&& -1 != agent.indexOf("Trident")) {// ie
			String name = java.net.URLEncoder.encode(_name, "UTF8");
			codedfilename = name;
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
			codedfilename = new String(_name.getBytes("UTF-8"), "iso-8859-1");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ codedfilename);
		workBook.write(toClient);
		toClient.flush();
		toClient.close();
	}
}
