package com.softline.service.system;


import java.util.List;

import com.softline.entity.HhBase;

public interface IBaseService {
	
	//根据type读取信息
	public List<HhBase> getBases(Integer type);
	//根据parent读取子信息
	public List<HhBase> getChildBases(Integer parentID);
	//根据ID读取
	public HhBase getBase(Integer type);
	//读取type相同，但是ID not in (hasStr)的内容
	public String getOtherBase(String hasStr,int type);
	
	//读取type相同，但是ID not in (hasStr)的内容
	public List<HhBase> getOtherBaseList(String hasStr,int type);
} 
