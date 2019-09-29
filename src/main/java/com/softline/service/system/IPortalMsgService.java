package com.softline.service.system;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;
import com.softline.util.MsgPage;

public interface IPortalMsgService {
	
	/**
	 * 
	 * 当提交审核时，在待办事项表中增加一条待办记录
	 * 
	 * */
	public boolean savePortalMsg(PortalMsg msg);

	/**
	 * 
	 * 当审核完成后时，更新待办记录
	 * 
	 * */
	public Integer updatePortalMsg(String table,String recordId);
	
	/**
	 * 
	 * 获取用户待办工作列表
	 * 
	 * */
	public List<PortalMsg> getProtalMsgList(String session_code,String session_company,HhUsers hhUsers);
	
	MsgPage getPortalMsgsList(PortalMsg portalMsg, Integer curPageNum, Integer pageSize);
	/**
	 * 包含处理与未处理数据
	 * @param portalMsg
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	MsgPage getPortalMsgsList2(PortalMsg portalMsg, Integer curPageNum, Integer pageSize);
	
	/**
	 * 
	 * @param session_code
	 * @param session_company
	 * @param hhUsers
	 * @param portalMsg
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	MsgPage getPortalMsgsList3(String session_code,String session_company,HhUsers hhUsers,PortalMsg portalMsg, Integer curPageNum, Integer pageSize);
}
