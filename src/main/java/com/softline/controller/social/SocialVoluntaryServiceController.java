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
import com.softline.entity.DataSocialVoluntary;
import com.softline.entity.DataVoluntaryService;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.social.ISocialVoluntaryService;
import com.softline.service.social.ISocialVoluntaryserviceService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/social/voluntaryservice")
/**
 * 
 * @author zy
 *
 */
public class SocialVoluntaryServiceController {

	@Resource(name = "socialVoluntaryserviceService")
	private ISocialVoluntaryserviceService socialVoluntaryserviceService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;

	@ModelAttribute
	public void getCommodity(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			DataVoluntaryService entityview = socialVoluntaryserviceService
					.getSocialVoluntary(id);
			map.put("socialVoluntary", entityview);
		}
	}

	/**
	 * 数据填报查询画面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String _list(DataVoluntaryService entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/voluntaryservice";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页信息取得
		MsgPage msgPage = socialVoluntaryserviceService.getSocialVoluntaryListView(
				entity, pageNum, Base.pagesize, Base.fun_search);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/voluntaryservice/list");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/social/voluntaryservice/voluntaryList";
	}

	/**
	 * 弹出查看/上报画面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String _view(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataVoluntaryService entityview;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataVoluntaryService();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			Date date = new Date();
			entityview.setYear(Integer.parseInt(df.format(date)));
			entityview.setMonth(Integer.parseInt(df1.format(date)));
		} else {
			// 数据明细
			entityview = socialVoluntaryserviceService.getSocialVoluntary(id);
			// 审核信息
			examine = examineService.getListExamine(entityview.getId(),
					Base.examkind_voluntary);
		}
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", examine);
		return "/social/voluntaryservice/voluntaryView";
	}

	/**
	 * 弹出新增/修改画面
	 * 
	 * @return
	 */
	@RequestMapping("/newmodify")
	public String _newmodify(DataVoluntaryService entity,
			HttpServletRequest request, Map<String, Object> map, String op)
			throws IOException {
		map.put("op", op);
		DataVoluntaryService entityView;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		// 新增的场合
		if (entity.getId() == null) {
			entityView = new DataVoluntaryService();
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
			entityView = socialVoluntaryserviceService.getSocialVoluntary(entity
					.getId());
			// 审核信息
			examine = examineService.getListExamine(entityView.getId(),
					Base.examkind_voluntary);
		}
		map.put("entityview", entityView);
		map.put("entityExamineviewlist", examine);
		return "/social/voluntaryservice/voluntaryNewModify";
	}

	/**
	 * 弹出上报画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/postexam")
	public String _postexam(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataVoluntaryService entityView;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		if (id == null) {
			entityView = new DataVoluntaryService();
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			entityView.setYear(Integer.parseInt(df.format(date)));
			entityView.setMonth(Integer.parseInt(df1.format(date)));
		} else {
			// 数据明细
			entityView = socialVoluntaryserviceService.getSocialVoluntary(id);
			// 审核信息
			examine = examineService.getListExamine(entityView.getId(),
					Base.examkind_voluntary);
		}
		map.put("entityview", entityView);
		map.put("entityExamineviewlist", examine);
		map.put("op", op);
		return "/social/voluntaryservice/voluntaryView";
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
		CommitResult result = socialVoluntaryserviceService.deleteSocialVoluntary(id,
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
	public String _save(DataVoluntaryService entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		CommitResult result = socialVoluntaryserviceService.saveSocialVoluntary(
				entity, use, "save");
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
	public String _saveandreport(DataVoluntaryService entity,
			HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		String data = "";
		entity.setStatus(Base.examstatus_2);
		CommitResult result = socialVoluntaryserviceService.saveSocialVoluntary(
				entity, use, "saveAndReport");
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
		DataVoluntaryService entity = socialVoluntaryserviceService
				.getSocialVoluntary(id);
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
			CommitResult result = socialVoluntaryserviceService.saveSocialVoluntary(
					entity, use, "report");
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
	public String _examinelist(DataVoluntaryService entity,
			HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/voluntaryservice";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		MsgPage msgPage = socialVoluntaryserviceService.getSocialVoluntaryListView(
				entity, pageNum, Base.pagesize, Base.fun_examine);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/voluntaryservice/examinelist");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/social/voluntaryservice/voluntaryExamineList";
	}

	/**
	 * 跳转到数据审核画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exam")
	public String _exam(Integer id, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		DataVoluntaryService entityView;
		if (id == null) {
			entityView = new DataVoluntaryService();
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			entityView.setYear(Integer.parseInt(df.format(date)));
			entityView.setMonth(Integer.parseInt(df1.format(date)));
		} else {
			entityView = socialVoluntaryserviceService.getSocialVoluntary(id);
		}
		map.put("entityview", entityView);
		return "/social/voluntaryservice/voluntaryExamine";
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
		CommitResult result = socialVoluntaryserviceService
				.saveSocialVoluntaryExamine(entityid, examStr, examResult, use);
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
		DataVoluntaryService entity = socialVoluntaryserviceService
				.getSocialVoluntary(id);
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
	}

	
	
	/**
	 * 跳转到审核画面
	 * 
	 * @return
	 */
	@RequestMapping("/dataPartyworklist")
	public String dataPartyworklist(DataVoluntaryService entity,
			HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/voluntaryservice";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		MsgPage msgPage = socialVoluntaryserviceService.getSocialVoluntaryListView(
				entity, pageNum, Base.pagesize, Base.fun_examine);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/voluntaryservice/dataPartyworklist");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/dataExchange/dataPartyworklist";
	}
	
	/**
	 * 跳转到审核画面
	 * 党建工作
	 * @return
	 */
	@RequestMapping("/summaryPartyworklist")
	public String summaryPartyworklist(DataVoluntaryService entity,
			HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/voluntaryservice";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		MsgPage msgPage = socialVoluntaryserviceService.getSocialVoluntaryListView(
				entity, pageNum, Base.pagesize, Base.fun_examine);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/voluntaryservice/summaryPartyworklist");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/summaryInfo/summaryPartyworklist";
	}

	
}
