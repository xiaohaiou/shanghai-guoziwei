package com.softline.controller.settingCenter.user;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.softline.common.Base;
import com.softline.common.CompanyTree;
import com.softline.service.select.ISelectUserService;
import com.softline.service.settingCenter.user.IUserHrAndFinanceCompanyService;
import com.softline.service.system.IWorkWebService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/user/hrAndFinanceCompany")
public class UserHrAndFinaceCompanyController{
	
	@Resource(name="userHrAndFinanceCompanyService")
	private IUserHrAndFinanceCompanyService userHrAndFinanceCompanyService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "workWebService")
	private IWorkWebService workWebService;
	
	@RequestMapping("getUserHrCompanys")
	public String getUserHrCompanys(Map<String, Object> map,String userName,String vcAccount,Integer isHavingSuper,String vcOrganId,String compName,HttpServletRequest request){
		
		String curPageNum = request.getParameter("pageNum");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        
        Integer pageNum = Integer.valueOf(curPageNum);
        if(StringUtils.isBlank(vcOrganId)){//默认选取海航物流
        	vcOrganId = Base.HRTopPrefix;
        	compName = "海航物流";
        }
        String orgs = vcOrganId.replace("-", ",").substring(0,vcOrganId.length()-1);
        if(isHavingSuper != null && isHavingSuper == 0){
        	String [] tempa = orgs.split(",");
        	orgs = tempa[tempa.length-1];
        }
        MsgPage msgPage = userHrAndFinanceCompanyService.getUserCompanyList(userName, vcAccount, orgs, pageNum, 10);
        map.put("msgPage", msgPage);
        
        //数据反绑
        map.put("userName", userName);
        map.put("vcAccount", vcAccount);
        map.put("vcOrganId", vcOrganId);
        map.put("compName", compName);
        map.put("isHavingSuper", isHavingSuper);
        
        //财务树初始化
        CompanyTree leveltree = selectUserService.getDepTree();
		String leveltreeId = leveltree.getId();
		String allCompanyTree = "[";
		allCompanyTree += "{id:'"+leveltreeId+"',pId:'0',name:'"+leveltree.getText()+"'},";
		String shtml = getTreeHTML(leveltree);
		allCompanyTree += shtml;
		allCompanyTree += "]";
		map.put("allCompanyTree", allCompanyTree);
		
		map.put("url", request.getContextPath() + "/user/hrAndFinanceCompany/getUserHrCompanys");
        
		return "/settingCenter/user/userHrCompanyList";
	}
	
	private String getTreeHTML(CompanyTree cTree){
		StringBuffer buffer = new StringBuffer();
		List<CompanyTree> sonList = cTree.getChildren();
		if(sonList.size()>0){
			for(int i=0;i<sonList.size();i++){
				CompanyTree son = sonList.get(i);
				String sonId = son.getId();
				String pId = cTree.getId();
				buffer.append("{id:'"+sonId+"',pId:'"+pId+"',name:'"+son.getText()+"'},");
				buffer.append(getTreeHTML(son));
			}
		}
		return buffer.toString();
	}
	
	@RequestMapping("getUserFinanceCompanys")
	public String getUserFinanceCompanys(Map<String, Object> map,String userName,String vcAccount,String vcOrganId,Integer isHavingSuper,String compName, HttpServletRequest request){
		
		String curPageNum = request.getParameter("pageNum");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        
        Integer pageNum = Integer.valueOf(curPageNum);
        if(StringUtils.isBlank(vcOrganId)){//默认选取海航物流
        	vcOrganId = "0-1-d4985136e97d11e7968906a2160000ae-"; //海航物流
        	compName = "海航物流";
        }
        String tempVcOrganId = "'" + vcOrganId.substring(0,vcOrganId.length()-1) + "'";
        String orgs = tempVcOrganId.replace("-", "','");
        if(isHavingSuper != null && isHavingSuper == 0){
        	String [] tempa = orgs.split(",");
        	orgs = tempa[tempa.length-1];
        }
        MsgPage msgPage = userHrAndFinanceCompanyService.getUserFinanceCompanyList(userName, vcAccount, orgs, pageNum, 10);
        map.put("msgPage", msgPage);
        
        //数据反绑
        map.put("userName", userName);
        map.put("vcAccount", vcAccount);
        map.put("vcOrganId", vcOrganId);
        map.put("compName", compName);
        map.put("isHavingSuper", isHavingSuper);
        //财务树初始化
//        WorkWebServiceService service = new WorkWebServiceService();
//		IWorkWebService iw = service.getPort(IWorkWebService.class);
		String allCompanyTree = workWebService.getAllFinanceTree();
		map.put("allCompanyTree", allCompanyTree);
		
		map.put("url", request.getContextPath() + "/user/hrAndFinanceCompany/getUserFinanceCompanys");
		
		return "/settingCenter/user/userFinanceCompanyList";
	}
	
	@RequestMapping("_export1")
	public void export1(String userName,String vcAccount,String vcOrganId,String compName,Integer isHavingSuper,
			Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 if(StringUtils.isBlank(vcOrganId)){//默认选取海航物流
        	vcOrganId = Base.HRTopPrefix;
        	compName = "海航物流";
        }
        String orgs = vcOrganId.replace("-", ",").substring(0,vcOrganId.length()-1);
        if(isHavingSuper != null && isHavingSuper == 0){
        	String [] tempa = orgs.split(",");
        	orgs = tempa[tempa.length-1];
        }
		List<Object[]> results = userHrAndFinanceCompanyService.getUserCompanyList(userName, vcAccount, orgs);
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFCell cell = null;
		XSSFSheet sheet = workBook.createSheet("sheet1");

		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("人员名称");
		row.createCell(1).setCellValue("用户账号");
		row.createCell(2).setCellValue("所属部门");
		row.createCell(3).setCellValue("权限公司");
		
		for(int i = 0; i < results.size(); i++){
			row =  sheet.createRow(i+1);
			Object[] obj = results.get(i);
			row.createCell(0).setCellValue(obj[4]==null?"":obj[4].toString());
			row.createCell(1).setCellValue(obj[3]==null?"":obj[3].toString());
			row.createCell(2).setCellValue(obj[5]==null?"":obj[5].toString());
			row.createCell(3).setCellValue(compName);
		}
		// 清空response
		response.reset();
		String _name = "hr数据权限用户.xlsx";
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
	
	@RequestMapping("_export2")
	public void export2(String userName,String vcAccount,String vcOrganId,String compName,Integer isHavingSuper,
			Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if(StringUtils.isBlank(vcOrganId)){//默认选取海航物流
        	vcOrganId = "0-1-d4985136e97d11e7968906a2160000ae-"; //海航物流
        	compName = "海航物流";
        }
        String tempVcOrganId = "'" + vcOrganId.substring(0,vcOrganId.length()-1) + "'";
        String orgs = tempVcOrganId.replace("-", "','");
        if(isHavingSuper != null && isHavingSuper == 0){
        	String [] tempa = orgs.split(",");
        	orgs = tempa[tempa.length-1];
        }
		List<Object[]> results = userHrAndFinanceCompanyService.getUserFinanceCompanyList(userName, vcAccount, orgs);
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFCell cell = null;
		XSSFSheet sheet = workBook.createSheet("sheet1");

		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("人员名称");
		row.createCell(1).setCellValue("用户账号");
		row.createCell(2).setCellValue("所属部门");
		row.createCell(3).setCellValue("权限公司");
		
		for(int i = 0; i < results.size(); i++){
			row =  sheet.createRow(i+1);
			Object[] obj = results.get(i);
			row.createCell(0).setCellValue(obj[4]==null?"":obj[4].toString());
			row.createCell(1).setCellValue(obj[3]==null?"":obj[3].toString());
			row.createCell(2).setCellValue(obj[5]==null?"":obj[5].toString());
			row.createCell(3).setCellValue(compName);
		}
		
		// 清空response
		response.reset();
		String _name = "财务数据权限用户.xlsx";
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
