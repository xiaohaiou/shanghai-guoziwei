package com.softline.dao.settingCenter.user;

import java.util.List;

import com.softline.entity.settingCenter.HhModelRegister;

public interface IModelRegisterDao {

	Integer getAllRowCount(String qModelNum, String qModelName, String qSysId);

	List<HhModelRegister> getModelRegisterList(String qModelNum, String qModelName, String qSysId, int offset,
			Integer pageSize);

	HhModelRegister getTheModelRegister(Integer id);

	void saveOrUpdateModelRegister(HhModelRegister register);

	void deleteModelRegister(Integer id);

	List<HhModelRegister> getRegistedList();

	List<HhModelRegister> getModelsBySysId(Integer sysId);

	List<HhModelRegister> getModelsByRoleId(Integer id);

	List<HhModelRegister> getRegistedModelList(Integer roleId);

	List<HhModelRegister> getRegistedModelListBySysId(Integer sysId);

	List<HhModelRegister> getRoleModelListBySysIdAndRoleId(Integer sysId,
			Integer roleId);

	Integer checkModelNum(String modelNum, Integer id);
	
	Integer getModelPersonlistCount( Integer id,String vcName,String vcAccount);
	
	List<Object[]> getModelPersonlist( Integer id, String vcName,String vcAccount,Integer offset,Integer pageSize);

	//Integer checkSysNum(String sysNum);

}
