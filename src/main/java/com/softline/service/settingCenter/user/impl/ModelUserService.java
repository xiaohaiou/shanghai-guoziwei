package com.softline.service.settingCenter.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.settingCenter.user.IModelUserDao;
import com.softline.entity.HhUsers;
import com.softline.entity.settingCenter.HhModelUser;
import com.softline.service.settingCenter.user.IModelUserService;
import com.softline.service.system.ICommonService;

@Service("modelUserService")
public class ModelUserService implements IModelUserService {
	@Autowired
	@Qualifier("modelUserDao")
	private IModelUserDao modelUserDao;
	
	@Resource(name = "commonService")
	private ICommonService commonService;

	@Override
	public void saveOrUpdateModelUser(HhModelUser modelUser, String vcEmployeeIds) {
		// TODO Auto-generated method stub
		//先删除原来表中的数据
		modelUserDao.deleteModelUser(modelUser.getModelId());
		//如果vcEmployeeIds不为""，再添加新的数据
		if (!"".equals(vcEmployeeIds) && vcEmployeeIds != null) {
			String [] vcEmployeeIdss = vcEmployeeIds.split(",");
			for (int i = 0; i < vcEmployeeIdss.length; i++) {
				HhModelUser newModelUser = new HhModelUser();
				newModelUser.setModelId(modelUser.getModelId());
				newModelUser.setVcEmployeeId(vcEmployeeIdss[i]);
				commonService.save(newModelUser);
			}
		}
	}

	@Override
	public List<HhUsers> getSelectHhUsersList(Integer modelId) {
		// TODO Auto-generated method stub
		return modelUserDao.getSelectHhUsersList(modelId);
	}
	
	@Override
	public Integer checkTheId(String vcEmployeeId, Integer modelId) {
		// TODO Auto-generated method stub
		return modelUserDao.checkTheId(vcEmployeeId, modelId);
	}

	@Override
	public HhModelUser getHhModelUserByVcEmployeeId(String vcEmployeeId) {
		return modelUserDao.getHhModelUserByVcEmployeeId(vcEmployeeId);
	}

}
