package com.softline.controller.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.common.ExcelDataTreating;
import com.softline.controller.common.commonController;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.ReportReceivabledebt;
import com.softline.entity.ReportReceivabledebtOuter;
import com.softline.entity.ReportReceivabledebtinfo;
import com.softline.entity.ReportReceivabledebtinfoOuter;
import com.softline.entity.SysExamine;
import com.softline.entity.financing.ReportFinancingWeekThis;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReceivableDebtOuterService;
import com.softline.service.report.IReceivableDebtService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.DateUtils;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/receivabledebtOuter")
public class ReportReceivableDebtOuterController {
	
	@Resource(name = "receivabledebtOuterService")
	private IReceivableDebtOuterService receivabledebtOuterService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportReceivabledebtOuter entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/receivabledebtOuter";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=receivabledebtOuterService.getReceivabledebtListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/receivabledebtOuter/list");
		map.put("mapurl", "/shanghai-gzw/receivabledebtOuter");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportReceivabledebtOuter/reportReceivabledebtOuterList";
	}
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportReceivabledebtOuter entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/receivabledebtOuter";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
        entity.setGetOperateType(Base.fun_examine);
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=receivabledebtOuterService.getReceivabledebtListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	  
		map.put("searchurl", "/shanghai-gzw/receivabledebtOuter/examinelist");
	
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportReceivabledebtOuter/reportReceivabledebtOuterExamineList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		
		map.put("examstatustype",examstatustype);
		
	}
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,Integer type,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportReceivabledebtOuter entityview;
		if(id==null)
		{
			entityview=new ReportReceivabledebtOuter();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
			entityview=receivabledebtOuterService.getReceivabledebtbyID(id);
		Integer status=0;
		status=entityview.getStatus();
		map.put("status",status);
		map.put("entityview", entityview);	
		return "/report/reportReceivabledebtOuter/reportReceivabledebtOuterExamine";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportReceivabledebtOuter entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
		
		ReportReceivabledebtOuter entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportReceivabledebtOuter();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
		}
		else
		{	
			entityview=receivabledebtOuterService.getReceivabledebtbyID(entity.getId());
			List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_receivabledebtinner);
			map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);	
		addData(map);
		
		return "/report/reportReceivabledebtOuter/reportReceivabledebtOuterNewModify";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportReceivabledebtOuter reportReceivabledebt,MultipartFile itemFile,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		int maxWeekOFYear = DateUtils.getMaxWeekNumOfYear(reportReceivabledebt.getYear());
		if(reportReceivabledebt==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}else if(reportReceivabledebt.getWeek() > maxWeekOFYear){
			result=CommitResult.createErrorResult("填写的周数大于改年最大的周数");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			reportReceivabledebt.setStatus(Base.examstatus_1);
			result= receivabledebtOuterService.saveReceivabledebt(reportReceivabledebt, use,itemFile);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//跳转查看页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportReceivabledebtOuter entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportReceivabledebtOuter();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=receivabledebtOuterService.getReceivabledebtbyID(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_receivabledebtout);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/reportReceivabledebtOuter/reportReceivabledebtOuterView";
	}
	
	//查询列表页面的上报，即跳向复核页面
	@RequestMapping(value ="/postexam")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		ReportReceivabledebtOuter entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportReceivabledebtOuter();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=receivabledebtOuterService.getReceivabledebtbyID(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_receivabledebtout);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		map.put("op", op);
		return "/report/reportReceivabledebtOuter/reportReceivabledebtOuterView";
	}
	
	
	//查询页面的复核即复核页面的上报功能
	@ResponseBody
	@RequestMapping(value ="/recheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _recheck(Integer id,MultipartFile itemFile,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		ReportReceivabledebtOuter entity=receivabledebtOuterService.getReceivabledebtbyID(id);
		if(entity!=null)
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setRecheckDate(df.format(new Date()));
			entity.setRecheckPersonId(use.getVcEmployeeId());
			entity.setRecheckPersonName(use.getVcName());
			entity.setStatus(Base.examstatus_2);
			result= receivabledebtOuterService.saveReceivabledebt(entity, use,itemFile);
		}
		else
		{
			
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 删除方法
	 * @param id
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportgroupdelete(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=receivabledebtOuterService.deleteReceivabledebt(id, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= receivabledebtOuterService.saveReceivabledebtExamine(entityid, examStr, examResult, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 进入projectDetail.jsp页面中的nodeList
	 * @param pjId
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/_itemViewList")
	public String _nodeViewList(ReportReceivabledebtOuter entity,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  receivabledebtOuterService.getReceivabledebtInfoListView(entity, Integer.parseInt(curPageNum), Base.pagesize);
		map.put("msgPage", msgPage);
		String view = request.getParameter("view");
		map.put("view", view);
		return "/report/reportReceivabledebtOuter/reportReceivabledebtInfoOuterList";
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(Integer id,String operate,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		ReportReceivabledebtOuter entity=receivabledebtOuterService.getReceivabledebtbyID(id);
		result.setResult(true);
		if(entity==null)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}
	
	//应收债权(外部)明细导出
	@RequestMapping("/export")
	public String reportReceivabledebtOuterExport(ReportReceivabledebtinfoOuter entity,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException {

        
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//数据权限
        entity.setParentorg(str);
        HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
		if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
			entity.setCoreorg("");
		}
      
        
        List<ReportReceivabledebtinfoOuter> list = new ArrayList<ReportReceivabledebtinfoOuter>();
        //查询所有实体类
        list =receivabledebtOuterService.getReportReceivabledebtOuter(entity);

		
//		List<ReportFinancingProjectProgress> entityList=reportFinancingWeekNextService.getExportList(id);
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = null;
		HSSFRow row = null;
		//导出文件名称
		String fileName = "应收债权(外部)明细查询";
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		sheet = wb.createSheet("应收债权(外部)明细查询列表");
		// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
		row = sheet.createRow((int) 0);
		String [] titleArray = {"序号","上报期间","所属核心企业","债权单位名称","欠款单位","欠款金额(万元)","欠款时间","超期金额(万元)","累计收回金额（万元）"};
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
		
		sheet = this.setExcelData(sheet,list,styleList.get(1));

		tool.outputExcel(wb,fileName,response);
		return null;
	}
	
	private HSSFSheet setExcelData(HSSFSheet sheet,List<ReportReceivabledebtinfoOuter> entityList,HSSFCellStyle style) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
				HSSFRow row = null;
				//将数据写入Excel
				// 循环将数据写入Excel
				int t = 1;
				for (ReportReceivabledebtinfoOuter entity:entityList) {
					row = sheet.createRow(t);
					// 创建单元格，设置值
					Cell cell = row.createCell(0);
					cell.setCellStyle(style);
					cell.setCellValue(t++);
					//上报期间
					cell = row.createCell(1);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getYear()+"年"+entity.getWeek()+"周");
					//所属核心企业
					cell = row.createCell(2);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getCoreorgname());
					//债权单位名称
					cell = row.createCell(3);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getOrgname());
					//欠款单位
					cell = row.createCell(4);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getDebtOrgname());
					//欠款金额（万元）
					cell = row.createCell(5);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getLoanMoney());
					//欠款时间
					cell = row.createCell(6);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getLoanTime());
					//超期金额（万元）
					cell = row.createCell(7);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getCqLoanMoney());
                    //累计回收金额(万元)
					cell = row.createCell(8);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getTotalBackMoney());
					
				}
				return sheet;
	}

	/**
	 *   --------------------------------应收债权(内部)明细查询---------------------------------------
	 */
	
	//维护的列表页面
	@RequestMapping("/detaillist")
	public String detaillist(ReportReceivabledebtinfoOuter entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/receivabledebtOuter";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
		//核心业态下拉
		List<HhOrganInfoTreeRelation>  xtxl = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype);
		for(HhOrganInfoTreeRelation xt : xtxl){
			if(Base.BIMF_TOP_NODE_ID.equals(xt.getId().getNnodeId())){
				xt.setVcFullName("全部");
			}
		}
		
		map.put("coreCompSelect", xtxl);
		HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
		if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
			entity.setCoreorg("");
		}
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=receivabledebtOuterService.getReceivabledebtinfoListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/receivabledebtOuter/detaillist");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportReceivabledebtOuter/reportReceivableDebtOuterDetailList";
	}
	
	
	//跳转查看页面
	@RequestMapping("/detailinfoview")
	public String collectinfoview(Integer infoid,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportReceivabledebtinfoOuter entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(infoid==null)
		{	
			entityview=new ReportReceivabledebtinfoOuter();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=receivabledebtOuterService.getReceivabledebtinfobyID(infoid);
		}	
		map.put("entityview", entityview);
		return "/report/reportReceivabledebtOuter/reportReceivableDebtOuterDetailView";
	}
	
	/**
	 *   --------------------------------应收债权(外部)汇总查询---------------------------------------
	 */
	
	//维护的列表页面
	@RequestMapping("/collectlist")
	public String collectlist(ReportReceivabledebtinfoOuter entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/receivabledebtOuter";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
		//核心业态下拉
		List<HhOrganInfoTreeRelation>  xtxl = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype);
		for(HhOrganInfoTreeRelation xt : xtxl){
			if(Base.BIMF_TOP_NODE_ID.equals(xt.getId().getNnodeId())){
				xt.setVcFullName("全部");
			}
		}
		
		map.put("coreCompSelect", xtxl);
		HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
		if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
			entity.setCoreorg("");
		}
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=receivabledebtOuterService.getReceivabledebtinfoCollectList(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/receivabledebtOuter/collectlist");
	    map.put("mapurl", "/shanghai-gzw/receivabledebtOuter");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportReceivabledebtOuter/reportReceivableDebtOuterCollectList";
	}
	//应收债权(外部)汇总查询导出
	@RequestMapping("/collectExport")
	public String collectlistExport(ReportReceivabledebtinfoOuter entity,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException {

        
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//数据权限
        entity.setParentorg(str);
        HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
		if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
			entity.setCoreorg("");
		}
      
        
        List<ReportReceivabledebtinfoOuter> list = new ArrayList<ReportReceivabledebtinfoOuter>();
        //查询所有实体类
        list =receivabledebtOuterService.getReceivabledebtinfoCollectListExport(entity);

		
//			List<ReportFinancingProjectProgress> entityList=reportFinancingWeekNextService.getExportList(id);
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = null;
		HSSFRow row = null;
		//导出文件名称
		String fileName = "应收债权(外部)汇总查询";
		// 设置表头
		ExcelDataTreating tool = new ExcelDataTreating();
		// 创建单元格，设置值表头，设置表头居中
		List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
		sheet = wb.createSheet("应收债权(外部)汇总查询列表");
		// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
		row = sheet.createRow((int) 0);
		String [] titleArray = {"序号","上报期间","所属核心企业","债权单位名称","欠款金额(万元)","超期欠款金额(万元)","预计坏账金额（万元）"};
		row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
		
		sheet = this.setExcelDatas(sheet,list,styleList.get(1));

		tool.outputExcel(wb,fileName,response);
		return null;
	}
	private HSSFSheet setExcelDatas(HSSFSheet sheet,List<ReportReceivabledebtinfoOuter> entityList,HSSFCellStyle style) {
		// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
				HSSFRow row = null;
				//将数据写入Excel
				// 循环将数据写入Excel
				int t = 1;
				for (ReportReceivabledebtinfoOuter entity:entityList) {
					row = sheet.createRow(t);
					// 创建单元格，设置值
					Cell cell = row.createCell(0);
					cell.setCellStyle(style);
					cell.setCellValue(t++);
					//上报期间
					cell = row.createCell(1);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getYear()+"年"+entity.getWeek()+"周");
					//所属核心企业
					cell = row.createCell(2);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getCoreorgname());
					//债权单位名称
					cell = row.createCell(3);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getOrgname());
					//欠款金额（万元）
					cell = row.createCell(4);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getLoanMoney());
					//超期欠款金额（万元）
					cell = row.createCell(5);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getCqLoanMoney());
                    //预计坏账金额(万元)
					cell = row.createCell(6);
					cell.setCellStyle(style);
					cell.setCellValue(entity.getYjhz());
					
				}
				return sheet;
	}

	/**
	 *   --------------------------------公司大额应收债权(外部)查询---------------------------------------
	 */
	
	//维护的列表页面
		@RequestMapping("/orglist")
		public String orglist(ReportReceivabledebtinfoOuter entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			String mapurl=request.getContextPath()+ "/receivabledebtOuter";
			map.put("mapurl", mapurl);
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
			//核心业态下拉
			List<HhOrganInfoTreeRelation>  xtxl = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype);
			for(HhOrganInfoTreeRelation xt : xtxl){
				if(Base.BIMF_TOP_NODE_ID.equals(xt.getId().getNnodeId())){
					xt.setVcFullName("全部");
				}
			}
			
			map.put("coreCompSelect", xtxl);
			HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
			if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
				entity.setCoreorg("");
			}
	        Integer pageNum = Integer.valueOf(curPageNum);
	        //数据权限
	        entity.setParentorg(str);
	        List<ReportReceivabledebtinfoOuter> resultList=receivabledebtOuterService.getReceivabledebtinfoOrgList(entity,pageNum,Base.pagesize);
		    map.put("resultList", resultList);
		    map.put("searchurl", "/shanghai-gzw/receivabledebtOuter/orglist");
		    map.put("entityview", entity);
			addData(map);
			return "/report/reportReceivabledebtOuter/orgreportReceivableDebtOuterList";
		}
		
		//公司大额应收债权(外部)查询导出
		@RequestMapping("/orgExport")
		public String receivabledebtinfoOrgExport(ReportReceivabledebtinfoOuter entity,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException {

	        
			HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			//数据权限
	        entity.setParentorg(str);
	        HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
			if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
				entity.setCoreorg("");
			}
	      
	        
	        List<ReportReceivabledebtinfoOuter> list = new ArrayList<ReportReceivabledebtinfoOuter>();
	        //查询所有实体类
	        list =receivabledebtOuterService.getReceivabledebtinfoOrgExport(entity);

			
//				List<ReportFinancingProjectProgress> entityList=reportFinancingWeekNextService.getExportList(id);
			// 1.创建一个workbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
			HSSFSheet sheet = null;
			HSSFRow row = null;
			//导出文件名称
			String fileName = "公司大额应收债权(外部)查询";
			// 设置表头
			ExcelDataTreating tool = new ExcelDataTreating();
			// 创建单元格，设置值表头，设置表头居中
			List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
			sheet = wb.createSheet("公司大额应收债权(外部)查询列表");
			// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
			row = sheet.createRow((int) 0);
			String [] titleArray = {"序号","上报期间","所属核心企业","债权单位名称","债务单位名称","催收责任人","欠款金额(万元)","超期欠款金额（万元）"};
			row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
			
			sheet = this.setExcelDatass(sheet,list,styleList.get(1));

			tool.outputExcel(wb,fileName,response);
			return null;
		}
		private HSSFSheet setExcelDatass(HSSFSheet sheet,List<ReportReceivabledebtinfoOuter> entityList,HSSFCellStyle style) {
			// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
					HSSFRow row = null;
					//将数据写入Excel
					// 循环将数据写入Excel
					int t = 1;
					for (ReportReceivabledebtinfoOuter entity:entityList) {
						row = sheet.createRow(t);
						// 创建单元格，设置值
						Cell cell = row.createCell(0);
						cell.setCellStyle(style);
						cell.setCellValue(t++);
						//上报期间
						cell = row.createCell(1);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getYear()+"年"+entity.getWeek()+"周");
						//所属核心企业
						cell = row.createCell(2);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getCoreorgname());
						//债权单位名称
						cell = row.createCell(3);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getOrgname());
						//债务单位名称
						cell = row.createCell(4);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getDebtOrgname());
						//催收责任人
						cell = row.createCell(5);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getCuishouPerson());
						//欠款金额（万元）
						cell = row.createCell(6);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getLoanMoney());
						//超期欠款金额（万元）
						cell = row.createCell(7);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getCqLoanMoney());
						
					}
					return sheet;
		}
		
		/**
		 *   --------------------------------核心企业大额应收债权(外部)查询---------------------------------------
		 */
		//维护的列表页面
		@RequestMapping("/coreorglist")
		public String coreorglist(ReportReceivabledebtinfoOuter entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			String mapurl=request.getContextPath()+ "/receivabledebtOuter";
			map.put("mapurl", mapurl);
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
			//核心业态下拉
			List<HhOrganInfoTreeRelation>  xtxl = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype);
			for(HhOrganInfoTreeRelation xt : xtxl){
				if(Base.BIMF_TOP_NODE_ID.equals(xt.getId().getNnodeId())){
					xt.setVcFullName("全部");
				}
			}
			
			map.put("coreCompSelect", xtxl);
			HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
			if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
				entity.setCoreorg("");
			}
	        Integer pageNum = Integer.valueOf(curPageNum);
	        //数据权限
	        entity.setParentorg(str);
	        List<ReportReceivabledebtinfoOuter> resultList=receivabledebtOuterService.getReceivabledebtinfoOrgList(entity,pageNum,Base.pagesize);
	        map.put("resultList", resultList);
		    map.put("searchurl", "/shanghai-gzw/receivabledebtOuter/coreorglist");
		    map.put("exporturl", "/shanghai-gzw/receivabledebtOuter/coreorgexport");
		    map.put("entityview", entity);
			addData(map);
			return "/report/reportReceivabledebtOuter/coreOrgreportReceivableDebtOuterList";
		}
		//核心企业大额应收债权(外部)查询导出
		@RequestMapping("/coreorgexport")
		public String receivabledebtinfoCoreOrgExport(ReportReceivabledebtinfoOuter entity,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException {

	        
			HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			//数据权限
	        entity.setParentorg(str);
	        HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
			if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
				entity.setCoreorg("");
			}
	      
	        
	        List<ReportReceivabledebtinfoOuter> list = new ArrayList<ReportReceivabledebtinfoOuter>();
	        //查询所有实体类
	        list =receivabledebtOuterService.getReceivabledebtinfoOrgExport(entity);

			
//						List<ReportFinancingProjectProgress> entityList=reportFinancingWeekNextService.getExportList(id);
			// 1.创建一个workbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
			HSSFSheet sheet = null;
			HSSFRow row = null;
			//导出文件名称
			String fileName = "核心企业大额应收债权(外部)查询";
			// 设置表头
			ExcelDataTreating tool = new ExcelDataTreating();
			// 创建单元格，设置值表头，设置表头居中
			List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
			sheet = wb.createSheet("核心企业大额应收债权(外部)查询");
			// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
			row = sheet.createRow((int) 0);
			String [] titleArray = {"序号","上报期间","所属核心企业","债权单位名称","债务单位名称","催收责任人","欠款金额(万元)","超期欠款金额（万元）"};
			row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
			
			sheet = this.setExcelDatass(sheet,list,styleList.get(1));

			tool.outputExcel(wb,fileName,response);
			return null;
		}
		
		/**
		 *   --------------------------------超期外部应收账款无催收进展一览---------------------------------------
		 */
		
		//维护的列表页面
		@RequestMapping("/overoutlist")
		public String overoutlist(ReportReceivabledebtinfoOuter entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			String mapurl=request.getContextPath()+ "/receivabledebtOuter";
			map.put("mapurl", mapurl);
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
			//核心业态下拉
			List<HhOrganInfoTreeRelation>  xtxl = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype);
			for(HhOrganInfoTreeRelation xt : xtxl){
				if(Base.BIMF_TOP_NODE_ID.equals(xt.getId().getNnodeId())){
					xt.setVcFullName("全部");
				}
			}
			
			map.put("coreCompSelect", xtxl);
			HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
			if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
				entity.setCoreorg("");
			}
	        Integer pageNum = Integer.valueOf(curPageNum);
	        //数据权限
	        entity.setParentorg(str);
	        MsgPage msgPage=receivabledebtOuterService.getOverOutList(entity,pageNum,Base.pagesize);
		    map.put("msgPage", msgPage);
		    map.put("searchurl", "/shanghai-gzw/receivabledebtOuter/overoutlist");
		    map.put("entityview", entity);
			addData(map);
			return "/report/reportReceivabledebtOuter/overOutList";
		}
		//核心企业大额应收债权(外部)查询导出
		@RequestMapping("/overoutexport")
		public String receivabledebtinfooveroutExport(ReportReceivabledebtinfoOuter entity,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException {

	        
			HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			//数据权限
	        entity.setParentorg(str);
	        HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
			if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
				entity.setCoreorg("");
			}
	      
	        
	        List<ReportReceivabledebtinfoOuter> list = new ArrayList<ReportReceivabledebtinfoOuter>();
	        //查询所有实体类
	        list =receivabledebtOuterService.getOverOutListExport(entity);

			
//								List<ReportFinancingProjectProgress> entityList=reportFinancingWeekNextService.getExportList(id);
			// 1.创建一个workbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
			HSSFSheet sheet = null;
			HSSFRow row = null;
			//导出文件名称
			String fileName = "超期外部应收账款无催收进展一览";
			// 设置表头
			ExcelDataTreating tool = new ExcelDataTreating();
			// 创建单元格，设置值表头，设置表头居中
			List<HSSFCellStyle> styleList = tool.setExcelStyle(wb);
			sheet = wb.createSheet("超期外部应收账款无催收进展一览查询");
			// 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
			row = sheet.createRow((int) 0);
			String [] titleArray = {"序号","上报期间","所属核心业态","债权单位名称","超期欠款金额（万元)","未催收原因","回款计划","催收责任人"};
			row = tool.setBondExcelTitle(styleList.get(0),row,titleArray);
			
			sheet = this.setExcelDatasss(sheet,list,styleList.get(1));

			tool.outputExcel(wb,fileName,response);
			return null;
		}
		private HSSFSheet setExcelDatasss(HSSFSheet sheet,List<ReportReceivabledebtinfoOuter> entityList,HSSFCellStyle style) {
			// 遍历实体选择// num: 0未选择/1筹资流入/2筹资流出/3投资流出
					HSSFRow row = null;
					//将数据写入Excel
					// 循环将数据写入Excel
					int t = 1;
					for (ReportReceivabledebtinfoOuter entity:entityList) {
						row = sheet.createRow(t);
						// 创建单元格，设置值
						Cell cell = row.createCell(0);
						cell.setCellStyle(style);
						cell.setCellValue(t++);
						//上报期间
						cell = row.createCell(1);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getYear()+"年"+entity.getWeek()+"周");
						//所属核心业态
						cell = row.createCell(2);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getCoreorgname());
						//债权单位名称
						cell = row.createCell(3);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getOrgname());
						//超期欠款金额（万元）
						cell = row.createCell(4);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getCqLoanMoney());
						//未催收原因
						cell = row.createCell(5);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getNoCuishoureason());
						//回款计划
						cell = row.createCell(6);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getHkjh());
						//催收负责人）
						cell = row.createCell(7);
						cell.setCellStyle(style);
						cell.setCellValue(entity.getCuishouPerson());
						
					}
					return sheet;
		}
		//数据导出
		@RequestMapping("/reportExport")
		public void reportExport(ReportReceivabledebtOuter entityview,HttpServletRequest request,HttpServletResponse response,
													Map<String, Object> map) throws IOException, ParsePropertyException, InvalidFormatException {
			            
			String mapurl=request.getContextPath()+ "/receivabledebt";
			map.put("mapurl", mapurl);
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
	        Integer pageNum = Integer.valueOf(curPageNum);
	        //数据权限
	        entityview.setParentorg(str);
	        
	        MsgPage msgPage=receivabledebtOuterService.getReceivabledebtListView(entityview,pageNum,Base.pagesize);
	        
	        List<ReportReceivabledebtOuter> listOuterBean  = msgPage.getList();
	        List<ReportReceivabledebtinfoOuter> listInfoOuterBean  = new ArrayList<ReportReceivabledebtinfoOuter>();
	        
	        for(ReportReceivabledebtOuter beanTemp:listOuterBean){	 
	        	if(beanTemp.getId() != null){
	        		beanTemp = receivabledebtOuterService.getReceivabledebtbyID(beanTemp.getId());
	        		listInfoOuterBean.addAll(beanTemp.getInfolist());        		        		
	        	}
	        }
	             
	        receivabledebtOuterService.getExcelData(listInfoOuterBean,response);
		}
}
