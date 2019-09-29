package com.softline.service.bylaw.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.bylaw.IBylawDeptDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.bylaw.BylawDept;
import com.softline.service.bylaw.IBylawDeptService;
import com.softline.service.bylaw.IBylawInfoService;
@Service("bylawDeptService")
public class BylawDeptService implements IBylawDeptService{
	@Autowired
	@Qualifier("bylawDeptDao")
	private IBylawDeptDao bylawDeptDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Override
	public List<BylawDept> getAllBylawDept() {
		BylawDept condition = new BylawDept();
		return bylawDeptDao.getBylawDepts(condition, null, null);
	}
	
	
}
