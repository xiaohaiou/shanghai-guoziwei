package com.softline.dao.bylaw;

import java.util.List;

import com.softline.entity.bylaw.BylawDept;

public interface IBylawDeptDao {
	/**
	 * 查询规章制度的体系类别
	 * @param condition 查询条件实体
	 * @param offset 记录开始位置
	 * @param pageSize 每页显示位置
	 * @return
	 */
	List<BylawDept> getBylawDepts(BylawDept condition, Integer offset,Integer pageSize);
}
