package com.softline.controller.bimr;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrContractModel;
import com.softline.service.bimr.IContractModelService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * 合同范本Controller
 * @author pengguolin
 */
@Controller
@RequestMapping("/bimr/contractModel")
public class ContractModelController {
	@Resource(name = "contractModelService") private IContractModelService contractModelService;
	@Resource(name = "systemService") private ISystemService systemService;
	@Resource(name = "commonService") private ICommonService commonService;
	@Resource(name = "selectUserService") private ISelectUserService selectUserService;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("/list")
	public String queryList(BimrContractModel entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/bimr/contractModel";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = contractModelService.getContractModelList(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/bimr/contractModel/list");
	    map.put("entity", entity);
		return "/bimr/contractModel/list";
	}
	
	@RequestMapping("/lookList")
	public String lookList(BimrContractModel entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		String mapurl = request.getContextPath()+ "/bimr/contractModel";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = contractModelService.getContractModelList(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/bimr/contractModel/lookList");
	    map.put("entity", entity);
		return "/bimr/contractModel/lookList";
	}
	
	@RequestMapping("/addOrModify")
	public String addOrModify(BimrContractModel entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
	    BimrContractModel bimrContractModel;
		if(entity.getId() != null) {
			bimrContractModel = contractModelService.getBimrContractModel(entity);
		}else {
			bimrContractModel = new BimrContractModel();
		}
		map.put("bimrContractModel", bimrContractModel);
		HhFile hfile1 = commonService.getFileByEnIdAndType(entity.getId(), Base.CONTRACTMODEL_PDF);
		if (hfile1 != null) {
			map.put("file_Path1", hfile1.getFilePath());
			map.put("file_Name1", hfile1.getFileName());
		} else {
			map.put("file_Path1", "");
			map.put("file_Name1", "");
		}
		HhFile hfile2 = commonService.getFileByEnIdAndType(entity.getId(), Base.CONTRACTMODEL_BLANK);
		if (hfile2 != null) {
			map.put("file_Path2", hfile2.getFilePath());
			map.put("file_Name2", hfile2.getFileName());
		} else {
			map.put("file_Path2", "");
			map.put("file_Name2", "");
		}
		return "/bimr/contractModel/addOrModify";
	}
	
	@RequestMapping("/view")
	public String view(BimrContractModel entity, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		BimrContractModel bimrContractModel = contractModelService.getBimrContractModel(entity);
		map.put("bimrContractModel", bimrContractModel);
		HhFile hfile1 = commonService.getFileByEnIdAndType(entity.getId(), Base.CONTRACTMODEL_PDF);
		if (hfile1 != null) {
			map.put("file_Path1", hfile1.getFilePath());
			map.put("file_Name1", hfile1.getFileName());
		} else {
			map.put("file_Path1", "");
			map.put("file_Name1", "");
		}
		HhFile hfile2 = commonService.getFileByEnIdAndType(entity.getId(), Base.CONTRACTMODEL_BLANK);
		if (hfile2 != null) {
			map.put("file_Path2", hfile2.getFilePath());
			map.put("file_Name2", hfile2.getFileName());
		} else {
			map.put("file_Path2", "");
			map.put("file_Name2", "");
		}
		return "/bimr/contractModel/view";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate")
	public String saveOrUpdate(@ModelAttribute("bimrContractModel") BimrContractModel entity, HttpServletRequest request,
			@RequestParam(value="file1",required=false) MultipartFile lecturerEnclosure,
			@RequestParam(value="file2",required=false) MultipartFile listOf) throws IOException, ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			String package_path = DES.CONTRACTMODEL_PATH;
			contractModelService.save(entity, user, lecturerEnclosure, listOf, package_path);
		}else {
			entity.setIsDel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			String package_path = DES.CONTRACTMODEL_PATH;
			contractModelService.update(entity, user, lecturerEnclosure, listOf, package_path);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndReported(@ModelAttribute("bimrContractModel") BimrContractModel entity, HttpServletRequest request) throws IOException, ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			contractModelService.save(entity);
		}else {
			entity.setIsDel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			contractModelService.update(entity);
		}
		return "success";
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String deleteContract(String id, Map<String, Object> map, HttpServletRequest request) {
		String result = "";
		if (Common.notEmpty(id)) {
			BimrContractModel bimrContractModel = contractModelService.getBimrContractModelById(Integer.parseInt(id));
			if (bimrContractModel != null) {
				bimrContractModel.setIsDel(1);
				contractModelService.update(bimrContractModel);
				result = "success";
			} else {
				result = "false";
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(String id, String op){
		String result = "";
		if (Common.notEmpty(id)) {
			BimrContractModel entity = contractModelService.getBimrContractModelById(Integer.parseInt(id));
			if(entity == null){
				result = "false";
			} else {
				if (Common.notEmpty(op)) {
					if ("check".equals(op)) {//查看判断
						result = "success";
					} else if ("examine".equals(op)) {//审核判断
						result = "success";
					} else {
							result = "success";
					}
				} else {
					result = "false";
				} 
			}
		}
		return result;
	}

}
