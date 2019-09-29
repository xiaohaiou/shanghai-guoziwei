package com.softline.controller.queryPage.social;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softline.common.Base;
import com.softline.entity.DataSocialBrand;
import com.softline.entity.HhBase;
import com.softline.entity.SysExamine;
import com.softline.service.social.ISocialBrandService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/queryPage/social/brand")
/**
 * 
 * @author zy
 *
 */
public class QueryPageSocialBrandController {

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
			DataSocialBrand entityview = socialBrandService
					.getSocialBrand(id);
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
		String mapurl = request.getContextPath() + "/queryPage/social/brand";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页信息取得
		MsgPage msgPage = socialBrandService.getSocialBrandListView(
				entity, pageNum, Base.pagesize, Base.fun_search);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/queryPage/social/brand/list");
		map.put("entityview", entity);
		// 下拉框信息信息
		addData(map);
		return "/queryPage/social/brand/brandList";
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
		return "/queryPage/social/brand/brandView";
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
