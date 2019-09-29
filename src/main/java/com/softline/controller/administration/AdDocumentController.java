package com.softline.controller.administration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opendata.api.ODPRequest;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.common.ExcelDataTreating;
import com.softline.entity.AdDocument;
import com.softline.entity.HhBase;
import com.softline.entity.HhInterfaceLog;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.administration.IAdDocumentService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("adDocument")
public class AdDocumentController {
	
	@Resource(name = "adDocumentService")
	private IAdDocumentService	adDocumentService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	
	 private Logger logger = Logger.getLogger(AdDocumentController.class);
	 
	@RequestMapping("query")
	public String queryDocumentList(AdDocument entity, String organalID, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth = systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(dataauth);
        }

        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adDocumentService.getDocumentView(entity,pageNum,Base.pagesize,Base.fun_search,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setCompId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adDocument/query");
	    map.put("entity", entity);
	    addData(map);
	    if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/administration/documentList";
	}
	//汇总界面增加企业信息汇总统计
	@RequestMapping("query1")
	public String queryDocumentList1(AdDocument entity, String organalID, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth = systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(dataauth);
        }

        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adDocumentService.getDocumentView(entity,pageNum,Base.pagesize,Base.fun_search,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setCompId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adDocument/query1");
	    map.put("entity", entity);
	    addData(map);
	    if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		return "/summaryInfo/documentList";
	}
	
	//汇总界面增加企业信息汇总统计
		@RequestMapping("query2")
		public String queryDocumentList2(AdDocument entity, String organalID, Map<String, Object> map, HttpServletRequest request) throws IOException {
			HttpSession session=request.getSession();
			 //数据权限
			String str=(String) session.getAttribute("gzwsession_company");
			String dataauth = systemService.getDataauth(str);
	        //是否在查询公司时包含该公司下所有子公司的数据
	        String isAllCompany = request.getParameter("isallcompany");
	        map.put("isallcompany", isAllCompany);
	        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
	        	entity.setCompId(str);
	        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
	        	entity.setCompId(str);
	        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
	        	entity.setCompId(dataauth);
	        }

	        String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        Integer pageNum = Integer.valueOf(curPageNum);
	        MsgPage msgPage=adDocumentService.getDocumentView(entity,pageNum,Base.pagesize,Base.fun_search,isAllCompany);
	        if(!Common.notEmpty(isAllCompany))
	        	entity.setCompId(null);
		    map.put("msgPage", msgPage);
		    map.put("searchurl", "/shanghai-gzw/adDocument/query2");
		    map.put("entity", entity);
		    addData(map);
		    if(organalID==null)
			{
				HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
				organalID=use.getNnodeId();
			}
			map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
			return "/dataExchange/documentList";
		}
		
	@RequestMapping("examineQuery")
	public String queryExamineDocumentList(AdDocument entity, String organalID, Map<String, Object> map, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		//数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth=systemService.getDataauth(str);
        //是否在查询公司时包含该公司下所有子公司的数据
        String isAllCompany = request.getParameter("isallcompany");
        map.put("isallcompany", isAllCompany);
        if("isall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if("notall".equals(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(str);
        }else if(!Common.notEmpty(isAllCompany)&&!Common.notEmpty(entity.getCompId())){
        	entity.setCompId(dataauth);
        }
		String curPageNum = request.getParameter("pageNums");
		//entity.setStatus(Base.examstatus_2);
		
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adDocumentService.getDocumentView(entity,pageNum,Base.pagesize,Base.fun_examine,isAllCompany);
        if(!Common.notEmpty(isAllCompany))
        	entity.setCompId(null);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/adDocument/examineQuery");
	    map.put("entity", entity);
	    addData(map);
	    if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		/*HhOrganInfo orgal=systemService.getOrganInfoById(organalID);
		if(orgal!=null)
			map.put("checkedCompanyName", orgal.getVcFullName());*/
		return "/administration/documentExamineList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> reportStatus= baseService.getBases(Base.examstatustype);
		map.put("reportStatus",reportStatus);
	}
	
	@RequestMapping("addOrModify")
	public String addOrModifyDocument(AdDocument entity, String lastModifyDate, String organalID, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		HttpSession session=request.getSession();
		map.put("op", op);
		map.put("lastModifyDate", lastModifyDate);
		AdDocument theEntity;
		if(entity.getId()!=null) {
			theEntity=adDocumentService.getDocument(entity);
		}else {
			theEntity=new AdDocument();
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat df1 = new SimpleDateFormat("MM");
			Date date=new Date();
			Calendar rightNow = Calendar.getInstance();  
	        rightNow.setTime(date);  
	        rightNow.add(Calendar.MONTH, -1);
			theEntity.setYear(Integer.parseInt(df.format(rightNow.getTime())));
			theEntity.setMonth(Integer.parseInt(df1.format(rightNow.getTime())));
		}
		map.put("theEntity", theEntity);
		if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		String str=(String) session.getAttribute("gzwsession_company");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)) );
		//map.put("organalID", organalID);
		return "/administration/documentAddOrModify";
	}
	
	@RequestMapping("view")
	public String viewDocument(AdDocument entity, String lastModifyDate, Map<String, Object> map, HttpServletRequest request, String op) {
		map.put("op", op);
		map.put("lastModifyDate", lastModifyDate);
		AdDocument theEntity=adDocumentService.getDocument(entity);
		map.put("theEntity", theEntity);
		//查看、上报时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_document);
		map.put("examineList", examineList);
		return "/administration/documentView";
	}
	
	@RequestMapping("examine")
	public String examineDocument(AdDocument entity, String lastModifyDate, Map<String, Object> map, HttpServletRequest request) {
		map.put("lastModifyDate", lastModifyDate);
		AdDocument theEntity=adDocumentService.getDocument(entity);
		map.put("theEntity", theEntity);
		//审批时，可以查看到审批记录
		List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_document);
		map.put("examineList", examineList);
		return "/administration/documentExamine";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateDocument(AdDocument entity, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		CommitResult result= adDocumentService.saveOrUpdateDocument(entity, use);
		String data="";
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateAndReported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateAndRepotedDocument(AdDocument entity, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		entity.setStatus(Base.examstatus_2);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setReportDate(df.format(date));
		entity.setReportPersonId(use.getVcEmployeeId());
		entity.setReportPersonName(use.getVcName());
		CommitResult result= adDocumentService.saveOrUpdateDocument(entity, use);
		if(result.isResult())
			potalMsgService.savePortalMsg(new PortalMsg(entity.getCompName()+entity.getYear()+"年"+entity.getMonth()+"月公文审批及时率数据未审核","行政办公",df.format(new Date()),0,0,0,
					use.getVcName(),df.format(new Date()),use.getVcName(),
					df.format(new Date()),entity.getCompId(),systemService.getParentBynNodeID(entity.getCompId(), null),"bimWork_gwsjsh_exam","ad_document",entity.getId().toString(),"/shanghai-gzw/adDocument/examineQuery?id="+entity.getId()));
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/reported", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportedDocument(Integer id, String lastModifyDate, HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		AdDocument entity=adDocumentService.getDocument(id);
		entity.setStatus(Base.examstatus_2);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setReportDate(df.format(date));
		entity.setReportPersonId(use.getVcEmployeeId());
		entity.setReportPersonName(use.getVcName());
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result= adDocumentService.saveOrUpdateDocument(entity, use);
		if(result.isResult())
			potalMsgService.savePortalMsg(new PortalMsg(entity.getCompName()+entity.getYear()+"年"+entity.getMonth()+"月公文审批及时率数据未审核","行政办公",df.format(new Date()),0,0,0,
					use.getVcName(),df.format(new Date()),use.getVcName(),
					df.format(new Date()),entity.getCompId(),systemService.getParentBynNodeID(entity.getCompId(), null),"bimWork_gwsjsh_exam","ad_document",entity.getId().toString(),"/shanghai-gzw/adDocument/examineQuery?id="+entity.getId()));
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid, String lastModifyDate, String examStr,Integer examResult,HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		AdDocument entity = adDocumentService.getDocument(entityid);
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result= adDocumentService.saveDocumentExamine(entity, examStr, examResult, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("ad_document", entityid.toString());
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deleteDocument(Integer id, String lastModifyDate, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		AdDocument entity = adDocumentService.getDocument(id);
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result=adDocumentService.deleteDocument(entity, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("ad_document", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "checkDelete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String checkDelete(Integer id) throws IOException {
		AdDocument entity = adDocumentService.getDocument(id);
		CommitResult result = new CommitResult();
		if(entity == null){
			result.setResult(false);
		}else {
			result.setResult(true);
		}
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/synIDocument", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String synIDocument(String year, String month,String NodeId, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");
				//获取上月年、月
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
		String first=null;
		String last=null;
		if(!Common.notEmpty(month)||!Common.notEmpty(year)){
			if("0".equals(calendar.get(Calendar.MONTH))){
				month = "12";
				year = (calendar.get(Calendar.YEAR)-1)+"";
			}else{
				month = calendar.get(Calendar.MONTH) + "";
				year = calendar.get(Calendar.YEAR) + "";
			}
			//获取前月的第一天：
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	    	 first = format.format(calendar.getTime());
	    	//获取前月的最后一天
	    	calendar.add(Calendar.MONTH, 1);
	    	calendar.set(Calendar.DAY_OF_MONTH,0);
	    	 last = format.format(calendar.getTime());
		}else {
			calendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
			calendar.set(Calendar.YEAR, Integer.parseInt(year));
			calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
			first = format.format(calendar.getTime());
			calendar.add(Calendar.MONTH, 1);
	    	calendar.set(Calendar.DAY_OF_MONTH,0);
	    	 last = format.format(calendar.getTime());
		}
	
    	
		boolean flag=true;
    	CommitResult result=new CommitResult();
		List list = adDocumentService.getSynDocumentComp(year, month);
		if(list.size() == 0){
			result.setExceptionStr("没有需要同步的数据！");
			result.setResult(false);
			String data=JSONArray.toJSONString(result);
			return data;
		}
		if (Common.notEmpty(NodeId)) {
			for (Object object : list) {
				if(NodeId.equals(object.toString())){
					tb(first, last, NodeId, user, year, month);
					flag=false;
					break;
				}
			}
			if (flag) {
				result.setExceptionStr("同步失败，数据已存在！");
				result.setResult(false);
				String data=JSONArray.toJSONString(result);
				
				return data;
		    }
		}
		else{
			for(Object obj : list){
				tb(first, last, String.valueOf(obj), user, year, month);
			}
		}
		result.setExceptionStr("数据同步成功！");
		result.setResult(true);
		String data=JSONArray.toJSONString(result);
		return data;
	}
	
	
	
	public void tb(String first,String last,String nnodeId,HhUsers user,String year,String month) {
		HhInterfaceLog hhInterfaceLog = new HhInterfaceLog();
		hhInterfaceLog.setCallVcEmployeeId(user.getVcEmployeeId());
		hhInterfaceLog.setCallPersonName(user.getVcName());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		hhInterfaceLog.setCallTime(df.format(new Date()));
		hhInterfaceLog.setIntefaceName(Base.DOC_METHOD);
		hhInterfaceLog.setIntefaceAlias("公文审批及时率");
		hhInterfaceLog.setRemark(systemService.getHrOrginfoNameBynnodeID(nnodeId));
		int i = 0;
		//String nNodeID = obj.toString();
		//传入url与秘钥
		String res=new ODPRequest(Base.ESB_URL,Base.BYLAW_Appsecret)
		.addTextSysPara("AccessToken", Base.BYLAW_AccessToken)
		.addTextSysPara("Method", Base.DOC_METHOD)
		.addTextSysPara("Format","json")
		//应用参数
		.addTextAppPara("SysSource",Base.DOC_SYS_SOURCE)
		.addTextAppPara("NodeId",nnodeId)
		.addTextAppPara("StartTime",first)
		.addTextAppPara("EndTime",last)
		.post();
		logger.info(res);
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
						return;
					}
					AdDocument entity = new AdDocument();
					entity.setCreatePersonName(user.getVcName());
					entity.setCreatePersonId(user.getVcEmployeeId());
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
					commonService.saveOrUpdate(entity);
				}
			}
		}
		hhInterfaceLog.setBackResult(responseInfo.getResult());
		hhInterfaceLog.setBackResultInfo(cc.toString());
		commonService.saveOrUpdate(hhInterfaceLog);
	}
	
	
	@RequestMapping("export")
	public String exportDocumentList(AdDocument entity, String organalID, Map<String, Object> map, HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		 //数据权限
		String str=(String) session.getAttribute("gzwsession_company");
		String dataauth = systemService.getDataauth(str);
        if(!Common.notEmpty(entity.getCompId()))
        	entity.setCompId(dataauth);
        String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=adDocumentService.getAllDocumentView(entity,Base.fun_search);

        // 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = null;
		HSSFRow row = null;
		//导出文件名称
		String fileName = "公文审批及时率数据";
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		sheet = wb.createSheet("公文审批及时率数据");
		//设置宽度
		sheet.setColumnWidth((short) 1, (short) 4000);
		sheet.setColumnWidth((short) 2, (short) 10000);
		sheet.setColumnWidth((short) 3, (short) 4000);
		sheet.setColumnWidth((short) 4, (short) 4000);
		sheet.setColumnWidth((short) 5, (short) 5000);
		sheet.setColumnWidth((short) 6, (short) 4000);
		sheet.setColumnWidth((short) 7, (short) 4000);
		sheet.setColumnWidth((short) 8, (short) 4000);
		sheet.setColumnWidth((short) 9, (short) 4000);
		sheet.setColumnWidth((short) 10, (short) 4000);
//		
		//参数说明：1：开始行 2：结束行  3：开始列 4：结束列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,11)); 
		/*sheet.addMergedRegion(new CellRangeAddress(1,1,10,12)); 
		sheet.addMergedRegion(new CellRangeAddress(1,1,15,20)); */
		// 3.在sheet中添加表头第0行
		row = sheet.createRow(0);
		
		//title1
		Cell cell = row.createCell(0);
		cell.setCellStyle(styleList.get(0));
		cell.setCellValue("公文审批及时率数据");
		
		//title2 样式
		row = sheet.createRow(1);
		for(int n=0;n<=11;n++){
			sheet.addMergedRegion(new CellRangeAddress(1,2,n,n));
		}
		String [] titleArray = {"序号","时间","单位名称","公文总量（条）","超时公文总量（条）","平均公文流转时间（小时）","平均流转节点（个）","审批及时率（%）","创建人","上报人","审核人","状态"};
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
		
		row = sheet.createRow(2);
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
		Integer rowNum = 3;
		sheet = adDocumentService.setSumExcelData(null,sheet,msgPage.getList(),styleList.get(1),rowNum);
		tool.outputExcel(wb,fileName,response);
		return null;
	}
}
