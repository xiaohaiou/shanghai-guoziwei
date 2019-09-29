package com.softline.controller.portal;

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
import org.springframework.web.bind.annotation.ResponseBody;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalNews;
import com.softline.service.portal.INewsService;
import com.softline.service.system.IBaseService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/portal/news")
public class PortalNewsController {
	@Resource(name = "newsService")
	private INewsService newsService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping(value = "/list")
	public String _query(PortalNews entity, Map<String ,Object> map, HttpServletRequest request){
		String mapurl = request.getContextPath()+ "/portal/news";
		map.put("mapurl", mapurl);
		 map.put("searchurl", "/shanghai-gzw/portal/news/list");
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = newsService.getPortalNewsList(entity, pageNum, 10);
		map.put("msgPage", msgPage);
		List<PortalNews> newsList = msgPage.getList();
		map.put("newsList", newsList);
		map.put("entity", entity);
		List<HhBase> newsType = baseService.getBases(Base.KNOWLEDGESTOREHOUSE_TYPE);
	    map.put("newsType",newsType);
		return "/portal/newsList";
	}
	
	@RequestMapping("/addOrModify")
	public String addOrModify(PortalNews entity, Map<String, Object> map, HttpServletRequest request, String op){
		map.put("op", op);
		if(entity.getId() != null) {
			entity = newsService.getPortalNews(entity.getId());
		}else {
			entity = new PortalNews();
		}
		map.put("portalNews", entity);
		List<HhBase> newsType = baseService.getBases(Base.KNOWLEDGESTOREHOUSE_TYPE);
	    map.put("newsType",newsType);
		return "portal/newsAddOrModify";
	}
	
	@RequestMapping("/view")
	public String viewNews(Integer id, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		PortalNews portalNews = newsService.getPortalNews(id);
		map.put("portalNews", portalNews);
		return "/portal/newsView";
	}
	
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(@ModelAttribute("portalNews")PortalNews entity, HttpServletRequest request){
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if(entity.getId() == null){
			entity.setCreateTime(df.format(new Date()));
			entity.setCreator(user.getVcName());
			entity.setDelFlag(0);
			entity.setIsIssue(0);
			newsService.save(entity);
			result = "success";
		} else {
			PortalNews portalNews = newsService.getPortalNews(entity.getId());
			if (portalNews == null) {
				result = "flase";
			} else {
				if(portalNews.getIsIssue() == 0){
					entity.setDelFlag(0);
					entity.setModifier(user.getVcName());
					entity.setModifyTime(df.format(new Date()));
					newsService.save(entity);
					result = "success";
				} else {
					result = "false2";
				}
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndPublish", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndPublish(@ModelAttribute("portalNews")PortalNews entity, HttpServletRequest request) throws IOException, ParseException {
		String result = "";
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
		if (entity.getId() == null) {
			entity.setCreateTime(df.format(new Date()));
			entity.setCreator(user.getVcName());
			entity.setDelFlag(0);
			entity.setIsIssue(1);
			newsService.save(entity);
			result = "success";
		} else {
			PortalNews portalNews = newsService.getPortalNews(entity.getId());
			if (portalNews == null) {
				result = "flase";
			} else {
				if(portalNews.getIsIssue() == 0){
					entity.setModifyTime(df.format(new Date()));
					entity.setModifier(user.getVcName());
					entity.setDelFlag(0);
					entity.setIsIssue(1);
					newsService.save(entity);
					result = "success";
				} else {
					result = "false2";
				}
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(Integer id, Map<String, Object> map,HttpServletRequest request){
		String result = "";
		PortalNews portalNews = newsService.getPortalNews(id);
		if (portalNews == null) {
			result = "false";
		}else {
			portalNews.setDelFlag(1);
			newsService.delete(portalNews);
			result = "success";
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/issueNo")
	public String issueNo(Integer id, Map<String, Object> map,HttpServletRequest request){
		String result = "";
		PortalNews portalNews = newsService.getPortalNews(id);
		if (portalNews.getIsIssue() != 0) {
			portalNews.setIsIssue(0);
			newsService.delete(portalNews);
			result = "success";
		} else {
			result = "false";
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/issue")
	public String issue(Integer id, Map<String, Object> map,HttpServletRequest request){
		String result = "";
		PortalNews portalNews = newsService.getPortalNews(id);
		if (portalNews.getIsIssue() != 1) {
			portalNews.setIsIssue(1);
			newsService.delete(portalNews);
			result = "success";
		} else {
			result = "false";
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(String id){
		String result = "";
		if (Common.notEmpty(id)) {
			PortalNews portalNews = newsService.getPortalNews(Integer.parseInt(id));
			if(portalNews == null){
				result = "false";
			} else {
				result = "success";
			} 
		}
		return result;
	}
	
}
