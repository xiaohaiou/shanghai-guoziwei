package com.softline.service.portal;

import java.util.List;

import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;
import com.softline.util.MsgPage;

public interface INewsService {
	void save(PortalNews portalNews);

	void delete(PortalNews portalNews);
	/**
	 * 
	 * @param portalNews
	 * @param curPageNum 当前页
	 * @param pageSize 每页显示的数量
	 * @return
	 */
	MsgPage getPortalNewsList(PortalNews portalNews, Integer curPageNum, Integer pageSize);

	List<PortalNews> getPortalNewsList(PortalNews portalNews);
	
	PortalNews getPortalNews(Integer id);
}
