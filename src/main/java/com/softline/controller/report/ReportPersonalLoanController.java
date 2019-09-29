package com.softline.controller.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.softline.entity.ReportPersonalloan;
import com.softline.entity.ReportPersonalloanNearMonth;
import com.softline.entity.ReportPersonalloanNearMonthDetail;
import com.softline.entity.ReportPersonlloaninfo;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportPersonalLoanService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportpersonalLoan")
public class ReportPersonalLoanController {
	
	@Resource(name = "reportPersonalLoanService")
	private IReportPersonalLoanService reportPersonalLoanService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			ReportPersonalloan entityview=reportPersonalLoanService.getPersonalLoanbyID(id);
			map.put("reportPersonalloan", entityview);
		}
	}
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportPersonalloan entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportpersonalLoan";
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
        MsgPage msgPage=reportPersonalLoanService.getPersonalloanListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportpersonalLoan/list");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportPersonalLoan/reportPersonalLoanList";
	}
	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportPersonalloan entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportpersonalLoan";
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
        MsgPage msgPage=reportPersonalLoanService.getPersonalloanListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportpersonalLoan/examinelist");
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportPersonalLoan/reportPersonalLoanExamineList";
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		
		map.put("examstatustype",examstatustype);
		
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportPersonalloan entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
		
		ReportPersonalloan entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportPersonalloan();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
		}
		else
		{	
			entityview=reportPersonalLoanService.getPersonalLoanbyID(entity.getId());
			List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_personalloan);
			map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);	
		addData(map);
		
		return "/report/reportPersonalLoan/reportPersonalLoanNewModify";
	}
	
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportPersonalloan reportPersonalloan,MultipartFile itemFile,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(reportPersonalloan==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			reportPersonalloan.setStatus(Base.examstatus_1);
			result= reportPersonalLoanService.savePersonalloan(reportPersonalloan, use,itemFile);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	
	//跳转查看页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportPersonalloan entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportPersonalloan();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportPersonalLoanService.getPersonalLoanbyID(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_personalloan);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/reportPersonalLoan/reportPersonalLoanView";
	}
	
	//查询列表页面的上报，即跳向复核页面
	@RequestMapping(value ="/postexam")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		ReportPersonalloan entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportPersonalloan();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=reportPersonalLoanService.getPersonalLoanbyID(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_personalloan);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		map.put("op", op);
		return "/report/reportPersonalLoan/reportPersonalLoanView";
	}
	
	//查询页面的复核即复核页面的上报功能
	@ResponseBody
	@RequestMapping(value ="/recheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _recheck(ReportPersonalloan entity,MultipartFile itemFile,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			result= reportPersonalLoanService.savePersonalloan(entity, use,itemFile);
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
		CommitResult result=reportPersonalLoanService.deletePersonalloan(id, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportPersonalloan entityview;
		if(id==null)
		{
			entityview=new ReportPersonalloan();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
			entityview=reportPersonalLoanService.getPersonalLoanbyID(id);
		map.put("entityview", entityview);	
		return "/report/reportPersonalLoan/reportPersonalLoanExamine";
	}

	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= reportPersonalLoanService.savePersonalloanExamine(entityid, examStr, examResult, use);
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
	public String _nodeViewList(ReportPersonalloan entity,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  reportPersonalLoanService.getPersonalloanInfoListView(entity, Integer.parseInt(curPageNum), Base.pagesize);
		map.put("msgPage", msgPage);
		String view = request.getParameter("view");
		map.put("view", view);
		//分页
	
		
		return "/report/reportPersonalLoan/reportPersonalLoanInfoList";
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportPersonalloan entity,String operate,HttpServletRequest request ,Map<String, Object> map){
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
	@RequestMapping("/nearmonthcompare")
	public String _nearmonthcompare(ReportPersonalloanNearMonth entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportpersonalLoan";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//数据权限
    	//核心业态下拉
		map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype) );

		Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=reportPersonalLoanService.getPersonalloanNearMonthList(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportpersonalLoan/nearmonthcompare");
	    map.put("entityview", entity);
		return "/report/reportPersonalLoan/nearMonthCompareList";
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
	public String personnalovertimeDetail(ReportPersonlloaninfo entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportpersonalLoan";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//数据权限
    	//核心业态下拉
		map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype) );

		Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=reportPersonalLoanService.getPersonnalovertimeDetail(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportpersonalLoan/personnalovertimeDetail");
	    map.put("entityview", entity);
		return "/report/reportPersonalLoan/personnalOverTimeDetailList";
	}
	
	//跳转查看页面
	@RequestMapping("/personnalovertimeview")
	public String personnalovertimeview(Integer overtimeid,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		ReportPersonlloaninfo entityview = reportPersonalLoanService.getPersonlloaninfobyId(overtimeid);
		map.put("entityview", entityview);
		return "/report/reportPersonalLoan/personnalOverTimeDetailView";
	}
	
	

	/**
	 *   --------------------------------个人借款信息汇总查询---------------------------------------
	 */

	//维护的列表页面
		@RequestMapping("/coreComovertimeDetail")
		public String coreComovertimeDetail(ReportPersonlloaninfo entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			String mapurl=request.getContextPath()+ "/reportpersonalLoan";
			map.put("mapurl", mapurl);
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			//数据权限
	    	//核心业态下拉
			map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype) );

			Integer pageNum = Integer.valueOf(curPageNum);
	        //数据权限
	        entity.setParentorg(str);
	        MsgPage msgPage=reportPersonalLoanService.getcoreComovertimeDetail(entity,pageNum,Base.pagesize);
		    map.put("msgPage", msgPage);
		    map.put("searchurl", "/shanghai-gzw/reportpersonalLoan/coreComovertimeDetail");
		    map.put("entityview", entity);
			return "/report/reportPersonalLoan/coreComOverTimeDetailList";
		}
	
	
	
}
