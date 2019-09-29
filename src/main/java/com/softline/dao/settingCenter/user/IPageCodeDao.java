package com.softline.dao.settingCenter.user;

import java.util.List;

import com.softline.entity.HhPagecode;
import com.softline.entity.settingCenter.PortalHhPagecode;

public interface IPageCodeDao {

	Integer getAllRowCount(String qCodeName, String qModelId, String qPageId);

	List<PortalHhPagecode> getPageCodeList(String qCodeName, String qModelId, String qPageId, int offset,
			Integer pageSize);

	PortalHhPagecode getThePageCode(Integer id);

	void saveOrUpdatePageCode(PortalHhPagecode register);

	void deletePageCode(Integer id);

	List<PortalHhPagecode> getCodeList(Integer sysId, Integer pageId);

	List<PortalHhPagecode> getPagecodesByRoleId(Integer roleId);

	List<PortalHhPagecode> getPagecodesByModelIdAndRoleId(Integer id, Integer roleId);

	Integer checkPagecodeNum(String pagecodeNum, Integer id);
	
	Integer getPageCodePersonlistCount(Integer id,String vcName,String vcAccount);

	List<Object[]> getPageCodePersonlist(Integer id, String vcName,String vcAccount,Integer offset,Integer pageSize);

	//Integer checkSysNum(String sysNum);

}
