package com.softline.service.riskcontrol.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.dao.riskcontrol.IRiskControlContractDao;
import com.softline.entity.RiskControlContract;
import com.softline.entity.HhUsers;
import com.softline.service.riskcontrol.IRiskControlContractService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Service("riskControlContractService")
public class RiskControlContractService implements IRiskControlContractService {

	@Autowired
	@Qualifier("riskControlContractDao")
	private IRiskControlContractDao riskControlContractDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	

	@Override
	public MsgPage getContracts(RiskControlContract entity, Integer curPageNum,
			Integer pageSize, String contractSignDate2, String dataAuthority) {
			MsgPage msgPage = new MsgPage();       
	        //总记录数
	        Integer totalRecords = riskControlContractDao.getContractListCount(entity, contractSignDate2, dataAuthority);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<RiskControlContract> list = riskControlContractDao.getContractList(entity, offset, pageSize, contractSignDate2, dataAuthority);
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);    
	        return msgPage;
	}

	@Override
	public Integer saveContract(RiskControlContract entity) {
		return riskControlContractDao.saveContract(entity);
	}

	@Override
	public void deleteContract(Integer id) {
		riskControlContractDao.deleteContract(id);
	}

	@Override
	public RiskControlContract getContract(Integer id) {
		return riskControlContractDao.getContractById(id);
	}

	@Override
	public void updateContract(RiskControlContract entity) {
		riskControlContractDao.updateContract(entity);
	}

	@Override
	public RiskControlContract getContract(RiskControlContract entity) {
		return riskControlContractDao.getContract(entity);
	}

	@Override
	public void saveContractExamine(Integer entityId, String examStr, 
			Integer examResult, HhUsers user) {
		RiskControlContract entity = getContract(entityId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//审核不通过
		if(examResult.equals(Base.examresult_1)){
			entity.setStatus(Base.examstatus_3);
		}else if(examResult.equals(Base.examresult_2)){ //审核通过
			entity.setStatus(Base.examstatus_4);
		}
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(user.getVcEmployeeId());
		entity.setAuditorPersonName(user.getVcName());
		updateContract(entity);
		examineService.saveExamine(entity.getId(), Base.examkind_riskcontrol_contract, user, examStr, examResult);
	}

	@Override
	public MsgPage getExamineContracts(RiskControlContract entity, Integer curPageNum, 
			Integer pageSize, String contractSignDate2, String dataAuthority) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = riskControlContractDao.getExamineContractListCount(entity, contractSignDate2, dataAuthority);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<RiskControlContract> list = riskControlContractDao.getExamineContractList(entity, offset, pageSize, contractSignDate2, dataAuthority);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
}
