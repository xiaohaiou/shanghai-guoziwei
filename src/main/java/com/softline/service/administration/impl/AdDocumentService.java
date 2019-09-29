package com.softline.service.administration.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opendata.api.ODPRequest;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.administration.IAdDocumentDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.AdDocument;
import com.softline.entity.HhInterfaceLog;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.administration.IAdDocumentService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;
@Service("adDocumentService")
public class AdDocumentService implements IAdDocumentService {
	
	@Autowired
	@Qualifier("adDocumentDao")
	private IAdDocumentDao adDocumentDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Resource(name = "systemService")
	private ISystemService systemService;	
	
	@Resource(name = "examineService")
	private IExamineService examineService;

	@Resource(name = "systemDao")
	private ISystemDao systemDao;	
	
	@Override
	public MsgPage getDocumentView(AdDocument entity, Integer curPageNum,Integer pageSize, Integer fun){
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = adDocumentDao.getDocumentListCount(entity, fun);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<AdDocument> list = adDocumentDao.getDocumentList(entity, offset, pageSize, fun);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	/**
	 * 查询该公司，以及公司下所有子公司的数据
	 */
	@Override
	public MsgPage getDocumentView(AdDocument entity, Integer curPageNum,Integer pageSize, Integer fun,String isAllCompany){
		// TODO Auto-generated method stub
		if("isall".equals(isAllCompany)){
			
			String nNodeID = entity.getCompId();
			if(!Common.notEmpty(nNodeID))
				return new MsgPage();
			String nNodeIDs = systemService.getDataauthNnodeIDs(nNodeID);
			nNodeIDs = dealWithAuthorstr(nNodeIDs);
			entity.setCompId(nNodeIDs);
			MsgPage msgPage = new MsgPage();       
			//总记录数
			Integer totalRecords = adDocumentDao.getDocumentListCount(entity, fun);
			//当前页开始记录
			int offset = msgPage.countOffset(curPageNum,pageSize);  
			//分页查询结果集
			List<AdDocument> list = adDocumentDao.getDocumentList(entity, offset, pageSize, fun);
			entity.setCompId(nNodeID);
			msgPage.setPageNum(curPageNum);
			msgPage.setPageSize(pageSize);
			msgPage.setTotalRecords(totalRecords);
			msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
			msgPage.setList(list);    
			return msgPage;
		}else{
			return getDocumentView(entity,curPageNum,pageSize,fun);
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
	public AdDocument getDocument(AdDocument entity) {
		// TODO Auto-generated method stub
		return adDocumentDao.getDocument(entity);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveDocumentCheck(AdDocument entity)
	{
		CommitResult result=new CommitResult();
		if(!adDocumentDao.saveDocumentCheck(entity))
		{
			result=CommitResult.createErrorResult("此单位"+entity.getYear()+"年度" + entity.getMonth() + "月已经有数据，不能再添加");
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
	private CommitResult theTimeDocumentCheck(AdDocument entity)
	{
		CommitResult result=new CommitResult();
		if(!adDocumentDao.theTimeDocumentCheck(entity))
		{
			result=CommitResult.createErrorResult("此数据已被其他人员操作，操作失败");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	@Override
	public CommitResult saveOrUpdateDocument(AdDocument entity, HhUsers use) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveDocumentCheck(entity);
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
			CommitResult result1=theTimeDocumentCheck(entity);
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
	public CommitResult deleteDocument(AdDocument entity, HhUsers use) {
		// TODO Auto-generated method stub
		CommitResult result=new CommitResult();
		result=theTimeDocumentCheck(entity);
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
	public AdDocument getDocument(Integer id) {
		// TODO Auto-generated method stub
		return adDocumentDao.getDocument(id);
	}
	
	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 公文ID
	 * @return
	 */
	public CommitResult saveDocumentExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		AdDocument entity=adDocumentDao.getDocument(entityID);
		if(!entity.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	@Override
	public CommitResult saveDocumentExamine(AdDocument entity, String examStr,
			Integer examResult, HhUsers use) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=theTimeDocumentCheck(entity);
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
		CommitResult documentresult= saveOrUpdateDocument(entity,use);
		if(!documentresult.isResult())
			return documentresult;
		
		AdDocument document= (AdDocument) documentresult.getEntity();
		Integer examineentityid=document.getId();

		examineService.saveExamine( examineentityid, Base.examkind_document, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}
	
	@Override
	public List getSynDocumentComp(String year, String month){
		return adDocumentDao.getVcCompanyId(year, month);
	}

	@Override
	public CommitResult synIDocument() {
		// TODO Auto-generated method stub
		
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
    	//获取前月的第一天：
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
    	String first = format.format(calendar.getTime());
    	//获取前月的最后一天
    	calendar.add(Calendar.MONTH, 1);
    	calendar.set(Calendar.DAY_OF_MONTH,0);
    	String last = format.format(calendar.getTime());
		
    	
		List list = getSynDocumentComp(year, month);
		if(list.size() == 0){
			result.setExceptionStr("没有需要同步的数据！");
			result.setResult(false);
			return result;
		}
		else{
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int i = 0;
			for(Object obj : list){
				HhInterfaceLog hhInterfaceLog = new HhInterfaceLog();
				hhInterfaceLog.setCallPersonName("系统管理员");
				hhInterfaceLog.setCallVcEmployeeId("9999999901");
				hhInterfaceLog.setCallTime(df.format(new Date()));
				hhInterfaceLog.setIntefaceAlias("公文审批及时率");
				hhInterfaceLog.setIntefaceName(Base.DOC_METHOD);
				hhInterfaceLog.setRemark(systemDao.getHrOrginfoNameBynnodeID(obj.toString()));
				String nNodeID = obj.toString();
				//传入url与秘钥
				String res=new ODPRequest(Base.ESB_URL,Base.BYLAW_Appsecret)
				.addTextSysPara("AccessToken", Base.BYLAW_AccessToken)
				.addTextSysPara("Method", Base.DOC_METHOD)
				.addTextSysPara("Format","json")
				//应用参数
				.addTextAppPara("SysSource",Base.DOC_SYS_SOURCE)
				.addTextAppPara("NodeId",nNodeID)
				.addTextAppPara("StartTime",first)
				.addTextAppPara("EndTime",last)
				.post();
				JSONObject o= JSON.parseObject(res);
				Object cc = o.getJSONObject("MsgResponse").get("ResponseInfo");
				ResponseInfo responseInfo = JSON.parseObject(cc.toString(), ResponseInfo.class);
				if(responseInfo.getResult().equals("1")){
					JSONArray ss =  o.getJSONObject("MsgResponse").getJSONArray("Data");
					if(ss.size() > 0){
						JSONObject c = ss.getJSONObject(0);
						JSONArray s = c.getJSONArray("OAData").getJSONArray(0);
						if(s.size() > 0){
							JSONObject b = s.getJSONObject(0);
							if(b.get("DocCount") == null || "".equals(b.get("DocCount")) || "0".equals(b.get("DocCount"))){
								continue;
							}
							AdDocument entity = new AdDocument();
							entity.setCreatePersonName("系统管理员");
							entity.setCreatePersonId("9999999901");
							entity.setCreateDate(df.format(new Date()));
							entity.setCompId(b.getString("NodeID"));
							entity.setCompName(b.getString("OrganName"));
							entity.setYear(Integer.parseInt(year));
							entity.setMonth(Integer.parseInt(month));
							entity.setIsdel(0);
							entity.setStatus(50112);
							entity.setTotalDocument(b.getInteger("DocCount"));
							entity.setTotalOverTimeDocument(b.getInteger("OutTimeDocCount"));
							entity.setAvgDocumentTime(b.getDouble("DocTimeCount"));
							entity.setAvgNode(b.getDouble("NodeAvgCount"));
							entity.setExamineRatio(Double.valueOf(b.getString("DocInTimePercent").replaceAll("%","")));
							commonDao.saveOrUpdate(entity);
						}
					}
				}
				hhInterfaceLog.setBackResult(responseInfo.getResult());
				hhInterfaceLog.setBackResultInfo(cc.toString());
				commonDao.saveOrUpdate(hhInterfaceLog);
			}
		}
		
		result.setExceptionStr("同步成功！");
		result.setResult(true);
		return result;
		} catch (Exception e) {
			result.setExceptionStr("同步失败！");
			result.setResult(false);
			return result;
		}
		
	}
	
	@Override
	public MsgPage getAllDocumentView(AdDocument entity,Integer fun) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = adDocumentDao.getDocumentListCount(entity, fun);
    	//当前页开始记录
//    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<AdDocument> list = adDocumentDao.getDocumentList(entity, 0, null, fun);
//    	msgPage.setPageNum(0);
//    	msgPage.setPageSize(0);
    	msgPage.setTotalRecords(totalRecords);
//    	msgPage.setTotalPage(0);
    	msgPage.setList(list);    
        return msgPage;
	}
	
	
	@Override
	public HSSFSheet setSumExcelData(HSSFRow row,HSSFSheet sheet,List<AdDocument> listEntity,HSSFCellStyle style,Integer rowNum) {
		if(listEntity.size()>0){
			for(int i = 0;i<listEntity.size();i++){
				row = sheet.createRow(rowNum+i);
				this.setAdDocument(listEntity.get(i), row, style ,(i+1));
			}
			rowNum = sheet.getLastRowNum()+1;
			row = sheet.createRow(rowNum);
		}
		return sheet;
	}
	
	/**
	 * @param entity 实体
	 * @param row	行
	 * @param cell	列
	 * @param style	样式
	 * @param num	序号
	 */
	private Cell setAdDocument(AdDocument entity,HSSFRow row,HSSFCellStyle style,Integer num) {
		Cell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(num);//序号
		cell=row.createCell(1);cell.setCellStyle(style);cell.setCellValue(String.valueOf(entity.getYear())+"-"+String.valueOf(entity.getMonth()));
		cell=row.createCell(2);cell.setCellStyle(style);cell.setCellValue(entity.getCompName());
		cell=row.createCell(3);cell.setCellStyle(style);cell.setCellValue(String.valueOf(entity.getTotalDocument()));
		cell=row.createCell(4);cell.setCellStyle(style);cell.setCellValue(String.valueOf(entity.getTotalOverTimeDocument()));
		cell=row.createCell(5);cell.setCellStyle(style);cell.setCellValue(String.valueOf(entity.getAvgDocumentTime()));
		cell=row.createCell(6);cell.setCellStyle(style);cell.setCellValue(String.valueOf(entity.getAvgNode()));
		cell=row.createCell(7);cell.setCellStyle(style);cell.setCellValue(String.valueOf(entity.getExamineRatio()));
		cell=row.createCell(8);cell.setCellStyle(style);cell.setCellValue(entity.getCreatePersonName());
		cell=row.createCell(9);cell.setCellStyle(style);cell.setCellValue(entity.getReportPersonName());
		cell=row.createCell(10);cell.setCellStyle(style);cell.setCellValue(entity.getAuditorPersonName());
		cell=row.createCell(11);cell.setCellStyle(style);cell.setCellValue(entity.getStatusName());
		return cell;
	}
	
	
}
