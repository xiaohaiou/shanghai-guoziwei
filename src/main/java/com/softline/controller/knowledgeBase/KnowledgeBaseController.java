package com.softline.controller.knowledgeBase;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.entity.HhBase;
import com.softline.entity.HhFile;
import com.softline.entity.HhUsers;
import com.softline.entity.KnowledgeBase;
import com.softline.entity.Purchase;
import com.softline.entityTemp.CommitResult;
import com.softline.service.knowledgeBase.IKnowledgeBaseService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/knowledgeBase")
public class KnowledgeBaseController {

	@Resource(name = "knowledgeBaseService")
	private IKnowledgeBaseService knowledgeBaseService;

	@Resource(name = "baseService")
	private IBaseService baseService;

	@Resource(name = "commonService")
	private ICommonService commonService;

	@Resource(name = "systemService")
	private ISystemService systemService;
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping(value = "/index")
	public String knowledgeBaseIndex(Map<String, Object> map, HttpServletRequest request) {
		return "/system/knowledgeBaseIndex";
	}

	@RequestMapping(value = "/list")
	public String pagelist(KnowledgeBase entity, Map<String, Object> map, HttpServletRequest request) {
		String mapurl = request.getContextPath()+ "/knowledgeBase";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
		String doDate2 = request.getParameter("doDate2");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage = knowledgeBaseService.getKnowledgeBases(entity,pageNum,Base.pagesize,doDate2);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/knowledgeBase/list");
	    map.put("entity", entity);
	    List<HhBase> typeId = baseService.getBases(Base.KNOWLEDGEBASE_TYPE);
	    map.put("typeId",typeId);
		map.put("doDate2", doDate2);
		return "/knowledgeBase/knowledgeBaseList";
	}
	
	@RequestMapping("/addOrModify")
	public String addOrModifyKnowledgeBase(KnowledgeBase entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		map.put("op", op);
		List<HhBase> type = baseService.getBases(Base.KNOWLEDGEBASE_TYPE);
	    map.put("type",type);
	    List<HhBase> area = baseService.getBases(Base.KNOWLEDGEBASE_AREA);
	    map.put("area",area);
	    KnowledgeBase knowledgeBase;
		if(entity.getId() != null) {
			knowledgeBase = knowledgeBaseService.getKnowledgeBase(entity);
			HhFile hfile = commonService.getFileByEnIdAndType(entity.getId(), Base.KNOWLEDGEBASE_TYPE);
			if (hfile != null) {
				map.put("file_Path", hfile.getFilePath());
				map.put("file_Name", hfile.getFileName());
			} else {
				map.put("file_Path", "");
				map.put("file_Name", "");
			}
		}else {
			knowledgeBase = new KnowledgeBase();
		}
		map.put("knowledgeBase", knowledgeBase);
		return "/knowledgeBase/knowledgeBaseAddOrModify";
	}
	
	@RequestMapping("/view")
	public String viewKnowledgeBase(KnowledgeBase entity, Map<String, Object> map, HttpServletRequest request, String id) {
		KnowledgeBase knowledgeBase = knowledgeBaseService.getKnowledgeBase(Integer.parseInt(id));
		map.put("knowledgeBase", knowledgeBase);
		HhFile hfile = commonService.getFileByEnIdAndType(entity.getId(), Base.KNOWLEDGEBASE_TYPE);
		if (hfile != null) {
			map.put("file_Path", hfile.getFilePath());
			map.put("file_Name", hfile.getFileName());
		} else {
			map.put("file_Path", "");
			map.put("file_Name", "");
		}
		return "/knowledgeBase/knowledgeBaseView";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate")
	public String saveOrUpdateKnowledgeBase(@ModelAttribute("knowledgeBase")KnowledgeBase entity, HttpServletRequest request,
			@RequestParam(value="pjFile",required=false) MultipartFile multFile) throws ParseException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setIsDel(0);
			entity.setTop(1);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
			String package_path = DES.KNOWLEDGE_PATH;
			knowledgeBaseService.save(entity, user, multFile, package_path);
		} else {
			entity.setIsDel(0);
			entity.setTop(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getVcEmployeeId());
			entity.setLastModifyPersonName(user.getVcName());
			knowledgeBaseService.updateKnowledgeBase(entity);
			if (!multFile.isEmpty()) {
				String package_path = DES.KNOWLEDGE_PATH;
				HhFile _file = commonService.getFileByEnIdAndType(entity.getId(), Base.KNOWLEDGEBASE_TYPE);
				if (_file != null) {
					File file = new File(_file.getFilePath());
					file.delete();
					updateFile(_file, multFile, package_path);
				}else{
					commonService.saveFile(multFile, entity.getId(),  Base.KNOWLEDGEBASE_TYPE, package_path);
				}
			}
		}
		return "success";
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String deleteKnowledgeBase(String id, Map<String, Object> map, HttpServletRequest request) {
		String result = "";
		if (Common.notEmpty(id)) {
			KnowledgeBase knowledgeBase = knowledgeBaseService.getKnowledgeBase(Integer.parseInt(id));
			if (knowledgeBase != null) {
				knowledgeBase.setIsDel(1);
				knowledgeBaseService.updateKnowledgeBase(knowledgeBase);
				result = "success";
			} else {
				result = "false";
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/top", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String topKnowledgeBase(String ids, HttpServletRequest request) {
		try {
			if (ids != null) {
				String[] listId = ids.split(",");
				for (int i = 0; i < listId.length; i++) {
					String _id = listId[i];
					Integer id = Integer.valueOf(_id);
					KnowledgeBase entity = knowledgeBaseService.getKnowledgeBase(id);
					entity.setTop(0);
					knowledgeBaseService.updateKnowledgeBase(entity);
				}
			}
			return "success";
		} catch (Exception ex) {
			return "false";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delTop", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String delTopKnowledgeBase(String ids, HttpServletRequest requests) {
		try {
			if (ids != null) {
				String[] listId = ids.split(",");
				for (int i = 0; i < listId.length; i++) {
					String _id = listId[i];
					Integer id = Integer.valueOf(_id);
					KnowledgeBase entity = knowledgeBaseService.getKnowledgeBase(id);
					entity.setTop(1);
					knowledgeBaseService.updateKnowledgeBase(entity);
				}
			}
			return "success";
		} catch (Exception ex) {
			return "false";
		}
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
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(String id,String operate,HttpServletRequest request ,Map<String, Object> map){
		String result = "";
		if (Common.notEmpty(id)) {
			KnowledgeBase entity = knowledgeBaseService.getKnowledgeBase(Integer.parseInt(id));
			if(entity == null){
				result = "false";
			} else {
				result = "success";
			}
		}
		return result;
	}

}
