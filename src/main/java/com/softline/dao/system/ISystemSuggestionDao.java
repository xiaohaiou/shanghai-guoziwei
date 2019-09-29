package com.softline.dao.system;

import java.util.List;

import com.softline.entity.SysSuggestion;

public interface ISystemSuggestionDao {
	
	/**
	 * 根据查询得到建议反馈记录
	 * @param condition 查询条件
	 * @return
	 */
	List<SysSuggestion> getSysSuggestions(SysSuggestion condition,Integer offset,Integer pageSize);
	
}
