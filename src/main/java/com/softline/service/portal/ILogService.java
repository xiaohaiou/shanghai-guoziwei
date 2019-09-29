package com.softline.service.portal;

import java.util.List;

import com.softline.entity.HhOperationLog;
import com.softline.entity.PortalNews;
import com.softline.util.MsgPage;

public interface ILogService {

	public List<HhOperationLog> getPortalLogList(HhOperationLog log, int offset, Integer pageSize);
	
	MsgPage getPortalMsgLogList(HhOperationLog log, Integer curPageNum, Integer pageSize);
	
}
