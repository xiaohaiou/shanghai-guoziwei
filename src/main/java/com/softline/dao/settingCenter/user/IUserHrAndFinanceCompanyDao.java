package com.softline.dao.settingCenter.user;

import java.util.List;

public interface IUserHrAndFinanceCompanyDao {
	
	/**
	 * 根据下面三个条件 查询有某个公司数据权限的用户   hr树权限
	 * @param userName
	 * @param vcAccount
	 * @param orgs
	 * @return
	 */
	List<Object[]> getUserCompanyList(String userName,String vcAccount,String orgs,Integer offset, Integer pageSize );
	
	/**
	 * 根据下面三个条件 查询有某个公司数据权限的用户    财务数权限
	 * @param userName
	 * @param vcAccount
	 * @param orgs
	 * @return
	 */
	List<Object[]> getUserFinanceCompanyList(String userName,String vcAccount,String orgs,Integer offset, Integer pageSize );
}
