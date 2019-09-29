package com.softline.controller.report;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsOrganal;
import com.softline.entity.ReportInspectProject;
import com.softline.entity.ReportKeywork;
import com.softline.entity.ReportPersonalloan;
import com.softline.entity.ReportPersonalloanNearMonth;
import com.softline.entity.ReportPersonalloanNearweek;
import com.softline.entity.ReportPersonalloanNearweekDetail;
import com.softline.entity.ReportPersonalloanNew;
import com.softline.entity.ReportPersonlloaninfo;
import com.softline.entity.ReportPersonlloaninfoNew;
import com.softline.entity.SysExamine;
import com.softline.entity.SysExamineReport;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportPersonalLoanNewService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.DateUtils;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportpersonalLoanNew")
public class ReportPersonalLoanNewController {
	
	@Resource(name = "reportPersonalLoanNewService")
	private IReportPersonalLoanNewService reportPersonalLoanNewService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			ReportPersonalloanNew entityview=reportPersonalLoanNewService.getPersonalLoanNewbyID(id);
			map.put("reportPersonalloanNew", entityview);
		}
	}
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportPersonalloanNew entity,String organalID, HttpServletRequest request ,Map<String, Object> map,String weekStart,String weekEnd) throws IOException {
		String mapurl=request.getContextPath()+ "/reportpersonalLoanNew";
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
        entity.setOrg(organalID);
        MsgPage msgPage=reportPersonalLoanNewService.getPersonalloanNewListView(entity,pageNum,Base.pagesize,weekStart,weekEnd);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportpersonalLoanNew/list");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportPersonalLoanNew/reportPersonalLoanList";
	}
	

	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportPersonalloanNew entity,String organalID,HttpServletRequest request ,Map<String, Object> map,String weekStart,String weekEnd) throws IOException {
		
		if(weekStart!=null){
			request.setAttribute("weekStart", weekStart);
			
		}
		if(weekEnd!=null){
			
			request.setAttribute("weekEnd", weekEnd);
			
		}
		String mapurl=request.getContextPath()+ "/reportpersonalLoanNew";
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
        entity.setOrg(organalID);
        MsgPage msgPage=reportPersonalLoanNewService.getPersonalloanNewListView(entity,pageNum,Base.pagesize,weekStart,weekEnd);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportpersonalLoanNew/examinelist");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportPersonalLoanNew/reportPersonalLoanExamineList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		
		map.put("examstatustype",examstatustype);
		
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportPersonalloanNew entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
		
		ReportPersonalloanNew entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportPersonalloanNew();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
		}
		else
		{	
			entityview=reportPersonalLoanNewService.getPersonalLoanNewbyID(entity.getId());
			List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_personalloan_1);
			map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);	
		addData(map);
		
		return "/report/reportPersonalLoanNew/reportPersonalLoanNewModify";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportPersonalloanNew reportPersonalloanNew,MultipartFile itemFile,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		int maxWeekOFYear = DateUtils.getMaxWeekNumOfYear(reportPersonalloanNew.getYear());
		if(reportPersonalloanNew==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}else if(reportPersonalloanNew.getWeek() > maxWeekOFYear){
			result=CommitResult.createErrorResult("填写的周数大于改年最大的周数");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			reportPersonalloanNew.setStatus(Base.examstatus_1);
			result= reportPersonalLoanNewService.savePersonalloanNew(reportPersonalloanNew, use,itemFile);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//跳转查看页面
		@RequestMapping("/view")
		public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
			
			ReportPersonalloanNew entityview;
			List<SysExamine> a=new ArrayList<SysExamine>();
			if(id==null)
			{	
				entityview=new ReportPersonalloanNew();
				Date date=new Date();
				entityview.setYear(date.getYear());
			}
			else
			{
				entityview=reportPersonalLoanNewService.getPersonalLoanNewbyID(id);
			    a= examineService.getListExamine(entityview.getId(), Base.examkind_personalloan_1);
			}	
			map.put("entityview", entityview);
			map.put("entityExamineviewlist", a);
			return "/report/reportPersonalLoanNew/reportPersonalLoanView";
		}
		

	
		//查询列表页面的上报，即跳向复核页面
		@RequestMapping(value ="/postexam")
		public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
			ReportPersonalloanNew entityview;
			List<SysExamine> a=new ArrayList<SysExamine>();
			if(id==null)
			{	
				entityview=new ReportPersonalloanNew();
				Date date=new Date();
				entityview.setYear(date.getYear());
			}
			else
			{
				entityview=reportPersonalLoanNewService.getPersonalLoanNewbyID(id);
			    a= examineService.getListExamine(entityview.getId(), Base.examkind_personalloan_1);
			    map.put("entityExamineviewlist", a);
			}	
			map.put("entityview", entityview);
			map.put("op", op);
			return "/report/reportPersonalLoanNew/reportPersonalLoanView";
		}
		
		//查询页面的复核即复核页面的上报功能
		@ResponseBody
		@RequestMapping(value ="/recheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public String _recheck(ReportPersonalloanNew entity,MultipartFile itemFile,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
			CommitResult result=new CommitResult();
			if(entity!=null)
			{
				HttpSession session=request.getSession();
				HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				entity.setRecheckDate(df.format(new Date()));
				entity.setRecheckPersonId(use.getVcEmployeeId());
				entity.setRecheckPersonName(use.getVcName());
				entity.setStatus(Base.examstatus_2);
				result= reportPersonalLoanNewService.savePersonalloanNew(entity, use,itemFile);
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
			CommitResult result=reportPersonalLoanNewService.deletePersonalloanNew(id, use);
			String data="";
			data=JSONArray.toJSONString(result); 			
			return data;
		}
		
		
		
		//审核
		@RequestMapping(value ="/exam")
		public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
			
			ReportPersonalloanNew entityview;
			if(id==null)
			{
				entityview=new ReportPersonalloanNew();
				Date date=new Date();
				entityview.setYear(date.getYear());
			}
			else
				entityview=reportPersonalLoanNewService.getPersonalLoanNewbyID(id);
			map.put("entityview", entityview);	
			return "/report/reportPersonalLoanNew/reportPersonalLoanExamine";
		}
		
//		*******跳转审核页面******
		
		
//		//审核
//		@RequestMapping(value ="/exam")
//		public String PerSonBorrow(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
//			
//			ReportPersonalloanNew entityview;
//			if(id==null)
//			{
//				entityview=new ReportPersonalloanNew();
//				Date date=new Date();
//			}
//			else
//				entityview=reportPersonalLoanNewService.getPersonalLoanNewbyID(id);
//			map.put("entityview", entityview);	
//			return "/report/reportpersonalLoanNew/reportPersonalLoanExamine";
//			
//		}
		

		//审核提交
		@ResponseBody
		@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

			CommitResult result= reportPersonalLoanNewService.savePersonalloanNewExamine(entityid, examStr, examResult, use);
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
		public String _nodeViewList(ReportPersonalloanNew entity,Map<String, Object> map,HttpServletRequest request){
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        Integer pageNum = Integer.valueOf(curPageNum);
			MsgPage msgPage =  reportPersonalLoanNewService.getPersonalloanInfoNewListView(entity, Integer.parseInt(curPageNum), Base.pagesize);
			map.put("msgPage", msgPage);
			String view = request.getParameter("view");
			map.put("view", view);
			return "/report/reportPersonalLoanNew/reportPersonalLoanInfoList";
		}
		
		@ResponseBody
		@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public String hasCheck(ReportPersonalloanNew entity,String operate,HttpServletRequest request ,Map<String, Object> map){
			CommitResult result=new CommitResult();
			result.setResult(true);
			if(entity==null)
			{
				result= CommitResult.createErrorResult("该数据已被删除");
			}
			return JSONArray.toJSONString(result);
		}
		
		
		/**
		 *   --------------------------------公司员工借款相邻月份差异比较查询---------------------------------------
		 */
		
		//维护的列表页面
		@RequestMapping("/nearweekcompare")
		public String _nearmonthcompare(ReportPersonalloanNearweek entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			String mapurl=request.getContextPath()+ "/reportpersonalLoanNew";
			map.put("mapurl", mapurl);
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			//数据权限
	    	//核心业态下拉
			List<HhOrganInfoTreeRelation>  xtxl = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype,str);
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
	        MsgPage msgPage=reportPersonalLoanNewService.getPersonalloanNearWeekDetailList(entity,pageNum,Base.pagesize);
	        map.put("nearweeks", reportPersonalLoanNewService.getPersonalloanNearWeekList(entity));
		    map.put("msgPage", msgPage);
		    map.put("searchurl", "/shanghai-gzw/reportpersonalLoanNew/nearweekcompare");
		    map.put("entityview", entity);
			return "/report/reportPersonalLoanNew/nearWeekCompareList";
		}
		
		@ResponseBody
		@RequestMapping(value ="/getcompanyName", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public String getcompanyName(String parentID,HttpServletRequest request ,Map<String, Object> map){
			List<HhOrganInfoTreeRelation> hhOrganInfoTreeRelation = new ArrayList<HhOrganInfoTreeRelation>();
			if (Common.notEmpty(parentID)) {
				 hhOrganInfoTreeRelation = systemService.getAllChildrenTreeOrganInfos(Base.financetype, parentID);
			}
			return JSONArray.toJSONString(hhOrganInfoTreeRelation);
		}
		
		
		/**
		 *   --------------------------------个人超期信息一览---------------------------------------
		 */
		//维护的列表页面
		@RequestMapping("/personnalovertimeDetail")
		public String personnalovertimeDetail(ReportPersonlloaninfoNew entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			String mapurl=request.getContextPath()+ "/reportpersonalLoanNew";
			map.put("mapurl", mapurl);
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			//数据权限
			//核心业态下拉
			List<HhOrganInfoTreeRelation>  xtxl = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype,str);
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
	        MsgPage msgPage=reportPersonalLoanNewService.getPersonnalovertimeDetail(entity,pageNum,Base.pagesize);
		    map.put("msgPage", msgPage);
		    map.put("searchurl", "/shanghai-gzw/reportpersonalLoanNew/personnalovertimeDetail");
		    map.put("entityview", entity);
			return "/report/reportPersonalLoanNew/personnalOverTimeDetailList";
		}
		
		//跳转查看页面
		@RequestMapping("/personnalovertimeview")
		public String personnalovertimeview(Integer overtimeid,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			ReportPersonlloaninfoNew entityview = (ReportPersonlloaninfoNew) commonService.findById(ReportPersonlloaninfoNew.class,overtimeid);
			map.put("entityview", entityview);
			return "/report/reportPersonalLoanNew/personnalOverTimeDetailView";
		}
		
		
		
		/**
		 *   --------------------------------个人借款信息汇总查询---------------------------------------
		 */

		//维护的列表页面
			@RequestMapping("/coreComovertimeDetail")
			public String coreComovertimeDetail(ReportPersonlloaninfoNew entity,HttpServletRequest request ,Map<String, Object> map,String yearStart,String yearEnd) throws IOException {
				if(yearStart!=null){
					request.setAttribute("yearStart", yearStart);
					
				}
				if(yearEnd!=null){
					
					request.setAttribute("yearEnd", yearEnd);
					
				}
				
				String mapurl=request.getContextPath()+ "/reportpersonalLoanNew";
				map.put("mapurl", mapurl);
				String curPageNum = request.getParameter("pageNums");
		        if (curPageNum == null) {
		        	curPageNum = "1";
		        }
		        HttpSession session=request.getSession();
				String str=(String) session.getAttribute("gzwsession_financecompany");
				//数据权限
				//核心业态下拉
				List<HhOrganInfoTreeRelation>  xtxl = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype,str);
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
		        MsgPage msgPage=reportPersonalLoanNewService.getcoreComovertimeDetail(entity,pageNum,Base.pagesize,yearStart,yearEnd);
			    map.put("msgPage", msgPage);
			    map.put("searchurl", "/shanghai-gzw/reportpersonalLoanNew/coreComovertimeDetail");
			    map.put("entityview", entity);
				return "/report/reportPersonalLoanNew/coreComOverTimeDetailList";
			}
		
			@ResponseBody
			@RequestMapping(value ="/getMaxWeekInYear", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
			public String getMaxWeekInYear(Integer year,HttpServletRequest request ,Map<String, Object> map){
				return DateUtils.getMaxWeekNumOfYear(year) + "";
			}
	
			
			/**
			 * 个人借款填报导出功能
			 * @param entity
			 * @param request
			 * @param map
			 * @param response
			 * @throws Exception
			 */
			@RequestMapping("grjktbExprot")
			public void grjktbExprot(ReportPersonlloaninfoNew entity,String organalID,HttpServletRequest request, Map<String, Object> map,HttpServletResponse response,String weekStart,String weekEnd) throws Exception{
				HttpSession session=request.getSession();
				String str=(String) session.getAttribute("gzwsession_financecompany");
		        //数据权限
		        entity.setParentorg(str);
		        entity.setOrg(organalID);
		        List<ReportPersonlloaninfoNew> list = reportPersonalLoanNewService.getPersonlloaninfoNewList(entity,weekStart,weekEnd);
				XSSFWorkbook workBook = new XSSFWorkbook();
				// 首先我们需要创建一个工作簿
				XSSFCell cell = null;
				// 设置单元格为空
				XSSFSheet sheet = workBook.createSheet();
				// 定义表名为123
				XSSFRow row = sheet.createRow((int) 0);
				
				String[] header = {"序号","上报期间","所属核心企业","公司名称","欠款人员工编号","欠款人","欠款类别","欠款类型","具体事由","欠款金额(万元)","欠款时间","已借款期限(月)","是否催收","是否进入法律程序","本周回收金额(万元)","累计回收金额(万元)","预计坏账","催收责任人","进展描述","备注","借款公文号"};
				
				for(int i = 0; i < header.length; i++) {
					row.createCell(i).setCellValue(header[i]);
				}

				for(int i = 0; i < list.size(); i++){
					ReportPersonlloaninfoNew item = list.get(i);
					row = sheet.createRow(i+1);
					int k = 0;
					row.createCell(k).setCellValue((i+1)+"");k++; //序号
					row.createCell(k).setCellValue(item.getYear()+"年第" + item.getWeek() + "周");k++; //上报期间
					row.createCell(k).setCellValue(item.getCoreorgname());k++; //所属核心企业
					row.createCell(k).setCellValue(item.getOrgname());k++; //公司名称
					row.createCell(k).setCellValue(item.getRespersonAccount());k++; //欠款人员工编号
					row.createCell(k).setCellValue(item.getResponsibleperson());k++; //欠款人
					row.createCell(k).setCellValue(item.getLoanType1Name());k++; //欠款类别
					row.createCell(k).setCellValue(item.getLoanType2Name());k++; //欠款类型
					row.createCell(k).setCellValue(item.getUseway());k++; //具体事由
					row.createCell(k).setCellValue(item.getLoanmoney());k++; //欠款金额(万元)
					row.createCell(k).setCellValue(item.getLoantime());k++; //欠款时间
					row.createCell(k).setCellValue(item.getLoanmonth());k++; //已借款期限(月)
					row.createCell(k).setCellValue((item.getIsCuishou()!= null && item.getIsCuishou()==1)?"是":"否");k++; //是否催收
					row.createCell(k).setCellValue((item.getIsInLaw()!= null && item.getIsInLaw()==1)?"是":"否");k++; //序号
					row.createCell(k).setCellValue(item.getWeekBackMoney());k++; //本周回收金额(万元)
					row.createCell(k).setCellValue(item.getTotalBackMoney());k++; //累计回收金额
					row.createCell(k).setCellValue(item.getYjhz());k++; //预计坏账
					row.createCell(k).setCellValue(item.getCszrr());k++; //催收责任人
					row.createCell(k).setCellValue(item.getZjms());k++; //进展描述
					row.createCell(k).setCellValue(item.getRemark());k++; //备注
					row.createCell(k).setCellValue(item.getLoannum());k++; //借款公文号
				}
				
				// 清空response
				response.reset();
				String _name = "个人借款详细.xlsx";
				OutputStream toClient = new BufferedOutputStream(
						response.getOutputStream());
				String codedfilename = null;
				String agent = request.getHeader("USER-AGENT");
				if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
						&& -1 != agent.indexOf("Trident")) {// ie

					String name = java.net.URLEncoder.encode(_name, "UTF8");

					codedfilename = name;
				} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

					codedfilename = new String(_name.getBytes("UTF-8"), "iso-8859-1");
				}
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename="
						+ codedfilename);
				workBook.write(toClient);
				toClient.flush();
				toClient.close();
			}
			
	/**
	 * 
	 * @Description 
	 * 				公司员工借款相邻月份差异比较查询页面的导出功能
	 * 				页面名称：nearWeekCompareList.jsp
	 * @author S.H.T
	 * @date 2018-5-25上午11:02:04
	 * @param entity
	 * @param request
	 * @param map
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("nearweekcompareExport")		
	public void nearweekcompareExport(ReportPersonalloanNearweek entity,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//数据权限
    	//核心业态下拉
		List<HhOrganInfoTreeRelation>  xtxl = systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype,str);
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
		 //数据权限
        entity.setParentorg(str);
        List<ReportPersonalloanNearweekDetail> list=reportPersonalLoanNewService.getPersonalloanNearWeekDetailList(entity);
        List<ReportPersonalloanNearweek>  nearweek = reportPersonalLoanNewService.getPersonalloanNearWeekList(entity);
		
        
        XSSFWorkbook workBook = new XSSFWorkbook();
		// 首先我们需要创建一个工作簿
		XSSFCell cell = null;
		// 设置单元格为空
		XSSFSheet sheet = workBook.createSheet();
		// 定义表名为123
		XSSFRow row = sheet.createRow((int) 0);
		row.createCell(0).setCellValue("整体情况列表");
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,9)); 
		String[] header = {"序号","上报期间","所属核心企业","借款人总额(万元)","借款人数(人)","借款超期人数(人)","超期金额(万元)","本周新增借款人数(人)","本周还完借款人数(人)"};
		row = sheet.createRow(1);
		for(int i = 0; i < header.length; i++) {
			row.createCell(i).setCellValue(header[i]);
		}
		for(int i = 0; i < nearweek.size(); i++){
			ReportPersonalloanNearweek item = nearweek.get(i);
			row = sheet.createRow(i+2);
			int k = 0;
			row.createCell(k).setCellValue((i+1)+"");k++; //序号
			row.createCell(k).setCellValue(item.getYear()+"年第" + item.getWeek() + "周");k++; //上报期间
			row.createCell(k).setCellValue(item.getCoreorgname());k++; //所属核心企业
			row.createCell(k).setCellValue(item.getLoantotalmoney());k++; //借款人总额(万元)
			row.createCell(k).setCellValue(item.getLoansumperson());k++; //借款人数(人)
			row.createCell(k).setCellValue(item.getLoansumoverperson());k++; //借款超期人数(人)
			row.createCell(k).setCellValue(item.getLoantotalovermoney());k++; //超期金额(万元)
			row.createCell(k).setCellValue(item.getMonthsumaddperson());k++; //本周新增借款人数(人)
			row.createCell(k).setCellValue(item.getMonthsumfinishperson());k++; //本周还完借款人数(人)
		}
		
		row = sheet.createRow(nearweek.size() + 2);
		row.createCell(0).setCellValue("公司员工个人借款增减变动情况");
		sheet.addMergedRegion(new CellRangeAddress(nearweek.size()+2,nearweek.size()+2,0,9)); 
		
		header =new String[]{"序号","上报期间","所属核心企业","公司名称","欠款人员工编号","员工姓名","期初余额(万元)","本周新增(万元)","本周已还(万元)","期末余额(万元)"};
		row = sheet.createRow(nearweek.size() + 3);
		for(int i = 0; i < header.length; i++) {
			row.createCell(i).setCellValue(header[i]);
		}
		for(int i = 0; i < list.size(); i++){
			ReportPersonalloanNearweekDetail item = list.get(i);
			row = sheet.createRow(i+4+nearweek.size());
			int k = 0;
			row.createCell(k).setCellValue((i+1)+"");k++; //序号
			row.createCell(k).setCellValue(item.getYear()+"年第" + item.getWeek() + "周");k++; //上报期间
			row.createCell(k).setCellValue(item.getCoreorgname());k++; //所属核心企业
			row.createCell(k).setCellValue(item.getOrgname());k++; //公司名称
			row.createCell(k).setCellValue(item.getPersonAccount());k++; //欠款人员工编号
			row.createCell(k).setCellValue(item.getPersonname());k++; //员工姓名
			row.createCell(k).setCellValue(item.getBeginningbalance());k++; //期初余额(万元)
			row.createCell(k).setCellValue(item.getMonthaddmoney());k++; //本周新增(万元)
			row.createCell(k).setCellValue(item.getMonthreturnmoney());k++; //本周已还(万元)
			row.createCell(k).setCellValue(item.getEndingbalance());k++; //期末余额(万元)
		}
		// 清空response
		response.reset();
		String _name = "相邻月份差异比较.xlsx";
		OutputStream toClient = new BufferedOutputStream(
				response.getOutputStream());
		String codedfilename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
				&& -1 != agent.indexOf("Trident")) {// ie

			String name = java.net.URLEncoder.encode(_name, "UTF8");

			codedfilename = name;
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

			codedfilename = new String(_name.getBytes("UTF-8"), "iso-8859-1");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ codedfilename);
		workBook.write(toClient);
		toClient.flush();
		toClient.close();
	}
	
	/**
	 * 个人借款超期信息一览
	 * 页面：personnalOverTimeDetailList.jsp
	 * 导出功能
	 *
	 * @author S.H.T
	 * @date 2018-5-25下午2:04:09
	 * @param entity
	 * @param request
	 * @param map
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("personnalovertimeExport")		
	public void personnalovertimeExport(ReportPersonlloaninfoNew entity,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//数据权限
		HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
		if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
			entity.setCoreorg("");
		}
        //数据权限
        entity.setParentorg(str);
        List<ReportPersonlloaninfoNew> list = reportPersonalLoanNewService.getPersonnalovertimeDetail(entity);
        
        XSSFWorkbook workBook = new XSSFWorkbook();
		// 首先我们需要创建一个工作簿
		XSSFCell cell = null;
		// 设置单元格为空
		XSSFSheet sheet = workBook.createSheet();
		// 定义表名为123
		XSSFRow row = sheet.createRow((int) 0);
		String[] header = {"序号","所属核心企业","公司名称","欠款人员工编号","员工姓名","欠款时间","借款金额(万元)","超期金额(万元)"};
		for(int i = 0; i < header.length; i++) {
			row.createCell(i).setCellValue(header[i]);
		}
		for(int i = 0; i < list.size(); i++){
			ReportPersonlloaninfoNew item = list.get(i);
			row = sheet.createRow(i+1);
			int k = 0;
			row.createCell(k).setCellValue((i+1)+"");k++; //序号
			row.createCell(k).setCellValue(item.getCoreorgname());k++; //所属核心企业
			row.createCell(k).setCellValue(item.getOrgname());k++; //公司名称
			row.createCell(k).setCellValue(item.getRespersonAccount());k++; //欠款人员工编号
			row.createCell(k).setCellValue(item.getResponsibleperson());k++; //员工姓名
			row.createCell(k).setCellValue(item.getLoantime());k++; //欠款时间
			row.createCell(k).setCellValue(item.getLoanmoney());k++; //借款金额(万元)
			row.createCell(k).setCellValue(item.getLoanovermoney());k++; //超期金额(万元)
		}
		
		
		// 清空response
		response.reset();
		String _name = "个人借款超期信息一览.xlsx";
		OutputStream toClient = new BufferedOutputStream(
				response.getOutputStream());
		String codedfilename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
				&& -1 != agent.indexOf("Trident")) {// ie

			String name = java.net.URLEncoder.encode(_name, "UTF8");

			codedfilename = name;
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

			codedfilename = new String(_name.getBytes("UTF-8"), "iso-8859-1");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ codedfilename);
		workBook.write(toClient);
		toClient.flush();
		toClient.close();
	}
	
	/**
	 * 个人借款信息汇总  页面:coreComOverTimeDetailList.jsp
	 * 导出功能
	 * @author S.H.T
	 * @date 2018-5-25下午2:24:55
	 * @param entity
	 * @param request
	 * @param map
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("coreComOvertimeExport")		
	public void coreComOvertimeExport(ReportPersonlloaninfoNew entity,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response,String yearStart,String yearEnd) throws IOException {
	
//		if(yearStart!=null){
//			request.setAttribute("yearStart", yearStart);
//			
//		}
//		if(yearEnd!=null){
//			
//			request.setAttribute("yearEnd", yearEnd);
//			
//		}
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//数据权限
		HhOrganInfoTreeRelation mm = systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreorg());
		if(mm != null && mm.getId().getNnodeId().equals(Base.BIMF_TOP_NODE_ID)){
			entity.setCoreorg("");
		}
        //数据权限
        entity.setParentorg(str);
        List<ReportPersonalloanNearweek> list = reportPersonalLoanNewService.getcoreComovertimeDetail(entity,yearStart,yearEnd);
        
        XSSFWorkbook workBook = new XSSFWorkbook();
		// 首先我们需要创建一个工作簿
		XSSFCell cell = null;
		// 设置单元格为空
		XSSFSheet sheet = workBook.createSheet();
		// 定义表名为123
		XSSFRow row = sheet.createRow((int) 0);
		String[] header = {"序号","上报期间","所属核心企业","公司名称","借款金额合计(万元)","超期金额合计(万元)"};
		for(int i = 0; i < header.length; i++) {
			row.createCell(i).setCellValue(header[i]);
		}
		for(int i = 0; i < list.size(); i++){
			ReportPersonalloanNearweek item = list.get(i);
			row = sheet.createRow(i+1);
			int k = 0;
			row.createCell(k).setCellValue((i+1)+"");k++; //序号
			row.createCell(k).setCellValue(item.getYear()+"年第" + item.getWeek() + "周");k++; //上报期间
			row.createCell(k).setCellValue(item.getCoreorgname());k++; //所属核心企业
			row.createCell(k).setCellValue(item.getOrgname());k++; //公司名称
			row.createCell(k).setCellValue(item.getLoantotalmoney());k++; //借款金额合计(万元)
			row.createCell(k).setCellValue(item.getLoantotalovermoney());k++; //超期金额合计(万元)
		}
		
		
		// 清空response
		response.reset();
		String _name = "个人借款信息汇总.xlsx";
		OutputStream toClient = new BufferedOutputStream(
				response.getOutputStream());
		String codedfilename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
				&& -1 != agent.indexOf("Trident")) {// ie

			String name = java.net.URLEncoder.encode(_name, "UTF8");

			codedfilename = name;
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

			codedfilename = new String(_name.getBytes("UTF-8"), "iso-8859-1");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ codedfilename);
		workBook.write(toClient);
		toClient.flush();
		toClient.close();
	}

}
