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
import com.softline.entity.DataSocialConsensus;
import com.softline.entity.HhBase;
import com.softline.entity.SysExamine;
import com.softline.service.social.ISocialConsensusService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/social/consensus")
/**
 * 
 * @author zy
 *
 */
public class QueryPageSocialConsensusController {

	@Resource(name = "socialConsensusService")
	private ISocialConsensusService socialConsensusService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;

	@ModelAttribute
	public void getCommodity(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			DataSocialConsensus entityview = socialConsensusService
					.getSocialConsensus(id);
			map.put("socialConsensus", entityview);
		}
	}

	/**
	 * 数据填报查询画面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String _list(DataSocialConsensus entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/queryPage/social/consensus";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页信息取得
		MsgPage msgPage = socialConsensusService.getSocialConsensusListView(
				entity, pageNum, Base.pagesize, Base.fun_search);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/queryPage/social/consensus/list");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/queryPage/social/consensus/consensusList";
	}

	/**
	 * 弹出查看/上报画面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String _view(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataSocialConsensus entityview;
		List<SysExamine> examine = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataSocialConsensus();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date = new Date();
			entityview.setYear(Integer.parseInt(df.format(date)));
		} else {
			// 数据明细
			entityview = socialConsensusService.getSocialConsensus(id);
			// 审核信息
			examine = examineService.getListExamine(entityview.getId(),
					Base.examkind_consensus);
		}
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", examine);
		return "/queryPage/social/consensus/consensusView";
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
