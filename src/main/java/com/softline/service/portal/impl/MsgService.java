package com.softline.service.portal.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.portal.IMsgDao;
import com.softline.entity.PortalMsg;
import com.softline.service.portal.IMsgService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Service("msgService")
public class MsgService implements IMsgService {
	@Autowired
	@Qualifier("msgDao")
	private IMsgDao msgDao;
	
	@Autowired
	@Qualifier("commonService")
	private ICommonService commonService;
	
	@Override
	public void save(PortalMsg portalMsg) {
		commonService.saveOrUpdate(portalMsg);
	}

	@Override
	public void delete(PortalMsg portalMsg) {
		commonService.saveOrUpdate(portalMsg);
	}
	
	@Override
	public MsgPage getPortalMsgList(PortalMsg portalMsg, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = msgDao.getAllRowCount(portalMsg);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<PortalMsg> list = msgDao.getPortalMsgList(portalMsg, offset, pageSize);
    	if(!list.isEmpty()){
    		for (int i = 0; i < list.size(); i++) {
    			PortalMsg temp = list.get(i);
    			if(temp.getTitle().length() > 20){
    				temp.setTitle(temp.getTitle().substring(0, 20)+"...");
    			}
				if(temp.getDescription().length() > 45){
					temp.setDescription(temp.getDescription().substring(0,45)+"...");
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
}
