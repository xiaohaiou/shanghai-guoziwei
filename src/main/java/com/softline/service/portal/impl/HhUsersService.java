package com.softline.service.portal.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.portal.IHhUsersDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.impl.CommonDao;
import com.softline.entity.HhUsers;
import com.softline.service.portal.IHhUsersService;
import com.softline.util.MsgPage;
@Service("hhUsersService")
public class HhUsersService implements IHhUsersService {

	@Autowired
	@Qualifier("hhUsersDao")
	private IHhUsersDao hhUsersDao; 
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	
	@Override
	public MsgPage getHhUsersList(HhUsers condi, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = hhUsersDao.getUsersCountByCondition(condi);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HhUsers> list = hhUsersDao.getUsersByCondition(condi, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}


	@Override
	public boolean vaildateAccountUnique(Integer id, String account) {
		List<HhUsers> users = hhUsersDao.getUsersByAccount(account);
		int size = users.size();
		if(id != null) {
			HhUsers user = (HhUsers) commonDao.findById(HhUsers.class, id);
			if(!account.equals(user.getVcAccount())) {
				if(size > 0) {
					return false;
				}
			}
		}else {
			if(size > 0) {
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean validateEmployeeIdUnique(Integer id, String vcEmployeeId) {
		List<HhUsers> users = hhUsersDao.getUsersByVcEmployee(vcEmployeeId);
		int size = users.size();
		if(id != null) {
			HhUsers user = (HhUsers) commonDao.findById(HhUsers.class, id);
			if(!vcEmployeeId.equals(user.getVcAccount())) {
				if(size > 0) {
					return false;
				}
			}
		}else {
			if(size > 0) {
				return false;
			}
		}
		return true;
	}

}
