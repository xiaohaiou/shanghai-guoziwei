package com.softline.service.settingCenter.user.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.softline.dao.settingCenter.user.IUserHrAndFinanceCompanyDao;
import com.softline.service.settingCenter.user.IUserHrAndFinanceCompanyService;
import com.softline.util.MsgPage;

@Service("userHrAndFinanceCompanyService")
public class UserHrAndFinanceCompanyService implements IUserHrAndFinanceCompanyService {
	
	@Autowired
	@Qualifier("userHrAndFinanceCompanyDao")
	private IUserHrAndFinanceCompanyDao userHrAndFinanceCompanyDao;

	@Override
	public MsgPage getUserCompanyList(String userName, String vcAccount,
			String orgs, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
        //总记录数
        Integer totalRecords = userHrAndFinanceCompanyDao.getUserCompanyList(userName, vcAccount, orgs, null, null).size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object[]> list = userHrAndFinanceCompanyDao.getUserCompanyList(userName,vcAccount,orgs, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
		
	}

	@Override
	public MsgPage getUserFinanceCompanyList(String userName, String vcAccount,
			String orgs, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
        //总记录数
        Integer totalRecords = userHrAndFinanceCompanyDao.getUserFinanceCompanyList(userName, vcAccount, orgs, null, null).size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object[]> list = userHrAndFinanceCompanyDao.getUserFinanceCompanyList(userName,vcAccount,orgs, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public List<Object[]> getUserCompanyList(String userName, String vcAccount,
			String orgs) {
		return userHrAndFinanceCompanyDao.getUserCompanyList(userName, vcAccount, orgs, null, null);
	}

	@Override
	public List<Object[]> getUserFinanceCompanyList(String userName,
			String vcAccount, String orgs) {
		return userHrAndFinanceCompanyDao.getUserFinanceCompanyList(userName, vcAccount, orgs, null, null);
	}
	
	

}
