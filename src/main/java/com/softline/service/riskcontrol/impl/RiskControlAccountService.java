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
import com.softline.dao.riskcontrol.IRiskControlAccountDao;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.RiskControlAccount;
import com.softline.entity.HhUsers;
import com.softline.service.riskcontrol.IRiskControlAccountService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Service("riskControlAccountService")
public class RiskControlAccountService implements IRiskControlAccountService {

	@Autowired
	@Qualifier("riskControlAccountDao")
	private IRiskControlAccountDao riskControlAccountDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	

	@Override
	public MsgPage getAccounts(RiskControlAccount entity, Integer curPageNum,
			Integer pageSize, String complainReceiveDate2, String dataAuthority) {
			MsgPage msgPage = new MsgPage();       
	        //总记录数
	        Integer totalRecords = riskControlAccountDao.getAccountListCount(entity,complainReceiveDate2, dataAuthority);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<RiskControlAccount> list = riskControlAccountDao.getAccountList(entity, offset, pageSize, complainReceiveDate2, dataAuthority);
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);    
	        return msgPage;
	}

	@Override
	public Integer saveAccount(RiskControlAccount entity) {
		return riskControlAccountDao.saveAccount(entity);
	}

	@Override
	public void deleteAccount(Integer id) {
		riskControlAccountDao.deleteAccount(id);
	}

	@Override
	public RiskControlAccount getAccount(Integer id) {
		return riskControlAccountDao.getAccountById(id);
	}

	@Override
	public void updateAccount(RiskControlAccount entity) {
		riskControlAccountDao.updateAccount(entity);
	}

	@Override
	public RiskControlAccount getAccount(RiskControlAccount entity) {
		return riskControlAccountDao.getAccount(entity);
	}

	@Override
	public void saveAccountExamine(Integer entityId, String examStr, 
			Integer examResult, HhUsers user) {
		RiskControlAccount entity = getAccount(entityId);
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
		updateAccount(entity);
		examineService.saveExamine(entity.getId(), Base.examkind_riskcontrol_account, user, examStr, examResult);
	}

	@Override
	public MsgPage getExamineAccounts(RiskControlAccount entity, Integer curPageNum, 
			Integer pageSize, String complainReceiveDate2, String dataAuthority) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = riskControlAccountDao.getExamineAccountListCount(entity,complainReceiveDate2, dataAuthority);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<RiskControlAccount> list = riskControlAccountDao.getExamineAccountList(entity, offset, pageSize,complainReceiveDate2, dataAuthority);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public HhOrganInfo getCoreComp(String companyId) {
		//先获取所属核心企业Id
		HhOrganInfo info = riskControlAccountDao.getCoreCompId(companyId);
		String coreCompId = "";
		String compIds[] = info.getVcOrganId().split("-");
		coreCompId = compIds.length > 3?compIds[3]:"";
		if ("".equals(coreCompId)){
			return new HhOrganInfo();
		} else {
			return riskControlAccountDao.getCoreCompId(coreCompId);
		}	
	}
}
