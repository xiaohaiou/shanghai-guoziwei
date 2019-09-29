package com.softline.service.portal.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.portal.ILogDao;
import com.softline.entity.HhOperationLog;
import com.softline.entity.PortalNews;
import com.softline.service.portal.ILogService;
import com.softline.util.MsgPage;

@Service("logService")
public class LogService implements ILogService {
	@Autowired
	@Qualifier("logDao")
	private ILogDao logDao;
	
	@Override
	public List<HhOperationLog> getPortalLogList(HhOperationLog log, int offset, Integer pageSize) {
		return logDao.getPortalLogList(log, offset, pageSize);
	}
	
	@Override
	public MsgPage getPortalMsgLogList(HhOperationLog log, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = logDao.getAllRowCount(log);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HhOperationLog> list = logDao.getPortalLogList(log, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
}
