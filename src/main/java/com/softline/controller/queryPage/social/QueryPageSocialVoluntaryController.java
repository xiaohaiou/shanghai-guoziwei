package com.softline.controller.queryPage.social;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softline.common.Base;
import com.softline.entity.DataSocialVoluntary;
import com.softline.entity.HhBase;
import com.softline.entity.SysExamine;
import com.softline.service.social.ISocialVoluntaryService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/social/voluntary")
/**
 * 
 * @author zy
 *
 */
public class QueryPageSocialVoluntaryController {

	@Resource(name = "socialVoluntaryService")
	private ISocialVoluntaryService socialVoluntaryService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;

	@ModelAttribute
	public void getCommodity(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			DataSocialVoluntary entityview = socialVoluntaryService
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
	public String _list(DataSocialVoluntary entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/queryPage/social/voluntary";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页信息取得
		MsgPage msgPage = socialVoluntaryService.getSocialVoluntaryListView(
				entity, pageNum, Base.pagesize, Base.fun_search);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/queryPage/social/voluntary/list");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/queryPage/social/voluntary/voluntaryList";
	}

	/**
	 * 弹出查看/上报画面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String _view(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataSocialVoluntary entityview;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataSocialVoluntary();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			Date date = new Date();
			entityview.setYear(Integer.parseInt(df.format(date)));
			entityview.setMonth(Integer.parseInt(df1.format(date)));
		} else {
			// 数据明细
			entityview = socialVoluntaryService.getSocialVoluntary(id);
			// 审核信息
			examine = examineService.getListExamine(entityview.getId(),
					Base.examkind_voluntary);
		}
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", examine);
		return "/queryPage/social/voluntary/voluntaryView";
	}

	/**
	 * 审核状态下拉框信息追加
	 * 
	 */
	private void addData(Map<String, Object> map) {
		List<HhBase> examstatustype = baseService.getBases(Base.examstatustype);
		map.put("examStatus", examstatustype);
	}

}
