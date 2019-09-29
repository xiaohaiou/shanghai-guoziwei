package com.softline.service.settingCenter.user;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.util.MsgPage;

public interface IModelRegisterService {

	MsgPage getModelMsgRegisterList(String qModelNum, String qModelName, String qSysId, Integer curPageNum, Integer pageSize);

	HhModelRegister getTheModelRegister(Integer id);

	void saveOrUpdateModelRegister(HhModelRegister register);

	String deleteModelRegister(Integer id);

	List<HhModelRegister> getRegistedList();

	List<HhModelRegister> getModelsBySysId(Integer sysId);

	List<HhModelRegister> getModelsByRoleId(Integer id);

	Integer checkModelNum(String modelNum, Integer id);

	List<HhModelRegister> getRegistedModelList(Integer RoleId);

	List<HhModelRegister> getRegistedModelListBySysId(Integer sysId);

	List<HhModelRegister> getRoleModelListBySysIdAndRoleId(Integer sysId,
			Integer roleId);
	
	MsgPage getModelMsgPersonList(Integer modelId, String vcName, String vcAccount, Integer curPageNum, Integer pageSize);
	
	XSSFWorkbook getModelMsgPersonListExport(Integer modelId, String vcName, String vcAccount);
}
