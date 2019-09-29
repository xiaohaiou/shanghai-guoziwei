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
import com.softline.entity.DataSocialNewMedia;
import com.softline.entity.HhBase;
import com.softline.entity.SysExamine;
import com.softline.service.social.ISocialNewMediaService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/social/newMedia")
/**
 * 
 * @author zy
 *
 */
public class QueryPageSocialNewMediaController {

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
		String mapurl = request.getContextPath() + "/queryPage/social/newMedia";
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
		map.put("searchurl", "/shanghai-gzw/queryPage/social/newMedia/list");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/queryPage/social/newMedia/newMediaList";
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
		return "/queryPage/social/newMedia/newMediaView";
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
