package com.softline.service.project.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opendata.api.ODPRequest;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.dao.project.IWkReportDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhFile;
import com.softline.entity.HhInterfaceLog;
import com.softline.entity.HhUsers;
import com.softline.entity.project.PjApprove;
import com.softline.entity.project.PjNode;
import com.softline.entity.project.PjWeekreport;
import com.softline.entity.project.PjWeekreportHistory;
import com.softline.entity.project.PjWeekreportTemp;
import com.softline.entityTemp.BackBylawInfo;
import com.softline.entityTemp.ProjectWeekReportESBEntity;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.bylaw.impl.BylawInfoService;
import com.softline.service.project.IWkReportService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;
@Service("wkReportService")
public class WkReportService implements IWkReportService {
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("commonService")
	private ICommonService commonService;
	
	@Autowired
	@Qualifier("wkReportDao")
	private IWkReportDao wkReportDao;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//读取配置文件中的信息，用于海航ESB中的接口调用
	@Value("${project.URL}")
	private String URL;
	@Value("${project.method}")
	private String method;
	@Value("${project.Appsecret}")
	private String Appsecret;
	@Value("${project.AccessToken}")
	private String AccessToken;
	
	static Logger log = Logger.getLogger(WkReportService.class.getName());
	
	@Override
	public String saveWkReportTemp(HhUsers user, PjWeekreportTemp wkReportTemp,
			MultipartFile file,PjWeekreportTemp OldEntity) {
		if(wkReportTemp.getId() != null){//编辑
			//PjWeekreportTemp entity = (PjWeekreportTemp) commonService.findById(PjWeekreportTemp.class, wkReportTemp.getId());
			wkReportTemp.setCreateDate(OldEntity.getCreateDate());
			wkReportTemp.setCreatePersonId(OldEntity.getCreatePersonId());
			wkReportTemp.setCreatePersonName(OldEntity.getCreatePersonName());
			wkReportTemp.setLastModifyDate(df.format(new Date()));
			wkReportTemp.setLastModifyPersonId(user.getVcEmployeeId());
			wkReportTemp.setLastModifyPersonName(user.getVcName());
			
			wkReportTemp.setWrDatetime(OldEntity.getWrDatetime());
			
		}else{//新增
			wkReportTemp.setCreateDate(df.format(new Date()));
			wkReportTemp.setCreatePersonId(user.getVcEmployeeId());
			wkReportTemp.setCreatePersonName(user.getVcName());
			wkReportTemp.setWrDatetime(wkReportTemp.getWrStartTime() + "~" + wkReportTemp.getWrEndTime());
		}
		wkReportTemp.setIsdel(0);
		wkReportTemp.setReportStatus(0);
		commonDao.saveOrUpdate(wkReportTemp);
		String filePath = DES.PROJECT_WEEKLINE_TEMP_PATH + wkReportTemp.getId() + File.separator;
		String fileName = "z"+wkReportTemp.getWrWeek()+".pdf";
		//保存附件
		if (file!= null && !file.isEmpty()) {
			Common.deleteFile(filePath, fileName);
			Common.saveWkFile(file, filePath, fileName);
			wkReportTemp.setWrLine(filePath+fileName);
			//wkReportTemp.setWkPDFFile(f);
		}
		String result = JSON.toJSONString(wkReportTemp);
		return result;
	}

	@Override
	public MsgPage getWkReports(Integer pjId, Integer curPageNum,
			Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<PjWeekreport> t = wkReportDao.getWkReports(pjId, null, null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<PjWeekreport> list = wkReportDao.getWkReports(pjId, offset, pageSize);
    	//添加附件
    	for(int i = 0; i < list.size();i++){
    		PjWeekreport entity  = 	list.get(i);
    		HhFile file = commonDao.getFileByEnIdAndType(entity.getId(), Base.PROJECT_WK_PDF_TEMP);
    		entity.setWkPDFFile(file);
    	}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public void saveWkReport(HhUsers user, PjWeekreport wkReport,
			MultipartFile file, PjWeekreport OldEntity) {
		if(wkReport.getId() != null){//编辑
			//PjWeekreportTemp entity = (PjWeekreportTemp) commonService.findById(PjWeekreportTemp.class, wkReportTemp.getId());
			wkReport.setCreateDate(OldEntity.getCreateDate());
			wkReport.setCreatePersonId(OldEntity.getCreatePersonId());
			wkReport.setCreatePersonName(OldEntity.getCreatePersonName());
			
			wkReport.setLastModifyDate(df.format(new Date()));
			wkReport.setLastModifyPersonId(user.getVcEmployeeId());
			wkReport.setLastModifyPersonName(user.getVcName());
			
			wkReport.setReportPersonId(OldEntity.getReportPersonId());
			wkReport.setReportPersonName(OldEntity.getReportPersonName());
			wkReport.setReportStatus(OldEntity.getReportStatus());
			wkReport.setReportTime(OldEntity.getReportTime());
			wkReport.setWrDatetime(OldEntity.getWrDatetime());
			if(OldEntity.getReportStatus()>1){//已审核、已退回的节点被修改后，其状态变更为“未上报”
				wkReport.setReportStatus(0);//未上报
			}
			
			wkReport.setVersion(OldEntity.getVersion());
			wkReport.setWrLine(OldEntity.getWrLine());
		}else{//新增
			wkReport.setCreateDate(df.format(new Date()));
			wkReport.setCreatePersonId(user.getVcEmployeeId());
			wkReport.setCreatePersonName(user.getVcName());
			wkReport.setReportStatus(0);
			wkReport.setVersion(0);
			wkReport.setWrDatetime(wkReport.getWrStartTime()+"~" + wkReport.getWrEndTime());
		}
		
		wkReport.setIsdel(0);
		commonDao.saveOrUpdate(wkReport);
		
		String filePath = DES.PROJECT_WEEKLINE_NORMAL_PATH + wkReport.getPjId() + File.separator + wkReport.getId() + File.separator;
		String fileName = "z"+wkReport.getWrWeek()+".pdf";
		//保存附件
		if (file!= null && !file.isEmpty()) {
			Common.deleteFile(filePath, fileName);
			Common.saveWkFile(file, filePath, fileName);
			wkReport.setWrLine(filePath+fileName);
			//wkReportTemp.setWkPDFFile(f);
		}
		
	}

	@Override
	public List<PjWeekreport> getWkReports(Integer pjId, Integer reportStatus) {
		// TODO Auto-generated method stub
		List<PjWeekreport> results = wkReportDao.getWkReports(pjId, reportStatus);
		for(int i=0;i <results.size();i++ ){
			PjWeekreport wk = results.get(i);
			HhFile file = commonDao.getFileByEnIdAndType(wk.getId(), Base.PROJECT_WK_PDF_TEMP);
			wk.setWkPDFFile(file);
		}
		return results;
	}

	@Override
	public List<PjWeekreportHistory> getPjWeekreportHistories(Integer wkId) {
		List<PjWeekreportHistory> results =  wkReportDao.getPjWeekreportHistories(wkId);
		for(int i = 0; i < results.size(); i++){
			PjWeekreportHistory entity = results.get(i);
			PjApprove approve = (PjApprove) commonDao.findById(PjApprove.class, entity.getApproveId());
			entity.setApprove(approve);
		}
		return results;
	}

	@Override
	public void deleteWkReport(Integer id, HhUsers users) {
		// TODO Auto-generated method stub
		PjWeekreport  weekreport = (PjWeekreport) commonDao.findById(PjWeekreport.class, id);
		weekreport.setIsdel(1);
		weekreport.setLastModifyDate(df.format(new Date()));
		weekreport.setLastModifyPersonId(users.getVcEmployeeId());
		weekreport.setLastModifyPersonName(users.getVcName());
		
		PjWeekreportHistory wkReportHistory = new PjWeekreportHistory();
		BeanUtils.copyProperties(weekreport, wkReportHistory);
		wkReportHistory.setId(null);
		wkReportHistory.setWkReportId(weekreport.getId());
		commonDao.saveOrUpdate(wkReportHistory);
		
		weekreport.setVersion(weekreport.getVersion()==null?1:(weekreport.getVersion()+1));
		
	}

	@Override
	public ProjectWeekReportESBEntity getWeekReportSubInfo(String year, String week,String Pid,HhUsers users) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HhInterfaceLog intefaceLog = new HhInterfaceLog();
		intefaceLog.setIntefaceName(method);
		intefaceLog.setIntefaceAlias("重点基建工程周报接口");
		intefaceLog.setCallPersonName(users.getVcName());
		intefaceLog.setCallTime(df.format(new Date()));
		intefaceLog.setCallVcEmployeeId(users.getVcEmployeeId());
		
		ProjectWeekReportESBEntity result  = new ProjectWeekReportESBEntity();
		log.info("开始调用获取周报信息的接口");
		String res = new ODPRequest(URL, Appsecret)
				.addTextSysPara("Method", method)
				.addTextSysPara("AccessToken", AccessToken)
				.addTextSysPara("Format", "json")
				//应用参数
				.addTextAppPara("year", year)
				.addTextAppPara("week", week)
				.addTextAppPara("type", "3") //日报：1，月报：2，周报：3
				.addTextAppPara("sid", Pid)
				.post();
		log.info(res);
		//将字符串转化为JSON对象
		JSONObject obj= JSON.parseObject(res);
		//获取返回结果，调用接口成功或失败
		Object cc = obj.getJSONObject("MsgResponse").get("ResponseInfo");
		ResponseInfo responseInfo = JSON.parseObject(cc.toString(), ResponseInfo.class);
		String backResult = responseInfo.getResult();
		if(backResult.equals("1")){
			Object ss =  obj.getJSONObject("MsgResponse").getJSONObject("Data").get("data");
			if(ss != null){
				List<ProjectWeekReportESBEntity> dataList = JSON.parseArray(ss.toString(),ProjectWeekReportESBEntity.class);
				if(dataList != null && dataList.size() == 1){
					result = dataList.get(0);
				}
			}
			
		}
		intefaceLog.setBackResult(backResult);
		intefaceLog.setBackResultInfo(cc.toString());
		commonDao.saveOrUpdate(intefaceLog);
		log.info("调用接口成功！");
		return result;
	}
}
