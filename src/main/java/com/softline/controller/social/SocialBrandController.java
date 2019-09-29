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

import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.entity.DataSocialBrand;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.social.ISocialBrandService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/social/brand")
/**
 * 
 * @author zy
 *
 */
public class SocialBrandController {

	@Resource(name = "socialBrandService")
	private ISocialBrandService socialBrandService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;

	@ModelAttribute
	public void getCommodity(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			DataSocialBrand entityview = socialBrandService.getSocialBrand(id);
			map.put("socialBrand", entityview);
		}
	}

	/**
	 * 数据填报查询画面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String _list(DataSocialBrand entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/brand";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页信息取得
		MsgPage msgPage = socialBrandService.getSocialBrandListView(entity,
				pageNum, Base.pagesize, Base.fun_search);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/brand/list");
		map.put("entityview", entity);
		// 下拉框信息信息
		addData(map);
		return "/social/brand/brandList";
	}

	/**
	 * 弹出查看/上报画面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String _view(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataSocialBrand entityview;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataSocialBrand();
		} else {
			// 数据明细
			entityview = socialBrandService.getSocialBrand(id);
			// 审核信息
			examine = examineService.getListExamine(entityview.getId(),
					Base.examkind_brand);
		}
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", examine);
		return "/social/brand/brandView";
	}

	/**
	 * 弹出新增/修改画面
	 * 
	 * @return
	 */
	@RequestMapping("/newmodify")
	public String _newmodify(DataSocialBrand entity,
			HttpServletRequest request, Map<String, Object> map, String op)
			throws IOException {
		map.put("op", op);
		DataSocialBrand entityView;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		// 新增的场合
		if (entity.getId() == null) {
			entityView = new DataSocialBrand();
			// 修改的场合
		} else {
			entityView = socialBrandService.getSocialBrand(entity.getId());
			// 审核信息
			examine = examineService.getListExamine(entityView.getId(),
					Base.examkind_brand);
		}
		// 下拉框信息
		addData(map);
		map.put("entityview", entityView);
		map.put("entityExamineviewlist", examine);
		return "/social/brand/brandNewModify";
	}

	/**
	 * 弹出上报画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/postexam")
	public String _postexam(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataSocialBrand entityView;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		if (id == null) {
			entityView = new DataSocialBrand();
		} else {
			// 数据明细
			entityView = socialBrandService.getSocialBrand(id);
			// 审核信息
			examine = examineService.getListExamine(entityView.getId(),
					Base.examkind_brand);
		}
		map.put("entityview", entityView);
		map.put("entityExamineviewlist", examine);
		map.put("op", op);
		return "/social/brand/brandView";
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
		CommitResult result = socialBrandService.deleteSocialBrand(id, use);
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
	public String _save(DataSocialBrand entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		CommitResult result = socialBrandService.saveSocialBrand(entity, use, "save");
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
	public String _saveandreport(DataSocialBrand entity,
			HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		String data = "";
		entity.setStatus(Base.examstatus_2);
		CommitResult result = socialBrandService.saveSocialBrand(entity, use,
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
		DataSocialBrand entity = socialBrandService.getSocialBrand(id);
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
			CommitResult result = socialBrandService.saveSocialBrand(entity,
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
	public String _examinelist(DataSocialBrand entity,
			HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/social/brand";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		MsgPage msgPage = socialBrandService.getSocialBrandListView(entity,
				pageNum, Base.pagesize, Base.fun_examine);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/social/brand/examinelist");
		map.put("entityview", entity);
		// 下拉框信息信息
		addData(map);
		return "/social/brand/brandExamineList";
	}

	/**
	 * 跳转到数据审核画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exam")
	public String _exam(Integer id, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		DataSocialBrand entityView;
		if (id == null) {
			entityView = new DataSocialBrand();
		} else {
			entityView = socialBrandService.getSocialBrand(id);
		}
		map.put("entityview", entityView);
		return "/social/brand/brandExamine";
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
		CommitResult result = socialBrandService.saveSocialBrandExamine(
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
		DataSocialBrand entity = socialBrandService.getSocialBrand(id);
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
		// 审核状态
		List<HhBase> examstatustype = baseService.getBases(Base.examstatustype);
		map.put("examStatus", examstatustype);
		// 品牌类型
		List<HhBase> brandType = baseService.getBases(Base.brandType);
		map.put("brandType", brandType);
		// 品牌位阶
		List<HhBase> brandLevel = baseService.getBases(Base.brandLevel);
		map.put("brandLevel", brandLevel);
		// 品牌属性
		List<HhBase> brandProperty = baseService.getBases(Base.brandProperty);
		map.put("brandProperty", brandProperty);
	}

}
