package com.softline.controller.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportReceivabledebt;
import com.softline.entity.ReportReceivabledebtinfo;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReceivableDebtService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/receivabledebt")
public class ReportReceivableDebtController {
	
	@Resource(name = "receivabledebtService")
	private IReceivableDebtService receivabledebtService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			ReportReceivabledebt entityview=receivabledebtService.getReceivabledebtbyID(id);
			map.put("reportReceivabledebt", entityview);
		}
	}
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportReceivabledebt entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
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
        entity.setParentorg(str);
        MsgPage msgPage=receivabledebtService.getReceivabledebtListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    if (entity.getType()== 0) {
	    	map.put("searchurl", "/shanghai-gzw/receivabledebt/list?type=0");
		}else {
			map.put("searchurl", "/shanghai-gzw/receivabledebt/list?type=1");
		}
	    
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportReceivabledebt/reportReceivabledebtList";
	}
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportReceivabledebt entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/receivabledebt";
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
        MsgPage msgPage=receivabledebtService.getReceivabledebtListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    if (entity.getType() == 0) {
	    	map.put("searchurl", "/shanghai-gzw/receivabledebt/examinelist?type=0");
		}else {
			map.put("searchurl", "/shanghai-gzw/receivabledebt/examinelist?type=1");
		}
	    
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportReceivabledebt/reportReceivabledebtExamineList";
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
		
		ReportReceivabledebt entityview;
		if(id==null)
		{
			entityview=new ReportReceivabledebt();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
			entityview=receivabledebtService.getReceivabledebtbyID(id);
		map.put("entityview", entityview);	
		return "/report/reportReceivabledebt/reportReceivabledebtExamine";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportReceivabledebt entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
		
		ReportReceivabledebt entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportReceivabledebt();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
			entityview.setType(entity.getType());
		}
		else
		{	
			entityview=receivabledebtService.getReceivabledebtbyID(entity.getId());
			List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_receivabledebtinner);
			map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);	
		addData(map);
		
		return "/report/reportReceivabledebt/reportReceivabledebtNewModify";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(ReportReceivabledebt reportReceivabledebt,MultipartFile itemFile,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(reportReceivabledebt==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			reportReceivabledebt.setStatus(Base.examstatus_1);
			result= receivabledebtService.saveReceivabledebt(reportReceivabledebt, use,itemFile);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//跳转查看页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportReceivabledebt entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportReceivabledebt();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=receivabledebtService.getReceivabledebtbyID(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_receivabledebtinner);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/reportReceivabledebt/reportReceivabledebtView";
	}
	
	//查询列表页面的上报，即跳向复核页面
	@RequestMapping(value ="/postexam")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		ReportReceivabledebt entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportReceivabledebt();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=receivabledebtService.getReceivabledebtbyID(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_receivabledebtinner);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		map.put("op", op);
		return "/report/reportReceivabledebt/reportReceivabledebtView";
	}
	
	
	//查询页面的复核即复核页面的上报功能
	@ResponseBody
	@RequestMapping(value ="/recheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _recheck(ReportReceivabledebt entity,MultipartFile itemFile,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
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
			result= receivabledebtService.saveReceivabledebt(entity, use,itemFile);
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
		CommitResult result=receivabledebtService.deleteReceivabledebt(id, use);
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

		CommitResult result= receivabledebtService.saveReceivabledebtExamine(entityid, examStr, examResult, use);
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
	public String _nodeViewList(ReportReceivabledebt entity,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  receivabledebtService.getReceivabledebtInfoListView(entity, Integer.parseInt(curPageNum), Base.pagesize);
		map.put("msgPage", msgPage);
		String view = request.getParameter("view");
		map.put("view", view);
		//分页
		map.put("type", entity.getType());
		
		return "/report/reportReceivabledebt/reportReceivabledebtInfoList";
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportReceivabledebt entity,String operate,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		result.setResult(true);
		if(entity==null)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}
	
	
	
	/**
	 *   --------------------------------应收债权(内部)明细查询---------------------------------------
	 */
	
	//维护的列表页面
	@RequestMapping("/detaillist")
	public String detaillist(ReportReceivabledebtinfo entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/receivabledebt";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
		//核心业态下拉
		map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype) );
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=receivabledebtService.getReceivabledebtinfoListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    if (entity.getType() == 0) {
	    	 map.put("searchurl", "/shanghai-gzw/receivabledebt/detaillist?type=0");
		}else {
			map.put("searchurl", "/shanghai-gzw/receivabledebt/detaillist?type=1");
		}
	   
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportReceivabledebt/reportReceivableDebtDetailList";
	}
	
	
	//跳转查看页面
	@RequestMapping("/detailinfoview")
	public String collectinfoview(Integer infoid,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportReceivabledebtinfo entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(infoid==null)
		{	
			entityview=new ReportReceivabledebtinfo();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=receivabledebtService.getReceivabledebtinfobyID(infoid);
			Calendar cal = Calendar.getInstance();
			//设置年份
	        cal.set(Calendar.YEAR,entityview.getYear());
	        //设置月份
	        cal.set(Calendar.MONTH, entityview.getMonth()-1);
	        //获取某月最大天数
	        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        entityview.setDay(String.valueOf(lastDay));
		}	
		map.put("entityview", entityview);
		return "/report/reportReceivabledebt/reportReceivableDebtDetailView";
	}
	
	/**
	 *   --------------------------------应收债权(内部)汇总查询---------------------------------------
	 */
	
	//维护的列表页面
	@RequestMapping("/collectlist")
	public String collectlist(ReportReceivabledebtinfo entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/receivabledebt";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
		//核心业态下拉
		map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype) );
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=receivabledebtService.getReceivabledebtinfoCollectList(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    if (entity.getType() == 0) {
	    	map.put("searchurl", "/shanghai-gzw/receivabledebt/collectlist?type=0");
	    }else {
	    	map.put("searchurl", "/shanghai-gzw/receivabledebt/collectlist?type=1");
		}
	    
	    map.put("entityview", entity);
		addData(map);
		return "/report/reportReceivabledebt/reportReceivableDebtCollectList";
	}
	
	
	/**
	 *   --------------------------------公司大额应收债权(内部)查询---------------------------------------
	 */
	
	//维护的列表页面
		@RequestMapping("/orglist")
		public String orglist(ReportReceivabledebtinfo entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			String mapurl=request.getContextPath()+ "/receivabledebt";
			map.put("mapurl", mapurl);
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
			//核心业态下拉
			map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype) );
	        Integer pageNum = Integer.valueOf(curPageNum);
	        //数据权限
	        entity.setParentorg(str);
	        MsgPage msgPage=receivabledebtService.getReceivabledebtinfoOrgList(entity,pageNum,Base.pagesize);
		    map.put("msgPage", msgPage);
		    if (entity.getType() == 0) {
		    	map.put("searchurl", "/shanghai-gzw/receivabledebt/orglist?type=0");
		    }else {
		    	map.put("searchurl", "/shanghai-gzw/receivabledebt/orglist?type=1");
			}
		    
		    map.put("entityview", entity);
			addData(map);
			return "/report/reportReceivabledebt/orgreportReceivableDebtList";
		}
		
		
		/**
		 *   --------------------------------核心企业大额应收债权(内部)查询---------------------------------------
		 */
		//维护的列表页面
		@RequestMapping("/coreorglist")
		public String coreorglist(ReportReceivabledebtinfo entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			String mapurl=request.getContextPath()+ "/receivabledebt";
			map.put("mapurl", mapurl);
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
			//核心业态下拉
			map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype) );
	        Integer pageNum = Integer.valueOf(curPageNum);
	        //数据权限
	        entity.setParentorg(str);
	        MsgPage msgPage=receivabledebtService.getReceivabledebtinfoOrgList(entity,pageNum,Base.pagesize);
		    map.put("msgPage", msgPage);
		    if (entity.getType() == 0) {
		    	map.put("searchurl", "/shanghai-gzw/receivabledebt/coreorglist?type=0");
		    }else {
		    	map.put("searchurl", "/shanghai-gzw/receivabledebt/coreorglist?type=1");
			}
		    
		    map.put("entityview", entity);
			addData(map);
			return "/report/reportReceivabledebt/coreOrgreportReceivableDebtList";
		}
		
		
		/**
		 *   --------------------------------超期外部应收账款无催收进展一览---------------------------------------
		 */
		
		//维护的列表页面
		@RequestMapping("/overoutlist")
		public String overoutlist(ReportReceivabledebtinfo entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			String mapurl=request.getContextPath()+ "/receivabledebt";
			map.put("mapurl", mapurl);
			String curPageNum = request.getParameter("pageNums");
	        if (curPageNum == null) {
	        	curPageNum = "1";
	        }
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
			//核心业态下拉
			map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype) );
	        Integer pageNum = Integer.valueOf(curPageNum);
	        //数据权限
	        entity.setParentorg(str);
	        MsgPage msgPage=receivabledebtService.getOverOutList(entity,pageNum,Base.pagesize);
		    map.put("msgPage", msgPage);
		    if (entity.getType() == 0) {
		    	map.put("searchurl", "/shanghai-gzw/receivabledebt/overoutlist?type=0");
		    }else {
		    	map.put("searchurl", "/shanghai-gzw/receivabledebt/overoutlist?type=1");
			}
		    
		    map.put("entityview", entity);
			addData(map);
			return "/report/reportReceivabledebt/overOutList";
		}
}
