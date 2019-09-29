package com.softline.dao.portal;

import java.util.List;

import com.softline.entity.HhUsers;

public interface IHhUsersDao {

	/**
	 * 通过查询条件获取用户列表
	 * @param condi
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	List<HhUsers> getUsersByCondition(HhUsers condi,Integer offset,Integer pageSize);
	
	/**
	 * 根据查询条件获取总记录数
	 * @param condi
	 * @return
	 */
	Integer getUsersCountByCondition(HhUsers condi);
	
	
	List<HhUsers> getUsersByAccount(String account);
	
	List<HhUsers> getUsersByVcEmployee(String vcEmployee);
}
