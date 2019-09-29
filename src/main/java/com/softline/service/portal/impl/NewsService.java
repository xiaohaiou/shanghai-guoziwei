package com.softline.service.portal.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Common;
import com.softline.dao.portal.INewsDao;
import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;
import com.softline.service.portal.INewsService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Service("newsService")
public class NewsService implements INewsService {
	@Autowired
	@Qualifier("newsDao")
	private INewsDao newsDao;
	
	@Autowired
	@Qualifier("commonService")
	private ICommonService commonService;
	
	@Override
	public void save(PortalNews portalNews) {
		commonService.saveOrUpdate(portalNews);
	}

	@Override
	public void delete(PortalNews portalNews) {
		commonService.saveOrUpdate(portalNews);
	}

	@Override
	public MsgPage getPortalNewsList(PortalNews portalNews, Integer curPageNum,
			Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = newsDao.getAllRowCount(portalNews);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<PortalNews> list = newsDao.getPortalMsgList(portalNews, offset, pageSize);
    	if(!list.isEmpty()){
    		for (int i = 0; i < list.size(); i++) {
    			PortalNews temp = list.get(i);
    			if(Common.notEmpty(temp.getTitle())){
    				temp.setTitle(temp.getTitle());
    			}
				if(Common.notEmpty(temp.getDescription())){
					temp.setDescription(temp.getDescription());
				}
    		}
    	}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public List<PortalNews> getPortalNewsList(PortalNews portalNews) {
		return newsDao.getPortalNewsList(portalNews);
	}

	@Override
	public PortalNews getPortalNews(Integer id) {
		return newsDao.getPortalNews(id);
	}

}
