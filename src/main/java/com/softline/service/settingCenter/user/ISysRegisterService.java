package com.softline.service.settingCenter.user;

import java.util.List;

import com.softline.entity.settingCenter.HhSysRegister;
import com.softline.util.MsgPage;

public interface ISysRegisterService {

	MsgPage getSysMsgRegisterList(String qSysNum, String qSysName, Integer curPageNum, Integer pageSize);

	HhSysRegister getTheSysRegister(Integer id);

	void saveOrUpdateSysRegister(HhSysRegister register);

	String delSysRegister(Integer id);

	Integer checkSysNum(String sysNum, Integer id);

	List<HhSysRegister> getRegistedList();

	List<HhSysRegister> getRegistedListByRoleId(Integer roleId);
	
}
