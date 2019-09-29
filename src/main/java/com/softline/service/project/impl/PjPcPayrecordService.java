package com.softline.service.project.impl;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.softline.dao.project.IPjPcPayrecordDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhUsers;
import com.softline.entity.project.PjPcPayrecord;
import com.softline.entity.project.PjPcPayrecordTemp;
import com.softline.service.project.IPjPcPayrecordService;
@Service("pjPcPayrecordService")
public class PjPcPayrecordService implements IPjPcPayrecordService{
	@Autowired
	@Qualifier("pjPcPayrecordDao")
	private IPjPcPayrecordDao pjPcPayrecordDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public List<PjPcPayrecordTemp> getPayrecordTemps(Integer contractTempId) {
		// TODO Auto-generated method stub
		return pjPcPayrecordDao.getPayrecordTemps(contractTempId);
	}
	
	@Override
	public String savePayRecordTemp(HhUsers user,
			PjPcPayrecordTemp payRecord, PjPcPayrecordTemp oldEntity) {
		if(payRecord.getId() != null && oldEntity != null){//modify
			payRecord.setCreateDate(df.format(new Date()));
			payRecord.setCreatePersonId(oldEntity.getCreatePersonId());
			payRecord.setCreatePersonName(oldEntity.getCreatePersonName());
			payRecord.setLastModifyDate(df.format(new Date()));
			payRecord.setLastModifyPersonName(user.getVcName());
			payRecord.setLastModifyPersonId(user.getVcEmployeeId());
		}else{//new add
			payRecord.setCreateDate(df.format(new Date()));
			payRecord.setCreatePersonId(user.getVcEmployeeId());
			payRecord.setCreatePersonName(user.getVcName());
		}
		payRecord.setIsdel(0);
		commonDao.saveOrUpdate(payRecord);
		String result = JSON.toJSONString(payRecord);
		return result;
	}

	@Override
	public List<PjPcPayrecord> getPayrecords(Integer contractId) {
		return pjPcPayrecordDao.getPayrecords(contractId);
	}

	@Override
	public void savePayRecord(HhUsers user, PjPcPayrecord payRecord,
			PjPcPayrecord oldEntity) {
		if(payRecord.getId() != null && oldEntity != null){//modify
			payRecord.setCreateDate(oldEntity.getCreateDate());
			payRecord.setCreatePersonId(oldEntity.getCreatePersonId());
			payRecord.setCreatePersonName(oldEntity.getCreatePersonName());
			
			payRecord.setLastModifyDate(df.format(new Date()));
			payRecord.setLastModifyPersonName(user.getVcName());
			payRecord.setLastModifyPersonId(user.getVcEmployeeId());
			
		}else{//new add
			payRecord.setCreateDate(df.format(new Date()));
			payRecord.setCreatePersonId(user.getVcEmployeeId());
			payRecord.setCreatePersonName(user.getVcName());
		}
		payRecord.setIsdel(0);
		commonDao.saveOrUpdate(payRecord);
		
	}


}
