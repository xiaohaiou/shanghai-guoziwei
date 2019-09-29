package com.softline.service.system.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Common;
import com.softline.dao.portal.IMsgDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;
import com.softline.service.system.IPortalMsgService;
import com.softline.util.MsgPage;

@Service("potalMsgService")
public class PortalMsgService implements IPortalMsgService {

	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("msgDao")
	private IMsgDao msgDao;
	
	@Override
	public boolean savePortalMsg(PortalMsg msg){
		try{
			commonDao.saveOrUpdate(msg);
			return true;
		}catch(Exception ex){
			Logger.getLogger(ex.getMessage());
			return false;
		}
	}

	@Override
	public Integer updatePortalMsg(String table,String recordId) {
		try{
			return msgDao.updatePortalMsg(table, recordId);
		}catch(Exception ex){
			Logger.getLogger(ex.getMessage());
			return -1;
		}
	}
	
	@Override
	public List<PortalMsg> getProtalMsgList(String session_code,String session_company,HhUsers hhUsers){
		return msgDao.getProtalMsgList(session_code, session_company, hhUsers);
	}

	@Override
	public MsgPage getPortalMsgsList(PortalMsg portalMsg, Integer curPageNum,
			Integer pageSize) {
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
    			if(Common.notEmpty(temp.getTitle())){
    				if(temp.getTitle().length() > 20){
        				temp.setTitle(temp.getTitle().substring(0, 20)+"...");
        			}
    			}
    			if (Common.notEmpty(temp.getDescription())) {
    				if(temp.getDescription().length() > 45){
    					temp.setDescription(temp.getDescription().substring(0,45)+"...");
    				}
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
	public MsgPage getPortalMsgsList2(PortalMsg portalMsg, Integer curPageNum,
			Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = msgDao.getAllRowsCount(portalMsg);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<PortalMsg> list = msgDao.getPortalMsgsList(portalMsg, offset, pageSize);
    	if(!list.isEmpty()){
    		for (int i = 0; i < list.size(); i++) {
    			PortalMsg temp = list.get(i);
    			if(Common.notEmpty(temp.getTitle())){
    				temp.setTitle(temp.getTitle());
    			}
    			if (Common.notEmpty(temp.getDescription())) {
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
	public MsgPage getPortalMsgsList3(String session_code,
			String session_company, HhUsers hhUsers, PortalMsg portalMsg,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        List<PortalMsg> result = msgDao.getProtalMsgList(session_code, session_company, hhUsers,portalMsg, null, null);
        Integer totalRecords = result.size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<PortalMsg> list = msgDao.getProtalMsgList(session_code, session_company, hhUsers,portalMsg, offset, pageSize);
    	if(!list.isEmpty()){
    		for (int i = 0; i < list.size(); i++) {
    			PortalMsg temp = list.get(i);
    			if(Common.notEmpty(temp.getTitle())){
    				temp.setTitle(temp.getTitle());
    			}
    			if (Common.notEmpty(temp.getDescription())) {
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
}
