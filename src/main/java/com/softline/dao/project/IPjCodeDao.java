package com.softline.dao.project;

import java.util.List;

import com.softline.entity.project.PjCode;

public interface IPjCodeDao {
	
	/**
	 * 查询
	 * @param codeType 类型
	 * @return 列表
	 */
	List<PjCode> getPjCodes(Integer codeType);
}
