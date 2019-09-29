package com.softline.dao.portal;

import java.util.List;

import com.softline.entity.HhOperationLog;
import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;

public interface ILogDao {

	List<HhOperationLog> getPortalLogList(HhOperationLog log, int offset, Integer pageSize);

	public Integer getAllRowCount(HhOperationLog log);
}
