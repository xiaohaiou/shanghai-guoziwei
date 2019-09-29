package com.softline.service.settingCenter.user;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhModelUser;

public interface IModelUserService {

	void saveOrUpdateModelUser(HhModelUser modelUser, String vcEmployeeIds);

	List<HhUsers> getSelectHhUsersList(Integer modelId);
	
	Integer checkTheId(String vcEmployeeId, Integer modelId);

	HhModelUser getHhModelUserByVcEmployeeId(String vcEmployeeId);

}
