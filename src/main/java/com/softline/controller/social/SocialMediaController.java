package com.softline.controller.social;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.entity.DataSocialMedia;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.social.ISocialMediaService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/social/media")
/**
 * 
 * @author zy
 *
 */
public class SocialMediaController {

	@Resource(name = "socialMediaService")
	private ISocialMediaService socialMediaService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;

	@ModelAttribute
	public void getCommodity(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			DataSocialMedia entityview = socialMediaService.getSocialMedia(id);
			map.put("socialMedia", entityview);
		}
	}

	/**
	 * 数据填报查询画面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String _list(DataSocialMedia entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/media";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页信息取得
		MsgPage msgPage = socialMediaService.getSocialMediaListView(entity,
				pageNum, Base.pagesize, Base.fun_search);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/media/list");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/social/media/mediaList";
	}

	/**
	 * 弹出查看/上报画面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String _view(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataSocialMedia entityview;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataSocialMedia();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			Date date = new Date();
			entityview.setYear(Integer.parseInt(df.format(date)));
			entityview.setMonth(Integer.parseInt(df1.format(date)));
		} else {
			// 数据明细
			entityview = socialMediaService.getSocialMedia(id);
			// 审核信息
			examine = examineService.getListExamine(entityview.getId(),
					Base.examkind_media);
		}
		// 审核状态信息
		addData(map);
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", examine);
		return "/social/media/mediaView";
	}

	/**
	 * 弹出新增/修改画面
	 * 
	 * @return
	 */
	@RequestMapping("/newmodify")
	public String _newmodify(DataSocialMedia entity,
			HttpServletRequest request, Map<String, Object> map, String op)
			throws IOException {
		map.put("op", op);
		DataSocialMedia entityView;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		// 新增的场合
		if (entity.getId() == null) {
			entityView = new DataSocialMedia();
			Date date = new Date();
			// 默认当前日期
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			Calendar rightNow = Calendar.getInstance();  
	        rightNow.setTime(date);  
	        rightNow.add(Calendar.MONTH, -1);
			entityView.setYear(Integer.parseInt(df.format(rightNow.getTime())));
			entityView.setMonth(Integer.parseInt(df1.format(rightNow.getTime())));
			// 修改的场合
		} else {
			entityView = socialMediaService.getSocialMedia(entity.getId());
			// 审核信息
			examine = examineService.getListExamine(entityView.getId(),
					Base.examkind_media);
		}
		// 审核状态信息
		addData(map);
		map.put("entityview", entityView);
		map.put("entityExamineviewlist", examine);
		return "/social/media/mediaNewModify";
	}

	/**
	 * 弹出上报画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/postexam")
	public String _postexam(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataSocialMedia entityView;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		if (id == null) {
			entityView = new DataSocialMedia();
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			entityView.setYear(Integer.parseInt(df.format(date)));
			entityView.setMonth(Integer.parseInt(df1.format(date)));
		} else {
			// 数据明细
			entityView = socialMediaService.getSocialMedia(id);
			// 审核信息
			examine = examineService.getListExamine(entityView.getId(),
					Base.examkind_media);
		}
		// 审核状态信息
		addData(map);
		map.put("entityview", entityView);
		map.put("entityExamineviewlist", examine);
		map.put("op", op);
		return "/social/media/mediaView";
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
		CommitResult result = socialMediaService.deleteSocialMedia(id, use);
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
	public String _save(DataSocialMedia entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		CommitResult result = socialMediaService.saveSocialMedia(entity, use,
				"save");
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
	public String _saveandreport(DataSocialMedia entity,
			HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		String data = "";
		entity.setStatus(Base.examstatus_2);
		CommitResult result = socialMediaService.saveSocialMedia(entity, use,
				"saveAndReport");
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
		DataSocialMedia entity = socialMediaService.getSocialMedia(id);
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
			CommitResult result = socialMediaService.saveSocialMedia(entity,
					use, "report");
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
	public String _examinelist(DataSocialMedia entity,
			HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/media";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		MsgPage msgPage = socialMediaService.getSocialMediaListView(entity,
				pageNum, Base.pagesize, Base.fun_examine);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/media/examinelist");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/social/media/mediaExamineList";
	}

	/**
	 * 跳转到数据审核画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exam")
	public String _exam(Integer id, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		DataSocialMedia entityView;
		if (id == null) {
			entityView = new DataSocialMedia();
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			entityView.setYear(Integer.parseInt(df.format(date)));
			entityView.setMonth(Integer.parseInt(df1.format(date)));
		} else {
			entityView = socialMediaService.getSocialMedia(id);
		}
		// 审核状态信息
		addData(map);
		map.put("entityview", entityView);
		return "/social/media/mediaExamine";
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
		CommitResult result = socialMediaService.saveSocialMediaExamine(
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
		DataSocialMedia entity = socialMediaService.getSocialMedia(id);
		if (entity == null) {
			result.setResult(false);
			result = CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}

	/**
	 * 审核状态下拉框信息追加
	 * 
	 */
	private void addData(Map<String, Object> map) {
		List<HhBase> examstatustype = baseService.getBases(Base.examstatustype);
		map.put("examStatus", examstatustype);
		List<HhBase> mediaClass = baseService.getBases(Base.mediaType);
		map.put("mediaClass", mediaClass);
	}

	
	/**
	 * 跳转到审核画面
	 * 
	 * @return
	 */
	@RequestMapping("/dataCreditInfolist")
	public String dataCreditInfolist(DataSocialMedia entity,
			HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/media";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		MsgPage msgPage = socialMediaService.getSocialMediaListView(entity,
				pageNum, Base.pagesize, Base.fun_examine);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/media/dataCreditInfolist");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/dataExchange/dataCreditInfolist";
	}
	/**
	 * 跳转到审核画面
	 * 征信
	 * @return
	 */
	@RequestMapping("/summaryCreditInfolist")
	public String summaryCreditInfolist(DataSocialMedia entity,
			HttpServletRequest request, Map<String, Object> map)
					throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/media";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		MsgPage msgPage = socialMediaService.getSocialMediaListView(entity,
				pageNum, Base.pagesize, Base.fun_examine);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/media/summaryCreditInfolist");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/summaryInfo/summaryCreditInfolist";
	}
	
	
}
