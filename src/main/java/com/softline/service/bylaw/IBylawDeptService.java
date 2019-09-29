package com.softline.service.bylaw;

import java.util.List;

import com.softline.entity.bylaw.BylawDept;

public interface IBylawDeptService {
	
	/**
	 * 获取所有的体系类别
	 * @return
	 */
	List<BylawDept> getAllBylawDept();
	
}
