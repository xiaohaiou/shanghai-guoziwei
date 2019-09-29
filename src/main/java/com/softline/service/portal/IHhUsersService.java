package com.softline.service.portal;

import com.softline.entity.HhUsers;
import com.softline.util.MsgPage;

public interface IHhUsersService {
	/**
	 * 分页查询用户列表
	 * @param condi
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	MsgPage getHhUsersList(HhUsers condi, Integer curPageNum, Integer pageSize);
	
	/**
	 * 验证账号的唯一性
	 * 不唯一返回false，唯一返回true
	 * @param id
	 * @param account
	 * @return 
	 */
	boolean vaildateAccountUnique(Integer id,String account);
	
	/**
	 * 验证工号的唯一性
	 * @param id
	 * @param vcEmployeeId
	 * @return
	 */
	boolean validateEmployeeIdUnique(Integer id,String vcEmployeeId);
	
	
}
