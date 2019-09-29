package com.softline.dao.portal;

import java.util.List;

import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;

public interface INewsDao {

	Integer getAllRowCount(PortalNews portalNews);

	List<PortalNews> getPortalMsgList(PortalNews portalNews, int offset,
			Integer pageSize);

	List<PortalNews> getPortalNewsList(PortalNews portalNews);
	
	PortalNews getPortalNews(Integer id);

}
