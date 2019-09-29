package com.softline.service.portal;

import com.softline.entity.PortalMsg;
import com.softline.util.MsgPage;

public interface IMsgService {

	void save(PortalMsg portalMsg);

	void delete(PortalMsg portalMsg);
	/**
	 * 
	 * @param portalMsg
	 * @param curPageNum 当前页
	 * @param pageSize 每页显示的数量
	 * @return
	 */
	MsgPage getPortalMsgList(PortalMsg portalMsg, Integer curPageNum, Integer pageSize);
	
}
