package com.softline.service.project.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.softline.common.Common;
import com.softline.dao.project.IContractDao;
import com.softline.dao.project.IPjPcPayrecordDao;
import com.softline.dao.project.IProjectDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhUsers;
import com.softline.entity.project.PjApprove;
import com.softline.entity.project.PjContract;
import com.softline.entity.project.PjContractHistory;
import com.softline.entity.project.PjContractTemp;
import com.softline.entity.project.PjPcPayrecord;
import com.softline.entity.project.PjPcPayrecordTemp;
import com.softline.service.project.IContractService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;
@Service("contractService")
public class ContractService implements IContractService {
	
	
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("contractDao")
	private IContractDao contractDao;
	
	@Autowired
	@Qualifier("pjPcPayrecordDao")
	private IPjPcPayrecordDao pjPcPayrecordDao;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public MsgPage getContracts(Integer pjId, Integer curPageNum,
			Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<PjContract> t = contractDao.getContracts(pjId, null, null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<PjContract> list = contractDao.getContracts(pjId, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	@Override
	public String saveContractTemp(HhUsers user, String payRecordIds,PjContractTemp contractTemp,
			PjContractTemp oldEntity) {
		if(contractTemp.getId() != null && oldEntity !=null){//modify
			contractTemp.setCreateDate(oldEntity.getCreateDate());
			contractTemp.setCreatePersonId(oldEntity.getCreatePersonId());
			contractTemp.setCreatePersonName(oldEntity.getCreatePersonName());
			contractTemp.setLastModifyDate(df.format(new Date()));
			contractTemp.setLastModifyPersonId(user.getVcEmployeeId());
			contractTemp.setLastModifyPersonName(user.getVcName());
		}else{//new add
			contractTemp.setCreateDate(df.format(new Date()));
			contractTemp.setCreatePersonId(user.getVcEmployeeId());
			contractTemp.setCreatePersonName(user.getVcName());
		}
		contractTemp.setIsdel(0);
		contractTemp.setReportStatus(0);//未上报
		commonDao.saveOrUpdate(contractTemp);
		
		//付款记录关联
		if(Common.notEmpty(payRecordIds)){
			String[] pays = payRecordIds.split(",");
			for(int i = 0; i < pays.length; i++ ){
				PjPcPayrecordTemp payTemp = (PjPcPayrecordTemp) commonDao.findById(PjPcPayrecordTemp.class, Common.parseInteger(pays[i]));
				payTemp.setPcId(contractTemp.getId());
			}
		}
		String result = JSON.toJSONString(contractTemp);
		return result;
	}

	@Override
	public void deleteContractTemp(Integer contractTempId) {
		PjContractTemp entity = (PjContractTemp) commonDao.findById(PjContractTemp.class, contractTempId);
		commonDao.delete(entity);
		List<PjPcPayrecordTemp> payrecordTemps = pjPcPayrecordDao.getPayrecordTemps(contractTempId);
		for(int i = 0; i < payrecordTemps.size(); i++){
			PjPcPayrecordTemp t = payrecordTemps.get(i);
			commonDao.delete(t);
		}
	}

	@Override
	public void saveContract(HhUsers user, String payRecordIds,
			PjContract contract, PjContract oldEntity) {
		if(contract.getId() != null && oldEntity !=null){//modify
			contract.setCreateDate(oldEntity.getCreateDate());
			contract.setCreatePersonId(oldEntity.getCreatePersonId());
			contract.setCreatePersonName(oldEntity.getCreatePersonName());
			
			contract.setLastModifyDate(df.format(new Date()));
			contract.setLastModifyPersonId(user.getVcEmployeeId());
			contract.setLastModifyPersonName(user.getVcName());
			
			contract.setReportPersonId(oldEntity.getReportPersonId());
			contract.setReportPersonName(oldEntity.getReportPersonName());
			contract.setReportStatus(oldEntity.getReportStatus());
			contract.setReportTime(oldEntity.getReportTime());
			
			if(oldEntity.getReportStatus()>1){//审批通过或退回时变为未上报
				contract.setReportStatus(0);//未上报
			}
			
			contract.setVersion(oldEntity.getVersion());
			
			contract.setIsdel(0);
			commonDao.saveOrUpdate(contract);
			
		}else{//new add
			contract.setCreateDate(df.format(new Date()));
			contract.setCreatePersonId(user.getVcEmployeeId());
			contract.setCreatePersonName(user.getVcName());
			contract.setVersion(0);
			contract.setReportStatus(0);
			
			contract.setIsdel(0);
			commonDao.saveOrUpdate(contract);
			
			//付款记录关联
			if(Common.notEmpty(payRecordIds)){
				String[] pays = payRecordIds.split(",");
				for(int i = 0; i < pays.length; i++ ){
					PjPcPayrecordTemp payTemp = (PjPcPayrecordTemp) commonDao.findById(PjPcPayrecordTemp.class, Common.parseInteger(pays[i]));
					PjPcPayrecord pay = new PjPcPayrecord();
					BeanUtils.copyProperties(payTemp, pay);
					pay.setId(null);
					pay.setPcId(contract.getId());
					commonDao.saveOrUpdate(pay);
				}
			}
		}
		
	}

	@Override
	public List<PjContract> getContracts(Integer pjId, Integer reportStatus) {
		return contractDao.getContracts(pjId, reportStatus);
	}

	@Override
	public List<PjContractHistory> getContractHistories(Integer contractId) {
		// TODO Auto-generated method stub
		List<PjContractHistory> result = contractDao.getContractHistories(contractId);
		for(int i = 0; i < result.size();i++){
			PjContractHistory entity = result.get(i);
			PjApprove approve = (PjApprove) commonDao.findById(PjApprove.class, entity.getApproveId());
			entity.setApprove(approve);
		}
		return result;
	}

	@Override
	public void deleteContract(Integer id, HhUsers users) {
		// TODO Auto-generated method stub
		PjContract contract = (PjContract) commonDao.findById(PjContract.class, id);
		contract.setIsdel(1);
		contract.setLastModifyDate(df.format(new Date()));
		contract.setLastModifyPersonId(users.getVcEmployeeId());
		contract.setLastModifyPersonName(users.getVcName());
		
		
		
		PjContractHistory contractHistory = new PjContractHistory();
		BeanUtils.copyProperties(contract, contractHistory);
		contractHistory.setId(null);
		contractHistory.setContractId(contract.getId());
		commonDao.saveOrUpdate(contractHistory);
		
		contract.setVersion(contract.getVersion()==null?1:(contract.getVersion()+1));
	}
}
