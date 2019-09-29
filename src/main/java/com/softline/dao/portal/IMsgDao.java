package com.softline.dao.portal;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;

public interface IMsgDao {

	//MsgPage getPortalMsgList(PortalMsg portalMsg, Integer curPageNum, Integer pageSize);
	
	Integer getAllRowCount(PortalMsg portalMsg);
	
	Integer getAllRowsCount(PortalMsg portalMsg);
	
	List<PortalMsg> getPortalMsgList(PortalMsg portalMsg, int offset, int pageSize);
	
	List<PortalMsg> getPortalMsgsList(PortalMsg portalMsg, int offset, int pageSize);
	
	public Integer updatePortalMsg(String table,String recordId);
	
	/**
	 * 根据表名和记录ID获取唯一记录
	 * @param table 表名
	 * @param recordId 表中记录的ID
	 * @return {PortalMsg}
	 */
	public PortalMsg getPortMsg(String table,String recordId);
	
	public List<PortalMsg> getProtalMsgList(String session_code,String session_company,HhUsers hhUsers);
	
	public List<PortalMsg> getProtalMsgList(String session_code,String session_company,HhUsers hhUsers,PortalMsg portalMsg, Integer offset, Integer pageSize);
}
