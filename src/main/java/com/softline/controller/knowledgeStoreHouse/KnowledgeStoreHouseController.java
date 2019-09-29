package com.softline.controller.knowledgeStoreHouse;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.KnowledgeStoreHouse;
import com.softline.entity.RiskControlContract;
import com.softline.service.knowledgeStoreHouse.IKnowledgeStoreHouseService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/knowledgeStoreHouse")
public class KnowledgeStoreHouseController {

	@Resource(name = "knowledgeStoreHouseService")
	private IKnowledgeStoreHouseService knowledgeStoreHouseService;

	@Resource(name = "baseService")
	private IBaseService baseService;

	@Resource(name = "commonService")
	private ICommonService commonService;

	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping(value = "/index")
	public String knowledgeBaseIndex(KnowledgeStoreHouse entity, Map<String, Object> map, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		map.put("entity", entity);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = knowledgeStoreHouseService.getKnowledgeStoreHouses(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/knowledgeStoreHouse/searchKnowledgeData");
		return "/knowledgeStoreHouse/knowledgeStoreHouseIndex";
	}

	@RequestMapping(value = "/moduleList")
	public String moduleList(KnowledgeStoreHouse entity, Map<String, Object> map, HttpServletRequest request, String type) {
		map.put("type", type);
		String mapurl = request.getContextPath()+ "/knowledgeStoreHouse";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str = (String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        if (Common.notEmpty(type)) {
        	if("cw".equals(type)){
            	entity.setModuleId(70064);
            } else if ("xzbg".equals(type)) {
            	entity.setModuleId(70065);
    		} else if ("rlzy".equals(type)) {
            	entity.setModuleId(70066);
    		} else if ("cgsj".equals(type)) {
            	entity.setModuleId(70067);
    		} else if ("szsj".equals(type)) {
            	entity.setModuleId(70068);
    		} else if ("fxkz".equals(type)) {
            	entity.setModuleId(70069);
    		} else if ("zsk".equals(type)) {
            	entity.setModuleId(70070);
    		}
		}
        MsgPage msgPage = knowledgeStoreHouseService.getKnowledgeStoreHouses(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/knowledgeStoreHouse/moduleList");
	    map.put("entity", entity);
	    List<HhBase> moduleId = baseService.getBases(Base.KNOWLEDGESTOREHOUSE_TYPE);
	    map.put("moduleId",moduleId);
		return "/knowledgeStoreHouse/knowledgeStoreHouseModuleList";
	}
	
	@RequestMapping(value = "/list")
	public String pagelist(KnowledgeStoreHouse entity, Map<String, Object> map, HttpServletRequest request, String type) {
		if (Common.notEmpty(type)) {
			if("cw".equals(type)){
            	entity.setModuleId(70064);
            } else if ("xzbg".equals(type)) {
            	entity.setModuleId(70065);
    		} else if ("rlzy".equals(type)) {
            	entity.setModuleId(70066);
    		} else if ("cgsj".equals(type)) {
            	entity.setModuleId(70067);
    		} else if ("szsj".equals(type)) {
            	entity.setModuleId(70068);
    		} else if ("fxkz".equals(type)) {
            	entity.setModuleId(70069);
    		} else if ("zsk".equals(type)) {
            	entity.setModuleId(70070);
    		}
		}
		map.put("type", type);
		String mapurl = request.getContextPath()+ "/knowledgeStoreHouse";
		map.put("mapurl", mapurl);
		HttpSession session = request.getSession();
		String str = (String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        if (entity.getId() != null) {
        	HhFile hfile = commonService.getFileByEnIdAndType(entity.getId(), Base.KNOWLEDGESTOREHOUSE_TYPE);
        	entity.setHhFile(hfile);
		}
        MsgPage msgPage = knowledgeStoreHouseService.getKnowledgeStoreHouses(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/knowledgeStoreHouse/list");
	    map.put("entity", entity);
	    List<HhBase> moduleId = baseService.getBases(Base.KNOWLEDGESTOREHOUSE_TYPE);
	    map.put("moduleId",moduleId);
		return "/knowledgeStoreHouse/knowledgeStoreHouseList";
	}
	
	@RequestMapping("/addOrModify")
	public String addOrModifyRepository(KnowledgeStoreHouse entity, Map<String, Object> map, HttpServletRequest request, String op, String type) throws IOException {
		map.put("op", op);
		map.put("type", type);
		HttpSession session = request.getSession();
		String str = (String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
		HhBase hhBase = new HhBase();
		List<HhBase> moduleId = new ArrayList<HhBase>();
		//设置模块值
		if (Common.notEmpty(type)) {
			if("cw".equals(type)){
				hhBase.setId(70064);
    			hhBase.setDescription("财务");
            } else if ("xzbg".equals(type)) {
            	hhBase.setId(70065);
    			hhBase.setDescription("行政办公");
    		} else if ("rlzy".equals(type)) {
    			hhBase.setId(70066);
    			hhBase.setDescription("人力资源");
    		} else if ("cgsj".equals(type)) {
    			hhBase.setId(70067);
    			hhBase.setDescription("采购");
    		} else if ("szsj".equals(type)) {
    			hhBase.setId(70068);
    			hhBase.setDescription("社责数据");
    		} else if ("fxkz".equals(type)) {
    			hhBase.setId(70069);
    			hhBase.setDescription("风险控制");
    		}
		}
		moduleId.add(hhBase);
		map.put("moduleId",moduleId);
	    KnowledgeStoreHouse knowledgeStoreHouse;
		if(entity.getId() != null) {
			knowledgeStoreHouse = knowledgeStoreHouseService.getKnowledgeStoreHouse(entity);
			HhFile hfile = commonService.getFileByEnIdAndType(entity.getId(), Base.KNOWLEDGESTOREHOUSE_TYPE);
			if (hfile != null) {
				map.put("file_Path", hfile.getFilePath());
				map.put("file_Name", hfile.getFileName());
			} else {
				map.put("file_Path", "");
				map.put("file_Name", "");
			}
		}else {
			knowledgeStoreHouse = new KnowledgeStoreHouse();
			if (Common.notEmpty(type)) {
				if("cw".equals(type)){
					knowledgeStoreHouse.setModuleId(70064);
	            } else if ("xzbg".equals(type)) {
	            	knowledgeStoreHouse.setModuleId(70065);
	    		} else if ("rlzy".equals(type)) {
	    			knowledgeStoreHouse.setModuleId(70066);
	    		} else if ("cgsj".equals(type)) {
	    			knowledgeStoreHouse.setModuleId(70067);
	    		} else if ("szsj".equals(type)) {
	    			knowledgeStoreHouse.setModuleId(70068);
	    		} else if ("fxkz".equals(type)) {
	    			knowledgeStoreHouse.setModuleId(70069);
	    		} else if ("zsk".equals(type)) {
	    			knowledgeStoreHouse.setModuleId(70070);
	    		}
			}
		}
		map.put("knowledgeStoreHouse", knowledgeStoreHouse);
		return "/knowledgeStoreHouse/knowledgeStoreHouseAddOrModify";
	}
	
	@RequestMapping("/view")
	public String viewRepository(KnowledgeStoreHouse entity, Map<String, Object> map, HttpServletRequest request, String id) {
		KnowledgeStoreHouse knowledgeStoreHouse = knowledgeStoreHouseService.getKnowledgeStoreHouse(Integer.parseInt(id));
		map.put("knowledgeStoreHouse", knowledgeStoreHouse);
		HhFile hfile = commonService.getFileByEnIdAndType(entity.getId(), Base.KNOWLEDGESTOREHOUSE_TYPE);
		if (hfile != null) {
			map.put("file_Path", hfile.getFilePath());
			map.put("file_Name", hfile.getFileName());
		} else {
			map.put("file_Path", "");
			map.put("file_Name", "");
		}
		return "/knowledgeStoreHouse/knowledgeStoreHouseView";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate")
	public String saveOrUpdateRepository(@ModelAttribute("knowledgeStoreHouse")KnowledgeStoreHouse entity, HttpServletRequest request,
			@RequestParam(value="pjFile",required=false) MultipartFile multFile) throws ParseException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setIsDel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			String package_path = DES.KNOWLEDGESTOREHOUSE_PATH;
			knowledgeStoreHouseService.save(entity, user, multFile, package_path);
			result = "success";
		} else {
			KnowledgeStoreHouse knowledgeStoreHouse = knowledgeStoreHouseService.getKnowledgeStoreHouse(entity.getId());
			if (knowledgeStoreHouse == null) {
				result = "flase";
			} else {
				entity.setIsDel(0);
				entity.setLastModifyDate(df.format(new Date()));
				entity.setLastModifyPersonId(user.getVcEmployeeId());
				entity.setLastModifyPersonName(user.getVcName());
				knowledgeStoreHouseService.updateKnowledgeStoreHouse(entity);
				if (!multFile.isEmpty()) {
					String package_path = DES.KNOWLEDGESTOREHOUSE_PATH;
					HhFile _file = commonService.getFileByEnIdAndType(entity.getId(), Base.KNOWLEDGESTOREHOUSE_TYPE);
					if (_file != null) {
						File file = new File(_file.getFilePath());
						file.delete();
						updateFile(_file, multFile, package_path);
					}else{
						commonService.saveFile(multFile, entity.getId(), Base.KNOWLEDGESTOREHOUSE_TYPE, package_path);
					}
				}
				result = "success";
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String deleteRepository(String id, Map<String, Object> map, HttpServletRequest request) {
		String result = "";
		if (Common.notEmpty(id)) {
			KnowledgeStoreHouse knowledgeStoreHouse = knowledgeStoreHouseService.getKnowledgeStoreHouse(Integer.parseInt(id));
			if (knowledgeStoreHouse != null) {
				knowledgeStoreHouse.setIsDel(1);
				knowledgeStoreHouseService.updateKnowledgeStoreHouse(knowledgeStoreHouse);
				result = "success";
			} else {
				result = "false";
			}
		}
		return result;
	}
	
	private void updateFile(HhFile file, MultipartFile multFile, String package_path) {
		try {
			List<String> fileStrList = Common.saveFile(multFile, package_path);
			file.setFileName(fileStrList.get(0));
			file.setFilePath(fileStrList.get(1));
			file.setFileUuid(fileStrList.get(2));
			// 先执行保存
			commonService.saveOrUpdate(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/searchKnowledgeData")
	public String searchKnowledgeData(Map<String, Object> map, HttpServletRequest request, String documentName) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		String str=(String) session.getAttribute("gzwsession_company");
		String dataAuthority = systemService.getDataauth(str);
		String mapurl = request.getContextPath()+ "/knowledgeStoreHouse";
		map.put("mapurl", mapurl);
		KnowledgeStoreHouse entity = new KnowledgeStoreHouse();
		documentName = new String(documentName.getBytes("iso8859-1"),"utf-8");
		if (Common.notEmpty(documentName)) {
			entity.setDocumentName(documentName);
		}
		map.put("entity", entity);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = knowledgeStoreHouseService.getKnowledgeStoreHouses(entity,pageNum,Base.pagesize,dataAuthority);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/knowledgeStoreHouse/searchKnowledgeData");
		return "/knowledgeStoreHouse/searchKnowledgeStoreHouseList";
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(String id){
		String result = "";
		if (Common.notEmpty(id)) {
			KnowledgeStoreHouse entity = knowledgeStoreHouseService.getKnowledgeStoreHouse(Integer.parseInt(id));
			if(entity == null){
				result = "false";
			} else {
				result = "success";
			} 
		}
		return result;
	}

}
