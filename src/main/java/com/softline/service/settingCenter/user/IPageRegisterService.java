package com.softline.service.settingCenter.user;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.softline.entity.settingCenter.HhPageregister;
import com.softline.util.MsgPage;

public interface IPageRegisterService {

	MsgPage getPageMsgRegisterList(String qPageNum, String qPageName, String qModelId, Integer curPageNum, Integer pageSize);

	HhPageregister getThePageRegister(Integer id);

	void saveOrUpdatePageRegister(HhPageregister register);

	String deletePageRegister(Integer id);

	List<HhPageregister> getRegistedList();

	List<HhPageregister> getPagesByModelId(Integer modelId);

	List<HhPageregister> getPagesByRoleId(Integer id);

	List<HhPageregister> getRolePageListByModelIdAndRoleId(Integer modelId,
			Integer roleId);

	Integer checkPageNum(String pageNum, Integer id);
	MsgPage getPageMsgPersonList(Integer modelId, String vcName, String vcAccount, Integer curPageNum, Integer pageSize);
	
	XSSFWorkbook getPageMsgPersonListExport(Integer modelId, String vcName, String vcAccount);
}
