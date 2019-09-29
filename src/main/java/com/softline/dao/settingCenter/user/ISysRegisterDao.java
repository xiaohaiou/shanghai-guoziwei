package com.softline.dao.settingCenter.user;

import java.util.List;

import com.softline.entity.settingCenter.HhSysRegister;

public interface ISysRegisterDao {

	Integer getAllRowCount(String qSysNum, String qSysName);

	List<HhSysRegister> getSysRegisterList(String qSysNum, String qSysName, int offset,
			Integer pageSize);

	HhSysRegister getTheSysRegister(Integer id);

	void saveOrUpdateSysRegister(HhSysRegister register);

	void deleteSysRegister(Integer id);

	Integer checkSysNum(String sysNum, Integer id);

	List<HhSysRegister> getRegistedList();

	List<HhSysRegister> getRegistedListByRoleId(Integer roleId);

}
