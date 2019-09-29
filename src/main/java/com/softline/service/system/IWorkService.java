package com.softline.service.system;

import java.util.List;

import com.softline.entity.PortalWork;
import com.softline.entity.SysUsers;

public interface IWorkService {

	
	public List<PortalWork> getPortalList(SysUsers usersEntity, String year, String week);
	
	public boolean saveWork(PortalWork portalWork);

	public List<PortalWork> getWorkListByCurDate(String curDate,String vcEmployee);
	
	
}
