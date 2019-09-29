package com.softline.service.settingCenter.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.settingCenter.user.ISysRegisterDao;
import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.entity.settingCenter.HhSysRegister;
import com.softline.service.settingCenter.user.IModelRegisterService;
import com.softline.service.settingCenter.user.ISysRegisterService;

import com.softline.util.MsgPage;

@Service("sysRegisterService")
public class SysRegisterService implements ISysRegisterService {
	@Autowired
	@Qualifier("sysRegisterDao")
	private ISysRegisterDao sysRegisterDao;
	
	@Resource(name = "modelRegisterService")
	private IModelRegisterService modelRegisterService;

	@Override
	public MsgPage getSysMsgRegisterList(String qSysNum, String qSysName, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = sysRegisterDao.getAllRowCount(qSysNum, qSysName);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HhSysRegister> list = sysRegisterDao.getSysRegisterList(qSysNum, qSysName, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public HhSysRegister getTheSysRegister(Integer id) {
		// TODO Auto-generated method stub
		return sysRegisterDao.getTheSysRegister(id);
	}

	@Override
	public void saveOrUpdateSysRegister(HhSysRegister register) {
		// TODO Auto-generated method stub
		sysRegisterDao.saveOrUpdateSysRegister(register);
	}

	@Override
	public String delSysRegister(Integer id) {
		// TODO Auto-generated method stub
		//先查询此系统是否绑定模板
		List<HhModelRegister> list = modelRegisterService.getModelsBySysId(id);
		if (!list.isEmpty()) {
			return "false";
		}else {
			sysRegisterDao.deleteSysRegister(id);
			return "success";
		}
	}

	@Override
	public Integer checkSysNum(String sysNum, Integer id) {
		// TODO Auto-generated method stub
		return sysRegisterDao.checkSysNum(sysNum, id);
	}

	@Override
	public List<HhSysRegister> getRegistedList() {
		// TODO Auto-generated method stub
		return sysRegisterDao.getRegistedList();
	}

	@Override
	public List<HhSysRegister> getRegistedListByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return sysRegisterDao.getRegistedListByRoleId(roleId);
	}

}
