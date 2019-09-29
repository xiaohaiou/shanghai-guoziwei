package com.softline.service.project.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.project.IPjAuthorityPersonDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhUsers;
import com.softline.entity.project.PjAuthorityPerson;
import com.softline.entity.project.PjProject;
import com.softline.service.project.IPjAuthorityPersonService;
import com.softline.util.MsgPage;
@Service("pjAuthorityPersonService")
public class PjAuthorityPersonService implements IPjAuthorityPersonService {

	@Autowired
	@Qualifier("pjAuthorityPersonDao")
	private IPjAuthorityPersonDao pjAuthorityPersonDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	

	@Override
	public MsgPage getPjAuthorityPersons(PjAuthorityPerson condition,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<PjAuthorityPerson> t = pjAuthorityPersonDao.getPjAuthorityPerson(condition, null, null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<PjAuthorityPerson> list = pjAuthorityPersonDao.getPjAuthorityPerson(condition, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public void saveAddAuthorityPerson(HhUsers user, PjAuthorityPerson entity) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		entity.setCreateDate(df.format(new Date()));
		entity.setCreatePersonId(user.getVcEmployeeId());
		entity.setCreatePersonName(user.getVcName());
		entity.setIsdel(0);
		commonDao.saveOrUpdate(entity);
		
	}

	@Override
	public void saveModifyAuthorityPerson(HhUsers user,
			PjAuthorityPerson entity, PjAuthorityPerson oldEntity) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		entity.setCreateDate(oldEntity.getCreateDate());
		entity.setCreatePersonId(oldEntity.getCreatePersonId());
		entity.setCreatePersonName(oldEntity.getCreatePersonName());
		entity.setIsdel(0);
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(user.getVcEmployeeId());
		entity.setLastModifyPersonName(user.getVcName());
		
		commonDao.saveOrUpdate(entity);
	}

	@Override
	public void deleteAuthorityPerson(HhUsers user, Integer id) {
		
		PjAuthorityPerson entity = (PjAuthorityPerson) commonDao.findById(PjAuthorityPerson.class, id);
		entity.setIsdel(1);//删除
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(user.getVcEmployeeId());
		entity.setLastModifyPersonName(user.getVcName());
		
	}
	
	

}
