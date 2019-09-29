package com.softline.dao.project;

import java.util.List;

import com.softline.entity.project.PjAuthorityPerson;

public interface IPjAuthorityPersonDao {
	
	/**
	 * 根据查询条件获取权限人
	 * @param condition 查询条件
	 * @return
	 */
	List<PjAuthorityPerson> getPjAuthorityPerson(PjAuthorityPerson condition,Integer offset,Integer pageSize);
}
