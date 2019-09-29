package com.softline.service.system.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.system.IBaseDao;
import com.softline.entity.HhBase;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ISystemService;

@Service("baseService")
public class BaseService implements IBaseService{
	@Autowired
	@Qualifier("baseDao")
	private IBaseDao baseDao;
	@Resource(name = "systemService")
	private ISystemService systemService;
	//获取某种类型的枚举对象
	@Override
	public List<HhBase> getBases(Integer type) {
		// TODO Auto-generated method stub
		return baseDao.getBases(type);
	}
	
	
	//获取某个枚举值的子对象
	@Override
	public List<HhBase> getChildBases(Integer parentID)
	{
		
		// TODO Auto-generated method stub
		return baseDao.getChildBases( parentID);
	}
	
	//获取某个枚举ID对应的对象
	public HhBase getBase(Integer id) {
		return baseDao.getBase(id);
	}
	
	
	public String getOtherBase(String hasStr,int type)
	{
		return baseDao.getOtherBase(hasStr,type);
	}
	
	//读取type相同，但是ID not in (hasStr)的内容
	public List<HhBase> getOtherBaseList(String hasStr,int type)
	{
		return baseDao.getOtherBaseList(hasStr,type);
	}
}
