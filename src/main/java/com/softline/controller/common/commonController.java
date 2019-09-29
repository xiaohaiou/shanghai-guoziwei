package com.softline.controller.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.softline.entity.HhUsers;
import com.softline.service.select.ISelectUserService;

@Controller
@RequestMapping("/common")
public class commonController {
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;

	@RequestMapping("download")
	public ResponseEntity<byte[]> download(String _url, String _name,
			HttpServletRequest request) throws IOException {
		String nodepath = this.getClass().getClassLoader().getResource("/")
				.getPath();
		// 项目的根目录路径
		String filePath = File.separator
				+ nodepath.substring(1, nodepath.length() - 16);
		String path = filePath + _url;
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();

		String codedfilename = null;
		try {
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
					&& -1 != agent.indexOf("Trident")) {// ie

				String name = java.net.URLEncoder.encode(_name, "UTF8");

				codedfilename = name;
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

				codedfilename = new String(_name.getBytes("UTF-8"),
						"iso-8859-1");
			}
//			String fileName = new String(_name.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
			headers.setContentDispositionFormData("attachment", codedfilename);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
	}
	
	@RequestMapping("/searchPerson")
	public String searchPerson(HttpServletRequest request){
		return "/system/PersonSearch";
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
					hql.append("<li class=\"line-list\"><label class=\"checkboxList checkbox_checked\" for='" + item.getVcEmployeeId() + "'>" +
						      "<span class=\"icon\"></span><input type=\"checkbox\" style=\" height: 13px;\" id='" + item.getVcEmployeeId() + "' checked value='"+ item.getVcEmployeeId() +"' vcName='"+ item.getVcName() +"'>" + item.getVcName() + "-----" + item.getVcAccount() + "-----" + item.getVcFullName() + "</label></li>");
				}else {
					hql.append("<li class=\"line-list\"><label class=\"checkboxList\" for='" + item.getVcEmployeeId() + "'>" +
						      "<span class=\"icon\"></span><input type=\"checkbox\" style=\" height: 13px;\" id='" + item.getVcEmployeeId() + "' value='"+ item.getVcEmployeeId() +"' vcName='"+ item.getVcName() +"'>" + item.getVcName() + "-----" + item.getVcAccount() + "-----" + item.getVcFullName() + "</label></li>");
				}
			}
			hql.append(" </ul>");
			jsonString = JSON.toJSONString(hql.toString());
		}
		response.getWriter().write(jsonString);
	}
	
	
	/**
	 * 公共导出方法
	 * JasonTang
	 * @param path
	 * @param name
	 * @param response
	 */
	public static void doDownLoad(String path, String name, HttpServletResponse response) {
		try {
			response.reset();
			response.setHeader("Content-disposition", "attachment;success=true;filename =" + URLEncoder.encode(name, "UTF-8"));
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			OutputStream fos = null;
			InputStream fis = null;
			File uploadFile = new File(path);
			fis = new FileInputStream(uploadFile);
			bis = new BufferedInputStream(fis);
			fos = response.getOutputStream();
			bos = new BufferedOutputStream(fos);
			// 弹出下载对话框
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.flush();
			fis.close();
			bis.close();
			fos.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param wb
	 * @param name
	 * @param response
	 * @throws IOException 
	 * @since 2017-09-28
	 */
	public static void doDownLoad(Workbook wb, String name,
			HttpServletResponse response) throws IOException {
		response.reset();
		response.setHeader(
				"Content-disposition",
				"attachment;success=true;filename ="
						+ URLEncoder.encode(name, "UTF-8"));
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		fos = response.getOutputStream();
		bos = new BufferedOutputStream(fos);

		wb.write(bos);
		bos.flush();

		fos.close();
		bos.close();
	}

}
