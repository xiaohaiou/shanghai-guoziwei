package com.softline.dao.system;

import java.util.List;

import com.softline.entity.PortalWork;
import com.softline.entity.SysUsers;


public interface IWorkDao {
	public List<PortalWork> getPortalList(SysUsers usersEntity,String beginDate,String endDate);

	public List<PortalWork> getWorkListByCurDate(String curDate, String VcEmployeeId);
}
