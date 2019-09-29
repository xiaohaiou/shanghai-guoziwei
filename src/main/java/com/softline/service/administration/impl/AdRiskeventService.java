package com.softline.service.administration.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.administration.IAdRiskeventDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.AdRiskevent;
import com.softline.entity.AdSupervise;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.service.administration.IAdRiskeventService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;
@Service("adRiskeventService")
public class AdRiskeventService implements IAdRiskeventService {
	
	@Autowired
	@Qualifier("adRiskeventDao")
	private IAdRiskeventDao adRiskeventDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "systemService")
	private ISystemService systemService;

	@Override
	public MsgPage getRiskeventView(AdRiskevent entity, Integer curPageNum,
			Integer pageSize, Integer fun) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = adRiskeventDao.getRiskeventListCount(entity, fun);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<AdRiskevent> list = adRiskeventDao.getRiskeventList(entity, offset, pageSize, fun);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public MsgPage getRiskeventView(AdRiskevent entity, Integer curPageNum,
			Integer pageSize, Integer fun,String isAllCompany) {	
	
		if("isall".equals(isAllCompany)){
			
			String nNodeID = entity.getRiskCompId();
			if(!Common.notEmpty(nNodeID))
				return new MsgPage();
			String nNodeIDs = systemService.getDataauthNnodeIDs(nNodeID);
			nNodeIDs = dealWithAuthorstr(nNodeIDs);
			entity.setRiskCompId(nNodeIDs);
			MsgPage msgPage = new MsgPage();       
	        //总记录数
	        Integer totalRecords = adRiskeventDao.getRiskeventListCount(entity, fun);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<AdRiskevent> list = adRiskeventDao.getRiskeventList(entity, offset, pageSize, fun);
	    	entity.setRiskCompId(nNodeID);
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);    
	        return msgPage;			
		}else{
			return getRiskeventView(entity,curPageNum,pageSize,fun);
		}
	}
	/**
	 * 处理查询公司编码格式
	 * @param str
	 * @return
	 */
	private String dealWithAuthorstr(String str){
		if(!Common.notEmpty(str) || !str.contains(","))
			return "";
		StringBuffer sb = new StringBuffer();
		String[] strArr = str.split(",");
		for(int i=0;i<strArr.length-1;i++){
			sb.append("'").append(strArr[i]).append("',");
		}
		sb.append("'").append(strArr[strArr.length-1]).append("'");
		return sb.toString();
	}
	
	@Override
	public AdRiskevent getRiskevent(AdRiskevent entity) {
		// TODO Auto-generated method stub
		return adRiskeventDao.getRiskevent(entity);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveRiskeventCheck(AdRiskevent entity)
	{
		CommitResult result=new CommitResult();
		/*if(!adRiskeventDao.saveRiskeventCheck(entity))
		{
			result=CommitResult.createErrorResult("此单位"+entity.getYear()+"年" + entity.getSeasonName() + "已经有数据，不能再添加");
			return result;
		}*/
		result.setResult(true);
		return result;
	}
	
	/**
	 * 并发性校验
	 * @param entity
	 * @return
	 */
	private CommitResult theTimeRiskeventCheck(AdRiskevent entity)
	{
		CommitResult result=new CommitResult();
		if(!adRiskeventDao.theTimeRiskeventCheck(entity))
		{
			result=CommitResult.createErrorResult("此数据已被其他人员操作，操作失败");
			return result;
		}
		result.setResult(true);
		return result;
	}

	@Override
	public CommitResult saveOrUpdateRiskevent(AdRiskevent entity, HhUsers use) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveRiskeventCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity.getId()==null)
		{
			entity.setIsdel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
		}
		else
		{	
			//并发性校验
			CommitResult result1=theTimeRiskeventCheck(entity);
			if(!result1.isResult())
			{
				return result1;
			}
			entity.setIsdel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		commonDao.saveOrUpdate(entity);
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	@Override
	public CommitResult deleteRiskevent(AdRiskevent entity, HhUsers use) {
		// TODO Auto-generated method stub
		CommitResult result=new CommitResult();
		result=theTimeRiskeventCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	@Override
	public AdRiskevent getRiskevent(Integer id) {
		// TODO Auto-generated method stub
		return adRiskeventDao.getRiskevent(id);
	}
	
	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 公文ID
	 * @return
	 */
	public CommitResult saveRiskeventExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		AdRiskevent entity=adRiskeventDao.getRiskevent(entityID);
		if(!entity.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	@Override
	public CommitResult saveRiskeventExamine(AdRiskevent entity, String examStr,
			Integer examResult, HhUsers use) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//并发性校验
		CommitResult result=new CommitResult();
		result=theTimeRiskeventCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		//审核不通过
		if(examResult.equals(Base.examresult_1))
			entity.setStatus(Base.examstatus_3);
		//审核通过
		else if(examResult.equals(Base.examresult_2))
			entity.setStatus(Base.examstatus_4);
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		//保存Riskevent
		CommitResult riskeventresult= saveOrUpdateRiskevent(entity,use);
		if(!riskeventresult.isResult())
			return riskeventresult;
		
		AdRiskevent riskevent= (AdRiskevent) riskeventresult.getEntity();
		Integer examineentityid=riskevent.getId();

		examineService.saveExamine( examineentityid, Base.examkind_riskevent, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}

	@Override
	public HhOrganInfo getCoreComp(String riskCompId) {
		// TODO Auto-generated method stub
		//先获取所属核心企业Id
		HhOrganInfo info = adRiskeventDao.getCoreCompId(riskCompId);
		String coreCompId = "";
		String compIds[] = info.getVcOrganId().split("-");
		coreCompId = compIds.length > 3?compIds[3]:"";
		if ("".equals(coreCompId))
			return new HhOrganInfo();
		else
			return adRiskeventDao.getCoreCompId(coreCompId);
	}
}
