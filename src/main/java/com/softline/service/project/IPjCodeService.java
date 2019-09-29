package com.softline.service.project;

import java.util.List;

import com.softline.entity.project.PjCode;

public interface IPjCodeService {
	/**
	 * 查询
	 * @param codeType 类型
	 * @return 列表
	 */
	List<PjCode> getPjCodes(Integer codeType);
}
