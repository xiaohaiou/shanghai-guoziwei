package com.softline.controller.system;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Common;
import com.softline.entity.HhUsers;
import com.softline.entity.SysSuggestion;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.ISystemSuggestionService;
import com.softline.util.MsgPage;

/**
 * 
 * @author JasonTang 2017-06-12
 * 
 * */
@Controller
@RequestMapping("/suggestion")
public class SuggestionController {

	@Resource(name = "systemService")
	private ISystemService systemService;

	@Resource(name = "commonService")
	private ICommonService commonService;

	@Resource(name = "systemSuggestionService")
	private ISystemSuggestionService systemSuggestionService;
	
	/**
	 * 
	 * @param condition
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("_list")
	public String _list(SysSuggestion condition,Map<String ,Object> map, HttpServletRequest request){
		//String  isAdm = (String) request.getSession().getAttribute("isAdm");
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
//		if(isAdm.equals("0")){
//			condition.setCreateName(user.getVcName());
//			condition.setCreator(user.getVcEmployeeId());
//		}
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  systemSuggestionService.getSysSuggestions(condition, pageNum, 10);
		map.put("suggestion", condition);
		map.put("msgPage", msgPage);
		map.put("searchurl", request.getContextPath()+"/suggestion/_list");
		return "/system/suggestionList";
	}
	
	@RequestMapping("_edit")
	public String _edit(Integer id,Map<String ,Object> map, HttpServletRequest request){
		if(id != null){
			SysSuggestion suggestion = (SysSuggestion) commonService.findById(SysSuggestion.class, id);
			map.put("suggestion", suggestion);
		}
		return "/system/suggestionEdit";
	}
	
	/**
	 * 
	 * @param suggestion
	 * @param map
	 * @param request
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("_save")
	public String _save(SysSuggestion suggestion,Map<String ,Object> map, HttpServletRequest request,@RequestParam(value="file",required=false) MultipartFile file){
		HhUsers user = (HhUsers) request.getSession().getAttribute(
				"gzwsession_users");
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			if (Common.notEmpty(fileType)) {
				fileType = fileType.toLowerCase();
				if (!(fileType.equals(".pdf") || fileType.equals(".png")
						|| fileType.equals(".jpeg") || fileType.equals(".docx")
						|| fileType.equals(".jpg") || fileType.equals(".doc"))) {
					return "fail";
				}
			} else {
				return "fail";
			}

		}

		if (suggestion.getId() != null) {
			SysSuggestion entity = (SysSuggestion) commonService.findById(
					SysSuggestion.class, suggestion.getId());
			systemSuggestionService.saveSuggestion(user, suggestion, file,
					entity);
		} else {
			systemSuggestionService
					.saveSuggestion(user, suggestion, file, null);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("_del")
	public String _del(Integer id,Map<String ,Object> map, HttpServletRequest request){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		SysSuggestion suggestion = (SysSuggestion) commonService.findById(SysSuggestion.class, id);
		suggestion.setIsDel(1);
		suggestion.setModifyId(user.getVcEmployeeId());
		suggestion.setModifyName(user.getVcName());
		suggestion.setModifyTime(df.format(new Date()));
		commonService.saveOrUpdate(suggestion);
		return "success";
	}

}
