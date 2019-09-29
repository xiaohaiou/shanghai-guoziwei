package com.softline.controller.social;

import java.io.IOException;
import java.text.DateFormat;
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

import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.entity.DataSocialNewMedia;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.social.ISocialNewMediaService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/social/newMedia")
/**
 * 
 * @author zy
 *
 */
public class SocialNewMediaController {

	@Resource(name = "socialNewMediaService")
	private ISocialNewMediaService socialNewMediaService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;

	@ModelAttribute
	public void getCommodity(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			DataSocialNewMedia entityview = socialNewMediaService
					.getSocialNewMedia(id);
			map.put("socialNewMedia", entityview);
		}
	}

	/**
	 * 数据填报查询画面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String _list(DataSocialNewMedia entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/newMedia";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页信息取得
		MsgPage msgPage = socialNewMediaService.getSocialNewMediaListView(
				entity, pageNum, Base.pagesize, Base.fun_search);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/newMedia/list");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/social/newMedia/newMediaList";
	}

	/**
	 * 弹出查看/上报画面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String _view(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataSocialNewMedia entityview;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataSocialNewMedia();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date = new Date();
			entityview.setYear(Integer.parseInt(df.format(date)));
		} else {
			// 数据明细
			entityview = socialNewMediaService.getSocialNewMedia(id);
			// 审核信息
			examine = examineService.getListExamine(entityview.getId(),
					Base.examkind_newmedia);
		}
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", examine);
		return "/social/newMedia/newMediaView";
	}

	/**
	 * 弹出新增/修改画面
	 * 
	 * @return
	 */
	@RequestMapping("/newmodify")
	public String _newmodify(DataSocialNewMedia entity,
			HttpServletRequest request, Map<String, Object> map, String op)
			throws IOException {
		map.put("op", op);
		DataSocialNewMedia entityView;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		// 新增的场合
		if (entity.getId() == null) {
			entityView = new DataSocialNewMedia();
			Date date = new Date();
			// 默认当前日期
			DateFormat df = new SimpleDateFormat("yyyy");
			entityView.setYear(Integer.parseInt(df.format(date)));
			// 修改的场合
		} else {
			entityView = socialNewMediaService
					.getSocialNewMedia(entity.getId());
			// 审核信息
			examine = examineService.getListExamine(entityView.getId(),
					Base.examkind_newmedia);
		}
		map.put("entityview", entityView);
		map.put("entityExamineviewlist", examine);
		return "/social/newMedia/newMediaNewModify";
	}

	/**
	 * 弹出上报画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/postexam")
	public String _postexam(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataSocialNewMedia entityView;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		if (id == null) {
			entityView = new DataSocialNewMedia();
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy");
			entityView.setYear(Integer.parseInt(df.format(date)));
		} else {
			// 数据明细
			entityView = socialNewMediaService.getSocialNewMedia(id);
			// 审核信息
			examine = examineService.getListExamine(entityView.getId(),
					Base.examkind_newmedia);
		}
		map.put("entityview", entityView);
		map.put("entityExamineviewlist", examine);
		map.put("op", op);
		return "/social/newMedia/newMediaView";
	}

	/**
	 * 数据采集画面的删除功能
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public String _delete(Integer id, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result = socialNewMediaService.deleteSocialNewMedia(id,
				use);
		String data = "";
		data = JSONArray.toJSONString(result);
		return data;
	}

	/**
	 * 新增/修改画面的保存功能
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(DataSocialNewMedia entity, HttpServletRequest request,
			Map<String, Object> map,
			@RequestParam(value = "wrFile", required = false) MultipartFile file)
			throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		CommitResult result = socialNewMediaService.saveSocialNewMedia(entity,
				use, "save", file);
		String data = "";
		data = JSONArray.toJSONString(result);
		return data;
	}

	/**
	 * 新增/修改画面的保存并上报功能
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(DataSocialNewMedia entity,
			HttpServletRequest request, Map<String, Object> map,
			@RequestParam(value = "wrFile", required = false) MultipartFile file)
			throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		String data = "";
		entity.setStatus(Base.examstatus_2);
		CommitResult result = socialNewMediaService.saveSocialNewMedia(entity,
				use, "saveAndReport", file);
		data = JSONArray.toJSONString(result);
		return data;
	}

	/**
	 * 上报功能
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/report", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _report(Integer id, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		DataSocialNewMedia entity = socialNewMediaService.getSocialNewMedia(id);
		String data = "";
		// 未上报和已退回可以再点上报
		if (entity.getStatus().equals(Base.examstatus_1)
				|| entity.getStatus().equals(Base.examstatus_3)) {
			entity.setStatus(Base.examstatus_2);
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setReportDate(df.format(date));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			CommitResult result = socialNewMediaService.saveSocialNewMedia(
					entity, use, "report", null);
			data = JSONArray.toJSONString(result);
		} else {
			CommitResult result = new CommitResult();
			result = CommitResult.createErrorResult("该信息已被上报！");
			data = JSONArray.toJSONString(result);
		}
		return data;
	}

	/**
	 * 跳转到审核画面
	 * 
	 * @return
	 */
	@RequestMapping("/examinelist")
	public String _examinelist(DataSocialNewMedia entity,
			HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/newMedia";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		MsgPage msgPage = socialNewMediaService.getSocialNewMediaListView(
				entity, pageNum, Base.pagesize, Base.fun_examine);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/newMedia/examinelist");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/social/newMedia/newMediaExamineList";
	}

	/**
	 * 跳转到数据审核画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exam")
	public String _exam(Integer id, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		DataSocialNewMedia entityView;
		if (id == null) {
			entityView = new DataSocialNewMedia();
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy");
			entityView.setYear(Integer.parseInt(df.format(date)));
		} else {
			entityView = socialNewMediaService.getSocialNewMedia(id);
		}
		map.put("entityview", entityView);
		return "/social/newMedia/newMediaExamine";
	}

	/**
	 * 审核功能（通过/退回）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid, String examStr,
			Integer examResult, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result = socialNewMediaService.saveSocialNewMediaExamine(
				entityid, examStr, examResult, use);
		String data = "";
		data = JSONArray.toJSONString(result);
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(Integer id, HttpServletRequest request,
			Map<String, Object> map) {
		CommitResult result = new CommitResult();
		result.setResult(true);
		DataSocialNewMedia entity = socialNewMediaService.getSocialNewMedia(id);
		if (entity == null) {
			result.setResult(false);
			result = CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}

	/**
	 * 下拉框信息追加
	 * 
	 */
	private void addData(Map<String, Object> map) {
		List<HhBase> examstatustype = baseService.getBases(Base.examstatustype);
		map.put("examStatus", examstatustype);
	}

}
