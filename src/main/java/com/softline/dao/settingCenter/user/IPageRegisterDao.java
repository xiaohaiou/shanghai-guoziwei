package com.softline.dao.settingCenter.user;

import java.util.List;

import com.softline.entity.settingCenter.HhPageregister;

public interface IPageRegisterDao {

	Integer getAllRowCount(String qPageNum, String qPageName, String qModelId);

	List<HhPageregister> getPageRegisterList(String qPageNum, String qPageName, String qModelId, int offset,
			Integer pageSize);

	HhPageregister getThePageRegister(Integer id);

	void saveOrUpdatePageRegister(HhPageregister register);

	void deletePageRegister(Integer id);

	List<HhPageregister> getRegistedList();

	List<HhPageregister> getPagesByModelId(Integer modelId);

	void deletePageCodeByPageId(Integer id);

	List<HhPageregister> getPagesByRoleId(Integer id);

	List<HhPageregister> getRolePageListByModelIdAndRoleId(Integer modelId,
			Integer roleId);

	Integer checkPageNum(String pageNum, Integer id);

	Integer getPagePersonlistCount(Integer id,String vcName,String vcAccount);

	List<Object[]> getPagePersonlist(Integer id, String vcName,String vcAccount,Integer offset,Integer pageSize);

}
