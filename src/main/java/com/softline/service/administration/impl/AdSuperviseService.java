package com.softline.service.administration.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opendata.api.ODPRequest;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.administration.IAdSuperviseDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.dao.system.impl.SystemDao;
import com.softline.entity.AdSupervise;
import com.softline.entity.HhInterfaceLog;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.administration.IAdSuperviseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;
@Service("adSuperviseService")
public class AdSuperviseService implements IAdSuperviseService {
	@Autowired
	@Qualifier("adSuperviseDao")
	private IAdSuperviseDao adSuperviseDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	@Qualifier("commonService")
	private ICommonService commonService;
	
	@Autowired
	@Qualifier("systemService")
	private  ISystemService systemService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	

	@Override
	public MsgPage getSuperviseView(AdSupervise entity, Integer curPageNum,
			Integer pageSize, Integer fun,String audiorDateStart,String audiorDateEnd,String reportDateStart,String reportDateEnd) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();         
        //总记录数
        Integer totalRecords = adSuperviseDao.getSuperviseListCount(entity, fun,audiorDateStart,audiorDateEnd,reportDateStart,reportDateEnd);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<AdSupervise> list = adSuperviseDao.getSuperviseList(entity, offset, pageSize, fun,audiorDateStart,audiorDateEnd,reportDateStart,reportDateEnd);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public MsgPage getSuperviseView(AdSupervise entity, Integer curPageNum,
			Integer pageSize, Integer fun,String audiorDateStart,String audiorDateEnd,String reportDateStart,String reportDateEnd,String isAllCompany){
		
		if("isall".equals(isAllCompany)){
			
			String nNodeID = entity.getCompId();
			if(!Common.notEmpty(nNodeID))
				return new MsgPage();
			String nNodeIDs = systemService.getDataauthNnodeIDs(nNodeID);
			nNodeIDs = dealWithAuthorstr(nNodeIDs);
			entity.setCompId(nNodeIDs);
			MsgPage msgPage = new MsgPage();  
	        //总记录数
	        Integer totalRecords = adSuperviseDao.getSuperviseListCount(entity, fun,audiorDateStart,audiorDateEnd,reportDateStart,reportDateEnd);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<AdSupervise> list = adSuperviseDao.getSuperviseList(entity, offset, pageSize, fun,audiorDateStart,audiorDateEnd,reportDateStart,reportDateEnd);
			entity.setCompId(nNodeID);
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);    
	        return msgPage;
			
		}else{
			return getSuperviseView(entity,curPageNum,pageSize,fun,audiorDateStart,audiorDateEnd,reportDateStart,reportDateEnd);
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
	public AdSupervise getSupervise(AdSupervise entity) {
		// TODO Auto-generated method stub
		return adSuperviseDao.getSupervise(entity);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveSuperviseCheck(AdSupervise entity)
	{
		CommitResult result=new CommitResult();
		if(!adSuperviseDao.saveSuperviseCheck(entity))
		{
			result=CommitResult.createErrorResult("此单位"+entity.getYear()+"年度已经有数据，不能再添加");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 并发性校验
	 * @param entity
	 * @return
	 */
	private CommitResult theTimeSuperviseCheck(AdSupervise entity)
	{
		CommitResult result=new CommitResult();
		if(!adSuperviseDao.theTimeSuperviseCheck(entity))
		{
			result=CommitResult.createErrorResult("此数据已被其他人员操作，操作失败");
			return result;
		}
		result.setResult(true);
		return result;
	}

	@Override
	public CommitResult saveOrUpdateSupervise(AdSupervise entity, HhUsers use) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveSuperviseCheck(entity);
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
			CommitResult result1=theTimeSuperviseCheck(entity);
			if(!result1.isResult())
			{
				return result1;
			}
			entity.setIsdel(0);
			if(entity.getLastModifyDate() == null || "".equals(entity.getLastModifyDate())){
				entity.setLastModifyDate(df.format(new Date()));
			}
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		commonDao.saveOrUpdate(entity);
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	@Override
	public CommitResult deleteSupervise(AdSupervise entity, HhUsers use) {
		// TODO Auto-generated method stub
		
		CommitResult result=new CommitResult();
		result=theTimeSuperviseCheck(entity);
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
	public AdSupervise getSupervise(Integer id) {
		// TODO Auto-generated method stub
		return adSuperviseDao.getSupervise(id);
	}

	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 督办ID
	 * @return
	 */
	public CommitResult saveSuperviseExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		AdSupervise entity=adSuperviseDao.getSupervise(entityID);
		if(!entity.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	@Override
	public CommitResult saveSuperviseExamine(AdSupervise entity, String examStr,
			Integer examResult, HhUsers use) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//并发性校验
		CommitResult result=new CommitResult();
		result=theTimeSuperviseCheck(entity);
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
		//保存document
		CommitResult superviseresult= saveOrUpdateSupervise(entity,use);
		if(!superviseresult.isResult())
			return superviseresult;
		
		AdSupervise supervise= (AdSupervise) superviseresult.getEntity();
		Integer examineentityid=supervise.getId();

		examineService.saveExamine( examineentityid, Base.examkind_supervise, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}
	
	public List getSuperviseList(String year, String month){
		return adSuperviseDao.getSuperviseList(year, month);
	}

	@Override
	public CommitResult savesynISupervise() {
		/*HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");*/
		//获取上月年、月
		CommitResult result=new CommitResult();
		try {
		String year = null;
		String month = null;
		Calendar calendar = Calendar.getInstance();
		if("0".equals(calendar.get(Calendar.MONTH))){
			month = "12";
			year = (calendar.get(Calendar.YEAR)-1)+"";
		}else{
			month = calendar.get(Calendar.MONTH) + "";
			year = calendar.get(Calendar.YEAR) + "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
    	//获取上月的第一天：
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
    	String first = year + "-1-1";
    	//获取上月的最后一天
    	calendar.add(Calendar.MONTH, 1);
    	calendar.set(Calendar.DAY_OF_MONTH,0);
    	String last = format.format(calendar.getTime());
		
    	
		List<String> list = getSuperviseList(year, month);
		List<AdSupervise> existlist = adSuperviseDao.getSuperviseExistList(year);
		if(list.size() == 0){
			result.setExceptionStr("没有需要同步的数据！");
			result.setResult(false);
			return result;
		}
		else{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
		  for(String string : list){
				AdSupervise adSupervise = new AdSupervise();
				HhInterfaceLog hhInterfaceLog = new HhInterfaceLog();
				hhInterfaceLog.setCallPersonName("系统管理员");
				hhInterfaceLog.setCallVcEmployeeId("9999999901");
				hhInterfaceLog.setCallTime(df.format(new Date()));
				hhInterfaceLog.setIntefaceAlias("行政督办办结率");
				hhInterfaceLog.setIntefaceName(Base.DOC_SYS_SOURCE);
				hhInterfaceLog.setRemark(systemService.getHrOrginfoNameByOrgID(string));
				//nNodeID = "0-1-855579-856150-";
				//nNodeID = "0-1-855579-856150-850766-801197-802701-";
				//传入url与秘钥
				String res=new ODPRequest(Base.ESB_URL,Base.BYLAW_Appsecret)
				.addTextSysPara("AccessToken", Base.BYLAW_AccessToken)
				.addTextSysPara("Method", Base.SUPERVISE_METHOD)
				.addTextSysPara("Format","json")
				//应用参数
				.addTextAppPara("SysSource",Base.DOC_SYS_SOURCE)
				.addTextAppPara("OrganID",string)
				.addTextAppPara("DateTimeStart",first)
				.addTextAppPara("DateTimeEnd",last)
				.addTextAppPara("TaskCreateTimeStart",first)
				.addTextAppPara("TaskCreateTimeEnd",last)
				.post();
				System.out.println(res);
				JSONObject o= JSON.parseObject(res);
				Object cc = o.getJSONObject("MsgResponse").get("ResponseInfo");
				ResponseInfo responseInfo = JSON.parseObject(cc.toString(), ResponseInfo.class);
				hhInterfaceLog.setBackResult(responseInfo.getResult());
				hhInterfaceLog.setBackResultInfo(cc.toString());
				commonDao.saveOrUpdate(hhInterfaceLog);
				if(responseInfo.getResult().equals("-1")){
					continue;
				}
				if(responseInfo.getResult().equals("1")){
					JSONObject obj =  o.getJSONObject("MsgResponse").getJSONObject("Data").getJSONObject("OrganRateList").getJSONObject("RateInfo");
					if(obj.get("TotalCount") == null || "".equals(obj.get("TotalCount")) || "0".equals(obj.get("TotalCount"))){
						continue;
					}
					boolean flag=true;
					for (AdSupervise adSupervise2 : existlist) {	
						
						if(adSupervise2.getVcOrganID().equals(string)){		
							System.out.println(string);
							adSupervise2.setTotalDivision(obj.getInteger("TotalCount"));
							adSupervise2.setFinishDivision(obj.getInteger("FinishCount"));
							adSupervise2.setPropelDivision(obj.getInteger("ProcessCount"));
							adSupervise2.setFinishDivisionRatio(obj.getDouble("FinishRate"));
							adSupervise2.setLastModifyPersonName("系统管理员");
							adSupervise2.setLastModifyPersonId("9999999901");
							adSupervise2.setLastModifyDate(df.format(new Date()));
							adSupervise2.setYear(Integer.parseInt(year));
							adSupervise2.setStatus(50112);
							commonDao.saveOrUpdate(adSupervise2);
							flag=false;
							break;
						}
					}
					if(flag){
						adSupervise.setCompId(obj.getString("NodeID"));
						adSupervise.setCompName(obj.getString("OrganName"));
						adSupervise.setTotalDivision(obj.getInteger("TotalCount"));
						adSupervise.setFinishDivision(obj.getInteger("FinishCount"));
						adSupervise.setPropelDivision(obj.getInteger("ProcessCount"));
						adSupervise.setFinishDivisionRatio(obj.getDouble("FinishRate"));
						adSupervise.setCreatePersonName("系统管理员");
						adSupervise.setCreatePersonId("9999999901");
						adSupervise.setCreateDate(df.format(new Date()));
						adSupervise.setYear(Integer.parseInt(year));
						adSupervise.setIsdel(0);
						adSupervise.setStatus(50112);
						commonDao.saveOrUpdate(adSupervise);
					}
					
				}
				
			}
		}
		result.setExceptionStr("数据同步成功！");
		result.setResult(true);
		return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setExceptionStr("数据同步失败！");
			result.setResult(false);
			return result;
		}
	}

}
