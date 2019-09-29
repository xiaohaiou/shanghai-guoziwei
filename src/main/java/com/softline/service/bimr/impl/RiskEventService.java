package com.softline.service.bimr.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.dao.bimr.IRiskEventDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrCompliancePerson;
import com.softline.entity.bimr.BimrRiskEvent;
import com.softline.entity.bimr.BimrRiskEventAdoptstrategy;
import com.softline.entity.bimr.BimrRiskEventAdoptstrategyFeedback;
import com.softline.entity.bimr.BimrRiskEventFeedback;
import com.softline.entity.bimr.BimrRiskEventRelevance;
import com.softline.entity.bimr.BimrRiskEventRelevancetrack;
import com.softline.entityTemp.BimrRiskEventSecondTop;
import com.softline.entityTemp.CommitResult;
import com.softline.service.bimr.IRiskEventService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

/**
 * 风险事件
 * 
 * @author tj
 */
@Service("riskEventService")
public class RiskEventService implements IRiskEventService {

	@Autowired
	private IRiskEventDao riskEventDao;
	
	@Autowired
	@Qualifier("commonService")
	private ICommonService commonService;
	
	@Autowired
	@Qualifier("examineService")
	private IExamineService examineService;	
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Override
	public MsgPage getRiskEventListView(BimrRiskEvent entity, Integer curPageNum, Integer pageSize,Integer highunitmeasure) {
		
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = riskEventDao.getRiskEventListCount(entity,highunitmeasure);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrRiskEvent> list = riskEventDao.getRiskEventList(entity, offset, pageSize,highunitmeasure);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}
	
	@Override
	public MsgPage getRiskEventMeasureListView(BimrRiskEvent entity, Integer curPageNum, Integer pageSize,String insidetrackpersonid) {
		
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = riskEventDao.getRiskEventMeasureListCount(entity, insidetrackpersonid);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrRiskEvent> list = riskEventDao.getRiskEventMeasureList(entity, offset, pageSize, insidetrackpersonid);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}
	
	@Override
	public MsgPage getRiskFeedBackListView(BimrRiskEvent entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = riskEventDao.getRiskFeedBackListCount(entity);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrRiskEvent> list = riskEventDao.getRiskFeedBackList(entity, offset, pageSize);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}
	
	@Override
	public MsgPage getExamineTracklist(BimrRiskEventRelevancetrack entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = riskEventDao.getExamineTracklistCount(entity);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrRiskEventRelevancetrack> trackList = new ArrayList<BimrRiskEventRelevancetrack>();
		List<Object> list = riskEventDao.getExamineTracklist(entity, offset, pageSize);
		for (Object object : list) {
			Object[] obj = (Object[])object;
			BimrRiskEventRelevancetrack event = new BimrRiskEventRelevancetrack();
			event.setId(obj[0] != null?Integer.valueOf(obj[0].toString()):null);
			event.setEventid(obj[1] != null?Integer.valueOf(obj[1].toString()):null);
			event.setTakestrategy(obj[2] != null?Integer.valueOf(obj[2].toString()):null);
			event.setTakestrategytxt(obj[3] != null?obj[3].toString():"");
			event.setTakestrategyremark(obj[4] != null?obj[4].toString():"");
			event.setReportTime(obj[5] != null?obj[5].toString():"");
			event.setCompName(obj[6] != null?obj[6].toString():"");
			event.setEventTitle(obj[7] != null?obj[7].toString():"");
			event.setEventNum(obj[8] != null?obj[8].toString():"");
			Integer status = obj[9] != null?Integer.valueOf(obj[9].toString()):null;
			event.setStatus(status);
			if (status!=null && status==82104) {
				event.setStatusName("跟踪待审核");
			}else if (status!=null && status==82105) {
				event.setStatusName("跟踪已审核");
			}else {
				event.setStatusName("跟踪已退回");
			}
			trackList.add(event);
		}
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(trackList);
		return msgPage;
	}

	public CommitResult saveRiskEvent(BimrRiskEvent entity,HhUsers use,Integer type)
	{
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveRiskEventCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity.getId()==null)
		{
			if (type == 0) {//说明是保存
				entity.setStatus(82100);//事件待上报
			}else {			//说明是上报并保存
				entity.setStatus(82101);//事件待审核
			}
			entity.setIsDel(0);
			
			entity.setRelevancestatus(1);//事件未关联
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
		}
		else
		{
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		
/*		HhOrganInfoTreeRelation nodeBusiness = systemDao.getTreeOrganInfoNodeBusiness(Base.financetype, entity.getCompId());
        String coreOrg=nodeBusiness.getId().getNnodeId();
        String coreOrgNm =nodeBusiness.getVcFullName();
        entity.setCoreorg(coreOrg);
        entity.setCoreorgname(coreOrgNm);*/
		commonDao.saveOrUpdate(entity);
		
		
		
		
		
		//保存附件
		/*if (file1!= null && !file1.isEmpty()) {
			if (entity.getFileOne() != null) {
				String uuid = entity.getFileOne().getFileUuid();
				Common.deleteFile(DES.report_path, uuid);
				commonDao.delete(entity.getFileOne());
			}
			HhFile f = commonService.saveFile(file1, entity.getId(),
					Base.inspect_project,DES.report_path);
		}*/
		
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	private CommitResult saveRiskEventCheck(BimrRiskEvent entity)
	{
		CommitResult result=new CommitResult();
		/*if(!purchaseDao.savePurchaseRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该季度已经有数据，不能再添加");
			return result;
		}
		if(!purchaseDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}*/
		result.setResult(true);
		return result;
	}
	
	public BimrRiskEvent getRiskEventById(Integer id){
		BimrRiskEvent entity=riskEventDao.getRiskEventById(id);
		/*if(entity!=null)
			entity.setFileOne(commonService.getFileByEnIdAndType(entity.getId(), Base.inspect_project));*/
		
		return entity;
	}
	
	@Override
	public CommitResult deleteRiskEvent(BimrRiskEvent entity, HhUsers use) {
		// TODO Auto-generated method stub
		CommitResult result=new CommitResult();
		/*result=theTimeDocumentCheck(entity);
		if(!result.isResult())
		{
			return result;
		}*/
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsDel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	public List<BimrRiskEventAdoptstrategy> getAdoptstrategy(Integer eventId){
		return riskEventDao.getAdoptstrategy(eventId);
	}

	
	@Override
	public List<BimrRiskEventFeedback> getRiskEventFeedbackList(Integer eventId) {
		return riskEventDao.getRiskEventFeedbackList(eventId);
	}
	
	@Override
	public List<BimrRiskEventAdoptstrategyFeedback> getAdoptstrategyFeedbcakList(
			Integer eventId) {
		return riskEventDao.getAdoptstrategyFeedbcakList(eventId);
	}

/*	@Override
	public MsgPage getRiskEventResultListView(BimrRiskEvent entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = riskEventDao.getRiskEventResultListCount(entity);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrRiskEvent> list = riskEventDao.getRiskEventResultList(entity, offset, pageSize);
		
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}*/
	
	@Override
	public MsgPage getRiskEventRelevanceListView(BimrRiskEvent entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = riskEventDao.getRiskEventListCount(entity,null);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrRiskEvent> result = new ArrayList<BimrRiskEvent>();
		List<Object> list = riskEventDao.getRiskEventRelevanceList(entity, offset, pageSize);
		for (Object object : list) {
			Object[] obj = (Object[])object;
			BimrRiskEvent event = new BimrRiskEvent();
			event.setReportTime(obj[0] != null?obj[0].toString():"");
			event.setEventTitle(obj[1] != null?obj[1].toString():"");
			event.setCompName(obj[2] != null?obj[2].toString():"");
			event.setRelevancethreeName(obj[3] != null?obj[3].toString():"");
			event.setRelevancePerson(obj[4] != null?obj[4].toString():"");
			event.setRelevanceauditor(obj[5] != null?obj[5].toString():"");
			event.setRelevancestatus(obj[6] != null?Integer.valueOf(obj[6].toString()):null);
			event.setId(obj[7] != null?Integer.valueOf(obj[7].toString()):null);
			event.setEventNum(obj[8] != null?obj[8].toString():"");
			result.add(event);
		}
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(result);
		return msgPage;
	}

	@Override
	public List<BimrRiskEventRelevance> getRelevanceList(Integer eventId) {
		return riskEventDao.getRelevanceList(eventId);
	}

	@Override
	public CommitResult saveRiskEventRelevance(BimrRiskEvent entity,
			HhUsers use, List<BimrRiskEventRelevance> relevanceList) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveRiskEventCheck(entity);
		if(!result.isResult())
		{
			return result;
		}

		//设置关联人ID、姓名
		entity.setRelevancePersonId(use.getVcEmployeeId());
		entity.setRelevancePerson(use.getVcName());
		//设置关联状态为待审核
		entity.setRelevancestatus(3);
		commonDao.saveOrUpdate(entity);
		
		List<BimrRiskEventRelevance> oldList=riskEventDao.getRelevanceList(entity.getId());
		for(BimrRiskEventRelevance l:oldList){
			commonDao.delete(l);//物理删除
		}
		
		for(BimrRiskEventRelevance l:relevanceList){
			commonDao.saveOrUpdate(l);//新增
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}



	@Override
	public CommitResult saveRelevanceExamine(BimrRiskEvent entity, String examStr,
			Integer examResult, HhUsers use) {
		//审核不通过
		if(examResult.equals(Base.examresult_1))
			entity.setRelevancestatus(5);//把关联状态设为被退回 5
		//审核通过
		else if(examResult.equals(Base.examresult_2))
			entity.setRelevancestatus(4);
		//设置审核人
		entity.setRelevanceauditorId(use.getVcEmployeeId());
		entity.setRelevanceauditor(use.getVcName());
		commonDao.saveOrUpdate(entity);
		
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_risk_Relevance, use, examStr, examResult);
		CommitResult result=new CommitResult();
		result.setResult(true);
		return result;
	}

	@Override
	public CommitResult updatestauts(BimrRiskEvent entity, Integer status,
			HhUsers use) {
		CommitResult result=new CommitResult();
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//entity.setStatus(entity);//办结申请中
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			entity.setStatus(status);
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	@Override
	public CommitResult saveriskeventExamine(BimrRiskEvent entity,List<BimrRiskEventAdoptstrategy> addoptstrategyList,
			String examStr, Integer examResult, HhUsers use,String optype,BimrRiskEventRelevancetrack track) {
		
			//不通过
		if(examResult.equals(Base.examresult_1)){
			if (optype.equals("event")) {//如果是事件退回
				entity.setStatus(82106);     //把状态设为被 事件已退回	
			}
		}
			
			//通过
		else{
			if (optype.equals("event")) {//如果是事件审核
				entity.setStatus(82102);       //把状态设为被 已审核未跟踪
			}
			String code = "";
    		String organ = entity.getVcOrganID();
    		Date d = new Date();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    		String time = sdf.format(d);
 
    		/* 	RE-HNAHO-实业缩写
				RE-INF-海航基础缩写
				RE-COO-供销大集缩写
				RE-FIN-海航金融缩写
				RE-E&H-教育医疗缩写
				RE-OT-其他
			*/

    		/*if(organ.contains("0-1-26655-847044-") || organ.contains("0-1-26655-26658-")){
				code = "RE-HNAHO-"+time+"-";
			}else if(organ.contains("0-1-26655-27534-")){
				code = "RE-INF-"+time+"-";
			}else if(organ.contains("0-1-26655-4010-")){
				code = "RE-COO-"+time+"-";
			}else if(organ.contains("0-1-26655-101351-")){
				code = "RE-FIN-"+time+"-";
			}else if(organ.contains("0-1-26655-848600-")){
				code = "RE-E&H-"+time+"-";
			}else {
				code = "RE-OT-"+time+"-";
			}*/
    		
    		/**
    		 *  
    		 * 海航物流整体简称：HNALO，0-1-855579-
    		 * 海航物流总部简称： HNALOHQ，0-1-855579-855580-
    		 * 直属企业：HNALODU，0-1-855579-856081-
    		 * 海航基础：HNAINF，0-1-855579-856150-
    		 * 供销大集：CCOOP，0-1-855579-856151-
    		 * 香港国际建投: HKICIM，0-1-855579-856152-
    		 * 海航投资:HNAINV，0-1-855579-856153-
    		 * CWT:CWT，0-1-855579-856154-
    		 * 海越股份:HYGF，0-1-855579-856155-
    		 * 航基股份: HNAINFRA，0-1-855579-856156-
    		 * 中国顺客隆:SKL，0-1-855579-856157-
    		 * 
    		 * */
    		
    		if(organ.contains("0-1-855579-855580-")){
				code = "RE-HNALOHQ-"+time+"-";
			}
    		else if(organ.contains("0-1-855579-856081-")){
				code = "RE-HNALODU-"+time+"-";
			}
    		else if(organ.contains("0-1-855579-856150-")){
				code = "RE-HNAINF-"+time+"-";
			}
    		else if(organ.contains("0-1-855579-856151-")){
				code = "RE-CCOOP-"+time+"-";
			}
    		else if(organ.contains("0-1-855579-856152-")){
				code = "RE-HKICIM-"+time+"-";
			}
    		else if(organ.contains("0-1-855579-856153-")){
				code = "RE-HNAINV-"+time+"-";
			}
    		else if(organ.contains("0-1-855579-856154-")){
				code = "RE-CWT-"+time+"-";
			}
    		else if(organ.contains("0-1-855579-856155-")){
				code = "RE-HYGF-"+time+"-";
			}
    		else if(organ.contains("0-1-855579-856156-")){
				code = "RE-HNAINFRA-"+time+"-";
			}
    		else if(organ.contains("0-1-855579-856157-")){
				code = "RE-SKL-"+time+"-";
			}
			else{
				code = "RE-HNALO-"+time+"-";
			}
    		
    		List<BimrRiskEvent> list = riskEventDao.getRiskEventByCode(code,entity.getId());
    		if(list.size() > 0){
    			BimrRiskEvent event = list.get(0);
    			String lastCode = event.getEventNum();
    			String[] str = lastCode.split("-");
    			int seq = Integer.parseInt(str[3]);
    			String str1 = String.format("%04d", seq+1);
    			code += str1;
    			entity.setEventNum(code);
    		}else {
    			entity.setEventNum(code+"0001");
			}
		}
		//设置审核人
/*		entity.setRelevanceauditorId(use.getVcEmployeeId());
		entity.setRelevanceauditor(use.getVcName());*/
		Integer examineentityid;
		if (optype.equals("event")){
			commonDao.saveOrUpdate(entity);
			
			 examineentityid=entity.getId();
		}else {
			commonDao.saveOrUpdate(track);
			
			 examineentityid=track.getId();
		}
		
		List<BimrRiskEventAdoptstrategy> oldList=riskEventDao.getAdoptstrategy(entity.getId());
		for(BimrRiskEventAdoptstrategy l:oldList){
			commonDao.delete(l);//物理删除
		}
		
		for(BimrRiskEventAdoptstrategy l:addoptstrategyList){
			l.setEventId(entity.getId());
			l.setIsDel(0);
			commonDao.saveOrUpdate(l);//新增
		}

		if (optype.equals("event")) {
			examineService.saveExamine( examineentityid, Base.examkind_risk_event, use, examStr, examResult);
		}else {
			examineService.saveExamine( examineentityid, Base.examkind_risk_track, use, examStr, examResult);
		}
		
		CommitResult result=new CommitResult();
		result.setResult(true);
		return result;
	}

	@Override
	public CommitResult updatestautsfortrack(BimrRiskEvent entity,BimrRiskEventRelevancetrack track, HhUsers use) {
		CommitResult result=new CommitResult();
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		commonDao.saveOrUpdate(track);
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	@Override
	public CommitResult updatestautsforfeedback(BimrRiskEvent entity,
			HhUsers use) {
		CommitResult result=new CommitResult();
		if(entity != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	public CommitResult savetrack(BimrRiskEventRelevancetrack entity,
			HhUsers use) {
		CommitResult result=new CommitResult();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(entity != null)
		{
			
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	@Override
	public CommitResult saveFeedback(BimrRiskEventFeedback entity, HhUsers use) {
		CommitResult result=new CommitResult();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(entity != null)
		{
			
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	@Override
	public CommitResult saveistrackover(BimrRiskEvent entity, HhUsers use) {
		boolean flag = true;
		CommitResult result=new CommitResult();
		if(entity != null){
			List<BimrRiskEventRelevancetrack> tracklist = riskEventDao.getRelevancetrackList(entity.getId());
			for (BimrRiskEventRelevancetrack item : tracklist) {
				if (item.getStatus() !=  82105) {
					flag = false;
				}
			}
		}
		if (flag == true) {
			entity.setStatus(82109);//如果所有事件跟踪都是已审核，则把事件大状态改为事件跟踪结束
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(flag);
		result.setEntity(entity);
		return result;
	}
	
	@Override
	public CommitResult deleteTrack(BimrRiskEventRelevancetrack entity,HhUsers use) {
		CommitResult result=new CommitResult();
		if(entity != null)
		{
			entity.setIsDel(1);
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	@Override
	public MsgPage getRiskEventSecondList(BimrRiskEventRelevance entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = riskEventDao.getRiskEventSecondListCount(entity);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrRiskEventSecondTop> result = new ArrayList<BimrRiskEventSecondTop>();
		List<Object> list = riskEventDao.getRiskEventSecondList(entity, offset, pageSize);
		for (Object object : list) {
			Object[] obj = (Object[])object;
			BimrRiskEventSecondTop event = new BimrRiskEventSecondTop();
			event.setId(obj[0] != null?Integer.valueOf(obj[0].toString()):null);
			event.setRelevanceTime(obj[1] != null?obj[1].toString():"");
			event.setOneName(obj[2] != null?obj[2].toString():"");
			event.setOneid(obj[3] != null?Integer.valueOf(obj[3].toString()):null);
			event.setSecondName(obj[4] != null?obj[4].toString():"");
			event.setSecondid(obj[5] != null?Integer.valueOf(obj[5].toString()):null);
			event.setSecondDefinition(obj[6] != null?obj[6].toString():"");
			event.setCount(obj[7] != null?Integer.valueOf(obj[7].toString()):null);
			result.add(event);
		}
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(result);
		return msgPage;
	}

	@Override
	public MsgPage getRiskEventSecondListforDetail(
			BimrRiskEventRelevance entity, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		List<Integer> eventidList = riskEventDao.getRiskEventID(entity);
		String _idString = eventidList.toString().replace("[", "").replace("]", "");
		// 总记录数
		Integer totalRecords = riskEventDao.getRiskEventSecondListforDetailCount(entity,_idString);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<BimrRiskEvent> list = riskEventDao.getRiskEventSecondforDetailList(entity, offset, pageSize,_idString);
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}

	@Override
	public List<BimrRiskEventRelevancetrack> getRelevancetrackList(Integer eventId) {
		return riskEventDao.getRelevancetrackList(eventId);
	}

	@Override
	public BimrRiskEventRelevancetrack getBimrRiskEventRelevancetrack(
			Integer trackId) {
		return riskEventDao.getBimrRiskEventRelevancetrack(trackId);
	}

	@Override
	public BimrRiskEventFeedback getBimrRiskEventFeedback(Integer feedbackid) {
		return riskEventDao.getBimrRiskEventFeedback(feedbackid);
	}

	@Override
	public BimrRiskEventAdoptstrategyFeedback getBimrRiskEventAdoptstrategyFeedback(Integer feedbackid) {
		return riskEventDao.getBimrRiskEventAdoptstrategyFeedback(feedbackid);
	}
	
	@Override
	public CommitResult deleteFeedback(BimrRiskEventFeedback entity, HhUsers use) {
		CommitResult result=new CommitResult();
		if(entity != null)
		{
			entity.setIsDel(1);
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}


	@Override
	public CommitResult deleteFeedbacks(BimrRiskEventAdoptstrategyFeedback entity, HhUsers use) {
		CommitResult result=new CommitResult();
		if(entity != null)
		{
			entity.setIsDel(1);
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	
	@Override
	public BimrRiskEventAdoptstrategyFeedback getAdoptstrategyFeedback(
			Integer AdoptstrategyFeedbcakId) {
		return riskEventDao.getAdoptstrategyFeedbcak(AdoptstrategyFeedbcakId);
	}

	@Override
	public CommitResult saveAdoptstrategyFeedbackList(BimrRiskEvent entity,
			List<BimrRiskEventAdoptstrategyFeedback> list, HhUsers use) {
		CommitResult result=new CommitResult();
		for(BimrRiskEventAdoptstrategyFeedback l:list){
			l.setEventId(entity.getId());
			l.setIsDel(0);
			commonDao.saveOrUpdate(l);//新增
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	@Override
	public CommitResult saveAdoptstrategyFeedback(BimrRiskEventAdoptstrategyFeedback entity,
			HhUsers use) {
		CommitResult result=new CommitResult();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(entity != null)
		{
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
	public CommitResult saveFeedbackExamine(BimrRiskEvent entity,
			String examStr, Integer examResult, HhUsers use) {
		//审核不通过
		if(examResult.equals(Base.examresult_1))
			entity.setStatus(82102);
		//审核通过
		else if(examResult.equals(Base.examresult_2))
			entity.setStatus(82109);
		commonDao.saveOrUpdate(entity);
		Integer examineentityid=entity.getId();
		examineService.saveExamine( examineentityid, Base.examkind_risk_feedback, use, examStr, examResult);
		CommitResult result=new CommitResult();
		result.setResult(true);
		return result;
	}

	




	
	
}
