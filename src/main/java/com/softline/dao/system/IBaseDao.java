package com.softline.dao.system;

import java.util.List;

import com.softline.entity.HhBase;

public interface IBaseDao {
	
	public List<HhBase> getBases(Integer type);
	public List<HhBase> getChildBases(Integer parentID);
	public  HhBase   getBase(Integer id);
	
	public List<HhBase> getUserBusinessFormat(String ComIDs);//获取当前用户所属业态
	
	
	public String getOtherBase(String hasStr,int type);
	
	//读取type相同，但是ID not in (hasStr)的内容
	public List<HhBase> getOtherBaseList(String hasStr,int type);
}
