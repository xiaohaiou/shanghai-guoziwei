package com.softline.service.system.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.IWorkDao;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalWork;
import com.softline.entity.SysUsers;
import com.softline.service.system.IWorkService;

@Service("workService")
public class WorkService implements IWorkService {

	@Autowired
	@Qualifier("workDao")
	private IWorkDao workDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Override
	public List<PortalWork> getPortalList(SysUsers usersEntity,String year,String week) {
		//根据week获取该州开始日期与结束日期
		Calendar cal = Calendar.getInstance();   
		
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);        
        cal.set(Calendar.YEAR, Integer.parseInt(year));
		
        cal.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(week));
        String preYear = cal.get(Calendar.YEAR)+"";
        String preMonth = (cal.get(Calendar.MONTH) + 1)+"";
        String preDate = cal.get(Calendar.DAY_OF_MONTH)+"";
        if(preMonth.length() == 1){
        	preMonth = "0"+preMonth;
        }
        if(preDate.length() == 1){
        	preDate = "0"+preDate;
        }
        String beginDate = preYear+"-"+preMonth+"-"+preDate;
         
        cal.add(Calendar.DAY_OF_WEEK, 6);  
        String nextYear = cal.get(Calendar.YEAR)+"";
        String nextMonth = (cal.get(Calendar.MONTH) + 1)+"";
        String nextDate = cal.get(Calendar.DAY_OF_MONTH)+"";
        if(nextMonth.length() == 1){
        	nextMonth = "0"+nextMonth;
        }
        if(nextDate.length() == 1){
        	nextDate = "0"+nextDate;
        }
        String endDate = nextYear+"-"+nextMonth+"-"+nextDate;
		
		return workDao.getPortalList(usersEntity,beginDate,endDate);
	}
	
	@Override
	public boolean saveWork(PortalWork portalWork){
		try{
			commonDao.saveOrUpdate(portalWork);
			return true;
		}catch(Exception ex){
			Logger.getLogger(ex.getMessage());
			return false;
		}
	}

	@Override
	public List<PortalWork> getWorkListByCurDate(String curDate, String vcEmployee) {
		return workDao.getWorkListByCurDate(curDate, vcEmployee);
	}
}
