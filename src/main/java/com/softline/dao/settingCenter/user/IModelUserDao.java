package com.softline.dao.settingCenter.user;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhModelUser;

public interface IModelUserDao {

	void deleteModelUser(Integer modelId);

	List<HhUsers> getSelectHhUsersList(Integer modelId);
	
	Integer checkTheId(String vcEmployeeId, Integer modelId);

	HhModelUser getHhModelUserByVcEmployeeId(String vcEmployeeId);

}
