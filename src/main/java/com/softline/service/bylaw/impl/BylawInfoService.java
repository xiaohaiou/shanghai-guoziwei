package com.softline.service.bylaw.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opendata.api.ODPRequest;
import com.softline.common.Base;
import com.softline.common.BylawInfoTree;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.dao.bylaw.IBylawDeptDao;
import com.softline.dao.bylaw.IBylawInfoDao;
import com.softline.dao.select.ISelectUserDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.bylaw.BylawDept;
import com.softline.entity.bylaw.BylawInfo;
import com.softline.entity.bylaw.BylawInfoSynRecord;
import com.softline.entityTemp.BackBylawInfo;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.bylaw.IBylawInfoService;
import com.softline.util.MsgPage;
@Service("bylawInfoService")
public class BylawInfoService implements IBylawInfoService{
	@Autowired
	@Qualifier("bylawInfoDao")
	private IBylawInfoDao bylawInfoDao;
	
	@Autowired
	@Qualifier("bylawDeptDao")
	private IBylawDeptDao bylawDeptDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Autowired
	@Qualifier("selectUserDao")
	private ISelectUserDao selectUserDao;
	
	
	static Logger log = Logger.getLogger(BylawInfoService.class.getName());

	@Override
	public MsgPage getBylawInfos(String dataauth,BylawInfo condition, Integer curPageNum,
			Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<BylawInfo> t = bylawInfoDao.getBylawInfos(dataauth,condition, null, null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<BylawInfo> list = bylawInfoDao.getBylawInfos(dataauth,condition, offset, pageSize);
    	for(int i = 0; i < list.size(); i++){
    		BylawInfo bylawInfo = list.get(i);
    		if(bylawInfo.getIsLinked() != null && bylawInfo.getIsLinked() == 1)
    			bylawInfo.setLinkDiscription(getLinkDiscription(bylawInfo, ""));
    	}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public List<BylawInfo> getBylawInfos(String dataauth,BylawInfo condition) {
		return bylawInfoDao.getBylawInfos(dataauth,condition, null, null);
	}

	@Override
	public void saveBylawinfo(BylawInfo bylawInfo, HhUsers hhUsers) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BylawInfo newEntity = (BylawInfo) commonDao.findById(BylawInfo.class, bylawInfo.getId());
		
		newEntity.setDeptId(bylawInfo.getDeptId());//体系类型
		newEntity.setFileLevel(bylawInfo.getFileLevel());//层级类别
		newEntity.setParentId(bylawInfo.getParentId());//上级规章制度ID
		newEntity.setIsLinked(1);//已进行关联
		newEntity.setHaveLevel(bylawInfo.getHaveLevel());//是否涉及层级类别(1 涉及 0 不涉及)
		newEntity.setBylawTitle(bylawInfo.getBylawTitle());
		
		newEntity.setLastModifyDate(df.format(new Date()));
		newEntity.setLastModifyPersonId(hhUsers.getVcEmployeeId());
		newEntity.setLastModifyPersonName(hhUsers.getVcName());
		
	}
	
	/**
	 * 已经建立了关联的规章制度调用该方法
	 * 递归拼接建立了关联的规章制度的描述
	 * @param bylawInfo 规章制度实体
	 * @return
	 */
	private String getLinkDiscription(BylawInfo bylawInfo,String result){
		if(bylawInfo.getParentId() == null && bylawInfo.getFileLevel() != null && bylawInfo.getFileLevel() == 1){
			if(Common.notEmpty(result)){
				result = bylawInfo.getOrgNm()+"-->"+bylawInfo.getBylawTitle()+"-->"+result;
			}else{
				result = bylawInfo.getOrgNm()+"-->"+bylawInfo.getBylawTitle();
			}
			
		}else{
			if(bylawInfo.getParentId() != null){
				BylawInfo parent = (BylawInfo) commonDao.findById(BylawInfo.class, bylawInfo.getParentId());
				if(Common.notEmpty(result)){
					result = bylawInfo.getBylawTitle() + "-->" + result;
				}else{
					result = bylawInfo.getBylawTitle();
				}
				if (null!=parent) {
					result = getLinkDiscription(parent, result);
				}
			}
		}
		return result;
	}

	@Override
	public String xsaveSynBylawInfo(HhUsers user) {
			log.info("开始同步规章制度信息");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String opTime = df.format(new Date());
			//以年为单位同步规章制度
			Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
			Integer startYear = Base.BYLAW_START_TIME;
			while(startYear <= currentYear){
				log.info("同步"+startYear+"年规章制度信息");
				String startDateTime = startYear+"-01-01 00:00:00";
				String endDatetime = startYear+"-12-31 23:59:59";
				long startTime = new Date().getTime();
				String aa = getSynBackDate(startDateTime, endDatetime);
				log.info("通过接口获取"+startYear+"年数据耗时：" + (new Date().getTime()- startTime)+"ms");
				 startTime = new Date().getTime();
				JSONObject obj= JSON.parseObject(aa);
				Object cc = obj.getJSONObject("MsgResponse").get("ResponseInfo");
				ResponseInfo responseInfo = JSON.parseObject(cc.toString(), ResponseInfo.class);
				if(responseInfo.getResult().equals("1")){
					Object ss =  obj.getJSONObject("MsgResponse").getJSONObject("Data").get("RulesList");
					List<BackBylawInfo> backBylawInfos = JSON.parseArray(ss.toString(),BackBylawInfo.class);
					log.info("JSON数据"+startYear+"年数据转换数据耗时：" + (new Date().getTime()- startTime)+"ms");
					//保存同步记录
					log.info("保存"+startYear+"年规章制度同步结果信息");
					xsaveSynRecord(backBylawInfos.size(), startDateTime, endDatetime, responseInfo, user, opTime);
					//保存同步的规章制度
					log.info("保存"+startYear+"年规章制度同步信息");
					 startTime = new Date().getTime();
					saveSynData(backBylawInfos);
					log.info("ddd"+startYear+"年数据耗时：" + (new Date().getTime()- startTime)+"ms");
					
				}else{
					//保存同步记录
					log.info("保存"+startYear+"年规章制度同步结果信息");
					xsaveSynRecord(0, startDateTime, endDatetime, responseInfo, user, opTime);
				}
				log.info("处理"+startYear+"年数据数据耗时：" + (new Date().getTime()- startTime)+"ms");
				startYear ++;
				
			}
			return "success";
	}
	
	/**
	 * 同步数据
	 * @param backBylawInfos
	 */
	private void saveSynData(List<BackBylawInfo> backBylawInfos){
		for(BackBylawInfo backBylawInfo : backBylawInfos){
			String fileId = backBylawInfo.getDocID();
			BylawInfo entity = bylawInfoDao.getBylawInfoByFileId(fileId);
			if(entity == null){
				entity = new BylawInfo();
			}
			entity.setFileId(backBylawInfo.getDocID());//文件ID
			entity.setFileDocType(backBylawInfo.getDocType());//文件类型
			entity.setFileName(backBylawInfo.getTitle());//规章制度标题
			entity.setFileNumber(backBylawInfo.getNumber());//文号
			entity.setFileEffectiveTime(backBylawInfo.getEffectiveTime());//生效日期
			entity.setFileIsAbolish(backBylawInfo.getIsAbolish());//是否失效
			entity.setFileDocDueDate(backBylawInfo.getDocDueDate());//IsAbolish为true时表示废止时间；IsAbolish为false时表示有效期限
			entity.setFileAbolishReason(backBylawInfo.getAbolishReason());//废止理由
			entity.setFileStatus(backBylawInfo.getStatus());//0 已删除，1 正常
			entity.setFileTitle(backBylawInfo.getTitle());//规章制度标题
			HhOrganInfo hhOrganInfo = systemDao.getOrganInfoById(backBylawInfo.getOrganID());//获取公司实体
			if(hhOrganInfo != null){
				entity.setOrg(backBylawInfo.getOrganID());//获取发文公司
				entity.setOrgNm(hhOrganInfo.getVcFullName());//公司名称
				String parentorg=systemDao.getHrorginfoParentNnodeId(backBylawInfo.getOrganID());
				entity.setParentId(null==parentorg?null:Integer.parseInt(parentorg));
				Integer isCoreOrg = 0;//是否为核心企业 0不是核心企业 1是核心企业
				if(hhOrganInfo.getVcParentId().equals(Base.HRTopPrefix)){
					isCoreOrg = 1;
					entity.setCoreOrg(hhOrganInfo.getNnodeId());//所属核心企业
					entity.setCoreOrgNm(hhOrganInfo.getVcFullName());//所属核心企业
				}else{
					isCoreOrg = 0;
					String organID = hhOrganInfo.getVcOrganId();
					String[] bb = organID.split("-");
					if(bb.length > 4){
						HhOrganInfo coreOrgInfo = systemDao.getOrganInfoById(bb[3]);//获取公司实体
						if(coreOrgInfo!=null){
							entity.setCoreOrg(coreOrgInfo.getNnodeId());//所属核心企业
							entity.setCoreOrgNm(coreOrgInfo.getVcFullName());//所属核心企业
						}
					}
				}
				entity.setIsCoreOrg(isCoreOrg);//是否核心企业
			}
			List<BylawDept> bylawDepts = bylawDeptDao.getBylawDepts(new BylawDept(), null, null);
			int otherDeptId=0;
			for (int i = 0; i < bylawDepts.size(); i++) {
				if ("其他".equals(bylawDepts.get(i).getDeptNm())) {
					otherDeptId=bylawDepts.get(i).getDeptId();
					break;
				}
			}
			for (int i = 0; i < bylawDepts.size(); i++) {
				if (bylawDepts.get(i).getDeptNm().equals(entity.getFileDocType())) {
					entity.setDeptId(bylawDepts.get(i).getDeptId());
					break;
				}
				if (null==entity.getDeptId()||0==entity.getDeptId()) {
					entity.setDeptId(otherDeptId);
				}
			}
			commonDao.saveOrUpdate(entity);
		}
	}
	
	
	/**
	 * 保存同步记录
	 * @param synRecordCount
	 * @param startDateTime
	 * @param endDatetime
	 * @param responseInfo
	 * @param user
	 * @param optTime
	 */
	private void xsaveSynRecord(Integer synRecordCount, String startDateTime,
			String endDatetime, ResponseInfo responseInfo, HhUsers user,
			String optTime) {
		
		BylawInfoSynRecord entity = new BylawInfoSynRecord();
		if (user == null) {
			entity.setCreatePersonId(null);
			entity.setCreatePersonName("系统定时同步");
		}else{
			entity.setCreatePersonId(user.getVcEmployeeId());
			entity.setCreatePersonName(user.getVcName());
		}
		entity.setOptTime(optTime);
		entity.setSynEndTime(endDatetime);
		entity.setSynErrorCode(responseInfo.getErrorCode());
		entity.setSynErrorInfo(responseInfo.getErrorInfo());
		entity.setSynRecordCount(synRecordCount);
		entity.setSynResult(responseInfo.getResult());
		entity.setSynStartTime(startDateTime);
		commonDao.saveOrUpdate(entity);
	}

	/**
	 * 获取返回的JSON同步数据
	 * @param startDateTime
	 * @param endDatetime
	 * @return
	 */
	public String getSynBackDate(String startDateTime,String endDatetime){
		String res=new ODPRequest(Base.BYLAW_URL,Base.BYLAW_Appsecret)
		.addTextSysPara("Method",Base.BYLAW_METHOD)
		.addTextSysPara("AccessToken",Base.BYLAW_AccessToken)
		.addTextSysPara("Format","json")
		//应用参数
		.addTextAppPara("StartDatetime",startDateTime)
		.addTextAppPara("EndDatetime",endDatetime)
		.post();
		return res;
	}
	
	
	
	

	@Override
	public MsgPage getBylawInfoSynRecords(BylawInfoSynRecord condition,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<BylawInfoSynRecord> t = bylawInfoDao.getBylawInfoSynRecords(condition, null, null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<BylawInfoSynRecord> list = bylawInfoDao.getBylawInfoSynRecords(condition, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public String getBylawInfoTreeHtml() {
		StringBuffer buffer = new StringBuffer();
		HhOrganInfo sdktop = selectUserDao.getTop(Base.HRTop,null);
		List<HhOrganInfo> organInfos = systemDao.getOrganInfos(Base.HRTopPrefix);
		buffer.append("<li class=\"line-list\" title=\""+sdktop.getVcFullName()+"\">");
			if(organInfos.size() > 0){
				buffer.append("<span class=\"departmentLabel\" treeId=\"comp"+sdktop.getNnodeId()+"\"><i class=\"iconfont icon-tree-close-2\"></i><a href='#n'>"+sdktop.getVcFullName()+"</a></span>");
				buffer.append("<ul class=\"level-2 \">");
				buffer.append(getCoreCompanyHtml(organInfos));
				buffer.append("</ul>");
			}else{
				buffer.append("<label class=\"checkboxList\" treeId=\"comp"+sdktop.getNnodeId()+"\"><a href='#n'>"+sdktop.getVcFullName()+"</a></label>");
			}
		buffer.append("</li>");
		return buffer.toString();
	}
	
	/**
	 * 核心企业树HTML
	 * @param organInfos
	 * @return
	 */
	private String getCoreCompanyHtml(List<HhOrganInfo> organInfos){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<organInfos.size();i++){
			HhOrganInfo sdktop = organInfos.get(i);
			List<BylawDept> bylawDepts = bylawDeptDao.getBylawDepts(new BylawDept(), null, null);
			List<BylawInfo> bylawInfos = bylawInfoDao.getZongzAndNoLevel(null, sdktop.getNnodeId());
			if(bylawInfos.size() > 0){
				buffer.append("<li class=\"line-list\" title=\""+sdktop.getVcFullName()+"\">");
					buffer.append("<span class=\"departmentLabel\" treeId=\"comp"+sdktop.getNnodeId()+"\"><i class=\"iconfont icon-tree-close-2\"></i><a href='#n'>"+sdktop.getVcFullName()+"</a></span>");
					buffer.append("<ul class=\"level-2 \">");
					buffer.append(getBylawDeptHtml(bylawDepts, sdktop.getNnodeId()));
					buffer.append("</ul>");
				buffer.append("</li>");
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 体系结构HTML
	 * @param bylawDepts
	 * @param org
	 * @return
	 */
	private String getBylawDeptHtml(List<BylawDept> bylawDepts,String org){
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < bylawDepts.size();i++){
			BylawDept dept = bylawDepts.get(i);
			List<BylawInfo> bylawInfos = bylawInfoDao.getZongzAndNoLevel(dept.getDeptId(), org);
			if(bylawInfos.size() > 0){
				buffer.append("<li class=\"line-list\" title=\""+dept.getDeptNm()+"\">");
					buffer.append("<span class=\"departmentLabel\" treeId=\"dept"+dept.getDeptId()+"\"><i class=\"iconfont icon-tree-close-2\"></i><a href='#n'>"+dept.getDeptNm()+"</a></span>");
					buffer.append("<ul class=\"level-2 \">");
					buffer.append(lastBylawHtml(bylawInfos));
					buffer.append("</ul>");
				buffer.append("</li>");
			}
		}
		
		return buffer.toString();
	}

	/**
	 * 底层规章制度HTML
	 * @param bylawInfos
	 * @return
	 */
	private String lastBylawHtml(List<BylawInfo> bylawInfos){
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < bylawInfos.size();i++){
			BylawInfo bylawInfo = bylawInfos.get(i);
			List<BylawInfo> bybylawInfos = bylawInfoDao.getSonBylawInfos(bylawInfo.getId());
			buffer.append("<li class=\"line-list\" title=\""+bylawInfo.getBylawTitle()+"\">");
			if(bybylawInfos.size() > 0){
					buffer.append("<span class=\"departmentLabel\" treeId=\"bylaw"+bylawInfo.getId()+"\"><i class=\"iconfont icon-tree-close-2\"></i><a href='javascript:;' onclick=\"getDetail('"+bylawInfo.getId()+"')\">"+bylawInfo.getBylawTitle()+"</a></span>");
					buffer.append("<ul class=\"level-2 \">");
					buffer.append(lastBylawHtml(bybylawInfos));
					buffer.append("</ul>");
				}else{
					buffer.append("<label class=\"checkboxList\" treeId=\"bylaw"+bylawInfo.getId()+"\"><a href='javascript:;' onclick=\"getDetail('"+bylawInfo.getId()+"')\">"+bylawInfo.getBylawTitle()+"</a></label>");
				}
			buffer.append("</li>");
		}
		return buffer.toString();
	}
	
	
	@Override
	public BylawInfoTree getBylawInfoTree(String bylawTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveSynBylawInfoInYar(String startYear, HhUsers user) {
		log.info("开始同步规章制度信息");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String opTime = df.format(new Date());
		log.info("同步"+startYear+"年规章制度信息");
		String startDateTime = startYear+"-01-01 00:00:00";
		String endDatetime = startYear+"-12-31 23:59:59";
		long startTime = new Date().getTime();
		String aa = getSynBackDate(startDateTime, endDatetime);
		log.info("通过接口获取"+startYear+"年数据耗时：" + (new Date().getTime()- startTime)+"ms");
		 startTime = new Date().getTime();
		JSONObject obj= JSON.parseObject(aa);
		Object cc = obj.getJSONObject("MsgResponse").get("ResponseInfo");
		ResponseInfo responseInfo = JSON.parseObject(cc.toString(), ResponseInfo.class);
		if(responseInfo.getResult().equals("1")){
			Object ss =  obj.getJSONObject("MsgResponse").getJSONObject("Data").get("RulesList");
			List<BackBylawInfo> backBylawInfos = JSON.parseArray(ss.toString(),BackBylawInfo.class);
			log.info("JSON数据"+startYear+"年数据转换数据耗时：" + (new Date().getTime()- startTime)+"ms");
			//保存同步记录
			log.info("保存"+startYear+"年规章制度同步结果信息");
			xsaveSynRecord(backBylawInfos.size(), startDateTime, endDatetime, responseInfo, user, opTime);
			//保存同步的规章制度
			log.info("保存"+startYear+"年规章制度同步信息");
			 startTime = new Date().getTime();
			saveSynData(backBylawInfos);
			log.info("ddd"+startYear+"年数据耗时：" + (new Date().getTime()- startTime)+"ms");
			
		}else{
			//保存同步记录
			log.info("保存"+startYear+"年规章制度同步结果信息");
			xsaveSynRecord(0, startDateTime, endDatetime, responseInfo, user, opTime);
		}
		log.info("处理"+startYear+"年数据数据耗时：" + (new Date().getTime()- startTime)+"ms");
		return "success";
	}

	@Override
	public List<BylawInfo> getCanChangedBylawInfos(String orgId) {
		return bylawInfoDao.getCanChangedBylawInfos(orgId);
	}

	@Override
	public String saveChangeLink(Integer id, String changeBylawInfoId,HhUsers user) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BylawInfo info  = (BylawInfo) commonDao.findById(BylawInfo.class, id);
		if(!("jb".equals(changeBylawInfoId))){//替换规章制度
			BylawInfo infoNew  = (BylawInfo) commonDao.findById(BylawInfo.class, Common.parseInteger(changeBylawInfoId));
			//新节点修改
			infoNew.setBylawTitle(infoNew.getFileName());
			infoNew.setDeptId(info.getDeptId());
			infoNew.setHaveLevel(info.getHaveLevel());
			infoNew.setIsLinked(1);
			infoNew.setFileLevel(info.getFileLevel());
			infoNew.setParentId(info.getParentId());
			infoNew.setLastModifyDate(df.format(new Date()));
			infoNew.setLastModifyPersonId(user.getVcEmployeeId());
			infoNew.setLastModifyPersonName(user.getVcName());
			commonDao.saveOrUpdate(infoNew);
			//子节点修改
			List<BylawInfo> sonBylawInfos2 =  bylawInfoDao.getSonBylawInfos(info.getId());
			if(sonBylawInfos2 != null){
				for(BylawInfo sonEntity : sonBylawInfos2){
					sonEntity.setParentId(infoNew.getId());
					sonEntity.setIsLinked(1);
					sonEntity.setLastModifyDate(df.format(new Date()));
					sonEntity.setLastModifyPersonId(user.getVcEmployeeId());
					sonEntity.setLastModifyPersonName(user.getVcName());
					commonDao.saveOrUpdate(sonEntity);
				}
			}
			
		}else{//解绑
			jbBylawInfo(info, user);
		}
		
		info.setIsLinked(0);
		info.setParentId(null);
		info.setFileLevel(null);
		info.setLastModifyDate(df.format(new Date()));
		info.setLastModifyPersonId(user.getVcEmployeeId());
		info.setLastModifyPersonName(user.getVcName());
		commonDao.saveOrUpdate(info);
		return "success";
	}
	
	/**
	 * 解绑，将子规章制度变为没有关联的状态
	 * @param info
	 * @param user
	 */
	private void jbBylawInfo(BylawInfo info,HhUsers user){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<BylawInfo> sonBylawInfos2 =  bylawInfoDao.getSonBylawInfos(info.getId());
		if(sonBylawInfos2 != null){
			for(BylawInfo sonEntity : sonBylawInfos2){
				sonEntity.setParentId(null);
				sonEntity.setIsLinked(0);
				sonEntity.setLastModifyDate(df.format(new Date()));
				sonEntity.setLastModifyPersonId(user.getVcEmployeeId());
				sonEntity.setLastModifyPersonName(user.getVcName());
				commonDao.saveOrUpdate(sonEntity);
				jbBylawInfo(sonEntity, user);
			}
		}
	}
	
	
}
