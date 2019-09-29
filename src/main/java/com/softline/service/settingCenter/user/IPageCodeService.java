package com.softline.service.settingCenter.user;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.softline.entity.HhPagecode;
import com.softline.entity.settingCenter.PortalHhPagecode;
import com.softline.util.MsgPage;

public interface IPageCodeService {

	MsgPage getPageMsgCodeList(String qCodeName, String qModelId, String qPageId, Integer curPageNum, Integer pageSize);

	PortalHhPagecode getThePageCode(Integer id);

	void saveOrUpdatePageCode(PortalHhPagecode register);

	String deletePageCode(Integer id);
	/**
	 * 获取页面的所有功能
	 * @param modelId 模板id
	 * @param pageId 页面id
	 * @return
	 */
	public List<PortalHhPagecode> getCodeList(Integer sysId, Integer pageId);

	List<PortalHhPagecode> getPagecodesByRoleId(Integer roleId);

	List<PortalHhPagecode> getPagecodesByModelIdAndRoleId(Integer id, Integer roleId);

	Integer checkPagecodeNum(String pagecodeNum, Integer id);
	MsgPage getPageCodePersonList(Integer modelId, String vcName, String vcAccount, Integer curPageNum, Integer pageSize);

	XSSFWorkbook getPageCodePersonListExport(Integer modelId, String vcName,String vcAccount);
	

	//Integer checkSysNum(String sysNum);
	
}
